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

public final class View extends Message<View, Builder> {
    public static final ProtoAdapter<View> ADAPTER = new ProtoAdapter_View();
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.user.OrderViewData#ADAPTER", tag = 5)
    public final OrderViewData data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer errno;

    public View(Integer num, String str, OrderViewData orderViewData) {
        this(num, str, orderViewData, ByteString.EMPTY);
    }

    public View(Integer num, String str, OrderViewData orderViewData, ByteString byteString) {
        super(ADAPTER, byteString);
        this.errno = num;
        this.errmsg = str;
        this.data = orderViewData;
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
        if (!(obj instanceof View)) {
            return false;
        }
        View view = (View) obj;
        if (!Internal.equals(unknownFields(), view.unknownFields()) || !Internal.equals(this.errno, view.errno) || !Internal.equals(this.errmsg, view.errmsg) || !Internal.equals(this.data, view.data)) {
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
        StringBuilder replace = sb.replace(0, 2, "View{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<View, Builder> {
        public OrderViewData data;
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

        public Builder data(OrderViewData orderViewData) {
            this.data = orderViewData;
            return this;
        }

        public View build() {
            return new View(this.errno, this.errmsg, this.data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_View extends ProtoAdapter<View> {
        ProtoAdapter_View() {
            super(FieldEncoding.LENGTH_DELIMITED, View.class);
        }

        public int encodedSize(View view) {
            int i = 0;
            int encodedSizeWithTag = (view.errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, view.errno) : 0) + (view.errmsg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, view.errmsg) : 0);
            if (view.data != null) {
                i = OrderViewData.ADAPTER.encodedSizeWithTag(5, view.data);
            }
            return encodedSizeWithTag + i + view.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, View view) throws IOException {
            if (view.errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, view.errno);
            }
            if (view.errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, view.errmsg);
            }
            if (view.data != null) {
                OrderViewData.ADAPTER.encodeWithTag(protoWriter, 5, view.data);
            }
            protoWriter.writeBytes(view.unknownFields());
        }

        public View decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag == -1) {
                    protoReader.endMessage(beginMessage);
                    return builder.build();
                } else if (nextTag != 5) {
                    switch (nextTag) {
                        case 1:
                            builder.errno(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 2:
                            builder.errmsg(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        default:
                            FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                            builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                            break;
                    }
                } else {
                    builder.data(OrderViewData.ADAPTER.decode(protoReader));
                }
            }
        }

        public View redact(View view) {
            Builder newBuilder = view.newBuilder();
            if (newBuilder.data != null) {
                newBuilder.data = OrderViewData.ADAPTER.redact(newBuilder.data);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
