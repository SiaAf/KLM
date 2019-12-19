package nl.klm.demo.dto;

import nl.klm.demo.model.Locations;
import nl.klm.demo.model.Page;

import java.util.List;

public class AirportDto {
    private List<Locations> locations;
    private Page page;

    public List<Locations> getLocations() {
        return locations;
    }

    public void setLocations(List<Locations> locations) {
        this.locations = locations;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
