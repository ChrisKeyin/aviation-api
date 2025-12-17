package com.aviation.aviation_api.controller;

import com.aviation.aviation_api.entity.Flight;
import com.aviation.aviation_api.service.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class FlightControllerTest {

    private MockMvc mockMvc;
    private FlightService flightService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        flightService = Mockito.mock(FlightService.class);
        FlightController controller = new FlightController(flightService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void getAll_returnsList() throws Exception {
        when(flightService.getAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/flights"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(flightService).getAll();
    }

    @Test
    void getById_returnsFlight() throws Exception {
        Flight flight = new Flight();
        flight.setFlightNumber("AC123");
        flight.setDepartureAirport("YHZ");
        flight.setArrivalAirport("YYZ");
        flight.setStatus("ON_TIME");

        when(flightService.getById(1L)).thenReturn(flight);

        mockMvc.perform(get("/api/flights/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightNumber").value("AC123"))
                .andExpect(jsonPath("$.departureAirport").value("YHZ"))
                .andExpect(jsonPath("$.arrivalAirport").value("YYZ"))
                .andExpect(jsonPath("$.status").value("ON_TIME"));

        verify(flightService).getById(1L);
    }

    @Test
    void create_returnsCreated() throws Exception {
        // request body (matches your FlightController.FlightRequest)
        var req = new FlightController.FlightRequest();
        req.flightNumber = "AC123";
        req.status = "ON_TIME";
        req.departureAirport = "YHZ";
        req.arrivalAirport = "YYZ";
        req.scheduledDeparture = LocalDateTime.parse("2025-12-17T10:00:00");
        req.scheduledArrival = LocalDateTime.parse("2025-12-17T12:30:00");
        req.airlineId = 1L;
        req.gateId = 2L;

        Flight saved = new Flight();
        saved.setFlightNumber("AC123");
        saved.setStatus("ON_TIME");
        saved.setDepartureAirport("YHZ");
        saved.setArrivalAirport("YYZ");
        saved.setScheduledDeparture(req.scheduledDeparture);
        saved.setScheduledArrival(req.scheduledArrival);

        when(flightService.create(any(Flight.class), eq(1L), eq(2L))).thenReturn(saved);

        mockMvc.perform(post("/api/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.flightNumber").value("AC123"))
                .andExpect(jsonPath("$.status").value("ON_TIME"));

        verify(flightService).create(any(Flight.class), eq(1L), eq(2L));
    }

    @Test
    void arrivals_searchEndpoint_returnsList() throws Exception {
        when(flightService.getArrivals("YYZ")).thenReturn(List.of());

        mockMvc.perform(get("/api/flights/search/arrivals")
                        .param("airport", "YYZ"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(flightService).getArrivals("YYZ");
    }

    @Test
    void departures_searchEndpoint_returnsList() throws Exception {
        when(flightService.getDepartures("YHZ")).thenReturn(List.of());

        mockMvc.perform(get("/api/flights/search/departures")
                        .param("airport", "YHZ"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(flightService).getDepartures("YHZ");
    }
}
