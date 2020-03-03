package com.mipay.core.internal.registry;

import com.mipay.core.runtime.Extension;
import com.mipay.core.runtime.ExtensionPoint;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.io.File;
import java.util.List;

public class ExtensionPointElement extends ExtensionRegistryElement implements ExtensionPoint {
    private String mIdentifier;
    private String mLabel;
    private String mNamespace;
    private String mSchema;

    protected ExtensionPointElement(ExtensionRegistry extensionRegistry) {
        super(extensionRegistry);
    }

    /* access modifiers changed from: package-private */
    public void setLabel(String str) {
        this.mLabel = str;
    }

    public String getLabel() {
        return this.mLabel == null ? "" : this.mLabel;
    }

    public Extension getExtension(String str) {
        return this.mRegistry.getExtensionFromPoint(getUniqueIdentifier(), str);
    }

    public List<Extension> getExtensions() {
        return this.mRegistry.getExtensionsFromPoint(getUniqueIdentifier());
    }

    /* access modifiers changed from: package-private */
    public void setSchemaReference(String str) {
        this.mSchema = str;
    }

    public String getSchemaReference() {
        return this.mSchema == null ? "" : this.mSchema.replace(File.separatorChar, IOUtils.f15883a);
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

    public String toString() {
        return getUniqueIdentifier();
    }
}
