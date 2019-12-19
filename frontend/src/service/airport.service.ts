import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ConfigService} from "./config.service";


@Injectable()
export class AirportService {
    config = new ConfigService();


    constructor(private httpClient: HttpClient) {
    }

    getAirports(size: string, page: string, language: string, searchItem: string) {
        const url = this.config.baseUrl + 'airports/params';
        const params = new HttpParams()
            .set('size', size)
            .set('page', page)
            .set('lang', language)
            .set('term', searchItem);

        return this.httpClient.get(url, {headers: this.config.addHeader(), params: params})
    }
}
