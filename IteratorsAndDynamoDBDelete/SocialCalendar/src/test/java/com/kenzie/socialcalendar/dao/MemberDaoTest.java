package com.kenzie.socialcalendar.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.socialcalendar.dao.models.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class MemberDaoTest {
    @InjectMocks
    private MemberDao memberDao;

    @Mock
    private DynamoDBMapper mapper;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    void deletePermanently_withMemberId_resultsInDynamoDbDeleteRequest() {
        // GIVEN
        String memberId = "MEMBER_TO_DELETE";

        // WHEN
        memberDao.deletePermanently(memberId);

        // THEN
        // delete() is called.
        // NOTE: Can also use Captor to ensure the right memberId is present
        verify(mapper, times(1)).delete(any(Member.class));
    }
}
