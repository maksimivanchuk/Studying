import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Person} from '../model/Person';
import {CreatePersonDto} from '../model/CreatePersonDto';
import {UpdatePersonDto} from '../model/UpdatePersonDto';

const endpoint: string = 'http://localhost:8080/persons';
const httpOptions = {
  headers: new HttpHeaders({'Content-type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(private http: HttpClient) { }

  public getAllPersons(): Observable<Person[]> {
    return this.http.get<Person[]>(endpoint);
  }

  public getPerson(id: number): Observable<Person> {
    return this.http.get<Person>(endpoint + "/" + id);
  }

  public createPerson(person: CreatePersonDto): Observable<Person> {
    return this.http.post<any>(endpoint, person, httpOptions);
  }

  public deletePerson(id: number): Observable<any> {
    return this.http.delete(endpoint + "/" + id);
  }

  public updatePerson(person: UpdatePersonDto): Observable<any> {
    return this.http.put(endpoint, person, httpOptions);
  }
}
