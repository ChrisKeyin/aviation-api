package com.aviation.aviation_api.service;

import com.aviation.aviation_api.entity.Airline;
import com.aviation.aviation_api.entity.Flight;
import com.aviation.aviation_api.entity.Gate;
import com.aviation.aviation_api.repository.AirlineRepository;
import com.aviation.aviation_api.repository.FlightRepository;
import com.aviation.aviation_api.repository.GateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirlineRepository airlineRepository;
    private final GateRepository gateRepository;

    public FlightService(FlightRepository flightRepository,
                         AirlineRepository airlineRepository,
                         GateRepository gateRepository) {
        this.flightRepository = flightRepository;
        this.airlineRepository = airlineRepository;
        this.gateRepository = gateRepository;
    }


    public Flight create(Flight flight, Long airlineId, Long gateId) {
        Airline airline = airlineRepository.findById(airlineId)
                .orElseThrow(() -> new RuntimeException("Airline not found: " + airlineId));

        Gate gate = gateRepository.findById(gateId)
                .orElseThrow(() -> new RuntimeException("Gate not found: " + gateId));

        flight.setAirline(airline);
        flight.setGate(gate);

        return flightRepository.save(flight);
    }

    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    public Flight getById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found: " + id));
    }

    public Flight update(Long id, Flight updated, Long airlineId, Long gateId) {
        Flight existing = getById(id);

        Airline airline = airlineRepository.findById(airlineId)
                .orElseThrow(() -> new RuntimeException("Airline not found: " + airlineId));

        Gate gate = gateRepository.findById(gateId)
                .orElseThrow(() -> new RuntimeException("Gate not found: " + gateId));

        existing.setFlightNumber(updated.getFlightNumber());
        existing.setStatus(updated.getStatus());
        existing.setDepartureAirport(updated.getDepartureAirport());
        existing.setArrivalAirport(updated.getArrivalAirport());
        existing.setScheduledDeparture(updated.getScheduledDeparture());
        existing.setScheduledArrival(updated.getScheduledArrival());
        existing.setAirline(airline);
        existing.setGate(gate);

        return flightRepository.save(existing);
    }

    public void delete(Long id) {
        flightRepository.deleteById(id);
    }
}
