package com.drew.metadata.jpeg;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.MetadataException;
import com.drew.metadata.TagDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class HuffmanTablesDirectory extends Directory {
    public static final int e = 1;
    protected static final byte[] f = {0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
    protected static final byte[] g = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    protected static final byte[] h = {0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0};
    protected static final byte[] i = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    protected static final byte[] j = {0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, Constants.TagName.ELECTRONIC_PRICE_FAVOURABLE};
    protected static final byte[] k = {1, 2, 3, 0, 4, 17, 5, 18, Framer.ENTER_FRAME_PREFIX, 49, Constants.TagName.TERMINAL_BACK_CONTENT, 6, 19, Constants.TagName.TERMINAL_BACK_MAIN_ID, 97, 7, 34, Constants.TagName.ELECTRONIC_TYPE_ID, 20, 50, -127, -111, ScriptToolsConst.TagName.ScriptDown, 8, 35, Constants.TagName.INVOICE_TOKEN, Constants.TagName.SEID, Constants.TagName.STATION_NAME, 21, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, -47, -16, Constants.TagName.USER_LOGIN_FAIL_COUNT, 51, Constants.TagName.OPERATE_TIMING, Constants.TagName.ELECTRONIC_NUMBER, -126, 9, 10, 22, 23, 24, 25, 26, Constants.TagName.ORDER_RANGE_TYPE, Constants.TagName.QUERY_RECORD_COUNT, 39, Constants.TagName.CARD_APP_BLANCE, 41, 42, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.BUSINESS_ORDER_OP_TYPE, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ACTIVITY_INFO, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 74, 83, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.QUERY_DATA_SORT_TYPE, 87, 88, 89, Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.PAY_ORDER, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ORDER_TYPE, 102, 103, Constants.TagName.DEVICE_MODEL, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.PAY_ORDER_ID, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_FROZEN_FLAG, 119, 120, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.ELECTRONIC_OUT_STATE, -125, -124, Constants.TagName.ACTIVITY_END, Constants.TagName.ACTIVITY_TOTAL, Constants.TagName.ACTIVITY_REMAINDER, Constants.TagName.ACTIVITY_DEFINITION, Constants.TagName.COMPANY_CODE, Constants.TagName.PAY_CHANNEL_NAME, Constants.TagName.TEXT_NOTICE, Constants.TagName.PLATFORM_NOTICES, Constants.TagName.UNSOLVED_NOTICES, Constants.TagName.PREDEPOSIT_TYPE, Constants.TagName.PREDEPOSIT_INFO, Constants.TagName.PREDEPOSIT_LIST, Constants.TagName.PRODUCT_INFO, Constants.TagName.PRODUCT_CODE, Constants.TagName.PRODUCT_NAME, ScriptToolsConst.TagName.ResponseSingle, ScriptToolsConst.TagName.ResponseMultiple, ScriptToolsConst.TagName.CommandMultiple, -91, Constants.TagName.OPERATION_ID, Constants.TagName.OPERATION_STEP, -88, -87, -86, Constants.TagName.APP_TYPE, Constants.TagName.APP_AID, Constants.TagName.PATCH_DATA, Constants.TagName.BUSINESS_HANDLE_RESULT, Constants.TagName.CPLC, -73, Constants.TagName.EUID, Constants.TagName.SIR, Constants.TagName.IMEI, Constants.TagName.RENT_HANDLE_TYPE, Constants.TagName.RENT_HANDLE_DATD, Constants.TagName.USER_PLATFORM_TYPE, Constants.TagName.USER_PLATFORM_ID, Constants.TagName.PROMOTION_MESSAGE, Constants.TagName.PROMOTION_MESSAGE_LIST, Constants.TagName.PROMOTION_MESSAGE_DATA, Constants.TagName.BUSINESS_ORDER_LOAD_TYPE, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6};
    protected static final byte[] l = {0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 119};
    protected static final byte[] m = {0, 1, 2, 3, 17, 4, 5, Framer.ENTER_FRAME_PREFIX, 49, 6, 18, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.TERMINAL_BACK_MAIN_ID, 7, 97, Constants.TagName.ELECTRONIC_TYPE_ID, 19, 34, 50, -127, 8, 20, Constants.TagName.INVOICE_TOKEN, -111, ScriptToolsConst.TagName.ScriptDown, Constants.TagName.SEID, Constants.TagName.STATION_NAME, 9, 35, 51, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, -16, 21, Constants.TagName.OPERATE_TIMING, Constants.TagName.ELECTRONIC_NUMBER, -47, 10, 22, Constants.TagName.USER_LOGIN_FAIL_COUNT, 52, -31, Constants.TagName.ORDER_RANGE_TYPE, -15, 23, 24, 25, 26, Constants.TagName.QUERY_RECORD_COUNT, 39, Constants.TagName.CARD_APP_BLANCE, 41, 42, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.BUSINESS_ORDER_OP_TYPE, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ACTIVITY_INFO, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 74, 83, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.QUERY_DATA_SORT_TYPE, 87, 88, 89, Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.PAY_ORDER, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ORDER_TYPE, 102, 103, Constants.TagName.DEVICE_MODEL, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.PAY_ORDER_ID, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_FROZEN_FLAG, 119, 120, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.ELECTRONIC_OUT_STATE, -126, -125, -124, Constants.TagName.ACTIVITY_END, Constants.TagName.ACTIVITY_TOTAL, Constants.TagName.ACTIVITY_REMAINDER, Constants.TagName.ACTIVITY_DEFINITION, Constants.TagName.COMPANY_CODE, Constants.TagName.PAY_CHANNEL_NAME, Constants.TagName.TEXT_NOTICE, Constants.TagName.PLATFORM_NOTICES, Constants.TagName.UNSOLVED_NOTICES, Constants.TagName.PREDEPOSIT_TYPE, Constants.TagName.PREDEPOSIT_INFO, Constants.TagName.PREDEPOSIT_LIST, Constants.TagName.PRODUCT_INFO, Constants.TagName.PRODUCT_CODE, Constants.TagName.PRODUCT_NAME, ScriptToolsConst.TagName.ResponseSingle, ScriptToolsConst.TagName.ResponseMultiple, ScriptToolsConst.TagName.CommandMultiple, -91, Constants.TagName.OPERATION_ID, Constants.TagName.OPERATION_STEP, -88, -87, -86, Constants.TagName.APP_TYPE, Constants.TagName.APP_AID, Constants.TagName.PATCH_DATA, Constants.TagName.BUSINESS_HANDLE_RESULT, Constants.TagName.CPLC, -73, Constants.TagName.EUID, Constants.TagName.SIR, Constants.TagName.IMEI, Constants.TagName.RENT_HANDLE_TYPE, Constants.TagName.RENT_HANDLE_DATD, Constants.TagName.USER_PLATFORM_TYPE, Constants.TagName.USER_PLATFORM_ID, Constants.TagName.PROMOTION_MESSAGE, Constants.TagName.PROMOTION_MESSAGE_LIST, Constants.TagName.PROMOTION_MESSAGE_DATA, Constants.TagName.BUSINESS_ORDER_LOAD_TYPE, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -30, -29, -28, -27, -26, -25, -24, -23, -22, -14, -13, -12, -11, -10, -9, -8, -7, -6};
    @NotNull
    protected static final HashMap<Integer, String> o = new HashMap<>();
    @NotNull
    protected final List<HuffmanTable> n = new ArrayList(4);

    @NotNull
    public String a() {
        return "Huffman";
    }

    static {
        o.put(1, "Number of Tables");
    }

    public HuffmanTablesDirectory() {
        a((TagDescriptor) new HuffmanTablesDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return o;
    }

    @NotNull
    public HuffmanTable y(int i2) {
        return this.n.get(i2);
    }

    public int j() throws MetadataException {
        return b(1);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public List<HuffmanTable> k() {
        return this.n;
    }

    public boolean l() {
        if (this.n.size() == 0) {
            return false;
        }
        for (HuffmanTable f2 : this.n) {
            if (!f2.f()) {
                return false;
            }
        }
        return true;
    }

    public boolean m() {
        return !l();
    }

    public static class HuffmanTable {

        /* renamed from: a  reason: collision with root package name */
        private final int f5233a;
        private final HuffmanTableClass b;
        private final int c;
        private final byte[] d;
        private final byte[] e;

        public HuffmanTable(@NotNull HuffmanTableClass huffmanTableClass, int i, @NotNull byte[] bArr, @NotNull byte[] bArr2) {
            if (bArr == null) {
                throw new IllegalArgumentException("lengthBytes cannot be null.");
            } else if (bArr2 != null) {
                this.b = huffmanTableClass;
                this.c = i;
                this.d = bArr;
                this.e = bArr2;
                this.f5233a = this.e.length + 17;
            } else {
                throw new IllegalArgumentException("valueBytes cannot be null.");
            }
        }

        public int a() {
            return this.f5233a;
        }

        public HuffmanTableClass b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        @NotNull
        public byte[] d() {
            byte[] bArr = new byte[this.d.length];
            System.arraycopy(this.d, 0, bArr, 0, this.d.length);
            return bArr;
        }

        @NotNull
        public byte[] e() {
            byte[] bArr = new byte[this.e.length];
            System.arraycopy(this.e, 0, bArr, 0, this.e.length);
            return bArr;
        }

        public boolean f() {
            if (this.b == HuffmanTableClass.DC) {
                if (Arrays.equals(this.d, HuffmanTablesDirectory.f) && Arrays.equals(this.e, HuffmanTablesDirectory.g)) {
                    return true;
                }
                if (!Arrays.equals(this.d, HuffmanTablesDirectory.h) || !Arrays.equals(this.e, HuffmanTablesDirectory.i)) {
                    return false;
                }
                return true;
            } else if (this.b != HuffmanTableClass.AC) {
                return false;
            } else {
                if (Arrays.equals(this.d, HuffmanTablesDirectory.j) && Arrays.equals(this.e, HuffmanTablesDirectory.k)) {
                    return true;
                }
                if (!Arrays.equals(this.d, HuffmanTablesDirectory.l) || !Arrays.equals(this.e, HuffmanTablesDirectory.m)) {
                    return false;
                }
                return true;
            }
        }

        public boolean g() {
            return !f();
        }

        public enum HuffmanTableClass {
            DC,
            AC,
            UNKNOWN;

            public static HuffmanTableClass typeOf(int i) {
                switch (i) {
                    case 0:
                        return DC;
                    case 1:
                        return AC;
                    default:
                        return UNKNOWN;
                }
            }
        }
    }
}
