package com.mi.global.shop.model.address;

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

public final class SetDefaultData extends Message<SetDefaultData, Builder> {
    public static final ProtoAdapter<SetDefaultData> ADAPTER = new ProtoAdapter_SetDefaultData();
    public static final Boolean DEFAULT_DATA = false;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 1)
    public final Boolean data;

    public SetDefaultData(Boolean bool) {
        this(bool, ByteString.EMPTY);
    }

    public SetDefaultData(Boolean bool, ByteString byteString) {
        super(ADAPTER, byteString);
        this.data = bool;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.data = this.data;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SetDefaultData)) {
            return false;
        }
        SetDefaultData setDefaultData = (SetDefaultData) obj;
        if (!Internal.equals(unknownFields(), setDefaultData.unknownFields()) || !Internal.equals(this.data, setDefaultData.data)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = (unknownFields().hashCode() * 37) + (this.data != null ? this.data.hashCode() : 0);
        this.hashCode = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.data != null) {
            sb.append(", data=");
            sb.append(this.data);
        }
        StringBuilder replace = sb.replace(0, 2, "SetDefaultData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<SetDefaultData, Builder> {
        public Boolean data;

        public Builder data(Boolean bool) {
            this.data = bool;
            return this;
        }

        public SetDefaultData build() {
            return new SetDefaultData(this.data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_SetDefaultData extends ProtoAdapter<SetDefaultData> {
        ProtoAdapter_SetDefaultData() {
            super(FieldEncoding.LENGTH_DELIMITED, SetDefaultData.class);
        }

        public int encodedSize(SetDefaultData setDefaultData) {
            return (setDefaultData.data != null ? ProtoAdapter.BOOL.encodedSizeWithTag(1, setDefaultData.data) : 0) + setDefaultData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, SetDefaultData setDefaultData) throws IOException {
            if (setDefaultData.data != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 1, setDefaultData.data);
            }
            protoWriter.writeBytes(setDefaultData.unknownFields());
        }

        public SetDefaultData decode(ProtoReader protoReader) throws IOException {
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
                    builder.data(ProtoAdapter.BOOL.decode(protoReader));
                }
            }
        }

        public SetDefaultData redact(SetDefaultData setDefaultData) {
            Builder newBuilder = setDefaultData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
