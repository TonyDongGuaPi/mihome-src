package com.drew.lang;

import com.drew.lang.annotations.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RandomAccessStreamReader extends RandomAccessReader {

    /* renamed from: a  reason: collision with root package name */
    public static final int f5200a = 2048;
    static final /* synthetic */ boolean b = (!RandomAccessStreamReader.class.desiredAssertionStatus());
    @NotNull
    private final InputStream c;
    private final int d;
    private final ArrayList<byte[]> e;
    private boolean f;
    private long g;

    public int a(int i) {
        return i;
    }

    public RandomAccessStreamReader(@NotNull InputStream inputStream) {
        this(inputStream, 2048, -1);
    }

    public RandomAccessStreamReader(@NotNull InputStream inputStream, int i) {
        this(inputStream, i, -1);
    }

    public RandomAccessStreamReader(@NotNull InputStream inputStream, int i, long j) {
        this.e = new ArrayList<>();
        if (inputStream == null) {
            throw new NullPointerException();
        } else if (i > 0) {
            this.d = i;
            this.c = inputStream;
            this.g = j;
        } else {
            throw new IllegalArgumentException("chunkLength must be greater than zero");
        }
    }

    public long a() throws IOException {
        if (this.g != -1) {
            return this.g;
        }
        b(Integer.MAX_VALUE, 1);
        if (b || this.f) {
            return this.g;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2) throws IOException {
        if (i < 0) {
            throw new BufferBoundsException(String.format("Attempt to read from buffer using a negative index (%d)", new Object[]{Integer.valueOf(i)}));
        } else if (i2 < 0) {
            throw new BufferBoundsException("Number of requested bytes must be zero or greater");
        } else if ((((long) i) + ((long) i2)) - 1 > 2147483647L) {
            throw new BufferBoundsException(String.format("Number of requested bytes summed with starting index exceed maximum range of signed 32 bit integers (requested index: %d, requested count: %d)", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        } else if (b(i, i2)) {
        } else {
            if (b || this.f) {
                throw new BufferBoundsException(i, i2, this.g);
            }
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, int i2) throws IOException {
        if (i < 0 || i2 < 0) {
            return false;
        }
        long j = (((long) i) + ((long) i2)) - 1;
        if (j > 2147483647L) {
            return false;
        }
        int i3 = (int) j;
        if (!this.f) {
            int i4 = i3 / this.d;
            while (i4 >= this.e.size()) {
                if (b || !this.f) {
                    byte[] bArr = new byte[this.d];
                    int i5 = 0;
                    while (!this.f && i5 != this.d) {
                        int read = this.c.read(bArr, i5, this.d - i5);
                        if (read == -1) {
                            this.f = true;
                            int size = (this.e.size() * this.d) + i5;
                            if (this.g == -1) {
                                this.g = (long) size;
                            } else if (this.g != ((long) size) && !b) {
                                throw new AssertionError();
                            }
                            if (((long) i3) >= this.g) {
                                this.e.add(bArr);
                                return false;
                            }
                        } else {
                            i5 += read;
                        }
                    }
                    this.e.add(bArr);
                } else {
                    throw new AssertionError();
                }
            }
            return true;
        } else if (((long) i3) < this.g) {
            return true;
        } else {
            return false;
        }
    }

    public byte b(int i) throws IOException {
        if (b || i >= 0) {
            int i2 = i / this.d;
            return this.e.get(i2)[i % this.d];
        }
        throw new AssertionError();
    }

    @NotNull
    public byte[] c(int i, int i2) throws IOException {
        a(i, i2);
        byte[] bArr = new byte[i2];
        int i3 = 0;
        while (i2 != 0) {
            int i4 = i % this.d;
            int min = Math.min(i2, this.d - i4);
            System.arraycopy(this.e.get(i / this.d), i4, bArr, i3, min);
            i2 -= min;
            i += min;
            i3 += min;
        }
        return bArr;
    }
}
