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

public final class VerInfo extends Message<VerInfo, Builder> {
    public static final ProtoAdapter<VerInfo> ADAPTER = new ProtoAdapter_VerInfo();
    public static final Boolean DEFAULT_FORCEUPDATE = false;
    public static final String DEFAULT_MD5 = "";
    public static final String DEFAULT_PATCHURL = "";
    public static final String DEFAULT_URL = "";
    public static final String DEFAULT_VERSION = "";
    public static final Integer DEFAULT_VERSIONCODE = 0;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 4)
    public final Boolean forceUpdate;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String md5;
    @WireField(adapter = "com.mi.global.shop.model.app.Note#ADAPTER", label = WireField.Label.REPEATED, tag = 2)
    public final List<Note> notes;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String patchUrl;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String url;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String version;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 5)
    public final Integer versionCode;

    public VerInfo(String str, List<Note> list, String str2, Boolean bool, Integer num, String str3, String str4) {
        this(str, list, str2, bool, num, str3, str4, ByteString.EMPTY);
    }

    public VerInfo(String str, List<Note> list, String str2, Boolean bool, Integer num, String str3, String str4, ByteString byteString) {
        super(ADAPTER, byteString);
        this.version = str;
        this.notes = Internal.immutableCopyOf("notes", list);
        this.url = str2;
        this.forceUpdate = bool;
        this.versionCode = num;
        this.md5 = str3;
        this.patchUrl = str4;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.version = this.version;
        builder.notes = Internal.copyOf("notes", this.notes);
        builder.url = this.url;
        builder.forceUpdate = this.forceUpdate;
        builder.versionCode = this.versionCode;
        builder.md5 = this.md5;
        builder.patchUrl = this.patchUrl;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VerInfo)) {
            return false;
        }
        VerInfo verInfo = (VerInfo) obj;
        if (!Internal.equals(unknownFields(), verInfo.unknownFields()) || !Internal.equals(this.version, verInfo.version) || !Internal.equals(this.notes, verInfo.notes) || !Internal.equals(this.url, verInfo.url) || !Internal.equals(this.forceUpdate, verInfo.forceUpdate) || !Internal.equals(this.versionCode, verInfo.versionCode) || !Internal.equals(this.md5, verInfo.md5) || !Internal.equals(this.patchUrl, verInfo.patchUrl)) {
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
        int hashCode = ((((((((((((unknownFields().hashCode() * 37) + (this.version != null ? this.version.hashCode() : 0)) * 37) + (this.notes != null ? this.notes.hashCode() : 1)) * 37) + (this.url != null ? this.url.hashCode() : 0)) * 37) + (this.forceUpdate != null ? this.forceUpdate.hashCode() : 0)) * 37) + (this.versionCode != null ? this.versionCode.hashCode() : 0)) * 37) + (this.md5 != null ? this.md5.hashCode() : 0)) * 37;
        if (this.patchUrl != null) {
            i2 = this.patchUrl.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.version != null) {
            sb.append(", version=");
            sb.append(this.version);
        }
        if (this.notes != null) {
            sb.append(", notes=");
            sb.append(this.notes);
        }
        if (this.url != null) {
            sb.append(", url=");
            sb.append(this.url);
        }
        if (this.forceUpdate != null) {
            sb.append(", forceUpdate=");
            sb.append(this.forceUpdate);
        }
        if (this.versionCode != null) {
            sb.append(", versionCode=");
            sb.append(this.versionCode);
        }
        if (this.md5 != null) {
            sb.append(", md5=");
            sb.append(this.md5);
        }
        if (this.patchUrl != null) {
            sb.append(", patchUrl=");
            sb.append(this.patchUrl);
        }
        StringBuilder replace = sb.replace(0, 2, "VerInfo{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<VerInfo, Builder> {
        public Boolean forceUpdate;
        public String md5;
        public List<Note> notes = Internal.newMutableList();
        public String patchUrl;
        public String url;
        public String version;
        public Integer versionCode;

        public Builder version(String str) {
            this.version = str;
            return this;
        }

        public Builder notes(List<Note> list) {
            Internal.checkElementsNotNull(list);
            this.notes = list;
            return this;
        }

        public Builder url(String str) {
            this.url = str;
            return this;
        }

        public Builder forceUpdate(Boolean bool) {
            this.forceUpdate = bool;
            return this;
        }

        public Builder versionCode(Integer num) {
            this.versionCode = num;
            return this;
        }

        public Builder md5(String str) {
            this.md5 = str;
            return this;
        }

        public Builder patchUrl(String str) {
            this.patchUrl = str;
            return this;
        }

        public VerInfo build() {
            return new VerInfo(this.version, this.notes, this.url, this.forceUpdate, this.versionCode, this.md5, this.patchUrl, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_VerInfo extends ProtoAdapter<VerInfo> {
        ProtoAdapter_VerInfo() {
            super(FieldEncoding.LENGTH_DELIMITED, VerInfo.class);
        }

        public int encodedSize(VerInfo verInfo) {
            int i = 0;
            int encodedSizeWithTag = (verInfo.version != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, verInfo.version) : 0) + Note.ADAPTER.asRepeated().encodedSizeWithTag(2, verInfo.notes) + (verInfo.url != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, verInfo.url) : 0) + (verInfo.forceUpdate != null ? ProtoAdapter.BOOL.encodedSizeWithTag(4, verInfo.forceUpdate) : 0) + (verInfo.versionCode != null ? ProtoAdapter.INT32.encodedSizeWithTag(5, verInfo.versionCode) : 0) + (verInfo.md5 != null ? ProtoAdapter.STRING.encodedSizeWithTag(6, verInfo.md5) : 0);
            if (verInfo.patchUrl != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(7, verInfo.patchUrl);
            }
            return encodedSizeWithTag + i + verInfo.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, VerInfo verInfo) throws IOException {
            if (verInfo.version != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, verInfo.version);
            }
            if (verInfo.notes != null) {
                Note.ADAPTER.asRepeated().encodeWithTag(protoWriter, 2, verInfo.notes);
            }
            if (verInfo.url != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, verInfo.url);
            }
            if (verInfo.forceUpdate != null) {
                ProtoAdapter.BOOL.encodeWithTag(protoWriter, 4, verInfo.forceUpdate);
            }
            if (verInfo.versionCode != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 5, verInfo.versionCode);
            }
            if (verInfo.md5 != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, verInfo.md5);
            }
            if (verInfo.patchUrl != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, verInfo.patchUrl);
            }
            protoWriter.writeBytes(verInfo.unknownFields());
        }

        public VerInfo decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.version(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.notes.add(Note.ADAPTER.decode(protoReader));
                            break;
                        case 3:
                            builder.url(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.forceUpdate(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 5:
                            builder.versionCode(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 6:
                            builder.md5(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 7:
                            builder.patchUrl(ProtoAdapter.STRING.decode(protoReader));
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

        public VerInfo redact(VerInfo verInfo) {
            Builder newBuilder = verInfo.newBuilder();
            Internal.redactElements(newBuilder.notes, Note.ADAPTER);
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
