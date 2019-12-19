package com.klm.springangular.helper;

import com.klm.springangular.model.Locations;

import java.util.Comparator;

public class AirportNameComparator implements Comparator<Locations> {
    @Override
    public int compare(Locations firstLocation, Locations secondLocation) {
        return firstLocation.getName().compareTo(secondLocation.getName());
    }
}
