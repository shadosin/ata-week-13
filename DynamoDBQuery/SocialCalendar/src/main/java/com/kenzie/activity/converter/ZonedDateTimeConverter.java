package com.kenzie.activity.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles conversion between ZonedDateTime and String.
 */
public class ZonedDateTimeConverter implements DynamoDBTypeConverter<String, ZonedDateTime> {
    @Override
    public String convert(ZonedDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    @Override
    public ZonedDateTime unconvert(String dateTimeRepresentation) {
        return ZonedDateTime.parse(dateTimeRepresentation);
    }
}
