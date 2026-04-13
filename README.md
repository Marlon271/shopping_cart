# Shopping Cart

Proyecto de carrito de compras orientado a gestionar una canasta activa por comprador, registrar productos, ajustar cantidades y consultar el consolidado monetario desde una sola interfaz web.

## Que hace el sistema

Actualmente el proyecto permite:

- abrir o reutilizar una canasta activa por comprador
- registrar productos por SKU dentro de la canasta
- consultar el detalle completo con sus lineas
- ajustar unidades por cada linea registrada
- retirar productos de manera individual
- recalcular y consultar los montos acumulados
- operar todo el flujo desde un frontend Vue 3
- exponer los servicios mediante un `gateway` en Spring Boot
- persistir la informacion en PostgreSQL

## Como funciona el carrito

1. El usuario ingresa un `shopperId` y abre su canasta.
2. El sistema crea una nueva canasta o reutiliza la que ya este abierta para ese comprador.
3. Desde el formulario principal se registran productos con `skuId`, nombre, unidades y valor unitario.
4. Cada producto agregado aparece en el libro mayor de lineas de la canasta.
5. El usuario puede modificar unidades o retirar una linea cuando lo necesite.
6. El panel lateral muestra el monto bruto, el total de lineas y el total de unidades para mantener el control de la compra.

## Base tecnica

- `frontend`: Vue 3 + Pinia + Vue Router para la experiencia operativa del carrito
- `gateway`: Spring Boot como punto unico de entrada para el cliente web
- `backend`: Spring Boot con logica de negocio, persistencia y calculo de montos
- `database`: PostgreSQL con las tablas `purchase_baskets` y `basket_lines`
- `docker-compose`: orquestacion local de frontend, gateway, backend y base de datos

## API disponible

- `POST /api/v1/baskets`
- `POST /api/v1/baskets/{basketId}/lines`
- `GET /api/v1/baskets/{basketId}`
- `PATCH /api/v1/baskets/{basketId}/lines/{lineId}`
- `DELETE /api/v1/baskets/{basketId}/lines/{lineId}`
- `GET /api/v1/baskets/{basketId}/amounts`

Ejemplo de apertura:

```json
{
  "shopperId": 101
}
```

Comportamiento esperado:

- `201 Created` cuando se crea una nueva canasta
- `200 OK` cuando se reutiliza una canasta abierta del mismo comprador

## Ejecucion local

```bash
docker compose up --build
```

Servicios expuestos:

- `frontend`: `http://localhost:5173`
- `gateway`: `http://localhost:8080`
- `backend`: `http://localhost:8081`
- `postgres`: `localhost:5030`

## Documentacion

- introduccion: `Doc/introduccion.md`
- estructura de solucion: `Doc/estructura-solucion.md`
- priorizacion: `Doc/moscow-priorizacion.md`
- diagramas: `Doc/Diagramas`
- historias de usuario: `Doc/HU`
