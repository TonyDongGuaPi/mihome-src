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

public final class Express extends Message<Express, Builder> {
    public static final ProtoAdapter<Express> ADAPTER = new ProtoAdapter_Express();
    public static final String DEFAULT_EXPRESS_ID = "";
    public static final String DEFAULT_EXPRESS_NAME = "";
    public static final String DEFAULT_EXPRESS_SN = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String express_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String express_name;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String express_sn;

    public Express(String str, String str2, String str3) {
        this(str, str2, str3, ByteString.EMPTY);
    }

    public Express(String str, String str2, String str3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.express_id = str;
        this.express_sn = str2;
        this.express_name = str3;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.express_id = this.express_id;
        builder.express_sn = this.express_sn;
        builder.express_name = this.express_name;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Express)) {
            return false;
        }
        Express express = (Express) obj;
        if (!Internal.equals(unknownFields(), express.unknownFields()) || !Internal.equals(this.express_id, express.express_id) || !Internal.equals(this.express_sn, express.express_sn) || !Internal.equals(this.express_name, express.express_name)) {
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
        int hashCode = ((((unknownFields().hashCode() * 37) + (this.express_id != null ? this.express_id.hashCode() : 0)) * 37) + (this.express_sn != null ? this.express_sn.hashCode() : 0)) * 37;
        if (this.express_name != null) {
            i2 = this.express_name.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.express_id != null) {
            sb.append(", express_id=");
            sb.append(this.express_id);
        }
        if (this.express_sn != null) {
            sb.append(", express_sn=");
            sb.append(this.express_sn);
        }
        if (this.express_name != null) {
            sb.append(", express_name=");
            sb.append(this.express_name);
        }
        StringBuilder replace = sb.replace(0, 2, "Express{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Express, Builder> {
        public String express_id;
        public String express_name;
        public String express_sn;

        public Builder express_id(String str) {
            this.express_id = str;
            return this;
        }

        public Builder express_sn(String str) {
            this.express_sn = str;
            return this;
        }

        public Builder express_name(String str) {
            this.express_name = str;
            return this;
        }

        public Express build() {
            return new Express(this.express_id, this.express_sn, this.express_name, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Express extends ProtoAdapter<Express> {
        ProtoAdapter_Express() {
            super(FieldEncoding.LENGTH_DELIMITED, Express.class);
        }

        public int encodedSize(Express express) {
            int i = 0;
            int encodedSizeWithTag = (express.express_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, express.express_id) : 0) + (express.express_sn != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, express.express_sn) : 0);
            if (express.express_name != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(3, express.express_name);
            }
            return encodedSizeWithTag + i + express.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, Express express) throws IOException {
            if (express.express_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, express.express_id);
            }
            if (express.express_sn != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, express.express_sn);
            }
            if (express.express_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, express.express_name);
            }
            protoWriter.writeBytes(express.unknownFields());
        }

        public Express decode(ProtoReader protoReader) throws IOException {
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
                            builder.express_sn(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.express_name(ProtoAdapter.STRING.decode(protoReader));
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

        public Express redact(Express express) {
            Builder newBuilder = express.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
