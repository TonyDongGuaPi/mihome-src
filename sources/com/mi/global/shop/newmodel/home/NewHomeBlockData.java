package com.mi.global.shop.newmodel.home;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class NewHomeBlockData {
    @SerializedName("accessorieslist")
    public ArrayList<NewHomeBlockInfo> mAccessorySections = new ArrayList<>();
    @SerializedName("index_ad")
    public ArrayList<NewHomeBlockInfo> mAdSections = new ArrayList<>();
    @SerializedName("gamerecommend")
    public ArrayList<NewHomeBlockInfo> mGameSections = new ArrayList<>();
    @SerializedName("hdrecommend")
    public ArrayList<NewHomeBlockInfo> mHDSections = new ArrayList<>();
    @SerializedName("suspendedBall")
    public ArrayList<NewHomeBlockInfo> mHEntrance = new ArrayList<>();
    @SerializedName("gallery")
    public ArrayList<NewHomeBlockInfo> mHeaderGallery = new ArrayList<>();
    @SerializedName("products")
    public ArrayList<NewHomeBlockInfo> mHomeSections = new ArrayList<>();
    @SerializedName("hotbuy")
    public ArrayList<NewHomeBlockInfo> mHotBuySections = new ArrayList<>();
    @SerializedName("phonelist")
    public ArrayList<NewHomeBlockInfo> mPhoneSections = new ArrayList<>();
    @SerializedName("productrecommend")
    public ArrayList<NewHomeBlockInfo> mProductSections = new ArrayList<>();

    public NewHomeBlockInfo getHomeSectionBySort(int i) {
        if (this.mHomeSections == null || this.mHomeSections.size() <= 0) {
            return null;
        }
        for (int i2 = 0; i2 < this.mHomeSections.size(); i2++) {
            NewHomeBlockInfo newHomeBlockInfo = this.mHomeSections.get(i2);
            if (newHomeBlockInfo.mDesc.mSort == i) {
                return newHomeBlockInfo;
            }
        }
        return null;
    }

    public static NewHomeBlockData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewHomeBlockData decode(ProtoReader protoReader) throws IOException {
        NewHomeBlockData newHomeBlockData = new NewHomeBlockData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newHomeBlockData.mHeaderGallery.add(NewHomeBlockInfo.decode(protoReader));
                        break;
                    case 2:
                        newHomeBlockData.mHomeSections.add(NewHomeBlockInfo.decode(protoReader));
                        break;
                    case 3:
                        newHomeBlockData.mAdSections.add(NewHomeBlockInfo.decode(protoReader));
                        break;
                    case 4:
                        newHomeBlockData.mHotBuySections.add(NewHomeBlockInfo.decode(protoReader));
                        break;
                    case 5:
                        newHomeBlockData.mGameSections.add(NewHomeBlockInfo.decode(protoReader));
                        break;
                    case 6:
                        newHomeBlockData.mPhoneSections.add(NewHomeBlockInfo.decode(protoReader));
                        break;
                    case 7:
                        newHomeBlockData.mAccessorySections.add(NewHomeBlockInfo.decode(protoReader));
                        break;
                    case 8:
                        newHomeBlockData.mProductSections.add(NewHomeBlockInfo.decode(protoReader));
                        break;
                    case 9:
                        newHomeBlockData.mHDSections.add(NewHomeBlockInfo.decode(protoReader));
                        break;
                    case 10:
                        newHomeBlockData.mHEntrance.add(NewHomeBlockInfo.decode(protoReader));
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newHomeBlockData;
            }
        }
    }
}
