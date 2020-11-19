import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {StartComponent} from './components/start/start.component';
import {BandComponent} from './components/band/band.component';
import {PersonComponent} from './components/person/person.component';


const routes: Routes = [
  {path: '', component: StartComponent},
  {path: 'bands', component: BandComponent},
  {path: 'persons', component: PersonComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
