package com.mi.global.shop.model.app;

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

public final class BlockInfo extends Message<BlockInfo, Builder> {
    public static final ProtoAdapter<BlockInfo> ADAPTER = new ProtoAdapter_BlockInfo();
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.app.Desc#ADAPTER", tag = 2)
    public final Desc desc;
    @WireField(adapter = "com.mi.global.shop.model.app.Items#ADAPTER", label = WireField.Label.REPEATED, tag = 1)
    public final List<Items> items;

    public BlockInfo(List<Items> list, Desc desc2) {
        this(list, desc2, ByteString.EMPTY);
    }

    public BlockInfo(List<Items> list, Desc desc2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.items = Internal.immutableCopyOf("items", list);
        this.desc = desc2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.items = Internal.copyOf("items", this.items);
        builder.desc = this.desc;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BlockInfo)) {
            return false;
        }
        BlockInfo blockInfo = (BlockInfo) obj;
        if (!Internal.equals(unknownFields(), blockInfo.unknownFields()) || !Internal.equals(this.items, blockInfo.items) || !Internal.equals(this.desc, blockInfo.desc)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = (((unknownFields().hashCode() * 37) + (this.items != null ? this.items.hashCode() : 1)) * 37) + (this.desc != null ? this.desc.hashCode() : 0);
        this.hashCode = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.items != null) {
            sb.append(", items=");
            sb.append(this.items);
        }
        if (this.desc != null) {
            sb.append(", desc=");
            sb.append(this.desc);
        }
        StringBuilder replace = sb.replace(0, 2, "BlockInfo{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<BlockInfo, Builder> {
        public Desc desc;
        public List<Items> items = Internal.newMutableList();

        public Builder items(List<Items> list) {
            Internal.checkElementsNotNull(list);
            this.items = list;
            return this;
        }

        public Builder desc(Desc desc2) {
            this.desc = desc2;
            return this;
        }

        public BlockInfo build() {
            return new BlockInfo(this.items, this.desc, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_BlockInfo extends ProtoAdapter<BlockInfo> {
        ProtoAdapter_BlockInfo() {
            super(FieldEncoding.LENGTH_DELIMITED, BlockInfo.class);
        }

        public int encodedSize(BlockInfo blockInfo) {
            return Items.ADAPTER.asRepeated().encodedSizeWithTag(1, blockInfo.items) + (blockInfo.desc != null ? Desc.ADAPTER.encodedSizeWithTag(2, blockInfo.desc) : 0) + blockInfo.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, BlockInfo blockInfo) throws IOException {
            if (blockInfo.items != null) {
                Items.ADAPTER.asRepeated().encodeWithTag(protoWriter, 1, blockInfo.items);
            }
            if (blockInfo.desc != null) {
                Desc.ADAPTER.encodeWithTag(protoWriter, 2, blockInfo.desc);
            }
            protoWriter.writeBytes(blockInfo.unknownFields());
        }

        public BlockInfo decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.items.add(Items.ADAPTER.decode(protoReader));
                            break;
                        case 2:
                            builder.desc(Desc.ADAPTER.decode(protoReader));
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

        public BlockInfo redact(BlockInfo blockInfo) {
            Builder newBuilder = blockInfo.newBuilder();
            Internal.redactElements(newBuilder.items, Items.ADAPTER);
            if (newBuilder.desc != null) {
                newBuilder.desc = Desc.ADAPTER.redact(newBuilder.desc);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
