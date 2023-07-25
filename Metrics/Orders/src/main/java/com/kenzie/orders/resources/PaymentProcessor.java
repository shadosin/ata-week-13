package com.kenzie.orders.resources;

public interface PaymentProcessor {

    /**
     * Processes a payment.
     *
     * @param newOrder The order containing the payment information
     * @return True if successful, false otherwise.
     * @throws Exception If an error occurs while processing the payment, an exception is thrown.
     */
    boolean processPayment(Order newOrder) throws Exception;
}
