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

public final class ExpressInfoData extends Message<ExpressInfoData, Builder> {
    public static final ProtoAdapter<ExpressInfoData> ADAPTER = new ProtoAdapter_ExpressInfoData();
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.user.ExpressInfoInnerData#ADAPTER", tag = 1)
    public final ExpressInfoInnerData expressInfo;

    public ExpressInfoData(ExpressInfoInnerData expressInfoInnerData) {
        this(expressInfoInnerData, ByteString.EMPTY);
    }

    public ExpressInfoData(ExpressInfoInnerData expressInfoInnerData, ByteString byteString) {
        super(ADAPTER, byteString);
        this.expressInfo = expressInfoInnerData;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.expressInfo = this.expressInfo;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExpressInfoData)) {
            return false;
        }
        ExpressInfoData expressInfoData = (ExpressInfoData) obj;
        if (!Internal.equals(unknownFields(), expressInfoData.unknownFields()) || !Internal.equals(this.expressInfo, expressInfoData.expressInfo)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = (unknownFields().hashCode() * 37) + (this.expressInfo != null ? this.expressInfo.hashCode() : 0);
        this.hashCode = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.expressInfo != null) {
            sb.append(", expressInfo=");
            sb.append(this.expressInfo);
        }
        StringBuilder replace = sb.replace(0, 2, "ExpressInfoData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<ExpressInfoData, Builder> {
        public ExpressInfoInnerData expressInfo;

        public Builder expressInfo(ExpressInfoInnerData expressInfoInnerData) {
            this.expressInfo = expressInfoInnerData;
            return this;
        }

        public ExpressInfoData build() {
            return new ExpressInfoData(this.expressInfo, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_ExpressInfoData extends ProtoAdapter<ExpressInfoData> {
        ProtoAdapter_ExpressInfoData() {
            super(FieldEncoding.LENGTH_DELIMITED, ExpressInfoData.class);
        }

        public int encodedSize(ExpressInfoData expressInfoData) {
            return (expressInfoData.expressInfo != null ? ExpressInfoInnerData.ADAPTER.encodedSizeWithTag(1, expressInfoData.expressInfo) : 0) + expressInfoData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, ExpressInfoData expressInfoData) throws IOException {
            if (expressInfoData.expressInfo != null) {
                ExpressInfoInnerData.ADAPTER.encodeWithTag(protoWriter, 1, expressInfoData.expressInfo);
            }
            protoWriter.writeBytes(expressInfoData.unknownFields());
        }

        public ExpressInfoData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag == -1) {
                    protoReader.endMessage(beginMessage);
                    return builder.build();
                } else if (nextTag != 1) {
                    FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                    builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                } else {
                    builder.expressInfo(ExpressInfoInnerData.ADAPTER.decode(protoReader));
                }
            }
        }

        public ExpressInfoData redact(ExpressInfoData expressInfoData) {
            Builder newBuilder = expressInfoData.newBuilder();
            if (newBuilder.expressInfo != null) {
                newBuilder.expressInfo = ExpressInfoInnerData.ADAPTER.redact(newBuilder.expressInfo);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
