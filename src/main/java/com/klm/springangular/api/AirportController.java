package com.klm.springangular.api;

import com.klm.springangular.dto.AirportDto;
import com.klm.springangular.service.AirportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class AirportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirportController.class);

    @Autowired
    AirportService airportService;

    @GetMapping
    @RequestMapping(value = "/airports/params", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AirportDto> getAllAirports(
        @RequestParam(required = false, name = "size") Integer size,
        @RequestParam(required = false, name = "page") Integer page,
        @RequestParam(required = false, name = "lang") String language,
        @RequestParam(required = false, name = "term") String searchItem) {
        LOGGER.info("get the airports with term:" + searchItem);
        LOGGER.info("get the airports with size:" + size);
        try {
            AirportDto airportDto = airportService.getAllAirportsWithParams(size, page, language, searchItem);
            return new ResponseEntity<>(airportDto, HttpStatus.OK);
        } catch (HttpClientErrorException err) {
            LOGGER.info(err.getMessage());
            return new ResponseEntity("bad request", HttpStatus.BAD_REQUEST);
        }
    }
}
