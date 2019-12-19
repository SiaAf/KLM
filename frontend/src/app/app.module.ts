import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import bootstrap from "bootstrap";
import {AppComponent} from './app.component';
import {FormsModule} from "@angular/forms";
import {AirportService} from "../service/airport.service";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {NavBarComponent} from './nav-bar/nav-bar.component';
import {AirportComponent} from './airport/airport.component';
import {AppRoutingModule} from "../routing/app-routing.module";
import {FareService} from "../service/fare.service";

import {FareComponent} from './fare/fare.component';
import {NgxPaginationModule} from "ngx-pagination";
import {NgxLoadingModule} from "ngx-loading";


@NgModule({
    declarations: [
        AppComponent,
        NavBarComponent,
        AirportComponent,
        FareComponent,
    ],
    imports: [
        BrowserModule,
        NgxPaginationModule,
        FormsModule,
        HttpClientModule,
        AppRoutingModule,
        NgxLoadingModule.forRoot({})
    ],
    providers: [
        AirportService,
        HttpClient,
        FareService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
