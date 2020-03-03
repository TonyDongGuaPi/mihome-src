package com.google.android.exoplayer2.extractor.mp4;

import android.support.annotation.Nullable;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.mp4.Atom;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class AtomParsers {
    private static final int MAX_GAPLESS_TRIM_SIZE_SAMPLES = 3;
    private static final String TAG = "AtomParsers";
    private static final int TYPE_clcp = Util.getIntegerCodeForString("clcp");
    private static final int TYPE_mdta = Util.getIntegerCodeForString("mdta");
    private static final int TYPE_meta = Util.getIntegerCodeForString("meta");
    private static final int TYPE_sbtl = Util.getIntegerCodeForString("sbtl");
    private static final int TYPE_soun = Util.getIntegerCodeForString("soun");
    private static final int TYPE_subt = Util.getIntegerCodeForString("subt");
    private static final int TYPE_text = Util.getIntegerCodeForString("text");
    private static final int TYPE_vide = Util.getIntegerCodeForString("vide");
    private static final byte[] opusMagic = Util.getUtf8Bytes("OpusHead");

    private interface SampleSizeBox {
        int getSampleCount();

        boolean isFixedSampleSize();

        int readNextSampleSize();
    }

    public static Track parseTrak(Atom.ContainerAtom containerAtom, Atom.LeafAtom leafAtom, long j, DrmInitData drmInitData, boolean z, boolean z2) throws ParserException {
        long j2;
        Atom.LeafAtom leafAtom2;
        long[] jArr;
        long[] jArr2;
        Atom.ContainerAtom containerAtom2 = containerAtom;
        Atom.ContainerAtom containerAtomOfType = containerAtom2.getContainerAtomOfType(Atom.TYPE_mdia);
        int trackTypeForHdlr = getTrackTypeForHdlr(parseHdlr(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_hdlr).data));
        if (trackTypeForHdlr == -1) {
            return null;
        }
        TkhdData parseTkhd = parseTkhd(containerAtom2.getLeafAtomOfType(Atom.TYPE_tkhd).data);
        long j3 = C.TIME_UNSET;
        if (j == C.TIME_UNSET) {
            j2 = parseTkhd.duration;
            leafAtom2 = leafAtom;
        } else {
            leafAtom2 = leafAtom;
            j2 = j;
        }
        long parseMvhd = parseMvhd(leafAtom2.data);
        if (j2 != C.TIME_UNSET) {
            j3 = Util.scaleLargeTimestamp(j2, 1000000, parseMvhd);
        }
        long j4 = j3;
        Atom.ContainerAtom containerAtomOfType2 = containerAtomOfType.getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl);
        Pair<Long, String> parseMdhd = parseMdhd(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_mdhd).data);
        StsdData parseStsd = parseStsd(containerAtomOfType2.getLeafAtomOfType(Atom.TYPE_stsd).data, parseTkhd.id, parseTkhd.rotationDegrees, (String) parseMdhd.second, drmInitData, z2);
        if (!z) {
            Pair<long[], long[]> parseEdts = parseEdts(containerAtom2.getContainerAtomOfType(Atom.TYPE_edts));
            jArr = (long[]) parseEdts.second;
            jArr2 = (long[]) parseEdts.first;
        } else {
            jArr2 = null;
            jArr = null;
        }
        if (parseStsd.format == null) {
            return null;
        }
        return new Track(parseTkhd.id, trackTypeForHdlr, ((Long) parseMdhd.first).longValue(), parseMvhd, j4, parseStsd.format, parseStsd.requiredSampleTransformation, parseStsd.trackEncryptionBoxes, parseStsd.nalUnitLengthFieldLength, jArr2, jArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:132:0x0333  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0366  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.exoplayer2.extractor.mp4.TrackSampleTable parseStbl(com.google.android.exoplayer2.extractor.mp4.Track r49, com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom r50, com.google.android.exoplayer2.extractor.GaplessInfoHolder r51) throws com.google.android.exoplayer2.ParserException {
        /*
            r1 = r49
            r0 = r50
            r2 = r51
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stsz
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r3 = r0.getLeafAtomOfType(r3)
            if (r3 == 0) goto L_0x0014
            com.google.android.exoplayer2.extractor.mp4.AtomParsers$StszSampleSizeBox r4 = new com.google.android.exoplayer2.extractor.mp4.AtomParsers$StszSampleSizeBox
            r4.<init>(r3)
            goto L_0x0021
        L_0x0014:
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stz2
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r3 = r0.getLeafAtomOfType(r3)
            if (r3 == 0) goto L_0x04d1
            com.google.android.exoplayer2.extractor.mp4.AtomParsers$Stz2SampleSizeBox r4 = new com.google.android.exoplayer2.extractor.mp4.AtomParsers$Stz2SampleSizeBox
            r4.<init>(r3)
        L_0x0021:
            int r3 = r4.getSampleCount()
            r5 = 0
            if (r3 != 0) goto L_0x0042
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r9 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable
            long[] r2 = new long[r5]
            int[] r3 = new int[r5]
            r4 = 0
            long[] r6 = new long[r5]
            int[] r7 = new int[r5]
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r0 = r9
            r1 = r49
            r5 = r6
            r6 = r7
            r7 = r10
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r9
        L_0x0042:
            int r6 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stco
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r6 = r0.getLeafAtomOfType(r6)
            r7 = 1
            if (r6 != 0) goto L_0x0053
            int r6 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_co64
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r6 = r0.getLeafAtomOfType(r6)
            r8 = 1
            goto L_0x0054
        L_0x0053:
            r8 = 0
        L_0x0054:
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r6.data
            int r9 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stsc
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r9 = r0.getLeafAtomOfType(r9)
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r9.data
            int r10 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stts
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r10 = r0.getLeafAtomOfType(r10)
            com.google.android.exoplayer2.util.ParsableByteArray r10 = r10.data
            int r11 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stss
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r11 = r0.getLeafAtomOfType(r11)
            r12 = 0
            if (r11 == 0) goto L_0x0072
            com.google.android.exoplayer2.util.ParsableByteArray r11 = r11.data
            goto L_0x0073
        L_0x0072:
            r11 = r12
        L_0x0073:
            int r13 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_ctts
            com.google.android.exoplayer2.extractor.mp4.Atom$LeafAtom r0 = r0.getLeafAtomOfType(r13)
            if (r0 == 0) goto L_0x007e
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r0.data
            goto L_0x007f
        L_0x007e:
            r0 = r12
        L_0x007f:
            com.google.android.exoplayer2.extractor.mp4.AtomParsers$ChunkIterator r13 = new com.google.android.exoplayer2.extractor.mp4.AtomParsers$ChunkIterator
            r13.<init>(r9, r6, r8)
            r6 = 12
            r10.setPosition(r6)
            int r8 = r10.readUnsignedIntToInt()
            int r8 = r8 - r7
            int r9 = r10.readUnsignedIntToInt()
            int r14 = r10.readUnsignedIntToInt()
            if (r0 == 0) goto L_0x00a0
            r0.setPosition(r6)
            int r15 = r0.readUnsignedIntToInt()
            goto L_0x00a1
        L_0x00a0:
            r15 = 0
        L_0x00a1:
            r16 = -1
            if (r11 == 0) goto L_0x00b7
            r11.setPosition(r6)
            int r6 = r11.readUnsignedIntToInt()
            if (r6 <= 0) goto L_0x00b5
            int r12 = r11.readUnsignedIntToInt()
            int r16 = r12 + -1
            goto L_0x00b8
        L_0x00b5:
            r11 = r12
            goto L_0x00b8
        L_0x00b7:
            r6 = 0
        L_0x00b8:
            boolean r12 = r4.isFixedSampleSize()
            if (r12 == 0) goto L_0x00d2
            java.lang.String r12 = "audio/raw"
            com.google.android.exoplayer2.Format r5 = r1.format
            java.lang.String r5 = r5.sampleMimeType
            boolean r5 = r12.equals(r5)
            if (r5 == 0) goto L_0x00d2
            if (r8 != 0) goto L_0x00d2
            if (r15 != 0) goto L_0x00d2
            if (r6 != 0) goto L_0x00d2
            r5 = 1
            goto L_0x00d3
        L_0x00d2:
            r5 = 0
        L_0x00d3:
            r18 = 0
            if (r5 != 0) goto L_0x0230
            long[] r5 = new long[r3]
            int[] r12 = new int[r3]
            long[] r7 = new long[r3]
            r21 = r6
            int[] r6 = new int[r3]
            r28 = r8
            r2 = r9
            r27 = r10
            r10 = r16
            r23 = r18
            r9 = r21
            r1 = 0
            r8 = 0
            r22 = 0
            r25 = 0
            r26 = 0
            r21 = r15
            r15 = r23
        L_0x00f8:
            if (r8 >= r3) goto L_0x01a4
            r29 = r23
            r23 = 1
        L_0x00fe:
            if (r22 != 0) goto L_0x0119
            boolean r23 = r13.moveNext()
            if (r23 == 0) goto L_0x0119
            r32 = r2
            r31 = r3
            long r2 = r13.offset
            r33 = r2
            int r2 = r13.numSamples
            r22 = r2
            r3 = r31
            r2 = r32
            r29 = r33
            goto L_0x00fe
        L_0x0119:
            r32 = r2
            r31 = r3
            if (r23 != 0) goto L_0x013a
            java.lang.String r2 = "AtomParsers"
            java.lang.String r3 = "Unexpected end of chunk data"
            com.google.android.exoplayer2.util.Log.w(r2, r3)
            long[] r5 = java.util.Arrays.copyOf(r5, r8)
            int[] r12 = java.util.Arrays.copyOf(r12, r8)
            long[] r7 = java.util.Arrays.copyOf(r7, r8)
            int[] r6 = java.util.Arrays.copyOf(r6, r8)
            r31 = r8
            goto L_0x01a8
        L_0x013a:
            if (r0 == 0) goto L_0x014d
        L_0x013c:
            if (r26 != 0) goto L_0x014b
            if (r21 <= 0) goto L_0x014b
            int r26 = r0.readUnsignedIntToInt()
            int r25 = r0.readInt()
            int r21 = r21 + -1
            goto L_0x013c
        L_0x014b:
            int r26 = r26 + -1
        L_0x014d:
            r2 = r25
            r5[r8] = r29
            int r3 = r4.readNextSampleSize()
            r12[r8] = r3
            r3 = r12[r8]
            if (r3 <= r1) goto L_0x015d
            r1 = r12[r8]
        L_0x015d:
            r35 = r4
            long r3 = (long) r2
            long r3 = r3 + r15
            r7[r8] = r3
            if (r11 != 0) goto L_0x0167
            r3 = 1
            goto L_0x0168
        L_0x0167:
            r3 = 0
        L_0x0168:
            r6[r8] = r3
            if (r8 != r10) goto L_0x0179
            r3 = 1
            r6[r8] = r3
            int r9 = r9 + -1
            if (r9 <= 0) goto L_0x0179
            int r4 = r11.readUnsignedIntToInt()
            int r4 = r4 - r3
            r10 = r4
        L_0x0179:
            long r3 = (long) r14
            long r15 = r15 + r3
            int r3 = r32 + -1
            if (r3 != 0) goto L_0x018c
            if (r28 <= 0) goto L_0x018c
            int r3 = r27.readUnsignedIntToInt()
            int r4 = r27.readInt()
            int r28 = r28 + -1
            r14 = r4
        L_0x018c:
            r4 = r12[r8]
            r37 = r1
            r36 = r2
            long r1 = (long) r4
            long r23 = r29 + r1
            int r22 = r22 + -1
            int r8 = r8 + 1
            r2 = r3
            r3 = r31
            r4 = r35
            r25 = r36
            r1 = r37
            goto L_0x00f8
        L_0x01a4:
            r32 = r2
            r31 = r3
        L_0x01a8:
            r3 = r22
            r2 = r25
            long r10 = (long) r2
            long r15 = r15 + r10
        L_0x01ae:
            if (r21 <= 0) goto L_0x01be
            int r2 = r0.readUnsignedIntToInt()
            if (r2 == 0) goto L_0x01b8
            r0 = 0
            goto L_0x01bf
        L_0x01b8:
            r0.readInt()
            int r21 = r21 + -1
            goto L_0x01ae
        L_0x01be:
            r0 = 1
        L_0x01bf:
            if (r9 != 0) goto L_0x01d3
            if (r32 != 0) goto L_0x01d3
            if (r3 != 0) goto L_0x01d3
            if (r28 != 0) goto L_0x01d3
            r2 = r26
            if (r2 != 0) goto L_0x01d5
            if (r0 != 0) goto L_0x01ce
            goto L_0x01d5
        L_0x01ce:
            r37 = r1
            r1 = r49
            goto L_0x0227
        L_0x01d3:
            r2 = r26
        L_0x01d5:
            java.lang.String r4 = "AtomParsers"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r10 = "Inconsistent stbl box for track "
            r8.append(r10)
            r37 = r1
            r1 = r49
            int r10 = r1.id
            r8.append(r10)
            java.lang.String r10 = ": remainingSynchronizationSamples "
            r8.append(r10)
            r8.append(r9)
            java.lang.String r9 = ", remainingSamplesAtTimestampDelta "
            r8.append(r9)
            r9 = r32
            r8.append(r9)
            java.lang.String r9 = ", remainingSamplesInChunk "
            r8.append(r9)
            r8.append(r3)
            java.lang.String r3 = ", remainingTimestampDeltaChanges "
            r8.append(r3)
            r3 = r28
            r8.append(r3)
            java.lang.String r3 = ", remainingSamplesAtTimestampOffset "
            r8.append(r3)
            r8.append(r2)
            if (r0 != 0) goto L_0x021b
            java.lang.String r0 = ", ctts invalid"
            goto L_0x021d
        L_0x021b:
            java.lang.String r0 = ""
        L_0x021d:
            r8.append(r0)
            java.lang.String r0 = r8.toString()
            com.google.android.exoplayer2.util.Log.w(r4, r0)
        L_0x0227:
            r2 = r5
            r5 = r7
            r3 = r12
            r7 = r15
            r0 = r31
            r4 = r37
            goto L_0x026c
        L_0x0230:
            r31 = r3
            int r0 = r13.length
            long[] r0 = new long[r0]
            int r2 = r13.length
            int[] r2 = new int[r2]
        L_0x023a:
            boolean r3 = r13.moveNext()
            if (r3 == 0) goto L_0x024d
            int r3 = r13.index
            long r4 = r13.offset
            r0[r3] = r4
            int r3 = r13.index
            int r4 = r13.numSamples
            r2[r3] = r4
            goto L_0x023a
        L_0x024d:
            com.google.android.exoplayer2.Format r3 = r1.format
            int r3 = r3.pcmEncoding
            com.google.android.exoplayer2.Format r4 = r1.format
            int r4 = r4.channelCount
            int r3 = com.google.android.exoplayer2.util.Util.getPcmFrameSize(r3, r4)
            long r4 = (long) r14
            com.google.android.exoplayer2.extractor.mp4.FixedSampleSizeRechunker$Results r0 = com.google.android.exoplayer2.extractor.mp4.FixedSampleSizeRechunker.rechunk(r3, r0, r2, r4)
            long[] r2 = r0.offsets
            int[] r3 = r0.sizes
            int r4 = r0.maximumSize
            long[] r5 = r0.timestamps
            int[] r6 = r0.flags
            long r7 = r0.duration
            r0 = r31
        L_0x026c:
            r11 = 1000000(0xf4240, double:4.940656E-318)
            long r13 = r1.timescale
            r9 = r7
            long r9 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r9, r11, r13)
            long[] r11 = r1.editListDurations
            r14 = 1000000(0xf4240, double:4.940656E-318)
            if (r11 == 0) goto L_0x04b1
            boolean r11 = r51.hasGaplessInfo()
            if (r11 == 0) goto L_0x0285
            goto L_0x04b1
        L_0x0285:
            long[] r9 = r1.editListDurations
            int r9 = r9.length
            r10 = 1
            if (r9 != r10) goto L_0x031e
            int r9 = r1.type
            if (r9 != r10) goto L_0x031e
            int r9 = r5.length
            r10 = 2
            if (r9 < r10) goto L_0x031e
            long[] r9 = r1.editListMediaTimes
            r10 = 0
            r21 = r9[r10]
            long[] r9 = r1.editListDurations
            r23 = r9[r10]
            long r9 = r1.timescale
            long r11 = r1.movieTimescale
            r25 = r9
            r27 = r11
            long r9 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r23, r25, r27)
            long r23 = r21 + r9
            r9 = r5
            r10 = r7
            r12 = r21
            r38 = r3
            r39 = r4
            r3 = r14
            r14 = r23
            boolean r9 = canApplyEditWithGaplessInfo(r9, r10, r12, r14)
            if (r9 == 0) goto L_0x0322
            long r10 = r7 - r23
            r9 = 0
            r12 = r5[r9]
            long r23 = r21 - r12
            com.google.android.exoplayer2.Format r9 = r1.format
            int r9 = r9.sampleRate
            long r12 = (long) r9
            long r14 = r1.timescale
            r25 = r12
            r27 = r14
            long r14 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r23, r25, r27)
            com.google.android.exoplayer2.Format r9 = r1.format
            int r9 = r9.sampleRate
            long r12 = (long) r9
            long r3 = r1.timescale
            r40 = r7
            r7 = r14
            r14 = r3
            long r3 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r10, r12, r14)
            int r9 = (r7 > r18 ? 1 : (r7 == r18 ? 0 : -1))
            if (r9 != 0) goto L_0x02e8
            int r9 = (r3 > r18 ? 1 : (r3 == r18 ? 0 : -1))
            if (r9 == 0) goto L_0x0324
        L_0x02e8:
            r9 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 > 0) goto L_0x0324
            int r11 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r11 > 0) goto L_0x0324
            int r0 = (int) r7
            r7 = r51
            r7.encoderDelay = r0
            int r0 = (int) r3
            r7.encoderPadding = r0
            long r3 = r1.timescale
            r7 = 1000000(0xf4240, double:4.940656E-318)
            com.google.android.exoplayer2.util.Util.scaleLargeTimestampsInPlace(r5, r7, r3)
            long[] r0 = r1.editListDurations
            r3 = 0
            r7 = r0[r3]
            r9 = 1000000(0xf4240, double:4.940656E-318)
            long r11 = r1.movieTimescale
            long r7 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r7, r9, r11)
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r9 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable
            r0 = r9
            r1 = r49
            r3 = r38
            r4 = r39
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r9
        L_0x031e:
            r38 = r3
            r39 = r4
        L_0x0322:
            r40 = r7
        L_0x0324:
            long[] r3 = r1.editListDurations
            int r3 = r3.length
            r4 = 1
            if (r3 != r4) goto L_0x0366
            long[] r3 = r1.editListDurations
            r4 = 0
            r7 = r3[r4]
            int r3 = (r7 > r18 ? 1 : (r7 == r18 ? 0 : -1))
            if (r3 != 0) goto L_0x0366
            long[] r0 = r1.editListMediaTimes
            r7 = r0[r4]
            r0 = 0
        L_0x0338:
            int r3 = r5.length
            if (r0 >= r3) goto L_0x034d
            r3 = r5[r0]
            long r9 = r3 - r7
            r11 = 1000000(0xf4240, double:4.940656E-318)
            long r13 = r1.timescale
            long r3 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r9, r11, r13)
            r5[r0] = r3
            int r0 = r0 + 1
            goto L_0x0338
        L_0x034d:
            r0 = 0
            long r9 = r40 - r7
            r11 = 1000000(0xf4240, double:4.940656E-318)
            long r13 = r1.timescale
            long r7 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r9, r11, r13)
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r9 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable
            r0 = r9
            r1 = r49
            r3 = r38
            r4 = r39
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r9
        L_0x0366:
            int r3 = r1.type
            r4 = 1
            if (r3 != r4) goto L_0x036d
            r3 = 1
            goto L_0x036e
        L_0x036d:
            r3 = 0
        L_0x036e:
            long[] r4 = r1.editListDurations
            int r4 = r4.length
            int[] r4 = new int[r4]
            long[] r7 = r1.editListDurations
            int r7 = r7.length
            int[] r7 = new int[r7]
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
        L_0x037c:
            long[] r12 = r1.editListDurations
            int r12 = r12.length
            if (r8 >= r12) goto L_0x03e7
            long[] r12 = r1.editListMediaTimes
            r13 = r12[r8]
            r15 = -1
            int r12 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r12 == 0) goto L_0x03da
            long[] r12 = r1.editListDurations
            r21 = r12[r8]
            r42 = r11
            long r11 = r1.timescale
            r43 = r9
            r44 = r10
            long r9 = r1.movieTimescale
            r23 = r11
            r25 = r9
            long r9 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r21, r23, r25)
            r11 = 1
            int r12 = com.google.android.exoplayer2.util.Util.binarySearchCeil((long[]) r5, (long) r13, (boolean) r11, (boolean) r11)
            r4[r8] = r12
            long r13 = r13 + r9
            r9 = 0
            int r10 = com.google.android.exoplayer2.util.Util.binarySearchCeil((long[]) r5, (long) r13, (boolean) r3, (boolean) r9)
            r7[r8] = r10
        L_0x03b0:
            r10 = r4[r8]
            r12 = r7[r8]
            if (r10 >= r12) goto L_0x03c3
            r10 = r4[r8]
            r10 = r6[r10]
            r10 = r10 & r11
            if (r10 != 0) goto L_0x03c3
            r10 = r4[r8]
            int r10 = r10 + r11
            r4[r8] = r10
            goto L_0x03b0
        L_0x03c3:
            r10 = r7[r8]
            r12 = r4[r8]
            int r10 = r10 - r12
            int r10 = r44 + r10
            r12 = r4[r8]
            r13 = r42
            if (r13 == r12) goto L_0x03d2
            r12 = 1
            goto L_0x03d3
        L_0x03d2:
            r12 = 0
        L_0x03d3:
            r12 = r43 | r12
            r13 = r7[r8]
            r43 = r12
            goto L_0x03e1
        L_0x03da:
            r43 = r9
            r44 = r10
            r13 = r11
            r9 = 0
            r11 = 1
        L_0x03e1:
            int r8 = r8 + 1
            r11 = r13
            r9 = r43
            goto L_0x037c
        L_0x03e7:
            r43 = r9
            r9 = 0
            r11 = 1
            if (r10 == r0) goto L_0x03ee
            goto L_0x03ef
        L_0x03ee:
            r11 = 0
        L_0x03ef:
            r0 = r43 | r11
            if (r0 == 0) goto L_0x03f6
            long[] r3 = new long[r10]
            goto L_0x03f7
        L_0x03f6:
            r3 = r2
        L_0x03f7:
            if (r0 == 0) goto L_0x03fc
            int[] r8 = new int[r10]
            goto L_0x03fe
        L_0x03fc:
            r8 = r38
        L_0x03fe:
            if (r0 == 0) goto L_0x0402
            r39 = 0
        L_0x0402:
            if (r0 == 0) goto L_0x0407
            int[] r11 = new int[r10]
            goto L_0x0408
        L_0x0407:
            r11 = r6
        L_0x0408:
            long[] r10 = new long[r10]
            r12 = 0
        L_0x040b:
            long[] r13 = r1.editListDurations
            int r13 = r13.length
            if (r9 >= r13) goto L_0x0494
            long[] r13 = r1.editListMediaTimes
            r20 = r13[r9]
            r13 = r4[r9]
            r14 = r7[r9]
            if (r0 == 0) goto L_0x042a
            int r15 = r14 - r13
            java.lang.System.arraycopy(r2, r13, r3, r12, r15)
            r45 = r4
            r4 = r38
            java.lang.System.arraycopy(r4, r13, r8, r12, r15)
            java.lang.System.arraycopy(r6, r13, r11, r12, r15)
            goto L_0x042e
        L_0x042a:
            r45 = r4
            r4 = r38
        L_0x042e:
            r22 = r12
            r15 = r13
            r12 = r39
        L_0x0433:
            if (r15 >= r14) goto L_0x0477
            r16 = 1000000(0xf4240, double:4.940656E-318)
            r46 = r6
            r47 = r7
            long r6 = r1.movieTimescale
            r48 = r2
            r2 = r12
            r12 = r18
            r23 = r14
            r24 = r15
            r14 = r16
            r16 = r6
            long r6 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r12, r14, r16)
            r12 = r5[r24]
            long r25 = r12 - r20
            r27 = 1000000(0xf4240, double:4.940656E-318)
            long r12 = r1.timescale
            r29 = r12
            long r12 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r25, r27, r29)
            long r6 = r6 + r12
            r10[r22] = r6
            if (r0 == 0) goto L_0x0469
            r6 = r8[r22]
            if (r6 <= r2) goto L_0x0469
            r2 = r4[r24]
        L_0x0469:
            r12 = r2
            int r22 = r22 + 1
            int r15 = r24 + 1
            r14 = r23
            r6 = r46
            r7 = r47
            r2 = r48
            goto L_0x0433
        L_0x0477:
            r48 = r2
            r46 = r6
            r47 = r7
            r2 = r12
            long[] r6 = r1.editListDurations
            r12 = r6[r9]
            long r18 = r18 + r12
            int r9 = r9 + 1
            r39 = r2
            r38 = r4
            r12 = r22
            r4 = r45
            r6 = r46
            r2 = r48
            goto L_0x040b
        L_0x0494:
            r14 = 1000000(0xf4240, double:4.940656E-318)
            long r4 = r1.movieTimescale
            r12 = r18
            r16 = r4
            long r12 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r12, r14, r16)
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r9 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable
            r0 = r9
            r1 = r49
            r2 = r3
            r3 = r8
            r4 = r39
            r5 = r10
            r6 = r11
            r7 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r9
        L_0x04b1:
            r48 = r2
            r39 = r4
            r46 = r6
            r4 = r3
            long r2 = r1.timescale
            r6 = 1000000(0xf4240, double:4.940656E-318)
            com.google.android.exoplayer2.util.Util.scaleLargeTimestampsInPlace(r5, r6, r2)
            com.google.android.exoplayer2.extractor.mp4.TrackSampleTable r11 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable
            r0 = r11
            r1 = r49
            r2 = r48
            r3 = r4
            r4 = r39
            r6 = r46
            r7 = r9
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r11
        L_0x04d1:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.String r1 = "Track has no sample table size information"
            r0.<init>((java.lang.String) r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.parseStbl(com.google.android.exoplayer2.extractor.mp4.Track, com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom, com.google.android.exoplayer2.extractor.GaplessInfoHolder):com.google.android.exoplayer2.extractor.mp4.TrackSampleTable");
    }

    @Nullable
    public static Metadata parseUdta(Atom.LeafAtom leafAtom, boolean z) {
        if (z) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtom.data;
        parsableByteArray.setPosition(8);
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_meta) {
                parsableByteArray.setPosition(position);
                return parseUdtaMeta(parsableByteArray, position + readInt);
            }
            parsableByteArray.setPosition(position + readInt);
        }
        return null;
    }

    @Nullable
    public static Metadata parseMdtaFromMeta(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_hdlr);
        Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(Atom.TYPE_keys);
        Atom.LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_ilst);
        if (leafAtomOfType == null || leafAtomOfType2 == null || leafAtomOfType3 == null || parseHdlr(leafAtomOfType.data) != TYPE_mdta) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType2.data;
        parsableByteArray.setPosition(12);
        int readInt = parsableByteArray.readInt();
        String[] strArr = new String[readInt];
        for (int i = 0; i < readInt; i++) {
            int readInt2 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            strArr[i] = parsableByteArray.readString(readInt2 - 8);
        }
        ParsableByteArray parsableByteArray2 = leafAtomOfType3.data;
        parsableByteArray2.setPosition(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray2.bytesLeft() > 8) {
            int position = parsableByteArray2.getPosition();
            int readInt3 = parsableByteArray2.readInt();
            int readInt4 = parsableByteArray2.readInt() - 1;
            if (readInt4 < 0 || readInt4 >= strArr.length) {
                Log.w(TAG, "Skipped metadata with unknown key index: " + readInt4);
            } else {
                MdtaMetadataEntry parseMdtaMetadataEntryFromIlst = MetadataUtil.parseMdtaMetadataEntryFromIlst(parsableByteArray2, position + readInt3, strArr[readInt4]);
                if (parseMdtaMetadataEntryFromIlst != null) {
                    arrayList.add(parseMdtaMetadataEntryFromIlst);
                }
            }
            parsableByteArray2.setPosition(position + readInt3);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata((List<? extends Metadata.Entry>) arrayList);
    }

    @Nullable
    private static Metadata parseUdtaMeta(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(12);
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_ilst) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + readInt);
            }
            parsableByteArray.setPosition(position + readInt);
        }
        return null;
    }

    @Nullable
    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i) {
            Metadata.Entry parseIlstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (parseIlstElement != null) {
                arrayList.add(parseIlstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata((List<? extends Metadata.Entry>) arrayList);
    }

    private static long parseMvhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        if (Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0) {
            i = 16;
        }
        parsableByteArray.skipBytes(i);
        return parsableByteArray.readUnsignedInt();
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        boolean z;
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        int readInt = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        if (parseFullAtomVersion == 0) {
            i = 4;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= i) {
                z = true;
                break;
            } else if (parsableByteArray.data[position + i3] != -1) {
                z = false;
                break;
            } else {
                i3++;
            }
        }
        long j = C.TIME_UNSET;
        if (z) {
            parsableByteArray.skipBytes(i);
        } else {
            long readUnsignedInt = parseFullAtomVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
            if (readUnsignedInt != 0) {
                j = readUnsignedInt;
            }
        }
        parsableByteArray.skipBytes(16);
        int readInt2 = parsableByteArray.readInt();
        int readInt3 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int readInt4 = parsableByteArray.readInt();
        int readInt5 = parsableByteArray.readInt();
        if (readInt2 == 0 && readInt3 == 65536 && readInt4 == -65536 && readInt5 == 0) {
            i2 = 90;
        } else if (readInt2 == 0 && readInt3 == -65536 && readInt4 == 65536 && readInt5 == 0) {
            i2 = 270;
        } else if (readInt2 == -65536 && readInt3 == 0 && readInt4 == 0 && readInt5 == -65536) {
            i2 = 180;
        }
        return new TkhdData(readInt, j, i2);
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        return parsableByteArray.readInt();
    }

    private static int getTrackTypeForHdlr(int i) {
        if (i == TYPE_soun) {
            return 1;
        }
        if (i == TYPE_vide) {
            return 2;
        }
        if (i == TYPE_text || i == TYPE_sbtl || i == TYPE_subt || i == TYPE_clcp) {
            return 3;
        }
        return i == TYPE_meta ? 4 : -1;
    }

    private static Pair<Long, String> parseMdhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        if (parseFullAtomVersion == 0) {
            i = 4;
        }
        parsableByteArray.skipBytes(i);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        return Pair.create(Long.valueOf(readUnsignedInt), "" + ((char) (((readUnsignedShort >> 10) & 31) + 96)) + ((char) (((readUnsignedShort >> 5) & 31) + 96)) + ((char) ((readUnsignedShort & 31) + 96)));
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, int i, int i2, String str, DrmInitData drmInitData, boolean z) throws ParserException {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.setPosition(12);
        int readInt = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(readInt);
        for (int i3 = 0; i3 < readInt; i3++) {
            int position = parsableByteArray.getPosition();
            int readInt2 = parsableByteArray.readInt();
            Assertions.checkArgument(readInt2 > 0, "childAtomSize should be positive");
            int readInt3 = parsableByteArray.readInt();
            if (readInt3 == Atom.TYPE_avc1 || readInt3 == Atom.TYPE_avc3 || readInt3 == Atom.TYPE_encv || readInt3 == Atom.TYPE_mp4v || readInt3 == Atom.TYPE_hvc1 || readInt3 == Atom.TYPE_hev1 || readInt3 == Atom.TYPE_s263 || readInt3 == Atom.TYPE_vp08 || readInt3 == Atom.TYPE_vp09) {
                parseVideoSampleEntry(parsableByteArray, readInt3, position, readInt2, i, i2, drmInitData, stsdData, i3);
            } else if (readInt3 == Atom.TYPE_mp4a || readInt3 == Atom.TYPE_enca || readInt3 == Atom.TYPE_ac_3 || readInt3 == Atom.TYPE_ec_3 || readInt3 == Atom.TYPE_dtsc || readInt3 == Atom.TYPE_dtse || readInt3 == Atom.TYPE_dtsh || readInt3 == Atom.TYPE_dtsl || readInt3 == Atom.TYPE_samr || readInt3 == Atom.TYPE_sawb || readInt3 == Atom.TYPE_lpcm || readInt3 == Atom.TYPE_sowt || readInt3 == Atom.TYPE__mp3 || readInt3 == Atom.TYPE_alac || readInt3 == Atom.TYPE_alaw || readInt3 == Atom.TYPE_ulaw || readInt3 == Atom.TYPE_Opus || readInt3 == Atom.TYPE_fLaC) {
                parseAudioSampleEntry(parsableByteArray, readInt3, position, readInt2, i, str, z, drmInitData, stsdData, i3);
            } else if (readInt3 == Atom.TYPE_TTML || readInt3 == Atom.TYPE_tx3g || readInt3 == Atom.TYPE_wvtt || readInt3 == Atom.TYPE_stpp || readInt3 == Atom.TYPE_c608) {
                parseTextSampleEntry(parsableByteArray, readInt3, position, readInt2, i, str, stsdData);
            } else if (readInt3 == Atom.TYPE_camm) {
                stsdData.format = Format.createSampleFormat(Integer.toString(i), MimeTypes.APPLICATION_CAMERA_MOTION, (String) null, -1, (DrmInitData) null);
            }
            parsableByteArray2.setPosition(position + readInt2);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, StsdData stsdData) throws ParserException {
        String str2;
        String str3;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i5 = i;
        StsdData stsdData2 = stsdData;
        parsableByteArray2.setPosition(i2 + 8 + 8);
        List list = null;
        long j = Long.MAX_VALUE;
        if (i5 == Atom.TYPE_TTML) {
            str2 = MimeTypes.APPLICATION_TTML;
        } else if (i5 == Atom.TYPE_tx3g) {
            int i6 = (i3 - 8) - 8;
            byte[] bArr = new byte[i6];
            parsableByteArray2.readBytes(bArr, 0, i6);
            list = Collections.singletonList(bArr);
            str3 = MimeTypes.APPLICATION_TX3G;
            stsdData2.format = Format.createTextSampleFormat(Integer.toString(i4), str3, (String) null, -1, 0, str, -1, (DrmInitData) null, j, list);
        } else if (i5 == Atom.TYPE_wvtt) {
            str2 = MimeTypes.APPLICATION_MP4VTT;
        } else if (i5 == Atom.TYPE_stpp) {
            str2 = MimeTypes.APPLICATION_TTML;
            j = 0;
        } else if (i5 == Atom.TYPE_c608) {
            str2 = MimeTypes.APPLICATION_MP4CEA608;
            stsdData2.requiredSampleTransformation = 1;
        } else {
            throw new IllegalStateException();
        }
        str3 = str2;
        stsdData2.format = Format.createTextSampleFormat(Integer.toString(i4), str3, (String) null, -1, 0, str, -1, (DrmInitData) null, j, list);
    }

    /* JADX WARNING: Removed duplicated region for block: B:74:0x0159 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x015a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void parseVideoSampleEntry(com.google.android.exoplayer2.util.ParsableByteArray r22, int r23, int r24, int r25, int r26, int r27, com.google.android.exoplayer2.drm.DrmInitData r28, com.google.android.exoplayer2.extractor.mp4.AtomParsers.StsdData r29, int r30) throws com.google.android.exoplayer2.ParserException {
        /*
            r0 = r22
            r1 = r24
            r2 = r25
            r3 = r28
            r4 = r29
            int r5 = r1 + 8
            int r5 = r5 + 8
            r0.setPosition(r5)
            r5 = 16
            r0.skipBytes(r5)
            int r11 = r22.readUnsignedShort()
            int r12 = r22.readUnsignedShort()
            r5 = 50
            r0.skipBytes(r5)
            int r5 = r22.getPosition()
            int r6 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_encv
            r7 = 0
            r8 = r23
            if (r8 != r6) goto L_0x0055
            android.util.Pair r6 = parseSampleEntryEncryptionData(r0, r1, r2)
            if (r6 == 0) goto L_0x0052
            java.lang.Object r8 = r6.first
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            if (r3 != 0) goto L_0x0040
            r3 = r7
            goto L_0x004a
        L_0x0040:
            java.lang.Object r9 = r6.second
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox r9 = (com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox) r9
            java.lang.String r9 = r9.schemeType
            com.google.android.exoplayer2.drm.DrmInitData r3 = r3.copyWithSchemeType(r9)
        L_0x004a:
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox[] r9 = r4.trackEncryptionBoxes
            java.lang.Object r6 = r6.second
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox r6 = (com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox) r6
            r9[r30] = r6
        L_0x0052:
            r0.setPosition(r5)
        L_0x0055:
            r20 = r3
            r3 = -1
            r9 = 1065353216(0x3f800000, float:1.0)
            r14 = r7
            r17 = r14
            r3 = 0
            r16 = 1065353216(0x3f800000, float:1.0)
            r18 = -1
        L_0x0062:
            int r9 = r5 - r1
            if (r9 >= r2) goto L_0x0157
            r0.setPosition(r5)
            int r9 = r22.getPosition()
            int r10 = r22.readInt()
            if (r10 != 0) goto L_0x007c
            int r13 = r22.getPosition()
            int r13 = r13 - r1
            if (r13 != r2) goto L_0x007c
            goto L_0x0157
        L_0x007c:
            if (r10 <= 0) goto L_0x0080
            r15 = 1
            goto L_0x0081
        L_0x0080:
            r15 = 0
        L_0x0081:
            java.lang.String r6 = "childAtomSize should be positive"
            com.google.android.exoplayer2.util.Assertions.checkArgument(r15, r6)
            int r6 = r22.readInt()
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_avcC
            r13 = 3
            if (r6 != r15) goto L_0x00b3
            if (r7 != 0) goto L_0x0094
            r21 = 1
            goto L_0x0096
        L_0x0094:
            r21 = 0
        L_0x0096:
            com.google.android.exoplayer2.util.Assertions.checkState(r21)
            java.lang.String r7 = "video/avc"
            int r9 = r9 + 8
            r0.setPosition(r9)
            com.google.android.exoplayer2.video.AvcConfig r6 = com.google.android.exoplayer2.video.AvcConfig.parse(r22)
            java.util.List<byte[]> r14 = r6.initializationData
            int r9 = r6.nalUnitLengthFieldLength
            r4.nalUnitLengthFieldLength = r9
            if (r3 != 0) goto L_0x0154
            float r6 = r6.pixelWidthAspectRatio
            r16 = r6
            goto L_0x0154
        L_0x00b3:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_hvcC
            if (r6 != r15) goto L_0x00d5
            if (r7 != 0) goto L_0x00bc
            r21 = 1
            goto L_0x00be
        L_0x00bc:
            r21 = 0
        L_0x00be:
            com.google.android.exoplayer2.util.Assertions.checkState(r21)
            java.lang.String r7 = "video/hevc"
            int r9 = r9 + 8
            r0.setPosition(r9)
            com.google.android.exoplayer2.video.HevcConfig r6 = com.google.android.exoplayer2.video.HevcConfig.parse(r22)
            java.util.List<byte[]> r14 = r6.initializationData
            int r6 = r6.nalUnitLengthFieldLength
            r4.nalUnitLengthFieldLength = r6
            goto L_0x0154
        L_0x00d5:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_vpcC
            if (r6 != r15) goto L_0x00f1
            if (r7 != 0) goto L_0x00de
            r21 = 1
            goto L_0x00e0
        L_0x00de:
            r21 = 0
        L_0x00e0:
            com.google.android.exoplayer2.util.Assertions.checkState(r21)
            int r6 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_vp08
            if (r8 != r6) goto L_0x00ed
            java.lang.String r6 = "video/x-vnd.on2.vp8"
        L_0x00ea:
            r7 = r6
            goto L_0x0154
        L_0x00ed:
            java.lang.String r6 = "video/x-vnd.on2.vp9"
            goto L_0x00ea
        L_0x00f1:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_d263
            if (r6 != r15) goto L_0x0103
            if (r7 != 0) goto L_0x00fa
            r21 = 1
            goto L_0x00fc
        L_0x00fa:
            r21 = 0
        L_0x00fc:
            com.google.android.exoplayer2.util.Assertions.checkState(r21)
            java.lang.String r7 = "video/3gpp"
            goto L_0x0154
        L_0x0103:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_esds
            if (r6 != r15) goto L_0x0120
            if (r7 != 0) goto L_0x010c
            r21 = 1
            goto L_0x010e
        L_0x010c:
            r21 = 0
        L_0x010e:
            com.google.android.exoplayer2.util.Assertions.checkState(r21)
            android.util.Pair r6 = parseEsdsFromParent(r0, r9)
            java.lang.Object r7 = r6.first
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r6 = r6.second
            java.util.List r14 = java.util.Collections.singletonList(r6)
            goto L_0x0154
        L_0x0120:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_pasp
            if (r6 != r15) goto L_0x012a
            float r16 = parsePaspFromParent(r0, r9)
            r3 = 1
            goto L_0x0154
        L_0x012a:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_sv3d
            if (r6 != r15) goto L_0x0133
            byte[] r17 = parseProjFromParent(r0, r9, r10)
            goto L_0x0154
        L_0x0133:
            int r9 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_st3d
            if (r6 != r9) goto L_0x0154
            int r6 = r22.readUnsignedByte()
            r0.skipBytes(r13)
            if (r6 != 0) goto L_0x0154
            int r6 = r22.readUnsignedByte()
            switch(r6) {
                case 0: goto L_0x0152;
                case 1: goto L_0x014f;
                case 2: goto L_0x014b;
                case 3: goto L_0x0148;
                default: goto L_0x0147;
            }
        L_0x0147:
            goto L_0x0154
        L_0x0148:
            r18 = 3
            goto L_0x0154
        L_0x014b:
            r6 = 2
            r18 = 2
            goto L_0x0154
        L_0x014f:
            r18 = 1
            goto L_0x0154
        L_0x0152:
            r18 = 0
        L_0x0154:
            int r5 = r5 + r10
            goto L_0x0062
        L_0x0157:
            if (r7 != 0) goto L_0x015a
            return
        L_0x015a:
            java.lang.String r6 = java.lang.Integer.toString(r26)
            r8 = 0
            r9 = -1
            r10 = -1
            r13 = -1082130432(0xffffffffbf800000, float:-1.0)
            r19 = 0
            r15 = r27
            com.google.android.exoplayer2.Format r0 = com.google.android.exoplayer2.Format.createVideoSampleFormat(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            r4.format = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.parseVideoSampleEntry(com.google.android.exoplayer2.util.ParsableByteArray, int, int, int, int, int, com.google.android.exoplayer2.drm.DrmInitData, com.google.android.exoplayer2.extractor.mp4.AtomParsers$StsdData, int):void");
    }

    private static Pair<long[], long[]> parseEdts(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType;
        if (containerAtom == null || (leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_elst)) == null) {
            return Pair.create((Object) null, (Object) null);
        }
        ParsableByteArray parsableByteArray = leafAtomOfType.data;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[readUnsignedIntToInt];
        long[] jArr2 = new long[readUnsignedIntToInt];
        int i = 0;
        while (i < readUnsignedIntToInt) {
            jArr[i] = parseFullAtomVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
            jArr2[i] = parseFullAtomVersion == 1 ? parsableByteArray.readLong() : (long) parsableByteArray.readInt();
            if (parsableByteArray.readShort() == 1) {
                parsableByteArray.skipBytes(2);
                i++;
            } else {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
        }
        return Pair.create(jArr, jArr2);
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        return ((float) parsableByteArray.readUnsignedIntToInt()) / ((float) parsableByteArray.readUnsignedIntToInt());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v4, resolved type: byte[]} */
    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x0253: MOVE  (r3v5 java.lang.String) = (r24v0 java.lang.String)
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:122)
        	at jadx.core.dex.visitors.regions.TernaryMod.visitRegion(TernaryMod.java:34)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:73)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:31)
        */
    /* JADX WARNING: Multi-variable type inference failed */
    private static void parseAudioSampleEntry(com.google.android.exoplayer2.util.ParsableByteArray r26, int r27, int r28, int r29, int r30, java.lang.String r31, boolean r32, com.google.android.exoplayer2.drm.DrmInitData r33, com.google.android.exoplayer2.extractor.mp4.AtomParsers.StsdData r34, int r35) throws com.google.android.exoplayer2.ParserException {
        /*
            r0 = r26
            r1 = r28
            r2 = r29
            r14 = r31
            r3 = r33
            r13 = r34
            int r4 = r1 + 8
            r5 = 8
            int r4 = r4 + r5
            r0.setPosition(r4)
            r4 = 6
            r12 = 0
            if (r32 == 0) goto L_0x0020
            int r5 = r26.readUnsignedShort()
            r0.skipBytes(r4)
            goto L_0x0024
        L_0x0020:
            r0.skipBytes(r5)
            r5 = 0
        L_0x0024:
            r6 = 16
            r11 = 2
            r10 = 1
            if (r5 == 0) goto L_0x0047
            if (r5 != r10) goto L_0x002d
            goto L_0x0047
        L_0x002d:
            if (r5 != r11) goto L_0x0046
            r0.skipBytes(r6)
            double r4 = r26.readDouble()
            long r4 = java.lang.Math.round(r4)
            int r4 = (int) r4
            int r5 = r26.readUnsignedIntToInt()
            r6 = 20
            r0.skipBytes(r6)
            r7 = r5
            goto L_0x0057
        L_0x0046:
            return
        L_0x0047:
            int r7 = r26.readUnsignedShort()
            r0.skipBytes(r4)
            int r4 = r26.readUnsignedFixedPoint1616()
            if (r5 != r10) goto L_0x0057
            r0.skipBytes(r6)
        L_0x0057:
            int r5 = r26.getPosition()
            int r6 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_enca
            r15 = 0
            r8 = r27
            if (r8 != r6) goto L_0x0089
            android.util.Pair r6 = parseSampleEntryEncryptionData(r0, r1, r2)
            if (r6 == 0) goto L_0x0086
            java.lang.Object r8 = r6.first
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            if (r3 != 0) goto L_0x0074
            r3 = r15
            goto L_0x007e
        L_0x0074:
            java.lang.Object r9 = r6.second
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox r9 = (com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox) r9
            java.lang.String r9 = r9.schemeType
            com.google.android.exoplayer2.drm.DrmInitData r3 = r3.copyWithSchemeType(r9)
        L_0x007e:
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox[] r9 = r13.trackEncryptionBoxes
            java.lang.Object r6 = r6.second
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox r6 = (com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox) r6
            r9[r35] = r6
        L_0x0086:
            r0.setPosition(r5)
        L_0x0089:
            r9 = r3
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_ac_3
            if (r8 != r3) goto L_0x0092
            java.lang.String r3 = "audio/ac3"
            goto L_0x00fa
        L_0x0092:
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_ec_3
            if (r8 != r3) goto L_0x009a
            java.lang.String r3 = "audio/eac3"
            goto L_0x00fa
        L_0x009a:
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_dtsc
            if (r8 != r3) goto L_0x00a2
            java.lang.String r3 = "audio/vnd.dts"
            goto L_0x00fa
        L_0x00a2:
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_dtsh
            if (r8 == r3) goto L_0x00f8
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_dtsl
            if (r8 != r3) goto L_0x00ab
            goto L_0x00f8
        L_0x00ab:
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_dtse
            if (r8 != r3) goto L_0x00b2
            java.lang.String r3 = "audio/vnd.dts.hd;profile=lbr"
            goto L_0x00fa
        L_0x00b2:
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_samr
            if (r8 != r3) goto L_0x00b9
            java.lang.String r3 = "audio/3gpp"
            goto L_0x00fa
        L_0x00b9:
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_sawb
            if (r8 != r3) goto L_0x00c0
            java.lang.String r3 = "audio/amr-wb"
            goto L_0x00fa
        L_0x00c0:
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_lpcm
            if (r8 == r3) goto L_0x00f5
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_sowt
            if (r8 != r3) goto L_0x00c9
            goto L_0x00f5
        L_0x00c9:
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE__mp3
            if (r8 != r3) goto L_0x00d0
            java.lang.String r3 = "audio/mpeg"
            goto L_0x00fa
        L_0x00d0:
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_alac
            if (r8 != r3) goto L_0x00d7
            java.lang.String r3 = "audio/alac"
            goto L_0x00fa
        L_0x00d7:
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_alaw
            if (r8 != r3) goto L_0x00de
            java.lang.String r3 = "audio/g711-alaw"
            goto L_0x00fa
        L_0x00de:
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_ulaw
            if (r8 != r3) goto L_0x00e5
            java.lang.String r3 = "audio/g711-mlaw"
            goto L_0x00fa
        L_0x00e5:
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_Opus
            if (r8 != r3) goto L_0x00ec
            java.lang.String r3 = "audio/opus"
            goto L_0x00fa
        L_0x00ec:
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_fLaC
            if (r8 != r3) goto L_0x00f3
            java.lang.String r3 = "audio/flac"
            goto L_0x00fa
        L_0x00f3:
            r3 = r15
            goto L_0x00fa
        L_0x00f5:
            java.lang.String r3 = "audio/raw"
            goto L_0x00fa
        L_0x00f8:
            java.lang.String r3 = "audio/vnd.dts.hd"
        L_0x00fa:
            r17 = r4
            r8 = r5
            r16 = r7
            r18 = r15
            r7 = r3
        L_0x0102:
            int r3 = r8 - r1
            r4 = -1
            if (r3 >= r2) goto L_0x0248
            r0.setPosition(r8)
            int r6 = r26.readInt()
            if (r6 <= 0) goto L_0x0112
            r3 = 1
            goto L_0x0113
        L_0x0112:
            r3 = 0
        L_0x0113:
            java.lang.String r5 = "childAtomSize should be positive"
            com.google.android.exoplayer2.util.Assertions.checkArgument(r3, r5)
            int r3 = r26.readInt()
            int r5 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_esds
            if (r3 == r5) goto L_0x01f3
            if (r32 == 0) goto L_0x0128
            int r5 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_wave
            if (r3 != r5) goto L_0x0128
            goto L_0x01f3
        L_0x0128:
            int r4 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_dac3
            if (r3 != r4) goto L_0x0149
            int r3 = r8 + 8
            r0.setPosition(r3)
            java.lang.String r3 = java.lang.Integer.toString(r30)
            com.google.android.exoplayer2.Format r3 = com.google.android.exoplayer2.audio.Ac3Util.parseAc3AnnexFFormat(r0, r3, r14, r9)
            r13.format = r3
        L_0x013b:
            r5 = r6
            r24 = r7
            r6 = r8
            r19 = r9
            r2 = r13
            r7 = 0
            r20 = 1
            r21 = 2
            goto L_0x023a
        L_0x0149:
            int r4 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_dec3
            if (r3 != r4) goto L_0x015d
            int r3 = r8 + 8
            r0.setPosition(r3)
            java.lang.String r3 = java.lang.Integer.toString(r30)
            com.google.android.exoplayer2.Format r3 = com.google.android.exoplayer2.audio.Ac3Util.parseEAc3AnnexFFormat(r0, r3, r14, r9)
            r13.format = r3
            goto L_0x013b
        L_0x015d:
            int r4 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_ddts
            if (r3 != r4) goto L_0x0199
            java.lang.String r3 = java.lang.Integer.toString(r30)
            r5 = 0
            r19 = -1
            r20 = -1
            r21 = 0
            r22 = 0
            r4 = r7
            r23 = r6
            r6 = r19
            r24 = r7
            r7 = r20
            r25 = r8
            r8 = r16
            r19 = r9
            r9 = r17
            r20 = 1
            r10 = r21
            r21 = 2
            r11 = r19
            r12 = r22
            r2 = r13
            r13 = r31
            com.google.android.exoplayer2.Format r3 = com.google.android.exoplayer2.Format.createAudioSampleFormat(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            r2.format = r3
            r5 = r23
            r6 = r25
            r7 = 0
            goto L_0x023a
        L_0x0199:
            r23 = r6
            r24 = r7
            r25 = r8
            r19 = r9
            r2 = r13
            r20 = 1
            r21 = 2
            int r4 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_alac
            if (r3 != r4) goto L_0x01bb
            r5 = r23
            byte[] r3 = new byte[r5]
            r6 = r25
            r0.setPosition(r6)
            r7 = 0
            r0.readBytes(r3, r7, r5)
            r18 = r3
            goto L_0x023a
        L_0x01bb:
            r5 = r23
            r6 = r25
            r7 = 0
            int r4 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_dOps
            if (r3 != r4) goto L_0x01e2
            int r3 = r5 + -8
            byte[] r4 = opusMagic
            int r4 = r4.length
            int r4 = r4 + r3
            byte[] r4 = new byte[r4]
            byte[] r8 = opusMagic
            byte[] r9 = opusMagic
            int r9 = r9.length
            java.lang.System.arraycopy(r8, r7, r4, r7, r9)
            int r8 = r6 + 8
            r0.setPosition(r8)
            byte[] r8 = opusMagic
            int r8 = r8.length
            r0.readBytes(r4, r8, r3)
        L_0x01df:
            r18 = r4
            goto L_0x023a
        L_0x01e2:
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_dfLa
            if (r5 != r3) goto L_0x023a
            int r3 = r5 + -12
            byte[] r4 = new byte[r3]
            int r8 = r6 + 12
            r0.setPosition(r8)
            r0.readBytes(r4, r7, r3)
            goto L_0x01df
        L_0x01f3:
            r5 = r6
            r24 = r7
            r6 = r8
            r19 = r9
            r2 = r13
            r7 = 0
            r20 = 1
            r21 = 2
            int r8 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_esds
            if (r3 != r8) goto L_0x0205
            r8 = r6
            goto L_0x0209
        L_0x0205:
            int r8 = findEsdsPosition(r0, r6, r5)
        L_0x0209:
            if (r8 == r4) goto L_0x0236
            android.util.Pair r3 = parseEsdsFromParent(r0, r8)
            java.lang.Object r4 = r3.first
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r3 = r3.second
            r18 = r3
            byte[] r18 = (byte[]) r18
            java.lang.String r3 = "audio/mp4a-latm"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0238
            android.util.Pair r3 = com.google.android.exoplayer2.util.CodecSpecificDataUtil.parseAacAudioSpecificConfig(r18)
            java.lang.Object r8 = r3.first
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r17 = r8.intValue()
            java.lang.Object r3 = r3.second
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r16 = r3.intValue()
            goto L_0x0238
        L_0x0236:
            r4 = r24
        L_0x0238:
            r24 = r4
        L_0x023a:
            int r8 = r6 + r5
            r13 = r2
            r9 = r19
            r7 = r24
            r2 = r29
            r10 = 1
            r11 = 2
            r12 = 0
            goto L_0x0102
        L_0x0248:
            r24 = r7
            r19 = r9
            r2 = r13
            r21 = 2
            com.google.android.exoplayer2.Format r0 = r2.format
            if (r0 != 0) goto L_0x0286
            r3 = r24
            if (r3 == 0) goto L_0x0286
            java.lang.String r0 = "audio/raw"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0261
            r7 = 2
            goto L_0x0262
        L_0x0261:
            r7 = -1
        L_0x0262:
            java.lang.String r0 = java.lang.Integer.toString(r30)
            r4 = 0
            r5 = -1
            r6 = -1
            if (r18 != 0) goto L_0x026d
            r8 = r15
            goto L_0x0272
        L_0x026d:
            java.util.List r1 = java.util.Collections.singletonList(r18)
            r8 = r1
        L_0x0272:
            r10 = 0
            r1 = r3
            r12 = r2
            r2 = r4
            r3 = r5
            r4 = r6
            r5 = r16
            r6 = r17
            r9 = r19
            r11 = r31
            com.google.android.exoplayer2.Format r0 = com.google.android.exoplayer2.Format.createAudioSampleFormat(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r12.format = r0
        L_0x0286:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.parseAudioSampleEntry(com.google.android.exoplayer2.util.ParsableByteArray, int, int, int, int, java.lang.String, boolean, com.google.android.exoplayer2.drm.DrmInitData, com.google.android.exoplayer2.extractor.mp4.AtomParsers$StsdData, int):void");
    }

    private static int findEsdsPosition(ParsableByteArray parsableByteArray, int i, int i2) {
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_esds) {
                return position;
            }
            position += readInt;
        }
        return -1;
    }

    private static Pair<String, byte[]> parseEsdsFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8 + 4);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        if ((readUnsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((readUnsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort());
        }
        if ((readUnsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String mimeTypeFromMp4ObjectType = MimeTypes.getMimeTypeFromMp4ObjectType(parsableByteArray.readUnsignedByte());
        if (MimeTypes.AUDIO_MPEG.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS_HD.equals(mimeTypeFromMp4ObjectType)) {
            return Pair.create(mimeTypeFromMp4ObjectType, (Object) null);
        }
        parsableByteArray.skipBytes(12);
        parsableByteArray.skipBytes(1);
        int parseExpandableClassSize = parseExpandableClassSize(parsableByteArray);
        byte[] bArr = new byte[parseExpandableClassSize];
        parsableByteArray.readBytes(bArr, 0, parseExpandableClassSize);
        return Pair.create(mimeTypeFromMp4ObjectType, bArr);
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2) {
        Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent;
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_sinf && (parseCommonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, position, readInt)) != null) {
                return parseCommonEncryptionSinfFromParent;
            }
            position += readInt;
        }
        return null;
    }

    static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        String str = null;
        Integer num = null;
        int i4 = -1;
        int i5 = 0;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == Atom.TYPE_frma) {
                num = Integer.valueOf(parsableByteArray.readInt());
            } else if (readInt2 == Atom.TYPE_schm) {
                parsableByteArray.skipBytes(4);
                str = parsableByteArray.readString(4);
            } else if (readInt2 == Atom.TYPE_schi) {
                i4 = i3;
                i5 = readInt;
            }
            i3 += readInt;
        }
        if (!C.CENC_TYPE_cenc.equals(str) && !C.CENC_TYPE_cbc1.equals(str) && !C.CENC_TYPE_cens.equals(str) && !C.CENC_TYPE_cbcs.equals(str)) {
            return null;
        }
        boolean z = true;
        Assertions.checkArgument(num != null, "frma atom is mandatory");
        Assertions.checkArgument(i4 != -1, "schi atom is mandatory");
        TrackEncryptionBox parseSchiFromParent = parseSchiFromParent(parsableByteArray, i4, i5, str);
        if (parseSchiFromParent == null) {
            z = false;
        }
        Assertions.checkArgument(z, "tenc atom is mandatory");
        return Pair.create(num, parseSchiFromParent);
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2, String str) {
        int i3;
        int i4;
        int i5 = i + 8;
        while (true) {
            byte[] bArr = null;
            if (i5 - i >= i2) {
                return null;
            }
            parsableByteArray.setPosition(i5);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_tenc) {
                int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (parseFullAtomVersion == 0) {
                    parsableByteArray.skipBytes(1);
                    i4 = 0;
                    i3 = 0;
                } else {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    i3 = readUnsignedByte & 15;
                    i4 = (readUnsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
                }
                boolean z = parsableByteArray.readUnsignedByte() == 1;
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                byte[] bArr2 = new byte[16];
                parsableByteArray.readBytes(bArr2, 0, bArr2.length);
                if (z && readUnsignedByte2 == 0) {
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    bArr = new byte[readUnsignedByte3];
                    parsableByteArray.readBytes(bArr, 0, readUnsignedByte3);
                }
                return new TrackEncryptionBox(z, str, readUnsignedByte2, bArr2, i4, i3, bArr);
            }
            i5 += readInt;
        }
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_proj) {
                return Arrays.copyOfRange(parsableByteArray.data, i3, readInt + i3);
            }
            i3 += readInt;
        }
        return null;
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i = readUnsignedByte & 127;
        while ((readUnsignedByte & 128) == 128) {
            readUnsignedByte = parsableByteArray.readUnsignedByte();
            i = (i << 7) | (readUnsignedByte & 127);
        }
        return i;
    }

    private static boolean canApplyEditWithGaplessInfo(long[] jArr, long j, long j2, long j3) {
        int length = jArr.length - 1;
        int constrainValue = Util.constrainValue(3, 0, length);
        int constrainValue2 = Util.constrainValue(jArr.length - 3, 0, length);
        if (jArr[0] > j2 || j2 >= jArr[constrainValue] || jArr[constrainValue2] >= j3 || j3 > j) {
            return false;
        }
        return true;
    }

    private AtomParsers() {
    }

    private static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            Assertions.checkState(parsableByteArray.readInt() != 1 ? false : true, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            long j;
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            if (this.chunkOffsetsAreLongs) {
                j = this.chunkOffsets.readUnsignedLongToLong();
            } else {
                j = this.chunkOffsets.readUnsignedInt();
            }
            this.offset = j;
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i2 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i2;
                this.nextSamplesPerChunkChangeIndex = i2 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    private static final class TkhdData {
        /* access modifiers changed from: private */
        public final long duration;
        /* access modifiers changed from: private */
        public final int id;
        /* access modifiers changed from: private */
        public final int rotationDegrees;

        public TkhdData(int i, long j, int i2) {
            this.id = i;
            this.duration = j;
            this.rotationDegrees = i2;
        }
    }

    private static final class StsdData {
        public static final int STSD_HEADER_SIZE = 8;
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize = this.data.readUnsignedIntToInt();
        private final int sampleCount = this.data.readUnsignedIntToInt();

        public StszSampleSizeBox(Atom.LeafAtom leafAtom) {
            this.data = leafAtom.data;
            this.data.setPosition(12);
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            return this.fixedSampleSize == 0 ? this.data.readUnsignedIntToInt() : this.fixedSampleSize;
        }

        public boolean isFixedSampleSize() {
            return this.fixedSampleSize != 0;
        }
    }

    static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize = (this.data.readUnsignedIntToInt() & 255);
        private final int sampleCount = this.data.readUnsignedIntToInt();
        private int sampleIndex;

        public boolean isFixedSampleSize() {
            return false;
        }

        public Stz2SampleSizeBox(Atom.LeafAtom leafAtom) {
            this.data = leafAtom.data;
            this.data.setPosition(12);
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            if (this.fieldSize == 8) {
                return this.data.readUnsignedByte();
            }
            if (this.fieldSize == 16) {
                return this.data.readUnsignedShort();
            }
            int i = this.sampleIndex;
            this.sampleIndex = i + 1;
            if (i % 2 != 0) {
                return this.currentByte & 15;
            }
            this.currentByte = this.data.readUnsignedByte();
            return (this.currentByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
        }
    }
}
