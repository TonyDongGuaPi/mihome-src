package com.xiaomi.mimc.protobuf;

import java.util.Collection;
import java.util.List;

public interface LazyStringList extends ProtocolStringList {
    void a(int i, ByteString byteString);

    void a(int i, byte[] bArr);

    void a(ByteString byteString);

    void a(LazyStringList lazyStringList);

    void a(byte[] bArr);

    boolean a(Collection<? extends ByteString> collection);

    boolean b(Collection<byte[]> collection);

    Object d(int i);

    List<?> e();

    ByteString f(int i);

    List<byte[]> f();

    byte[] g(int i);

    LazyStringList h();
}
