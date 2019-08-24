package br.com.emmanuelneri.aggregator;

import br.com.emmanuelneri.aggregator.converter.OrderConverter;
import br.com.emmanuelneri.aggregator.domain.Order;
import br.com.emmanuelneri.aggregator.domain.OrderItem;
import br.com.emmanuelneri.aggregator.strategy.AggregatorBodyListStrategy;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.language.SimpleExpression;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }

    @Bean
    public RoutesBuilder orderAggregatorRoute() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("kafka:orderItems?brokers=localhost:9092 &groupId=camel-aggregator-consumer")
                        .routeId("fromOrderItemsTopic")
                        .unmarshal().json(JsonLibrary.Jackson, OrderItem.class)
                        .setHeader("aggregateId", new SimpleExpression("${body.orderId}"))
                        .to("direct:start");

                from("direct:start")
                        .aggregate(header("aggregateId"), new AggregatorBodyListStrategy<OrderItem>())
                        .completionInterval(30000)
                        .to("direct:result");

                from("direct:result")
                        .transform().method(OrderConverter.class, "convert")
                        .marshal().json(JsonLibrary.Jackson, Order.class)
                        .to("kafka:orders?brokers=localhost:9092");
            }
        };
    }
}
