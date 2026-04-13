# HU-105 Retiro de Linea

**Como** operador  
**Quiero** retirar una linea especifica de la canasta  
**Para** dejar solo los productos que continuan vigentes en la compra

## Criterios de aceptacion

- la linea debe existir
- la linea debe pertenecer a la canasta indicada
- la canasta debe seguir abierta
- el sistema devuelve una confirmacion legible al finalizar

## Artefactos asociados

- `DELETE /api/v1/baskets/{basketId}/lines/{lineId}`
- response `RemoveBasketLineResponse`
- rama de trabajo `HU-105-basket-dev`
