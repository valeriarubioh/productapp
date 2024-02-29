# productapp
Prueba técnica analista desarrollador.
Aplicación Full Stack Note con Spring Boot y React. El backend utiliza Docker Compose para configurar una base de datos MySQL junto con PHPMyAdmin para facilitar la gestión de la base de datos.

Configuración de backend Puerto de backend: 8091

Configuración del front-end Puerto del front-end: 3000

## Instalación

Clona este repositorio:
```bash
git clone https://github.com/valeriarubioh/productapp.git
```
### Configuración del Backend y la Base de datos

Es necesario tener instalado Docker, el repositorio usó version 20.10.17
```bash
docker-compose -p product_app up -d
```
PhpMyAdmin es accesible en: http://localhost:8090

* Username: root
* Password: password

#### Configuración Base de datos:
- crud_app -> tabla `produtos`

#### Ejecutar Spring Boot Application
Es necesario tener instalado Java con jdk 17 y tener configurado la variable de ambiente JAVA_HOME el computador

```bash
cd backend
./mvnw spring-boot:run
```

#### Pruebas con Postman
Puede importar en Postman el archivo `endpoints.postman_collection.json` para hacer pruebas. El archivo se encuentra en la carpeta `backend`

### Configuración del Frontend
Instala las dependencia del frontend:
```bash
cd frontend
npm install
npm install bootstrap
```
Ejecutar frontend:
```bash
npm start
```
