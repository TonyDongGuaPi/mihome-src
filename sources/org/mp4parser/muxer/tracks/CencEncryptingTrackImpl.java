package org.mp4parser.muxer.tracks;

import com.google.android.exoplayer2.C;
import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.crypto.SecretKey;
import org.mp4parser.Box;
import org.mp4parser.IsoFile;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.OriginalFormatBox;
import org.mp4parser.boxes.iso14496.part12.ProtectionSchemeInformationBox;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part12.SchemeInformationBox;
import org.mp4parser.boxes.iso14496.part12.SchemeTypeBox;
import org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox;
import org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox;
import org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox;
import org.mp4parser.boxes.iso23001.part7.CencSampleAuxiliaryDataFormat;
import org.mp4parser.boxes.iso23001.part7.TrackEncryptionBox;
import org.mp4parser.boxes.sampleentry.AudioSampleEntry;
import org.mp4parser.boxes.sampleentry.VisualSampleEntry;
import org.mp4parser.boxes.samplegrouping.CencSampleEncryptionInformationGroupEntry;
import org.mp4parser.boxes.samplegrouping.GroupEntry;
import org.mp4parser.muxer.Edit;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.TrackMetaData;
import org.mp4parser.muxer.samples.CencEncryptingSampleList;
import org.mp4parser.muxer.tracks.h264.H264NalUnitHeader;
import org.mp4parser.muxer.tracks.h264.H264TrackImpl;
import org.mp4parser.muxer.tracks.h265.H265NalUnitHeader;
import org.mp4parser.muxer.tracks.h265.H265TrackImpl;
import org.mp4parser.tools.ByteBufferByteChannel;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeReaderVariable;
import org.mp4parser.tools.RangeStartMap;

public class CencEncryptingTrackImpl implements CencEncryptedTrack {

    /* renamed from: a  reason: collision with root package name */
    Track f4015a;
    Map<UUID, SecretKey> b;
    UUID c;
    List<Sample> d;
    List<CencSampleAuxiliaryDataFormat> e;
    boolean f;
    boolean g;
    SampleDescriptionBox h;
    RangeStartMap<Integer, SecretKey> i;
    Map<GroupEntry, long[]> j;
    Object k;
    private final String l;

    public CencEncryptingTrackImpl(Track track, UUID uuid, SecretKey secretKey, boolean z) {
        this(track, uuid, Collections.singletonMap(uuid, secretKey), (Map<CencSampleEncryptionInformationGroupEntry, long[]>) null, C.CENC_TYPE_cenc, z);
    }

    public CencEncryptingTrackImpl(Track track, UUID uuid, Map<UUID, SecretKey> map, Map<CencSampleEncryptionInformationGroupEntry, long[]> map2, String str, boolean z) {
        this(track, uuid, map, map2, str, z, false);
    }

