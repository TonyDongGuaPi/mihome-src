package org.xutils.view;

import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import org.xutils.ViewInjector;
import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public final class ViewInjectorImpl implements ViewInjector {

    /* renamed from: a  reason: collision with root package name */
    private static final HashSet<Class<?>> f11945a = new HashSet<>();
    private static final Object b = new Object();
    private static volatile ViewInjectorImpl c;

    static {
        f11945a.add(Object.class);
        f11945a.add(Activity.class);
        f11945a.add(Fragment.class);
        try {
            f11945a.add(Class.forName("android.support.v4.app.Fragment"));
            f11945a.add(Class.forName("android.support.v4.app.FragmentActivity"));
        } catch (Throwable unused) {
        }
    }

    private ViewInjectorImpl() {
    }

    public static void a() {
        if (c == null) {
            synchronized (b) {
                if (c == null) {
                    c = new ViewInjectorImpl();
                }
            }
        }
        x.Ext.a((ViewInjector) c);
    }

    public void a(View view) {
        a((Object) view, view.getClass(), new ViewFinder(view));
    }

    public void a(Activity activity) {
        int value;
        Class<?> cls = activity.getClass();
        try {
            ContentView a2 = a(cls);
            if (a2 != null && (value = a2.value()) > 0) {
                cls.getMethod("setContentView", new Class[]{Integer.TYPE}).invoke(activity, new Object[]{Integer.valueOf(value)});
            }
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
        }
        a((Object) activity, cls, new ViewFinder(activity));
    }

    public void a(Object obj, View view) {
        a(obj, obj.getClass(), new ViewFinder(view));
    }

    public View a(Object obj, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        int value;
        Class<?> cls = obj.getClass();
        View view = null;
        try {
            ContentView a2 = a(cls);
            if (a2 != null && (value = a2.value()) > 0) {
                view = layoutInflater.inflate(value, viewGroup, false);
            }
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
        }
        a(obj, cls, new ViewFinder(view));
        return view;
    }

    private static ContentView a(Class<?> cls) {
        if (cls == null || f11945a.contains(cls)) {
            return null;
        }
        ContentView contentView = (ContentView) cls.getAnnotation(ContentView.class);
        return contentView == null ? a((Class<?>) cls.getSuperclass()) : contentView;
    }

    private static void a(Object obj, Class<?> cls, ViewFinder viewFinder) {
        Event event;
        int i;
        ViewInject viewInject;
        if (cls != null && !f11945a.contains(cls)) {
            a(obj, (Class<?>) cls.getSuperclass(), viewFinder);
            Field[] declaredFields = cls.getDeclaredFields();
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    Class<?> type = field.getType();
                    if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers()) && !type.isPrimitive() && !type.isArray() && (viewInject = (ViewInject) field.getAnnotation(ViewInject.class)) != null) {
                        try {
                            View a2 = viewFinder.a(viewInject.value(), viewInject.parentId());
                            if (a2 != null) {
                                field.setAccessible(true);
                                field.set(obj, a2);
                            } else {
                                throw new RuntimeException("Invalid @ViewInject for " + cls.getSimpleName() + "." + field.getName());
                            }
                        } catch (Throwable th) {
                            LogUtil.b(th.getMessage(), th);
                        }
                    }
                }
            }
            Method[] declaredMethods = cls.getDeclaredMethods();
            if (declaredMethods != null && declaredMethods.length > 0) {
                for (Method method : declaredMethods) {
                    if (!Modifier.isStatic(method.getModifiers()) && Modifier.isPrivate(method.getModifiers()) && (event = (Event) method.getAnnotation(Event.class)) != null) {
                        try {
                            int[] value = event.value();
                            int[] parentId = event.parentId();
                            if (parentId == null) {
                                i = 0;
                            } else {
                                i = parentId.length;
                            }
                            int i2 = 0;
                            while (i2 < value.length) {
                                int i3 = value[i2];
                                if (i3 > 0) {
                                    ViewInfo viewInfo = new ViewInfo();
                                    viewInfo.f11944a = i3;
                                    viewInfo.b = i > i2 ? parentId[i2] : 0;
                                    method.setAccessible(true);
                                    EventListenerManager.a(viewFinder, viewInfo, event, obj, method);
                                }
                                i2++;
                            }
                        } catch (Throwable th2) {
                            LogUtil.b(th2.getMessage(), th2);
                        }
                    }
                }
            }
        }
    }
}
