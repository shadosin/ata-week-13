package com.kenzie.socialcalendar.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.socialcalendar.dao.models.Member;

import java.util.Objects;
import java.util.UUID;
import javax.inject.Inject;

/**
 * Manages access to Member items.
 */
public class MemberDao {
    private DynamoDBMapper mapper;

    /**
     * Creates an MemberDao with the given DDB mapper.
     * @param mapper DynamoDBMapper
     */
    @Inject
    public MemberDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Creates a new member.
     * @param member The member to create
     * @return The member
     */
    public Member createMember(Member member) {
        if (Objects.isNull(member.getId())) {
            member.setId(UUID.randomUUID().toString());
        }

        mapper.save(member);
        return member;
    }

    /**
     * Gets the member specified by ID.
     * @param memberId The member ID to look for
     * @return The Member indicated by memberId, if found; null otherwise.
     */
    public Member getMember(String memberId) {
        return mapper.load(Member.class, memberId);
    }

    /**
     * Permanently deletes a Member, if the member exists.
     * @param memberId The member ID for the member to delete
     */
    public void deletePermanently(String memberId) {
        Member memberToDelete = new Member();
        memberToDelete.setId(memberId);
        if (memberId != null) {
            mapper.delete(memberToDelete);
        }
    }
}
