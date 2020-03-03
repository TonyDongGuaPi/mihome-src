package com.xiaomi.smarthome.scenenew.lumiscene;

public class ActionExtra {
    private static final ActionExtra B = new ActionExtra();

    /* renamed from: a  reason: collision with root package name */
    private static final String f21945a = "^lumi\\.(\\S+)\\.door_bell$";
    private static final String b = "^lumi\\.(\\S+)\\.play_specify_fm$";
    private static final String c = "^lumi\\.(\\S+)\\.play_music_new$";
    private static final String d = "^lumi\\.(\\S+)\\.adjust_fm_vol$";
    private static final String e = "^lumi\\.(\\S*)gateway(\\S*)\\.adjust_bright$";
    private static final String f = "^event\\.(\\S+)\\.rotate$";
    private static final String g = "^lumi\\.(\\S+)\\.set_curtain_level$";
    private static final String h = "^lumi\\.(\\S*)acpartner(\\S*)\\.set_on$";
    private static final String i = "^lumi\\.(\\S*)acpartner(\\S*)\\.set_off$";
    private static final String j = "^lumi\\.(\\S*)acpartner(\\S*)\\.set_ac$";
    private static final String k = "^lumi\\.(\\S*)acpartner(\\S*)\\.toggle_ac$";
    private static final String l = "^lumi\\.(\\S*)acpartner(\\S*)\\.send_other_ele_cmd$";
    private static final String m = "^lumi\\.(\\S*)lock(\\S*)\\.set_spk_vol$";
    private static final String n = "^lumi\\.(\\S*)lock(\\S*)\\.set_door_vol$";
    private static final String o = "^lumi\\.(\\S*)light.(\\S*)\\.adjust_ct$";
    private static final String p = "^lumi\\.(\\S*)light.(\\S*)\\.adjust_bright$";
    private static final String q = "^lumi\\.(\\S*)light.(\\S*)\\.set_bright$";
    private static final String r = "^lumi\\.(\\S*)light.(\\S*)\\.set_ct$";
    private static final String s = "^lumi\\.(\\S*)light.(\\S*)\\.set_rgb$";
    private static final String t = "^lumi\\.(\\S*)dimmer.rgbegl01(\\S*)\\.set_rgb$";
    private static final String u = "^lumi\\.(\\S*)airrtc.tcp(\\S*)\\.set_ac$";
    private static final String v = "^lumi\\.(\\S*)airrtc.vrfegl01(\\S*)\\.set_power$";
    private static final String w = "^lumi\\.(\\S*)airrtc.vrfegl01(\\S*)\\.set_ac$";
    private static final String x = "^lumi\\.(\\S*)camera(\\S*)\\.set_video$";
    private static final String y = "^lumi\\.(\\S*)camera(\\S*)\\.record_video$";
    private static final String z = "^lumi\\.(\\S*)curtain(\\S*)\\.adjust_curtain_level$";
    private String[] A = {g, h, i, j, k, m, n, q, r, u, s, t};

    public static ActionExtra a() {
        return B;
    }

