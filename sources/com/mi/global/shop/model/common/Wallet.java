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

public final class Wallet extends Message<Wallet, Builder> {
    public static final ProtoAdapter<Wallet> ADAPTER = new ProtoAdapter_Wallet();
    public static final String DEFAULT_BANK = "";
    public static final String DEFAULT_DESC = "";
    public static final Boolean DEFAULT_MBKVALIDE = false;
    public static final String DEFAULT_MOBIKWIK_DESC = "";
    public static final String DEFAULT_MOBIKWIK_IMG = "";
    public static final String DEFAULT_PAYU_IMG = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String bank;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String desc;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 6)
    public final Boolean mbkValide;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String mobikwik_desc;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String mobikwik_img;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String payu_img;

    public Wallet(String str, String str2, String str3, String str4, String str5, Boolean bool) {
        this(str, str2, str3, str4, str5, bool, ByteString.EMPTY);
    }

    public Wallet(String str, String str2, String str3, String str4, String str5, Boolean bool, ByteString byteString) {
        super(ADAPTER, byteString);
        this.desc = str;
        this.bank = str2;
        this.payu_img = str3;
        this.mobikwik_img = str4;
        this.mobikwik_desc = str5;
        this.mbkValide = bool;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.desc = this.desc;
        builder.bank = this.bank;
        builder.payu_img = this.payu_img;
        builder.mobikwik_img = this.mobikwik_img;
        builder.mobikwik_desc = this.mobikwik_desc;
        builder.mbkValide = this.mbkValide;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Wallet)) {
            return false;
        }
        Wallet wallet = (Wallet) obj;
        if (!Internal.equals(unknownFields(), wallet.unknownFields()) || !Internal.equals(this.desc, wallet.desc) || !Internal.equals(this.bank, wallet.bank) || !Internal.equals(this.payu_img, wallet.payu_img) || !Internal.equals(this.mobikwik_img, wallet.mobikwik_img) || !Internal.equals(this.mobikwik_desc, wallet.mobikwik_desc) || !Internal.equals(this.mbkValide, wallet.mbkValide)) {
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
        int hashCode = ((((((((((unknownFields().hashCode() * 37) + (this.desc != null ? this.desc.hashCode() : 0)) * 37) + (this.bank != null ? this.bank.hashCode() : 0)) * 37) + (this.payu_img != null ? this.payu_img.hashCode() : 0)) * 37) + (this.mobikwik_img != null ? this.mobikwik_img.hashCode() : 0)) * 37) + (this.mobikwik_desc != null ? this.mobikwik_desc.hashCode() : 0)) * 37;
        if (this.mbkValide != null) {
            i2 = this.mbkValide.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.desc != null) {
            sb.append(", desc=");
            sb.append(this.desc);
        }
        if (this.bank != null) {
            sb.append(", bank=");
            sb.append(this.bank);
        }
        if (this.payu_img != null) {
            sb.append(", payu_img=");
            sb.append(this.payu_img);
        }
        if (this.mobikwik_img != null) {
            sb.append(", mobikwik_img=");
            sb.append(this.mobikwik_img);
        }
        if (this.mobikwik_desc != null) {
            sb.append(", mobikwik_desc=");
            sb.append(this.mobikwik_desc);
        }
        if (this.mbkValide != null) {
            sb.append(", mbkValide=");
            sb.append(this.mbkValide);
        }
        StringBuilder replace = sb.replace(0, 2, "Wallet{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Wallet, Builder> {
        public String bank;
        public String desc;
        public Boolean mbkValide;
        public String mobikwik_desc;
        public String mobikwik_img;
        public String payu_img;

        public Builder desc(String str) {
            this.desc = str;
            return this;
        }

        public Builder bank(String str) {
            this.bank = str;
            return this;
        }

        public Builder payu_img(String str) {
            this.payu_img = str;
            return this;
        }

        public Builder mobikwik_img(String str) {
            this.mobikwik_img = str;
            return this;
        }

        public Builder mobikwik_desc(String str) {
            this.mobikwik_desc = str;
            return this;
        }

        public Builder mbkValide(Boolean bool) {
            this.mbkValide = bool;
            return this;
        }

        public Wallet build() {
            return new Wallet(this.desc, this.bank, this.payu_img, this.mobikwik_img, this.mobikwik_desc, this.mbkValide, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Wallet extends ProtoAdapter<Wallet> {
        ProtoAdapter_Wallet() {
            super(FieldEncoding.LENGTH_DELIMITED, Wallet.class);
        }

        public int encodedSize(Wallet wallet) {
            int i = 0;
            int encodedSizeWithTag = (wallet.desc != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, wallet.desc) : 0) + (wallet.bank != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, wallet.bank) : 0) + (wallet.payu_img != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, wallet.payu_img) : 0) + (wallet.mobikwik_img != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, wallet.mobikwik_img) : 0) + (wallet.mobikwik_desc != null ? ProtoAdapter.STRING.encodedSizeWithTag(5, wallet.mobikwik_desc) : 0);
            if (wallet.mbkValide != null) {
                i = ProtoAdapter.BOOL.encodedSizeWithTag(6, wallet.mbkValide);
            }
            return encodedSizeWithTag + i + wallet.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, Wallet wallet) throws IOException {
            if (wallet.desc != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, wallet.desc);
            }
            if (wallet.bank != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, wallet.bank);
            }
            if (wallet.payu_img != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, wallet.payu_img);
            }
            if (wallet.mobikwik_img != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, wallet.mobikwik_img);
            }
            if (wallet.mobikwik_desc != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, wallet.mobikwik_desc);
            }
            if (wallet.mbkValide != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 6, wallet.mbkValide);
            }
            protoWriter.writeBytes(wallet.unknownFields());
        }

        public Wallet decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.desc(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.bank(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.payu_img(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.mobikwik_img(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 5:
                            builder.mobikwik_desc(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 6:
                            builder.mbkValide(ProtoAdapter.BOOL.decode(protoReader));
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

        public Wallet redact(Wallet wallet) {
            Builder newBuilder = wallet.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
