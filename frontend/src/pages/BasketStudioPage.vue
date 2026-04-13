<template>
  <StudioLayout>
    <section class="studio-grid">
      <WorkspaceHero
        :shopper-id="shopperId"
        :basket="basket"
        :amounts="amounts"
        :busy="isBusy"
        :line-count="lineCount"
        @update:shopper-id="setShopperId"
        @open-basket="openOrReuseBasket"
        @refresh="refreshWorkspace"
      />

      <div v-if="feedback" class="status-banner status-banner--success">
        {{ feedback }}
      </div>

      <div v-if="error" class="status-banner status-banner--error">
        {{ error }}
      </div>

      <section class="workspace-columns">
        <div class="workspace-columns__main">
          <QuickEntryPanel :busy="isBusy" :disabled="!hasBasket" @submit="createLine" />
          <BasketLedger
            :lines="lines"
            :busy="isBusy"
            :has-basket="hasBasket"
            @adjust="adjustUnits"
            @remove="removeCurrentLine"
          />
        </div>

        <TotalsRail
          :basket="basket"
          :amounts="amounts"
          :line-count="lineCount"
          :busy="isBusy"
          @refresh="refreshWorkspace"
        />
      </section>
    </section>
  </StudioLayout>
</template>

<script setup>
import { computed } from 'vue';
import { storeToRefs } from 'pinia';
import StudioLayout from '../layouts/StudioLayout.vue';
import WorkspaceHero from '../components/basket/WorkspaceHero.vue';
import QuickEntryPanel from '../components/basket/QuickEntryPanel.vue';
import BasketLedger from '../components/basket/BasketLedger.vue';
import TotalsRail from '../components/basket/TotalsRail.vue';
import { useBasketStudioStore } from '../stores/basketStudioStore';

const basketStore = useBasketStudioStore();

const {
  shopperId,
  basket,
  lines,
  amounts,
  loading,
  mutating,
  feedback,
  error,
  hasBasket,
  lineCount
} = storeToRefs(basketStore);

const {
  setShopperId,
  openOrReuseBasket,
  refreshWorkspace,
  createLine,
  adjustUnits,
  removeCurrentLine
} = basketStore;

const isBusy = computed(() => loading.value || mutating.value);
</script>
