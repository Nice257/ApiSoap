-- Donnees de test du Portail Fournisseur legacy
-- Le fournisseur (id=1) est lie aux 3 lignes de catalogue via supplier_id

INSERT INTO supplier (id, supplier_code, company_name) VALUES
(1, 'SUP-001', 'Burundi AgroSupply Ltd');

INSERT INTO supplier_catalog (id, supplier_id, sku, product_name, wholesale_price, lead_time_days, supplier_stock_level) VALUES
(1, 1, 'SKU-RICE-5KG',  'Riz parfume 5kg (sac)',        12.75,  7,  450),
(2, 1, 'SKU-BEANS-1KG', 'Haricots rouges 1kg (paquet)',   3.50,  5, 1200),
(3, 1, 'SKU-OIL-5L',    'Huile vegetale 5L (bidon)',     18.90, 14,  300);
