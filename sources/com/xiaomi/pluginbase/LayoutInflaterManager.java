package com.xiaomi.pluginbase;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.LayoutInflater;
import java.util.HashMap;
import java.util.Map;

public class LayoutInflaterManager {
    private static LayoutInflaterManager sInstance = new LayoutInflaterManager();
    Map<AssetManager, LayoutInflater> mLayoutInflaterCache = new HashMap();

    private LayoutInflaterManager() {
    }

    public static LayoutInflaterManager getInstance() {
        return sInstance;
    }

    public LayoutInflater getLayoutInflater(Context context) {
        LayoutInflater layoutInflater = this.mLayoutInflaterCache.get(context.getAssets());
        if (layoutInflater != null) {
            return layoutInflater;
        }
        LayoutInflater cloneInContext = LayoutInflater.from(context).cloneInContext(context);
        cloneInContext.setFactory2(new LayoutInflaterFactory());
        this.mLayoutInflaterCache.put(context.getAssets(), cloneInContext);
        return cloneInContext;
    }

    public void clear() {
        this.mLayoutInflaterCache.clear();
    }
}
