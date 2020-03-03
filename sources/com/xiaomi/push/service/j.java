package com.xiaomi.push.service;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.ho;
import com.xiaomi.push.hs;
import com.xiaomi.push.ib;
import com.xiaomi.push.ik;
import com.xiaomi.push.in;
import com.xiaomi.push.iy;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class j extends XMPushService.i {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ i f12929a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ String f344a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ List f345a;
    final /* synthetic */ String b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    j(i iVar, int i, String str, List list, String str2) {
        super(i);
        this.f12929a = iVar;
        this.f344a = str;
        this.f345a = list;
        this.b = str2;
    }

    public String a() {
        return "Send tiny data.";
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m334a() {
        String a2 = this.f12929a.a(this.f344a);
        ArrayList<in> a3 = bf.a(this.f345a, this.f344a, a2, 32768);
        b.a("TinyData LongConnUploader.upload pack notifications " + a3.toString() + "  ts:" + System.currentTimeMillis());
        if (a3 != null) {
            Iterator<in> it = a3.iterator();
            while (it.hasNext()) {
                in next = it.next();
                next.a("uploadWay", "longXMPushService");
                ik a4 = w.a(this.f344a, a2, next, ho.Notification);
                if (!TextUtils.isEmpty(this.b) && !TextUtils.equals(this.f344a, this.b)) {
                    if (a4.a() == null) {
                        ib ibVar = new ib();
                        ibVar.a("-1");
                        a4.a(ibVar);
                    }
                    a4.a().b("ext_traffic_source_pkg", this.b);
                }
                this.f12929a.f12928a.a(this.f344a, iy.a(a4), true);
            }
            for (hs d : this.f345a) {
                b.a("TinyData LongConnUploader.upload uploaded by com.xiaomi.push.service.TinyDataUploader.  item" + d.d() + "  ts:" + System.currentTimeMillis());
            }
            return;
        }
        b.d("TinyData LongConnUploader.upload Get a null XmPushActionNotification list when TinyDataHelper.pack() in XMPushService.");
    }
}
