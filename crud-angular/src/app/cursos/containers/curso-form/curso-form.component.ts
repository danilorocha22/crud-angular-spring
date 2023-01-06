import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Curso } from './../../models/curso';
import { AlertService } from '../../../shared/services/alert/alert.service';

import { CursosService } from '../../../shared/services/cursos/cursos.service';

@Component({
  selector: 'app-curso-form',
  templateUrl: './curso-form.component.html',
  styleUrls: ['./curso-form.component.scss']
})
export class CursoFormComponent implements OnInit {

  form = this.formBuilder.group({
    _id: [''],
    nome: [''],
    categoria: ['']
  })

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private service: CursosService,
    private alert: AlertService,
    private location: Location,
    private activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    const CURSO: Curso = this.activatedRoute.snapshot.data['curso']
    this.form.setValue({
      _id: CURSO._id,
      nome: CURSO.nome,
      categoria: CURSO.categoria
    })

  }

  onSubmit() {
    if(this.form.value.nome != '' && this.form.value.categoria != '' &&
     this.form.value.categoria != 'null') {

      this.service.save(this.form.value)
        .subscribe({
          next: () => {
            if (this.form.value._id)
              this.alert.show('Curso atualizado com sucesso.')
            else
              this.alert.show('Curso salvo com sucesso.')

            this.onCancel()
          },
          error: () => {
            if (this.form.value._id)
              this.alert.show('Não foi possível atualizar o curso.')
            else
              this.alert.show('Não foi possível salvar o curso.')
          },
          complete: () => console.info('metodo de salvar finalizado')
        })
    } else{
      this.alert.show('Informe os dados para salvar')
    }
  }

  onCancel() {
    this.location.back()
  }

  /* private alert(msg: string) {
    this.snackBar.open(msg, 'X', {
      duration: 3000,
      verticalPosition: 'top',
      horizontalPosition: 'center'
    })
  } */

}
