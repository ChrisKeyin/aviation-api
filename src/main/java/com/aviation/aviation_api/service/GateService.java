package com.aviation.aviation_api.service;

import com.aviation.aviation_api.controller.GateController;
import com.aviation.aviation_api.entity.Gate;

import java.util.List;

public interface GateService {
    Gate create(GateController.GateRequest request);
    List<Gate> getAll();
    Gate getById(Long id);
    Gate update(Long id, GateController.GateRequest request);
    void delete(Long id);
}
