package com.kenzie;


import com.kenzie.activity.*;
import com.kenzie.activity.dependency.DaggerServiceComponent;
import com.kenzie.activity.dependency.ServiceComponent;

public final class ActivityProvider {
    private static final ServiceComponent DAGGER = DaggerServiceComponent.create();

    private ActivityProvider() {
    }

    public static DeleteMemberActivity provideDeleteMemberActivity() {
        return DAGGER.provideDeleteMemberActivity();
    }
    public static CreateMemberActivity provideCreateMemberActivity() {
        return DAGGER.provideCreateMemberActivity();
    }
    public static GetMemberActivity provideGetMemberActivity() {
        return DAGGER.provideGetMemberActivity();
    }

    public static GetInviteActivity provideGetInviteActivity() {
        return DAGGER.provideGetInviteActivity();
    }
    public static GetInvitesForMemberActivity provideGetInvitesForMemberActivity() {
        return DAGGER.provideGetInvitesForMemberActivity();
    }

    public static GetInvitesForEventActivity provideGetInvitesForEventActivity() {
        return DAGGER.provideGetInvitesForEventActivity();
    }
    public static CreateInviteActivity provideCreateInviteActivity() {
        return DAGGER.provideCreateInviteActivity();
    }

    public static GetEventActivity provideGetEventActivity() {
        return DAGGER.provideGetEventActivity();
    }
    public static CreateEventActivity provideCreateEventActivity() {
        return DAGGER.provideCreateEventActivity();
    }
    public static CancelEventActivity provideCancelEventActivity() {
        return DAGGER.provideCancelEventActivity();
    }

    public static GetEventAnnouncementsActivity provideGetEventAnnouncementsActivity() {
        return DAGGER.provideGetEventAnnouncementsActivity();
    }
    public static GetEventAnnouncementsBetweenDatesActivity provideGetEventAnnouncementsBetweenDatesActivity() {
        return DAGGER.provideGetEventAnnouncementsBetweenDatesActivity();
    }
    public static CreateEventAnnouncementActivity provideCreateEventAnnouncementActivity() {
        return DAGGER.provideCreateEventAnnouncementActivity();
    }
}
