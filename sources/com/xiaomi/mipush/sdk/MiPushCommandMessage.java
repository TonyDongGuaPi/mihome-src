package com.xiaomi.mipush.sdk;

import android.os.Bundle;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import java.util.ArrayList;
import java.util.List;

public class MiPushCommandMessage implements PushMessageHandler.a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11514a = "command";
    private static final String b = "resultCode";
    private static final String c = "reason";
    private static final String d = "commandArguments";
    private static final String e = "category";
    private static final long serialVersionUID = 1;
    private String category;
    private String command;
    private List<String> commandArguments;
    private String reason;
    private long resultCode;

    public static MiPushCommandMessage fromBundle(Bundle bundle) {
        MiPushCommandMessage miPushCommandMessage = new MiPushCommandMessage();
        miPushCommandMessage.command = bundle.getString("command");
        miPushCommandMessage.resultCode = bundle.getLong("resultCode");
        miPushCommandMessage.reason = bundle.getString("reason");
        miPushCommandMessage.commandArguments = bundle.getStringArrayList(d);
        miPushCommandMessage.category = bundle.getString("category");
        return miPushCommandMessage;
    }

    public String getCategory() {
        return this.category;
    }

    public String getCommand() {
        return this.command;
    }

    public List<String> getCommandArguments() {
        return this.commandArguments;
    }

    public String getReason() {
        return this.reason;
    }

    public long getResultCode() {
        return this.resultCode;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public void setCommand(String str) {
        this.command = str;
    }

    public void setCommandArguments(List<String> list) {
        this.commandArguments = list;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public void setResultCode(long j) {
        this.resultCode = j;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("command", this.command);
        bundle.putLong("resultCode", this.resultCode);
        bundle.putString("reason", this.reason);
        if (this.commandArguments != null) {
            bundle.putStringArrayList(d, (ArrayList) this.commandArguments);
        }
        bundle.putString("category", this.category);
        return bundle;
    }

    public String toString() {
        return "command={" + this.command + "}, resultCode={" + this.resultCode + "}, reason={" + this.reason + "}, category={" + this.category + "}, commandArguments={" + this.commandArguments + "}";
    }
}