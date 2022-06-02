import { environment } from './../../../environments/environment.prod';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, tap } from 'rxjs';

import { Curso } from './../models/curso';

@Injectable({
  providedIn: 'root'
})
export class CursosService {

  private readonly API = 'cursos';

  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<Curso[]>(environment.BASE_URL + this.API)
    .pipe(
      first(),
      tap(cursos => console.log(cursos))
      );
  }

  salve(registro: Curso) {
    return this.httpClient.post<Curso>( environment.BASE_URL + this.API, registro)
    .pipe(first()) //pipe(first()) não é necessário
  }

}
