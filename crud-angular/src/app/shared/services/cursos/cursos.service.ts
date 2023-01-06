import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, tap } from 'rxjs';
import { environment } from '../../../../environments/environment';

import { Curso } from '../../../cursos/models/curso';

@Injectable({
  providedIn: 'root'
})
export class CursosService {

  private readonly API = 'cursos';

  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<Curso[]>(environment.API + environment.BASE_URL + this.API)
      .pipe(
        first(),
        /* delay(5000),
        tap(courses => console.log(courses)) */
      );
  }

  save(curso: Partial<Curso>) {
    if (curso._id) {
      return this.update(curso);
    }
    return this.create(curso);
  }

  getById(id: string) {
    return this.httpClient.get<Curso>(environment.API + environment.BASE_URL + `${this.API}/${id}`)
  }

  remove(id: string) {
    return this.httpClient.delete(environment.API + environment.BASE_URL + `${this.API}/${id}`).pipe(first());
  }


  private create(curso: Partial<Curso>) {
    return this.httpClient.post<Curso>(environment.API + environment.BASE_URL + this.API, curso).pipe(first());
  }

  private update(curso: Partial<Curso>) {
    return this.httpClient.put<Curso>(environment.API + environment.BASE_URL + `${this.API}/${curso._id}`, curso).pipe(first());
  }

}
