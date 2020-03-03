package com.xiaomi.mishopsdk.plugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.xiaomi.mishopsdk.R;
import com.xiaomi.mishopsdk.plugin.Model.PluginRuntimeEnv;
import com.xiaomi.mishopsdk.plugin.lib.PluginInfo;
import com.xiaomi.shop2.plugin.NativeLibManager;
import com.xiaomi.shop2.plugin.NativePluginDBUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class PluginInstallUtils {
    private static final Set<String> sPluginNotInAppSet = new HashSet<String>() {
        {
            add("10002");
            add("10003");
            add("10004");
            add("10005");
        }
    };

    public static PluginRuntimeEnv installRunEnv(PluginInfo pluginInfo, Context context) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        String restoredPath = pluginInfo.getRestoredPath();
        NativePluginDBUtils.getInstance().dencryptFile(pluginInfo.localPath, restoredPath);
        ClassLoader createPluginClassLoader = ClassLoaderFactory.createPluginClassLoader(restoredPath, NativeLibManager.getPluginNativePath(pluginInfo));
        AssetManager newInstance = AssetManager.class.newInstance();
        newInstance.getClass().getMethod("addAssetPath", new Class[]{String.class}).invoke(newInstance, new Object[]{restoredPath});
        Resources resources = new Resources(newInstance, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
        Resources.Theme newTheme = resources.newTheme();
        newTheme.applyStyle(R.style.mishopsdk_Theme_Light, true);
        return new PluginRuntimeEnv(pluginInfo, createPluginClassLoader, resources, newInstance, newTheme);
    }

    @Deprecated
    public static boolean pluginIsSupportedByApp(String str) {
        return !sPluginNotInAppSet.contains(str);
    }
}
