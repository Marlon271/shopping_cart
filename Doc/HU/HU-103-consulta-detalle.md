# HU-103 Consulta de Detalle

**Como** operador  
**Quiero** consultar la canasta con sus lineas activas  
**Para** revisar rapidamente el estado actual de la compra

## Criterios de aceptacion

- el sistema responde metadatos de la canasta
- el detalle incluye la lista ordenada de lineas
- se informa cuantas lineas estan asociadas a la canasta
- si la canasta no existe, se responde error controlado

## Artefactos asociados

- `GET /api/v1/baskets/{basketId}`
- DTO `BasketDetailResponse`
- rama de trabajo `HU-103-basket-dev`
