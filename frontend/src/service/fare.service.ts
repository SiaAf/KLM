import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ConfigService} from "./config.service";


@Injectable()
export class FareService {
    config = new ConfigService();


    constructor(private httpClient: HttpClient) {
    }

    getFare(originCode: string, destinationCode: string) {
        const url = this.config.baseUrl + 'fares/' + originCode + '/' + destinationCode;
        return this.httpClient.get(url, {headers: this.config.addHeader()})
    }
}
