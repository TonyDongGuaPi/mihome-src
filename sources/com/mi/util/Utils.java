package com.mi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.mi.MiApplicationContext;
import java.util.ArrayList;
import java.util.TreeSet;

public class Utils {

    public static class Preference {
        private static boolean checkContext(Context context) {
            return context == null;
        }

        public static void setStringSetPref(Context context, String str, TreeSet<String> treeSet) {
            SharedPreferences defaultSharedPreferences;
            SharedPreferences.Editor edit;
            if (!checkContext(context) && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
                edit.putStringSet(str, treeSet);
                edit.apply();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
            r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r1);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.util.Set<java.lang.String> getStringSetPref(android.content.Context r1, java.lang.String r2, java.util.TreeSet<java.lang.String> r3) {
            /*
                boolean r0 = checkContext(r1)
                if (r0 == 0) goto L_0x0007
                return r3
            L_0x0007:
                android.content.SharedPreferences r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r1)
                if (r1 == 0) goto L_0x0012
                java.util.Set r1 = r1.getStringSet(r2, r3)
                return r1
            L_0x0012:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mi.util.Utils.Preference.getStringSetPref(android.content.Context, java.lang.String, java.util.TreeSet):java.util.Set");
        }

        public static void setLongPref(String str, Long l) {
            setLongPref(MiApplicationContext.f1260a, str, l);
        }

        public static void setLongPref(Context context, String str, Long l) {
            SharedPreferences defaultSharedPreferences;
            SharedPreferences.Editor edit;
            if (!checkContext(context) && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
                edit.putLong(str, l.longValue());
                edit.apply();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
            r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r1);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static long getLongPref(android.content.Context r1, java.lang.String r2, long r3) {
            /*
                boolean r0 = checkContext(r1)
                if (r0 == 0) goto L_0x0007
                return r3
            L_0x0007:
                android.content.SharedPreferences r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r1)
                if (r1 == 0) goto L_0x0012
                long r1 = r1.getLong(r2, r3)
                return r1
            L_0x0012:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mi.util.Utils.Preference.getLongPref(android.content.Context, java.lang.String, long):long");
        }

        @Deprecated
        public static void setProcessStringPref(Context context, String str, String str2) {
            SharedPreferences sharedPreferences;
            SharedPreferences.Editor edit;
            if (context != null && (sharedPreferences = context.getSharedPreferences(str, 4)) != null && (edit = sharedPreferences.edit()) != null) {
                edit.putString(str, str2);
                edit.apply();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
            r1 = r1.getSharedPreferences(r2, 4);
         */
        @java.lang.Deprecated
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.lang.String getProcessStringPref(android.content.Context r1, java.lang.String r2, java.lang.String r3) {
            /*
                if (r1 != 0) goto L_0x0003
                return r3
            L_0x0003:
                r0 = 4
                android.content.SharedPreferences r1 = r1.getSharedPreferences(r2, r0)
                if (r1 == 0) goto L_0x000f
                java.lang.String r1 = r1.getString(r2, r3)
                return r1
            L_0x000f:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mi.util.Utils.Preference.getProcessStringPref(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
        }

        public static void setStringPref(String str, String str2) {
            setStringPref(MiApplicationContext.f1260a, str, str2);
        }

        public static void setStringPref(Context context, String str, String str2) {
            SharedPreferences defaultSharedPreferences;
            SharedPreferences.Editor edit;
            if (!checkContext(context) && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
                edit.putString(str, str2);
                edit.apply();
            }
        }

        public static void setStringPrefWithCommit(Context context, String str, String str2) {
            SharedPreferences defaultSharedPreferences;
            SharedPreferences.Editor edit;
            if (!checkContext(context) && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
                edit.putString(str, str2);
                edit.commit();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
            r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r1);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.lang.String getStringPref(android.content.Context r1, java.lang.String r2, java.lang.String r3) {
            /*
                boolean r0 = checkContext(r1)
                if (r0 == 0) goto L_0x0007
                return r3
            L_0x0007:
                android.content.SharedPreferences r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r1)
                if (r1 == 0) goto L_0x0012
                java.lang.String r1 = r1.getString(r2, r3)
                return r1
            L_0x0012:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mi.util.Utils.Preference.getStringPref(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
            r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static int getIntPref(android.content.Context r0, java.lang.String r1, int r2) {
            /*
                if (r0 != 0) goto L_0x0003
                return r2
            L_0x0003:
                android.content.SharedPreferences r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0)
                if (r0 == 0) goto L_0x000e
                int r0 = r0.getInt(r1, r2)
                return r0
            L_0x000e:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mi.util.Utils.Preference.getIntPref(android.content.Context, java.lang.String, int):int");
        }

        public static void setIntPref(Context context, String str, int i) {
            SharedPreferences defaultSharedPreferences;
            SharedPreferences.Editor edit;
            if (context != null && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
                edit.putInt(str, i);
                edit.apply();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
            r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r1);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static boolean getBooleanPref(android.content.Context r1, java.lang.String r2, boolean r3) {
            /*
                boolean r0 = checkContext(r1)
                if (r0 == 0) goto L_0x0007
                return r3
            L_0x0007:
                android.content.SharedPreferences r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r1)
                if (r1 == 0) goto L_0x0012
                boolean r1 = r1.getBoolean(r2, r3)
                return r1
            L_0x0012:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mi.util.Utils.Preference.getBooleanPref(android.content.Context, java.lang.String, boolean):boolean");
        }

        public static void setBooleanPref(Context context, String str, boolean z) {
            SharedPreferences defaultSharedPreferences;
            SharedPreferences.Editor edit;
            if (!checkContext(context) && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
                edit.putBoolean(str, z);
                edit.apply();
            }
        }

        @Deprecated
        public static void removeProcessPref(Context context, String str) {
            SharedPreferences sharedPreferences;
            SharedPreferences.Editor edit;
            if (!checkContext(context) && (sharedPreferences = context.getSharedPreferences(str, 4)) != null && (edit = sharedPreferences.edit()) != null) {
                edit.remove(str);
                edit.apply();
            }
        }

        public static void removePref(Context context, String str) {
            SharedPreferences defaultSharedPreferences;
            SharedPreferences.Editor edit;
            if (!checkContext(context) && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
                edit.remove(str);
                edit.apply();
            }
        }

        public static ArrayList<String> getAllPreferenceKey(Context context) {
            SharedPreferences defaultSharedPreferences;
            if (checkContext(context) || (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) == null) {
                return null;
            }
            ArrayList<String> arrayList = new ArrayList<>();
            for (String obj : defaultSharedPreferences.getAll().keySet()) {
                arrayList.add(obj.toString());
            }
            return arrayList;
        }
    }

    public static class Network {
        public static boolean isNetWorkConnected(Context context) {
            NetworkInfo activeNetworkInfo;
            if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                return false;
            }
            return true;
        }

        public static int getActiveNetworkType(Context context) {
            NetworkInfo activeNetworkInfo;
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return -1;
            }
            return activeNetworkInfo.getType();
        }

        public static boolean isWifiConnected(Context context) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
                return false;
            }
            return true;
        }

        public static String getWifiSSID(Context context) {
            return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getSSID();
        }

        public static boolean isMobileConnected(Context context) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
        }

        public static String getMacAddress() {
            WifiInfo connectionInfo = ((WifiManager) MiApplicationContext.f1260a.getSystemService("wifi")).getConnectionInfo();
            String macAddress = connectionInfo != null ? connectionInfo.getMacAddress() : null;
            if (!TextUtils.isEmpty(macAddress)) {
                Preference.setStringPref(MiApplicationContext.f1260a, "pref_key_user_mac_address", macAddress);
            }
            return macAddress;
        }
    }
}
