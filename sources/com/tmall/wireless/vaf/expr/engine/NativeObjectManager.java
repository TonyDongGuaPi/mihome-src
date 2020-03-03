package com.tmall.wireless.vaf.expr.engine;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.libra.expr.common.StringSupport;
import com.tmall.wireless.vaf.expr.engine.data.Data;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NativeObjectManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9349a = "NObjManager_TMTEST";
    private List<ViewBase> b = new ArrayList();
    private Map<String, Object> c = new ArrayMap();
    private StringSupport d;

    public void a(StringSupport stringSupport) {
        this.d = stringSupport;
    }

    public void a() {
        b();
        this.c = null;
        this.d = null;
    }

    public void b() {
        this.b.clear();
        this.c.clear();
    }

    public void a(ViewBase viewBase) {
        if (viewBase != null) {
            this.b.add(viewBase);
        }
    }

    public boolean a(String str, Object obj) {
        if (TextUtils.isEmpty(str) || obj == null) {
            Log.e(f9349a, "registerObject param invalidate");
            return false;
        }
        this.c.put(str, obj);
        return true;
    }

    public boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        this.c.remove(str);
        return true;
    }

    public void b(ViewBase viewBase) {
        if (viewBase != null) {
            this.b.remove(viewBase);
        }
    }

    public Object a(Object obj, int i) {
        Object obj2 = null;
        if (obj == null || i == 0) {
            Log.e(f9349a, "getProperty param invalidate");
            return null;
        }
        try {
            String a2 = this.d.a(i);
            Method method = obj.getClass().getMethod(String.format("get%c%s", new Object[]{Character.valueOf(Character.toUpperCase(a2.charAt(0))), a2.substring(1).toString()}), new Class[0]);
            if (method != null) {
                obj2 = method.invoke(obj, new Object[0]);
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        if (obj2 != null || !(obj instanceof ViewBase)) {
            return obj2;
        }
        return ((ViewBase) obj).g(i);
    }

    public boolean a(Object obj, int i, Data data) {
        boolean z = false;
        if (obj == null || i == 0 || data == null) {
            Log.e(f9349a, "setIntegerPropertyImp param invalidate");
            return false;
        }
        try {
            String a2 = this.d.a(i);
            String format = String.format("set%c%s", new Object[]{Character.valueOf(Character.toUpperCase(a2.charAt(0))), a2.substring(1).toString()});
            Method method = obj.getClass().getMethod(format, new Class[]{data.f.b()});
            if (method != null) {
                method.invoke(obj, new Object[]{data.f.c()});
                z = true;
            } else {
                Log.e(f9349a, "view:" + obj.getClass() + "  setIntegerPropertyImp find method failed:" + format);
            }
        } catch (InvocationTargetException e) {
            Log.e(f9349a, "view:" + obj.getClass() + "  setIntegerPropertyImp failed:" + e);
        } catch (IllegalAccessException e2) {
            Log.e(f9349a, "view:" + obj.getClass() + "  setIntegerPropertyImp failed:" + e2);
        } catch (NoSuchMethodException e3) {
            Log.e(f9349a, "view:" + obj.getClass() + "  setIntegerPropertyImp failed:" + e3);
        }
        if (z || !(obj instanceof ViewBase)) {
            return z;
        }
        return ((ViewBase) obj).a(i, (Object) data);
    }

    public Object b(String str) {
        return this.c.get(str);
    }

    public ViewBase c(String str) {
        if (!TextUtils.isEmpty(str)) {
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                ViewBase viewBase = this.b.get(i);
                if (TextUtils.equals(viewBase.N(), str)) {
                    return viewBase;
                }
            }
        }
        return null;
    }

    public ViewBase a(int i) {
        return c(this.d.a(i));
    }
}
