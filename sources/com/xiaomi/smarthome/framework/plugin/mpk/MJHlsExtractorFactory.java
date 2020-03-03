package com.xiaomi.smarthome.framework.plugin.mpk;

import com.google.android.exoplayer2.source.hls.HlsExtractorFactory;

public class MJHlsExtractorFactory implements HlsExtractorFactory {
    public static final String AAC_FILE_EXTENSION = ".aac";
    public static final String AC3_FILE_EXTENSION = ".ac3";
    public static final String EC3_FILE_EXTENSION = ".ec3";
    public static final String M4_FILE_EXTENSION_PREFIX = ".m4";
    public static final String MP3_FILE_EXTENSION = ".mp3";
    public static final String MP4_FILE_EXTENSION = "mp4";
    public static final String MP4_FILE_EXTENSION_PREFIX = ".mp4";
    public static final String TS_FILE_EXTENSION = "ts";
    public static final String VTT_FILE_EXTENSION = ".vtt";
    public static final String WEBVTT_FILE_EXTENSION = ".webvtt";

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v15, resolved type: com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v21, resolved type: com.google.android.exoplayer2.extractor.Extractor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v23, resolved type: com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v24, resolved type: com.google.android.exoplayer2.extractor.Extractor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v25, resolved type: com.google.android.exoplayer2.extractor.Extractor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v26, resolved type: com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v27, resolved type: com.google.android.exoplayer2.extractor.Extractor} */
    /* JADX WARNING: type inference failed for: r8v5, types: [com.google.android.exoplayer2.extractor.ts.TsExtractor] */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003c, code lost:
        if (r7 == null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0046, code lost:
        if (r8.endsWith("mp4") != false) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0054, code lost:
        if (r8.startsWith(".m4", r8.length() - 4) != false) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0062, code lost:
        if (r8.startsWith(".mp4", r8.length() - 5) == false) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0065, code lost:
        r7 = 16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0067, code lost:
        if (r10 == null) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0069, code lost:
        r7 = 48;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006c, code lost:
        r10 = java.util.Collections.emptyList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0070, code lost:
        r8 = r9.codecs;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0076, code lost:
        if (android.text.TextUtils.isEmpty(r8) != false) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0082, code lost:
        if ("audio/mp4a-latm".equals(com.google.android.exoplayer2.util.MimeTypes.getAudioMediaMimeType(r8)) != false) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0084, code lost:
        r7 = r7 | 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0090, code lost:
        if ("video/avc".equals(com.google.android.exoplayer2.util.MimeTypes.getVideoMediaMimeType(r8)) != false) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0092, code lost:
        r7 = r7 | 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0094, code lost:
        r7 = new com.google.android.exoplayer2.extractor.ts.TsExtractor(2, r12, new com.google.android.exoplayer2.extractor.ts.DefaultTsPayloadReaderFactory(r7, r10));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a5, code lost:
        if (r10 == null) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a9, code lost:
        r10 = java.util.Collections.emptyList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ae, code lost:
        r0 = new com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor(0, r12, (com.google.android.exoplayer2.extractor.mp4.Track) null, r11, r10);
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.util.Pair<com.google.android.exoplayer2.extractor.Extractor, java.lang.Boolean> createExtractor(com.google.android.exoplayer2.extractor.Extractor r7, android.net.Uri r8, com.google.android.exoplayer2.Format r9, java.util.List<com.google.android.exoplayer2.Format> r10, com.google.android.exoplayer2.drm.DrmInitData r11, com.google.android.exoplayer2.util.TimestampAdjuster r12, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r13, com.google.android.exoplayer2.extractor.ExtractorInput r14) {
        /*
            r6 = this;
            java.lang.String r8 = r8.getLastPathSegment()
            if (r8 != 0) goto L_0x0008
            java.lang.String r8 = ""
        L_0x0008:
            java.lang.String r13 = ".aac"
            boolean r13 = r8.endsWith(r13)
            r14 = 0
            r0 = 1
            if (r13 == 0) goto L_0x001a
            com.google.android.exoplayer2.extractor.ts.AdtsExtractor r7 = new com.google.android.exoplayer2.extractor.ts.AdtsExtractor
            r7.<init>()
        L_0x0017:
            r14 = 1
            goto L_0x00bc
        L_0x001a:
            java.lang.String r13 = ".ac3"
            boolean r13 = r8.endsWith(r13)
            if (r13 != 0) goto L_0x00b5
            java.lang.String r13 = ".ec3"
            boolean r13 = r8.endsWith(r13)
            if (r13 == 0) goto L_0x002c
            goto L_0x00b5
        L_0x002c:
            java.lang.String r13 = ".mp3"
            boolean r13 = r8.endsWith(r13)
            if (r13 == 0) goto L_0x003c
            com.google.android.exoplayer2.extractor.mp3.Mp3Extractor r7 = new com.google.android.exoplayer2.extractor.mp3.Mp3Extractor
            r8 = 0
            r7.<init>(r14, r8)
            goto L_0x0017
        L_0x003c:
            if (r7 == 0) goto L_0x0040
            goto L_0x00bc
        L_0x0040:
            java.lang.String r7 = "mp4"
            boolean r7 = r8.endsWith(r7)
            if (r7 != 0) goto L_0x00a1
            java.lang.String r7 = ".m4"
            int r13 = r8.length()
            int r13 = r13 + -4
            boolean r7 = r8.startsWith(r7, r13)
            if (r7 != 0) goto L_0x00a1
            java.lang.String r7 = ".mp4"
            int r13 = r8.length()
            int r13 = r13 + -5
            boolean r7 = r8.startsWith(r7, r13)
            if (r7 == 0) goto L_0x0065
            goto L_0x00a1
        L_0x0065:
            r7 = 16
            if (r10 == 0) goto L_0x006c
            r7 = 48
            goto L_0x0070
        L_0x006c:
            java.util.List r10 = java.util.Collections.emptyList()
        L_0x0070:
            java.lang.String r8 = r9.codecs
            boolean r9 = android.text.TextUtils.isEmpty(r8)
            if (r9 != 0) goto L_0x0094
            java.lang.String r9 = "audio/mp4a-latm"
            java.lang.String r11 = com.google.android.exoplayer2.util.MimeTypes.getAudioMediaMimeType(r8)
            boolean r9 = r9.equals(r11)
            if (r9 != 0) goto L_0x0086
            r7 = r7 | 2
        L_0x0086:
            java.lang.String r9 = "video/avc"
            java.lang.String r8 = com.google.android.exoplayer2.util.MimeTypes.getVideoMediaMimeType(r8)
            boolean r8 = r9.equals(r8)
            if (r8 != 0) goto L_0x0094
            r7 = r7 | 4
        L_0x0094:
            com.google.android.exoplayer2.extractor.ts.TsExtractor r8 = new com.google.android.exoplayer2.extractor.ts.TsExtractor
            com.google.android.exoplayer2.extractor.ts.DefaultTsPayloadReaderFactory r9 = new com.google.android.exoplayer2.extractor.ts.DefaultTsPayloadReaderFactory
            r9.<init>(r7, r10)
            r7 = 2
            r8.<init>(r7, r12, r9)
            r7 = r8
            goto L_0x00bc
        L_0x00a1:
            com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor r7 = new com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor
            r1 = 0
            r3 = 0
            if (r10 == 0) goto L_0x00a9
        L_0x00a7:
            r5 = r10
            goto L_0x00ae
        L_0x00a9:
            java.util.List r10 = java.util.Collections.emptyList()
            goto L_0x00a7
        L_0x00ae:
            r0 = r7
            r2 = r12
            r4 = r11
            r0.<init>(r1, r2, r3, r4, r5)
            goto L_0x00bc
        L_0x00b5:
            com.google.android.exoplayer2.extractor.ts.Ac3Extractor r7 = new com.google.android.exoplayer2.extractor.ts.Ac3Extractor
            r7.<init>()
            goto L_0x0017
        L_0x00bc:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r14)
            android.util.Pair r7 = android.util.Pair.create(r7, r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.mpk.MJHlsExtractorFactory.createExtractor(com.google.android.exoplayer2.extractor.Extractor, android.net.Uri, com.google.android.exoplayer2.Format, java.util.List, com.google.android.exoplayer2.drm.DrmInitData, com.google.android.exoplayer2.util.TimestampAdjuster, java.util.Map, com.google.android.exoplayer2.extractor.ExtractorInput):android.util.Pair");
    }
}
