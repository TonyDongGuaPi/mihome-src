package com.mi.global.shop.model.user;

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

public final class ExpressInfo extends Message<ExpressInfo, Builder> {
    public static final ProtoAdapter<ExpressInfo> ADAPTER = new ProtoAdapter_ExpressInfo();
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.user.ExpressInfoData#ADAPTER", tag = 5)
    public final ExpressInfoData data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer errno;

    public ExpressInfo(Integer num, String str, ExpressInfoData expressInfoData) {
        this(num, str, expressInfoData, ByteString.EMPTY);
    }

    public ExpressInfo(Integer num, String str, ExpressInfoData expressInfoData, ByteString byteString) {
        super(ADAPTER, byteString);
        this.errno = num;
        this.errmsg = str;
        this.data = expressInfoData;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.errno = this.errno;
        builder.errmsg = this.errmsg;
        builder.data = this.data;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExpressInfo)) {
            return false;
        }
        ExpressInfo expressInfo = (ExpressInfo) obj;
        if (!Internal.equals(unknownFields(), expressInfo.unknownFields()) || !Internal.equals(this.errno, expressInfo.errno) || !Internal.equals(this.errmsg, expressInfo.errmsg) || !Internal.equals(this.data, expressInfo.data)) {
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
        int hashCode = ((((unknownFields().hashCode() * 37) + (this.errno != null ? this.errno.hashCode() : 0)) * 37) + (this.errmsg != null ? this.errmsg.hashCode() : 0)) * 37;
        if (this.data != null) {
            i2 = this.data.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.errno != null) {
            sb.append(", errno=");
            sb.append(this.errno);
        }
        if (this.errmsg != null) {
            sb.append(", errmsg=");
            sb.append(this.errmsg);
        }
        if (this.data != null) {
            sb.append(", data=");
            sb.append(this.data);
        }
        StringBuilder replace = sb.replace(0, 2, "ExpressInfo{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<ExpressInfo, Builder> {
        public ExpressInfoData data;
        public String errmsg;
        public Integer errno;

        public Builder errno(Integer num) {
            this.errno = num;
            return this;
        }

        public Builder errmsg(String str) {
            this.errmsg = str;
            return this;
        }

        public Builder data(ExpressInfoData expressInfoData) {
            this.data = expressInfoData;
            return this;
        }

        public ExpressInfo build() {
            return new ExpressInfo(this.errno, this.errmsg, this.data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_ExpressInfo extends ProtoAdapter<ExpressInfo> {
        ProtoAdapter_ExpressInfo() {
            super(FieldEncoding.LENGTH_DELIMITED, ExpressInfo.class);
        }

        public int encodedSize(ExpressInfo expressInfo) {
            int i = 0;
            int encodedSizeWithTag = (expressInfo.errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, expressInfo.errno) : 0) + (expressInfo.errmsg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, expressInfo.errmsg) : 0);
            if (expressInfo.data != null) {
                i = ExpressInfoData.ADAPTER.encodedSizeWithTag(5, expressInfo.data);
            }
            return encodedSizeWithTag + i + expressInfo.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, ExpressInfo expressInfo) throws IOException {
            if (expressInfo.errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, expressInfo.errno);
            }
            if (expressInfo.errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, expressInfo.errmsg);
            }
            if (expressInfo.data != null) {
                ExpressInfoData.ADAPTER.encodeWithTag(protoWriter, 5, expressInfo.data);
            }
            protoWriter.writeBytes(expressInfo.unknownFields());
        }

        public ExpressInfo decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag == -1) {
                    protoReader.endMessage(beginMessage);
                    return builder.build();
                } else if (nextTag != 5) {
                    switch (nextTag) {
                        case 1:
                            builder.errno(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 2:
                            builder.errmsg(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        default:
                            FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                            builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                            break;
                    }
                } else {
                    builder.data(ExpressInfoData.ADAPTER.decode(protoReader));
                }
            }
        }

        public ExpressInfo redact(ExpressInfo expressInfo) {
            Builder newBuilder = expressInfo.newBuilder();
            if (newBuilder.data != null) {
                newBuilder.data = ExpressInfoData.ADAPTER.redact(newBuilder.data);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
