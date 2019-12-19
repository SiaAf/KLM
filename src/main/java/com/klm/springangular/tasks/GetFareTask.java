package nl.klm.demo.tasks;

import nl.klm.demo.model.Fare;
import nl.klm.demo.service.FareService;
import nl.klm.demo.service.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;

import java.util.function.Supplier;

public class GetFareTask implements Supplier<Fare> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetAirportTask.class);
    private String fareUrl;
    private HttpService httpService;
    private FareService fareService;
    private String originCode;
    private String destinationCode;

    public GetFareTask(String fareUrl, HttpService httpService, FareService fareService, String originCode, String destinationCode) {
        this.fareUrl = fareUrl;
        this.httpService = httpService;
        this.fareService = fareService;
        this.originCode = originCode;
        this.destinationCode = destinationCode;
    }

    @Override
    public Fare get() {
        LOGGER.info("WER ARE IN GET FARE THREAD FOR CODE " + originCode + " - " + destinationCode);
        HttpEntity httpEntity = httpService.getWithParameters(fareUrl +"/" + originCode + "/" + destinationCode ,null,null,"null","null");
        return fareService.createFare(httpEntity);
    }
}
