package com.tsmclient.smartcard.exception;

import java.io.IOException;

public class NfcEeIOException extends IOException {
    public static final int NFC_DISABLED = 1;
    public static final int NFC_EE_INVALID_COMMAND = 7;
    public static final int NFC_EE_IO_ERROR = 4;
    public static final int NFC_EE_NOT_PRESENTED = 6;
    public static final int NFC_EE_OPENED_ALREADY = 3;
    public static final int NFC_EE_RESTRICTED = 5;
    public static final int NFC_EE_TIME_OUT = 8;
    public static final int NFC_NOT_OPENED = 2;
    private int mErrorCode;

    public NfcEeIOException(String str) {
        this(str, 0);
    }

    public NfcEeIOException(String str, int i) {
        super(str);
        this.mErrorCode = i;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }
}
