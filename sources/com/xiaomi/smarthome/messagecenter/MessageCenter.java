package com.xiaomi.smarthome.messagecenter;

import android.app.Activity;
import android.content.Context;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.plugin.PluginUpdateInfo;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.feedback.FeedbackApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.redpoint.ProfileRedPointManager;
import com.xiaomi.smarthome.framework.redpoint.RedPointController;
import com.xiaomi.smarthome.framework.redpoint.RedPointManagerNew;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.messagecenter.shopmessage.MessageCenterCountHelper;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import com.xiaomi.smarthome.miio.page.SettingMainPageV2;
import com.xiaomi.smarthome.miio.update.AppUpdateManger;
import com.xiaomi.smarthome.miio.update.ModelUpdateInfo;
import com.xiaomi.smarthome.miio.update.ModelUpdateManager;
import com.xiaomi.smarthome.voice.VoiceManager;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;

public class MessageCenter {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10454a = "new_message";
    public static final String b = "new_update";
    public static final String c = "new_feedback";
    public static final String d = "new_sign_notify";
    public static final String e = "new_message_count_json";
    public static final String f = "new_shop_message_count_json";
    public static final int g = 1;
    public static final int h = 2;
    private static final String r = "MessageCenter";
    private static MessageCenter u;
    int i = 0;
    int j = 0;
    int k = 0;
    boolean l = true;
    boolean m = true;
    boolean n = true;
    RedPointController o;
    MLAlertDialog p = null;
    RedPointController q;
    private Context s = SHApplication.getAppContext();
    private HashMap<String, List<MessageCenterListener>> t = new HashMap<>();
    private RedPointController v;

    public static MessageCenter a() {
        if (u == null) {
            u = new MessageCenter();
        }
        return u;
    }

    private MessageCenter() {
    }

    public void a(String str, Object obj, boolean z) {
        b(str, obj, z);
    }

    private Map<String, Long> j() {
        Set<String> keySet = AllTypeMsgManager.a().d().keySet();
        HashMap hashMap = new HashMap();
        for (String put : keySet) {
            hashMap.put(put, 0L);
        }
        for (MessageRecord next : MessageRecord.queryAll()) {
            if (next.receiveTime > ((Long) hashMap.get(next.messageType)).longValue()) {
                hashMap.put(next.messageType, Long.valueOf(next.receiveTime));
            }
        }
        return hashMap;
    }

