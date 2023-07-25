package com.kenzie.orders;

import com.amazonaws.services.cloudwatch.model.StandardUnit;
import com.kenzie.orders.resources.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class OrderProcessorTest {

    private static final String ORDER_ID = "orderId-1234";
    private static final String CUSTOMER_ID =  "customerId-58483";
    private static final String PAYMENT_ID = "paymentId-13424";
    private static final double TOTAL_PRICE = 13.99;
    private static final String FAILURE_METRIC_NAME = "ORDER_FAILURES";
    private static final String SUCCESS_METRIC_NAME = "ORDER_TOTALS";

    @Mock
    private MetricsPublisher metricsPublisher;

    @Mock
    private CreditProcessor creditProcessor;

    @InjectMocks
    private OrderProcessor orderProcessor;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void processOrder_measuresTime_correctTimeRangeReturned() {
        //GIVEN
        Order order = new Order(ORDER_ID, CUSTOMER_ID, PAYMENT_ID,TOTAL_PRICE);

        ArgumentCaptor<Double> captor = ArgumentCaptor.forClass(Double.class);

        double minElapsedTime = 250;

        //WHEN
        long startTime = System.currentTimeMillis();
        orderProcessor.processOrder(order);
        long endTime = System.currentTimeMillis();

        doCallRealMethod().when(creditProcessor).processPayment(notNull());

        //THEN
        double maxElapsedTime = endTime - startTime;
        verify(metricsPublisher, times(1)).addMetric(matches("ORDER_PROCESSING_TIMES"), captor.capture(), eq(StandardUnit.Milliseconds));
        double reportedElapsedTime = captor.getValue();
        assertTrue(reportedElapsedTime >= minElapsedTime, "The time measured is less than of the minimum time");
        assertTrue(reportedElapsedTime <= maxElapsedTime, "The time measured is greater than of the maximum time");
    }

    @Test
    public void processOrder_failedOrder_logsOneValueErrorMetric() throws Exception {
        // GIVEN
        Order order = new Order(ORDER_ID, CUSTOMER_ID, PAYMENT_ID,TOTAL_PRICE);
        when(creditProcessor.processPayment(order)).thenThrow(new RuntimeException());

        // WHEN
        orderProcessor.processOrder(order);

        // THEN
        verify(metricsPublisher, times(1)).addMetric(FAILURE_METRIC_NAME, 1, StandardUnit.Count);
    }

    @Test
    public void processOrder_validOrder_logsOrderTotalMetric() {
        // GIVEN
        Order order = new Order(ORDER_ID, CUSTOMER_ID, PAYMENT_ID,TOTAL_PRICE);

        // WHEN
        orderProcessor.processOrder(order);

        // THEN
        verify(metricsPublisher, times(1)).addMetric(SUCCESS_METRIC_NAME, TOTAL_PRICE, StandardUnit.None);
    }
}
