import {Location} from '@angular/common';
import {Component, OnInit} from '@angular/core';
import {NonNullableFormBuilder, Validators} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {Curso} from '../../models/curso';
import {AlertService} from '../../../shared/services/alert/alert.service';

import {CursosService} from '../../../shared/services/cursos/cursos.service';

@Component({
  selector: 'app-curso-form',
  templateUrl: './curso-form.component.html',
  styleUrls: ['./curso-form.component.scss']
})
export class CursoFormComponent implements OnInit {

  form = this.formBuilder.group({
    _id: [''],
    nome: ['',[
      Validators.required,
      Validators.minLength(2),
      Validators.maxLength(100)]],
    categoria: ['',[Validators.required]]
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
    const curso: Curso = this.activatedRoute.snapshot.data['curso']
    console.log(curso)
    this.form.setValue({
      _id: curso._id,
      nome: curso.nome,
      categoria: curso.categoria
    })

  }

  onSubmit() {

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
        complete: () => console.info('método salvar finalizado')
      })
  }

  /*onSubmit() {
    if (this.form.value.nome != '' && this.form.value.categoria != '' &&
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
          complete: () => console.info('método salvar finalizado')
        })
    } else {
      this.alert.show('Informe os dados para salvar')
    }
  }*/

  getErrorMessage(fieldName: string) {
    const field = this.form.get(fieldName)

    if (field?.hasError('required')) {
      return 'Campo obrigatório';
    }

    if (field?.hasError('minlength')) {
      const requiredLength = field.errors ? field.errors['minlength']['requiredLength'] : 5
      return `Informe no mínimo ${requiredLength} caracteres`
    }

    if (field?.hasError('maxlength')) {
      const requiredLength = field.errors ? field.errors['maxlength']['requiredLength'] : 100
      return `Informe no máximo ${requiredLength} caracteres`
    }

    return 'Campo Inválido'
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
