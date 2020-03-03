package com.miui.tsmclient.util;

import android.text.TextUtils;
import android.util.Pair;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.google.common.base.Ascii;
import com.tsmclient.smartcard.Coder;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class DMPEncryptUtil {
    private static final String ALGORITHM_AES = "AES";
    private static final String ALGORITHM_RSA = "RSA";
    private static final String BOUNCY_CASTLE_PROVIDER = "BC";
    private static final String RSA_ECB_PKCS1Padding = "RSA/ECB/PKCS1Padding";
    private static byte[] base64DecodeChars = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Constants.TagName.CARD_BUSINESS_ORDER_STATUS, -1, -1, -1, Constants.TagName.CARD_APP_ACTIVATION_STATUS, 52, TarConstants.R, 54, 55, ScriptToolsConst.TagName.TagSerial, ScriptToolsConst.TagName.TagApdu, Constants.TagName.BUSINESS_ORDER_OP_TYPE, Constants.TagName.CARD_APP_RAMDOM, ScriptToolsConst.TagName.TagExpectationAndNext, Constants.TagName.CARD_APP_VERSION, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, Ascii.GS, 30, 31, 32, Framer.ENTER_FRAME_PREFIX, 34, 35, Constants.TagName.USER_LOGIN_FAIL_COUNT, Constants.TagName.ORDER_RANGE_TYPE, Constants.TagName.QUERY_RECORD_COUNT, 39, Constants.TagName.CARD_APP_BLANCE, 41, 42, Constants.TagName.USER_LOCK_TIME, Constants.TagName.SYSTEM_NEW_VERSION, 45, Constants.TagName.SIM_SEID, Constants.TagName.CARD_FORM, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    public static Pair<String, byte[]> encodeAES(String str) throws Exception {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        instance.init(128);
        byte[] encoded = instance.generateKey().getEncoded();
        SecretKeySpec secretKeySpec = new SecretKeySpec(encoded, "AES");
        Cipher instance2 = Cipher.getInstance("AES");
        instance2.init(1, secretKeySpec);
        return new Pair<>(Coder.bytesToHexString(instance2.doFinal(str.getBytes("utf-8"))), encoded);
    }

    public static String encodeRSAByPublicKey(byte[] bArr, String str) throws Exception {
        if (bArr == null || TextUtils.isEmpty(str)) {
            return null;
        }
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding", BOUNCY_CASTLE_PROVIDER);
        instance.init(1, (RSAPublicKey) KeyFactory.getInstance("RSA", BOUNCY_CASTLE_PROVIDER).generatePublic(new X509EncodedKeySpec(decodeKey(str))));
        return Coder.bytesToHexString(instance.doFinal(bArr));
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002b A[LOOP:2: B:10:0x002b->B:13:0x0038, LOOP_START, PHI: r4 
      PHI: (r4v1 int) = (r4v0 int), (r4v9 int) binds: [B:9:0x0027, B:13:0x0038] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0096 A[LOOP:0: B:4:0x0015->B:35:0x0096, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a2 A[EDGE_INSN: B:39:0x00a2->B:36:0x00a2 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a2 A[EDGE_INSN: B:41:0x00a2->B:36:0x00a2 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a2 A[EDGE_INSN: B:42:0x00a2->B:36:0x00a2 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a2 A[EDGE_INSN: B:43:0x00a2->B:36:0x00a2 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] decodeKey(java.lang.String r8) throws java.io.UnsupportedEncodingException {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 == 0) goto L_0x0008
            r8 = 0
            return r8
        L_0x0008:
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "US-ASCII"
            byte[] r8 = r8.getBytes(r1)
            int r1 = r8.length
            r2 = 0
        L_0x0015:
            if (r2 >= r1) goto L_0x00a2
        L_0x0017:
            byte[] r3 = base64DecodeChars
            int r4 = r2 + 1
            byte r2 = r8[r2]
            byte r2 = r3[r2]
            r3 = -1
            if (r4 >= r1) goto L_0x0027
            if (r2 == r3) goto L_0x0025
            goto L_0x0027
        L_0x0025:
            r2 = r4
            goto L_0x0017
        L_0x0027:
            if (r2 != r3) goto L_0x002b
            goto L_0x00a2
        L_0x002b:
            byte[] r5 = base64DecodeChars
            int r6 = r4 + 1
            byte r4 = r8[r4]
            byte r4 = r5[r4]
            if (r6 >= r1) goto L_0x003a
            if (r4 == r3) goto L_0x0038
            goto L_0x003a
        L_0x0038:
            r4 = r6
            goto L_0x002b
        L_0x003a:
            if (r4 != r3) goto L_0x003e
            goto L_0x00a2
        L_0x003e:
            int r2 = r2 << 2
            r5 = r4 & 48
            int r5 = r5 >>> 4
            r2 = r2 | r5
            char r2 = (char) r2
            r0.append(r2)
        L_0x0049:
            int r2 = r6 + 1
            byte r5 = r8[r6]
            r6 = 61
            if (r5 != r6) goto L_0x005c
            java.lang.String r8 = r0.toString()
            java.lang.String r0 = "iso8859-1"
            byte[] r8 = r8.getBytes(r0)
            return r8
        L_0x005c:
            byte[] r7 = base64DecodeChars
            byte r5 = r7[r5]
            if (r2 >= r1) goto L_0x0067
            if (r5 == r3) goto L_0x0065
            goto L_0x0067
        L_0x0065:
            r6 = r2
            goto L_0x0049
        L_0x0067:
            if (r5 != r3) goto L_0x006a
            goto L_0x00a2
        L_0x006a:
            r4 = r4 & 15
            int r4 = r4 << 4
            r7 = r5 & 60
            int r7 = r7 >>> 2
            r4 = r4 | r7
            char r4 = (char) r4
            r0.append(r4)
        L_0x0077:
            int r4 = r2 + 1
            byte r2 = r8[r2]
            if (r2 != r6) goto L_0x0088
            java.lang.String r8 = r0.toString()
            java.lang.String r0 = "iso8859-1"
            byte[] r8 = r8.getBytes(r0)
            return r8
        L_0x0088:
            byte[] r7 = base64DecodeChars
            byte r2 = r7[r2]
            if (r4 >= r1) goto L_0x0093
            if (r2 == r3) goto L_0x0091
            goto L_0x0093
        L_0x0091:
            r2 = r4
            goto L_0x0077
        L_0x0093:
            if (r2 != r3) goto L_0x0096
            goto L_0x00a2
        L_0x0096:
            r3 = r5 & 3
            int r3 = r3 << 6
            r2 = r2 | r3
            char r2 = (char) r2
            r0.append(r2)
            r2 = r4
            goto L_0x0015
        L_0x00a2:
            java.lang.String r8 = r0.toString()
            java.lang.String r0 = "iso8859-1"
            byte[] r8 = r8.getBytes(r0)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.util.DMPEncryptUtil.decodeKey(java.lang.String):byte[]");
    }
}
