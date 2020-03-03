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

public final class MutexInfoData extends Message<MutexInfoData, Builder> {
    public static final ProtoAdapter<MutexInfoData> ADAPTER = new ProtoAdapter_MutexInfoData();
    public static final Integer DEFAULT_CODE = 0;
    public static final String DEFAULT_DESC = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer code;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String desc;

    public MutexInfoData(Integer num, String str) {
        this(num, str, ByteString.EMPTY);
    }

    public MutexInfoData(Integer num, String str, ByteString byteString) {
        super(ADAPTER, byteString);
        this.code = num;
        this.desc = str;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.code = this.code;
        builder.desc = this.desc;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MutexInfoData)) {
            return false;
        }
        MutexInfoData mutexInfoData = (MutexInfoData) obj;
        if (!Internal.equals(unknownFields(), mutexInfoData.unknownFields()) || !Internal.equals(this.code, mutexInfoData.code) || !Internal.equals(this.desc, mutexInfoData.desc)) {
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
        int hashCode = ((unknownFields().hashCode() * 37) + (this.code != null ? this.code.hashCode() : 0)) * 37;
        if (this.desc != null) {
            i2 = this.desc.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.code != null) {
            sb.append(", code=");
            sb.append(this.code);
        }
        if (this.desc != null) {
            sb.append(", desc=");
            sb.append(this.desc);
        }
        StringBuilder replace = sb.replace(0, 2, "MutexInfoData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<MutexInfoData, Builder> {
        public Integer code;
        public String desc;

        public Builder code(Integer num) {
            this.code = num;
            return this;
        }

        public Builder desc(String str) {
            this.desc = str;
            return this;
        }

        public MutexInfoData build() {
            return new MutexInfoData(this.code, this.desc, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_MutexInfoData extends ProtoAdapter<MutexInfoData> {
        ProtoAdapter_MutexInfoData() {
            super(FieldEncoding.LENGTH_DELIMITED, MutexInfoData.class);
        }

        public int encodedSize(MutexInfoData mutexInfoData) {
            int i = 0;
            int encodedSizeWithTag = mutexInfoData.code != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, mutexInfoData.code) : 0;
            if (mutexInfoData.desc != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(2, mutexInfoData.desc);
            }
            return encodedSizeWithTag + i + mutexInfoData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, MutexInfoData mutexInfoData) throws IOException {
            if (mutexInfoData.code != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, mutexInfoData.code);
            }
            if (mutexInfoData.desc != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, mutexInfoData.desc);
            }
            protoWriter.writeBytes(mutexInfoData.unknownFields());
        }

        public MutexInfoData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.code(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 2:
                            builder.desc(ProtoAdapter.STRING.decode(protoReader));
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

        public MutexInfoData redact(MutexInfoData mutexInfoData) {
            Builder newBuilder = mutexInfoData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
