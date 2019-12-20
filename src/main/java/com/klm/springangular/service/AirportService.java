package com.klm.springangular.service;

import com.google.gson.Gson;
import com.klm.springangular.dto.AirportDto;
import com.klm.springangular.helper.AirportCodeComparator;
import com.klm.springangular.model.AirportsResponse;
import com.klm.springangular.model.Locations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;

@Service
public class AirportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AirportService.class);

    @Value("${klm.server.airports.url}")
    private String airportsUrl;
    @Autowired
    private HttpService httpService;

    public AirportDto getAllAirportsWithParams(Integer size, Integer page, String lang, String term) throws HttpClientErrorException {
        LOGGER.info(" we getAllAirportsWithParams from the AirportService with the term of: " + term);
        HttpEntity httpEntity = httpService.getWithParameters(airportsUrl, size, page, lang, term);
        return createAirportsResponse(httpEntity);
    }

    public AirportDto createAirportsResponse(HttpEntity httpEntity) {
        Gson gson = new Gson();
        AirportsResponse airportsResponse = gson.fromJson((String) httpEntity.getBody(), AirportsResponse.class);
        List<Locations> listOfAirports = airportsResponse.get_embedded().getLocations();
        Collections.sort(listOfAirports, new AirportCodeComparator());
        return createAirportDto(airportsResponse);
    }

    public Locations createAirportResponse(HttpEntity httpEntity) {
        Gson gson = new Gson();
        Locations singleAirport = gson.fromJson((String) httpEntity.getBody(), Locations.class);
        return singleAirport;
    }

    private AirportDto createAirportDto(AirportsResponse airportsResponse) {
        AirportDto airportDto = new AirportDto();
        airportDto.setLocations(airportsResponse.get_embedded().getLocations());
        airportDto.setPage(airportsResponse.getPage());
        return airportDto;
    }

}
