import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { Niveau } from '../model/niveau';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NiveauService {

  private baseUrl: string;
  private wsShowAllNiveauUrl: string;

  constructor( private http: HttpClient) {
    //this.baseUrl = 'http://localhost:8080/';
    this.baseUrl = 'http://passes-salsa.ddns.net:8080/';
    this.wsShowAllNiveauUrl = 'showAllNiveau';
  }

  public findAll(): Observable<Niveau[]> {
    return this.http.get<Niveau[]>(this.baseUrl + this.wsShowAllNiveauUrl);
  }
}
