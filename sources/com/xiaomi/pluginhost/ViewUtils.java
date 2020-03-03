package com.xiaomi.pluginhost;

import android.app.Activity;
import android.os.IBinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import com.xiaomi.pluginhost.Reflector;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ViewUtils {
    public static void a(Activity activity) {
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

    public static List<View> a(View view) {
        ArrayList arrayList = new ArrayList();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                arrayList.add(childAt);
                arrayList.addAll(a(childAt));
            }
        }
        return arrayList;
    }
}
