package com.bumptech.glide.load.engine.cache;

import android.support.annotation.NonNull;
import android.support.v4.util.Pools;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SafeKeyGenerator {

    /* renamed from: a  reason: collision with root package name */
    private final LruCache<Key, String> f4918a = new LruCache<>(1000);
    private final Pools.Pool<PoolableDigestContainer> b = FactoryPools.b(10, new FactoryPools.Factory<PoolableDigestContainer>() {
        /* renamed from: a */
        public PoolableDigestContainer b() {
            try {
                return new PoolableDigestContainer(MessageDigest.getInstance("SHA-256"));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    });

    public String a(Key key) {
        String c;
        synchronized (this.f4918a) {
            c = this.f4918a.c(key);
        }
        if (c == null) {
            c = b(key);
        }
        synchronized (this.f4918a) {
            this.f4918a.b(key, c);
        }
        return c;
    }

    private String b(Key key) {
        PoolableDigestContainer poolableDigestContainer = (PoolableDigestContainer) Preconditions.a(this.b.acquire());
        try {
            key.a(poolableDigestContainer.f4920a);
            return Util.a(poolableDigestContainer.f4920a.digest());
        } finally {
            this.b.release(poolableDigestContainer);
        }
    }

    private static final class PoolableDigestContainer implements FactoryPools.Poolable {

        /* renamed from: a  reason: collision with root package name */
        final MessageDigest f4920a;
        private final StateVerifier b = StateVerifier.a();

        PoolableDigestContainer(MessageDigest messageDigest) {
            this.f4920a = messageDigest;
        }

        @NonNull
        public StateVerifier d_() {
            return this.b;
        }
    }
}
