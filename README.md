# Basket Workspace

Base tecnica del proyecto reconstruido a partir del repositorio de referencia, pero con un dominio propio y una trazabilidad nueva.

## Estado actual

Actualmente van implementadas:

- apertura o reutilizacion de una canasta activa por comprador
- registro de lineas de producto sobre una canasta abierta
- consulta detallada de la canasta con sus lineas
- ajuste de unidades por linea
- retiro individual de lineas
- consolidado monetario de la canasta
- backend en Spring Boot con modelo `Basket`
- modelo `BasketLine` para productos agregados
- frontend Vue 3 con una vista operativa renovada
- gateway en Spring Boot como punto unico de entrada
- PostgreSQL con las tablas `purchase_baskets` y `basket_lines`
- entorno local con Docker Compose

## Arquitectura

- `frontend`: tablero visual para operar la canasta
- `backend`: microservicio principal de negocio
- `gateway`: fachada REST consumida por el frontend
- `database`: scripts de inicializacion de PostgreSQL

## Endpoint disponible

- `POST /api/v1/baskets`
- `POST /api/v1/baskets/{basketId}/lines`
- `GET /api/v1/baskets/{basketId}`
- `PATCH /api/v1/baskets/{basketId}/lines/{lineId}`
- `DELETE /api/v1/baskets/{basketId}/lines/{lineId}`
- `GET /api/v1/baskets/{basketId}/amounts`

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

- `frontend`: `http://localhost:5173`
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
