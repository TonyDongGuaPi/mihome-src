package com.mi.global.shop.model.user;

import com.mi.global.shop.model.Tags;
import com.mi.global.shop.model.common.DeliversData;
import com.mi.global.shop.model.common.ListProduct;
import com.mi.global.shop.model.common.OrderStatusInfo;
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

public final class OrderList extends Message<OrderList, Builder> {
    public static final ProtoAdapter<OrderList> ADAPTER = new ProtoAdapter_OrderList();
    public static final String DEFAULT_ADD_TIME = "";
    public static final String DEFAULT_ADD_TIME_FM = "";
    public static final String DEFAULT_CONSIGNEE = "";
    public static final String DEFAULT_DIFFERENCE_AMOUNT = "";
    public static final String DEFAULT_GOODS_AMOUNT = "";
    public static final String DEFAULT_ORDER_FLOW = "";
    public static final String DEFAULT_ORDER_ID = "";
    public static final String DEFAULT_ORDER_STATUS = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String add_time;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 11)
    public final String add_time_fm;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 10)
    public final String consignee;
    @WireField(adapter = "com.mi.global.shop.model.common.DeliversData#ADAPTER", label = WireField.Label.REPEATED, tag = 8)
    public final List<DeliversData> delivers;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final String difference_amount;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String goods_amount;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String order_flow;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String order_id;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String order_status;
    @WireField(adapter = "com.mi.global.shop.model.common.OrderStatusInfo#ADAPTER", tag = 5)
    public final OrderStatusInfo order_status_info;
    @WireField(adapter = "com.mi.global.shop.model.common.ListProduct#ADAPTER", label = WireField.Label.REPEATED, tag = 6)
    public final List<ListProduct> product;

    public OrderList(String str, String str2, String str3, String str4, OrderStatusInfo orderStatusInfo, List<ListProduct> list, String str5, List<DeliversData> list2, String str6, String str7, String str8) {
        this(str, str2, str3, str4, orderStatusInfo, list, str5, list2, str6, str7, str8, ByteString.EMPTY);
    }

    public OrderList(String str, String str2, String str3, String str4, OrderStatusInfo orderStatusInfo, List<ListProduct> list, String str5, List<DeliversData> list2, String str6, String str7, String str8, ByteString byteString) {
        super(ADAPTER, byteString);
        this.order_id = str;
        this.order_status = str2;
        this.goods_amount = str3;
        this.add_time = str4;
        this.order_status_info = orderStatusInfo;
        this.product = Internal.immutableCopyOf("product", list);
        this.order_flow = str5;
        this.delivers = Internal.immutableCopyOf(Tags.Order.DELIVERS, list2);
        this.difference_amount = str6;
        this.consignee = str7;
        this.add_time_fm = str8;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.order_id = this.order_id;
        builder.order_status = this.order_status;
        builder.goods_amount = this.goods_amount;
        builder.add_time = this.add_time;
        builder.order_status_info = this.order_status_info;
        builder.product = Internal.copyOf("product", this.product);
        builder.order_flow = this.order_flow;
        builder.delivers = Internal.copyOf(Tags.Order.DELIVERS, this.delivers);
        builder.difference_amount = this.difference_amount;
        builder.consignee = this.consignee;
        builder.add_time_fm = this.add_time_fm;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OrderList)) {
            return false;
        }
        OrderList orderList = (OrderList) obj;
        if (!Internal.equals(unknownFields(), orderList.unknownFields()) || !Internal.equals(this.order_id, orderList.order_id) || !Internal.equals(this.order_status, orderList.order_status) || !Internal.equals(this.goods_amount, orderList.goods_amount) || !Internal.equals(this.add_time, orderList.add_time) || !Internal.equals(this.order_status_info, orderList.order_status_info) || !Internal.equals(this.product, orderList.product) || !Internal.equals(this.order_flow, orderList.order_flow) || !Internal.equals(this.delivers, orderList.delivers) || !Internal.equals(this.difference_amount, orderList.difference_amount) || !Internal.equals(this.consignee, orderList.consignee) || !Internal.equals(this.add_time_fm, orderList.add_time_fm)) {
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
        int hashCode = ((((((((((((((unknownFields().hashCode() * 37) + (this.order_id != null ? this.order_id.hashCode() : 0)) * 37) + (this.order_status != null ? this.order_status.hashCode() : 0)) * 37) + (this.goods_amount != null ? this.goods_amount.hashCode() : 0)) * 37) + (this.add_time != null ? this.add_time.hashCode() : 0)) * 37) + (this.order_status_info != null ? this.order_status_info.hashCode() : 0)) * 37) + (this.product != null ? this.product.hashCode() : 1)) * 37) + (this.order_flow != null ? this.order_flow.hashCode() : 0)) * 37;
        if (this.delivers != null) {
            i3 = this.delivers.hashCode();
        }
        int hashCode2 = (((((hashCode + i3) * 37) + (this.difference_amount != null ? this.difference_amount.hashCode() : 0)) * 37) + (this.consignee != null ? this.consignee.hashCode() : 0)) * 37;
        if (this.add_time_fm != null) {
            i2 = this.add_time_fm.hashCode();
        }
        int i4 = hashCode2 + i2;
        this.hashCode = i4;
        return i4;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.order_id != null) {
            sb.append(", order_id=");
            sb.append(this.order_id);
        }
        if (this.order_status != null) {
            sb.append(", order_status=");
            sb.append(this.order_status);
        }
        if (this.goods_amount != null) {
            sb.append(", goods_amount=");
            sb.append(this.goods_amount);
        }
        if (this.add_time != null) {
            sb.append(", add_time=");
            sb.append(this.add_time);
        }
        if (this.order_status_info != null) {
            sb.append(", order_status_info=");
            sb.append(this.order_status_info);
        }
        if (this.product != null) {
            sb.append(", product=");
            sb.append(this.product);
        }
        if (this.order_flow != null) {
            sb.append(", order_flow=");
            sb.append(this.order_flow);
        }
        if (this.delivers != null) {
            sb.append(", delivers=");
            sb.append(this.delivers);
        }
        if (this.difference_amount != null) {
            sb.append(", difference_amount=");
            sb.append(this.difference_amount);
        }
        if (this.consignee != null) {
            sb.append(", consignee=");
            sb.append(this.consignee);
        }
        if (this.add_time_fm != null) {
            sb.append(", add_time_fm=");
            sb.append(this.add_time_fm);
        }
        StringBuilder replace = sb.replace(0, 2, "OrderList{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<OrderList, Builder> {
        public String add_time;
        public String add_time_fm;
        public String consignee;
        public List<DeliversData> delivers = Internal.newMutableList();
        public String difference_amount;
        public String goods_amount;
        public String order_flow;
        public String order_id;
        public String order_status;
        public OrderStatusInfo order_status_info;
        public List<ListProduct> product = Internal.newMutableList();

        public Builder order_id(String str) {
            this.order_id = str;
            return this;
        }

        public Builder order_status(String str) {
            this.order_status = str;
            return this;
        }

        public Builder goods_amount(String str) {
            this.goods_amount = str;
            return this;
        }

        public Builder add_time(String str) {
            this.add_time = str;
            return this;
        }

        public Builder order_status_info(OrderStatusInfo orderStatusInfo) {
            this.order_status_info = orderStatusInfo;
            return this;
        }

        public Builder product(List<ListProduct> list) {
            Internal.checkElementsNotNull(list);
            this.product = list;
            return this;
        }

        public Builder order_flow(String str) {
            this.order_flow = str;
            return this;
        }

        public Builder delivers(List<DeliversData> list) {
            Internal.checkElementsNotNull(list);
            this.delivers = list;
            return this;
        }

        public Builder difference_amount(String str) {
            this.difference_amount = str;
            return this;
        }

        public Builder consignee(String str) {
            this.consignee = str;
            return this;
        }

        public Builder add_time_fm(String str) {
            this.add_time_fm = str;
            return this;
        }

        public OrderList build() {
            return new OrderList(this.order_id, this.order_status, this.goods_amount, this.add_time, this.order_status_info, this.product, this.order_flow, this.delivers, this.difference_amount, this.consignee, this.add_time_fm, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_OrderList extends ProtoAdapter<OrderList> {
        ProtoAdapter_OrderList() {
            super(FieldEncoding.LENGTH_DELIMITED, OrderList.class);
        }

        public int encodedSize(OrderList orderList) {
            int i = 0;
            int encodedSizeWithTag = (orderList.order_id != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, orderList.order_id) : 0) + (orderList.order_status != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, orderList.order_status) : 0) + (orderList.goods_amount != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, orderList.goods_amount) : 0) + (orderList.add_time != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, orderList.add_time) : 0) + (orderList.order_status_info != null ? OrderStatusInfo.ADAPTER.encodedSizeWithTag(5, orderList.order_status_info) : 0) + ListProduct.ADAPTER.asRepeated().encodedSizeWithTag(6, orderList.product) + (orderList.order_flow != null ? ProtoAdapter.STRING.encodedSizeWithTag(7, orderList.order_flow) : 0) + DeliversData.ADAPTER.asRepeated().encodedSizeWithTag(8, orderList.delivers) + (orderList.difference_amount != null ? ProtoAdapter.STRING.encodedSizeWithTag(9, orderList.difference_amount) : 0) + (orderList.consignee != null ? ProtoAdapter.STRING.encodedSizeWithTag(10, orderList.consignee) : 0);
            if (orderList.add_time_fm != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(11, orderList.add_time_fm);
            }
            return encodedSizeWithTag + i + orderList.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, OrderList orderList) throws IOException {
            if (orderList.order_id != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, orderList.order_id);
            }
            if (orderList.order_status != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, orderList.order_status);
            }
            if (orderList.goods_amount != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, orderList.goods_amount);
            }
            if (orderList.add_time != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, orderList.add_time);
            }
            if (orderList.order_status_info != null) {
                OrderStatusInfo.ADAPTER.encodeWithTag(protoWriter, 5, orderList.order_status_info);
            }
            if (orderList.product != null) {
                ListProduct.ADAPTER.asRepeated().encodeWithTag(protoWriter, 6, orderList.product);
            }
            if (orderList.order_flow != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, orderList.order_flow);
            }
            if (orderList.delivers != null) {
                DeliversData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 8, orderList.delivers);
            }
            if (orderList.difference_amount != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 9, orderList.difference_amount);
            }
            if (orderList.consignee != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 10, orderList.consignee);
            }
            if (orderList.add_time_fm != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 11, orderList.add_time_fm);
            }
            protoWriter.writeBytes(orderList.unknownFields());
        }

        public OrderList decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.order_id(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.order_status(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.goods_amount(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.add_time(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 5:
                            builder.order_status_info(OrderStatusInfo.ADAPTER.decode(protoReader));
                            break;
                        case 6:
                            builder.product.add(ListProduct.ADAPTER.decode(protoReader));
                            break;
                        case 7:
                            builder.order_flow(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 8:
                            builder.delivers.add(DeliversData.ADAPTER.decode(protoReader));
                            break;
                        case 9:
                            builder.difference_amount(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 10:
                            builder.consignee(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 11:
                            builder.add_time_fm(ProtoAdapter.STRING.decode(protoReader));
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

        public OrderList redact(OrderList orderList) {
            Builder newBuilder = orderList.newBuilder();
            if (newBuilder.order_status_info != null) {
                newBuilder.order_status_info = OrderStatusInfo.ADAPTER.redact(newBuilder.order_status_info);
            }
            Internal.redactElements(newBuilder.product, ListProduct.ADAPTER);
            Internal.redactElements(newBuilder.delivers, DeliversData.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
