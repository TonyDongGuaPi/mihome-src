package com.qti.location.sdk.utils;

import com.qti.location.sdk.utils.IZatValidationResults;

public class IZatDataValidationString {

    /* renamed from: a  reason: collision with root package name */
    private static String f8630a = "IZatDataValidation";

    public static boolean a(String str) {
        return str.matches("[A-Fa-f0-9]{12}");
    }

    /* renamed from: com.qti.location.sdk.utils.IZatDataValidationString$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f8631a = new int[IZatValidationResults.IZatDataTypes.values().length];

        static {
            try {
                f8631a[IZatValidationResults.IZatDataTypes.WIFI_MAC_ADDRESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static IZatValidationResults a(String str, IZatValidationResults.IZatDataTypes iZatDataTypes) {
        String str2;
        if (AnonymousClass1.f8631a[iZatDataTypes.ordinal()] != 1) {
            str2 = "Unknown type to be validate";
        } else if (a(str)) {
            return new IZatValidationResults(true);
        } else {
            str2 = "[WIFI_MAC_ADDRESS]Str with len 12, [0-F], current value: " + str;
        }
        IZatValidationResults iZatValidationResults = new IZatValidationResults(false, str2);
        iZatValidationResults.d();
        return iZatValidationResults;
    }
}
