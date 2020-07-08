import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SalsaListePassesComponent } from './salsa-liste-passes/salsa-liste-passes.component';

const routes: Routes = [
  //{ path: 'liste-passes', component: SalsaListePassesComponent }
  { path: 'liste-passes', component: SalsaListePassesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
