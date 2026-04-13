<template>
  <aside class="totals-rail reveal reveal--delay-5">
    <section class="surface-panel surface-panel--rail">
      <header class="section-heading section-heading--rail">
        <div>
          <span class="section-heading__eyebrow">Panel lateral</span>
          <h2>Resumen de montos</h2>
        </div>
        <p>Lee el consolidado y el pulso de la canasta sin abandonar la mesa de trabajo.</p>
      </header>

      <div class="rail-hero-number">
        <span>Monto bruto actual</span>
        <strong>{{ formatCurrency(amounts?.grossAmount ?? 0) }}</strong>
      </div>

      <div class="rail-metrics">
        <article class="rail-metric">
          <span>Total de lineas</span>
          <strong>{{ amounts?.totalLines ?? lineCount }}</strong>
        </article>
        <article class="rail-metric">
          <span>Total de unidades</span>
          <strong>{{ formatUnitsLabel(amounts?.totalUnits ?? 0) }}</strong>
        </article>
      </div>

      <div class="rail-meta">
        <div>
          <span>Canasta</span>
          <strong>{{ basket?.basketId ?? 'Pendiente' }}</strong>
        </div>
        <div>
          <span>Actualizado</span>
          <strong>{{ formatDate(basket?.updatedAt) }}</strong>
        </div>
        <div>
          <span>Estado</span>
          <strong>{{ basket?.state ?? 'SIN APERTURA' }}</strong>
        </div>
      </div>

      <button class="button button--primary button--full" :disabled="busy || !basket?.basketId" @click="$emit('refresh')">
        Recalcular vista
      </button>
    </section>
  </aside>
</template>

<script setup>
import { formatCurrency, formatDate, formatUnitsLabel } from '../../utils/formatters';

defineProps({
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

defineEmits(['refresh']);
</script>
