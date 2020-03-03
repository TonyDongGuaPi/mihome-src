package com.xiaomi.smarthome.framework.plugin.mpk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.crash.PluginCrashHandler;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.page.BaseWhiteActivity;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorInfoActivity extends BaseWhiteActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.error_info_activity);
        ((TextView) findViewById(R.id.info)).setText(getIntent().getStringExtra("info"));
    }

    public static void showErrorInfo(Context context, XmPluginPackage xmPluginPackage, Throwable th) {
        long j;
        if (GlobalSetting.s || GlobalSetting.q) {
            MyLog.a(th);
            Intent intent = new Intent(context, ErrorInfoActivity.class);
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            printWriter.close();
            intent.putExtra("info", stringWriter.toString());
            intent.setFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(intent);
            return;
        }
        long j2 = 0;
        if (xmPluginPackage != null) {
            j2 = xmPluginPackage.getPluginId();
            j = xmPluginPackage.getPackageId();
        } else {
            j = 0;
        }
        PluginCrashHandler.a(th, j2, j);
    }
}
