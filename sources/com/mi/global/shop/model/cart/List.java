package com.mi.global.shop.model.cart;

import com.mi.global.shop.model.common.CartData;
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

public final class List extends Message<List, Builder> {
    public static final ProtoAdapter<List> ADAPTER = new ProtoAdapter_List();
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.common.CartData#ADAPTER", tag = 3)
    public final CartData data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer errno;

    public List(Integer num, String str, CartData cartData) {
        this(num, str, cartData, ByteString.EMPTY);
    }

    public List(Integer num, String str, CartData cartData, ByteString byteString) {
        super(ADAPTER, byteString);
        this.errno = num;
        this.errmsg = str;
        this.data = cartData;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.errno = this.errno;
        builder.errmsg = this.errmsg;
        builder.data = this.data;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List list = (List) obj;
        if (!Internal.equals(unknownFields(), list.unknownFields()) || !Internal.equals(this.errno, list.errno) || !Internal.equals(this.errmsg, list.errmsg) || !Internal.equals(this.data, list.data)) {
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
        int hashCode = ((((unknownFields().hashCode() * 37) + (this.errno != null ? this.errno.hashCode() : 0)) * 37) + (this.errmsg != null ? this.errmsg.hashCode() : 0)) * 37;
        if (this.data != null) {
            i2 = this.data.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.errno != null) {
            sb.append(", errno=");
            sb.append(this.errno);
        }
        if (this.errmsg != null) {
            sb.append(", errmsg=");
            sb.append(this.errmsg);
        }
        if (this.data != null) {
            sb.append(", data=");
            sb.append(this.data);
        }
        StringBuilder replace = sb.replace(0, 2, "List{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<List, Builder> {
        public CartData data;
        public String errmsg;
        public Integer errno;

        public Builder errno(Integer num) {
            this.errno = num;
            return this;
        }

        public Builder errmsg(String str) {
            this.errmsg = str;
            return this;
        }

        public Builder data(CartData cartData) {
            this.data = cartData;
            return this;
        }

        public List build() {
            return new List(this.errno, this.errmsg, this.data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_List extends ProtoAdapter<List> {
        ProtoAdapter_List() {
            super(FieldEncoding.LENGTH_DELIMITED, List.class);
        }

        public int encodedSize(List list) {
            int i = 0;
            int encodedSizeWithTag = (list.errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, list.errno) : 0) + (list.errmsg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, list.errmsg) : 0);
            if (list.data != null) {
                i = CartData.ADAPTER.encodedSizeWithTag(3, list.data);
            }
            return encodedSizeWithTag + i + list.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, List list) throws IOException {
            if (list.errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, list.errno);
            }
            if (list.errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, list.errmsg);
            }
            if (list.data != null) {
                CartData.ADAPTER.encodeWithTag(protoWriter, 3, list.data);
            }
            protoWriter.writeBytes(list.unknownFields());
        }

        public List decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.errno(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 2:
                            builder.errmsg(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.data(CartData.ADAPTER.decode(protoReader));
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

        public List redact(List list) {
            Builder newBuilder = list.newBuilder();
            if (newBuilder.data != null) {
                newBuilder.data = CartData.ADAPTER.redact(newBuilder.data);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
