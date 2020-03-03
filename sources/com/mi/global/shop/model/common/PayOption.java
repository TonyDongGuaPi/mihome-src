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
import java.util.List;
import okio.ByteString;

public final class PayOption extends Message<PayOption, Builder> {
    public static final ProtoAdapter<PayOption> ADAPTER = new ProtoAdapter_PayOption();
    public static final Boolean DEFAULT_ENABLE = false;
    public static final String DEFAULT_IMAGE = "";
    public static final String DEFAULT_INFO = "";
    public static final String DEFAULT_INFOTITLE = "";
    public static final String DEFAULT_KEY = "";
    public static final String DEFAULT_SUBTITLE = "";
    public static final String DEFAULT_TITLE = "";
    public static final String DEFAULT_UPIOPTIONS = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 3)
    public final Boolean enable;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String image;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String info;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String infotitle;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String key;
    @WireField(adapter = "com.mi.global.shop.model.common.PayOption#ADAPTER", label = WireField.Label.REPEATED, tag = 8)
    public final List<PayOption> subOptions;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String subtitle;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String title;

    public PayOption(String str, String str2, Boolean bool, String str3, String str4, String str5, String str6, List<PayOption> list) {
        this(str, str2, bool, str3, str4, str5, str6, list, ByteString.EMPTY);
    }

    public PayOption(String str, String str2, Boolean bool, String str3, String str4, String str5, String str6, List<PayOption> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.title = str;
        this.subtitle = str2;
        this.enable = bool;
        this.infotitle = str3;
        this.info = str4;
        this.image = str5;
        this.key = str6;
        this.subOptions = Internal.immutableCopyOf("subOptions", list);
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.title = this.title;
        builder.subtitle = this.subtitle;
        builder.enable = this.enable;
        builder.infotitle = this.infotitle;
        builder.info = this.info;
        builder.image = this.image;
        builder.key = this.key;
        builder.subOptions = Internal.copyOf("subOptions", this.subOptions);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PayOption)) {
            return false;
        }
        PayOption payOption = (PayOption) obj;
        if (!Internal.equals(unknownFields(), payOption.unknownFields()) || !Internal.equals(this.title, payOption.title) || !Internal.equals(this.subtitle, payOption.subtitle) || !Internal.equals(this.enable, payOption.enable) || !Internal.equals(this.infotitle, payOption.infotitle) || !Internal.equals(this.info, payOption.info) || !Internal.equals(this.image, payOption.image) || !Internal.equals(this.key, payOption.key) || !Internal.equals(this.subOptions, payOption.subOptions)) {
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
        int hashCode = ((((((((((((unknownFields().hashCode() * 37) + (this.title != null ? this.title.hashCode() : 0)) * 37) + (this.subtitle != null ? this.subtitle.hashCode() : 0)) * 37) + (this.enable != null ? this.enable.hashCode() : 0)) * 37) + (this.infotitle != null ? this.infotitle.hashCode() : 0)) * 37) + (this.info != null ? this.info.hashCode() : 0)) * 37) + (this.image != null ? this.image.hashCode() : 0)) * 37;
        if (this.key != null) {
            i2 = this.key.hashCode();
        }
        int hashCode2 = ((hashCode + i2) * 37) + (this.subOptions != null ? this.subOptions.hashCode() : 1);
        this.hashCode = hashCode2;
        return hashCode2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.title != null) {
            sb.append(", title=");
            sb.append(this.title);
        }
        if (this.subtitle != null) {
            sb.append(", subtitle=");
            sb.append(this.subtitle);
        }
        if (this.enable != null) {
            sb.append(", enable=");
            sb.append(this.enable);
        }
        if (this.infotitle != null) {
            sb.append(", infotitle=");
            sb.append(this.infotitle);
        }
        if (this.info != null) {
            sb.append(", info=");
            sb.append(this.info);
        }
        if (this.image != null) {
            sb.append(", image=");
            sb.append(this.image);
        }
        if (this.key != null) {
            sb.append(", key=");
            sb.append(this.key);
        }
        if (this.subOptions != null) {
            sb.append(", subOptions=");
            sb.append(this.subOptions);
        }
        StringBuilder replace = sb.replace(0, 2, "PayOption{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<PayOption, Builder> {
        public Boolean enable;
        public String image;
        public String info;
        public String infotitle;
        public String key;
        public List<PayOption> subOptions = Internal.newMutableList();
        public String subtitle;
        public String title;

        public Builder title(String str) {
            this.title = str;
            return this;
        }

        public Builder subtitle(String str) {
            this.subtitle = str;
            return this;
        }

        public Builder enable(Boolean bool) {
            this.enable = bool;
            return this;
        }

        public Builder infotitle(String str) {
            this.infotitle = str;
            return this;
        }

        public Builder info(String str) {
            this.info = str;
            return this;
        }

        public Builder image(String str) {
            this.image = str;
            return this;
        }

        public Builder key(String str) {
            this.key = str;
            return this;
        }

        public Builder subOptions(List<PayOption> list) {
            Internal.checkElementsNotNull(list);
            this.subOptions = list;
            return this;
        }

        public PayOption build() {
            return new PayOption(this.title, this.subtitle, this.enable, this.infotitle, this.info, this.image, this.key, this.subOptions, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_PayOption extends ProtoAdapter<PayOption> {
        ProtoAdapter_PayOption() {
            super(FieldEncoding.LENGTH_DELIMITED, PayOption.class);
        }

        public int encodedSize(PayOption payOption) {
            int i = 0;
            int encodedSizeWithTag = (payOption.title != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, payOption.title) : 0) + (payOption.subtitle != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, payOption.subtitle) : 0) + (payOption.enable != null ? ProtoAdapter.BOOL.encodedSizeWithTag(3, payOption.enable) : 0) + (payOption.infotitle != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, payOption.infotitle) : 0) + (payOption.info != null ? ProtoAdapter.STRING.encodedSizeWithTag(5, payOption.info) : 0) + (payOption.image != null ? ProtoAdapter.STRING.encodedSizeWithTag(6, payOption.image) : 0);
            if (payOption.key != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(7, payOption.key);
            }
            return encodedSizeWithTag + i + PayOption.ADAPTER.asRepeated().encodedSizeWithTag(8, payOption.subOptions) + payOption.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, PayOption payOption) throws IOException {
            if (payOption.title != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, payOption.title);
            }
            if (payOption.subtitle != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, payOption.subtitle);
            }
            if (payOption.enable != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 3, payOption.enable);
            }
            if (payOption.infotitle != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, payOption.infotitle);
            }
            if (payOption.info != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, payOption.info);
            }
            if (payOption.image != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, payOption.image);
            }
            if (payOption.key != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, payOption.key);
            }
            if (payOption.subOptions != null) {
                PayOption.ADAPTER.asRepeated().encodeWithTag(protoWriter, 8, payOption.subOptions);
            }
            protoWriter.writeBytes(payOption.unknownFields());
        }

        public PayOption decode(ProtoReader protoReader) throws IOException {
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
                            builder.subtitle(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.enable(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 4:
                            builder.infotitle(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 5:
                            builder.info(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 6:
                            builder.image(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 7:
                            builder.key(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 8:
                            builder.subOptions.add(PayOption.ADAPTER.decode(protoReader));
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

        public PayOption redact(PayOption payOption) {
            Builder newBuilder = payOption.newBuilder();
            Internal.redactElements(newBuilder.subOptions, PayOption.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
