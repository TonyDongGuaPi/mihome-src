package com.xiaomi.youpin.pojo;

import android.text.TextUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xiaomi.plugin.PackageRawInfo;
import java.util.ArrayList;
import java.util.List;

public class PluginInfoLocal {
    public List<PluginInfoItem> infoItemList;

    public static class PluginInfoItem {
        public boolean isDebug;
        public String packageName;
        public String path;
        public int versionCode;

        public void copyFrom(PluginInfoItem pluginInfoItem) {
            if (pluginInfoItem != null) {
                this.path = pluginInfoItem.path;
                this.versionCode = pluginInfoItem.versionCode;
                this.isDebug = pluginInfoItem.isDebug;
            }
        }

        public JsonObject toJsonObject() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("packageName", this.packageName);
            jsonObject.addProperty("path", this.path);
            jsonObject.addProperty("versionCode", (Number) Integer.valueOf(this.versionCode));
            jsonObject.addProperty("isDebug", Boolean.valueOf(this.isDebug));
            return jsonObject;
        }

        public static PluginInfoItem parse(JsonObject jsonObject) {
            if (jsonObject == null) {
                return null;
            }
            PluginInfoItem pluginInfoItem = new PluginInfoItem();
            pluginInfoItem.packageName = jsonObject.getAsJsonPrimitive("packageName").getAsString();
            pluginInfoItem.path = jsonObject.getAsJsonPrimitive("path").getAsString();
            pluginInfoItem.versionCode = jsonObject.getAsJsonPrimitive("versionCode").getAsInt();
            pluginInfoItem.isDebug = jsonObject.getAsJsonPrimitive("isDebug").getAsBoolean();
            return pluginInfoItem;
        }
    }

    public String toString() {
        if (this.infoItemList == null) {
            return "";
        }
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < this.infoItemList.size(); i++) {
            jsonArray.add((JsonElement) this.infoItemList.get(i).toJsonObject());
        }
        return jsonArray.toString();
    }

    public PluginInfoItem getPluginInfo(String str) {
        if (this.infoItemList == null) {
            return null;
        }
        for (int i = 0; i < this.infoItemList.size(); i++) {
            if (str.equals(this.infoItemList.get(i).packageName)) {
                return this.infoItemList.get(i);
            }
        }
        return null;
    }

    public void addPluginInfo(String str, PackageRawInfo packageRawInfo, boolean z) {
        if (packageRawInfo != null && str != null) {
            PluginInfoItem pluginInfoItem = new PluginInfoItem();
            pluginInfoItem.packageName = packageRawInfo.mPackageName;
            pluginInfoItem.path = str;
            pluginInfoItem.versionCode = packageRawInfo.mVersion;
            pluginInfoItem.isDebug = z;
            addPluginInfo(pluginInfoItem);
        }
    }

    public void addPluginInfo(PluginInfoItem pluginInfoItem) {
        if (pluginInfoItem != null) {
            if (this.infoItemList == null) {
                this.infoItemList = new ArrayList();
                this.infoItemList.add(pluginInfoItem);
                return;
            }
            PluginInfoItem pluginInfo = getPluginInfo(pluginInfoItem.packageName);
            if (pluginInfo == null) {
                this.infoItemList.add(pluginInfoItem);
            } else {
                pluginInfo.copyFrom(pluginInfoItem);
            }
        }
    }

    public void deletePluginInfo(String str) {
        if (str != null && this.infoItemList != null) {
            for (int i = 0; i < this.infoItemList.size(); i++) {
                if (str.equals(this.infoItemList.get(i).packageName)) {
                    this.infoItemList.remove(i);
                    return;
                }
            }
        }
    }

    public static PluginInfoLocal parse(String str) {
        PluginInfoLocal pluginInfoLocal = new PluginInfoLocal();
        pluginInfoLocal.infoItemList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return pluginInfoLocal;
        }
        try {
            JsonArray asJsonArray = new JsonParser().parse(str).getAsJsonArray();
            for (int i = 0; i < asJsonArray.size(); i++) {
                pluginInfoLocal.infoItemList.add(PluginInfoItem.parse(asJsonArray.get(i).getAsJsonObject()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pluginInfoLocal;
    }
}