    public CencEncryptingTrackImpl(Track track, UUID uuid, Map<UUID, SecretKey> map, Map<CencSampleEncryptionInformationGroupEntry, long[]> map2, String str, boolean z, boolean z2) {
        UUID uuid2 = uuid;
        Map<UUID, SecretKey> map3 = map;
        boolean z3 = z;
        this.b = new HashMap();
        this.f = false;
        this.g = false;
        this.h = null;
        this.f4015a = track;
        this.b = map3;
        this.c = uuid2;
        this.f = z3;
        this.l = str;
        this.j = new HashMap();
        for (Map.Entry next : track.h().entrySet()) {
            if (!(next.getKey() instanceof CencSampleEncryptionInformationGroupEntry)) {
                this.j.put(next.getKey(), next.getValue());
            }
        }
        if (map2 != null) {
            for (Map.Entry next2 : map2.entrySet()) {
                this.j.put(next2.getKey(), next2.getValue());
            }
        }
        this.j = new HashMap<GroupEntry, long[]>(this.j) {
            public long[] put(GroupEntry groupEntry, long[] jArr) {
                if (!(groupEntry instanceof CencSampleEncryptionInformationGroupEntry)) {
                    return (long[]) super.put(groupEntry, jArr);
                }
                throw new RuntimeException("Please supply CencSampleEncryptionInformationGroupEntries in the constructor");
            }
        };
        this.d = track.l();
        this.e = new ArrayList();
        BigInteger bigInteger = new BigInteger("1");
        int i2 = 8;
        byte[] bArr = {0, 0, 0, 0, 0, 0, 0, 0};
        if (!z3) {
            new SecureRandom().nextBytes(bArr);
        }
        BigInteger bigInteger2 = new BigInteger(1, bArr);
        ArrayList arrayList = new ArrayList();
        if (map2 != null) {
            arrayList.addAll(map2.keySet());
        }
        this.i = new RangeStartMap<>();
        int i3 = 0;
        int i4 = -1;
        while (i3 < track.l().size()) {
            int i5 = 0;
            int i6 = 0;
            while (i5 < arrayList.size()) {
                if (Arrays.binarySearch(h().get((GroupEntry) arrayList.get(i5)), (long) i3) >= 0) {
                    i6 = i5 + 1;
                }
                i5++;
                Track track2 = track;
            }
            if (i4 != i6) {
                if (i6 == 0) {
                    this.i.put(Integer.valueOf(i3), map3.get(uuid2));
                } else {
                    int i7 = i6 - 1;
                    if (((CencSampleEncryptionInformationGroupEntry) arrayList.get(i7)).e() != null) {
                        SecretKey secretKey = map3.get(((CencSampleEncryptionInformationGroupEntry) arrayList.get(i7)).e());
                        if (secretKey != null) {
                            this.i.put(Integer.valueOf(i3), secretKey);
                        } else {
                            throw new RuntimeException("Key " + ((CencSampleEncryptionInformationGroupEntry) arrayList.get(i7)).e() + " was not supplied for decryption");
                        }
                    } else {
                        this.i.put(Integer.valueOf(i3), null);
                        i4 = i6;
                    }
                }
                i4 = i6;
            }
            i3++;
            Track track3 = track;
        }
        int i8 = -1;
        for (Box next3 : track.n().e().a()) {
            if (next3 instanceof AvcConfigurationBox) {
                this.k = next3;
                this.g = true;
                i8 = ((AvcConfigurationBox) next3).h() + 1;
            }
            if (next3 instanceof HevcConfigurationBox) {
                this.k = next3;
                this.g = true;
                i8 = ((HevcConfigurationBox) next3).s() + 1;
            }
        }
        int i9 = 0;
        while (i9 < this.d.size()) {
            Sample sample = this.d.get(i9);
            CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat = new CencSampleAuxiliaryDataFormat();
            this.e.add(cencSampleAuxiliaryDataFormat);
            if (this.i.get(Integer.valueOf(i9)) != null) {
                byte[] byteArray = bigInteger2.toByteArray();
                byte[] bArr2 = new byte[i2];
                // fill-array-data instruction
                bArr2[0] = 0;
                bArr2[1] = 0;
                bArr2[2] = 0;
                bArr2[3] = 0;
                bArr2[4] = 0;
                bArr2[5] = 0;
                bArr2[6] = 0;
                bArr2[7] = 0;
                System.arraycopy(byteArray, byteArray.length - i2 > 0 ? byteArray.length - i2 : 0, bArr2, 8 - byteArray.length < 0 ? 0 : 8 - byteArray.length, byteArray.length > i2 ? 8 : byteArray.length);
                cencSampleAuxiliaryDataFormat.f3934a = bArr2;
                ByteBuffer byteBuffer = (ByteBuffer) sample.b().rewind();
                if (this.g) {
                    if (z2) {
                        cencSampleAuxiliaryDataFormat.b = new CencSampleAuxiliaryDataFormat.Pair[]{cencSampleAuxiliaryDataFormat.a(byteBuffer.remaining(), 0)};
                    } else {
                        ArrayList arrayList2 = new ArrayList(5);
                        while (byteBuffer.remaining() > 0) {
                            int a2 = CastUtils.a(IsoTypeReaderVariable.a(byteBuffer, i8));
                            int i10 = a2 + i8;
                            int i11 = (i10 < 112 || a(byteBuffer.duplicate())) ? i10 : (i10 % 16) + 96;
                            arrayList2.add(cencSampleAuxiliaryDataFormat.a(i11, (long) (i10 - i11)));
                            byteBuffer.position(byteBuffer.position() + a2);
                        }
                        cencSampleAuxiliaryDataFormat.b = (CencSampleAuxiliaryDataFormat.Pair[]) arrayList2.toArray(new CencSampleAuxiliaryDataFormat.Pair[arrayList2.size()]);
                    }
                }
                bigInteger2 = bigInteger2.add(bigInteger);
            }
            i9++;
            i2 = 8;
        }
    }

