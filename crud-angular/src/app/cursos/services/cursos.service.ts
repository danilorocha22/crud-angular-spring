import { environment } from './../../../environments/environment.prod';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, tap } from 'rxjs';

import { Curso } from './../models/curso';

@Injectable({
  providedIn: 'root'
})
export class CursosService {

  private readonly API = 'api/v1/cursos';

  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<Curso[]>(environment.BASE_URL + this.API)
      .pipe(
        first(),
        delay(5000),
        tap(cursos => console.log(cursos))
      );
  }

  save(curso: Partial<Curso>) {
    return this.httpClient.post<Curso>(environment.BASE_URL + this.API, curso)
      .pipe(first()) //pipe(first()) não é necessário
  }

  getById(id: string) {
    return this.httpClient.get<Curso>(`${this.API}/${id}`)
  }


}
