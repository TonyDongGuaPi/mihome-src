package org.mp4parser.muxer.tracks;

import com.google.android.exoplayer2.C;
import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.crypto.SecretKey;
import org.mp4parser.Box;
import org.mp4parser.IsoFile;
import org.mp4parser.boxes.iso14496.part12.OriginalFormatBox;
import org.mp4parser.boxes.iso14496.part12.ProtectionSchemeInformationBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part12.SchemeTypeBox;
import org.mp4parser.boxes.sampleentry.AudioSampleEntry;
import org.mp4parser.boxes.sampleentry.VisualSampleEntry;
import org.mp4parser.boxes.samplegrouping.CencSampleEncryptionInformationGroupEntry;
import org.mp4parser.boxes.samplegrouping.GroupEntry;
import org.mp4parser.muxer.AbstractTrack;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.TrackMetaData;
import org.mp4parser.muxer.samples.CencDecryptingSampleList;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.ByteBufferByteChannel;
import org.mp4parser.tools.Path;
import org.mp4parser.tools.RangeStartMap;

public class CencDecryptingTrackImpl extends AbstractTrack {
    static final /* synthetic */ boolean g = (!CencDecryptingTrackImpl.class.desiredAssertionStatus());
    CencDecryptingSampleList d;
    Track e;
    RangeStartMap<Integer, SecretKey> f;

    public CencDecryptingTrackImpl(CencEncryptedTrack cencEncryptedTrack, SecretKey secretKey) {
        this(cencEncryptedTrack, (Map<UUID, SecretKey>) Collections.singletonMap(cencEncryptedTrack.i(), secretKey));
    }

    public CencDecryptingTrackImpl(CencEncryptedTrack cencEncryptedTrack, Map<UUID, SecretKey> map) {
        super("dec(" + cencEncryptedTrack.f() + Operators.BRACKET_END_STR);
        this.f = new RangeStartMap<>();
        this.e = cencEncryptedTrack;
        SchemeTypeBox schemeTypeBox = (SchemeTypeBox) Path.a((AbstractContainerBox) cencEncryptedTrack.n(), "enc./sinf/schm");
        if (!g && schemeTypeBox == null) {
            throw new AssertionError();
        } else if (C.CENC_TYPE_cenc.equals(schemeTypeBox.e()) || C.CENC_TYPE_cbc1.equals(schemeTypeBox.e())) {
            ArrayList arrayList = new ArrayList();
            for (Map.Entry next : cencEncryptedTrack.h().entrySet()) {
                if (next.getKey() instanceof CencSampleEncryptionInformationGroupEntry) {
                    arrayList.add((CencSampleEncryptionInformationGroupEntry) next.getKey());
                } else {
                    h().put(next.getKey(), next.getValue());
                }
            }
            int i = -1;
            for (int i2 = 0; i2 < cencEncryptedTrack.l().size(); i2++) {
                int i3 = 0;
                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                    if (Arrays.binarySearch(cencEncryptedTrack.h().get((GroupEntry) arrayList.get(i4)), (long) i2) >= 0) {
                        i3 = i4 + 1;
                    }
                }
                if (i != i3) {
                    if (i3 == 0) {
                        this.f.put(Integer.valueOf(i2), map.get(cencEncryptedTrack.i()));
                    } else {
                        int i5 = i3 - 1;
                        if (((CencSampleEncryptionInformationGroupEntry) arrayList.get(i5)).c()) {
                            SecretKey secretKey = map.get(((CencSampleEncryptionInformationGroupEntry) arrayList.get(i5)).e());
                            if (secretKey != null) {
                                this.f.put(Integer.valueOf(i2), secretKey);
                            } else {
                                throw new RuntimeException("Key " + ((CencSampleEncryptionInformationGroupEntry) arrayList.get(i5)).e() + " was not supplied for decryption");
                            }
                        } else {
                            this.f.put(Integer.valueOf(i2), null);
                        }
                    }
                    i = i3;
                }
            }
            this.d = new CencDecryptingSampleList(this.f, cencEncryptedTrack.l(), cencEncryptedTrack.k(), schemeTypeBox.e());
        } else {
            throw new RuntimeException("You can only use the CencDecryptingTrackImpl with CENC (cenc or cbc1) encrypted tracks");
        }
    }

    public void close() throws IOException {
        this.e.close();
    }

    public long[] b() {
        return this.e.b();
    }

    public SampleDescriptionBox n() {
        OriginalFormatBox originalFormatBox = (OriginalFormatBox) Path.a((AbstractContainerBox) this.e.n(), "enc./sinf/frma");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            this.e.n().b(Channels.newChannel(byteArrayOutputStream));
            SampleDescriptionBox sampleDescriptionBox = (SampleDescriptionBox) new IsoFile((ReadableByteChannel) new ByteBufferByteChannel(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()))).a().get(0);
            if (sampleDescriptionBox.e() instanceof AudioSampleEntry) {
                ((AudioSampleEntry) sampleDescriptionBox.e()).a(originalFormatBox.d());
            } else if (sampleDescriptionBox.e() instanceof VisualSampleEntry) {
                ((VisualSampleEntry) sampleDescriptionBox.e()).a(originalFormatBox.d());
            } else {
                throw new RuntimeException("I don't know " + sampleDescriptionBox.e().ae_());
            }
            LinkedList linkedList = new LinkedList();
            for (Box next : sampleDescriptionBox.e().a()) {
                if (!next.ae_().equals(ProtectionSchemeInformationBox.f3875a)) {
                    linkedList.add(next);
                }
            }
            sampleDescriptionBox.e().a((List<? extends Box>) linkedList);
            return sampleDescriptionBox;
        } catch (IOException unused) {
            throw new RuntimeException("Dumping stsd to memory failed");
        }
    }

    public long[] m() {
        return this.e.m();
    }

    public TrackMetaData o() {
        return this.e.o();
    }

    public String p() {
        return this.e.p();
    }

    public List<Sample> l() {
        return this.d;
    }
}
