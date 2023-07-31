package com.kenzie.activity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.kenzie.activity.dao.EventAnnouncementDao;
import com.kenzie.activity.dao.models.EventAnnouncement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetEventAnnouncementsBetweenDatesActivityTest {
    private ZonedDateTime startTime = ZonedDateTime.now().minusDays(7);
    private ZonedDateTime endTime = ZonedDateTime.now();
    @InjectMocks
    private GetEventAnnouncementsBetweenDatesActivity activity;

    @Mock
    private EventAnnouncementDao eventAnnouncementDao;
    @BeforeEach
    public void setup() {
        initMocks(this);
    }
    @Test
    public void getEventAnnouncements_BetweenTimes_returnsList(){
        // GIVEN && WHEN
        List<EventAnnouncement> announcements = activity.handleRequest("eventId",startTime, endTime);

        // THEN
        verify(eventAnnouncementDao).getEventAnnouncementsBetweenDates("eventId", startTime, endTime);
    }
}
