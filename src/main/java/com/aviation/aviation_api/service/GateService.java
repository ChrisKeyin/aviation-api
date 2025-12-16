package com.aviation.aviation_api.service;

import com.aviation.aviation_api.entity.Gate;
import java.util.List;

public interface GateService {
    Gate create(Gate gate);
    List<Gate> getAll();
    Gate getById(Long id);
    Gate update(Long id, Gate gate);
    void delete(Long id);
}
