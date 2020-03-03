package com.xiaomi.mipush.sdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.HashMap;

class ax extends Handler {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ aw f11541a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ax(aw awVar, Looper looper) {
        super(looper);
        this.f11541a = awVar;
    }

    public void dispatchMessage(Message message) {
        aw awVar;
        bb bbVar;
        HashMap<String, String> c;
        aw awVar2;
        bb bbVar2;
        if (message.what == 19) {
            String str = (String) message.obj;
            int i = message.arg1;
            synchronized (am.class) {
                if (am.a(this.f11541a.c).e(str)) {
                    if (am.a(this.f11541a.c).c(str) < 10) {
                        if (bb.DISABLE_PUSH.ordinal() == i && "syncing".equals(am.a(this.f11541a.c).a(bb.DISABLE_PUSH))) {
                            awVar2 = this.f11541a;
                            bbVar2 = bb.DISABLE_PUSH;
                        } else if (bb.ENABLE_PUSH.ordinal() != i || !"syncing".equals(am.a(this.f11541a.c).a(bb.ENABLE_PUSH))) {
                            if (bb.UPLOAD_HUAWEI_TOKEN.ordinal() == i && "syncing".equals(am.a(this.f11541a.c).a(bb.UPLOAD_HUAWEI_TOKEN))) {
                                awVar = this.f11541a;
                                bbVar = bb.UPLOAD_HUAWEI_TOKEN;
                                c = h.c(this.f11541a.c, d.ASSEMBLE_PUSH_HUAWEI);
                            } else if (bb.UPLOAD_FCM_TOKEN.ordinal() == i && "syncing".equals(am.a(this.f11541a.c).a(bb.UPLOAD_FCM_TOKEN))) {
                                awVar = this.f11541a;
                                bbVar = bb.UPLOAD_FCM_TOKEN;
                                c = h.c(this.f11541a.c, d.ASSEMBLE_PUSH_FCM);
                            } else if (bb.UPLOAD_COS_TOKEN.ordinal() != i || !"syncing".equals(am.a(this.f11541a.c).a(bb.UPLOAD_COS_TOKEN))) {
                                if (bb.UPLOAD_FTOS_TOKEN.ordinal() == i && "syncing".equals(am.a(this.f11541a.c).a(bb.UPLOAD_FTOS_TOKEN))) {
                                    awVar = this.f11541a;
                                    bbVar = bb.UPLOAD_FTOS_TOKEN;
                                    c = h.c(this.f11541a.c, d.ASSEMBLE_PUSH_FTOS);
                                }
                                am.a(this.f11541a.c).b(str);
                            } else {
                                awVar = this.f11541a;
                                bbVar = bb.UPLOAD_COS_TOKEN;
                                c = h.c(this.f11541a.c, d.ASSEMBLE_PUSH_COS);
                            }
                            awVar.a(str, bbVar, false, c);
                            am.a(this.f11541a.c).b(str);
                        } else {
                            awVar2 = this.f11541a;
                            bbVar2 = bb.ENABLE_PUSH;
                        }
                        awVar2.a(str, bbVar2, true, (HashMap<String, String>) null);
                        am.a(this.f11541a.c).b(str);
                    } else {
                        am.a(this.f11541a.c).d(str);
                    }
                }
            }
        }
    }
}
