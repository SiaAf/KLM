import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ConfigService} from "./config.service";


@Injectable()
export class AirportService {
    config = new ConfigService();


    constructor(private httpClient: HttpClient) {
    }

    getAirports(paramsMap?: Map<string, string>) {
        const url = this.config.baseUrl + 'airports/params';
        let params = new HttpParams();
        if (paramsMap != undefined) {
            paramsMap.forEach((value, key) => {
                params = params.append(key, value);
            });
            return this.httpClient.get(url, {headers: this.config.addHeader(), params: params})
        }
        return this.httpClient.get(url, {headers: this.config.addHeader()})
    }

}
