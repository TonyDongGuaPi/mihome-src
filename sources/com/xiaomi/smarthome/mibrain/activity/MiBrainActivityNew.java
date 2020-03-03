package com.xiaomi.smarthome.mibrain.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.metadata.id3.InternalFrame;
import com.tencent.smtt.sdk.QbSdk;
import com.xiaomi.ai.AsrListener;
import com.xiaomi.ai.SpeechError;
import com.xiaomi.ai.SpeechResult;
import com.xiaomi.passport.ui.settings.UserAvatarUpdateActivity;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.AndroidCommonConfigManager;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.runtime.util.TitleBarUtil;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.permission.PermissionHelper;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.mibrain.MiBrainManager;
import com.xiaomi.smarthome.mibrain.adapter.MiBrainMainRecycleAdapter;
import com.xiaomi.smarthome.mibrain.model.MiBrainConfigInfo;
import com.xiaomi.smarthome.mibrain.model.NLPResultInfo;
import com.xiaomi.smarthome.mibrain.sip.SipApi;
import com.xiaomi.smarthome.mibrain.sip.model.StartResult;
import com.xiaomi.smarthome.mibrain.viewutil.MiBrainExecutingView;
import com.xiaomi.smarthome.mibrain.viewutil.floatview.MiBrainPreferenceManager;
import com.xiaomi.smarthome.mibrain.viewutil.waveview.IOnReceiveVoiceCallBack;
import com.xiaomi.smarthome.mibrain.viewutil.waveview.MiBrainAudioRecordView;
import com.xiaomi.smarthome.mibrain.viewutil.waveview.MiBrainRecycleViewLayout;
import com.xiaomi.smarthome.mibrain.viewutil.waveview.MiBrainWaveView;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.voice.VoiceManager;
import com.xiaomi.smarthome.voice.VoiceSettingActivity;
import com.xiaomi.voiceassistant.mijava.AiServiceError;
import com.xiaomi.voiceassistant.mijava.MiBrainCloudSDKManager;
import com.xiaomi.voiceassistant.mijava.NLPResultCallBack;
import com.xiaomi.voiceassistant.mijava.NLProcessor;
import com.yanzhenjie.permission.Action;
import java.util.List;

