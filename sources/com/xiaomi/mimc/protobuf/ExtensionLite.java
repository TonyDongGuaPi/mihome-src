package com.xiaomi.mimc.protobuf;

import com.xiaomi.mimc.protobuf.MessageLite;
import com.xiaomi.mimc.protobuf.WireFormat;

public abstract class ExtensionLite<ContainingType extends MessageLite, Type> {
    public abstract int a();

    public abstract WireFormat.FieldType b();

    public abstract boolean c();

    public abstract Type d();

    public abstract MessageLite e();

    /* access modifiers changed from: package-private */
    public boolean f() {
        return true;
    }
}
