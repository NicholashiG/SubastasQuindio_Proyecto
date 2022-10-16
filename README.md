
# Todo List

## Log

Poder ingresar minimo dos usuarios (Guardar en  un Log estos logins)

Registrar todas las acciones que se hacen en las interfaces (CRUD y transaccional)

Registrar las excepciones propias

## Manejo de archivos de texto plano

Cargar y Guardar 3 objetos del modelo con el siguiente formato

``` code
<dato_1>@@< dato_2>@@< dato_3>@@< dato_4>
```

Ejemplo 
``` 
nombre@@apellid>@@cedula@@dirección
```

Cargar y Guardar el registro de la transaccion 
```
Por ejemplo, si la transacción es de un retiro, este archivo debe tener la información de la transacción,la cuenta, el cliente y el empleado asociado. De tal forma, que en el archivo se guarde la información de la siguiente forma: (esto es un ejemplo de cómo se puede guardar)
<numeroTransacción>@@<hora>@@<fecha>@@<valor>@@<numeroCuenta>@@<cedulaCliente>@@< codigoEmpleado>
De esta forma, cuando se vuelva cargar la información del archivo de la transacción se va a saber con qué cliente y empleado está asociado, y a que cuenta pertenece.
Cada vez que se realice una transacción (Ejemplo) se adiciona esta información al archivo.
Del archivo que maneja la persistencia de la información de la transacción se va a realizar una copia de dicho archivo y lo va alojar en la carpeta respaldo con el formato del nombre del archivo como se muestra en la imagen de estructura de carpetas.

```

## Implementacion de flujo de datos
la verdad no se a que se refiere, dice que hacer el mismo montaje del banco

## Manejo de archivos serializados

Cada vez que se inicie la aplicacion se va a guardar una copia del archivo XML, es decir. va a copiar archivos dichos archivos y los ca alojar en la carpeta respaldo con el formato mostrado en la estructura (imagen abajo)

## Explicacion

Montar uno o varios vídeos en una plataforma web de reproducción de vídeos (por ejemplo youtube.) Poner enlaces del vídeo. 

Se debe explicar cómo entendió la estructura del proyecto con el manejo de archivos y se debe mostrar lo solicitado en los puntos 1 al 4. 

- Explicar cómo está implementado lo de flujo de datos en sus proyecto
- Explicar en orden cuando:
  - //iniciarYSalvarDatosPrueba() //Mostrando datos en código y como se guardan en los respectivos archivos.
  - //cargarDatosDesdeArchivos()//Cargar los datos desde los archivos (4 archivos)
  - //guardarResourceBinario()
  - //cargarResourceBinario()    
  - //guardarResourceXML()    
  - cargarResourceXML()

Resultado final es que se cargue la información por medio del archivo serializado Binario o XML

- Explicar los demás puntos
- NO se permite que la explicación del vídeo se realice de forma textual, sólo verbal. 
- Cada integrante del grupo va a realizar una intervención donde explique alguno de los puntos solicitados.
- Deben ser los mismos integrantes del grupo que han estado realizando las entregas sobre el proyecto final.
