import {Injectable} from '@angular/core';
import {HttpHeaders} from '@angular/common/http';


@Injectable()
export class ConfigService {

    baseUrl = 'http://localhost:8083/';

    addHeader(): HttpHeaders {
        const headers = new HttpHeaders({'content-type': 'application/json'});
        return headers;
    }

}
