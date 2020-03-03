package com.mi.global.shop.model.user;

import com.mi.global.shop.model.Tags;
import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.util.List;
import okio.ByteString;

public final class ExpressInfoInnerData extends Message<ExpressInfoInnerData, Builder> {
    public static final ProtoAdapter<ExpressInfoInnerData> ADAPTER = new ProtoAdapter_ExpressInfoInnerData();
    public static final String DEFAULT_EXPRESS_ID = "";
    public static final String DEFAULT_EXPRESS_NAME = "";
    public static final String DEFAULT_EXPRESS_SN = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String express_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String express_name;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String express_sn;
    @WireField(adapter = "com.mi.global.shop.model.user.ExpressTrace#ADAPTER", label = WireField.Label.REPEATED, tag = 4)
    public final List<ExpressTrace> express_trace;

    public ExpressInfoInnerData(String str, String str2, String str3, List<ExpressTrace> list) {
        this(str, str2, str3, list, ByteString.EMPTY);
    }

    public ExpressInfoInnerData(String str, String str2, String str3, List<ExpressTrace> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.express_id = str;
        this.express_name = str2;
        this.express_sn = str3;
        this.express_trace = Internal.immutableCopyOf(Tags.Order.EXPRESS_TRACE, list);
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.express_id = this.express_id;
        builder.express_name = this.express_name;
        builder.express_sn = this.express_sn;
        builder.express_trace = Internal.copyOf(Tags.Order.EXPRESS_TRACE, this.express_trace);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExpressInfoInnerData)) {
            return false;
        }
        ExpressInfoInnerData expressInfoInnerData = (ExpressInfoInnerData) obj;
        if (!Internal.equals(unknownFields(), expressInfoInnerData.unknownFields()) || !Internal.equals(this.express_id, expressInfoInnerData.express_id) || !Internal.equals(this.express_name, expressInfoInnerData.express_name) || !Internal.equals(this.express_sn, expressInfoInnerData.express_sn) || !Internal.equals(this.express_trace, expressInfoInnerData.express_trace)) {
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
        int hashCode = ((((unknownFields().hashCode() * 37) + (this.express_id != null ? this.express_id.hashCode() : 0)) * 37) + (this.express_name != null ? this.express_name.hashCode() : 0)) * 37;
        if (this.express_sn != null) {
            i2 = this.express_sn.hashCode();
        }
        int hashCode2 = ((hashCode + i2) * 37) + (this.express_trace != null ? this.express_trace.hashCode() : 1);
        this.hashCode = hashCode2;
        return hashCode2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.express_id != null) {
            sb.append(", express_id=");
            sb.append(this.express_id);
        }
        if (this.express_name != null) {
            sb.append(", express_name=");
            sb.append(this.express_name);
        }
        if (this.express_sn != null) {
            sb.append(", express_sn=");
            sb.append(this.express_sn);
        }
        if (this.express_trace != null) {
            sb.append(", express_trace=");
            sb.append(this.express_trace);
        }
        StringBuilder replace = sb.replace(0, 2, "ExpressInfoInnerData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<ExpressInfoInnerData, Builder> {
        public String express_id;
        public String express_name;
        public String express_sn;
        public List<ExpressTrace> express_trace = Internal.newMutableList();

        public Builder express_id(String str) {
            this.express_id = str;
            return this;
        }

        public Builder express_name(String str) {
            this.express_name = str;
            return this;
        }

        public Builder express_sn(String str) {
            this.express_sn = str;
            return this;
        }

        public Builder express_trace(List<ExpressTrace> list) {
            Internal.checkElementsNotNull(list);
            this.express_trace = list;
            return this;
        }

        public ExpressInfoInnerData build() {
            return new ExpressInfoInnerData(this.express_id, this.express_name, this.express_sn, this.express_trace, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_ExpressInfoInnerData extends ProtoAdapter<ExpressInfoInnerData> {
        ProtoAdapter_ExpressInfoInnerData() {
            super(FieldEncoding.LENGTH_DELIMITED, ExpressInfoInnerData.class);
        }

        public int encodedSize(ExpressInfoInnerData expressInfoInnerData) {
            int i = 0;
            int encodedSizeWithTag = (expressInfoInnerData.express_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, expressInfoInnerData.express_id) : 0) + (expressInfoInnerData.express_name != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, expressInfoInnerData.express_name) : 0);
            if (expressInfoInnerData.express_sn != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(3, expressInfoInnerData.express_sn);
            }
            return encodedSizeWithTag + i + ExpressTrace.ADAPTER.asRepeated().encodedSizeWithTag(4, expressInfoInnerData.express_trace) + expressInfoInnerData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, ExpressInfoInnerData expressInfoInnerData) throws IOException {
            if (expressInfoInnerData.express_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, expressInfoInnerData.express_id);
            }
            if (expressInfoInnerData.express_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, expressInfoInnerData.express_name);
            }
            if (expressInfoInnerData.express_sn != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, expressInfoInnerData.express_sn);
            }
            if (expressInfoInnerData.express_trace != null) {
                ExpressTrace.ADAPTER.asRepeated().encodeWithTag(protoWriter, 4, expressInfoInnerData.express_trace);
            }
            protoWriter.writeBytes(expressInfoInnerData.unknownFields());
        }

        public ExpressInfoInnerData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.express_id(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.express_name(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.express_sn(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.express_trace.add(ExpressTrace.ADAPTER.decode(protoReader));
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

        public ExpressInfoInnerData redact(ExpressInfoInnerData expressInfoInnerData) {
            Builder newBuilder = expressInfoInnerData.newBuilder();
            Internal.redactElements(newBuilder.express_trace, ExpressTrace.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
