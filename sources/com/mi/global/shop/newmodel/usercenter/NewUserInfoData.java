package com.mi.global.shop.newmodel.usercenter;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.model.UserCentralListData;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class NewUserInfoData {
    @SerializedName("userinfo")
    public NewUserInfoData jsonUserInfoData;
    @SerializedName("loyaltyInfo")
    public LoyaltyInfoData loyaltyInfo;
    @SerializedName("not_pay_order_count")
    public int not_pay_order_count;
    @SerializedName("not_used_coupon_count")
    public int not_used_coupon_count;
    @SerializedName("returns_count")
    public int returns_count;
    @SerializedName("not_comment_item_count")
    public int reviews_count;
    @SerializedName("ship_count")
    public int ship_count;
    @SerializedName("unread")
    public int unread;
    @SerializedName("userCenterInfo")
    public ArrayList<UserCentralListData> userCenterInfo = new ArrayList<>();

    public static NewUserInfoData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewUserInfoData decode(ProtoReader protoReader) throws IOException {
        NewUserInfoData newUserInfoData = new NewUserInfoData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newUserInfoData.not_pay_order_count = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newUserInfoData.not_used_coupon_count = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 3:
                        newUserInfoData.ship_count = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 4:
                        newUserInfoData.returns_count = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 5:
                        newUserInfoData.reviews_count = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 6:
                        newUserInfoData.unread = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 7:
                        newUserInfoData.loyaltyInfo = LoyaltyInfoData.decode(protoReader);
                        break;
                    case 8:
                        newUserInfoData.userCenterInfo.add(UserCentralListData.decode(protoReader));
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newUserInfoData;
            }
        }
    }

    public void resetData() {
        this.not_pay_order_count = 0;
        this.not_used_coupon_count = 0;
        this.ship_count = 0;
        this.returns_count = 0;
        this.reviews_count = 0;
        this.unread = 0;
    }
}
