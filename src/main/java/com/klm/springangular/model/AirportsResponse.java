package com.klm.springangular.model;

public class AirportsResponse {
    private embedded _embedded;
    private Page page;

    public embedded get_embedded() {
        return _embedded;
    }

    public void set_embedded(embedded _embedded) {
        this._embedded = _embedded;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
