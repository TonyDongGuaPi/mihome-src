package com.amap.api.services.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public final class d {
    private static d e;

    /* renamed from: a  reason: collision with root package name */
    private List<String> f4393a;
    /* access modifiers changed from: private */
    public String b;
    /* access modifiers changed from: private */
    public final Context c;
    private final Handler d;

    public static d a(Context context) {
        if (e == null) {
            synchronized (d.class) {
                if (e == null) {
                    e = new d(context);
                }
            }
        }
        return e;
    }

    private d(Context context) {
        this.c = context.getApplicationContext();
        if (Looper.myLooper() == null) {
            this.d = new a(Looper.getMainLooper(), this);
        } else {
            this.d = new a(this);
        }
    }

    public void a(String str) {
        this.b = str;
    }

    public List<String> a() {
        if (this.f4393a != null && this.f4393a.size() > 0 && !TextUtils.isEmpty(this.f4393a.get(0))) {
            return this.f4393a;
        }
        this.f4393a = b();
        return this.f4393a;
    }

    public void b(String str) {
        if (this.f4393a != null) {
            this.f4393a.clear();
            this.f4393a.add(str);
        }
        a(str, 273);
    }

    private List<String> b() {
        String str = "";
        try {
            String string = Settings.System.getString(this.c.getContentResolver(), this.b);
            if (!TextUtils.isEmpty(string)) {
                str = i.c(string);
            }
        } catch (Exception unused) {
        }
        String str2 = "";
        String a2 = e.a(this.c, this.b);
        if (!TextUtils.isEmpty(a2)) {
            str2 = i.c(a2);
        }
        String str3 = "";
        String string2 = this.c.getSharedPreferences("SharedPreferenceAdiu", 0).getString(this.b, (String) null);
        if (!TextUtils.isEmpty(string2)) {
            str3 = i.c(string2);
        }
        ArrayList arrayList = new ArrayList(3);
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(str);
            int i = 16;
            if (!TextUtils.isEmpty(str2)) {
                if (!TextUtils.equals(str2, str)) {
                    arrayList.add(str2);
                } else {
                    i = 0;
                }
            }
            if (TextUtils.isEmpty(str3)) {
                i |= 256;
            } else if (!TextUtils.equals(str3, str)) {
                if (!TextUtils.equals(str3, str2)) {
                    arrayList.add(str3);
                }
                i |= 256;
            }
            if (i > 0) {
                this.d.sendMessage(this.d.obtainMessage(i, str));
            }
            return arrayList;
        } else if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            int i2 = 256;
            if (!TextUtils.isEmpty(str3)) {
                if (!TextUtils.equals(str3, str2)) {
                    arrayList.add(str3);
                } else {
                    i2 = 0;
                }
            }
            this.d.sendMessage(this.d.obtainMessage(i2 | 1, str2));
            return arrayList;
        } else if (TextUtils.isEmpty(str3)) {
            return null;
        } else {
            arrayList.add(str3);
            this.d.sendMessage(this.d.obtainMessage(17, str3));
            return arrayList;
        }
    }

    /* access modifiers changed from: private */
    public synchronized void a(final String str, final int i) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread() {
                public void run() {
                    String b2 = i.b(str);
                    if (!TextUtils.isEmpty(b2)) {
                        if ((i & 1) > 0) {
                            try {
                                if (Build.VERSION.SDK_INT >= 23) {
                                    Settings.System.putString(d.this.c.getContentResolver(), d.this.b, b2);
                                } else {
                                    Settings.System.putString(d.this.c.getContentResolver(), d.this.b, b2);
                                }
                            } catch (Exception unused) {
                            }
                        }
                        if ((i & 16) > 0) {
                            e.a(d.this.c, d.this.b, b2);
                        }
                        if ((i & 256) > 0) {
                            SharedPreferences.Editor edit = d.this.c.getSharedPreferences("SharedPreferenceAdiu", 0).edit();
                            edit.putString(d.this.b, b2);
                            if (Build.VERSION.SDK_INT >= 9) {
                                edit.apply();
                            } else {
                                edit.commit();
                            }
                        }
                    }
                }
            }.start();
        } else {
            String b2 = i.b(str);
            if (!TextUtils.isEmpty(b2)) {
                if ((i & 1) > 0) {
                    try {
                        if (Build.VERSION.SDK_INT >= 23) {
                            Settings.System.putString(this.c.getContentResolver(), this.b, b2);
                        } else {
                            Settings.System.putString(this.c.getContentResolver(), this.b, b2);
                        }
                    } catch (Exception unused) {
                    }
                }
                if ((i & 16) > 0) {
                    e.a(this.c, this.b, b2);
                }
                if ((i & 256) > 0) {
                    SharedPreferences.Editor edit = this.c.getSharedPreferences("SharedPreferenceAdiu", 0).edit();
                    edit.putString(this.b, b2);
                    if (Build.VERSION.SDK_INT >= 9) {
                        edit.apply();
                    } else {
                        edit.commit();
                    }
                }
            }
        }
    }

    private static final class a extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<d> f4395a;

        a(d dVar) {
            this.f4395a = new WeakReference<>(dVar);
        }

        a(Looper looper, d dVar) {
            super(looper);
            this.f4395a = new WeakReference<>(dVar);
        }

        public void handleMessage(Message message) {
            d dVar = (d) this.f4395a.get();
            if (dVar != null && message != null && message.obj != null) {
                dVar.a((String) message.obj, message.what);
            }
        }
    }
}
