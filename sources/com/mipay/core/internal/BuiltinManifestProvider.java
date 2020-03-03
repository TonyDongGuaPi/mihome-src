package com.mipay.core.internal;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BuiltinManifestProvider implements ManifestProvider {
    private final AssetManager mAssetManager;

    public BuiltinManifestProvider(Context context) {
        this.mAssetManager = context.getAssets();
    }

    public InputStream openBundleManifest(String str) throws IOException {
        AssetManager assetManager = this.mAssetManager;
        return assetManager.open("manifest" + File.separator + str + File.separator + "MANIFEST.MF");
    }

    public InputStream openExtensionManifest(String str) throws IOException {
        AssetManager assetManager = this.mAssetManager;
        return assetManager.open("manifest" + File.separator + str + File.separator + "plugin.xml");
    }
}
