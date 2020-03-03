package com.xiaomi.mimc.protobuf;

import java.io.IOException;
import java.nio.ByteBuffer;

public abstract class ByteOutput {
    public abstract void a(byte b) throws IOException;

    public abstract void a(ByteBuffer byteBuffer) throws IOException;

    public abstract void a(byte[] bArr, int i, int i2) throws IOException;

    public abstract void b(ByteBuffer byteBuffer) throws IOException;

    public abstract void b(byte[] bArr, int i, int i2) throws IOException;
}
