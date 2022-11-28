import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PlacesPage } from './places.page';

const routes: Routes = [
  {
    path: '',
    component: PlacesPage
  },
  {
    path: 'palce-detail',
    loadChildren: () => import('./place-detail/place-detail.module').then( m => m.PlaceDetailPageModule)
  },
  {
    path: 'place-detail',
    loadChildren: () => import('./place-detail/place-detail.module').then( m => m.PlaceDetailPageModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PlacesPageRoutingModule {}
