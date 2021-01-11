import { Component, OnInit } from '@angular/core';
import { faHeart } from '@fortawesome/free-solid-svg-icons';


import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-random-action',
  templateUrl: './random-action.component.html',
  styleUrls: ['./random-action.component.css']
})
export class RandomActionComponent implements OnInit {
  phraseid : number
  phrase : string // = "Faites 20 pompes en moins de 1 minute";
  categorie : string // = "Sport"
  faHeart = faHeart

  liked = false
  nblikes : number
  constructor(private userService: UserService) { }


  ngOnInit(): void {
    this.userService.getRandomPhrase().subscribe(
      data => {
        this.phraseid = data['id'];
        this.phrase = data['phrase'];
        this.categorie = data['category']['name'];
        console.log(data);

        this.userService.getLikeStatus(data['id']).subscribe(
          data => {
            this.liked = data;
          },
          err => {
            this.phrase = JSON.parse(err.error).message;
          }
        );

        this.userService.getNumberReactions(data['id']).subscribe(
          data => {
            this.nblikes = data;
          },
          err => {
            this.phrase = JSON.parse(err.error).message;
          }
        );
      },
      err => {
        this.phrase = JSON.parse(err.error).message;
      }
    );
    
  }

  getCategorie(){
  		return this.categorie
  }

  changeValuesLike() : void {
      this.userService.triggerLikePhrase(this.phraseid).subscribe(
        data => {
        },
        err =>{

        }
      );
  		if(this.liked){
        this.liked = false
        this.nblikes = this.nblikes - 1
  		}else{
        this.liked = true
        this.nblikes = this.nblikes + 1

  		}
  }
}
