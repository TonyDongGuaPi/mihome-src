package org.mp4parser.muxer.samples;

import com.google.android.exoplayer2.C;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractList;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import org.mp4parser.boxes.iso23001.part7.CencSampleAuxiliaryDataFormat;
import org.mp4parser.muxer.Sample;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.RangeStartMap;

public class CencEncryptingSampleList extends AbstractList<Sample> {

    /* renamed from: a  reason: collision with root package name */
    Cipher f4002a;
    List<CencSampleAuxiliaryDataFormat> b;
    RangeStartMap<Integer, SecretKey> c;
    List<Sample> d;
    /* access modifiers changed from: private */
    public final String e;

    public CencEncryptingSampleList(SecretKey secretKey, List<Sample> list, List<CencSampleAuxiliaryDataFormat> list2) {
        this(new RangeStartMap(0, secretKey), list, list2, C.CENC_TYPE_cenc);
    }

    public CencEncryptingSampleList(RangeStartMap<Integer, SecretKey> rangeStartMap, List<Sample> list, List<CencSampleAuxiliaryDataFormat> list2, String str) {
        this.c = new RangeStartMap<>();
        this.b = list2;
        this.c = rangeStartMap;
        this.e = str;
        this.d = list;
        try {
            if (C.CENC_TYPE_cenc.equals(str)) {
                this.f4002a = Cipher.getInstance("AES/CTR/NoPadding");
            } else if (C.CENC_TYPE_cbc1.equals(str)) {
                this.f4002a = Cipher.getInstance("AES/CBC/NoPadding");
            } else {
                throw new RuntimeException("Only cenc & cbc1 is supported as encryptionAlgo");
            }
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        } catch (NoSuchPaddingException e3) {
            throw new RuntimeException(e3);
        }
    }

    /* renamed from: a */
    public Sample get(int i) {
        Sample sample = this.d.get(i);
        if (this.c.get(Integer.valueOf(i)) == null) {
            return sample;
        }
        return new EncryptedSampleImpl(sample, this.b.get(i), this.f4002a, this.c.get(Integer.valueOf(i)));
    }

    /* access modifiers changed from: protected */
    public void a(byte[] bArr, SecretKey secretKey) {
        try {
            byte[] bArr2 = new byte[16];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            this.f4002a.init(1, secretKey, new IvParameterSpec(bArr2));
        } catch (InvalidAlgorithmParameterException e2) {
            throw new RuntimeException(e2);
        } catch (InvalidKeyException e3) {
            throw new RuntimeException(e3);
        }
    }

    public int size() {
        return this.d.size();
    }

    private class EncryptedSampleImpl implements Sample {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ boolean f4003a = (!CencEncryptingSampleList.class.desiredAssertionStatus());
        private final Sample c;
        private final CencSampleAuxiliaryDataFormat d;
        private final Cipher e;
        private final SecretKey f;

        private EncryptedSampleImpl(Sample sample, CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat, Cipher cipher, SecretKey secretKey) {
            this.c = sample;
            this.d = cencSampleAuxiliaryDataFormat;
            this.e = cipher;
            this.f = secretKey;
        }

        public void a(WritableByteChannel writableByteChannel) throws IOException {
            ByteBuffer byteBuffer = (ByteBuffer) this.c.b().rewind();
            CencEncryptingSampleList.this.a(this.d.f3934a, this.f);
            try {
                if (this.d.b == null || this.d.b.length <= 0) {
                    byte[] bArr = new byte[byteBuffer.limit()];
                    byteBuffer.get(bArr);
                    if (C.CENC_TYPE_cbc1.equals(CencEncryptingSampleList.this.e)) {
                        int length = (bArr.length / 16) * 16;
                        writableByteChannel.write(ByteBuffer.wrap(this.e.doFinal(bArr, 0, length)));
                        writableByteChannel.write(ByteBuffer.wrap(bArr, length, bArr.length - length));
                    } else if (C.CENC_TYPE_cenc.equals(CencEncryptingSampleList.this.e)) {
                        writableByteChannel.write(ByteBuffer.wrap(this.e.doFinal(bArr)));
                    }
                } else {
                    byte[] bArr2 = new byte[byteBuffer.limit()];
                    byteBuffer.get(bArr2);
                    int i = 0;
                    for (CencSampleAuxiliaryDataFormat.Pair pair : this.d.b) {
                        int a2 = i + pair.a();
                        if (pair.b() > 0) {
                            this.e.update(bArr2, a2, CastUtils.a(pair.b()), bArr2, a2);
                            i = (int) (((long) a2) + pair.b());
                        } else {
                            i = a2;
                        }
                    }
                    writableByteChannel.write(ByteBuffer.wrap(bArr2));
                }
                byteBuffer.rewind();
            } catch (IllegalBlockSizeException e2) {
                throw new RuntimeException(e2);
            } catch (BadPaddingException e3) {
                throw new RuntimeException(e3);
            } catch (ShortBufferException e4) {
                throw new RuntimeException(e4);
            }
        }

        public long a() {
            return this.c.a();
        }

        public ByteBuffer b() {
            ByteBuffer byteBuffer = (ByteBuffer) this.c.b().rewind();
            ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.limit());
            CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat = this.d;
            CencEncryptingSampleList.this.a(this.d.f3934a, this.f);
            try {
                if (cencSampleAuxiliaryDataFormat.b != null) {
                    for (CencSampleAuxiliaryDataFormat.Pair pair : cencSampleAuxiliaryDataFormat.b) {
                        byte[] bArr = new byte[pair.a()];
                        byteBuffer.get(bArr);
                        allocate.put(bArr);
                        if (pair.b() > 0) {
                            byte[] bArr2 = new byte[CastUtils.a(pair.b())];
                            byteBuffer.get(bArr2);
                            if (!f4003a) {
                                if (bArr2.length % 16 != 0) {
                                    throw new AssertionError();
                                }
                            }
                            byte[] update = this.e.update(bArr2);
                            if (!f4003a) {
                                if (update.length != bArr2.length) {
                                    throw new AssertionError();
                                }
                            }
                            allocate.put(update);
                        }
                    }
                } else {
                    byte[] bArr3 = new byte[byteBuffer.limit()];
                    byteBuffer.get(bArr3);
                    if (C.CENC_TYPE_cbc1.equals(CencEncryptingSampleList.this.e)) {
                        int length = (bArr3.length / 16) * 16;
                        allocate.put(this.e.doFinal(bArr3, 0, length));
                        allocate.put(bArr3, length, bArr3.length - length);
                    } else if (C.CENC_TYPE_cenc.equals(CencEncryptingSampleList.this.e)) {
                        allocate.put(this.e.doFinal(bArr3));
                    }
                }
                byteBuffer.rewind();
                allocate.rewind();
                return allocate;
            } catch (IllegalBlockSizeException e2) {
                throw new RuntimeException(e2);
            } catch (BadPaddingException e3) {
                throw new RuntimeException(e3);
            }
        }
    }
}
