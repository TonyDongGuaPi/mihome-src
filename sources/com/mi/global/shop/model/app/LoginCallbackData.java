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

public final class LoginCallbackData extends Message<LoginCallbackData, Builder> {
    public static final ProtoAdapter<LoginCallbackData> ADAPTER = new ProtoAdapter_LoginCallbackData();
    public static final Integer DEFAULT_CART = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 2)
    public final Integer cart;
    @WireField(adapter = "com.mi.global.shop.model.app.CookieInfo#ADAPTER", label = WireField.Label.REPEATED, tag = 3)
    public final List<CookieInfo> cookies;
    @WireField(adapter = "com.mi.global.shop.model.app.UserProfile#ADAPTER", tag = 1)
    public final UserProfile profile;

    public LoginCallbackData(UserProfile userProfile, Integer num, List<CookieInfo> list) {
        this(userProfile, num, list, ByteString.EMPTY);
    }

    public LoginCallbackData(UserProfile userProfile, Integer num, List<CookieInfo> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.profile = userProfile;
        this.cart = num;
        this.cookies = Internal.immutableCopyOf("cookies", list);
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.profile = this.profile;
        builder.cart = this.cart;
        builder.cookies = Internal.copyOf("cookies", this.cookies);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LoginCallbackData)) {
            return false;
        }
        LoginCallbackData loginCallbackData = (LoginCallbackData) obj;
        if (!Internal.equals(unknownFields(), loginCallbackData.unknownFields()) || !Internal.equals(this.profile, loginCallbackData.profile) || !Internal.equals(this.cart, loginCallbackData.cart) || !Internal.equals(this.cookies, loginCallbackData.cookies)) {
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
        int hashCode = ((unknownFields().hashCode() * 37) + (this.profile != null ? this.profile.hashCode() : 0)) * 37;
        if (this.cart != null) {
            i2 = this.cart.hashCode();
        }
        int hashCode2 = ((hashCode + i2) * 37) + (this.cookies != null ? this.cookies.hashCode() : 1);
        this.hashCode = hashCode2;
        return hashCode2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.profile != null) {
            sb.append(", profile=");
            sb.append(this.profile);
        }
        if (this.cart != null) {
            sb.append(", cart=");
            sb.append(this.cart);
        }
        if (this.cookies != null) {
            sb.append(", cookies=");
            sb.append(this.cookies);
        }
        StringBuilder replace = sb.replace(0, 2, "LoginCallbackData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<LoginCallbackData, Builder> {
        public Integer cart;
        public List<CookieInfo> cookies = Internal.newMutableList();
        public UserProfile profile;

        public Builder profile(UserProfile userProfile) {
            this.profile = userProfile;
            return this;
        }

        public Builder cart(Integer num) {
            this.cart = num;
            return this;
        }

        public Builder cookies(List<CookieInfo> list) {
            Internal.checkElementsNotNull(list);
            this.cookies = list;
            return this;
        }

        public LoginCallbackData build() {
            return new LoginCallbackData(this.profile, this.cart, this.cookies, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_LoginCallbackData extends ProtoAdapter<LoginCallbackData> {
        ProtoAdapter_LoginCallbackData() {
            super(FieldEncoding.LENGTH_DELIMITED, LoginCallbackData.class);
        }

        public int encodedSize(LoginCallbackData loginCallbackData) {
            int i = 0;
            int encodedSizeWithTag = loginCallbackData.profile != null ? UserProfile.ADAPTER.encodedSizeWithTag(1, loginCallbackData.profile) : 0;
            if (loginCallbackData.cart != null) {
                i = ProtoAdapter.INT32.encodedSizeWithTag(2, loginCallbackData.cart);
            }
            return encodedSizeWithTag + i + CookieInfo.ADAPTER.asRepeated().encodedSizeWithTag(3, loginCallbackData.cookies) + loginCallbackData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, LoginCallbackData loginCallbackData) throws IOException {
            if (loginCallbackData.profile != null) {
                UserProfile.ADAPTER.encodeWithTag(protoWriter, 1, loginCallbackData.profile);
            }
            if (loginCallbackData.cart != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 2, loginCallbackData.cart);
            }
            if (loginCallbackData.cookies != null) {
                CookieInfo.ADAPTER.asRepeated().encodeWithTag(protoWriter, 3, loginCallbackData.cookies);
            }
            protoWriter.writeBytes(loginCallbackData.unknownFields());
        }

        public LoginCallbackData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.profile(UserProfile.ADAPTER.decode(protoReader));
                            break;
                        case 2:
                            builder.cart(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 3:
                            builder.cookies.add(CookieInfo.ADAPTER.decode(protoReader));
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

        public LoginCallbackData redact(LoginCallbackData loginCallbackData) {
            Builder newBuilder = loginCallbackData.newBuilder();
            if (newBuilder.profile != null) {
                newBuilder.profile = UserProfile.ADAPTER.redact(newBuilder.profile);
            }
            Internal.redactElements(newBuilder.cookies, CookieInfo.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
