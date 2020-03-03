package com.drew.lang;

import com.drew.lang.annotations.Nullable;
import java.util.HashMap;
import java.util.Map;

public class ByteTrie<T> {

    /* renamed from: a  reason: collision with root package name */
    private final ByteTrieNode<T> f5192a = new ByteTrieNode<>();
    private int b;

    static class ByteTrieNode<T> {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public final Map<Byte, ByteTrieNode<T>> f5193a = new HashMap();
        /* access modifiers changed from: private */
        public T b = null;

        ByteTrieNode() {
        }

        public void a(T t) {
            if (this.b == null) {
                this.b = t;
                return;
            }
            throw new RuntimeException("Value already set for this trie node");
        }
    }

    @Nullable
    public T a(byte[] bArr) {
        ByteTrieNode<T> byteTrieNode = this.f5192a;
        T a2 = byteTrieNode.b;
        for (byte valueOf : bArr) {
            byteTrieNode = (ByteTrieNode) byteTrieNode.f5193a.get(Byte.valueOf(valueOf));
            if (byteTrieNode == null) {
                break;
            }
            if (byteTrieNode.b != null) {
                a2 = byteTrieNode.b;
            }
        }
        return a2;
    }

    public void a(T t, byte[]... bArr) {
        ByteTrieNode<T> byteTrieNode = this.f5192a;
        int length = bArr.length;
        ByteTrieNode<T> byteTrieNode2 = byteTrieNode;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i2;
            for (byte b2 : bArr[i]) {
                ByteTrieNode<T> byteTrieNode3 = (ByteTrieNode) byteTrieNode2.f5193a.get(Byte.valueOf(b2));
                if (byteTrieNode3 == null) {
                    byteTrieNode3 = new ByteTrieNode<>();
                    byteTrieNode2.f5193a.put(Byte.valueOf(b2), byteTrieNode3);
                }
                byteTrieNode2 = byteTrieNode3;
                i3++;
            }
            i++;
            i2 = i3;
        }
        if (i2 != 0) {
            byteTrieNode2.a(t);
            this.b = Math.max(this.b, i2);
            return;
        }
        throw new IllegalArgumentException("Parts must contain at least one byte.");
    }

    public void a(T t) {
        this.f5192a.a(t);
    }

    public int a() {
        return this.b;
    }
}
