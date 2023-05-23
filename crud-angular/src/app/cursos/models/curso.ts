import {Aulas} from "./aulas";

export interface Curso {
  _id: string;
  nome: string;
  categoria: string;
  aulas: Aulas[];
}
