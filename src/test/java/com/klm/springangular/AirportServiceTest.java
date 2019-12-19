package com.klm.springangular;

import com.klm.springangular.dto.AirportDto;
import com.klm.springangular.service.AirportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirportServiceTest {

    @Autowired
    private AirportService airportService;

    @Test
    public void getAirportsWithParams() {
       AirportDto airportDto = airportService.getAllAirportsWithParams(null,null,null,null);
        assertTrue(airportDto.getPage().getSize() == 25);
    }
}
