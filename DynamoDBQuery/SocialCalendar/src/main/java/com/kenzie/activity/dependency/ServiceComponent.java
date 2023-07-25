package com.kenzie.activity.dependency;

import com.kenzie.activity.*;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Declares the dependency roots that Dagger will provide.
 */
@Singleton
@Component(modules = DaoModule.class)
public interface ServiceComponent {
    DeleteMemberActivity provideDeleteMemberActivity();
    CreateMemberActivity provideCreateMemberActivity();
    GetMemberActivity provideGetMemberActivity();

    GetInviteActivity provideGetInviteActivity();
    GetInvitesForMemberActivity provideGetInvitesForMemberActivity();
    CreateInviteActivity provideCreateInviteActivity();

    GetEventActivity provideGetEventActivity();
    CreateEventActivity provideCreateEventActivity();
    CancelEventActivity provideCancelEventActivity();

    GetInvitesForEventActivity provideGetInvitesForEventActivity();

    GetEventAnnouncementsActivity provideGetEventAnnouncementsActivity();

    GetEventAnnouncementsBetweenDatesActivity provideGetEventAnnouncementsBetweenDatesActivity();

    CreateEventAnnouncementActivity provideCreateEventAnnouncementActivity();
}
