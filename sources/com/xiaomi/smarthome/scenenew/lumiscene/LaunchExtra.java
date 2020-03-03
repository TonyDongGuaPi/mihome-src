package com.xiaomi.smarthome.scenenew.lumiscene;

import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.smarthome.device.api.SceneInfo;
import com.xiaomi.smarthome.framework.log.LogUtil;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LaunchExtra {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21949a = "^event\\.(\\S+)\\.no_motion$";
    private static final String b = "^event\\.(\\S*)sensor_magnet(\\S*)\\.no_close$";
    private static final String c = "^prop\\.(\\S+)\\.temperature$";
    private static final String d = "^prop\\.(\\S+)\\.humidity$";
    private static final String e = "^event\\.(\\S*)sensor_smoke(\\S*)\\.alarm$";
    private static final String f = "^event\\.(\\S*)sensor_natgas(\\S*)\\.alarm$";
    private static final String g = "^prop\\.(\\S*)lock(\\S*)\\.reminder_type$";
    private static final String h = "^event\\.(\\S*)vibration(\\S*)\\.open$";
    private static final String i = "^event\\.(\\S*)vibration(\\S*)\\.close$";
    private static final String j = "^event\\.(\\S*)vibration(\\S*)\\.no_close$";
    private static final String k = "^event\\.(\\S*)sensor_occupy(\\S*)\\.out$";
    private static final String l = "^prop\\.(\\S*)sensor_motion(\\S*)\\.illumination$";
    private static final String m = "^event\\.(\\S*)lock(\\S*)\\.fing_verified$";
    private static final String n = "^event\\.(\\S*)lock(\\S*)\\.psw_verified$";
    private static final String o = "^event\\.(\\S*)lock(\\S*)\\.card_verified$";
    private static final String p = "^event\\.(\\S*)lock(\\S*)\\.open_verified$";
    private static final LaunchExtra q = new LaunchExtra();
    private String[] r = {e, f, m, n, o};

    public static LaunchExtra a() {
        return q;
    }

    private LaunchExtra() {
    }

    public void a(SceneInfo.SceneLaunch sceneLaunch, String str, String str2) {
        String a2;
        LogUtil.a("lumiscene", "summer launch extra>>" + str + ",event>>" + str2);
        if (Arrays.asList(this.r).contains(str2)) {
            str = a(str, sceneLaunch.mEventValue.toString());
        } else if (str2.equalsIgnoreCase(f21949a)) {
            str = b(a(str, sceneLaunch.mEventValue.toString()), String.valueOf(sceneLaunch.mLaunchType));
        } else if (str2.equalsIgnoreCase(b)) {
            str = b(str, String.valueOf(sceneLaunch.mLaunchType));
        } else {
            if (str2.equalsIgnoreCase(c)) {
                if (sceneLaunch.mEventValue != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(sceneLaunch.mEventValue.toString());
                        int optInt = jSONObject.optInt("min", -2100);
                        int optInt2 = jSONObject.optInt("max", 6100);
                        if (optInt2 == 6000) {
                            a2 = a(str, new JSONArray().put(2).put(optInt).toString());
                        } else {
                            a2 = a(str, new JSONArray().put(3).put(optInt2).toString());
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            } else if (str2.equalsIgnoreCase(d)) {
                if (sceneLaunch.mEventValue != null) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(sceneLaunch.mEventValue.toString());
                        int optInt3 = jSONObject2.optInt("min", -100);
                        int optInt4 = jSONObject2.optInt("max", XiaomiOAuthConstants.SCOPE_MI_CLOUD_GALLERY);
                        if (optInt4 == 10000) {
                            a2 = a(str, new JSONArray().put(2).put(optInt3).toString());
                        } else {
                            a2 = a(str, new JSONArray().put(3).put(optInt4).toString());
                        }
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                }
            } else if (str2.equalsIgnoreCase(h) || str2.equalsIgnoreCase(i)) {
                if (sceneLaunch.mLaunchType != 0) {
                    str = b(str, String.valueOf(sceneLaunch.mLaunchType));
                }
            } else if (str2.equalsIgnoreCase(j)) {
                if (sceneLaunch.mLaunchType != 0) {
                    str = a(b(str, String.valueOf(sceneLaunch.mLaunchType)), sceneLaunch.mEventValue.toString());
                }
            } else if (str2.equalsIgnoreCase(g)) {
                if (sceneLaunch.mLaunchType != 0) {
                    str = a(str, String.valueOf(Integer.parseInt(sceneLaunch.mEventValue.toString(), 16)));
                }
            } else if (str2.equalsIgnoreCase(k)) {
                if (sceneLaunch.mLaunchType != 0) {
                    str = a(str, String.valueOf(((Integer) sceneLaunch.mEventValue).intValue() / 60));
                }
            } else if (str2.equalsIgnoreCase(l)) {
                if (sceneLaunch.mLaunchType != 0) {
                    try {
                        JSONObject jSONObject3 = new JSONObject(sceneLaunch.mEventValue.toString());
                        int optInt5 = jSONObject3.optInt("min", -1);
                        int optInt6 = jSONObject3.optInt("max", -1);
                        if (optInt5 != -1) {
                            a2 = a(str, new JSONArray().put(2).put(optInt5).toString());
                        } else if (optInt6 != -1) {
                            a2 = a(str, new JSONArray().put(3).put(optInt6).toString());
                        }
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                    }
                }
            } else if (!str2.equalsIgnoreCase(p)) {
                str = a(str, sceneLaunch.mEventValue.toString());
            } else if (sceneLaunch.mEventValue.toString().length() > 1) {
                str = a(b(str, "2"), "0");
            } else {
                str = a(b(str, "0"), sceneLaunch.mEventValue.toString());
            }
            str = a2;
        }
        sceneLaunch.mExtra = str;
    }

    private String a(String str, String str2) {
        return str.replace("\"x\"", str2);
    }

    private String b(String str, String str2) {
        return str.replace("\"y\"", str2);
    }
}
