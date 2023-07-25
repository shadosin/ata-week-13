package com.kenzie.orders;


import com.kenzie.orders.resources.CustomerManager;
import com.kenzie.orders.resources.InventoryManager;
import com.kenzie.orders.resources.Order;
import com.kenzie.orders.resources.PaymentProcessor;

/**
 * Class representing final state of the coding activity.
 */
public class OrderProcessor {

    // TODO - refactor this class so all dependencies are accepted via constructor
    private CustomerManager customerManager = new CustomerManager();
    private InventoryManager inventoryManager = new InventoryManager();
    private PaymentProcessor creditProcessor;
    private MetricsPublisher metricsPublisher;

    /**
     * Constructs a OrderProcessor object.
     *
     * @param metricsPublisher Used to publish metrics to CloudWatch.
     */
    public OrderProcessor(MetricsPublisher metricsPublisher, PaymentProcessor creditProcessor) {
        this.metricsPublisher = metricsPublisher;
        this.creditProcessor = creditProcessor;
    }

    /**
     * Processes an order and payment.
     *
     * @param newOrder The order to be processed
     */
    public void processOrder(Order newOrder) {

        try {
            customerManager.verifyCustomerInfo(newOrder);
            int pickListNumber = inventoryManager.createPickList(newOrder);
            creditProcessor.processPayment(newOrder);
            inventoryManager.processPickList(pickListNumber);
        } catch (Exception e) {
            System.out.println("Error processing order " + newOrder.getOrderId());
        }
    }
}
