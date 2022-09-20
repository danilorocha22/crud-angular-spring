import { Curso } from './../models/curso';
import { CursosService } from './../services/cursos.service';
import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CursoResolver implements Resolve<Curso> {

  constructor(
    private cursosService: CursosService
  ) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Curso> {
    if (route.params && route.params['id']) {
      return this.cursosService.getById(route.params['id'])
    }
    return of({ _id: '', nome: '', categoria: '' });
  }
}
