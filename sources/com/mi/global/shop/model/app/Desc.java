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

public final class Desc extends Message<Desc, Builder> {
    public static final ProtoAdapter<Desc> ADAPTER = new ProtoAdapter_Desc();
    public static final String DEFAULT_BACKGROUND_IMG = "";
    public static final String DEFAULT_BUTTON_IMG = "";
    public static final String DEFAULT_BUTTON_TEXT = "";
    public static final Integer DEFAULT_SORT = 0;
    public static final String DEFAULT_SUB_TYPE = "";
    public static final String DEFAULT_TITLE = "";
    public static final String DEFAULT_TYPE = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String background_img;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String button_img;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String button_text;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 4)
    public final Integer sort;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String sub_type;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String title;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String type;

    public Desc(String str, String str2, String str3, Integer num, String str4, String str5, String str6) {
        this(str, str2, str3, num, str4, str5, str6, ByteString.EMPTY);
    }

    public Desc(String str, String str2, String str3, Integer num, String str4, String str5, String str6, ByteString byteString) {
        super(ADAPTER, byteString);
        this.type = str;
        this.sub_type = str2;
        this.title = str3;
        this.sort = num;
        this.background_img = str4;
        this.button_img = str5;
        this.button_text = str6;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.type = this.type;
        builder.sub_type = this.sub_type;
        builder.title = this.title;
        builder.sort = this.sort;
        builder.background_img = this.background_img;
        builder.button_img = this.button_img;
        builder.button_text = this.button_text;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Desc)) {
            return false;
        }
        Desc desc = (Desc) obj;
        if (!Internal.equals(unknownFields(), desc.unknownFields()) || !Internal.equals(this.type, desc.type) || !Internal.equals(this.sub_type, desc.sub_type) || !Internal.equals(this.title, desc.title) || !Internal.equals(this.sort, desc.sort) || !Internal.equals(this.background_img, desc.background_img) || !Internal.equals(this.button_img, desc.button_img) || !Internal.equals(this.button_text, desc.button_text)) {
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
        int hashCode = ((((((((((((unknownFields().hashCode() * 37) + (this.type != null ? this.type.hashCode() : 0)) * 37) + (this.sub_type != null ? this.sub_type.hashCode() : 0)) * 37) + (this.title != null ? this.title.hashCode() : 0)) * 37) + (this.sort != null ? this.sort.hashCode() : 0)) * 37) + (this.background_img != null ? this.background_img.hashCode() : 0)) * 37) + (this.button_img != null ? this.button_img.hashCode() : 0)) * 37;
        if (this.button_text != null) {
            i2 = this.button_text.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.type != null) {
            sb.append(", type=");
            sb.append(this.type);
        }
        if (this.sub_type != null) {
            sb.append(", sub_type=");
            sb.append(this.sub_type);
        }
        if (this.title != null) {
            sb.append(", title=");
            sb.append(this.title);
        }
        if (this.sort != null) {
            sb.append(", sort=");
            sb.append(this.sort);
        }
        if (this.background_img != null) {
            sb.append(", background_img=");
            sb.append(this.background_img);
        }
        if (this.button_img != null) {
            sb.append(", button_img=");
            sb.append(this.button_img);
        }
        if (this.button_text != null) {
            sb.append(", button_text=");
            sb.append(this.button_text);
        }
        StringBuilder replace = sb.replace(0, 2, "Desc{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Desc, Builder> {
        public String background_img;
        public String button_img;
        public String button_text;
        public Integer sort;
        public String sub_type;
        public String title;
        public String type;

        public Builder type(String str) {
            this.type = str;
            return this;
        }

        public Builder sub_type(String str) {
            this.sub_type = str;
            return this;
        }

        public Builder title(String str) {
            this.title = str;
            return this;
        }

        public Builder sort(Integer num) {
            this.sort = num;
            return this;
        }

        public Builder background_img(String str) {
            this.background_img = str;
            return this;
        }

        public Builder button_img(String str) {
            this.button_img = str;
            return this;
        }

        public Builder button_text(String str) {
            this.button_text = str;
            return this;
        }

        public Desc build() {
            return new Desc(this.type, this.sub_type, this.title, this.sort, this.background_img, this.button_img, this.button_text, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Desc extends ProtoAdapter<Desc> {
        ProtoAdapter_Desc() {
            super(FieldEncoding.LENGTH_DELIMITED, Desc.class);
        }

        public int encodedSize(Desc desc) {
            int i = 0;
            int encodedSizeWithTag = (desc.type != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, desc.type) : 0) + (desc.sub_type != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, desc.sub_type) : 0) + (desc.title != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, desc.title) : 0) + (desc.sort != null ? ProtoAdapter.INT32.encodedSizeWithTag(4, desc.sort) : 0) + (desc.background_img != null ? ProtoAdapter.STRING.encodedSizeWithTag(5, desc.background_img) : 0) + (desc.button_img != null ? ProtoAdapter.STRING.encodedSizeWithTag(6, desc.button_img) : 0);
            if (desc.button_text != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(7, desc.button_text);
            }
            return encodedSizeWithTag + i + desc.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, Desc desc) throws IOException {
            if (desc.type != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, desc.type);
            }
            if (desc.sub_type != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, desc.sub_type);
            }
            if (desc.title != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, desc.title);
            }
            if (desc.sort != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 4, desc.sort);
            }
            if (desc.background_img != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, desc.background_img);
            }
            if (desc.button_img != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, desc.button_img);
            }
            if (desc.button_text != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, desc.button_text);
            }
            protoWriter.writeBytes(desc.unknownFields());
        }

        public Desc decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.type(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.sub_type(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.title(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.sort(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 5:
                            builder.background_img(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 6:
                            builder.button_img(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 7:
                            builder.button_text(ProtoAdapter.STRING.decode(protoReader));
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

        public Desc redact(Desc desc) {
            Builder newBuilder = desc.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
