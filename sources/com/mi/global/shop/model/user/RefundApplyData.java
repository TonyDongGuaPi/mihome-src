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

public final class RefundApplyData extends Message<RefundApplyData, Builder> {
    public static final ProtoAdapter<RefundApplyData> ADAPTER = new ProtoAdapter_RefundApplyData();
    public static final String DEFAULT_ORDER_STATUS_INFO = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String order_status_info;

    public RefundApplyData(String str) {
        this(str, ByteString.EMPTY);
    }

    public RefundApplyData(String str, ByteString byteString) {
        super(ADAPTER, byteString);
        this.order_status_info = str;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.order_status_info = this.order_status_info;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RefundApplyData)) {
            return false;
        }
        RefundApplyData refundApplyData = (RefundApplyData) obj;
        if (!Internal.equals(unknownFields(), refundApplyData.unknownFields()) || !Internal.equals(this.order_status_info, refundApplyData.order_status_info)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = (unknownFields().hashCode() * 37) + (this.order_status_info != null ? this.order_status_info.hashCode() : 0);
        this.hashCode = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.order_status_info != null) {
            sb.append(", order_status_info=");
            sb.append(this.order_status_info);
        }
        StringBuilder replace = sb.replace(0, 2, "RefundApplyData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<RefundApplyData, Builder> {
        public String order_status_info;

        public Builder order_status_info(String str) {
            this.order_status_info = str;
            return this;
        }

        public RefundApplyData build() {
            return new RefundApplyData(this.order_status_info, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_RefundApplyData extends ProtoAdapter<RefundApplyData> {
        ProtoAdapter_RefundApplyData() {
            super(FieldEncoding.LENGTH_DELIMITED, RefundApplyData.class);
        }

        public int encodedSize(RefundApplyData refundApplyData) {
            return (refundApplyData.order_status_info != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, refundApplyData.order_status_info) : 0) + refundApplyData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, RefundApplyData refundApplyData) throws IOException {
            if (refundApplyData.order_status_info != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, refundApplyData.order_status_info);
            }
            protoWriter.writeBytes(refundApplyData.unknownFields());
        }

        public RefundApplyData decode(ProtoReader protoReader) throws IOException {
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
                    builder.order_status_info(ProtoAdapter.STRING.decode(protoReader));
                }
            }
        }

        public RefundApplyData redact(RefundApplyData refundApplyData) {
            Builder newBuilder = refundApplyData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
