package com.mipay.core.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class BundleParser {
    private static final String MANIFEST_BUNDLE_ACTIVATOR = "Bundle-Activator";
    private static final String MANIFEST_BUNDLE_MANIFESTVERSION = "Bundle-ManifestVersion";
    private static final String MANIFEST_BUNDLE_NAME = "Bundle-Name";
    private static final String MANIFEST_BUNDLE_SYMBOLICNAME = "Bundle-SymbolicName";
    private static final String MANIFEST_BUNDLE_VERSION = "Bundle-Version";
    private static final String MANIFEST_REQUIRE_BUNDLE = "Require-Bundle";

    public void parse(InputStream inputStream, OSGiBundleImpl oSGiBundleImpl) {
        try {
            Attributes mainAttributes = new Manifest(inputStream).getMainAttributes();
            String value = mainAttributes.getValue(MANIFEST_BUNDLE_NAME);
            String value2 = mainAttributes.getValue(MANIFEST_BUNDLE_SYMBOLICNAME);
            String value3 = mainAttributes.getValue(MANIFEST_BUNDLE_ACTIVATOR);
            String value4 = mainAttributes.getValue(MANIFEST_REQUIRE_BUNDLE);
            oSGiBundleImpl.setName(value);
            oSGiBundleImpl.setSymbolicName(value2);
            oSGiBundleImpl.setActivator(value3);
            oSGiBundleImpl.setRequiredBundle(value4);
        } catch (IOException unused) {
        }
    }
}
