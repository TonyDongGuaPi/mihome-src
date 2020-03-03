package com.xiaomi.youpin.login.ui.baseui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ImageView;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.util.Reflector;
import com.xiaomi.youpin.login.R;
import com.xiaomi.youpin.login.other.common.DisplayUtils;
import com.xiaomi.youpin.login.other.common.TitleBarUtil;
import com.xiaomi.youpin.login.other.log.LogUtils;
import java.lang.reflect.Field;
import java.util.List;

public abstract class BaseActivity extends FragmentActivity {
    public static final String FULL_REFERER_KEY = "full_referer_key";
    public static final String MIOT_ACTIVITY_FINISH_TAG = "miot_activit_finish_tag";
    public static final String SINGLE_REFERER_KEY = "single_referer_key";

    /* renamed from: a  reason: collision with root package name */
    private final BroadcastReceiver f23606a = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), "miot_activit_finish_tag") && BaseActivity.this.mIsPaused) {
                BaseActivity.this.finish();
            }
        }
    };
    private String b;
    private String c;
    protected boolean mIsPaused = false;
    int mOnResumeTimestamp;
    String mPageName;

    /* access modifiers changed from: protected */
    public String getPageName() {
        return null;
    }

    public static boolean isAppOnForeground(Context context) {
        String packageName = context.getPackageName();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.processName.startsWith(packageName) && (next.importance == 100 || next.importance == 200)) {
                LogUtils.e("CommonUtils", "Process:" + next.processName);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        DisplayUtils.b(this);
        super.onCreate(bundle);
        DarkModeCompat.b((Activity) this);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.a(this)) {
            setRequestedOrientation(1);
        }
        TitleBarUtil.a(getWindow());
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mIsPaused = false;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mIsPaused = true;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        try {
            fixInputMethodManager(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        clearAllActivityViews(this);
        super.onDestroy();
    }

    public static void fixInputMethodManager(Activity activity) {
        InputMethodManager inputMethodManager;
        if (activity != null && (inputMethodManager = (InputMethodManager) activity.getSystemService("input_method")) != null) {
            Reflector.a(inputMethodManager, "windowDismissed", new Reflector.TypedObject(activity.getWindow().getDecorView().getWindowToken(), IBinder.class));
            Reflector.a(inputMethodManager, "startGettingWindowFocus", new Reflector.TypedObject((Object) null, View.class));
            String[] strArr = {"mCurRootView", "mServedView", "mNextServedView"};
            for (String declaredField : strArr) {
                try {
                    Field declaredField2 = inputMethodManager.getClass().getDeclaredField(declaredField);
                    if (!declaredField2.isAccessible()) {
                        declaredField2.setAccessible(true);
                    }
                    Object obj = declaredField2.get(inputMethodManager);
                    if (obj != null && (obj instanceof View)) {
                        if (((View) obj).getContext() == activity) {
                            declaredField2.set(inputMethodManager, (Object) null);
                        } else {
                            return;
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    private static void a(View view) {
        if (view != null) {
            if (view instanceof ImageView) {
                ((ImageView) view).setImageBitmap((Bitmap) null);
            } else if (view instanceof WebView) {
                WebView webView = (WebView) view;
                webView.setTag((Object) null);
                webView.stopLoading();
                webView.clearHistory();
                try {
                    webView.removeAllViews();
                } catch (Exception unused) {
                }
                webView.clearView();
                try {
                    ((ViewGroup) webView.getParent()).removeView(webView);
                } catch (Exception unused2) {
                }
                webView.destroy();
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    a(viewGroup.getChildAt(i));
                }
                try {
                    viewGroup.removeAllViews();
                } catch (Exception unused3) {
                }
            }
        }
    }

    public static void clearAllActivityViews(Activity activity) {
        try {
            a(activity.getWindow().getDecorView().findViewById(16908290));
        } catch (Exception unused) {
        }
    }

    public String getSingleReferer() {
        String str = this.b;
        return str == null ? "" : str;
    }

    public String getFullReferer() {
        String str = this.c;
        return str == null ? "" : str;
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_right);
    }
}
