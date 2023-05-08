import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Curso} from "../../models/curso";

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
  @Output() add: EventEmitter<any> = new EventEmitter(false)
  @Output() edit: EventEmitter<any> = new EventEmitter(false)
  @Output() delete: EventEmitter<any> = new EventEmitter(false)
  // readonly: é uma propriedade do TS para deixar o objeto como final
  readonly displayedColumns: string[] = ['nome', 'categoria', 'acoes'];

  onAdd() {
    //this.router.navigate(['novo'], {relativeTo: this.route})
    // @ts-ignore
    this.add.emit(true)
  }

  onEdit(curso: Curso): void {
    this.edit.emit(curso)
  }

  onDelete(curso: Curso): void {
    this.delete.emit(curso)
  }


}
