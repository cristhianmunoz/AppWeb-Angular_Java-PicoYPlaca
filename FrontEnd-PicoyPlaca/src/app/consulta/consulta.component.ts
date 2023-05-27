import { Component } from '@angular/core';
import { ConsultaService } from './consulta.service';

interface ConsultaResponse {
  status: string;
  message: string;
}

@Component({
  selector: 'app-consulta',
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.css']
})
export class ConsultaComponent {
  placa: string = '';
  fecha: string = '';
  resultado: string = '';

  constructor(private consultaService: ConsultaService) { }

  submitForm() {
    // Validación de la fecha
    const fechaActual = new Date();
    const fechaIngresada = new Date(this.fecha);

    if (fechaIngresada < fechaActual) {
      alert('La fecha ingresada debe ser igual o posterior a la fecha actual.');
      return;
    }

    alert(this.fecha)
    // Envío de la solicitud al backend
    this.consultaService.realizarConsulta(this.placa, this.fecha)
      .subscribe((response:any) => {
        const consultaResponse: ConsultaResponse = response as ConsultaResponse;

        // Manejo de la respuesta del backend
        if (consultaResponse.status === 'OK') {
          this.resultado = 'El vehículo con placa ' + this.placa + ' puede circular en la fecha ' + this.fecha;
          alert(this.resultado);
        } if (consultaResponse.status === 'BAD_REQUEST') {
          this.resultado = 'El vehículo con placa ' + this.placa + ' no puede circular en la fecha ' + this.fecha;
          alert(this.resultado);
        }
      }, error => {
        // Manejo de errores de la solicitud
        this.resultado = 'Error al realizar la consulta';
        alert(this.resultado);
      });
  }
}


//alert(this.fecha);
