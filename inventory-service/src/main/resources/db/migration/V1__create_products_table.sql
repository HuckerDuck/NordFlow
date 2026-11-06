
-- V1__create_products_table.sql
-- Creates the products table


create table products (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    sku VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(150) NOT NULL,
    category VARCHAR(30) NOT NULL,

    price NUMERIC(10, 2) NOT NULL CHECK (price >= 0),

    stock_quantity INTEGER NOT NULL DEFAULT 0 CHECK (stock_quantity >= 0),
    reserved_quantity INTEGER NOT NULL DEFAULT 0 CHECK (reserved_quantity >= 0),

    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),

    -- can never reserve more than we have
    CONSTRAINT reserved_not_more_than_stock CHECK (reserved_quantity <= stock_quantity)
);

-- The product list is filtered on category a lot
CREATE INDEX products_category_idx ON products (category);
