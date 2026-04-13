import httpClient from '../api/httpClient';

export function openBasket(payload) {
  return httpClient.post('/api/v1/baskets', payload);
}

export function getBasketDetail(basketId) {
  return httpClient.get(`/api/v1/baskets/${basketId}`);
}

export function getBasketAmounts(basketId) {
  return httpClient.get(`/api/v1/baskets/${basketId}/amounts`);
}

export function registerLine(basketId, payload) {
  return httpClient.post(`/api/v1/baskets/${basketId}/lines`, payload);
}

export function adjustLineUnits(basketId, lineId, payload) {
  return httpClient.patch(`/api/v1/baskets/${basketId}/lines/${lineId}`, payload);
}

export function removeLine(basketId, lineId) {
  return httpClient.delete(`/api/v1/baskets/${basketId}/lines/${lineId}`);
}
