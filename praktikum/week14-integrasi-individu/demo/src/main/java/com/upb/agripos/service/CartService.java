package com.upb.agripos.service;

import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.Product;

public class CartService {
    // KOLEKSI: Menggunakan List untuk menyimpan item keranjang
    private List<CartItem> cartItems = new ArrayList<>();

    public void addToCart(Product product, int qty) {
        // Cek apakah produk sudah ada di keranjang?
        for (CartItem item : cartItems) {
            if (item.getProduct().getCode().equals(product.getCode())) {
                // Jika ada, tambahkan jumlahnya saja
                item.setQuantity(item.getQuantity() + qty);
                return;
            }
        }
        // Jika belum ada, buat item baru di list
        cartItems.add(new CartItem(product, qty));
    }

    public void clearCart() {
        cartItems.clear();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getSubtotal();
        }
        return total;
    }
}