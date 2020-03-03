package com.xiaomi.youpin.login.entity.account;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Calendar;

public class MiAccountInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f23513a;
    public String b;
    public String c;
    public String d;
    public ArrayList<String> e;
    public String f;
    public String g;
    public Bitmap h;
    public Gender i;
    public Calendar j;

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

    public String a() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mUserId", this.f23513a);
        jsonObject.addProperty("mUserName", this.b);
        jsonObject.addProperty("mAvatarAddress", this.c == null ? "" : this.c);
        jsonObject.addProperty("mPhone", this.d == null ? "" : this.d);
        jsonObject.addProperty("mEmail", this.f == null ? "" : this.f);
        jsonObject.addProperty("mNickName", this.g == null ? "" : this.g);
        jsonObject.addProperty("mGender", this.i == null ? "" : this.i.getType());
        jsonObject.addProperty("mBirthday", (Number) Long.valueOf(this.j == null ? 0 : this.j.getTimeInMillis()));
        return jsonObject.toString();
    }

    public static String a(JsonObject jsonObject, String str) {
        JsonElement jsonElement = jsonObject.get(str);
        return (jsonElement == null || !(jsonElement instanceof JsonPrimitive)) ? "" : jsonElement.getAsString();
    }

    public static long b(JsonObject jsonObject, String str) {
        JsonElement jsonElement = jsonObject.get(str);
        if (jsonElement == null || !(jsonElement instanceof JsonPrimitive)) {
            return 0;
        }
        return jsonElement.getAsLong();
    }

    public static MiAccountInfo a(String str) {
        JsonElement parse;
        if (TextUtils.isEmpty(str) || (parse = new JsonParser().parse(str)) == null) {
            return null;
        }
        MiAccountInfo miAccountInfo = new MiAccountInfo();
        JsonObject asJsonObject = parse.getAsJsonObject();
        miAccountInfo.f23513a = a(asJsonObject, "mUserId");
        miAccountInfo.b = a(asJsonObject, "mUserName");
        miAccountInfo.c = a(asJsonObject, "mAvatarAddress");
        miAccountInfo.d = a(asJsonObject, "mPhone");
        miAccountInfo.f = a(asJsonObject, "mEmail");
        miAccountInfo.g = a(asJsonObject, "mNickName");
        String a2 = a(asJsonObject, "mGender");
        if (Gender.MALE.getType().equals(a2)) {
            miAccountInfo.i = Gender.MALE;
        } else if (Gender.FEMALE.getType().equals(a2)) {
            miAccountInfo.i = Gender.FEMALE;
        }
        long b2 = b(asJsonObject, "mBirthday");
        miAccountInfo.j = Calendar.getInstance();
        miAccountInfo.j.setTimeInMillis(b2);
        return miAccountInfo;
    }
}
