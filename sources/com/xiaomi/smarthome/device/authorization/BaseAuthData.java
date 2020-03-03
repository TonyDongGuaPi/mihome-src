package com.xiaomi.smarthome.device.authorization;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class BaseAuthData {

    /* renamed from: a  reason: collision with root package name */
    public String f15013a;
    public String b;
    public List<VoiceContrlData> c = new ArrayList();
    public List<VoiceContrlData> d = new ArrayList();

    /* JADX WARNING: Removed duplicated region for block: B:48:0x013c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
            r11 = this;
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r0 = r11.d
            r0.clear()
            com.xiaomi.smarthome.homeroom.HomeManager r0 = com.xiaomi.smarthome.homeroom.HomeManager.a()
            java.util.List r0 = r0.f()
            android.util.SparseArray r1 = new android.util.SparseArray
            r1.<init>()
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r3 = 0
            r4 = 0
        L_0x0019:
            int r5 = r0.size()
            if (r4 >= r5) goto L_0x0079
            java.lang.Object r5 = r0.get(r4)
            com.xiaomi.smarthome.homeroom.model.Home r5 = (com.xiaomi.smarthome.homeroom.model.Home) r5
            java.lang.String r5 = r5.k()
            r1.append(r4, r5)
            java.lang.Object r5 = r0.get(r4)
            com.xiaomi.smarthome.homeroom.model.Home r5 = (com.xiaomi.smarthome.homeroom.model.Home) r5
            java.util.List r5 = r5.d()
            if (r5 != 0) goto L_0x0039
            return
        L_0x0039:
            android.util.SparseArray r5 = new android.util.SparseArray
            r5.<init>()
            r6 = 0
        L_0x003f:
            java.lang.Object r7 = r0.get(r4)
            com.xiaomi.smarthome.homeroom.model.Home r7 = (com.xiaomi.smarthome.homeroom.model.Home) r7
            java.util.List r7 = r7.d()
            int r7 = r7.size()
            if (r6 >= r7) goto L_0x0069
            java.lang.Object r7 = r0.get(r4)
            com.xiaomi.smarthome.homeroom.model.Home r7 = (com.xiaomi.smarthome.homeroom.model.Home) r7
            java.util.List r7 = r7.d()
            java.lang.Object r7 = r7.get(r6)
            com.xiaomi.smarthome.homeroom.model.Room r7 = (com.xiaomi.smarthome.homeroom.model.Room) r7
            java.lang.String r7 = r7.e()
            r5.append(r6, r7)
            int r6 = r6 + 1
            goto L_0x003f
        L_0x0069:
            java.lang.Object r6 = r0.get(r4)
            com.xiaomi.smarthome.homeroom.model.Home r6 = (com.xiaomi.smarthome.homeroom.model.Home) r6
            java.lang.String r6 = r6.k()
            r2.put(r6, r5)
            int r4 = r4 + 1
            goto L_0x0019
        L_0x0079:
            com.xiaomi.smarthome.homeroom.HomeManager r0 = com.xiaomi.smarthome.homeroom.HomeManager.a()
            java.lang.String r4 = r11.f15013a
            com.xiaomi.smarthome.homeroom.model.Home r0 = r0.q(r4)
            if (r0 == 0) goto L_0x0099
            java.lang.String r4 = r0.k()
            int r4 = r1.indexOfValue(r4)
            if (r4 <= 0) goto L_0x0099
            r1.remove(r4)
            java.lang.String r0 = r0.k()
            r1.put(r3, r0)
        L_0x0099:
            r0 = 0
            r4 = r0
            r5 = r4
            r0 = 0
        L_0x009d:
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r6 = r11.c
            int r6 = r6.size()
            if (r0 >= r6) goto L_0x0252
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r6 = r11.c
            java.lang.Object r6 = r6.get(r0)
            if (r6 == 0) goto L_0x024e
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r6 = r11.c
            java.lang.Object r6 = r6.get(r0)
            com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData r6 = (com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData) r6
            java.lang.String r6 = r6.f15014a
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 == 0) goto L_0x00bf
            goto L_0x024e
        L_0x00bf:
            com.xiaomi.smarthome.auth.bindaccount.ThirdAccountBindManager r6 = com.xiaomi.smarthome.auth.bindaccount.ThirdAccountBindManager.a()
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r7 = r11.c
            java.lang.Object r7 = r7.get(r0)
            com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData r7 = (com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData) r7
            java.lang.String r7 = r7.f15014a
            com.xiaomi.smarthome.device.Device r6 = r6.c((java.lang.String) r7)
            if (r6 == 0) goto L_0x0153
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r7 = r11.c
            java.lang.Object r7 = r7.get(r0)
            com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData r7 = (com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData) r7
            r8 = 1
            r7.h = r8
            org.json.JSONObject r7 = r6.propInfo
            if (r7 == 0) goto L_0x0146
            org.json.JSONObject r7 = r6.propInfo     // Catch:{ JSONException -> 0x0108 }
            java.lang.String r8 = "third_cloud_device_group_id"
            java.lang.String r7 = r7.getString(r8)     // Catch:{ JSONException -> 0x0108 }
            org.json.JSONObject r6 = r6.propInfo     // Catch:{ JSONException -> 0x0108 }
            java.lang.String r8 = "third_cloud_device_icon"
            java.lang.String r6 = r6.getString(r8)     // Catch:{ JSONException -> 0x0108 }
            boolean r5 = android.text.TextUtils.isEmpty(r7)     // Catch:{ JSONException -> 0x0103 }
            if (r5 != 0) goto L_0x0101
            com.xiaomi.smarthome.auth.bindaccount.ThirdAccountBindManager r5 = com.xiaomi.smarthome.auth.bindaccount.ThirdAccountBindManager.a()     // Catch:{ JSONException -> 0x0103 }
            android.util.Pair r5 = r5.b((java.lang.String) r7)     // Catch:{ JSONException -> 0x0103 }
            r4 = r5
        L_0x0101:
            r5 = r6
            goto L_0x010c
        L_0x0103:
            r5 = move-exception
            r10 = r6
            r6 = r5
            r5 = r10
            goto L_0x0109
        L_0x0108:
            r6 = move-exception
        L_0x0109:
            r6.printStackTrace()
        L_0x010c:
            if (r4 == 0) goto L_0x0136
            java.lang.Object r6 = r4.second
            if (r6 == 0) goto L_0x0136
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r6 = r11.c
            java.lang.Object r6 = r6.get(r0)
            com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData r6 = (com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData) r6
            java.lang.Object r7 = r4.second
            com.xiaomi.smarthome.auth.bindaccount.model.ThirdAccount r7 = (com.xiaomi.smarthome.auth.bindaccount.model.ThirdAccount) r7
            java.lang.String r7 = r7.a()
            r6.f = r7
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r6 = r11.c     // Catch:{ Exception -> 0x0136 }
            java.lang.Object r6 = r6.get(r0)     // Catch:{ Exception -> 0x0136 }
            com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData r6 = (com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData) r6     // Catch:{ Exception -> 0x0136 }
            java.lang.Object r7 = r4.first     // Catch:{ Exception -> 0x0136 }
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ Exception -> 0x0136 }
            int r7 = r7.intValue()     // Catch:{ Exception -> 0x0136 }
            r6.j = r7     // Catch:{ Exception -> 0x0136 }
        L_0x0136:
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L_0x0146
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r6 = r11.c
            java.lang.Object r6 = r6.get(r0)
            com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData r6 = (com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData) r6
            r6.e = r5
        L_0x0146:
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r6 = r11.d
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r7 = r11.c
            java.lang.Object r7 = r7.get(r0)
            r6.add(r7)
            goto L_0x024e
        L_0x0153:
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r6 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r7 = r11.c
            java.lang.Object r7 = r7.get(r0)
            com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData r7 = (com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData) r7
            java.lang.String r7 = r7.f15014a
            com.xiaomi.smarthome.device.Device r6 = r6.b((java.lang.String) r7)
            r7 = 2131499574(0x7f0c1a36, float:1.8622801E38)
            if (r6 == 0) goto L_0x0223
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r8 = r11.c
            java.lang.Object r8 = r8.get(r0)
            com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData r8 = (com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData) r8
            r8.h = r3
            boolean r8 = r6.isHomeShared()
            if (r8 != 0) goto L_0x0201
            com.xiaomi.smarthome.homeroom.HomeManager r7 = com.xiaomi.smarthome.homeroom.HomeManager.a()
            java.lang.String r8 = r6.did
            com.xiaomi.smarthome.homeroom.model.Home r7 = r7.q(r8)
            com.xiaomi.smarthome.homeroom.HomeManager r8 = com.xiaomi.smarthome.homeroom.HomeManager.a()
            java.lang.String r6 = r6.did
            java.lang.String r6 = r8.r(r6)
            if (r7 == 0) goto L_0x01b6
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r8 = r11.c
            java.lang.Object r8 = r8.get(r0)
            com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData r8 = (com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData) r8
            java.lang.String r9 = r7.k()
            r8.f = r9
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r8 = r11.c
            java.lang.Object r8 = r8.get(r0)
            com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData r8 = (com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData) r8
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r9 = r11.c
            java.lang.Object r9 = r9.get(r0)
            com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData r9 = (com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData) r9
            java.lang.String r9 = r9.f
            int r9 = r1.indexOfValue(r9)
            r8.j = r9
        L_0x01b6:
            boolean r8 = android.text.TextUtils.isEmpty(r6)
            if (r8 != 0) goto L_0x0217
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r8 = r11.c
            java.lang.Object r8 = r8.get(r0)
            com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData r8 = (com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData) r8
            r8.g = r6
            if (r7 == 0) goto L_0x0217
            java.lang.String r6 = r7.k()
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L_0x0217
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r6 = r11.c
            java.lang.Object r6 = r6.get(r0)
            com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData r6 = (com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData) r6
            java.lang.String r8 = r7.k()
            java.lang.Object r8 = r2.get(r8)
            if (r8 == 0) goto L_0x01fd
            java.lang.String r7 = r7.k()
            java.lang.Object r7 = r2.get(r7)
            android.util.SparseArray r7 = (android.util.SparseArray) r7
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r8 = r11.c
            java.lang.Object r8 = r8.get(r0)
            com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData r8 = (com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData) r8
            java.lang.String r8 = r8.g
            int r7 = r7.indexOfValue(r8)
            goto L_0x01fe
        L_0x01fd:
            r7 = -1
        L_0x01fe:
            r6.k = r7
            goto L_0x0217
        L_0x0201:
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r6 = r11.c
            java.lang.Object r6 = r6.get(r0)
            com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData r6 = (com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData) r6
            android.content.Context r8 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            android.content.res.Resources r8 = r8.getResources()
            java.lang.String r7 = r8.getString(r7)
            r6.g = r7
        L_0x0217:
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r6 = r11.d
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r7 = r11.c
            java.lang.Object r7 = r7.get(r0)
            r6.add(r7)
            goto L_0x024e
        L_0x0223:
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r6 = r11.c
            java.lang.Object r6 = r6.get(r0)
            com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData r6 = (com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData) r6
            r6.h = r3
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r6 = r11.c
            java.lang.Object r6 = r6.get(r0)
            com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData r6 = (com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData) r6
            android.content.Context r8 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            android.content.res.Resources r8 = r8.getResources()
            java.lang.String r7 = r8.getString(r7)
            r6.g = r7
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r6 = r11.d
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r7 = r11.c
            java.lang.Object r7 = r7.get(r0)
            r6.add(r7)
        L_0x024e:
            int r0 = r0 + 1
            goto L_0x009d
        L_0x0252:
            java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData$VoiceContrlData> r0 = r11.d
            com.xiaomi.smarthome.device.authorization.DeviceAuthData.b((java.util.List<com.xiaomi.smarthome.device.authorization.BaseAuthData.VoiceContrlData>) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.authorization.BaseAuthData.a():void");
    }

    public static class VoiceContrlData implements Comparable<VoiceContrlData> {

        /* renamed from: a  reason: collision with root package name */
        public String f15014a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public int h = -1;
        public int i;
        public int j = -1;
        public int k = -1;

        public VoiceContrlData() {
        }

        public VoiceContrlData(String str, String str2, int i2) {
            this.f15014a = str;
            this.b = str2;
            this.i = i2;
        }

        /* renamed from: a */
        public VoiceContrlData clone() {
            VoiceContrlData voiceContrlData = new VoiceContrlData();
            voiceContrlData.f15014a = this.f15014a;
            voiceContrlData.b = this.b;
            return voiceContrlData;
        }

        public static List<VoiceContrlData> a(List<VoiceContrlData> list) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < list.size(); i2++) {
                arrayList.add(list.get(i2).clone());
            }
            return arrayList;
        }

        /* renamed from: a */
        public int compareTo(@NonNull VoiceContrlData voiceContrlData) {
            if (this.h != voiceContrlData.h) {
                return this.h - voiceContrlData.h;
            }
            if (this.j != voiceContrlData.j) {
                if (this.j < 0) {
                    return 1;
                }
                if (voiceContrlData.j < 0) {
                    return -1;
                }
                return this.j - voiceContrlData.j;
            } else if (this.k == voiceContrlData.k) {
                return this.i - voiceContrlData.i;
            } else {
                if (this.k < 0) {
                    return 1;
                }
                if (voiceContrlData.k < 0) {
                    return -1;
                }
                return this.k - voiceContrlData.k;
            }
        }
    }
}
