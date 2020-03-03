package com.mi.global.shop.model.common;

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

public final class ReductionData extends Message<ReductionData, Builder> {
    public static final ProtoAdapter<ReductionData> ADAPTER = new ProtoAdapter_ReductionData();
    public static final String DEFAULT_ACTIVITY_NAME = "";
    public static final String DEFAULT_ACTNAME = "";
    public static final String DEFAULT_REDUCEMONEY = "";
    public static final String DEFAULT_REDUCEMONEYSINGLE = "";
    public static final String DEFAULT_REDUCEMONEYSINGLE_TXT = "";
    public static final String DEFAULT_REDUCEMONEY_TXT = "";
    public static final Integer DEFAULT_TIMES = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String actName;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String activity_name;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String reduceMoney;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String reduceMoneySingle;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String reduceMoneySingle_txt;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final String reduceMoney_txt;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 3)
    public final Integer times;

    public ReductionData(String str, String str2, Integer num, String str3, String str4, String str5, String str6) {
        this(str, str2, num, str3, str4, str5, str6, ByteString.EMPTY);
    }

    public ReductionData(String str, String str2, Integer num, String str3, String str4, String str5, String str6, ByteString byteString) {
        super(ADAPTER, byteString);
        this.actName = str;
        this.reduceMoneySingle = str2;
        this.times = num;
        this.reduceMoney = str3;
        this.activity_name = str4;
        this.reduceMoneySingle_txt = str5;
        this.reduceMoney_txt = str6;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.actName = this.actName;
        builder.reduceMoneySingle = this.reduceMoneySingle;
        builder.times = this.times;
        builder.reduceMoney = this.reduceMoney;
        builder.activity_name = this.activity_name;
        builder.reduceMoneySingle_txt = this.reduceMoneySingle_txt;
        builder.reduceMoney_txt = this.reduceMoney_txt;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ReductionData)) {
            return false;
        }
        ReductionData reductionData = (ReductionData) obj;
        if (!Internal.equals(unknownFields(), reductionData.unknownFields()) || !Internal.equals(this.actName, reductionData.actName) || !Internal.equals(this.reduceMoneySingle, reductionData.reduceMoneySingle) || !Internal.equals(this.times, reductionData.times) || !Internal.equals(this.reduceMoney, reductionData.reduceMoney) || !Internal.equals(this.activity_name, reductionData.activity_name) || !Internal.equals(this.reduceMoneySingle_txt, reductionData.reduceMoneySingle_txt) || !Internal.equals(this.reduceMoney_txt, reductionData.reduceMoney_txt)) {
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
        int hashCode = ((((((((((((unknownFields().hashCode() * 37) + (this.actName != null ? this.actName.hashCode() : 0)) * 37) + (this.reduceMoneySingle != null ? this.reduceMoneySingle.hashCode() : 0)) * 37) + (this.times != null ? this.times.hashCode() : 0)) * 37) + (this.reduceMoney != null ? this.reduceMoney.hashCode() : 0)) * 37) + (this.activity_name != null ? this.activity_name.hashCode() : 0)) * 37) + (this.reduceMoneySingle_txt != null ? this.reduceMoneySingle_txt.hashCode() : 0)) * 37;
        if (this.reduceMoney_txt != null) {
            i2 = this.reduceMoney_txt.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.actName != null) {
            sb.append(", actName=");
            sb.append(this.actName);
        }
        if (this.reduceMoneySingle != null) {
            sb.append(", reduceMoneySingle=");
            sb.append(this.reduceMoneySingle);
        }
        if (this.times != null) {
            sb.append(", times=");
            sb.append(this.times);
        }
        if (this.reduceMoney != null) {
            sb.append(", reduceMoney=");
            sb.append(this.reduceMoney);
        }
        if (this.activity_name != null) {
            sb.append(", activity_name=");
            sb.append(this.activity_name);
        }
        if (this.reduceMoneySingle_txt != null) {
            sb.append(", reduceMoneySingle_txt=");
            sb.append(this.reduceMoneySingle_txt);
        }
        if (this.reduceMoney_txt != null) {
            sb.append(", reduceMoney_txt=");
            sb.append(this.reduceMoney_txt);
        }
        StringBuilder replace = sb.replace(0, 2, "ReductionData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<ReductionData, Builder> {
        public String actName;
        public String activity_name;
        public String reduceMoney;
        public String reduceMoneySingle;
        public String reduceMoneySingle_txt;
        public String reduceMoney_txt;
        public Integer times;

        public Builder actName(String str) {
            this.actName = str;
            return this;
        }

        public Builder reduceMoneySingle(String str) {
            this.reduceMoneySingle = str;
            return this;
        }

        public Builder times(Integer num) {
            this.times = num;
            return this;
        }

        public Builder reduceMoney(String str) {
            this.reduceMoney = str;
            return this;
        }

        public Builder activity_name(String str) {
            this.activity_name = str;
            return this;
        }

        public Builder reduceMoneySingle_txt(String str) {
            this.reduceMoneySingle_txt = str;
            return this;
        }

        public Builder reduceMoney_txt(String str) {
            this.reduceMoney_txt = str;
            return this;
        }

        public ReductionData build() {
            return new ReductionData(this.actName, this.reduceMoneySingle, this.times, this.reduceMoney, this.activity_name, this.reduceMoneySingle_txt, this.reduceMoney_txt, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_ReductionData extends ProtoAdapter<ReductionData> {
        ProtoAdapter_ReductionData() {
            super(FieldEncoding.LENGTH_DELIMITED, ReductionData.class);
        }

        public int encodedSize(ReductionData reductionData) {
            int i = 0;
            int encodedSizeWithTag = (reductionData.actName != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, reductionData.actName) : 0) + (reductionData.reduceMoneySingle != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, reductionData.reduceMoneySingle) : 0) + (reductionData.times != null ? ProtoAdapter.INT32.encodedSizeWithTag(3, reductionData.times) : 0) + (reductionData.reduceMoney != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, reductionData.reduceMoney) : 0) + (reductionData.activity_name != null ? ProtoAdapter.STRING.encodedSizeWithTag(7, reductionData.activity_name) : 0) + (reductionData.reduceMoneySingle_txt != null ? ProtoAdapter.STRING.encodedSizeWithTag(8, reductionData.reduceMoneySingle_txt) : 0);
            if (reductionData.reduceMoney_txt != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(9, reductionData.reduceMoney_txt);
            }
            return encodedSizeWithTag + i + reductionData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, ReductionData reductionData) throws IOException {
            if (reductionData.actName != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, reductionData.actName);
            }
            if (reductionData.reduceMoneySingle != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, reductionData.reduceMoneySingle);
            }
            if (reductionData.times != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 3, reductionData.times);
            }
            if (reductionData.reduceMoney != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, reductionData.reduceMoney);
            }
            if (reductionData.activity_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, reductionData.activity_name);
            }
            if (reductionData.reduceMoneySingle_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, reductionData.reduceMoneySingle_txt);
            }
            if (reductionData.reduceMoney_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 9, reductionData.reduceMoney_txt);
            }
            protoWriter.writeBytes(reductionData.unknownFields());
        }

        public ReductionData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.actName(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.reduceMoneySingle(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.times(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 4:
                            builder.reduceMoney(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 7:
                            builder.activity_name(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 8:
                            builder.reduceMoneySingle_txt(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 9:
                            builder.reduceMoney_txt(ProtoAdapter.STRING.decode(protoReader));
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

        public ReductionData redact(ReductionData reductionData) {
            Builder newBuilder = reductionData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
