package com.kenzie.socialcalendar;



import com.kenzie.socialcalendar.activity.CreateMemberActivity;
import com.kenzie.socialcalendar.activity.DeleteMemberActivity;
import com.kenzie.socialcalendar.activity.GetMemberActivity;
import com.kenzie.socialcalendar.dao.models.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class Phase1Test {
    private DeleteMemberActivity deleteMemberActivity;
    private CreateMemberActivity createMemberActivity;
    private GetMemberActivity getMemberActivity;

    @BeforeEach
    public void setup() {
        deleteMemberActivity = ActivityProvider.provideDeleteMemberActivity();
        createMemberActivity = ActivityProvider.provideCreateMemberActivity();
        getMemberActivity = ActivityProvider.provideGetMemberActivity();
    }

    @Test
    void deleteMember_onExistingMember_deletesMember() {
        // GIVEN
        // Member to delete
        Member member = new Member();
        member.setName("Abby Normal");
        createMemberActivity.handleRequest(member);

        // WHEN
        deleteMemberActivity.handleRequest(member.getId());

        // THEN
        Member deletedMember = getMemberActivity.handleRequest(member.getId());
        assertNull(
            deletedMember,
            String.format("Expected no member found for ID '%s' but received %s", member.getId(), deletedMember)
        );
    }
}
