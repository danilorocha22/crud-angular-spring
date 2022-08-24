import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {ActivatedRoute, Router} from '@angular/router';
import {catchError, Observable, of} from 'rxjs';
import {ErrorDialogComponent} from 'src/app/shared/components/error-dialog/error-dialog.component';

import {Curso} from '../models/curso';
import {CursosService} from '../services/cursos.service';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.scss']
})
export class CursosComponent implements OnInit {

  cursos$: Observable<Curso[]>
  //cursosService: CursosService;

  constructor(
    private cursosService: CursosService,
     public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute
    ) {
    //this.cursos = [];
    //this.cursosService = new CursosService();//injeção de dependência
    this.cursos$ = this.cursosService.list()
    .pipe(
      catchError(error => {
        this.onError('Não foi possível carregar os cursos, tente novamente mais tarde.')
        return of([]);
      })
    );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  onAdd() {
    this.router.navigate(['novo'], {relativeTo: this.route})
  }

  //controla o ciclo de vida do componente
  ngOnInit(): void {}

}
