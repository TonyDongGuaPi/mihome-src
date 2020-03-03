package com.xiaomi.mishopsdk.plugin.Model;

import android.content.res.AssetManager;
import android.content.res.Resources;
import com.xiaomi.mishopsdk.plugin.lib.PluginInfo;
import com.xiaomi.shop2.plugin.PluginLayoutInflaterFactory;

public class PluginRuntimeEnv {
    public AssetManager pluginAsset;
    public ClassLoader pluginClassLoader;
    public PluginInfo pluginInfo;
    public PluginLayoutInflaterFactory pluginLayoutInflaterFactory = new PluginLayoutInflaterFactory();
    public Resources pluginRes;
    public Resources.Theme pluginTheme;

    public PluginRuntimeEnv(PluginInfo pluginInfo2, ClassLoader classLoader, Resources resources, AssetManager assetManager, Resources.Theme theme) {
        this.pluginInfo = pluginInfo2;
        this.pluginClassLoader = classLoader;
        this.pluginRes = resources;
        this.pluginAsset = assetManager;
        this.pluginTheme = theme;
    }
}
