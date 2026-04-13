CREATE TABLE IF NOT EXISTS purchase_baskets (
    id BIGSERIAL PRIMARY KEY,
    shopper_id BIGINT NOT NULL,
    state VARCHAR(20) NOT NULL DEFAULT 'OPEN',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_purchase_baskets_state
        CHECK (state IN ('OPEN', 'CONFIRMED', 'CANCELLED'))
);

CREATE UNIQUE INDEX IF NOT EXISTS uq_purchase_baskets_open_shopper
    ON purchase_baskets (shopper_id)
    WHERE state = 'OPEN';

CREATE INDEX IF NOT EXISTS idx_purchase_baskets_shopper_id
    ON purchase_baskets (shopper_id);

CREATE INDEX IF NOT EXISTS idx_purchase_baskets_state
    ON purchase_baskets (state);

CREATE TABLE IF NOT EXISTS basket_lines (
    id BIGSERIAL PRIMARY KEY,
    basket_id BIGINT NOT NULL,
    sku_id BIGINT NOT NULL,
    product_label VARCHAR(255) NOT NULL,
    units INTEGER NOT NULL,
    unit_amount NUMERIC(12,2) NOT NULL,
    line_amount NUMERIC(12,2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_basket_lines_basket
        FOREIGN KEY (basket_id) REFERENCES purchase_baskets(id) ON DELETE CASCADE,
    CONSTRAINT chk_basket_lines_units
        CHECK (units > 0),
    CONSTRAINT chk_basket_lines_unit_amount
        CHECK (unit_amount > 0),
    CONSTRAINT chk_basket_lines_line_amount
        CHECK (line_amount > 0),
    CONSTRAINT uq_basket_lines_basket_sku
        UNIQUE (basket_id, sku_id)
);

CREATE INDEX IF NOT EXISTS idx_basket_lines_basket_id
    ON basket_lines (basket_id);

CREATE INDEX IF NOT EXISTS idx_basket_lines_sku_id
    ON basket_lines (sku_id);
