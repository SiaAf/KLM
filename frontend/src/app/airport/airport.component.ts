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
    map: Map<string, string> = new Map<string, string>();

    constructor(private airportService: AirportService) {
        this.getAirports();
    }

    ngOnInit() {
    }

    onPageClick(id: any) {
        this.map.set('page', id);
        this.search != undefined ? this.map.set('term', this.search) : null;
        this.getAirports(this.map);
    }

    onChange() {
        this.map.set('term', this.search);
        this.getAirports(this.map);
    }

    getAirports(map ?: Map<string, string>) {
        this.airportService.getAirports(map).subscribe((res) => {
            this.airports = <Airports>res;
            this.page = this.airports.page;
            this.locations = this.airports.locations;
        }, (err: Response) => {
            console.log(err)
        });
    }
}
