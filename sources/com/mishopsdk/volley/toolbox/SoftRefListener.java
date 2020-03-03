package com.mishopsdk.volley.toolbox;

import com.mishopsdk.volley.Response;
import com.mishopsdk.volley.VolleyError;
import com.taobao.weex.el.parse.Operators;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;

public class SoftRefListener implements Response.Listener {
    private static final int FINAL = 16;
    private static final int GET_PARENT_TRY_TIMES_MAX = 5;
    private static final int SYNTHETIC = 4096;
    private static final int SYNTHETIC_AND_FINAL = 4112;
    private SoftReference<Object> mParent;
    private Field mParentFiled;
    private Response.Listener mRealListener;

    public SoftRefListener(Response.Listener listener) {
        if (listener != null) {
            this.mRealListener = listener;
            this.mParentFiled = getParentFiled(listener);
            this.mParent = new SoftReference<>(getParent());
            setParent((Object) null);
            return;
        }
        throw new RuntimeException("listener should not be null");
    }

    public void onStart() {
        if (isListenerValid()) {
            this.mRealListener.onStart();
        }
    }

    public void onSuccess(Object obj, boolean z) {
        if (isListenerValid()) {
            this.mRealListener.onSuccess(obj, z);
        }
    }

    public void onError(VolleyError volleyError) {
        if (isListenerValid()) {
            this.mRealListener.onError(volleyError);
        }
    }

    public void onFinish() {
        if (isListenerValid()) {
            this.mRealListener.onFinish();
        }
    }

    private boolean isListenerValid() {
        if (this.mParentFiled == null) {
            return true;
        }
        Object obj = this.mParent.get();
        if (obj == null) {
            return false;
        }
        return setParent(obj);
    }

    private Object getParent() {
        if (this.mParentFiled != null) {
            try {
                return this.mParentFiled.get(this.mRealListener);
            } catch (Exception e) {
                e.printStackTrace();
                this.mParentFiled = null;
            }
        }
        return null;
    }

    private boolean setParent(Object obj) {
        if (this.mParentFiled != null) {
            try {
                this.mParentFiled.set(this.mRealListener, obj);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                this.mParentFiled = null;
            }
        }
        return false;
    }

    private Field getParentFiled(Object obj) {
        Field field;
        Class<?> cls = obj.getClass();
        Field field2 = null;
        String str = "this$0";
        int i = 0;
        while (true) {
            try {
                field = cls.getDeclaredField(str);
                try {
                    field.setAccessible(true);
                    if ((field.getModifiers() & 4112) == 4112) {
                        break;
                    }
                    str = str + Operators.DOLLAR_STR;
                    i++;
                    if (i > 5) {
                        break;
                    }
                    field2 = field;
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    return field;
                }
            } catch (Exception e2) {
                e = e2;
                field = field2;
                e.printStackTrace();
                return field;
            }
        }
        return field;
    }
}
