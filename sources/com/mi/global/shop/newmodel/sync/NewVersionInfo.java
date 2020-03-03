package com.mi.global.shop.newmodel.sync;

import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class NewVersionInfo {
    public boolean forceUpdate;
    public String hotfixUrl;
    public String md5;
    public ArrayList<NewVersionInfoNote> notes = new ArrayList<>();
    public String patchUrl;
    public String url;
    public String version;
    public int versionCode;

    public static NewVersionInfo decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewVersionInfo decode(ProtoReader protoReader) throws IOException {
        NewVersionInfo newVersionInfo = new NewVersionInfo();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newVersionInfo.version = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newVersionInfo.notes.add(NewVersionInfoNote.decode(protoReader));
                        break;
                    case 3:
                        newVersionInfo.url = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        newVersionInfo.forceUpdate = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 5:
                        newVersionInfo.versionCode = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 6:
                        newVersionInfo.md5 = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 7:
                        newVersionInfo.patchUrl = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 8:
                        newVersionInfo.hotfixUrl = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newVersionInfo;
            }
        }
    }
}
