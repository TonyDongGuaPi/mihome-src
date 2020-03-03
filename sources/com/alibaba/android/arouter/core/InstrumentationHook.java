package com.alibaba.android.arouter.core;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Field;

@Deprecated
public class InstrumentationHook extends Instrumentation {
    public Activity newActivity(ClassLoader classLoader, String str, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String[] stringArrayExtra;
        Class<?> loadClass = classLoader.loadClass(str);
        Object newInstance = loadClass.newInstance();
        if (ARouter.h() && (stringArrayExtra = intent.getStringArrayExtra(ARouter.b)) != null && stringArrayExtra.length > 0) {
            for (String str2 : stringArrayExtra) {
                Object obj = intent.getExtras().get(TextUtils.a(str2));
                if (obj != null) {
                    try {
                        Field declaredField = loadClass.getDeclaredField(TextUtils.a(str2));
                        declaredField.setAccessible(true);
                        declaredField.set(newInstance, obj);
                    } catch (Exception e) {
                        ARouter.c.error("ARouter::", "Inject values for activity error! [" + e.getMessage() + Operators.ARRAY_END_STR);
                    }
                }
            }
        }
        return (Activity) newInstance;
    }
}
