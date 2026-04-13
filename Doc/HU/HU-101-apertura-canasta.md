# HU-101 Apertura de Canasta Operativa

**Como** operador del tablero  
**Quiero** abrir una canasta activa para un comprador  
**Para** iniciar una sesion de compra reutilizable

## Criterios de aceptacion

- si el comprador no tiene canasta abierta, el sistema crea una nueva
- si ya existe una canasta abierta, el sistema la reutiliza
- la respuesta indica el identificador de la canasta y su estado
- solo se acepta un `shopperId` positivo

## Artefactos asociados

- `POST /api/v1/baskets`
- entidad `Basket`
- tabla `purchase_baskets`
- rama de trabajo `HU-101-basket-dev`
