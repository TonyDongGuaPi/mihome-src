package com.mi.global.shop.model.user;

import com.mi.global.shop.model.basestruct.PageMessage;
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

public final class OrderListData extends Message<OrderListData, Builder> {
    public static final ProtoAdapter<OrderListData> ADAPTER = new ProtoAdapter_OrderListData();
    public static final Integer DEFAULT_CURRENT_PAGE = 0;
    public static final Integer DEFAULT_TOTAL_COUNT = 0;
    public static final Integer DEFAULT_TOTAL_PAGES = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 2)
    public final Integer current_page;
    @WireField(adapter = "com.mi.global.shop.model.user.ExtData#ADAPTER", tag = 4)
    public final ExtData ext;
    @WireField(adapter = "com.mi.global.shop.model.user.OrderList#ADAPTER", label = WireField.Label.REPEATED, tag = 5)
    public final List<OrderList> order_list;
    @WireField(adapter = "com.mi.global.shop.model.basestruct.PageMessage#ADAPTER", tag = 6)
    public final PageMessage pagemsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 3)
    public final Integer total_count;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer total_pages;

    public OrderListData(Integer num, Integer num2, Integer num3, ExtData extData, List<OrderList> list, PageMessage pageMessage) {
        this(num, num2, num3, extData, list, pageMessage, ByteString.EMPTY);
    }

    public OrderListData(Integer num, Integer num2, Integer num3, ExtData extData, List<OrderList> list, PageMessage pageMessage, ByteString byteString) {
        super(ADAPTER, byteString);
        this.total_pages = num;
        this.current_page = num2;
        this.total_count = num3;
        this.ext = extData;
        this.order_list = Internal.immutableCopyOf("order_list", list);
        this.pagemsg = pageMessage;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.total_pages = this.total_pages;
        builder.current_page = this.current_page;
        builder.total_count = this.total_count;
        builder.ext = this.ext;
        builder.order_list = Internal.copyOf("order_list", this.order_list);
        builder.pagemsg = this.pagemsg;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OrderListData)) {
            return false;
        }
        OrderListData orderListData = (OrderListData) obj;
        if (!Internal.equals(unknownFields(), orderListData.unknownFields()) || !Internal.equals(this.total_pages, orderListData.total_pages) || !Internal.equals(this.current_page, orderListData.current_page) || !Internal.equals(this.total_count, orderListData.total_count) || !Internal.equals(this.ext, orderListData.ext) || !Internal.equals(this.order_list, orderListData.order_list) || !Internal.equals(this.pagemsg, orderListData.pagemsg)) {
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
        int hashCode = ((((((((((unknownFields().hashCode() * 37) + (this.total_pages != null ? this.total_pages.hashCode() : 0)) * 37) + (this.current_page != null ? this.current_page.hashCode() : 0)) * 37) + (this.total_count != null ? this.total_count.hashCode() : 0)) * 37) + (this.ext != null ? this.ext.hashCode() : 0)) * 37) + (this.order_list != null ? this.order_list.hashCode() : 1)) * 37;
        if (this.pagemsg != null) {
            i2 = this.pagemsg.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.total_pages != null) {
            sb.append(", total_pages=");
            sb.append(this.total_pages);
        }
        if (this.current_page != null) {
            sb.append(", current_page=");
            sb.append(this.current_page);
        }
        if (this.total_count != null) {
            sb.append(", total_count=");
            sb.append(this.total_count);
        }
        if (this.ext != null) {
            sb.append(", ext=");
            sb.append(this.ext);
        }
        if (this.order_list != null) {
            sb.append(", order_list=");
            sb.append(this.order_list);
        }
        if (this.pagemsg != null) {
            sb.append(", pagemsg=");
            sb.append(this.pagemsg);
        }
        StringBuilder replace = sb.replace(0, 2, "OrderListData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<OrderListData, Builder> {
        public Integer current_page;
        public ExtData ext;
        public List<OrderList> order_list = Internal.newMutableList();
        public PageMessage pagemsg;
        public Integer total_count;
        public Integer total_pages;

        public Builder total_pages(Integer num) {
            this.total_pages = num;
            return this;
        }

        public Builder current_page(Integer num) {
            this.current_page = num;
            return this;
        }

        public Builder total_count(Integer num) {
            this.total_count = num;
            return this;
        }

        public Builder ext(ExtData extData) {
            this.ext = extData;
            return this;
        }

        public Builder order_list(List<OrderList> list) {
            Internal.checkElementsNotNull(list);
            this.order_list = list;
            return this;
        }

        public Builder pagemsg(PageMessage pageMessage) {
            this.pagemsg = pageMessage;
            return this;
        }

        public OrderListData build() {
            return new OrderListData(this.total_pages, this.current_page, this.total_count, this.ext, this.order_list, this.pagemsg, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_OrderListData extends ProtoAdapter<OrderListData> {
        ProtoAdapter_OrderListData() {
            super(FieldEncoding.LENGTH_DELIMITED, OrderListData.class);
        }

        public int encodedSize(OrderListData orderListData) {
            int i = 0;
            int encodedSizeWithTag = (orderListData.total_pages != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, orderListData.total_pages) : 0) + (orderListData.current_page != null ? ProtoAdapter.INT32.encodedSizeWithTag(2, orderListData.current_page) : 0) + (orderListData.total_count != null ? ProtoAdapter.INT32.encodedSizeWithTag(3, orderListData.total_count) : 0) + (orderListData.ext != null ? ExtData.ADAPTER.encodedSizeWithTag(4, orderListData.ext) : 0) + OrderList.ADAPTER.asRepeated().encodedSizeWithTag(5, orderListData.order_list);
            if (orderListData.pagemsg != null) {
                i = PageMessage.ADAPTER.encodedSizeWithTag(6, orderListData.pagemsg);
            }
            return encodedSizeWithTag + i + orderListData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, OrderListData orderListData) throws IOException {
            if (orderListData.total_pages != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, orderListData.total_pages);
            }
            if (orderListData.current_page != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 2, orderListData.current_page);
            }
            if (orderListData.total_count != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 3, orderListData.total_count);
            }
            if (orderListData.ext != null) {
                ExtData.ADAPTER.encodeWithTag(protoWriter, 4, orderListData.ext);
            }
            if (orderListData.order_list != null) {
                OrderList.ADAPTER.asRepeated().encodeWithTag(protoWriter, 5, orderListData.order_list);
            }
            if (orderListData.pagemsg != null) {
                PageMessage.ADAPTER.encodeWithTag(protoWriter, 6, orderListData.pagemsg);
            }
            protoWriter.writeBytes(orderListData.unknownFields());
        }

        public OrderListData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.total_pages(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 2:
                            builder.current_page(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 3:
                            builder.total_count(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 4:
                            builder.ext(ExtData.ADAPTER.decode(protoReader));
                            break;
                        case 5:
                            builder.order_list.add(OrderList.ADAPTER.decode(protoReader));
                            break;
                        case 6:
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

        public OrderListData redact(OrderListData orderListData) {
            Builder newBuilder = orderListData.newBuilder();
            if (newBuilder.ext != null) {
                newBuilder.ext = ExtData.ADAPTER.redact(newBuilder.ext);
            }
            Internal.redactElements(newBuilder.order_list, OrderList.ADAPTER);
            if (newBuilder.pagemsg != null) {
                newBuilder.pagemsg = PageMessage.ADAPTER.redact(newBuilder.pagemsg);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
