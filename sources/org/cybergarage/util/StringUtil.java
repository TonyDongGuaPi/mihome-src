package org.cybergarage.util;

public final class StringUtil {
    public static final boolean hasData(String str) {
        return str != null && str.length() > 0;
    }

    public static final int toInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            Debug.warning(e);
            return 0;
        }
    }

    public static final long toLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            Debug.warning(e);
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0018  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int findOf(java.lang.String r7, java.lang.String r8, int r9, int r10, int r11, boolean r12) {
        /*
            r0 = -1
            if (r11 != 0) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = r8.length()
        L_0x0008:
            if (r11 <= 0) goto L_0x000d
            if (r10 >= r9) goto L_0x0010
            goto L_0x000f
        L_0x000d:
            if (r9 >= r10) goto L_0x0010
        L_0x000f:
            return r0
        L_0x0010:
            char r2 = r7.charAt(r9)
            r3 = 0
            r4 = 0
        L_0x0016:
            if (r3 >= r1) goto L_0x002c
            char r5 = r8.charAt(r3)
            r6 = 1
            if (r12 != r6) goto L_0x0022
            if (r2 != r5) goto L_0x0029
            return r9
        L_0x0022:
            if (r2 == r5) goto L_0x0026
            int r4 = r4 + 1
        L_0x0026:
            if (r4 != r1) goto L_0x0029
            return r9
        L_0x0029:
            int r3 = r3 + 1
            goto L_0x0016
        L_0x002c:
            int r9 = r9 + r11
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cybergarage.util.StringUtil.findOf(java.lang.String, java.lang.String, int, int, int, boolean):int");
    }

    public static final int findFirstOf(String str, String str2) {
        return findOf(str, str2, 0, str.length() - 1, 1, true);
    }

    public static final int findFirstNotOf(String str, String str2) {
        return findOf(str, str2, 0, str.length() - 1, 1, false);
    }

    public static final int findLastOf(String str, String str2) {
        return findOf(str, str2, str.length() - 1, 0, -1, true);
    }

    public static final int findLastNotOf(String str, String str2) {
        return findOf(str, str2, str.length() - 1, 0, -1, false);
    }

    public static final String trim(String str, String str2) {
        int findFirstNotOf = findFirstNotOf(str, str2);
        if (findFirstNotOf < 0) {
            return str;
        }
        String substring = str.substring(findFirstNotOf, str.length());
        int findLastNotOf = findLastNotOf(substring, str2);
        if (findLastNotOf < 0) {
            return substring;
        }
        return substring.substring(0, findLastNotOf + 1);
    }
}
