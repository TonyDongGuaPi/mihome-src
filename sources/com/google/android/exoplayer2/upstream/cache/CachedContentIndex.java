package com.google.android.exoplayer2.upstream.cache;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.coloros.mcssdk.c.a;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.AtomicFile;
import com.google.android.exoplayer2.util.ReusableBufferedOutputStream;
import com.google.android.exoplayer2.util.Util;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

class CachedContentIndex {
    public static final String FILE_NAME = "cached_content_index.exi";
    private static final int FLAG_ENCRYPTED_INDEX = 1;
    private static final int VERSION = 2;
    private final AtomicFile atomicFile;
    private ReusableBufferedOutputStream bufferedOutputStream;
    private boolean changed;
    private final Cipher cipher;
    private final boolean encrypt;
    private final SparseArray<String> idToKey;
    private final HashMap<String, CachedContent> keyToContent;
    private final SparseBooleanArray removedIds;
    private final SecretKeySpec secretKeySpec;

    public CachedContentIndex(File file) {
        this(file, (byte[]) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CachedContentIndex(File file, byte[] bArr) {
        this(file, bArr, bArr != null);
    }

    public CachedContentIndex(File file, byte[] bArr, boolean z) {
        this.encrypt = z;
        boolean z2 = true;
        if (bArr != null) {
            Assertions.checkArgument(bArr.length != 16 ? false : z2);
            try {
                this.cipher = getCipher();
                this.secretKeySpec = new SecretKeySpec(bArr, a.b);
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                throw new IllegalStateException(e);
            }
        } else {
            Assertions.checkState(!z);
            this.cipher = null;
            this.secretKeySpec = null;
        }
        this.keyToContent = new HashMap<>();
        this.idToKey = new SparseArray<>();
        this.removedIds = new SparseBooleanArray();
        this.atomicFile = new AtomicFile(new File(file, FILE_NAME));
    }

    public void load() {
        Assertions.checkState(!this.changed);
        if (!readFile()) {
            this.atomicFile.delete();
            this.keyToContent.clear();
            this.idToKey.clear();
        }
    }

    public void store() throws Cache.CacheException {
        if (this.changed) {
            writeFile();
            this.changed = false;
            int size = this.removedIds.size();
            for (int i = 0; i < size; i++) {
                this.idToKey.remove(this.removedIds.keyAt(i));
            }
            this.removedIds.clear();
        }
    }

    public CachedContent getOrAdd(String str) {
        CachedContent cachedContent = this.keyToContent.get(str);
        return cachedContent == null ? addNew(str) : cachedContent;
    }

    public CachedContent get(String str) {
        return this.keyToContent.get(str);
    }

    public Collection<CachedContent> getAll() {
        return this.keyToContent.values();
    }

    public int assignIdForKey(String str) {
        return getOrAdd(str).id;
    }

    public String getKeyForId(int i) {
        return this.idToKey.get(i);
    }

    public void maybeRemove(String str) {
        CachedContent cachedContent = this.keyToContent.get(str);
        if (cachedContent != null && cachedContent.isEmpty() && !cachedContent.isLocked()) {
            this.keyToContent.remove(str);
            this.changed = true;
            this.idToKey.put(cachedContent.id, (Object) null);
            this.removedIds.put(cachedContent.id, true);
        }
    }

    public void removeEmpty() {
        String[] strArr = new String[this.keyToContent.size()];
        this.keyToContent.keySet().toArray(strArr);
        for (String maybeRemove : strArr) {
            maybeRemove(maybeRemove);
        }
    }

    public Set<String> getKeys() {
        return this.keyToContent.keySet();
    }

    public void applyContentMetadataMutations(String str, ContentMetadataMutations contentMetadataMutations) {
        if (getOrAdd(str).applyMetadataMutations(contentMetadataMutations)) {
            this.changed = true;
        }
    }

    public ContentMetadata getContentMetadata(String str) {
        CachedContent cachedContent = get(str);
        return cachedContent != null ? cachedContent.getMetadata() : DefaultContentMetadata.EMPTY;
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x009e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean readFile() {
        /*
            r9 = this;
            r0 = 0
            r1 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x009b, all -> 0x0093 }
            com.google.android.exoplayer2.util.AtomicFile r3 = r9.atomicFile     // Catch:{ IOException -> 0x009b, all -> 0x0093 }
            java.io.InputStream r3 = r3.openRead()     // Catch:{ IOException -> 0x009b, all -> 0x0093 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x009b, all -> 0x0093 }
            java.io.DataInputStream r3 = new java.io.DataInputStream     // Catch:{ IOException -> 0x009b, all -> 0x0093 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x009b, all -> 0x0093 }
            int r1 = r3.readInt()     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            if (r1 < 0) goto L_0x008d
            r4 = 2
            if (r1 <= r4) goto L_0x001d
            goto L_0x008d
        L_0x001d:
            int r5 = r3.readInt()     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            r6 = 1
            r5 = r5 & r6
            if (r5 == 0) goto L_0x0055
            javax.crypto.Cipher r5 = r9.cipher     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            if (r5 != 0) goto L_0x002d
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r3)
            return r0
        L_0x002d:
            r5 = 16
            byte[] r5 = new byte[r5]     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            r3.readFully(r5)     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            javax.crypto.spec.IvParameterSpec r7 = new javax.crypto.spec.IvParameterSpec     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            r7.<init>(r5)     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            javax.crypto.Cipher r5 = r9.cipher     // Catch:{ InvalidAlgorithmParameterException | InvalidKeyException -> 0x004e }
            javax.crypto.spec.SecretKeySpec r8 = r9.secretKeySpec     // Catch:{ InvalidAlgorithmParameterException | InvalidKeyException -> 0x004e }
            r5.init(r4, r8, r7)     // Catch:{ InvalidAlgorithmParameterException | InvalidKeyException -> 0x004e }
            java.io.DataInputStream r4 = new java.io.DataInputStream     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            javax.crypto.CipherInputStream r5 = new javax.crypto.CipherInputStream     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            javax.crypto.Cipher r7 = r9.cipher     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            r5.<init>(r2, r7)     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            r3 = r4
            goto L_0x005b
        L_0x004e:
            r1 = move-exception
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            throw r2     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
        L_0x0055:
            boolean r2 = r9.encrypt     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            if (r2 == 0) goto L_0x005b
            r9.changed = r6     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
        L_0x005b:
            int r2 = r3.readInt()     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            r4 = 0
            r5 = 0
        L_0x0061:
            if (r4 >= r2) goto L_0x0072
            com.google.android.exoplayer2.upstream.cache.CachedContent r7 = com.google.android.exoplayer2.upstream.cache.CachedContent.readFromStream(r1, r3)     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            r9.add(r7)     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            int r7 = r7.headerHashCode(r1)     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            int r5 = r5 + r7
            int r4 = r4 + 1
            goto L_0x0061
        L_0x0072:
            int r1 = r3.readInt()     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            int r2 = r3.read()     // Catch:{ IOException -> 0x009c, all -> 0x0091 }
            r4 = -1
            if (r2 != r4) goto L_0x007f
            r2 = 1
            goto L_0x0080
        L_0x007f:
            r2 = 0
        L_0x0080:
            if (r1 != r5) goto L_0x0089
            if (r2 != 0) goto L_0x0085
            goto L_0x0089
        L_0x0085:
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r3)
            return r6
        L_0x0089:
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r3)
            return r0
        L_0x008d:
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r3)
            return r0
        L_0x0091:
            r0 = move-exception
            goto L_0x0095
        L_0x0093:
            r0 = move-exception
            r3 = r1
        L_0x0095:
            if (r3 == 0) goto L_0x009a
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r3)
        L_0x009a:
            throw r0
        L_0x009b:
            r3 = r1
        L_0x009c:
            if (r3 == 0) goto L_0x00a1
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r3)
        L_0x00a1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.cache.CachedContentIndex.readFile():boolean");
    }

    private void writeFile() throws Cache.CacheException {
        DataOutputStream dataOutputStream;
        IOException e;
        try {
            OutputStream startWrite = this.atomicFile.startWrite();
            if (this.bufferedOutputStream == null) {
                this.bufferedOutputStream = new ReusableBufferedOutputStream(startWrite);
            } else {
                this.bufferedOutputStream.reset(startWrite);
            }
            dataOutputStream = new DataOutputStream(this.bufferedOutputStream);
            try {
                dataOutputStream.writeInt(2);
                dataOutputStream.writeInt(this.encrypt ? 1 : 0);
                if (this.encrypt) {
                    byte[] bArr = new byte[16];
                    new Random().nextBytes(bArr);
                    dataOutputStream.write(bArr);
                    this.cipher.init(1, this.secretKeySpec, new IvParameterSpec(bArr));
                    dataOutputStream.flush();
                    dataOutputStream = new DataOutputStream(new CipherOutputStream(this.bufferedOutputStream, this.cipher));
                }
                dataOutputStream.writeInt(this.keyToContent.size());
                int i = 0;
                for (CachedContent next : this.keyToContent.values()) {
                    next.writeToStream(dataOutputStream);
                    i += next.headerHashCode(2);
                }
                dataOutputStream.writeInt(i);
                this.atomicFile.endWrite(dataOutputStream);
                Util.closeQuietly((Closeable) null);
            } catch (InvalidAlgorithmParameterException | InvalidKeyException e2) {
                throw new IllegalStateException(e2);
            } catch (IOException e3) {
                e = e3;
                try {
                    throw new Cache.CacheException((Throwable) e);
                } catch (Throwable th) {
                    th = th;
                    Util.closeQuietly((Closeable) dataOutputStream);
                    throw th;
                }
            }
        } catch (IOException e4) {
            IOException iOException = e4;
            dataOutputStream = null;
            e = iOException;
            throw new Cache.CacheException((Throwable) e);
        } catch (Throwable th2) {
            Throwable th3 = th2;
            dataOutputStream = null;
            th = th3;
            Util.closeQuietly((Closeable) dataOutputStream);
            throw th;
        }
    }

    private CachedContent addNew(String str) {
        CachedContent cachedContent = new CachedContent(getNewId(this.idToKey), str);
        add(cachedContent);
        this.changed = true;
        return cachedContent;
    }

    private void add(CachedContent cachedContent) {
        this.keyToContent.put(cachedContent.key, cachedContent);
        this.idToKey.put(cachedContent.id, cachedContent.key);
    }

    private static Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        if (Util.SDK_INT == 18) {
            try {
                return Cipher.getInstance("AES/CBC/PKCS5PADDING", "BC");
            } catch (Throwable unused) {
            }
        }
        return Cipher.getInstance("AES/CBC/PKCS5PADDING");
    }

    public static int getNewId(SparseArray<String> sparseArray) {
        int i;
        int size = sparseArray.size();
        if (size == 0) {
            i = 0;
        } else {
            i = sparseArray.keyAt(size - 1) + 1;
        }
        if (i < 0) {
            int i2 = 0;
            while (i < size && i == sparseArray.keyAt(i)) {
                i2 = i + 1;
            }
        }
        return i;
    }
}
