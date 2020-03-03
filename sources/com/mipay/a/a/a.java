package com.mipay.a.a;

import a.a.b;
import a.a.c;
import a.a.d;
import a.a.e;
import a.a.f;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import com.mipay.sdk.app.Constants;
import com.mipay.sdk.permission.PermissionManager;
import com.xiaomi.youpin.share.ShareObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;
import org.json.JSONException;
import org.json.JSONObject;

public class a implements b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8105a = "ImageSelector";
    private static final int b = 80;
    private static final String c = "quality";
    private static final String d = "select";
    private static final int e = 1;
    private static final String f = "params";
    private static final String g = "image";
    private C0062a h;

    /* renamed from: com.mipay.a.a.a$a  reason: collision with other inner class name */
    public static class C0062a {

        /* renamed from: a  reason: collision with root package name */
        public int f8108a;
        public int b;
    }

    /* access modifiers changed from: private */
    public int a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return new JSONObject(str).getInt("quality");
            } catch (JSONException e2) {
                Log.d(f8105a, "getRequestImageQuality JSONException", e2);
            }
        }
        return 80;
    }

    private Uri a(Intent intent, Uri uri) {
        String action;
        boolean z = true;
        if (!(intent == null || intent.getData() == null || ((action = intent.getAction()) != null && action.equals("android.media.action.IMAGE_CAPTURE")))) {
            z = false;
        }
        return z ? uri : intent.getData();
    }

    /* access modifiers changed from: private */
    public String a(Context context, Intent intent, Uri uri, int i) {
        Bitmap bitmap;
        Uri a2 = a(intent, uri);
        if (a2 == null) {
            return null;
        }
        try {
            bitmap = b.a(context, a2, a(context).f8108a / 2, a(context).b / 2);
        } catch (IOException e2) {
            Log.e(f8105a, "compressBitmap failed", e2);
            bitmap = null;
        }
        if (bitmap == null) {
            return null;
        }
        String a3 = a(b.a(bitmap, i));
        bitmap.recycle();
        return a3;
    }

    public static final String a(byte[] bArr) {
        return Base64.encodeToString(bArr, 2);
    }

    /* access modifiers changed from: private */
    public JSONObject a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("params", str2);
            }
            if (TextUtils.isEmpty(str)) {
                return jSONObject;
            }
            jSONObject.put("image", str);
            return jSONObject;
        } catch (JSONException e2) {
            Log.e(f8105a, "makeResult failed", e2);
            return new JSONObject();
        }
    }

    /* access modifiers changed from: private */
    public void a(Activity activity, Uri uri, boolean z) {
        Intent intent = new Intent();
        intent.setType(ShareObject.d);
        intent.setAction("android.intent.action.GET_CONTENT");
        Intent intent2 = new Intent("android.intent.action.CHOOSER");
        if (z) {
            ArrayList arrayList = new ArrayList();
            Intent intent3 = new Intent();
            intent3.setAction("android.media.action.IMAGE_CAPTURE");
            intent3.putExtra(AgentOptions.k, uri);
            arrayList.add(intent3);
            intent2.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[arrayList.size()]));
        }
        intent2.putExtra("android.intent.extra.INTENT", intent);
        activity.startActivityForResult(intent2, 1);
    }

    private Uri b(String str) {
        try {
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            File file = new File(externalStoragePublicDirectory.getAbsolutePath() + File.separator + "captured_media");
            file.mkdirs();
            return Uri.fromFile(new File(file.getAbsolutePath() + File.separator + System.currentTimeMillis() + "." + str));
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public f a(e eVar) {
        return d.equals(eVar.a()) ? b(eVar) : new f(204, "no such action");
    }

    public C0062a a(Context context) {
        if (this.h != null) {
            return this.h;
        }
        this.h = new C0062a();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.h.b = displayMetrics.heightPixels;
        this.h.f8108a = displayMetrics.widthPixels;
        return this.h;
    }

    public void a(Map<String, String> map) {
    }

    public f b(e eVar) {
        if (!TextUtils.equals(Environment.getExternalStorageState(), "mounted")) {
            return new f(200, "storage not ready");
        }
        final Uri b2 = b("jpg");
        d d2 = eVar.d();
        final Activity a2 = d2.a();
        final d dVar = d2;
        final Activity activity = a2;
        final Uri uri = b2;
        final e eVar2 = eVar;
        d2.a(new c() {
            public void a(int i, int i2, Intent intent) {
                dVar.b(this);
                String a2 = a.this.a((Context) activity, intent, uri, a.this.a(eVar2.b()));
                eVar2.c().a((i2 != -1 || TextUtils.isEmpty(a2)) ? i2 == 0 ? new f(100, "cancel") : new f(200) : new f(a.this.a(a2, eVar2.b())));
            }
        });
        if (!PermissionManager.checkCameraPermission(a2, new PermissionManager.OnPermissionCancelListener() {
            public void OnCancel() {
                Toast.makeText(a2, Constants.getString(Constants.ID_CAMERA_TEXT), 0).show();
                a.this.a(a2, b2, false);
            }
        })) {
            return null;
        }
        a(a2, b2, true);
        return null;
    }

    public b.a c(e eVar) {
        return b.a.CALLBACK;
    }
}
