# fibonacci REST
## Descripción de la prueba
La evaluación técnica consiste en desarrollar diferentes microservicios y enviar el código fuente así
como los archivos compilados (si apllica)

## Serie de fibonacci
- Desarrollar un servicio REST que calcule la serie de fibonacci
- Recibir como parámetro el límite el cuál no podrá pasar el cálculo de dicha serie
- Se debe guardar en un archivo cada petición válida recibida por el servicio
- Se deben poder consultar los número enviados en peticiones anteriores
- Se debe poder eliminar un registro de número enviados 
- Generar los contratos de interfaz en swagger

## Consumo de servicio

###  Leer todos los registros guardados en el archivo
[http://localhost:8080/fibonacci/getAll/] [GET]

###  Recibir como parámetro el límite el cuál no podrá pasar el cálculo de dicha serie
[http://localhost:8080/fibonacci/get/{n}] [GET]

### poder eliminar un registro de número enviados 
[http://localhost:8080/fibonacci/delete/{n}] [DELETE]


- manejo de interfaz grafica
[http://localhost:8080/]
![alt text](https://github.com/tavo110699/fibonacci/blob/main/img/Captura%20de%20Pantalla%202022-09-24%20a%20la(s)%2020.52.58.png?raw=true)



## Ejecucion del proyecto
Ejecutar java spring boot, uilizando mvn. esto dependera de IDE del programador, para Visual Studio Code isntalar los recursos necesarios de SpringBoot y Java, Buscar la clase principal, presionar click derecho , buscar la opcion Run Java o en su defecto mediane la opcion de Run and Debug
