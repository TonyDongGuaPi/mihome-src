package com.mipay.core.runtime;

import java.util.List;

public interface ExtensionConfig {
    ExtensionExecutable createExtensionExecutable(String str);

    String getAttribute(String str);

    List<String> getAttributeNames();

    List<ExtensionConfig> getChildren();

    List<ExtensionConfig> getChildren(String str);

    Extension getDeclaringExtension();

    String getName();

    String getNamespaceIdentifier();

    Object getParent();

    String getValue();
}
