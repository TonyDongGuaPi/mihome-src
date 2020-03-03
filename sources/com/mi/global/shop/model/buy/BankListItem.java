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

public final class BankListItem extends Message<BankListItem, Builder> {
    public static final ProtoAdapter<BankListItem> ADAPTER = new ProtoAdapter_BankListItem();
    public static final String DEFAULT_DESC = "";
    public static final String DEFAULT_NAME = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String desc;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String name;

    public BankListItem(String str, String str2) {
        this(str, str2, ByteString.EMPTY);
    }

    public BankListItem(String str, String str2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.name = str;
        this.desc = str2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.name = this.name;
        builder.desc = this.desc;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BankListItem)) {
            return false;
        }
        BankListItem bankListItem = (BankListItem) obj;
        if (!Internal.equals(unknownFields(), bankListItem.unknownFields()) || !Internal.equals(this.name, bankListItem.name) || !Internal.equals(this.desc, bankListItem.desc)) {
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
        int hashCode = ((unknownFields().hashCode() * 37) + (this.name != null ? this.name.hashCode() : 0)) * 37;
        if (this.desc != null) {
            i2 = this.desc.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.name != null) {
            sb.append(", name=");
            sb.append(this.name);
        }
        if (this.desc != null) {
            sb.append(", desc=");
            sb.append(this.desc);
        }
        StringBuilder replace = sb.replace(0, 2, "BankListItem{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<BankListItem, Builder> {
        public String desc;
        public String name;

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder desc(String str) {
            this.desc = str;
            return this;
        }

        public BankListItem build() {
            return new BankListItem(this.name, this.desc, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_BankListItem extends ProtoAdapter<BankListItem> {
        ProtoAdapter_BankListItem() {
            super(FieldEncoding.LENGTH_DELIMITED, BankListItem.class);
        }

        public int encodedSize(BankListItem bankListItem) {
            int i = 0;
            int encodedSizeWithTag = bankListItem.name != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, bankListItem.name) : 0;
            if (bankListItem.desc != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(2, bankListItem.desc);
            }
            return encodedSizeWithTag + i + bankListItem.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, BankListItem bankListItem) throws IOException {
            if (bankListItem.name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, bankListItem.name);
            }
            if (bankListItem.desc != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, bankListItem.desc);
            }
            protoWriter.writeBytes(bankListItem.unknownFields());
        }

        public BankListItem decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.name(ProtoAdapter.STRING.decode(protoReader));
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

        public BankListItem redact(BankListItem bankListItem) {
            Builder newBuilder = bankListItem.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
