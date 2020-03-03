package com.mi.global.bbs.utils;

import android.text.TextUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.mi.global.bbs.model.PostDataItem;
import com.mi.global.bbs.model.PostDetailModel;
import java.util.ArrayList;
import java.util.List;

public class PostContentUtil {
    public static List<PostDataItem> getPostData(PostDetailModel.DataBean dataBean) {
        ArrayList arrayList = new ArrayList();
        PostDataItem postDataItem = new PostDataItem();
        postDataItem.setDataType(3);
        postDataItem.setAuthor(dataBean.author);
        postDataItem.setThreadBean(dataBean.thread);
        arrayList.add(postDataItem);
        arrayList.addAll(getPostDataByArray(dataBean.thread.arraydata));
        if (dataBean.thread.specialinfo != null && dataBean.thread.specialinfo.visible == 1) {
            PostDataItem postDataItem2 = new PostDataItem();
            postDataItem2.setDataType(4);
            postDataItem2.setThreadBean(dataBean.thread);
            postDataItem2.setSpecialInfo(dataBean.thread.specialinfo);
            arrayList.add(postDataItem2);
        }
        PostDataItem postDataItem3 = new PostDataItem();
        postDataItem3.setDataType(5);
        postDataItem3.setAuthor(dataBean.author);
        arrayList.add(postDataItem3);
        if (!(dataBean.column == null || dataBean.column.column == null || dataBean.column.column.size() <= 0)) {
            PostDataItem postDataItem4 = new PostDataItem();
            postDataItem4.setDataType(6);
            postDataItem4.setColumn(dataBean.column);
            arrayList.add(postDataItem4);
        }
        return arrayList;
    }

    private static List<PostDataItem> getPostDataByArray(JsonArray jsonArray) {
        ArrayList arrayList = new ArrayList();
        getItemArrayData(jsonArray, arrayList, "", new StringBuilder(), 0, "left", "");
        return arrayList;
    }

    private static void getItemArrayData(JsonArray jsonArray, List<PostDataItem> list, String str, StringBuilder sb, int i, String str2, String str3) {
        StringBuilder sb2 = sb;
        if (jsonArray != null) {
            String str4 = str;
            int i2 = i;
            String str5 = str2;
            String str6 = str3;
            for (int i3 = 0; i3 < jsonArray.size(); i3++) {
                JsonElement jsonElement = jsonArray.get(i3);
                if (jsonElement.isJsonObject()) {
                    JsonArray asJsonArray = jsonElement.getAsJsonObject().getAsJsonArray("data");
                    str4 = jsonElement.getAsJsonObject().get("type").getAsString();
                    int asInt = jsonElement.getAsJsonObject().get("newline").getAsInt();
                    str5 = jsonElement.getAsJsonObject().get("align").getAsString();
                    String asString = jsonElement.getAsJsonObject().get("href").getAsString();
                    getItemArrayData(asJsonArray, list, str4, sb, asInt, str5, asString);
                    i2 = asInt;
                    str6 = asString;
                } else {
                    if (i2 == 0) {
                        if ("url".equals(str4)) {
                            sb2.append("  <a href=\"");
                            sb2.append(str6);
                            sb2.append("\">");
                            sb2.append(jsonElement.getAsString());
                            sb2.append("</a>");
                        } else {
                            sb2.append("  ");
                            sb2.append(jsonElement.getAsString());
                        }
                    }
                    if (i2 == 1) {
                        getItemJsonData(jsonElement, list, str4, sb, str5, str6);
                        sb2.delete(0, sb.length());
                    }
                }
            }
        }
    }

    private static void getItemJsonData(JsonElement jsonElement, List<PostDataItem> list, String str, StringBuilder sb, String str2, String str3) {
        String sb2 = sb.toString();
        if (TextUtils.isEmpty(str)) {
            PostDataItem postDataItem = new PostDataItem();
            postDataItem.setPostTxt(sb2 + "  " + jsonElement.getAsString());
            postDataItem.setAlignType(str2);
            postDataItem.setDataType(1);
            list.add(postDataItem);
        } else if ("img".equals(str)) {
            if (!TextUtils.isEmpty(sb2)) {
                PostDataItem postDataItem2 = new PostDataItem();
                postDataItem2.setPostTxt(sb2);
                postDataItem2.setDataType(1);
                list.add(postDataItem2);
            }
            String asString = jsonElement.getAsString();
            PostDataItem postDataItem3 = new PostDataItem();
            postDataItem3.setPostImage(asString);
            postDataItem3.setDataType(2);
            list.add(postDataItem3);
        } else if ("url".equals(str)) {
            String asString2 = jsonElement.getAsString();
            sb.append("  <a href=\"");
            sb.append(str3);
            sb.append("\">");
            sb.append(asString2);
            sb.append("</a>");
            String sb3 = sb.toString();
            PostDataItem postDataItem4 = new PostDataItem();
            postDataItem4.setPostTxt(sb3);
            postDataItem4.setAlignType(str2);
            postDataItem4.setDataType(1);
            list.add(postDataItem4);
        } else if ("bbs_video".equals(str)) {
            if (!TextUtils.isEmpty(sb2)) {
                PostDataItem postDataItem5 = new PostDataItem();
                postDataItem5.setPostTxt(sb2);
                postDataItem5.setDataType(1);
                list.add(postDataItem5);
            }
            String asString3 = jsonElement.getAsString();
            PostDataItem postDataItem6 = new PostDataItem();
            postDataItem6.setPostTxt(asString3);
            postDataItem6.setDataType(10);
            list.add(postDataItem6);
        } else {
            PostDataItem postDataItem7 = new PostDataItem();
            postDataItem7.setDataType(999);
            list.add(postDataItem7);
        }
    }
}
