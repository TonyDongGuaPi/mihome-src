package com.xiaomi.smarthome.scenenew.manager;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.ListAdapter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xiaomi.plugin.Callback;
import com.xiaomi.plugin.Parser;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.IXmPluginHostActivity;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.MD5;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scenenew.MySceneFragmentNew;
import com.xiaomi.smarthome.scenenew.SceneTabFragment;
import com.xiaomi.smarthome.scenenew.adapter.ChooseGoodAdapter;
import com.xiaomi.smarthome.scenenew.model.GoodInfo;
import com.xiaomi.smarthome.scenenew.model.RecommendSceneInfo;
import com.xiaomi.youpin.XmpluginHostApiImp;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.xiaomi.youpin.youpin_network.util.YouPinParamsUtil;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecommendSceneManager {
    public static final int b = 1;
    public static final int c = 2;
    /* access modifiers changed from: private */
    public static final String g = "RecommendSceneManager";
    private static RecommendSceneManager h = null;
    private static final String j = "recommend_scene_info_sp";
    private static final String k = "recommend_scene_sp_name";

    /* renamed from: a  reason: collision with root package name */
    RecommendSceneInfo f21973a;
    RecommendSceneInfo.RecommendSceneItem d;
    public boolean e = false;
    SceneTabFragment f;
    private String i = null;
    /* access modifiers changed from: private */
    public ArrayList<IGetRecommendSceneCallBack> l = new ArrayList<>();
    private Handler m = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                RecommendSceneManager.this.d();
            }
        }
    };
    private ChooseGoodAdapter n;
    private WeakReference<MLAlertDialog> o;
    private ViewPager p;
    private boolean q = false;
    private boolean r = false;

    public interface IGetRecommendSceneCallBack {
        void a(int i);

        void b(int i);
    }

    public interface IGetVideoCallBack {
        void a();

        void a(File file);
    }

    public static RecommendSceneManager a() {
        if (h == null) {
            synchronized (RecommendSceneManager.class) {
                if (h == null) {
                    h = new RecommendSceneManager();
                }
            }
        }
        return h;
    }

    public boolean a(String str) {
        return new File(c(str)).exists();
    }

    public void a(String str, IGetVideoCallBack iGetVideoCallBack) {
        String c2 = c(str);
        final File file = new File(c2);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            File externalFilesDir = SHApplication.getAppContext().getExternalFilesDir("/recommend_video/");
            if (!externalFilesDir.exists()) {
                externalFilesDir.mkdirs();
            }
            final WeakReference weakReference = new WeakReference(iGetVideoCallBack);
            a(str, c2, new NetworkUtils.DownloadTask.DownloadListener() {
                public void a() {
                    IGetVideoCallBack iGetVideoCallBack = (IGetVideoCallBack) weakReference.get();
                    if (iGetVideoCallBack != null) {
                        LogUtil.a(RecommendSceneManager.g, "downLoadVideo onSuccess");
                        iGetVideoCallBack.a(file);
                    }
                }

                public void b() {
                    IGetVideoCallBack iGetVideoCallBack = (IGetVideoCallBack) weakReference.get();
                    if (iGetVideoCallBack != null) {
                        LogUtil.a(RecommendSceneManager.g, "downLoadVideo onFailed");
                        iGetVideoCallBack.a();
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                }
            });
        } else if (iGetVideoCallBack != null) {
            iGetVideoCallBack.a(file);
        }
    }

    private String c(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return SHApplication.getAppContext().getExternalFilesDir("/recommend_video/").getPath() + "/" + System.currentTimeMillis() + ".mp4";
            }
            return SHApplication.getAppContext().getExternalFilesDir("/recommend_video/").getPath() + "/" + MD5.a(str) + ".mp4";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private void a(String str, String str2, NetworkUtils.DownloadTask.DownloadListener downloadListener) {
        File file = new File(str2);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        AsyncTaskUtils.a(new NetworkUtils.DownloadTask(SHApplication.getAppContext(), str, file, downloadListener), new Object[0]);
    }

    private RecommendSceneManager() {
    }

    public RecommendSceneInfo b() {
        return this.f21973a;
    }

    public void c() {
        this.m.sendEmptyMessage(1);
    }

    public void d() {
        String e2 = e();
        if (!TextUtils.isEmpty(e2)) {
            try {
                this.f21973a = RecommendSceneInfo.a(new JSONObject(e2));
                for (int i2 = 0; i2 < this.l.size(); i2++) {
                    this.l.get(i2).a(2);
                }
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
        RemoteSceneApi.a().a(SHApplication.getAppContext(), new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                try {
                    RecommendSceneManager.this.a(jSONObject);
                    RecommendSceneManager.this.f21973a = RecommendSceneInfo.a(jSONObject);
                    RecommendSceneManager.this.e = true;
                    for (int i = 0; i < RecommendSceneManager.this.l.size(); i++) {
                        ((IGetRecommendSceneCallBack) RecommendSceneManager.this.l.get(i)).a(1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Error error) {
                RecommendSceneManager.this.e = false;
                for (int i = 0; i < RecommendSceneManager.this.l.size(); i++) {
                    ((IGetRecommendSceneCallBack) RecommendSceneManager.this.l.get(i)).b(1);
                }
            }
        });
    }

    public void a(String str, final IXmPluginHostActivity.AsyncCallback<String> asyncCallback) {
        RemoteSceneApi.a().a(SHApplication.getAppContext(), new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                try {
                    RecommendSceneManager.this.f21973a = RecommendSceneInfo.a(jSONObject);
                    if (RecommendSceneManager.this.f21973a != null && RecommendSceneManager.this.f21973a.c != null) {
                        IXmPluginHostActivity.AsyncCallback asyncCallback = asyncCallback;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Error error) {
                RecommendSceneManager.this.e = false;
                for (int i = 0; i < RecommendSceneManager.this.l.size(); i++) {
                    ((IGetRecommendSceneCallBack) RecommendSceneManager.this.l.get(i)).b(1);
                }
            }
        });
    }

    public void a(JSONObject jSONObject) {
        String s = CoreApi.a().s();
        if (!TextUtils.isEmpty(s)) {
            SharedPreferences.Editor edit = p().edit();
            edit.putString(s + j, jSONObject.toString()).apply();
        }
    }

    public String e() {
        SharedPreferences p2;
        if (SHApplication.getStateNotifier().a() != 4 || (p2 = p()) == null) {
            return "";
        }
        return p2.getString(CoreApi.a().s() + j, "");
    }

    public void a(IGetRecommendSceneCallBack iGetRecommendSceneCallBack) {
        if (iGetRecommendSceneCallBack != null) {
            int i2 = 0;
            while (i2 < this.l.size()) {
                if (this.l.get(i2) != iGetRecommendSceneCallBack) {
                    i2++;
                } else {
                    return;
                }
            }
            this.l.add(iGetRecommendSceneCallBack);
        }
    }

    public void b(IGetRecommendSceneCallBack iGetRecommendSceneCallBack) {
        if (this.l.contains(iGetRecommendSceneCallBack)) {
            this.l.remove(iGetRecommendSceneCallBack);
        }
    }

    public void a(final List<String> list, final AsyncCallback<List<GoodInfo>, Error> asyncCallback) {
        new JsonObject().add("detail", a(list));
        XmpluginHostApiImp.instance().sendMijiaShopRequest(YouPinParamsUtil.b, a(list), new Callback<List<GoodInfo>>() {
            /* renamed from: a */
            public void onCache(List<GoodInfo> list) {
                LogUtil.a("RecommendSceneFragment", "onCache" + list.toString());
            }

            /* renamed from: a */
            public void onSuccess(List<GoodInfo> list, boolean z) {
                LogUtil.a("RecommendSceneFragment", "onSuccess" + list.toString());
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(list);
                }
            }

            public void onFailure(int i, String str) {
                LogUtil.a("RecommendSceneFragment", "onFailure" + str);
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(i, str));
                }
            }
        }, new Parser<List<GoodInfo>>() {
            /* renamed from: a */
            public List<GoodInfo> parse(JsonElement jsonElement) {
                JsonObject asJsonObject = jsonElement.getAsJsonObject();
                ArrayList arrayList = new ArrayList();
                int i = 0;
                while (i < list.size()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(ApiConst.j);
                    i++;
                    sb.append(i);
                    GoodInfo a2 = GoodInfo.a(asJsonObject.get(sb.toString()).getAsJsonObject());
                    if (a2 != null) {
                        arrayList.add(a2);
                    }
                }
                return arrayList;
            }
        }, false);
    }

    private JsonObject a(List<String> list) {
        JsonObject jsonObject = new JsonObject();
        int i2 = 0;
        while (i2 < list.size()) {
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("model", "Shopv2");
            jsonObject2.addProperty("action", "getDetail");
            JsonObject jsonObject3 = new JsonObject();
            jsonObject3.addProperty(ApiConst.j, list.get(i2));
            jsonObject2.add("parameters", jsonObject3);
            StringBuilder sb = new StringBuilder();
            sb.append(ApiConst.j);
            i2++;
            sb.append(i2);
            jsonObject.add(sb.toString(), jsonObject2);
        }
        return jsonObject;
    }

    public MLAlertDialog f() {
        if (this.o == null) {
            return null;
        }
        return (MLAlertDialog) this.o.get();
    }

    public Dialog a(Context context, final List<GoodInfo> list) {
        this.n = new ChooseGoodAdapter(context, list);
        MLAlertDialog b2 = new MLAlertDialog.Builder(context).a((ListAdapter) this.n, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                UrlDispatchManger.a().c(((GoodInfo) list.get(i)).c);
                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
            }
        }).a((int) R.string.smarthome_choose_good_dialog_title).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b();
        this.o = new WeakReference<>(b2);
        return b2;
    }

    public RecommendSceneInfo.RecommendSceneItem g() {
        return this.d;
    }

    public void a(RecommendSceneInfo.RecommendSceneItem recommendSceneItem) {
        this.d = recommendSceneItem;
    }

    public void a(final Context context, JSONArray jSONArray) {
        if (jSONArray != null) {
            final XQProgressDialog xQProgressDialog = new XQProgressDialog(context);
            xQProgressDialog.setMessage(context.getString(R.string.get_data_from_server));
            xQProgressDialog.setCancelable(false);
            xQProgressDialog.show();
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                arrayList.add(jSONArray.opt(i2).toString());
            }
            a().a((List<String>) arrayList, (AsyncCallback<List<GoodInfo>, Error>) new AsyncCallback<List<GoodInfo>, Error>() {
                /* renamed from: a */
                public void onSuccess(List<GoodInfo> list) {
                    if ((context instanceof BaseActivity) && ((BaseActivity) context).isValid()) {
                        RecommendSceneManager.a().a(context, list).show();
                    }
                    xQProgressDialog.dismiss();
                }

                public void onFailure(Error error) {
                    xQProgressDialog.dismiss();
                }
            });
        }
    }

    public SceneTabFragment h() {
        return this.f;
    }

    public void a(SceneTabFragment sceneTabFragment) {
        this.f = sceneTabFragment;
    }

    public void a(boolean z) {
        MySceneFragmentNew mySceneFragmentNew;
        if (this.f != null && this.f.a() && (mySceneFragmentNew = this.f.j) != null) {
            mySceneFragmentNew.a(z);
        }
    }

    public boolean i() {
        return this.q;
    }

    public void b(boolean z) {
        this.r = z;
    }

    public boolean j() {
        return this.r;
    }

    public void c(boolean z) {
        this.q = z;
    }

    public String k() {
        return this.i;
    }

    public void b(String str) {
        this.i = str;
    }

    public void l() {
        if (h != null) {
            h.f21973a = null;
            this.e = false;
        }
    }

    public boolean m() {
        if (CoreApi.a().l()) {
            return CoreApi.a().D();
        }
        return ServerCompact.e(SHApplication.getAppContext()) || ServerCompact.o(SHApplication.getAppContext());
    }

    public boolean a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && b(str2, str) <= 0) {
            return false;
        }
        return true;
    }

    public int b(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return 0;
        }
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (TextUtils.isEmpty(str2)) {
            return 1;
        }
        String[] split = str.split("[._]");
        String[] split2 = str2.split("[._]");
        int min = Math.min(split.length, split2.length);
        int i2 = 0;
        while (i2 < min) {
            try {
                int parseInt = Integer.parseInt(split[i2]);
                int parseInt2 = Integer.parseInt(split2[i2]);
                if (parseInt != parseInt2) {
                    return parseInt - parseInt2;
                }
                i2++;
            } catch (Exception e2) {
                BluetoothLog.a((Throwable) e2);
                return 0;
            }
        }
        return split.length - split2.length;
    }

    public String a(Device device) {
        if (device == null || TextUtils.isEmpty(device.extra)) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject(device.extra);
            if (jSONObject.has("fw_version")) {
                return jSONObject.optString("fw_version");
            }
            return "";
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public void n() {
        h = null;
    }

    private SharedPreferences p() {
        String a2 = MD5Util.a(CoreApi.a().s());
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        Context appContext = SHApplication.getAppContext();
        return appContext.getSharedPreferences("recommend_scene_sp_name_" + a2, 0);
    }
}
