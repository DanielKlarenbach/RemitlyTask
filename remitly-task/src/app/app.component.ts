import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from "../environments/environment";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  baseUrl = environment.baseUrl;
  title = 'remitly-task';
  GBP: number = 0;
  PLN: number = 0;
  currencyToConvert: String = ' GBP';


  constructor(private http: HttpClient) {
  }

  setCurrencyToConvert(event: Event) {
    // @ts-ignore
    this.currencyToConvert = event.target.id;
  }

  convert() {
    if (this.currencyToConvert == 'GBP') {
      this.convertGBPToPLN();
    } else {
      this.convertPLNToGBP();
    }
  }

  convertGBPToPLN() {
    let obs = this.http.get<number>(this.baseUrl + 'GBPToPLN/' + this.GBP.toString());
    obs.subscribe(
      response => this.PLN = response,
      error => {
        console.log(error);
        alert(error.error.message);
      }
    );
  }

  convertPLNToGBP() {
    let obs = this.http.get<number>(this.baseUrl + 'PLNToGBP/' + this.PLN.toString());
    obs.subscribe(
      response => this.GBP = response,
      error => {
        console.log(error);
        alert(error.error.message);
      }
    );
  }

}
