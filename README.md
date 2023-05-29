# AppWeb Pico Y Placa (Angular & Java)
Aplicativo Web que valida la posibilidad de circular en la ciudad de Quito bajo la restricción del "Pico y Placa".

## Tecnologias utilizadas

### Front End
* Node "18.16.0",
* NPM "9.5.1"
* Angular CLI 16.0.3

### Back End (Spring Boot)
* JDK 17.0.7 2023-04-18 LTS, se recomienda el uso de bellsoft: https://bell-sw.com/pages/downloads/#/java-17-lts
* Apache Maven 3.9.2

## Ejecutación

### Front End
  * Copiar la carpeta "front-end-picoy-placa" proyecto de la ubicación "\AppWeb-Angular_Java-PicoYPlaca\FrontEnd-PicoYPlaca\dist".
  * Y pegarlo en su servidor de preferencia, recomiendo usar Node Express.
    * Para hacer uso de Node Express localmente, recomiendo hacer uso de la siguiente linea de código:
      *  'npm install express path --save'

### Back End
  1. Abrir una terminal (cmd) y localizarse en la carpeta 
     * "\AppWeb-Angular_Java-PicoYPlaca\BackEnd-PicoYPlaca\out\artifacts\BackEnd_PicoYPlaca_jar".
  2. Ejecutar el siguiente comando:
     * 'java -jar BackEnd-PicoYPlaca.jar'
  
