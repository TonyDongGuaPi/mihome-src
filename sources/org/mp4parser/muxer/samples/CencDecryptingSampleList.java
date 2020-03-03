package org.mp4parser.muxer.samples;

import com.google.android.exoplayer2.C;
import java.nio.ByteBuffer;
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
import javax.crypto.spec.IvParameterSpec;
import org.mp4parser.boxes.iso23001.part7.CencSampleAuxiliaryDataFormat;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.SampleImpl;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.RangeStartMap;

public class CencDecryptingSampleList extends AbstractList<Sample> {

    /* renamed from: a  reason: collision with root package name */
    List<CencSampleAuxiliaryDataFormat> f4001a;
    RangeStartMap<Integer, SecretKey> b;
    List<Sample> c;
    String d;

    public CencDecryptingSampleList(SecretKey secretKey, List<Sample> list, List<CencSampleAuxiliaryDataFormat> list2) {
        this(new RangeStartMap(0, secretKey), list, list2, C.CENC_TYPE_cenc);
    }

    public CencDecryptingSampleList(RangeStartMap<Integer, SecretKey> rangeStartMap, List<Sample> list, List<CencSampleAuxiliaryDataFormat> list2, String str) {
        this.b = new RangeStartMap<>();
        this.f4001a = list2;
        this.b = rangeStartMap;
        this.c = list;
        this.d = str;
    }

    /* access modifiers changed from: package-private */
    public Cipher a(SecretKey secretKey, byte[] bArr) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        try {
            if (C.CENC_TYPE_cenc.equals(this.d)) {
                Cipher instance = Cipher.getInstance("AES/CTR/NoPadding");
                instance.init(2, secretKey, new IvParameterSpec(bArr2));
                return instance;
            } else if (C.CENC_TYPE_cbc1.equals(this.d)) {
                Cipher instance2 = Cipher.getInstance("AES/CBC/NoPadding");
                instance2.init(2, secretKey, new IvParameterSpec(bArr2));
                return instance2;
            } else {
                throw new RuntimeException("Only cenc & cbc1 is supported as encryptionAlgo");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e2) {
            throw new RuntimeException(e2);
        } catch (InvalidAlgorithmParameterException e3) {
            throw new RuntimeException(e3);
        } catch (InvalidKeyException e4) {
            throw new RuntimeException(e4);
        }
    }

    /* renamed from: a */
    public Sample get(int i) {
        if (this.b.get(Integer.valueOf(i)) == null) {
            return this.c.get(i);
        }
        Sample sample = this.c.get(i);
        ByteBuffer b2 = sample.b();
        b2.rewind();
        ByteBuffer allocate = ByteBuffer.allocate(b2.limit());
        CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat = this.f4001a.get(i);
        Cipher a2 = a(this.b.get(Integer.valueOf(i)), cencSampleAuxiliaryDataFormat.f3934a);
        try {
            if (cencSampleAuxiliaryDataFormat.b == null || cencSampleAuxiliaryDataFormat.b.length <= 0) {
                byte[] bArr = new byte[b2.limit()];
                b2.get(bArr);
                if (C.CENC_TYPE_cbc1.equals(this.d)) {
                    int length = (bArr.length / 16) * 16;
                    allocate.put(a2.doFinal(bArr, 0, length));
                    allocate.put(bArr, length, bArr.length - length);
                } else if (C.CENC_TYPE_cenc.equals(this.d)) {
                    allocate.put(a2.doFinal(bArr));
                }
            } else {
                for (CencSampleAuxiliaryDataFormat.Pair pair : cencSampleAuxiliaryDataFormat.b) {
                    int a3 = pair.a();
                    int a4 = CastUtils.a(pair.b());
                    byte[] bArr2 = new byte[a3];
                    b2.get(bArr2);
                    allocate.put(bArr2);
                    if (a4 > 0) {
                        byte[] bArr3 = new byte[a4];
                        b2.get(bArr3);
                        allocate.put(a2.update(bArr3));
                    }
                }
                if (b2.remaining() > 0) {
                    System.err.println("Decrypted sample but still data remaining: " + sample.a());
                }
                allocate.put(a2.doFinal());
            }
            b2.rewind();
            allocate.rewind();
            return new SampleImpl(allocate);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e2) {
            throw new RuntimeException(e2);
        }
    }

    public int size() {
        return this.c.size();
    }
}
