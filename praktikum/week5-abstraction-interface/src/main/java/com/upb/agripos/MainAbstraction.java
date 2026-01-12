package main.java.com.upb.agripos;

import main.java.com.upb.agripos.Util.CreditBy;
import main.java.com.upb.agripos.model.kontrak.*;
import main.java.com.upb.agripos.model.pembayaran.*;

public class MainAbstraction {
    public static void main(String[] args) {
        Pembayaran cash = new Cash("INV-001", 100000, 120000);
        Pembayaran ew = new EWallet("INV-002", 150000, "user@ewallet", "123456");

        System.out.println(((Receiptable) cash).cetakStruk());
        System.out.println(((Receiptable) ew).cetakStruk());

    CreditBy.print("240202830", "BAGUS ALLDIANSYAH");
    }
}