package com.mi.global.shop.model.app;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import okio.ByteString;

public final class StPage extends Message<StPage, Builder> {
    public static final ProtoAdapter<StPage> ADAPTER = new ProtoAdapter_StPage();
    public static final Integer DEFAULT_DURATION = 0;
    public static final Integer DEFAULT_ENDTIME = 0;
    public static final String DEFAULT_GIF = "";
    public static final String DEFAULT_IMG = "";
    public static final String DEFAULT_OPENTYPE = "";
    public static final Integer DEFAULT_STARTTIME = 0;
    public static final String DEFAULT_URL = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 5)
    public final Integer duration;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 2)
    public final Integer endTime;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String gif;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String img;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String openType;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer startTime;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String url;

    public StPage(Integer num, Integer num2, String str, String str2, Integer num3, String str3, String str4) {
        this(num, num2, str, str2, num3, str3, str4, ByteString.EMPTY);
    }

    public StPage(Integer num, Integer num2, String str, String str2, Integer num3, String str3, String str4, ByteString byteString) {
        super(ADAPTER, byteString);
        this.startTime = num;
        this.endTime = num2;
        this.url = str;
        this.img = str2;
        this.duration = num3;
        this.openType = str3;
        this.gif = str4;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.startTime = this.startTime;
        builder.endTime = this.endTime;
        builder.url = this.url;
        builder.img = this.img;
        builder.duration = this.duration;
        builder.openType = this.openType;
        builder.gif = this.gif;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StPage)) {
            return false;
        }
        StPage stPage = (StPage) obj;
        if (!Internal.equals(unknownFields(), stPage.unknownFields()) || !Internal.equals(this.startTime, stPage.startTime) || !Internal.equals(this.endTime, stPage.endTime) || !Internal.equals(this.url, stPage.url) || !Internal.equals(this.img, stPage.img) || !Internal.equals(this.duration, stPage.duration) || !Internal.equals(this.openType, stPage.openType) || !Internal.equals(this.gif, stPage.gif)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int i2 = 0;
        int hashCode = ((((((((((((unknownFields().hashCode() * 37) + (this.startTime != null ? this.startTime.hashCode() : 0)) * 37) + (this.endTime != null ? this.endTime.hashCode() : 0)) * 37) + (this.url != null ? this.url.hashCode() : 0)) * 37) + (this.img != null ? this.img.hashCode() : 0)) * 37) + (this.duration != null ? this.duration.hashCode() : 0)) * 37) + (this.openType != null ? this.openType.hashCode() : 0)) * 37;
        if (this.gif != null) {
            i2 = this.gif.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.startTime != null) {
            sb.append(", startTime=");
            sb.append(this.startTime);
        }
        if (this.endTime != null) {
            sb.append(", endTime=");
            sb.append(this.endTime);
        }
        if (this.url != null) {
            sb.append(", url=");
            sb.append(this.url);
        }
        if (this.img != null) {
            sb.append(", img=");
            sb.append(this.img);
        }
        if (this.duration != null) {
            sb.append(", duration=");
            sb.append(this.duration);
        }
        if (this.openType != null) {
            sb.append(", openType=");
            sb.append(this.openType);
        }
        if (this.gif != null) {
            sb.append(", gif=");
            sb.append(this.gif);
        }
        StringBuilder replace = sb.replace(0, 2, "StPage{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<StPage, Builder> {
        public Integer duration;
        public Integer endTime;
        public String gif;
        public String img;
        public String openType;
        public Integer startTime;
        public String url;

        public Builder startTime(Integer num) {
            this.startTime = num;
            return this;
        }

        public Builder endTime(Integer num) {
            this.endTime = num;
            return this;
        }

        public Builder url(String str) {
            this.url = str;
            return this;
        }

        public Builder img(String str) {
            this.img = str;
            return this;
        }

        public Builder duration(Integer num) {
            this.duration = num;
            return this;
        }

        public Builder openType(String str) {
            this.openType = str;
            return this;
        }

        public Builder gif(String str) {
            this.gif = str;
            return this;
        }

        public StPage build() {
            return new StPage(this.startTime, this.endTime, this.url, this.img, this.duration, this.openType, this.gif, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_StPage extends ProtoAdapter<StPage> {
        ProtoAdapter_StPage() {
            super(FieldEncoding.LENGTH_DELIMITED, StPage.class);
        }

        public int encodedSize(StPage stPage) {
            int i = 0;
            int encodedSizeWithTag = (stPage.startTime != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, stPage.startTime) : 0) + (stPage.endTime != null ? ProtoAdapter.INT32.encodedSizeWithTag(2, stPage.endTime) : 0) + (stPage.url != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, stPage.url) : 0) + (stPage.img != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, stPage.img) : 0) + (stPage.duration != null ? ProtoAdapter.INT32.encodedSizeWithTag(5, stPage.duration) : 0) + (stPage.openType != null ? ProtoAdapter.STRING.encodedSizeWithTag(6, stPage.openType) : 0);
            if (stPage.gif != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(7, stPage.gif);
            }
            return encodedSizeWithTag + i + stPage.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, StPage stPage) throws IOException {
            if (stPage.startTime != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, stPage.startTime);
            }
            if (stPage.endTime != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 2, stPage.endTime);
            }
            if (stPage.url != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, stPage.url);
            }
            if (stPage.img != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, stPage.img);
            }
            if (stPage.duration != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 5, stPage.duration);
            }
            if (stPage.openType != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, stPage.openType);
            }
            if (stPage.gif != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, stPage.gif);
            }
            protoWriter.writeBytes(stPage.unknownFields());
        }

        public StPage decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.startTime(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 2:
                            builder.endTime(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 3:
                            builder.url(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.img(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 5:
                            builder.duration(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 6:
                            builder.openType(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 7:
                            builder.gif(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        default:
                            FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                            builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                            break;
                    }
                } else {
                    protoReader.endMessage(beginMessage);
                    return builder.build();
                }
            }
        }

        public StPage redact(StPage stPage) {
            Builder newBuilder = stPage.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
