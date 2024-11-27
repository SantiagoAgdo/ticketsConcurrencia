# Reservas de Tickets

Este proyecto fue desarrollado con **Java Spring Boot** y **PostgreSQL** y **Optimistic Locking**.

- Reservas de tickets con concurrencia.
- Limpieza automática de reservas vencidas.
- Arquitectura modular.
- PostgreSQL como base de datos relacional.

---

## Requisitos previos

1. **Java 17**  
2. **Maven 3**  
3. **PostgreSQL**

---

## Instalación

### 1. Clona el repositorio
```bash
git clone https://github.com/tu-usuario/sistema-reservas-tickets.git](https://github.com/SantiagoAgdo/ticketsConcurrencia

-Inicia PostgreSQL y crea una base de datos:
createdb productos_db

Configura las credenciales en el archivo src/main/resources/application.properties:

Dar Permisos : productos_db=# GRANT ALL PRIVILEGES ON DATABASE productos_db TO admin;

Compilar proyecto: mvn clean install
ejecutar: mvn spring-boot:run


```
### Curl
```
 curl --location --request POST 'http://localhost:8080/api/tickets/3/reserve?usuario=test.agdor%40.com'
```

### Respuesta esperada
```
 {
    "id": 1,
    "status": "reservado",
    "reservedBy": "santiago.agdor@.com",
    "reservedAt": "2024-11-27T08:53:35.370509",
    "version": 1
}
```



### Consideraciones
- Para probar la concurrecia puede usarse postman runner y efectuar la misma peticion con el mismo id 
- asegurar que en la tabla de tickets haya uno disponible (se puede agregar con este query: INSERT INTO tickets (status, reserved_by, reserved_at, version) VALUES ('disponible', NULL, NULL, 0);)
