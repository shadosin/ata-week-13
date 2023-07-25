package com.kenzie.orders;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import com.amazonaws.services.cloudwatch.model.StandardUnit;

/**
 * Contains operations for publishing metrics.
 */
public class MetricsPublisher {

    private AmazonCloudWatch cloudWatch = AmazonCloudWatchClientBuilder.standard()
        .withRegion(Regions.US_EAST_1)
        .build();


    /**
     * Publishes the given metric to CloudWatch.
     *
     * @param metricName name of metric to publish.
     * @param value      value of metric.
     * @param unit       unit of metric.
     */
    public void addMetric(final String metricName, final double value, final StandardUnit unit) {
        final PutMetricDataRequest request = buildMetricDataRequest(metricName, value, unit);
        cloudWatch.putMetricData(request);
    }

    /**
     * Helper method that builds the PutMetricDataRequest object used to publish to CloudWatch.
     *
     * @param metricName name of metric
     * @param value      value of metric
     * @param unit       unit of metric.
     * @return PutMetricDataRequest
     */
    public PutMetricDataRequest buildMetricDataRequest(
            final String metricName,
            final double value,
            final StandardUnit unit) {
        // TODO - implement the method to convert raw values into a metric data object, then return that object
        return null;
    }
}
