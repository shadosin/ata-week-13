package com.kenzie.activity;

import com.google.common.collect.ImmutableList;
import com.kenzie.activity.dao.InviteDao;
import com.kenzie.activity.dao.MemberDao;
import com.kenzie.activity.dao.models.Invite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;

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
    private void setup() {
        initMocks(this);
    }

    @Test
    void handleRequest_attemptsToDeleteMember() {
        // GIVEN
        String memberId = "1234";
        when(inviteDao.getInvitesSentToMember(memberId)).thenReturn(Collections.emptyList());

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

        when(inviteDao.getInvitesSentToMember(memberId)).thenReturn(ImmutableList.of(inviteToBeDeleted));

        // WHEN
        activity.handleRequest(memberId);

        // THEN
        verify(inviteDao).deleteInvite(inviteToBeDeleted.getEventId(), inviteToBeDeleted.getMemberId());
    }
}
