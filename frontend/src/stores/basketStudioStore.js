import { computed, ref } from 'vue';
import { defineStore } from 'pinia';
import * as basketService from '../services/basketService';

function resolveError(error) {
  return error?.response?.data?.message ?? 'No fue posible completar la operación.';
}

export const useBasketStudioStore = defineStore('basketStudio', () => {
  const shopperId = ref(101);
  const basket = ref(null);
  const lines = ref([]);
  const amounts = ref(null);
  const loading = ref(false);
  const mutating = ref(false);
  const feedback = ref('');
  const error = ref('');

  const basketId = computed(() => basket.value?.basketId ?? null);
  const hasBasket = computed(() => Boolean(basketId.value));
  const lineCount = computed(() => lines.value.length);

  function clearMessages() {
    feedback.value = '';
    error.value = '';
  }

  function setShopperId(value) {
    shopperId.value = value === '' ? '' : Number(value);
  }

  async function refreshWorkspace() {
    if (!basketId.value) {
      return;
    }

    loading.value = true;

    try {
      const [detailResponse, amountResponse] = await Promise.all([
        basketService.getBasketDetail(basketId.value),
        basketService.getBasketAmounts(basketId.value)
      ]);

      basket.value = {
        ...basket.value,
        ...detailResponse.data
      };
      lines.value = detailResponse.data.lines ?? [];
      amounts.value = amountResponse.data;
    } catch (requestError) {
      error.value = resolveError(requestError);
    } finally {
      loading.value = false;
    }
  }

  async function openOrReuseBasket() {
    clearMessages();

    if (!Number(shopperId.value) || Number(shopperId.value) < 1) {
      error.value = 'Debes ingresar un shopperId valido.';
      return;
    }

    mutating.value = true;

    try {
      const response = await basketService.openBasket({ shopperId: Number(shopperId.value) });
      basket.value = response.data;
      feedback.value = response.status === 201
        ? 'Se abrió una nueva canasta operativa.'
        : 'Se reutilizó la canasta abierta del comprador.';
      await refreshWorkspace();
    } catch (requestError) {
      error.value = resolveError(requestError);
    } finally {
      mutating.value = false;
    }
  }

  async function createLine(payload) {
    clearMessages();

    if (!hasBasket.value) {
      error.value = 'Primero debes abrir o cargar una canasta.';
      return;
    }

    mutating.value = true;

    try {
      await basketService.registerLine(basketId.value, payload);
      feedback.value = 'La linea fue registrada correctamente.';
      await refreshWorkspace();
    } catch (requestError) {
      error.value = resolveError(requestError);
    } finally {
      mutating.value = false;
    }
  }

  async function adjustUnits(lineId, units) {
    clearMessages();
    mutating.value = true;

    try {
      await basketService.adjustLineUnits(basketId.value, lineId, { units });
      feedback.value = 'Las unidades se actualizaron.';
      await refreshWorkspace();
    } catch (requestError) {
      error.value = resolveError(requestError);
    } finally {
      mutating.value = false;
    }
  }

  async function removeCurrentLine(lineId) {
    clearMessages();
    mutating.value = true;

    try {
      await basketService.removeLine(basketId.value, lineId);
      feedback.value = 'La linea fue retirada de la canasta.';
      await refreshWorkspace();
    } catch (requestError) {
      error.value = resolveError(requestError);
    } finally {
      mutating.value = false;
    }
  }

  return {
    shopperId,
    basket,
    lines,
    amounts,
    loading,
    mutating,
    feedback,
    error,
    basketId,
    hasBasket,
    lineCount,
    setShopperId,
    openOrReuseBasket,
    refreshWorkspace,
    createLine,
    adjustUnits,
    removeCurrentLine
  };
});
