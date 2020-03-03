package com.xiaomi.qrcode;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.zxing.DecodeHintType;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

final class DecodeHintManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12959a = "DecodeHintManager";
    private static final Pattern b = Pattern.compile(",");

    private DecodeHintManager() {
    }

    private static Map<String, String> a(String str) {
        String str2;
        String str3;
        HashMap hashMap = new HashMap();
        int i = 0;
        while (true) {
            if (i >= str.length()) {
                break;
            } else if (str.charAt(i) == '&') {
                i++;
            } else {
                int indexOf = str.indexOf(38, i);
                int indexOf2 = str.indexOf(61, i);
                if (indexOf < 0) {
                    if (indexOf2 < 0) {
                        str2 = Uri.decode(str.substring(i).replace('+', ' '));
                        str3 = "";
                    } else {
                        String decode = Uri.decode(str.substring(i, indexOf2).replace('+', ' '));
                        str3 = Uri.decode(str.substring(indexOf2 + 1).replace('+', ' '));
                        str2 = decode;
                    }
                    if (!hashMap.containsKey(str2)) {
                        hashMap.put(str2, str3);
                    }
                } else if (indexOf2 < 0 || indexOf2 > indexOf) {
                    String decode2 = Uri.decode(str.substring(i, indexOf).replace('+', ' '));
                    if (!hashMap.containsKey(decode2)) {
                        hashMap.put(decode2, "");
                    }
                    i = indexOf + 1;
                } else {
                    String decode3 = Uri.decode(str.substring(i, indexOf2).replace('+', ' '));
                    String decode4 = Uri.decode(str.substring(indexOf2 + 1, indexOf).replace('+', ' '));
                    if (!hashMap.containsKey(decode3)) {
                        hashMap.put(decode3, decode4);
                    }
                    i = indexOf + 1;
                }
            }
        }
        return hashMap;
    }

    static Map<DecodeHintType, ?> a(Uri uri) {
        String str;
        String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery == null || encodedQuery.isEmpty()) {
            return null;
        }
        Map<String, String> a2 = a(encodedQuery);
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        for (DecodeHintType decodeHintType : DecodeHintType.values()) {
            if (!(decodeHintType == DecodeHintType.CHARACTER_SET || decodeHintType == DecodeHintType.NEED_RESULT_POINT_CALLBACK || decodeHintType == DecodeHintType.POSSIBLE_FORMATS || (str = a2.get(decodeHintType.name())) == null)) {
                if (decodeHintType.getValueType().equals(Object.class)) {
                    enumMap.put(decodeHintType, str);
                } else if (decodeHintType.getValueType().equals(Void.class)) {
                    enumMap.put(decodeHintType, Boolean.TRUE);
                } else if (decodeHintType.getValueType().equals(String.class)) {
                    enumMap.put(decodeHintType, str);
                } else if (decodeHintType.getValueType().equals(Boolean.class)) {
                    if (str.isEmpty()) {
                        enumMap.put(decodeHintType, Boolean.TRUE);
                    } else if ("0".equals(str) || "false".equalsIgnoreCase(str) || "no".equalsIgnoreCase(str)) {
                        enumMap.put(decodeHintType, Boolean.FALSE);
                    } else {
                        enumMap.put(decodeHintType, Boolean.TRUE);
                    }
                } else if (decodeHintType.getValueType().equals(int[].class)) {
                    if (!str.isEmpty() && str.charAt(str.length() - 1) == ',') {
                        str = str.substring(0, str.length() - 1);
                    }
                    String[] split = b.split(str);
                    int[] iArr = new int[split.length];
                    int i = 0;
                    while (i < split.length) {
                        try {
                            iArr[i] = Integer.parseInt(split[i]);
                            i++;
                        } catch (NumberFormatException unused) {
                            Log.w(f12959a, "Skipping array of integers hint " + decodeHintType + " due to invalid numeric value: '" + split[i] + Operators.SINGLE_QUOTE);
                            iArr = null;
                        }
                    }
                    if (iArr != null) {
                        enumMap.put(decodeHintType, iArr);
                    }
                } else {
                    Log.w(f12959a, "Unsupported hint type '" + decodeHintType + "' of type " + decodeHintType.getValueType());
                }
            }
        }
        Log.i(f12959a, "Hints from the URI: " + enumMap);
        return enumMap;
    }

    static Map<DecodeHintType, Object> a(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null || extras.isEmpty()) {
            return null;
        }
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        for (DecodeHintType decodeHintType : DecodeHintType.values()) {
            if (!(decodeHintType == DecodeHintType.CHARACTER_SET || decodeHintType == DecodeHintType.NEED_RESULT_POINT_CALLBACK || decodeHintType == DecodeHintType.POSSIBLE_FORMATS)) {
                String name = decodeHintType.name();
                if (extras.containsKey(name)) {
                    if (decodeHintType.getValueType().equals(Void.class)) {
                        enumMap.put(decodeHintType, Boolean.TRUE);
                    } else {
                        Object obj = extras.get(name);
                        if (decodeHintType.getValueType().isInstance(obj)) {
                            enumMap.put(decodeHintType, obj);
                        } else {
                            Log.w(f12959a, "Ignoring hint " + decodeHintType + " because it is not assignable from " + obj);
                        }
                    }
                }
            }
        }
        Log.i(f12959a, "Hints from the Intent: " + enumMap);
        return enumMap;
    }
}
