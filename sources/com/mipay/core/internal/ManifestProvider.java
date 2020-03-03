package com.mipay.core.internal;

import java.io.IOException;
import java.io.InputStream;

public interface ManifestProvider {
    InputStream openBundleManifest(String str) throws IOException;

    InputStream openExtensionManifest(String str) throws IOException;
}
