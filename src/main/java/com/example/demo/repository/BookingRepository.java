package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Booking;
import com.example.demo.model.BookingStatus;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomerEmail(String email);
    Optional<Booking> findByFlightNumber(String flightNumber);
    List<Booking> findByStatus(BookingStatus status);
}