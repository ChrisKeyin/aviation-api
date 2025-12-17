package com.aviation.aviation_api.controller;

import com.aviation.aviation_api.entity.Airport;
import com.aviation.aviation_api.repository.AirportRepository;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Airport create(@RequestBody Airport airport) {
        return airportRepository.save(airport);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getById(@PathVariable Long id) {
        return airportRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airport> update(@PathVariable Long id, @RequestBody Airport updated) {
        return airportRepository.findById(id)
                .map(existing -> {
                    // Update fields (adjust if your Airport has different field names)
                    existing.setCode(updated.getCode());
                    existing.setName(updated.getName());
                    existing.setCity(updated.getCity());

                    Airport saved = airportRepository.save(existing);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!airportRepository.existsById(id)) {
            throw new RuntimeException("Airport not found with id " + id);
        }
        airportRepository.deleteById(id);
    }
}
