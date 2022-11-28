import { Injectable } from '@angular/core';
import { Place } from './place.model';
@Injectable({
  providedIn: 'root'
})
export class PlacesService {

  private places: Place[] = [
    {
      id: '1',
      title: 'Montando el caballito',
      imageURL: 'https://img2.xnostars.com/videos/xander-corvus-conito-depilado-dillion-harper/thumbs/xander-corvus-conito-depilado-dillion-harper-08.jpg',
      comments: ['Asombrosa vagina', 'preciosa vagina']
    },
    {
      id: '2',
      title: 'Dando sentones',
      imageURL: 'https://static1.definebabe.com/p/1/41/99673/Dillion-Harper-bang-bros-network-07.jpg',
      comments: ['Asombrosa vagina', 'preciosa vagina']
    }, {
      id: '3',
      title: 'Monstrando la vagina',
      imageURL: 'https://static-ca-cdn.eporner.com/gallery/YV/qv/fTzNQPNqvYV/471621-dillion-harper-nude_880x660.jpg',
      comments: ['Asombrosa vagina', 'preciosa vagina']
    }, {
      id: '4',
      title: 'Chupango pene',
      imageURL: 'https://img-14.poringa.net/poringa/img/2/0/E/4/5/C/Hatesoul/709.jpg',
      comments: ['Asombrosa vagina', 'preciosa vagina']
    }, {
      id: '5',
      title: 'Dedito en el culo',
      imageURL: 'https://img-15.poringa.net/poringa/img/A/C/2/1/C/4/Hatesoul/A13.jpg',
      comments: ['Asombrosa vagina', 'preciosa vagina']
    }, {
      id: '6',
      title: 'Pene en el culo',
      imageURL: 'https://img-15.poringa.net/poringa/img/C/9/A/A/8/F/Hatesoul/D16.jpg',
      comments: ['Asombrosa vagina', 'preciosa vagina']
    }, {
      id: '7',
      title: 'Pene en el vagina y dedo en el culo',
      imageURL: 'https://img-15.poringa.net/poringa/img/5/5/F/5/7/D/Hatesoul/C9A.jpg',
      comments: ['Asombrosa vagina', 'preciosa vagina']
    }

  ]

  constructor() { }

  getPlaces() {
    return [...this.places]
  }

  getPlace(placeId: string) {
    return {
      ...this.places.find(place => {
        return place.id === placeId
      })
    }
  }

  addPlace(title: string, imageURL: string) {
    this.places.push({
      title,
      imageURL,
      comments: [],
      id: this.places.length + 1 + ""
    });
  }

  deletePlace(placeId: string) {
    this.places = this.places.filter(place => {
      return place.id !== placeId
    })
  }


}
