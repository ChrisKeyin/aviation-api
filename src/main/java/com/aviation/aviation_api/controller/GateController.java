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

    // Request DTO (keeps your API clean: you send airportId instead of embedding an Airport object)
    public static class GateRequest {
        private String gateCode;
        private String terminal;
        private Boolean active;
        private Long airportId;

        public GateRequest() {}

        public String getGateCode() { return gateCode; }
        public void setGateCode(String gateCode) { this.gateCode = gateCode; }

        public String getTerminal() { return terminal; }
        public void setTerminal(String terminal) { this.terminal = terminal; }

        public Boolean getActive() { return active; }
        public void setActive(Boolean active) { this.active = active; }

        public Long getAirportId() { return airportId; }
        public void setAirportId(Long airportId) { this.airportId = airportId; }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Gate create(@RequestBody GateRequest request) {
        return gateService.create(request);
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
    public Gate update(@PathVariable Long id, @RequestBody GateRequest request) {
        return gateService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        gateService.delete(id);
    }
}
