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

public final class Sync extends Message<Sync, Builder> {
    public static final ProtoAdapter<Sync> ADAPTER = new ProtoAdapter_Sync();
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.app.SyncData#ADAPTER", tag = 3)
    public final SyncData data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer errno;

    public Sync(Integer num, String str, SyncData syncData) {
        this(num, str, syncData, ByteString.EMPTY);
    }

    public Sync(Integer num, String str, SyncData syncData, ByteString byteString) {
        super(ADAPTER, byteString);
        this.errno = num;
        this.errmsg = str;
        this.data = syncData;
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
        if (!(obj instanceof Sync)) {
            return false;
        }
        Sync sync = (Sync) obj;
        if (!Internal.equals(unknownFields(), sync.unknownFields()) || !Internal.equals(this.errno, sync.errno) || !Internal.equals(this.errmsg, sync.errmsg) || !Internal.equals(this.data, sync.data)) {
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
        StringBuilder replace = sb.replace(0, 2, "Sync{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Sync, Builder> {
        public SyncData data;
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

        public Builder data(SyncData syncData) {
            this.data = syncData;
            return this;
        }

        public Sync build() {
            return new Sync(this.errno, this.errmsg, this.data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Sync extends ProtoAdapter<Sync> {
        ProtoAdapter_Sync() {
            super(FieldEncoding.LENGTH_DELIMITED, Sync.class);
        }

        public int encodedSize(Sync sync) {
            int i = 0;
            int encodedSizeWithTag = (sync.errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, sync.errno) : 0) + (sync.errmsg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, sync.errmsg) : 0);
            if (sync.data != null) {
                i = SyncData.ADAPTER.encodedSizeWithTag(3, sync.data);
            }
            return encodedSizeWithTag + i + sync.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, Sync sync) throws IOException {
            if (sync.errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, sync.errno);
            }
            if (sync.errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, sync.errmsg);
            }
            if (sync.data != null) {
                SyncData.ADAPTER.encodeWithTag(protoWriter, 3, sync.data);
            }
            protoWriter.writeBytes(sync.unknownFields());
        }

        public Sync decode(ProtoReader protoReader) throws IOException {
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
                            builder.data(SyncData.ADAPTER.decode(protoReader));
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

        public Sync redact(Sync sync) {
            Builder newBuilder = sync.newBuilder();
            if (newBuilder.data != null) {
                newBuilder.data = SyncData.ADAPTER.redact(newBuilder.data);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
