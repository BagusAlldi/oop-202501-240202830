-- 1. Hapus tabel lama jika sudah ada
DROP TABLE IF EXISTS products;

-- 2. Buat tabel baru dengan kolom 'kategori' sesuai Class Diagram
CREATE TABLE products (
    kode VARCHAR(10) PRIMARY KEY,
    nama VARCHAR(100),
    kategori VARCHAR(50),
    harga DOUBLE PRECISION,
    stok INT
);