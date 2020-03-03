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

public final class PushType extends Message<PushType, Builder> {
    public static final ProtoAdapter<PushType> ADAPTER = new ProtoAdapter_PushType();
    public static final Boolean DEFAULT_DEFAULTSTATUS = false;
    public static final String DEFAULT_DESC = "";
    public static final Boolean DEFAULT_ENABLE = false;
    public static final String DEFAULT_KEY = "";
    public static final String DEFAULT_TITLE = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 5)
    public final Boolean defaultStatus;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String desc;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 4)
    public final Boolean enable;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String key;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String title;

    public PushType(String str, String str2, String str3, Boolean bool, Boolean bool2) {
        this(str, str2, str3, bool, bool2, ByteString.EMPTY);
    }

    public PushType(String str, String str2, String str3, Boolean bool, Boolean bool2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.title = str;
        this.desc = str2;
        this.key = str3;
        this.enable = bool;
        this.defaultStatus = bool2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.title = this.title;
        builder.desc = this.desc;
        builder.key = this.key;
        builder.enable = this.enable;
        builder.defaultStatus = this.defaultStatus;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PushType)) {
            return false;
        }
        PushType pushType = (PushType) obj;
        if (!Internal.equals(unknownFields(), pushType.unknownFields()) || !Internal.equals(this.title, pushType.title) || !Internal.equals(this.desc, pushType.desc) || !Internal.equals(this.key, pushType.key) || !Internal.equals(this.enable, pushType.enable) || !Internal.equals(this.defaultStatus, pushType.defaultStatus)) {
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
        int hashCode = ((((((((unknownFields().hashCode() * 37) + (this.title != null ? this.title.hashCode() : 0)) * 37) + (this.desc != null ? this.desc.hashCode() : 0)) * 37) + (this.key != null ? this.key.hashCode() : 0)) * 37) + (this.enable != null ? this.enable.hashCode() : 0)) * 37;
        if (this.defaultStatus != null) {
            i2 = this.defaultStatus.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.title != null) {
            sb.append(", title=");
            sb.append(this.title);
        }
        if (this.desc != null) {
            sb.append(", desc=");
            sb.append(this.desc);
        }
        if (this.key != null) {
            sb.append(", key=");
            sb.append(this.key);
        }
        if (this.enable != null) {
            sb.append(", enable=");
            sb.append(this.enable);
        }
        if (this.defaultStatus != null) {
            sb.append(", defaultStatus=");
            sb.append(this.defaultStatus);
        }
        StringBuilder replace = sb.replace(0, 2, "PushType{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<PushType, Builder> {
        public Boolean defaultStatus;
        public String desc;
        public Boolean enable;
        public String key;
        public String title;

        public Builder title(String str) {
            this.title = str;
            return this;
        }

        public Builder desc(String str) {
            this.desc = str;
            return this;
        }

        public Builder key(String str) {
            this.key = str;
            return this;
        }

        public Builder enable(Boolean bool) {
            this.enable = bool;
            return this;
        }

        public Builder defaultStatus(Boolean bool) {
            this.defaultStatus = bool;
            return this;
        }

        public PushType build() {
            return new PushType(this.title, this.desc, this.key, this.enable, this.defaultStatus, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_PushType extends ProtoAdapter<PushType> {
        ProtoAdapter_PushType() {
            super(FieldEncoding.LENGTH_DELIMITED, PushType.class);
        }

        public int encodedSize(PushType pushType) {
            int i = 0;
            int encodedSizeWithTag = (pushType.title != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, pushType.title) : 0) + (pushType.desc != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, pushType.desc) : 0) + (pushType.key != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, pushType.key) : 0) + (pushType.enable != null ? ProtoAdapter.BOOL.encodedSizeWithTag(4, pushType.enable) : 0);
            if (pushType.defaultStatus != null) {
                i = ProtoAdapter.BOOL.encodedSizeWithTag(5, pushType.defaultStatus);
            }
            return encodedSizeWithTag + i + pushType.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, PushType pushType) throws IOException {
            if (pushType.title != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, pushType.title);
            }
            if (pushType.desc != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, pushType.desc);
            }
            if (pushType.key != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, pushType.key);
            }
            if (pushType.enable != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 4, pushType.enable);
            }
            if (pushType.defaultStatus != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 5, pushType.defaultStatus);
            }
            protoWriter.writeBytes(pushType.unknownFields());
        }

        public PushType decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.title(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.desc(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.key(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.enable(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 5:
                            builder.defaultStatus(ProtoAdapter.BOOL.decode(protoReader));
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

        public PushType redact(PushType pushType) {
            Builder newBuilder = pushType.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
