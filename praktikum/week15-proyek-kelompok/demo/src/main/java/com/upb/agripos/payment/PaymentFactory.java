package com.upb.agripos.payment;

public class PaymentFactory {
    public static PaymentMethod getPaymentMethod(String type) {
        if (type.equalsIgnoreCase("CASH")) return (amount) -> {
            System.out.println("Bayar Tunai: " + amount);
            return true; 
        };
        return null;
    }
}