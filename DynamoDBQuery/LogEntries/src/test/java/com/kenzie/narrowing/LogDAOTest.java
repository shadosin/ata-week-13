package com.kenzie.narrowing;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LogDAOTest {

    @InjectMocks
    private LogDAO logDao;

    @Mock
    private DynamoDBMapper mapper;

    @Mock
    private PaginatedQueryList<Log> queryResult;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void getLogsBetweenTimes_validTimes_returnsLogs() {
        //GIVEN
        String logLevel = "INFO";
        String startTime = "20200109-15:01:01";
        String endTime = "20200113-09:01:01";
        when(mapper.query(eq(Log.class), any(DynamoDBQueryExpression.class))).thenReturn(queryResult);
        ArgumentCaptor<DynamoDBQueryExpression<Log>> captor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);

        //WHEN
        List<Log> result = logDao.getLogsBetweenTimes(logLevel, startTime, endTime);

        // THEN
        assertEquals(queryResult, result, "Expected list of logs to be what was returned from DynamoDB");
        verify(mapper).query(eq(Log.class), captor.capture());
        Map<String, AttributeValue> queriedExpressionAttributes = captor.getValue().getExpressionAttributeValues();
        Collection<AttributeValue> expressionAttributesValues = queriedExpressionAttributes.values();
        Set<String> expressionAttributesKeys = queriedExpressionAttributes.keySet();
        String queriedKeyConditionExpression = captor.getValue().getKeyConditionExpression();
        assertTrue(expressionAttributesValues.contains(new AttributeValue(logLevel)), "Expected query expression to set log level " +
            "to " + logLevel + " in expression attribute values");
        assertTrue(expressionAttributesValues.contains(new AttributeValue(startTime)), "Expected query expression to set start time to "
            + startTime + " in expression attribute values");
        assertTrue(expressionAttributesValues.contains(new AttributeValue(endTime)), "Expected query expression to set end time to "
            + endTime + " in expression attribute values");
        for (String key : expressionAttributesKeys) {
            assertTrue(queriedKeyConditionExpression.contains(key), "Expected key condition expression to reference " +
                "keys set in expression attribute values");
        }

    }

    @Test
    public void getLogsBeforeTime_validTime_returnsLogs() {
        //GIVEN
        String logLevel = "INFO";
        String endTime = "20200109-16:59:59";
        when(mapper.query(eq(Log.class), any(DynamoDBQueryExpression.class))).thenReturn(queryResult);
        ArgumentCaptor<DynamoDBQueryExpression<Log>> captor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);

        //WHEN
        List<Log> result = logDao.getLogsBeforeTime(logLevel, endTime);

        // THEN
        assertEquals(queryResult, result, "Expected list of logs to be what was returned from DynamoDB");
        verify(mapper).query(eq(Log.class), captor.capture());
        Map<String, AttributeValue> queriedExpressionAttributes = captor.getValue().getExpressionAttributeValues();
        Collection<AttributeValue> expressionAttributesValues = queriedExpressionAttributes.values();
        Set<String> expressionAttributesKeys = queriedExpressionAttributes.keySet();
        String queriedKeyConditionExpression = captor.getValue().getKeyConditionExpression();
        assertTrue(expressionAttributesValues.contains(new AttributeValue(logLevel)), "Expected query expression to set log level " +
            "to " + logLevel + " in expression attribute values");
        assertTrue(expressionAttributesValues.contains(new AttributeValue(endTime)), "Expected query expression to set end time to "
            + endTime + " in expression attribute values");
        for (String key : expressionAttributesKeys) {
            assertTrue(queriedKeyConditionExpression.contains(key), "Expected key condition expression to reference " +
                "keys set in expression attribute values");
        }
    }

    @Test
    public void getLogsAfterTime_validTime_returnsLogs() {
        //GIVEN
        String logLevel = "INFO";
        String startTime = "20200112-01:01:01";
        when(mapper.query(eq(Log.class), any(DynamoDBQueryExpression.class))).thenReturn(queryResult);
        ArgumentCaptor<DynamoDBQueryExpression<Log>> captor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);

        //WHEN
        List<Log> result = logDao.getLogsAfterTime(logLevel, startTime);

        // THEN
        assertEquals(queryResult, result, "Expected list of logs to be what was returned from DynamoDB");
        verify(mapper).query(eq(Log.class), captor.capture());
        Map<String, AttributeValue> queriedExpressionAttributes = captor.getValue().getExpressionAttributeValues();
        Collection<AttributeValue> expressionAttributesValues = queriedExpressionAttributes.values();
        Set<String> expressionAttributesKeys = queriedExpressionAttributes.keySet();
        String queriedKeyConditionExpression = captor.getValue().getKeyConditionExpression();
        assertTrue(expressionAttributesValues.contains(new AttributeValue(logLevel)), "Expected query expression to set log level " +
            "to " + logLevel + " in expression attribute values");
        assertTrue(expressionAttributesValues.contains(new AttributeValue(startTime)), "Expected query expression to set start time to "
            + startTime + " in expression attribute values");
        for (String key : expressionAttributesKeys) {
            assertTrue(queriedKeyConditionExpression.contains(key), "Expected key condition expression to reference " +
                "keys set in expression attribute values");
        }
    }
}
