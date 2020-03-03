package com.xiaomi.smarthome.mibrain.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Vibrator;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.exoplayer2.metadata.id3.InternalFrame;
import com.tencent.smtt.sdk.QbSdk;
import com.xiaomi.ai.AsrListener;
import com.xiaomi.ai.SpeechError;
import com.xiaomi.ai.SpeechResult;
import com.xiaomi.passport.ui.settings.UserAvatarUpdateActivity;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.runtime.util.TitleBarUtil;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.permission.PermissionHelper;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.mibrain.MiBrainManager;
import com.xiaomi.smarthome.mibrain.model.NLPResultInfo;
import com.xiaomi.smarthome.mibrain.sip.SipApi;
import com.xiaomi.smarthome.mibrain.sip.model.StartResult;
import com.xiaomi.smarthome.mibrain.viewutil.waveview.AudioRecordWaveView;
import com.xiaomi.smarthome.mibrain.viewutil.waveview.MiBrainRecycleViewLayout2;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.voice.VoiceManager;
import com.xiaomi.voiceassistant.mijava.AiServiceError;
import com.xiaomi.voiceassistant.mijava.MiBrainCloudSDKManager;
import com.xiaomi.voiceassistant.mijava.NLPResultCallBack;
import com.yanzhenjie.permission.Action;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MiBrainActivityCardStyle extends BaseActivity {
    private static final int A = 8;
    private static final int B = 9;
    private static final int C = 10;
    private static final int D = 11;
    public static final int DEFAULT = 0;
    private static final int E = 12;
    public static final int ERROR = 3;
    private static final int F = 13;
    private static final int G = 14;
    private static final int H = 4000;
    public static final String INTENT_KEY_DID = "device_id";
    public static final String INTENT_KEY_TIPS = "device_tips";
    public static final int LISTENING = 1;
    public static final int WORKING = 2;
    /* access modifiers changed from: private */
    public static final String b = "MiBrainActivityCardStyle";
    private static final int e = 100;
    private static final int v = 0;
    private static final int w = 1;
    private static final int x = 2;
    private static final int y = 6;
    private static final int z = 7;
    /* access modifiers changed from: private */
    public Handler I = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            String access$000 = MiBrainActivityCardStyle.b;
            LogUtilGrey.a(access$000, "msg.what" + message.what);
            switch (message.what) {
                case 0:
                    MiBrainActivityCardStyle.this.m();
                    return;
                case 1:
                    MiBrainActivityCardStyle.this.k();
                    return;
                case 2:
                    MiBrainActivityCardStyle.this.l();
                    return;
                case 6:
                    MiBrainActivityCardStyle.this.a(true, message.obj);
                    return;
                case 7:
                    MiBrainActivityCardStyle.this.a(false, message.obj);
                    return;
                case 8:
                    MiBrainActivityCardStyle.this.a((Object) MiBrainActivityCardStyle.this.getResources().getString(R.string.mi_brain_can_not_use_mi_brain), 8);
                    return;
                case 9:
                    MiBrainActivityCardStyle.this.a((Object) MiBrainActivityCardStyle.this.getResources().getString(R.string.mi_brain_can_not_use_mi_brain), 9);
                    return;
                case 10:
                    MiBrainActivityCardStyle.this.a(message.obj, 10);
                    if (message.obj != null) {
                        MiBrainActivityCardStyle.this.b(message.obj.toString());
                        return;
                    }
                    return;
                case 11:
                    MiBrainActivityCardStyle.this.a((List<String>) MiBrainActivityCardStyle.this.p);
                    return;
                case 12:
                    MiBrainActivityCardStyle.this.a(message.obj, false);
                    return;
                case 13:
                    MiBrainActivityCardStyle.this.a();
                    return;
                case 14:
                    MiBrainActivityCardStyle.this.a(message.obj, true);
                    return;
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public View.OnClickListener J = new View.OnClickListener() {
        public void onClick(View view) {
            StatHelper.aq();
            if (MiBrainActivityCardStyle.this.c == null) {
                ToastUtil.a((int) R.string.mi_brain_session_err);
            } else if (MiBrainActivityCardStyle.this.miBrainCloudSDKManager != null) {
                String access$000 = MiBrainActivityCardStyle.b;
                LogUtilGrey.a(access$000, "mAudioRecordSogou.getStatus()----&&&&" + MiBrainActivityCardStyle.this.f10574a);
                switch (MiBrainActivityCardStyle.this.f10574a) {
                    case 0:
                        MiBrainActivityCardStyle.this.h();
                        return;
                    case 1:
                        MiBrainActivityCardStyle.this.i();
                        return;
                    case 2:
                        MiBrainActivityCardStyle.this.i();
                        return;
                    case 3:
                        MiBrainActivityCardStyle.this.h();
                        return;
                    default:
                        return;
                }
            }
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public int f10574a = 0;
    /* access modifiers changed from: private */
    public StartResult c;
    /* access modifiers changed from: private */
    public NLPResultInfo d;
    private TextView f;
    private TextView g;
    private TextView h;
    private View i;
    private TextView j;
    private View k;
    private TextView l;
    /* access modifiers changed from: private */
    public Vibrator m;
    AsrListenerImpl mAsrListenerImpl = new AsrListenerImpl();
    @BindView(2131427731)
    AudioRecordWaveView mAudioRecordWaveView;
    @BindView(2131428545)
    View mCardContainer;
    @BindView(2131430849)
    View mContentView;
    LayoutInflater mInflater;
    @BindView(2131430797)
    View mMaskView;
    @BindView(2131430871)
    TextView mMiBrainHintTv;
    NLPResultCallBack mNLPResultCallBack = new NLPResultCallBack() {
        public void a(AiServiceError aiServiceError) {
            String access$000 = MiBrainActivityCardStyle.b;
            LogUtilGrey.a(access$000, "       isListening:" + MiBrainActivityCardStyle.this.s + "mAudioRecordSogou.getStatus()NLP onError " + aiServiceError.toString() + "       isListening:" + MiBrainActivityCardStyle.this.s);
            Message obtain = Message.obtain();
            obtain.what = 10;
            if (aiServiceError.e == AiServiceError.d) {
                obtain.obj = aiServiceError.g;
            } else {
                obtain.obj = MiBrainManager.a().b(aiServiceError.f);
            }
            obtain.arg1 = aiServiceError.f;
            MiBrainActivityCardStyle.this.I.sendMessageDelayed(obtain, 100);
        }

        public void a(String str) {
            MiBrainActivityCardStyle.this.I.removeMessages(11);
            String access$000 = MiBrainActivityCardStyle.b;
            LogUtilGrey.a(access$000, "       isListening:" + MiBrainActivityCardStyle.this.s + "mStatus" + MiBrainActivityCardStyle.this.f10574a + "NLP success " + str);
            NLPResultInfo unused = MiBrainActivityCardStyle.this.d = NLPResultInfo.a(str);
            if (MiBrainActivityCardStyle.this.d != null) {
                if (!TextUtils.isEmpty(MiBrainActivityCardStyle.this.d.g) && MiBrainActivityCardStyle.this.d.g.equalsIgnoreCase(UserAvatarUpdateActivity.CAMERA)) {
                    Message obtain = Message.obtain();
                    obtain.what = 10;
                    obtain.obj = MiBrainActivityCardStyle.this.getResources().getString(R.string.mi_brain_not_support);
                    MiBrainActivityCardStyle.this.I.sendMessageDelayed(obtain, 100);
                } else if (MiBrainActivityCardStyle.this.d.f == 0) {
                    Message obtain2 = Message.obtain();
                    obtain2.obj = TextUtils.isEmpty(MiBrainActivityCardStyle.this.d.c) ? MiBrainActivityCardStyle.this.getResources().getString(R.string.mi_brain_iot_success) : MiBrainActivityCardStyle.this.d.c;
                    obtain2.what = MiBrainActivityCardStyle.this.d.f10654a == 200 ? 6 : 7;
                    MiBrainActivityCardStyle.this.I.sendMessage(obtain2);
                } else if (MiBrainActivityCardStyle.this.d.f == 1) {
                    MiBrainActivityCardStyle.this.b(MiBrainActivityCardStyle.this.d.c);
                    MiBrainActivityCardStyle.this.I.sendEmptyMessage(13);
                } else {
                    MiBrainActivityCardStyle.this.b(MiBrainActivityCardStyle.this.d.c);
                    Message obtain3 = Message.obtain();
                    obtain3.what = 10;
                    obtain3.obj = MiBrainActivityCardStyle.this.d.c;
                    obtain3.arg1 = QbSdk.EXTENSION_INIT_FAILURE;
                    MiBrainActivityCardStyle.this.I.sendMessageDelayed(obtain3, 100);
                }
            }
        }
    };
    @BindView(2131430873)
    View mTipsView;
    MiBrainCloudSDKManager miBrainCloudSDKManager;
    @BindView(2131430865)
    MiBrainRecycleViewLayout2 miBrainRecycleViewLayout;
    private String n;
    /* access modifiers changed from: private */
    public Device o;
    /* access modifiers changed from: private */
    public List<String> p;
    private XQProgressDialog q;
    private boolean r = false;
    /* access modifiers changed from: private */
    public boolean s = false;
    private AudioManager t;
    private AudioManager.OnAudioFocusChangeListener u;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        TitleBarUtil.enableWhiteTranslucentStatus((Activity) this);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_mi_brain_card_style_layout);
        ButterKnife.bind((Activity) this);
        Intent intent = getIntent();
        if (intent == null) {
            str = "";
        } else {
            str = intent.getStringExtra("device_id");
        }
        this.n = str;
        this.o = SmartHomeDeviceManager.a().b(this.n);
        if (this.o == null) {
            String str2 = b;
            LogUtilGrey.a(str2, "device is null for " + this.n);
            finish();
            return;
        }
        this.m = (Vibrator) getSystemService("vibrator");
        d();
        a(intent);
        b();
        STAT.e.k(this.o.model);
        this.mCardContainer.animate().translationY((float) DisplayUtils.a((Activity) this, 0.0f)).start();
    }

    private void a(Intent intent) {
        String stringExtra = intent == null ? "" : intent.getStringExtra(INTENT_KEY_TIPS);
        if (this.o != null) {
            if (TextUtils.isEmpty(stringExtra)) {
                List<String> b2 = MiBrainManager.a().b(this.n);
                if (b2 == null) {
                    LogUtil.a(b, "getMiBrainTips return null");
                    b2 = new ArrayList<>();
                }
                if (b2.isEmpty()) {
                    showDialog();
                    RemoteFamilyApi.a().f(this.n, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            if (MiBrainActivityCardStyle.this.isValid()) {
                                MiBrainActivityCardStyle.this.dismissDialog();
                                List unused = MiBrainActivityCardStyle.this.p = MiBrainActivityCardStyle.this.a(jSONObject);
                                MiBrainActivityCardStyle.this.I.sendEmptyMessage(11);
                            }
                        }

                        public void onFailure(Error error) {
                            if (MiBrainActivityCardStyle.this.isValid()) {
                                MiBrainActivityCardStyle.this.dismissDialog();
                            }
                        }
                    });
                    return;
                }
                this.p = b2;
                a(b2);
                return;
            }
            try {
                this.p = a(new JSONObject(stringExtra));
                this.I.sendEmptyMessage(11);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void showDialog() {
        dismissDialog();
        this.q = XQProgressDialog.a(this, "", getString(R.string.loading));
    }

    public void dismissDialog() {
        if (this.q != null) {
            this.q.dismiss();
            this.q = null;
        }
    }

    /* access modifiers changed from: private */
    @UiThread
    public void a(Object obj, boolean z2) {
        if (obj != null && !TextUtils.isEmpty(obj.toString())) {
            this.mMiBrainHintTv.setVisibility(8);
            this.mTipsView.setVisibility(8);
            this.mContentView.setVisibility(0);
            this.i.setVisibility(0);
            this.k.setVisibility(8);
            this.j.setVisibility(0);
            this.j.setText(obj.toString());
            this.i.requestLayout();
            if (z2) {
                this.I.sendEmptyMessageDelayed(11, 4000);
            }
        }
    }

    /* access modifiers changed from: private */
    @UiThread
    public void a() {
        this.I.removeMessages(11);
        this.mMiBrainHintTv.setVisibility(8);
        this.mTipsView.setVisibility(8);
        this.k.setVisibility(0);
        this.l.setText(this.d.c);
        this.i.setVisibility(8);
        if (this.d.e == null || this.d.e.size() <= 0 || TextUtils.isEmpty(this.d.e.get(0).d)) {
            this.miBrainRecycleViewLayout.show(this.d.e, this.d.c, "");
        } else {
            this.miBrainRecycleViewLayout.show(this.d.e, this.d.c, this.d.e.get(0).d);
        }
    }

    /* access modifiers changed from: private */
    @UiThread
    public void a(List<String> list) {
        if (list == null || list.isEmpty()) {
            this.mTipsView.setVisibility(8);
            return;
        }
        this.mMiBrainHintTv.setVisibility(8);
        this.mTipsView.setVisibility(0);
        TextView textView = this.f;
        textView.setText("“" + list.get(0) + "”");
        if (list.size() > 1) {
            TextView textView2 = this.g;
            textView2.setText("“" + list.get(1) + "”");
        } else {
            this.g.setVisibility(8);
        }
        if (list.size() > 2) {
            TextView textView3 = this.h;
            textView3.setText("“" + list.get(2) + "”");
        } else {
            this.h.setVisibility(8);
        }
        this.mContentView.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public List<String> a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        try {
            Object obj = jSONObject.get("device");
            if (obj != null) {
                if (obj instanceof JSONObject) {
                    Object obj2 = ((JSONObject) obj).get("tips");
                    if (obj2 != null) {
                        if (obj2 instanceof JSONArray) {
                            for (int i2 = 0; i2 < ((JSONArray) obj2).length(); i2++) {
                                Object obj3 = ((JSONArray) obj2).get(i2);
                                if (obj3 != null) {
                                    if (obj3 instanceof JSONObject) {
                                        Object obj4 = ((JSONObject) obj3).get("contents");
                                        if (obj4 == null) {
                                            continue;
                                        } else if (obj4 instanceof JSONArray) {
                                            int i3 = 0;
                                            while (true) {
                                                if (i3 >= ((JSONArray) obj4).length()) {
                                                    break;
                                                }
                                                Object obj5 = ((JSONArray) obj4).get(i3);
                                                if (obj5 != null) {
                                                    if (obj5 instanceof String) {
                                                        arrayList.add((String) obj5);
                                                        if (arrayList.size() >= 3) {
                                                            break;
                                                        }
                                                    }
                                                }
                                                i3++;
                                            }
                                            if (arrayList.size() >= 3) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            return arrayList;
                        }
                    }
                    return arrayList;
                }
            }
            return arrayList;
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void b() {
        try {
            this.t = (AudioManager) getSystemService("audio");
            this.u = new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int i) {
                }
            };
            this.t.requestAudioFocus(this.u, 3, 2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void c() {
        try {
            this.t.abandonAudioFocus(this.u);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.miBrainCloudSDKManager != null) {
            this.miBrainCloudSDKManager.j();
            this.miBrainCloudSDKManager.a((NLPResultCallBack) null);
            this.miBrainCloudSDKManager.a((AsrListener) null);
            this.miBrainCloudSDKManager = null;
        }
        c();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        String str = b;
        LogUtilGrey.a(str, b + "onResume" + this.r);
        super.onResume();
        if (!MiBrainManager.l()) {
            if (!VoiceManager.a().a(SHApplication.getAppContext())) {
                this.I.sendEmptyMessageDelayed(9, 100);
                return;
            }
            PermissionHelper.f(this, true, new Action() {
                public void onAction(List<String> list) {
                    MiBrainActivityCardStyle.this.g();
                }
            });
        }
        if (((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo() == null) {
            this.I.sendEmptyMessage(8);
        }
    }

    private void d() {
        this.mInflater = LayoutInflater.from(this);
        f();
        this.f = (TextView) this.mTipsView.findViewById(R.id.tips_tv_1);
        this.g = (TextView) this.mTipsView.findViewById(R.id.tips_tv_2);
        this.h = (TextView) this.mTipsView.findViewById(R.id.tips_tv_3);
        this.i = this.mContentView.findViewById(R.id.single_result);
        this.j = (TextView) this.i.findViewById(R.id.single_result_tv);
        this.k = this.mContentView.findViewById(R.id.multi_result);
        this.l = (TextView) this.k.findViewById(R.id.multi_result_title_tv);
        this.I.sendEmptyMessage(0);
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiBrainActivityCardStyle.this.e();
            }
        });
    }

    /* access modifiers changed from: private */
    public void e() {
        this.mCardContainer.animate().translationY((float) DisplayUtils.a((Activity) this, 600.0f)).start();
        this.mMaskView.animate().alpha(0.0f).withEndAction(new Runnable() {
            public void run() {
                MiBrainActivityCardStyle.this.overridePendingTransition(0, 0);
                MiBrainActivityCardStyle.this.finish();
            }
        }).start();
    }

    private void f() {
        this.mAudioRecordWaveView.setVoiceIconClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                MiBrainActivityCardStyle.this.mAudioRecordWaveView.setEnabled(false);
                MiBrainActivityCardStyle.this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        MiBrainActivityCardStyle.this.mAudioRecordWaveView.setEnabled(true);
                    }
                }, 2000);
                if (MiBrainActivityCardStyle.this.m.hasVibrator()) {
                    MiBrainActivityCardStyle.this.m.vibrate(100);
                }
                if (MiBrainManager.l()) {
                    STAT.d.d(MiBrainActivityCardStyle.this.o.model, 1);
                    MiBrainManager.m();
                    return;
                }
                PermissionHelper.b(MiBrainActivityCardStyle.this, true, new Action() {
                    public void onAction(List<String> list) {
                        STAT.d.d(MiBrainActivityCardStyle.this.o.model, 2);
                        if (MiBrainActivityCardStyle.this.J != null) {
                            MiBrainActivityCardStyle.this.J.onClick(view);
                        }
                        MiBrainActivityCardStyle.this.mAudioRecordWaveView.startAnimation();
                    }
                });
            }
        });
    }

    public void onBackPressed() {
        j();
        e();
    }

    /* access modifiers changed from: private */
    public void g() {
        SipApi.a().a(this, new AsyncCallback<StartResult, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(StartResult startResult) {
                if (MiBrainActivityCardStyle.this.c == null) {
                    StartResult unused = MiBrainActivityCardStyle.this.c = startResult;
                    if (MiBrainActivityCardStyle.this.c == null) {
                        ToastUtil.a((CharSequence) "session is null");
                        return;
                    }
                    String access$000 = MiBrainActivityCardStyle.b;
                    LogUtilGrey.a(access$000, "session id=" + MiBrainActivityCardStyle.this.c.f10711a);
                    String access$0002 = MiBrainActivityCardStyle.b;
                    LogUtilGrey.a(access$0002, "token=" + MiBrainActivityCardStyle.this.c.b);
                    String access$0003 = MiBrainActivityCardStyle.b;
                    LogUtilGrey.a(access$0003, "expire=" + MiBrainActivityCardStyle.this.c.c);
                    MiBrainActivityCardStyle.this.miBrainCloudSDKManager = MiBrainCloudSDKManager.a(MiBrainActivityCardStyle.this, CoreApi.a().s(), MiBrainActivityCardStyle.this.c.b, MiBrainActivityCardStyle.this.c.f10711a, true);
                    MiBrainActivityCardStyle.this.miBrainCloudSDKManager.a(MiBrainActivityCardStyle.this.mNLPResultCallBack);
                    MiBrainActivityCardStyle.this.miBrainCloudSDKManager.a((AsrListener) MiBrainActivityCardStyle.this.mAsrListenerImpl);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return "“" + str + "”";
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.miBrainCloudSDKManager != null && !this.s) {
            this.s = true;
            MiBrainManager.a().i();
            this.I.sendEmptyMessage(1);
            this.I.postDelayed(new Runnable() {
                public void run() {
                    MiBrainCloudSDKManager miBrainCloudSDKManager = MiBrainActivityCardStyle.this.miBrainCloudSDKManager;
                    if (miBrainCloudSDKManager != null) {
                        int unused = MiBrainActivityCardStyle.this.f10574a = 1;
                        miBrainCloudSDKManager.i();
                    }
                }
            }, 200);
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.miBrainCloudSDKManager != null) {
            if (this.f10574a == 1 || this.f10574a == 2) {
                this.miBrainCloudSDKManager.k();
                this.f10574a = 0;
            }
        }
    }

    private void j() {
        if (this.miBrainCloudSDKManager != null) {
            if (this.f10574a == 1 || this.f10574a == 2) {
                this.miBrainCloudSDKManager.k();
                this.f10574a = 0;
            }
            if (this.miBrainCloudSDKManager != null) {
                this.miBrainCloudSDKManager.j();
            }
        }
    }

    /* access modifiers changed from: private */
    public void k() {
        this.mTipsView.setVisibility(8);
        this.mMiBrainHintTv.setVisibility(0);
        this.miBrainRecycleViewLayout.hide();
        this.mContentView.setVisibility(8);
        this.k.setVisibility(8);
        this.i.setVisibility(0);
        this.j.setText("");
        this.mAudioRecordWaveView.enterListeningUI();
    }

    /* access modifiers changed from: private */
    public void l() {
        this.mMiBrainHintTv.setVisibility(8);
        this.mTipsView.setVisibility(8);
        this.mAudioRecordWaveView.exitListeningUI();
    }

    /* access modifiers changed from: private */
    public void m() {
        this.mTipsView.setVisibility(0);
        this.mContentView.setVisibility(8);
        this.miBrainRecycleViewLayout.hide();
        this.mAudioRecordWaveView.exitListeningUI();
        this.mMiBrainHintTv.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void a(boolean z2, Object obj) {
        String str = b;
        LogUtilGrey.a(str, "enterIOTResultUI   isListening:" + this.s);
        if (!this.s) {
            this.mMiBrainHintTv.setVisibility(8);
            this.mTipsView.setVisibility(8);
            this.miBrainRecycleViewLayout.hide();
            this.mContentView.setVisibility(0);
            this.i.setVisibility(0);
            this.j.setVisibility(0);
            this.k.setVisibility(8);
            this.mAudioRecordWaveView.exitListeningUI();
            if (obj != null) {
                this.j.setText(obj.toString());
                this.i.requestLayout();
                b(obj.toString());
            } else {
                this.j.setText("");
            }
            this.I.sendEmptyMessageDelayed(11, 4000);
        }
    }

    /* access modifiers changed from: private */
    public void a(Object obj, int i2) {
        String str = b;
        LogUtilGrey.a(str, "enterErrorUI isListening" + this.s);
        this.mTipsView.setVisibility(8);
        this.miBrainRecycleViewLayout.hide();
        this.mMiBrainHintTv.setVisibility(8);
        this.mContentView.setVisibility(0);
        this.i.setVisibility(0);
        this.j.setVisibility(0);
        this.k.setVisibility(8);
        this.mAudioRecordWaveView.exitListeningUI();
        this.I.sendEmptyMessageDelayed(11, 4000);
        if (obj != null) {
            this.j.setText(obj.toString());
            this.i.requestLayout();
        } else {
            this.j.setText("");
        }
        if (i2 == 8) {
            this.r = true;
        } else if (i2 == 9) {
            this.r = true;
        }
    }

    private class AsrListenerImpl implements AsrListener {
        private static final int b = 500;
        private long c;

        public void a(float f) {
        }

        public void d() {
        }

        private AsrListenerImpl() {
        }

        public void a() {
            LogUtil.a(MiBrainActivityCardStyle.b, "ASR onReadyForSpeech");
            int unused = MiBrainActivityCardStyle.this.f10574a = 0;
        }

        public void b() {
            LogUtil.a(MiBrainActivityCardStyle.b, "ASR onBeginningOfSpeech");
            this.c = System.currentTimeMillis();
        }

        public void a(byte[] bArr) {
            if (bArr != null && bArr.length > 0) {
                long j = 0;
                for (int i = 0; i < bArr.length; i++) {
                    j += (long) (bArr[i] * bArr[i]);
                }
                double d = (double) j;
                double length = (double) bArr.length;
                Double.isNaN(d);
                Double.isNaN(length);
                double d2 = d / length;
                int log10 = (int) (Math.log10(d2 * d2) * 10.0d);
                LogUtil.a("onBufferReceived", log10 + "");
                MiBrainActivityCardStyle.this.mAudioRecordWaveView.receiveVoice((double) log10);
            }
        }

        public void c() {
            LogUtil.a(MiBrainActivityCardStyle.b, "ASR onEndOfSpeech");
            int unused = MiBrainActivityCardStyle.this.f10574a = 0;
            MiBrainActivityCardStyle.this.I.sendEmptyMessage(2);
            boolean unused2 = MiBrainActivityCardStyle.this.s = false;
        }

        public void a(SpeechError speechError) {
            String access$000 = MiBrainActivityCardStyle.b;
            LogUtil.a(access$000, "ASR onError" + speechError.toString());
            int unused = MiBrainActivityCardStyle.this.f10574a = 3;
            if (speechError.b() == 5 || speechError.b() == 8 || speechError.b() == 11) {
                boolean unused2 = MiBrainActivityCardStyle.this.s = false;
                MiBrainActivityCardStyle.this.I.sendEmptyMessageDelayed(9, 100);
            } else if (speechError.b() == -15 || speechError.b() == 13) {
                MiBrainActivityCardStyle.this.I.sendEmptyMessage(8);
            } else {
                Message obtain = Message.obtain();
                obtain.what = 10;
                obtain.obj = MiBrainActivityCardStyle.this.getString(R.string.mi_brain_not_understand);
                MiBrainActivityCardStyle.this.I.sendMessageDelayed(obtain, 100);
            }
        }

        public void a(SpeechResult speechResult) {
            MiBrainActivityCardStyle.this.I.removeMessages(11);
            String access$000 = MiBrainActivityCardStyle.b;
            LogUtil.a(access$000, "onResults**********" + speechResult.toString() + InternalFrame.ID + speechResult.f());
            String f = speechResult.f();
            Message obtain = Message.obtain();
            obtain.what = 14;
            obtain.obj = MiBrainActivityCardStyle.this.a(f);
            MiBrainActivityCardStyle.this.I.sendMessage(obtain);
        }

        public void b(SpeechResult speechResult) {
            MiBrainActivityCardStyle.this.I.removeMessages(11);
            String access$000 = MiBrainActivityCardStyle.b;
            LogUtil.a(access$000, "onPartialResults**********" + speechResult.toString() + InternalFrame.ID + speechResult.f());
            if (System.currentTimeMillis() - this.c > 500) {
                String f = speechResult.f();
                Message obtain = Message.obtain();
                obtain.what = 12;
                obtain.obj = MiBrainActivityCardStyle.this.a(f);
                MiBrainActivityCardStyle.this.I.sendMessage(obtain);
                this.c = System.currentTimeMillis();
            }
        }

        public void a(String str, String str2) {
            LogUtil.a(MiBrainActivityCardStyle.b, "ASR onFileStored");
        }
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        if (this.miBrainCloudSDKManager != null) {
            this.miBrainCloudSDKManager.c(str);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        j();
    }
}
