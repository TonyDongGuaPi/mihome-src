package com.google.android.exoplayer2.extractor.mp4;

import android.support.annotation.Nullable;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.mp4parser.boxes.apple.AppleWaveBox;
import org.mp4parser.boxes.apple.PixelAspectRationAtom;
import org.mp4parser.boxes.dolby.AC3SpecificBox;
import org.mp4parser.boxes.dolby.DTSSpecificBox;
import org.mp4parser.boxes.dolby.EC3SpecificBox;
import org.mp4parser.boxes.iso14496.part12.ChunkOffset64BitBox;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.EditBox;
import org.mp4parser.boxes.iso14496.part12.EditListBox;
import org.mp4parser.boxes.iso14496.part12.MediaDataBox;
import org.mp4parser.boxes.iso14496.part12.MovieExtendsBox;
import org.mp4parser.boxes.iso14496.part12.MovieExtendsHeaderBox;
import org.mp4parser.boxes.iso14496.part12.MovieFragmentBox;
import org.mp4parser.boxes.iso14496.part12.OriginalFormatBox;
import org.mp4parser.boxes.iso14496.part12.ProtectionSchemeInformationBox;
import org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationOffsetsBox;
import org.mp4parser.boxes.iso14496.part12.SampleAuxiliaryInformationSizesBox;
import org.mp4parser.boxes.iso14496.part12.SampleSizeBox;
import org.mp4parser.boxes.iso14496.part12.SampleToChunkBox;
import org.mp4parser.boxes.iso14496.part12.SchemeInformationBox;
import org.mp4parser.boxes.iso14496.part12.SchemeTypeBox;
import org.mp4parser.boxes.iso14496.part12.SegmentIndexBox;
import org.mp4parser.boxes.iso14496.part12.StaticChunkOffsetBox;
import org.mp4parser.boxes.iso14496.part12.SyncSampleBox;
import org.mp4parser.boxes.iso14496.part12.TrackExtendsBox;
import org.mp4parser.boxes.iso14496.part12.TrackFragmentBaseMediaDecodeTimeBox;
import org.mp4parser.boxes.iso14496.part12.TrackFragmentBox;
import org.mp4parser.boxes.iso14496.part12.TrackFragmentHeaderBox;
import org.mp4parser.boxes.iso14496.part12.TrackHeaderBox;
import org.mp4parser.boxes.iso14496.part12.TrackRunBox;
import org.mp4parser.boxes.iso14496.part14.ESDescriptorBox;
import org.mp4parser.boxes.iso14496.part15.AvcConfigurationBox;
import org.mp4parser.boxes.iso14496.part15.HevcConfigurationBox;
import org.mp4parser.boxes.iso14496.part30.WebVTTSampleEntry;
import org.mp4parser.boxes.iso14496.part30.XMLSubtitleSampleEntry;
import org.mp4parser.boxes.iso23001.part7.ProtectionSystemSpecificHeaderBox;
import org.mp4parser.boxes.iso23001.part7.SampleEncryptionBox;
import org.mp4parser.boxes.iso23001.part7.TrackEncryptionBox;
import org.mp4parser.boxes.iso23009.part1.EventMessageBox;
import org.mp4parser.boxes.sampleentry.AudioSampleEntry;
import org.mp4parser.boxes.sampleentry.TextSampleEntry;
import org.mp4parser.boxes.sampleentry.VisualSampleEntry;
import org.mp4parser.boxes.samplegrouping.SampleGroupDescriptionBox;
import org.mp4parser.boxes.samplegrouping.SampleToGroupBox;

