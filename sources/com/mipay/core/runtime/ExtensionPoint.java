package com.mipay.core.runtime;

import java.util.List;

public interface ExtensionPoint {
    Extension getExtension(String str);

    List<Extension> getExtensions();

    String getLabel();

    String getNamespaceIdentifier();

    String getSchemaReference();

    String getSimpleIdentifier();

    String getUniqueIdentifier();
}
