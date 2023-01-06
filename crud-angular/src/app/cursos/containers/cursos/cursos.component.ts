import { ConfirmDialogComponent } from './../../../shared/components/confirm-dialog/confirm-dialog.component';
import { Component } from '@angular/core';
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
export class CursosComponent {

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

  onAdd() {
    this.router.navigate(['novo'], { relativeTo: this.route })
  }

  onEdit(curso: Curso) {
    this.router.navigate(['editar', curso._id], { relativeTo: this.route })
  }

  onDelete(curso: Curso) {

    let res = this.onDialog('Excluir!', `Deseja excluir o curso ${curso.nome}?`)
    console.log(res)
  

    /* this.cursosService.remove(curso._id)
      .subscribe({
        next: () => {
          this.refresh()
          this.onSuccess('Curso removido com sucesso!')
        },
        error: () => this.onError('Erro ao tentar remover curso.'),
        complete: () => console.info('remove completed')
      }) */

  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  onSuccess(msg: string) {
    this.snackBar.open(msg, 'X', {
      duration: 3000,
      verticalPosition: 'top',
      horizontalPosition: 'center'
    })
  }

  onDialog(titulo: string, msg: string) {

    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: { titulo, msg }
    })

    dialogRef.afterClosed().subscribe(res => {
      //console.log(Boolean(res))
      return Boolean(res)
    })

  }

}
