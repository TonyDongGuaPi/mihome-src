package com.xiaomi.mishopsdk.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.xiaomi.mishopsdk.R;
import com.xiaomi.mishopsdk.plugin.lib.PluginInfo;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.mobilestats.StatService;
import com.xiaomi.shop2.util.Device;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BasePluginFragment extends BaseFragment {
    public static final String APP_HOME_HOST = "com.xiaomi.shop.plugin.homepage.MainFragment";
    public static long lastTimeOfProductDetail1;
    public static long lastTimeOfProductDetail2;
    private static final SimpleArrayMap<String, FragmentInfo> sPluginFfgClassMap = new SimpleArrayMap<>();
    protected String addCartPath;
    PluginInfo mPluginInfo;

    /* access modifiers changed from: protected */
    public String getStateId() {
        return null;
    }

    public static Fragment instantiate(Context context, String str, Bundle bundle) {
        try {
            ClassLoader classLoader = context.getClassLoader();
            FragmentInfo fragmentInfo = sPluginFfgClassMap.get(str);
            if (!(fragmentInfo == null || fragmentInfo.mClassLoaderHashCode == classLoader.hashCode())) {
                sPluginFfgClassMap.remove(str);
                fragmentInfo = null;
            }
            if (fragmentInfo == null) {
                fragmentInfo = new FragmentInfo(classLoader.hashCode(), classLoader.loadClass(str));
                sPluginFfgClassMap.put(str, fragmentInfo);
            }
            Fragment fragment = (Fragment) fragmentInfo.mFragmentClass.newInstance();
            if (bundle != null) {
                bundle.setClassLoader(fragment.getClass().getClassLoader());
                fragment.setArguments(bundle);
            }
            return fragment;
        } catch (ClassNotFoundException e) {
            throw new Fragment.InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e);
        } catch (InstantiationException e2) {
            throw new Fragment.InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (IllegalAccessException e3) {
            throw new Fragment.InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e3);
        }
    }

    public void onCreate(Bundle bundle) {
        PluginInfo pluginInfo;
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (!(arguments == null || !arguments.containsKey(Constants.Plugin.ARGUMENT_PLUGININFO) || (pluginInfo = (PluginInfo) arguments.getSerializable(Constants.Plugin.ARGUMENT_PLUGININFO)) == null)) {
            this.mPluginInfo = pluginInfo;
        }
        if (arguments != null && arguments.containsKey(Constants.Plugin.ARGUMENT_PLUGINPREVIOUS)) {
            this.addCartPath = arguments.getString(Constants.Plugin.ARGUMENT_PLUGINPREVIOUS);
        }
    }

    /* access modifiers changed from: protected */
    public void startFragment(Class<?> cls, Bundle bundle) {
        startFragmentForResult(cls, bundle, -1);
    }

    /* access modifiers changed from: protected */
    public void startAndFinishSelf(Class<?> cls, Bundle bundle) {
        startFragment(cls, bundle);
        getActivity().overridePendingTransition(0, 0);
        getActivity().finish();
    }

    /* access modifiers changed from: protected */
    public void startFragmentForResult(Class<?> cls, Bundle bundle, int i) {
        Intent intent = new Intent();
        if (!bundle.containsKey(Constants.Plugin.ARGUMENT_PLUGININFO)) {
            bundle.putSerializable(Constants.Plugin.ARGUMENT_PLUGININFO, this.mPluginInfo);
        }
        if (!bundle.containsKey(Constants.Plugin.ARGUMENT_PLUGINID) && this.mPluginInfo != null && !TextUtils.isEmpty(this.mPluginInfo.id)) {
            bundle.putString(Constants.Plugin.ARGUMENT_PLUGINID, this.mPluginInfo.id);
        }
        intent.putExtra("fragment", cls.toString());
        createAddcartPath(bundle);
        intent.putExtras(bundle);
        intent.setAction(Constants.Plugin.ACTION_CHILD);
        intent.setData(Uri.parse("ShopPlugin://" + cls.getName()));
        intent.setPackage(Constants.REAL_PACKAGE_NAME);
        FragmentActivity activity = getActivity();
        if (activity != null && isAdded()) {
            activity.startActivityForResult(intent, i);
            activity.overridePendingTransition(R.anim.mishopsdk_right_enter, R.anim.mishopsdk_left_out);
        }
    }

    /* access modifiers changed from: protected */
    public void startNewPluginActivity(String str, Bundle bundle) {
        startNewPluginActivityForResult(str, bundle, -1, (String) null);
    }

    /* access modifiers changed from: protected */
    public void startNewPluginActivity(String str, Bundle bundle, String str2) {
        startNewPluginActivityForResult(str, bundle, -1, str2);
    }

    /* access modifiers changed from: protected */
    public void startNewPluginActivityForResult(String str, Bundle bundle, int i) {
        startNewPluginActivityForResult(str, bundle, i, (String) null);
    }

    /* access modifiers changed from: protected */
    public void startNewPluginActivityForResult(String str, Bundle bundle, int i, String str2) {
        createAddcartPath(bundle);
        Intent createPluginIntent = createPluginIntent(str, bundle, str2, (String) null);
        FragmentActivity activity = getActivity();
        if (activity != null && isAdded()) {
            activity.startActivityForResult(createPluginIntent, i);
            activity.overridePendingTransition(R.anim.mishopsdk_right_enter, R.anim.mishopsdk_left_out);
        }
    }

    public static void startNewPlugin(Activity activity, JSONObject jSONObject) throws JSONException {
        startNewPlugin(activity, jSONObject, (Bundle) null);
    }

    public static void startNewPlugin(Activity activity, JSONObject jSONObject, Bundle bundle) throws JSONException {
        String str;
        if (jSONObject != null) {
            String optString = jSONObject.optString("path");
            if (bundle == null) {
                bundle = new Bundle();
            }
            if (optString.contains(Constants.Plugin.ARGUMENT_PLUGINID)) {
                int indexOf = optString.indexOf(Constants.Plugin.ARGUMENT_PLUGINID) + Constants.Plugin.ARGUMENT_PLUGINID.length() + 1;
                int indexOf2 = optString.indexOf(a.b, indexOf);
                if (indexOf2 == -1) {
                    indexOf2 = optString.length();
                }
                str = optString.substring(indexOf, indexOf2);
                bundle.putString(Constants.Plugin.ARGUMENT_PLUGINID, str);
            } else {
                str = null;
            }
            if (jSONObject.has(Constants.Plugin.ARGUMENT_PLUGININFO)) {
                bundle.putString(Constants.Plugin.ARGUMENT_PLUGININFO, jSONObject.optString(Constants.Plugin.ARGUMENT_PLUGININFO));
            }
            if (jSONObject.has("extra") && !TextUtils.isEmpty(jSONObject.optString("extra"))) {
                JSONObject jSONObject2 = new JSONObject(jSONObject.optString("extra"));
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    Object opt = jSONObject2.opt(next);
                    if (opt != null) {
                        if (opt instanceof Integer) {
                            bundle.putInt(next, ((Integer) opt).intValue());
                        } else if (opt instanceof Double) {
                            bundle.putDouble(next, ((Double) opt).doubleValue());
                        } else if (opt instanceof Boolean) {
                            bundle.putBoolean(next, ((Boolean) opt).booleanValue());
                        } else {
                            bundle.putString(next, opt.toString());
                        }
                    }
                }
            }
            createAddCartPath(activity, bundle, jSONObject);
            activity.startActivity(createPluginIntent(str, bundle, (String) null, optString));
            activity.overridePendingTransition(R.anim.mishopsdk_right_enter, R.anim.mishopsdk_left_out);
        }
    }

    public static class Fasade {
        public static void startNewPluginActivity(Activity activity, String str, Bundle bundle) {
            startNewPluginActivity(activity, str, bundle, (String) null);
        }

        public static void startNewPluginActivity(Activity activity, String str, Bundle bundle, String str2) {
            BasePluginFragment.createAddCartPath(activity, bundle, (JSONObject) null);
            Intent createPluginIntent = BasePluginFragment.createPluginIntent(str, bundle, str2, (String) null);
            if (activity != null) {
                activity.startActivity(createPluginIntent);
                activity.overridePendingTransition(R.anim.mishopsdk_right_enter, R.anim.mishopsdk_left_out);
            }
        }
    }

    public static Intent createPluginIntent(String str, Bundle bundle, String str2, String str3) {
        Intent intent = new Intent();
        intent.putExtra(Constants.Plugin.ARGUMENT_PLUGINID, str);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setAction(Constants.Plugin.ACTION_ROOT);
        intent.setPackage(Constants.REAL_PACKAGE_NAME);
        if (!TextUtils.isEmpty(str2)) {
            intent.setData(Uri.parse("ShopPlugin://" + str2));
        } else if (!TextUtils.isEmpty(str3)) {
            intent.setData(Uri.parse(str3));
        }
        convertIntentBeforStart(intent, str);
        return intent;
    }

    private static void convertIntentBeforStart(Intent intent, String str) {
        if ("100".equals(str) && Device.MISHOP_SDK_VERSION >= 20161201) {
            Uri data = intent.getData();
            boolean z = true;
            if (data != null) {
                String uri = data.toString();
                if (!TextUtils.isEmpty(uri)) {
                    if (uri.contains(APP_HOME_HOST)) {
                        intent.setData(Uri.parse(uri.replace(APP_HOME_HOST, "com.xiaomi.mishopsdk.plugin.main.ui.HomeFragment")));
                    }
                    z = false;
                }
            }
            if (z) {
                intent.setData(Uri.parse("ShopPlugin://com.xiaomi.mishopsdk.plugin.main.ui.HomeFragment"));
            }
            intent.setAction(Constants.Plugin.ACTION_HOMEPAGE);
            intent.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void startPluginOrder(Bundle bundle) {
        startNewPluginActivity(Constants.Plugin.PLUGINID_ORDER, bundle);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void startPluginAddress(Bundle bundle) {
        startNewPluginActivity("105", bundle);
    }

    /* access modifiers changed from: protected */
    public void gotoShoppingCart() {
        startPluginCart(new Bundle());
    }

    public void onResume() {
        super.onResume();
        StatService.onFragmentResume(this, getPageParams());
    }

    public void onPause() {
        super.onPause();
        StatService.onFragmentPause(this, getPageParams());
    }

    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null && bundle.containsKey("addCartPath")) {
            this.addCartPath = bundle.getString("addCartPath");
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (!TextUtils.isEmpty(this.addCartPath)) {
            bundle.putString("addCartPath", this.addCartPath);
        }
    }

    /* access modifiers changed from: protected */
    public void startPluginGoodsDetail(Bundle bundle) {
        startNewPluginActivity("101", bundle);
    }

    /* access modifiers changed from: protected */
    public void startPluginCart(Bundle bundle) {
        startNewPluginActivity("102", bundle);
    }

    private static class FragmentInfo {
        int mClassLoaderHashCode;
        Class<?> mFragmentClass;

        public FragmentInfo(int i, Class<?> cls) {
            this.mClassLoaderHashCode = i;
            this.mFragmentClass = cls;
        }
    }

    /* access modifiers changed from: protected */
    public HashMap<String, String> getPageParams() {
        HashMap<String, String> hashMap = new HashMap<>();
        String stateId = getStateId();
        if (!TextUtils.isEmpty(stateId)) {
            hashMap.put("pageId", stateId);
        }
        if (!TextUtils.isEmpty(this.addCartPath)) {
            hashMap.put("path", this.addCartPath);
        }
        if (this.mPluginInfo != null && !TextUtils.isEmpty(this.mPluginInfo.id)) {
            hashMap.put(Constants.Plugin.ARGUMENT_PLUGINID, this.mPluginInfo.id);
        }
        return hashMap;
    }

    private void createAddcartPath(Bundle bundle) {
        String str;
        if (bundle != null && !bundle.containsKey(Constants.Plugin.ARGUMENT_PLUGINPREVIOUS)) {
            if (this.addCartPath == null) {
                str = "";
            } else {
                str = this.addCartPath + Constants.Split.CTRL_A;
            }
            boolean z = false;
            if (this.mPluginInfo != null && !TextUtils.isEmpty(this.mPluginInfo.id)) {
                str = str + "p" + this.mPluginInfo.id;
                z = true;
            }
            if (bundle.containsKey(Constants.Plugin.ARGUMENT_LOGCODE)) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(z ? Constants.Split.CTRL_B : "");
                sb.append(bundle.getString(Constants.Plugin.ARGUMENT_LOGCODE));
                str = sb.toString();
            }
            bundle.putString(Constants.Plugin.ARGUMENT_PLUGINPREVIOUS, str);
        }
    }

    /* access modifiers changed from: private */
    public static void createAddCartPath(Activity activity, Bundle bundle, JSONObject jSONObject) {
        if (!bundle.containsKey(Constants.Plugin.ARGUMENT_PLUGINPREVIOUS) && activity != null) {
            Intent intent = activity.getIntent();
            String str = "";
            String str2 = "";
            if (bundle != null && bundle.containsKey(Constants.Plugin.ARGUMENT_LOGCODE)) {
                str2 = bundle.getString(Constants.Plugin.ARGUMENT_LOGCODE);
            }
            if (TextUtils.isEmpty(str2) && jSONObject != null && jSONObject.has(Constants.Plugin.ARGUMENT_LOGCODE)) {
                str2 = jSONObject.optString(Constants.Plugin.ARGUMENT_LOGCODE);
            }
            boolean z = false;
            if (!(intent == null || intent.getExtras() == null)) {
                if (intent.getExtras().containsKey(Constants.Plugin.ARGUMENT_PLUGINPREVIOUS)) {
                    str = intent.getExtras().getString(Constants.Plugin.ARGUMENT_PLUGINPREVIOUS) + Constants.Split.CTRL_A;
                }
                if (intent.getExtras().containsKey(Constants.Plugin.ARGUMENT_PLUGINID)) {
                    str = str + "p" + intent.getExtras().getString(Constants.Plugin.ARGUMENT_PLUGINID);
                    z = true;
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(z ? Constants.Split.CTRL_B : "");
                sb.append(str2);
                str = sb.toString();
            }
            bundle.putString(Constants.Plugin.ARGUMENT_PLUGINPREVIOUS, str);
        }
    }
}
