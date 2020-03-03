package com.lidroid.xutils;

import android.app.Activity;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.view.View;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.EventListenerManager;
import com.lidroid.xutils.view.ResLoader;
import com.lidroid.xutils.view.ViewFinder;
import com.lidroid.xutils.view.ViewInjectInfo;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.PreferenceInject;
import com.lidroid.xutils.view.annotation.ResInject;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.EventBase;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ViewUtils {
    private ViewUtils() {
    }

    public static void a(View view) {
        a((Object) view, new ViewFinder(view));
    }

    public static void a(Activity activity) {
        a((Object) activity, new ViewFinder(activity));
    }

    public static void a(PreferenceActivity preferenceActivity) {
        a((Object) preferenceActivity, new ViewFinder(preferenceActivity));
    }

    public static void a(Object obj, View view) {
        a(obj, new ViewFinder(view));
    }

    public static void a(Object obj, Activity activity) {
        a(obj, new ViewFinder(activity));
    }

    public static void a(Object obj, PreferenceGroup preferenceGroup) {
        a(obj, new ViewFinder(preferenceGroup));
    }

    public static void a(Object obj, PreferenceActivity preferenceActivity) {
        a(obj, new ViewFinder(preferenceActivity));
    }

    private static void a(Object obj, ViewFinder viewFinder) {
        Method[] methodArr;
        Method method;
        int i;
        Object obj2 = obj;
        ViewFinder viewFinder2 = viewFinder;
        Class<?> cls = obj.getClass();
        ContentView contentView = (ContentView) cls.getAnnotation(ContentView.class);
        boolean z = true;
        int i2 = 0;
        if (contentView != null) {
            try {
                cls.getMethod("setContentView", new Class[]{Integer.TYPE}).invoke(obj2, new Object[]{Integer.valueOf(contentView.value())});
            } catch (Throwable th) {
                LogUtils.b(th.getMessage(), th);
            }
        }
        Field[] declaredFields = cls.getDeclaredFields();
        if (declaredFields != null && declaredFields.length > 0) {
            for (Field field : declaredFields) {
                ViewInject viewInject = (ViewInject) field.getAnnotation(ViewInject.class);
                if (viewInject != null) {
                    try {
                        View a2 = viewFinder2.a(viewInject.value(), viewInject.parentId());
                        if (a2 != null) {
                            field.setAccessible(true);
                            field.set(obj2, a2);
                        }
                    } catch (Throwable th2) {
                        LogUtils.b(th2.getMessage(), th2);
                    }
                } else {
                    ResInject resInject = (ResInject) field.getAnnotation(ResInject.class);
                    if (resInject != null) {
                        try {
                            Object a3 = ResLoader.a(resInject.type(), viewFinder.a(), resInject.id());
                            if (a3 != null) {
                                field.setAccessible(true);
                                field.set(obj2, a3);
                            }
                        } catch (Throwable th3) {
                            LogUtils.b(th3.getMessage(), th3);
                        }
                    } else {
                        PreferenceInject preferenceInject = (PreferenceInject) field.getAnnotation(PreferenceInject.class);
                        if (preferenceInject != null) {
                            try {
                                Preference a4 = viewFinder2.a((CharSequence) preferenceInject.value());
                                if (a4 != null) {
                                    field.setAccessible(true);
                                    field.set(obj2, a4);
                                }
                            } catch (Throwable th4) {
                                LogUtils.b(th4.getMessage(), th4);
                            }
                        }
                    }
                }
            }
        }
        Method[] declaredMethods = cls.getDeclaredMethods();
        if (declaredMethods != null && declaredMethods.length > 0) {
            int length = declaredMethods.length;
            int i3 = 0;
            while (i3 < length) {
                Method method2 = declaredMethods[i3];
                Annotation[] declaredAnnotations = method2.getDeclaredAnnotations();
                if (declaredAnnotations != null && declaredAnnotations.length > 0) {
                    int length2 = declaredAnnotations.length;
                    int i4 = 0;
                    while (i4 < length2) {
                        Annotation annotation = declaredAnnotations[i4];
                        Class<? extends Annotation> annotationType = annotation.annotationType();
                        if (annotationType.getAnnotation(EventBase.class) != null) {
                            method2.setAccessible(z);
                            try {
                                Method declaredMethod = annotationType.getDeclaredMethod("value", new Class[i2]);
                                Object obj3 = null;
                                try {
                                    method = annotationType.getDeclaredMethod("parentId", new Class[i2]);
                                } catch (Throwable unused) {
                                    method = null;
                                }
                                Object invoke = declaredMethod.invoke(annotation, new Object[i2]);
                                if (method != null) {
                                    obj3 = method.invoke(annotation, new Object[i2]);
                                }
                                if (obj3 == null) {
                                    i = 0;
                                } else {
                                    i = Array.getLength(obj3);
                                }
                                int length3 = Array.getLength(invoke);
                                int i5 = 0;
                                while (i5 < length3) {
                                    ViewInjectInfo viewInjectInfo = new ViewInjectInfo();
                                    methodArr = declaredMethods;
                                    try {
                                        viewInjectInfo.f6376a = Array.get(invoke, i5);
                                        viewInjectInfo.b = i > i5 ? ((Integer) Array.get(obj3, i5)).intValue() : 0;
                                        EventListenerManager.a(viewFinder2, viewInjectInfo, annotation, obj2, method2);
                                        i5++;
                                        declaredMethods = methodArr;
                                    } catch (Throwable th5) {
                                        th = th5;
                                        LogUtils.b(th.getMessage(), th);
                                        i4++;
                                        declaredMethods = methodArr;
                                        z = true;
                                        i2 = 0;
                                    }
                                }
                            } catch (Throwable th6) {
                                th = th6;
                                methodArr = declaredMethods;
                                LogUtils.b(th.getMessage(), th);
                                i4++;
                                declaredMethods = methodArr;
                                z = true;
                                i2 = 0;
                            }
                        }
                        methodArr = declaredMethods;
                        i4++;
                        declaredMethods = methodArr;
                        z = true;
                        i2 = 0;
                    }
                }
                i3++;
                declaredMethods = declaredMethods;
                z = true;
                i2 = 0;
            }
        }
    }
}
