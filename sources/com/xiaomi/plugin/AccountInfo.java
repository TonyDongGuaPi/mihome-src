package com.xiaomi.plugin;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Calendar;

public class AccountInfo {
    public Bitmap mAvatar;
    public String mAvatarAddress;
    public Calendar mBirthday;
    public String mEmail;
    public Gender mGender;
    public String mNickName;
    public String mPhone;
    public ArrayList<String> mPhoneList;
    public String mUserId;
    public String mUserName;

    public enum Gender {
        MALE("m"),
        FEMALE("f");
        
        private String mGender;

        private Gender(String str) {
            this.mGender = str;
        }

        public String getType() {
            return this.mGender;
        }
    }

    public String toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mUserId", this.mUserId);
        jsonObject.addProperty("mUserName", this.mUserName);
        jsonObject.addProperty("mAvatarAddress", this.mAvatarAddress == null ? "" : this.mAvatarAddress);
        jsonObject.addProperty("mPhone", this.mPhone == null ? "" : this.mPhone);
        jsonObject.addProperty("mEmail", this.mEmail == null ? "" : this.mEmail);
        jsonObject.addProperty("mNickName", this.mNickName == null ? "" : this.mNickName);
        jsonObject.addProperty("mGender", this.mGender == null ? "" : this.mGender.getType());
        jsonObject.addProperty("mBirthday", (Number) Long.valueOf(this.mBirthday == null ? 0 : this.mBirthday.getTimeInMillis()));
        return jsonObject.toString();
    }

    public static String getJSsonString(JsonObject jsonObject, String str) {
        JsonElement jsonElement = jsonObject.get(str);
        return (jsonElement == null || !(jsonElement instanceof JsonPrimitive)) ? "" : jsonElement.getAsString();
    }

    public static long getJSsonLong(JsonObject jsonObject, String str) {
        JsonElement jsonElement = jsonObject.get(str);
        if (jsonElement == null || !(jsonElement instanceof JsonPrimitive)) {
            return 0;
        }
        return jsonElement.getAsLong();
    }

    public static AccountInfo parseFromJson(String str) {
        JsonElement parse;
        if (TextUtils.isEmpty(str) || (parse = new JsonParser().parse(str)) == null) {
            return null;
        }
        AccountInfo accountInfo = new AccountInfo();
        JsonObject asJsonObject = parse.getAsJsonObject();
        accountInfo.mUserId = getJSsonString(asJsonObject, "mUserId");
        accountInfo.mUserName = getJSsonString(asJsonObject, "mUserName");
        accountInfo.mAvatarAddress = getJSsonString(asJsonObject, "mAvatarAddress");
        accountInfo.mPhone = getJSsonString(asJsonObject, "mPhone");
        accountInfo.mEmail = getJSsonString(asJsonObject, "mEmail");
        accountInfo.mNickName = getJSsonString(asJsonObject, "mNickName");
        String jSsonString = getJSsonString(asJsonObject, "mGender");
        if (Gender.MALE.equals(jSsonString)) {
            accountInfo.mGender = Gender.MALE;
        } else if (Gender.FEMALE.equals(jSsonString)) {
            accountInfo.mGender = Gender.FEMALE;
        }
        long jSsonLong = getJSsonLong(asJsonObject, "mBirthday");
        accountInfo.mBirthday = Calendar.getInstance();
        accountInfo.mBirthday.setTimeInMillis(jSsonLong);
        return accountInfo;
    }
}
