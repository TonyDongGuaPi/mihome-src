package com.xiaomi.mishopsdk.utils;

import android.app.Application;
import android.text.TextUtils;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.util.AndroidUtil;
import com.xiaomi.mishopsdk.util.JSONUtil;
import com.xiaomi.mishopsdk.util.Log;
import com.xiaomi.mobilestats.StatService;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Deprecated
public class ExposeHelper {
    private static final String PATTERN_LOGCODE = "\"(?i)(?:log_code|mLogCode|logcode)\":\"([^\"]*)\"";
    private static final String TAG = "ExposeHelper";
    /* access modifiers changed from: private */
    public HashMap<String, HashSet<ViewItem>> mDelayExPoseItems = new HashMap<>();
    /* access modifiers changed from: private */
    public HashSet<String> mUploadedExposeItems = new HashSet<>();

    private static class ViewItem {
        String logCode;
        int pos;

        public ViewItem(String str, int i) {
            this.logCode = str;
            this.pos = i;
        }

        public String toString() {
            return this.logCode + " : " + this.pos;
        }
    }

    private static class SingletonHolder {
        /* access modifiers changed from: private */
        public static ExposeHelper INSTANCE = new ExposeHelper();

        private SingletonHolder() {
        }
    }

    public static ExposeHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public <T> void addExposeItem(final T t, final int i) {
        AndroidUtil.sLogStatViewQueue.postRunnable(new Runnable() {
            public void run() {
                if (t != null) {
                    String format = JSONUtil.format(t);
                    if (!TextUtils.isEmpty(format)) {
                        Matcher matcher = Pattern.compile(ExposeHelper.PATTERN_LOGCODE).matcher(format);
                        while (matcher.find()) {
                            String group = matcher.group(1);
                            if (!TextUtils.isEmpty(group) && !ExposeHelper.this.mUploadedExposeItems.contains(group)) {
                                ExposeHelper.this.mUploadedExposeItems.add(group);
                                Log.d(ExposeHelper.TAG, "uploadAllStashLogs: >>" + group + "=====" + i + " ==== ");
                                Application application = ShopApp.instance;
                                StringBuilder sb = new StringBuilder();
                                sb.append(i);
                                sb.append("");
                                StatService.onView(application, group, sb.toString(), new HashMap());
                            }
                        }
                        Log.d(ExposeHelper.TAG, "mUploadedExposeItems size=%s.", (Object) Integer.valueOf(ExposeHelper.this.mUploadedExposeItems.size()));
                    }
                }
            }
        });
    }

    public <T> void addDelayExposeItem(final T t, final int i, final String str) {
        AndroidUtil.sLogStatViewQueue.postRunnable(new Runnable() {
            public void run() {
                if (t != null) {
                    String format = JSONUtil.format(t);
                    if (!TextUtils.isEmpty(format)) {
                        Matcher matcher = Pattern.compile(ExposeHelper.PATTERN_LOGCODE).matcher(format);
                        while (matcher.find()) {
                            String group = matcher.group(1);
                            if (!TextUtils.isEmpty(group) && !ExposeHelper.this.mUploadedExposeItems.contains(group)) {
                                HashSet hashSet = (HashSet) ExposeHelper.this.mDelayExPoseItems.get(str);
                                if (hashSet == null) {
                                    HashSet hashSet2 = new HashSet();
                                    hashSet2.add(new ViewItem(group, i));
                                    ExposeHelper.this.mDelayExPoseItems.put(str, hashSet2);
                                } else {
                                    hashSet.add(new ViewItem(group, i));
                                }
                            }
                        }
                        Log.d(ExposeHelper.TAG, "mDelayExPoseItems size=%s.", (Object) Integer.valueOf(ExposeHelper.this.mDelayExPoseItems.size()));
                    }
                }
            }
        });
    }

    public void uploadAllStashLogs(final String str) {
        AndroidUtil.sLogStatViewQueue.postRunnable(new Runnable() {
            public void run() {
                HashSet hashSet = (HashSet) ExposeHelper.this.mDelayExPoseItems.get(str);
                if (hashSet != null && hashSet.size() > 0) {
                    Iterator it = hashSet.iterator();
                    while (it.hasNext()) {
                        ViewItem viewItem = (ViewItem) it.next();
                        if (!ExposeHelper.this.mUploadedExposeItems.contains(viewItem.logCode) && !TextUtils.isEmpty(viewItem.logCode)) {
                            Log.d(ExposeHelper.TAG, "uploadAllStashLogs: >>%s=====%s==== tag >> ", viewItem, Integer.valueOf(viewItem.pos), str);
                            Application application = ShopApp.instance;
                            String str = viewItem.logCode;
                            StatService.onView(application, str, viewItem.pos + "", new HashMap());
                            ExposeHelper.this.mUploadedExposeItems.add(viewItem.logCode);
                        }
                    }
                }
            }
        });
    }

    public void clearLogs() {
        if (this.mUploadedExposeItems != null) {
            this.mUploadedExposeItems.clear();
        }
        if (this.mDelayExPoseItems != null) {
            this.mDelayExPoseItems.clear();
        }
    }
}
