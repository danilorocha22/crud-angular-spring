import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Curso } from '../../models/curso';
import { CursosService } from '../../services/cursos.service';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.scss']
})
export class CursosComponent implements OnInit {

  cursos$: Observable<Curso[]> | null = null;

  constructor(
    private cursosService: CursosService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.refresh()
  }

  refresh() {
    this.cursos$ = this.cursosService.list()
      .pipe(
        catchError(() => {
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
    this.router.navigate(['novo'], { relativeTo: this.route })
  }

  onEdit(curso: Curso) {
    this.router.navigate(['editar', curso._id], { relativeTo: this.route })
  }

  onDelete(curso: Curso) {
    this.cursosService.remove(curso._id)
      .subscribe({
        next: () => {
          this.refresh(),
            this.snackBar.open('Curso removido com sucesso!', 'X', {
              duration: 3000,
              verticalPosition: 'top',
              horizontalPosition: 'center'
            })
        },
        error: () => this.onError('Erro ao tentar remover curso.'),
        complete: () => console.info('remove completed')
      })
  }

  //controla o ciclo de vida do componente
  ngOnInit(): void { }

}
