package com.kenzie.socialcalendar;

import com.kenzie.socialcalendar.activity.CancelEventActivity;
import com.kenzie.socialcalendar.activity.CreateEventActivity;
import com.kenzie.socialcalendar.activity.GetEventActivity;
import com.kenzie.socialcalendar.dao.models.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Phase3Test {
    private GetEventActivity getEventActivity;
    private CreateEventActivity createEventActivity;
    private CancelEventActivity cancelEventActivity;

    @BeforeEach
    public void setup() {
        getEventActivity = ActivityProvider.provideGetEventActivity();
        createEventActivity = ActivityProvider.provideCreateEventActivity();
        cancelEventActivity = ActivityProvider.provideCancelEventActivity();
    }

    @Test
    void cancelEvent_doesNotDeleteEvent() {
        // GIVEN
        Event event = new Event();
        event.setName("A cancelable event");
        event.setCanceled(false);
        event = createEventActivity.handleRequest(event);

        // WHEN
        cancelEventActivity.handleRequest(event.getId());

        // THEN
        Event refreshedEvent = getEventActivity.handleRequest(event.getId());
        assertNotNull(refreshedEvent, String.format("Expected event '%s' to still exist but does not", event));
    }

    @Test
    void cancelEvent_updatesEventToCanceled() {
        // GIVEN
        Event event = new Event();
        event.setName("Another cancelable event");
        event.setCanceled(false);
        event = createEventActivity.handleRequest(event);

        // WHEN
        Event result = cancelEventActivity.handleRequest(event.getId());

        // THEN
        // The returned Event is marked canceled
        assertTrue(result.isCanceled(), "Expected returned event to be marked canceled but was not: " + result);

        // The Event in the db is marked canceled
        Event refreshedEvent = getEventActivity.handleRequest(event.getId());
        assertTrue(refreshedEvent.isCanceled(),
            "Expected event to be updated to canceled but was not: " + refreshedEvent);
    }
}
