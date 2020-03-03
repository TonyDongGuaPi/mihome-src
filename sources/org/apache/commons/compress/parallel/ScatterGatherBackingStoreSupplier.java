package org.apache.commons.compress.parallel;

import java.io.IOException;

public interface ScatterGatherBackingStoreSupplier {
    ScatterGatherBackingStore a() throws IOException;
}
