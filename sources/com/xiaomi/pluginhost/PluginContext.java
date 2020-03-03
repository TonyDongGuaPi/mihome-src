package com.xiaomi.pluginhost;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import com.xiaomi.plugin.XmPluginPackage;

public class PluginContext extends ContextWrapper {

    /* renamed from: a  reason: collision with root package name */
    XmPluginPackage f12586a;
    Resources.Theme b;

    public PluginContext(Context context, XmPluginPackage xmPluginPackage) {
        super(context);
        this.f12586a = xmPluginPackage;
    }

    public ClassLoader getClassLoader() {
        return this.f12586a.classLoader;
    }

    public Resources getResources() {
        return this.f12586a.resources;
    }

    public AssetManager getAssets() {
        return this.f12586a.assetManager;
    }

    public Resources.Theme getTheme() {
        if (this.b == null) {
            this.b = this.f12586a.resources.newTheme();
            try {
                this.b.applyStyle(Build.VERSION.SDK_INT >= 14 ? 16974120 : 16973829, true);
            } catch (Exception unused) {
            }
        }
        return this.b;
    }
}
