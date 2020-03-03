package com.google.android.exoplayer2.text.ttml;

import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public final class TtmlDecoder extends SimpleSubtitleDecoder {
    private static final String ATTR_BEGIN = "begin";
    private static final String ATTR_DURATION = "dur";
    private static final String ATTR_END = "end";
    private static final String ATTR_IMAGE = "backgroundImage";
    private static final String ATTR_REGION = "region";
    private static final String ATTR_STYLE = "style";
    private static final Pattern CELL_RESOLUTION = Pattern.compile("^(\\d+) (\\d+)$");
    private static final Pattern CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final CellResolution DEFAULT_CELL_RESOLUTION = new CellResolution(32, 15);
    private static final FrameAndTickRate DEFAULT_FRAME_AND_TICK_RATE = new FrameAndTickRate(30.0f, 1, 1);
    private static final int DEFAULT_FRAME_RATE = 30;
    private static final Pattern FONT_SIZE = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    private static final Pattern OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    private static final Pattern PERCENTAGE_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    private static final Pattern PIXEL_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)px (\\d+\\.?\\d*?)px$");
    private static final String TAG = "TtmlDecoder";
    private static final String TTP = "http://www.w3.org/ns/ttml#parameter";
    private final XmlPullParserFactory xmlParserFactory;

    public TtmlDecoder() {
        super(TAG);
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
            this.xmlParserFactory.setNamespaceAware(true);
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    /* access modifiers changed from: protected */
    public TtmlSubtitle decode(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        TtmlSubtitle ttmlSubtitle;
        TtsExtent ttsExtent;
        CellResolution cellResolution;
        FrameAndTickRate frameAndTickRate;
        FrameAndTickRate frameAndTickRate2;
        try {
            XmlPullParser newPullParser = this.xmlParserFactory.newPullParser();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            TtsExtent ttsExtent2 = null;
            hashMap2.put("", new TtmlRegion((String) null));
            newPullParser.setInput(new ByteArrayInputStream(bArr, 0, i), (String) null);
            ArrayDeque arrayDeque = new ArrayDeque();
            FrameAndTickRate frameAndTickRate3 = DEFAULT_FRAME_AND_TICK_RATE;
            CellResolution cellResolution2 = DEFAULT_CELL_RESOLUTION;
            TtmlSubtitle ttmlSubtitle2 = null;
            int i2 = 0;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.getEventType()) {
                TtmlNode ttmlNode = (TtmlNode) arrayDeque.peek();
                if (i2 == 0) {
                    String name = newPullParser.getName();
                    if (eventType == 2) {
                        if (TtmlNode.TAG_TT.equals(name)) {
                            FrameAndTickRate parseFrameAndTickRates = parseFrameAndTickRates(newPullParser);
                            cellResolution = parseCellResolution(newPullParser, DEFAULT_CELL_RESOLUTION);
                            ttsExtent = parseTtsExtent(newPullParser);
                            frameAndTickRate2 = parseFrameAndTickRates;
                        } else {
                            ttsExtent = ttsExtent2;
                            frameAndTickRate2 = frameAndTickRate3;
                            cellResolution = cellResolution2;
                        }
                        if (!isSupportedTag(name)) {
                            Log.i(TAG, "Ignoring unsupported tag: " + newPullParser.getName());
                            i2++;
                            ttmlSubtitle = ttmlSubtitle2;
                            frameAndTickRate = frameAndTickRate2;
                        } else if (TtmlNode.TAG_HEAD.equals(name)) {
                            ttmlSubtitle = ttmlSubtitle2;
                            frameAndTickRate = frameAndTickRate2;
                            parseHeader(newPullParser, hashMap, cellResolution, ttsExtent, hashMap2, hashMap3);
                        } else {
                            ttmlSubtitle = ttmlSubtitle2;
                            frameAndTickRate = frameAndTickRate2;
                            try {
                                TtmlNode parseNode = parseNode(newPullParser, ttmlNode, hashMap2, frameAndTickRate);
                                arrayDeque.push(parseNode);
                                if (ttmlNode != null) {
                                    ttmlNode.addChild(parseNode);
                                }
                            } catch (SubtitleDecoderException e) {
                                Log.w(TAG, "Suppressing parser error", e);
                                i2++;
                            }
                        }
                    } else {
                        ttmlSubtitle = ttmlSubtitle2;
                        if (eventType == 4) {
                            ttmlNode.addChild(TtmlNode.buildTextNode(newPullParser.getText()));
                        } else if (eventType == 3) {
                            TtmlSubtitle ttmlSubtitle3 = newPullParser.getName().equals(TtmlNode.TAG_TT) ? new TtmlSubtitle((TtmlNode) arrayDeque.peek(), hashMap, hashMap2, hashMap3) : ttmlSubtitle;
                            arrayDeque.pop();
                            ttsExtent = ttsExtent2;
                            cellResolution = cellResolution2;
                            ttmlSubtitle = ttmlSubtitle3;
                            frameAndTickRate = frameAndTickRate3;
                        }
                        ttsExtent = ttsExtent2;
                        frameAndTickRate = frameAndTickRate3;
                        cellResolution = cellResolution2;
                    }
                    frameAndTickRate3 = frameAndTickRate;
                    cellResolution2 = cellResolution;
                    ttsExtent2 = ttsExtent;
                } else {
                    ttmlSubtitle = ttmlSubtitle2;
                    if (eventType == 2) {
                        i2++;
                    } else if (eventType == 3) {
                        i2--;
                    }
                }
                ttmlSubtitle2 = ttmlSubtitle;
                newPullParser.next();
            }
            return ttmlSubtitle2;
        } catch (XmlPullParserException e2) {
            throw new SubtitleDecoderException("Unable to decode source", e2);
        } catch (IOException e3) {
            throw new IllegalStateException("Unexpected error when reading input.", e3);
        }
    }

    private FrameAndTickRate parseFrameAndTickRates(XmlPullParser xmlPullParser) throws SubtitleDecoderException {
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "frameRate");
        int parseInt = attributeValue != null ? Integer.parseInt(attributeValue) : 30;
        float f = 1.0f;
        String attributeValue2 = xmlPullParser.getAttributeValue(TTP, "frameRateMultiplier");
        if (attributeValue2 != null) {
            String[] split = Util.split(attributeValue2, " ");
            if (split.length == 2) {
                f = ((float) Integer.parseInt(split[0])) / ((float) Integer.parseInt(split[1]));
            } else {
                throw new SubtitleDecoderException("frameRateMultiplier doesn't have 2 parts");
            }
        }
        int i = DEFAULT_FRAME_AND_TICK_RATE.subFrameRate;
        String attributeValue3 = xmlPullParser.getAttributeValue(TTP, "subFrameRate");
        if (attributeValue3 != null) {
            i = Integer.parseInt(attributeValue3);
        }
        int i2 = DEFAULT_FRAME_AND_TICK_RATE.tickRate;
        String attributeValue4 = xmlPullParser.getAttributeValue(TTP, "tickRate");
        if (attributeValue4 != null) {
            i2 = Integer.parseInt(attributeValue4);
        }
        return new FrameAndTickRate(((float) parseInt) * f, i, i2);
    }

    private CellResolution parseCellResolution(XmlPullParser xmlPullParser, CellResolution cellResolution) throws SubtitleDecoderException {
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "cellResolution");
        if (attributeValue == null) {
            return cellResolution;
        }
        Matcher matcher = CELL_RESOLUTION.matcher(attributeValue);
        if (!matcher.matches()) {
            Log.w(TAG, "Ignoring malformed cell resolution: " + attributeValue);
            return cellResolution;
        }
        try {
            int parseInt = Integer.parseInt(matcher.group(1));
            int parseInt2 = Integer.parseInt(matcher.group(2));
            if (parseInt != 0 && parseInt2 != 0) {
                return new CellResolution(parseInt, parseInt2);
            }
            throw new SubtitleDecoderException("Invalid cell resolution " + parseInt + " " + parseInt2);
        } catch (NumberFormatException unused) {
            Log.w(TAG, "Ignoring malformed cell resolution: " + attributeValue);
            return cellResolution;
        }
    }

    private TtsExtent parseTtsExtent(XmlPullParser xmlPullParser) {
        String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_EXTENT);
        if (attributeValue == null) {
            return null;
        }
        Matcher matcher = PIXEL_COORDINATES.matcher(attributeValue);
        if (!matcher.matches()) {
            Log.w(TAG, "Ignoring non-pixel tts extent: " + attributeValue);
            return null;
        }
        try {
            return new TtsExtent(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        } catch (NumberFormatException unused) {
            Log.w(TAG, "Ignoring malformed tts extent: " + attributeValue);
            return null;
        }
    }

    private Map<String, TtmlStyle> parseHeader(XmlPullParser xmlPullParser, Map<String, TtmlStyle> map, CellResolution cellResolution, TtsExtent ttsExtent, Map<String, TtmlRegion> map2, Map<String, String> map3) throws IOException, XmlPullParserException {
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "style")) {
                String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "style");
                TtmlStyle parseStyleAttributes = parseStyleAttributes(xmlPullParser, new TtmlStyle());
                if (attributeValue != null) {
                    for (String str : parseStyleIds(attributeValue)) {
                        parseStyleAttributes.chain(map.get(str));
                    }
                }
                if (parseStyleAttributes.getId() != null) {
                    map.put(parseStyleAttributes.getId(), parseStyleAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "region")) {
                TtmlRegion parseRegionAttributes = parseRegionAttributes(xmlPullParser, cellResolution, ttsExtent);
                if (parseRegionAttributes != null) {
                    map2.put(parseRegionAttributes.id, parseRegionAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, TtmlNode.TAG_METADATA)) {
                parseMetadata(xmlPullParser, map3);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, TtmlNode.TAG_HEAD));
        return map;
    }

    private void parseMetadata(XmlPullParser xmlPullParser, Map<String, String> map) throws IOException, XmlPullParserException {
        String attributeValue;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "image") && (attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "id")) != null) {
                map.put(attributeValue, xmlPullParser.nextText());
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, TtmlNode.TAG_METADATA));
    }

    private TtmlRegion parseRegionAttributes(XmlPullParser xmlPullParser, CellResolution cellResolution, TtsExtent ttsExtent) {
        float f;
        float f2;
        float f3;
        float f4;
        int i;
        String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "id");
        if (attributeValue == null) {
            return null;
        }
        String attributeValue2 = XmlPullParserUtil.getAttributeValue(xmlPullParser, "origin");
        if (attributeValue2 != null) {
            Matcher matcher = PERCENTAGE_COORDINATES.matcher(attributeValue2);
            Matcher matcher2 = PIXEL_COORDINATES.matcher(attributeValue2);
            if (matcher.matches()) {
                try {
                    float parseFloat = Float.parseFloat(matcher.group(1)) / 100.0f;
                    f = Float.parseFloat(matcher.group(2)) / 100.0f;
                    f2 = parseFloat;
                } catch (NumberFormatException unused) {
                    Log.w(TAG, "Ignoring region with malformed origin: " + attributeValue2);
                    return null;
                }
            } else if (!matcher2.matches()) {
                Log.w(TAG, "Ignoring region with unsupported origin: " + attributeValue2);
                return null;
            } else if (ttsExtent == null) {
                Log.w(TAG, "Ignoring region with missing tts:extent: " + attributeValue2);
                return null;
            } else {
                try {
                    int parseInt = Integer.parseInt(matcher2.group(1));
                    int parseInt2 = Integer.parseInt(matcher2.group(2));
                    f2 = ((float) parseInt) / ((float) ttsExtent.width);
                    f = ((float) parseInt2) / ((float) ttsExtent.height);
                } catch (NumberFormatException unused2) {
                    Log.w(TAG, "Ignoring region with malformed origin: " + attributeValue2);
                    return null;
                }
            }
            String attributeValue3 = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_EXTENT);
            if (attributeValue3 != null) {
                Matcher matcher3 = PERCENTAGE_COORDINATES.matcher(attributeValue3);
                Matcher matcher4 = PIXEL_COORDINATES.matcher(attributeValue3);
                if (matcher3.matches()) {
                    try {
                        f3 = Float.parseFloat(matcher3.group(1)) / 100.0f;
                        f4 = Float.parseFloat(matcher3.group(2)) / 100.0f;
                    } catch (NumberFormatException unused3) {
                        Log.w(TAG, "Ignoring region with malformed extent: " + attributeValue2);
                        return null;
                    }
                } else if (!matcher4.matches()) {
                    Log.w(TAG, "Ignoring region with unsupported extent: " + attributeValue2);
                    return null;
                } else if (ttsExtent == null) {
                    Log.w(TAG, "Ignoring region with missing tts:extent: " + attributeValue2);
                    return null;
                } else {
                    try {
                        int parseInt3 = Integer.parseInt(matcher4.group(1));
                        int parseInt4 = Integer.parseInt(matcher4.group(2));
                        float f5 = ((float) parseInt3) / ((float) ttsExtent.width);
                        f4 = ((float) parseInt4) / ((float) ttsExtent.height);
                        f3 = f5;
                    } catch (NumberFormatException unused4) {
                        Log.w(TAG, "Ignoring region with malformed extent: " + attributeValue2);
                        return null;
                    }
                }
                String attributeValue4 = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_DISPLAY_ALIGN);
                if (attributeValue4 != null) {
                    String lowerInvariant = Util.toLowerInvariant(attributeValue4);
                    char c = 65535;
                    int hashCode = lowerInvariant.hashCode();
                    if (hashCode != -1364013995) {
                        if (hashCode == 92734940 && lowerInvariant.equals("after")) {
                            c = 1;
                        }
                    } else if (lowerInvariant.equals("center")) {
                        c = 0;
                    }
                    switch (c) {
                        case 0:
                            f += f4 / 2.0f;
                            i = 1;
                            break;
                        case 1:
                            f += f4;
                            i = 2;
                            break;
                    }
                }
                i = 0;
                return new TtmlRegion(attributeValue, f2, f, 0, i, f3, 1, 1.0f / ((float) cellResolution.rows));
            }
            Log.w(TAG, "Ignoring region without an extent");
            return null;
        }
        Log.w(TAG, "Ignoring region without an origin");
        return null;
    }

    private String[] parseStyleIds(String str) {
        String trim = str.trim();
        return trim.isEmpty() ? new String[0] : Util.split(trim, "\\s+");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00b9, code lost:
        if (r3.equals(com.google.android.exoplayer2.text.ttml.TtmlNode.UNDERLINE) != false) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x012f, code lost:
        if (r3.equals("center") != false) goto L_0x0133;
     */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x021a A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00ea  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.exoplayer2.text.ttml.TtmlStyle parseStyleAttributes(org.xmlpull.v1.XmlPullParser r12, com.google.android.exoplayer2.text.ttml.TtmlStyle r13) {
        /*
            r11 = this;
            int r0 = r12.getAttributeCount()
            r1 = 0
            r2 = r13
            r13 = 0
        L_0x0007:
            if (r13 >= r0) goto L_0x021e
            java.lang.String r3 = r12.getAttributeValue(r13)
            java.lang.String r4 = r12.getAttributeName(r13)
            int r5 = r4.hashCode()
            r6 = 4
            r7 = 2
            r8 = 3
            r9 = -1
            r10 = 1
            switch(r5) {
                case -1550943582: goto L_0x0071;
                case -1224696685: goto L_0x0067;
                case -1065511464: goto L_0x005c;
                case -879295043: goto L_0x0050;
                case -734428249: goto L_0x0046;
                case 3355: goto L_0x003c;
                case 94842723: goto L_0x0032;
                case 365601008: goto L_0x0028;
                case 1287124693: goto L_0x001e;
                default: goto L_0x001d;
            }
        L_0x001d:
            goto L_0x007b
        L_0x001e:
            java.lang.String r5 = "backgroundColor"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x007b
            r4 = 1
            goto L_0x007c
        L_0x0028:
            java.lang.String r5 = "fontSize"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x007b
            r4 = 4
            goto L_0x007c
        L_0x0032:
            java.lang.String r5 = "color"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x007b
            r4 = 2
            goto L_0x007c
        L_0x003c:
            java.lang.String r5 = "id"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x007b
            r4 = 0
            goto L_0x007c
        L_0x0046:
            java.lang.String r5 = "fontWeight"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x007b
            r4 = 5
            goto L_0x007c
        L_0x0050:
            java.lang.String r5 = "textDecoration"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x007b
            r4 = 8
            goto L_0x007c
        L_0x005c:
            java.lang.String r5 = "textAlign"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x007b
            r4 = 7
            goto L_0x007c
        L_0x0067:
            java.lang.String r5 = "fontFamily"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x007b
            r4 = 3
            goto L_0x007c
        L_0x0071:
            java.lang.String r5 = "fontStyle"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x007b
            r4 = 6
            goto L_0x007c
        L_0x007b:
            r4 = -1
        L_0x007c:
            switch(r4) {
                case 0: goto L_0x0205;
                case 1: goto L_0x01e2;
                case 2: goto L_0x01bf;
                case 3: goto L_0x01b6;
                case 4: goto L_0x0194;
                case 5: goto L_0x0184;
                case 6: goto L_0x0174;
                case 7: goto L_0x00f4;
                case 8: goto L_0x0081;
                default: goto L_0x007f;
            }
        L_0x007f:
            goto L_0x021a
        L_0x0081:
            java.lang.String r3 = com.google.android.exoplayer2.util.Util.toLowerInvariant(r3)
            int r4 = r3.hashCode()
            r5 = -1461280213(0xffffffffa8e6a22b, float:-2.5605459E-14)
            if (r4 == r5) goto L_0x00bc
            r5 = -1026963764(0xffffffffc2c9c6cc, float:-100.888275)
            if (r4 == r5) goto L_0x00b2
            r5 = 913457136(0x36723ff0, float:3.6098027E-6)
            if (r4 == r5) goto L_0x00a8
            r5 = 1679736913(0x641ec051, float:1.1713774E22)
            if (r4 == r5) goto L_0x009e
            goto L_0x00c6
        L_0x009e:
            java.lang.String r4 = "linethrough"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00c6
            r7 = 0
            goto L_0x00c7
        L_0x00a8:
            java.lang.String r4 = "nolinethrough"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00c6
            r7 = 1
            goto L_0x00c7
        L_0x00b2:
            java.lang.String r4 = "underline"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00c6
            goto L_0x00c7
        L_0x00bc:
            java.lang.String r4 = "nounderline"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00c6
            r7 = 3
            goto L_0x00c7
        L_0x00c6:
            r7 = -1
        L_0x00c7:
            switch(r7) {
                case 0: goto L_0x00ea;
                case 1: goto L_0x00e0;
                case 2: goto L_0x00d6;
                case 3: goto L_0x00cc;
                default: goto L_0x00ca;
            }
        L_0x00ca:
            goto L_0x021a
        L_0x00cc:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r11.createIfNull(r2)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r2.setUnderline(r1)
            goto L_0x021a
        L_0x00d6:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r11.createIfNull(r2)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r2.setUnderline(r10)
            goto L_0x021a
        L_0x00e0:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r11.createIfNull(r2)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r2.setLinethrough(r1)
            goto L_0x021a
        L_0x00ea:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r11.createIfNull(r2)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r2.setLinethrough(r10)
            goto L_0x021a
        L_0x00f4:
            java.lang.String r3 = com.google.android.exoplayer2.util.Util.toLowerInvariant(r3)
            int r4 = r3.hashCode()
            switch(r4) {
                case -1364013995: goto L_0x0129;
                case 100571: goto L_0x011f;
                case 3317767: goto L_0x0115;
                case 108511772: goto L_0x010b;
                case 109757538: goto L_0x0100;
                default: goto L_0x00ff;
            }
        L_0x00ff:
            goto L_0x0132
        L_0x0100:
            java.lang.String r4 = "start"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0132
            r6 = 1
            goto L_0x0133
        L_0x010b:
            java.lang.String r4 = "right"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0132
            r6 = 2
            goto L_0x0133
        L_0x0115:
            java.lang.String r4 = "left"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0132
            r6 = 0
            goto L_0x0133
        L_0x011f:
            java.lang.String r4 = "end"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0132
            r6 = 3
            goto L_0x0133
        L_0x0129:
            java.lang.String r4 = "center"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0132
            goto L_0x0133
        L_0x0132:
            r6 = -1
        L_0x0133:
            switch(r6) {
                case 0: goto L_0x0168;
                case 1: goto L_0x015c;
                case 2: goto L_0x0150;
                case 3: goto L_0x0144;
                case 4: goto L_0x0138;
                default: goto L_0x0136;
            }
        L_0x0136:
            goto L_0x021a
        L_0x0138:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r11.createIfNull(r2)
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_CENTER
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r2.setTextAlign(r3)
            goto L_0x021a
        L_0x0144:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r11.createIfNull(r2)
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r2.setTextAlign(r3)
            goto L_0x021a
        L_0x0150:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r11.createIfNull(r2)
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r2.setTextAlign(r3)
            goto L_0x021a
        L_0x015c:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r11.createIfNull(r2)
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_NORMAL
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r2.setTextAlign(r3)
            goto L_0x021a
        L_0x0168:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r11.createIfNull(r2)
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_NORMAL
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r2.setTextAlign(r3)
            goto L_0x021a
        L_0x0174:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r11.createIfNull(r2)
            java.lang.String r4 = "italic"
            boolean r3 = r4.equalsIgnoreCase(r3)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r2.setItalic(r3)
            goto L_0x021a
        L_0x0184:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r11.createIfNull(r2)
            java.lang.String r4 = "bold"
            boolean r3 = r4.equalsIgnoreCase(r3)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r2.setBold(r3)
            goto L_0x021a
        L_0x0194:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r4 = r11.createIfNull(r2)     // Catch:{ SubtitleDecoderException -> 0x019f }
            parseFontSize(r3, r4)     // Catch:{ SubtitleDecoderException -> 0x019e }
            r2 = r4
            goto L_0x021a
        L_0x019e:
            r2 = r4
        L_0x019f:
            java.lang.String r4 = "TtmlDecoder"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Failed parsing fontSize value: "
            r5.append(r6)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            com.google.android.exoplayer2.util.Log.w(r4, r3)
            goto L_0x021a
        L_0x01b6:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r11.createIfNull(r2)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r2.setFontFamily(r3)
            goto L_0x021a
        L_0x01bf:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r11.createIfNull(r2)
            int r4 = com.google.android.exoplayer2.util.ColorParser.parseTtmlColor(r3)     // Catch:{ IllegalArgumentException -> 0x01cb }
            r2.setFontColor(r4)     // Catch:{ IllegalArgumentException -> 0x01cb }
            goto L_0x021a
        L_0x01cb:
            java.lang.String r4 = "TtmlDecoder"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Failed parsing color value: "
            r5.append(r6)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            com.google.android.exoplayer2.util.Log.w(r4, r3)
            goto L_0x021a
        L_0x01e2:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r11.createIfNull(r2)
            int r4 = com.google.android.exoplayer2.util.ColorParser.parseTtmlColor(r3)     // Catch:{ IllegalArgumentException -> 0x01ee }
            r2.setBackgroundColor(r4)     // Catch:{ IllegalArgumentException -> 0x01ee }
            goto L_0x021a
        L_0x01ee:
            java.lang.String r4 = "TtmlDecoder"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Failed parsing background value: "
            r5.append(r6)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            com.google.android.exoplayer2.util.Log.w(r4, r3)
            goto L_0x021a
        L_0x0205:
            java.lang.String r4 = "style"
            java.lang.String r5 = r12.getName()
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x021a
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r11.createIfNull(r2)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r2 = r2.setId(r3)
        L_0x021a:
            int r13 = r13 + 1
            goto L_0x0007
        L_0x021e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TtmlDecoder.parseStyleAttributes(org.xmlpull.v1.XmlPullParser, com.google.android.exoplayer2.text.ttml.TtmlStyle):com.google.android.exoplayer2.text.ttml.TtmlStyle");
    }

    private TtmlStyle createIfNull(TtmlStyle ttmlStyle) {
        return ttmlStyle == null ? new TtmlStyle() : ttmlStyle;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.exoplayer2.text.ttml.TtmlNode parseNode(org.xmlpull.v1.XmlPullParser r21, com.google.android.exoplayer2.text.ttml.TtmlNode r22, java.util.Map<java.lang.String, com.google.android.exoplayer2.text.ttml.TtmlRegion> r23, com.google.android.exoplayer2.text.ttml.TtmlDecoder.FrameAndTickRate r24) throws com.google.android.exoplayer2.text.SubtitleDecoderException {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r22
            r3 = r24
            java.lang.String r4 = ""
            int r5 = r21.getAttributeCount()
            r6 = 0
            com.google.android.exoplayer2.text.ttml.TtmlStyle r12 = r0.parseStyleAttributes(r1, r6)
            r17 = r6
            r18 = r17
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r15 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6 = r4
            r4 = 0
        L_0x0028:
            if (r4 >= r5) goto L_0x00bf
            java.lang.String r7 = r1.getAttributeName(r4)
            java.lang.String r8 = r1.getAttributeValue(r4)
            int r19 = r7.hashCode()
            switch(r19) {
                case -934795532: goto L_0x006d;
                case 99841: goto L_0x0063;
                case 100571: goto L_0x0059;
                case 93616297: goto L_0x004f;
                case 109780401: goto L_0x0044;
                case 1292595405: goto L_0x003a;
                default: goto L_0x0039;
            }
        L_0x0039:
            goto L_0x0077
        L_0x003a:
            java.lang.String r9 = "backgroundImage"
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L_0x0077
            r7 = 5
            goto L_0x0078
        L_0x0044:
            java.lang.String r9 = "style"
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L_0x0077
            r7 = 3
            goto L_0x0078
        L_0x004f:
            java.lang.String r9 = "begin"
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L_0x0077
            r7 = 0
            goto L_0x0078
        L_0x0059:
            java.lang.String r9 = "end"
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L_0x0077
            r7 = 1
            goto L_0x0078
        L_0x0063:
            java.lang.String r9 = "dur"
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L_0x0077
            r7 = 2
            goto L_0x0078
        L_0x006d:
            java.lang.String r9 = "region"
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L_0x0077
            r7 = 4
            goto L_0x0078
        L_0x0077:
            r7 = -1
        L_0x0078:
            switch(r7) {
                case 0: goto L_0x00b4;
                case 1: goto L_0x00ac;
                case 2: goto L_0x00a4;
                case 3: goto L_0x0098;
                case 4: goto L_0x008e;
                case 5: goto L_0x007c;
                default: goto L_0x007b;
            }
        L_0x007b:
            goto L_0x008b
        L_0x007c:
            java.lang.String r7 = "#"
            boolean r7 = r8.startsWith(r7)
            if (r7 == 0) goto L_0x008b
            r7 = 1
            java.lang.String r7 = r8.substring(r7)
            r17 = r7
        L_0x008b:
            r7 = r23
            goto L_0x00bb
        L_0x008e:
            r7 = r23
            boolean r9 = r7.containsKey(r8)
            if (r9 == 0) goto L_0x00bb
            r6 = r8
            goto L_0x00bb
        L_0x0098:
            r7 = r23
            java.lang.String[] r8 = r0.parseStyleIds(r8)
            int r9 = r8.length
            if (r9 <= 0) goto L_0x00bb
            r18 = r8
            goto L_0x00bb
        L_0x00a4:
            r7 = r23
            long r8 = parseTimeExpression(r8, r3)
            r15 = r8
            goto L_0x00bb
        L_0x00ac:
            r7 = r23
            long r8 = parseTimeExpression(r8, r3)
            r13 = r8
            goto L_0x00bb
        L_0x00b4:
            r7 = r23
            long r8 = parseTimeExpression(r8, r3)
            r10 = r8
        L_0x00bb:
            int r4 = r4 + 1
            goto L_0x0028
        L_0x00bf:
            if (r2 == 0) goto L_0x00db
            long r3 = r2.startTimeUs
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r5 == 0) goto L_0x00e0
            int r3 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x00d3
            long r3 = r2.startTimeUs
            long r10 = r10 + r3
        L_0x00d3:
            int r3 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x00e0
            long r3 = r2.startTimeUs
            long r13 = r13 + r3
            goto L_0x00e0
        L_0x00db:
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x00e0:
            int r3 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1))
            if (r3 != 0) goto L_0x00f6
            int r3 = (r15 > r7 ? 1 : (r15 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x00eb
            long r15 = r15 + r10
            r13 = r15
            goto L_0x00f6
        L_0x00eb:
            if (r2 == 0) goto L_0x00f6
            long r3 = r2.endTimeUs
            int r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r5 == 0) goto L_0x00f6
            long r2 = r2.endTimeUs
            r13 = r2
        L_0x00f6:
            java.lang.String r7 = r21.getName()
            r8 = r10
            r10 = r13
            r13 = r18
            r14 = r6
            r15 = r17
            com.google.android.exoplayer2.text.ttml.TtmlNode r1 = com.google.android.exoplayer2.text.ttml.TtmlNode.buildNode(r7, r8, r10, r12, r13, r14, r15)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TtmlDecoder.parseNode(org.xmlpull.v1.XmlPullParser, com.google.android.exoplayer2.text.ttml.TtmlNode, java.util.Map, com.google.android.exoplayer2.text.ttml.TtmlDecoder$FrameAndTickRate):com.google.android.exoplayer2.text.ttml.TtmlNode");
    }

    private static boolean isSupportedTag(String str) {
        return str.equals(TtmlNode.TAG_TT) || str.equals(TtmlNode.TAG_HEAD) || str.equals("body") || str.equals("div") || str.equals("p") || str.equals("span") || str.equals(TtmlNode.TAG_BR) || str.equals("style") || str.equals(TtmlNode.TAG_STYLING) || str.equals("layout") || str.equals("region") || str.equals(TtmlNode.TAG_METADATA) || str.equals("image") || str.equals("data") || str.equals(TtmlNode.TAG_INFORMATION);
    }

    private static void parseFontSize(String str, TtmlStyle ttmlStyle) throws SubtitleDecoderException {
        Matcher matcher;
        String[] split = Util.split(str, "\\s+");
        if (split.length == 1) {
            matcher = FONT_SIZE.matcher(str);
        } else if (split.length == 2) {
            matcher = FONT_SIZE.matcher(split[1]);
            Log.w(TAG, "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        } else {
            throw new SubtitleDecoderException("Invalid number of entries for fontSize: " + split.length + ".");
        }
        if (matcher.matches()) {
            String group = matcher.group(3);
            char c = 65535;
            int hashCode = group.hashCode();
            if (hashCode != 37) {
                if (hashCode != 3240) {
                    if (hashCode == 3592 && group.equals("px")) {
                        c = 0;
                    }
                } else if (group.equals("em")) {
                    c = 1;
                }
            } else if (group.equals(Operators.MOD)) {
                c = 2;
            }
            switch (c) {
                case 0:
                    ttmlStyle.setFontSizeUnit(1);
                    break;
                case 1:
                    ttmlStyle.setFontSizeUnit(2);
                    break;
                case 2:
                    ttmlStyle.setFontSizeUnit(3);
                    break;
                default:
                    throw new SubtitleDecoderException("Invalid unit for fontSize: '" + group + "'.");
            }
            ttmlStyle.setFontSize(Float.valueOf(matcher.group(1)).floatValue());
            return;
        }
        throw new SubtitleDecoderException("Invalid expression for fontSize: '" + str + "'.");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c3, code lost:
        if (r14.equals("t") != false) goto L_0x00f9;
     */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0119  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long parseTimeExpression(java.lang.String r14, com.google.android.exoplayer2.text.ttml.TtmlDecoder.FrameAndTickRate r15) throws com.google.android.exoplayer2.text.SubtitleDecoderException {
        /*
            java.util.regex.Pattern r0 = CLOCK_TIME
            java.util.regex.Matcher r0 = r0.matcher(r14)
            boolean r1 = r0.matches()
            r2 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            r4 = 5
            r5 = 4
            r6 = 3
            r7 = 2
            r8 = 1
            if (r1 == 0) goto L_0x008b
            java.lang.String r14 = r0.group(r8)
            long r8 = java.lang.Long.parseLong(r14)
            r10 = 3600(0xe10, double:1.7786E-320)
            long r8 = r8 * r10
            double r8 = (double) r8
            java.lang.String r14 = r0.group(r7)
            long r10 = java.lang.Long.parseLong(r14)
            r12 = 60
            long r10 = r10 * r12
            double r10 = (double) r10
            java.lang.Double.isNaN(r8)
            java.lang.Double.isNaN(r10)
            double r8 = r8 + r10
            java.lang.String r14 = r0.group(r6)
            long r6 = java.lang.Long.parseLong(r14)
            double r6 = (double) r6
            java.lang.Double.isNaN(r6)
            double r8 = r8 + r6
            java.lang.String r14 = r0.group(r5)
            r5 = 0
            if (r14 == 0) goto L_0x0051
            double r10 = java.lang.Double.parseDouble(r14)
            goto L_0x0052
        L_0x0051:
            r10 = r5
        L_0x0052:
            r14 = 0
            double r8 = r8 + r10
            java.lang.String r14 = r0.group(r4)
            if (r14 == 0) goto L_0x0064
            long r10 = java.lang.Long.parseLong(r14)
            float r14 = (float) r10
            float r1 = r15.effectiveFrameRate
            float r14 = r14 / r1
            double r10 = (double) r14
            goto L_0x0065
        L_0x0064:
            r10 = r5
        L_0x0065:
            r14 = 0
            double r8 = r8 + r10
            r14 = 6
            java.lang.String r14 = r0.group(r14)
            if (r14 == 0) goto L_0x0085
            long r0 = java.lang.Long.parseLong(r14)
            double r0 = (double) r0
            int r14 = r15.subFrameRate
            double r4 = (double) r14
            java.lang.Double.isNaN(r0)
            java.lang.Double.isNaN(r4)
            double r0 = r0 / r4
            float r14 = r15.effectiveFrameRate
            double r14 = (double) r14
            java.lang.Double.isNaN(r14)
            double r5 = r0 / r14
        L_0x0085:
            r14 = 0
            double r8 = r8 + r5
            double r8 = r8 * r2
            long r14 = (long) r8
            return r14
        L_0x008b:
            java.util.regex.Pattern r0 = OFFSET_TIME
            java.util.regex.Matcher r0 = r0.matcher(r14)
            boolean r1 = r0.matches()
            if (r1 == 0) goto L_0x0124
            java.lang.String r14 = r0.group(r8)
            double r9 = java.lang.Double.parseDouble(r14)
            java.lang.String r14 = r0.group(r7)
            r0 = -1
            int r1 = r14.hashCode()
            r11 = 102(0x66, float:1.43E-43)
            if (r1 == r11) goto L_0x00ee
            r5 = 104(0x68, float:1.46E-43)
            if (r1 == r5) goto L_0x00e4
            r5 = 109(0x6d, float:1.53E-43)
            if (r1 == r5) goto L_0x00da
            r5 = 3494(0xda6, float:4.896E-42)
            if (r1 == r5) goto L_0x00d0
            switch(r1) {
                case 115: goto L_0x00c6;
                case 116: goto L_0x00bc;
                default: goto L_0x00bb;
            }
        L_0x00bb:
            goto L_0x00f8
        L_0x00bc:
            java.lang.String r1 = "t"
            boolean r14 = r14.equals(r1)
            if (r14 == 0) goto L_0x00f8
            goto L_0x00f9
        L_0x00c6:
            java.lang.String r1 = "s"
            boolean r14 = r14.equals(r1)
            if (r14 == 0) goto L_0x00f8
            r4 = 2
            goto L_0x00f9
        L_0x00d0:
            java.lang.String r1 = "ms"
            boolean r14 = r14.equals(r1)
            if (r14 == 0) goto L_0x00f8
            r4 = 3
            goto L_0x00f9
        L_0x00da:
            java.lang.String r1 = "m"
            boolean r14 = r14.equals(r1)
            if (r14 == 0) goto L_0x00f8
            r4 = 1
            goto L_0x00f9
        L_0x00e4:
            java.lang.String r1 = "h"
            boolean r14 = r14.equals(r1)
            if (r14 == 0) goto L_0x00f8
            r4 = 0
            goto L_0x00f9
        L_0x00ee:
            java.lang.String r1 = "f"
            boolean r14 = r14.equals(r1)
            if (r14 == 0) goto L_0x00f8
            r4 = 4
            goto L_0x00f9
        L_0x00f8:
            r4 = -1
        L_0x00f9:
            switch(r4) {
                case 0: goto L_0x0119;
                case 1: goto L_0x0114;
                case 2: goto L_0x0120;
                case 3: goto L_0x010d;
                case 4: goto L_0x0105;
                case 5: goto L_0x00fd;
                default: goto L_0x00fc;
            }
        L_0x00fc:
            goto L_0x0120
        L_0x00fd:
            int r14 = r15.tickRate
            double r14 = (double) r14
            java.lang.Double.isNaN(r14)
            double r9 = r9 / r14
            goto L_0x0120
        L_0x0105:
            float r14 = r15.effectiveFrameRate
            double r14 = (double) r14
            java.lang.Double.isNaN(r14)
            double r9 = r9 / r14
            goto L_0x0120
        L_0x010d:
            r14 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r9 = r9 / r14
            goto L_0x0120
        L_0x0114:
            r14 = 4633641066610819072(0x404e000000000000, double:60.0)
            double r9 = r9 * r14
            goto L_0x0120
        L_0x0119:
            r14 = 4660134898793709568(0x40ac200000000000, double:3600.0)
            double r9 = r9 * r14
        L_0x0120:
            double r9 = r9 * r2
            long r14 = (long) r9
            return r14
        L_0x0124:
            com.google.android.exoplayer2.text.SubtitleDecoderException r15 = new com.google.android.exoplayer2.text.SubtitleDecoderException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Malformed time expression: "
            r0.append(r1)
            r0.append(r14)
            java.lang.String r14 = r0.toString()
            r15.<init>((java.lang.String) r14)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TtmlDecoder.parseTimeExpression(java.lang.String, com.google.android.exoplayer2.text.ttml.TtmlDecoder$FrameAndTickRate):long");
    }

    private static final class FrameAndTickRate {
        final float effectiveFrameRate;
        final int subFrameRate;
        final int tickRate;

        FrameAndTickRate(float f, int i, int i2) {
            this.effectiveFrameRate = f;
            this.subFrameRate = i;
            this.tickRate = i2;
        }
    }

    private static final class CellResolution {
        final int columns;
        final int rows;

        CellResolution(int i, int i2) {
            this.columns = i;
            this.rows = i2;
        }
    }

    private static final class TtsExtent {
        final int height;
        final int width;

        TtsExtent(int i, int i2) {
            this.width = i;
            this.height = i2;
        }
    }
}
