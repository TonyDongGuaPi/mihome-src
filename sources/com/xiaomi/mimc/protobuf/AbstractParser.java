package com.xiaomi.mimc.protobuf;

import com.xiaomi.mimc.protobuf.AbstractMessageLite;
import com.xiaomi.mimc.protobuf.MessageLite;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractParser<MessageType extends MessageLite> implements Parser<MessageType> {

    /* renamed from: a  reason: collision with root package name */
    private static final ExtensionRegistryLite f11294a = ExtensionRegistryLite.d();

    private UninitializedMessageException a(MessageType messagetype) {
        if (messagetype instanceof AbstractMessageLite) {
            return ((AbstractMessageLite) messagetype).L();
        }
        return new UninitializedMessageException((MessageLite) messagetype);
    }

    private MessageType b(MessageType messagetype) throws InvalidProtocolBufferException {
        if (messagetype == null || messagetype.h_()) {
            return messagetype;
        }
        throw a(messagetype).asInvalidProtocolBufferException().setUnfinishedMessage(messagetype);
    }

    /* renamed from: a */
    public MessageType c(CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
        return (MessageLite) d(codedInputStream, f11294a);
    }

    /* renamed from: a */
    public MessageType b(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return b((MessageLite) d(codedInputStream, extensionRegistryLite));
    }

    /* renamed from: b */
    public MessageType d(CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
        return b(codedInputStream, f11294a);
    }

    /* renamed from: a */
    public MessageType c(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        MessageType messagetype;
        try {
            CodedInputStream newCodedInput = byteString.newCodedInput();
            messagetype = (MessageLite) d(newCodedInput, extensionRegistryLite);
            newCodedInput.a(0);
            return messagetype;
        } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(messagetype);
        } catch (InvalidProtocolBufferException e2) {
            throw e2;
        }
    }

    /* renamed from: a */
    public MessageType c(ByteString byteString) throws InvalidProtocolBufferException {
        return c(byteString, f11294a);
    }

    /* renamed from: b */
    public MessageType d(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return b(c(byteString, extensionRegistryLite));
    }

    /* renamed from: b */
    public MessageType d(ByteString byteString) throws InvalidProtocolBufferException {
        return d(byteString, f11294a);
    }

    /* renamed from: a */
    public MessageType c(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        MessageType messagetype;
        try {
            CodedInputStream a2 = CodedInputStream.a(bArr, i, i2);
            messagetype = (MessageLite) d(a2, extensionRegistryLite);
            a2.a(0);
            return messagetype;
        } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(messagetype);
        } catch (InvalidProtocolBufferException e2) {
            throw e2;
        }
    }

    /* renamed from: a */
    public MessageType c(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
        return c(bArr, i, i2, f11294a);
    }

    /* renamed from: a */
    public MessageType c(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return c(bArr, 0, bArr.length, extensionRegistryLite);
    }

    /* renamed from: a */
    public MessageType c(byte[] bArr) throws InvalidProtocolBufferException {
        return c(bArr, 0, bArr.length, f11294a);
    }

    /* renamed from: b */
    public MessageType d(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return b(c(bArr, i, i2, extensionRegistryLite));
    }

    /* renamed from: b */
    public MessageType d(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
        return d(bArr, i, i2, f11294a);
    }

    /* renamed from: b */
    public MessageType d(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return d(bArr, 0, bArr.length, extensionRegistryLite);
    }

    /* renamed from: b */
    public MessageType d(byte[] bArr) throws InvalidProtocolBufferException {
        return d(bArr, f11294a);
    }

    /* renamed from: a */
    public MessageType g(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        CodedInputStream a2 = CodedInputStream.a(inputStream);
        MessageType messagetype = (MessageLite) d(a2, extensionRegistryLite);
        try {
            a2.a(0);
            return messagetype;
        } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(messagetype);
        }
    }

    /* renamed from: a */
    public MessageType g(InputStream inputStream) throws InvalidProtocolBufferException {
        return g(inputStream, f11294a);
    }

    /* renamed from: b */
    public MessageType h(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return b(g(inputStream, extensionRegistryLite));
    }

    /* renamed from: b */
    public MessageType h(InputStream inputStream) throws InvalidProtocolBufferException {
        return h(inputStream, f11294a);
    }

    /* renamed from: c */
    public MessageType e(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        try {
            int read = inputStream.read();
            if (read == -1) {
                return null;
            }
            return g((InputStream) new AbstractMessageLite.Builder.LimitedInputStream(inputStream, CodedInputStream.a(read, inputStream)), extensionRegistryLite);
        } catch (IOException e) {
            throw new InvalidProtocolBufferException(e.getMessage());
        }
    }

    /* renamed from: c */
    public MessageType e(InputStream inputStream) throws InvalidProtocolBufferException {
        return e(inputStream, f11294a);
    }

    /* renamed from: d */
    public MessageType f(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return b(e(inputStream, extensionRegistryLite));
    }

    /* renamed from: d */
    public MessageType f(InputStream inputStream) throws InvalidProtocolBufferException {
        return f(inputStream, f11294a);
    }
}
