package com.xiaomi.smarthome.printer;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.AppConfigHelper;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.renderer.DeviceRenderer;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.printer.DeviceImageApi;
import com.xiaomi.smarthome.printer.SendPrintActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class SendPrintActivity extends BaseActivity implements View.OnClickListener, DeviceRenderer.LoadingCallback {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21164a = "SendPrintActivity";
    private View b;
    private ImageView c;
    /* access modifiers changed from: private */
    public RecyclerView d;
    private TextView e;
    private TextView f;
    private View g;
    private SmartHomeDeviceManager.IClientDeviceListener h = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void b(int i) {
        }

        public void a(int i) {
            SendPrintActivity.this.a(SmartHomeDeviceManager.a().d(), false);
        }

        public void a(int i, Device device) {
            SendPrintActivity.this.a(SmartHomeDeviceManager.a().d(), false);
        }
    };
    HashMap<String, String> mImageMap = new HashMap<>();

    public void onLoadingStart() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sendprint);
        this.b = findViewById(R.id.rl_root);
        this.c = (ImageView) findViewById(R.id.ivLoading);
        this.b.setVisibility(8);
        this.d = (RecyclerView) findViewById(R.id.rv_content);
        this.e = (TextView) findViewById(R.id.tvBuy);
        this.f = (TextView) findViewById(R.id.tvHint);
        ImageView imageView = (ImageView) findViewById(R.id.module_a_3_return_btn);
        this.g = findViewById(R.id.ll_content);
        imageView.setImageResource(R.drawable.common_title_bar_close);
        imageView.setOnClickListener(this);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.sendprint_title);
        d();
        SmartHomeDeviceManager.a().a(this.h);
        Log.i(f21164a, "onCreate");
        if (StartupCheckList.b()) {
            Log.i(f21164a, "check login");
            SHApplication.getApplication().onApplicationLifeCycleStart();
            a();
            return;
        }
        Log.i(f21164a, "isAllPass false");
        StartupCheckList.a((StartupCheckList.CheckListCallback) new StartupCheckList.CheckListCallback() {
            public void a() {
            }

            public void b() {
            }

            public void c() {
            }

            public void d() {
            }

            public void e() {
                Log.e("GetDeviceTask", "pass cta");
                SHApplication.getApplication().onApplicationLifeCycleStart();
                SendPrintActivity.this.a();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a() {
        CoreApi.a().a((Context) this, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                Log.i(SendPrintActivity.f21164a, "onLoginSuccess onCoreReady ");
                SmartHomeDeviceManager.a().a((SmartHomeDeviceManager.IsDeviceReadyCallback) new SmartHomeDeviceManager.IsDeviceReadyCallback() {
                    public final void onDeviceReady(List list) {
                        SendPrintActivity.AnonymousClass3.this.a(list);
                    }
                });
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a(List list) {
                Log.i(SendPrintActivity.f21164a, "onLoginSuccess onCoreReady onDeviceReady");
                SendPrintActivity.this.a(SmartHomeDeviceManager.a().d(), true);
            }
        });
    }

    private void b() {
        e();
        this.g.setVisibility(0);
        this.d.setVisibility(8);
        if (SHApplication.getStateNotifier().a() != 4) {
            Log.i(f21164a, "nologin");
            this.f.setText(R.string.sendprint_nologin);
            this.e.setText(R.string.sendprint_gologin);
            this.e.setVisibility(0);
            this.e.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    int a2 = SHApplication.getStateNotifier().a();
                    Log.i(SendPrintActivity.f21164a, "onClick startLogin currentState:" + a2);
                    if (a2 != 4) {
                        SendPrintActivity.this.d();
                        LoginApi.a().a((Context) SendPrintActivity.this, 1, (LoginApi.LoginCallback) new LoginApi.LoginCallback() {
                            public void a() {
                                SendPrintActivity.this.e();
                                SendPrintActivity.this.a(SmartHomeDeviceManager.a().d(), false);
                            }
                        });
                        return;
                    }
                    SendPrintActivity.this.a(SmartHomeDeviceManager.a().d(), false);
                }
            });
        } else if (ServerCompact.e((Context) this)) {
            Log.i(f21164a, "isInternationalServer");
            this.f.setText(R.string.sendprint_nodevicehint_internal);
            this.e.setVisibility(8);
        } else {
            Log.i(f21164a, "showLoading buy");
            this.f.setText(R.string.sendprint_nodevicehint);
            this.e.setText(R.string.sendprint_nodevicebuy);
            this.e.setVisibility(0);
            final AppConfigHelper appConfigHelper = new AppConfigHelper(this);
            this.e.setOnClickListener(new View.OnClickListener() {
                /* access modifiers changed from: private */
                public String c;

                public void onClick(View view) {
                    SendPrintActivity.this.d();
                    appConfigHelper.a("printer_buy_url", "1", "zh_CN", (AppConfigHelper.OnCacheHandler<JSONObject>) new AppConfigHelper.OnCacheHandler<JSONObject>() {
                        public boolean a(JSONObject jSONObject) throws Exception {
                            String unused = AnonymousClass5.this.c = new JSONObject(jSONObject.optJSONObject("result").optString("content")).optString("youpin_url");
                            return true;
                        }

                        public boolean b(JSONObject jSONObject) throws Exception {
                            return a(jSONObject);
                        }
                    }, (AppConfigHelper.JsonAsyncHandler) new AppConfigHelper.JsonAsyncHandler() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject, Response response) {
                            try {
                                SendPrintActivity.this.e();
                                FrameManager.b().k().loadWebView(new JSONObject(jSONObject.optJSONObject("result").optString("content")).optString("youpin_url"), "");
                            } catch (JSONException e) {
                                FrameManager.b().k().loadWebView(AnonymousClass5.this.c, "");
                                LogUtil.b(SendPrintActivity.f21164a, Log.getStackTraceString(e));
                            }
                        }

                        public void onFailure(Error error, Exception exc, Response response) {
                            if (!TextUtils.isEmpty(AnonymousClass5.this.c)) {
                                SendPrintActivity.this.e();
                                FrameManager.b().k().loadWebView(AnonymousClass5.this.c, "");
                            }
                        }
                    });
                }
            });
        }
    }

    private void a(Uri uri) {
        Cursor query;
        int columnIndex;
        if (uri != null) {
            String scheme = uri.getScheme();
            String str = null;
            if (scheme == null) {
                str = uri.getPath();
            } else if ("file".equals(scheme)) {
                str = uri.getPath();
            } else if ("content".equals(scheme) && (query = getContentResolver().query(uri, new String[]{Downloads._DATA}, (String) null, (String[]) null, (String) null)) != null) {
                if (query.moveToFirst() && (columnIndex = query.getColumnIndex(Downloads._DATA)) > -1) {
                    str = query.getString(columnIndex);
                }
                query.close();
            }
            getIntent().putExtra("file", str);
        }
    }

    /* access modifiers changed from: private */
    public void a(List<Device> list, boolean z) {
        Log.i(f21164a, "openPrinter devices:" + list);
        a((Uri) getIntent().getParcelableExtra("android.intent.extra.STREAM"));
        Uri uri = (Uri) getIntent().getParcelableExtra("android.intent.extra.STREAM");
        HashMap<String, PluginRecord> c2 = c();
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (Device next : list) {
                if (c2.containsKey(next.model)) {
                    arrayList.add(next);
                }
            }
            if (arrayList.size() == 0) {
                LogUtilGrey.a(f21164a, "openPrinter size:0");
                b();
            } else if (arrayList.size() != 1 || !z) {
                LogUtilGrey.a(f21164a, "openPrinter  else");
                this.b.setVisibility(0);
                this.g.setVisibility(8);
                this.d.setLayoutManager(new LinearLayoutManager(this));
                this.d.setAdapter(new SendPrintAdapter(arrayList));
                e();
            } else {
                LogUtilGrey.a(f21164a, "openPrinter  size:1");
                DeviceStat newDeviceStat = ((Device) arrayList.get(0)).newDeviceStat();
                SendMessageCallbackImpl.a(this, CoreApi.a().d(newDeviceStat.model), getIntent(), newDeviceStat, this);
            }
        } else {
            b();
        }
    }

    private HashMap<String, PluginRecord> c() {
        HashMap<String, PluginRecord> hashMap = new HashMap<>();
        for (PluginRecord next : CoreApi.a().O()) {
            PluginDeviceInfo c2 = next.c();
            if (c2 != null && c2.K() == 124) {
                hashMap.put(next.o(), next);
            }
        }
        final AtomicInteger atomicInteger = new AtomicInteger(hashMap.size());
        for (Map.Entry<String, PluginRecord> key : hashMap.entrySet()) {
            final String str = (String) key.getKey();
            DeviceImageApi.a(str, new AsyncCallback<DeviceImageApi.DeviceImageEntity, com.xiaomi.smarthome.frame.Error>() {
                public void onFailure(com.xiaomi.smarthome.frame.Error error) {
                }

                /* renamed from: a */
                public void onSuccess(DeviceImageApi.DeviceImageEntity deviceImageEntity) {
                    SendPrintActivity.this.mImageMap.put(str, deviceImageEntity.f21157a);
                    RecyclerView.Adapter adapter = SendPrintActivity.this.d.getAdapter();
                    if (atomicInteger.decrementAndGet() % 3 == 0 && adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    public void d() {
        this.c.setVisibility(0);
        Drawable drawable = this.c.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).start();
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        this.b.setVisibility(0);
        Drawable drawable = this.c.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).stop();
        }
        this.c.setVisibility(8);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.module_a_3_return_btn) {
            onBackPressed();
        }
    }

    public void onLoadingFinish(boolean z) {
        if (z) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        SmartHomeDeviceManager.a().c(this.h);
    }

    private class SendPrintAdapter extends RecyclerView.Adapter<SendPrintViewHolder> {
        private final ArrayList<Device> b;
        private Home c = HomeManager.a().m();

        public SendPrintAdapter(ArrayList<Device> arrayList) {
            this.b = arrayList;
            if (this.c == null) {
                this.c = new Home.Builder().g(SendPrintActivity.this.getString(R.string.default_home_name)).a();
            }
        }

        /* renamed from: a */
        public SendPrintViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SendPrintViewHolder(View.inflate(viewGroup.getContext(), R.layout.item_sendprint, (ViewGroup) null));
        }

        /* renamed from: a */
        public void onBindViewHolder(SendPrintViewHolder sendPrintViewHolder, int i) {
            final Device device = this.b.get(i);
            Home q = HomeManager.a().q(device.did);
            sendPrintViewHolder.f21176a.setText(device.name);
            TextView textView = sendPrintViewHolder.b;
            SendPrintActivity sendPrintActivity = SendPrintActivity.this;
            Object[] objArr = new Object[1];
            if (q == null) {
                q = this.c;
            }
            objArr[0] = q.k();
            textView.setText(sendPrintActivity.getString(R.string.app_printer_home, objArr));
            sendPrintViewHolder.c.setText(HomeManager.a().r(device.did));
            sendPrintViewHolder.d.setImageURI(SendPrintActivity.this.mImageMap.get(device.model));
            sendPrintViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    LogUtilGrey.a(SendPrintActivity.f21164a, "openPrinter  clickItem");
                    SendMessageCallbackImpl.a(SendPrintActivity.this, CoreApi.a().d(device.model), SendPrintActivity.this.getIntent(), device.newDeviceStat(), SendPrintActivity.this);
                }
            });
        }

        public int getItemCount() {
            return this.b.size();
        }
    }

    private class SendPrintViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f21176a;
        TextView b;
        TextView c;
        SimpleDraweeView d;

        public SendPrintViewHolder(View view) {
            super(view);
            this.f21176a = (TextView) view.findViewById(R.id.tv_name);
            this.b = (TextView) view.findViewById(R.id.tv_device_desc);
            this.c = (TextView) view.findViewById(R.id.tv_room);
            this.d = (SimpleDraweeView) view.findViewById(R.id.ivImage);
        }
    }
}
