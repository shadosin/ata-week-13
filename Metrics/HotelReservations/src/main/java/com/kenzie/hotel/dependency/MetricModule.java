package com.kenzie.hotel.dependency;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Provides AmazonCloudWatch instance for metric publishing.
 */
@Module
public class MetricModule {

    /**
     * Creates and returns an AmazonCloudWatch instance for the appropriate region.
     * @return a DynamoDBMapper
     */
    @Singleton
    @Provides
    public AmazonCloudWatch provideAmazonCloudWatch() {
        return AmazonCloudWatchClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
    }
}
