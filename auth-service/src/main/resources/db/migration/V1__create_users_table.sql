
-- V1__create_users_table.sql
-- Creates the users table


create table users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,

    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,

    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    account_enabled BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()

);

-- Added index for email since we will use it alot when the user logs in
CREATE INDEX users_email_idx ON users (email);