package com.mi.global.shop.model.common;

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

public final class AppConfigData extends Message<AppConfigData, Builder> {
    public static final ProtoAdapter<AppConfigData> ADAPTER = new ProtoAdapter_AppConfigData();
    public static final String DEFAULT_DISCOUNTMIN = "";
    public static final String DEFAULT_DISCOUNTMIN_TXT = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String discountMin;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String discountMin_txt;

    public AppConfigData(String str, String str2) {
        this(str, str2, ByteString.EMPTY);
    }

    public AppConfigData(String str, String str2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.discountMin = str;
        this.discountMin_txt = str2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.discountMin = this.discountMin;
        builder.discountMin_txt = this.discountMin_txt;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AppConfigData)) {
            return false;
        }
        AppConfigData appConfigData = (AppConfigData) obj;
        if (!Internal.equals(unknownFields(), appConfigData.unknownFields()) || !Internal.equals(this.discountMin, appConfigData.discountMin) || !Internal.equals(this.discountMin_txt, appConfigData.discountMin_txt)) {
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
        int hashCode = ((unknownFields().hashCode() * 37) + (this.discountMin != null ? this.discountMin.hashCode() : 0)) * 37;
        if (this.discountMin_txt != null) {
            i2 = this.discountMin_txt.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.discountMin != null) {
            sb.append(", discountMin=");
            sb.append(this.discountMin);
        }
        if (this.discountMin_txt != null) {
            sb.append(", discountMin_txt=");
            sb.append(this.discountMin_txt);
        }
        StringBuilder replace = sb.replace(0, 2, "AppConfigData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<AppConfigData, Builder> {
        public String discountMin;
        public String discountMin_txt;

        public Builder discountMin(String str) {
            this.discountMin = str;
            return this;
        }

        public Builder discountMin_txt(String str) {
            this.discountMin_txt = str;
            return this;
        }

        public AppConfigData build() {
            return new AppConfigData(this.discountMin, this.discountMin_txt, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_AppConfigData extends ProtoAdapter<AppConfigData> {
        ProtoAdapter_AppConfigData() {
            super(FieldEncoding.LENGTH_DELIMITED, AppConfigData.class);
        }

        public int encodedSize(AppConfigData appConfigData) {
            int i = 0;
            int encodedSizeWithTag = appConfigData.discountMin != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, appConfigData.discountMin) : 0;
            if (appConfigData.discountMin_txt != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(5, appConfigData.discountMin_txt);
            }
            return encodedSizeWithTag + i + appConfigData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, AppConfigData appConfigData) throws IOException {
            if (appConfigData.discountMin != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, appConfigData.discountMin);
            }
            if (appConfigData.discountMin_txt != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, appConfigData.discountMin_txt);
            }
            protoWriter.writeBytes(appConfigData.unknownFields());
        }

        public AppConfigData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag == -1) {
                    protoReader.endMessage(beginMessage);
                    return builder.build();
                } else if (nextTag == 1) {
                    builder.discountMin(ProtoAdapter.STRING.decode(protoReader));
                } else if (nextTag != 5) {
                    FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                    builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                } else {
                    builder.discountMin_txt(ProtoAdapter.STRING.decode(protoReader));
                }
            }
        }

        public AppConfigData redact(AppConfigData appConfigData) {
            Builder newBuilder = appConfigData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
