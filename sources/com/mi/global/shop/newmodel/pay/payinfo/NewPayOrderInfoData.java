package com.mi.global.shop.newmodel.pay.payinfo;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class NewPayOrderInfoData {
    @SerializedName("address")
    public String address;
    @SerializedName("consignee")
    public String consignee;
    @SerializedName("goods_amount")
    public String goods_amount;
    @SerializedName("orderItems")
    public ArrayList<NewPayOrderItemData> orderItems = new ArrayList<>();
    @SerializedName("order_id")
    public String order_id;
    @SerializedName("original_price")
    public String original_price;
    @SerializedName("reduce_price")
    public String reduce_price;
    @SerializedName("remaining_time")
    public String remaining_time;
    @SerializedName("shipment_expense")
    public String shipment_expense;
    @SerializedName("tel")
    public String tel;
    @SerializedName("zipcode")
    public String zipcode;

    public static NewPayOrderInfoData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewPayOrderInfoData decode(ProtoReader protoReader) throws IOException {
        NewPayOrderInfoData newPayOrderInfoData = new NewPayOrderInfoData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return newPayOrderInfoData;
            } else if (nextTag != 1) {
                switch (nextTag) {
                    case 3:
                        newPayOrderInfoData.consignee = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        newPayOrderInfoData.address = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 5:
                        newPayOrderInfoData.zipcode = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 6:
                        newPayOrderInfoData.tel = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 7:
                        newPayOrderInfoData.goods_amount = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 8:
                        newPayOrderInfoData.original_price = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 9:
                        newPayOrderInfoData.shipment_expense = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 10:
                        newPayOrderInfoData.reduce_price = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 11:
                        newPayOrderInfoData.orderItems.add(NewPayOrderItemData.decode(protoReader));
                        break;
                    case 12:
                        newPayOrderInfoData.remaining_time = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                newPayOrderInfoData.order_id = ProtoAdapter.STRING.decode(protoReader);
            }
        }
    }
}
