package br.com.emmanuelneri.aggregator.strategy;

import br.com.emmanuelneri.aggregator.interfaces.Aggregator;
import br.com.emmanuelneri.aggregator.interfaces.Aggregable;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class AggregatorBodyListStrategy<T extends Aggregable> implements AggregationStrategy {

    public Exchange aggregate(final Exchange oldExchange, final Exchange newExchange) {
        Aggregator<T> aggregator;

        final T message = getInitialBody(newExchange);
        if(oldExchange == null) {
            aggregator = new Aggregator<>(message.id());
        } else {
            aggregator = getAggregatedBody(oldExchange);
        }

        aggregator.add(message);
        newExchange.getIn().setBody(aggregator);

        return newExchange;
    }

    @SuppressWarnings("unchecked")
    private T getInitialBody(Exchange newExchange) {
        return (T) newExchange.getIn().getBody();
    }

    @SuppressWarnings("unchecked")
    private Aggregator<T> getAggregatedBody(Exchange oldExchange) {
        return (Aggregator<T>) oldExchange.getIn().getBody();
    }
}
