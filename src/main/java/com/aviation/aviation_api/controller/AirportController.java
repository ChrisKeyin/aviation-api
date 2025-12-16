package com.aviation.aviation_api.controller;

import com.aviation.aviation_api.entity.Airport;
import com.aviation.aviation_api.repository.AirportRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportRepository airportRepository;

    public AirportController(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @GetMapping
    public List<Airport> getAll() {
        return airportRepository.findAll();
    }

    @PostMapping
    public Airport create(@RequestBody Airport airport) {
        return airportRepository.save(airport);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getById(@PathVariable Long id) {
        return airportRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
