package com.mi.global.shop.model.buy;

import com.mi.global.shop.model.basestruct.PageMessage;
import com.mi.global.shop.model.common.PayList;
import com.mi.global.shop.model.common.PayOption;
import com.mi.global.shop.model.common.PayParam;
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

public final class PayInfoData extends Message<PayInfoData, Builder> {
    public static final ProtoAdapter<PayInfoData> ADAPTER = new ProtoAdapter_PayInfoData();
    public static final String DEFAULT_BANK_LIST = "";
    public static final String DEFAULT_MENTION = "";
    public static final String DEFAULT_MENTION_EXT = "";
    public static final String DEFAULT_PAYPARAMSTRING = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final String bank_list;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String mention;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String mention_ext;
    @WireField(adapter = "com.mi.global.shop.model.buy.onlinepayCouponInfoData#ADAPTER", tag = 6)
    public final onlinepayCouponInfoData onlinepayCouponInfo;
    @WireField(adapter = "com.mi.global.shop.model.buy.OrderInfoData#ADAPTER", tag = 1)
    public final OrderInfoData orderInfo;
    @WireField(adapter = "com.mi.global.shop.model.basestruct.PageMessage#ADAPTER", tag = 11)
    public final PageMessage pagemsg;
    @WireField(adapter = "com.mi.global.shop.model.common.PayList#ADAPTER", tag = 2)
    public final PayList payList;
    @WireField(adapter = "com.mi.global.shop.model.common.PayOption#ADAPTER", label = WireField.Label.REPEATED, tag = 12)
    public final List<PayOption> payOptions;
    @WireField(adapter = "com.mi.global.shop.model.common.PayParam#ADAPTER", tag = 3)
    public final PayParam payParam;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String payParamString;
    @WireField(adapter = "com.mi.global.shop.model.buy.SupportData#ADAPTER", tag = 4)
    public final SupportData support;

    public PayInfoData(OrderInfoData orderInfoData, PayList payList2, PayParam payParam2, SupportData supportData, String str, onlinepayCouponInfoData onlinepaycouponinfodata, String str2, String str3, String str4, PageMessage pageMessage, List<PayOption> list) {
        this(orderInfoData, payList2, payParam2, supportData, str, onlinepaycouponinfodata, str2, str3, str4, pageMessage, list, ByteString.EMPTY);
    }

