package nl.klm.demo.service;

import com.google.gson.Gson;
import nl.klm.demo.dto.AirportDto;
import nl.klm.demo.helper.AirportCodeComparator;
import nl.klm.demo.helper.AirportNameComparator;
import nl.klm.demo.model.AirportsResponse;
import nl.klm.demo.model.Locations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;

@Service
public class AirportService {
    @Value("${klm.server.airports.url}")
    private String airportsUrl;
    @Autowired
    private HttpService httpService;

    public AirportDto getAllAirportsWithParams(String size, String page, String lang, String term) throws HttpClientErrorException {
       Integer intSize= null,intPage = null;
       if (!size.equals("null")) {
           intSize = Integer.parseInt(size);
       }
        if (!page.equals("null")) {
            intPage = Integer.parseInt(page);
        }
        HttpEntity httpEntity = httpService.getWithParameters(airportsUrl,intSize,intPage,lang,term);
        return createAirportsResponse(httpEntity);
    }


    public AirportDto createAirportsResponse(HttpEntity httpEntity) {
        Gson gson=new Gson();
        AirportsResponse airportsResponse = gson.fromJson((String) httpEntity.getBody(),  AirportsResponse.class);
        List<Locations> listOfAirports = airportsResponse.get_embedded().getLocations();
        Collections.sort(listOfAirports,new AirportCodeComparator());
        return createAirportDto(airportsResponse);
    }

    public Locations createAirportResponse(HttpEntity httpEntity) {
        Gson gson=new Gson();
        Locations singleAirport = gson.fromJson((String) httpEntity.getBody(),  Locations.class);
        return singleAirport;
    }

    private AirportDto createAirportDto (AirportsResponse airportsResponse) {
        AirportDto airportDto = new AirportDto();
        airportDto.setLocations(airportsResponse.get_embedded().getLocations());
        airportDto.setPage(airportsResponse.getPage());
        return airportDto;
    }

    public AirportDto getAirport(String language, String code) {
        return null;
    }
}