abstract class Atom {
    public static final int DEFINES_LARGE_SIZE = 1;
    public static final int EXTENDS_TO_END_SIZE = 0;
    public static final int FULL_HEADER_SIZE = 12;
    public static final int HEADER_SIZE = 8;
    public static final int LONG_HEADER_SIZE = 16;
    public static final int TYPE_Opus = Util.getIntegerCodeForString("Opus");
    public static final int TYPE_TTML = Util.getIntegerCodeForString("TTML");
    public static final int TYPE__mp3 = Util.getIntegerCodeForString(".mp3");
    public static final int TYPE_ac_3 = Util.getIntegerCodeForString(AudioSampleEntry.g);
    public static final int TYPE_alac = Util.getIntegerCodeForString("alac");
    public static final int TYPE_alaw = Util.getIntegerCodeForString("alaw");
    public static final int TYPE_avc1 = Util.getIntegerCodeForString(VisualSampleEntry.c);
    public static final int TYPE_avc3 = Util.getIntegerCodeForString(VisualSampleEntry.d);
    public static final int TYPE_avcC = Util.getIntegerCodeForString(AvcConfigurationBox.f3916a);
    public static final int TYPE_c608 = Util.getIntegerCodeForString("c608");
    public static final int TYPE_camm = Util.getIntegerCodeForString("camm");
    public static final int TYPE_co64 = Util.getIntegerCodeForString(ChunkOffset64BitBox.f3834a);
    public static final int TYPE_ctts = Util.getIntegerCodeForString(CompositionTimeToSample.f3836a);
    public static final int TYPE_d263 = Util.getIntegerCodeForString("d263");
    public static final int TYPE_dOps = Util.getIntegerCodeForString("dOps");
    public static final int TYPE_dac3 = Util.getIntegerCodeForString(AC3SpecificBox.f3813a);
    public static final int TYPE_data = Util.getIntegerCodeForString("data");
    public static final int TYPE_ddts = Util.getIntegerCodeForString(DTSSpecificBox.f3814a);
    public static final int TYPE_dec3 = Util.getIntegerCodeForString(EC3SpecificBox.f3815a);
    public static final int TYPE_dfLa = Util.getIntegerCodeForString("dfLa");
    public static final int TYPE_dtsc = Util.getIntegerCodeForString("dtsc");
    public static final int TYPE_dtse = Util.getIntegerCodeForString(AudioSampleEntry.l);
    public static final int TYPE_dtsh = Util.getIntegerCodeForString(AudioSampleEntry.k);
    public static final int TYPE_dtsl = Util.getIntegerCodeForString(AudioSampleEntry.j);
    public static final int TYPE_ec_3 = Util.getIntegerCodeForString(AudioSampleEntry.h);
    public static final int TYPE_edts = Util.getIntegerCodeForString(EditBox.f3844a);
    public static final int TYPE_elst = Util.getIntegerCodeForString(EditListBox.f3845a);
    public static final int TYPE_emsg = Util.getIntegerCodeForString(EventMessageBox.f3937a);
    public static final int TYPE_enca = Util.getIntegerCodeForString(AudioSampleEntry.m);
    public static final int TYPE_encv = Util.getIntegerCodeForString(VisualSampleEntry.h);
    public static final int TYPE_esds = Util.getIntegerCodeForString(ESDescriptorBox.c);
    public static final int TYPE_fLaC = Util.getIntegerCodeForString("fLaC");
    public static final int TYPE_frma = Util.getIntegerCodeForString(OriginalFormatBox.f3872a);
    public static final int TYPE_ftyp = Util.getIntegerCodeForString("ftyp");
    public static final int TYPE_hdlr = Util.getIntegerCodeForString("hdlr");
    public static final int TYPE_hev1 = Util.getIntegerCodeForString(VisualSampleEntry.g);
    public static final int TYPE_hvc1 = Util.getIntegerCodeForString(VisualSampleEntry.f);
    public static final int TYPE_hvcC = Util.getIntegerCodeForString(HevcConfigurationBox.f3918a);
    public static final int TYPE_ilst = Util.getIntegerCodeForString("ilst");
    public static final int TYPE_keys = Util.getIntegerCodeForString(QuickTimeAtomTypes.h);
    public static final int TYPE_lpcm = Util.getIntegerCodeForString("lpcm");
    public static final int TYPE_mdat = Util.getIntegerCodeForString(MediaDataBox.f3859a);
    public static final int TYPE_mdhd = Util.getIntegerCodeForString("mdhd");
    public static final int TYPE_mdia = Util.getIntegerCodeForString("mdia");
    public static final int TYPE_mean = Util.getIntegerCodeForString("mean");
    public static final int TYPE_mehd = Util.getIntegerCodeForString(MovieExtendsHeaderBox.f3865a);
    public static final int TYPE_meta = Util.getIntegerCodeForString("meta");
    public static final int TYPE_minf = Util.getIntegerCodeForString("minf");
    public static final int TYPE_moof = Util.getIntegerCodeForString(MovieFragmentBox.f3866a);
    public static final int TYPE_moov = Util.getIntegerCodeForString("moov");
    public static final int TYPE_mp4a = Util.getIntegerCodeForString(AudioSampleEntry.c);
    public static final int TYPE_mp4v = Util.getIntegerCodeForString(VisualSampleEntry.f3955a);
    public static final int TYPE_mvex = Util.getIntegerCodeForString(MovieExtendsBox.f3864a);
    public static final int TYPE_mvhd = Util.getIntegerCodeForString("mvhd");
    public static final int TYPE_name = Util.getIntegerCodeForString("name");
    public static final int TYPE_pasp = Util.getIntegerCodeForString(PixelAspectRationAtom.f3798a);
    public static final int TYPE_proj = Util.getIntegerCodeForString("proj");
    public static final int TYPE_pssh = Util.getIntegerCodeForString(ProtectionSystemSpecificHeaderBox.f3936a);
    public static final int TYPE_s263 = Util.getIntegerCodeForString(VisualSampleEntry.b);
    public static final int TYPE_saio = Util.getIntegerCodeForString(SampleAuxiliaryInformationOffsetsBox.f3876a);
    public static final int TYPE_saiz = Util.getIntegerCodeForString(SampleAuxiliaryInformationSizesBox.f3877a);
    public static final int TYPE_samr = Util.getIntegerCodeForString(AudioSampleEntry.f3949a);
    public static final int TYPE_sawb = Util.getIntegerCodeForString(AudioSampleEntry.b);
    public static final int TYPE_sbgp = Util.getIntegerCodeForString(SampleToGroupBox.f3961a);
    public static final int TYPE_schi = Util.getIntegerCodeForString(SchemeInformationBox.f3886a);
    public static final int TYPE_schm = Util.getIntegerCodeForString(SchemeTypeBox.f3887a);
    public static final int TYPE_senc = Util.getIntegerCodeForString(SampleEncryptionBox.e);
    public static final int TYPE_sgpd = Util.getIntegerCodeForString(SampleGroupDescriptionBox.f3960a);
    public static final int TYPE_sidx = Util.getIntegerCodeForString(SegmentIndexBox.f3888a);
    public static final int TYPE_sinf = Util.getIntegerCodeForString(ProtectionSchemeInformationBox.f3875a);
    public static final int TYPE_sowt = Util.getIntegerCodeForString("sowt");
    public static final int TYPE_st3d = Util.getIntegerCodeForString("st3d");
    public static final int TYPE_stbl = Util.getIntegerCodeForString("stbl");
    public static final int TYPE_stco = Util.getIntegerCodeForString(StaticChunkOffsetBox.f3892a);
    public static final int TYPE_stpp = Util.getIntegerCodeForString(XMLSubtitleSampleEntry.f3931a);
    public static final int TYPE_stsc = Util.getIntegerCodeForString(SampleToChunkBox.f3884a);
    public static final int TYPE_stsd = Util.getIntegerCodeForString("stsd");
    public static final int TYPE_stss = Util.getIntegerCodeForString(SyncSampleBox.f3897a);
    public static final int TYPE_stsz = Util.getIntegerCodeForString(SampleSizeBox.f3882a);
    public static final int TYPE_stts = Util.getIntegerCodeForString("stts");
    public static final int TYPE_stz2 = Util.getIntegerCodeForString("stz2");
    public static final int TYPE_sv3d = Util.getIntegerCodeForString("sv3d");
    public static final int TYPE_tenc = Util.getIntegerCodeForString(TrackEncryptionBox.d);
    public static final int TYPE_tfdt = Util.getIntegerCodeForString(TrackFragmentBaseMediaDecodeTimeBox.f3902a);
    public static final int TYPE_tfhd = Util.getIntegerCodeForString(TrackFragmentHeaderBox.f3904a);
    public static final int TYPE_tkhd = Util.getIntegerCodeForString(TrackHeaderBox.f3907a);
    public static final int TYPE_traf = Util.getIntegerCodeForString(TrackFragmentBox.f3903a);
    public static final int TYPE_trak = Util.getIntegerCodeForString("trak");
    public static final int TYPE_trex = Util.getIntegerCodeForString(TrackExtendsBox.f3901a);
    public static final int TYPE_trun = Util.getIntegerCodeForString(TrackRunBox.f3910a);
    public static final int TYPE_tx3g = Util.getIntegerCodeForString(TextSampleEntry.f3952a);
    public static final int TYPE_udta = Util.getIntegerCodeForString("udta");
    public static final int TYPE_ulaw = Util.getIntegerCodeForString("ulaw");
    public static final int TYPE_uuid = Util.getIntegerCodeForString("uuid");
    public static final int TYPE_vmhd = Util.getIntegerCodeForString("vmhd");
    public static final int TYPE_vp08 = Util.getIntegerCodeForString("vp08");
    public static final int TYPE_vp09 = Util.getIntegerCodeForString("vp09");
    public static final int TYPE_vpcC = Util.getIntegerCodeForString("vpcC");
    public static final int TYPE_wave = Util.getIntegerCodeForString(AppleWaveBox.f3793a);
    public static final int TYPE_wvtt = Util.getIntegerCodeForString(WebVTTSampleEntry.f3929a);
    public final int type;

