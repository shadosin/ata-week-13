package com.kenzie.orders;


import com.amazonaws.services.cloudwatch.model.StandardUnit;
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
    public OrderProcessor(MetricsPublisher metricsPublisher, PaymentProcessor creditProcessor,
                          CustomerManager customerManager, InventoryManager inventoryManager) {
        this.metricsPublisher = metricsPublisher;
        this.creditProcessor = creditProcessor;
        this.customerManager = customerManager;
        this.inventoryManager = inventoryManager;
    }

    /**
     * Processes an order and payment.
     *
     * @param newOrder The order to be processed
     */
    public void processOrder(Order newOrder) {
    long startTime = System.currentTimeMillis();
        try {
            customerManager.verifyCustomerInfo(newOrder);
            int pickListNumber = inventoryManager.createPickList(newOrder);
            creditProcessor.processPayment(newOrder);
            inventoryManager.processPickList(pickListNumber);
        } catch (Exception e) {
            metricsPublisher.addMetric("ORDER_FAILURES", 1, StandardUnit.Count);
            System.out.println("Error processing order " + newOrder.getOrderId());
        }
        metricsPublisher.addMetric("ORDER_TOTALS", newOrder.getTotalPrice(), StandardUnit.None);
        metricsPublisher.addMetric("ORDER_FAILURES", 0, StandardUnit.Count);

        long endTime = System.currentTimeMillis();

        metricsPublisher.addMetric("ORDER_PROCESSING_TIMES", endTime-startTime, StandardUnit.Milliseconds);
    }
}
