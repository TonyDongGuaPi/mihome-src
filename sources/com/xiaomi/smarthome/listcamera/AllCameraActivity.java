package com.xiaomi.smarthome.listcamera;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.smarthome.AppStateNotifier;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.MenuDialog;
import com.xiaomi.smarthome.library.common.dialog.MultipleChoiceDialogHelper;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.miio.TitleBarUtil;

public class AllCameraActivity extends BaseActivity {
    public static final String INTENT_KEY_DID = "intent_key_did";
    public static final String INTENT_KEY_EVENT = "intent_key_event";

    /* renamed from: a  reason: collision with root package name */
    private String f19178a;
    private String b;
    private View c;
    private View d;
    /* access modifiers changed from: private */
    public AllCameraPage e;
    /* access modifiers changed from: private */
    public Dialog f;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.client_all_container);
        getWindow().setBackgroundDrawable((Drawable) null);
        TitleBarUtil.a(TitleBarUtil.a(), findViewById(R.id.title_bar_choose_device));
        Intent intent = getIntent();
        this.f19178a = intent.getStringExtra(INTENT_KEY_DID);
        this.b = intent.getStringExtra(INTENT_KEY_EVENT);
        this.e = new AllCameraPage();
        this.e.a(true);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, this.e, (String) null);
        beginTransaction.commitAllowingStateLoss();
        this.c = findViewById(R.id.title_bar_choose_device);
        this.d = findViewById(R.id.menu_bar_choose_device_scene);
    }

    private void a() {
        CoreApi.a().a(getContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                AppStateNotifier stateNotifier = SHApplication.getStateNotifier();
                switch (stateNotifier.a()) {
                    case 2:
                        AllCameraActivity.this.a(stateNotifier);
                        return;
                    case 3:
                        AllCameraActivity.this.b();
                        return;
                    default:
                        return;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(AppStateNotifier appStateNotifier) {
        if (this.f != null && this.f.isShowing()) {
            this.f.dismiss();
        }
        this.f = new XQProgressDialog(this);
        this.f.setCancelable(false);
        ((XQProgressDialog) this.f).setMessage(getResources().getString(R.string.logining_please_wait));
        this.f.show();
        appStateNotifier.a((AppStateNotifier.LoginCallback) new AppStateNotifier.LoginCallback() {
            public void a() {
                AllCameraActivity.this.f.dismiss();
                AllCameraActivity.this.e.d();
                Toast.makeText(AllCameraActivity.this, AllCameraActivity.this.getString(R.string.login_success), 0).show();
            }

            public void b() {
                AllCameraActivity.this.f.dismiss();
                Toast.makeText(AllCameraActivity.this, AllCameraActivity.this.getString(R.string.login_fail), 0).show();
                AllCameraActivity.this.b();
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.f != null && this.f.isShowing()) {
            this.f.dismiss();
            this.f = null;
        }
        if (this.f == null) {
            this.f = SHApplication.showLoginDialog(this, true);
            this.f.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    AllCameraActivity.this.finish();
                }
            });
            this.f.setCanceledOnTouchOutside(false);
        } else if (!this.f.isShowing()) {
            this.f.show();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        a();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        this.e.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        this.e.onBackPressed();
    }

    public View getChooseDeviceTitleBar() {
        return this.c;
    }

    public View getChooseDeviceMenuBar() {
        return this.d;
    }

    public void showCameraAddOption() {
        final LayoutInflater from = LayoutInflater.from(this);
        final MenuDialog menuDialog = new MenuDialog(this);
        menuDialog.a((BaseAdapter) new BaseAdapter() {
            public int getCount() {
                return 3;
            }

            public Object getItem(int i) {
                return null;
            }

            public long getItemId(int i) {
                return 0;
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                switch (i) {
                    case 0:
                        View inflate = from.inflate(R.layout.menu_dialog_item, (ViewGroup) null);
                        ((TextView) inflate.findViewById(R.id.text1)).setText(R.string.camera_option_sort);
                        inflate.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                AllCameraActivity.this.startActivity(new Intent(AllCameraActivity.this.getContext(), CameraSortActivity.class));
                                menuDialog.dismiss();
                            }
                        });
                        return inflate;
                    case 1:
                        View inflate2 = from.inflate(R.layout.menu_dialog_item, (ViewGroup) null);
                        ((TextView) inflate2.findViewById(R.id.text1)).setText(R.string.camera_option_change_show);
                        inflate2.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                MLAlertDialog b = new MLAlertDialog.Builder(AllCameraActivity.this.getContext()).a((CharSequence[]) new String[]{AllCameraActivity.this.getString(R.string.camera_option_large_show), AllCameraActivity.this.getString(R.string.camera_option_smarll_show)}, CameraGroupManager.a().f(), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                    public void onClick(final DialogInterface dialogInterface, int i) {
                                        CameraGroupManager.a().a(i, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                                            /* renamed from: a */
                                            public void onSuccess(Void voidR) {
                                                LocalBroadcastManager.getInstance(AllCameraActivity.this.getContext()).sendBroadcast(new Intent(AllCameraPage.d));
                                                dialogInterface.dismiss();
                                            }

                                            public void onFailure(Error error) {
                                                dialogInterface.dismiss();
                                            }
                                        });
                                        dialogInterface.dismiss();
                                    }
                                }).b();
                                b.show();
                                MultipleChoiceDialogHelper.a(AllCameraActivity.this.getContext(), b);
                                menuDialog.dismiss();
                            }
                        });
                        return inflate2;
                    case 2:
                        View inflate3 = from.inflate(R.layout.menu_dialog_item, (ViewGroup) null);
                        ((TextView) inflate3.findViewById(R.id.text1)).setText(R.string.camera_option_fullscreen);
                        inflate3.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                AllCameraActivity.this.startActivity(new Intent(AllCameraActivity.this.getContext(), CameraHorizontalActivity.class));
                                menuDialog.dismiss();
                            }
                        });
                        return inflate3;
                    default:
                        return view;
                }
            }
        });
        menuDialog.setCanceledOnTouchOutside(true);
        if (CoreApi.a().q()) {
            menuDialog.show();
            StatHelper.k();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        CameraDeviceOpManager.a().h();
    }
}
