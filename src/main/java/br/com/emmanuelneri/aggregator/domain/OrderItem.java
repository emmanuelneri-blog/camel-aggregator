package br.com.emmanuelneri.aggregator.domain;

import br.com.emmanuelneri.aggregator.interfaces.Aggregable;

public class OrderItem implements Aggregable {

    private String orderId;
    private String productName;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String id() {
        return this.orderId;
    }
}
