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

public final class ActivityConf extends Message<ActivityConf, Builder> {
    public static final ProtoAdapter<ActivityConf> ADAPTER = new ProtoAdapter_ActivityConf();
    public static final Integer DEFAULT_DURATION = 0;
    public static final Integer DEFAULT_ENDTIME = 0;
    public static final Integer DEFAULT_STARTTIME = 0;
    public static final String DEFAULT_URL = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 4)
    public final Integer duration;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 3)
    public final Integer endTime;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 2)
    public final Integer startTime;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String url;

    public ActivityConf(String str, Integer num, Integer num2, Integer num3) {
        this(str, num, num2, num3, ByteString.EMPTY);
    }

    public ActivityConf(String str, Integer num, Integer num2, Integer num3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.url = str;
        this.startTime = num;
        this.endTime = num2;
        this.duration = num3;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.url = this.url;
        builder.startTime = this.startTime;
        builder.endTime = this.endTime;
        builder.duration = this.duration;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ActivityConf)) {
            return false;
        }
        ActivityConf activityConf = (ActivityConf) obj;
        if (!Internal.equals(unknownFields(), activityConf.unknownFields()) || !Internal.equals(this.url, activityConf.url) || !Internal.equals(this.startTime, activityConf.startTime) || !Internal.equals(this.endTime, activityConf.endTime) || !Internal.equals(this.duration, activityConf.duration)) {
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
        int hashCode = ((((((unknownFields().hashCode() * 37) + (this.url != null ? this.url.hashCode() : 0)) * 37) + (this.startTime != null ? this.startTime.hashCode() : 0)) * 37) + (this.endTime != null ? this.endTime.hashCode() : 0)) * 37;
        if (this.duration != null) {
            i2 = this.duration.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.url != null) {
            sb.append(", url=");
            sb.append(this.url);
        }
        if (this.startTime != null) {
            sb.append(", startTime=");
            sb.append(this.startTime);
        }
        if (this.endTime != null) {
            sb.append(", endTime=");
            sb.append(this.endTime);
        }
        if (this.duration != null) {
            sb.append(", duration=");
            sb.append(this.duration);
        }
        StringBuilder replace = sb.replace(0, 2, "ActivityConf{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<ActivityConf, Builder> {
        public Integer duration;
        public Integer endTime;
        public Integer startTime;
        public String url;

        public Builder url(String str) {
            this.url = str;
            return this;
        }

        public Builder startTime(Integer num) {
            this.startTime = num;
            return this;
        }

        public Builder endTime(Integer num) {
            this.endTime = num;
            return this;
        }

        public Builder duration(Integer num) {
            this.duration = num;
            return this;
        }

        public ActivityConf build() {
            return new ActivityConf(this.url, this.startTime, this.endTime, this.duration, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_ActivityConf extends ProtoAdapter<ActivityConf> {
        ProtoAdapter_ActivityConf() {
            super(FieldEncoding.LENGTH_DELIMITED, ActivityConf.class);
        }

        public int encodedSize(ActivityConf activityConf) {
            int i = 0;
            int encodedSizeWithTag = (activityConf.url != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, activityConf.url) : 0) + (activityConf.startTime != null ? ProtoAdapter.INT32.encodedSizeWithTag(2, activityConf.startTime) : 0) + (activityConf.endTime != null ? ProtoAdapter.INT32.encodedSizeWithTag(3, activityConf.endTime) : 0);
            if (activityConf.duration != null) {
                i = ProtoAdapter.INT32.encodedSizeWithTag(4, activityConf.duration);
            }
            return encodedSizeWithTag + i + activityConf.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, ActivityConf activityConf) throws IOException {
            if (activityConf.url != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, activityConf.url);
            }
            if (activityConf.startTime != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 2, activityConf.startTime);
            }
            if (activityConf.endTime != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 3, activityConf.endTime);
            }
            if (activityConf.duration != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 4, activityConf.duration);
            }
            protoWriter.writeBytes(activityConf.unknownFields());
        }

        public ActivityConf decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.url(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.startTime(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 3:
                            builder.endTime(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 4:
                            builder.duration(ProtoAdapter.INT32.decode(protoReader));
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

        public ActivityConf redact(ActivityConf activityConf) {
            Builder newBuilder = activityConf.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
