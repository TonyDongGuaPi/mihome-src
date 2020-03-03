package com.mi.global.shop.model.buy;

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

public final class SubmitData extends Message<SubmitData, Builder> {
    public static final ProtoAdapter<SubmitData> ADAPTER = new ProtoAdapter_SubmitData();
    public static final String DEFAULT_ERRORS = "";
    public static final Boolean DEFAULT_IS_ZERO_ORDER = false;
    public static final String DEFAULT_LINK = "";
    public static final String DEFAULT_ORDER_ID = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String errors;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 2)
    public final Boolean is_zero_order;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String link;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String order_id;

    public SubmitData(String str, Boolean bool, String str2, String str3) {
        this(str, bool, str2, str3, ByteString.EMPTY);
    }

    public SubmitData(String str, Boolean bool, String str2, String str3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.order_id = str;
        this.is_zero_order = bool;
        this.link = str2;
        this.errors = str3;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.order_id = this.order_id;
        builder.is_zero_order = this.is_zero_order;
        builder.link = this.link;
        builder.errors = this.errors;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SubmitData)) {
            return false;
        }
        SubmitData submitData = (SubmitData) obj;
        if (!Internal.equals(unknownFields(), submitData.unknownFields()) || !Internal.equals(this.order_id, submitData.order_id) || !Internal.equals(this.is_zero_order, submitData.is_zero_order) || !Internal.equals(this.link, submitData.link) || !Internal.equals(this.errors, submitData.errors)) {
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
        int hashCode = ((((((unknownFields().hashCode() * 37) + (this.order_id != null ? this.order_id.hashCode() : 0)) * 37) + (this.is_zero_order != null ? this.is_zero_order.hashCode() : 0)) * 37) + (this.link != null ? this.link.hashCode() : 0)) * 37;
        if (this.errors != null) {
            i2 = this.errors.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.order_id != null) {
            sb.append(", order_id=");
            sb.append(this.order_id);
        }
        if (this.is_zero_order != null) {
            sb.append(", is_zero_order=");
            sb.append(this.is_zero_order);
        }
        if (this.link != null) {
            sb.append(", link=");
            sb.append(this.link);
        }
        if (this.errors != null) {
            sb.append(", errors=");
            sb.append(this.errors);
        }
        StringBuilder replace = sb.replace(0, 2, "SubmitData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<SubmitData, Builder> {
        public String errors;
        public Boolean is_zero_order;
        public String link;
        public String order_id;

        public Builder order_id(String str) {
            this.order_id = str;
            return this;
        }

        public Builder is_zero_order(Boolean bool) {
            this.is_zero_order = bool;
            return this;
        }

        public Builder link(String str) {
            this.link = str;
            return this;
        }

        public Builder errors(String str) {
            this.errors = str;
            return this;
        }

        public SubmitData build() {
            return new SubmitData(this.order_id, this.is_zero_order, this.link, this.errors, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_SubmitData extends ProtoAdapter<SubmitData> {
        ProtoAdapter_SubmitData() {
            super(FieldEncoding.LENGTH_DELIMITED, SubmitData.class);
        }

        public int encodedSize(SubmitData submitData) {
            int i = 0;
            int encodedSizeWithTag = (submitData.order_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, submitData.order_id) : 0) + (submitData.is_zero_order != null ? ProtoAdapter.BOOL.encodedSizeWithTag(2, submitData.is_zero_order) : 0) + (submitData.link != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, submitData.link) : 0);
            if (submitData.errors != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(4, submitData.errors);
            }
            return encodedSizeWithTag + i + submitData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, SubmitData submitData) throws IOException {
            if (submitData.order_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, submitData.order_id);
            }
            if (submitData.is_zero_order != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 2, submitData.is_zero_order);
            }
            if (submitData.link != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, submitData.link);
            }
            if (submitData.errors != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, submitData.errors);
            }
            protoWriter.writeBytes(submitData.unknownFields());
        }

        public SubmitData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.order_id(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.is_zero_order(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 3:
                            builder.link(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.errors(ProtoAdapter.STRING.decode(protoReader));
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

        public SubmitData redact(SubmitData submitData) {
            Builder newBuilder = submitData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
