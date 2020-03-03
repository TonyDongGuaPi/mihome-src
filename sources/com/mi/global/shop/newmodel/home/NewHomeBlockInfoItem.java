package com.mi.global.shop.newmodel.home;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewHomeBlockInfoItem {
    @SerializedName("full_price")
    public String mFullPrice;
    @SerializedName("icon_content")
    public String mIconContent;
    @SerializedName("icon_img")
    public String mIconImg;
    @SerializedName("icon_type")
    public String mIconType;
    @SerializedName("height")
    public String mImageHeight;
    @SerializedName("thumb")
    public String mImageUrl;
    @SerializedName("url")
    public String mPath;
    @SerializedName("desc")
    public String mProductMore;
    @SerializedName("title")
    public String mProductName;
    @SerializedName("product_price")
    public String mProductPrice;
    @SerializedName("product_tag")
    public String mProductTag;
    @SerializedName("t_full_price")
    public String mTFullPrice;
    @SerializedName("t_product_price")
    public String mTProductPrice;
    @SerializedName("viewId")
    public String mViewId;
    @SerializedName("target")
    public String target;

    public String getImageUrl() {
        if (TextUtils.isEmpty(this.mImageUrl)) {
            return this.mImageUrl;
        }
        if (this.mImageUrl.startsWith("http:") || this.mImageUrl.startsWith("https:")) {
            return this.mImageUrl;
        }
        return "http:" + this.mImageUrl;
    }

    public static NewHomeBlockInfoItem decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewHomeBlockInfoItem decode(ProtoReader protoReader) throws IOException {
        NewHomeBlockInfoItem newHomeBlockInfoItem = new NewHomeBlockInfoItem();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newHomeBlockInfoItem.mProductName = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newHomeBlockInfoItem.mPath = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newHomeBlockInfoItem.mImageUrl = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        newHomeBlockInfoItem.mProductPrice = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 5:
                        newHomeBlockInfoItem.mProductMore = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 6:
                        newHomeBlockInfoItem.mTProductPrice = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 7:
                        newHomeBlockInfoItem.mImageHeight = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 8:
                        newHomeBlockInfoItem.mFullPrice = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 9:
                        newHomeBlockInfoItem.mTFullPrice = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 10:
                        newHomeBlockInfoItem.target = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 11:
                        newHomeBlockInfoItem.mIconType = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 12:
                        newHomeBlockInfoItem.mIconContent = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 13:
                        newHomeBlockInfoItem.mIconImg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 14:
                        newHomeBlockInfoItem.mViewId = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 15:
                        newHomeBlockInfoItem.mProductTag = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newHomeBlockInfoItem;
            }
        }
    }
}
