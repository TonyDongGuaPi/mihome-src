package com.xiaomi.smarthome.preload;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.core.server.ReflectUtils;

public class PreloadLayoutInflater extends LayoutInflater {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f21153a = {"android.widget.", "android.webkit.", "android.app."};

    public PreloadLayoutInflater(Context context) {
        super(context);
    }

    public PreloadLayoutInflater(LayoutInflater layoutInflater, Context context) {
        super(layoutInflater, context);
    }

    public LayoutInflater cloneInContext(Context context) {
        return new PreloadLayoutInflater(this, context);
    }

    public View inflate(int i, ViewGroup viewGroup, boolean z) {
        if (viewGroup != null || z) {
            return super.inflate(i, viewGroup, z);
        }
        View a2 = PreloadInflateCache.a(i);
        if (a2 == null) {
            return super.inflate(i, viewGroup, z);
        }
        a(a2);
        return a2;
    }

    /* access modifiers changed from: protected */
    public View onCreateView(String str, AttributeSet attributeSet) throws ClassNotFoundException {
        String[] strArr = f21153a;
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            try {
                View createView = createView(str, strArr[i], attributeSet);
                if (createView != null) {
                    return createView;
                }
                i++;
            } catch (ClassNotFoundException unused) {
            }
        }
        return super.onCreateView(str, attributeSet);
    }

    private void a(View view) {
        Context context = getContext();
        if (context != null) {
            ReflectUtils.a((Object) view, "mContext", (Object) context);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    a(viewGroup.getChildAt(i));
                }
            }
        }
    }
}
