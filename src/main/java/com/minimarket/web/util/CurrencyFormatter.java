package com.minimarket.web.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CurrencyFormatter {
    public static String formatToRupiah(Double amount) {
        if (amount == null) {
            return "Rp 0";
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("id", "ID"));
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        return "Rp " + formatter.format(amount);
    }
}
