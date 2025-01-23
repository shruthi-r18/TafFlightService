package com.backendExam.TafFlightService.Services;

import com.backendExam.TafFlightService.Model.Flight;
import com.backendExam.TafFlightService.Services.Interface.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    private final RestTemplate restTemplate;

    @Value("${datastore.ms.url}")
    String datastore_url;

    public FlightServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Flight add(Flight flight) {
        String datastoreUrl = datastore_url + "/flights";
        Flight flightCreated = restTemplate.postForObject(datastoreUrl, flight, Flight.class);
        return flightCreated;
    }

    @Override
    public List<Flight> getAll() {
        String datastoreUrl = datastore_url + "/flights";
        ResponseEntity<List<Flight>> response = restTemplate.exchange(
                datastoreUrl,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<Flight>>() {
                }
        );
        return response.getBody();
    }

    @Override
    public Flight get(Long id) {
        String datastoreUrl = datastore_url + "/flights/" + id;
        Flight flightById = restTemplate.getForObject(datastoreUrl, Flight.class);
        return flightById;
    }

    @Override
    public Flight update(Long id, Flight flight) {
        String datastoreUrl = datastore_url + "/flights/" + id;
        if (id == null || flight == null) {
            throw new IllegalArgumentException("Id and Flight details must not be null");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Flight> entity = new HttpEntity<>(flight, headers);

        try {
            Flight updatesFlightDetails = restTemplate.exchange(
                    datastoreUrl, HttpMethod.PUT, entity, Flight.class
            ).getBody();
            return updatesFlightDetails;
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            // Log the error and rethrow or return a default value
//            logger.error("Error updating user with ID {}: {}", userId, ex.getMessage());
            throw new RuntimeException("Failed to update flight detais", ex);
        } catch (Exception ex) {
            // Handle other unexpected exceptions
//            logger.error("Unexpected error: {}", ex.getMessage());
            throw new RuntimeException("Unexpected error occurred", ex);
        }
    }

    @Override
    public String delete(Long id) {
        String datastoreUrl = datastore_url + "/flights/" + id;

        try {
            restTemplate.delete(datastoreUrl);
            return "Deleted flight with ID: " + id;
        } catch (Exception e) {
            return "Failed to delete flight with ID: " + id + ". Error: " + e.getMessage();

        }

    }
}
