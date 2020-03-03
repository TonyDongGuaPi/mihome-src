package com.mi.global.shop.model.common;

import com.payu.sdk.Constants;
import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.util.List;
import okio.ByteString;

public final class PayList extends Message<PayList, Builder> {
    public static final ProtoAdapter<PayList> ADAPTER = new ProtoAdapter_PayList();
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", label = WireField.Label.REPEATED, tag = 2)
    public final List<String> cards;
    @WireField(adapter = "com.mi.global.shop.model.common.Emi#ADAPTER", label = WireField.Label.REPEATED, tag = 1)
    public final List<Emi> emi;
    @WireField(adapter = "com.mi.global.shop.model.common.Netbank#ADAPTER", tag = 3)
    public final Netbank netbank;
    @WireField(adapter = "com.mi.global.shop.model.common.Wallet#ADAPTER", tag = 4)
    public final Wallet wallet;

    public PayList(List<Emi> list, List<String> list2, Netbank netbank2, Wallet wallet2) {
        this(list, list2, netbank2, wallet2, ByteString.EMPTY);
    }

    public PayList(List<Emi> list, List<String> list2, Netbank netbank2, Wallet wallet2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.emi = Internal.immutableCopyOf(Constants.PAYTYPE_EMI, list);
        this.cards = Internal.immutableCopyOf("cards", list2);
        this.netbank = netbank2;
        this.wallet = wallet2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.emi = Internal.copyOf(Constants.PAYTYPE_EMI, this.emi);
        builder.cards = Internal.copyOf("cards", this.cards);
        builder.netbank = this.netbank;
        builder.wallet = this.wallet;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PayList)) {
            return false;
        }
        PayList payList = (PayList) obj;
        if (!Internal.equals(unknownFields(), payList.unknownFields()) || !Internal.equals(this.emi, payList.emi) || !Internal.equals(this.cards, payList.cards) || !Internal.equals(this.netbank, payList.netbank) || !Internal.equals(this.wallet, payList.wallet)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int i2 = 1;
        int hashCode = ((unknownFields().hashCode() * 37) + (this.emi != null ? this.emi.hashCode() : 1)) * 37;
        if (this.cards != null) {
            i2 = this.cards.hashCode();
        }
        int i3 = (hashCode + i2) * 37;
        int i4 = 0;
        int hashCode2 = (i3 + (this.netbank != null ? this.netbank.hashCode() : 0)) * 37;
        if (this.wallet != null) {
            i4 = this.wallet.hashCode();
        }
        int i5 = hashCode2 + i4;
        this.hashCode = i5;
        return i5;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.emi != null) {
            sb.append(", emi=");
            sb.append(this.emi);
        }
        if (this.cards != null) {
            sb.append(", cards=");
            sb.append(this.cards);
        }
        if (this.netbank != null) {
            sb.append(", netbank=");
            sb.append(this.netbank);
        }
        if (this.wallet != null) {
            sb.append(", wallet=");
            sb.append(this.wallet);
        }
        StringBuilder replace = sb.replace(0, 2, "PayList{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<PayList, Builder> {
        public List<String> cards = Internal.newMutableList();
        public List<Emi> emi = Internal.newMutableList();
        public Netbank netbank;
        public Wallet wallet;

        public Builder emi(List<Emi> list) {
            Internal.checkElementsNotNull(list);
            this.emi = list;
            return this;
        }

        public Builder cards(List<String> list) {
            Internal.checkElementsNotNull(list);
            this.cards = list;
            return this;
        }

        public Builder netbank(Netbank netbank2) {
            this.netbank = netbank2;
            return this;
        }

        public Builder wallet(Wallet wallet2) {
            this.wallet = wallet2;
            return this;
        }

        public PayList build() {
            return new PayList(this.emi, this.cards, this.netbank, this.wallet, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_PayList extends ProtoAdapter<PayList> {
        ProtoAdapter_PayList() {
            super(FieldEncoding.LENGTH_DELIMITED, PayList.class);
        }

        public int encodedSize(PayList payList) {
            int i = 0;
            int encodedSizeWithTag = Emi.ADAPTER.asRepeated().encodedSizeWithTag(1, payList.emi) + ProtoAdapter.STRING.asRepeated().encodedSizeWithTag(2, payList.cards) + (payList.netbank != null ? Netbank.ADAPTER.encodedSizeWithTag(3, payList.netbank) : 0);
            if (payList.wallet != null) {
                i = Wallet.ADAPTER.encodedSizeWithTag(4, payList.wallet);
            }
            return encodedSizeWithTag + i + payList.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, PayList payList) throws IOException {
            if (payList.emi != null) {
                Emi.ADAPTER.asRepeated().encodeWithTag(protoWriter, 1, payList.emi);
            }
            if (payList.cards != null) {
                ProtoAdapter.STRING.asRepeated().encodeWithTag(protoWriter, 2, payList.cards);
            }
            if (payList.netbank != null) {
                Netbank.ADAPTER.encodeWithTag(protoWriter, 3, payList.netbank);
            }
            if (payList.wallet != null) {
                Wallet.ADAPTER.encodeWithTag(protoWriter, 4, payList.wallet);
            }
            protoWriter.writeBytes(payList.unknownFields());
        }

        public PayList decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.emi.add(Emi.ADAPTER.decode(protoReader));
                            break;
                        case 2:
                            builder.cards.add(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.netbank(Netbank.ADAPTER.decode(protoReader));
                            break;
                        case 4:
                            builder.wallet(Wallet.ADAPTER.decode(protoReader));
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

        public PayList redact(PayList payList) {
            Builder newBuilder = payList.newBuilder();
            Internal.redactElements(newBuilder.emi, Emi.ADAPTER);
            if (newBuilder.netbank != null) {
                newBuilder.netbank = Netbank.ADAPTER.redact(newBuilder.netbank);
            }
            if (newBuilder.wallet != null) {
                newBuilder.wallet = Wallet.ADAPTER.redact(newBuilder.wallet);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
