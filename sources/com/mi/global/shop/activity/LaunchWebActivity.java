package com.mi.global.shop.activity;

import android.content.Context;
import android.content.Intent;
import com.google.android.exoplayer2.C;
import com.mi.log.LogUtil;

public class LaunchWebActivity extends WebActivity {
    public static void startActivityStandard(Context context, String str) {
        LogUtil.b("LaunchWebActivity", "url:" + str);
        Intent intent = new Intent(context, LaunchWebActivity.class);
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        intent.putExtra("url", str);
        context.startActivity(intent);
    }
}
