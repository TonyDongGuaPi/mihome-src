package com.mi.global.shop.model.buy;

import com.mi.global.shop.model.basestruct.PageMessage;
import com.mi.global.shop.model.common.RegionData;
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

public final class CheckoutData extends Message<CheckoutData, Builder> {
    public static final ProtoAdapter<CheckoutData> ADAPTER = new ProtoAdapter_CheckoutData();
    public static final String DEFAULT_ADDRESS_LIST = "";
    public static final Integer DEFAULT_COUPONSCOUNT = 0;
    public static final String DEFAULT_COUPON_LIST = "";
    public static final String DEFAULT_CURRENCY = "";
    public static final Boolean DEFAULT_NEEDGOBACK = false;
    public static final Boolean DEFAULT_NEEDVCODE = false;
    public static final String DEFAULT_SHIPMENT_EXPENSE = "";
    public static final String DEFAULT_SHIPMENT_EXPENSE_TXT = "";
    public static final String DEFAULT_TOTALPAY = "";
    public static final String DEFAULT_TOTALPAY_TXT = "";
    public static final String DEFAULT_USERINFOURL = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 12)
    public final String address_list;
    @WireField(adapter = "com.mi.global.shop.model.buy.CartInfoData#ADAPTER", tag = 14)
    public final CartInfoData cart_info;
    @WireField(adapter = "com.mi.global.shop.model.buy.CheckoutInfoData#ADAPTER", tag = 1)
    public final CheckoutInfoData checkoutInfo;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String coupon_list;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 7)
    public final Integer couponsCount;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 10)
    public final String currency;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 13)
    public final Boolean needGoBack;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 11)
    public final Boolean needVcode;
    @WireField(adapter = "com.mi.global.shop.model.basestruct.PageMessage#ADAPTER", tag = 15)
    public final PageMessage pagemsg;
    @WireField(adapter = "com.mi.global.shop.model.common.RegionData#ADAPTER", label = WireField.Label.REPEATED, tag = 9)
    public final List<RegionData> region;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String shipment_expense;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String shipment_expense_txt;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String totalpay;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String totalpay_txt;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String userInfoUrl;

    public CheckoutData(CheckoutInfoData checkoutInfoData, String str, String str2, String str3, String str4, String str5, Integer num, String str6, List<RegionData> list, String str7, Boolean bool, String str8, Boolean bool2, CartInfoData cartInfoData, PageMessage pageMessage) {
        this(checkoutInfoData, str, str2, str3, str4, str5, num, str6, list, str7, bool, str8, bool2, cartInfoData, pageMessage, ByteString.EMPTY);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CheckoutData(CheckoutInfoData checkoutInfoData, String str, String str2, String str3, String str4, String str5, Integer num, String str6, List<RegionData> list, String str7, Boolean bool, String str8, Boolean bool2, CartInfoData cartInfoData, PageMessage pageMessage, ByteString byteString) {
        super(ADAPTER, byteString);
        this.checkoutInfo = checkoutInfoData;
        this.totalpay = str;
        this.totalpay_txt = str2;
        this.shipment_expense = str3;
        this.shipment_expense_txt = str4;
        this.coupon_list = str5;
        this.couponsCount = num;
        this.userInfoUrl = str6;
        List<RegionData> list2 = list;
        this.region = Internal.immutableCopyOf("region", list);
        this.currency = str7;
        this.needVcode = bool;
        this.address_list = str8;
        this.needGoBack = bool2;
        this.cart_info = cartInfoData;
        this.pagemsg = pageMessage;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.checkoutInfo = this.checkoutInfo;
        builder.totalpay = this.totalpay;
        builder.totalpay_txt = this.totalpay_txt;
        builder.shipment_expense = this.shipment_expense;
        builder.shipment_expense_txt = this.shipment_expense_txt;
        builder.coupon_list = this.coupon_list;
        builder.couponsCount = this.couponsCount;
        builder.userInfoUrl = this.userInfoUrl;
        builder.region = Internal.copyOf("region", this.region);
        builder.currency = this.currency;
        builder.needVcode = this.needVcode;
        builder.address_list = this.address_list;
        builder.needGoBack = this.needGoBack;
        builder.cart_info = this.cart_info;
        builder.pagemsg = this.pagemsg;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CheckoutData)) {
            return false;
        }
        CheckoutData checkoutData = (CheckoutData) obj;
        if (!Internal.equals(unknownFields(), checkoutData.unknownFields()) || !Internal.equals(this.checkoutInfo, checkoutData.checkoutInfo) || !Internal.equals(this.totalpay, checkoutData.totalpay) || !Internal.equals(this.totalpay_txt, checkoutData.totalpay_txt) || !Internal.equals(this.shipment_expense, checkoutData.shipment_expense) || !Internal.equals(this.shipment_expense_txt, checkoutData.shipment_expense_txt) || !Internal.equals(this.coupon_list, checkoutData.coupon_list) || !Internal.equals(this.couponsCount, checkoutData.couponsCount) || !Internal.equals(this.userInfoUrl, checkoutData.userInfoUrl) || !Internal.equals(this.region, checkoutData.region) || !Internal.equals(this.currency, checkoutData.currency) || !Internal.equals(this.needVcode, checkoutData.needVcode) || !Internal.equals(this.address_list, checkoutData.address_list) || !Internal.equals(this.needGoBack, checkoutData.needGoBack) || !Internal.equals(this.cart_info, checkoutData.cart_info) || !Internal.equals(this.pagemsg, checkoutData.pagemsg)) {
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
        int hashCode = ((((((((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.checkoutInfo != null ? this.checkoutInfo.hashCode() : 0)) * 37) + (this.totalpay != null ? this.totalpay.hashCode() : 0)) * 37) + (this.totalpay_txt != null ? this.totalpay_txt.hashCode() : 0)) * 37) + (this.shipment_expense != null ? this.shipment_expense.hashCode() : 0)) * 37) + (this.shipment_expense_txt != null ? this.shipment_expense_txt.hashCode() : 0)) * 37) + (this.coupon_list != null ? this.coupon_list.hashCode() : 0)) * 37) + (this.couponsCount != null ? this.couponsCount.hashCode() : 0)) * 37) + (this.userInfoUrl != null ? this.userInfoUrl.hashCode() : 0)) * 37) + (this.region != null ? this.region.hashCode() : 1)) * 37) + (this.currency != null ? this.currency.hashCode() : 0)) * 37) + (this.needVcode != null ? this.needVcode.hashCode() : 0)) * 37) + (this.address_list != null ? this.address_list.hashCode() : 0)) * 37) + (this.needGoBack != null ? this.needGoBack.hashCode() : 0)) * 37) + (this.cart_info != null ? this.cart_info.hashCode() : 0)) * 37;
        if (this.pagemsg != null) {
            i2 = this.pagemsg.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.checkoutInfo != null) {
            sb.append(", checkoutInfo=");
            sb.append(this.checkoutInfo);
        }
        if (this.totalpay != null) {
            sb.append(", totalpay=");
            sb.append(this.totalpay);
        }
        if (this.totalpay_txt != null) {
            sb.append(", totalpay_txt=");
            sb.append(this.totalpay_txt);
        }
        if (this.shipment_expense != null) {
            sb.append(", shipment_expense=");
            sb.append(this.shipment_expense);
        }
        if (this.shipment_expense_txt != null) {
            sb.append(", shipment_expense_txt=");
            sb.append(this.shipment_expense_txt);
        }
        if (this.coupon_list != null) {
            sb.append(", coupon_list=");
            sb.append(this.coupon_list);
        }
        if (this.couponsCount != null) {
            sb.append(", couponsCount=");
            sb.append(this.couponsCount);
        }
        if (this.userInfoUrl != null) {
            sb.append(", userInfoUrl=");
            sb.append(this.userInfoUrl);
        }
        if (this.region != null) {
            sb.append(", region=");
            sb.append(this.region);
        }
        if (this.currency != null) {
            sb.append(", currency=");
            sb.append(this.currency);
        }
        if (this.needVcode != null) {
            sb.append(", needVcode=");
            sb.append(this.needVcode);
        }
        if (this.address_list != null) {
            sb.append(", address_list=");
            sb.append(this.address_list);
        }
        if (this.needGoBack != null) {
            sb.append(", needGoBack=");
            sb.append(this.needGoBack);
        }
        if (this.cart_info != null) {
            sb.append(", cart_info=");
            sb.append(this.cart_info);
        }
        if (this.pagemsg != null) {
            sb.append(", pagemsg=");
            sb.append(this.pagemsg);
        }
        StringBuilder replace = sb.replace(0, 2, "CheckoutData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<CheckoutData, Builder> {
        public String address_list;
        public CartInfoData cart_info;
        public CheckoutInfoData checkoutInfo;
        public String coupon_list;
        public Integer couponsCount;
        public String currency;
        public Boolean needGoBack;
        public Boolean needVcode;
        public PageMessage pagemsg;
        public List<RegionData> region = Internal.newMutableList();
        public String shipment_expense;
        public String shipment_expense_txt;
        public String totalpay;
        public String totalpay_txt;
        public String userInfoUrl;

        public Builder checkoutInfo(CheckoutInfoData checkoutInfoData) {
            this.checkoutInfo = checkoutInfoData;
            return this;
        }

        public Builder totalpay(String str) {
            this.totalpay = str;
            return this;
        }

        public Builder totalpay_txt(String str) {
            this.totalpay_txt = str;
            return this;
        }

        public Builder shipment_expense(String str) {
            this.shipment_expense = str;
            return this;
        }

        public Builder shipment_expense_txt(String str) {
            this.shipment_expense_txt = str;
            return this;
        }

        public Builder coupon_list(String str) {
            this.coupon_list = str;
            return this;
        }

        public Builder couponsCount(Integer num) {
            this.couponsCount = num;
            return this;
        }

        public Builder userInfoUrl(String str) {
            this.userInfoUrl = str;
            return this;
        }

        public Builder region(List<RegionData> list) {
            Internal.checkElementsNotNull(list);
            this.region = list;
            return this;
        }

        public Builder currency(String str) {
            this.currency = str;
            return this;
        }

        public Builder needVcode(Boolean bool) {
            this.needVcode = bool;
            return this;
        }

        public Builder address_list(String str) {
            this.address_list = str;
            return this;
        }

        public Builder needGoBack(Boolean bool) {
            this.needGoBack = bool;
            return this;
        }

        public Builder cart_info(CartInfoData cartInfoData) {
            this.cart_info = cartInfoData;
            return this;
        }

        public Builder pagemsg(PageMessage pageMessage) {
            this.pagemsg = pageMessage;
            return this;
        }

        public CheckoutData build() {
            return new CheckoutData(this.checkoutInfo, this.totalpay, this.totalpay_txt, this.shipment_expense, this.shipment_expense_txt, this.coupon_list, this.couponsCount, this.userInfoUrl, this.region, this.currency, this.needVcode, this.address_list, this.needGoBack, this.cart_info, this.pagemsg, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_CheckoutData extends ProtoAdapter<CheckoutData> {
        ProtoAdapter_CheckoutData() {
            super(FieldEncoding.LENGTH_DELIMITED, CheckoutData.class);
        }

        public int encodedSize(CheckoutData checkoutData) {
            int i = 0;
            int encodedSizeWithTag = (checkoutData.checkoutInfo != null ? CheckoutInfoData.ADAPTER.encodedSizeWithTag(1, checkoutData.checkoutInfo) : 0) + (checkoutData.totalpay != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, checkoutData.totalpay) : 0) + (checkoutData.totalpay_txt != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, checkoutData.totalpay_txt) : 0) + (checkoutData.shipment_expense != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, checkoutData.shipment_expense) : 0) + (checkoutData.shipment_expense_txt != null ? ProtoAdapter.STRING.encodedSizeWithTag(5, checkoutData.shipment_expense_txt) : 0) + (checkoutData.coupon_list != null ? ProtoAdapter.STRING.encodedSizeWithTag(6, checkoutData.coupon_list) : 0) + (checkoutData.couponsCount != null ? ProtoAdapter.INT32.encodedSizeWithTag(7, checkoutData.couponsCount) : 0) + (checkoutData.userInfoUrl != null ? ProtoAdapter.STRING.encodedSizeWithTag(8, checkoutData.userInfoUrl) : 0) + RegionData.ADAPTER.asRepeated().encodedSizeWithTag(9, checkoutData.region) + (checkoutData.currency != null ? ProtoAdapter.STRING.encodedSizeWithTag(10, checkoutData.currency) : 0) + (checkoutData.needVcode != null ? ProtoAdapter.BOOL.encodedSizeWithTag(11, checkoutData.needVcode) : 0) + (checkoutData.address_list != null ? ProtoAdapter.STRING.encodedSizeWithTag(12, checkoutData.address_list) : 0) + (checkoutData.needGoBack != null ? ProtoAdapter.BOOL.encodedSizeWithTag(13, checkoutData.needGoBack) : 0) + (checkoutData.cart_info != null ? CartInfoData.ADAPTER.encodedSizeWithTag(14, checkoutData.cart_info) : 0);
            if (checkoutData.pagemsg != null) {
                i = PageMessage.ADAPTER.encodedSizeWithTag(15, checkoutData.pagemsg);
            }
            return encodedSizeWithTag + i + checkoutData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, CheckoutData checkoutData) throws IOException {
            if (checkoutData.checkoutInfo != null) {
                CheckoutInfoData.ADAPTER.encodeWithTag(protoWriter, 1, checkoutData.checkoutInfo);
            }
            if (checkoutData.totalpay != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, checkoutData.totalpay);
            }
            if (checkoutData.totalpay_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, checkoutData.totalpay_txt);
            }
            if (checkoutData.shipment_expense != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, checkoutData.shipment_expense);
            }
            if (checkoutData.shipment_expense_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, checkoutData.shipment_expense_txt);
            }
            if (checkoutData.coupon_list != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, checkoutData.coupon_list);
            }
            if (checkoutData.couponsCount != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 7, checkoutData.couponsCount);
            }
            if (checkoutData.userInfoUrl != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, checkoutData.userInfoUrl);
            }
            if (checkoutData.region != null) {
                RegionData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 9, checkoutData.region);
            }
            if (checkoutData.currency != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 10, checkoutData.currency);
            }
            if (checkoutData.needVcode != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 11, checkoutData.needVcode);
            }
            if (checkoutData.address_list != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 12, checkoutData.address_list);
            }
            if (checkoutData.needGoBack != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 13, checkoutData.needGoBack);
            }
            if (checkoutData.cart_info != null) {
                CartInfoData.ADAPTER.encodeWithTag(protoWriter, 14, checkoutData.cart_info);
            }
            if (checkoutData.pagemsg != null) {
                PageMessage.ADAPTER.encodeWithTag(protoWriter, 15, checkoutData.pagemsg);
            }
            protoWriter.writeBytes(checkoutData.unknownFields());
        }

        public CheckoutData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.checkoutInfo(CheckoutInfoData.ADAPTER.decode(protoReader));
                            break;
                        case 2:
                            builder.totalpay(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.totalpay_txt(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.shipment_expense(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 5:
                            builder.shipment_expense_txt(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 6:
                            builder.coupon_list(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 7:
                            builder.couponsCount(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 8:
                            builder.userInfoUrl(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 9:
                            builder.region.add(RegionData.ADAPTER.decode(protoReader));
                            break;
                        case 10:
                            builder.currency(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 11:
                            builder.needVcode(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 12:
                            builder.address_list(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 13:
                            builder.needGoBack(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 14:
                            builder.cart_info(CartInfoData.ADAPTER.decode(protoReader));
                            break;
                        case 15:
                            builder.pagemsg(PageMessage.ADAPTER.decode(protoReader));
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

        public CheckoutData redact(CheckoutData checkoutData) {
            Builder newBuilder = checkoutData.newBuilder();
            if (newBuilder.checkoutInfo != null) {
                newBuilder.checkoutInfo = CheckoutInfoData.ADAPTER.redact(newBuilder.checkoutInfo);
            }
            Internal.redactElements(newBuilder.region, RegionData.ADAPTER);
            if (newBuilder.cart_info != null) {
                newBuilder.cart_info = CartInfoData.ADAPTER.redact(newBuilder.cart_info);
            }
            if (newBuilder.pagemsg != null) {
                newBuilder.pagemsg = PageMessage.ADAPTER.redact(newBuilder.pagemsg);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
