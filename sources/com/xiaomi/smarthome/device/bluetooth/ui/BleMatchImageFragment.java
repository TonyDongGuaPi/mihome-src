package com.xiaomi.smarthome.device.bluetooth.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest;
import com.xiaomi.smarthome.core.server.internal.device.DeviceFactory;
import com.xiaomi.smarthome.device.BaikeApi;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.MiioBtSearchResponse;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.operation.js_sdk.OperationCommonWebViewActivity;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity;
import com.xiaomi.smarthome.stat.STAT;
import io.reactivex.functions.Consumer;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONObject;

public class BleMatchImageFragment extends BleMatchFragment {
    private static final String d = "pair_image_ninebot";
    private static final int e = 10000;
    /* access modifiers changed from: private */
    public ProgressBar f;
    private SimpleDraweeView g;
    /* access modifiers changed from: private */
    public ImageView h;
    private Button i;
    private TextView j;
    private ImageView k;
    private BleDevice l;
    private AnimationDrawable m;
    /* access modifiers changed from: private */
    public Handler n;
    /* access modifiers changed from: private */
    public boolean o = false;
    private TextView p;
    /* access modifiers changed from: private */
    public Runnable q = new Runnable() {
        public void run() {
            if (!BleMatchImageFragment.this.o) {
                if (BLEDeviceManager.e()) {
                    BLEDeviceManager.a(new SearchRequest.Builder().a(10000, 3).a(), BleMatchImageFragment.this.r);
                } else {
                    BleMatchImageFragment.this.n.postDelayed(BleMatchImageFragment.this.q, 1000);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public final MiioBtSearchResponse r = new MiioBtSearchResponse() {
        public void a() {
            BleMatchImageFragment.this.a(true);
        }

        public void a(BleDevice bleDevice) {
            BleMatchImageFragment.this.a(bleDevice);
        }

        public void b() {
            BleMatchImageFragment.this.g();
        }

        public void c() {
            BleMatchImageFragment.this.h();
        }
    };

    private interface PairImageCallback {
        void a(String str);
    }

    public static BleMatchImageFragment b() {
        return new BleMatchImageFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.ble_bind_image, (ViewGroup) null);
        this.g = (SimpleDraweeView) inflate.findViewById(R.id.icon);
        this.h = (ImageView) inflate.findViewById(R.id.loading);
        this.i = (Button) inflate.findViewById(R.id.retry);
        this.k = (ImageView) inflate.findViewById(R.id.arrow);
        this.f = (ProgressBar) inflate.findViewById(R.id.pbar);
        this.p = (TextView) inflate.findViewById(R.id.device_detail);
        this.j = (TextView) inflate.findViewById(R.id.devices);
        this.j.getPaint().setFlags(8);
        this.n = new Handler(Looper.getMainLooper());
        this.g.setHierarchy(new GenericDraweeHierarchyBuilder(this.g.getResources()).setFadeDuration(200).build());
        if (((BleMatchActivity) this.mContext).hasMatchListFragment()) {
            this.j.setVisibility(0);
            this.k.setVisibility(0);
        } else {
            this.j.setVisibility(8);
            this.k.setVisibility(8);
        }
        this.m = (AnimationDrawable) this.mContext.getResources().getDrawable(R.drawable.ble_loading);
        if (this.f15225a == null) {
            BluetoothMyLogger.c("BleMatchImageFragment mDevice is null");
        }
        if (this.f15225a != null) {
            if (TextUtils.equals(this.f15225a.model, DeviceFactory.C) || TextUtils.equals(this.f15225a.model, DeviceFactory.D)) {
                a(this.f15225a.model, (PairImageCallback) new PairImageCallback() {
                    public void a(final String str) {
                        Activity activity = (Activity) BleMatchImageFragment.this.mContext;
                        if (activity != null && !activity.isFinishing()) {
                            BleMatchImageFragment.this.n.post(new Runnable() {
                                public void run() {
                                    BleMatchImageFragment.this.b(str);
                                }
                            });
                        }
                    }
                });
            } else {
                b(this.f15225a.t());
            }
            STAT.c.e(this.f15225a.model, SmartConfigMainActivity.DEVICE_FROM_APP_PLUS_TYPE == 7 ? 1 : 2);
        }
        f();
        return inflate;
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        if (this.f15225a == null) {
            return;
        }
        if (DeviceUtils.a(this.f15225a)) {
            ((GenericDraweeHierarchy) this.g.getHierarchy()).setActualImageScaleType(ScalingUtils.ScaleType.CENTER);
            this.g.setImageURI(CommonUtils.c((int) R.drawable.mikey_binding_new));
            this.f.setVisibility(8);
            this.h.setVisibility(0);
            e();
        } else if (!TextUtils.isEmpty(str)) {
            AnonymousClass2 r0 = new BaseControllerListener() {
                public void onFinalImageSet(String str, @Nullable Object obj, @Nullable Animatable animatable) {
                    BleMatchImageFragment.this.f.setVisibility(8);
                    BleMatchImageFragment.this.h.setVisibility(0);
                }

                public void onFailure(String str, Throwable th) {
                    super.onFailure(str, th);
                }
            };
            this.g.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setControllerListener(r0)).setImageRequest(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setResizeOptions(new ResizeOptions(540, 960)).build())).build());
            e();
        }
    }

