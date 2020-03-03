package com.tencent.tinker.loader.app;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;

public interface ITinkerInlineFenceBridge {
    Context a(Context context);

    AssetManager a(AssetManager assetManager);

    Resources a(Resources resources);

    ClassLoader a(ClassLoader classLoader);

    Object a(String str, Object obj);

    void a();

    void a(int i);

    void a(Configuration configuration);

    void a(TinkerApplication tinkerApplication);

    void a(TinkerApplication tinkerApplication, Context context);

    void b();
}
