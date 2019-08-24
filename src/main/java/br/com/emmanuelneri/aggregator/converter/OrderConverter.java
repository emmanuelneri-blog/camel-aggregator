package br.com.emmanuelneri.aggregator.converter;

import br.com.emmanuelneri.aggregator.domain.Order;
import br.com.emmanuelneri.aggregator.domain.OrderItem;
import br.com.emmanuelneri.aggregator.interfaces.Aggregator;

public class OrderConverter {

    public Order convert(final Aggregator<OrderItem> aggregator) {
        return new Order(aggregator.getId(), aggregator.getValues());
    }
}
