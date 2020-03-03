package com.tiqiaa.icontrol.util;

import com.google.code.microlog4android.format.PatternFormatter;
import com.spare.pinyin.HanziToPinyin;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class StringUtil {
    private static int BEGIN = 45217;
    static String COMMON_CHANESE = "的一是了我不人在他有这个上们来到时大地为子中你说生国年着就那和要她出也得里后自以会家可下而过天去能对小多然于心学么之都好看起发当没成只如事把还用第样道想作种开见明问力理尔点文几定本公特做外孩相西果走将月十实向声车全信重三机工物气每并别真打太新比才便夫再书部水像眼等体却加电主界门利海受听表德少克代员许稜先口由死安写性马光白或住难望教命花结乐色";
    private static int END = 63486;
    private static final String TAG = "StringUtil";
    private static char[] chartable = {21834, 33453, 25830, 25645, 34558, 21457, 22134, 21704, 21704, 20987, 21888, 22403, 22920, 25343, 21734, 21866, 26399, 28982, 25746, 22604, 22604, 22604, 25366, 26132, 21387, 21277};
    private static char[] initialtable = {'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f', 'g', 'h', 'h', 'j', 'k', 'l', PatternFormatter.MESSAGE_CONVERSION_CHAR, 'n', 'o', 'p', 'q', PatternFormatter.RELATIVE_TIME_CONVERSION_CHAR, 's', PatternFormatter.THREAD_CONVERSION_CHAR, PatternFormatter.THREAD_CONVERSION_CHAR, PatternFormatter.THREAD_CONVERSION_CHAR, 'w', 'x', 'y', 'z'};
    private static int[] table = new int[27];

    static {
        for (int i = 0; i < 26; i++) {
            table[i] = gbValue(chartable[i]);
        }
        table[26] = END;
    }

    public static String getFirstLetters(String str) {
        String str2 = "";
        int i = 0;
        while (i < str.length()) {
            try {
                str2 = String.valueOf(str2) + Char2Initial(str.charAt(i));
                i++;
            } catch (Exception unused) {
                return "";
            }
        }
        return str2;
    }

    public static String getFullPinYin(String str) {
        LogUtil.d(TAG, "getFullPinYin.......source = " + str);
        try {
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
        } catch (Exception unused) {
            return str;
        }
    }

    private static char Char2Initial(char c) {
        int gbValue;
        if (c >= 'a' && c <= 'z') {
            return (char) ((c - 'a') + 65);
        }
        if ((c >= 'A' && c <= 'Z') || (gbValue = gbValue(c)) < BEGIN || gbValue > END) {
            return c;
        }
        int i = 0;
        while (i < 26 && (gbValue < table[i] || gbValue >= table[i + 1])) {
            i++;
        }
        if (gbValue == END) {
            i = 25;
        }
        return initialtable[i];
    }

    public static boolean isStartWithEn(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        char charAt = str.charAt(0);
        if (charAt >= 'a' && charAt <= 'z') {
            return true;
        }
        if (charAt < 'A' || charAt > 'Z') {
            return false;
        }
        return true;
    }

    private static int gbValue(char c) {
        try {
            byte[] bytes = (String.valueOf(new String()) + c).getBytes("GB2312");
            if (bytes.length < 2) {
                return 0;
            }
            return ((bytes[0] << 8) & 65280) + (bytes[1] & 255);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static boolean equals(String str, String str2) {
        boolean z = true;
        if (((str != null && !str.equals("")) || (str2 != null && !str2.equals(""))) && (str == null || str2 == null || !str.equals(str2))) {
            z = false;
        }
        LogUtil.d("StringUril", "equals.......str1=" + str + ",str2=" + str2 + ",equals=" + z);
        return z;
    }

    public static List<String> div_word(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        Date date = new Date();
        IKSegmenter iKSegmenter = new IKSegmenter((Reader) new StringReader(str), true);
        ArrayList arrayList = new ArrayList();
        while (true) {
            try {
                Lexeme a2 = iKSegmenter.a();
                if (a2 == null) {
                    break;
                }
                arrayList.add(a2.f());
            } catch (IOException e) {
                e.printStackTrace();
                if (arrayList.size() == 0) {
                    arrayList.add(str);
                }
            }
        }
        Date date2 = new Date();
        LogUtil.d(TAG, "div_word.............str = " + str + ", words = " + TQJSON.toJSONString(arrayList) + "..\n..分词耗时 -> " + (date2.getTime() - date.getTime()));
        return arrayList;
    }

    public static String getTestString() {
        Random random = new Random();
        int nextInt = random.nextInt(3) + 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nextInt; i++) {
            sb.append(COMMON_CHANESE.charAt(random.nextInt(COMMON_CHANESE.length())));
        }
        return sb.toString();
    }
}
