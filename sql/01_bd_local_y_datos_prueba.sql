CREATE DATABASE IF NOT EXISTS ep2_innovatech
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE ep2_innovatech;

CREATE TABLE IF NOT EXISTS venta (
  id_venta BIGINT NOT NULL AUTO_INCREMENT,
  direccion_compra VARCHAR(255) NOT NULL,
  valor_compra INT NULL,
  fecha_compra DATE NOT NULL,
  despacho_generado BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (id_venta)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS despacho (
  id_despacho BIGINT NOT NULL AUTO_INCREMENT,
  fecha_despacho DATE NULL,
  patente_camion VARCHAR(255) NULL,
  intento INT NULL DEFAULT 0,
  id_compra BIGINT NULL,
  direccion_compra VARCHAR(255) NULL,
  valor_compra INT NULL,
  despachado BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (id_despacho)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;

INSERT INTO venta (id_venta, direccion_compra, valor_compra, fecha_compra, despacho_generado)
VALUES
  (1, 'Av. Providencia 1234, Providencia', 450000, '2026-05-20', false),
  (2, 'Av. Apoquindo 4500, Las Condes', 890000, '2026-05-21', false),
  (3, 'San Diego 850, Santiago', 250000, '2026-05-22', true)
ON DUPLICATE KEY UPDATE
  direccion_compra = VALUES(direccion_compra),
  valor_compra = VALUES(valor_compra),
  fecha_compra = VALUES(fecha_compra),
  despacho_generado = VALUES(despacho_generado);

INSERT INTO despacho (id_despacho, fecha_despacho, patente_camion, intento, id_compra, direccion_compra, valor_compra, despachado)
VALUES
  (1, '2026-05-23', 'ABCD12', 1, 3, 'San Diego 850, Santiago', 250000, false)
ON DUPLICATE KEY UPDATE
  fecha_despacho = VALUES(fecha_despacho),
  patente_camion = VALUES(patente_camion),
  intento = VALUES(intento),
  id_compra = VALUES(id_compra),
  direccion_compra = VALUES(direccion_compra),
  valor_compra = VALUES(valor_compra),
  despachado = VALUES(despachado);