package com.delicious_cake.delicious_cake_app.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.delicious_cake.delicious_cake_app.entities.ReservationEntity;
import com.delicious_cake.delicious_cake_app.repositories.ReservationRepository;

@Service 
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    //Create Method
    public ReservationEntity createReservation(ReservationEntity reservation) {
        if(reservation.getCustomer() == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if(reservation.getCreatedAt() == null) {
            throw new IllegalArgumentException("CreatedAt cannot be null");
        }
        if(reservation.getPickupAt() == null) {
            throw new IllegalArgumentException("PickupAt cannot be null");
        }
        if(reservation.getTotal() == null || reservation.getTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Total cannot be null or negative");
        }
        if(reservation.getPending() == null || reservation.getPending().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Pending cannot be null or negative");
        }
        return reservationRepository.save(reservation);
    }

    //Get Method
    public ReservationEntity getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + id));
    }   

    //Get All Method
    public List<ReservationEntity> getAllReservations() {
        return reservationRepository.findAll();
    }

    //Update Method
    public ReservationEntity updateReservation(Long id, ReservationEntity reservation) {
        ReservationEntity existingReservation = getReservationById(id);

        if(reservation.getCustomer() == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if(reservation.getCreatedAt() == null) {
            throw new IllegalArgumentException("CreatedAt cannot be null");
        }
        if(reservation.getPickupAt() == null) {
            throw new IllegalArgumentException("PickupAt cannot be null");
        }
        if(reservation.getTotal() == null || reservation.getTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Total cannot be null or negative");
        }
        if(reservation.getPending() == null || reservation.getPending().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Pending cannot be null or negative");
        }

        existingReservation.setCustomer(reservation.getCustomer());
        existingReservation.setDescription(reservation.getDescription());
        existingReservation.setCreatedAt(reservation.getCreatedAt());
        existingReservation.setPickupAt(reservation.getPickupAt());
        existingReservation.setTotal(reservation.getTotal());
        existingReservation.setPending(reservation.getPending());

        return reservationRepository.save(existingReservation);
    }

    //Delete Method
    public void deleteReservation(Long id) {
        try {
            ReservationEntity existingReservation = getReservationById(id);
            reservationRepository.delete(existingReservation);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Reservation not found with id: " + id);
        }
    }
}