    public static int parseFullAtomFlags(int i) {
        return i & 16777215;
    }

    public static int parseFullAtomVersion(int i) {
        return (i >> 24) & 255;
    }

    public Atom(int i) {
        this.type = i;
    }

    public String toString() {
        return getAtomTypeString(this.type);
    }

    static final class LeafAtom extends Atom {
        public final ParsableByteArray data;

        public LeafAtom(int i, ParsableByteArray parsableByteArray) {
            super(i);
            this.data = parsableByteArray;
        }
    }

    static final class ContainerAtom extends Atom {
        public final List<ContainerAtom> containerChildren = new ArrayList();
        public final long endPosition;
        public final List<LeafAtom> leafChildren = new ArrayList();

        public ContainerAtom(int i, long j) {
            super(i);
            this.endPosition = j;
        }

        public void add(LeafAtom leafAtom) {
            this.leafChildren.add(leafAtom);
        }

        public void add(ContainerAtom containerAtom) {
            this.containerChildren.add(containerAtom);
        }

        @Nullable
        public LeafAtom getLeafAtomOfType(int i) {
            int size = this.leafChildren.size();
            for (int i2 = 0; i2 < size; i2++) {
                LeafAtom leafAtom = this.leafChildren.get(i2);
                if (leafAtom.type == i) {
                    return leafAtom;
                }
            }
            return null;
        }

        @Nullable
        public ContainerAtom getContainerAtomOfType(int i) {
            int size = this.containerChildren.size();
            for (int i2 = 0; i2 < size; i2++) {
                ContainerAtom containerAtom = this.containerChildren.get(i2);
                if (containerAtom.type == i) {
                    return containerAtom;
                }
            }
            return null;
        }

        public int getChildAtomOfTypeCount(int i) {
            int size = this.leafChildren.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                if (this.leafChildren.get(i3).type == i) {
                    i2++;
                }
            }
            int size2 = this.containerChildren.size();
            for (int i4 = 0; i4 < size2; i4++) {
                if (this.containerChildren.get(i4).type == i) {
                    i2++;
                }
            }
            return i2;
        }

        public String toString() {
            return getAtomTypeString(this.type) + " leaves: " + Arrays.toString(this.leafChildren.toArray()) + " containers: " + Arrays.toString(this.containerChildren.toArray());
        }
    }

    public static String getAtomTypeString(int i) {
        return "" + ((char) ((i >> 24) & 255)) + ((char) ((i >> 16) & 255)) + ((char) ((i >> 8) & 255)) + ((char) (i & 255));
    }
}
