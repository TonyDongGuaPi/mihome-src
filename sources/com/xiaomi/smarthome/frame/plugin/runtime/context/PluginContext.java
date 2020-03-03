package com.xiaomi.smarthome.frame.plugin.runtime.context;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import com.xiaomi.plugin.core.XmPluginPackage;

public class PluginContext extends ContextWrapper {
    Resources.Theme mTheme;
    XmPluginPackage mXmPluginPackage;

    public PluginContext(Context context, XmPluginPackage xmPluginPackage) {
        super(context);
        this.mXmPluginPackage = xmPluginPackage;
    }

    public ClassLoader getClassLoader() {
        return this.mXmPluginPackage.classLoader;
    }

    public Resources getResources() {
        return this.mXmPluginPackage.resources;
    }

    public AssetManager getAssets() {
        return this.mXmPluginPackage.assetManager;
    }

    public Resources.Theme getTheme() {
        if (this.mTheme == null) {
            this.mTheme = this.mXmPluginPackage.resources.newTheme();
            try {
                this.mTheme.applyStyle(Build.VERSION.SDK_INT >= 14 ? 16974120 : 16973829, true);
            } catch (Exception unused) {
            }
        }
        return this.mTheme;
    }
}
