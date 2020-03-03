package com.xiaomi.payment.entry;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.mibi.common.data.MarketUtils;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.Utils;
import com.mipay.core.runtime.Extension;
import com.mipay.core.runtime.ExtensionConfig;
import com.mipay.core.runtime.ExtensionPoint;
import com.xiaomi.payment.Activator;
import com.xiaomi.payment.MibiPadHybridDialogActivity;
import com.xiaomi.payment.MibiPhoneHybridActivity;
import com.xiaomi.payment.data.AnalyticsConstants;
import com.xiaomi.payment.data.BundleUtils;
import com.xiaomi.payment.data.EntryData;
import com.xiaomi.payment.entry.IEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntryManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12290a = "EntryManager";
    private static EntryManager b = new EntryManager();
    private static final String c = "com.xiaomi.payment.entry_provider";
    private static final String d = "provider";
    private static final String e = "id";
    private static final String f = "class";
    private static final String g = "return_result";
    private static final String h = "exported";
    private Map<String, EntryWithParams> i = new HashMap();

    private class EntryWithParams {

        /* renamed from: a  reason: collision with root package name */
        IEntry f12294a;
        Map<String, Object> b;

        private EntryWithParams() {
        }
    }

    private EntryManager() {
        b();
    }

    public static EntryManager a() {
        return b;
    }

    private void b() {
        List<Extension> extensions;
        ExtensionPoint extensionPoint = Activator.getBundleContext().getExtensionPoint(c);
        if (extensionPoint != null && (extensions = extensionPoint.getExtensions()) != null) {
            for (Extension next : extensions) {
                try {
                    for (ExtensionConfig next2 : next.getConfigurationElements()) {
                        try {
                            if (TextUtils.equals(next2.getName(), d)) {
                                String attribute = next2.getAttribute("id");
                                IEntry iEntry = (IEntry) next2.createExtensionExecutable("class");
                                EntryWithParams entryWithParams = new EntryWithParams();
                                entryWithParams.f12294a = iEntry;
                                entryWithParams.b = a(next2);
                                if (!TextUtils.isEmpty(attribute) && iEntry != null) {
                                    this.i.put(attribute, entryWithParams);
                                }
                            }
                        } catch (Exception unused) {
                            Log.d(f12290a, "parse entry extension fails at " + next.getSimpleIdentifier());
                        }
                    }
                } catch (Exception unused2) {
                    Log.d(f12290a, "parse entry extension fails at " + next.getSimpleIdentifier());
                }
            }
        }
    }

    private Map<String, Object> a(ExtensionConfig extensionConfig) {
        HashMap hashMap = new HashMap();
        for (String next : extensionConfig.getAttributeNames()) {
            if (!TextUtils.isEmpty(next) && !TextUtils.equals(next, "id") && !TextUtils.equals(next, "class")) {
                String attribute = extensionConfig.getAttribute(next);
                if (!TextUtils.isEmpty(attribute)) {
                    hashMap.put(next, attribute);
                }
            }
        }
        return hashMap;
    }

    public ArrayList<String> a(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Map.Entry<String, EntryWithParams> value : this.i.entrySet()) {
            IEntry iEntry = ((EntryWithParams) value.getValue()).f12294a;
            if (iEntry.a(context)) {
                arrayList.add(iEntry.a());
            }
        }
        return arrayList;
    }

    public boolean a(String str) {
        return Boolean.parseBoolean(a(str, g));
    }

    public boolean b(String str) {
        String a2 = a(str, h);
        return a2 == null || !a2.equalsIgnoreCase("false");
    }

    private String a(String str, String str2) {
        EntryWithParams entryWithParams;
        Map<String, Object> map;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (entryWithParams = this.i.get(str)) == null || (map = entryWithParams.b) == null) {
            return null;
        }
        return (String) map.get(str2);
    }

    public boolean a(Activity activity, EntryData entryData, Bundle bundle, int i2) {
        if (activity == null) {
            return false;
        }
        return a((IEntry.ContextEnterInterface) new ActivityEnterInterface(activity), entryData, bundle, i2);
    }

    public boolean a(Fragment fragment, EntryData entryData, Bundle bundle, int i2) {
        if (fragment == null) {
            return false;
        }
        return a((IEntry.ContextEnterInterface) new FragmentEnterInterface(fragment), entryData, bundle, i2);
    }

    public boolean a(Context context, EntryData entryData, Bundle bundle, int i2) {
        if (context == null) {
            return false;
        }
        return a((IEntry.ContextEnterInterface) new BackgroundContextEnterInterface(context), entryData, bundle, i2);
    }

    private boolean a(IEntry.ContextEnterInterface contextEnterInterface, EntryData entryData, Bundle bundle, int i2) {
        boolean z = false;
        if (entryData == null) {
            return false;
        }
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        Bundle a2 = BundleUtils.a(entryData.mExtraData);
        if (a2 != null) {
            bundle2.putAll(a2);
        }
        switch (entryData.mType) {
            case LOCAL:
                z = a(entryData.mId, contextEnterInterface, bundle2, i2);
                break;
            case WEB:
                z = a(entryData.mId, contextEnterInterface, entryData.mUrl, bundle2, i2);
                break;
            case APP:
                z = a(entryData.mId, contextEnterInterface, entryData.mIntentUri, entryData.mPackageName, bundle2, i2);
                break;
        }
        if (z || TextUtils.isEmpty(entryData.mUrl)) {
            return z;
        }
        if (entryData.mType != EntryData.Type.LOCAL && entryData.mType != EntryData.Type.APP) {
            return z;
        }
        return a(entryData.mId, contextEnterInterface, entryData.mUrl, bundle2, i2);
    }

    private boolean a(String str, IEntry.ContextEnterInterface contextEnterInterface, String str2, Bundle bundle, int i2) {
        if (contextEnterInterface == null) {
            return false;
        }
        if (TextUtils.isEmpty(str2)) {
            Log.e(f12290a, "webApp failed, url is null");
            return false;
        }
        if (bundle != null) {
            str2 = BundleUtils.a(str2, bundle);
        }
        Intent intent = new Intent();
        if (Utils.b()) {
            intent.setClass(contextEnterInterface.a(), MibiPadHybridDialogActivity.class);
        } else {
            intent.setClass(contextEnterInterface.a(), MibiPhoneHybridActivity.class);
        }
        intent.putExtra("com.miui.sdk.hybrid.extra.URL", str2);
        contextEnterInterface.a(intent, i2);
        c(str);
        return true;
    }

    public boolean a(String str, Context context, String str2, Bundle bundle) {
        if (context == null) {
            return false;
        }
        return a(str, (IEntry.ContextEnterInterface) new BackgroundContextEnterInterface(context), str2, bundle, -1);
    }

    public boolean a(String str, Activity activity, String str2, Bundle bundle, int i2) {
        if (activity == null) {
            return false;
        }
        return a(str, (IEntry.ContextEnterInterface) new ActivityEnterInterface(activity), str2, bundle, i2);
    }

    public boolean a(String str, Fragment fragment, String str2, Bundle bundle, int i2) {
        if (fragment == null) {
            return false;
        }
        return a(str, (IEntry.ContextEnterInterface) new FragmentEnterInterface(fragment), str2, bundle, i2);
    }

    private boolean a(String str, IEntry.ContextEnterInterface contextEnterInterface, String str2, String str3, Bundle bundle, int i2) {
        if (contextEnterInterface == null) {
            return false;
        }
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            boolean a2 = a(contextEnterInterface, str2, bundle, i2);
            boolean z = true;
            if (!a2 && !Utils.c(contextEnterInterface.a(), str3) && !MarketUtils.b(contextEnterInterface.a(), str3)) {
                Log.e(f12290a, "enterApp failed intentUri:" + str2 + " and PackageName = " + str3);
                z = false;
            }
            if (z) {
                c(str);
            }
            return z;
        }
        Log.e(f12290a, "enterApp failed intentUri and packageName is null");
        return false;
    }

    public boolean a(String str, Context context, String str2, String str3, Bundle bundle) {
        if (context == null) {
            return false;
        }
        return a(str, (IEntry.ContextEnterInterface) new BackgroundContextEnterInterface(context), str2, str3, bundle, -1);
    }

    public boolean a(String str, Fragment fragment, String str2, String str3, Bundle bundle, int i2) {
        if (fragment == null) {
            return false;
        }
        return a(str, (IEntry.ContextEnterInterface) new FragmentEnterInterface(fragment), str2, str3, bundle, i2);
    }

    public boolean a(String str, Activity activity, String str2, String str3, Bundle bundle, int i2) {
        if (activity == null) {
            return false;
        }
        return a(str, (IEntry.ContextEnterInterface) new ActivityEnterInterface(activity), str2, str3, bundle, i2);
    }

    private boolean a(IEntry.ContextEnterInterface contextEnterInterface, String str, Bundle bundle, int i2) {
        Intent b2;
        if (contextEnterInterface == null || TextUtils.isEmpty(str) || (b2 = Utils.b(str)) == null) {
            return false;
        }
        if (bundle != null) {
            b2.putExtras(bundle);
        }
        if (!Utils.b(contextEnterInterface.a(), b2)) {
            return false;
        }
        contextEnterInterface.a(b2, i2);
        return true;
    }

    public boolean a(String str, Activity activity, Bundle bundle, int i2) {
        if (activity == null) {
            return false;
        }
        return a(str, (IEntry.ContextEnterInterface) new ActivityEnterInterface(activity), bundle, i2);
    }

    public boolean a(String str, Fragment fragment, Bundle bundle, int i2) {
        if (fragment == null) {
            return false;
        }
        return a(str, (IEntry.ContextEnterInterface) new FragmentEnterInterface(fragment), bundle, i2);
    }

    public boolean a(String str, Context context, Bundle bundle) {
        if (context == null) {
            return false;
        }
        return a(str, (IEntry.ContextEnterInterface) new BackgroundContextEnterInterface(context), bundle, -1);
    }

    private boolean a(String str, IEntry.ContextEnterInterface contextEnterInterface, Bundle bundle, int i2) {
        if (contextEnterInterface == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            this.i.get(str).f12294a.a(contextEnterInterface, bundle, i2);
            c(str);
            return true;
        } catch (Exception e2) {
            Log.e(f12290a, "enter failed for id: " + str, e2);
            return false;
        }
    }

    public boolean a(Context context, String str) {
        if (!this.i.containsKey(str)) {
            return false;
        }
        return this.i.get(str).f12294a.a(context);
    }

    private static class ActivityEnterInterface implements IEntry.ContextEnterInterface {

        /* renamed from: a  reason: collision with root package name */
        private Activity f12292a;

        public ActivityEnterInterface(Activity activity) {
            this.f12292a = activity;
        }

        public Context a() {
            return this.f12292a;
        }

        public void a(Intent intent, int i) {
            this.f12292a.startActivityForResult(intent, i);
        }
    }

    private static class FragmentEnterInterface implements IEntry.ContextEnterInterface {

        /* renamed from: a  reason: collision with root package name */
        private Fragment f12295a;

        public FragmentEnterInterface(Fragment fragment) {
            this.f12295a = fragment;
        }

        public Context a() {
            return this.f12295a.getActivity();
        }

        public void a(Intent intent, int i) {
            this.f12295a.startActivityForResult(intent, i);
        }
    }

    private static class BackgroundContextEnterInterface implements IEntry.ContextEnterInterface {

        /* renamed from: a  reason: collision with root package name */
        private Context f12293a;

        public BackgroundContextEnterInterface(Context context) {
            this.f12293a = context;
        }

        public Context a() {
            return this.f12293a;
        }

        public void a(Intent intent, int i) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            this.f12293a.startActivity(intent);
        }
    }

    private void c(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(AnalyticsConstants.bD, str);
        MistatisticUtils.a("entry", AnalyticsConstants.bC, (Map<String, String>) hashMap);
    }
}
