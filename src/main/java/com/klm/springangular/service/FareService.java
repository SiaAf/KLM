package com.klm.springangular.service;

import com.google.gson.Gson;
import com.klm.springangular.dto.FareDto;
import com.klm.springangular.model.Fare;
import com.klm.springangular.model.Locations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

@Service
public class FareService {

    public Fare createFare(HttpEntity httpEntity) {
        Gson gson = new Gson();
        Fare fare = gson.fromJson((String) httpEntity.getBody(), Fare.class);
        return fare;
    }

    public FareDto createFareDto(Locations locationsOrig, Locations locationsDes, Fare fare) {
        FareDto fareDto = new FareDto();
        fareDto.setOriginCode(locationsOrig.getCode());
        fareDto.setOriginDescription(locationsOrig.getDescription());
        fareDto.setOriginName(locationsOrig.getName());

        fareDto.setDestinationCode(locationsDes.getCode());
        fareDto.setDestinationDescription(locationsDes.getDescription());
        fareDto.setDestinationName(locationsDes.getName());

        fareDto.setRate(fare.getAmount());
        fareDto.setCurrency(fare.getCurrency());
        return fareDto;
    }
}
