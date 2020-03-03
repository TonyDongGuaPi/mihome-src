package com.tsmclient.smartcard.handler;

import android.nfc.tech.TagTechnology;
import android.os.Bundle;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.terminal.IScTerminal;
import java.io.IOException;

public interface ISmartCardHandler<T extends TagTechnology> {
    public static final ByteArray AID_PPSE = ByteArray.wrap(new byte[]{50, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_CONTENT, 89, Constants.TagName.SIM_SEID, 83, 89, 83, Constants.TagName.SIM_SEID, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_BASEBAND_VERSION, 48, 49});
    public static final ByteArray AID_PSE = ByteArray.wrap(new byte[]{49, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_CONTENT, 89, Constants.TagName.SIM_SEID, 83, 89, 83, Constants.TagName.SIM_SEID, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_BASEBAND_VERSION, 48, 49});
    public static final ByteArray EMPTY_RECORD = ByteArray.wrap(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    public static final ByteArray EMPTY_RECORD_TWO = ByteArray.wrap(new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1});
    public static final int ERROR_IO = 1;
    public static final int ERROR_NOT_SUPPORTED = 2;
    public static final int ERROR_UNKNOWN = 3;
    public static final ByteArray GET_BALANCE_CMD = ByteArray.wrap(new byte[]{Byte.MIN_VALUE, Constants.TagName.ORDER_TRADE_STATUSES, 0, 2, 4});
    public static final String KEY_ERROR = "error";
    public static final String KEY_READ_CARD_OPTION_RULES = "KEY_READ_CARD_OPTION_RULES";
    public static final String KEY_READ_CARD_OPTION_SKIP_RECORD = "KEY_READ_CARD_OPTION_SKIP_RECORD";
    public static final String KEY_SUCCESS = "success";
    public static final ByteArray STATUS_APP_NOT_FOUND = ByteArray.wrap(new byte[]{Constants.TagName.PAY_ORDER_ID, -126});
    public static final ByteArray STATUS_ERROR_PARAM = ByteArray.wrap(new byte[]{Constants.TagName.PAY_ORDER_ID, Constants.TagName.ACTIVITY_TOTAL});
    public static final ByteArray STATUS_OK = ByteArray.wrap(new byte[]{Constants.TagName.SYSTEM_VERSION, 0});
    public static final ByteArray STATUS_RECORD_END = ByteArray.wrap(new byte[]{Constants.TagName.PAY_ORDER_ID, -125});
    public static final int TYPE_ISODEP = 1;
    public static final int TYPE_NFCF = 2;

    int getTechType();

    Bundle onHandleCard(T t) throws IOException, UnProcessableCardException;

    Bundle onHandleCard(IScTerminal iScTerminal, Bundle bundle) throws IOException, UnProcessableCardException;
}
