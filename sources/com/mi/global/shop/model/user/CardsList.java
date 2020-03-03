package com.mi.global.shop.model.user;

import com.mi.global.shop.model.common.UserCardsType;
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

public final class CardsList extends Message<CardsList, Builder> {
    public static final ProtoAdapter<CardsList> ADAPTER = new ProtoAdapter_CardsList();
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.common.UserCardsType#ADAPTER", label = WireField.Label.REPEATED, tag = 1)
    public final List<UserCardsType> user_cards;

    public CardsList(List<UserCardsType> list) {
        this(list, ByteString.EMPTY);
    }

    public CardsList(List<UserCardsType> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.user_cards = Internal.immutableCopyOf("user_cards", list);
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.user_cards = Internal.copyOf("user_cards", this.user_cards);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CardsList)) {
            return false;
        }
        CardsList cardsList = (CardsList) obj;
        if (!Internal.equals(unknownFields(), cardsList.unknownFields()) || !Internal.equals(this.user_cards, cardsList.user_cards)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = (unknownFields().hashCode() * 37) + (this.user_cards != null ? this.user_cards.hashCode() : 1);
        this.hashCode = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.user_cards != null) {
            sb.append(", user_cards=");
            sb.append(this.user_cards);
        }
        StringBuilder replace = sb.replace(0, 2, "CardsList{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<CardsList, Builder> {
        public List<UserCardsType> user_cards = Internal.newMutableList();

        public Builder user_cards(List<UserCardsType> list) {
            Internal.checkElementsNotNull(list);
            this.user_cards = list;
            return this;
        }

        public CardsList build() {
            return new CardsList(this.user_cards, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_CardsList extends ProtoAdapter<CardsList> {
        ProtoAdapter_CardsList() {
            super(FieldEncoding.LENGTH_DELIMITED, CardsList.class);
        }

        public int encodedSize(CardsList cardsList) {
            return UserCardsType.ADAPTER.asRepeated().encodedSizeWithTag(1, cardsList.user_cards) + cardsList.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, CardsList cardsList) throws IOException {
            if (cardsList.user_cards != null) {
                UserCardsType.ADAPTER.asRepeated().encodeWithTag(protoWriter, 1, cardsList.user_cards);
            }
            protoWriter.writeBytes(cardsList.unknownFields());
        }

        public CardsList decode(ProtoReader protoReader) throws IOException {
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
                    builder.user_cards.add(UserCardsType.ADAPTER.decode(protoReader));
                }
            }
        }

        public CardsList redact(CardsList cardsList) {
            Builder newBuilder = cardsList.newBuilder();
            Internal.redactElements(newBuilder.user_cards, UserCardsType.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
