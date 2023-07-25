package com.kenzie.socialcalendar.activity;

import com.kenzie.socialcalendar.dao.InviteDao;
import com.kenzie.socialcalendar.dao.MemberDao;
import com.kenzie.socialcalendar.dao.models.Invite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DeleteMemberActivityTest {
    @InjectMocks
    private DeleteMemberActivity activity;

    @Mock
    private MemberDao memberDao;

    @Mock
    private InviteDao inviteDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    void handleRequest_attemptsToDeleteMember() {
        // GIVEN
        String memberId = "1234";

        // WHEN
        activity.handleRequest(memberId);

        // THEN
        verify(memberDao).deletePermanently(memberId);
    }

    @Test
    void handleRequest_memberHasReceivedInvite_deletesInvite() {
        // GIVEN
        String memberId = "MEMBER 1";
        String eventId = "EVENT 1";

        Invite inviteToBeDeleted = new Invite();
        inviteToBeDeleted.setEventId(eventId);
        inviteToBeDeleted.setMemberId(memberId);
        inviteToBeDeleted.setCanceled(false);

        List<Invite> memberInvites = new ArrayList<>();
        memberInvites.add(inviteToBeDeleted);

        when(inviteDao.getInvitesSentToMember(memberId)).thenReturn(memberInvites);

        // WHEN
        activity.handleRequest(memberId);

        // THEN
        verify(inviteDao).deleteInvite(inviteToBeDeleted.getEventId(), inviteToBeDeleted.getMemberId());
    }
}
