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

public final class PostFreeData extends Message<PostFreeData, Builder> {
    public static final ProtoAdapter<PostFreeData> ADAPTER = new ProtoAdapter_PostFreeData();
    public static final String DEFAULT_ACTIVITY_NAME = "";
    public static final String DEFAULT_ACTNAME = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String actName;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String activity_name;

    public PostFreeData(String str, String str2) {
        this(str, str2, ByteString.EMPTY);
    }

    public PostFreeData(String str, String str2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.actName = str;
        this.activity_name = str2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.actName = this.actName;
        builder.activity_name = this.activity_name;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PostFreeData)) {
            return false;
        }
        PostFreeData postFreeData = (PostFreeData) obj;
        if (!Internal.equals(unknownFields(), postFreeData.unknownFields()) || !Internal.equals(this.actName, postFreeData.actName) || !Internal.equals(this.activity_name, postFreeData.activity_name)) {
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
        int hashCode = ((unknownFields().hashCode() * 37) + (this.actName != null ? this.actName.hashCode() : 0)) * 37;
        if (this.activity_name != null) {
            i2 = this.activity_name.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.actName != null) {
            sb.append(", actName=");
            sb.append(this.actName);
        }
        if (this.activity_name != null) {
            sb.append(", activity_name=");
            sb.append(this.activity_name);
        }
        StringBuilder replace = sb.replace(0, 2, "PostFreeData{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<PostFreeData, Builder> {
        public String actName;
        public String activity_name;

        public Builder actName(String str) {
            this.actName = str;
            return this;
        }

        public Builder activity_name(String str) {
            this.activity_name = str;
            return this;
        }

        public PostFreeData build() {
            return new PostFreeData(this.actName, this.activity_name, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_PostFreeData extends ProtoAdapter<PostFreeData> {
        ProtoAdapter_PostFreeData() {
            super(FieldEncoding.LENGTH_DELIMITED, PostFreeData.class);
        }

        public int encodedSize(PostFreeData postFreeData) {
            int i = 0;
            int encodedSizeWithTag = postFreeData.actName != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, postFreeData.actName) : 0;
            if (postFreeData.activity_name != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(2, postFreeData.activity_name);
            }
            return encodedSizeWithTag + i + postFreeData.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, PostFreeData postFreeData) throws IOException {
            if (postFreeData.actName != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, postFreeData.actName);
            }
            if (postFreeData.activity_name != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, postFreeData.activity_name);
            }
            protoWriter.writeBytes(postFreeData.unknownFields());
        }

        public PostFreeData decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.actName(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.activity_name(ProtoAdapter.STRING.decode(protoReader));
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

        public PostFreeData redact(PostFreeData postFreeData) {
            Builder newBuilder = postFreeData.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
