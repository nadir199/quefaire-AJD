import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/test/';
const API_PHRASE_URL = 'http://localhost:8080/randomphrase/'
const API_PH_URL = 'http://localhost:8080/phrase/'


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user', { responseType: 'text' });
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'mod', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }

  getRandomPhrase() : Observable<any> {
    return this.http.get(API_PHRASE_URL, {responseType : 'json'});
  }
  
  getLikeStatus(phid) : Observable<any> {
    return this.http.get(API_PH_URL + phid + "/isliked", {responseType : 'json'});
  }

  triggerLikePhrase(phid) : Observable<any> {
    return this.http.get(API_PH_URL + phid + "/like", {responseType : 'json'});
  }

  getNumberReactions(phid) : Observable<any> {
    return this.http.get(API_PH_URL + phid + "/reactions", {responseType : 'json'});
  }


  getLikedActivities() : Observable<any> {
    return this.http.get(API_PH_URL + "likedphrases", {responseType : 'json'});
  }
}
