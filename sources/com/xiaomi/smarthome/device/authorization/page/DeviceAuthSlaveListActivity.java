package com.xiaomi.smarthome.device.authorization.page;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.HomeKeyManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.auth.AuthCode;
import com.xiaomi.smarthome.auth.AuthInfo;
import com.xiaomi.smarthome.auth.AuthManager;
import com.xiaomi.smarthome.authlib.IAuthCallBack;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.Miio;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONObject;

public class DeviceAuthSlaveListActivity extends BaseActivity {
    public static final String INTENT_KEY_DID = "device_id";
    public static final String INTENT_KEY_SHOW_BOTTOM_BAR = "bottom_bar";
    public static final int MESSAGE_UPLOAD_DEVICE_AUTH = 1;
    public static final int MESSAGE_UPLOAD_SCENE_AUTH = 2;

    /* renamed from: a  reason: collision with root package name */
    private static final String f15044a = "DeviceAuthSlaveListActivity";
    private DeviceAuthFragment b;
    private SceneAuthFragment c;
    @BindView(2131428503)
    LinearLayout commonWhiteEmptyView;
    private IAuthCallBack d = AuthManager.h().e();
    private String[] e;
    private int f = -1;
    /* access modifiers changed from: private */
    public XQProgressDialog g;
    private boolean h = false;
    boolean isDeviceAuthChanged = false;
    boolean isDeviceDataReady = false;
    boolean isSceneAuthChanged = false;
    boolean isSceneDataReady = false;
    String mDid;
    public FragmentManager mFragmentManager;
    public Handler mHandler = new ActivityHandler();
    @BindView(2131427768)
    TextView mThirdOkButton;
    @BindView(2131430975)
    TextView mTitleTextView;
    @BindView(2131430969)
    ImageView moduleA4ReturnBtn;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(getIntent());
        setContentView(R.layout.activity_voicectrl);
        ButterKnife.bind((Activity) this);
        b();
        c();
        if (this.f != -1) {
            HomeKeyManager.a().a((BaseActivity) this);
        }
    }

    public void onBackPressed() {
        if (this.mFragmentManager.findFragmentByTag(this.e[1]) instanceof SceneAuthFragment) {
            a(this.isSceneAuthChanged);
            if (this.h) {
                this.mThirdOkButton.setVisibility(0);
                return;
            }
            return;
        }
        b(this.isDeviceAuthChanged);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages((Object) null);
    }

    public void showProgressDialog(String str) {
        this.g = new XQProgressDialog(this);
        this.g.setCancelable(true);
        this.g.setMessage(str);
        this.g.show();
        this.g.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
    }

    private boolean a() {
        return this.f == 2 || this.f == 6;
    }

    private void b() {
        AuthInfo a2;
        if (a() && (a2 = AuthManager.h().a()) != null) {
            this.mThirdOkButton.setText(getString(R.string.action_back) + a2.a());
        }
        this.moduleA4ReturnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceAuthSlaveListActivity.this.onBackPressed();
            }
        });
        ((TextView) this.commonWhiteEmptyView.findViewById(R.id.common_white_empty_text)).setText(R.string.no_data_tips);
    }

    public void goSceneAuthFragment() {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_left_out, R.anim.fragment_left_in, R.anim.fragment_right_out).replace(R.id.fragment_container, this.c, this.e[1]).addToBackStack(this.e[1]).commit();
        if (this.h) {
            this.mThirdOkButton.setVisibility(8);
        }
    }

    private void c() {
        this.mFragmentManager = getSupportFragmentManager();
        this.e = new String[]{getString(R.string.auth_contrl_device_title), getString(R.string.auth_contrl_scene_title)};
        this.b = new DeviceAuthFragment();
        this.c = new SceneAuthFragment();
        this.mFragmentManager.beginTransaction().add(R.id.fragment_container, this.b, this.e[0]).commit();
        this.mThirdOkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceAuthSlaveListActivity.this.onBackPressed();
            }
        });
        if (this.h) {
            this.mThirdOkButton.setVisibility(0);
        } else {
            this.mThirdOkButton.setVisibility(8);
        }
    }

    private void a(boolean z) {
        if (z) {
            this.mHandler.removeMessages(2);
            e();
        }
        this.mFragmentManager.popBackStack();
    }

    private void b(boolean z) {
        if (z) {
            d();
        }
        f();
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.b != null && this.b.mRecyclerView != null) {
            if (TextUtils.equals(this.b.j, DeviceAuthFragment.f15018a)) {
                DeviceApi.getInstance().updateDeviceAuthData(this, this.mDid, this.b.h, this.b.f, (AsyncCallback<JSONObject, Error>) null);
            } else if (TextUtils.equals(this.b.j, DeviceAuthFragment.b)) {
                DeviceApi.getInstance().updateHomeRoomAuthData(this, this.mDid, this.b.g, (AsyncCallback<JSONObject, Error>) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.c != null && this.c.c != null) {
            ArrayList arrayList = new ArrayList();
            for (Map.Entry next : this.c.c.entrySet()) {
                if (!((Boolean) next.getValue()).booleanValue()) {
                    arrayList.add(next.getKey());
                }
            }
            DeviceApi.getInstance().updateSceneAuthData(this, this.mDid, arrayList, (AsyncCallback<JSONObject, Error>) null);
        }
    }

    private void f() {
        if ((this.f == 2 || this.f == 6) && this.d != null) {
            try {
                this.d.onSuccess(100, AuthCode.a(100));
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        } else {
            setResult(-1, new Intent());
        }
        finish();
    }

    private void a(Intent intent) {
        if (intent == null) {
            Miio.b("AuthManager", "DeviceAuthSlaveListActivity intent == null");
            finish();
            return;
        }
        this.mDid = intent.getStringExtra("device_id");
        if (TextUtils.isEmpty(this.mDid)) {
            Miio.b("AuthManager", "DeviceAuthSlaveListActivity TextUtils.isEmpty(mDid)");
            finish();
        } else if (SmartHomeDeviceManager.a().b(this.mDid) == null) {
            Miio.b("AuthManager", "DeviceAuthSlaveListActivity cannot find device");
            ToastUtil.a((int) R.string.auth_no_match);
            finish();
        } else {
            this.f = AuthManager.h().g();
            this.h = intent.getBooleanExtra(INTENT_KEY_SHOW_BOTTOM_BAR, false);
        }
    }

    public void setDeviceAuthChanged(boolean z) {
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, 2000);
        this.isDeviceAuthChanged = z;
    }

    public void setSceneAuthChanged(boolean z) {
        this.mHandler.removeMessages(2);
        this.mHandler.sendEmptyMessageDelayed(2, 2000);
        this.isSceneAuthChanged = z;
    }

    public void setDeviceDataReady(boolean z, boolean z2) {
        this.isDeviceDataReady = z;
        a(!this.isDeviceDataReady, z2);
    }

    public void setSceneDataReady(boolean z, boolean z2) {
        this.isSceneDataReady = z;
        a(!this.isSceneDataReady, z2);
    }

    private void a(boolean z, boolean z2) {
        if (isValid() && !z) {
            if (this.g != null) {
                getWindow().getDecorView().postDelayed(new Runnable() {
                    public void run() {
                        if (DeviceAuthSlaveListActivity.this.g != null) {
                            DeviceAuthSlaveListActivity.this.g.dismiss();
                        }
                    }
                }, 500);
            }
            if (!z2) {
                ToastUtil.a((int) R.string.home_set_failed);
            }
        }
    }

    private static class ActivityHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<DeviceAuthSlaveListActivity> f15049a;

        private ActivityHandler(DeviceAuthSlaveListActivity deviceAuthSlaveListActivity) {
            this.f15049a = new WeakReference<>(deviceAuthSlaveListActivity);
        }

        public void handleMessage(Message message) {
            DeviceAuthSlaveListActivity deviceAuthSlaveListActivity;
            if (this.f15049a != null && (deviceAuthSlaveListActivity = (DeviceAuthSlaveListActivity) this.f15049a.get()) != null) {
                switch (message.what) {
                    case 1:
                        deviceAuthSlaveListActivity.d();
                        return;
                    case 2:
                        deviceAuthSlaveListActivity.e();
                        return;
                    default:
                        return;
                }
            }
        }
    }
}
