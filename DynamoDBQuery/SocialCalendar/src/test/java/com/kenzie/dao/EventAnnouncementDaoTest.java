package com.kenzie.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.kenzie.activity.dao.EventAnnouncementDao;
import com.kenzie.activity.dao.models.EventAnnouncement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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
        when(mapper.query(eq(EventAnnouncement.class), any(DynamoDBQueryExpression.class))).thenReturn(queryResult);

        // WHEN
        List<EventAnnouncement> results = new ArrayList<>();

        // THEN
        verify(mapper).query(eq(EventAnnouncement.class), any(DynamoDBQueryExpression.class));
        assertEquals(results, queryResult, "Expected getEventAnnouncements to return the query list");
    }


}
