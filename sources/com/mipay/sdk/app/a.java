package com.mipay.sdk.app;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import com.xiaomi.youpin.share.ShareObject;
import java.io.File;
import java.util.ArrayList;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;

class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8166a = "ImageSelector";
    private ValueCallback<Uri[]> b;
    private Uri c;
    private Context d;

    a(Context context) {
        this.d = context;
    }

    private Uri a(Intent intent) {
        String action;
        boolean z = true;
        if (intent != null && ((action = intent.getAction()) == null || !action.equals("android.media.action.IMAGE_CAPTURE"))) {
            z = false;
        }
        return z ? this.c : intent.getData();
    }

    private Uri a(String str) {
        try {
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            File file = new File(externalStoragePublicDirectory.getAbsolutePath() + File.separator + "captured_media");
            file.mkdirs();
            return Uri.fromFile(new File(file.getAbsolutePath() + File.separator + System.currentTimeMillis() + "." + str));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Uri[] b(int i, Intent intent) {
        Uri a2;
        if (i == 0 || (a2 = a(intent)) == null) {
            return null;
        }
        return new Uri[]{a2};
    }

    public void a(int i, Intent intent) {
        this.b.onReceiveValue(b(i, intent));
        this.b = null;
    }

    public void a(ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        if (this.b == null && TextUtils.equals(Environment.getExternalStorageState(), "mounted")) {
            this.b = valueCallback;
            this.c = a("jpg");
            ArrayList arrayList = new ArrayList();
            Intent intent = new Intent();
            intent.setAction("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(AgentOptions.k, this.c);
            arrayList.add(intent);
            Intent intent2 = new Intent();
            intent2.setType(ShareObject.d);
            intent2.setAction("android.intent.action.GET_CONTENT");
            Intent intent3 = new Intent("android.intent.action.CHOOSER");
            intent3.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[arrayList.size()]));
            intent3.putExtra("android.intent.extra.INTENT", intent2);
            try {
                if (this.d instanceof Activity) {
                    ((Activity) this.d).startActivityForResult(intent3, 100000);
                }
            } catch (ActivityNotFoundException e) {
                this.b = null;
                Log.e(f8166a, "open activity failed", e);
            }
        }
    }
}
