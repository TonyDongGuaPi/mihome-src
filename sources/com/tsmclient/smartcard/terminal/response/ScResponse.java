package com.tsmclient.smartcard.terminal.response;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.tsmclient.smartcard.ByteArray;

public interface ScResponse {
    public static final ByteArray STATUS_ACTIVATION_CONFLICT = ByteArray.wrap(new byte[]{Constants.TagName.PAY_ORDER, 48});
    public static final ByteArray STATUS_APP_NOTE_EXISTS = ByteArray.wrap(new byte[]{Constants.TagName.PAY_ORDER_ID, -126});
    public static final ByteArray STATUS_INCORRECT_LC = ByteArray.wrap(new byte[]{103, 0});
    public static final ByteArray STATUS_INCORRECT_P1_P2 = ByteArray.wrap(new byte[]{Constants.TagName.PAY_ORDER_ID, Constants.TagName.ACTIVITY_TOTAL});
    public static final ByteArray STATUS_INCORRECT_VALUES_IN_DATA_FIELD = ByteArray.wrap(new byte[]{Constants.TagName.PAY_ORDER_ID, Byte.MIN_VALUE});
    public static final ByteArray STATUS_MORE_DATA_AVAILABLE = ByteArray.wrap(new byte[]{Constants.TagName.PAY_ORDER, 16});
    public static final ByteArray STATUS_OK = ByteArray.wrap(new byte[]{Constants.TagName.SYSTEM_VERSION, 0});
    public static final ByteArray STATUS_OPERATION_FAILED = ByteArray.wrap(new byte[]{Constants.TagName.PAY_ORDER, 32});
    public static final ByteArray STATUS_REFERENCE_NOT_FOUND = ByteArray.wrap(new byte[]{Constants.TagName.PAY_ORDER_ID, Constants.TagName.ACTIVITY_DEFINITION});
    public static final ByteArray STATUS_SE_RESTRICT = ByteArray.wrap(new byte[]{102, -91});

    ByteArray getData();

    ByteArray getStatus();

    byte[] toBytes();
}
