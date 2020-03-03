package com.xiaomi.mimc.protobuf;

import com.xiaomi.mimc.protobuf.AbstractMessageLite;
import com.xiaomi.mimc.protobuf.AbstractMessageLite.Builder;
import com.xiaomi.mimc.protobuf.ByteString;
import com.xiaomi.mimc.protobuf.MessageLite;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

public abstract class AbstractMessageLite<MessageType extends AbstractMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> implements MessageLite {
    protected int m = 0;

    public ByteString J() {
        try {
            ByteString.CodedBuilder newCodedBuilder = ByteString.newCodedBuilder(k());
            a(newCodedBuilder.b());
            return newCodedBuilder.a();
        } catch (IOException e) {
            throw new RuntimeException(a("ByteString"), e);
        }
    }

    public byte[] K() {
        try {
            byte[] bArr = new byte[k()];
            CodedOutputStream a2 = CodedOutputStream.a(bArr);
            a(a2);
            a2.c();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException(a("byte array"), e);
        }
    }

    public void a(OutputStream outputStream) throws IOException {
        CodedOutputStream a2 = CodedOutputStream.a(outputStream, CodedOutputStream.a(k()));
        a(a2);
        a2.a();
    }

    public void b(OutputStream outputStream) throws IOException {
        int k = k();
        CodedOutputStream a2 = CodedOutputStream.a(outputStream, CodedOutputStream.a(CodedOutputStream.s(k) + k));
        a2.r(k);
        a(a2);
        a2.a();
    }

    /* access modifiers changed from: package-private */
    public UninitializedMessageException L() {
        return new UninitializedMessageException((MessageLite) this);
    }

    private String a(String str) {
        return "Serializing " + getClass().getName() + " to a " + str + " threw an IOException (should never happen).";
    }

    protected static void b(ByteString byteString) throws IllegalArgumentException {
        if (!byteString.isValidUtf8()) {
            throw new IllegalArgumentException("Byte string is not UTF-8.");
        }
    }

    protected static <T> void a(Iterable<T> iterable, Collection<? super T> collection) {
        Builder.a(iterable, collection);
    }

    public static abstract class Builder<MessageType extends AbstractMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> implements MessageLite.Builder {
        /* renamed from: Q */
        public abstract BuilderType clone();

        /* access modifiers changed from: protected */
        public abstract BuilderType a(MessageType messagetype);

        /* renamed from: a */
        public abstract BuilderType b(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;

        /* renamed from: a */
        public BuilderType b(CodedInputStream codedInputStream) throws IOException {
            return b(codedInputStream, ExtensionRegistryLite.d());
        }

        /* renamed from: i */
        public BuilderType j(ByteString byteString) throws InvalidProtocolBufferException {
            try {
                CodedInputStream newCodedInput = byteString.newCodedInput();
                b(newCodedInput);
                newCodedInput.a(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException(a("ByteString"), e2);
            }
        }

        /* renamed from: a */
        public BuilderType b(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            try {
                CodedInputStream newCodedInput = byteString.newCodedInput();
                b(newCodedInput, extensionRegistryLite);
                newCodedInput.a(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException(a("ByteString"), e2);
            }
        }

        /* renamed from: a */
        public BuilderType b(byte[] bArr) throws InvalidProtocolBufferException {
            return b(bArr, 0, bArr.length);
        }

        /* renamed from: a */
        public BuilderType b(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
            try {
                CodedInputStream a2 = CodedInputStream.a(bArr, i, i2);
                b(a2);
                a2.a(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException(a("byte array"), e2);
            }
        }

        /* renamed from: a */
        public BuilderType b(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return b(bArr, 0, bArr.length, extensionRegistryLite);
        }

        /* renamed from: a */
        public BuilderType b(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            try {
                CodedInputStream a2 = CodedInputStream.a(bArr, i, i2);
                b(a2, extensionRegistryLite);
                a2.a(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException(a("byte array"), e2);
            }
        }

        /* renamed from: a */
        public BuilderType c(InputStream inputStream) throws IOException {
            CodedInputStream a2 = CodedInputStream.a(inputStream);
            b(a2);
            a2.a(0);
            return this;
        }

        /* renamed from: a */
        public BuilderType c(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            CodedInputStream a2 = CodedInputStream.a(inputStream);
            b(a2, extensionRegistryLite);
            a2.a(0);
            return this;
        }

        static final class LimitedInputStream extends FilterInputStream {

            /* renamed from: a  reason: collision with root package name */
            private int f11293a;

            LimitedInputStream(InputStream inputStream, int i) {
                super(inputStream);
                this.f11293a = i;
            }

            public int available() throws IOException {
                return Math.min(super.available(), this.f11293a);
            }

            public int read() throws IOException {
                if (this.f11293a <= 0) {
                    return -1;
                }
                int read = super.read();
                if (read >= 0) {
                    this.f11293a--;
                }
                return read;
            }

            public int read(byte[] bArr, int i, int i2) throws IOException {
                if (this.f11293a <= 0) {
                    return -1;
                }
                int read = super.read(bArr, i, Math.min(i2, this.f11293a));
                if (read >= 0) {
                    this.f11293a -= read;
                }
                return read;
            }

            public long skip(long j) throws IOException {
                long skip = super.skip(Math.min(j, (long) this.f11293a));
                if (skip >= 0) {
                    this.f11293a = (int) (((long) this.f11293a) - skip);
                }
                return skip;
            }
        }

        public boolean b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int read = inputStream.read();
            if (read == -1) {
                return false;
            }
            c((InputStream) new LimitedInputStream(inputStream, CodedInputStream.a(read, inputStream)), extensionRegistryLite);
            return true;
        }

        public boolean b(InputStream inputStream) throws IOException {
            return b(inputStream, ExtensionRegistryLite.d());
        }

        /* renamed from: a */
        public BuilderType c(MessageLite messageLite) {
            if (aa().getClass().isInstance(messageLite)) {
                return a((AbstractMessageLite) messageLite);
            }
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }

        private String a(String str) {
            return "Reading " + getClass().getName() + " from a " + str + " threw an IOException (should never happen).";
        }

        protected static UninitializedMessageException b(MessageLite messageLite) {
            return new UninitializedMessageException(messageLite);
        }

        protected static <T> void a(Iterable<T> iterable, Collection<? super T> collection) {
            if (iterable == null) {
                throw new NullPointerException();
            } else if (iterable instanceof LazyStringList) {
                a((Iterable<?>) ((LazyStringList) iterable).e());
                collection.addAll((Collection) iterable);
            } else if (iterable instanceof Collection) {
                a((Iterable<?>) iterable);
                collection.addAll((Collection) iterable);
            } else {
                for (T next : iterable) {
                    if (next != null) {
                        collection.add(next);
                    } else {
                        throw new NullPointerException();
                    }
                }
            }
        }

        private static void a(Iterable<?> iterable) {
            for (Object obj : iterable) {
                if (obj == null) {
                    throw new NullPointerException();
                }
            }
        }
    }
}
