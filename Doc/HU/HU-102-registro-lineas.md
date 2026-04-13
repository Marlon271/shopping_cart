# HU-102 Registro de Lineas de Producto

**Como** operador  
**Quiero** cargar productos en la canasta usando SKU, unidades y valor unitario  
**Para** construir el contenido de la compra sin duplicar lineas innecesarias

## Criterios de aceptacion

- el sistema agrega una nueva linea cuando el SKU no existe
- el sistema acumula unidades cuando el SKU ya esta cargado
- la canasta debe estar en estado `OPEN`
- el valor unitario y las unidades deben ser mayores a cero

## Artefactos asociados

- `POST /api/v1/baskets/{basketId}/lines`
- entidad `BasketLine`
- tabla `basket_lines`
- rama de trabajo `HU-102-basket-dev`
