package com.mi.global.shop.model.discover;

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

public final class FindData extends Message<FindData, Builder> {
    public static final ProtoAdapter<FindData> ADAPTER = new ProtoAdapter_FindData();
    public static final Long DEFAULT_CURRENTPAGE = 0L;
    public static final Long DEFAULT_TOTALPAGES = 0L;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 2)
    public final Long currentpage;
    @WireField(adapter = "com.mi.global.shop.model.app.ItemsInfo#ADAPTER", label = WireField.Label.REPEATED, tag = 3)
    public final List<ItemsInfo> items;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 1)
    public final Long totalpages;

    public FindData(Long l, Long l2, List<ItemsInfo> list) {
        this(l, l2, list, ByteString.EMPTY);
    }

    public FindData(Long l, Long l2, List<ItemsInfo> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.totalpages = l;
        this.currentpage = l2;
        this.items = Internal.immutableCopyOf("items", list);
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.totalpages = this.totalpages;
        builder.currentpage = this.currentpage;
        builder.items = Internal.copyOf("items", this.items);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FindData)) {
            return false;
        }
        FindData findData = (FindData) obj;
        if (!Internal.equals(unknownFields(), findData.unknownFields()) || !Internal.equals(this.totalpages, findData.totalpages) || !Internal.equals(this.currentpage, findData.currentpage) || !Internal.equals(this.items, findData.items)) {
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
        int hashCode = ((unknownFields().hashCode() * 37) + (this.totalpages != null ? this.totalpages.hashCode() : 0)) * 37;
        if (this.currentpage != null) {
            i2 = this.currentpage.hashCode();
        }
        int hashCode2 = ((hashCode + i2) * 37) + (this.items != null ? this.items.hashCode() : 1);
        this.hashCode = hashCode2;
        return hashCode2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.totalpages != null) {
            sb.append(", totalpages=");
            sb.append(this.totalpages);
        }
        if (this.currentpage != null) {
            sb.append(", currentpage=");
            sb.append(this.currentpage);
        }
        if (this.items != null) {
            sb.append(", items=");
            sb.append(this.items);
        }
        StringBuilder replace = sb.replace(0, 2, "FindData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<FindData, Builder> {
        public Long currentpage;
        public List<ItemsInfo> items = Internal.newMutableList();
        public Long totalpages;

        public Builder totalpages(Long l) {
            this.totalpages = l;
            return this;
        }

        public Builder currentpage(Long l) {
            this.currentpage = l;
            return this;
        }

        public Builder items(List<ItemsInfo> list) {
            Internal.checkElementsNotNull(list);
            this.items = list;
            return this;
        }

        public FindData build() {
            return new FindData(this.totalpages, this.currentpage, this.items, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_FindData extends ProtoAdapter<FindData> {
        ProtoAdapter_FindData() {
            super(FieldEncoding.LENGTH_DELIMITED, FindData.class);
        }

        public int encodedSize(FindData findData) {
            int i = 0;
            int encodedSizeWithTag = findData.totalpages != null ? ProtoAdapter.INT64.encodedSizeWithTag(1, findData.totalpages) : 0;
            if (findData.currentpage != null) {
                i = ProtoAdapter.INT64.encodedSizeWithTag(2, findData.currentpage);
            }
            return encodedSizeWithTag + i + ItemsInfo.ADAPTER.asRepeated().encodedSizeWithTag(3, findData.items) + findData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, FindData findData) throws IOException {
            if (findData.totalpages != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 1, findData.totalpages);
            }
            if (findData.currentpage != null) {
                ProtoAdapter.INT64.encodeWithTag(protoWriter, 2, findData.currentpage);
            }
            if (findData.items != null) {
                ItemsInfo.ADAPTER.asRepeated().encodeWithTag(protoWriter, 3, findData.items);
            }
            protoWriter.writeBytes(findData.unknownFields());
        }

        public FindData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.totalpages(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 2:
                            builder.currentpage(ProtoAdapter.INT64.decode(protoReader));
                            break;
                        case 3:
                            builder.items.add(ItemsInfo.ADAPTER.decode(protoReader));
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

        public FindData redact(FindData findData) {
            Builder newBuilder = findData.newBuilder();
            Internal.redactElements(newBuilder.items, ItemsInfo.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
