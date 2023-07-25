package com.kenzie.hotel.activity;

import com.kenzie.hotel.dao.ReservationDao;
import com.kenzie.hotel.dao.models.Reservation;
import com.kenzie.hotel.metrics.MetricsPublisher;

import javax.inject.Inject;

/**
 * Handles requests to cancel a reservation.
 */
public class CancelReservationActivity {

    private ReservationDao reservationDao;
    private MetricsPublisher metricsPublisher;

    /**
     * Constructs a CancelReservationActivity.
     * @param reservationDao Dao used to update reservations.
     * @param metricsPublisher MetricsPublisher used to publish metrics.
     */
    @Inject
    public CancelReservationActivity(ReservationDao reservationDao, MetricsPublisher metricsPublisher) {
        this.reservationDao = reservationDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Cancels the given reservation.
     * @param reservationId of the reservation to cancel.
     * @return canceled reservation
     */
    public Reservation handleRequest(final String reservationId) {

        Reservation response = reservationDao.cancelReservation(reservationId);
        return response;
    }
}
