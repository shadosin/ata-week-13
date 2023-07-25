package com.kenzie.orders.resources;

import java.util.Random;

public class InventoryManager {

    Random randomNumberGenerator;

    /**
     * Constructor.
     *
     */
    public InventoryManager() {
        randomNumberGenerator = new Random();
    }

    /**
     * Stubs out back end work with a wait time of 250 milliseconds.
     *
     * @param newOrder The new order to work on
     * @return A pick list identifier number
     */
    public int createPickList(Order newOrder) {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return randomNumberGenerator.nextInt();
    }

    /**
     * Stubs out back end work with a wait time of 250 milliseconds.
     *
     * @param pickListNumber A pick list identifier number
     * @return True if successful, false otherwise
     */
    public boolean processPickList(int pickListNumber) {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}
