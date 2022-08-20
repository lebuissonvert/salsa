import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { Danse } from '../model/danse';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DanseService {

  private baseUrl: string;
  private wsShowAllDanseUrl: string;

  constructor( private http: HttpClient) {
    //this.baseUrl = 'http://localhost:8080/';
    this.baseUrl = 'http://passes-salsa.ddns.net:8080/';
    this.wsShowAllDanseUrl = 'showAllDanse';
  }

  public findAll(): Observable<Danse[]> {
    return this.http.get<Danse[]>(this.baseUrl + this.wsShowAllDanseUrl);
  }
}
