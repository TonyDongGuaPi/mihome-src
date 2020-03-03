package com.xiaomi.smarthome.scene;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.auth.bindaccount.ThirdAccountBindManager;
import com.xiaomi.smarthome.config.AndroidCommonConfigManager;
import com.xiaomi.smarthome.config.model.LvMiSupportLocalInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.RecommendSceneItem;
import com.xiaomi.smarthome.device.utils.ClientIconMap;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.ApiHelper;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.lite.scene.SceneLogUtil;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.scene.api.SceneApi;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

public class SmartHomeSceneUtility {

    /* renamed from: a  reason: collision with root package name */
    public static final String f21464a = "mikey_click";
    public static final String b = "mikey_dbclick";
    public static final String c = "miband_sleep";
    public static final String d = "miband_awake";
    private static final String e = "SmartHomeSceneUtility";
    private static int[] f = {R.drawable.intelligence_icon_if_nor, R.drawable.intelligence_icon_ifonly_nor};
    private static String[] g = {"lumi.gateway.v1", "lumi.gateway.v2", "lumi.gateway.v3", "lumi.acpartner.v1", "lumi.acpartner.v2", "lumi.camera.v1", "lumi.camera.aq1", "lumi.acpartner.v3", "lumi.gateway.mitw01", "lumi.gateway.mieu01", "lumi.gateway.mihk01", "lumi.gateway.aqcn01", "lumi.gateway.lmuk01", "lumi.gateway.iragl01", "lumi.gateway.aqhm01", "lumi.camera.gwagl01", "lumi.gateway.aqhm02", "lumi.plug.mitw01", "lumi.sensor_cube.aqgl01", "lumi.relay.c2acn01", "lumi.lock.acn03", "lumi.airrtc.tcpecn02", "lumi.dimmer.cwegl01"};
    private static final String h = "com.xiaomi.smarthome.scene.smarthomelauncher";

    public static class SceneItemInfo {

        /* renamed from: a  reason: collision with root package name */
        public int f21465a = R.drawable.device_list_phone_no;
        public boolean b;
    }

    public static int a(int i) {
        switch (i) {
            case 167:
            case 173:
                return R.drawable.intelligence_icon_home_nor;
            case 168:
            case 174:
                return R.drawable.intelligence_icon_away_nor;
            case 169:
                return R.drawable.intelligence_icon_wakeup_nor;
            case 170:
                return R.drawable.intelligence_icon_sleep_nor;
            default:
                return R.drawable.intelligence_icon_default_nor;
        }
    }

    public static int b(int i) {
        switch (i) {
            case 167:
            case 173:
                return R.drawable.widget_icon_home_nor;
            case 168:
            case 174:
                return R.drawable.widget_icon_away_nor;
            case 169:
                return R.drawable.widget_icon_wakeup_nor;
            case 170:
                return R.drawable.widget_icon_sleep_nor;
            default:
                return R.drawable.widget_icon_default_nor;
        }
    }

    public static int c(int i) {
        switch (i) {
            case 167:
            case 173:
                return R.drawable.lite_recommend_home_normal;
            case 168:
            case 174:
                return R.drawable.lite_recommend_leave_normal;
            case 169:
                return R.drawable.lite_recommend_getup_normal;
            case 170:
                return R.drawable.lite_recommend_sleep_normal;
            default:
                return 0;
        }
    }

    public static int d(int i) {
        switch (i) {
            case 167:
                return R.drawable.client_page_scene_come_home_bg;
            case 168:
                return R.drawable.client_page_scene_leave_home_home_bg;
            case 169:
                return R.drawable.client_page_scene_get_up_bg;
            case 170:
                return R.drawable.client_page_scene_sleep_bg;
            default:
                return R.drawable.client_page_scene_user_define_bg;
        }
    }

