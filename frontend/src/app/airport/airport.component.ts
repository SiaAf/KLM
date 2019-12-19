import {Component, OnInit} from '@angular/core';
import {Airports} from "../../model/airports";
import {AirportService} from "../../service/airport.service";
import {Locations} from "../../model/locations";
import {Page} from "../../model/page";

@Component({
    selector: 'app-airport',
    templateUrl: './airport.component.html',
    styleUrls: ['./airport.component.css']
})
export class AirportComponent implements OnInit {
    airports: Airports;
    locations: Locations[];
    page: Page;
    search: string;

    constructor(private airportService: AirportService) {
        this.getAirports(null, null, null, null);
    }

    ngOnInit() {
    }

    onPageClick(id: any) {

        this.getAirports(null, id, null, this.search == undefined ? null : this.search);
    }

    onChange() {
        this.getAirports(null, null, null, this.search);
    }

    getAirports(size: string, page: string, language: string, searchItem: string) {
        this.airportService.getAirports(size, page, language, searchItem).subscribe((res) => {
            this.airports = <Airports>res;
            this.page = this.airports.page;
            this.locations = this.airports.locations;
        }, (err: Response) => {
            console.log(err)
        });
    }
}
