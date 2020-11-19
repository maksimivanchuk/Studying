import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Band} from '../model/Band';

const endpoint: string = 'http://localhost:8080/bands';
const httpOptions = {
  headers: new HttpHeaders({'Content-type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class BandService {

  constructor(private http: HttpClient) { }

  public getAllBands(): Observable<Band[]> {
    return this.http.get<Band[]>(endpoint);
  }

  public getBand(id: number): Observable<Band> {
    return this.http.get<Band>(endpoint + "/" + id);
  }

  public createBand(band: Band): Observable<Band> {
    return this.http.post<any>(endpoint, band, httpOptions);
  }

  public deleteBand(id: number): Observable<any> {
    return this.http.delete(endpoint + "/" + id);
  }

  public updateBand(band: Band): Observable<any> {
    return this.http.put(endpoint, band, httpOptions);
  }
}
