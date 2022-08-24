import {Location} from '@angular/common';
import {Component, OnInit} from '@angular/core';
import {NonNullableFormBuilder} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';

import {CursosService} from '../services/cursos.service';

@Component({
  selector: 'app-curso-form',
  templateUrl: './curso-form.component.html',
  styleUrls: ['./curso-form.component.scss']
})
export class CursoFormComponent implements OnInit {

  form = this.formBuilder.group({
    nome: [''],
    categoria: ['']
  })

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private service: CursosService,
    private snackBar: MatSnackBar,
    private location: Location
  ) {
    //this.form
  }

  ngOnInit(): void {}

  onSubmit() {
    this.service.save(this.form.value)
    .subscribe({
      next: () => this.onSuccess(),
      error: () => this.onError(),
      complete: () => console.info('complete')
    })
  }

  onSuccess() {
    this.snackBar.open(
      'Curso salvo com sucesso.',
      'Fechar',
      {duration:3000}
    )
    this.onCancel()
  }

  onError() {
    this.snackBar.open(
      'Erro ao tentar salvar curso.',
      'Fechar',
      {duration:3000}
      )
  }

  onCancel() {
    this.location.back()
  }

}
