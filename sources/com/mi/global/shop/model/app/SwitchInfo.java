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

public final class SwitchInfo extends Message<SwitchInfo, Builder> {
    public static final ProtoAdapter<SwitchInfo> ADAPTER = new ProtoAdapter_SwitchInfo();
    public static final Boolean DEFAULT_USENEWMODEL = false;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 1)
    public final Boolean useNewModel;

    public SwitchInfo(Boolean bool) {
        this(bool, ByteString.EMPTY);
    }

    public SwitchInfo(Boolean bool, ByteString byteString) {
        super(ADAPTER, byteString);
        this.useNewModel = bool;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.useNewModel = this.useNewModel;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SwitchInfo)) {
            return false;
        }
        SwitchInfo switchInfo = (SwitchInfo) obj;
        if (!Internal.equals(unknownFields(), switchInfo.unknownFields()) || !Internal.equals(this.useNewModel, switchInfo.useNewModel)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = (unknownFields().hashCode() * 37) + (this.useNewModel != null ? this.useNewModel.hashCode() : 0);
        this.hashCode = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.useNewModel != null) {
            sb.append(", useNewModel=");
            sb.append(this.useNewModel);
        }
        StringBuilder replace = sb.replace(0, 2, "SwitchInfo{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<SwitchInfo, Builder> {
        public Boolean useNewModel;

        public Builder useNewModel(Boolean bool) {
            this.useNewModel = bool;
            return this;
        }

        public SwitchInfo build() {
            return new SwitchInfo(this.useNewModel, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_SwitchInfo extends ProtoAdapter<SwitchInfo> {
        ProtoAdapter_SwitchInfo() {
            super(FieldEncoding.LENGTH_DELIMITED, SwitchInfo.class);
        }

        public int encodedSize(SwitchInfo switchInfo) {
            return (switchInfo.useNewModel != null ? ProtoAdapter.BOOL.encodedSizeWithTag(1, switchInfo.useNewModel) : 0) + switchInfo.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, SwitchInfo switchInfo) throws IOException {
            if (switchInfo.useNewModel != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 1, switchInfo.useNewModel);
            }
            protoWriter.writeBytes(switchInfo.unknownFields());
        }

        public SwitchInfo decode(ProtoReader protoReader) throws IOException {
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
                    builder.useNewModel(ProtoAdapter.BOOL.decode(protoReader));
                }
            }
        }

        public SwitchInfo redact(SwitchInfo switchInfo) {
            Builder newBuilder = switchInfo.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
