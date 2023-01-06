import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from './app-material/app-material.module';
import { DialogComponent } from './components/dialog/dialog.component';
import { CategoriaPipe } from './pipes/categoria.pipe';



@NgModule({
  declarations: [
    DialogComponent,
     CategoriaPipe
  ],
  imports: [
    CommonModule,
    AppMaterialModule
  ],
  exports: [
    DialogComponent,
    CategoriaPipe
  ]
})
export class SharedModule { }
