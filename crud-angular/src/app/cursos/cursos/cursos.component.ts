import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

import { Curso } from './../models/curso';
import { CursosService } from './../services/cursos.service';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.scss']
})
export class CursosComponent implements OnInit {

  cursos: Observable<Curso[]>;
  displayedColumns = ['nome', 'categoria'];
  //cursosService: CursosService;

  constructor(private cursosService: CursosService) {
    //this.cursos = [];
    //this.cursosService = new CursosService();//injeção de dependência
    this.cursos = this.cursosService.list();
  }

  //controla o ciclo de vida do componente
  ngOnInit(): void {}

}
