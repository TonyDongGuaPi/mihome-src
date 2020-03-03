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

public final class Note extends Message<Note, Builder> {
    public static final ProtoAdapter<Note> ADAPTER = new ProtoAdapter_Note();
    public static final String DEFAULT_DESC = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String desc;

    public Note(String str) {
        this(str, ByteString.EMPTY);
    }

    public Note(String str, ByteString byteString) {
        super(ADAPTER, byteString);
        this.desc = str;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.desc = this.desc;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Note)) {
            return false;
        }
        Note note = (Note) obj;
        if (!Internal.equals(unknownFields(), note.unknownFields()) || !Internal.equals(this.desc, note.desc)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = (unknownFields().hashCode() * 37) + (this.desc != null ? this.desc.hashCode() : 0);
        this.hashCode = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.desc != null) {
            sb.append(", desc=");
            sb.append(this.desc);
        }
        StringBuilder replace = sb.replace(0, 2, "Note{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<Note, Builder> {
        public String desc;

        public Builder desc(String str) {
            this.desc = str;
            return this;
        }

        public Note build() {
            return new Note(this.desc, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_Note extends ProtoAdapter<Note> {
        ProtoAdapter_Note() {
            super(FieldEncoding.LENGTH_DELIMITED, Note.class);
        }

        public int encodedSize(Note note) {
            return (note.desc != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, note.desc) : 0) + note.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, Note note) throws IOException {
            if (note.desc != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, note.desc);
            }
            protoWriter.writeBytes(note.unknownFields());
        }

        public Note decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag == -1) {
                    protoReader.endMessage(beginMessage);
                    return builder.build();
                } else if (nextTag != 1) {
                    FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                    builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                } else {
                    builder.desc(ProtoAdapter.STRING.decode(protoReader));
                }
            }
        }

        public Note redact(Note note) {
            Builder newBuilder = note.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