    @SuppressLint({"CheckResult"})
    private void e() {
        BaikeApi.a(this.f15225a.model).subscribe(new Consumer() {
            public final void accept(Object obj) {
                BleMatchImageFragment.this.c((String) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(String str) throws Exception {
        this.p.setVisibility(0);
        this.p.setOnClickListener(new View.OnClickListener(str) {
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                BleMatchImageFragment.this.a(this.f$1, view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(String str, View view) {
        OperationCommonWebViewActivity.start(getContext(), str, (String) null);
        STAT.d.bn(this.f15225a.model);
    }

    private void a(final String str, final PairImageCallback pairImageCallback) {
        Request request;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TSMAuthContants.PARAM_LANGUAGE, "zh_CN");
            StringBuilder sb = new StringBuilder();
            sb.append(d);
            sb.append(GlobalSetting.E ? "_preview" : "");
            jSONObject.put("name", sb.toString());
            jSONObject.put("version", "1");
        } catch (Exception unused) {
        }
        try {
            request = new Request.Builder().a("GET").b(a(jSONObject)).a();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            request = null;
        }
        if (request != null) {
            HttpApi.a(request, (AsyncHandler) new AsyncHandler() {
                public void onFailure(Error error, Exception exc, Response response) {
                }

                public void onSuccess(Object obj, Response response) {
                }

                public void processFailure(Call call, IOException iOException) {
                }

                public void processResponse(Response response) {
                    JSONObject optJSONObject;
                    String str;
                    try {
                        JSONObject optJSONObject2 = new JSONObject(response.body().string()).optJSONObject("result");
                        if (optJSONObject2 != null) {
                            String optString = optJSONObject2.optString("content");
                            if (!TextUtils.isEmpty(optString) && (optJSONObject = new JSONObject(optString).optJSONObject(str)) != null) {
                                Locale I = CoreApi.a().I();
                                if (I == null) {
                                    I = Locale.getDefault();
                                }
                                String lowerCase = I.getLanguage().toLowerCase();
                                if ("zh".equals(lowerCase)) {
                                    str = optJSONObject.optString("zh-Hans");
                                } else {
                                    str = optJSONObject.optString(lowerCase);
                                }
                                if (TextUtils.isEmpty(str)) {
                                    str = optJSONObject.optString("en");
                                }
                                pairImageCallback.a(str);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private String a(JSONObject jSONObject) throws UnsupportedEncodingException {
        return ServerRouteUtil.a(SHApplication.getAppContext()) + "/app/service/getappconfig?data=" + URLEncoder.encode(jSONObject.toString(), "UTF-8");
    }

    public void c() {
        BluetoothMyLogger.f("startWatchAdvertisement isMatchByKey");
        if (BLEDeviceManager.e()) {
            BLEDeviceManager.a(new SearchRequest.Builder().a(10000, 3).a(), this.r);
            return;
        }
        BLEDeviceManager.f();
        this.n.removeCallbacks(this.q);
        this.n.postDelayed(this.q, 1000);
    }

    public void d() {
        Log.i("stopScan", "BMIF stop");
        BluetoothHelper.b();
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            this.i.setVisibility(8);
            this.h.setVisibility(0);
            this.h.setImageDrawable(this.m);
            this.m.start();
            return;
        }
        this.i.setVisibility(0);
        this.h.setVisibility(8);
        this.m.stop();
    }

    private void f() {
        this.i.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BleMatchImageFragment.this.c();
            }
        });
        this.j.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((BleMatchActivity) BleMatchImageFragment.this.mContext).switchToMatchListFragment();
            }
        });
        this.k.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((BleMatchActivity) BleMatchImageFragment.this.mContext).switchToMatchListFragment();
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        if (com.xiaomi.smarthome.device.bluetooth.MikeyAdvParser.b(r0) != false) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0054, code lost:
        if (r7.d().b() != false) goto L_0x0056;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.xiaomi.smarthome.device.BleDevice r7) {
        /*
            r6 = this;
            com.xiaomi.smarthome.device.BleDevice r0 = r6.f15225a
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            com.xiaomi.smarthome.device.BleDevice r0 = r6.f15225a
            java.lang.String r0 = r0.model
            java.lang.String r1 = r7.model
            boolean r0 = r0.equals(r1)
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x001e
            java.lang.String r0 = r7.model
            boolean r0 = r6.a((java.lang.String) r0)
            if (r0 == 0) goto L_0x001c
            goto L_0x001e
        L_0x001c:
            r0 = 0
            goto L_0x001f
        L_0x001e:
            r0 = 1
        L_0x001f:
            if (r0 == 0) goto L_0x0089
            boolean r0 = com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager.c((com.xiaomi.smarthome.device.BleDevice) r7)
            if (r0 == 0) goto L_0x0028
            goto L_0x0089
        L_0x0028:
            com.xiaomi.smarthome.device.BleDevice r0 = r6.f15225a
            java.lang.String r0 = r0.model
            java.lang.String r3 = "xiaomi.mikey.v1"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0046
            com.xiaomi.smarthome.bluetooth.XmBluetoothDevice r0 = com.xiaomi.smarthome.device.bluetooth.BleObjectTranslator.a((com.xiaomi.smarthome.device.BleDevice) r7)
            com.xiaomi.smarthome.device.bluetooth.MikeyAdvParser$ParseResult r3 = com.xiaomi.smarthome.device.bluetooth.MikeyAdvParser.a((com.xiaomi.smarthome.bluetooth.XmBluetoothDevice) r0)
            if (r3 == 0) goto L_0x003f
            goto L_0x0056
        L_0x003f:
            boolean r0 = com.xiaomi.smarthome.device.bluetooth.MikeyAdvParser.b((com.xiaomi.smarthome.bluetooth.XmBluetoothDevice) r0)
            if (r0 == 0) goto L_0x0058
            goto L_0x0056
        L_0x0046:
            com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket r0 = r7.d()
            if (r0 == 0) goto L_0x0058
            com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket r0 = r7.d()
            boolean r0 = r0.b()
            if (r0 == 0) goto L_0x0058
        L_0x0056:
            r0 = 1
            goto L_0x0059
        L_0x0058:
            r0 = 0
        L_0x0059:
            java.lang.String r3 = ">>> for %s: isMiot = %b, isBinding = %b"
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.String r5 = r7.mac
            java.lang.String r5 = com.xiaomi.smarthome.frame.log.BluetoothMyLogger.a(r5)
            r4[r1] = r5
            com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket r5 = r7.d()
            if (r5 == 0) goto L_0x006d
            r1 = 1
        L_0x006d:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r4[r2] = r1
            r1 = 2
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r0)
            r4[r1] = r2
            java.lang.String r1 = java.lang.String.format(r3, r4)
            com.xiaomi.smarthome.frame.log.BluetoothMyLogger.d(r1)
            if (r0 == 0) goto L_0x0088
            r6.l = r7
            r6.d()
        L_0x0088:
            return
        L_0x0089:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.bluetooth.ui.BleMatchImageFragment.a(com.xiaomi.smarthome.device.BleDevice):void");
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.l == null) {
            a(false);
        } else {
            ((BleMatchActivity) this.mContext).onDeviceMatched(this.l);
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.l != null) {
            this.h.setVisibility(4);
            ((BleMatchActivity) this.mContext).onDeviceMatched(this.l);
        }
    }

    public void onPause() {
        super.onPause();
        d();
    }

    public void onResume() {
        super.onResume();
        c();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.o = true;
        this.m.stop();
        this.n.removeCallbacks(this.q);
    }
}
