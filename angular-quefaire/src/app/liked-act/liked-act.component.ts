import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-liked-act',
  templateUrl: './liked-act.component.html',
  styleUrls: ['./liked-act.component.css']
})
export class LikedActComponent implements OnInit {
  likedphrases:any

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getLikedActivities().subscribe(
      data => {
        this.likedphrases = data;
        console.log(data);
      },
      err => {
        this.likedphrases = JSON.parse(err.error).message;
      }
    );
  }

}
