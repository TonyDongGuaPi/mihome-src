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

public final class RefreshUserInfo extends Message<RefreshUserInfo, Builder> {
    public static final ProtoAdapter<RefreshUserInfo> ADAPTER = new ProtoAdapter_RefreshUserInfo();
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.app.RefreshUserInfoData#ADAPTER", tag = 3)
    public final RefreshUserInfoData data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer errno;

    public RefreshUserInfo(Integer num, String str, RefreshUserInfoData refreshUserInfoData) {
        this(num, str, refreshUserInfoData, ByteString.EMPTY);
    }

    public RefreshUserInfo(Integer num, String str, RefreshUserInfoData refreshUserInfoData, ByteString byteString) {
        super(ADAPTER, byteString);
        this.errno = num;
        this.errmsg = str;
        this.data = refreshUserInfoData;
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
        if (!(obj instanceof RefreshUserInfo)) {
            return false;
        }
        RefreshUserInfo refreshUserInfo = (RefreshUserInfo) obj;
        if (!Internal.equals(unknownFields(), refreshUserInfo.unknownFields()) || !Internal.equals(this.errno, refreshUserInfo.errno) || !Internal.equals(this.errmsg, refreshUserInfo.errmsg) || !Internal.equals(this.data, refreshUserInfo.data)) {
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
        StringBuilder replace = sb.replace(0, 2, "RefreshUserInfo{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<RefreshUserInfo, Builder> {
        public RefreshUserInfoData data;
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

        public Builder data(RefreshUserInfoData refreshUserInfoData) {
            this.data = refreshUserInfoData;
            return this;
        }

        public RefreshUserInfo build() {
            return new RefreshUserInfo(this.errno, this.errmsg, this.data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_RefreshUserInfo extends ProtoAdapter<RefreshUserInfo> {
        ProtoAdapter_RefreshUserInfo() {
            super(FieldEncoding.LENGTH_DELIMITED, RefreshUserInfo.class);
        }

        public int encodedSize(RefreshUserInfo refreshUserInfo) {
            int i = 0;
            int encodedSizeWithTag = (refreshUserInfo.errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, refreshUserInfo.errno) : 0) + (refreshUserInfo.errmsg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, refreshUserInfo.errmsg) : 0);
            if (refreshUserInfo.data != null) {
                i = RefreshUserInfoData.ADAPTER.encodedSizeWithTag(3, refreshUserInfo.data);
            }
            return encodedSizeWithTag + i + refreshUserInfo.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, RefreshUserInfo refreshUserInfo) throws IOException {
            if (refreshUserInfo.errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, refreshUserInfo.errno);
            }
            if (refreshUserInfo.errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, refreshUserInfo.errmsg);
            }
            if (refreshUserInfo.data != null) {
                RefreshUserInfoData.ADAPTER.encodeWithTag(protoWriter, 3, refreshUserInfo.data);
            }
            protoWriter.writeBytes(refreshUserInfo.unknownFields());
        }

        public RefreshUserInfo decode(ProtoReader protoReader) throws IOException {
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
                            builder.data(RefreshUserInfoData.ADAPTER.decode(protoReader));
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

        public RefreshUserInfo redact(RefreshUserInfo refreshUserInfo) {
            Builder newBuilder = refreshUserInfo.newBuilder();
            if (newBuilder.data != null) {
                newBuilder.data = RefreshUserInfoData.ADAPTER.redact(newBuilder.data);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
