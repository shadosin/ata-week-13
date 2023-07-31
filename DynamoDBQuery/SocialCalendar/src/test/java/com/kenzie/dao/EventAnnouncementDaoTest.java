package com.kenzie.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.kenzie.activity.dao.EventAnnouncementDao;
import com.kenzie.activity.dao.models.EventAnnouncement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EventAnnouncementDaoTest {

    @Mock
    DynamoDBMapper mapper;

    @Mock
    PaginatedQueryList<EventAnnouncement> queryResult;

    @InjectMocks
    EventAnnouncementDao eventAnnouncementDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void getEventAnnouncements_queriesDynamoDb_returnsListFromDynamo() {
        // GIVEN
        final String TEST_ID = "ikaompgei";
        when(mapper.query(eq(EventAnnouncement.class), any(DynamoDBQueryExpression.class))).thenReturn(queryResult);

        // WHEN
        List<EventAnnouncement> results = eventAnnouncementDao.getEventAnnouncements(TEST_ID);
        // THEN
        ArgumentCaptor<DynamoDBQueryExpression<EventAnnouncement>> queryExpressionArgumentCaptor =
                ArgumentCaptor.forClass(DynamoDBQueryExpression.class);
        verify(mapper).query(eq(EventAnnouncement.class), queryExpressionArgumentCaptor.capture());
        assertEquals(results, queryResult, "Expected getEventAnnouncements to return the query list");

        DynamoDBQueryExpression<EventAnnouncement> capturedQueryExpression = queryExpressionArgumentCaptor.getValue();
        EventAnnouncement hashKeyValue = capturedQueryExpression.getHashKeyValues();
        assertEquals(hashKeyValue.getEventId(), TEST_ID, "Expected hash key to contain event ID");
    }
    @Test
    public void getEventAnnouncements_BetweenTimes_returnsList(){
        String eventId = "eventId";
        ZonedDateTime startTime = ZonedDateTime.now().minusDays(7);
        ZonedDateTime endTime = ZonedDateTime.now();
        when(mapper.query(eq(EventAnnouncement.class), any(DynamoDBQueryExpression.class))).thenReturn(queryResult);

        List<EventAnnouncement> results = eventAnnouncementDao.getEventAnnouncementsBetweenDates(eventId, startTime, endTime);

        verify(mapper).query(eq(EventAnnouncement.class), any(DynamoDBQueryExpression.class));
        assertEquals(results, queryResult, "Expected getEventAnnoucementsBetweenTimes to return the query list");
    }

}
