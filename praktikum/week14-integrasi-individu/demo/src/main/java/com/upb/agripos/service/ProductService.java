package com.upb.agripos.service;

import java.util.List;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;
import com.upb.agripos.util.ValidationException;

public class ProductService {
    private ProductDAO dao = new ProductDAO();

    public List<Product> findAll() {
        return dao.findAll();
    }

    public void addProduct(Product p) throws ValidationException {
        validateProduct(p);
        dao.save(p);
    }

    // [BARU] Method Update
    public void updateProduct(Product p) throws ValidationException {
        validateProduct(p);
        dao.update(p);
    }

    public void deleteProduct(String code) {
        dao.delete(code);
    }

    public void reduceStock(String code, int currentStock, int qtySold) {
        int newStock = currentStock - qtySold;
        if (newStock < 0) newStock = 0; 
        dao.updateStock(code, newStock);
    }

    // Helper Validasi (Biar tidak nulis ulang)
    private void validateProduct(Product p) throws ValidationException {
        if (p.getCode() == null || p.getCode().trim().isEmpty()) {
            throw new ValidationException("Kode produk wajib diisi!");
        }
        if (p.getName() == null || p.getName().trim().isEmpty()) {
            throw new ValidationException("Nama produk wajib diisi!");
        }
        if (p.getPrice() < 0) {
            throw new ValidationException("Harga tidak boleh negatif!");
        }
        if (p.getStock() < 0) {
            throw new ValidationException("Stok tidak boleh negatif!");
        }
    }
}