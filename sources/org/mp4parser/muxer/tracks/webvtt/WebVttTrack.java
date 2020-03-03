package org.mp4parser.muxer.tracks.webvtt;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mp4parser.Box;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part30.WebVTTConfigurationBox;
import org.mp4parser.boxes.iso14496.part30.WebVTTSampleEntry;
import org.mp4parser.boxes.iso14496.part30.WebVTTSourceLabelBox;
import org.mp4parser.muxer.AbstractTrack;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.TrackMetaData;
import org.mp4parser.muxer.tracks.webvtt.sampleboxes.CuePayloadBox;
import org.mp4parser.muxer.tracks.webvtt.sampleboxes.CueSettingsBox;
import org.mp4parser.muxer.tracks.webvtt.sampleboxes.VTTCueBox;
import org.mp4parser.muxer.tracks.webvtt.sampleboxes.VTTEmptyCueBox;
import org.mp4parser.tools.ByteBufferByteChannel;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.Mp4Arrays;

public class WebVttTrack extends AbstractTrack {
    private static final String i = "^﻿?WEBVTT((\\u0020|\t).*)?$";
    private static final Pattern j = Pattern.compile(i);
    private static final String k = "\\S*[:=]\\S*";
    private static final Pattern l = Pattern.compile(k);
    private static final String m = "^(?!.*(-->)).*$";
    private static final Pattern n = Pattern.compile(m);
    private static final String o = "(\\d+:)?[0-5]\\d:[0-5]\\d\\.\\d{3}";
    private static final Pattern p = Pattern.compile(o);
    private static final String q = "\\S*:\\S*";
    private static final Pattern r = Pattern.compile(q);
    private static final Sample s = new Sample() {

        /* renamed from: a  reason: collision with root package name */
        ByteBuffer f4058a;

        {
            VTTEmptyCueBox vTTEmptyCueBox = new VTTEmptyCueBox();
            this.f4058a = ByteBuffer.allocate(CastUtils.a(vTTEmptyCueBox.c()));
            try {
                vTTEmptyCueBox.b(new ByteBufferByteChannel(this.f4058a));
                this.f4058a.rewind();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void a(WritableByteChannel writableByteChannel) throws IOException {
            writableByteChannel.write(this.f4058a.duplicate());
        }

        public long a() {
            return (long) this.f4058a.remaining();
        }

        public ByteBuffer b() {
            return this.f4058a.duplicate();
        }
    };
    TrackMetaData d = new TrackMetaData();
    SampleDescriptionBox e;
    List<Sample> f = new ArrayList();
    long[] g = new long[0];
    WebVTTSampleEntry h;

    public void close() throws IOException {
    }

    public String p() {
        return "text";
    }

    public WebVttTrack(InputStream inputStream, String str, Locale locale) throws IOException {
        super(str);
        this.d.a(1000);
        this.d.a(locale.getISO3Language());
        this.e = new SampleDescriptionBox();
        this.h = new WebVTTSampleEntry();
        this.e.a((Box) this.h);
        WebVTTConfigurationBox webVTTConfigurationBox = new WebVTTConfigurationBox();
        this.h.a((Box) webVTTConfigurationBox);
        this.h.a((Box) new WebVTTSourceLabelBox());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String readLine = bufferedReader.readLine();
        if (readLine == null || !j.matcher(readLine).matches()) {
            throw new IOException("Expected WEBVTT. Got " + readLine);
        }
        webVTTConfigurationBox.a(webVTTConfigurationBox.d() + "\n" + readLine);
        while (true) {
            String readLine2 = bufferedReader.readLine();
            if (readLine2 == null) {
                throw new IOException("Expected an empty line after webvtt header");
            } else if (readLine2.isEmpty()) {
                long j2 = 0;
                while (true) {
                    String readLine3 = bufferedReader.readLine();
                    if (readLine3 == null) {
                        return;
                    }
                    if (!"".equals(readLine3.trim())) {
                        readLine3 = n.matcher(readLine3).find() ? bufferedReader.readLine() : readLine3;
                        Matcher matcher = p.matcher(readLine3);
                        if (matcher.find()) {
                            long a2 = a(matcher.group());
                            if (matcher.find()) {
                                String group = matcher.group();
                                long a3 = a(group);
                                Matcher matcher2 = r.matcher(readLine3.substring(readLine3.indexOf(group) + group.length()));
                                String str2 = null;
                                while (matcher2.find()) {
                                    str2 = matcher2.group();
                                }
                                StringBuilder sb = new StringBuilder();
                                while (true) {
                                    String readLine4 = bufferedReader.readLine();
                                    if (readLine4 != null && !readLine4.isEmpty()) {
                                        if (sb.length() > 0) {
                                            sb.append("\n");
                                        }
                                        sb.append(readLine4.trim());
                                    }
                                }
                                if (a2 != j2) {
                                    this.g = Mp4Arrays.a(this.g, a2 - j2);
                                    this.f.add(s);
                                }
                                this.g = Mp4Arrays.a(this.g, a3 - a2);
                                VTTCueBox vTTCueBox = new VTTCueBox();
                                if (str2 != null) {
                                    CueSettingsBox cueSettingsBox = new CueSettingsBox();
                                    cueSettingsBox.a(str2);
                                    vTTCueBox.a(cueSettingsBox);
                                }
                                CuePayloadBox cuePayloadBox = new CuePayloadBox();
                                cuePayloadBox.a(sb.toString());
                                vTTCueBox.a(cuePayloadBox);
                                this.f.add(new BoxBearingSample(Collections.singletonList(vTTCueBox)));
                                j2 = a3;
                            } else {
                                throw new IOException("Expected cue end time: " + readLine3);
                            }
                        } else {
                            throw new IOException("Expected cue start time: " + readLine3);
                        }
                    }
                }
            } else if (l.matcher(readLine2).find()) {
                webVTTConfigurationBox.a(webVTTConfigurationBox.d() + "\n" + readLine2);
            } else {
                throw new IOException("Expected WebVTT metadata header. Got " + readLine2);
            }
        }
    }

    private static long a(String str) throws NumberFormatException {
        if (str.matches(o)) {
            String[] split = str.split("\\.", 2);
            long j2 = 0;
            for (String parseLong : split[0].split(":")) {
                j2 = (j2 * 60) + Long.parseLong(parseLong);
            }
            return (j2 * 1000) + Long.parseLong(split[1]);
        }
        throw new NumberFormatException("has invalid format");
    }

    public SampleDescriptionBox n() {
        return this.e;
    }

    public long[] m() {
        long[] jArr = new long[this.g.length];
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2] = (this.g[i2] * this.d.b()) / 1000;
        }
        return jArr;
    }

    public TrackMetaData o() {
        return this.d;
    }

    public List<Sample> l() {
        return this.f;
    }

    private static class BoxBearingSample implements Sample {

        /* renamed from: a  reason: collision with root package name */
        List<Box> f4059a;

        public BoxBearingSample(List<Box> list) {
            this.f4059a = list;
        }

        public void a(WritableByteChannel writableByteChannel) throws IOException {
            for (Box b : this.f4059a) {
                b.b(writableByteChannel);
            }
        }

        public long a() {
            long j = 0;
            for (Box c : this.f4059a) {
                j += c.c();
            }
            return j;
        }

        public ByteBuffer b() {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                a(Channels.newChannel(byteArrayOutputStream));
                return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
