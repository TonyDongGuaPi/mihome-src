package com.xiaomi.smarthome.core.server.debug;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.sdk.R;

public class NetRequestWarningActivity extends Activity {
    public static final String KEY_ITEM = "item";
    String mContent = "";
    TextView mTextView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        DisplayUtils.e(this);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        setContentView(R.layout.net_request_warning_activity);
        this.mTextView = (TextView) findViewById(R.id.text);
        getParams(getIntent());
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getParams(intent);
    }

    /* access modifiers changed from: package-private */
    public void getParams(Intent intent) {
        String stringExtra = intent.getStringExtra(KEY_ITEM);
        if (!TextUtils.isEmpty(stringExtra)) {
            this.mContent += "\n\n" + stringExtra;
        }
        this.mTextView.setText(this.mContent);
    }
}
