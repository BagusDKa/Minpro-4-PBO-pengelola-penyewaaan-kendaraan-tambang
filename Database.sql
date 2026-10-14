CREATE DATABASE IF NOT EXISTS db_kendaraan_tambang;
USE db_kendaraan_tambang;

DROP TABLE IF EXISTS kendaraan;

CREATE TABLE kendaraan (
    id                  VARCHAR(20)   NOT NULL PRIMARY KEY,
    jenis               ENUM('Dump Truck', 'Excavator') NOT NULL,
    nama_kendaraan      VARCHAR(100)  NOT NULL,
    harga_sewa_per_hari DECIMAL(15,2) NOT NULL,
    kapasitas           DECIMAL(10,2) NOT NULL  -- ton (Dump Truck) / m3 bucket (Excavator)
);

INSERT INTO kendaraan (id, jenis, nama_kendaraan, harga_sewa_per_hari, kapasitas) VALUES
('DT-001', 'Dump Truck', 'Komatsu HD785',   3500000, 91.0),
('DT-002', 'Dump Truck', 'Caterpillar 777', 3200000, 100.0),
('EX-001', 'Excavator',  'Komatsu PC200',   2500000, 1.2);
