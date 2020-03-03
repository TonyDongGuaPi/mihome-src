package com.facebook.imagepipeline.bitmaps;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.facebook.common.memory.PooledByteBufferFactory;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class EmptyJpegGenerator {
    private static final byte[] EMPTY_JPEG_PREFIX = {-1, -40, -1, -37, 0, Constants.TagName.TERMINAL_BACK_INFO_TYPE, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Constants.TagName.STATION_ENAME, 0, 17, 8};
    private static final byte[] EMPTY_JPEG_SUFFIX = {3, 1, 34, 0, 2, 17, 0, 3, 17, 0, -1, Constants.TagName.USER_PLATFORM_TYPE, 0, 31, 0, 0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, Constants.TagName.USER_PLATFORM_TYPE, 0, Constants.TagName.BUSINESS_HANDLE_RESULT, 16, 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, Constants.TagName.ELECTRONIC_PRICE_FAVOURABLE, 1, 2, 3, 0, 4, 17, 5, 18, Framer.ENTER_FRAME_PREFIX, 49, Constants.TagName.TERMINAL_BACK_CONTENT, 6, 19, Constants.TagName.TERMINAL_BACK_MAIN_ID, 97, 7, 34, Constants.TagName.ELECTRONIC_TYPE_ID, 20, 50, -127, -111, ScriptToolsConst.TagName.ScriptDown, 8, 35, Constants.TagName.INVOICE_TOKEN, Constants.TagName.SEID, Constants.TagName.STATION_NAME, 21, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, -47, -16, Constants.TagName.USER_LOGIN_FAIL_COUNT, 51, Constants.TagName.OPERATE_TIMING, Constants.TagName.ELECTRONIC_NUMBER, -126, 9, 10, 22, 23, 24, 25, 26, Constants.TagName.ORDER_RANGE_TYPE, Constants.TagName.QUERY_RECORD_COUNT, 39, Constants.TagName.CARD_APP_BLANCE, 41, 42, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.BUSINESS_ORDER_OP_TYPE, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ACTIVITY_INFO, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 74, 83, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.QUERY_DATA_SORT_TYPE, 87, 88, 89, Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.PAY_ORDER, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ORDER_TYPE, 102, 103, Constants.TagName.DEVICE_MODEL, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.PAY_ORDER_ID, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_FROZEN_FLAG, 119, 120, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.ELECTRONIC_OUT_STATE, -125, -124, Constants.TagName.ACTIVITY_END, Constants.TagName.ACTIVITY_TOTAL, Constants.TagName.ACTIVITY_REMAINDER, Constants.TagName.ACTIVITY_DEFINITION, Constants.TagName.COMPANY_CODE, Constants.TagName.PAY_CHANNEL_NAME, Constants.TagName.TEXT_NOTICE, Constants.TagName.PLATFORM_NOTICES, Constants.TagName.UNSOLVED_NOTICES, Constants.TagName.PREDEPOSIT_TYPE, Constants.TagName.PREDEPOSIT_INFO, Constants.TagName.PREDEPOSIT_LIST, Constants.TagName.PRODUCT_INFO, Constants.TagName.PRODUCT_CODE, Constants.TagName.PRODUCT_NAME, ScriptToolsConst.TagName.ResponseSingle, ScriptToolsConst.TagName.ResponseMultiple, ScriptToolsConst.TagName.CommandMultiple, -91, Constants.TagName.OPERATION_ID, Constants.TagName.OPERATION_STEP, -88, -87, -86, Constants.TagName.APP_TYPE, Constants.TagName.APP_AID, Constants.TagName.PATCH_DATA, Constants.TagName.BUSINESS_HANDLE_RESULT, Constants.TagName.CPLC, -73, Constants.TagName.EUID, Constants.TagName.SIR, Constants.TagName.IMEI, Constants.TagName.RENT_HANDLE_TYPE, Constants.TagName.RENT_HANDLE_DATD, Constants.TagName.USER_PLATFORM_TYPE, Constants.TagName.USER_PLATFORM_ID, Constants.TagName.PROMOTION_MESSAGE, Constants.TagName.PROMOTION_MESSAGE_LIST, Constants.TagName.PROMOTION_MESSAGE_DATA, Constants.TagName.BUSINESS_ORDER_LOAD_TYPE, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, Constants.TagName.USER_PLATFORM_TYPE, 0, 31, 1, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, Constants.TagName.USER_PLATFORM_TYPE, 0, Constants.TagName.BUSINESS_HANDLE_RESULT, 17, 0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 119, 0, 1, 2, 3, 17, 4, 5, Framer.ENTER_FRAME_PREFIX, 49, 6, 18, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.TERMINAL_BACK_MAIN_ID, 7, 97, Constants.TagName.ELECTRONIC_TYPE_ID, 19, 34, 50, -127, 8, 20, Constants.TagName.INVOICE_TOKEN, -111, ScriptToolsConst.TagName.ScriptDown, Constants.TagName.SEID, Constants.TagName.STATION_NAME, 9, 35, 51, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, -16, 21, Constants.TagName.OPERATE_TIMING, Constants.TagName.ELECTRONIC_NUMBER, -47, 10, 22, Constants.TagName.USER_LOGIN_FAIL_COUNT, 52, -31, Constants.TagName.ORDER_RANGE_TYPE, -15, 23, 24, 25, 26, Constants.TagName.QUERY_RECORD_COUNT, 39, Constants.TagName.CARD_APP_BLANCE, 41, 42, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.BUSINESS_ORDER_OP_TYPE, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ACTIVITY_INFO, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 74, 83, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.QUERY_DATA_SORT_TYPE, 87, 88, 89, Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.PAY_ORDER, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ORDER_TYPE, 102, 103, Constants.TagName.DEVICE_MODEL, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.PAY_ORDER_ID, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_FROZEN_FLAG, 119, 120, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.ELECTRONIC_OUT_STATE, -126, -125, -124, Constants.TagName.ACTIVITY_END, Constants.TagName.ACTIVITY_TOTAL, Constants.TagName.ACTIVITY_REMAINDER, Constants.TagName.ACTIVITY_DEFINITION, Constants.TagName.COMPANY_CODE, Constants.TagName.PAY_CHANNEL_NAME, Constants.TagName.TEXT_NOTICE, Constants.TagName.PLATFORM_NOTICES, Constants.TagName.UNSOLVED_NOTICES, Constants.TagName.PREDEPOSIT_TYPE, Constants.TagName.PREDEPOSIT_INFO, Constants.TagName.PREDEPOSIT_LIST, Constants.TagName.PRODUCT_INFO, Constants.TagName.PRODUCT_CODE, Constants.TagName.PRODUCT_NAME, ScriptToolsConst.TagName.ResponseSingle, ScriptToolsConst.TagName.ResponseMultiple, ScriptToolsConst.TagName.CommandMultiple, -91, Constants.TagName.OPERATION_ID, Constants.TagName.OPERATION_STEP, -88, -87, -86, Constants.TagName.APP_TYPE, Constants.TagName.APP_AID, Constants.TagName.PATCH_DATA, Constants.TagName.BUSINESS_HANDLE_RESULT, Constants.TagName.CPLC, -73, Constants.TagName.EUID, Constants.TagName.SIR, Constants.TagName.IMEI, Constants.TagName.RENT_HANDLE_TYPE, Constants.TagName.RENT_HANDLE_DATD, Constants.TagName.USER_PLATFORM_TYPE, Constants.TagName.USER_PLATFORM_ID, Constants.TagName.PROMOTION_MESSAGE, Constants.TagName.PROMOTION_MESSAGE_LIST, Constants.TagName.PROMOTION_MESSAGE_DATA, Constants.TagName.BUSINESS_ORDER_LOAD_TYPE, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -30, -29, -28, -27, -26, -25, -24, -23, -22, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -38, 0, 12, 3, 1, 0, 2, 17, 3, 17, 0, Constants.TagName.CARD_APP_ACTIVATION_STATUS, 0, Constants.TagName.URL_TYPE, Constants.TagName.PAY_CHANNEL_NAME, Constants.TagName.CARD_APP_BLANCE, ScriptToolsConst.TagName.CommandSingle, 15, -1, -39};
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    public EmptyJpegGenerator(PooledByteBufferFactory pooledByteBufferFactory) {
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.common.references.CloseableReference<com.facebook.common.memory.PooledByteBuffer> generate(short r5, short r6) {
        /*
            r4 = this;
            r0 = 0
            com.facebook.common.memory.PooledByteBufferFactory r1 = r4.mPooledByteBufferFactory     // Catch:{ IOException -> 0x0048 }
            byte[] r2 = EMPTY_JPEG_PREFIX     // Catch:{ IOException -> 0x0048 }
            int r2 = r2.length     // Catch:{ IOException -> 0x0048 }
            byte[] r3 = EMPTY_JPEG_SUFFIX     // Catch:{ IOException -> 0x0048 }
            int r3 = r3.length     // Catch:{ IOException -> 0x0048 }
            int r2 = r2 + r3
            int r2 = r2 + 4
            com.facebook.common.memory.PooledByteBufferOutputStream r1 = r1.newOutputStream(r2)     // Catch:{ IOException -> 0x0048 }
            byte[] r0 = EMPTY_JPEG_PREFIX     // Catch:{ IOException -> 0x0042, all -> 0x0040 }
            r1.write(r0)     // Catch:{ IOException -> 0x0042, all -> 0x0040 }
            int r0 = r6 >> 8
            byte r0 = (byte) r0     // Catch:{ IOException -> 0x0042, all -> 0x0040 }
            r1.write(r0)     // Catch:{ IOException -> 0x0042, all -> 0x0040 }
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte r6 = (byte) r6     // Catch:{ IOException -> 0x0042, all -> 0x0040 }
            r1.write(r6)     // Catch:{ IOException -> 0x0042, all -> 0x0040 }
            int r6 = r5 >> 8
            byte r6 = (byte) r6     // Catch:{ IOException -> 0x0042, all -> 0x0040 }
            r1.write(r6)     // Catch:{ IOException -> 0x0042, all -> 0x0040 }
            r5 = r5 & 255(0xff, float:3.57E-43)
            byte r5 = (byte) r5     // Catch:{ IOException -> 0x0042, all -> 0x0040 }
            r1.write(r5)     // Catch:{ IOException -> 0x0042, all -> 0x0040 }
            byte[] r5 = EMPTY_JPEG_SUFFIX     // Catch:{ IOException -> 0x0042, all -> 0x0040 }
            r1.write(r5)     // Catch:{ IOException -> 0x0042, all -> 0x0040 }
            com.facebook.common.memory.PooledByteBuffer r5 = r1.toByteBuffer()     // Catch:{ IOException -> 0x0042, all -> 0x0040 }
            com.facebook.common.references.CloseableReference r5 = com.facebook.common.references.CloseableReference.of(r5)     // Catch:{ IOException -> 0x0042, all -> 0x0040 }
            if (r1 == 0) goto L_0x003f
            r1.close()
        L_0x003f:
            return r5
        L_0x0040:
            r5 = move-exception
            goto L_0x004f
        L_0x0042:
            r5 = move-exception
            r0 = r1
            goto L_0x0049
        L_0x0045:
            r5 = move-exception
            r1 = r0
            goto L_0x004f
        L_0x0048:
            r5 = move-exception
        L_0x0049:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException     // Catch:{ all -> 0x0045 }
            r6.<init>(r5)     // Catch:{ all -> 0x0045 }
            throw r6     // Catch:{ all -> 0x0045 }
        L_0x004f:
            if (r1 == 0) goto L_0x0054
            r1.close()
        L_0x0054:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.bitmaps.EmptyJpegGenerator.generate(short, short):com.facebook.common.references.CloseableReference");
    }
}
