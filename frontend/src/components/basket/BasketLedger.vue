<template>
  <section class="surface-panel reveal reveal--delay-3">
    <header class="section-heading">
      <div>
        <span class="section-heading__eyebrow">Libro mayor</span>
        <h2>Lineas de la canasta</h2>
      </div>
      <p>Actualiza unidades o retira registros sin perder el contexto del comprador.</p>
    </header>

    <div v-if="!hasBasket" class="empty-state">
      <h3>Abre una canasta para comenzar.</h3>
      <p>El tablero se habilita en cuanto registres un `shopperId` valido.</p>
    </div>

    <div v-else-if="!lines.length" class="empty-state">
      <h3>La canasta aun no tiene lineas.</h3>
      <p>Usa el panel de carga rápida para registrar los primeros productos.</p>
    </div>

    <div v-else class="ledger-table-wrap">
      <table class="ledger-table">
        <thead>
          <tr>
            <th>Producto</th>
            <th>SKU</th>
            <th>Precio</th>
            <th>Unidades</th>
            <th>Monto</th>
            <th>Accion</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="line in lines" :key="line.lineId">
            <td>
              <div class="ledger-title">
                <strong>{{ line.productLabel }}</strong>
                <span>Linea #{{ line.lineId }}</span>
              </div>
            </td>
            <td>#{{ line.skuId }}</td>
            <td>{{ formatCurrency(line.unitAmount) }}</td>
            <td>
              <div class="quantity-editor">
                <input
                  :value="drafts[line.lineId] ?? line.units"
                  type="number"
                  min="1"
                  @input="drafts[line.lineId] = $event.target.value"
                />
                <button class="button button--ghost" :disabled="busy" @click="emitAdjust(line.lineId)">
                  Guardar
                </button>
              </div>
            </td>
            <td>{{ formatCurrency(line.lineAmount) }}</td>
            <td>
              <button class="button button--danger" :disabled="busy" @click="$emit('remove', line.lineId)">
                Retirar
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </section>
</template>

<script setup>
import { reactive } from 'vue';
import { formatCurrency } from '../../utils/formatters';

defineProps({
  lines: {
    type: Array,
    default: () => []
  },
  busy: {
    type: Boolean,
    default: false
  },
  hasBasket: {
    type: Boolean,
    default: false
  }
});

const emit = defineEmits(['adjust', 'remove']);
const drafts = reactive({});

function emitAdjust(lineId) {
  emit('adjust', lineId, Number(drafts[lineId] ?? 1));
}
</script>
