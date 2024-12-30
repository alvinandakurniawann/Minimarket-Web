package com.minimarket.web.util;

import java.text.DecimalFormat;

public class CurrencyFormatter {
    public static String formatToRupiah(Double amount) {
        if (amount == null) {
            return "Rp 0";
        }
        DecimalFormat formatter = new DecimalFormat("#,###");
        return "Rp " + formatter.format(amount);
    }
}
