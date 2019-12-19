package nl.klm.demo.tasks;

import nl.klm.demo.model.Locations;
import nl.klm.demo.service.AirportService;
import nl.klm.demo.service.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;

import java.util.function.Supplier;

public class GetAirportTask implements Supplier<Locations> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetAirportTask.class);
    private String airportsUrl;
    private HttpService httpService;
    private AirportService airportService;
    private String code;
    private String language;

    public GetAirportTask(String airportsUrl, HttpService httpService, AirportService airportService, String code, String language) {
        this.airportsUrl = airportsUrl;
        this.httpService = httpService;
        this.airportService = airportService;
        this.code = code;
        this.language = language;
    }


    @Override
    public Locations get() {
        LOGGER.info("WER ARE IN GET JOB FOR CODE" + code);
        HttpEntity httpEntity = httpService.getWithParameters(airportsUrl +"/" + code ,null,null,language,"null");
        return airportService.createAirportResponse(httpEntity);
    }
}
