package com.mi.global.shop.newmodel.notice;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewNoticeData {
    public static final String NOINFO = "0";
    public static final String ORDERINFO = "2";
    public static final String URGENTINFO = "1";
    @SerializedName("content")
    public String content;
    @SerializedName("type")
    public String type;
    @SerializedName("url")
    public String url;

    public static NewNoticeData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewNoticeData decode(ProtoReader protoReader) throws IOException {
        NewNoticeData newNoticeData = new NewNoticeData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newNoticeData.type = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newNoticeData.content = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newNoticeData.url = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newNoticeData;
            }
        }
    }
}
