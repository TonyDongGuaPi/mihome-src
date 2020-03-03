package com.mibi.common.account;

import android.text.TextUtils;
import com.xiaomi.youpin.UserMode;

public enum AccountLevel {
    SYSTEM("system"),
    GUEST(UserMode.b);
    
    private final String mLevelStr;

    private AccountLevel(String str) {
        this.mLevelStr = str;
    }

    public static AccountLevel get(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (AccountLevel accountLevel : values()) {
                if (str.equalsIgnoreCase(accountLevel.mLevelStr)) {
                    return accountLevel;
                }
            }
        }
        return SYSTEM;
    }
}
