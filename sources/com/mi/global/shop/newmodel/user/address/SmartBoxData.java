package com.mi.global.shop.newmodel.user.address;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import okio.Buffer;

public class SmartBoxData implements Serializable {
    private static final long serialVersionUID = 3124916604139107833L;
    @SerializedName("defaultid")
    public String defaultid;
    @SerializedName("explain")
    public String explain;
    @SerializedName("hint")
    public String hint;
    @SerializedName("list")
    public ArrayList<SmartDetailItemData> smartDetailListData = new ArrayList<>();
    @SerializedName("title")
    public String title;

    public static SmartBoxData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static SmartBoxData decode(ProtoReader protoReader) throws IOException {
        SmartBoxData smartBoxData = new SmartBoxData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        smartBoxData.hint = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        smartBoxData.explain = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        smartBoxData.smartDetailListData.add(SmartDetailItemData.decode(protoReader));
                        break;
                    case 4:
                        smartBoxData.defaultid = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 5:
                        smartBoxData.title = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return smartBoxData;
            }
        }
    }
}
