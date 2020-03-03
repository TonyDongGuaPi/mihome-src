package com.mi.global.shop.newmodel.sync;

import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewVersionInfoNote {
    public String desc;

    public static NewVersionInfoNote decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewVersionInfoNote decode(ProtoReader protoReader) throws IOException {
        NewVersionInfoNote newVersionInfoNote = new NewVersionInfoNote();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return newVersionInfoNote;
            } else if (nextTag != 1) {
                protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
            } else {
                newVersionInfoNote.desc = ProtoAdapter.STRING.decode(protoReader);
            }
        }
    }
}
