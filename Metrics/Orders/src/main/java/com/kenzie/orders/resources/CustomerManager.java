package com.kenzie.orders.resources;

public class CustomerManager {

    /**
     * Stubs out back end work with a wait time of 250 milliseconds.
     *
     * @param newOrder An order to work on
     */
    public void verifyCustomerInfo(Order newOrder) {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
