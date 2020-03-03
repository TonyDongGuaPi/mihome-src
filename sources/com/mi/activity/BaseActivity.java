package com.mi.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.mi.log.LogUtil;
import com.mi.micomponents.R;
import com.mi.model.SyncModel;
import com.mi.model.Tags;
import com.mi.model.UpdaterInfo;
import com.mi.util.AppUpdater;
import com.mi.util.Device;
import com.mi.util.HotfixUtil;
import com.mi.util.HttpDownloader;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import com.mi.util.Utils;
import com.mi.util.permission.PermissionUtil;
import com.mi.volley.MiJsonObjectRequest;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class BaseActivity extends AppCompatActivity {
    /* access modifiers changed from: private */
    public static final String TAG = "BaseActivity";
    /* access modifiers changed from: private */
    public long end;
    /* access modifiers changed from: private */
    public boolean hasRener = false;
    protected AppUpdater mUpdater;
    protected long renderDuration;
    /* access modifiers changed from: private */
    public long start;

    /* access modifiers changed from: protected */
    public void getRenderDuration() {
    }

    /* access modifiers changed from: protected */
    public String getUpdateUrl() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void installHotfix(String str) {
    }

    /* access modifiers changed from: protected */
    public Fragment newFragmentByTag(String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.start = System.nanoTime();
    }

    public void setContentView(@LayoutRes int i) {
        AnonymousClass1 r0 = new FrameLayout(this) {
            /* access modifiers changed from: protected */
            public void dispatchDraw(Canvas canvas) {
                super.dispatchDraw(canvas);
                if (!BaseActivity.this.hasRener) {
                    long unused = BaseActivity.this.end = System.nanoTime();
                    BaseActivity.this.renderDuration = ((System.nanoTime() - BaseActivity.this.start) / 10000) / 100;
                    boolean unused2 = BaseActivity.this.hasRener = true;
                    BaseActivity.this.getRenderDuration();
                }
            }
        };
        getLayoutInflater().inflate(i, r0, true);
        setContentView((View) r0);
    }

    /* access modifiers changed from: protected */
    public void checkUpdate(final boolean z) {
        if (this.mUpdater == null) {
            this.mUpdater = new AppUpdater(this);
        }
        if (!z && !this.mUpdater.needCheck()) {
            return;
        }
        if (z) {
            MiJsonObjectRequest miJsonObjectRequest = new MiJsonObjectRequest(0, getUpdateUrl(), new Response.Listener<JSONObject>() {
                /* renamed from: a */
                public void onResponse(JSONObject jSONObject) {
                    BaseActivity.this.handleUpdateData(z, jSONObject);
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    String access$400 = BaseActivity.TAG;
                    VolleyLog.d(access$400, "Error: " + volleyError.getMessage());
                }
            });
            miJsonObjectRequest.setTag(TAG);
            RequestQueueUtil.a().add(miJsonObjectRequest);
            return;
        }
        LogUtil.b("Check update", "SyncModel.response=" + SyncModel.response);
        handleUpdateData(z, SyncModel.response);
    }

    /* access modifiers changed from: private */
    public void handleUpdateData(boolean z, JSONObject jSONObject) {
        JSONObject jSONObject2;
        try {
            String str = TAG;
            LogUtil.b(str, "handleUpdateData Device.APP_VERSION:" + Device.r);
            UpdaterInfo updaterInfo = null;
            if (jSONObject != null) {
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                if (optJSONObject != null) {
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("versionInfo");
                    String optString = optJSONObject2.optString("version", (String) null);
                    int optInt = optJSONObject2.optInt("versionCode");
                    String optString2 = optJSONObject2.optString(Tags.VersionUpdate.n);
                    String optString3 = optJSONObject2.optString("url", (String) null);
                    Utils.Preference.setIntPref(this, "versionCode", optInt);
                    String str2 = TAG;
                    LogUtil.b(str2, "handleUpdateData Device.APP_VERSION:" + Device.r + ", versionCode:" + optInt);
                    if (optInt > Device.r) {
                        UpdaterInfo updaterInfo2 = new UpdaterInfo();
                        updaterInfo2.b = optString3;
                        updaterInfo2.c = optJSONObject2.optString("md5", (String) null);
                        updaterInfo2.d = optJSONObject2.optString(Tags.VersionUpdate.d, (String) null);
                        updaterInfo2.e = optString;
                        updaterInfo2.f = optInt;
                        updaterInfo2.g = optJSONObject2.optBoolean("forceUpdate", false);
                        JSONArray optJSONArray = optJSONObject2.optJSONArray("notes");
                        updaterInfo2.j = optString2;
                        ArrayList<UpdaterInfo.DescType> arrayList = new ArrayList<>();
                        if (optJSONArray != null) {
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                if (!optJSONArray.isNull(i) && (jSONObject2 = (JSONObject) optJSONArray.opt(i)) != null) {
                                    UpdaterInfo.DescType descType = new UpdaterInfo.DescType();
                                    descType.mDescType = jSONObject2.optString("type");
                                    descType.mDesc = jSONObject2.optString("desc");
                                    arrayList.add(descType);
                                }
                            }
                        }
                        updaterInfo2.f7375a = arrayList;
                        updaterInfo = updaterInfo2;
                    }
                } else {
                    Utils.Preference.setLongPref(this, "pref_last_update_is_ok", Long.valueOf(System.currentTimeMillis()));
                }
            }
            if (updaterInfo != null) {
                this.mUpdater.loadVersionLogAndPopDialog(updaterInfo);
            } else if (z) {
                MiToast.a((Context) this, R.string.no_update, 0);
            }
        } catch (Exception unused) {
            VolleyLog.d(TAG, "JSON parse error");
        }
    }

    /* access modifiers changed from: protected */
    public void checkHotfix() {
        JSONObject optJSONObject;
        if (SyncModel.response != null && (optJSONObject = SyncModel.response.optJSONObject("data")) != null) {
            LogUtil.b("checkHotfix data:" + optJSONObject);
            String optString = optJSONObject.optJSONObject("versionInfo").optString(Tags.VersionUpdate.e, (String) null);
            if (!TextUtils.isEmpty(optString)) {
                HotfixUtil.a(getApplicationContext(), optString);
                final String str = getExternalCacheDir().getAbsolutePath() + "/" + optString.substring(optString.lastIndexOf(47));
                HttpDownloader httpDownloader = new HttpDownloader(optString);
                httpDownloader.a(str);
                httpDownloader.a((HttpDownloader.HttpDownloadListener) new HttpDownloader.HttpDownloadListener() {
                    public void a(Object obj) {
                    }

                    public void a(Object obj, long j, long j2) {
                    }

                    public void a(Object obj, Throwable th) {
                    }

                    public void b(Object obj) {
                        BaseActivity.this.installHotfix(str);
                    }
                });
                httpDownloader.c();
            }
        }
    }

    public void showFragment(View view, String str, Bundle bundle, Bundle bundle2, boolean z) {
        if (view == null) {
            LogUtil.c(TAG, "mDecoratedView is NOT FOUND.");
        } else if (view.getId() <= 0) {
            throw new IllegalArgumentException("The activity in xml layout MUST has argument 'id'.");
        } else if (bundle == null) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            Fragment fragmentByTag = getFragmentByTag(str);
            if (fragmentByTag == null) {
                fragmentByTag = newFragmentByTag(str);
                if (bundle2 != null) {
                    fragmentByTag.setArguments(bundle2);
                }
            }
            if (fragmentByTag == null) {
                String str2 = TAG;
                LogUtil.c(str2, "NO fragment found by tag: " + str);
                return;
            }
            beginTransaction.setTransition(4099);
            beginTransaction.replace(view.getId(), fragmentByTag, str);
            if (z) {
                beginTransaction.addToBackStack((String) null);
            }
            beginTransaction.commitAllowingStateLoss();
        }
    }

    public Fragment getFragmentByTag(String str) {
        return getSupportFragmentManager().findFragmentByTag(str);
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        PermissionUtil.a(i, strArr, iArr);
    }

    /* access modifiers changed from: protected */
    @TargetApi(23)
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        PermissionUtil.a((Activity) this, i);
    }

    public static boolean isActivityAlive(Context context) {
        if (context == null || !(context instanceof Activity)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17) {
            return !((Activity) context).isFinishing();
        }
        Activity activity = (Activity) context;
        if (activity.isFinishing() || activity.isDestroyed()) {
            return false;
        }
        return true;
    }
}
