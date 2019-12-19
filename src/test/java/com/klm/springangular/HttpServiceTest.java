package com.klm.springangular;
import com.google.gson.Gson;
import com.klm.springangular.model.AirportsResponse;
import com.klm.springangular.service.HttpService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpServiceTest {
    @Autowired
    HttpService httpService;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Test
    public void getAirports() {
        Exception exception = assertThrows(HttpClientErrorException.class, () -> {
            httpService.getWithParameters("http://localhost:8080/airpor",null,null,null,null);
        });
        String expectedMessage = "404 Not Found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    public void getAirportsWithParams() {
        HttpEntity httpEntity = httpService.getWithParameters("http://localhost:8080/airports",1,1,"null","yp");
        Gson gson=new Gson();
        AirportsResponse airportsResponse = gson.fromJson((String) httpEntity.getBody(),  AirportsResponse.class);
       assertTrue(airportsResponse.getPage().getSize() == 1);
    }
}