    private ActionExtra() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:106:0x0289 A[Catch:{ Exception -> 0x02a7 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.xiaomi.smarthome.device.api.SceneInfo.SceneAction r8, com.xiaomi.smarthome.device.api.SceneInfo r9, java.lang.String r10, java.lang.String r11) {
        /*
            r7 = this;
            if (r8 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.String[] r0 = r7.A     // Catch:{ Exception -> 0x02a7 }
            java.util.List r0 = java.util.Arrays.asList(r0)     // Catch:{ Exception -> 0x02a7 }
            boolean r0 = r0.contains(r11)     // Catch:{ Exception -> 0x02a7 }
            r1 = 1
            if (r0 == 0) goto L_0x001d
            java.lang.Object r9 = r8.mActionValue     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r9 = r7.a(r10, r9)     // Catch:{ Exception -> 0x02a7 }
        L_0x001a:
            r10 = r9
            goto L_0x0285
        L_0x001d:
            java.lang.String r0 = "^lumi\\.(\\S+)\\.door_bell$"
            boolean r0 = r11.equalsIgnoreCase(r0)     // Catch:{ Exception -> 0x02a7 }
            if (r0 == 0) goto L_0x003b
            java.lang.Object r9 = r8.mActionValue     // Catch:{ Exception -> 0x02a7 }
            if (r9 != 0) goto L_0x0030
            java.lang.String r9 = "10000"
            java.lang.String r9 = r7.a(r10, r9)     // Catch:{ Exception -> 0x02a7 }
            goto L_0x001a
        L_0x0030:
            java.lang.Object r9 = r8.mActionValue     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r9 = r7.a(r10, r9)     // Catch:{ Exception -> 0x02a7 }
            goto L_0x001a
        L_0x003b:
            java.lang.String r0 = "^lumi\\.(\\S+)\\.play_specify_fm$"
            boolean r0 = r11.equalsIgnoreCase(r0)     // Catch:{ Exception -> 0x02a7 }
            r2 = 2
            r3 = 0
            if (r0 == 0) goto L_0x0089
            java.lang.Object r9 = r8.mActionValue     // Catch:{ Exception -> 0x02a7 }
            if (r9 == 0) goto L_0x0285
            org.json.JSONArray r9 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0083 }
            java.lang.Object r11 = r8.mActionValue     // Catch:{ JSONException -> 0x0083 }
            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0083 }
            r9.<init>(r11)     // Catch:{ JSONException -> 0x0083 }
            int r11 = r9.length()     // Catch:{ JSONException -> 0x0083 }
            if (r11 != r2) goto L_0x0285
            int r11 = r9.getInt(r3)     // Catch:{ JSONException -> 0x0083 }
            int r9 = r9.getInt(r1)     // Catch:{ JSONException -> 0x0083 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0083 }
            r0.<init>()     // Catch:{ JSONException -> 0x0083 }
            r0.append(r11)     // Catch:{ JSONException -> 0x0083 }
            java.lang.String r11 = ","
            r0.append(r11)     // Catch:{ JSONException -> 0x0083 }
            r0.append(r3)     // Catch:{ JSONException -> 0x0083 }
            java.lang.String r11 = ","
            r0.append(r11)     // Catch:{ JSONException -> 0x0083 }
            r0.append(r9)     // Catch:{ JSONException -> 0x0083 }
            java.lang.String r9 = r0.toString()     // Catch:{ JSONException -> 0x0083 }
            java.lang.String r9 = r7.a(r10, r9)     // Catch:{ JSONException -> 0x0083 }
            goto L_0x001a
        L_0x0083:
            r9 = move-exception
            r9.printStackTrace()     // Catch:{ Exception -> 0x02a7 }
            goto L_0x0285
        L_0x0089:
            java.lang.String r0 = "^lumi\\.(\\S+)\\.play_music_new$"
            boolean r0 = r11.equalsIgnoreCase(r0)     // Catch:{ Exception -> 0x02a7 }
            if (r0 == 0) goto L_0x00de
            java.lang.Object r9 = r8.mActionValue     // Catch:{ Exception -> 0x02a7 }
            if (r9 == 0) goto L_0x0285
            org.json.JSONArray r9 = new org.json.JSONArray     // Catch:{ JSONException -> 0x00d8 }
            java.lang.Object r11 = r8.mActionValue     // Catch:{ JSONException -> 0x00d8 }
            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x00d8 }
            r9.<init>(r11)     // Catch:{ JSONException -> 0x00d8 }
            int r11 = r9.length()     // Catch:{ JSONException -> 0x00d8 }
            if (r11 != r2) goto L_0x0285
            java.lang.String r11 = r9.getString(r3)     // Catch:{ JSONException -> 0x00d8 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ JSONException -> 0x00d8 }
            int r11 = r11.intValue()     // Catch:{ JSONException -> 0x00d8 }
            int r9 = r9.getInt(r1)     // Catch:{ JSONException -> 0x00d8 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00d8 }
            r0.<init>()     // Catch:{ JSONException -> 0x00d8 }
            r0.append(r11)     // Catch:{ JSONException -> 0x00d8 }
            java.lang.String r11 = ","
            r0.append(r11)     // Catch:{ JSONException -> 0x00d8 }
            r0.append(r3)     // Catch:{ JSONException -> 0x00d8 }
            java.lang.String r11 = ","
            r0.append(r11)     // Catch:{ JSONException -> 0x00d8 }
            r0.append(r9)     // Catch:{ JSONException -> 0x00d8 }
            java.lang.String r9 = r0.toString()     // Catch:{ JSONException -> 0x00d8 }
            java.lang.String r9 = r7.a(r10, r9)     // Catch:{ JSONException -> 0x00d8 }
            goto L_0x001a
        L_0x00d8:
            r9 = move-exception
            r9.printStackTrace()     // Catch:{ Exception -> 0x02a7 }
            goto L_0x0285
        L_0x00de:
            java.lang.String r0 = "^lumi\\.(\\S+)\\.adjust_fm_vol$"
            boolean r0 = r11.equalsIgnoreCase(r0)     // Catch:{ Exception -> 0x02a7 }
            r4 = 16
            if (r0 != 0) goto L_0x0216
            java.lang.String r0 = "^lumi\\.(\\S*)gateway(\\S*)\\.adjust_bright$"
            boolean r0 = r11.equalsIgnoreCase(r0)     // Catch:{ Exception -> 0x02a7 }
            if (r0 != 0) goto L_0x0216
            java.lang.String r0 = "^lumi\\.(\\S*)light.(\\S*)\\.adjust_bright$"
            boolean r0 = r11.equalsIgnoreCase(r0)     // Catch:{ Exception -> 0x02a7 }
            if (r0 != 0) goto L_0x0216
            java.lang.String r0 = "^lumi\\.(\\S*)light.(\\S*)\\.adjust_ct$"
            boolean r0 = r11.equalsIgnoreCase(r0)     // Catch:{ Exception -> 0x02a7 }
            if (r0 != 0) goto L_0x0216
            java.lang.String r0 = "^lumi\\.(\\S*)curtain(\\S*)\\.adjust_curtain_level$"
            boolean r0 = r11.equalsIgnoreCase(r0)     // Catch:{ Exception -> 0x02a7 }
            if (r0 == 0) goto L_0x010a
            goto L_0x0216
        L_0x010a:
            java.lang.String r9 = "^lumi\\.(\\S*)airrtc.vrfegl01(\\S*)\\.set_power$"
            boolean r9 = r11.equalsIgnoreCase(r9)     // Catch:{ Exception -> 0x02a7 }
            if (r9 == 0) goto L_0x016a
            org.json.JSONArray r9 = new org.json.JSONArray     // Catch:{ Exception -> 0x02a7 }
            java.lang.Object r11 = r8.mActionValue     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x02a7 }
            r9.<init>(r11)     // Catch:{ Exception -> 0x02a7 }
            int r11 = r9.length()     // Catch:{ Exception -> 0x02a7 }
            if (r11 != r2) goto L_0x0285
            java.lang.String r11 = r9.getString(r3)     // Catch:{ Exception -> 0x02a7 }
            int r9 = r9.getInt(r1)     // Catch:{ Exception -> 0x02a7 }
            int r9 = r9 + r1
            java.lang.String r0 = "\"channelIndex\""
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r9 = r10.replace(r0, r9)     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r10 = "on"
            boolean r10 = r11.equals(r10)     // Catch:{ Exception -> 0x0164 }
            if (r10 == 0) goto L_0x014d
            java.lang.String r10 = "\"ac_state\""
            r11 = 536870656(0x1fffff00, float:1.0841856E-19)
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ Exception -> 0x0164 }
            java.lang.String r10 = r9.replace(r10, r11)     // Catch:{ Exception -> 0x0164 }
            goto L_0x0285
        L_0x014d:
            java.lang.String r10 = "off"
            boolean r10 = r11.equals(r10)     // Catch:{ Exception -> 0x0164 }
            if (r10 == 0) goto L_0x001a
            java.lang.String r10 = "\"ac_state\""
            r11 = 268435200(0xfffff00, float:2.5243164E-29)
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ Exception -> 0x0164 }
            java.lang.String r10 = r9.replace(r10, r11)     // Catch:{ Exception -> 0x0164 }
            goto L_0x0285
        L_0x0164:
            r10 = move-exception
            r6 = r10
            r10 = r9
            r9 = r6
            goto L_0x02a8
        L_0x016a:
            java.lang.String r9 = "^lumi\\.(\\S*)airrtc.vrfegl01(\\S*)\\.set_ac$"
            boolean r9 = r11.equalsIgnoreCase(r9)     // Catch:{ Exception -> 0x02a7 }
            if (r9 == 0) goto L_0x01a2
            org.json.JSONArray r9 = new org.json.JSONArray     // Catch:{ Exception -> 0x02a7 }
            java.lang.Object r11 = r8.mActionValue     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x02a7 }
            r9.<init>(r11)     // Catch:{ Exception -> 0x02a7 }
            int r11 = r9.length()     // Catch:{ Exception -> 0x02a7 }
            if (r11 != r2) goto L_0x0285
            int r11 = r9.getInt(r3)     // Catch:{ Exception -> 0x02a7 }
            int r9 = r9.getInt(r1)     // Catch:{ Exception -> 0x02a7 }
            int r9 = r9 + r1
            java.lang.String r0 = "\"channelIndex\""
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r9 = r10.replace(r0, r9)     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r10 = "\"ac_state\""
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ Exception -> 0x0164 }
            java.lang.String r10 = r9.replace(r10, r11)     // Catch:{ Exception -> 0x0164 }
            goto L_0x0285
        L_0x01a2:
            java.lang.String r9 = "^lumi\\.(\\S*)camera(\\S*)\\.set_video$"
            boolean r9 = r11.equalsIgnoreCase(r9)     // Catch:{ Exception -> 0x02a7 }
            if (r9 == 0) goto L_0x01de
            java.lang.String r9 = "off"
            java.lang.Object r11 = r8.mActionValue     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x02a7 }
            boolean r9 = r9.equals(r11)     // Catch:{ Exception -> 0x02a7 }
            if (r9 == 0) goto L_0x01c0
            java.lang.String r9 = "0"
            java.lang.String r9 = r7.a(r10, r9)     // Catch:{ Exception -> 0x02a7 }
            goto L_0x001a
        L_0x01c0:
            java.lang.String r9 = "on"
            java.lang.Object r11 = r8.mActionValue     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x02a7 }
            boolean r9 = r9.equals(r11)     // Catch:{ Exception -> 0x02a7 }
            if (r9 == 0) goto L_0x01d6
            java.lang.String r9 = "1"
            java.lang.String r9 = r7.a(r10, r9)     // Catch:{ Exception -> 0x02a7 }
            goto L_0x001a
        L_0x01d6:
            java.lang.String r9 = "2"
            java.lang.String r9 = r7.a(r10, r9)     // Catch:{ Exception -> 0x02a7 }
            goto L_0x001a
        L_0x01de:
            java.lang.String r9 = "^lumi\\.(\\S*)camera(\\S*)\\.record_video$"
            boolean r9 = r11.equalsIgnoreCase(r9)     // Catch:{ Exception -> 0x02a7 }
            if (r9 == 0) goto L_0x01ee
            java.lang.String r9 = "12"
            java.lang.String r9 = r7.a(r10, r9)     // Catch:{ Exception -> 0x02a7 }
            goto L_0x001a
        L_0x01ee:
            java.lang.String r9 = "^lumi\\.(\\S*)acpartner(\\S*)\\.send_other_ele_cmd$"
            boolean r9 = r11.equalsIgnoreCase(r9)     // Catch:{ Exception -> 0x02a7 }
            if (r9 == 0) goto L_0x020a
            java.lang.Object r9 = r8.mActionValue     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x02a7 }
            long r2 = java.lang.Long.parseLong(r9, r4)     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r9 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r9 = r7.a(r10, r9)     // Catch:{ Exception -> 0x02a7 }
            goto L_0x001a
        L_0x020a:
            java.lang.Object r9 = r8.mActionValue     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r9 = r7.a(r10, r9)     // Catch:{ Exception -> 0x02a7 }
            goto L_0x001a
        L_0x0216:
            r11 = 0
            java.util.List<com.xiaomi.smarthome.device.api.SceneInfo$SceneLaunch> r0 = r9.mLaunchList     // Catch:{ Exception -> 0x02a7 }
            if (r0 != 0) goto L_0x021c
            return
        L_0x021c:
            java.util.List<com.xiaomi.smarthome.device.api.SceneInfo$SceneLaunch> r9 = r9.mLaunchList     // Catch:{ Exception -> 0x02a7 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ Exception -> 0x02a7 }
        L_0x0222:
            boolean r0 = r9.hasNext()     // Catch:{ Exception -> 0x02a7 }
            if (r0 == 0) goto L_0x023a
            java.lang.Object r0 = r9.next()     // Catch:{ Exception -> 0x02a7 }
            com.xiaomi.smarthome.device.api.SceneInfo$SceneLaunch r0 = (com.xiaomi.smarthome.device.api.SceneInfo.SceneLaunch) r0     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r2 = r0.mEventString     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r5 = "^event\\.(\\S+)\\.rotate$"
            boolean r2 = r2.matches(r5)     // Catch:{ Exception -> 0x02a7 }
            if (r2 == 0) goto L_0x0222
            java.lang.String r11 = r0.mDid     // Catch:{ Exception -> 0x02a7 }
        L_0x023a:
            if (r11 == 0) goto L_0x027d
            java.lang.String r9 = "."
            int r9 = r11.indexOf(r9)     // Catch:{ Exception -> 0x02a7 }
            r0 = -1
            if (r9 == r0) goto L_0x027a
            int r9 = r9 + r1
            java.lang.String r9 = r11.substring(r9)     // Catch:{ Exception -> 0x02a7 }
            java.lang.Long r9 = java.lang.Long.valueOf(r9, r4)     // Catch:{ Exception -> 0x02a7 }
            long r4 = r9.longValue()     // Catch:{ Exception -> 0x02a7 }
            org.json.JSONArray r9 = new org.json.JSONArray     // Catch:{ Exception -> 0x02a7 }
            r9.<init>()     // Catch:{ Exception -> 0x02a7 }
            org.json.JSONArray r9 = r9.put(r4)     // Catch:{ Exception -> 0x02a7 }
            r11 = 12
            org.json.JSONArray r9 = r9.put(r11)     // Catch:{ Exception -> 0x02a7 }
            r11 = 3
            org.json.JSONArray r9 = r9.put(r11)     // Catch:{ Exception -> 0x02a7 }
            r11 = 85
            org.json.JSONArray r9 = r9.put(r11)     // Catch:{ Exception -> 0x02a7 }
            org.json.JSONArray r9 = r9.put(r3)     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x02a7 }
            java.lang.String r9 = r7.a(r10, r9)     // Catch:{ Exception -> 0x02a7 }
            goto L_0x001a
        L_0x027a:
            r9 = r10
            goto L_0x001a
        L_0x027d:
            java.lang.String r9 = "20"
            java.lang.String r9 = r7.a(r10, r9)     // Catch:{ Exception -> 0x02a7 }
            goto L_0x001a
        L_0x0285:
            int r9 = r8.mDelayTime     // Catch:{ Exception -> 0x02a7 }
            if (r9 <= 0) goto L_0x02ab
            org.json.JSONArray r9 = new org.json.JSONArray     // Catch:{ Exception -> 0x02a7 }
            r9.<init>(r10)     // Catch:{ Exception -> 0x02a7 }
            int r11 = r9.length()     // Catch:{ Exception -> 0x02a7 }
            int r0 = r11 + -2
            if (r0 < 0) goto L_0x02a2
            int r11 = r11 - r1
            if (r11 < 0) goto L_0x02a2
            r1 = 6
            r9.put(r0, r1)     // Catch:{ Exception -> 0x02a7 }
            int r0 = r8.mDelayTime     // Catch:{ Exception -> 0x02a7 }
            r9.put(r11, r0)     // Catch:{ Exception -> 0x02a7 }
        L_0x02a2:
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x02a7 }
            goto L_0x02ac
        L_0x02a7:
            r9 = move-exception
        L_0x02a8:
            r9.printStackTrace()
        L_0x02ab:
            r9 = r10
        L_0x02ac:
            r8.mExtra = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scenenew.lumiscene.ActionExtra.a(com.xiaomi.smarthome.device.api.SceneInfo$SceneAction, com.xiaomi.smarthome.device.api.SceneInfo, java.lang.String, java.lang.String):void");
    }

    private String a(String str, String str2) {
        return str.replace("\"x\"", str2);
    }
}
