package com.mi.global.shop.newmodel.user.address;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewSetDefaultAddressResult extends BaseResult {
    @SerializedName("data")
    public NewSetDefaultAddressData data;

    public static NewSetDefaultAddressResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewSetDefaultAddressResult decode(ProtoReader protoReader) throws IOException {
        NewSetDefaultAddressResult newSetDefaultAddressResult = new NewSetDefaultAddressResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newSetDefaultAddressResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newSetDefaultAddressResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newSetDefaultAddressResult.data = NewSetDefaultAddressData.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newSetDefaultAddressResult;
            }
        }
    }
}
