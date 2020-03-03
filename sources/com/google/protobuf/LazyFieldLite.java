package com.google.protobuf;

public class LazyFieldLite {
    private ByteString bytes;
    private ExtensionRegistryLite extensionRegistry;
    private volatile boolean isDirty = false;
    protected volatile MessageLite value;

    public LazyFieldLite(ExtensionRegistryLite extensionRegistryLite, ByteString byteString) {
        this.extensionRegistry = extensionRegistryLite;
        this.bytes = byteString;
    }

    public LazyFieldLite() {
    }

    public static LazyFieldLite fromValue(MessageLite messageLite) {
        LazyFieldLite lazyFieldLite = new LazyFieldLite();
        lazyFieldLite.setValue(messageLite);
        return lazyFieldLite;
    }

    public boolean containsDefaultInstance() {
        return this.value == null && this.bytes == null;
    }

    public void clear() {
        this.bytes = null;
        this.value = null;
        this.extensionRegistry = null;
        this.isDirty = true;
    }

    public MessageLite getValue(MessageLite messageLite) {
        ensureInitialized(messageLite);
        return this.value;
    }

    public MessageLite setValue(MessageLite messageLite) {
        MessageLite messageLite2 = this.value;
        this.value = messageLite;
        this.bytes = null;
        this.isDirty = true;
        return messageLite2;
    }

    public void merge(LazyFieldLite lazyFieldLite) {
        if (!lazyFieldLite.containsDefaultInstance()) {
            if (this.bytes == null) {
                this.bytes = lazyFieldLite.bytes;
            } else {
                this.bytes.concat(lazyFieldLite.toByteString());
            }
            this.isDirty = false;
        }
    }

    public void setByteString(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        this.bytes = byteString;
        this.extensionRegistry = extensionRegistryLite;
        this.isDirty = false;
    }

    public ExtensionRegistryLite getExtensionRegistry() {
        return this.extensionRegistry;
    }

    public int getSerializedSize() {
        if (this.isDirty) {
            return this.value.getSerializedSize();
        }
        return this.bytes.size();
    }

    public ByteString toByteString() {
        if (!this.isDirty) {
            return this.bytes;
        }
        synchronized (this) {
            if (!this.isDirty) {
                ByteString byteString = this.bytes;
                return byteString;
            }
            if (this.value == null) {
                this.bytes = ByteString.EMPTY;
            } else {
                this.bytes = this.value.toByteString();
            }
            this.isDirty = false;
            ByteString byteString2 = this.bytes;
            return byteString2;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:9|10|(1:12)(1:13)|14|15|16) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0023 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void ensureInitialized(com.google.protobuf.MessageLite r3) {
        /*
            r2 = this;
            com.google.protobuf.MessageLite r0 = r2.value
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            monitor-enter(r2)
            com.google.protobuf.MessageLite r0 = r2.value     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x000c
            monitor-exit(r2)     // Catch:{ all -> 0x0025 }
            return
        L_0x000c:
            com.google.protobuf.ByteString r0 = r2.bytes     // Catch:{ IOException -> 0x0023 }
            if (r0 == 0) goto L_0x0021
            com.google.protobuf.Parser r3 = r3.getParserForType()     // Catch:{ IOException -> 0x0023 }
            com.google.protobuf.ByteString r0 = r2.bytes     // Catch:{ IOException -> 0x0023 }
            com.google.protobuf.ExtensionRegistryLite r1 = r2.extensionRegistry     // Catch:{ IOException -> 0x0023 }
            java.lang.Object r3 = r3.parseFrom((com.google.protobuf.ByteString) r0, (com.google.protobuf.ExtensionRegistryLite) r1)     // Catch:{ IOException -> 0x0023 }
            com.google.protobuf.MessageLite r3 = (com.google.protobuf.MessageLite) r3     // Catch:{ IOException -> 0x0023 }
            r2.value = r3     // Catch:{ IOException -> 0x0023 }
            goto L_0x0023
        L_0x0021:
            r2.value = r3     // Catch:{ IOException -> 0x0023 }
        L_0x0023:
            monitor-exit(r2)     // Catch:{ all -> 0x0025 }
            return
        L_0x0025:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0025 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.LazyFieldLite.ensureInitialized(com.google.protobuf.MessageLite):void");
    }
}
