package com.xiaomi.base.utils;

import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExposeHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10022a = "\"(?i)(?:log_code|mLogCode|logcode)\":\"([^\"]*)\"";
    private static final String b = "ExposeHelper";
    /* access modifiers changed from: private */
    public HashMap<String, HashSet<ViewItem>> c = new HashMap<>();
    /* access modifiers changed from: private */
    public HashSet<String> d = new HashSet<>();

    public static ExposeHelper a() {
        return SingletonHolder.f10026a;
    }

    public <T> void a(final T t, final int i, final String str) {
        AndroidUtil.d.b(new Runnable() {
            public void run() {
                if (t != null && !TextUtils.isEmpty("")) {
                    Matcher matcher = Pattern.compile(ExposeHelper.f10022a).matcher("");
                    while (matcher.find()) {
                        String group = matcher.group(1);
                        if (!TextUtils.isEmpty(group) && !ExposeHelper.this.d.contains(group)) {
                            HashSet hashSet = (HashSet) ExposeHelper.this.c.get(str);
                            if (hashSet == null) {
                                HashSet hashSet2 = new HashSet();
                                hashSet2.add(new ViewItem(group, i));
                                ExposeHelper.this.c.put(str, hashSet2);
                            } else {
                                hashSet.add(new ViewItem(group, i));
                            }
                        }
                    }
                }
            }
        });
    }

    public <T> void a(final T t, final int i) {
        AndroidUtil.d.b(new Runnable() {
            public void run() {
                if (t != null && !TextUtils.isEmpty("")) {
                    Matcher matcher = Pattern.compile(ExposeHelper.f10022a).matcher("");
                    while (matcher.find()) {
                        String group = matcher.group(1);
                        if (!TextUtils.isEmpty(group) && !ExposeHelper.this.d.contains(group)) {
                            ExposeHelper.this.d.add(group);
                            Log.d(ExposeHelper.b, "uploadAllStashLogs: >>" + group + "=====" + i + " ==== ");
                        }
                    }
                }
            }
        });
    }

    public void b() {
        if (this.d != null) {
            this.d.clear();
        }
        if (this.c != null) {
            this.c.clear();
        }
    }

    public void a(final String str) {
        AndroidUtil.d.b(new Runnable() {
            public void run() {
                HashSet hashSet = (HashSet) ExposeHelper.this.c.get(str);
                if (hashSet != null && hashSet.size() > 0) {
                    Iterator it = hashSet.iterator();
                    while (it.hasNext()) {
                        ViewItem viewItem = (ViewItem) it.next();
                        if (!ExposeHelper.this.d.contains(viewItem.f10027a) && !TextUtils.isEmpty(viewItem.f10027a)) {
                            ExposeHelper.this.d.add(viewItem.f10027a);
                        }
                    }
                }
            }
        });
    }

    private static class SingletonHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static ExposeHelper f10026a = new ExposeHelper();

        private SingletonHolder() {
        }
    }

    private static class ViewItem {

        /* renamed from: a  reason: collision with root package name */
        String f10027a;
        int b;

        public ViewItem(String str, int i) {
            this.f10027a = str;
            this.b = i;
        }

        public String toString() {
            return this.f10027a + " : " + this.b;
        }
    }
}
