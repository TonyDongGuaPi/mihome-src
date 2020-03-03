package com.mi.global.shop.model.cod;

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

public final class Result extends Message<Result, Builder> {
    public static final ProtoAdapter<Result> ADAPTER = new ProtoAdapter_Result();
    public static final Boolean DEFAULT_REFRESH = false;
    public static final String DEFAULT_URL = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 2)
    public final Boolean refresh;
    @WireField(adapter = "com.mi.global.shop.model.cod.Support#ADAPTER", tag = 3)
    public final Support support;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String url;

    public Result(String str, Boolean bool, Support support2) {
        this(str, bool, support2, ByteString.EMPTY);
    }

    public Result(String str, Boolean bool, Support support2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.url = str;
        this.refresh = bool;
        this.support = support2;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.url = this.url;
        builder.refresh = this.refresh;
        builder.support = this.support;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Result)) {
            return false;
        }
        Result result = (Result) obj;
        if (!Internal.equals(unknownFields(), result.unknownFields()) || !Internal.equals(this.url, result.url) || !Internal.equals(this.refresh, result.refresh) || !Internal.equals(this.support, result.support)) {
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
        int hashCode = ((((unknownFields().hashCode() * 37) + (this.url != null ? this.url.hashCode() : 0)) * 37) + (this.refresh != null ? this.refresh.hashCode() : 0)) * 37;
        if (this.support != null) {
            i2 = this.support.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.url != null) {
            sb.append(", url=");
            sb.append(this.url);
        }
        if (this.refresh != null) {
            sb.append(", refresh=");
            sb.append(this.refresh);
        }
        if (this.support != null) {
            sb.append(", support=");
            sb.append(this.support);
        }
        StringBuilder replace = sb.replace(0, 2, "Result{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Result, Builder> {
        public Boolean refresh;
        public Support support;
        public String url;

        public Builder url(String str) {
            this.url = str;
            return this;
        }

        public Builder refresh(Boolean bool) {
            this.refresh = bool;
            return this;
        }

        public Builder support(Support support2) {
            this.support = support2;
            return this;
        }

        public Result build() {
            return new Result(this.url, this.refresh, this.support, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Result extends ProtoAdapter<Result> {
        ProtoAdapter_Result() {
            super(FieldEncoding.LENGTH_DELIMITED, Result.class);
        }

        public int encodedSize(Result result) {
            int i = 0;
            int encodedSizeWithTag = (result.url != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, result.url) : 0) + (result.refresh != null ? ProtoAdapter.BOOL.encodedSizeWithTag(2, result.refresh) : 0);
            if (result.support != null) {
                i = Support.ADAPTER.encodedSizeWithTag(3, result.support);
            }
            return encodedSizeWithTag + i + result.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, Result result) throws IOException {
            if (result.url != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, result.url);
            }
            if (result.refresh != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 2, result.refresh);
            }
            if (result.support != null) {
                Support.ADAPTER.encodeWithTag(protoWriter, 3, result.support);
            }
            protoWriter.writeBytes(result.unknownFields());
        }

        public Result decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.url(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.refresh(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 3:
                            builder.support(Support.ADAPTER.decode(protoReader));
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

        public Result redact(Result result) {
            Builder newBuilder = result.newBuilder();
            if (newBuilder.support != null) {
                newBuilder.support = Support.ADAPTER.redact(newBuilder.support);
            }
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
