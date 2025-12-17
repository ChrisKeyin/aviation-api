package com.aviation.aviation_api.repository;

import com.aviation.aviation_api.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureAirport(String departureAirport);
    List<Flight> findByArrivalAirport(String arrivalAirport);
    List<Flight> findByStatus(String status);

    List<Flight> findByArrivalAirportOrderByScheduledArrivalAsc(String arrivalAirport);
    List<Flight> findByDepartureAirportOrderByScheduledDepartureAsc(String departureAirport);

    List<Flight> findByArrivalAirportAndScheduledArrivalBetweenOrderByScheduledArrivalAsc(
            String arrivalAirport, LocalDateTime start, LocalDateTime end);

    List<Flight> findByDepartureAirportAndScheduledDepartureBetweenOrderByScheduledDepartureAsc(
            String departureAirport, LocalDateTime start, LocalDateTime end);
}
