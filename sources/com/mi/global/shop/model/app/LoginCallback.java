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

public final class LoginCallback extends Message<LoginCallback, Builder> {
    public static final ProtoAdapter<LoginCallback> ADAPTER = new ProtoAdapter_LoginCallback();
    public static final String DEFAULT_ERRMSG = "";
    public static final Integer DEFAULT_ERRNO = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.mi.global.shop.model.app.LoginCallbackData#ADAPTER", tag = 3)
    public final LoginCallbackData data;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String errmsg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer errno;

    public LoginCallback(Integer num, String str, LoginCallbackData loginCallbackData) {
        this(num, str, loginCallbackData, ByteString.EMPTY);
    }

    public LoginCallback(Integer num, String str, LoginCallbackData loginCallbackData, ByteString byteString) {
        super(ADAPTER, byteString);
        this.errno = num;
        this.errmsg = str;
        this.data = loginCallbackData;
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
        if (!(obj instanceof LoginCallback)) {
            return false;
        }
        LoginCallback loginCallback = (LoginCallback) obj;
        if (!Internal.equals(unknownFields(), loginCallback.unknownFields()) || !Internal.equals(this.errno, loginCallback.errno) || !Internal.equals(this.errmsg, loginCallback.errmsg) || !Internal.equals(this.data, loginCallback.data)) {
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
        StringBuilder replace = sb.replace(0, 2, "LoginCallback{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<LoginCallback, Builder> {
        public LoginCallbackData data;
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

        public Builder data(LoginCallbackData loginCallbackData) {
            this.data = loginCallbackData;
            return this;
        }

        public LoginCallback build() {
            return new LoginCallback(this.errno, this.errmsg, this.data, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_LoginCallback extends ProtoAdapter<LoginCallback> {
        ProtoAdapter_LoginCallback() {
            super(FieldEncoding.LENGTH_DELIMITED, LoginCallback.class);
        }

        public int encodedSize(LoginCallback loginCallback) {
            int i = 0;
            int encodedSizeWithTag = (loginCallback.errno != null ? ProtoAdapter.INT32.encodedSizeWithTag(1, loginCallback.errno) : 0) + (loginCallback.errmsg != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, loginCallback.errmsg) : 0);
            if (loginCallback.data != null) {
                i = LoginCallbackData.ADAPTER.encodedSizeWithTag(3, loginCallback.data);
            }
            return encodedSizeWithTag + i + loginCallback.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, LoginCallback loginCallback) throws IOException {
            if (loginCallback.errno != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, loginCallback.errno);
            }
            if (loginCallback.errmsg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, loginCallback.errmsg);
            }
            if (loginCallback.data != null) {
                LoginCallbackData.ADAPTER.encodeWithTag(protoWriter, 3, loginCallback.data);
            }
            protoWriter.writeBytes(loginCallback.unknownFields());
        }

        public LoginCallback decode(ProtoReader protoReader) throws IOException {
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
                            builder.data(LoginCallbackData.ADAPTER.decode(protoReader));
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

        public LoginCallback redact(LoginCallback loginCallback) {
            Builder newBuilder = loginCallback.newBuilder();
            if (newBuilder.data != null) {
                newBuilder.data = LoginCallbackData.ADAPTER.redact(newBuilder.data);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
