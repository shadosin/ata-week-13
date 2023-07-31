package com.kenzie.activity;

import com.kenzie.activity.dao.EventAnnouncementDao;
import com.kenzie.activity.dao.InviteDao;
import com.kenzie.activity.dao.models.Invite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetInvitesForEventActivityTest {
    @InjectMocks
    private GetInvitesForEventActivity activity;

    @Mock
    private InviteDao inviteDao;

    @BeforeEach
    private void setup() {
        initMocks(this);
    }

    @Test
    void handleRequest_withNullExclusiveStartKey_callsDao(){
        List<Invite> announcements = activity.handleRequest("eventId", null);

        verify(inviteDao).getInvitesForEvent("eventId", null);
    }
    @Test
    void handleRequest_withExclsuiveStartKey_callsDao(){
        List<Invite> announcements = activity.handleRequest("eventId", "1234");

        verify(inviteDao).getInvitesForEvent("eventId", "1234");
    }
}
