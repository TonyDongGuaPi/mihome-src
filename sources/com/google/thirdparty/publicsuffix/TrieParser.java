package com.google.thirdparty.publicsuffix;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

@GwtCompatible
class TrieParser {
    private static final Joiner PREFIX_JOINER = Joiner.on("");

    TrieParser() {
    }

    static ImmutableMap<String, PublicSuffixType> parseTrie(CharSequence charSequence) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            i += doParseTrieToBuilder(Lists.newLinkedList(), charSequence.subSequence(i, length), builder);
        }
        return builder.build();
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x006a A[EDGE_INSN: B:36:0x006a->B:27:0x006a ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int doParseTrieToBuilder(java.util.List<java.lang.CharSequence> r9, java.lang.CharSequence r10, com.google.common.collect.ImmutableMap.Builder<java.lang.String, com.google.thirdparty.publicsuffix.PublicSuffixType> r11) {
        /*
            int r0 = r10.length()
            r1 = 0
            r2 = 0
            r3 = 0
        L_0x0007:
            r4 = 58
            r5 = 33
            r6 = 44
            r7 = 63
            if (r2 >= r0) goto L_0x0025
            char r3 = r10.charAt(r2)
            r8 = 38
            if (r3 == r8) goto L_0x0025
            if (r3 == r7) goto L_0x0025
            if (r3 == r5) goto L_0x0025
            if (r3 == r4) goto L_0x0025
            if (r3 != r6) goto L_0x0022
            goto L_0x0025
        L_0x0022:
            int r2 = r2 + 1
            goto L_0x0007
        L_0x0025:
            java.lang.CharSequence r8 = r10.subSequence(r1, r2)
            java.lang.CharSequence r8 = reverse(r8)
            r9.add(r1, r8)
            if (r3 == r5) goto L_0x0038
            if (r3 == r7) goto L_0x0038
            if (r3 == r4) goto L_0x0038
            if (r3 != r6) goto L_0x004b
        L_0x0038:
            com.google.common.base.Joiner r4 = PREFIX_JOINER
            java.lang.String r4 = r4.join((java.lang.Iterable<?>) r9)
            int r5 = r4.length()
            if (r5 <= 0) goto L_0x004b
            com.google.thirdparty.publicsuffix.PublicSuffixType r5 = com.google.thirdparty.publicsuffix.PublicSuffixType.fromCode(r3)
            r11.put(r4, r5)
        L_0x004b:
            int r2 = r2 + 1
            if (r3 == r7) goto L_0x006a
            if (r3 == r6) goto L_0x006a
        L_0x0051:
            if (r2 >= r0) goto L_0x006a
            java.lang.CharSequence r3 = r10.subSequence(r2, r0)
            int r3 = doParseTrieToBuilder(r9, r3, r11)
            int r2 = r2 + r3
            char r3 = r10.charAt(r2)
            if (r3 == r7) goto L_0x0068
            char r3 = r10.charAt(r2)
            if (r3 != r6) goto L_0x0051
        L_0x0068:
            int r2 = r2 + 1
        L_0x006a:
            r9.remove(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.thirdparty.publicsuffix.TrieParser.doParseTrieToBuilder(java.util.List, java.lang.CharSequence, com.google.common.collect.ImmutableMap$Builder):int");
    }

    private static CharSequence reverse(CharSequence charSequence) {
        int length = charSequence.length();
        if (length <= 1) {
            return charSequence;
        }
        char[] cArr = new char[length];
        int i = length - 1;
        cArr[0] = charSequence.charAt(i);
        for (int i2 = 1; i2 < length; i2++) {
            cArr[i2] = charSequence.charAt(i - i2);
            int i3 = i2 - 1;
            if (Character.isSurrogatePair(cArr[i2], cArr[i3])) {
                swap(cArr, i3, i2);
            }
        }
        return new String(cArr);
    }

    private static void swap(char[] cArr, int i, int i2) {
        char c = cArr[i];
        cArr[i] = cArr[i2];
        cArr[i2] = c;
    }
}
