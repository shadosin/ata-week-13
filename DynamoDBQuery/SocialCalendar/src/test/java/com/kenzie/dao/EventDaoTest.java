package com.kenzie.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.activity.dao.EventDao;
import com.kenzie.activity.dao.models.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class EventDaoTest {
    @InjectMocks
    private EventDao eventDao;

    @Mock
    private DynamoDBMapper mapper;

    @Captor
    ArgumentCaptor<Event> eventCaptor;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    void cancelEvent_onExistingEvent_cancelsButDoesntDeleteEvent() {
        // GIVEN
        // event ID to cancel
        String eventId = "1234";
        Event event = new Event();
        event.setId(eventId);
        when(mapper.load(Event.class, eventId)).thenReturn(event);

        // WHEN
        Event result = eventDao.cancelEvent(eventId);

        // THEN
        // save() is called, and Event has attribute called isCanceled which is true
        verify(mapper).save(eventCaptor.capture());
        assertTrue(eventCaptor.getValue().isCanceled());

        // delete() is never called
        verify(mapper, never()).delete(any());

        // resulting Event is marked canceled
        assertTrue(result.isCanceled(), "Expected canceled event to be marked canceled, but was not: " + result);
    }
}
