package org.jacoco.agent.rt.internal_8ff85ea.core.runtime;

import java.util.List;

final class CommandLineSupport {

    /* renamed from: a  reason: collision with root package name */
    private static final char f3631a = ' ';
    private static final char b = '\"';
    private static final char c = '\\';
    private static final int d = 0;
    private static final int e = 1;
    private static final int f = 2;

    static String a(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c2 : str.toCharArray()) {
            if (c2 == '\"' || c2 == '\\') {
                sb.append('\\');
            }
            sb.append(c2);
        }
        if (!(str.indexOf(32) == -1 && str.indexOf(34) == -1)) {
            sb.insert(0, '\"').append('\"');
        }
        return sb.toString();
    }

    static String a(List<String> list) {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (String next : list) {
            if (z) {
                sb.append(' ');
            }
            sb.append(a(next));
            z = true;
        }
        return sb.toString();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.util.List<java.lang.String> b(java.lang.String r12) {
        /*
            if (r12 == 0) goto L_0x006f
            int r0 = r12.length()
            if (r0 != 0) goto L_0x000a
            goto L_0x006f
        L_0x000a:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            char[] r12 = r12.toCharArray()
            int r2 = r12.length
            r3 = 32
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 32
        L_0x0020:
            if (r5 >= r2) goto L_0x006b
            char r8 = r12[r5]
            r9 = 92
            r10 = 34
            r11 = 1
            switch(r6) {
                case 0: goto L_0x0058;
                case 1: goto L_0x0046;
                case 2: goto L_0x002d;
                default: goto L_0x002c;
            }
        L_0x002c:
            goto L_0x0068
        L_0x002d:
            if (r8 == r10) goto L_0x003c
            if (r8 != r9) goto L_0x0032
            goto L_0x003c
        L_0x0032:
            if (r8 != r7) goto L_0x0038
            a(r0, r1)
            goto L_0x0044
        L_0x0038:
            r1.append(r8)
            goto L_0x0044
        L_0x003c:
            int r6 = r1.length()
            int r6 = r6 - r11
            r1.setCharAt(r6, r8)
        L_0x0044:
            r6 = 1
            goto L_0x0068
        L_0x0046:
            if (r8 != r7) goto L_0x004d
            a(r0, r1)
            r6 = 0
            goto L_0x0068
        L_0x004d:
            if (r8 != r9) goto L_0x0054
            r1.append(r9)
            r6 = 2
            goto L_0x0068
        L_0x0054:
            r1.append(r8)
            goto L_0x0068
        L_0x0058:
            boolean r9 = java.lang.Character.isWhitespace(r8)
            if (r9 != 0) goto L_0x0068
            if (r8 != r10) goto L_0x0061
            goto L_0x0066
        L_0x0061:
            r1.append(r8)
            r10 = 32
        L_0x0066:
            r7 = r10
            goto L_0x0044
        L_0x0068:
            int r5 = r5 + 1
            goto L_0x0020
        L_0x006b:
            a(r0, r1)
            return r0
        L_0x006f:
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jacoco.agent.rt.internal_8ff85ea.core.runtime.CommandLineSupport.b(java.lang.String):java.util.List");
    }

    private static void a(List<String> list, StringBuilder sb) {
        if (sb.length() > 0) {
            list.add(sb.toString());
            sb.setLength(0);
        }
    }

    private CommandLineSupport() {
    }
}
