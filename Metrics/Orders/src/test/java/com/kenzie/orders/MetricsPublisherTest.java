package com.kenzie.orders;

import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import com.amazonaws.services.cloudwatch.model.StandardUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MetricsPublisherTest {

    MetricsPublisher metricsPublisher = new MetricsPublisher();

    @Test
    public void buildMetricDataRequest_validRequest_returnsPutMetricDataRequest() {
        //GIVEN
        final String metricName = "SOME_ORDER_METRIC";
        final double metricValue = 1;

        //WHEN
        PutMetricDataRequest metricRequest = metricsPublisher.buildMetricDataRequest(metricName, metricValue,
            StandardUnit.None);


        //THEN
        assertNotNull(metricRequest, "The returned PutMetricDataRequest is null.");
        assertEquals("EXAMPLE/ORDERS", metricRequest.getNamespace(),
                "The namespace in the PutMetricDataRequest is not \"EXAMPLE/ORDERS\".");
        assertEquals(1, metricRequest.getMetricData().size(),
                "Only one MetricDatum is expected in the PutMetricDataRequest, instead found " +
                        metricRequest.getMetricData().size() + ".");
        assertEquals(1, metricRequest.getMetricData().get(0).getDimensions().size(),
                "Only one Dimension is expected in the MetricDatum, instead found " +
                        metricRequest.getMetricData().get(0).getDimensions().size() + ".");
        assertEquals("ENVIRONMENT", metricRequest.getMetricData().get(0).getDimensions().get(0).getName(),
                "The dimension name found was not \"ENVIRONMENT\".");
        assertEquals("PRODUCTION", metricRequest.getMetricData().get(0).getDimensions().get(0).getValue(),
                "The dimension value found was not \"PRODUCTION\".");
        assertEquals(metricName, metricRequest.getMetricData().get(0).getMetricName(), "Expected " +
                "metric name to be " + metricName);
        assertEquals(metricValue, metricRequest.getMetricData().get(0).getValue(), "Expected metric " +
            "value to be " + metricValue);

    }

}
