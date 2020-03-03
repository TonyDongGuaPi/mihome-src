package com.mi.global.shop.model.buy;

import com.mi.global.shop.model.Tags;
import com.mi.global.shop.model.common.AddressData;
import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import java.io.IOException;
import java.util.List;
import okio.ByteString;

public final class CheckoutInfoData extends Message<CheckoutInfoData, Builder> {
    public static final ProtoAdapter<CheckoutInfoData> ADAPTER = new ProtoAdapter_CheckoutInfoData();
    public static final String DEFAULT_ACTIVITYDISCOUNTMONEY = "";
    public static final String DEFAULT_ACTIVITYDISCOUNTMONEY_TXT = "";
    public static final String DEFAULT_AMOUNT = "";
    public static final String DEFAULT_INVOICE_TITLE = "";
    public static final String DEFAULT_NEED_PAY_AMOUNT = "";
    public static final String DEFAULT_PRODUCTMONEY = "";
    public static final String DEFAULT_PRODUCTMONEY_TXT = "";
    public static final String DEFAULT_SHIPMENT = "";
    public static final Integer DEFAULT_TOTAL = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final String activityDiscountMoney;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 14)
    public final String activityDiscountMoney_txt;
    @WireField(adapter = "com.mi.global.shop.model.common.AddressData#ADAPTER", tag = 1)
    public final AddressData address;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 11)
    public final String amount;
    @WireField(adapter = "com.mi.global.shop.model.buy.DelivertimeData#ADAPTER", label = WireField.Label.REPEATED, tag = 5)
    public final List<DelivertimeData> delivertime;
    @WireField(adapter = "com.mi.global.shop.model.buy.InvoiceData#ADAPTER", label = WireField.Label.REPEATED, tag = 6)
    public final List<InvoiceData> invoice;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String invoice_title;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 13)
    public final String need_pay_amount;
    @WireField(adapter = "com.mi.global.shop.model.buy.PaylistData#ADAPTER", label = WireField.Label.REPEATED, tag = 3)
    public final List<PaylistData> paylist;
    @WireField(adapter = "com.mi.global.shop.model.buy.PaymentData#ADAPTER", label = WireField.Label.REPEATED, tag = 2)
    public final List<PaymentData> payment;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String productMoney;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 15)
    public final String productMoney_txt;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 12)
    public final String shipment;
    @WireField(adapter = "com.mi.global.shop.model.buy.ShipmentlistData#ADAPTER", label = WireField.Label.REPEATED, tag = 4)
    public final List<ShipmentlistData> shipmentlist;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 10)
    public final Integer total;

    public CheckoutInfoData(AddressData addressData, List<PaymentData> list, List<PaylistData> list2, List<ShipmentlistData> list3, List<DelivertimeData> list4, List<InvoiceData> list5, String str, String str2, String str3, Integer num, String str4, String str5, String str6, String str7, String str8) {
        this(addressData, list, list2, list3, list4, list5, str, str2, str3, num, str4, str5, str6, str7, str8, ByteString.EMPTY);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CheckoutInfoData(AddressData addressData, List<PaymentData> list, List<PaylistData> list2, List<ShipmentlistData> list3, List<DelivertimeData> list4, List<InvoiceData> list5, String str, String str2, String str3, Integer num, String str4, String str5, String str6, String str7, String str8, ByteString byteString) {
        super(ADAPTER, byteString);
        this.address = addressData;
        List<PaymentData> list6 = list;
        this.payment = Internal.immutableCopyOf(UrlConstants.payment, list);
        List<PaylistData> list7 = list2;
        this.paylist = Internal.immutableCopyOf(Tags.CheckoutSubmit.PAYLIST, list2);
        List<ShipmentlistData> list8 = list3;
        this.shipmentlist = Internal.immutableCopyOf(Tags.CheckoutSubmit.SHIPMENTLIST, list3);
        List<DelivertimeData> list9 = list4;
        this.delivertime = Internal.immutableCopyOf(Tags.CheckoutSubmit.DELIVERTIME, list4);
        List<InvoiceData> list10 = list5;
        this.invoice = Internal.immutableCopyOf(Tags.CheckoutSubmit.INVOICE, list5);
        this.invoice_title = str;
        this.productMoney = str2;
        this.activityDiscountMoney = str3;
        this.total = num;
        this.amount = str4;
        this.shipment = str5;
        this.need_pay_amount = str6;
        this.activityDiscountMoney_txt = str7;
        this.productMoney_txt = str8;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.address = this.address;
        builder.payment = Internal.copyOf(UrlConstants.payment, this.payment);
        builder.paylist = Internal.copyOf(Tags.CheckoutSubmit.PAYLIST, this.paylist);
        builder.shipmentlist = Internal.copyOf(Tags.CheckoutSubmit.SHIPMENTLIST, this.shipmentlist);
        builder.delivertime = Internal.copyOf(Tags.CheckoutSubmit.DELIVERTIME, this.delivertime);
        builder.invoice = Internal.copyOf(Tags.CheckoutSubmit.INVOICE, this.invoice);
        builder.invoice_title = this.invoice_title;
        builder.productMoney = this.productMoney;
        builder.activityDiscountMoney = this.activityDiscountMoney;
        builder.total = this.total;
        builder.amount = this.amount;
        builder.shipment = this.shipment;
        builder.need_pay_amount = this.need_pay_amount;
        builder.activityDiscountMoney_txt = this.activityDiscountMoney_txt;
        builder.productMoney_txt = this.productMoney_txt;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CheckoutInfoData)) {
            return false;
        }
        CheckoutInfoData checkoutInfoData = (CheckoutInfoData) obj;
        if (!Internal.equals(unknownFields(), checkoutInfoData.unknownFields()) || !Internal.equals(this.address, checkoutInfoData.address) || !Internal.equals(this.payment, checkoutInfoData.payment) || !Internal.equals(this.paylist, checkoutInfoData.paylist) || !Internal.equals(this.shipmentlist, checkoutInfoData.shipmentlist) || !Internal.equals(this.delivertime, checkoutInfoData.delivertime) || !Internal.equals(this.invoice, checkoutInfoData.invoice) || !Internal.equals(this.invoice_title, checkoutInfoData.invoice_title) || !Internal.equals(this.productMoney, checkoutInfoData.productMoney) || !Internal.equals(this.activityDiscountMoney, checkoutInfoData.activityDiscountMoney) || !Internal.equals(this.total, checkoutInfoData.total) || !Internal.equals(this.amount, checkoutInfoData.amount) || !Internal.equals(this.shipment, checkoutInfoData.shipment) || !Internal.equals(this.need_pay_amount, checkoutInfoData.need_pay_amount) || !Internal.equals(this.activityDiscountMoney_txt, checkoutInfoData.activityDiscountMoney_txt) || !Internal.equals(this.productMoney_txt, checkoutInfoData.productMoney_txt)) {
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
        int i3 = 1;
        int hashCode = ((((((((((unknownFields().hashCode() * 37) + (this.address != null ? this.address.hashCode() : 0)) * 37) + (this.payment != null ? this.payment.hashCode() : 1)) * 37) + (this.paylist != null ? this.paylist.hashCode() : 1)) * 37) + (this.shipmentlist != null ? this.shipmentlist.hashCode() : 1)) * 37) + (this.delivertime != null ? this.delivertime.hashCode() : 1)) * 37;
        if (this.invoice != null) {
            i3 = this.invoice.hashCode();
        }
        int hashCode2 = (((((((((((((((((hashCode + i3) * 37) + (this.invoice_title != null ? this.invoice_title.hashCode() : 0)) * 37) + (this.productMoney != null ? this.productMoney.hashCode() : 0)) * 37) + (this.activityDiscountMoney != null ? this.activityDiscountMoney.hashCode() : 0)) * 37) + (this.total != null ? this.total.hashCode() : 0)) * 37) + (this.amount != null ? this.amount.hashCode() : 0)) * 37) + (this.shipment != null ? this.shipment.hashCode() : 0)) * 37) + (this.need_pay_amount != null ? this.need_pay_amount.hashCode() : 0)) * 37) + (this.activityDiscountMoney_txt != null ? this.activityDiscountMoney_txt.hashCode() : 0)) * 37;
        if (this.productMoney_txt != null) {
            i2 = this.productMoney_txt.hashCode();
        }
        int i4 = hashCode2 + i2;
        this.hashCode = i4;
        return i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.address != null) {
            sb.append(", address=");
            sb.append(this.address);
        }
        if (this.payment != null) {
            sb.append(", payment=");
            sb.append(this.payment);
        }
        if (this.paylist != null) {
            sb.append(", paylist=");
            sb.append(this.paylist);
        }
        if (this.shipmentlist != null) {
            sb.append(", shipmentlist=");
            sb.append(this.shipmentlist);
        }
        if (this.delivertime != null) {
            sb.append(", delivertime=");
            sb.append(this.delivertime);
        }
        if (this.invoice != null) {
            sb.append(", invoice=");
            sb.append(this.invoice);
        }
        if (this.invoice_title != null) {
            sb.append(", invoice_title=");
            sb.append(this.invoice_title);
        }
        if (this.productMoney != null) {
            sb.append(", productMoney=");
            sb.append(this.productMoney);
        }
        if (this.activityDiscountMoney != null) {
            sb.append(", activityDiscountMoney=");
            sb.append(this.activityDiscountMoney);
        }
        if (this.total != null) {
            sb.append(", total=");
            sb.append(this.total);
        }
        if (this.amount != null) {
            sb.append(", amount=");
            sb.append(this.amount);
        }
        if (this.shipment != null) {
            sb.append(", shipment=");
            sb.append(this.shipment);
        }
        if (this.need_pay_amount != null) {
            sb.append(", need_pay_amount=");
            sb.append(this.need_pay_amount);
        }
        if (this.activityDiscountMoney_txt != null) {
            sb.append(", activityDiscountMoney_txt=");
            sb.append(this.activityDiscountMoney_txt);
        }
        if (this.productMoney_txt != null) {
            sb.append(", productMoney_txt=");
            sb.append(this.productMoney_txt);
        }
        StringBuilder replace = sb.replace(0, 2, "CheckoutInfoData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<CheckoutInfoData, Builder> {
        public String activityDiscountMoney;
        public String activityDiscountMoney_txt;
        public AddressData address;
        public String amount;
        public List<DelivertimeData> delivertime = Internal.newMutableList();
        public List<InvoiceData> invoice = Internal.newMutableList();
        public String invoice_title;
        public String need_pay_amount;
        public List<PaylistData> paylist = Internal.newMutableList();
        public List<PaymentData> payment = Internal.newMutableList();
        public String productMoney;
        public String productMoney_txt;
        public String shipment;
        public List<ShipmentlistData> shipmentlist = Internal.newMutableList();
        public Integer total;

        public Builder address(AddressData addressData) {
            this.address = addressData;
            return this;
        }

        public Builder payment(List<PaymentData> list) {
            Internal.checkElementsNotNull(list);
            this.payment = list;
            return this;
        }

        public Builder paylist(List<PaylistData> list) {
            Internal.checkElementsNotNull(list);
            this.paylist = list;
            return this;
        }

        public Builder shipmentlist(List<ShipmentlistData> list) {
            Internal.checkElementsNotNull(list);
            this.shipmentlist = list;
            return this;
        }

        public Builder delivertime(List<DelivertimeData> list) {
            Internal.checkElementsNotNull(list);
            this.delivertime = list;
            return this;
        }

        public Builder invoice(List<InvoiceData> list) {
            Internal.checkElementsNotNull(list);
            this.invoice = list;
            return this;
        }

        public Builder invoice_title(String str) {
            this.invoice_title = str;
            return this;
        }

        public Builder productMoney(String str) {
            this.productMoney = str;
            return this;
        }

        public Builder activityDiscountMoney(String str) {
            this.activityDiscountMoney = str;
            return this;
        }

        public Builder total(Integer num) {
            this.total = num;
            return this;
        }

        public Builder amount(String str) {
            this.amount = str;
            return this;
        }

        public Builder shipment(String str) {
            this.shipment = str;
            return this;
        }

        public Builder need_pay_amount(String str) {
            this.need_pay_amount = str;
            return this;
        }

        public Builder activityDiscountMoney_txt(String str) {
            this.activityDiscountMoney_txt = str;
            return this;
        }

        public Builder productMoney_txt(String str) {
            this.productMoney_txt = str;
            return this;
        }

        public CheckoutInfoData build() {
            return new CheckoutInfoData(this.address, this.payment, this.paylist, this.shipmentlist, this.delivertime, this.invoice, this.invoice_title, this.productMoney, this.activityDiscountMoney, this.total, this.amount, this.shipment, this.need_pay_amount, this.activityDiscountMoney_txt, this.productMoney_txt, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_CheckoutInfoData extends ProtoAdapter<CheckoutInfoData> {
        ProtoAdapter_CheckoutInfoData() {
            super(FieldEncoding.LENGTH_DELIMITED, CheckoutInfoData.class);
        }

        public int encodedSize(CheckoutInfoData checkoutInfoData) {
            int i = 0;
            int encodedSizeWithTag = (checkoutInfoData.address != null ? AddressData.ADAPTER.encodedSizeWithTag(1, checkoutInfoData.address) : 0) + PaymentData.ADAPTER.asRepeated().encodedSizeWithTag(2, checkoutInfoData.payment) + PaylistData.ADAPTER.asRepeated().encodedSizeWithTag(3, checkoutInfoData.paylist) + ShipmentlistData.ADAPTER.asRepeated().encodedSizeWithTag(4, checkoutInfoData.shipmentlist) + DelivertimeData.ADAPTER.asRepeated().encodedSizeWithTag(5, checkoutInfoData.delivertime) + InvoiceData.ADAPTER.asRepeated().encodedSizeWithTag(6, checkoutInfoData.invoice) + (checkoutInfoData.invoice_title != null ? ProtoAdapter.STRING.encodedSizeWithTag(7, checkoutInfoData.invoice_title) : 0) + (checkoutInfoData.productMoney != null ? ProtoAdapter.STRING.encodedSizeWithTag(8, checkoutInfoData.productMoney) : 0) + (checkoutInfoData.activityDiscountMoney != null ? ProtoAdapter.STRING.encodedSizeWithTag(9, checkoutInfoData.activityDiscountMoney) : 0) + (checkoutInfoData.total != null ? ProtoAdapter.INT32.encodedSizeWithTag(10, checkoutInfoData.total) : 0) + (checkoutInfoData.amount != null ? ProtoAdapter.STRING.encodedSizeWithTag(11, checkoutInfoData.amount) : 0) + (checkoutInfoData.shipment != null ? ProtoAdapter.STRING.encodedSizeWithTag(12, checkoutInfoData.shipment) : 0) + (checkoutInfoData.need_pay_amount != null ? ProtoAdapter.STRING.encodedSizeWithTag(13, checkoutInfoData.need_pay_amount) : 0) + (checkoutInfoData.activityDiscountMoney_txt != null ? ProtoAdapter.STRING.encodedSizeWithTag(14, checkoutInfoData.activityDiscountMoney_txt) : 0);
            if (checkoutInfoData.productMoney_txt != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(15, checkoutInfoData.productMoney_txt);
            }
            return encodedSizeWithTag + i + checkoutInfoData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, CheckoutInfoData checkoutInfoData) throws IOException {
            if (checkoutInfoData.address != null) {
                AddressData.ADAPTER.encodeWithTag(protoWriter, 1, checkoutInfoData.address);
            }
            if (checkoutInfoData.payment != null) {
                PaymentData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 2, checkoutInfoData.payment);
            }
            if (checkoutInfoData.paylist != null) {
                PaylistData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 3, checkoutInfoData.paylist);
            }
            if (checkoutInfoData.shipmentlist != null) {
                ShipmentlistData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 4, checkoutInfoData.shipmentlist);
            }
            if (checkoutInfoData.delivertime != null) {
                DelivertimeData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 5, checkoutInfoData.delivertime);
            }
            if (checkoutInfoData.invoice != null) {
                InvoiceData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 6, checkoutInfoData.invoice);
            }
            if (checkoutInfoData.invoice_title != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, checkoutInfoData.invoice_title);
            }
            if (checkoutInfoData.productMoney != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, checkoutInfoData.productMoney);
            }
            if (checkoutInfoData.activityDiscountMoney != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 9, checkoutInfoData.activityDiscountMoney);
            }
            if (checkoutInfoData.total != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 10, checkoutInfoData.total);
            }
            if (checkoutInfoData.amount != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 11, checkoutInfoData.amount);
            }
            if (checkoutInfoData.shipment != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 12, checkoutInfoData.shipment);
            }
            if (checkoutInfoData.need_pay_amount != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 13, checkoutInfoData.need_pay_amount);
            }
            if (checkoutInfoData.activityDiscountMoney_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 14, checkoutInfoData.activityDiscountMoney_txt);
            }
            if (checkoutInfoData.productMoney_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 15, checkoutInfoData.productMoney_txt);
            }
            protoWriter.writeBytes(checkoutInfoData.unknownFields());
        }

        public CheckoutInfoData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.address(AddressData.ADAPTER.decode(protoReader));
                            break;
                        case 2:
                            builder.payment.add(PaymentData.ADAPTER.decode(protoReader));
                            break;
                        case 3:
                            builder.paylist.add(PaylistData.ADAPTER.decode(protoReader));
                            break;
                        case 4:
                            builder.shipmentlist.add(ShipmentlistData.ADAPTER.decode(protoReader));
                            break;
                        case 5:
                            builder.delivertime.add(DelivertimeData.ADAPTER.decode(protoReader));
                            break;
                        case 6:
                            builder.invoice.add(InvoiceData.ADAPTER.decode(protoReader));
                            break;
                        case 7:
                            builder.invoice_title(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 8:
                            builder.productMoney(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 9:
                            builder.activityDiscountMoney(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 10:
                            builder.total(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 11:
                            builder.amount(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 12:
                            builder.shipment(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 13:
                            builder.need_pay_amount(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 14:
                            builder.activityDiscountMoney_txt(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 15:
                            builder.productMoney_txt(ProtoAdapter.STRING.decode(protoReader));
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

        public CheckoutInfoData redact(CheckoutInfoData checkoutInfoData) {
            Builder newBuilder = checkoutInfoData.newBuilder();
            if (newBuilder.address != null) {
                newBuilder.address = AddressData.ADAPTER.redact(newBuilder.address);
            }
            Internal.redactElements(newBuilder.payment, PaymentData.ADAPTER);
            Internal.redactElements(newBuilder.paylist, PaylistData.ADAPTER);
            Internal.redactElements(newBuilder.shipmentlist, ShipmentlistData.ADAPTER);
            Internal.redactElements(newBuilder.delivertime, DelivertimeData.ADAPTER);
            Internal.redactElements(newBuilder.invoice, InvoiceData.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
