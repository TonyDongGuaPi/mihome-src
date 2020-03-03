package com.mobikwik.sdk.lib.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CommonUtils {
    public static final int OPR_ADD = 1;
    public static final int OPR_MUL = 3;
    public static final int OPR_SUB = 2;

    public static Double performArithmeticCalculations(Double d, Double d2, int i) {
        return performArithmeticCalculations(d + "", d2 + "", i);
    }

    public static Double performArithmeticCalculations(Double d, Double d2, int i, RoundingMode roundingMode) {
        return performArithmeticCalculations(d + "", d2 + "", i, roundingMode);
    }

    public static Double performArithmeticCalculations(String str, String str2, int i) {
        BigDecimal bigDecimal;
        BigDecimal bigDecimal2 = new BigDecimal(str);
        BigDecimal bigDecimal3 = new BigDecimal(str2);
        switch (i) {
            case 1:
                bigDecimal = bigDecimal2.add(bigDecimal3);
                break;
            case 2:
                bigDecimal = bigDecimal2.subtract(bigDecimal3);
                break;
            case 3:
                bigDecimal = bigDecimal2.multiply(bigDecimal3);
                break;
            default:
                bigDecimal = null;
                break;
        }
        return Double.valueOf(bigDecimal.doubleValue());
    }

    public static Double performArithmeticCalculations(String str, String str2, int i, RoundingMode roundingMode) {
        return roundDouble(performArithmeticCalculations(str, str2, i), roundingMode);
    }

    public static Double roundDouble(Double d) {
        return roundDouble(d, RoundingMode.UP);
    }

    public static Double roundDouble(Double d, RoundingMode roundingMode) {
        if ((d + "").split("\\.").length != 2) {
            return d;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(d);
        sb.append("");
        return sb.toString().split("\\.")[1].length() > 2 ? Double.valueOf(new BigDecimal(d.doubleValue()).setScale(2, roundingMode).doubleValue()) : d;
    }
}
