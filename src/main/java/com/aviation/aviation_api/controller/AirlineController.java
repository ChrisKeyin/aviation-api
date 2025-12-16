package com.aviation.aviation_api.controller;

import com.aviation.aviation_api.entity.Airline;
import com.aviation.aviation_api.repository.AirlineRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    private final AirlineRepository airlineRepository;

    public AirlineController(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @GetMapping
    public List<Airline> getAll() {
        return airlineRepository.findAll();
    }

    @PostMapping
    public Airline create(@RequestBody Airline airline) {
        return airlineRepository.save(airline);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airline> getById(@PathVariable Long id) {
        return airlineRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
