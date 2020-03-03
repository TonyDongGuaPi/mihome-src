package com.mi.global.shop.newmodel.expresstrack;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewExpressTraceItem {
    @SerializedName("city")
    public String city;
    @SerializedName("time")
    public String time;
    @SerializedName("track")
    public String track;

    public static NewExpressTraceItem decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewExpressTraceItem decode(ProtoReader protoReader) throws IOException {
        NewExpressTraceItem newExpressTraceItem = new NewExpressTraceItem();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newExpressTraceItem.time = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newExpressTraceItem.city = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newExpressTraceItem.track = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newExpressTraceItem;
            }
        }
    }
}
