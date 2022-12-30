import { Curso } from './../../models/curso';
import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { CursosService } from '../../services/cursos.service';

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
    private snackBar: MatSnackBar,
    private location: Location,
    private activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit():void {
    const CURSO:Curso = this.activatedRoute.snapshot.data['curso']
    this.form.setValue({
      _id: CURSO._id,
      nome: CURSO.nome,
      categoria: CURSO.categoria
    })

  }

  onSubmit() {
    this.service.save(this.form.value)
      .subscribe({
        next: () => this.onSuccess(),
        error: () => this.onError(),
        complete: () => console.info('save finish')
      })
  }

  onCancel() {
    this.location.back()
  }

  private onSuccess() {
    this.snackBar.open(
      'Curso salvo com sucesso.',
      'X',
      { duration: 3000 }
    )
    this.onCancel()
  }

  private onError() {
    this.snackBar.open(
      'Erro ao tentar salvar curso.',
      'X',
      { duration: 3000 }
    )
  }


}
