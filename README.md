# Creacion de Usuarios / Api-Rest Full con Java & SpringBoot

[![Build Status](https://travis-ci.org/MatiasLoiseau/api-rest.svg?branch=master)](https://travis-ci.org/MatiasLoiseau/api-rest)
[![Coverage Status](https://coveralls.io/repos/github/MatiasLoiseau/api-rest/badge.svg?branch=master&service=github)](https://coveralls.io/github/MatiasLoiseau/api-rest?branch=master)

# Tabla de Contenidos

1. [Introducción](#introducción)
2. [Instalación](#instalación)
    * [Requisitos básicos](#requisitos-básicos)
    * [Pasos para la instalación](#pasos-para-la-instalación)
    * [POSTMAN](#postman)
3. [Estructura del Proyecto](#estructura-del-proyecto)
4. [Test](#test)

---

# Introducción

El objetivo de esta API será exponer un servicio para la creacion de usuarios.
La API manejará los siguientes recursos:
* [User](#user)
* [Phone](#phone)
* [UserService](#userservice)
* [PhoneService](#phoneservice)
* [UserRepository](#userrepository)
* [PhoneRepository](#phonerepository)
* [UserController](#userController)

Todas los peticiones devolveran en formato JSON.

### POSTMAN
Las consultas de prueba sobre la API fueron realizadas con POSTMAN, se pueden encontrar ejemplos en el archivo [SET PRUEBA](https://github.com/MatiasLoiseau/api-rest/blob/master/SET%20PRUEBA)

Para su instalación deberán realizarse los siguientes pasos:

		# Distribuciones basadas en 64-bit:
		dowload Postman-win64-Setup and install postman

En caso de necesitar más información puede referirse a la [documentación](https://learning.postman.com/docs/introduction/overview/) de POSTMAN.

---

# Estructura del proyecto

### Archivos del proyecto
Se listarán los archivos principales del proyecto, con el fin de explicar su propósito:
* UserController.java: Implementa los controladores.
* UserResponse.java: Implementa la respuesta esperada cuando se crea correctamente un usuario.
* ErrorResponse.java: Implementa la respuesta esperada cuando se valida la existencia del correo al momento de realizar la creacion de un usuario.
* UserRepository.java: Implementa las operaciones para el repositorio.
* PhoneRepository.java: Implementa las operaciones para el repositorio.
* UserService.java: Declara los servicios para la gestion del user.
* UserServiceImpl.java: Implementa los servicios del user.
* PhoneService.java: Declara los servicios de para la gestion de phone.
* PhoneServiceImpl.java: Implementa los servicios de para la gestion de phone.
* User.java: Implementa la clase principal para la creacion de User.
* Phone.java: Implementa la clase phone para ser utilizada por la clase User.java para la creacion de usuarios.
___
### Archivos de los recursos
Se listan los archivos principales de los recursos, con el fin de explicar su propósito:
* application.properties: se deja la configuracion de la aplicacion
* schema.sql: Contiene el script de creacion de la estructura de la base de datos y sus tablas.
___

### Métodos
#### Crear un usuario
##### Parámetros
* name
* email
* password
* phones[]

##### Request
	 http POST  http://localhost:8080/api/registerUser
##### Responses
Algunas de las posibles respuestas:
* 200: En caso de que la consulta haya sido realizada con éxito
* 201: En caso de que la creacion de usuario haya sido realizada con éxito
* 400: En caso de que la consulta responda con un error
* 404: En caso de que no haya sido encontrado el objeto
* 500: Error en el servidor

Si la respuesta es exitosa se devolverá el recurso en formato JSON
Ejemplo:
         HTTP/1.1 201 OK
```json
         {
            "id": "1f48aea5-2771-4e68-8e6a-f62a020d3ac9",
            "created": "2024-09-05T04:14:05.619+00:00",
            "lastModified": null,
            "lastLogin": "2024-09-05T04:14:05.619+00:00",
            "isActive": true,
            "token": "62050355-781b-44ee-806c-1095576d10b0"
         }
```
Si la respuesta no es exitosa (error) ya que la validacion del correo retorna una existencia persistida se devolverá el recurso en formato JSON
Ejemplo:
         HTTP/1.1 400 BAD REQUEST
```json
         {
            "message": "El Correo ya esta registrado"
         }
```
***

# Test

### Ejecución de los test
Para correr los test los pasos son los siguientes:
En caso de que se quiera correr algun cambio se podrá generar la migración.

		python3 manage.py makemigrations
		python3 manage.py migrate
		python3 manage.py test

Para testear solo un recurso en particular:

		python3 manage.py test <nombre recurso>.tests
