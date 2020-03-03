package com.imi.fastjson.parser;

import com.mi.mistatistic.sdk.data.EventData;
import com.taobao.weex.el.parse.Operators;

public class JSONToken {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6088a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 6;
    public static final int g = 7;
    public static final int h = 8;
    public static final int i = 9;
    public static final int j = 10;
    public static final int k = 11;
    public static final int l = 12;
    public static final int m = 13;
    public static final int n = 14;
    public static final int o = 15;
    public static final int p = 16;
    public static final int q = 17;
    public static final int r = 18;
    public static final int s = 19;
    public static final int t = 20;
    public static final int u = 21;
    public static final int v = 22;

    public static String a(int i2) {
        switch (i2) {
            case 1:
                return "error";
            case 2:
                return "int";
            case 3:
                return "float";
            case 4:
                return EventData.b;
            case 5:
                return "iso8601";
            case 6:
                return "true";
            case 7:
                return "false";
            case 8:
                return "null";
            case 9:
                return "new";
            case 10:
                return Operators.BRACKET_START_STR;
            case 11:
                return Operators.BRACKET_END_STR;
            case 12:
                return Operators.BLOCK_START_STR;
            case 13:
                return "}";
            case 14:
                return Operators.ARRAY_START_STR;
            case 15:
                return Operators.ARRAY_END_STR;
            case 16:
                return ",";
            case 17:
                return ":";
            case 18:
                return "ident";
            case 19:
                return "fieldName";
            case 20:
                return "EOF";
            case 21:
                return "Set";
            case 22:
                return "TreeSet";
            default:
                return "Unkown";
        }
    }
}
