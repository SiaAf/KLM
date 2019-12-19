package nl.klm.demo.helper;

import nl.klm.demo.model.Locations;

import java.util.Comparator;

public class AirportCodeComparator  implements Comparator<Locations> {
    @Override
    public int compare(Locations firstLocation, Locations secondLocation) {
        return firstLocation.getCode().compareTo(secondLocation.getCode());
    }
}
