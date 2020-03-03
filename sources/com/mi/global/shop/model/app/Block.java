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
import okio.ByteString;

public final class Block extends Message<Block, Builder> {
    public static final ProtoAdapter<Block> ADAPTER = new ProtoAdapter_Block();
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.app.BlockData#ADAPTER", tag = 3)
    public final BlockData data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer errno;

    public Block(Integer num, String str, BlockData blockData) {
        this(num, str, blockData, ByteString.EMPTY);
    }

    public Block(Integer num, String str, BlockData blockData, ByteString byteString) {
        super(ADAPTER, byteString);
        this.errno = num;
        this.errmsg = str;
        this.data = blockData;
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
        if (!(obj instanceof Block)) {
            return false;
        }
        Block block = (Block) obj;
        if (!Internal.equals(unknownFields(), block.unknownFields()) || !Internal.equals(this.errno, block.errno) || !Internal.equals(this.errmsg, block.errmsg) || !Internal.equals(this.data, block.data)) {
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
        StringBuilder replace = sb.replace(0, 2, "Block{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Block, Builder> {
        public BlockData data;
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

        public Builder data(BlockData blockData) {
            this.data = blockData;
            return this;
        }

        public Block build() {
            return new Block(this.errno, this.errmsg, this.data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Block extends ProtoAdapter<Block> {
        ProtoAdapter_Block() {
            super(FieldEncoding.LENGTH_DELIMITED, Block.class);
        }

        public int encodedSize(Block block) {
            int i = 0;
            int encodedSizeWithTag = (block.errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, block.errno) : 0) + (block.errmsg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, block.errmsg) : 0);
            if (block.data != null) {
                i = BlockData.ADAPTER.encodedSizeWithTag(3, block.data);
            }
            return encodedSizeWithTag + i + block.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, Block block) throws IOException {
            if (block.errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, block.errno);
            }
            if (block.errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, block.errmsg);
            }
            if (block.data != null) {
                BlockData.ADAPTER.encodeWithTag(protoWriter, 3, block.data);
            }
            protoWriter.writeBytes(block.unknownFields());
        }

        public Block decode(ProtoReader protoReader) throws IOException {
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
                            builder.data(BlockData.ADAPTER.decode(protoReader));
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

        public Block redact(Block block) {
            Builder newBuilder = block.newBuilder();
            if (newBuilder.data != null) {
                newBuilder.data = BlockData.ADAPTER.redact(newBuilder.data);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
