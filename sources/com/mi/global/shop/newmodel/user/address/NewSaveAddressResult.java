package com.mi.global.shop.newmodel.user.address;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewSaveAddressResult extends BaseResult {
    @SerializedName("data")
    public NewSaveAddressData data;

    public static NewSaveAddressResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewSaveAddressResult decode(ProtoReader protoReader) throws IOException {
        NewSaveAddressResult newSaveAddressResult = new NewSaveAddressResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newSaveAddressResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newSaveAddressResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newSaveAddressResult.data = NewSaveAddressData.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newSaveAddressResult;
            }
        }
    }
}
