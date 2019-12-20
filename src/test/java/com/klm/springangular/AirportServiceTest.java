package com.klm.springangular;

import com.klm.springangular.dto.AirportDto;
import com.klm.springangular.model.Locations;
import com.klm.springangular.service.AirportService;
import com.klm.springangular.service.HttpService;
import org.eclipse.jetty.client.HttpResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirportServiceTest {

    @Autowired
    private AirportService airportService;
    @Autowired
    private HttpService httpService;
    @Value("${klm.server.airports.url}")
    private String airportsUrl;

    @Test
    public void Should_Return_Twenty_Five_When_All_Params_Are_Null() {
        AirportDto airportDto = airportService.getAllAirportsWithParams(null, null, null, null);
        assertTrue(airportDto.getPage().getSize() == 25);
    }

    @Test
    public void Should_Return_One_When_The_Size_Is_One() {
        AirportDto airportDto = airportService.getAllAirportsWithParams(1, null, null, null);
        assertTrue(airportDto.getPage().getSize() == 1);
    }

    @Test
    public void Should_Create_AirportDto() {
        HttpEntity response = httpService.getWithParameters(airportsUrl, 1, null, null, "ams");
        AirportDto locations = airportService.createAirportsResponse(response);
        assertTrue(locations.getPage().getTotalElements() == 4);
    }
}
