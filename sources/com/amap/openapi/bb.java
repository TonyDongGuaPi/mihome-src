package com.amap.openapi;

import android.util.Base64;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.miuipub.internal.hybrid.SignUtils;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class bb {

    /* renamed from: a  reason: collision with root package name */
    private static final byte[] f4620a = {Constants.TagName.CARD_APP_VERSION, Constants.TagName.CARD_APP_VERSION, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.TERMINAL_MODEL_NUMBER, 119, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.TERMINAL_BACK_INFO_TYPE, 48, 74, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, 54, TarConstants.U, Constants.TagName.DEVICE_MODEL, 76, Constants.TagName.ELECTRONIC_OUT_STATE, 97, 88, Constants.TagName.PAY_ORDER, TarConstants.R, Constants.TagName.ACTIVITY_INFO, 49, Constants.TagName.ELECTRONIC_OUT_STATE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.CP_NO, Constants.TagName.DEVICE_MODEL, Constants.TagName.ELECTRONIC_TYPE_ID, Constants.TagName.ELECTRONIC_TYPE_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 97, 76, 54, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.INVOICE_TOKEN, 87, TarConstants.R, 103, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.ELECTRONIC_TYPE_ID, Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, 76, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.PAY_ORDER_ID, 51, Constants.TagName.INVOICE_TOKEN, TarConstants.U, TarConstants.U, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.OPERATE_TIMING, 55, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_OUT_STATE, 51, Constants.TagName.PAY_ORDER_ID, 76, 55, 88, Constants.TagName.ELECTRONIC_OUT_STATE, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ELECTRONIC_STATE, Constants.TagName.ORDER_BRIEF_INFO, TarConstants.U, 52, 50, Constants.TagName.USER_LOCK_TIME, Constants.TagName.ORDER_TYPE, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ELECTRONIC_STATE, ScriptToolsConst.TagName.TagSerial, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.MAIN_ORDER_ID, 89, 120, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_ID, TarConstants.R, 48, 76, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.QUERY_DATA_SORT_TYPE, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.INVOICE_TOKEN, 74, Constants.TagName.TERMINAL_BACK_CONTENT, 83, 119, Constants.TagName.TERMINAL_BACK_CONTENT, 119, 83, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.INVOICE_TOKEN, Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.PAY_ORDER, Constants.TagName.ELECTRONIC_FROZEN_FLAG, Constants.TagName.DEVICE_MODEL, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.ELECTRONIC_END_TIME, TarConstants.U, 74, 89, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_OS_VERSION, 119, 119, Constants.TagName.TERMINAL_BASEBAND_VERSION, 77};

    public static byte[] a(byte[] bArr) throws Exception {
        PublicKey generatePublic = KeyFactory.getInstance(SignUtils.f8267a).generatePublic(new X509EncodedKeySpec(Base64.decode(new StringBuffer(new String(f4620a)).reverse().toString().getBytes(), 2)));
        Cipher instance = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING");
        instance.init(1, generatePublic);
        return instance.doFinal(bArr);
    }
}
