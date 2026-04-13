# Basket Workspace

Base tecnica del proyecto reconstruido a partir del repositorio de referencia, pero con un dominio propio y una trazabilidad nueva.

## Estado actual

En esta primera historia (`HU-101`) ya quedo implementado:

- apertura o reutilizacion de una canasta activa por comprador
- backend en Spring Boot con modelo `Basket`
- gateway en Spring Boot como punto unico de entrada
- PostgreSQL con la tabla `purchase_baskets`
- entorno local con Docker Compose

## Arquitectura

- `backend`: microservicio principal de negocio
- `gateway`: fachada REST consumida por el frontend
- `database`: scripts de inicializacion de PostgreSQL

## Endpoint disponible

- `POST /api/v1/baskets`

Request:

```json
{
  "shopperId": 101
}
```

## Respuesta esperada

- `201 Created` cuando se crea una nueva canasta abierta
- `200 OK` cuando el comprador ya tiene una canasta abierta y se reutiliza

## Ejecucion local

```bash
docker compose up --build
```

Servicios expuestos:

- `gateway`: `http://localhost:8080`
- `backend`: `http://localhost:8081`
- `postgres`: `localhost:5030`

## Proximas historias

- `HU-102`: registrar lineas de producto
- `HU-103`: consultar el detalle de la canasta
- `HU-104`: ajustar unidades
- `HU-105`: retirar lineas
- `HU-106`: consolidar montos
- `HU-107`: experiencia visual en frontend
- `HU-108`: documentacion, diagramas y pantallazos
