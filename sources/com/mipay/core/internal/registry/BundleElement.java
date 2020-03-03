package com.mipay.core.internal.registry;

import com.mipay.core.runtime.OSGiBundle;

public class BundleElement extends ExtensionRegistryElement {
    private final OSGiBundle mBundle;

    protected BundleElement(ExtensionRegistry extensionRegistry, OSGiBundle oSGiBundle) {
        super(extensionRegistry);
        this.mBundle = oSGiBundle;
    }

    public String toString() {
        return "Bundle " + this.mBundle.getSymbolicName();
    }
}
