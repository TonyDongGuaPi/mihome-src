package com.xiaomi.smarthome.voice.microaudio;

import android.text.TextUtils;
import com.xiaomi.smarthome.framework.push.PushListener;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.voice.microaudio.MicroAudioManager;
import com.xiaomi.smarthome.voice.microaudio.model.MicroPushMsgInfo;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MicroPushListener extends PushListener {
    public boolean b(String str, String str2) {
        a(str2);
        return true;
    }

    public boolean a(String str, String str2) {
        a(str2);
        return true;
    }

    private void a(String str) {
        Miio.b("MicroPushListener", "messageBody:    " + str);
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                MicroPushMsgInfo microPushMsgInfo = new MicroPushMsgInfo();
                microPushMsgInfo.e = jSONObject.optLong("time");
                microPushMsgInfo.h = jSONObject.optInt("speaker");
                microPushMsgInfo.f = jSONObject.optInt("source");
                microPushMsgInfo.d = jSONObject.optString("session_id");
                JSONObject optJSONObject = jSONObject.optJSONObject("content");
                if (optJSONObject != null) {
                    microPushMsgInfo.g = optJSONObject.optString("type", "");
                    if (microPushMsgInfo.g.equalsIgnoreCase("text")) {
                        microPushMsgInfo.i = optJSONObject.optString("data");
                    } else {
                        int i = 0;
                        if (microPushMsgInfo.g.equalsIgnoreCase(MicroPushMsgInfo.o)) {
                            JSONArray jSONArray = optJSONObject.getJSONArray("data");
                            microPushMsgInfo.j.clear();
                            while (i < jSONArray.length()) {
                                microPushMsgInfo.j.add(jSONArray.get(i).toString());
                                i++;
                            }
                        } else if (microPushMsgInfo.g.equalsIgnoreCase(MicroPushMsgInfo.q)) {
                            JSONArray jSONArray2 = optJSONObject.getJSONArray("data");
                            microPushMsgInfo.m.clear();
                            microPushMsgInfo.m.addAll(MicroPushMsgInfo.MultiCmdInfo.a(jSONArray2));
                        } else if (microPushMsgInfo.g.equalsIgnoreCase("list")) {
                            microPushMsgInfo.l = optJSONObject.optString("title", "");
                            JSONArray jSONArray3 = optJSONObject.getJSONArray("data");
                            microPushMsgInfo.j.clear();
                            while (i < jSONArray3.length()) {
                                microPushMsgInfo.j.add(jSONArray3.get(i).toString());
                                i++;
                            }
                        }
                    }
                    microPushMsgInfo.k = optJSONObject.optInt("result", -1);
                }
                Iterator<MicroAudioManager.IReceiveMicroPushMsgCallBack> it = MicroAudioManager.a().b.iterator();
                while (it.hasNext()) {
                    MicroAudioManager.IReceiveMicroPushMsgCallBack next = it.next();
                    if (next != null) {
                        next.a(microPushMsgInfo);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
