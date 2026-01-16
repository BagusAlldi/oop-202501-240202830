package com.upb.agripos.dao;

import java.util.List;

import com.upb.agripos.model.Product;

public interface ProductDAO {
    void insert(Product p) throws Exception;
    void update(Product p) throws Exception;
    void delete(String kode) throws Exception;
    Product findByCode(String kode) throws Exception;
    List<Product> findAll() throws Exception;
}