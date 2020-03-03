package com.mipay.core.internal.registry;

import com.mipay.core.runtime.Extension;
import com.mipay.core.runtime.ExtensionConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExtensionElement extends ExtensionRegistryElement implements Extension {
    private String mExtensionPointIdentifier;
    private String mIdentifier;
    private String mLabel;
    private String mNamespace;

    protected ExtensionElement(ExtensionRegistry extensionRegistry) {
        super(extensionRegistry);
    }

    /* access modifiers changed from: package-private */
    public void setLabel(String str) {
        this.mLabel = str;
    }

    public String getLabel() {
        return this.mLabel == null ? "" : this.mLabel;
    }

    public List<ExtensionConfig> getConfigurationElements() {
        ArrayList arrayList = new ArrayList();
        Iterator<ExtensionRegistryElement> it = getChildElements().iterator();
        while (it.hasNext()) {
            arrayList.add((ExtensionConfig) it.next());
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public void setNamespaceIdentifier(String str) {
        this.mNamespace = str;
    }

    public String getNamespaceIdentifier() {
        return this.mNamespace;
    }

    /* access modifiers changed from: package-private */
    public void setSimpleIdentifier(String str) {
        this.mIdentifier = str;
    }

    public String getSimpleIdentifier() {
        return this.mIdentifier;
    }

    public String getUniqueIdentifier() {
        return this.mNamespace + '.' + this.mIdentifier;
    }

    /* access modifiers changed from: package-private */
    public void setExtensionPointUniqueIdentifier(String str) {
        this.mExtensionPointIdentifier = str;
    }

    public String getExtensionPointUniqueIdentifier() {
        return this.mExtensionPointIdentifier;
    }

    public String toString() {
        return getUniqueIdentifier() + " -> " + getExtensionPointUniqueIdentifier();
    }
}
