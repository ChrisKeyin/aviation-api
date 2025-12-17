package com.aviation.aviation_api.service.impl;

import com.aviation.aviation_api.controller.GateController;
import com.aviation.aviation_api.entity.Airport;
import com.aviation.aviation_api.entity.Gate;
import com.aviation.aviation_api.repository.AirportRepository;
import com.aviation.aviation_api.repository.GateRepository;
import com.aviation.aviation_api.service.GateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateServiceImpl implements GateService {

    private final GateRepository gateRepository;
    private final AirportRepository airportRepository;

    public GateServiceImpl(GateRepository gateRepository, AirportRepository airportRepository) {
        this.gateRepository = gateRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public Gate create(GateController.GateRequest request) {
        if (request.getAirportId() == null) {
            throw new RuntimeException("airportId is required");
        }

        Airport airport = airportRepository.findById(request.getAirportId())
                .orElseThrow(() -> new RuntimeException("Airport not found with id " + request.getAirportId()));

        Gate gate = new Gate();
        gate.setGateCode(request.getGateCode());
        gate.setTerminal(request.getTerminal());
        gate.setActive(request.getActive() != null ? request.getActive() : true);
        gate.setAirport(airport);

        return gateRepository.save(gate);
    }

    @Override
    public List<Gate> getAll() {
        return gateRepository.findAll();
    }

    @Override
    public Gate getById(Long id) {
        return gateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gate not found with id " + id));
    }

    @Override
    public Gate update(Long id, GateController.GateRequest request) {
        Gate existing = getById(id);
        
        if (request.getGateCode() != null) existing.setGateCode(request.getGateCode());
        if (request.getTerminal() != null) existing.setTerminal(request.getTerminal());
        if (request.getActive() != null) existing.setActive(request.getActive());

        if (request.getAirportId() != null) {
            Airport airport = airportRepository.findById(request.getAirportId())
                    .orElseThrow(() -> new RuntimeException("Airport not found with id " + request.getAirportId()));
            existing.setAirport(airport);
        }

        return gateRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        gateRepository.deleteById(id);
    }
}