    public void b() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            public void subscribe(final ObservableEmitter observableEmitter) throws Exception {
                RemoteFamilyApi.a().a(SHApplication.getAppContext(), MessageCenterCountHelper.a(), (AsyncCallback<JSONArray, Error>) new AsyncCallback<JSONArray, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONArray jSONArray) {
                        observableEmitter.onNext(jSONArray);
                        observableEmitter.onComplete();
                    }

                    public void onFailure(Error error) {
                        observableEmitter.onNext(new JSONArray());
                        observableEmitter.onComplete();
                    }
                });
            }
        }).onErrorReturn(new Function<Throwable, Object>() {
            /* renamed from: a */
            public Object apply(Throwable th) {
                return new JSONArray();
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    public void a(long j2, int i2) {
        Miio.b("MessageCenter", "-----checkNewMessageForProfile timeStamp" + j2 + "  type  " + i2);
        DevicePushRedpointHelper.a(j2, i2);
    }

    public void a(int i2, int i3) {
        Miio.b("MessageCenter", "msgCount" + i2 + "    type:" + i3);
        k();
        if (i3 == 1) {
            if (i2 > 0) {
                this.v.a(RedPointManagerNew.f, true);
            } else {
                this.v.a(RedPointManagerNew.f, false);
            }
        } else if (i3 != 2) {
        } else {
            if (i2 > 0) {
                this.v.a("red_point_setting_page", true);
            } else {
                this.v.a("red_point_setting_page", false);
            }
        }
    }

    private void k() {
        if (this.v == null) {
            LogUtil.a("MessageCenter", "initNewMessageController");
            ArrayList arrayList = new ArrayList();
            arrayList.add(RedPointManagerNew.f);
            arrayList.add("red_point_setting_page");
            this.v = new RedPointController(arrayList, false);
        }
    }

    public void c() {
        FeedbackApi.INSTANCE.getRedDotCount(SHApplication.getAppContext(), new AsyncCallback<Integer, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(Integer num) {
                MessageCenter.this.b(MessageCenter.c, num, false);
            }
        });
    }

    public void d() {
        Miio.b("MessageCenter", "notifyUpdateListenerIfNeeded   " + this.i + "***" + this.k + "***" + this.j);
        Miio.b("MessageCenter", "notifyUpdateListenerIfNeeded   " + this.l + "&&&&&&" + this.n + "&&&&&&" + this.m);
        m();
        if (this.i == 1 || this.j == 1 || this.k == 1) {
            if (this.i == 1 && !this.l) {
                this.o.a(true);
                l();
                return;
            } else if (this.i == 1 && this.l) {
                this.o.a(RedPointManagerNew.j, true);
                this.o.a(RedPointManagerNew.i, true);
                this.o.a("red_point_setting_page", false);
                return;
            } else if (this.j == 1 && !this.m) {
                this.o.a(true);
                l();
                return;
            } else if (this.j == 1 && this.m) {
                this.o.a(RedPointManagerNew.j, true);
                this.o.a(RedPointManagerNew.i, true);
                this.o.a("red_point_setting_page", false);
                l();
                return;
            } else if (this.k == 1 && !this.n) {
                this.o.a(true);
                l();
                return;
            } else if (this.k == 1 && this.n) {
                this.o.a(RedPointManagerNew.j, true);
                this.o.a(RedPointManagerNew.i, true);
                this.o.a("red_point_setting_page", false);
                l();
                return;
            }
        }
        if (this.i == 3 || this.j == 3 || this.k == 3) {
            this.o.a(RedPointManagerNew.j, true);
            this.o.a(RedPointManagerNew.i, false);
            this.o.a("red_point_setting_page", false);
            l();
        } else if (this.i == 2 && this.j == 2 && this.k == 2) {
            this.o.a(false);
            l();
        }
    }

    private void l() {
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = true;
        this.m = true;
        this.n = true;
    }

    private void m() {
        if (this.o == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(RedPointManagerNew.j);
            arrayList.add(RedPointManagerNew.i);
            arrayList.add("red_point_setting_page");
            this.o = new RedPointController(arrayList, false);
        }
    }

    public boolean e() {
        long a2 = SharePrefsManager.a(SHApplication.getAppContext(), SmartHomeConfig.p, SmartHomeConfig.q, 0);
        if (a2 == 0) {
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (simpleDateFormat.format(new Date(a2)).equals(simpleDateFormat.format(new Date(System.currentTimeMillis())))) {
            return true;
        }
        return false;
    }

    public void f() {
        SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.p, SmartHomeConfig.q, System.currentTimeMillis());
    }

    public void a(Activity activity) {
        Miio.b("MessageCenter", "checkModelUpdate");
        this.i = 0;
        final WeakReference weakReference = new WeakReference(activity);
        ModelUpdateManager.d().a((ModelUpdateManager.CheckModelUpdateCallBack) new ModelUpdateManager.CheckModelUpdateCallBack() {
            public void a(List<ModelUpdateInfo> list) {
                if (list == null || list.size() <= 0) {
                    MessageCenter.this.i = 2;
                    MessageCenter.this.d();
                    return;
                }
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= list.size()) {
                        break;
                    }
                    ModelUpdateInfo modelUpdateInfo = list.get(i);
                    if (!modelUpdateInfo.h) {
                        try {
                            Device b2 = SmartHomeDeviceManager.a().b(modelUpdateInfo.b);
                            if (b2 != null) {
                                if (b2.isOnline) {
                                    z = true;
                                    break;
                                }
                            }
                        } catch (Exception unused) {
                            continue;
                        }
                    }
                    i++;
                }
                if (!z) {
                    MessageCenter.this.i = 2;
                    MessageCenter.this.d();
                    return;
                }
                ProfileRedPointManager.a().a(list, (ProfileRedPointManager.IgnoreStateCallback) new ProfileRedPointManager.IgnoreStateCallback() {
                    public void a(boolean z) {
                        MessageCenter.this.l = z;
                    }
                });
                ModelUpdateManager.d().a((ModelUpdateManager.IgnoreStateCallback) new ModelUpdateManager.IgnoreStateCallback() {
                    public void a(final boolean z) {
                        Activity activity = (Activity) weakReference.get();
                        if (activity != null && !activity.isFinishing()) {
                            activity.runOnUiThread(new Runnable() {
                                public void run() {
                                    if (z) {
                                        if (z) {
                                            MessageCenter.this.i = 3;
                                        } else {
                                            MessageCenter.this.i = 1;
                                        }
                                        MessageCenter.this.d();
                                        MessageCenter.this.i = 3;
                                    } else {
                                        MessageCenter.this.i = 1;
                                    }
                                    MessageCenter.this.d();
                                }
                            });
                        }
                    }
                });
            }

            public void a(int i) {
                MessageCenter.this.i = 2;
                MessageCenter.this.d();
            }
        });
    }

    public void b(Activity activity) {
        Miio.b("MessageCenter", "checkAppUpdate");
        final WeakReference weakReference = new WeakReference(activity);
        AppUpdateManger.a().c((AppUpdateManger.UpdateEventListener) new AppUpdateManger.UpdateEventListener() {
            public void a() {
                if (AppUpdateManger.d()) {
                    MessageCenter.this.j = 1;
                    Activity activity = (Activity) weakReference.get();
                    if (activity != null && !activity.isFinishing()) {
                        boolean b2 = AppUpdateManger.b();
                        MessageCenter.this.m = ProfileRedPointManager.a().f();
                        if (AppUpdateManger.a().j() == 1) {
                            Activity activity2 = (Activity) weakReference.get();
                            if (activity2 != null && !activity2.isFinishing()) {
                                AppUpdateManger.a().b(activity2);
                            } else {
                                return;
                            }
                        } else if (AppUpdateManger.a().j() == 2) {
                            Activity activity3 = (Activity) weakReference.get();
                            if (activity3 != null && !activity3.isFinishing()) {
                                AppUpdateManger.a().c(activity3);
                            } else {
                                return;
                            }
                        } else if (b2) {
                            MessageCenter.this.j = 3;
                        }
                    } else {
                        return;
                    }
                } else {
                    MessageCenter.this.j = 2;
                }
                MessageCenter.this.d();
            }

            public void b() {
                MessageCenter.this.j = 2;
                MessageCenter.this.d();
            }
        });
    }

    public void g() {
        Miio.b("MessageCenter", "checkPluginUpdate");
        this.m = true;
        CoreApi.a().a(false, (CoreApi.UpdatePluginAllCallback) new CoreApi.UpdatePluginAllCallback() {
            public void a(PluginError pluginError) {
                MessageCenter.this.k = 2;
                MessageCenter.this.d();
            }

            public void a() {
                ArrayList arrayList = new ArrayList(CoreApi.a().O());
                boolean z = false;
                boolean z2 = true;
                for (int i = 0; i < arrayList.size(); i++) {
                    PluginRecord pluginRecord = (PluginRecord) arrayList.get(i);
                    if (pluginRecord.l() && pluginRecord.n()) {
                        PluginUpdateInfo j = pluginRecord.j();
                        if (j != null) {
                            z2 = ModelUpdateManager.d().a(pluginRecord.o(), j.e()) & z2;
                        }
                        z = true;
                    }
                }
                if (z) {
                    if (z2) {
                        MessageCenter.this.k = 3;
                    } else {
                        MessageCenter.this.k = 1;
                    }
                    MessageCenter.this.n = ProfileRedPointManager.a().a((List<PluginRecord>) arrayList);
                } else {
                    MessageCenter.this.k = 2;
                }
                MessageCenter.this.d();
            }
        });
    }

    public void a(String str, int i2) {
        PreferenceUtils.a(SHApplication.getAppContext(), SettingMainPageV2.c, PreferenceUtils.c(SHApplication.getAppContext(), SettingMainPageV2.c, 0) + i2);
        List<MessageCenterListener> list = this.t.get(str);
        if (list != null) {
            for (MessageCenterListener messageCenterListener : list) {
                if (str.equals(f10454a)) {
                    messageCenterListener.onCheckNewMessageFinished(i2);
                }
            }
        }
    }

    public void b(String str, Object obj, boolean z) {
        final List list = this.t.get(str);
        if (list != null) {
            final String str2 = str;
            final Object obj2 = obj;
            final boolean z2 = z;
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    for (MessageCenterListener messageCenterListener : list) {
                        if (str2.equals(MessageCenter.f10454a)) {
                            messageCenterListener.onCheckNewMessageFinished(((Integer) obj2).intValue());
                        } else if (str2.equals(MessageCenter.b)) {
                            messageCenterListener.onCheckUpdateFinished(((Boolean) obj2).booleanValue(), z2);
                        } else if (str2.equals(MessageCenter.c)) {
                            messageCenterListener.onCheckNewFeedbackFinished(((Integer) obj2).intValue());
                        } else if (str2.equals(MessageCenter.d)) {
                            messageCenterListener.onCheckSignNotifyFinished(((Boolean) obj2).booleanValue(), z2);
                        }
                    }
                }
            });
        }
    }

    public void a(String str, MessageCenterListener messageCenterListener) {
        List list = this.t.get(str);
        if (list == null) {
            list = new ArrayList();
            this.t.put(str, list);
        }
        if (!list.contains(messageCenterListener)) {
            list.add(messageCenterListener);
        }
    }

    public void b(String str, MessageCenterListener messageCenterListener) {
        List list = this.t.get(str);
        if (list != null) {
            list.remove(messageCenterListener);
        }
    }

    public void h() {
        LogUtil.a("MessageCenter", Constants.Event.SLOT_LIFECYCLE.DESTORY);
        l();
        this.o = null;
        this.v = null;
        u = null;
    }

    public void i() {
        LogUtil.a("MessageCenter", "checkVoiceControlRedPoint");
        if (this.q == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(RedPointManagerNew.k);
            this.q = new RedPointController(arrayList, false);
        }
        if (VoiceManager.a().e()) {
            this.q.a(RedPointManagerNew.k, true);
        } else {
            this.q.a(RedPointManagerNew.k, false);
        }
    }
}
