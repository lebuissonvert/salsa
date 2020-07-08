import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { TypePasse } from '../model/typepasse';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TypePasseService {
  private baseUrl: string;
  private wsShowAllTypePasseUrl: string;

  constructor( private http: HttpClient) {
    this.baseUrl = 'http://localhost:8080/';
    //this.baseUrl = 'http://romain33.ddns.net:8080/';
    this.wsShowAllTypePasseUrl = 'showAllTypePasse';
  }

  public findAll(): Observable<TypePasse[]> {
    return this.http.get<TypePasse[]>(this.baseUrl + this.wsShowAllTypePasseUrl);
  }
}
