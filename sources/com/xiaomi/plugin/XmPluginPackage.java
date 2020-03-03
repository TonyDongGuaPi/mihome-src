package com.xiaomi.plugin;

import android.content.res.AssetManager;
import android.content.res.Resources;
import dalvik.system.DexClassLoader;

public class XmPluginPackage {
    public AssetManager assetManager;
    public DexClassLoader classLoader;
    public int packageId;
    public String packagePath;
    public PackageRawInfo packageRawInfo;
    public Resources resources;
    public IXmPluginMessageReceiver xmPluginMessageReceiver;

    public XmPluginPackage(String str, PackageRawInfo packageRawInfo2, DexClassLoader dexClassLoader, AssetManager assetManager2, Resources resources2, IXmPluginMessageReceiver iXmPluginMessageReceiver) {
        this.packageId = packageRawInfo2.mPackageId;
        this.packagePath = str;
        this.packageRawInfo = packageRawInfo2;
        this.classLoader = dexClassLoader;
        this.assetManager = assetManager2;
        this.resources = resources2;
        this.xmPluginMessageReceiver = iXmPluginMessageReceiver;
    }

    public synchronized String getPackagePath() {
        return this.packagePath;
    }

    public synchronized DexClassLoader getClassLoader() {
        return this.classLoader;
    }

    public synchronized AssetManager getAssetManager() {
        return this.assetManager;
    }

    public synchronized Resources getResources() {
        return this.resources;
    }

    public synchronized IXmPluginMessageReceiver getMessageReceiver() {
        return this.xmPluginMessageReceiver;
    }
}
