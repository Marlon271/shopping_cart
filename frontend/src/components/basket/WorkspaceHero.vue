<template>
  <section class="hero-panel reveal reveal--delay-1">
    <div class="hero-panel__wash hero-panel__wash--one"></div>
    <div class="hero-panel__wash hero-panel__wash--two"></div>

    <div class="hero-panel__frame">
      <div class="hero-panel__main">
        <div class="hero-panel__eyebrow">Basket Workspace / control operativo</div>

        <div class="hero-panel__copy">
          <h1>Una mesa de control para abrir, pulir y consolidar cada canasta.</h1>
          <p>
            Opera el flujo completo desde un solo espacio: recupera la canasta
            viva, registra SKU, ajusta volumen y valida el cierre monetario.
          </p>
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

          <button class="button button--ghost-inverse" :disabled="busy || !basket?.basketId" @click="$emit('refresh')">
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
      </div>

      <aside class="hero-panel__snapshot">
        <div class="hero-panel__snapshot-header">
          <div>
            <span class="hero-panel__snapshot-label">Estado actual</span>
            <strong>{{ basket?.state ?? 'SIN APERTURA' }}</strong>
          </div>
          <span class="hero-panel__snapshot-kicker">{{ lines.length }} referencias</span>
        </div>

        <div v-if="lines.length" class="hero-panel__manifest">
          <article v-for="line in previewLines" :key="line.lineId" class="hero-panel__manifest-row">
            <div>
              <strong>{{ line.productLabel }}</strong>
              <span>#{{ line.skuId }}</span>
            </div>
            <div class="hero-panel__manifest-meta">
              <em>{{ line.units }} u</em>
              <b>{{ formatCurrency(line.lineAmount) }}</b>
            </div>
          </article>
        </div>

        <div v-else class="hero-panel__empty">
          <strong>Sin lineas cargadas</strong>
          <p>La primera referencia registrada aparecera aqui como vista previa operativa.</p>
        </div>

        <div class="hero-panel__snapshot-footer">
          <div>
            <span>Ultima lectura</span>
            <strong>{{ formatDate(basket?.updatedAt) }}</strong>
          </div>
          <div>
            <span>Total de unidades</span>
            <strong>{{ formatUnitsLabel(amounts?.totalUnits ?? 0) }}</strong>
          </div>
        </div>
      </aside>
    </div>
  </section>
</template>

<script setup>
import { computed } from 'vue';
import { formatCurrency, formatDate, formatUnitsLabel } from '../../utils/formatters';

const props = defineProps({
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
  lines: {
    type: Array,
    default: () => []
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

const previewLines = computed(() => props.lines.slice(0, 3));

defineEmits(['update:shopper-id', 'open-basket', 'refresh']);
</script>
