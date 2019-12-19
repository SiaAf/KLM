package com.klm.springangular.dto;

import com.klm.springangular.model.Locations;
import com.klm.springangular.model.Page;

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
