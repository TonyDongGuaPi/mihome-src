package com.xiaomi.pluginhost;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.xiaomi.plugin.R;
import com.xiaomi.plugin.XmPluginPackage;
import java.io.PrintWriter;
import java.io.StringWriter;

public class PluginErrorInfoActivity extends FragmentActivity {
    public static void showErrorInfo(Context context, XmPluginPackage xmPluginPackage, Throwable th) {
        if (PluginSettings.f12601a) {
            Intent intent = new Intent(context, PluginErrorInfoActivity.class);
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            printWriter.close();
            intent.putExtra("info", stringWriter.toString());
            intent.setFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(intent);
        } else if (PluginSettings.e != null) {
            PluginSettings.e.a(xmPluginPackage, th);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.plugin_error_info_activity);
        ((TextView) findViewById(R.id.info)).setText(getIntent().getStringExtra("info"));
    }
}
