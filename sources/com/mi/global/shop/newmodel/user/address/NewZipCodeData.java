package com.mi.global.shop.newmodel.user.address;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewZipCodeData {
    @SerializedName("can_cod")
    public int can_cod;
    @SerializedName("can_delivery")
    public int can_delivery;
    @SerializedName("citys")
    public String citys;
    @SerializedName("enable")
    public int enable;
    @SerializedName("errors")
    public String errors;
    @SerializedName("limit")
    public String limit;
    @SerializedName("limit_cod")
    public String limit_cod;
    @SerializedName("parent_id")
    public String parent_id;
    @SerializedName("region_id")
    public String region_id;
    @SerializedName("region_name")
    public String region_name;
    @SerializedName("warehouse")
    public String warehouse;

    public static NewZipCodeData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewZipCodeData decode(ProtoReader protoReader) throws IOException {
        NewZipCodeData newZipCodeData = new NewZipCodeData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newZipCodeData.region_id = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newZipCodeData.parent_id = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newZipCodeData.region_name = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        newZipCodeData.can_cod = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 5:
                        newZipCodeData.can_delivery = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 6:
                        newZipCodeData.enable = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 8:
                        newZipCodeData.limit = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 9:
                        newZipCodeData.limit_cod = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 10:
                        newZipCodeData.citys = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 11:
                        newZipCodeData.errors = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 12:
                        newZipCodeData.warehouse = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newZipCodeData;
            }
        }
    }
}
