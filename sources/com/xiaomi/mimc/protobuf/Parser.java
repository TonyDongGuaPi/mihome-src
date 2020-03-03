package com.xiaomi.mimc.protobuf;

import java.io.InputStream;

public interface Parser<MessageType> {
    MessageType b(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    MessageType c(ByteString byteString) throws InvalidProtocolBufferException;

    MessageType c(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    MessageType c(CodedInputStream codedInputStream) throws InvalidProtocolBufferException;

    MessageType c(byte[] bArr) throws InvalidProtocolBufferException;

    MessageType c(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException;

    MessageType c(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    MessageType c(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    MessageType d(ByteString byteString) throws InvalidProtocolBufferException;

    MessageType d(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    MessageType d(CodedInputStream codedInputStream) throws InvalidProtocolBufferException;

    MessageType d(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    MessageType d(byte[] bArr) throws InvalidProtocolBufferException;

    MessageType d(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException;

    MessageType d(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    MessageType d(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    MessageType e(InputStream inputStream) throws InvalidProtocolBufferException;

    MessageType e(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    MessageType f(InputStream inputStream) throws InvalidProtocolBufferException;

    MessageType f(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    MessageType g(InputStream inputStream) throws InvalidProtocolBufferException;

    MessageType g(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

    MessageType h(InputStream inputStream) throws InvalidProtocolBufferException;

    MessageType h(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;
}
