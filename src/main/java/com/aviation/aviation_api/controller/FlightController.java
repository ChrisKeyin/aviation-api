package com.aviation.aviation_api.controller;

import com.aviation.aviation_api.entity.Flight;
import com.aviation.aviation_api.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public static class FlightRequest {
        public String flightNumber;
        public String status;
        public String departureAirport;
        public String arrivalAirport;
        public LocalDateTime scheduledDeparture;
        public LocalDateTime scheduledArrival;
        public Long airlineId;
        public Long gateId;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Flight create(@RequestBody FlightRequest req) {
        Flight f = new Flight();
        f.setFlightNumber(req.flightNumber);
        f.setStatus(req.status);
        f.setDepartureAirport(req.departureAirport);
        f.setArrivalAirport(req.arrivalAirport);
        f.setScheduledDeparture(req.scheduledDeparture);
        f.setScheduledArrival(req.scheduledArrival);

        return flightService.create(f, req.airlineId, req.gateId);
    }

    @GetMapping
    public List<Flight> getAll() {
        return flightService.getAll();
    }

    @GetMapping("/{id}")
    public Flight getById(@PathVariable Long id) {
        return flightService.getById(id);
    }

    @PutMapping("/{id}")
    public Flight update(@PathVariable Long id, @RequestBody FlightRequest req) {
        Flight f = new Flight();
        f.setFlightNumber(req.flightNumber);
        f.setStatus(req.status);
        f.setDepartureAirport(req.departureAirport);
        f.setArrivalAirport(req.arrivalAirport);
        f.setScheduledDeparture(req.scheduledDeparture);
        f.setScheduledArrival(req.scheduledArrival);

        return flightService.update(id, f, req.airlineId, req.gateId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        flightService.delete(id);
    }
}
