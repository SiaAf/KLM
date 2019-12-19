package com.klm.springangular.service;

import com.klm.springangular.dto.FareDto;
import com.klm.springangular.model.Fare;
import com.klm.springangular.model.Locations;
import com.klm.springangular.tasks.GetAirportTask;
import com.klm.springangular.tasks.GetFareTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);
    @Value("${klm.server.airports.url}")
    private String airportsUrl;
    @Value("${klm.server.fares.url}")
    private String fareUrl;
    @Autowired
    private HttpService httpService;
    @Autowired
    private AirportService airportService;
    @Autowired
    private FareService fareService;

    public FareDto dataAggregation(String originCode, String destCode) throws Exception {
        LOGGER.info("WE ARE IN dataAggregation TO GET " + originCode + " - " + destCode);
        GetAirportTask getAirportForOrigin = createGetAirportTask(originCode);
        GetAirportTask getAirportForDest = createGetAirportTask(destCode);
        GetFareTask getFareTask = createGetFareTask(originCode, destCode);
        CompletableFuture<Locations> originData = CompletableFuture.supplyAsync(getAirportForOrigin);
        CompletableFuture<Locations> destinationData = CompletableFuture.supplyAsync(getAirportForDest);
        CompletableFuture<Fare> fareData = CompletableFuture.supplyAsync(getFareTask);
        CompletableFuture<FareDto> combinedDataCompletionStage =
            CompletableFuture
                .allOf(originData, destinationData, fareData)
                .thenApply(all -> preprationForFareDto(originData, destinationData, fareData));
        return combinedDataCompletionStage.join();

    }

    private GetAirportTask createGetAirportTask(String code) {
        return new GetAirportTask(
            this.airportsUrl,
            this.httpService,
            this.airportService,
            code,
            "null");
    }

    private GetFareTask createGetFareTask(String origCode, String destCode) {
        return new GetFareTask(fareUrl, httpService, fareService, origCode, destCode);
    }

    private FareDto preprationForFareDto(CompletableFuture<Locations> originData,
        CompletableFuture<Locations> destinationData,
        CompletableFuture<Fare> fareData) {
        LOGGER.info("WE ARE IN ADD METHOD");
        Locations locationsOrig = originData.join();
        Locations locationsDes = destinationData.join();
        Fare fare = fareData.join();
        return fareService.createFareDto(locationsOrig,locationsDes,fare);
    }
}
