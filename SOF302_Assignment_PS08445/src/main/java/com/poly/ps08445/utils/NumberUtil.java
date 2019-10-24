package com.poly.ps08445.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumberUtil {

    static String DECIMAL_FORMAT;
    /**
     * Định dạng kiểu dữ liệu Double
     *
     * @param num là số được truyền vào
     * @param pattern là chuỗi định dạng cho kiểu double
     * @return chuỗi đã được định dạng
     *
     */
    public static String formatDouble(double num, String...pattern) {
        if (pattern.length > 0) {
            DECIMAL_FORMAT = pattern[0];
        } else {
            DECIMAL_FORMAT = "#,###";
        }

        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT, DecimalFormatSymbols.getInstance(new Locale("it", "IT")));
        return decimalFormat.format(num);
    }
}
