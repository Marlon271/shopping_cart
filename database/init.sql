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
