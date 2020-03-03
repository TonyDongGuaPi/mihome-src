package com.mi.global.shop.model.discover;

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

public final class UpData extends Message<UpData, Builder> {
    public static final ProtoAdapter<UpData> ADAPTER = new ProtoAdapter_UpData();
    public static final Long DEFAULT_NUM = 0L;
    public static final Boolean DEFAULT_RES = false;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 2)
    public final Long num;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 1)
    public final Boolean res;

    public UpData(Boolean bool, Long l) {
        this(bool, l, ByteString.EMPTY);
    }

    public UpData(Boolean bool, Long l, ByteString byteString) {
        super(ADAPTER, byteString);
        this.res = bool;
        this.num = l;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.res = this.res;
        builder.num = this.num;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UpData)) {
            return false;
        }
        UpData upData = (UpData) obj;
        if (!Internal.equals(unknownFields(), upData.unknownFields()) || !Internal.equals(this.res, upData.res) || !Internal.equals(this.num, upData.num)) {
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
        int hashCode = ((unknownFields().hashCode() * 37) + (this.res != null ? this.res.hashCode() : 0)) * 37;
        if (this.num != null) {
            i2 = this.num.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.res != null) {
            sb.append(", res=");
            sb.append(this.res);
        }
        if (this.num != null) {
            sb.append(", num=");
            sb.append(this.num);
        }
        StringBuilder replace = sb.replace(0, 2, "UpData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<UpData, Builder> {
        public Long num;
        public Boolean res;

        public Builder res(Boolean bool) {
            this.res = bool;
            return this;
        }

        public Builder num(Long l) {
            this.num = l;
            return this;
        }

        public UpData build() {
            return new UpData(this.res, this.num, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_UpData extends ProtoAdapter<UpData> {
        ProtoAdapter_UpData() {
            super(FieldEncoding.LENGTH_DELIMITED, UpData.class);
        }

        public int encodedSize(UpData upData) {
            int i = 0;
            int encodedSizeWithTag = upData.res != null ? ProtoAdapter.BOOL.encodedSizeWithTag(1, upData.res) : 0;
            if (upData.num != null) {
                i = ProtoAdapter.INT64.encodedSizeWithTag(2, upData.num);
            }
            return encodedSizeWithTag + i + upData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, UpData upData) throws IOException {
            if (upData.res != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 1, upData.res);
            }
            if (upData.num != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 2, upData.num);
            }
            protoWriter.writeBytes(upData.unknownFields());
        }

        public UpData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.res(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 2:
                            builder.num(ProtoAdapter.INT64.decode(protoReader));
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

        public UpData redact(UpData upData) {
            Builder newBuilder = upData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
