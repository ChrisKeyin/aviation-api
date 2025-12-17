package com.aviation.aviation_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "gates")
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String gateCode;   // e.g., A12

    @Column(nullable = false)
    private String terminal;   // e.g., T1

    @Column(nullable = false)
    private Boolean active = true;

    @ManyToOne(optional = false)
    @JoinColumn(name = "airport_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Airport airport;

    public Gate() {}

    public Gate(String gateCode, String terminal, Boolean active) {
        this.gateCode = gateCode;
        this.terminal = terminal;
        this.active = active;
    }

    public Long getId() { return id; }

    public String getGateCode() { return gateCode; }
    public void setGateCode(String gateCode) { this.gateCode = gateCode; }

    public String getTerminal() { return terminal; }
    public void setTerminal(String terminal) { this.terminal = terminal; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Airport getAirport() { return airport; }
    public void setAirport(Airport airport) { this.airport = airport; }
}
