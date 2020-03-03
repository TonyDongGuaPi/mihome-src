package com.xiaomi.payment.ui;

import com.mibi.common.account.AccountLoader;

public interface EntryResultCallback {
    void onEntryFailed(int i, String str);

    void onEntrySuccess(AccountLoader accountLoader);
}
