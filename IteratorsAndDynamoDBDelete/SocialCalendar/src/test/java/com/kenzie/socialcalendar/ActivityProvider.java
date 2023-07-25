package com.kenzie.socialcalendar;

import com.kenzie.socialcalendar.activity.*;
import com.kenzie.socialcalendar.dependency.ServiceComponent;

import com.kenzie.socialcalendar.dependency.DaggerServiceComponent;


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
}
