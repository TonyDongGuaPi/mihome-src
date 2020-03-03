package com.mipay.core.internal.registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ExtensionRegistryElement {
    private List<ExtensionRegistryElement> mChildren = null;
    private ExtensionRegistryElement mParent = null;
    protected final ExtensionRegistry mRegistry;

    protected ExtensionRegistryElement(ExtensionRegistry extensionRegistry) {
        this.mRegistry = extensionRegistry;
        this.mChildren = new ArrayList();
    }

    /* access modifiers changed from: package-private */
    public void addChildElement(ExtensionRegistryElement extensionRegistryElement) {
        if (!this.mChildren.contains(extensionRegistryElement)) {
            this.mChildren.add(extensionRegistryElement);
        }
    }

    /* access modifiers changed from: protected */
    public final List<ExtensionRegistryElement> getChildElements() {
        return Collections.unmodifiableList(this.mChildren);
    }

    /* access modifiers changed from: package-private */
    public void setParentElement(ExtensionRegistryElement extensionRegistryElement) {
        this.mParent = extensionRegistryElement;
    }

    /* access modifiers changed from: protected */
    public final ExtensionRegistryElement getParentElement() {
        return this.mParent;
    }
}
