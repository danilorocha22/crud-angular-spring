import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { ConfirmDialogComponent } from '../../../shared/components/confirm-dialog/confirm-dialog.component';
import { AlertService } from '../../../shared/services/alert/alert.service';

import { Curso } from '../../models/curso';
import { CursosService } from '../../../shared/services/cursos/cursos.service';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.scss']
})
export class CursosComponent {

  cursos$: Observable<Curso[]> | null = null
  dialogRef: MatDialogRef<ConfirmDialogComponent, any> | null = null

  constructor(
    private cursosService: CursosService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private alert: AlertService
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

  onAdd() :void {
    this.router.navigate(['novo'], {relativeTo: this.route})
      .then(r => console.log(`Sucesso para rota Novo: ${r}`))
   }

  onEdit(curso: Curso) :void {
    this.router.navigate(['editar', curso._id], {relativeTo: this.route})
      .then(r => console.log(`Sucesso para rota Editar: ${r}`))
   }

  onDelete(curso: Curso) :void {
    this.onDialog('Excluir?', `Deseja excluir o curso ${curso.nome}?`)

    this.dialogRef!.afterClosed().subscribe(res => {
      console.log(Boolean(res))

      if (res) {
        this.cursosService.remove(curso._id)
          .subscribe({
            next: () => {
              this.refresh()
              this.alert.show('Curso removido com sucesso!')
            },
            error: () => this.onError('Erro ao tentar remover curso.'),
            complete: () => console.info('remove completed')
          })
      }
    })
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  onDialog(titulo: string, msg: string) {
    this.dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: { titulo, msg }
    })
  }

}
