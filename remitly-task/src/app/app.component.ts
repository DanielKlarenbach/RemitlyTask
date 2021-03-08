import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {environment} from "../environments/environment";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  baseUrl = environment.baseUrl;
  title = 'remitly-task';
  GBP : number = 0;
  PLN : number = 0;

  constructor(private http: HttpClient) { }

  convertGBPToPLN(){
    let obs = this.http.get<number>(this.baseUrl+'GBPToPLN/'+this.GBP.toString());
    obs.subscribe(
      response => this.PLN = response,
      error => {
        console.log(error);
        alert(error.error.message);
      }
    );
  }
}
