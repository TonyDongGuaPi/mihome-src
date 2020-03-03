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

public final class CheckoutBody extends Message<CheckoutBody, Builder> {
    public static final ProtoAdapter<CheckoutBody> ADAPTER = new ProtoAdapter_CheckoutBody();
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.buy.CheckoutData#ADAPTER", tag = 3)
    public final CheckoutData Data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String Errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer Errno;

    public CheckoutBody(Integer num, String str, CheckoutData checkoutData) {
        this(num, str, checkoutData, ByteString.EMPTY);
    }

    public CheckoutBody(Integer num, String str, CheckoutData checkoutData, ByteString byteString) {
        super(ADAPTER, byteString);
        this.Errno = num;
        this.Errmsg = str;
        this.Data = checkoutData;
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
        if (!(obj instanceof CheckoutBody)) {
            return false;
        }
        CheckoutBody checkoutBody = (CheckoutBody) obj;
        if (!Internal.equals(unknownFields(), checkoutBody.unknownFields()) || !Internal.equals(this.Errno, checkoutBody.Errno) || !Internal.equals(this.Errmsg, checkoutBody.Errmsg) || !Internal.equals(this.Data, checkoutBody.Data)) {
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
        StringBuilder replace = sb.replace(0, 2, "CheckoutBody{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<CheckoutBody, Builder> {
        public CheckoutData Data;
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

        public Builder Data(CheckoutData checkoutData) {
            this.Data = checkoutData;
            return this;
        }

        public CheckoutBody build() {
            return new CheckoutBody(this.Errno, this.Errmsg, this.Data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_CheckoutBody extends ProtoAdapter<CheckoutBody> {
        ProtoAdapter_CheckoutBody() {
            super(FieldEncoding.LENGTH_DELIMITED, CheckoutBody.class);
        }

        public int encodedSize(CheckoutBody checkoutBody) {
            int i = 0;
            int encodedSizeWithTag = (checkoutBody.Errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, checkoutBody.Errno) : 0) + (checkoutBody.Errmsg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, checkoutBody.Errmsg) : 0);
            if (checkoutBody.Data != null) {
                i = CheckoutData.ADAPTER.encodedSizeWithTag(3, checkoutBody.Data);
            }
            return encodedSizeWithTag + i + checkoutBody.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, CheckoutBody checkoutBody) throws IOException {
            if (checkoutBody.Errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, checkoutBody.Errno);
            }
            if (checkoutBody.Errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, checkoutBody.Errmsg);
            }
            if (checkoutBody.Data != null) {
                CheckoutData.ADAPTER.encodeWithTag(protoWriter, 3, checkoutBody.Data);
            }
            protoWriter.writeBytes(checkoutBody.unknownFields());
        }

        public CheckoutBody decode(ProtoReader protoReader) throws IOException {
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
                            builder.Data(CheckoutData.ADAPTER.decode(protoReader));
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

        public CheckoutBody redact(CheckoutBody checkoutBody) {
            Builder newBuilder = checkoutBody.newBuilder();
            if (newBuilder.Data != null) {
                newBuilder.Data = CheckoutData.ADAPTER.redact(newBuilder.Data);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
