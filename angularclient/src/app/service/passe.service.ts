import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { Passe } from '../model/passe';
import { PaginatedPasse } from '../model/paginated-passe';
import {Observable} from 'rxjs';
import { catchError} from 'rxjs/operators';
import {FilterMetadata} from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class PasseService {

  private baseUrl: string;
  private wsShowAllPassePaginatedUrl: string;
  private wsSavePasseUrl: string;
  private wsReadPasseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8080/';
    //this.baseUrl = 'http://passes-salsa.ddns.net:8080/';
    this.wsShowAllPassePaginatedUrl = 'showAllPassePaginated';
    this.wsSavePasseUrl = 'savePasse';
    this.wsReadPasseUrl = 'readPasse';
  }

  public findAllPaginatedFiltered(
              page: number, size: number,
              sortField: string, sortOrder: number, p_filters: FilterMetadata): Observable<PaginatedPasse> {
      const obj = {
            'page': page+'',
            'size': size+'',
            'sortfield': sortField,
            'sortorder': sortOrder+'',
            'filters': JSON.stringify(p_filters)};
      return this.http.get<PaginatedPasse>(this.baseUrl + this.wsShowAllPassePaginatedUrl, { params: obj});
      /*
         page: 0
         size: 5
         sortfield: undefined
         sortorder: 1
         filters: {"nom":{"value":"aa","matchMode":"contains"},"cavalier":{"value":"bla","matchMode":"contains"}}
         */
    }

    public savePasse(passe: Passe) {
      return this.http.post<Passe>(this.baseUrl + this.wsSavePasseUrl, passe);
    }

    public readPasse(id: number) {
      const obj = {'id': id+''};
      return this.http.get<string>(this.baseUrl + this.wsReadPasseUrl, { params: obj});
    }
}
