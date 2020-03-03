package org.wltea.analyzer.core;

import java.lang.Character;

class CharacterUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4197a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 4;
    public static final int e = 8;

    static char b(char c2) {
        if (c2 == 12288) {
            return ' ';
        }
        return (c2 <= 65280 || c2 >= 65375) ? (c2 < 'A' || c2 > 'Z') ? c2 : (char) (c2 + ' ') : (char) (c2 - 65248);
    }

    CharacterUtil() {
    }

    static int a(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return 1;
        }
        if (c2 >= 'a' && c2 <= 'z') {
            return 2;
        }
        if (c2 >= 'A' && c2 <= 'Z') {
            return 2;
        }
        Character.UnicodeBlock of = Character.UnicodeBlock.of(c2);
        if (of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) {
            return 4;
        }
        return (of == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || of == Character.UnicodeBlock.HANGUL_SYLLABLES || of == Character.UnicodeBlock.HANGUL_JAMO || of == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO || of == Character.UnicodeBlock.HIRAGANA || of == Character.UnicodeBlock.KATAKANA || of == Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS) ? 8 : 0;
    }
}
