package com.kenzie.hotel.activity;


import com.kenzie.hotel.dao.ReservationDao;
import com.kenzie.hotel.dao.models.Reservation;
import com.kenzie.hotel.metrics.MetricsPublisher;

import javax.inject.Inject;

/**
 * Handles requests to book a reservation.
 */
public class BookReservationActivity {

    private ReservationDao reservationDao;
    private MetricsPublisher metricsPublisher;

    /**
     * Constructs a BookReservationActivity.
     * @param reservationDao Dao used to create reservations.
     * @param metricsPublisher MetricsPublisher used to publish metrics.
     */
    @Inject
    public BookReservationActivity(ReservationDao reservationDao, MetricsPublisher metricsPublisher) {
        this.reservationDao = reservationDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Creates a reservation with the provided details.
     * @param reservation Reservation to create.
     * @return the reservation that was created
     */
    public Reservation handleRequest(Reservation reservation) {

        Reservation response = reservationDao.bookReservation(reservation);
        return response;
    }
}
