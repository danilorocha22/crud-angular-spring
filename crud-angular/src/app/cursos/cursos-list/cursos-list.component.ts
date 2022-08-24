import { Component, OnInit } from '@angular/core';
import {Curso} from "../models/curso";
import {ActivatedRoute, Router} from "@angular/router";
import {Input} from "@angular/core";

@Component({
  selector: 'app-cursos-list',
  templateUrl: './cursos-list.component.html',
  styleUrls: ['./cursos-list.component.scss']
})
export class CursosListComponent implements OnInit {

  // @Input(): é um decorator, para passar os cursos do componente pai (cursos) para o componente filho (cursos-list)
  @Input() cursos: Curso[] = [];
  // readonly: é uma propriedade do TS para deixar o objeto como final
  readonly displayedColumns = ['nome', 'categoria', 'acoes'];

  constructor(
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
  }

  onAdd() {
    this.router.navigate(['novo'], {relativeTo: this.route})
  }

}
