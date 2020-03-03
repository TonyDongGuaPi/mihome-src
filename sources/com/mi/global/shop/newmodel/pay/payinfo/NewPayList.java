package com.mi.global.shop.newmodel.pay.payinfo;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class NewPayList {
    @SerializedName("cards")
    public ArrayList<String> cards = new ArrayList<>();
    @SerializedName("emi")
    public ArrayList<NewEmi> emi = new ArrayList<>();
    @SerializedName("netbank")
    public NewNetBank netbank;
    @SerializedName("wallet")
    public NewWallet wallet;

    public static NewPayList decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewPayList decode(ProtoReader protoReader) throws IOException {
        NewPayList newPayList = new NewPayList();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newPayList.emi.add(NewEmi.decode(protoReader));
                        break;
                    case 2:
                        newPayList.cards.add(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 3:
                        newPayList.netbank = NewNetBank.decode(protoReader);
                        break;
                    case 4:
                        newPayList.wallet = NewWallet.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newPayList;
            }
        }
    }
}
