package com.mi.global.shop.model.user;

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

public final class CardsBody extends Message<CardsBody, Builder> {
    public static final ProtoAdapter<CardsBody> ADAPTER = new ProtoAdapter_CardsBody();
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.user.CardsList#ADAPTER", tag = 3)
    public final CardsList data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer errno;

    public CardsBody(Integer num, String str, CardsList cardsList) {
        this(num, str, cardsList, ByteString.EMPTY);
    }

    public CardsBody(Integer num, String str, CardsList cardsList, ByteString byteString) {
        super(ADAPTER, byteString);
        this.errno = num;
        this.errmsg = str;
        this.data = cardsList;
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
        if (!(obj instanceof CardsBody)) {
            return false;
        }
        CardsBody cardsBody = (CardsBody) obj;
        if (!Internal.equals(unknownFields(), cardsBody.unknownFields()) || !Internal.equals(this.errno, cardsBody.errno) || !Internal.equals(this.errmsg, cardsBody.errmsg) || !Internal.equals(this.data, cardsBody.data)) {
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
        StringBuilder replace = sb.replace(0, 2, "CardsBody{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<CardsBody, Builder> {
        public CardsList data;
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

        public Builder data(CardsList cardsList) {
            this.data = cardsList;
            return this;
        }

        public CardsBody build() {
            return new CardsBody(this.errno, this.errmsg, this.data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_CardsBody extends ProtoAdapter<CardsBody> {
        ProtoAdapter_CardsBody() {
            super(FieldEncoding.LENGTH_DELIMITED, CardsBody.class);
        }

        public int encodedSize(CardsBody cardsBody) {
            int i = 0;
            int encodedSizeWithTag = (cardsBody.errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, cardsBody.errno) : 0) + (cardsBody.errmsg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, cardsBody.errmsg) : 0);
            if (cardsBody.data != null) {
                i = CardsList.ADAPTER.encodedSizeWithTag(3, cardsBody.data);
            }
            return encodedSizeWithTag + i + cardsBody.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, CardsBody cardsBody) throws IOException {
            if (cardsBody.errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, cardsBody.errno);
            }
            if (cardsBody.errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, cardsBody.errmsg);
            }
            if (cardsBody.data != null) {
                CardsList.ADAPTER.encodeWithTag(protoWriter, 3, cardsBody.data);
            }
            protoWriter.writeBytes(cardsBody.unknownFields());
        }

        public CardsBody decode(ProtoReader protoReader) throws IOException {
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
                            builder.data(CardsList.ADAPTER.decode(protoReader));
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

        public CardsBody redact(CardsBody cardsBody) {
            Builder newBuilder = cardsBody.newBuilder();
            if (newBuilder.data != null) {
                newBuilder.data = CardsList.ADAPTER.redact(newBuilder.data);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
