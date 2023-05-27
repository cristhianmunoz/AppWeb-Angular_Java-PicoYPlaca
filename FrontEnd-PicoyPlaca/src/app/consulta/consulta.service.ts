import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ConsultaService {
  private apiUrl = 'http://localhost:8080/api/consulta';

  constructor(private http: HttpClient) { }

  realizarConsulta(placa: string, fecha: string) {

    const requestData = {
      placa: placa,
      fecha: fecha
    };
    console.log(requestData);

    return this.http.post(this.apiUrl, requestData);
  }
}
