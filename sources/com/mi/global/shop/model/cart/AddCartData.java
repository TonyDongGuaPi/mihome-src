package com.mi.global.shop.model.cart;

import com.mi.global.shop.model.common.EditCartData;
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

public final class AddCartData extends Message<AddCartData, Builder> {
    public static final ProtoAdapter<AddCartData> ADAPTER = new ProtoAdapter_AddCartData();
    public static final Boolean DEFAULT_RES = false;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.common.EditCartData#ADAPTER", tag = 2)
    public final EditCartData cartdata;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 1)
    public final Boolean res;

    public AddCartData(Boolean bool, EditCartData editCartData) {
        this(bool, editCartData, ByteString.EMPTY);
    }

    public AddCartData(Boolean bool, EditCartData editCartData, ByteString byteString) {
        super(ADAPTER, byteString);
        this.res = bool;
        this.cartdata = editCartData;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.res = this.res;
        builder.cartdata = this.cartdata;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AddCartData)) {
            return false;
        }
        AddCartData addCartData = (AddCartData) obj;
        if (!Internal.equals(unknownFields(), addCartData.unknownFields()) || !Internal.equals(this.res, addCartData.res) || !Internal.equals(this.cartdata, addCartData.cartdata)) {
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
        int hashCode = ((unknownFields().hashCode() * 37) + (this.res != null ? this.res.hashCode() : 0)) * 37;
        if (this.cartdata != null) {
            i2 = this.cartdata.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.res != null) {
            sb.append(", res=");
            sb.append(this.res);
        }
        if (this.cartdata != null) {
            sb.append(", cartdata=");
            sb.append(this.cartdata);
        }
        StringBuilder replace = sb.replace(0, 2, "AddCartData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<AddCartData, Builder> {
        public EditCartData cartdata;
        public Boolean res;

        public Builder res(Boolean bool) {
            this.res = bool;
            return this;
        }

        public Builder cartdata(EditCartData editCartData) {
            this.cartdata = editCartData;
            return this;
        }

        public AddCartData build() {
            return new AddCartData(this.res, this.cartdata, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_AddCartData extends ProtoAdapter<AddCartData> {
        ProtoAdapter_AddCartData() {
            super(FieldEncoding.LENGTH_DELIMITED, AddCartData.class);
        }

        public int encodedSize(AddCartData addCartData) {
            int i = 0;
            int encodedSizeWithTag = addCartData.res != null ? ProtoAdapter.BOOL.encodedSizeWithTag(1, addCartData.res) : 0;
            if (addCartData.cartdata != null) {
                i = EditCartData.ADAPTER.encodedSizeWithTag(2, addCartData.cartdata);
            }
            return encodedSizeWithTag + i + addCartData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, AddCartData addCartData) throws IOException {
            if (addCartData.res != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 1, addCartData.res);
            }
            if (addCartData.cartdata != null) {
                EditCartData.ADAPTER.encodeWithTag(protoWriter, 2, addCartData.cartdata);
            }
            protoWriter.writeBytes(addCartData.unknownFields());
        }

        public AddCartData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.res(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 2:
                            builder.cartdata(EditCartData.ADAPTER.decode(protoReader));
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

        public AddCartData redact(AddCartData addCartData) {
            Builder newBuilder = addCartData.newBuilder();
            if (newBuilder.cartdata != null) {
                newBuilder.cartdata = EditCartData.ADAPTER.redact(newBuilder.cartdata);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
