package com.xiaomi.mipush.sdk;

import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import java.util.HashMap;
import java.util.Map;

public class MiPushMessage implements PushMessageHandler.a {
    public static final int MESSAGE_TYPE_ACCOUNT = 3;
    public static final int MESSAGE_TYPE_ALIAS = 1;
    public static final int MESSAGE_TYPE_REG = 0;
    public static final int MESSAGE_TYPE_TOPIC = 2;

    /* renamed from: a  reason: collision with root package name */
    private static final String f11515a = "messageId";
    private static final String b = "messageType";
    private static final String c = "content";
    private static final String d = "alias";
    private static final String e = "topic";
    private static final String f = "user_account";
    private static final String g = "passThrough";
    private static final String h = "notifyType";
    private static final String i = "notifyId";
    private static final String j = "isNotified";
    private static final String k = "description";
    private static final String l = "title";
    private static final String m = "category";
    private static final String n = "extra";
    private static final long serialVersionUID = 1;
    private String alias;
    private boolean arrived = false;
    private String category;
    private String content;
    private String description;
    private HashMap<String, String> extra = new HashMap<>();
    private boolean isNotified;
    private String messageId;
    private int messageType;
    private int notifyId;
    private int notifyType;
    private int passThrough;
    private String title;
    private String topic;
    private String userAccount;

    public static MiPushMessage fromBundle(Bundle bundle) {
        MiPushMessage miPushMessage = new MiPushMessage();
        miPushMessage.messageId = bundle.getString("messageId");
        miPushMessage.messageType = bundle.getInt("messageType");
        miPushMessage.passThrough = bundle.getInt(g);
        miPushMessage.alias = bundle.getString("alias");
        miPushMessage.userAccount = bundle.getString(f);
        miPushMessage.topic = bundle.getString("topic");
        miPushMessage.content = bundle.getString("content");
        miPushMessage.description = bundle.getString("description");
        miPushMessage.title = bundle.getString("title");
        miPushMessage.isNotified = bundle.getBoolean(j);
        miPushMessage.notifyId = bundle.getInt(i);
        miPushMessage.notifyType = bundle.getInt(h);
        miPushMessage.category = bundle.getString("category");
        miPushMessage.extra = (HashMap) bundle.getSerializable("extra");
        return miPushMessage;
    }

    public String getAlias() {
        return this.alias;
    }

    public String getCategory() {
        return this.category;
    }

    public String getContent() {
        return this.content;
    }

    public String getDescription() {
        return this.description;
    }

    public Map<String, String> getExtra() {
        return this.extra;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public int getMessageType() {
        return this.messageType;
    }

    public int getNotifyId() {
        return this.notifyId;
    }

    public int getNotifyType() {
        return this.notifyType;
    }

    public int getPassThrough() {
        return this.passThrough;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTopic() {
        return this.topic;
    }

    public String getUserAccount() {
        return this.userAccount;
    }

    public boolean isArrivedMessage() {
        return this.arrived;
    }

    public boolean isNotified() {
        return this.isNotified;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public void setArrivedMessage(boolean z) {
        this.arrived = z;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setExtra(Map<String, String> map) {
        this.extra.clear();
        if (map != null) {
            this.extra.putAll(map);
        }
    }

    public void setMessageId(String str) {
        this.messageId = str;
    }

    public void setMessageType(int i2) {
        this.messageType = i2;
    }

    public void setNotified(boolean z) {
        this.isNotified = z;
    }

    public void setNotifyId(int i2) {
        this.notifyId = i2;
    }

    public void setNotifyType(int i2) {
        this.notifyType = i2;
    }

    public void setPassThrough(int i2) {
        this.passThrough = i2;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public void setUserAccount(String str) {
        this.userAccount = str;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("messageId", this.messageId);
        bundle.putInt(g, this.passThrough);
        bundle.putInt("messageType", this.messageType);
        if (!TextUtils.isEmpty(this.alias)) {
            bundle.putString("alias", this.alias);
        }
        if (!TextUtils.isEmpty(this.userAccount)) {
            bundle.putString(f, this.userAccount);
        }
        if (!TextUtils.isEmpty(this.topic)) {
            bundle.putString("topic", this.topic);
        }
        bundle.putString("content", this.content);
        if (!TextUtils.isEmpty(this.description)) {
            bundle.putString("description", this.description);
        }
        if (!TextUtils.isEmpty(this.title)) {
            bundle.putString("title", this.title);
        }
        bundle.putBoolean(j, this.isNotified);
        bundle.putInt(i, this.notifyId);
        bundle.putInt(h, this.notifyType);
        if (!TextUtils.isEmpty(this.category)) {
            bundle.putString("category", this.category);
        }
        if (this.extra != null) {
            bundle.putSerializable("extra", this.extra);
        }
        return bundle;
    }

    public String toString() {
        return "messageId={" + this.messageId + "},passThrough={" + this.passThrough + "},alias={" + this.alias + "},topic={" + this.topic + "},userAccount={" + this.userAccount + "},content={" + this.content + "},description={" + this.description + "},title={" + this.title + "},isNotified={" + this.isNotified + "},notifyId={" + this.notifyId + "},notifyType={" + this.notifyType + "}, category={" + this.category + "}, extra={" + this.extra + "}";
    }
}
