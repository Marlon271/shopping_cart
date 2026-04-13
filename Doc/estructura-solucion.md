# Estructura de la Solucion

## Modulos

- `frontend`: cliente Vue 3 para operar la canasta desde un tablero visual
- `gateway`: servicio Spring Boot que expone la fachada consumida por el frontend
- `backend`: servicio Spring Boot con las reglas de negocio y persistencia
- `database`: script SQL inicial para PostgreSQL

## Decisiones de reconstruccion

- Se cambiaron los conceptos `cart` y `cart item` por `basket` y `basket line`.
- La capa de entrada ahora prioriza un estilo de tablero operativo, no una pantalla CRUD basica.
- Las rutas publicas quedaron agrupadas bajo `/api/v1/baskets`.
- La base de datos usa tablas renombradas: `purchase_baskets` y `basket_lines`.
- La documentacion se reescribio por completo para que la narrativa no replique la del repositorio de referencia.

## Stack tecnico

- `Vue 3` + `Pinia` + `Vue Router`
- `Spring Boot 4`
- `Spring Data JPA`
- `PostgreSQL 16`
- `Docker Compose`

## Flujo principal

1. El usuario abre el tablero frontend.
2. El frontend consume el `gateway`.
3. El `gateway` encapsula las llamadas al `backend`.
4. El `backend` aplica reglas de negocio y persiste cambios en PostgreSQL.
5. El frontend refresca detalle y montos en paralelo para mantener el tablero sincronizado.
