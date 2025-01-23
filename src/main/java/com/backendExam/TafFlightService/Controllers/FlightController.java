package com.backendExam.TafFlightService.Controllers;

import com.backendExam.TafFlightService.Model.Flight;
import com.backendExam.TafFlightService.Services.FlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private final FlightServiceImpl flightServiceImpl;

    public FlightController(FlightServiceImpl flightServiceImpl) {
        this.flightServiceImpl = flightServiceImpl;
    }
@PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        return new ResponseEntity<>(flightServiceImpl.add(flight), HttpStatus.CREATED);
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long flightId) {
        return new ResponseEntity<>(flightServiceImpl.get(flightId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        return new ResponseEntity<>(flightServiceImpl.getAll(), HttpStatus.OK);
    }

    @PutMapping("/{flightId}")
    public ResponseEntity<Flight> updateFlightById(@PathVariable Long flightId, @RequestBody Flight flight) {
        return new ResponseEntity<>(flightServiceImpl.update(flightId,  flight),HttpStatus.OK);
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<String> deleteFlightById(@PathVariable Long flightId) {

        return new ResponseEntity<>(flightServiceImpl.delete(flightId) ,HttpStatus.OK);
    }


}
