<template>
  <section class="surface-panel reveal reveal--delay-2">
    <header class="section-heading">
      <div>
        <span class="section-heading__eyebrow">Carga rápida</span>
        <h2>Registrar una nueva linea</h2>
      </div>
      <p>Usa referencias de SKU, unidades y valor unitario para poblar la canasta.</p>
    </header>

    <form class="entry-form" @submit.prevent="handleSubmit">
      <label class="field">
        <span>SKU</span>
        <input v-model="form.skuId" type="number" min="1" placeholder="Ej. 9901" />
      </label>

      <label class="field field--wide">
        <span>Etiqueta del producto</span>
        <input v-model="form.productLabel" type="text" placeholder="Ej. Monitor 27 pulgadas IPS" />
      </label>

      <label class="field">
        <span>Unidades</span>
        <input v-model="form.units" type="number" min="1" />
      </label>

      <label class="field">
        <span>Valor unitario</span>
        <input v-model="form.unitAmount" type="number" min="1" step="1000" />
      </label>

      <div class="entry-form__footer">
        <p>La vista fusiona lineas por SKU cuando detecta el mismo producto.</p>
        <button class="button button--primary" :disabled="busy || disabled">
          Agregar a la canasta
        </button>
      </div>
    </form>
  </section>
</template>

<script setup>
import { reactive } from 'vue';

const props = defineProps({
  busy: {
    type: Boolean,
    default: false
  },
  disabled: {
    type: Boolean,
    default: false
  }
});

const emit = defineEmits(['submit']);

const form = reactive({
  skuId: '',
  productLabel: '',
  units: 1,
  unitAmount: 100000
});

function handleSubmit() {
  if (props.disabled) {
    return;
  }

  emit('submit', {
    skuId: Number(form.skuId),
    productLabel: form.productLabel.trim(),
    units: Number(form.units),
    unitAmount: Number(form.unitAmount)
  });

  form.skuId = '';
  form.productLabel = '';
  form.units = 1;
  form.unitAmount = 100000;
}
</script>
