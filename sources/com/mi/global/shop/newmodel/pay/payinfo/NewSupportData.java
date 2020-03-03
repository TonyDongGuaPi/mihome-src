package com.mi.global.shop.newmodel.pay.payinfo;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewSupportData {
    @SerializedName("can_cod")
    public int can_cod;
    @SerializedName("can_onlinepay")
    public int can_onlinepay;
    @SerializedName("cod_message")
    public String cod_message;
    @SerializedName("codstatus")
    public String codstatus;

    public static NewSupportData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewSupportData decode(ProtoReader protoReader) throws IOException {
        NewSupportData newSupportData = new NewSupportData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newSupportData.can_cod = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newSupportData.can_onlinepay = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 3:
                        newSupportData.codstatus = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        newSupportData.cod_message = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newSupportData;
            }
        }
    }
}
