import {Component, OnInit} from '@angular/core';
import {Airports} from "../../model/airports";
import {Fare} from "../../model/fare";
import {Locations} from "../../model/locations";
import {AirportService} from "../../service/airport.service";
import {FareService} from "../../service/fare.service";

@Component({
    selector: 'app-fare',
    templateUrl: './fare.component.html',
    styleUrls: ['./fare.component.css']
})
export class FareComponent implements OnInit {
    originCode: string;
    destinationCode: string;
    airports: Airports;
    fare: Fare;
    locations: Locations[];
    public loading = false;
    map: Map<string, string> = new Map<string, string>();

    ngOnInit() {
    }

    constructor(private airportService: AirportService, private fareService: FareService) {
    }

    onSubmit() {
        if (this.originCode != undefined && this.destinationCode != undefined) {
            this.loading = true;
            this.fareService.getFare(this.originCode, this.destinationCode).subscribe((res) => {
                this.loading = false;
                this.fare = <Fare>res;
            }, (err: Response) => {
                this.loading = false;
                console.log(err)
            })
        }
    }

    onChange(id: String) {
        let selectedInputValue = (id === 'origin') ? this.originCode : this.destinationCode;
        this.map.set('term', selectedInputValue);
        this.airportService.getAirports(this.map).subscribe((res) => {
            this.airports = <Airports>res;
            this.locations = this.airports.locations;
        }, (err: Response) => {
            console.log(err)
        });
    }
}
