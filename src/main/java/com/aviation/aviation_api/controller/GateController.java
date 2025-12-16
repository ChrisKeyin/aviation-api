package com.aviation.aviation_api.controller;

import com.aviation.aviation_api.entity.Gate;
import com.aviation.aviation_api.service.GateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gates")
public class GateController {

    private final GateService gateService;

    public GateController(GateService gateService) {
        this.gateService = gateService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Gate create(@RequestBody Gate gate) {
        return gateService.create(gate);
    }

    @GetMapping
    public List<Gate> getAll() {
        return gateService.getAll();
    }

    @GetMapping("/{id}")
    public Gate getById(@PathVariable Long id) {
        return gateService.getById(id);
    }

    @PutMapping("/{id}")
    public Gate update(@PathVariable Long id, @RequestBody Gate gate) {
        return gateService.update(id, gate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        gateService.delete(id);
    }
}
