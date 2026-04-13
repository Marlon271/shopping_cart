# HU-106 Consolidado de Montos

**Como** operador  
**Quiero** consultar un resumen global de lineas, unidades y monto bruto  
**Para** entender rapidamente el valor consolidado de la canasta

## Criterios de aceptacion

- el sistema informa total de lineas
- el sistema informa total de unidades
- el sistema calcula el monto bruto acumulado
- el resumen se obtiene sin alterar la canasta

## Artefactos asociados

- `GET /api/v1/baskets/{basketId}/amounts`
- DTO `BasketAmountsResponse`
- rama de trabajo `HU-106-basket-dev`
