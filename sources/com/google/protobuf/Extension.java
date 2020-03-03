package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.MessageLite;
import com.google.protobuf.WireFormat;

public abstract class Extension<ContainingType extends MessageLite, Type> {

    protected enum ExtensionType {
        IMMUTABLE,
        MUTABLE,
        PROTO1
    }

    public enum MessageType {
        PROTO1,
        PROTO2
    }

    /* access modifiers changed from: protected */
    public abstract Object fromReflectionType(Object obj);

    public abstract Type getDefaultValue();

    public abstract Descriptors.FieldDescriptor getDescriptor();

    public abstract WireFormat.FieldType getLiteType();

    public abstract MessageLite getMessageDefaultInstance();

    public abstract int getNumber();

    public abstract boolean isRepeated();

    /* access modifiers changed from: protected */
    public abstract Object singularFromReflectionType(Object obj);

    /* access modifiers changed from: protected */
    public abstract Object singularToReflectionType(Object obj);

    /* access modifiers changed from: protected */
    public abstract Object toReflectionType(Object obj);

    /* access modifiers changed from: protected */
    public ExtensionType getExtensionType() {
        return ExtensionType.IMMUTABLE;
    }

    public MessageType getMessageType() {
        return MessageType.PROTO2;
    }
}
