package com.kenzie.orders.resources;

public class Order {
    private String orderId;
    private String customerId;
    private String paymentId;
    private double totalPrice;

    public Order(String orderId, String customerId, String paymentId, double totalPrice) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.paymentId = paymentId;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
