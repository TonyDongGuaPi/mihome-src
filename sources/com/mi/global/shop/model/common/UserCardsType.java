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

public final class UserCardsType extends Message<UserCardsType, Builder> {
    public static final ProtoAdapter<UserCardsType> ADAPTER = new ProtoAdapter_UserCardsType();
    public static final String DEFAULT_CARD_BRAND = "";
    public static final String DEFAULT_CARD_MODE = "";
    public static final String DEFAULT_CARD_NAME = "";
    public static final String DEFAULT_CARD_NO = "";
    public static final String DEFAULT_CARD_TOKEN = "";
    public static final String DEFAULT_CARD_TYPE = "";
    public static final String DEFAULT_EXPIRY_MONTH = "";
    public static final String DEFAULT_EXPIRY_YEAR = "";
    public static final String DEFAULT_NAME_ON_CARD = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final String card_brand;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String card_mode;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String card_name;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String card_no;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String card_token;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String card_type;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String expiry_month;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String expiry_year;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String name_on_card;

    public UserCardsType(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this(str, str2, str3, str4, str5, str6, str7, str8, str9, ByteString.EMPTY);
    }

    public UserCardsType(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, ByteString byteString) {
        super(ADAPTER, byteString);
        this.card_name = str;
        this.card_type = str2;
        this.card_token = str3;
        this.expiry_year = str4;
        this.expiry_month = str5;
        this.name_on_card = str6;
        this.card_no = str7;
        this.card_mode = str8;
        this.card_brand = str9;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.card_name = this.card_name;
        builder.card_type = this.card_type;
        builder.card_token = this.card_token;
        builder.expiry_year = this.expiry_year;
        builder.expiry_month = this.expiry_month;
        builder.name_on_card = this.name_on_card;
        builder.card_no = this.card_no;
        builder.card_mode = this.card_mode;
        builder.card_brand = this.card_brand;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UserCardsType)) {
            return false;
        }
        UserCardsType userCardsType = (UserCardsType) obj;
        if (!Internal.equals(unknownFields(), userCardsType.unknownFields()) || !Internal.equals(this.card_name, userCardsType.card_name) || !Internal.equals(this.card_type, userCardsType.card_type) || !Internal.equals(this.card_token, userCardsType.card_token) || !Internal.equals(this.expiry_year, userCardsType.expiry_year) || !Internal.equals(this.expiry_month, userCardsType.expiry_month) || !Internal.equals(this.name_on_card, userCardsType.name_on_card) || !Internal.equals(this.card_no, userCardsType.card_no) || !Internal.equals(this.card_mode, userCardsType.card_mode) || !Internal.equals(this.card_brand, userCardsType.card_brand)) {
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
        int hashCode = ((((((((((((((((unknownFields().hashCode() * 37) + (this.card_name != null ? this.card_name.hashCode() : 0)) * 37) + (this.card_type != null ? this.card_type.hashCode() : 0)) * 37) + (this.card_token != null ? this.card_token.hashCode() : 0)) * 37) + (this.expiry_year != null ? this.expiry_year.hashCode() : 0)) * 37) + (this.expiry_month != null ? this.expiry_month.hashCode() : 0)) * 37) + (this.name_on_card != null ? this.name_on_card.hashCode() : 0)) * 37) + (this.card_no != null ? this.card_no.hashCode() : 0)) * 37) + (this.card_mode != null ? this.card_mode.hashCode() : 0)) * 37;
        if (this.card_brand != null) {
            i2 = this.card_brand.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.card_name != null) {
            sb.append(", card_name=");
            sb.append(this.card_name);
        }
        if (this.card_type != null) {
            sb.append(", card_type=");
            sb.append(this.card_type);
        }
        if (this.card_token != null) {
            sb.append(", card_token=");
            sb.append(this.card_token);
        }
        if (this.expiry_year != null) {
            sb.append(", expiry_year=");
            sb.append(this.expiry_year);
        }
        if (this.expiry_month != null) {
            sb.append(", expiry_month=");
            sb.append(this.expiry_month);
        }
        if (this.name_on_card != null) {
            sb.append(", name_on_card=");
            sb.append(this.name_on_card);
        }
        if (this.card_no != null) {
            sb.append(", card_no=");
            sb.append(this.card_no);
        }
        if (this.card_mode != null) {
            sb.append(", card_mode=");
            sb.append(this.card_mode);
        }
        if (this.card_brand != null) {
            sb.append(", card_brand=");
            sb.append(this.card_brand);
        }
        StringBuilder replace = sb.replace(0, 2, "UserCardsType{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<UserCardsType, Builder> {
        public String card_brand;
        public String card_mode;
        public String card_name;
        public String card_no;
        public String card_token;
        public String card_type;
        public String expiry_month;
        public String expiry_year;
        public String name_on_card;

        public Builder card_name(String str) {
            this.card_name = str;
            return this;
        }

        public Builder card_type(String str) {
            this.card_type = str;
            return this;
        }

        public Builder card_token(String str) {
            this.card_token = str;
            return this;
        }

        public Builder expiry_year(String str) {
            this.expiry_year = str;
            return this;
        }

        public Builder expiry_month(String str) {
            this.expiry_month = str;
            return this;
        }

        public Builder name_on_card(String str) {
            this.name_on_card = str;
            return this;
        }

        public Builder card_no(String str) {
            this.card_no = str;
            return this;
        }

        public Builder card_mode(String str) {
            this.card_mode = str;
            return this;
        }

        public Builder card_brand(String str) {
            this.card_brand = str;
            return this;
        }

        public UserCardsType build() {
            return new UserCardsType(this.card_name, this.card_type, this.card_token, this.expiry_year, this.expiry_month, this.name_on_card, this.card_no, this.card_mode, this.card_brand, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_UserCardsType extends ProtoAdapter<UserCardsType> {
        ProtoAdapter_UserCardsType() {
            super(FieldEncoding.LENGTH_DELIMITED, UserCardsType.class);
        }

        public int encodedSize(UserCardsType userCardsType) {
            int i = 0;
            int encodedSizeWithTag = (userCardsType.card_name != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, userCardsType.card_name) : 0) + (userCardsType.card_type != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, userCardsType.card_type) : 0) + (userCardsType.card_token != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, userCardsType.card_token) : 0) + (userCardsType.expiry_year != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, userCardsType.expiry_year) : 0) + (userCardsType.expiry_month != null ? ProtoAdapter.STRING.encodedSizeWithTag(5, userCardsType.expiry_month) : 0) + (userCardsType.name_on_card != null ? ProtoAdapter.STRING.encodedSizeWithTag(6, userCardsType.name_on_card) : 0) + (userCardsType.card_no != null ? ProtoAdapter.STRING.encodedSizeWithTag(7, userCardsType.card_no) : 0) + (userCardsType.card_mode != null ? ProtoAdapter.STRING.encodedSizeWithTag(8, userCardsType.card_mode) : 0);
            if (userCardsType.card_brand != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(9, userCardsType.card_brand);
            }
            return encodedSizeWithTag + i + userCardsType.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, UserCardsType userCardsType) throws IOException {
            if (userCardsType.card_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, userCardsType.card_name);
            }
            if (userCardsType.card_type != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, userCardsType.card_type);
            }
            if (userCardsType.card_token != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, userCardsType.card_token);
            }
            if (userCardsType.expiry_year != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, userCardsType.expiry_year);
            }
            if (userCardsType.expiry_month != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, userCardsType.expiry_month);
            }
            if (userCardsType.name_on_card != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, userCardsType.name_on_card);
            }
            if (userCardsType.card_no != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, userCardsType.card_no);
            }
            if (userCardsType.card_mode != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, userCardsType.card_mode);
            }
            if (userCardsType.card_brand != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 9, userCardsType.card_brand);
            }
            protoWriter.writeBytes(userCardsType.unknownFields());
        }

        public UserCardsType decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.card_name(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.card_type(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.card_token(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.expiry_year(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 5:
                            builder.expiry_month(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 6:
                            builder.name_on_card(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 7:
                            builder.card_no(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 8:
                            builder.card_mode(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 9:
                            builder.card_brand(ProtoAdapter.STRING.decode(protoReader));
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

        public UserCardsType redact(UserCardsType userCardsType) {
            Builder newBuilder = userCardsType.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
