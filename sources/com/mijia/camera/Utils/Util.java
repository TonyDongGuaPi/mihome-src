package com.mijia.camera.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import com.mijia.app.AppCode;
import com.smarthome_camera.R;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.plugin.DeviceConstant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class Util {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7912a = "cn";
    public static final int b = 30000;
    public static final HashSet<String> c = new HashSet<>();
    private static final String d = "Util";

    public static int a(Resources resources, int i, boolean z) {
        if (z && i == 0) {
            return 3;
        }
        if (!z || i != 2) {
            return (i < 1 || i > 5 || i == 2) ? 1 : 2;
        }
        return 3;
    }

    public static int b(Resources resources, int i, boolean z) {
        if (i == 2 && z) {
            return 6;
        }
        if (i == 0 && z) {
            return 6;
        }
        if (i == 1) {
            return 2;
        }
        if (i == 3) {
            return 3;
        }
        if (i == 5) {
            return 4;
        }
        return i == 4 ? 5 : 1;
    }

    public static int g(Resources resources, int i) {
        if (i < 0) {
            return -1;
        }
        return i;
    }

    public static String a(Resources resources, int i) {
        String string = resources.getString(R.string.sd_card_status0);
        switch (i) {
            case 1:
                return resources.getString(R.string.sd_card_status1);
            case 2:
                return resources.getString(R.string.sd_card_status2);
            case 3:
                return resources.getString(R.string.sd_card_status3);
            case 4:
                return resources.getString(R.string.sd_card_status4);
            case 5:
                return resources.getString(R.string.sd_card_status5);
            default:
                return string;
        }
    }

    public static String b(Resources resources, int i) {
        switch (i) {
            case AppCode.m:
                return resources.getString(R.string.nas_recycle_week);
            case AppCode.n:
                return resources.getString(R.string.nas_recycle_month);
            case AppCode.o:
                return resources.getString(R.string.nas_recycle_3_months);
            case AppCode.p:
                return resources.getString(R.string.nas_recycle_6_months);
            case AppCode.q:
                return resources.getString(R.string.nas_recycle_12_months);
            default:
                return "";
        }
    }

    public static String c(Resources resources, int i) {
        if (i == 300) {
            return resources.getString(R.string.nas_sync_interval_real_time);
        }
        if (i == 3600) {
            return resources.getString(R.string.nas_sync_interval_1_hour);
        }
        if (i != 86400) {
            return resources.getString(R.string.nas_sync_interval_real_time);
        }
        return resources.getString(R.string.nas_sync_interval_1_day);
    }

    public static String d(Resources resources, int i) {
        if (i < 20) {
            return resources.getString(R.string.connect_step1);
        }
        if (i < 40) {
            return resources.getString(R.string.connect_step2);
        }
        if (i < 70) {
            return resources.getString(R.string.connect_step3);
        }
        if (i < 80) {
            return resources.getString(R.string.connect_step4);
        }
        if (i == 100) {
            return resources.getString(R.string.connect_step5);
        }
        return resources.getString(R.string.connect_step5);
    }

    public static Spanned e(Resources resources, int i) {
        if (i < 0) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resources.openRawResource(i)));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Html.fromHtml(sb.toString());
    }

    public static String f(Resources resources, int i) {
        if (i < 0) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resources.openRawResource(i)));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int b(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    static {
        c.add("SM-E7009");
        c.add("Redmi Note 4X");
    }

    public static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        Pattern compile = Pattern.compile("[._]+");
        String[] split = compile.split(str);
        String[] split2 = compile.split(str2);
        int min = Math.min(split.length, split2.length);
        for (int i = 0; i < min; i++) {
            if (split[i].compareTo(split2[i]) < 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean a() {
        String globalSettingServer = XmPluginHostApi.instance().getGlobalSettingServer();
        return !TextUtils.isEmpty(globalSettingServer) && globalSettingServer.toLowerCase().equals("cn");
    }

    public static boolean b() {
        String globalSettingServer = XmPluginHostApi.instance().getGlobalSettingServer();
        return !TextUtils.isEmpty(globalSettingServer) && globalSettingServer.toLowerCase().equals("cn");
    }

    public static int c() {
        return d() / 60;
    }

    public static int d() {
        try {
            return (TimeZone.getDefault().getRawOffset() + ((!TimeZone.getDefault().useDaylightTime() || !TimeZone.getDefault().inDaylightTime(new Date())) ? 0 : TimeZone.getDefault().getDSTSavings())) / 1000;
        } catch (Exception e) {
            e.printStackTrace();
            return -25200;
        }
    }

    public static boolean e() {
        String globalSettingServer = XmPluginHostApi.instance().getGlobalSettingServer();
        return !TextUtils.isEmpty(globalSettingServer) && globalSettingServer.toLowerCase().equals("cn");
    }

    public static int b(String str, String str2) {
        LogUtil.a(d, "009 019 sourceVersion:" + str + " targetVersion:" + str2);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return 0;
        }
        try {
            if (!str.contains(JSMethod.NOT_SET) || !str2.contains(JSMethod.NOT_SET)) {
                return 0;
            }
            String substring = str.substring(str.lastIndexOf(JSMethod.NOT_SET));
            String substring2 = str2.substring(str2.lastIndexOf(JSMethod.NOT_SET));
            if (!TextUtils.isEmpty(substring) && substring.startsWith(JSMethod.NOT_SET)) {
                substring = substring.substring(1);
            }
            if (!TextUtils.isEmpty(substring2) && substring2.startsWith(JSMethod.NOT_SET)) {
                substring2 = substring2.substring(1);
            }
            return d(substring, substring2);
        } catch (Exception e) {
            LogUtil.b(d, "compareDeviceMinorVersion009019 Exception:" + e.getLocalizedMessage());
            return -1;
        }
    }

    public static int c(String str, String str2) {
        LogUtil.a(d, "v3 sourceVersion:" + str + " targetVersion:" + str2);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return 0;
        }
        try {
            if (!str.contains(JSMethod.NOT_SET) || !str2.contains(JSMethod.NOT_SET)) {
                return 0;
            }
            String substring = str.substring(str.lastIndexOf(JSMethod.NOT_SET));
            String substring2 = str2.substring(str2.lastIndexOf(JSMethod.NOT_SET));
            if (!TextUtils.isEmpty(substring) && substring.startsWith(JSMethod.NOT_SET)) {
                substring = substring.substring(1);
            }
            if (!TextUtils.isEmpty(substring2) && substring2.startsWith(JSMethod.NOT_SET)) {
                substring2 = substring2.substring(1);
            }
            return d(substring, substring2);
        } catch (Exception e) {
            LogUtil.b(d, "compareDeviceMinorVersionV3 Exception:" + e.getLocalizedMessage());
            return -1;
        }
    }

    public static int d(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str.equals(str2)) {
            return 0;
        }
        String[] split = str.split("[._]");
        String[] split2 = str2.split("[._]");
        int min = Math.min(split.length, split2.length);
        long j = 0;
        int i = 0;
        while (i < min) {
            j = Long.parseLong(split[i]) - Long.parseLong(split2[i]);
            if (j != 0) {
                break;
            }
            i++;
        }
        if (j == 0) {
            for (int i2 = i; i2 < split.length; i2++) {
                if (Long.parseLong(split[i2]) > 0) {
                    return 1;
                }
            }
            while (i < split2.length) {
                if (Long.parseLong(split2[i]) > 0) {
                    return -1;
                }
                i++;
            }
            return 0;
        } else if (j > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                sb.append("0");
                sb.append(hexString);
            } else {
                sb.append(hexString);
            }
        }
        return sb.toString().toUpperCase();
    }

    public static String a(long j) {
        String str;
        if (j == 0) {
            return "";
        }
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        int i = instance.get(2);
        int i2 = instance.get(5);
        int i3 = instance.get(11);
        int i4 = instance.get(12);
        int i5 = i + 1;
        if ("zh".equals(XmPluginHostApi.instance().context().getResources().getConfiguration().locale.getLanguage())) {
            return new SimpleDateFormat("MM月dd日 HH:mm").format(instance.getTime());
        }
        switch (i5) {
            case 1:
                str = "Jan";
                break;
            case 2:
                str = "Feb";
                break;
            case 3:
                str = "Mar";
                break;
            case 4:
                str = "Apr";
                break;
            case 5:
                str = "May";
                break;
            case 6:
                str = "June";
                break;
            case 7:
                str = "July";
                break;
            case 8:
                str = "Aug";
                break;
            case 9:
                str = "Sept";
                break;
            case 10:
                str = "Oct";
                break;
            case 11:
                str = "Nov";
                break;
            case 12:
                str = "Dec";
                break;
            default:
                str = "Dec";
                break;
        }
        return str + "." + i2 + " " + i3 + ":" + i4;
    }

    public static byte[] a(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length() / 2; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }

    public static boolean e(String str, String str2) {
        boolean z = !TextUtils.isEmpty(str2) && "mijia.camera.v3".equals(str) && c(str2, "3.5.1_0070") > 0;
        boolean z2 = !TextUtils.isEmpty(str2) && DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equals(str);
        if (z || z2) {
            return true;
        }
        return false;
    }

    public static String a(long j, Resources resources) {
        String str;
        if (j == 0) {
            return "";
        }
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        int i = instance.get(2);
        int i2 = instance.get(5);
        int i3 = instance.get(11);
        int i4 = instance.get(12);
        int i5 = i + 1;
        if ("zh".equals(resources.getConfiguration().locale.getLanguage())) {
            return new SimpleDateFormat("MM月dd日 HH点mm分").format(instance.getTime());
        }
        switch (i5) {
            case 1:
                str = "Jan";
                break;
            case 2:
                str = "Feb";
                break;
            case 3:
                str = "Mar";
                break;
            case 4:
                str = "Apr";
                break;
            case 5:
                str = "May";
                break;
            case 6:
                str = "June";
                break;
            case 7:
                str = "July";
                break;
            case 8:
                str = "Aug";
                break;
            case 9:
                str = "Sept";
                break;
            case 10:
                str = "Oct";
                break;
            case 11:
                str = "Nov";
                break;
            case 12:
                str = "Dec";
                break;
            default:
                str = "Dec";
                break;
        }
        return str + "." + i2 + " " + i3 + ":" + i4;
    }
}
