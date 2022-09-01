import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Curso} from "../../models/curso";

@Component({
  selector: 'app-cursos-list',
  templateUrl: './cursos-list.component.html',
  styleUrls: ['./cursos-list.component.scss']
})
export class CursosListComponent implements OnInit {

  // @Input(): é um decorator, para passar os cursos do componente pai (cursos) para o componente filho (cursos-list)
  @Input() cursos: Curso[] = [];
  // @Output():
  // @ts-ignore
  @Output() add = new EventEmitter
  // readonly: é uma propriedade do TS para deixar o objeto como final
  readonly displayedColumns = ['nome', 'categoria', 'acoes'];

  constructor(
    /*private router: Router,
    private route: ActivatedRoute*/
  ) { }

  ngOnInit(): void {
  }

  onAdd() {
    //this.router.navigate(['novo'], {relativeTo: this.route})
    // @ts-ignore
    this.add.emit(true)
  }

}
