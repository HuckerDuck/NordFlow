-- Runs only the first time postgres starts with an empty volume
-- One database per service so no service can read another ones data

CREATE DATABASE nordflow_auth;
CREATE DATABASE nordflow_inventory;
CREATE DATABASE nordflow_order;
