package com.xiaomi.smarthome.frame.plugin.debug;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.frame.plugin.runtime.crash.PluginCrashHandler;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.sdk.R;
import java.io.PrintWriter;
import java.io.StringWriter;

public class PluginErrorInfoActivity extends FragmentActivity {
    public static void showErrorInfo(Context context, XmPluginPackage xmPluginPackage, Throwable th) {
        final long j;
        final long j2;
        if (HostSetting.g || HostSetting.i) {
            Intent intent = new Intent(context, PluginErrorInfoActivity.class);
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            printWriter.close();
            intent.putExtra("info", stringWriter.toString());
            intent.setFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(intent);
            return;
        }
        if (xmPluginPackage != null) {
            long pluginId = xmPluginPackage.getPluginId();
            j = xmPluginPackage.getPackageId();
            j2 = pluginId;
        } else {
            j2 = 0;
            j = 0;
        }
        final Throwable th2 = th;
        AsyncTaskUtils.a(new AsyncTask<Object, Object, Object>() {
            /* access modifiers changed from: protected */
            public Object doInBackground(Object... objArr) {
                try {
                    PluginCrashHandler.handlePluginException(th2, j2, j);
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }, new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        DisplayUtils.e(this);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        setContentView(R.layout.plugin_error_info_activity);
        ((TextView) findViewById(R.id.info)).setText(getIntent().getStringExtra("info"));
    }
}
