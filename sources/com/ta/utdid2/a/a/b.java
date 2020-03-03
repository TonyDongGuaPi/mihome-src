package com.ta.utdid2.a.a;

import android.annotation.SuppressLint;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import java.io.UnsupportedEncodingException;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class b {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f8962a = (!b.class.desiredAssertionStatus());

    static abstract class a {
        public int op;
        public byte[] output;

        a() {
        }
    }

    public static byte[] decode(String str, int i) {
        return decode(str.getBytes(), i);
    }

    public static byte[] decode(byte[] bArr, int i) {
        return decode(bArr, 0, bArr.length, i);
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) {
        C0064b bVar = new C0064b(i3, new byte[((i2 * 3) / 4)]);
        if (!bVar.process(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (bVar.op == bVar.output.length) {
            return bVar.output;
        } else {
            byte[] bArr2 = new byte[bVar.op];
            System.arraycopy(bVar.output, 0, bArr2, 0, bVar.op);
            return bArr2;
        }
    }

    /* renamed from: com.ta.utdid2.a.a.b$b  reason: collision with other inner class name */
    static class C0064b extends a {

        /* renamed from: a  reason: collision with root package name */
        private static final int[] f8963a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int[] b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private final int[] c;
        private int state;
        private int value;

        public C0064b(int i, byte[] bArr) {
            this.output = bArr;
            this.c = (i & 8) == 0 ? f8963a : b;
            this.state = 0;
            this.value = 0;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d9, code lost:
            r6 = r13;
         */
        /* JADX WARNING: Removed duplicated region for block: B:51:0x00e6  */
        /* JADX WARNING: Removed duplicated region for block: B:53:0x00ed  */
        /* JADX WARNING: Removed duplicated region for block: B:65:0x00e3 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(byte[] r12, int r13, int r14, boolean r15) {
            /*
                r11 = this;
                int r0 = r11.state
                r1 = 0
                r2 = 6
                if (r0 != r2) goto L_0x0007
                return r1
            L_0x0007:
                int r14 = r14 + r13
                int r0 = r11.state
                int r3 = r11.value
                byte[] r4 = r11.output
                int[] r5 = r11.c
                r6 = r3
                r3 = 0
            L_0x0012:
                r7 = 4
                if (r13 >= r14) goto L_0x00e3
                if (r0 != 0) goto L_0x005c
            L_0x0017:
                int r8 = r13 + 4
                if (r8 > r14) goto L_0x0058
                byte r6 = r12[r13]
                r6 = r6 & 255(0xff, float:3.57E-43)
                r6 = r5[r6]
                int r6 = r6 << 18
                int r9 = r13 + 1
                byte r9 = r12[r9]
                r9 = r9 & 255(0xff, float:3.57E-43)
                r9 = r5[r9]
                int r9 = r9 << 12
                r6 = r6 | r9
                int r9 = r13 + 2
                byte r9 = r12[r9]
                r9 = r9 & 255(0xff, float:3.57E-43)
                r9 = r5[r9]
                int r9 = r9 << r2
                r6 = r6 | r9
                int r9 = r13 + 3
                byte r9 = r12[r9]
                r9 = r9 & 255(0xff, float:3.57E-43)
                r9 = r5[r9]
                r6 = r6 | r9
                if (r6 < 0) goto L_0x0058
                int r13 = r3 + 2
                byte r9 = (byte) r6
                r4[r13] = r9
                int r13 = r3 + 1
                int r9 = r6 >> 8
                byte r9 = (byte) r9
                r4[r13] = r9
                int r13 = r6 >> 16
                byte r13 = (byte) r13
                r4[r3] = r13
                int r3 = r3 + 3
                r13 = r8
                goto L_0x0017
            L_0x0058:
                if (r13 < r14) goto L_0x005c
                goto L_0x00e3
            L_0x005c:
                int r8 = r13 + 1
                byte r13 = r12[r13]
                r13 = r13 & 255(0xff, float:3.57E-43)
                r13 = r5[r13]
                r9 = -2
                r10 = -1
                switch(r0) {
                    case 0: goto L_0x00d5;
                    case 1: goto L_0x00c8;
                    case 2: goto L_0x00af;
                    case 3: goto L_0x007c;
                    case 4: goto L_0x0070;
                    case 5: goto L_0x006b;
                    default: goto L_0x0069;
                }
            L_0x0069:
                goto L_0x00e0
            L_0x006b:
                if (r13 == r10) goto L_0x00e0
                r11.state = r2
                return r1
            L_0x0070:
                if (r13 != r9) goto L_0x0077
                int r13 = r0 + 1
                r0 = r13
                goto L_0x00e0
            L_0x0077:
                if (r13 == r10) goto L_0x00e0
                r11.state = r2
                return r1
            L_0x007c:
                if (r13 < 0) goto L_0x0097
                int r0 = r6 << 6
                r13 = r13 | r0
                int r0 = r3 + 2
                byte r6 = (byte) r13
                r4[r0] = r6
                int r0 = r3 + 1
                int r6 = r13 >> 8
                byte r6 = (byte) r6
                r4[r0] = r6
                int r0 = r13 >> 16
                byte r0 = (byte) r0
                r4[r3] = r0
                int r3 = r3 + 3
                r6 = r13
                r0 = 0
                goto L_0x00e0
            L_0x0097:
                if (r13 != r9) goto L_0x00aa
                int r13 = r3 + 1
                int r0 = r6 >> 2
                byte r0 = (byte) r0
                r4[r13] = r0
                int r13 = r6 >> 10
                byte r13 = (byte) r13
                r4[r3] = r13
                int r3 = r3 + 2
                r13 = 5
                r0 = 5
                goto L_0x00e0
            L_0x00aa:
                if (r13 == r10) goto L_0x00e0
                r11.state = r2
                return r1
            L_0x00af:
                if (r13 < 0) goto L_0x00b7
                int r6 = r6 << 6
                r13 = r13 | r6
                int r0 = r0 + 1
                goto L_0x00d9
            L_0x00b7:
                if (r13 != r9) goto L_0x00c3
                int r13 = r3 + 1
                int r0 = r6 >> 4
                byte r0 = (byte) r0
                r4[r3] = r0
                r3 = r13
                r0 = 4
                goto L_0x00e0
            L_0x00c3:
                if (r13 == r10) goto L_0x00e0
                r11.state = r2
                return r1
            L_0x00c8:
                if (r13 < 0) goto L_0x00d0
                int r6 = r6 << 6
                r13 = r13 | r6
                int r0 = r0 + 1
                goto L_0x00d9
            L_0x00d0:
                if (r13 == r10) goto L_0x00e0
                r11.state = r2
                return r1
            L_0x00d5:
                if (r13 < 0) goto L_0x00db
                int r0 = r0 + 1
            L_0x00d9:
                r6 = r13
                goto L_0x00e0
            L_0x00db:
                if (r13 == r10) goto L_0x00e0
                r11.state = r2
                return r1
            L_0x00e0:
                r13 = r8
                goto L_0x0012
            L_0x00e3:
                r12 = 1
                if (r15 != 0) goto L_0x00ed
                r11.state = r0
                r11.value = r6
                r11.op = r3
                return r12
            L_0x00ed:
                switch(r0) {
                    case 0: goto L_0x010f;
                    case 1: goto L_0x010c;
                    case 2: goto L_0x0103;
                    case 3: goto L_0x00f4;
                    case 4: goto L_0x00f1;
                    default: goto L_0x00f0;
                }
            L_0x00f0:
                goto L_0x010f
            L_0x00f1:
                r11.state = r2
                return r1
            L_0x00f4:
                int r13 = r3 + 1
                int r14 = r6 >> 10
                byte r14 = (byte) r14
                r4[r3] = r14
                int r3 = r13 + 1
                int r14 = r6 >> 2
                byte r14 = (byte) r14
                r4[r13] = r14
                goto L_0x010f
            L_0x0103:
                int r13 = r3 + 1
                int r14 = r6 >> 4
                byte r14 = (byte) r14
                r4[r3] = r14
                r3 = r13
                goto L_0x010f
            L_0x010c:
                r11.state = r2
                return r1
            L_0x010f:
                r11.state = r0
                r11.op = r3
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.a.a.b.C0064b.process(byte[], int, int, boolean):boolean");
        }
    }

    public static String encodeToString(byte[] bArr, int i) {
        try {
            return new String(encode(bArr, i), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] encode(byte[] bArr, int i) {
        return encode(bArr, 0, bArr.length, i);
    }

    @SuppressLint({"Assert"})
    public static byte[] encode(byte[] bArr, int i, int i2, int i3) {
        c cVar = new c(i3, (byte[]) null);
        int i4 = (i2 / 3) * 4;
        if (!cVar.do_padding) {
            switch (i2 % 3) {
                case 1:
                    i4 += 2;
                    break;
                case 2:
                    i4 += 3;
                    break;
            }
        } else if (i2 % 3 > 0) {
            i4 += 4;
        }
        if (cVar.do_newline && i2 > 0) {
            i4 += (((i2 - 1) / 57) + 1) * (cVar.do_cr ? 2 : 1);
        }
        cVar.output = new byte[i4];
        cVar.process(bArr, i, i2, true);
        if (f8962a || cVar.op == i4) {
            return cVar.output;
        }
        throw new AssertionError();
    }

    static class c extends a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ boolean f8964a = (!b.class.desiredAssertionStatus());

        /* renamed from: a  reason: collision with other field name */
        private static final byte[] f31a = {Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ACTIVITY_INFO, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 74, TarConstants.U, 76, 77, 78, Constants.TagName.CP_NO, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 83, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.QUERY_DATA_SORT_TYPE, 87, 88, 89, Constants.TagName.PREDEPOSIT_TOTAL, 97, Constants.TagName.OPERATE_TIMING, Constants.TagName.PAY_ORDER, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ORDER_TYPE, 102, 103, Constants.TagName.DEVICE_MODEL, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.PAY_ORDER_ID, Constants.TagName.ELECTRONIC, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.ELECTRONIC_ID, Constants.TagName.ELECTRONIC_TYPE_ID, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_FROZEN_FLAG, 119, 120, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.ELECTRONIC_OUT_STATE, 48, 49, 50, 51, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.USER_LOCK_TIME, Constants.TagName.CARD_FORM};
        private static final byte[] b = {Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ACTIVITY_INFO, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 74, TarConstants.U, 76, 77, 78, Constants.TagName.CP_NO, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 83, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.QUERY_DATA_SORT_TYPE, 87, 88, 89, Constants.TagName.PREDEPOSIT_TOTAL, 97, Constants.TagName.OPERATE_TIMING, Constants.TagName.PAY_ORDER, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ORDER_TYPE, 102, 103, Constants.TagName.DEVICE_MODEL, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.PAY_ORDER_ID, Constants.TagName.ELECTRONIC, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.ELECTRONIC_ID, Constants.TagName.ELECTRONIC_TYPE_ID, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_FROZEN_FLAG, 119, 120, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.ELECTRONIC_OUT_STATE, 48, 49, 50, 51, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, 45, 95};

        /* renamed from: a  reason: collision with other field name */
        int f32a;
        private final byte[] c;
        private int count;
        private final byte[] d;
        public final boolean do_cr;
        public final boolean do_newline;
        public final boolean do_padding;

        public c(int i, byte[] bArr) {
            this.output = bArr;
            boolean z = true;
            this.do_padding = (i & 1) == 0;
            this.do_newline = (i & 2) == 0;
            this.do_cr = (i & 4) == 0 ? false : z;
            this.d = (i & 8) == 0 ? f31a : b;
            this.c = new byte[2];
            this.f32a = 0;
            this.count = this.do_newline ? 19 : -1;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(byte[] r19, int r20, int r21, boolean r22) {
            /*
                r18 = this;
                r0 = r18
                byte[] r3 = r0.d
                byte[] r4 = r0.output
                int r5 = r0.count
                int r6 = r21 + r20
                int r7 = r0.f32a
                r8 = -1
                r9 = 0
                r10 = 1
                switch(r7) {
                    case 0: goto L_0x004f;
                    case 1: goto L_0x0030;
                    case 2: goto L_0x0013;
                    default: goto L_0x0012;
                }
            L_0x0012:
                goto L_0x004f
            L_0x0013:
                int r7 = r20 + 1
                if (r7 > r6) goto L_0x004f
                byte[] r11 = r0.c
                byte r11 = r11[r9]
                r11 = r11 & 255(0xff, float:3.57E-43)
                int r11 = r11 << 16
                byte[] r12 = r0.c
                byte r12 = r12[r10]
                r12 = r12 & 255(0xff, float:3.57E-43)
                int r12 = r12 << 8
                r11 = r11 | r12
                byte r2 = r19[r20]
                r2 = r2 & 255(0xff, float:3.57E-43)
                r2 = r2 | r11
                r0.f32a = r9
                goto L_0x0052
            L_0x0030:
                int r7 = r20 + 2
                if (r7 > r6) goto L_0x004f
                byte[] r7 = r0.c
                byte r7 = r7[r9]
                r7 = r7 & 255(0xff, float:3.57E-43)
                int r7 = r7 << 16
                int r11 = r20 + 1
                byte r2 = r19[r20]
                r2 = r2 & 255(0xff, float:3.57E-43)
                int r2 = r2 << 8
                r2 = r2 | r7
                int r7 = r11 + 1
                byte r11 = r19[r11]
                r11 = r11 & 255(0xff, float:3.57E-43)
                r2 = r2 | r11
                r0.f32a = r9
                goto L_0x0052
            L_0x004f:
                r7 = r20
                r2 = -1
            L_0x0052:
                r12 = 4
                r13 = 13
                r14 = 10
                r15 = 2
                if (r2 == r8) goto L_0x0090
                int r8 = r2 >> 18
                r8 = r8 & 63
                byte r8 = r3[r8]
                r4[r9] = r8
                int r8 = r2 >> 12
                r8 = r8 & 63
                byte r8 = r3[r8]
                r4[r10] = r8
                int r8 = r2 >> 6
                r8 = r8 & 63
                byte r8 = r3[r8]
                r4[r15] = r8
                r2 = r2 & 63
                byte r2 = r3[r2]
                r8 = 3
                r4[r8] = r2
                int r5 = r5 + -1
                if (r5 != 0) goto L_0x008d
                boolean r2 = r0.do_cr
                if (r2 == 0) goto L_0x0085
                r2 = 5
                r4[r12] = r13
                goto L_0x0086
            L_0x0085:
                r2 = 4
            L_0x0086:
                int r5 = r2 + 1
                r4[r2] = r14
                r2 = 19
                goto L_0x0092
            L_0x008d:
                r2 = r5
                r5 = 4
                goto L_0x0092
            L_0x0090:
                r2 = r5
                r5 = 0
            L_0x0092:
                int r8 = r7 + 3
                if (r8 > r6) goto L_0x00eb
                byte r11 = r19[r7]
                r11 = r11 & 255(0xff, float:3.57E-43)
                int r11 = r11 << 16
                int r16 = r7 + 1
                byte r15 = r19[r16]
                r15 = r15 & 255(0xff, float:3.57E-43)
                int r15 = r15 << 8
                r11 = r11 | r15
                int r7 = r7 + 2
                byte r7 = r19[r7]
                r7 = r7 & 255(0xff, float:3.57E-43)
                r7 = r7 | r11
                int r11 = r7 >> 18
                r11 = r11 & 63
                byte r11 = r3[r11]
                r4[r5] = r11
                int r11 = r5 + 1
                int r15 = r7 >> 12
                r15 = r15 & 63
                byte r15 = r3[r15]
                r4[r11] = r15
                int r11 = r5 + 2
                int r15 = r7 >> 6
                r15 = r15 & 63
                byte r15 = r3[r15]
                r4[r11] = r15
                int r11 = r5 + 3
                r7 = r7 & 63
                byte r7 = r3[r7]
                r4[r11] = r7
                int r5 = r5 + 4
                int r2 = r2 + -1
                if (r2 != 0) goto L_0x00e8
                boolean r2 = r0.do_cr
                if (r2 == 0) goto L_0x00df
                int r2 = r5 + 1
                r4[r5] = r13
                goto L_0x00e0
            L_0x00df:
                r2 = r5
            L_0x00e0:
                int r5 = r2 + 1
                r4[r2] = r14
                r7 = r8
                r2 = 19
                goto L_0x00e9
            L_0x00e8:
                r7 = r8
            L_0x00e9:
                r15 = 2
                goto L_0x0092
            L_0x00eb:
                if (r22 == 0) goto L_0x01eb
                int r8 = r0.f32a
                int r8 = r7 - r8
                int r11 = r6 + -1
                if (r8 != r11) goto L_0x0140
                int r8 = r0.f32a
                if (r8 <= 0) goto L_0x00ff
                byte[] r1 = r0.c
                byte r1 = r1[r9]
                r9 = 1
                goto L_0x0104
            L_0x00ff:
                int r8 = r7 + 1
                byte r1 = r19[r7]
                r7 = r8
            L_0x0104:
                r1 = r1 & 255(0xff, float:3.57E-43)
                int r1 = r1 << r12
                int r8 = r0.f32a
                int r8 = r8 - r9
                r0.f32a = r8
                int r8 = r5 + 1
                int r9 = r1 >> 6
                r9 = r9 & 63
                byte r9 = r3[r9]
                r4[r5] = r9
                int r5 = r8 + 1
                r1 = r1 & 63
                byte r1 = r3[r1]
                r4[r8] = r1
                boolean r1 = r0.do_padding
                if (r1 == 0) goto L_0x012c
                int r1 = r5 + 1
                r3 = 61
                r4[r5] = r3
                int r5 = r1 + 1
                r4[r1] = r3
            L_0x012c:
                boolean r1 = r0.do_newline
                if (r1 == 0) goto L_0x01cf
                boolean r1 = r0.do_cr
                if (r1 == 0) goto L_0x0139
                int r1 = r5 + 1
                r4[r5] = r13
                goto L_0x013a
            L_0x0139:
                r1 = r5
            L_0x013a:
                int r5 = r1 + 1
                r4[r1] = r14
                goto L_0x01cf
            L_0x0140:
                int r8 = r0.f32a
                int r8 = r7 - r8
                int r11 = r6 + -2
                if (r8 != r11) goto L_0x01b6
                int r8 = r0.f32a
                if (r8 <= r10) goto L_0x0157
                byte[] r8 = r0.c
                byte r8 = r8[r9]
                r9 = 1
                r17 = r8
                r8 = r7
                r7 = r17
                goto L_0x015b
            L_0x0157:
                int r8 = r7 + 1
                byte r7 = r19[r7]
            L_0x015b:
                r7 = r7 & 255(0xff, float:3.57E-43)
                int r7 = r7 << r14
                int r11 = r0.f32a
                if (r11 <= 0) goto L_0x0169
                byte[] r1 = r0.c
                int r11 = r9 + 1
                byte r1 = r1[r9]
                goto L_0x016f
            L_0x0169:
                int r11 = r8 + 1
                byte r1 = r19[r8]
                r8 = r11
                r11 = r9
            L_0x016f:
                r1 = r1 & 255(0xff, float:3.57E-43)
                r9 = 2
                int r1 = r1 << r9
                r1 = r1 | r7
                int r7 = r0.f32a
                int r7 = r7 - r11
                r0.f32a = r7
                int r7 = r5 + 1
                int r9 = r1 >> 12
                r9 = r9 & 63
                byte r9 = r3[r9]
                r4[r5] = r9
                int r5 = r7 + 1
                int r9 = r1 >> 6
                r9 = r9 & 63
                byte r9 = r3[r9]
                r4[r7] = r9
                int r7 = r5 + 1
                r1 = r1 & 63
                byte r1 = r3[r1]
                r4[r5] = r1
                boolean r1 = r0.do_padding
                if (r1 == 0) goto L_0x01a0
                int r1 = r7 + 1
                r3 = 61
                r4[r7] = r3
                goto L_0x01a1
            L_0x01a0:
                r1 = r7
            L_0x01a1:
                boolean r3 = r0.do_newline
                if (r3 == 0) goto L_0x01b3
                boolean r3 = r0.do_cr
                if (r3 == 0) goto L_0x01ae
                int r3 = r1 + 1
                r4[r1] = r13
                r1 = r3
            L_0x01ae:
                int r3 = r1 + 1
                r4[r1] = r14
                r1 = r3
            L_0x01b3:
                r5 = r1
                r7 = r8
                goto L_0x01cf
            L_0x01b6:
                boolean r1 = r0.do_newline
                if (r1 == 0) goto L_0x01cf
                if (r5 <= 0) goto L_0x01cf
                r1 = 19
                if (r2 == r1) goto L_0x01cf
                boolean r1 = r0.do_cr
                if (r1 == 0) goto L_0x01c9
                int r1 = r5 + 1
                r4[r5] = r13
                goto L_0x01ca
            L_0x01c9:
                r1 = r5
            L_0x01ca:
                int r3 = r1 + 1
                r4[r1] = r14
                r5 = r3
            L_0x01cf:
                boolean r1 = f8964a
                if (r1 != 0) goto L_0x01de
                int r1 = r0.f32a
                if (r1 != 0) goto L_0x01d8
                goto L_0x01de
            L_0x01d8:
                java.lang.AssertionError r1 = new java.lang.AssertionError
                r1.<init>()
                throw r1
            L_0x01de:
                boolean r1 = f8964a
                if (r1 != 0) goto L_0x0219
                if (r7 != r6) goto L_0x01e5
                goto L_0x0219
            L_0x01e5:
                java.lang.AssertionError r1 = new java.lang.AssertionError
                r1.<init>()
                throw r1
            L_0x01eb:
                int r3 = r6 + -1
                if (r7 != r3) goto L_0x01fc
                byte[] r3 = r0.c
                int r4 = r0.f32a
                int r6 = r4 + 1
                r0.f32a = r6
                byte r1 = r19[r7]
                r3[r4] = r1
                goto L_0x0219
            L_0x01fc:
                r3 = 2
                int r6 = r6 - r3
                if (r7 != r6) goto L_0x0219
                byte[] r3 = r0.c
                int r4 = r0.f32a
                int r6 = r4 + 1
                r0.f32a = r6
                byte r6 = r19[r7]
                r3[r4] = r6
                byte[] r3 = r0.c
                int r4 = r0.f32a
                int r6 = r4 + 1
                r0.f32a = r6
                int r7 = r7 + r10
                byte r1 = r19[r7]
                r3[r4] = r1
            L_0x0219:
                r0.op = r5
                r0.count = r2
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.a.a.b.c.process(byte[], int, int, boolean):boolean");
        }
    }

    private b() {
    }
}
