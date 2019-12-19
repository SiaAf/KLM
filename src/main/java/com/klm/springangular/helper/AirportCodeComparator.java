package com.klm.springangular.helper;

import com.klm.springangular.model.Locations;

import java.util.Comparator;

public class AirportCodeComparator  implements Comparator<Locations> {
    @Override
    public int compare(Locations firstLocation, Locations secondLocation) {
        return firstLocation.getCode().compareTo(secondLocation.getCode());
    }
}
