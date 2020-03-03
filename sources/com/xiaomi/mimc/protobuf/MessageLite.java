package com.xiaomi.mimc.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface MessageLite extends MessageLiteOrBuilder {

    public interface Builder extends MessageLiteOrBuilder, Cloneable {
        Builder R();

        MessageLite Y();

        MessageLite Z();

        Builder ab();

        Builder b(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

        Builder b(CodedInputStream codedInputStream) throws IOException;

        Builder b(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;

        Builder b(byte[] bArr) throws InvalidProtocolBufferException;

        Builder b(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException;

        Builder b(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

        Builder b(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

        boolean b(InputStream inputStream) throws IOException;

        boolean b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;

        Builder c(MessageLite messageLite);

        Builder c(InputStream inputStream) throws IOException;

        Builder c(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;

        Builder j(ByteString byteString) throws InvalidProtocolBufferException;
    }

    ByteString J();

    byte[] K();

    Parser<? extends MessageLite> M();

    Builder Y();

    Builder Z();

    void a(CodedOutputStream codedOutputStream) throws IOException;

    void a(OutputStream outputStream) throws IOException;

    void b(OutputStream outputStream) throws IOException;

    int k();
}
