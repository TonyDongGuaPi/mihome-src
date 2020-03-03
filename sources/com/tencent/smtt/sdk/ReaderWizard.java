package com.tencent.smtt.sdk;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import com.taobao.weex.common.Constants;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.sdk.TbsReaderView;

public class ReaderWizard {

    /* renamed from: a  reason: collision with root package name */
    private DexLoader f9081a;
    private TbsReaderView.ReaderCallback b;

    public ReaderWizard(TbsReaderView.ReaderCallback readerCallback) {
        this.f9081a = null;
        this.b = null;
        this.f9081a = a();
        this.b = readerCallback;
    }

    private static DexLoader a() {
        bh c = o.a(true).c();
        if (c != null) {
            return c.b();
        }
        return null;
    }

    public static Drawable getResDrawable(int i) {
        DexLoader a2 = a();
        if (a2 != null) {
            Object invokeStaticMethod = a2.invokeStaticMethod("com.tencent.tbs.reader.TbsReader", "getResDrawable", new Class[]{Integer.class}, Integer.valueOf(i));
            if (invokeStaticMethod instanceof Drawable) {
                return (Drawable) invokeStaticMethod;
            }
        }
        return null;
    }

    public static String getResString(int i) {
        DexLoader a2 = a();
        if (a2 == null) {
            return "";
        }
        Object invokeStaticMethod = a2.invokeStaticMethod("com.tencent.tbs.reader.TbsReader", "getResString", new Class[]{Integer.class}, Integer.valueOf(i));
        return invokeStaticMethod instanceof String ? (String) invokeStaticMethod : "";
    }

    public static boolean isSupportCurrentPlatform(Context context) {
        DexLoader a2 = a();
        if (a2 == null) {
            return false;
        }
        Object invokeStaticMethod = a2.invokeStaticMethod("com.tencent.tbs.reader.TbsReader", "isSupportCurrentPlatform", new Class[]{Context.class}, context);
        if (invokeStaticMethod instanceof Boolean) {
            return ((Boolean) invokeStaticMethod).booleanValue();
        }
        return false;
    }

    public static boolean isSupportExt(String str) {
        DexLoader a2 = a();
        if (a2 == null) {
            return false;
        }
        Object invokeStaticMethod = a2.invokeStaticMethod("com.tencent.tbs.reader.TbsReader", "isSupportExt", new Class[]{String.class}, str);
        if (invokeStaticMethod instanceof Boolean) {
            return ((Boolean) invokeStaticMethod).booleanValue();
        }
        return false;
    }

    public boolean checkPlugin(Object obj, Context context, String str, boolean z) {
        String str2;
        String str3;
        if (this.f9081a == null) {
            str2 = "ReaderWizard";
            str3 = "checkPlugin:Unexpect null object!";
        } else {
            Object invokeMethod = this.f9081a.invokeMethod(obj, "com.tencent.tbs.reader.TbsReader", "checkPlugin", new Class[]{Context.class, String.class, Boolean.class}, context, str, Boolean.valueOf(z));
            if (invokeMethod instanceof Boolean) {
                return ((Boolean) invokeMethod).booleanValue();
            }
            str2 = "ReaderWizard";
            str3 = "Unexpect return value type of call checkPlugin!";
        }
        Log.e(str2, str3);
        return false;
    }

    public void destroy(Object obj) {
        this.b = null;
        if (this.f9081a == null || obj == null) {
            Log.e("ReaderWizard", "destroy:Unexpect null object!");
            return;
        }
        this.f9081a.invokeMethod(obj, "com.tencent.tbs.reader.TbsReader", Constants.Event.SLOT_LIFECYCLE.DESTORY, new Class[0], new Object[0]);
    }

    public void doCommand(Object obj, Integer num, Object obj2, Object obj3) {
        if (this.f9081a == null) {
            Log.e("ReaderWizard", "doCommand:Unexpect null object!");
            return;
        }
        this.f9081a.invokeMethod(obj, "com.tencent.tbs.reader.TbsReader", "doCommand", new Class[]{Integer.class, Object.class, Object.class}, new Integer(num.intValue()), obj2, obj3);
    }

    public Object getTbsReader() {
        return this.f9081a.newInstance("com.tencent.tbs.reader.TbsReader", new Class[0], new Object[0]);
    }

    public boolean initTbsReader(Object obj, Context context) {
        String str;
        String str2;
        if (this.f9081a == null || obj == null) {
            str = "ReaderWizard";
            str2 = "initTbsReader:Unexpect null object!";
        } else {
            Object invokeMethod = this.f9081a.invokeMethod(obj, "com.tencent.tbs.reader.TbsReader", "init", new Class[]{Context.class, DexLoader.class, Object.class}, context, this.f9081a, this);
            if (invokeMethod instanceof Boolean) {
                return ((Boolean) invokeMethod).booleanValue();
            }
            str = "ReaderWizard";
            str2 = "Unexpect return value type of call initTbsReader!";
        }
        Log.e(str, str2);
        return false;
    }

    public void onCallBackAction(Integer num, Object obj, Object obj2) {
        if (this.b != null) {
            this.b.onCallBackAction(num, obj, obj2);
        }
    }

    public void onSizeChanged(Object obj, int i, int i2) {
        if (this.f9081a == null) {
            Log.e("ReaderWizard", "onSizeChanged:Unexpect null object!");
            return;
        }
        this.f9081a.invokeMethod(obj, "com.tencent.tbs.reader.TbsReader", "onSizeChanged", new Class[]{Integer.class, Integer.class}, new Integer(i), new Integer(i2));
    }

    public boolean openFile(Object obj, Context context, Bundle bundle, FrameLayout frameLayout) {
        String str;
        String str2;
        if (this.f9081a == null) {
            str = "ReaderWizard";
            str2 = "openFile:Unexpect null object!";
        } else {
            Object invokeMethod = this.f9081a.invokeMethod(obj, "com.tencent.tbs.reader.TbsReader", "openFile", new Class[]{Context.class, Bundle.class, FrameLayout.class}, context, bundle, frameLayout);
            if (invokeMethod instanceof Boolean) {
                return ((Boolean) invokeMethod).booleanValue();
            }
            str = "ReaderWizard";
            str2 = "Unexpect return value type of call openFile!";
        }
        Log.e(str, str2);
        return false;
    }

    public void userStatistics(Object obj, String str) {
        if (this.f9081a == null) {
            Log.e("ReaderWizard", "userStatistics:Unexpect null object!");
            return;
        }
        this.f9081a.invokeMethod(obj, "com.tencent.tbs.reader.TbsReader", "userStatistics", new Class[]{String.class}, str);
    }
}
