export function formatCurrency(value) {
  return new Intl.NumberFormat('es-CO', {
    style: 'currency',
    currency: 'COP',
    maximumFractionDigits: 0
  }).format(Number(value ?? 0));
}

export function formatDate(value) {
  if (!value) {
    return 'Sin registros';
  }

  return new Intl.DateTimeFormat('es-CO', {
    dateStyle: 'medium',
    timeStyle: 'short'
  }).format(new Date(value));
}

export function formatUnitsLabel(value) {
  const units = Number(value ?? 0);
  return `${units} ${units === 1 ? 'unidad' : 'unidades'}`;
}