    public static int e(int i) {
        switch (i) {
            case 167:
                return R.drawable.client_page_scene_come_home;
            case 168:
                return R.drawable.client_page_scene_leave_home;
            case 169:
                return R.drawable.client_page_scene_getup;
            case 170:
                return R.drawable.client_page_scene_sleep;
            default:
                return R.drawable.client_page_scene_user_define;
        }
    }

    public static String a(SceneApi.SmartHomeScene smartHomeScene) {
        String str;
        Device k;
        String str2;
        StringBuilder sb = new StringBuilder();
        if (smartHomeScene.l != null && smartHomeScene.l.size() > 0) {
            SceneApi.Condition condition = smartHomeScene.l.get(0);
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE) {
                Room p = HomeManager.a().p(condition.c.f21523a);
                str2 = (p == null ? LanguageUtil.a((Locale) null, (int) R.string.room_default) : p.e()) + " " + condition.c.b;
            } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.TIMER) {
                str2 = LanguageUtil.a((Locale) null, (int) R.string.smarthome_scene_start_timer);
            } else {
                str2 = c(SHApplication.getAppContext(), condition);
                if (str2.equalsIgnoreCase(SHApplication.getAppContext().getResources().getString(R.string.smarthome_scene_start_click))) {
                    str2 = "";
                }
            }
            sb.append(str2);
        }
        if (!TextUtils.isEmpty(sb.toString())) {
            sb.append("-");
        }
        if (smartHomeScene.k != null && smartHomeScene.k.size() > 0) {
            SceneApi.Action action = smartHomeScene.k.get(0);
            if (action.g instanceof SceneApi.SHSceneItemPayloadCommon) {
                Device b2 = SmartHomeDeviceManager.a().b(action.g.e);
                if (b2 == null) {
                    b2 = SmartHomeDeviceManager.a().l(action.g.e);
                }
                String str3 = "";
                if (b2 != null) {
                    str3 = b2.name;
                }
                if (str3.isEmpty() && (k = DeviceFactory.k(action.e)) != null) {
                    str3 = k.name;
                }
                str = str3 + " " + action.c;
            } else if (!(action.g instanceof SceneApi.SHSceneDelayPayload) || smartHomeScene.k.size() <= 1) {
                str = action != null ? action.b : "";
            } else {
                str = smartHomeScene.k.get(1).b;
            }
            if (str == null) {
                str = "";
            }
            sb.append(str);
        }
        return sb.toString();
    }

    public static void a(SimpleDraweeView simpleDraweeView, SceneApi.Condition condition) {
        if (condition == null) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.condition_click_icon));
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.TIMER) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.condition_timer_icon));
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE) {
            if (condition.c != null) {
                Device k = DeviceFactory.k(condition.c.d);
                if (k != null) {
                    DeviceFactory.b(k.model, simpleDraweeView);
                } else {
                    DeviceFactory.b((String) null, simpleDraweeView);
                }
            } else {
                DeviceFactory.b((String) null, simpleDraweeView);
            }
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_HOME) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.intelligence_icon_away_nor));
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.COME_HOME) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.intelligence_icon_home_nor));
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.PHONE_SMS) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.std_scene_icon_sms));
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.PHONE_CALL) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.std_scene_icon_phonecall));
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIKEY) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.std_icon_mikey_virtual));
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIBAND) {
            Device k2 = DeviceFactory.k(condition.f.d);
            if (k2 != null) {
                DeviceFactory.b(k2.model, simpleDraweeView);
            } else {
                DeviceFactory.b((String) null, simpleDraweeView);
            }
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.COME_LOC || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_LOC) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.std_scene_icon_wifi_poi));
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.ENTER_FENCE || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_FENCE) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.std_scene_icon_fence));
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.HUMIDITY) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.scene_humidity_icon));
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.SUN_RISE || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.SUN_SET) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.scene_sun_icon));
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.TEMPERATURE) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.scene_temperature_icon));
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.AQI) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.scene_pm25_icon));
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.CLICK) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.intelligence_icon_default_nor));
        } else {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.intelligence_icon_default_nor));
        }
    }

    public static SceneItemInfo a(SceneApi.Action action) {
        if (action == null) {
            return null;
        }
        SceneItemInfo sceneItemInfo = new SceneItemInfo();
        if (action.g instanceof SceneApi.SHScenePushPayload) {
            sceneItemInfo.b = true;
            sceneItemInfo.f21465a = R.drawable.device_list_phone;
        }
        Iterator<Device> it = SmartHomeDeviceManager.a().d().iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            Device next = it.next();
            if ((!next.isOwner() && !next.isFamily() && !next.isSubDevice()) || !next.isOnline) {
                z = true;
            }
            if (!z) {
                if ((action.g instanceof SceneApi.SHSceneItemPayloadCommon) && ((SceneApi.SHSceneItemPayloadCommon) action.g).e.equalsIgnoreCase(next.did)) {
                    sceneItemInfo.b = true;
                    sceneItemInfo.f21465a = ClientIconMap.a(next.model);
                }
                if (action.g instanceof SceneApi.SHScenePushPayload) {
                    sceneItemInfo.b = true;
                    sceneItemInfo.f21465a = R.drawable.device_list_phone;
                }
            }
        }
        for (Device next2 : SmartHomeDeviceManager.a().k()) {
            if (!((!next2.isOwner() && !next2.isFamily()) || !next2.isOnline) && (action.g instanceof SceneApi.SHSceneItemPayloadCommon) && ((SceneApi.SHSceneItemPayloadCommon) action.g).e.equalsIgnoreCase(next2.did)) {
                sceneItemInfo.b = true;
                sceneItemInfo.f21465a = ClientIconMap.a(next2.model);
            }
        }
        return sceneItemInfo;
    }

    public static void a(SimpleDraweeView simpleDraweeView, TextView textView, SceneApi.Action action) {
        if (action != null && action.g != null) {
            if (action.g instanceof SceneApi.SHScenePushPayload) {
                if (!TextUtils.isDigitsOnly(action.b)) {
                    textView.setText(action.b);
                } else {
                    textView.setText(R.string.smarthome_scene_push_action);
                }
                simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.std_scene_icon_push));
            } else if (action.g instanceof SceneApi.SHLiteScenePayload) {
                textView.setText(action.b);
                simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.scene_click_lite_scene_icon));
            } else if (action.g instanceof SceneApi.SHSceneAutoPayload) {
                textView.setText(action.b);
                simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.scene_auto_icon));
            } else {
                for (Device next : SmartHomeDeviceManager.a().d()) {
                    if ((next.isOwner() || next.isFamily()) && TextUtils.equals(next.did, action.g.e)) {
                        DeviceFactory.b(next.model, simpleDraweeView);
                        textView.setText(next.name);
                        return;
                    }
                }
                for (Device next2 : SmartHomeDeviceManager.a().k()) {
                    if (!((!next2.isOwner() && !next2.isFamily()) || !next2.isOnline) && next2.did.equals(action.g.e)) {
                        DeviceFactory.b(next2.model, simpleDraweeView);
                        textView.setText(next2.name);
                        return;
                    }
                }
                if (action.b != null && !TextUtils.isEmpty(action.e)) {
                    textView.setText(action.b);
                    DeviceFactory.b(action.e, simpleDraweeView);
                }
            }
        }
    }

    static void a(RecommendSceneItem recommendSceneItem, List<String> list) {
        RecommendSceneItem.RemommendSceneAction[] remommendSceneActionArr = recommendSceneItem.mRecommendActionList;
        int length = remommendSceneActionArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            RecommendSceneItem.RemommendSceneAction remommendSceneAction = remommendSceneActionArr[i];
            if (!(remommendSceneAction.mDeviceModels == null || CoreApi.a().d(remommendSceneAction.mDeviceModels[0]) == null)) {
                list.add(CoreApi.a().d(remommendSceneAction.mDeviceModels[0]).q());
            }
            i2++;
            if (i2 <= 4) {
                i++;
            } else {
                return;
            }
        }
    }

    public static String a(Context context, SceneApi.Condition condition) {
        if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.TIMER) {
            return context.getString(R.string.smarthome_scene_timer_title);
        }
        if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE) {
            String str = condition.c.c;
            if (str == null || !TextUtils.isEmpty(str)) {
                return str;
            }
            Device b2 = SmartHomeDeviceManager.a().b(condition.c.f21523a);
            if (b2 == null) {
                b2 = SmartHomeDeviceManager.a().l(condition.c.f21523a);
            }
            if (b2 != null) {
                str = b2.name;
            }
            if (str.isEmpty()) {
                return DeviceFactory.k(condition.c.d).name;
            }
            return str;
        } else if (condition.b()) {
            return condition.j.b;
        } else {
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.COME_LOC) {
                return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_enter_wifi);
            }
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_LOC) {
                return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_exit_wifi);
            }
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.ENTER_FENCE) {
                return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_enter_fence);
            }
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_FENCE) {
                return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_exit_fence);
            }
            return "";
        }
    }

    public static String b(Context context, SceneApi.Condition condition) {
        Device k;
        if (condition == null || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.CLICK) {
            return context.getString(R.string.smarthome_scene_start_click);
        }
        if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.TIMER) {
            return context.getString(R.string.smarthome_scene_timer_title);
        }
        if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE) {
            String str = condition.c.c;
            if (str == null || !TextUtils.isEmpty(str)) {
                return str;
            }
            Device b2 = SmartHomeDeviceManager.a().b(condition.c.f21523a);
            if (b2 == null) {
                b2 = SmartHomeDeviceManager.a().l(condition.c.f21523a);
            }
            if (b2 != null) {
                str = b2.name;
            }
            if (!str.isEmpty() || (k = DeviceFactory.k(condition.c.d)) == null) {
                return str;
            }
            return k.name;
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.COME_HOME) {
            return context.getString(R.string.condition_come_home);
        } else {
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_HOME) {
                return context.getString(R.string.condition_leave_home);
            }
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.PHONE_CALL) {
                return context.getString(R.string.scene_condition_phone_call);
            }
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.PHONE_SMS) {
                return context.getString(R.string.scene_condition_sms);
            }
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIKEY) {
                if (f21464a.equalsIgnoreCase(condition.e.j)) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_default_mikey_click);
                }
                if (b.equalsIgnoreCase(condition.e.j)) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_default_mikey_dbclick);
                }
                return "";
            } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIBAND) {
                if (c.equalsIgnoreCase(condition.f.j)) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_condition_miband_sleep);
                }
                if (d.equalsIgnoreCase(condition.f.j)) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_condition_miband_awake);
                }
                return "";
            } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.COME_LOC) {
                return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_enter_wifi);
            } else {
                if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_LOC) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_exit_wifi);
                }
                if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.ENTER_FENCE) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_enter_fence);
                }
                if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_FENCE) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_exit_fence);
                }
                if (condition.b()) {
                    return condition.j.g;
                }
                return "";
            }
        }
    }

    public static String c(Context context, SceneApi.Condition condition) {
        if (condition == null || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.CLICK) {
            return context.getString(R.string.smarthome_scene_start_click);
        }
        if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.TIMER) {
            return SmartHomeSceneTimerActivity.getTimerHint(context, condition.b != null ? condition.b.f21527a : null);
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE) {
            String str = condition.c.b;
            if (str == null || !TextUtils.isEmpty(str)) {
                return str;
            }
            Device b2 = SmartHomeDeviceManager.a().b(condition.c.f21523a);
            if (b2 == null) {
                b2 = SmartHomeDeviceManager.a().l(condition.c.f21523a);
            }
            if (b2 != null) {
                str = b2.name;
            }
            if (str.isEmpty()) {
                return DeviceFactory.k(condition.c.d).name;
            }
            return str;
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.COME_HOME) {
            return context.getString(R.string.condition_come_home);
        } else {
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_HOME) {
                return context.getString(R.string.condition_leave_home);
            }
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.PHONE_CALL) {
                return context.getString(R.string.scene_condition_phone_call);
            }
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.PHONE_SMS) {
                return context.getString(R.string.scene_condition_sms);
            }
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIKEY) {
                if (f21464a.equalsIgnoreCase(condition.e.j)) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_default_mikey_click);
                }
                if (b.equalsIgnoreCase(condition.e.j)) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_default_mikey_dbclick);
                }
                return "";
            } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIBAND) {
                if (c.equalsIgnoreCase(condition.f.j)) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_condition_miband_sleep);
                }
                if (d.equalsIgnoreCase(condition.f.j)) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_condition_miband_awake);
                }
                return "";
            } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.COME_LOC) {
                if (condition.h == null || TextUtils.isEmpty(condition.h.b)) {
                    return "";
                }
                return condition.h.b;
            } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_LOC) {
                if (condition.h == null || TextUtils.isEmpty(condition.h.b)) {
                    return "";
                }
                return condition.h.b;
            } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.ENTER_FENCE) {
                if (condition.i == null || TextUtils.isEmpty(condition.i.b)) {
                    return "";
                }
                return condition.i.b;
            } else if (condition.f21522a != SceneApi.Condition.LAUNCH_TYPE.LEAVE_FENCE) {
                return (!condition.b() || condition.j == null) ? "" : condition.j.g;
            } else {
                if (condition.i == null || TextUtils.isEmpty(condition.i.b)) {
                    return "";
                }
                return condition.i.b;
            }
        }
    }

    public static String d(Context context, SceneApi.Condition condition) {
        if (condition == null || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.CLICK) {
            return context.getString(R.string.smarthome_scene_start_click);
        }
        if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.TIMER) {
            return SmartHomeSceneTimerActivity.getTimerHint(context, condition.b != null ? condition.b.f21527a : null);
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE) {
            String str = condition.c.b;
            if (str == null || !TextUtils.isEmpty(str)) {
                return str;
            }
            Device b2 = SmartHomeDeviceManager.a().b(condition.c.f21523a);
            if (b2 == null) {
                b2 = SmartHomeDeviceManager.a().l(condition.c.f21523a);
            }
            if (b2 != null) {
                str = b2.name;
            }
            if (str.isEmpty()) {
                return DeviceFactory.k(condition.c.d).name;
            }
            return str;
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.COME_HOME) {
            return context.getString(R.string.condition_come_home);
        } else {
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_HOME) {
                return context.getString(R.string.condition_leave_home);
            }
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.PHONE_CALL) {
                return context.getString(R.string.scene_condition_phone_call);
            }
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.PHONE_SMS) {
                return context.getString(R.string.scene_condition_sms);
            }
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIKEY) {
                if (f21464a.equalsIgnoreCase(condition.e.j)) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_default_mikey_click);
                }
                if (b.equalsIgnoreCase(condition.e.j)) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_default_mikey_dbclick);
                }
                return "";
            } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIBAND) {
                if (c.equalsIgnoreCase(condition.f.j)) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_condition_miband_sleep);
                }
                if (d.equalsIgnoreCase(condition.f.j)) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_condition_miband_awake);
                }
                return "";
            } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.COME_LOC) {
                return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_enter_wifi);
            } else {
                if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_LOC) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_exit_wifi);
                }
                if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.ENTER_FENCE) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_enter_fence);
                }
                if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_FENCE) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_exit_fence);
                }
                if (!condition.b() || condition.j == null) {
                    return "";
                }
                if (!TextUtils.isEmpty(condition.j.g)) {
                    return condition.j.g;
                }
                return condition.j.b;
            }
        }
    }

    public static String e(Context context, SceneApi.Condition condition) {
        if (condition == null || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.CLICK) {
            return context.getString(R.string.smarthome_scene_start_click);
        }
        if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.TIMER) {
            return SmartHomeSceneTimerActivity.getTimerHint(context, condition.b != null ? condition.b.f21527a : null);
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE) {
            return condition.c.c + condition.c.b;
        } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.COME_HOME) {
            return context.getString(R.string.condition_come_home);
        } else {
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_HOME) {
                return context.getString(R.string.condition_leave_home);
            }
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIKEY) {
                if (f21464a.equalsIgnoreCase(condition.e.j)) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_default_mikey_click);
                }
                if (b.equalsIgnoreCase(condition.e.j)) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_default_mikey_dbclick);
                }
                return "";
            } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIBAND) {
                if (c.equalsIgnoreCase(condition.f.j)) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_condition_miband_sleep);
                }
                if (d.equalsIgnoreCase(condition.f.j)) {
                    return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.scene_condition_miband_awake);
                }
                return "";
            } else if (condition.b()) {
                return condition.j.g;
            } else {
                return "";
            }
        }
    }

    public static boolean a(Object obj, Object obj2) {
        if ((obj instanceof Integer) && (obj2 instanceof Integer)) {
            return obj.equals(obj2);
        }
        if ((obj instanceof Float) && (obj2 instanceof Float)) {
            return obj.equals(obj2);
        }
        if ((obj instanceof Double) && (obj2 instanceof Double)) {
            return obj.equals(obj2);
        }
        if ((obj instanceof String) && (obj2 instanceof String)) {
            return obj.equals(obj2);
        }
        String str = null;
        String obj3 = obj instanceof JSONObject ? obj.toString() : null;
        if (obj instanceof JSONArray) {
            obj3 = obj.toString();
        }
        if (obj2 instanceof JSONObject) {
            str = obj2.toString();
        }
        if (obj2 instanceof JSONArray) {
            str = obj2.toString();
        }
        if (obj3 != null && str != null) {
            return obj3.equals(str);
        }
        if (obj3 != null) {
            return obj3.equals(obj2);
        }
        if (str != null) {
            return str.equals(obj);
        }
        return obj.equals(obj2);
    }

    public static boolean b(SceneApi.SmartHomeScene smartHomeScene) {
        for (SceneApi.Condition condition : smartHomeScene.l) {
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.CLICK) {
                return true;
            }
        }
        return false;
    }

    public static int a(SceneApi.SmartHomeScene smartHomeScene, LinearLayout linearLayout) {
        if (linearLayout != null) {
            linearLayout.removeAllViews();
        }
        int i = 0;
        if (smartHomeScene == null || smartHomeScene.k == null || linearLayout == null) {
            return 0;
        }
        for (SceneApi.Action next : smartHomeScene.k) {
            i++;
            if (i >= 4) {
                return i;
            }
            if (next.g instanceof SceneApi.SHSceneDelayPayload) {
                linearLayout.addView(a(Integer.valueOf(R.drawable.std_scene_icon_delayed)));
            } else if (next.g instanceof SceneApi.SHScenePushPayload) {
                linearLayout.addView(a(Integer.valueOf(R.drawable.std_scene_icon_push)));
            } else if (next.g instanceof SceneApi.SHLiteScenePayload) {
                linearLayout.addView(a(Integer.valueOf(a(smartHomeScene.i))));
            } else if (next.g instanceof SceneApi.SHSceneAutoPayload) {
                linearLayout.addView(a(Integer.valueOf(R.drawable.scene_auto_icon)));
            } else if (next.g instanceof SceneApi.SHSceneItemPayloadCommon) {
                PluginRecord d2 = CoreApi.a().d(next.e);
                if (d2 != null) {
                    linearLayout.addView(b(d2.t()));
                } else {
                    Device c2 = next.g == null ? null : ThirdAccountBindManager.a().c(next.g.e);
                    if (c2 != null) {
                        String optString = c2.propInfo.optString(ThirdAccountBindManager.f13881a);
                        if (!TextUtils.isEmpty(optString)) {
                            linearLayout.addView(b(optString));
                        } else {
                            linearLayout.addView(a(Integer.valueOf(R.drawable.device_list_phone_no)));
                        }
                    } else {
                        linearLayout.addView(a(Integer.valueOf(DeviceFactory.q(next.e))));
                    }
                }
            } else {
                linearLayout.addView(a(Integer.valueOf(R.drawable.device_list_phone_no)));
            }
        }
        return i;
    }

    public static boolean a(SceneApi.Action action, SceneApi.SmartHomeScene smartHomeScene, SimpleDraweeView simpleDraweeView) {
        if (action == null) {
            return false;
        }
        simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
        if (action.g instanceof SceneApi.SHSceneDelayPayload) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.std_scene_icon_delayed));
            return true;
        } else if (action.g instanceof SceneApi.SHScenePushPayload) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.std_scene_icon_push));
            return true;
        } else if (action.g instanceof SceneApi.SHLiteScenePayload) {
            simpleDraweeView.setImageURI(CommonUtils.c(a(smartHomeScene.i)));
            return true;
        } else if (action.g instanceof SceneApi.SHSceneAutoPayload) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.scene_auto_icon));
            return true;
        } else if (action.g instanceof SceneApi.SHSceneItemPayloadCommon) {
            PluginRecord d2 = CoreApi.a().d(action.e);
            if (d2 != null) {
                simpleDraweeView.setImageURI(Uri.parse(d2.t()));
                return true;
            }
            simpleDraweeView.setImageURI(CommonUtils.c(DeviceFactory.q(action.e)));
            return true;
        } else {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.device_list_phone_no));
            return true;
        }
    }

    public static boolean a(Device device) {
        List<LvMiSupportLocalInfo> e2 = AndroidCommonConfigManager.a().e();
        if (device == null || TextUtils.isEmpty(device.model)) {
            return false;
        }
        if (e2 != null && e2.size() > 0) {
            LogUtil.a(e, "通过网络判断绿米设备");
            for (int i = 0; i < e2.size(); i++) {
                if (device.model.equalsIgnoreCase(e2.get(i).f13957a)) {
                    return true;
                }
            }
        }
        for (String equalsIgnoreCase : g) {
            if (equalsIgnoreCase.equalsIgnoreCase(device.model)) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String equalsIgnoreCase : g) {
            if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public static int f(int i) {
        if (i == 0) {
            return f[0];
        }
        if (i == 1) {
            return f[1];
        }
        return f[0];
    }

    public static String c(SceneApi.SmartHomeScene smartHomeScene) {
        Device n;
        String str;
        Device n2;
        String str2;
        boolean z = false;
        String str3 = null;
        for (SceneApi.Condition next : smartHomeScene.l) {
            if (!(next.c == null || next.c.d == null || (n2 = SmartHomeDeviceManager.a().n(next.c.f21523a)) == null)) {
                if (n2.isSubDevice()) {
                    str2 = n2.parentId;
                } else {
                    str2 = n2.did;
                }
                if (a(SmartHomeDeviceManager.a().n(str2))) {
                    str3 = str2;
                    z = true;
                }
            }
        }
        if (!z) {
            for (SceneApi.Action next2 : smartHomeScene.k) {
                if (!(next2.e == null || next2.g.e == null || (n = SmartHomeDeviceManager.a().n(next2.g.e)) == null)) {
                    if (n.isSubDevice()) {
                        str = n.parentId;
                    } else {
                        str = n.did;
                    }
                    if (a(SmartHomeDeviceManager.a().n(str))) {
                        str3 = str;
                        z = true;
                    }
                }
            }
        }
        if (z) {
            return str3;
        }
        return null;
    }

    public static void a(Activity activity, SceneApi.SmartHomeScene smartHomeScene, int i) {
        Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intent.putExtra("android.intent.extra.shortcut.NAME", smartHomeScene.g);
        Intent intent2 = new Intent(h);
        intent2.setComponent(new ComponentName(SHApplication.getAppContext().getPackageName(), SmartHomeLauncherActivity.class.getName()));
        intent.putExtra("duplicate", false);
        intent2.putExtra(SceneApi.d, smartHomeScene.f);
        intent2.putExtra("extra_scene_id", smartHomeScene.f);
        intent2.putExtra(SceneApi.b, CoreApi.a().s());
        intent.putExtra("android.intent.extra.shortcut.INTENT", intent2);
        if (ApiHelper.c) {
            ShortcutManager shortcutManager = (ShortcutManager) SHApplication.getAppContext().getSystemService(ShortcutManager.class);
            if (shortcutManager.isRequestPinShortcutSupported()) {
                shortcutManager.requestPinShortcut(new ShortcutInfo.Builder(activity, smartHomeScene.g).setIcon(Icon.createWithResource(activity, i)).setShortLabel(smartHomeScene.g).setIntent(intent2).build(), (IntentSender) null);
            } else {
                intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(activity, i));
                activity.sendBroadcast(intent);
            }
        } else {
            intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(activity, i));
            activity.sendBroadcast(intent);
        }
        ToastUtil.a((int) R.string.smarthome_scene_add_short_cut_success);
    }

    private static View b(String str) {
        View inflate = LayoutInflater.from(SHApplication.getAppContext()).inflate(R.layout.scene_action_icon_item, (ViewGroup) null);
        ((SimpleDraweeView) inflate.findViewById(R.id.icon_sdv)).setImageURI(Uri.parse(str));
        return inflate;
    }

    private static View a(Integer num) {
        View inflate = LayoutInflater.from(SHApplication.getAppContext()).inflate(R.layout.scene_action_icon_item, (ViewGroup) null);
        ((SimpleDraweeView) inflate.findViewById(R.id.icon_sdv)).setImageURI(CommonUtils.c(num.intValue()));
        return inflate;
    }

    private static String a(Object obj) {
        try {
            if (obj instanceof String) {
                return (String) obj;
            }
            if (!(obj instanceof Device) || TextUtils.isEmpty(((Device) obj).did)) {
                return null;
            }
            return ((Device) obj).did;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String a(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        if (i >= 0 && i <= 9) {
            sb.append("0");
        }
        sb.append(i);
        sb.append(":");
        if (i2 >= 0 && i2 <= 9) {
            sb.append("0");
        }
        sb.append(i2);
        return sb.toString();
    }

    public static void a(SceneApi.EffectiveTimeSpan effectiveTimeSpan, TextView textView, String str) {
        if (effectiveTimeSpan == null) {
            textView.setText(R.string.scene_exetime_all_day);
        } else if (effectiveTimeSpan.f21526a == effectiveTimeSpan.b && effectiveTimeSpan.c == effectiveTimeSpan.d) {
            textView.setText(R.string.scene_exetime_all_day);
        } else {
            int rawOffset = new GregorianCalendar().getTimeZone().getRawOffset();
            int convert = (int) TimeUnit.HOURS.convert((long) rawOffset, TimeUnit.MILLISECONDS);
            SceneLogUtil.a("offsetHOser----" + convert + "--mGTMoffeset---" + rawOffset);
            int i = (((effectiveTimeSpan.f21526a + convert) + -8) + 24) % 24;
            int i2 = effectiveTimeSpan.c;
            int i3 = (((effectiveTimeSpan.b + convert) + -8) + 24) % 24;
            int i4 = effectiveTimeSpan.d;
            if (i3 < i || (i3 == i && i4 < i2)) {
                textView.setText(a(i, i2) + "-" + a(i3, i4) + Operators.BRACKET_START_STR + str + Operators.BRACKET_END_STR);
                return;
            }
            textView.setText(a(i, i2) + "-" + a(i3, i4));
        }
    }
}
