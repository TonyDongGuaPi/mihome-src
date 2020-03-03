package cn.fraudmetrix.octopus.aspirit.activity.mvp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import cn.fraudmetrix.octopus.aspirit.activity.mvp.a;
import cn.fraudmetrix.octopus.aspirit.b.e;
import cn.fraudmetrix.octopus.aspirit.b.f;
import cn.fraudmetrix.octopus.aspirit.bean.b;
import cn.fraudmetrix.octopus.aspirit.bean.d;
import cn.fraudmetrix.octopus.aspirit.bean.h;
import cn.fraudmetrix.octopus.aspirit.g.f;
import cn.fraudmetrix.octopus.aspirit.main.OctopusManager;
import cn.fraudmetrix.octopus.aspirit.utils.OctopusConstants;
import cn.fraudmetrix.octopus.aspirit.utils.i;
import cn.fraudmetrix.octopus.aspirit.utils.j;
import cn.fraudmetrix.octopus.aspirit.utils.k;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.mi.global.shop.model.Tags;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class c implements a.C0018a {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public e<b, b> f608a = new e<b, b>() {
        public b a(b bVar) {
            String a2;
            if (bVar == null || TextUtils.isEmpty(bVar.login_url) || bVar.source_type != 1) {
                return bVar;
            }
            bVar.login_js = j.a(bVar.login_js);
            bVar.success_url = j.a(bVar.success_url);
            if (!TextUtils.isEmpty(bVar.crawled_urls)) {
                bVar.crawled_urls = bVar.crawled_urls.replace(Tags.MiHome.TEL_SEPARATOR4, "");
                try {
                    bVar.crawledUrls = JSONArray.parseArray(bVar.crawled_urls, d.class);
                    Map hashMap = new HashMap();
                    if (!TextUtils.isEmpty(bVar.load_js_content)) {
                        bVar.load_js_content = bVar.load_js_content.replace(Tags.MiHome.TEL_SEPARATOR4, "");
                        hashMap = (Map) JSON.parseObject(bVar.load_js_content, HashMap.class);
                    }
                    try {
                        for (d next : bVar.crawledUrls) {
                            if (!TextUtils.isEmpty(next.jsNameL1)) {
                                a2 = j.a((String) hashMap.get(next.jsNameL1));
                            } else if (!TextUtils.isEmpty(next.jsNameL2)) {
                                a2 = j.a((String) hashMap.get(next.jsNameL2));
                            } else if (!TextUtils.isEmpty(next.jsNameL3)) {
                                a2 = j.a((String) hashMap.get(next.jsNameL3));
                            }
                            next.JsContentL1 = a2;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        if (hashMap.containsKey(b.SPECIAL_URLS)) {
                            bVar.special_urls = JSON.parseArray(j.a((String) hashMap.get(b.SPECIAL_URLS)));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            return bVar;
        }
    };

    /* access modifiers changed from: private */
    public void a(@NonNull String str, f fVar, cn.fraudmetrix.octopus.aspirit.a.b bVar) {
        cn.fraudmetrix.octopus.aspirit.utils.d.a((Object) fVar, "channelDetailBean is null");
        cn.fraudmetrix.octopus.aspirit.bean.c cVar = new cn.fraudmetrix.octopus.aspirit.bean.c();
        cVar.cookies = fVar.e();
        cVar.success_url = fVar.h();
        cVar.user_agent = fVar.d();
        cVar.task_id = str;
        HashMap hashMap = new HashMap();
        hashMap.put(OctopusConstants.x, cVar.task_id);
        hashMap.put(OctopusConstants.v, false);
        hashMap.put("cookies", j.b(cVar.cookies));
        hashMap.put("success_url", j.b(cVar.success_url));
        hashMap.put(com.alipay.sdk.cons.b.b, j.b(cVar.user_agent));
        f.a.a().c(i.m, hashMap, bVar);
    }

    /* access modifiers changed from: private */
    public void b(String str, List<? extends cn.fraudmetrix.octopus.aspirit.g.f> list, cn.fraudmetrix.octopus.aspirit.a.b bVar) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (cn.fraudmetrix.octopus.aspirit.g.f fVar : list) {
                if (!TextUtils.isEmpty(fVar.e())) {
                    h hVar = new h();
                    hVar.name = fVar.a();
                    hVar.type = "GET";
                    hVar.pageSource = j.b(fVar.e());
                    arrayList.add(hVar);
                }
            }
        }
        if (arrayList.size() != 0) {
            HashMap hashMap = new HashMap();
            hashMap.put(OctopusConstants.x, str);
            hashMap.put("completed", "true");
            hashMap.put(OctopusConstants.v, false);
            hashMap.put("data", arrayList);
            f.a.a().c(i.k, hashMap, bVar);
        }
    }

    public void a(@NonNull final Context context, @NonNull String str, String str2, String str3, final cn.fraudmetrix.octopus.aspirit.a.a<cn.fraudmetrix.octopus.aspirit.bean.i> aVar) {
        cn.fraudmetrix.octopus.aspirit.utils.d.a((Object) context, "context is null");
        cn.fraudmetrix.octopus.aspirit.utils.d.a((Object) str3, "cityCode is null");
        cn.fraudmetrix.octopus.aspirit.utils.d.a(str, "channelCode is empty");
        final HashMap hashMap = new HashMap();
        hashMap.put("channel_code", str);
        hashMap.put(DTransferConstants.ae, str3);
        hashMap.put(OctopusConstants.w, true);
        hashMap.put(OctopusConstants.v, false);
        OctopusManager b = OctopusManager.b();
        if (!(b.f644a == null || b.f644a.passbackarams == null)) {
            hashMap.put(OctopusConstants.G, b.f644a.passbackarams);
        }
        OctopusManager.b().a().execute(new Runnable() {
            public void run() {
                if (cn.fraudmetrix.octopus.aspirit.utils.b.a().b().device_info == null) {
                    cn.fraudmetrix.octopus.aspirit.utils.b.a().b().device_info = k.a(context);
                }
                if (cn.fraudmetrix.octopus.aspirit.utils.b.a().b().device_info != null) {
                    hashMap.put(DeviceRequestsHelper.DEVICE_INFO_PARAM, cn.fraudmetrix.octopus.aspirit.utils.b.a().b().device_info);
                }
                f.a.a().b(i.j, hashMap, aVar);
            }
        });
    }

    public void a(String str, cn.fraudmetrix.octopus.aspirit.a.b bVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("path", j.b(str));
        hashMap.put(OctopusConstants.v, false);
        f.a.a().b(i.o, hashMap, bVar);
    }

    public void a(@NonNull String str, String str2, final cn.fraudmetrix.octopus.aspirit.b.c<b> cVar) {
        cn.fraudmetrix.octopus.aspirit.utils.d.a(str, "channelCode is empty");
        HashMap hashMap = new HashMap();
        hashMap.put("channel_code", str);
        if (str2 == null) {
            str2 = "";
        }
        hashMap.put("channel_type", str2);
        hashMap.put(OctopusConstants.w, true);
        f.a.a().b(i.i, hashMap, new cn.fraudmetrix.octopus.aspirit.a.a<b>() {
            /* renamed from: a */
            public void b(b bVar) {
                cVar.b(bVar);
            }

            public void a(Throwable th) {
                cVar.a(th);
            }

            /* renamed from: b */
            public b a(String str) {
                return (b) c.this.f608a.a(super.a(str));
            }
        });
    }

    public void a(@NonNull final String str, final List<? extends cn.fraudmetrix.octopus.aspirit.g.f> list, final cn.fraudmetrix.octopus.aspirit.a.b bVar) {
        cn.fraudmetrix.octopus.aspirit.utils.d.a(str, "taskId is empty");
        cn.fraudmetrix.octopus.aspirit.utils.d.a((Collection<?>) list, "taskDataList is empty");
        OctopusManager.b().a().execute(new Runnable() {
            public void run() {
                if (list.size() > 1) {
                    c.this.b(str, list.subList(1, list.size()), (cn.fraudmetrix.octopus.aspirit.a.b) null);
                }
                c.this.a(str, (cn.fraudmetrix.octopus.aspirit.g.f) list.get(0), bVar);
            }
        });
    }
}
