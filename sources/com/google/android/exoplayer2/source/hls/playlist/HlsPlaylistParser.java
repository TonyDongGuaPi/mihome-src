package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Base64;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer2.source.UnrecognizedInputFormatException;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.taobao.weex.el.parse.Operators;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HlsPlaylistParser implements ParsingLoadable.Parser<HlsPlaylist> {
    private static final String ATTR_CLOSED_CAPTIONS_NONE = "CLOSED-CAPTIONS=NONE";
    private static final String BOOLEAN_FALSE = "NO";
    private static final String BOOLEAN_TRUE = "YES";
    private static final String KEYFORMAT_IDENTITY = "identity";
    private static final String KEYFORMAT_PLAYREADY = "com.microsoft.playready";
    private static final String KEYFORMAT_WIDEVINE_PSSH_BINARY = "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed";
    private static final String KEYFORMAT_WIDEVINE_PSSH_JSON = "com.widevine";
    private static final String METHOD_AES_128 = "AES-128";
    private static final String METHOD_NONE = "NONE";
    private static final String METHOD_SAMPLE_AES = "SAMPLE-AES";
    private static final String METHOD_SAMPLE_AES_CENC = "SAMPLE-AES-CENC";
    private static final String METHOD_SAMPLE_AES_CTR = "SAMPLE-AES-CTR";
    private static final String PLAYLIST_HEADER = "#EXTM3U";
    private static final Pattern REGEX_ATTR_BYTERANGE = Pattern.compile("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
    private static final Pattern REGEX_AUDIO = Pattern.compile("AUDIO=\"(.+?)\"");
    private static final Pattern REGEX_AUTOSELECT = compileBooleanAttrPattern(Constants.w);
    private static final Pattern REGEX_AVERAGE_BANDWIDTH = Pattern.compile("AVERAGE-BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_BANDWIDTH = Pattern.compile("[^-]BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_BYTERANGE = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
    private static final Pattern REGEX_CHANNELS = Pattern.compile("CHANNELS=\"(.+?)\"");
    private static final Pattern REGEX_CODECS = Pattern.compile("CODECS=\"(.+?)\"");
    private static final Pattern REGEX_DEFAULT = compileBooleanAttrPattern("DEFAULT");
    private static final Pattern REGEX_FORCED = compileBooleanAttrPattern(Constants.x);
    private static final Pattern REGEX_FRAME_RATE = Pattern.compile("FRAME-RATE=([\\d\\.]+)\\b");
    private static final Pattern REGEX_GROUP_ID = Pattern.compile("GROUP-ID=\"(.+?)\"");
    private static final Pattern REGEX_IMPORT = Pattern.compile("IMPORT=\"(.+?)\"");
    private static final Pattern REGEX_INSTREAM_ID = Pattern.compile("INSTREAM-ID=\"((?:CC|SERVICE)\\d+)\"");
    private static final Pattern REGEX_IV = Pattern.compile("IV=([^,.*]+)");
    private static final Pattern REGEX_KEYFORMAT = Pattern.compile("KEYFORMAT=\"(.+?)\"");
    private static final Pattern REGEX_KEYFORMATVERSIONS = Pattern.compile("KEYFORMATVERSIONS=\"(.+?)\"");
    private static final Pattern REGEX_LANGUAGE = Pattern.compile("LANGUAGE=\"(.+?)\"");
    private static final Pattern REGEX_MEDIA_DURATION = Pattern.compile("#EXTINF:([\\d\\.]+)\\b");
    private static final Pattern REGEX_MEDIA_SEQUENCE = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    private static final Pattern REGEX_MEDIA_TITLE = Pattern.compile("#EXTINF:[\\d\\.]+\\b,(.+)");
    private static final Pattern REGEX_METHOD = Pattern.compile("METHOD=(NONE|AES-128|SAMPLE-AES|SAMPLE-AES-CENC|SAMPLE-AES-CTR)\\s*(?:,|$)");
    private static final Pattern REGEX_NAME = Pattern.compile("NAME=\"(.+?)\"");
    private static final Pattern REGEX_PLAYLIST_TYPE = Pattern.compile("#EXT-X-PLAYLIST-TYPE:(.+)\\b");
    private static final Pattern REGEX_RESOLUTION = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
    private static final Pattern REGEX_TARGET_DURATION = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
    private static final Pattern REGEX_TIME_OFFSET = Pattern.compile("TIME-OFFSET=(-?[\\d\\.]+)\\b");
    private static final Pattern REGEX_TYPE = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
    private static final Pattern REGEX_URI = Pattern.compile("URI=\"(.+?)\"");
    private static final Pattern REGEX_VALUE = Pattern.compile("VALUE=\"(.+?)\"");
    private static final Pattern REGEX_VARIABLE_REFERENCE = Pattern.compile("\\{\\$([a-zA-Z0-9\\-_]+)\\}");
    private static final Pattern REGEX_VERSION = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
    private static final String TAG_BYTERANGE = "#EXT-X-BYTERANGE";
    private static final String TAG_DEFINE = "#EXT-X-DEFINE";
    private static final String TAG_DISCONTINUITY = "#EXT-X-DISCONTINUITY";
    private static final String TAG_DISCONTINUITY_SEQUENCE = "#EXT-X-DISCONTINUITY-SEQUENCE";
    private static final String TAG_ENDLIST = "#EXT-X-ENDLIST";
    private static final String TAG_GAP = "#EXT-X-GAP";
    private static final String TAG_INDEPENDENT_SEGMENTS = "#EXT-X-INDEPENDENT-SEGMENTS";
    private static final String TAG_INIT_SEGMENT = "#EXT-X-MAP";
    private static final String TAG_KEY = "#EXT-X-KEY";
    private static final String TAG_MEDIA = "#EXT-X-MEDIA";
    private static final String TAG_MEDIA_DURATION = "#EXTINF";
    private static final String TAG_MEDIA_SEQUENCE = "#EXT-X-MEDIA-SEQUENCE";
    private static final String TAG_PLAYLIST_TYPE = "#EXT-X-PLAYLIST-TYPE";
    private static final String TAG_PREFIX = "#EXT";
    private static final String TAG_PROGRAM_DATE_TIME = "#EXT-X-PROGRAM-DATE-TIME";
    private static final String TAG_START = "#EXT-X-START";
    private static final String TAG_STREAM_INF = "#EXT-X-STREAM-INF";
    private static final String TAG_TARGET_DURATION = "#EXT-X-TARGETDURATION";
    private static final String TAG_VERSION = "#EXT-X-VERSION";
    private static final String TYPE_AUDIO = "AUDIO";
    private static final String TYPE_CLOSED_CAPTIONS = "CLOSED-CAPTIONS";
    private static final String TYPE_SUBTITLES = "SUBTITLES";
    private static final String TYPE_VIDEO = "VIDEO";
    private final HlsMasterPlaylist masterPlaylist;

    public HlsPlaylistParser() {
        this(HlsMasterPlaylist.EMPTY);
    }

    public HlsPlaylistParser(HlsMasterPlaylist hlsMasterPlaylist) {
        this.masterPlaylist = hlsMasterPlaylist;
    }

    public HlsPlaylist parse(Uri uri, InputStream inputStream) throws IOException {
        String trim;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayDeque arrayDeque = new ArrayDeque();
        try {
            if (checkPlaylistHeader(bufferedReader)) {
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        trim = readLine.trim();
                        if (!trim.isEmpty()) {
                            if (!trim.startsWith(TAG_STREAM_INF)) {
                                if (trim.startsWith(TAG_TARGET_DURATION) || trim.startsWith(TAG_MEDIA_SEQUENCE) || trim.startsWith(TAG_MEDIA_DURATION) || trim.startsWith(TAG_KEY) || trim.startsWith(TAG_BYTERANGE) || trim.equals(TAG_DISCONTINUITY) || trim.equals(TAG_DISCONTINUITY_SEQUENCE)) {
                                    break;
                                } else if (trim.equals(TAG_ENDLIST)) {
                                    break;
                                } else {
                                    arrayDeque.add(trim);
                                }
                            } else {
                                arrayDeque.add(trim);
                                HlsMasterPlaylist parseMasterPlaylist = parseMasterPlaylist(new LineIterator(arrayDeque, bufferedReader), uri.toString());
                                Util.closeQuietly((Closeable) bufferedReader);
                                return parseMasterPlaylist;
                            }
                        }
                    } else {
                        Util.closeQuietly((Closeable) bufferedReader);
                        throw new ParserException("Failed to parse the playlist, could not identify any tags.");
                    }
                }
                arrayDeque.add(trim);
                return parseMediaPlaylist(this.masterPlaylist, new LineIterator(arrayDeque, bufferedReader), uri.toString());
            }
            throw new UnrecognizedInputFormatException("Input does not start with the #EXTM3U header.", uri);
        } finally {
            Util.closeQuietly((Closeable) bufferedReader);
        }
    }

    private static boolean checkPlaylistHeader(BufferedReader bufferedReader) throws IOException {
        int read = bufferedReader.read();
        if (read == 239) {
            if (bufferedReader.read() != 187 || bufferedReader.read() != 191) {
                return false;
            }
            read = bufferedReader.read();
        }
        int skipIgnorableWhitespace = skipIgnorableWhitespace(bufferedReader, true, read);
        int length = PLAYLIST_HEADER.length();
        int i = skipIgnorableWhitespace;
        for (int i2 = 0; i2 < length; i2++) {
            if (i != PLAYLIST_HEADER.charAt(i2)) {
                return false;
            }
            i = bufferedReader.read();
        }
        return Util.isLinebreak(skipIgnorableWhitespace(bufferedReader, false, i));
    }

    private static int skipIgnorableWhitespace(BufferedReader bufferedReader, boolean z, int i) throws IOException {
        while (i != -1 && Character.isWhitespace(i) && (z || !Util.isLinebreak(i))) {
            i = bufferedReader.read();
        }
        return i;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x01a2  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01e8  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0206  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist parseMasterPlaylist(com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser.LineIterator r42, java.lang.String r43) throws java.io.IOException {
        /*
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.util.HashMap r11 = new java.util.HashMap
            r11.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r3 = 1
            r8 = 0
            r9 = 0
            r10 = 0
        L_0x002c:
            boolean r12 = r42.hasNext()
            if (r12 == 0) goto L_0x011c
            java.lang.String r12 = r42.next()
            java.lang.String r14 = "#EXT"
            boolean r14 = r12.startsWith(r14)
            if (r14 == 0) goto L_0x0041
            r4.add(r12)
        L_0x0041:
            java.lang.String r14 = "#EXT-X-DEFINE"
            boolean r14 = r12.startsWith(r14)
            if (r14 == 0) goto L_0x0059
            java.util.regex.Pattern r13 = REGEX_NAME
            java.lang.String r13 = parseStringAttr(r12, r13, r11)
            java.util.regex.Pattern r14 = REGEX_VALUE
            java.lang.String r12 = parseStringAttr(r12, r14, r11)
            r11.put(r13, r12)
            goto L_0x002c
        L_0x0059:
            java.lang.String r14 = "#EXT-X-INDEPENDENT-SEGMENTS"
            boolean r14 = r12.equals(r14)
            if (r14 == 0) goto L_0x0063
            r10 = 1
            goto L_0x002c
        L_0x0063:
            java.lang.String r14 = "#EXT-X-MEDIA"
            boolean r14 = r12.startsWith(r14)
            if (r14 == 0) goto L_0x006f
            r2.add(r12)
            goto L_0x002c
        L_0x006f:
            java.lang.String r14 = "#EXT-X-STREAM-INF"
            boolean r14 = r12.startsWith(r14)
            if (r14 == 0) goto L_0x002c
            java.lang.String r14 = "CLOSED-CAPTIONS=NONE"
            boolean r14 = r12.contains(r14)
            r9 = r9 | r14
            java.util.regex.Pattern r14 = REGEX_BANDWIDTH
            int r14 = parseIntAttr(r12, r14)
            java.util.regex.Pattern r15 = REGEX_AVERAGE_BANDWIDTH
            java.lang.String r15 = parseOptionalStringAttr(r12, r15, r11)
            if (r15 == 0) goto L_0x0090
            int r14 = java.lang.Integer.parseInt(r15)
        L_0x0090:
            r20 = r14
            java.util.regex.Pattern r14 = REGEX_CODECS
            java.lang.String r14 = parseOptionalStringAttr(r12, r14, r11)
            java.util.regex.Pattern r15 = REGEX_RESOLUTION
            java.lang.String r15 = parseOptionalStringAttr(r12, r15, r11)
            if (r15 == 0) goto L_0x00c4
            java.lang.String r13 = "x"
            java.lang.String[] r13 = r15.split(r13)
            r15 = r13[r8]
            int r15 = java.lang.Integer.parseInt(r15)
            r13 = r13[r3]
            int r13 = java.lang.Integer.parseInt(r13)
            if (r15 <= 0) goto L_0x00bc
            if (r13 > 0) goto L_0x00b8
            goto L_0x00bc
        L_0x00b8:
            r26 = r13
            r13 = r15
            goto L_0x00bf
        L_0x00bc:
            r13 = -1
            r26 = -1
        L_0x00bf:
            r21 = r13
            r22 = r26
            goto L_0x00c8
        L_0x00c4:
            r21 = -1
            r22 = -1
        L_0x00c8:
            r13 = -1082130432(0xffffffffbf800000, float:-1.0)
            java.util.regex.Pattern r15 = REGEX_FRAME_RATE
            java.lang.String r15 = parseOptionalStringAttr(r12, r15, r11)
            if (r15 == 0) goto L_0x00d9
            float r13 = java.lang.Float.parseFloat(r15)
            r23 = r13
            goto L_0x00db
        L_0x00d9:
            r23 = -1082130432(0xffffffffbf800000, float:-1.0)
        L_0x00db:
            java.util.regex.Pattern r13 = REGEX_AUDIO
            java.lang.String r12 = parseOptionalStringAttr(r12, r13, r11)
            if (r12 == 0) goto L_0x00ec
            if (r14 == 0) goto L_0x00ec
            java.lang.String r13 = com.google.android.exoplayer2.util.Util.getCodecsOfType(r14, r3)
            r1.put(r12, r13)
        L_0x00ec:
            java.lang.String r12 = r42.next()
            java.lang.String r12 = replaceVariableReferences(r12, r11)
            boolean r13 = r0.add(r12)
            if (r13 == 0) goto L_0x002c
            int r13 = r5.size()
            java.lang.String r15 = java.lang.Integer.toString(r13)
            r16 = 0
            java.lang.String r17 = "application/x-mpegURL"
            r18 = 0
            r24 = 0
            r25 = 0
            r19 = r14
            com.google.android.exoplayer2.Format r13 = com.google.android.exoplayer2.Format.createVideoContainerFormat(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$HlsUrl r14 = new com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$HlsUrl
            r14.<init>(r12, r13)
            r5.add(r14)
            goto L_0x002c
        L_0x011c:
            r12 = 0
            r13 = 0
            r14 = 0
        L_0x011f:
            int r15 = r2.size()
            if (r12 >= r15) goto L_0x0248
            java.lang.Object r15 = r2.get(r12)
            java.lang.String r15 = (java.lang.String) r15
            int r36 = parseSelectionFlags(r15)
            java.util.regex.Pattern r0 = REGEX_URI
            java.lang.String r0 = parseOptionalStringAttr(r15, r0, r11)
            java.util.regex.Pattern r3 = REGEX_NAME
            java.lang.String r3 = parseStringAttr(r15, r3, r11)
            java.util.regex.Pattern r8 = REGEX_LANGUAGE
            java.lang.String r37 = parseOptionalStringAttr(r15, r8, r11)
            java.util.regex.Pattern r8 = REGEX_GROUP_ID
            java.lang.String r8 = parseOptionalStringAttr(r15, r8, r11)
            r38 = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r8)
            r39 = r10
            java.lang.String r10 = ":"
            r2.append(r10)
            r2.append(r3)
            java.lang.String r27 = r2.toString()
            java.util.regex.Pattern r2 = REGEX_TYPE
            java.lang.String r2 = parseStringAttr(r15, r2, r11)
            int r10 = r2.hashCode()
            r40 = r13
            r13 = -959297733(0xffffffffc6d2473b, float:-26915.615)
            r41 = r4
            r4 = 2
            if (r10 == r13) goto L_0x0192
            r13 = -333210994(0xffffffffec239a8e, float:-7.911391E26)
            if (r10 == r13) goto L_0x0188
            r13 = 62628790(0x3bba3b6, float:1.1028458E-36)
            if (r10 == r13) goto L_0x017e
            goto L_0x019c
        L_0x017e:
            java.lang.String r10 = "AUDIO"
            boolean r2 = r2.equals(r10)
            if (r2 == 0) goto L_0x019c
            r2 = 0
            goto L_0x019d
        L_0x0188:
            java.lang.String r10 = "CLOSED-CAPTIONS"
            boolean r2 = r2.equals(r10)
            if (r2 == 0) goto L_0x019c
            r2 = 2
            goto L_0x019d
        L_0x0192:
            java.lang.String r10 = "SUBTITLES"
            boolean r2 = r2.equals(r10)
            if (r2 == 0) goto L_0x019c
            r2 = 1
            goto L_0x019d
        L_0x019c:
            r2 = -1
        L_0x019d:
            switch(r2) {
                case 0: goto L_0x0206;
                case 1: goto L_0x01e8;
                case 2: goto L_0x01a2;
                default: goto L_0x01a0;
            }
        L_0x01a0:
            goto L_0x023a
        L_0x01a2:
            java.util.regex.Pattern r0 = REGEX_INSTREAM_ID
            java.lang.String r0 = parseStringAttr(r15, r0, r11)
            java.lang.String r2 = "CC"
            boolean r2 = r0.startsWith(r2)
            if (r2 == 0) goto L_0x01bf
            java.lang.String r2 = "application/cea-608"
            java.lang.String r0 = r0.substring(r4)
            int r0 = java.lang.Integer.parseInt(r0)
        L_0x01ba:
            r24 = r0
            r19 = r2
            goto L_0x01cb
        L_0x01bf:
            java.lang.String r2 = "application/cea-708"
            r4 = 7
            java.lang.String r0 = r0.substring(r4)
            int r0 = java.lang.Integer.parseInt(r0)
            goto L_0x01ba
        L_0x01cb:
            if (r14 != 0) goto L_0x01d2
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
        L_0x01d2:
            r18 = 0
            r20 = 0
            r21 = -1
            r16 = r27
            r17 = r3
            r22 = r36
            r23 = r37
            com.google.android.exoplayer2.Format r0 = com.google.android.exoplayer2.Format.createTextContainerFormat(r16, r17, r18, r19, r20, r21, r22, r23, r24)
            r14.add(r0)
            goto L_0x023a
        L_0x01e8:
            java.lang.String r18 = "application/x-mpegURL"
            java.lang.String r19 = "text/vtt"
            r20 = 0
            r21 = -1
            r16 = r27
            r17 = r3
            r22 = r36
            r23 = r37
            com.google.android.exoplayer2.Format r2 = com.google.android.exoplayer2.Format.createTextContainerFormat(r16, r17, r18, r19, r20, r21, r22, r23)
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$HlsUrl r3 = new com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$HlsUrl
            r3.<init>(r0, r2)
            r7.add(r3)
            goto L_0x023a
        L_0x0206:
            java.lang.Object r2 = r1.get(r8)
            r31 = r2
            java.lang.String r31 = (java.lang.String) r31
            int r33 = parseChannelsAttribute(r15, r11)
            if (r31 == 0) goto L_0x021b
            java.lang.String r2 = com.google.android.exoplayer2.util.MimeTypes.getMediaMimeType(r31)
            r30 = r2
            goto L_0x021d
        L_0x021b:
            r30 = 0
        L_0x021d:
            java.lang.String r29 = "application/x-mpegURL"
            r32 = -1
            r34 = -1
            r35 = 0
            r28 = r3
            com.google.android.exoplayer2.Format r13 = com.google.android.exoplayer2.Format.createAudioContainerFormat(r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37)
            boolean r2 = isMediaTagMuxed(r5, r0)
            if (r2 == 0) goto L_0x0232
            goto L_0x023c
        L_0x0232:
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$HlsUrl r2 = new com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$HlsUrl
            r2.<init>(r0, r13)
            r6.add(r2)
        L_0x023a:
            r13 = r40
        L_0x023c:
            int r12 = r12 + 1
            r2 = r38
            r10 = r39
            r4 = r41
            r3 = 1
            r8 = 0
            goto L_0x011f
        L_0x0248:
            r41 = r4
            r39 = r10
            r40 = r13
            if (r9 == 0) goto L_0x0256
            java.util.List r0 = java.util.Collections.emptyList()
            r9 = r0
            goto L_0x0257
        L_0x0256:
            r9 = r14
        L_0x0257:
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist r0 = new com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist
            r2 = r0
            r3 = r43
            r4 = r41
            r8 = r40
            r10 = r39
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser.parseMasterPlaylist(com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser$LineIterator, java.lang.String):com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist");
    }

    private static HlsMediaPlaylist parseMediaPlaylist(HlsMasterPlaylist hlsMasterPlaylist, LineIterator lineIterator, String str) throws IOException {
        TreeMap treeMap;
        DrmInitData drmInitData;
        DrmInitData.SchemeData schemeData;
        HlsMasterPlaylist hlsMasterPlaylist2 = hlsMasterPlaylist;
        boolean z = hlsMasterPlaylist2.hasIndependentSegments;
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        TreeMap treeMap2 = new TreeMap();
        long j = C.TIME_UNSET;
        int i = 0;
        int i2 = 1;
        boolean z2 = z;
        String str2 = "";
        long j2 = -9223372036854775807L;
        int i3 = 0;
        String str3 = null;
        long j3 = 0;
        boolean z3 = false;
        int i4 = 0;
        long j4 = 0;
        int i5 = 1;
        boolean z4 = false;
        DrmInitData drmInitData2 = null;
        long j5 = 0;
        long j6 = 0;
        DrmInitData drmInitData3 = null;
        boolean z5 = false;
        long j7 = -1;
        int i6 = 0;
        long j8 = 0;
        String str4 = null;
        String str5 = null;
        HlsMediaPlaylist.Segment segment = null;
        loop0:
        while (true) {
            long j9 = 0;
            while (lineIterator.hasNext()) {
                String next = lineIterator.next();
                if (next.startsWith("#EXT")) {
                    arrayList2.add(next);
                }
                if (next.startsWith(TAG_PLAYLIST_TYPE)) {
                    String parseStringAttr = parseStringAttr(next, REGEX_PLAYLIST_TYPE, hashMap);
                    if ("VOD".equals(parseStringAttr)) {
                        i3 = 1;
                    } else if ("EVENT".equals(parseStringAttr)) {
                        i3 = 2;
                    }
                } else if (next.startsWith(TAG_START)) {
                    j = (long) (parseDoubleAttr(next, REGEX_TIME_OFFSET) * 1000000.0d);
                } else if (next.startsWith(TAG_INIT_SEGMENT)) {
                    String parseStringAttr2 = parseStringAttr(next, REGEX_URI, hashMap);
                    String parseOptionalStringAttr = parseOptionalStringAttr(next, REGEX_ATTR_BYTERANGE, hashMap);
                    if (parseOptionalStringAttr != null) {
                        String[] split = parseOptionalStringAttr.split("@");
                        j7 = Long.parseLong(split[i]);
                        if (split.length > i2) {
                            j5 = Long.parseLong(split[i2]);
                        }
                    }
                    segment = new HlsMediaPlaylist.Segment(parseStringAttr2, j5, j7);
                    j5 = 0;
                    j7 = -1;
                } else if (next.startsWith(TAG_TARGET_DURATION)) {
                    j2 = 1000000 * ((long) parseIntAttr(next, REGEX_TARGET_DURATION));
                } else if (next.startsWith(TAG_MEDIA_SEQUENCE)) {
                    j6 = parseLongAttr(next, REGEX_MEDIA_SEQUENCE);
                    j4 = j6;
                } else if (next.startsWith(TAG_VERSION)) {
                    i5 = parseIntAttr(next, REGEX_VERSION);
                } else {
                    if (next.startsWith(TAG_DEFINE)) {
                        String parseOptionalStringAttr2 = parseOptionalStringAttr(next, REGEX_IMPORT, hashMap);
                        if (parseOptionalStringAttr2 != null) {
                            String str6 = hlsMasterPlaylist2.variableDefinitions.get(parseOptionalStringAttr2);
                            if (str6 != null) {
                                hashMap.put(parseOptionalStringAttr2, str6);
                            }
                        } else {
                            hashMap.put(parseStringAttr(next, REGEX_NAME, hashMap), parseStringAttr(next, REGEX_VALUE, hashMap));
                        }
                    } else if (next.startsWith(TAG_MEDIA_DURATION)) {
                        str2 = parseOptionalStringAttr(next, REGEX_MEDIA_TITLE, "", hashMap);
                        j9 = (long) (parseDoubleAttr(next, REGEX_MEDIA_DURATION) * 1000000.0d);
                    } else if (next.startsWith(TAG_KEY)) {
                        String parseStringAttr3 = parseStringAttr(next, REGEX_METHOD, hashMap);
                        String parseOptionalStringAttr3 = parseOptionalStringAttr(next, REGEX_KEYFORMAT, "identity", hashMap);
                        if ("NONE".equals(parseStringAttr3)) {
                            treeMap2.clear();
                            drmInitData3 = null;
                            str4 = null;
                            str5 = null;
                        } else {
                            String parseOptionalStringAttr4 = parseOptionalStringAttr(next, REGEX_IV, hashMap);
                            if (!"identity".equals(parseOptionalStringAttr3)) {
                                if (str3 == null) {
                                    str3 = (METHOD_SAMPLE_AES_CENC.equals(parseStringAttr3) || METHOD_SAMPLE_AES_CTR.equals(parseStringAttr3)) ? C.CENC_TYPE_cenc : C.CENC_TYPE_cbcs;
                                }
                                if (KEYFORMAT_PLAYREADY.equals(parseOptionalStringAttr3)) {
                                    schemeData = parsePlayReadySchemeData(next, hashMap);
                                } else {
                                    schemeData = parseWidevineSchemeData(next, parseOptionalStringAttr3, hashMap);
                                }
                                if (schemeData != null) {
                                    treeMap2.put(parseOptionalStringAttr3, schemeData);
                                    str5 = parseOptionalStringAttr4;
                                    drmInitData3 = null;
                                    str4 = null;
                                }
                            } else if (METHOD_AES_128.equals(parseStringAttr3)) {
                                str4 = parseStringAttr(next, REGEX_URI, hashMap);
                                str5 = parseOptionalStringAttr4;
                            }
                            str5 = parseOptionalStringAttr4;
                            str4 = null;
                        }
                    } else if (next.startsWith(TAG_BYTERANGE)) {
                        String[] split2 = parseStringAttr(next, REGEX_BYTERANGE, hashMap).split("@");
                        j7 = Long.parseLong(split2[i]);
                        if (split2.length > i2) {
                            j5 = Long.parseLong(split2[i2]);
                        }
                    } else if (next.startsWith(TAG_DISCONTINUITY_SEQUENCE)) {
                        i4 = Integer.parseInt(next.substring(next.indexOf(58) + i2));
                        z3 = true;
                    } else if (next.equals(TAG_DISCONTINUITY)) {
                        i6++;
                    } else if (next.startsWith(TAG_PROGRAM_DATE_TIME)) {
                        if (j3 == 0) {
                            j3 = C.msToUs(Util.parseXsDateTime(next.substring(next.indexOf(58) + i2))) - j8;
                        }
                    } else if (next.equals(TAG_GAP)) {
                        z5 = true;
                    } else if (next.equals(TAG_INDEPENDENT_SEGMENTS)) {
                        z2 = true;
                    } else if (next.equals(TAG_ENDLIST)) {
                        z4 = true;
                    } else if (!next.startsWith("#")) {
                        String hexString = str4 == null ? null : str5 != null ? str5 : Long.toHexString(j6);
                        long j10 = j6 + 1;
                        if (j7 == -1) {
                            j5 = 0;
                        }
                        if (drmInitData3 != null || treeMap2.isEmpty()) {
                            treeMap = treeMap2;
                            drmInitData = drmInitData3;
                        } else {
                            DrmInitData.SchemeData[] schemeDataArr = (DrmInitData.SchemeData[]) treeMap2.values().toArray(new DrmInitData.SchemeData[i]);
                            drmInitData = new DrmInitData(str3, schemeDataArr);
                            if (drmInitData2 == null) {
                                DrmInitData.SchemeData[] schemeDataArr2 = new DrmInitData.SchemeData[schemeDataArr.length];
                                int i7 = 0;
                                while (i7 < schemeDataArr.length) {
                                    schemeDataArr2[i7] = schemeDataArr[i7].copyWithData((byte[]) null);
                                    i7++;
                                    treeMap2 = treeMap2;
                                }
                                treeMap = treeMap2;
                                drmInitData2 = new DrmInitData(str3, schemeDataArr2);
                            } else {
                                treeMap = treeMap2;
                            }
                        }
                        arrayList.add(new HlsMediaPlaylist.Segment(replaceVariableReferences(next, hashMap), segment, str2, j9, i6, j8, drmInitData, str4, hexString, j5, j7, z5));
                        j8 += j9;
                        str2 = "";
                        if (j7 != -1) {
                            j5 += j7;
                        }
                        j6 = j10;
                        drmInitData3 = drmInitData;
                        j7 = -1;
                        treeMap2 = treeMap;
                        hlsMasterPlaylist2 = hlsMasterPlaylist;
                        i = 0;
                        i2 = 1;
                        z5 = false;
                    }
                    treeMap2 = treeMap2;
                    hlsMasterPlaylist2 = hlsMasterPlaylist;
                    i = 0;
                    i2 = 1;
                }
            }
            break loop0;
        }
        return new HlsMediaPlaylist(i3, str, arrayList2, j, j3, z3, i4, j4, i5, j2, z2, z4, j3 != 0, drmInitData2, arrayList);
    }

    private static int parseSelectionFlags(String str) {
        int i = parseOptionalBooleanAttribute(str, REGEX_DEFAULT, false) ? 1 : 0;
        if (parseOptionalBooleanAttribute(str, REGEX_FORCED, false)) {
            i |= 2;
        }
        return parseOptionalBooleanAttribute(str, REGEX_AUTOSELECT, false) ? i | 4 : i;
    }

    private static int parseChannelsAttribute(String str, Map<String, String> map) {
        String parseOptionalStringAttr = parseOptionalStringAttr(str, REGEX_CHANNELS, map);
        if (parseOptionalStringAttr != null) {
            return Integer.parseInt(Util.splitAtFirst(parseOptionalStringAttr, "/")[0]);
        }
        return -1;
    }

    @Nullable
    private static DrmInitData.SchemeData parsePlayReadySchemeData(String str, Map<String, String> map) throws ParserException {
        if (!"1".equals(parseOptionalStringAttr(str, REGEX_KEYFORMATVERSIONS, "1", map))) {
            return null;
        }
        String parseStringAttr = parseStringAttr(str, REGEX_URI, map);
        return new DrmInitData.SchemeData(C.PLAYREADY_UUID, MimeTypes.VIDEO_MP4, PsshAtomUtil.buildPsshAtom(C.PLAYREADY_UUID, Base64.decode(parseStringAttr.substring(parseStringAttr.indexOf(44)), 0)));
    }

    @Nullable
    private static DrmInitData.SchemeData parseWidevineSchemeData(String str, String str2, Map<String, String> map) throws ParserException {
        if (KEYFORMAT_WIDEVINE_PSSH_BINARY.equals(str2)) {
            String parseStringAttr = parseStringAttr(str, REGEX_URI, map);
            return new DrmInitData.SchemeData(C.WIDEVINE_UUID, MimeTypes.VIDEO_MP4, Base64.decode(parseStringAttr.substring(parseStringAttr.indexOf(44)), 0));
        } else if (!KEYFORMAT_WIDEVINE_PSSH_JSON.equals(str2)) {
            return null;
        } else {
            try {
                return new DrmInitData.SchemeData(C.WIDEVINE_UUID, "hls", str.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new ParserException((Throwable) e);
            }
        }
    }

    private static int parseIntAttr(String str, Pattern pattern) throws ParserException {
        return Integer.parseInt(parseStringAttr(str, pattern, Collections.emptyMap()));
    }

    private static long parseLongAttr(String str, Pattern pattern) throws ParserException {
        return Long.parseLong(parseStringAttr(str, pattern, Collections.emptyMap()));
    }

    private static double parseDoubleAttr(String str, Pattern pattern) throws ParserException {
        return Double.parseDouble(parseStringAttr(str, pattern, Collections.emptyMap()));
    }

    private static String parseStringAttr(String str, Pattern pattern, Map<String, String> map) throws ParserException {
        String parseOptionalStringAttr = parseOptionalStringAttr(str, pattern, map);
        if (parseOptionalStringAttr != null) {
            return parseOptionalStringAttr;
        }
        throw new ParserException("Couldn't match " + pattern.pattern() + " in " + str);
    }

    @Nullable
    private static String parseOptionalStringAttr(String str, Pattern pattern, Map<String, String> map) {
        return parseOptionalStringAttr(str, pattern, (String) null, map);
    }

    private static String parseOptionalStringAttr(String str, Pattern pattern, String str2, Map<String, String> map) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            str2 = matcher.group(1);
        }
        return (map.isEmpty() || str2 == null) ? str2 : replaceVariableReferences(str2, map);
    }

    private static String replaceVariableReferences(String str, Map<String, String> map) {
        Matcher matcher = REGEX_VARIABLE_REFERENCE.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            String group = matcher.group(1);
            if (map.containsKey(group)) {
                matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(map.get(group)));
            }
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    private static boolean parseOptionalBooleanAttribute(String str, Pattern pattern, boolean z) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? matcher.group(1).equals("YES") : z;
    }

    private static Pattern compileBooleanAttrPattern(String str) {
        return Pattern.compile(str + "=(" + "NO" + "|" + "YES" + Operators.BRACKET_END_STR);
    }

    private static boolean isMediaTagMuxed(List<HlsMasterPlaylist.HlsUrl> list, String str) {
        if (str == null) {
            return true;
        }
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(list.get(i).url)) {
                return true;
            }
        }
        return false;
    }

    private static class LineIterator {
        private final Queue<String> extraLines;
        private String next;
        private final BufferedReader reader;

        public LineIterator(Queue<String> queue, BufferedReader bufferedReader) {
            this.extraLines = queue;
            this.reader = bufferedReader;
        }

        public boolean hasNext() throws IOException {
            if (this.next != null) {
                return true;
            }
            if (!this.extraLines.isEmpty()) {
                this.next = this.extraLines.poll();
                return true;
            }
            do {
                String readLine = this.reader.readLine();
                this.next = readLine;
                if (readLine == null) {
                    return false;
                }
                this.next = this.next.trim();
            } while (this.next.isEmpty());
            return true;
        }

        public String next() throws IOException {
            if (!hasNext()) {
                return null;
            }
            String str = this.next;
            this.next = null;
            return str;
        }
    }
}
