package com.aviation.aviation_api.service.impl;

import com.aviation.aviation_api.entity.Gate;
import com.aviation.aviation_api.repository.GateRepository;
import com.aviation.aviation_api.service.GateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateServiceImpl implements GateService {

    private final GateRepository gateRepository;

    public GateServiceImpl(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    @Override
    public Gate create(Gate gate) {
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
    public Gate update(Long id, Gate gate) {
        Gate existing = getById(id);
        existing.setGateCode(gate.getGateCode());
        existing.setTerminal(gate.getTerminal());
        existing.setActive(gate.getActive());
        return gateRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        gateRepository.deleteById(id);
    }
}
