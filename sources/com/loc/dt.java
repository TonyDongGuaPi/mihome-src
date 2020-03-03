package com.loc;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import java.io.UnsupportedEncodingException;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class dt {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f6568a = (!dt.class.desiredAssertionStatus());

    static abstract class a {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f6569a;
        public int b;

        a() {
        }
    }

    static class b extends a {
        private static final int[] c = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int[] d = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private int e = 0;
        private int f = 0;
        private final int[] g = c;

        public b(byte[] bArr) {
            this.f6569a = bArr;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c3, code lost:
            r8 = (r8 << 6) | r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c6, code lost:
            r3 = r3 + 1;
         */
        /* JADX WARNING: Removed duplicated region for block: B:47:0x00e0  */
        /* JADX WARNING: Removed duplicated region for block: B:49:0x00e3  */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x00f2  */
        /* JADX WARNING: Removed duplicated region for block: B:51:0x00fb  */
        /* JADX WARNING: Removed duplicated region for block: B:59:0x00dc A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean a(byte[] r13, int r14) {
            /*
                r12 = this;
                int r0 = r12.e
                r1 = 0
                r2 = 6
                if (r0 != r2) goto L_0x0007
                return r1
            L_0x0007:
                int r14 = r14 + r1
                int r0 = r12.e
                int r3 = r12.f
                byte[] r4 = r12.f6569a
                int[] r5 = r12.g
                r6 = 4
                r8 = r3
                r7 = 0
                r3 = r0
                r0 = 0
            L_0x0015:
                if (r0 >= r14) goto L_0x00dc
                if (r3 != 0) goto L_0x005c
            L_0x0019:
                int r9 = r0 + 4
                if (r9 > r14) goto L_0x005a
                byte r8 = r13[r0]
                r8 = r8 & 255(0xff, float:3.57E-43)
                r8 = r5[r8]
                int r8 = r8 << 18
                int r10 = r0 + 1
                byte r10 = r13[r10]
                r10 = r10 & 255(0xff, float:3.57E-43)
                r10 = r5[r10]
                int r10 = r10 << 12
                r8 = r8 | r10
                int r10 = r0 + 2
                byte r10 = r13[r10]
                r10 = r10 & 255(0xff, float:3.57E-43)
                r10 = r5[r10]
                int r10 = r10 << r2
                r8 = r8 | r10
                int r10 = r0 + 3
                byte r10 = r13[r10]
                r10 = r10 & 255(0xff, float:3.57E-43)
                r10 = r5[r10]
                r8 = r8 | r10
                if (r8 < 0) goto L_0x005a
                int r0 = r7 + 2
                byte r10 = (byte) r8
                r4[r0] = r10
                int r0 = r7 + 1
                int r10 = r8 >> 8
                byte r10 = (byte) r10
                r4[r0] = r10
                int r0 = r8 >> 16
                byte r0 = (byte) r0
                r4[r7] = r0
                int r7 = r7 + 3
                r0 = r9
                goto L_0x0019
            L_0x005a:
                if (r0 >= r14) goto L_0x00dc
            L_0x005c:
                int r9 = r0 + 1
                byte r0 = r13[r0]
                r0 = r0 & 255(0xff, float:3.57E-43)
                r0 = r5[r0]
                r10 = -2
                r11 = -1
                switch(r3) {
                    case 0: goto L_0x00ce;
                    case 1: goto L_0x00c1;
                    case 2: goto L_0x00ab;
                    case 3: goto L_0x0078;
                    case 4: goto L_0x0070;
                    case 5: goto L_0x006b;
                    default: goto L_0x0069;
                }
            L_0x0069:
                goto L_0x00d9
            L_0x006b:
                if (r0 == r11) goto L_0x00d9
                r12.e = r2
                return r1
            L_0x0070:
                if (r0 != r10) goto L_0x0073
                goto L_0x00c6
            L_0x0073:
                if (r0 == r11) goto L_0x00d9
                r12.e = r2
                return r1
            L_0x0078:
                if (r0 < 0) goto L_0x0094
                int r3 = r8 << 6
                r8 = r3 | r0
                int r0 = r7 + 2
                byte r3 = (byte) r8
                r4[r0] = r3
                int r0 = r7 + 1
                int r3 = r8 >> 8
                byte r3 = (byte) r3
                r4[r0] = r3
                int r0 = r8 >> 16
                byte r0 = (byte) r0
                r4[r7] = r0
                int r7 = r7 + 3
                r0 = r9
                r3 = 0
                goto L_0x0015
            L_0x0094:
                if (r0 != r10) goto L_0x00a6
                int r0 = r7 + 1
                int r3 = r8 >> 2
                byte r3 = (byte) r3
                r4[r0] = r3
                int r0 = r8 >> 10
                byte r0 = (byte) r0
                r4[r7] = r0
                int r7 = r7 + 2
                r3 = 5
                goto L_0x00d9
            L_0x00a6:
                if (r0 == r11) goto L_0x00d9
                r12.e = r2
                return r1
            L_0x00ab:
                if (r0 < 0) goto L_0x00ae
                goto L_0x00c3
            L_0x00ae:
                if (r0 != r10) goto L_0x00bc
                int r0 = r7 + 1
                int r3 = r8 >> 4
                byte r3 = (byte) r3
                r4[r7] = r3
                r7 = r0
                r0 = r9
                r3 = 4
                goto L_0x0015
            L_0x00bc:
                if (r0 == r11) goto L_0x00d9
                r12.e = r2
                return r1
            L_0x00c1:
                if (r0 < 0) goto L_0x00c9
            L_0x00c3:
                int r8 = r8 << 6
                r8 = r8 | r0
            L_0x00c6:
                int r3 = r3 + 1
                goto L_0x00d9
            L_0x00c9:
                if (r0 == r11) goto L_0x00d9
                r12.e = r2
                return r1
            L_0x00ce:
                if (r0 < 0) goto L_0x00d4
                int r3 = r3 + 1
                r8 = r0
                goto L_0x00d9
            L_0x00d4:
                if (r0 == r11) goto L_0x00d9
                r12.e = r2
                return r1
            L_0x00d9:
                r0 = r9
                goto L_0x0015
            L_0x00dc:
                switch(r3) {
                    case 0: goto L_0x00fe;
                    case 1: goto L_0x00fb;
                    case 2: goto L_0x00f2;
                    case 3: goto L_0x00e3;
                    case 4: goto L_0x00e0;
                    default: goto L_0x00df;
                }
            L_0x00df:
                goto L_0x00fe
            L_0x00e0:
                r12.e = r2
                return r1
            L_0x00e3:
                int r13 = r7 + 1
                int r14 = r8 >> 10
                byte r14 = (byte) r14
                r4[r7] = r14
                int r7 = r13 + 1
                int r14 = r8 >> 2
                byte r14 = (byte) r14
                r4[r13] = r14
                goto L_0x00fe
            L_0x00f2:
                int r13 = r7 + 1
                int r14 = r8 >> 4
                byte r14 = (byte) r14
                r4[r7] = r14
                r7 = r13
                goto L_0x00fe
            L_0x00fb:
                r12.e = r2
                return r1
            L_0x00fe:
                r12.e = r3
                r12.b = r7
                r13 = 1
                return r13
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loc.dt.b.a(byte[], int):boolean");
        }
    }

    static class c extends a {
        static final /* synthetic */ boolean g = (!dt.class.desiredAssertionStatus());
        private static final byte[] h = {Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ACTIVITY_INFO, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 74, TarConstants.U, 76, 77, 78, Constants.TagName.CP_NO, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 83, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.QUERY_DATA_SORT_TYPE, 87, 88, 89, Constants.TagName.PREDEPOSIT_TOTAL, 97, Constants.TagName.OPERATE_TIMING, Constants.TagName.PAY_ORDER, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ORDER_TYPE, 102, 103, Constants.TagName.DEVICE_MODEL, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.PAY_ORDER_ID, Constants.TagName.ELECTRONIC, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.ELECTRONIC_ID, Constants.TagName.ELECTRONIC_TYPE_ID, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_FROZEN_FLAG, 119, 120, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.ELECTRONIC_OUT_STATE, 48, 49, 50, 51, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.USER_LOCK_TIME, Constants.TagName.CARD_FORM};
        private static final byte[] i = {Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ACTIVITY_INFO, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 74, TarConstants.U, 76, 77, 78, Constants.TagName.CP_NO, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 83, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.QUERY_DATA_SORT_TYPE, 87, 88, 89, Constants.TagName.PREDEPOSIT_TOTAL, 97, Constants.TagName.OPERATE_TIMING, Constants.TagName.PAY_ORDER, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ORDER_TYPE, 102, 103, Constants.TagName.DEVICE_MODEL, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.PAY_ORDER_ID, Constants.TagName.ELECTRONIC, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.ELECTRONIC_ID, Constants.TagName.ELECTRONIC_TYPE_ID, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_FROZEN_FLAG, 119, 120, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.ELECTRONIC_OUT_STATE, 48, 49, 50, 51, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, 45, 95};
        int c;
        public final boolean d;
        public final boolean e;
        public final boolean f;
        private final byte[] j;
        private int k;
        private final byte[] l;

        public c(int i2) {
            this.f6569a = null;
            boolean z = true;
            this.d = (i2 & 1) == 0;
            this.e = (i2 & 2) == 0;
            this.f = (i2 & 4) == 0 ? false : z;
            this.l = (i2 & 8) == 0 ? h : i;
            this.j = new byte[2];
            this.c = 0;
            this.k = this.e ? 19 : -1;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Removed duplicated region for block: B:81:0x01ca  */
        /* JADX WARNING: Removed duplicated region for block: B:83:0x01d0  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean a(byte[] r18, int r19) {
            /*
                r17 = this;
                r0 = r17
                byte[] r2 = r0.l
                byte[] r3 = r0.f6569a
                int r4 = r0.k
                r5 = 0
                int r6 = r19 + 0
                int r7 = r0.c
                r8 = -1
                r9 = 2
                r10 = 1
                switch(r7) {
                    case 0: goto L_0x004a;
                    case 1: goto L_0x0030;
                    case 2: goto L_0x0014;
                    default: goto L_0x0013;
                }
            L_0x0013:
                goto L_0x004a
            L_0x0014:
                if (r6 <= 0) goto L_0x004a
                byte[] r7 = r0.j
                byte r7 = r7[r5]
                r7 = r7 & 255(0xff, float:3.57E-43)
                int r7 = r7 << 16
                byte[] r11 = r0.j
                byte r11 = r11[r10]
                r11 = r11 & 255(0xff, float:3.57E-43)
                int r11 = r11 << 8
                r7 = r7 | r11
                byte r11 = r18[r5]
                r11 = r11 & 255(0xff, float:3.57E-43)
                r7 = r7 | r11
                r0.c = r5
                r11 = 1
                goto L_0x004c
            L_0x0030:
                if (r9 > r6) goto L_0x004a
                byte[] r7 = r0.j
                byte r7 = r7[r5]
                r7 = r7 & 255(0xff, float:3.57E-43)
                int r7 = r7 << 16
                byte r11 = r18[r5]
                r11 = r11 & 255(0xff, float:3.57E-43)
                int r11 = r11 << 8
                r7 = r7 | r11
                byte r11 = r18[r10]
                r11 = r11 & 255(0xff, float:3.57E-43)
                r7 = r7 | r11
                r0.c = r5
                r11 = 2
                goto L_0x004c
            L_0x004a:
                r7 = -1
                r11 = 0
            L_0x004c:
                r13 = 4
                r14 = 13
                r15 = 10
                if (r7 == r8) goto L_0x0088
                int r8 = r7 >> 18
                r8 = r8 & 63
                byte r8 = r2[r8]
                r3[r5] = r8
                int r8 = r7 >> 12
                r8 = r8 & 63
                byte r8 = r2[r8]
                r3[r10] = r8
                int r8 = r7 >> 6
                r8 = r8 & 63
                byte r8 = r2[r8]
                r3[r9] = r8
                r7 = r7 & 63
                byte r7 = r2[r7]
                r8 = 3
                r3[r8] = r7
                int r4 = r4 + -1
                if (r4 != 0) goto L_0x0086
                boolean r4 = r0.f
                if (r4 == 0) goto L_0x007e
                r4 = 5
                r3[r13] = r14
                goto L_0x007f
            L_0x007e:
                r4 = 4
            L_0x007f:
                int r7 = r4 + 1
                r3[r4] = r15
                r4 = 19
                goto L_0x0089
            L_0x0086:
                r7 = 4
                goto L_0x0089
            L_0x0088:
                r7 = 0
            L_0x0089:
                int r8 = r11 + 3
                if (r8 > r6) goto L_0x00e2
                byte r12 = r18[r11]
                r12 = r12 & 255(0xff, float:3.57E-43)
                int r12 = r12 << 16
                int r16 = r11 + 1
                byte r9 = r18[r16]
                r9 = r9 & 255(0xff, float:3.57E-43)
                int r9 = r9 << 8
                r9 = r9 | r12
                int r11 = r11 + 2
                byte r11 = r18[r11]
                r11 = r11 & 255(0xff, float:3.57E-43)
                r9 = r9 | r11
                int r11 = r9 >> 18
                r11 = r11 & 63
                byte r11 = r2[r11]
                r3[r7] = r11
                int r11 = r7 + 1
                int r12 = r9 >> 12
                r12 = r12 & 63
                byte r12 = r2[r12]
                r3[r11] = r12
                int r11 = r7 + 2
                int r12 = r9 >> 6
                r12 = r12 & 63
                byte r12 = r2[r12]
                r3[r11] = r12
                int r11 = r7 + 3
                r9 = r9 & 63
                byte r9 = r2[r9]
                r3[r11] = r9
                int r7 = r7 + 4
                int r4 = r4 + -1
                if (r4 != 0) goto L_0x00df
                boolean r4 = r0.f
                if (r4 == 0) goto L_0x00d6
                int r4 = r7 + 1
                r3[r7] = r14
                goto L_0x00d7
            L_0x00d6:
                r4 = r7
            L_0x00d7:
                int r7 = r4 + 1
                r3[r4] = r15
                r11 = r8
                r4 = 19
                goto L_0x00e0
            L_0x00df:
                r11 = r8
            L_0x00e0:
                r9 = 2
                goto L_0x0089
            L_0x00e2:
                int r8 = r0.c
                int r8 = r11 - r8
                int r9 = r6 + -1
                if (r8 != r9) goto L_0x0136
                int r8 = r0.c
                if (r8 <= 0) goto L_0x00f4
                byte[] r1 = r0.j
                byte r1 = r1[r5]
                r5 = 1
                goto L_0x00f9
            L_0x00f4:
                int r8 = r11 + 1
                byte r1 = r18[r11]
                r11 = r8
            L_0x00f9:
                r1 = r1 & 255(0xff, float:3.57E-43)
                int r1 = r1 << r13
                int r8 = r0.c
                int r8 = r8 - r5
                r0.c = r8
                int r5 = r7 + 1
                int r8 = r1 >> 6
                r8 = r8 & 63
                byte r8 = r2[r8]
                r3[r7] = r8
                int r7 = r5 + 1
                r1 = r1 & 63
                byte r1 = r2[r1]
                r3[r5] = r1
                boolean r1 = r0.d
                if (r1 == 0) goto L_0x0121
                int r1 = r7 + 1
                r2 = 61
                r3[r7] = r2
                int r7 = r1 + 1
                r3[r1] = r2
            L_0x0121:
                boolean r1 = r0.e
                if (r1 == 0) goto L_0x01c1
                boolean r1 = r0.f
                if (r1 == 0) goto L_0x012e
                int r1 = r7 + 1
                r3[r7] = r14
                goto L_0x012f
            L_0x012e:
                r1 = r7
            L_0x012f:
                int r2 = r1 + 1
                r3[r1] = r15
            L_0x0133:
                r7 = r2
                goto L_0x01c1
            L_0x0136:
                int r8 = r0.c
                int r8 = r11 - r8
                int r9 = r6 + -2
                if (r8 != r9) goto L_0x01a9
                int r8 = r0.c
                if (r8 <= r10) goto L_0x0148
                byte[] r8 = r0.j
                byte r5 = r8[r5]
                r8 = 1
                goto L_0x014f
            L_0x0148:
                int r8 = r11 + 1
                byte r9 = r18[r11]
                r11 = r8
                r5 = r9
                r8 = 0
            L_0x014f:
                r5 = r5 & 255(0xff, float:3.57E-43)
                int r5 = r5 << r15
                int r9 = r0.c
                if (r9 <= 0) goto L_0x015e
                byte[] r1 = r0.j
                int r9 = r8 + 1
                byte r1 = r1[r8]
                r8 = r9
                goto L_0x0163
            L_0x015e:
                int r9 = r11 + 1
                byte r1 = r18[r11]
                r11 = r9
            L_0x0163:
                r1 = r1 & 255(0xff, float:3.57E-43)
                r9 = 2
                int r1 = r1 << r9
                r1 = r1 | r5
                int r5 = r0.c
                int r5 = r5 - r8
                r0.c = r5
                int r5 = r7 + 1
                int r8 = r1 >> 12
                r8 = r8 & 63
                byte r8 = r2[r8]
                r3[r7] = r8
                int r7 = r5 + 1
                int r8 = r1 >> 6
                r8 = r8 & 63
                byte r8 = r2[r8]
                r3[r5] = r8
                int r5 = r7 + 1
                r1 = r1 & 63
                byte r1 = r2[r1]
                r3[r7] = r1
                boolean r1 = r0.d
                if (r1 == 0) goto L_0x0194
                int r1 = r5 + 1
                r2 = 61
                r3[r5] = r2
                goto L_0x0195
            L_0x0194:
                r1 = r5
            L_0x0195:
                boolean r2 = r0.e
                if (r2 == 0) goto L_0x01a7
                boolean r2 = r0.f
                if (r2 == 0) goto L_0x01a2
                int r2 = r1 + 1
                r3[r1] = r14
                r1 = r2
            L_0x01a2:
                int r2 = r1 + 1
                r3[r1] = r15
                goto L_0x0133
            L_0x01a7:
                r7 = r1
                goto L_0x01c1
            L_0x01a9:
                boolean r1 = r0.e
                if (r1 == 0) goto L_0x01c1
                if (r7 <= 0) goto L_0x01c1
                r1 = 19
                if (r4 == r1) goto L_0x01c1
                boolean r1 = r0.f
                if (r1 == 0) goto L_0x01bc
                int r1 = r7 + 1
                r3[r7] = r14
                goto L_0x01bd
            L_0x01bc:
                r1 = r7
            L_0x01bd:
                int r7 = r1 + 1
                r3[r1] = r15
            L_0x01c1:
                boolean r1 = g
                if (r1 != 0) goto L_0x01d0
                int r1 = r0.c
                if (r1 != 0) goto L_0x01ca
                goto L_0x01d0
            L_0x01ca:
                java.lang.AssertionError r1 = new java.lang.AssertionError
                r1.<init>()
                throw r1
            L_0x01d0:
                boolean r1 = g
                if (r1 != 0) goto L_0x01dd
                if (r11 != r6) goto L_0x01d7
                goto L_0x01dd
            L_0x01d7:
                java.lang.AssertionError r1 = new java.lang.AssertionError
                r1.<init>()
                throw r1
            L_0x01dd:
                r0.b = r7
                r0.k = r4
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loc.dt.c.a(byte[], int):boolean");
        }
    }

    private dt() {
    }

    public static String a(byte[] bArr, int i) {
        try {
            int length = bArr.length;
            c cVar = new c(i);
            int i2 = (length / 3) * 4;
            if (!cVar.d) {
                switch (length % 3) {
                    case 0:
                        break;
                    case 1:
                        i2 += 2;
                        break;
                    case 2:
                        i2 += 3;
                        break;
                }
            } else if (length % 3 > 0) {
                i2 += 4;
            }
            if (cVar.e && length > 0) {
                int i3 = 1;
                int i4 = ((length - 1) / 57) + 1;
                if (cVar.f) {
                    i3 = 2;
                }
                i2 += i4 * i3;
            }
            cVar.f6569a = new byte[i2];
            cVar.a(bArr, length);
            if (!f6568a) {
                if (cVar.b != i2) {
                    throw new AssertionError();
                }
            }
            return new String(cVar.f6569a, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] a(String str) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        b bVar = new b(new byte[((length * 3) / 4)]);
        if (!bVar.a(bytes, length)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (bVar.b == bVar.f6569a.length) {
            return bVar.f6569a;
        } else {
            byte[] bArr = new byte[bVar.b];
            System.arraycopy(bVar.f6569a, 0, bArr, 0, bVar.b);
            return bArr;
        }
    }
}
