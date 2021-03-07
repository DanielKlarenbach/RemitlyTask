import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'remitly-task';
  GBP : number = 0;
  PLN : number = 0;

  constructor(private http: HttpClient) { }

  convertGBPToPLN(){
    let obs = this.http.get<number>('http://localhost:8080/GBPToPLN/'+this.GBP.toString());
    obs.subscribe((response) => {
      this.PLN = response;
    });
  }
}
