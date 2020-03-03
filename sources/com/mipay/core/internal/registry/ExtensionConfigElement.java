package com.mipay.core.internal.registry;

import android.text.TextUtils;
import com.mipay.core.runtime.Extension;
import com.mipay.core.runtime.ExtensionConfig;
import com.mipay.core.runtime.ExtensionExecutable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ExtensionConfigElement extends ExtensionRegistryElement implements ExtensionConfig {
    private final ArrayList<String> mAttributeNames = new ArrayList<>();
    private final HashMap<String, String> mAttributes = new HashMap<>();
    private String mName;
    private String mValue;

    protected ExtensionConfigElement(ExtensionRegistry extensionRegistry) {
        super(extensionRegistry);
    }

    /* access modifiers changed from: package-private */
    public void setName(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    /* access modifiers changed from: package-private */
    public void appendValue(String str) {
        if (this.mValue == null) {
            this.mValue = str;
            return;
        }
        this.mValue += str;
    }

    public String getValue() {
        return this.mValue;
    }

    public ExtensionExecutable createExtensionExecutable(String str) {
        String str2 = this.mAttributes.get(str);
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        int indexOf = str2.indexOf(58);
        if (indexOf != -1) {
            String trim = str2.substring(0, indexOf).trim();
            str2.substring(indexOf + 1).trim();
            str2 = trim;
        }
        int indexOf2 = str2.indexOf(47);
        if (indexOf2 != -1) {
            str2.substring(0, indexOf2).trim();
            str2 = str2.substring(indexOf2 + 1).trim();
        }
        try {
            try {
                return (ExtensionExecutable) Class.forName(str2).newInstance();
            } catch (Exception unused) {
                return null;
            }
        } catch (ClassNotFoundException unused2) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void addAttribute(String str, String str2) {
        if (!this.mAttributes.containsKey(str)) {
            this.mAttributes.put(str, str2);
            this.mAttributeNames.add(str);
        }
    }

    public String getAttribute(String str) {
        return this.mAttributes.get(str);
    }

    public List<String> getAttributeNames() {
        return Collections.unmodifiableList(this.mAttributeNames);
    }

    public List<ExtensionConfig> getChildren() {
        ArrayList arrayList = new ArrayList();
        Iterator<ExtensionRegistryElement> it = getChildElements().iterator();
        while (it.hasNext()) {
            arrayList.add((ExtensionConfig) it.next());
        }
        return arrayList;
    }

    public List<ExtensionConfig> getChildren(String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<ExtensionRegistryElement> it = getChildElements().iterator();
        while (it.hasNext()) {
            ExtensionConfig extensionConfig = (ExtensionConfig) it.next();
            if (TextUtils.equals(extensionConfig.getName(), str)) {
                arrayList.add(extensionConfig);
            }
        }
        return arrayList;
    }

    public Extension getDeclaringExtension() {
        ExtensionRegistryElement extensionRegistryElement = this;
        do {
            extensionRegistryElement = extensionRegistryElement.getParentElement();
            if (extensionRegistryElement == null) {
                return null;
            }
        } while (!(extensionRegistryElement instanceof ExtensionElement));
        return (ExtensionElement) extensionRegistryElement;
    }

    public Object getParent() {
        return getParentElement();
    }

    public String getNamespaceIdentifier() {
        Extension declaringExtension = getDeclaringExtension();
        if (declaringExtension != null) {
            return declaringExtension.getNamespaceIdentifier();
        }
        return null;
    }

    public String toString() {
        return this.mName;
    }
}
