
-- V2__seed_products.sql
-- Some real products so the store is not empty, prices in SEK


INSERT INTO products (sku, name, category, price, stock_quantity) VALUES
    ('GPU-RTX4070S-12G', 'NVIDIA GeForce RTX 4070 Super 12GB', 'GPU', 6990.00, 14),
    ('GPU-RX7800XT-16G', 'AMD Radeon RX 7800 XT 16GB', 'GPU', 5790.00, 9),
    ('GPU-RTX4060-8G', 'NVIDIA GeForce RTX 4060 8GB', 'GPU', 3490.00, 22),

    ('CPU-R7-7800X3D', 'AMD Ryzen 7 7800X3D', 'CPU', 4290.00, 18),
    ('CPU-R5-7600', 'AMD Ryzen 5 7600', 'CPU', 2190.00, 30),
    ('CPU-I5-14600K', 'Intel Core i5-14600K', 'CPU', 3290.00, 12),

    ('MB-B650-TOMAHAWK', 'MSI MAG B650 Tomahawk WiFi', 'MOTHERBOARD', 2490.00, 10),
    ('MB-Z790-STRIXE', 'ASUS ROG Strix Z790-E Gaming WiFi', 'MOTHERBOARD', 4990.00, 4),

    ('RAM-DDR5-32-6000', 'Kingston Fury Beast DDR5 32GB 6000MHz', 'RAM', 1190.00, 40),
    ('RAM-DDR5-64-6000', 'Corsair Vengeance DDR5 64GB 6000MHz', 'RAM', 2390.00, 8),

    ('SSD-990PRO-2TB', 'Samsung 990 Pro 2TB NVMe', 'STORAGE', 1990.00, 25),
    ('SSD-SN850X-1TB', 'WD Black SN850X 1TB NVMe', 'STORAGE', 1090.00, 0),

    ('PSU-RM850X', 'Corsair RM850x 850W 80+ Gold', 'PSU', 1490.00, 16),

    ('CASE-NORTH-CHALK', 'Fractal Design North Chalk White', 'CASE', 1690.00, 7),

    ('COOL-NHD15', 'Noctua NH-D15 chromax.black', 'COOLING', 1290.00, 11);
