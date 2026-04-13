<template>
  <section class="hero-panel reveal reveal--delay-1">
    <div class="hero-panel__eyebrow">Basket Workspace / control operativo</div>

    <div class="hero-panel__header">
      <div>
        <h1>Administra la canasta activa con un tablero claro y rapido.</h1>
        <p>
          Crea o recupera la canasta de un comprador, registra lineas y valida el
          consolidado sin salir de la misma vista.
        </p>
      </div>

      <div class="hero-panel__badge">
        <span>Estado actual</span>
        <strong>{{ basket?.state ?? 'SIN APERTURA' }}</strong>
      </div>
    </div>

    <div class="hero-panel__controls">
      <label class="field field--hero">
        <span>Shopper ID</span>
        <input
          :value="shopperId"
          type="number"
          min="1"
          placeholder="Ej. 101"
          @input="$emit('update:shopper-id', $event.target.value)"
        />
      </label>

      <button class="button button--primary" :disabled="busy" @click="$emit('open-basket')">
        {{ basket?.basketId ? 'Recargar canasta' : 'Abrir canasta' }}
      </button>

      <button class="button button--ghost" :disabled="busy || !basket?.basketId" @click="$emit('refresh')">
        Sincronizar vista
      </button>
    </div>

    <div class="hero-panel__metrics">
      <article class="metric-tile">
        <span>Basket ID</span>
        <strong>{{ basket?.basketId ?? 'Pendiente' }}</strong>
      </article>
      <article class="metric-tile">
        <span>Lineas activas</span>
        <strong>{{ lineCount }}</strong>
      </article>
      <article class="metric-tile">
        <span>Total acumulado</span>
        <strong>{{ formatCurrency(amounts?.grossAmount ?? 0) }}</strong>
      </article>
    </div>
  </section>
</template>

<script setup>
import { formatCurrency } from '../../utils/formatters';

defineProps({
  shopperId: {
    type: [String, Number],
    required: true
  },
  basket: {
    type: Object,
    default: null
  },
  amounts: {
    type: Object,
    default: null
  },
  lineCount: {
    type: Number,
    default: 0
  },
  busy: {
    type: Boolean,
    default: false
  }
});

defineEmits(['update:shopper-id', 'open-basket', 'refresh']);
</script>
