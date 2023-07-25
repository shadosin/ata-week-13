package com.kenzie.orders.resources;

public class CreditProcessor implements PaymentProcessor {

    /**
     * Stubs out back end work with a wait time of 250 milliseconds.
     *
     * @param newOrder The order to work on
     * @return True if successful, false otherwise
     */
    public boolean processPayment(Order newOrder) {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}
