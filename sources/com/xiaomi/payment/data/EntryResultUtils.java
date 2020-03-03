package com.xiaomi.payment.data;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

public class EntryResultUtils {
    public static Intent a(int i, String str, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtra("code", i);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("message", str);
        }
        if (bundle != null) {
            intent.putExtra("result", bundle);
        }
        return intent;
    }
}
