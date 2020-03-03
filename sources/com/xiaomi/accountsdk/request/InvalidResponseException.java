package com.xiaomi.accountsdk.request;

import com.xiaomi.accountsdk.account.exception.AccountException;

public class InvalidResponseException extends AccountException {
    private static final long serialVersionUID = 5544530065307643635L;
    public boolean isHtmlOr302;

    public InvalidResponseException(String str) {
        this(str, (Throwable) null);
    }

    public InvalidResponseException(int i, String str) {
        super(i, str);
        this.isHtmlOr302 = false;
    }

    public InvalidResponseException(String str, Throwable th) {
        this(str, th, false);
    }

    public InvalidResponseException(String str, Throwable th, boolean z) {
        super(-1, str, th);
        this.isHtmlOr302 = false;
        this.isHtmlOr302 = z;
    }
}
