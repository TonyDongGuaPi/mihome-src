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

public final class ChangeAddressBody extends Message<ChangeAddressBody, Builder> {
    public static final ProtoAdapter<ChangeAddressBody> ADAPTER = new ProtoAdapter_ChangeAddressBody();
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.buy.ChangeAddressData#ADAPTER", tag = 3)
    public final ChangeAddressData Data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String Errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer Errno;

    public ChangeAddressBody(Integer num, String str, ChangeAddressData changeAddressData) {
        this(num, str, changeAddressData, ByteString.EMPTY);
    }

    public ChangeAddressBody(Integer num, String str, ChangeAddressData changeAddressData, ByteString byteString) {
        super(ADAPTER, byteString);
        this.Errno = num;
        this.Errmsg = str;
        this.Data = changeAddressData;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.Errno = this.Errno;
        builder.Errmsg = this.Errmsg;
        builder.Data = this.Data;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ChangeAddressBody)) {
            return false;
        }
        ChangeAddressBody changeAddressBody = (ChangeAddressBody) obj;
        if (!Internal.equals(unknownFields(), changeAddressBody.unknownFields()) || !Internal.equals(this.Errno, changeAddressBody.Errno) || !Internal.equals(this.Errmsg, changeAddressBody.Errmsg) || !Internal.equals(this.Data, changeAddressBody.Data)) {
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
        int hashCode = ((((unknownFields().hashCode() * 37) + (this.Errno != null ? this.Errno.hashCode() : 0)) * 37) + (this.Errmsg != null ? this.Errmsg.hashCode() : 0)) * 37;
        if (this.Data != null) {
            i2 = this.Data.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.Errno != null) {
            sb.append(", Errno=");
            sb.append(this.Errno);
        }
        if (this.Errmsg != null) {
            sb.append(", Errmsg=");
            sb.append(this.Errmsg);
        }
        if (this.Data != null) {
            sb.append(", Data=");
            sb.append(this.Data);
        }
        StringBuilder replace = sb.replace(0, 2, "ChangeAddressBody{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<ChangeAddressBody, Builder> {
        public ChangeAddressData Data;
        public String Errmsg;
        public Integer Errno;

        public Builder Errno(Integer num) {
            this.Errno = num;
            return this;
        }

        public Builder Errmsg(String str) {
            this.Errmsg = str;
            return this;
        }

        public Builder Data(ChangeAddressData changeAddressData) {
            this.Data = changeAddressData;
            return this;
        }

        public ChangeAddressBody build() {
            return new ChangeAddressBody(this.Errno, this.Errmsg, this.Data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_ChangeAddressBody extends ProtoAdapter<ChangeAddressBody> {
        ProtoAdapter_ChangeAddressBody() {
            super(FieldEncoding.LENGTH_DELIMITED, ChangeAddressBody.class);
        }

        public int encodedSize(ChangeAddressBody changeAddressBody) {
            int i = 0;
            int encodedSizeWithTag = (changeAddressBody.Errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, changeAddressBody.Errno) : 0) + (changeAddressBody.Errmsg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, changeAddressBody.Errmsg) : 0);
            if (changeAddressBody.Data != null) {
                i = ChangeAddressData.ADAPTER.encodedSizeWithTag(3, changeAddressBody.Data);
            }
            return encodedSizeWithTag + i + changeAddressBody.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, ChangeAddressBody changeAddressBody) throws IOException {
            if (changeAddressBody.Errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, changeAddressBody.Errno);
            }
            if (changeAddressBody.Errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, changeAddressBody.Errmsg);
            }
            if (changeAddressBody.Data != null) {
                ChangeAddressData.ADAPTER.encodeWithTag(protoWriter, 3, changeAddressBody.Data);
            }
            protoWriter.writeBytes(changeAddressBody.unknownFields());
        }

        public ChangeAddressBody decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.Errno(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 2:
                            builder.Errmsg(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.Data(ChangeAddressData.ADAPTER.decode(protoReader));
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

        public ChangeAddressBody redact(ChangeAddressBody changeAddressBody) {
            Builder newBuilder = changeAddressBody.newBuilder();
            if (newBuilder.Data != null) {
                newBuilder.Data = ChangeAddressData.ADAPTER.redact(newBuilder.Data);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
