package com.mi.global.shop.newmodel.user.address;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class NewAddressData {
    @SerializedName("list")
    public ArrayList<NewAddressItem> list = new ArrayList<>();
    @SerializedName("list")
    public ArrayList<NewRegionItem> region = new ArrayList<>();

    public static NewAddressData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewAddressData decode(ProtoReader protoReader) throws IOException {
        NewAddressData newAddressData = new NewAddressData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newAddressData.list.add(NewAddressItem.decode(protoReader));
                        break;
                    case 2:
                        newAddressData.region.add(NewRegionItem.decode(protoReader));
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newAddressData;
            }
        }
    }
}