    public PayInfoData(OrderInfoData orderInfoData, PayList payList2, PayParam payParam2, SupportData supportData, String str, onlinepayCouponInfoData onlinepaycouponinfodata, String str2, String str3, String str4, PageMessage pageMessage, List<PayOption> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.orderInfo = orderInfoData;
        this.payList = payList2;
        this.payParam = payParam2;
        this.support = supportData;
        this.mention = str;
        this.onlinepayCouponInfo = onlinepaycouponinfodata;
        this.payParamString = str2;
        this.mention_ext = str3;
        this.bank_list = str4;
        this.pagemsg = pageMessage;
        this.payOptions = Internal.immutableCopyOf("payOptions", list);
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.orderInfo = this.orderInfo;
        builder.payList = this.payList;
        builder.payParam = this.payParam;
        builder.support = this.support;
        builder.mention = this.mention;
        builder.onlinepayCouponInfo = this.onlinepayCouponInfo;
        builder.payParamString = this.payParamString;
        builder.mention_ext = this.mention_ext;
        builder.bank_list = this.bank_list;
        builder.pagemsg = this.pagemsg;
        builder.payOptions = Internal.copyOf("payOptions", this.payOptions);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PayInfoData)) {
            return false;
        }
        PayInfoData payInfoData = (PayInfoData) obj;
        if (!Internal.equals(unknownFields(), payInfoData.unknownFields()) || !Internal.equals(this.orderInfo, payInfoData.orderInfo) || !Internal.equals(this.payList, payInfoData.payList) || !Internal.equals(this.payParam, payInfoData.payParam) || !Internal.equals(this.support, payInfoData.support) || !Internal.equals(this.mention, payInfoData.mention) || !Internal.equals(this.onlinepayCouponInfo, payInfoData.onlinepayCouponInfo) || !Internal.equals(this.payParamString, payInfoData.payParamString) || !Internal.equals(this.mention_ext, payInfoData.mention_ext) || !Internal.equals(this.bank_list, payInfoData.bank_list) || !Internal.equals(this.pagemsg, payInfoData.pagemsg) || !Internal.equals(this.payOptions, payInfoData.payOptions)) {
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
        int hashCode = ((((((((((((((((((unknownFields().hashCode() * 37) + (this.orderInfo != null ? this.orderInfo.hashCode() : 0)) * 37) + (this.payList != null ? this.payList.hashCode() : 0)) * 37) + (this.payParam != null ? this.payParam.hashCode() : 0)) * 37) + (this.support != null ? this.support.hashCode() : 0)) * 37) + (this.mention != null ? this.mention.hashCode() : 0)) * 37) + (this.onlinepayCouponInfo != null ? this.onlinepayCouponInfo.hashCode() : 0)) * 37) + (this.payParamString != null ? this.payParamString.hashCode() : 0)) * 37) + (this.mention_ext != null ? this.mention_ext.hashCode() : 0)) * 37) + (this.bank_list != null ? this.bank_list.hashCode() : 0)) * 37;
        if (this.pagemsg != null) {
            i2 = this.pagemsg.hashCode();
        }
        int hashCode2 = ((hashCode + i2) * 37) + (this.payOptions != null ? this.payOptions.hashCode() : 1);
        this.hashCode = hashCode2;
        return hashCode2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.orderInfo != null) {
            sb.append(", orderInfo=");
            sb.append(this.orderInfo);
        }
        if (this.payList != null) {
            sb.append(", payList=");
            sb.append(this.payList);
        }
        if (this.payParam != null) {
            sb.append(", payParam=");
            sb.append(this.payParam);
        }
        if (this.support != null) {
            sb.append(", support=");
            sb.append(this.support);
        }
        if (this.mention != null) {
            sb.append(", mention=");
            sb.append(this.mention);
        }
        if (this.onlinepayCouponInfo != null) {
            sb.append(", onlinepayCouponInfo=");
            sb.append(this.onlinepayCouponInfo);
        }
        if (this.payParamString != null) {
            sb.append(", payParamString=");
            sb.append(this.payParamString);
        }
        if (this.mention_ext != null) {
            sb.append(", mention_ext=");
            sb.append(this.mention_ext);
        }
        if (this.bank_list != null) {
            sb.append(", bank_list=");
            sb.append(this.bank_list);
        }
        if (this.pagemsg != null) {
            sb.append(", pagemsg=");
            sb.append(this.pagemsg);
        }
        if (this.payOptions != null) {
            sb.append(", payOptions=");
            sb.append(this.payOptions);
        }
        StringBuilder replace = sb.replace(0, 2, "PayInfoData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<PayInfoData, Builder> {
        public String bank_list;
        public String mention;
        public String mention_ext;
        public onlinepayCouponInfoData onlinepayCouponInfo;
        public OrderInfoData orderInfo;
        public PageMessage pagemsg;
        public PayList payList;
        public List<PayOption> payOptions = Internal.newMutableList();
        public PayParam payParam;
        public String payParamString;
        public SupportData support;

        public Builder orderInfo(OrderInfoData orderInfoData) {
            this.orderInfo = orderInfoData;
            return this;
        }

        public Builder payList(PayList payList2) {
            this.payList = payList2;
            return this;
        }

        public Builder payParam(PayParam payParam2) {
            this.payParam = payParam2;
            return this;
        }

        public Builder support(SupportData supportData) {
            this.support = supportData;
            return this;
        }

        public Builder mention(String str) {
            this.mention = str;
            return this;
        }

        public Builder onlinepayCouponInfo(onlinepayCouponInfoData onlinepaycouponinfodata) {
            this.onlinepayCouponInfo = onlinepaycouponinfodata;
            return this;
        }

        public Builder payParamString(String str) {
            this.payParamString = str;
            return this;
        }

        public Builder mention_ext(String str) {
            this.mention_ext = str;
            return this;
        }

        public Builder bank_list(String str) {
            this.bank_list = str;
            return this;
        }

        public Builder pagemsg(PageMessage pageMessage) {
            this.pagemsg = pageMessage;
            return this;
        }

        public Builder payOptions(List<PayOption> list) {
            Internal.checkElementsNotNull(list);
            this.payOptions = list;
            return this;
        }

        public PayInfoData build() {
            return new PayInfoData(this.orderInfo, this.payList, this.payParam, this.support, this.mention, this.onlinepayCouponInfo, this.payParamString, this.mention_ext, this.bank_list, this.pagemsg, this.payOptions, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_PayInfoData extends ProtoAdapter<PayInfoData> {
        ProtoAdapter_PayInfoData() {
            super(FieldEncoding.LENGTH_DELIMITED, PayInfoData.class);
        }

        public int encodedSize(PayInfoData payInfoData) {
            int i = 0;
            int encodedSizeWithTag = (payInfoData.orderInfo != null ? OrderInfoData.ADAPTER.encodedSizeWithTag(1, payInfoData.orderInfo) : 0) + (payInfoData.payList != null ? PayList.ADAPTER.encodedSizeWithTag(2, payInfoData.payList) : 0) + (payInfoData.payParam != null ? PayParam.ADAPTER.encodedSizeWithTag(3, payInfoData.payParam) : 0) + (payInfoData.support != null ? SupportData.ADAPTER.encodedSizeWithTag(4, payInfoData.support) : 0) + (payInfoData.mention != null ? ProtoAdapter.STRING.encodedSizeWithTag(5, payInfoData.mention) : 0) + (payInfoData.onlinepayCouponInfo != null ? onlinepayCouponInfoData.ADAPTER.encodedSizeWithTag(6, payInfoData.onlinepayCouponInfo) : 0) + (payInfoData.payParamString != null ? ProtoAdapter.STRING.encodedSizeWithTag(7, payInfoData.payParamString) : 0) + (payInfoData.mention_ext != null ? ProtoAdapter.STRING.encodedSizeWithTag(8, payInfoData.mention_ext) : 0) + (payInfoData.bank_list != null ? ProtoAdapter.STRING.encodedSizeWithTag(9, payInfoData.bank_list) : 0);
            if (payInfoData.pagemsg != null) {
                i = PageMessage.ADAPTER.encodedSizeWithTag(11, payInfoData.pagemsg);
            }
            return encodedSizeWithTag + i + PayOption.ADAPTER.asRepeated().encodedSizeWithTag(12, payInfoData.payOptions) + payInfoData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, PayInfoData payInfoData) throws IOException {
            if (payInfoData.orderInfo != null) {
                OrderInfoData.ADAPTER.encodeWithTag(protoWriter, 1, payInfoData.orderInfo);
            }
            if (payInfoData.payList != null) {
                PayList.ADAPTER.encodeWithTag(protoWriter, 2, payInfoData.payList);
            }
            if (payInfoData.payParam != null) {
                PayParam.ADAPTER.encodeWithTag(protoWriter, 3, payInfoData.payParam);
            }
            if (payInfoData.support != null) {
                SupportData.ADAPTER.encodeWithTag(protoWriter, 4, payInfoData.support);
            }
            if (payInfoData.mention != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, payInfoData.mention);
            }
            if (payInfoData.onlinepayCouponInfo != null) {
                onlinepayCouponInfoData.ADAPTER.encodeWithTag(protoWriter, 6, payInfoData.onlinepayCouponInfo);
            }
            if (payInfoData.payParamString != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, payInfoData.payParamString);
            }
            if (payInfoData.mention_ext != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, payInfoData.mention_ext);
            }
            if (payInfoData.bank_list != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 9, payInfoData.bank_list);
            }
            if (payInfoData.pagemsg != null) {
                PageMessage.ADAPTER.encodeWithTag(protoWriter, 11, payInfoData.pagemsg);
            }
            if (payInfoData.payOptions != null) {
                PayOption.ADAPTER.asRepeated().encodeWithTag(protoWriter, 12, payInfoData.payOptions);
            }
            protoWriter.writeBytes(payInfoData.unknownFields());
        }

        public PayInfoData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.orderInfo(OrderInfoData.ADAPTER.decode(protoReader));
                            break;
                        case 2:
                            builder.payList(PayList.ADAPTER.decode(protoReader));
                            break;
                        case 3:
                            builder.payParam(PayParam.ADAPTER.decode(protoReader));
                            break;
                        case 4:
                            builder.support(SupportData.ADAPTER.decode(protoReader));
                            break;
                        case 5:
                            builder.mention(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 6:
                            builder.onlinepayCouponInfo(onlinepayCouponInfoData.ADAPTER.decode(protoReader));
                            break;
                        case 7:
                            builder.payParamString(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 8:
                            builder.mention_ext(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 9:
                            builder.bank_list(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 11:
                            builder.pagemsg(PageMessage.ADAPTER.decode(protoReader));
                            break;
                        case 12:
                            builder.payOptions.add(PayOption.ADAPTER.decode(protoReader));
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

        public PayInfoData redact(PayInfoData payInfoData) {
            Builder newBuilder = payInfoData.newBuilder();
            if (newBuilder.orderInfo != null) {
                newBuilder.orderInfo = OrderInfoData.ADAPTER.redact(newBuilder.orderInfo);
            }
            if (newBuilder.payList != null) {
                newBuilder.payList = PayList.ADAPTER.redact(newBuilder.payList);
            }
            if (newBuilder.payParam != null) {
                newBuilder.payParam = PayParam.ADAPTER.redact(newBuilder.payParam);
            }
            if (newBuilder.support != null) {
                newBuilder.support = SupportData.ADAPTER.redact(newBuilder.support);
            }
            if (newBuilder.onlinepayCouponInfo != null) {
                newBuilder.onlinepayCouponInfo = onlinepayCouponInfoData.ADAPTER.redact(newBuilder.onlinepayCouponInfo);
            }
            if (newBuilder.pagemsg != null) {
                newBuilder.pagemsg = PageMessage.ADAPTER.redact(newBuilder.pagemsg);
            }
            Internal.redactElements(newBuilder.payOptions, PayOption.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
