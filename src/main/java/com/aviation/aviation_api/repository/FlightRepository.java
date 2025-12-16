package com.aviation.aviation_api.repository;

import com.aviation.aviation_api.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirport(String departureAirport);
    List<Flight> findByArrivalAirport(String arrivalAirport);
    List<Flight> findByStatus(String status);
}