public class MiBrainActivityNew extends BaseActivity {
    public static final int DEFAULT = 0;
    public static final int ERROR = 3;
    public static final int LISTENING = 1;
    public static final int WORKING = 2;
    /* access modifiers changed from: private */
    public static final String b = "MiBrainActivityNew";
    private static final int e = 100;
    private static final int l = 0;
    private static final int m = 1;
    private static final int n = 2;
    private static final int o = 3;
    private static final int p = 4;
    private static final int q = 5;
    private static final int r = 6;
    private static final int s = 7;
    private static final int t = 8;
    private static final int u = 9;
    private static final int v = 10;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public int f10590a = 0;
    /* access modifiers changed from: private */
    public StartResult c;
    /* access modifiers changed from: private */
    public NLPResultInfo d;
    /* access modifiers changed from: private */
    public List<MiBrainConfigInfo> f;
    private boolean g = false;
    /* access modifiers changed from: private */
    public boolean h = false;
    GenericDraweeHierarchy hierarchy;
    private AudioManager i;
    private AudioManager.OnAudioFocusChangeListener j;
    private View.OnClickListener k = new View.OnClickListener() {
        public void onClick(View view) {
            StatHelper.aq();
            if (MiBrainActivityNew.this.c == null) {
                ToastUtil.a((int) R.string.mi_brain_session_err);
            } else if (MiBrainActivityNew.this.miBrainCloudSDKManager != null) {
                String access$400 = MiBrainActivityNew.b;
                Miio.b(access$400, "mAudioRecordSogou.getStatus()----&&&&" + MiBrainActivityNew.this.f10590a);
                switch (MiBrainActivityNew.this.f10590a) {
                    case 0:
                        MiBrainActivityNew.this.h();
                        return;
                    case 1:
                        MiBrainActivityNew.this.i();
                        return;
                    case 2:
                        MiBrainActivityNew.this.i();
                        return;
                    case 3:
                        MiBrainActivityNew.this.h();
                        return;
                    default:
                        return;
                }
            }
        }
    };
    MiBrainMainRecycleAdapter mAdapter;
    AsrListenerImpl mAsrListenerImpl = new AsrListenerImpl();
    @BindView(2131430850)
    TextView mBrainContentResultDownTV;
    @BindView(2131430848)
    ImageView mBrainContentResultIV;
    @BindView(2131430852)
    RelativeLayout mBrainContentResultRl;
    @BindView(2131430851)
    TextView mBrainContentResultUpTV;
    @BindView(2131432314)
    TextView mBrainSettingWhiteTV;
    @BindView(2131430847)
    ImageView mCloseIV;
    @BindView(2131430853)
    TextView mErrorDescTV;
    @BindView(2131430860)
    ImageView mHelpIV;
    @BindView(2131430862)
    RelativeLayout mHelpLL;
    @BindView(2131430861)
    ListView mHelpListView;
    LayoutInflater mInflater;
    @BindView(2131431021)
    ImageView mMoreIV;
    NLPResultCallBack mNLPResultCallBack = new NLPResultCallBack() {
        public void a(AiServiceError aiServiceError) {
            String access$400 = MiBrainActivityNew.b;
            Miio.b(access$400, "       isListening:" + MiBrainActivityNew.this.h + "mAudioRecordSogou.getStatus()NLP onError " + aiServiceError.toString() + "       isListening:" + MiBrainActivityNew.this.h);
            if (MiBrainActivityNew.this.mHelpLL.getVisibility() != 0 && !MiBrainActivityNew.this.h) {
                Message obtain = Message.obtain();
                obtain.what = 10;
                if (aiServiceError.e == AiServiceError.d) {
                    obtain.obj = aiServiceError.g;
                } else {
                    obtain.obj = MiBrainManager.a().b(aiServiceError.f);
                }
                obtain.arg1 = aiServiceError.f;
                MiBrainActivityNew.this.w.sendMessageDelayed(obtain, 100);
            }
        }

        public void a(String str) {
            String access$400 = MiBrainActivityNew.b;
            Miio.b(access$400, "       isListening:" + MiBrainActivityNew.this.h + "mStatus" + MiBrainActivityNew.this.f10590a + "NLP success " + str);
            if (MiBrainActivityNew.this.mHelpLL.getVisibility() != 0 && !MiBrainActivityNew.this.h) {
                NLPResultInfo unused = MiBrainActivityNew.this.d = NLPResultInfo.a(str);
                if (MiBrainActivityNew.this.d != null) {
                    if (MiBrainActivityNew.this.mTipsLL.getVisibility() == 0) {
                        MiBrainActivityNew.this.mTipsLL.setVisibility(8);
                    }
                    if (!TextUtils.isEmpty(MiBrainActivityNew.this.d.g) && MiBrainActivityNew.this.d.g.equalsIgnoreCase(UserAvatarUpdateActivity.CAMERA)) {
                        Message obtain = Message.obtain();
                        obtain.what = 10;
                        obtain.obj = MiBrainActivityNew.this.getResources().getString(R.string.mi_brain_not_support);
                        MiBrainActivityNew.this.w.sendMessageDelayed(obtain, 100);
                    } else if (MiBrainActivityNew.this.d.f == 0) {
                        Message obtain2 = Message.obtain();
                        obtain2.obj = TextUtils.isEmpty(MiBrainActivityNew.this.d.c) ? MiBrainActivityNew.this.getResources().getString(R.string.mi_brain_iot_success) : MiBrainActivityNew.this.d.c;
                        obtain2.what = MiBrainActivityNew.this.d.f10654a == 200 ? 6 : 7;
                        MiBrainActivityNew.this.w.sendMessage(obtain2);
                    } else if (MiBrainActivityNew.this.d.f == 1) {
                        MiBrainActivityNew.this.m();
                        MiBrainActivityNew.this.b(MiBrainActivityNew.this.d.c);
                        MiBrainActivityNew.this.mBrainContentResultUpTV.setText("");
                        if (MiBrainActivityNew.this.mTipsLL.getVisibility() == 0) {
                            MiBrainActivityNew.this.mTipsLL.setVisibility(8);
                        }
                        if (MiBrainActivityNew.this.d.e == null || MiBrainActivityNew.this.d.e.size() <= 0 || TextUtils.isEmpty(MiBrainActivityNew.this.d.e.get(0).d)) {
                            MiBrainActivityNew.this.miBrainRecycleViewLayout.show(MiBrainActivityNew.this.d.e, MiBrainActivityNew.this.d.c, "");
                        } else {
                            MiBrainActivityNew.this.miBrainRecycleViewLayout.show(MiBrainActivityNew.this.d.e, MiBrainActivityNew.this.d.c, MiBrainActivityNew.this.d.e.get(0).d);
                        }
                    } else {
                        MiBrainActivityNew.this.b(MiBrainActivityNew.this.d.c);
                        Message obtain3 = Message.obtain();
                        obtain3.what = 10;
                        obtain3.obj = MiBrainActivityNew.this.d.c;
                        obtain3.arg1 = QbSdk.EXTENSION_INIT_FAILURE;
                        MiBrainActivityNew.this.w.sendMessageDelayed(obtain3, 100);
                    }
                }
            }
        }
    };
    @BindView(2131430872)
    LinearLayout mTipsLL;
    @BindView(2131430868)
    MiBrainAudioRecordView miBrainAudioRecordView;
    MiBrainCloudSDKManager miBrainCloudSDKManager;
    @BindView(2131430874)
    View miBrainControlView;
    @BindView(2131430857)
    MiBrainExecutingView miBrainExecutingView;
    @BindView(2131430876)
    RelativeLayout miBrainListeningRl;
    @BindView(2131430877)
    ImageView miBrainNoListeningIV;
    MiBrainPreferenceManager miBrainPreferenceManager;
    @BindView(2131430865)
    MiBrainRecycleViewLayout miBrainRecycleViewLayout;
    @BindView(2131430875)
    MiBrainWaveView miBrainWaveView;
    /* access modifiers changed from: private */
    public Handler w = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            String access$400 = MiBrainActivityNew.b;
            Miio.b(access$400, "msg.what" + message.what);
            switch (message.what) {
                case 0:
                    MiBrainActivityNew.this.p();
                    return;
                case 1:
                    MiBrainActivityNew.this.l();
                    return;
                case 2:
                    MiBrainActivityNew.this.o();
                    return;
                case 5:
                    MiBrainActivityNew.this.n();
                    return;
                case 6:
                    MiBrainActivityNew.this.a(true, message.obj);
                    return;
                case 7:
                    MiBrainActivityNew.this.a(false, message.obj);
                    return;
                case 8:
                    MiBrainActivityNew.this.a((Object) MiBrainActivityNew.this.getResources().getString(R.string.mi_brain_can_not_use_mi_brain), 8);
                    return;
                case 9:
                    MiBrainActivityNew.this.a((Object) MiBrainActivityNew.this.getResources().getString(R.string.mi_brain_can_not_use_mi_brain), 9);
                    return;
                case 10:
                    MiBrainActivityNew.this.a(message.obj, 10);
                    return;
                default:
                    return;
            }
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TitleBarUtil.enableWhiteTranslucentStatus((Activity) this);
        if (getIntent() != null && getIntent().hasExtra("from")) {
            StatHelper.l(getIntent().getStringExtra("from"));
        }
        setContentView(R.layout.activity_mi_brain_main);
        ButterKnife.bind((Activity) this);
        this.miBrainPreferenceManager = new MiBrainPreferenceManager(this);
        c();
        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
            public void run() {
                VoiceManager.a((BaseActivity) MiBrainActivityNew.this);
            }
        }, 1000);
        a();
        m();
    }

    private void a() {
        try {
            this.i = (AudioManager) getSystemService("audio");
            this.j = new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int i) {
                }
            };
            this.i.requestAudioFocus(this.j, 3, 2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void b() {
        try {
            this.i.abandonAudioFocus(this.j);
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
        b();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        String str = b;
        Miio.b(str, b + "onResume" + this.g);
        super.onResume();
        if (!VoiceManager.a().a(SHApplication.getAppContext())) {
            this.w.sendEmptyMessageDelayed(9, 100);
            return;
        }
        PermissionHelper.f(this, true, new Action() {
            public void onAction(List<String> list) {
                MiBrainActivityNew.this.f();
            }
        });
        if (((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo() == null) {
            this.w.sendEmptyMessage(8);
        } else if (this.g) {
            this.w.sendEmptyMessage(0);
            this.g = false;
        }
    }

    private void c() {
        this.mInflater = LayoutInflater.from(this);
        g();
        d();
        e();
        this.w.sendEmptyMessage(0);
        this.mTipsLL.setVisibility(0);
    }

    private void d() {
        this.mMoreIV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                VoiceManager.a((Activity) MiBrainActivityNew.this, (View.OnClickListener) new View.OnClickListener() {
                    public void onClick(View view) {
                        MiBrainActivityNew.this.startActivity(new Intent(MiBrainActivityNew.this, VoiceSettingActivity.class));
                        NLProcessor.b = "";
                        MiBrainActivityNew.this.j();
                        MiBrainActivityNew.this.finish();
                    }
                });
            }
        });
    }

    private void e() {
        this.miBrainWaveView.setClickListener(this.k);
        this.miBrainNoListeningIV.setOnClickListener(this.k);
        this.miBrainNoListeningIV.setContentDescription(getString(R.string.mi_brain_record_content_des));
        this.miBrainWaveView.setContentDescription(getString(R.string.mi_brain_record_content_des));
        this.mHelpIV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MiBrainActivityNew.this.mHelpLL.getVisibility() == 8) {
                    if (MiBrainActivityNew.this.mTipsLL.getVisibility() == 0) {
                        MiBrainActivityNew.this.mTipsLL.setVisibility(8);
                    }
                    MiBrainActivityNew.this.mHelpIV.setSelected(true);
                    MiBrainActivityNew.this.w.sendEmptyMessage(5);
                } else {
                    MiBrainActivityNew.this.mHelpListView.smoothScrollToPosition(0);
                }
                StatHelper.ao();
            }
        });
        this.mCloseIV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                StatHelper.ap();
                NLProcessor.b = "";
                MiBrainActivityNew.this.j();
                MiBrainActivityNew.this.finish();
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        j();
    }

    /* access modifiers changed from: private */
    public void f() {
        SipApi.a().a(this, new AsyncCallback<StartResult, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(StartResult startResult) {
                if (MiBrainActivityNew.this.c == null) {
                    StartResult unused = MiBrainActivityNew.this.c = startResult;
                    if (MiBrainActivityNew.this.c == null) {
                        ToastUtil.a((CharSequence) "session is null");
                        return;
                    }
                    String access$400 = MiBrainActivityNew.b;
                    Miio.b(access$400, "session id=" + MiBrainActivityNew.this.c.f10711a);
                    String access$4002 = MiBrainActivityNew.b;
                    Miio.b(access$4002, "token=" + MiBrainActivityNew.this.c.b);
                    String access$4003 = MiBrainActivityNew.b;
                    Miio.b(access$4003, "expire=" + MiBrainActivityNew.this.c.c);
                    MiBrainActivityNew.this.miBrainCloudSDKManager = MiBrainCloudSDKManager.a(MiBrainActivityNew.this, CoreApi.a().s(), MiBrainActivityNew.this.c.b, MiBrainActivityNew.this.c.f10711a, true);
                    MiBrainActivityNew.this.miBrainCloudSDKManager.a(MiBrainActivityNew.this.mNLPResultCallBack);
                    MiBrainActivityNew.this.miBrainCloudSDKManager.a((AsrListener) MiBrainActivityNew.this.mAsrListenerImpl);
                    if (VoiceManager.a().a(SHApplication.getAppContext())) {
                        MiBrainActivityNew.this.h();
                    }
                }
            }
        });
    }

    private void g() {
        this.f = AndroidCommonConfigManager.a().b();
        this.mHelpListView.setAdapter(new BaseAdapter() {
            public long getItemId(int i) {
                return (long) i;
            }

            public int getCount() {
                return MiBrainActivityNew.this.f.size();
            }

            public Object getItem(int i) {
                return MiBrainActivityNew.this.f.get(i);
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                HelpViewHolder helpViewHolder;
                MiBrainConfigInfo miBrainConfigInfo = (MiBrainConfigInfo) MiBrainActivityNew.this.f.get(i);
                if (view == null) {
                    view = MiBrainActivityNew.this.mInflater.inflate(R.layout.mi_brain_help_listview_item, (ViewGroup) null);
                    helpViewHolder = new HelpViewHolder(view);
                    view.setTag(helpViewHolder);
                } else {
                    helpViewHolder = (HelpViewHolder) view.getTag();
                }
                if (miBrainConfigInfo == null) {
                    return view;
                }
                if (!TextUtils.isEmpty(miBrainConfigInfo.f10653a)) {
                    helpViewHolder.f10613a.setText(miBrainConfigInfo.f10653a);
                } else {
                    helpViewHolder.f10613a.setText("");
                }
                helpViewHolder.c.setText(MiBrainActivityNew.this.a(miBrainConfigInfo.c));
                MiBrainActivityNew.this.hierarchy = new GenericDraweeHierarchyBuilder(MiBrainActivityNew.this.getResources()).setFadeDuration(200).setPlaceholderImage(MiBrainActivityNew.this.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build();
                helpViewHolder.b.setHierarchy(MiBrainActivityNew.this.hierarchy);
                if (!TextUtils.isEmpty(miBrainConfigInfo.b)) {
                    DeviceFactory.b(miBrainConfigInfo.b, helpViewHolder.b);
                }
                return view;
            }
        });
    }

    /* access modifiers changed from: private */
    public String a(List<String> list) {
        if (list == null || list.size() <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (i2 == 0) {
                sb.append("“");
                sb.append(list.get(i2));
                sb.append("”");
            } else {
                sb.append("\n");
                sb.append("“");
                sb.append(list.get(i2));
                sb.append("”");
            }
        }
        return sb.toString();
    }

    private static class HelpViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f10613a;
        SimpleDraweeView b;
        TextView c;

        HelpViewHolder(View view) {
            this.f10613a = (TextView) view.findViewById(R.id.name_tv);
            this.b = (SimpleDraweeView) view.findViewById(R.id.mi_brain_help_item_simple_iv);
            this.c = (TextView) view.findViewById(R.id.mi_brain_help_dec_tv);
        }
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
        if (this.miBrainCloudSDKManager != null && !this.h) {
            this.h = true;
            MiBrainManager.a().i();
            this.w.sendEmptyMessage(1);
            this.w.postDelayed(new Runnable() {
                public void run() {
                    MiBrainCloudSDKManager miBrainCloudSDKManager = MiBrainActivityNew.this.miBrainCloudSDKManager;
                    if (miBrainCloudSDKManager != null) {
                        int unused = MiBrainActivityNew.this.f10590a = 1;
                        miBrainCloudSDKManager.i();
                    }
                }
            }, 200);
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.miBrainCloudSDKManager != null) {
            if (this.f10590a == 1 || this.f10590a == 2) {
                this.miBrainCloudSDKManager.k();
                this.f10590a = 0;
            }
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        if (this.miBrainCloudSDKManager != null) {
            if (this.f10590a == 1 || this.f10590a == 2) {
                this.miBrainCloudSDKManager.k();
                this.f10590a = 0;
            }
            if (this.miBrainCloudSDKManager != null) {
                this.miBrainCloudSDKManager.j();
            }
        }
    }

    private void k() {
        this.miBrainRecycleViewLayout.hide();
        this.mHelpLL.setVisibility(8);
        this.miBrainNoListeningIV.setVisibility(8);
        this.miBrainExecutingView.setVisibility(8);
        if (((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo() == null) {
            this.mErrorDescTV.setVisibility(0);
            this.mErrorDescTV.setText(R.string.network_disable);
            this.mErrorDescTV.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MiBrainActivityNew.this.startActivity(new Intent("android.settings.SETTINGS"));
                }
            });
            return;
        }
        this.mErrorDescTV.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void l() {
        this.mBrainContentResultRl.setVisibility(0);
        this.mBrainContentResultIV.setVisibility(8);
        if (this.mBrainSettingWhiteTV.getVisibility() == 0) {
            this.mBrainSettingWhiteTV.setVisibility(8);
        }
        m();
        this.miBrainRecycleViewLayout.hide();
        this.miBrainExecutingView.setVisibility(8);
        this.miBrainListeningRl.setVisibility(0);
        this.miBrainNoListeningIV.setVisibility(8);
        if (this.mBrainContentResultUpTV.getVisibility() == 8) {
            this.mBrainContentResultUpTV.setVisibility(0);
        }
        this.mBrainContentResultUpTV.setText("");
        this.mBrainContentResultDownTV.setText("");
    }

    /* access modifiers changed from: private */
    public void m() {
        if (this.mHelpLL.getVisibility() == 0) {
            this.mHelpLL.setVisibility(8);
            this.mHelpIV.setSelected(false);
        }
    }

    /* access modifiers changed from: private */
    public void n() {
        i();
        if (this.mBrainSettingWhiteTV.getVisibility() == 0) {
            this.mBrainSettingWhiteTV.setVisibility(8);
        }
        this.mBrainContentResultRl.setVisibility(8);
        this.mHelpLL.setVisibility(0);
        this.miBrainRecycleViewLayout.hide();
        this.miBrainExecutingView.setVisibility(8);
        this.mBrainContentResultUpTV.setText("");
        this.mBrainContentResultDownTV.setText("");
    }

    /* access modifiers changed from: private */
    public void o() {
        this.miBrainNoListeningIV.setVisibility(0);
        this.miBrainListeningRl.setVisibility(8);
        if (this.mBrainSettingWhiteTV.getVisibility() == 0) {
            this.mBrainSettingWhiteTV.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void p() {
        this.mBrainContentResultRl.setVisibility(8);
        this.mBrainContentResultIV.setVisibility(8);
        if (this.mBrainSettingWhiteTV.getVisibility() == 0) {
            this.mBrainSettingWhiteTV.setVisibility(8);
        }
        m();
        this.miBrainRecycleViewLayout.hide();
        this.miBrainExecutingView.setVisibility(8);
        this.miBrainListeningRl.setVisibility(8);
        this.miBrainNoListeningIV.setVisibility(0);
        this.mBrainContentResultUpTV.setText("");
        this.mBrainContentResultDownTV.setText("");
    }

    /* access modifiers changed from: private */
    public void a(boolean z, Object obj) {
        String str = b;
        Miio.b(str, "enterIOTResultUI   isListening:" + this.h);
        if (!this.h) {
            this.mBrainContentResultRl.setVisibility(0);
            if (this.mBrainSettingWhiteTV.getVisibility() == 0) {
                this.mBrainSettingWhiteTV.setVisibility(8);
            }
            m();
            this.miBrainRecycleViewLayout.hide();
            this.miBrainExecutingView.stop();
            this.miBrainExecutingView.setVisibility(8);
            this.miBrainListeningRl.setVisibility(8);
            this.miBrainNoListeningIV.setVisibility(0);
            if (this.mBrainContentResultUpTV.getVisibility() == 8) {
                this.mBrainContentResultUpTV.setVisibility(0);
            }
            if (obj != null) {
                this.mBrainContentResultUpTV.setText(obj.toString());
                b(obj.toString());
            } else {
                this.mBrainContentResultUpTV.setText("");
            }
            this.mBrainContentResultDownTV.setText("");
            this.mBrainContentResultIV.setVisibility(0);
            if (z) {
                this.mBrainContentResultIV.setImageResource(R.drawable.mi_brain_success_icon);
            } else {
                this.mBrainContentResultIV.setImageResource(R.drawable.mi_brain_fail_icon);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(Object obj, int i2) {
        String str = b;
        Miio.b(str, "enterErrorUI isListening" + this.h);
        if (this.mTipsLL.getVisibility() == 0) {
            this.mTipsLL.setVisibility(8);
        }
        this.mBrainContentResultRl.setVisibility(0);
        m();
        this.miBrainRecycleViewLayout.hide();
        this.miBrainExecutingView.setVisibility(8);
        this.miBrainListeningRl.setVisibility(8);
        this.miBrainNoListeningIV.setVisibility(0);
        if (obj != null) {
            this.mBrainContentResultUpTV.setText(obj.toString());
        } else {
            this.mBrainContentResultUpTV.setText("");
        }
        this.mBrainContentResultDownTV.setText("");
        if (i2 == 8) {
            this.mBrainContentResultIV.setVisibility(0);
            this.g = true;
            this.mBrainContentResultIV.setImageResource(R.drawable.mi_brain_no_net_icon);
            this.mBrainSettingWhiteTV.setVisibility(0);
            this.mBrainSettingWhiteTV.setText(StringUtil.a((Context) this, (int) R.string.mi_brain_go_setting, (int) R.string.mi_brain_no_connect_net_module, (ClickableSpan) new ClickableSpan() {
                public void updateDrawState(TextPaint textPaint) {
                    textPaint.setColor(MiBrainActivityNew.this.getResources().getColor(R.color.class_text_17));
                    textPaint.setUnderlineText(false);
                }

                public void onClick(View view) {
                    MiBrainActivityNew.this.startActivity(new Intent("android.settings.SETTINGS"));
                }
            }));
            this.mBrainSettingWhiteTV.setMovementMethod(LinkMovementMethod.getInstance());
            this.mBrainSettingWhiteTV.setHighlightColor(0);
        } else if (i2 == 9) {
            this.mBrainContentResultIV.setVisibility(0);
            this.g = true;
            this.mBrainContentResultIV.setImageResource(R.drawable.mi_brain_go_setting_icon);
            this.mBrainSettingWhiteTV.setVisibility(0);
            this.mBrainSettingWhiteTV.setText(StringUtil.a((Context) this, (int) R.string.mi_brain_go_setting, (int) R.string.no_record_permission_module, (ClickableSpan) new ClickableSpan() {
                public void updateDrawState(TextPaint textPaint) {
                    textPaint.setColor(MiBrainActivityNew.this.getResources().getColor(R.color.class_text_17));
                    textPaint.setUnderlineText(false);
                }

                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", MiBrainActivityNew.this.getPackageName(), (String) null));
                    MiBrainActivityNew.this.startActivity(intent);
                }
            }));
            this.mBrainSettingWhiteTV.setMovementMethod(LinkMovementMethod.getInstance());
            this.mBrainSettingWhiteTV.setHighlightColor(0);
        } else if (i2 == 10) {
            this.mBrainContentResultIV.setVisibility(8);
        }
    }

    private class AsrListenerImpl implements AsrListener {
        private static final int b = 500;
        /* access modifiers changed from: private */
        public long c;

        public void a(float f) {
        }

        public void d() {
        }

        private AsrListenerImpl() {
        }

        public void a() {
            LogUtil.a(MiBrainActivityNew.b, "ASR onReadyForSpeech");
            int unused = MiBrainActivityNew.this.f10590a = 0;
        }

        public void b() {
            LogUtil.a(MiBrainActivityNew.b, "ASR onBeginningOfSpeech");
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
                MiBrainActivityNew.this.miBrainWaveView.receiveVoice((double) log10, new IOnReceiveVoiceCallBack() {
                    public void a(float f) {
                        MiBrainActivityNew.this.miBrainWaveView.postDelayed(new Runnable() {
                            public void run() {
                                MiBrainActivityNew.this.miBrainAudioRecordView.receivedAudio((((int) MiBrainActivityNew.this.miBrainWaveView.getRadiusEnd()) / 2) + MiBrainActivityNew.this.miBrainWaveView.dip2px(5.0f));
                            }
                        }, 50);
                    }
                });
            }
        }

        public void c() {
            LogUtil.a(MiBrainActivityNew.b, "ASR onEndOfSpeech");
            int unused = MiBrainActivityNew.this.f10590a = 0;
            MiBrainActivityNew.this.w.sendEmptyMessage(2);
            boolean unused2 = MiBrainActivityNew.this.h = false;
        }

        public void a(SpeechError speechError) {
            String access$400 = MiBrainActivityNew.b;
            LogUtil.a(access$400, "ASR onError" + speechError.toString());
            int unused = MiBrainActivityNew.this.f10590a = 3;
            MiBrainActivityNew.this.runOnUiThread(new Runnable() {
                public void run() {
                    if (MiBrainActivityNew.this.mTipsLL.getVisibility() == 0) {
                        MiBrainActivityNew.this.mTipsLL.setVisibility(8);
                    }
                }
            });
            if (MiBrainActivityNew.this.mHelpLL.getVisibility() != 0) {
                if (speechError.b() == 5 || speechError.b() == 8 || speechError.b() == 11) {
                    boolean unused2 = MiBrainActivityNew.this.h = false;
                    MiBrainActivityNew.this.w.sendEmptyMessageDelayed(9, 100);
                } else if (speechError.b() == -15 || speechError.b() == 13) {
                    MiBrainActivityNew.this.w.sendEmptyMessage(8);
                } else {
                    Message obtain = Message.obtain();
                    obtain.what = 10;
                    obtain.obj = MiBrainActivityNew.this.getString(R.string.mi_brain_not_understand);
                    MiBrainActivityNew.this.w.sendMessageDelayed(obtain, 100);
                }
            }
        }

        public void a(final SpeechResult speechResult) {
            String access$400 = MiBrainActivityNew.b;
            LogUtil.a(access$400, "onResults**********" + speechResult.toString() + InternalFrame.ID + speechResult.f());
            MiBrainActivityNew.this.runOnUiThread(new Runnable() {
                public void run() {
                    if (MiBrainActivityNew.this.mTipsLL.getVisibility() == 0) {
                        MiBrainActivityNew.this.mTipsLL.setVisibility(8);
                    }
                    MiBrainActivityNew.this.mBrainContentResultUpTV.setText(MiBrainActivityNew.this.a(speechResult.f()));
                }
            });
        }

        public void b(final SpeechResult speechResult) {
            String access$400 = MiBrainActivityNew.b;
            LogUtil.a(access$400, "onPartialResults**********" + speechResult.toString() + InternalFrame.ID + speechResult.f());
            MiBrainActivityNew.this.runOnUiThread(new Runnable() {
                public void run() {
                    if (System.currentTimeMillis() - AsrListenerImpl.this.c > 500) {
                        String f = speechResult.f();
                        if (!TextUtils.isEmpty(f) && MiBrainActivityNew.this.mTipsLL.getVisibility() == 0) {
                            MiBrainActivityNew.this.mTipsLL.setVisibility(8);
                        }
                        MiBrainActivityNew.this.mBrainContentResultUpTV.setText(MiBrainActivityNew.this.a(f));
                        long unused = AsrListenerImpl.this.c = System.currentTimeMillis();
                    }
                }
            });
        }

        public void a(String str, String str2) {
            LogUtil.a(MiBrainActivityNew.b, "ASR onFileStored");
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
