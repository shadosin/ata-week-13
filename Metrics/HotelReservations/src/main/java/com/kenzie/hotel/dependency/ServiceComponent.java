package com.kenzie.hotel.dependency;

import com.kenzie.hotel.activity.BookReservationActivity;
import com.kenzie.hotel.activity.CancelReservationActivity;
import com.kenzie.hotel.activity.ModifyReservationActivity;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Declares the dependency roots that Dagger will provide.
 */
@Singleton
@Component(modules = {MetricModule.class, DaoModule.class})
public interface ServiceComponent {
    BookReservationActivity provideBookReservationActivity();
    CancelReservationActivity provideCancelReservationActivity();
    ModifyReservationActivity provideModifyReservationActivity();
}
