<template>
  <section class="operations-strip reveal reveal--delay-2">
    <article class="operations-strip__item">
      <span class="operations-strip__index">01</span>
      <div>
        <strong>Comprador activo</strong>
        <p>{{ basket?.shopperId ? `Shopper ${basket.shopperId}` : 'Sin comprador cargado' }}</p>
      </div>
    </article>

    <article class="operations-strip__item">
      <span class="operations-strip__index">02</span>
      <div>
        <strong>Lineas cargadas</strong>
        <p>{{ lineCount }} registros sincronizados con el backend</p>
      </div>
    </article>

    <article class="operations-strip__item">
      <span class="operations-strip__index">03</span>
      <div>
        <strong>Montos vivos</strong>
        <p>{{ formatCurrency(amounts?.grossAmount ?? 0) }} en valor bruto actual</p>
      </div>
    </article>
  </section>
</template>

<script setup>
import { formatCurrency } from '../../utils/formatters';

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
  }
});
</script>
