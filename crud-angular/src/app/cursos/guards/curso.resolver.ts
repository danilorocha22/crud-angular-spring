import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot} from '@angular/router';
import {Observable, of} from 'rxjs';
import {Curso} from '../models/curso';
import {CursosService} from '../../shared/services/cursos/cursos.service';

@Injectable({
  providedIn: 'root'
})
export class CursoResolver  {

  constructor(
    private cursosService: CursosService
  ) { }

  resolve(route: ActivatedRouteSnapshot): Observable<Curso> {
    if (route.params && route.params['id']) {
      return this.cursosService.getById(route.params['id'])
    }
    return of({ _id: '', nome: '', categoria: '' });
  }
}
