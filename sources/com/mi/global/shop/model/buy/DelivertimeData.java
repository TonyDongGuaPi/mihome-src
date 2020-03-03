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

public final class DelivertimeData extends Message<DelivertimeData, Builder> {
    public static final ProtoAdapter<DelivertimeData> ADAPTER = new ProtoAdapter_DelivertimeData();
    public static final Boolean DEFAULT_CHECKED = false;
    public static final String DEFAULT_DESC = "";
    public static final String DEFAULT_VALUE = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 3)
    public final Boolean checked;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String desc;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String value;

    public DelivertimeData(String str, String str2, Boolean bool) {
        this(str, str2, bool, ByteString.EMPTY);
    }

    public DelivertimeData(String str, String str2, Boolean bool, ByteString byteString) {
        super(ADAPTER, byteString);
        this.value = str;
        this.desc = str2;
        this.checked = bool;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.value = this.value;
        builder.desc = this.desc;
        builder.checked = this.checked;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DelivertimeData)) {
            return false;
        }
        DelivertimeData delivertimeData = (DelivertimeData) obj;
        if (!Internal.equals(unknownFields(), delivertimeData.unknownFields()) || !Internal.equals(this.value, delivertimeData.value) || !Internal.equals(this.desc, delivertimeData.desc) || !Internal.equals(this.checked, delivertimeData.checked)) {
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
        int hashCode = ((((unknownFields().hashCode() * 37) + (this.value != null ? this.value.hashCode() : 0)) * 37) + (this.desc != null ? this.desc.hashCode() : 0)) * 37;
        if (this.checked != null) {
            i2 = this.checked.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.value != null) {
            sb.append(", value=");
            sb.append(this.value);
        }
        if (this.desc != null) {
            sb.append(", desc=");
            sb.append(this.desc);
        }
        if (this.checked != null) {
            sb.append(", checked=");
            sb.append(this.checked);
        }
        StringBuilder replace = sb.replace(0, 2, "DelivertimeData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<DelivertimeData, Builder> {
        public Boolean checked;
        public String desc;
        public String value;

        public Builder value(String str) {
            this.value = str;
            return this;
        }

        public Builder desc(String str) {
            this.desc = str;
            return this;
        }

        public Builder checked(Boolean bool) {
            this.checked = bool;
            return this;
        }

        public DelivertimeData build() {
            return new DelivertimeData(this.value, this.desc, this.checked, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_DelivertimeData extends ProtoAdapter<DelivertimeData> {
        ProtoAdapter_DelivertimeData() {
            super(FieldEncoding.LENGTH_DELIMITED, DelivertimeData.class);
        }

        public int encodedSize(DelivertimeData delivertimeData) {
            int i = 0;
            int encodedSizeWithTag = (delivertimeData.value != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, delivertimeData.value) : 0) + (delivertimeData.desc != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, delivertimeData.desc) : 0);
            if (delivertimeData.checked != null) {
                i = ProtoAdapter.BOOL.encodedSizeWithTag(3, delivertimeData.checked);
            }
            return encodedSizeWithTag + i + delivertimeData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, DelivertimeData delivertimeData) throws IOException {
            if (delivertimeData.value != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, delivertimeData.value);
            }
            if (delivertimeData.desc != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, delivertimeData.desc);
            }
            if (delivertimeData.checked != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 3, delivertimeData.checked);
            }
            protoWriter.writeBytes(delivertimeData.unknownFields());
        }

        public DelivertimeData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.value(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.desc(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.checked(ProtoAdapter.BOOL.decode(protoReader));
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

        public DelivertimeData redact(DelivertimeData delivertimeData) {
            Builder newBuilder = delivertimeData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
