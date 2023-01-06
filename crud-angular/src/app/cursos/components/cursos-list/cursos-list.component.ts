import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Curso } from "../../models/curso";

@Component({
  selector: 'app-cursos-list',
  templateUrl: './cursos-list.component.html',
  styleUrls: ['./cursos-list.component.scss']
})
export class CursosListComponent {

  // @Input(): é um decorator, para passar os cursos do componente pai (cursos) para o componente filho (cursos-list)
  @Input() cursos: Curso[] = [];
  // @Output():
  // @ts-ignore
  @Output() add = new EventEmitter(false)
  @Output() edit = new EventEmitter(false)
  @Output() delete = new EventEmitter(false)
  // readonly: é uma propriedade do TS para deixar o objeto como final
  readonly displayedColumns = ['nome', 'categoria', 'acoes'];

  onAdd() {
    //this.router.navigate(['novo'], {relativeTo: this.route})
    // @ts-ignore
    this.add.emit(true)
  }

  onEdit(curso: Curso) {
    this.edit.emit(curso)
  }

  onDelete(curso: Curso) {
    this.delete.emit(curso)
  }



}
