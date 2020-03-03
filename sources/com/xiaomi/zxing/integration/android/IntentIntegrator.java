package com.xiaomi.zxing.integration.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.xiaomi.mishopsdk.util.Constants;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntentIntegrator {

    /* renamed from: a  reason: collision with root package name */
    public static final int f1677a = 49374;
    public static final String b = "Install Barcode Scanner?";
    public static final String c = "This application requires Barcode Scanner. Would you like to install it?";
    public static final String d = "Yes";
    public static final String e = "No";
    public static final Collection<String> f = a("UPC_A", "UPC_E", "EAN_8", "EAN_13", "RSS_14");
    public static final Collection<String> g = a("UPC_A", "UPC_E", "EAN_8", "EAN_13", "CODE_39", "CODE_93", "CODE_128", "ITF", "RSS_14", "RSS_EXPANDED");
    public static final Collection<String> h = Collections.singleton("QR_CODE");
    public static final Collection<String> i = Collections.singleton("DATA_MATRIX");
    public static final Collection<String> j = null;
    public static final List<String> k = Collections.singletonList(n);
    public static final List<String> l = a(o, "com.srowen.bs.android.simple", n);
    /* access modifiers changed from: private */
    public static final String m = "IntentIntegrator";
    private static final String n = "com.xiaomi.zxing.client.android";
    private static final String o = "com.srowen.bs.android";
    /* access modifiers changed from: private */
    public final Activity p;
    /* access modifiers changed from: private */
    public final Fragment q;
    private String r;
    private String s;
    private String t;
    private String u;
    /* access modifiers changed from: private */
    public List<String> v;
    private final Map<String, Object> w = new HashMap(3);

    public IntentIntegrator(Activity activity) {
        this.p = activity;
        this.q = null;
        i();
    }

    public IntentIntegrator(Fragment fragment) {
        this.p = fragment.getActivity();
        this.q = fragment;
        i();
    }

    private void i() {
        this.r = b;
        this.s = c;
        this.t = d;
        this.u = e;
        this.v = l;
    }

    public String a() {
        return this.r;
    }

    public void a(String str) {
        this.r = str;
    }

    public void a(int i2) {
        this.r = this.p.getString(i2);
    }

    public String b() {
        return this.s;
    }

    public void b(String str) {
        this.s = str;
    }

    public void b(int i2) {
        this.s = this.p.getString(i2);
    }

    public String c() {
        return this.t;
    }

    public void c(String str) {
        this.t = str;
    }

    public void c(int i2) {
        this.t = this.p.getString(i2);
    }

    public String d() {
        return this.u;
    }

    public void d(String str) {
        this.u = str;
    }

    public void d(int i2) {
        this.u = this.p.getString(i2);
    }

    public Collection<String> e() {
        return this.v;
    }

    public final void a(List<String> list) {
        if (!list.isEmpty()) {
            this.v = list;
            return;
        }
        throw new IllegalArgumentException("No target applications");
    }

    public void e(String str) {
        this.v = Collections.singletonList(str);
    }

    public Map<String, ?> f() {
        return this.w;
    }

    public final void a(String str, Object obj) {
        this.w.put(str, obj);
    }

    public final AlertDialog g() {
        return a(j, -1);
    }

    public final AlertDialog e(int i2) {
        return a(j, i2);
    }

    public final AlertDialog a(Collection<String> collection) {
        return a(collection, -1);
    }

    public final AlertDialog a(Collection<String> collection, int i2) {
        Intent intent = new Intent("com.xiaomi.zxing.client.android.SCAN");
        intent.addCategory("android.intent.category.DEFAULT");
        if (collection != null) {
            StringBuilder sb = new StringBuilder();
            for (String next : collection) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(next);
            }
            intent.putExtra("SCAN_FORMATS", sb.toString());
        }
        if (i2 >= 0) {
            intent.putExtra("SCAN_CAMERA_ID", i2);
        }
        String a2 = a(intent);
        if (a2 == null) {
            return j();
        }
        intent.setPackage(a2);
        intent.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
        intent.addFlags(524288);
        b(intent);
        a(intent, (int) f1677a);
        return null;
    }

    /* access modifiers changed from: protected */
    public void a(Intent intent, int i2) {
        if (this.q == null) {
            this.p.startActivityForResult(intent, i2);
        } else {
            this.q.startActivityForResult(intent, i2);
        }
    }

    private String a(Intent intent) {
        List<ResolveInfo> queryIntentActivities = this.p.getPackageManager().queryIntentActivities(intent, 65536);
        if (queryIntentActivities == null) {
            return null;
        }
        for (String next : this.v) {
            if (a((Iterable<ResolveInfo>) queryIntentActivities, next)) {
                return next;
            }
        }
        return null;
    }

    private static boolean a(Iterable<ResolveInfo> iterable, String str) {
        for (ResolveInfo resolveInfo : iterable) {
            if (str.equals(resolveInfo.activityInfo.packageName)) {
                return true;
            }
        }
        return false;
    }

    private AlertDialog j() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.p);
        builder.setTitle(this.r);
        builder.setMessage(this.s);
        builder.setPositiveButton(this.t, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String str;
                if (IntentIntegrator.this.v.contains(IntentIntegrator.n)) {
                    str = IntentIntegrator.n;
                } else {
                    str = (String) IntentIntegrator.this.v.get(0);
                }
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str));
                try {
                    if (IntentIntegrator.this.q == null) {
                        IntentIntegrator.this.p.startActivity(intent);
                    } else {
                        IntentIntegrator.this.q.startActivity(intent);
                    }
                } catch (ActivityNotFoundException unused) {
                    String h = IntentIntegrator.m;
                    Log.w(h, "Google Play is not installed; cannot install " + str);
                }
            }
        });
        builder.setNegativeButton(this.u, (DialogInterface.OnClickListener) null);
        builder.setCancelable(true);
        return builder.show();
    }

    public static IntentResult a(int i2, int i3, Intent intent) {
        Integer num = null;
        if (i2 != 49374) {
            return null;
        }
        if (i3 != -1) {
            return new IntentResult();
        }
        String stringExtra = intent.getStringExtra("SCAN_RESULT");
        String stringExtra2 = intent.getStringExtra("SCAN_RESULT_FORMAT");
        byte[] byteArrayExtra = intent.getByteArrayExtra("SCAN_RESULT_BYTES");
        int intExtra = intent.getIntExtra("SCAN_RESULT_ORIENTATION", Integer.MIN_VALUE);
        if (intExtra != Integer.MIN_VALUE) {
            num = Integer.valueOf(intExtra);
        }
        return new IntentResult(stringExtra, stringExtra2, byteArrayExtra, num, intent.getStringExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL"));
    }

    public final AlertDialog a(CharSequence charSequence) {
        return a(charSequence, (CharSequence) "TEXT_TYPE");
    }

    public final AlertDialog a(CharSequence charSequence, CharSequence charSequence2) {
        Intent intent = new Intent();
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setAction("com.xiaomi.zxing.client.android.ENCODE");
        intent.putExtra("ENCODE_TYPE", charSequence2);
        intent.putExtra("ENCODE_DATA", charSequence);
        String a2 = a(intent);
        if (a2 == null) {
            return j();
        }
        intent.setPackage(a2);
        intent.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
        intent.addFlags(524288);
        b(intent);
        if (this.q == null) {
            this.p.startActivity(intent);
            return null;
        }
        this.q.startActivity(intent);
        return null;
    }

    private static List<String> a(String... strArr) {
        return Collections.unmodifiableList(Arrays.asList(strArr));
    }

    private void b(Intent intent) {
        for (Map.Entry next : this.w.entrySet()) {
            String str = (String) next.getKey();
            Object value = next.getValue();
            if (value instanceof Integer) {
                intent.putExtra(str, (Integer) value);
            } else if (value instanceof Long) {
                intent.putExtra(str, (Long) value);
            } else if (value instanceof Boolean) {
                intent.putExtra(str, (Boolean) value);
            } else if (value instanceof Double) {
                intent.putExtra(str, (Double) value);
            } else if (value instanceof Float) {
                intent.putExtra(str, (Float) value);
            } else if (value instanceof Bundle) {
                intent.putExtra(str, (Bundle) value);
            } else {
                intent.putExtra(str, value.toString());
            }
        }
    }
}
