package com.mi.global.shop.model.address;

import com.mi.global.shop.model.common.AddressData;
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

public final class ListBody extends Message<ListBody, Builder> {
    public static final ProtoAdapter<ListBody> ADAPTER = new ProtoAdapter_ListBody();
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.common.AddressData#ADAPTER", label = WireField.Label.REPEATED, tag = 3)
    public final List<AddressData> data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer errno;

    public ListBody(Integer num, String str, List<AddressData> list) {
        this(num, str, list, ByteString.EMPTY);
    }

    public ListBody(Integer num, String str, List<AddressData> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.errno = num;
        this.errmsg = str;
        this.data = Internal.immutableCopyOf("data", list);
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.errno = this.errno;
        builder.errmsg = this.errmsg;
        builder.data = Internal.copyOf("data", this.data);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListBody)) {
            return false;
        }
        ListBody listBody = (ListBody) obj;
        if (!Internal.equals(unknownFields(), listBody.unknownFields()) || !Internal.equals(this.errno, listBody.errno) || !Internal.equals(this.errmsg, listBody.errmsg) || !Internal.equals(this.data, listBody.data)) {
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
        int hashCode = ((unknownFields().hashCode() * 37) + (this.errno != null ? this.errno.hashCode() : 0)) * 37;
        if (this.errmsg != null) {
            i2 = this.errmsg.hashCode();
        }
        int hashCode2 = ((hashCode + i2) * 37) + (this.data != null ? this.data.hashCode() : 1);
        this.hashCode = hashCode2;
        return hashCode2;
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
        StringBuilder replace = sb.replace(0, 2, "ListBody{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<ListBody, Builder> {
        public List<AddressData> data = Internal.newMutableList();
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

        public Builder data(List<AddressData> list) {
            Internal.checkElementsNotNull(list);
            this.data = list;
            return this;
        }

        public ListBody build() {
            return new ListBody(this.errno, this.errmsg, this.data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_ListBody extends ProtoAdapter<ListBody> {
        ProtoAdapter_ListBody() {
            super(FieldEncoding.LENGTH_DELIMITED, ListBody.class);
        }

        public int encodedSize(ListBody listBody) {
            int i = 0;
            int encodedSizeWithTag = listBody.errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, listBody.errno) : 0;
            if (listBody.errmsg != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(2, listBody.errmsg);
            }
            return encodedSizeWithTag + i + AddressData.ADAPTER.asRepeated().encodedSizeWithTag(3, listBody.data) + listBody.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, ListBody listBody) throws IOException {
            if (listBody.errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, listBody.errno);
            }
            if (listBody.errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, listBody.errmsg);
            }
            if (listBody.data != null) {
                AddressData.ADAPTER.asRepeated().encodeWithTag(protoWriter, 3, listBody.data);
            }
            protoWriter.writeBytes(listBody.unknownFields());
        }

        public ListBody decode(ProtoReader protoReader) throws IOException {
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
                            builder.data.add(AddressData.ADAPTER.decode(protoReader));
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

        public ListBody redact(ListBody listBody) {
            Builder newBuilder = listBody.newBuilder();
            Internal.redactElements(newBuilder.data, AddressData.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
