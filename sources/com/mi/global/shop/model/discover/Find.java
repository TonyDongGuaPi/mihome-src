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
import okio.ByteString;

public final class Find extends Message<Find, Builder> {
    public static final ProtoAdapter<Find> ADAPTER = new ProtoAdapter_Find();
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.app.FindData#ADAPTER", tag = 3)
    public final FindData data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer errno;

    public Find(Integer num, String str, FindData findData) {
        this(num, str, findData, ByteString.EMPTY);
    }

    public Find(Integer num, String str, FindData findData, ByteString byteString) {
        super(ADAPTER, byteString);
        this.errno = num;
        this.errmsg = str;
        this.data = findData;
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
        if (!(obj instanceof Find)) {
            return false;
        }
        Find find = (Find) obj;
        if (!Internal.equals(unknownFields(), find.unknownFields()) || !Internal.equals(this.errno, find.errno) || !Internal.equals(this.errmsg, find.errmsg) || !Internal.equals(this.data, find.data)) {
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
        StringBuilder replace = sb.replace(0, 2, "Find{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Find, Builder> {
        public FindData data;
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

        public Builder data(FindData findData) {
            this.data = findData;
            return this;
        }

        public Find build() {
            return new Find(this.errno, this.errmsg, this.data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Find extends ProtoAdapter<Find> {
        ProtoAdapter_Find() {
            super(FieldEncoding.LENGTH_DELIMITED, Find.class);
        }

        public int encodedSize(Find find) {
            int i = 0;
            int encodedSizeWithTag = (find.errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, find.errno) : 0) + (find.errmsg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, find.errmsg) : 0);
            if (find.data != null) {
                i = FindData.ADAPTER.encodedSizeWithTag(3, find.data);
            }
            return encodedSizeWithTag + i + find.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, Find find) throws IOException {
            if (find.errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, find.errno);
            }
            if (find.errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, find.errmsg);
            }
            if (find.data != null) {
                FindData.ADAPTER.encodeWithTag(protoWriter, 3, find.data);
            }
            protoWriter.writeBytes(find.unknownFields());
        }

        public Find decode(ProtoReader protoReader) throws IOException {
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
                            builder.data(FindData.ADAPTER.decode(protoReader));
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

        public Find redact(Find find) {
            Builder newBuilder = find.newBuilder();
            if (newBuilder.data != null) {
                newBuilder.data = FindData.ADAPTER.redact(newBuilder.data);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
