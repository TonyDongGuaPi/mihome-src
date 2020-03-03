package com.xiaomi.youpin.mimcmsg;

import android.util.Base64;
import com.google.gson.Gson;
import com.xiaomi.mimc.MIMCGroupMessage;
import com.xiaomi.mimc.MIMCMessage;
import com.xiaomi.mimc.MIMCMessageHandler;
import com.xiaomi.mimc.MIMCServerAck;
import com.xiaomi.miot.store.api.MiotStoreApi;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.mimcmsg.api.IRecentCallback;
import com.xiaomi.youpin.mimcmsg.api.MIMCApi;
import com.xiaomi.youpin.mimcmsg.pojo.MIMCCustomMsg;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageHandler implements MIMCMessageHandler {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23638a = "MIMC";

    public void a(MIMCGroupMessage mIMCGroupMessage) {
    }

    public void a(MIMCMessage mIMCMessage) {
    }

    public void a(MIMCServerAck mIMCServerAck) {
    }

    public void b(MIMCGroupMessage mIMCGroupMessage) {
    }

    public void c(List<MIMCGroupMessage> list) {
    }

    public void a(List<MIMCMessage> list) {
        LogUtils.d("MIMC", "收到消息了...");
        if (!list.isEmpty()) {
            MIMCApi.a((IRecentCallback) null);
            d(list);
        }
    }

    public void b(List<MIMCGroupMessage> list) {
        LogUtils.d("MIMC", "收到群消息了...");
    }

    private void d(List<MIMCMessage> list) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (MIMCMessage next : list) {
            MIMCCustomMsg mIMCCustomMsg = new MIMCCustomMsg();
            mIMCCustomMsg.setSequence(next.b() + "");
            mIMCCustomMsg.setPayload(new String(Base64.decode(next.h(), 2)));
            mIMCCustomMsg.setToAccount(next.f());
            mIMCCustomMsg.setFromAccount(next.d());
            mIMCCustomMsg.setTimestamp(next.c());
            mIMCCustomMsg.setBizType(next.i());
            arrayList.add(mIMCCustomMsg);
        }
        String json = new Gson().toJson((Object) arrayList);
        hashMap.put("messageList", json);
        MiotStoreApi.a().sendJsEvent(MIMCMsgManager.d, hashMap);
        LogUtils.d("MIMC", "发送更新事件 : " + json);
    }
}
