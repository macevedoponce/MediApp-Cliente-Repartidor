import { Component, OnInit } from '@angular/core';
import {  ActivatedRoute  } from '@angular/router'
import { Place } from '../place.model';
import { PlacesService } from '../places.service';
@Component({
  selector: 'app-place-detail',
  templateUrl: './place-detail.page.html',
  styleUrls: ['./place-detail.page.scss'],
})
export class PlaceDetailPage implements OnInit {

  constructor(private activatedRoute: ActivatedRoute,private placesService: PlacesService) { }
place:Place
  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(paramMap =>{
      //redirect
      const recipeId = paramMap.get('placeId')
      this.place = this.placesService.getPlace(recipeId);
      console.log(this.place)
    })
  }

}
