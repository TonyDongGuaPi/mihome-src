package com.youpin.weex.app;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.taobao.weex.WXSDKInstance;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;

public final class WXAnalyzerDelegate {

    /* renamed from: a  reason: collision with root package name */
    private Object f2480a;

    public WXAnalyzerDelegate(@Nullable Context context) {
        if (context != null) {
            try {
                this.f2480a = Class.forName("com.taobao.weex.analyzer.WeexDevOptions").getDeclaredConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
            } catch (Exception unused) {
            }
        }
    }

    public void a(String str, int i, Runnable runnable) {
        if (this.f2480a != null && !TextUtils.isEmpty(str) && runnable != null) {
            try {
                this.f2480a.getClass().getDeclaredMethod("registerExtraOption", new Class[]{String.class, Integer.TYPE, Runnable.class}).invoke(this.f2480a, new Object[]{str, Integer.valueOf(i), runnable});
            } catch (Exception unused) {
            }
        }
    }

    public void a(MotionEvent motionEvent) {
        if (this.f2480a != null) {
            try {
                this.f2480a.getClass().getDeclaredMethod("onReceiveTouchEvent", new Class[]{MotionEvent.class}).invoke(this.f2480a, new Object[]{motionEvent});
            } catch (Exception unused) {
            }
        }
    }

    public void a() {
        if (this.f2480a != null) {
            try {
                this.f2480a.getClass().getDeclaredMethod("onCreate", new Class[0]).invoke(this.f2480a, new Object[0]);
            } catch (Exception unused) {
            }
        }
    }

    public void b() {
        if (this.f2480a != null) {
            try {
                this.f2480a.getClass().getDeclaredMethod("onStart", new Class[0]).invoke(this.f2480a, new Object[0]);
            } catch (Exception unused) {
            }
        }
    }

    public void c() {
        if (this.f2480a != null) {
            try {
                this.f2480a.getClass().getDeclaredMethod("onResume", new Class[0]).invoke(this.f2480a, new Object[0]);
            } catch (Exception unused) {
            }
        }
    }

    public void d() {
        if (this.f2480a != null) {
            try {
                this.f2480a.getClass().getDeclaredMethod("onPause", new Class[0]).invoke(this.f2480a, new Object[0]);
            } catch (Exception unused) {
            }
        }
    }

    public void e() {
        if (this.f2480a != null) {
            try {
                this.f2480a.getClass().getDeclaredMethod("onStop", new Class[0]).invoke(this.f2480a, new Object[0]);
            } catch (Exception unused) {
            }
        }
    }

    public void f() {
        if (this.f2480a != null) {
            try {
                this.f2480a.getClass().getDeclaredMethod(ActivityInfo.TYPE_STR_ONDESTROY, new Class[0]).invoke(this.f2480a, new Object[0]);
            } catch (Exception unused) {
            }
        }
    }

    public void a(@Nullable WXSDKInstance wXSDKInstance) {
        if (this.f2480a != null && wXSDKInstance != null) {
            try {
                this.f2480a.getClass().getDeclaredMethod("onWeexRenderSuccess", new Class[]{WXSDKInstance.class}).invoke(this.f2480a, new Object[]{wXSDKInstance});
            } catch (Exception unused) {
            }
        }
    }

    public boolean a(int i, KeyEvent keyEvent) {
        if (this.f2480a == null) {
            return false;
        }
        try {
            return ((Boolean) this.f2480a.getClass().getDeclaredMethod("onKeyUp", new Class[]{Integer.TYPE, KeyEvent.class}).invoke(this.f2480a, new Object[]{Integer.valueOf(i), keyEvent})).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public void a(WXSDKInstance wXSDKInstance, String str, String str2) {
        if (this.f2480a != null) {
            if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
                try {
                    this.f2480a.getClass().getDeclaredMethod("onException", new Class[]{WXSDKInstance.class, String.class, String.class}).invoke(this.f2480a, new Object[]{wXSDKInstance, str, str2});
                } catch (Exception unused) {
                }
            }
        }
    }

    public View a(WXSDKInstance wXSDKInstance, View view) {
        if (this.f2480a == null || wXSDKInstance == null || view == null) {
            return null;
        }
        try {
            return (View) this.f2480a.getClass().getDeclaredMethod("onWeexViewCreated", new Class[]{WXSDKInstance.class, View.class}).invoke(this.f2480a, new Object[]{wXSDKInstance, view});
        } catch (Exception unused) {
            return view;
        }
    }
}
