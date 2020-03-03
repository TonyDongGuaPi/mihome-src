package com.mi.global.shop.model;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.usercenter.NewUserInfoData;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class UserCentralData {
    public static final int TYPE_DIVIDER = 4;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_ORDER = 3;
    public static final int TYPE_SETTING = 2;
    public static final int TYPE_TITLE = 0;
    @SerializedName("icon")
    public String icon;
    @SerializedName("icon_desc")
    public String icon_desc;
    @SerializedName("login")
    public boolean login;
    @SerializedName("mDescription")
    public String mDescription = null;
    @SerializedName("mIsEnabled")
    public boolean mIsEnabled = false;
    @SerializedName("sequence")
    public int sequence;
    @SerializedName("tips")
    public String tips;
    @SerializedName("title")
    public String title;
    @SerializedName("type")
    public int type;
    @SerializedName("url")
    public String url;
    public NewUserInfoData userCentralInfo = new NewUserInfoData();

    public UserCentralData() {
    }

    public UserCentralData(String str, int i) {
        this.title = str;
        this.type = i;
        this.tips = "";
    }

    public UserCentralData(String str, String str2, int i) {
        this.icon = str;
        this.title = str2;
        this.type = i;
        this.tips = "";
    }

    public static UserCentralData initSettingData(String str, String str2, boolean z) {
        UserCentralData userCentralData = new UserCentralData(str, 2);
        userCentralData.mDescription = str2;
        userCentralData.mIsEnabled = z;
        return userCentralData;
    }

    public static UserCentralData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static UserCentralData decode(ProtoReader protoReader) throws IOException {
        UserCentralData userCentralData = new UserCentralData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        userCentralData.sequence = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        userCentralData.type = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 3:
                        userCentralData.title = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        userCentralData.tips = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 5:
                        userCentralData.icon = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 6:
                        userCentralData.icon_desc = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 7:
                        userCentralData.url = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 8:
                        userCentralData.login = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return userCentralData;
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserCentralData userCentralData = (UserCentralData) obj;
        if (userCentralData.sequence != this.sequence || userCentralData.type != this.type) {
            return false;
        }
        if (userCentralData.title == null ? this.title != null : !userCentralData.title.equals(this.title)) {
            return false;
        }
        if (userCentralData.tips == null ? this.tips != null : !userCentralData.tips.equals(this.tips)) {
            return false;
        }
        if (userCentralData.icon == null ? this.icon != null : !userCentralData.icon.equals(this.icon)) {
            return false;
        }
        if (userCentralData.icon_desc == null ? this.icon_desc != null : !userCentralData.icon_desc.equals(this.icon_desc)) {
            return false;
        }
        if (userCentralData.url == null ? this.url != null : !userCentralData.url.equals(this.url)) {
            return false;
        }
        if (userCentralData.login != this.login) {
            return false;
        }
        return super.equals(obj);
    }
}
