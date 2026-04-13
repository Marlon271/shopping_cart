# Introduccion

`Basket Workspace` es una reconstruccion del caso academico de carrito de compras, pero llevada a un dominio mas propio y a una experiencia visual mas madura. En lugar de repetir el vocabulario de `cart` e `item`, el proyecto se reorganizo alrededor de `Basket` y `BasketLine`, con contratos REST, nombres tecnicos y documentacion diferentes.

## Objetivo

El sistema permite abrir una canasta activa por comprador, registrar productos, consultar el detalle operativo, ajustar unidades, retirar lineas y obtener un consolidado monetario desde una sola interfaz.

## Alcance funcional

- apertura o reutilizacion de una canasta activa
- registro incremental de lineas por SKU
- consulta del detalle con lineas ordenadas
- ajuste puntual de unidades
- retiro individual de lineas
- calculo de totales globales
- tablero web responsivo apoyado por `API Gateway`

## Evidencia visual

Vista principal de escritorio:

![Tablero desktop](./Evidencia/tablero-desktop.png)

Vista angosta para validacion responsive:

![Tablero mobile](./Evidencia/tablero-mobile.png)
