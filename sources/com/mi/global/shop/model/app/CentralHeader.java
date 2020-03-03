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

public final class CentralHeader extends Message<CentralHeader, Builder> {
    public static final ProtoAdapter<CentralHeader> ADAPTER = new ProtoAdapter_CentralHeader();
    public static final String DEFAULT_USERCENTRALHEADERBG = "";
    public static final String DEFAULT_USERCENTRALHEADERTITLECOLOR = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String userCentralHeaderBg;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String userCentralHeaderTitleColor;

    public CentralHeader(String str, String str2) {
        this(str, str2, ByteString.EMPTY);
    }

    public CentralHeader(String str, String str2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.userCentralHeaderBg = str;
        this.userCentralHeaderTitleColor = str2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.userCentralHeaderBg = this.userCentralHeaderBg;
        builder.userCentralHeaderTitleColor = this.userCentralHeaderTitleColor;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CentralHeader)) {
            return false;
        }
        CentralHeader centralHeader = (CentralHeader) obj;
        if (!Internal.equals(unknownFields(), centralHeader.unknownFields()) || !Internal.equals(this.userCentralHeaderBg, centralHeader.userCentralHeaderBg) || !Internal.equals(this.userCentralHeaderTitleColor, centralHeader.userCentralHeaderTitleColor)) {
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
        int hashCode = ((unknownFields().hashCode() * 37) + (this.userCentralHeaderBg != null ? this.userCentralHeaderBg.hashCode() : 0)) * 37;
        if (this.userCentralHeaderTitleColor != null) {
            i2 = this.userCentralHeaderTitleColor.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.userCentralHeaderBg != null) {
            sb.append(", userCentralHeaderBg=");
            sb.append(this.userCentralHeaderBg);
        }
        if (this.userCentralHeaderTitleColor != null) {
            sb.append(", userCentralHeaderTitleColor=");
            sb.append(this.userCentralHeaderTitleColor);
        }
        StringBuilder replace = sb.replace(0, 2, "CentralHeader{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<CentralHeader, Builder> {
        public String userCentralHeaderBg;
        public String userCentralHeaderTitleColor;

        public Builder userCentralHeaderBg(String str) {
            this.userCentralHeaderBg = str;
            return this;
        }

        public Builder userCentralHeaderTitleColor(String str) {
            this.userCentralHeaderTitleColor = str;
            return this;
        }

        public CentralHeader build() {
            return new CentralHeader(this.userCentralHeaderBg, this.userCentralHeaderTitleColor, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_CentralHeader extends ProtoAdapter<CentralHeader> {
        ProtoAdapter_CentralHeader() {
            super(FieldEncoding.LENGTH_DELIMITED, CentralHeader.class);
        }

        public int encodedSize(CentralHeader centralHeader) {
            int i = 0;
            int encodedSizeWithTag = centralHeader.userCentralHeaderBg != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, centralHeader.userCentralHeaderBg) : 0;
            if (centralHeader.userCentralHeaderTitleColor != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(2, centralHeader.userCentralHeaderTitleColor);
            }
            return encodedSizeWithTag + i + centralHeader.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, CentralHeader centralHeader) throws IOException {
            if (centralHeader.userCentralHeaderBg != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, centralHeader.userCentralHeaderBg);
            }
            if (centralHeader.userCentralHeaderTitleColor != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, centralHeader.userCentralHeaderTitleColor);
            }
            protoWriter.writeBytes(centralHeader.unknownFields());
        }

        public CentralHeader decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.userCentralHeaderBg(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.userCentralHeaderTitleColor(ProtoAdapter.STRING.decode(protoReader));
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

        public CentralHeader redact(CentralHeader centralHeader) {
            Builder newBuilder = centralHeader.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
