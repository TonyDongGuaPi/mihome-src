package com.mi.global.shop.newmodel.cart;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class NewCartBargainItem {
    @SerializedName("actId")
    public String actId;
    @SerializedName("bargainGoodsId")
    public String bargainGoodsId;
    @SerializedName("bargainItemId")
    public String bargainItemId;
    @SerializedName("bargainPrice_txt")
    public String bargainPrice_txt;
    @SerializedName("bargain_name")
    public String bargain_name;
    @SerializedName("checked")
    public boolean checked;
    @SerializedName("goodsId")
    public ArrayList<String> goodsId = new ArrayList<>();
    @SerializedName("image_url")
    public String image_url;
    @SerializedName("json_data")
    public String json_data;
    @SerializedName("product_id")
    public String product_id;
    @SerializedName("salePrice")
    public String salePrice;
    @SerializedName("selecInfo")
    public ArrayList<NewCartSelectInfo> selecInfo = new ArrayList<>();
    @SerializedName("selectable")
    public boolean selectable;

    public static NewCartBargainItem decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewCartBargainItem decode(ProtoReader protoReader) throws IOException {
        NewCartBargainItem newCartBargainItem = new NewCartBargainItem();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return newCartBargainItem;
            } else if (nextTag == 5) {
                newCartBargainItem.actId = ProtoAdapter.STRING.decode(protoReader);
            } else if (nextTag == 11) {
                newCartBargainItem.product_id = ProtoAdapter.STRING.decode(protoReader);
            } else if (nextTag != 14) {
                switch (nextTag) {
                    case 1:
                        newCartBargainItem.goodsId.add(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        newCartBargainItem.salePrice = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        switch (nextTag) {
                            case 37:
                                newCartBargainItem.selectable = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                                break;
                            case 38:
                                newCartBargainItem.selecInfo.add(NewCartSelectInfo.decode(protoReader));
                                break;
                            case 39:
                                newCartBargainItem.bargain_name = ProtoAdapter.STRING.decode(protoReader);
                                break;
                            case 40:
                                newCartBargainItem.checked = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                                break;
                            case 41:
                                newCartBargainItem.bargainPrice_txt = ProtoAdapter.STRING.decode(protoReader);
                                break;
                            case 42:
                                newCartBargainItem.bargainGoodsId = ProtoAdapter.STRING.decode(protoReader);
                                break;
                            case 43:
                                newCartBargainItem.bargainItemId = ProtoAdapter.STRING.decode(protoReader);
                                break;
                            case 44:
                                newCartBargainItem.json_data = ProtoAdapter.STRING.decode(protoReader);
                                break;
                            default:
                                protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                                break;
                        }
                }
            } else {
                newCartBargainItem.image_url = ProtoAdapter.STRING.decode(protoReader);
            }
        }
    }
}