    public UUID i() {
        return this.c;
    }

    public boolean j() {
        return this.g;
    }

    public List<CencSampleAuxiliaryDataFormat> k() {
        return this.e;
    }

    public synchronized SampleDescriptionBox n() {
        if (this.h == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                this.f4015a.n().b(Channels.newChannel(byteArrayOutputStream));
                int i2 = 0;
                this.h = (SampleDescriptionBox) new IsoFile((ReadableByteChannel) new ByteBufferByteChannel(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()))).a().get(0);
                OriginalFormatBox originalFormatBox = new OriginalFormatBox();
                originalFormatBox.a(this.h.e().ae_());
                if (this.h.e() instanceof AudioSampleEntry) {
                    ((AudioSampleEntry) this.h.e()).a(AudioSampleEntry.m);
                } else if (this.h.e() instanceof VisualSampleEntry) {
                    ((VisualSampleEntry) this.h.e()).a(VisualSampleEntry.h);
                } else {
                    throw new RuntimeException("I don't know how to cenc " + this.h.e().ae_());
                }
                ProtectionSchemeInformationBox protectionSchemeInformationBox = new ProtectionSchemeInformationBox();
                protectionSchemeInformationBox.a((Box) originalFormatBox);
                SchemeTypeBox schemeTypeBox = new SchemeTypeBox();
                schemeTypeBox.a(this.l);
                schemeTypeBox.c(65536);
                protectionSchemeInformationBox.a((Box) schemeTypeBox);
                SchemeInformationBox schemeInformationBox = new SchemeInformationBox();
                TrackEncryptionBox trackEncryptionBox = new TrackEncryptionBox();
                trackEncryptionBox.d(this.c == null ? 0 : 8);
                if (this.c != null) {
                    i2 = 1;
                }
                trackEncryptionBox.c(i2);
                trackEncryptionBox.a(this.c == null ? new UUID(0, 0) : this.c);
                schemeInformationBox.a((Box) trackEncryptionBox);
                protectionSchemeInformationBox.a((Box) schemeInformationBox);
                this.h.e().a((Box) protectionSchemeInformationBox);
            } catch (IOException unused) {
                throw new RuntimeException("Dumping stsd to memory failed");
            }
        }
        return this.h;
    }

    public long[] m() {
        return this.f4015a.m();
    }

    public long e() {
        return this.f4015a.e();
    }

    public List<CompositionTimeToSample.Entry> a() {
        return this.f4015a.a();
    }

    public long[] b() {
        return this.f4015a.b();
    }

    public List<SampleDependencyTypeBox.Entry> c() {
        return this.f4015a.c();
    }

    public TrackMetaData o() {
        return this.f4015a.o();
    }

    public String p() {
        return this.f4015a.p();
    }

    public List<Sample> l() {
        return new CencEncryptingSampleList(this.i, this.f4015a.l(), this.e, this.l);
    }

    public SubSampleInformationBox d() {
        return this.f4015a.d();
    }

    public void close() throws IOException {
        this.f4015a.close();
    }

    public String f() {
        return "enc(" + this.f4015a.f() + Operators.BRACKET_END_STR;
    }

    public List<Edit> g() {
        return this.f4015a.g();
    }

    public Map<GroupEntry, long[]> h() {
        return this.j;
    }

    public boolean a(ByteBuffer byteBuffer) {
        if (this.k instanceof HevcConfigurationBox) {
            H265NalUnitHeader b2 = H265TrackImpl.b(byteBuffer.slice());
            if (b2.b >= 0 && b2.b <= 9) {
                return false;
            }
            if (b2.b >= 16 && b2.b <= 21) {
                return false;
            }
            if (b2.b < 16 || b2.b > 21) {
                return true;
            }
            return false;
        } else if (this.k instanceof AvcConfigurationBox) {
            H264NalUnitHeader b3 = H264TrackImpl.b(byteBuffer.slice());
            if (b3.b == 19 || b3.b == 2 || b3.b == 3 || b3.b == 4 || b3.b == 20 || b3.b == 5 || b3.b == 1) {
                return false;
            }
            return true;
        } else {
            throw new RuntimeException("Subsample encryption is activated but the CencEncryptingTrackImpl can't say if this sample is to be encrypted or not!");
        }
    }
}
