package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.taobao.weex.common.Constants;
import com.tencent.smtt.export.external.DexLoader;
import dalvik.system.DexClassLoader;

class bi {

    /* renamed from: a  reason: collision with root package name */
    protected DexLoader f9162a = null;

    public bi(DexLoader dexLoader) {
        this.f9162a = dexLoader;
    }

    public Object a(Context context) {
        return this.f9162a.newInstance("com.tencent.tbs.player.TbsPlayerProxy", new Class[]{Context.class, DexClassLoader.class}, context, this.f9162a.getClassLoader());
    }

    public void a(Object obj) {
        this.f9162a.invokeMethod(obj, "com.tencent.tbs.player.TbsPlayerProxy", "onUserStateChanged", new Class[0], new Object[0]);
    }

    public void a(Object obj, Activity activity, int i) {
        this.f9162a.invokeMethod(obj, "com.tencent.tbs.player.TbsPlayerProxy", "onActivity", new Class[]{Activity.class, Integer.TYPE}, activity, Integer.valueOf(i));
    }

    public boolean a(Object obj, Bundle bundle, FrameLayout frameLayout, Object obj2) {
        DexLoader dexLoader;
        String str;
        String str2;
        Class[] clsArr;
        Object[] objArr;
        if (obj2 != null) {
            dexLoader = this.f9162a;
            str = "com.tencent.tbs.player.TbsPlayerProxy";
            str2 = Constants.Value.PLAY;
            clsArr = new Class[]{Bundle.class, FrameLayout.class, Object.class};
            objArr = new Object[]{bundle, frameLayout, obj2};
        } else {
            dexLoader = this.f9162a;
            str = "com.tencent.tbs.player.TbsPlayerProxy";
            str2 = Constants.Value.PLAY;
            clsArr = new Class[]{Bundle.class, FrameLayout.class};
            objArr = new Object[]{bundle, frameLayout};
        }
        Object invokeMethod = dexLoader.invokeMethod(obj, str, str2, clsArr, objArr);
        if (invokeMethod instanceof Boolean) {
            return ((Boolean) invokeMethod).booleanValue();
        }
        return false;
    }
}
