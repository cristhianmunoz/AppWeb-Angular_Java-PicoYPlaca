package com.cdma.backendpicoyplaca.controllers;

import com.cdma.backendpicoyplaca.services.ConsultaRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // Reemplaza con la URL de tu frontend
//@RequestMapping("/api/consulta")
public class ConsultaController {

    @PostMapping("/api/consulta")
    public ResponseEntity<String> realizarConsulta(@RequestBody ConsultaRequest request) {
        String placa = request.getPlaca();
        String fechaString = request.getFecha();

        LocalDateTime fecha = LocalDateTime.parse(fechaString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Realiza la lógica de validación aquí
        boolean puedeCircular = validarPicoYPlaca(placa, fecha);

        if (puedeCircular) {
            return new ResponseEntity<>("El vehículo con placa " + placa + " puede circular en la fecha " + fechaString, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("El vehículo con placa " + placa + " no puede circular en la fecha " + fechaString, HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validarPicoYPlaca(String placa, LocalDateTime fecha) {
        // Obtiene el último dígito de la placa
        int ultimoDigito = Integer.parseInt(placa.substring(placa.length() - 1));

        // Verifica si es sábado, domingo o feriado
        boolean esFinDeSemanaOFeriado = fecha.getDayOfWeek().getValue() >= 6; // 6 representa sábado y 7 representa domingo
        // Aquí puedes agregar la lógica para verificar si es feriado

        // Verifica si está dentro de los horarios de restricción
        LocalTime hora = fecha.toLocalTime();
        boolean estaEnHorarioRestriccion = hora.isAfter(LocalTime.of(6, 0)) && hora.isBefore(LocalTime.of(9, 30))
                || hora.isAfter(LocalTime.of(16, 0)) && hora.isBefore(LocalTime.of(20, 0));

        // Verifica si cumple con las restricciones de día y último dígito de la placa
        if (!esFinDeSemanaOFeriado && estaEnHorarioRestriccion) {
            switch (fecha.getDayOfWeek()) {
                case MONDAY:
                    return ultimoDigito == 1 || ultimoDigito == 2;
                case TUESDAY:
                    return ultimoDigito == 3 || ultimoDigito == 4;
                case WEDNESDAY:
                    return ultimoDigito == 5 || ultimoDigito == 6;
                case THURSDAY:
                    System.out.println(ultimoDigito+"siiiuuuu8");
                    return ultimoDigito == 7 || ultimoDigito == 8;
                case FRIDAY:
                    return ultimoDigito == 9 || ultimoDigito == 0;
                default:
                    return true; // Otros días de la semana no tienen restricción
            }
        } else {
            return true; // Fuera de los horarios de restricción o días de fin de semana/feriados
        }
    }
}
