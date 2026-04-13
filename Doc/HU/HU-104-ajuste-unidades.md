# HU-104 Ajuste de Unidades

**Como** operador  
**Quiero** cambiar las unidades de una linea existente  
**Para** corregir el volumen de compra sin reingresar el producto

## Criterios de aceptacion

- el cambio se hace sobre una linea existente
- la linea debe pertenecer a la canasta indicada
- solo se permiten cantidades mayores a cero
- el monto de la linea se recalcula despues del ajuste

## Artefactos asociados

- `PATCH /api/v1/baskets/{basketId}/lines/{lineId}`
- request `AdjustBasketLineUnitsRequest`
- rama de trabajo `HU-104-basket-dev`
