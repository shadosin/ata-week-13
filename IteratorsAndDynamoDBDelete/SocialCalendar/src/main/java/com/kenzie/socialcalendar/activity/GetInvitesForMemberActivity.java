package com.kenzie.socialcalendar.activity;

import com.kenzie.socialcalendar.dao.EventDao;
import com.kenzie.socialcalendar.dao.InviteDao;
import com.kenzie.socialcalendar.dao.models.Invite;

import java.util.List;
import javax.inject.Inject;

/**
 * Handles requests to get invites for a given member.
 */
public class GetInvitesForMemberActivity {
    private InviteDao inviteDao;
    private EventDao eventDao;

    /**
     * Constructs an Activity with the given DAO.
     * @param inviteDao The InviteDao to use to fetch invites
     * @param eventDao The EventDao to use to fetch events in Phase 4
     */
    @Inject
    public GetInvitesForMemberActivity(InviteDao inviteDao,
                                       EventDao eventDao) {
        this.inviteDao = inviteDao;
        this.eventDao = eventDao;
    }

    /**
     * Fetches all invites sent to a given member.
     *
     * NOTE: A little deviation from usual.
     * Here we're using values directly in our arguments and return value,
     * whereas in a typical Coral service we'd have Request/Result objects
     * that would be generated from configuration via Coral. We haven't
     * created service infrastructure for this activity, so we're just
     * using the values directly.
     *
     * @param memberId The ID of the member to fetch invites for
     * @return List of Invites sent to the member (if any found)
     */
    public List<Invite> handleRequest(final String memberId) {
        return inviteDao.getInvitesSentToMember(memberId);
    }
}
