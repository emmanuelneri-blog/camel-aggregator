package br.com.emmanuelneri.aggregator.domain;

import java.util.List;

public class Order {

    private String id;
    private List<OrderItem> items;

    public Order(final String id, final List<OrderItem> items) {
        this.id = id;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
