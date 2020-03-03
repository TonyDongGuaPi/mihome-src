package com.mibi.common.account;

import android.accounts.AccountsException;
import android.content.Context;

public interface IGuestAccountProvider {
    AccountToken a(Context context, String str) throws AccountsException;

    AccountToken b(Context context, String str) throws AccountsException;
}
