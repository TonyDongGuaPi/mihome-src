package com.mipay.core.internal.registry;

import android.text.TextUtils;
import com.mipay.core.runtime.Extension;
import com.mipay.core.runtime.ExtensionPoint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ExtensionRegistry {
    private final HashMap<String, ExtensionElement> mExtensionElementMap = new HashMap<>();
    private final HashMap<String, ExtensionPointElement> mExtensionPointElementMap = new HashMap<>();
    private final HashMap<String, ArrayList<Extension>> mLinkMap = new HashMap<>();

    /* access modifiers changed from: package-private */
    public void addBundle(BundleElement bundleElement) {
        for (ExtensionRegistryElement next : bundleElement.getChildElements()) {
            if (next instanceof ExtensionElement) {
                addExtension((ExtensionElement) next);
            } else if (next instanceof ExtensionPointElement) {
                addExtensionPoint((ExtensionPointElement) next);
            }
        }
    }

    private void addExtension(ExtensionElement extensionElement) {
        String uniqueIdentifier = extensionElement.getUniqueIdentifier();
        if (!this.mExtensionElementMap.containsKey(uniqueIdentifier)) {
            this.mExtensionElementMap.put(uniqueIdentifier, extensionElement);
            String extensionPointUniqueIdentifier = extensionElement.getExtensionPointUniqueIdentifier();
            ArrayList arrayList = this.mLinkMap.get(extensionPointUniqueIdentifier);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.mLinkMap.put(extensionPointUniqueIdentifier, arrayList);
            }
            arrayList.add(extensionElement);
        }
    }

    private void addExtensionPoint(ExtensionPointElement extensionPointElement) {
        String uniqueIdentifier = extensionPointElement.getUniqueIdentifier();
        if (!this.mExtensionPointElementMap.containsKey(uniqueIdentifier)) {
            this.mExtensionPointElementMap.put(uniqueIdentifier, extensionPointElement);
        }
    }

    public Extension getExtension(String str) {
        if (!checkId(str)) {
            return null;
        }
        return this.mExtensionElementMap.get(str);
    }

    public ExtensionPoint getExtensionPoint(String str) {
        if (!checkId(str)) {
            return null;
        }
        return this.mExtensionPointElementMap.get(str);
    }

    public List<Extension> getExtensionsFromPoint(String str) {
        ArrayList arrayList;
        if (checkId(str) && this.mExtensionPointElementMap.containsKey(str) && (arrayList = this.mLinkMap.get(str)) != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return null;
    }

    public Extension getExtensionFromPoint(String str, String str2) {
        if (!checkId(str) || !checkId(str2)) {
            return null;
        }
        Extension extension = this.mExtensionElementMap.get(str2);
        if (TextUtils.equals(str, extension.getExtensionPointUniqueIdentifier())) {
            return extension;
        }
        return null;
    }

    private boolean checkId(String str) {
        return (str == null || str.lastIndexOf(46) == -1) ? false : true;
    }
}
