package com.spare.pinyin;

import com.spare.pinyin.HanziToPinyin;
import java.util.ArrayList;
import java.util.Iterator;

public class PinYin {
    public static String a(String str) {
        ArrayList<HanziToPinyin.Token> a2 = HanziToPinyin.a().a(str);
        StringBuilder sb = new StringBuilder();
        if (a2 != null && a2.size() > 0) {
            Iterator<HanziToPinyin.Token> it = a2.iterator();
            while (it.hasNext()) {
                HanziToPinyin.Token next = it.next();
                if (2 == next.e) {
                    sb.append(next.g);
                } else {
                    sb.append(next.f);
                }
            }
        }
        return sb.toString().toLowerCase();
    }

    public static boolean b(String str) {
        for (char c : str.toCharArray()) {
            if (c >= 19968 && c <= 40959) {
                return true;
            }
            if (c >= 13312 && c <= 19903) {
                return true;
            }
            if (c >= 63744 && c <= 64255) {
                return true;
            }
        }
        return false;
    }
}
