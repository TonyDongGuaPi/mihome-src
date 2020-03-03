package com.mipay.core.runtime;

import java.util.List;

public interface Extension {
    List<ExtensionConfig> getConfigurationElements();

    String getExtensionPointUniqueIdentifier();

    String getLabel();

    String getNamespaceIdentifier();

    String getSimpleIdentifier();

    String getUniqueIdentifier();
}
