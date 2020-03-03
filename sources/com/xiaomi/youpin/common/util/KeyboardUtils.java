package com.xiaomi.youpin.common.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import com.xiaomi.youpin.common.util.Reflector;
import java.lang.reflect.Field;

public final class KeyboardUtils {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static int f23254a;
    private static ViewTreeObserver.OnGlobalLayoutListener b;
    /* access modifiers changed from: private */
    public static OnSoftInputChangedListener c;

    public interface OnSoftInputChangedListener {
        void a(int i);
    }

    private KeyboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void a(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        if (inputMethodManager != null) {
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus == null) {
                currentFocus = new View(activity);
                currentFocus.setFocusable(true);
                currentFocus.setFocusableInTouchMode(true);
                currentFocus.requestFocus();
            }
            inputMethodManager.showSoftInput(currentFocus, 2);
        }
    }

    public static void a(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.a().getSystemService("input_method");
        if (inputMethodManager != null) {
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            inputMethodManager.showSoftInput(view, 2);
        }
    }

    public static void b(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        if (inputMethodManager != null) {
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus == null) {
                currentFocus = new View(activity);
            }
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    public static void b(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.a().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void a() {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.a().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(2, 0);
        }
    }

    public static boolean c(Activity activity) {
        return a(activity, 200);
    }

    public static boolean a(Activity activity, int i) {
        return g(activity) >= i;
    }

    /* access modifiers changed from: private */
    public static int g(Activity activity) {
        View findViewById = activity.findViewById(16908290);
        Rect rect = new Rect();
        findViewById.getWindowVisibleDisplayFrame(rect);
        return findViewById.getBottom() - rect.bottom;
    }

    public static void a(final Activity activity, OnSoftInputChangedListener onSoftInputChangedListener) {
        if ((activity.getWindow().getAttributes().flags & 512) != 0) {
            activity.getWindow().clearFlags(512);
        }
        View findViewById = activity.findViewById(16908290);
        f23254a = g(activity);
        c = onSoftInputChangedListener;
        b = new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int f;
                if (KeyboardUtils.c != null && KeyboardUtils.f23254a != (f = KeyboardUtils.g(activity))) {
                    KeyboardUtils.c.a(f);
                    int unused = KeyboardUtils.f23254a = f;
                }
            }
        };
        findViewById.getViewTreeObserver().addOnGlobalLayoutListener(b);
    }

    @TargetApi(16)
    public static void d(Activity activity) {
        activity.findViewById(16908290).getViewTreeObserver().removeOnGlobalLayoutListener(b);
        c = null;
        b = null;
    }

    public static void a(Context context) {
        InputMethodManager inputMethodManager;
        if (context != null && (inputMethodManager = (InputMethodManager) Utils.a().getSystemService("input_method")) != null) {
            String[] strArr = {"mCurRootView", "mServedView", "mNextServedView"};
            for (int i = 0; i < 3; i++) {
                try {
                    Field declaredField = inputMethodManager.getClass().getDeclaredField(strArr[i]);
                    if (declaredField != null) {
                        if (!declaredField.isAccessible()) {
                            declaredField.setAccessible(true);
                        }
                        Object obj = declaredField.get(inputMethodManager);
                        if (obj == null) {
                            continue;
                        } else if (obj instanceof View) {
                            if (((View) obj).getContext() == context) {
                                declaredField.set(inputMethodManager, (Object) null);
                            } else {
                                return;
                            }
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    public static void b() {
        Log.i("KeyboardUtils", "Please refer to the following code.");
    }

    public static void e(Activity activity) {
        InputMethodManager inputMethodManager;
        if (activity != null && (inputMethodManager = (InputMethodManager) activity.getSystemService("input_method")) != null) {
            Reflector.a(inputMethodManager, "windowDismissed", new Reflector.TypedObject(activity.getWindow().getDecorView().getWindowToken(), IBinder.class));
            Reflector.a(inputMethodManager, "startGettingWindowFocus", new Reflector.TypedObject((Object) null, View.class));
            for (String declaredField : new String[]{"mCurRootView", "mServedView", "mNextServedView"}) {
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
}
