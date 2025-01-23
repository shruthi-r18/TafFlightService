package com.backendExam.TafFlightService.Services.Interface;

import com.backendExam.TafFlightService.Model.Flight;

import java.util.List;

public interface FlightService {
    Flight add(Flight flight);

    List<Flight> getAll();

    Flight get(Long id);

    Flight update(Long id,Flight flight);

    String delete(Long id);
}
