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
import java.util.List;
import okio.ByteString;

public final class PayParam extends Message<PayParam, Builder> {
    public static final ProtoAdapter<PayParam> ADAPTER = new ProtoAdapter_PayParam();
    public static final String DEFAULT_AMOUNT = "";
    public static final String DEFAULT_EMAIL = "";
    public static final String DEFAULT_ENFORCE_PAYMETHOD = "";
    public static final String DEFAULT_FIRSTNAME = "";
    public static final String DEFAULT_FURL = "";
    public static final String DEFAULT_HASH = "";
    public static final String DEFAULT_KEY = "";
    public static final String DEFAULT_PAY_URL = "";
    public static final String DEFAULT_PHONE = "";
    public static final String DEFAULT_PRODUCTINFO = "";
    public static final String DEFAULT_SIGN = "";
    public static final Boolean DEFAULT_SUPPORTSTORECARDS = false;
    public static final String DEFAULT_SURL = "";
    public static final String DEFAULT_TXNID = "";
    public static final String DEFAULT_USER_CREDENTIALS = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String amount;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String email;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 10)
    public final String enforce_paymethod;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String firstname;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final String furl;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 13)
    public final String hash;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String key;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 12)
    public final String pay_url;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String phone;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String productinfo;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 15)
    public final String sign;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 16)
    public final Boolean supportStoreCards;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String surl;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String txnid;
    @WireField(adapter = "com.mi.global.shop.model.common.UserCardsType#ADAPTER", label = WireField.Label.REPEATED, tag = 14)
    public final List<UserCardsType> user_cards;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 11)
    public final String user_credentials;

    public PayParam(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, List<UserCardsType> list, String str14, Boolean bool) {
        this(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, list, str14, bool, ByteString.EMPTY);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PayParam(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, List<UserCardsType> list, String str14, Boolean bool, ByteString byteString) {
        super(ADAPTER, byteString);
        this.key = str;
        this.txnid = str2;
        this.amount = str3;
        this.productinfo = str4;
        this.firstname = str5;
        this.email = str6;
        this.phone = str7;
        this.surl = str8;
        this.furl = str9;
        this.enforce_paymethod = str10;
        this.user_credentials = str11;
        this.pay_url = str12;
        this.hash = str13;
        this.user_cards = Internal.immutableCopyOf("user_cards", list);
        this.sign = str14;
        this.supportStoreCards = bool;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.key = this.key;
        builder.txnid = this.txnid;
        builder.amount = this.amount;
        builder.productinfo = this.productinfo;
        builder.firstname = this.firstname;
        builder.email = this.email;
        builder.phone = this.phone;
        builder.surl = this.surl;
        builder.furl = this.furl;
        builder.enforce_paymethod = this.enforce_paymethod;
        builder.user_credentials = this.user_credentials;
        builder.pay_url = this.pay_url;
        builder.hash = this.hash;
        builder.user_cards = Internal.copyOf("user_cards", this.user_cards);
        builder.sign = this.sign;
        builder.supportStoreCards = this.supportStoreCards;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PayParam)) {
            return false;
        }
        PayParam payParam = (PayParam) obj;
        if (!Internal.equals(unknownFields(), payParam.unknownFields()) || !Internal.equals(this.key, payParam.key) || !Internal.equals(this.txnid, payParam.txnid) || !Internal.equals(this.amount, payParam.amount) || !Internal.equals(this.productinfo, payParam.productinfo) || !Internal.equals(this.firstname, payParam.firstname) || !Internal.equals(this.email, payParam.email) || !Internal.equals(this.phone, payParam.phone) || !Internal.equals(this.surl, payParam.surl) || !Internal.equals(this.furl, payParam.furl) || !Internal.equals(this.enforce_paymethod, payParam.enforce_paymethod) || !Internal.equals(this.user_credentials, payParam.user_credentials) || !Internal.equals(this.pay_url, payParam.pay_url) || !Internal.equals(this.hash, payParam.hash) || !Internal.equals(this.user_cards, payParam.user_cards) || !Internal.equals(this.sign, payParam.sign) || !Internal.equals(this.supportStoreCards, payParam.supportStoreCards)) {
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
        int hashCode = ((((((((((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.key != null ? this.key.hashCode() : 0)) * 37) + (this.txnid != null ? this.txnid.hashCode() : 0)) * 37) + (this.amount != null ? this.amount.hashCode() : 0)) * 37) + (this.productinfo != null ? this.productinfo.hashCode() : 0)) * 37) + (this.firstname != null ? this.firstname.hashCode() : 0)) * 37) + (this.email != null ? this.email.hashCode() : 0)) * 37) + (this.phone != null ? this.phone.hashCode() : 0)) * 37) + (this.surl != null ? this.surl.hashCode() : 0)) * 37) + (this.furl != null ? this.furl.hashCode() : 0)) * 37) + (this.enforce_paymethod != null ? this.enforce_paymethod.hashCode() : 0)) * 37) + (this.user_credentials != null ? this.user_credentials.hashCode() : 0)) * 37) + (this.pay_url != null ? this.pay_url.hashCode() : 0)) * 37) + (this.hash != null ? this.hash.hashCode() : 0)) * 37) + (this.user_cards != null ? this.user_cards.hashCode() : 1)) * 37) + (this.sign != null ? this.sign.hashCode() : 0)) * 37;
        if (this.supportStoreCards != null) {
            i2 = this.supportStoreCards.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.key != null) {
            sb.append(", key=");
            sb.append(this.key);
        }
        if (this.txnid != null) {
            sb.append(", txnid=");
            sb.append(this.txnid);
        }
        if (this.amount != null) {
            sb.append(", amount=");
            sb.append(this.amount);
        }
        if (this.productinfo != null) {
            sb.append(", productinfo=");
            sb.append(this.productinfo);
        }
        if (this.firstname != null) {
            sb.append(", firstname=");
            sb.append(this.firstname);
        }
        if (this.email != null) {
            sb.append(", email=");
            sb.append(this.email);
        }
        if (this.phone != null) {
            sb.append(", phone=");
            sb.append(this.phone);
        }
        if (this.surl != null) {
            sb.append(", surl=");
            sb.append(this.surl);
        }
        if (this.furl != null) {
            sb.append(", furl=");
            sb.append(this.furl);
        }
        if (this.enforce_paymethod != null) {
            sb.append(", enforce_paymethod=");
            sb.append(this.enforce_paymethod);
        }
        if (this.user_credentials != null) {
            sb.append(", user_credentials=");
            sb.append(this.user_credentials);
        }
        if (this.pay_url != null) {
            sb.append(", pay_url=");
            sb.append(this.pay_url);
        }
        if (this.hash != null) {
            sb.append(", hash=");
            sb.append(this.hash);
        }
        if (this.user_cards != null) {
            sb.append(", user_cards=");
            sb.append(this.user_cards);
        }
        if (this.sign != null) {
            sb.append(", sign=");
            sb.append(this.sign);
        }
        if (this.supportStoreCards != null) {
            sb.append(", supportStoreCards=");
            sb.append(this.supportStoreCards);
        }
        StringBuilder replace = sb.replace(0, 2, "PayParam{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<PayParam, Builder> {
        public String amount;
        public String email;
        public String enforce_paymethod;
        public String firstname;
        public String furl;
        public String hash;
        public String key;
        public String pay_url;
        public String phone;
        public String productinfo;
        public String sign;
        public Boolean supportStoreCards;
        public String surl;
        public String txnid;
        public List<UserCardsType> user_cards = Internal.newMutableList();
        public String user_credentials;

        public Builder key(String str) {
            this.key = str;
            return this;
        }

        public Builder txnid(String str) {
            this.txnid = str;
            return this;
        }

        public Builder amount(String str) {
            this.amount = str;
            return this;
        }

        public Builder productinfo(String str) {
            this.productinfo = str;
            return this;
        }

        public Builder firstname(String str) {
            this.firstname = str;
            return this;
        }

        public Builder email(String str) {
            this.email = str;
            return this;
        }

        public Builder phone(String str) {
            this.phone = str;
            return this;
        }

        public Builder surl(String str) {
            this.surl = str;
            return this;
        }

        public Builder furl(String str) {
            this.furl = str;
            return this;
        }

        public Builder enforce_paymethod(String str) {
            this.enforce_paymethod = str;
            return this;
        }

        public Builder user_credentials(String str) {
            this.user_credentials = str;
            return this;
        }

        public Builder pay_url(String str) {
            this.pay_url = str;
            return this;
        }

        public Builder hash(String str) {
            this.hash = str;
            return this;
        }

        public Builder user_cards(List<UserCardsType> list) {
            Internal.checkElementsNotNull(list);
            this.user_cards = list;
            return this;
        }

        public Builder sign(String str) {
            this.sign = str;
            return this;
        }

        public Builder supportStoreCards(Boolean bool) {
            this.supportStoreCards = bool;
            return this;
        }

        public PayParam build() {
            return new PayParam(this.key, this.txnid, this.amount, this.productinfo, this.firstname, this.email, this.phone, this.surl, this.furl, this.enforce_paymethod, this.user_credentials, this.pay_url, this.hash, this.user_cards, this.sign, this.supportStoreCards, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_PayParam extends ProtoAdapter<PayParam> {
        ProtoAdapter_PayParam() {
            super(FieldEncoding.LENGTH_DELIMITED, PayParam.class);
        }

        public int encodedSize(PayParam payParam) {
            int i = 0;
            int encodedSizeWithTag = (payParam.key != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, payParam.key) : 0) + (payParam.txnid != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, payParam.txnid) : 0) + (payParam.amount != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, payParam.amount) : 0) + (payParam.productinfo != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, payParam.productinfo) : 0) + (payParam.firstname != null ? ProtoAdapter.STRING.encodedSizeWithTag(5, payParam.firstname) : 0) + (payParam.email != null ? ProtoAdapter.STRING.encodedSizeWithTag(6, payParam.email) : 0) + (payParam.phone != null ? ProtoAdapter.STRING.encodedSizeWithTag(7, payParam.phone) : 0) + (payParam.surl != null ? ProtoAdapter.STRING.encodedSizeWithTag(8, payParam.surl) : 0) + (payParam.furl != null ? ProtoAdapter.STRING.encodedSizeWithTag(9, payParam.furl) : 0) + (payParam.enforce_paymethod != null ? ProtoAdapter.STRING.encodedSizeWithTag(10, payParam.enforce_paymethod) : 0) + (payParam.user_credentials != null ? ProtoAdapter.STRING.encodedSizeWithTag(11, payParam.user_credentials) : 0) + (payParam.pay_url != null ? ProtoAdapter.STRING.encodedSizeWithTag(12, payParam.pay_url) : 0) + (payParam.hash != null ? ProtoAdapter.STRING.encodedSizeWithTag(13, payParam.hash) : 0) + UserCardsType.ADAPTER.asRepeated().encodedSizeWithTag(14, payParam.user_cards) + (payParam.sign != null ? ProtoAdapter.STRING.encodedSizeWithTag(15, payParam.sign) : 0);
            if (payParam.supportStoreCards != null) {
                i = ProtoAdapter.BOOL.encodedSizeWithTag(16, payParam.supportStoreCards);
            }
            return encodedSizeWithTag + i + payParam.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, PayParam payParam) throws IOException {
            if (payParam.key != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, payParam.key);
            }
            if (payParam.txnid != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, payParam.txnid);
            }
            if (payParam.amount != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, payParam.amount);
            }
            if (payParam.productinfo != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, payParam.productinfo);
            }
            if (payParam.firstname != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, payParam.firstname);
            }
            if (payParam.email != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, payParam.email);
            }
            if (payParam.phone != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, payParam.phone);
            }
            if (payParam.surl != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, payParam.surl);
            }
            if (payParam.furl != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 9, payParam.furl);
            }
            if (payParam.enforce_paymethod != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 10, payParam.enforce_paymethod);
            }
            if (payParam.user_credentials != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 11, payParam.user_credentials);
            }
            if (payParam.pay_url != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 12, payParam.pay_url);
            }
            if (payParam.hash != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 13, payParam.hash);
            }
            if (payParam.user_cards != null) {
                UserCardsType.ADAPTER.asRepeated().encodeWithTag(protoWriter, 14, payParam.user_cards);
            }
            if (payParam.sign != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 15, payParam.sign);
            }
            if (payParam.supportStoreCards != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 16, payParam.supportStoreCards);
            }
            protoWriter.writeBytes(payParam.unknownFields());
        }

        public PayParam decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.key(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.txnid(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.amount(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.productinfo(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 5:
                            builder.firstname(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 6:
                            builder.email(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 7:
                            builder.phone(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 8:
                            builder.surl(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 9:
                            builder.furl(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 10:
                            builder.enforce_paymethod(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 11:
                            builder.user_credentials(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 12:
                            builder.pay_url(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 13:
                            builder.hash(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 14:
                            builder.user_cards.add(UserCardsType.ADAPTER.decode(protoReader));
                            break;
                        case 15:
                            builder.sign(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 16:
                            builder.supportStoreCards(ProtoAdapter.BOOL.decode(protoReader));
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

        public PayParam redact(PayParam payParam) {
            Builder newBuilder = payParam.newBuilder();
            Internal.redactElements(newBuilder.user_cards, UserCardsType.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
