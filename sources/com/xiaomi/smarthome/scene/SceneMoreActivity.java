package com.xiaomi.smarthome.scene;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.MiStatType;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.MultipleChoiceDialogHelper;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.lite.scene.LiteSceneManager;
import com.xiaomi.smarthome.newui.NameEditDialogHelper;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.actiivity.SmarthomeCreateAutoSceneActivity;
import org.json.JSONObject;

public class SceneMoreActivity extends BaseActivity {
    public static final String TAG_SCENE_DELETED = "scene_deleted";

    /* renamed from: a  reason: collision with root package name */
    private static final String f21312a = "SceneMoreActivity";
    /* access modifiers changed from: private */
    public SceneApi.SmartHomeScene b;
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public String d;
    @BindView(2131430971)
    ImageView ivTitleMore;
    @BindView(2131430969)
    ImageView ivTitleReturn;
    @BindView(2131430975)
    TextView tvTitle;
    @BindView(2131433815)
    View viewAddToHome;
    @BindView(2131433816)
    View viewAddToLockscreen;
    @BindView(2131433825)
    View viewDeleteScene;
    @BindView(2131433827)
    View viewExecuteLocation;
    @BindView(2131433828)
    View viewExecuteResultNotify;
    @BindView(2131433845)
    View viewRename;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scene_more);
        ButterKnife.bind((Activity) this);
        String stringExtra = getIntent().getStringExtra("extra_scene_id");
        boolean z = true;
        if (getIntent().getIntExtra("from", 0) != 1) {
            z = false;
        }
        this.c = z;
        this.d = getIntent().getStringExtra(SmarthomeCreateAutoSceneActivity.EXTRA_DEVICE_ID);
        if (TextUtils.isEmpty(stringExtra) || TextUtils.equals("0", stringExtra)) {
            Log.e(f21312a, "sceneId <= 0");
            finish();
            return;
        }
        if (getIntent().getBooleanExtra(SmarthomeCreateAutoSceneActivity.EXTRA_IS_LITE, false)) {
            this.b = LiteSceneManager.j().a(stringExtra);
        } else {
            this.b = SceneManager.x().e(stringExtra);
        }
        if (this.b == null) {
            Log.e(f21312a, "mScene is null");
            finish();
            return;
        }
        a();
        b();
        c();
        f();
        d();
        g();
        h();
    }

    private void a() {
        this.ivTitleReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SceneMoreActivity.this.finish();
            }
        });
        this.tvTitle.setText(R.string.scene2_more);
        this.ivTitleMore.setVisibility(8);
    }

    private void b() {
        ((TextView) this.viewRename.findViewById(R.id.tv_name)).setText(R.string.scene2_rename);
        ((TextView) this.viewRename.findViewById(R.id.tv_content)).setText(this.b.g);
        this.viewRename.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MobclickAgent.a(SceneMoreActivity.this.getContext(), MiStatType.CLICK.getValue(), "scene_more_rename");
                SceneMoreActivity.this.a(SceneMoreActivity.this.b);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final SceneApi.SmartHomeScene smartHomeScene) {
        String str = TextUtils.isEmpty(smartHomeScene.g) ? "" : smartHomeScene.g;
        NameEditDialogHelper.a((Context) this, -1, str, getString(R.string.scene2_rename), str, CreateSceneManager.f21204a, (NameEditDialogHelper.NameEditListenerV2) new NameEditDialogHelper.NameEditListenerV2() {
            public void a(String str) {
            }

            public void a(ClientRemarkInputView clientRemarkInputView, String str) {
                final XQProgressDialog xQProgressDialog = new XQProgressDialog(SceneMoreActivity.this);
                xQProgressDialog.setMessage(SceneMoreActivity.this.getString(R.string.changeing_back_name));
                xQProgressDialog.setCancelable(false);
                xQProgressDialog.show();
                final String str2 = smartHomeScene.g;
                smartHomeScene.g = str;
                if (SmartHomeConfig.c) {
                    RemoteSceneApi.a().c((Context) SceneMoreActivity.this, smartHomeScene, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            if (SceneMoreActivity.this.isValid()) {
                                SmarthomeCreateAutoSceneActivity.notifyPlugin(SceneMoreActivity.this, SceneMoreActivity.this.c, SceneMoreActivity.this.b, SceneMoreActivity.this.d);
                                xQProgressDialog.dismiss();
                                ((TextView) SceneMoreActivity.this.viewRename.findViewById(R.id.tv_content)).setText(SceneMoreActivity.this.b.g);
                                if (SceneMoreActivity.this.b.t) {
                                    LiteSceneManager.j().b();
                                } else {
                                    SceneManager.x().c((String) null);
                                }
                            }
                        }

                        public void onFailure(Error error) {
                            if (SceneMoreActivity.this.isValid()) {
                                if (error == null || error.a() != -1) {
                                    Toast.makeText(SceneMoreActivity.this, R.string.change_back_name_fail, 0).show();
                                } else {
                                    Toast.makeText(SceneMoreActivity.this, R.string.smarthome_scene_has_deleted_device, 0).show();
                                }
                                xQProgressDialog.dismiss();
                                smartHomeScene.g = str2;
                            }
                        }
                    });
                }
            }

            public String b(String str) {
                if (TextUtils.equals(smartHomeScene.g, str)) {
                    return SceneMoreActivity.this.getString(R.string.smarthome_scene_reset_name);
                }
                for (SceneApi.SmartHomeScene next : SceneManager.x().v()) {
                    if (!TextUtils.equals(next.f, smartHomeScene.f) && TextUtils.equals(next.g, str)) {
                        return SceneMoreActivity.this.getString(R.string.scene_modify_name_error);
                    }
                }
                for (SceneApi.SmartHomeScene next2 : LiteSceneManager.j().e()) {
                    if (!TextUtils.equals(next2.f, smartHomeScene.f) && TextUtils.equals(next2.g, str)) {
                        return SceneMoreActivity.this.getString(R.string.scene_modify_name_error);
                    }
                }
                return "";
            }
        });
    }

    private void c() {
        ((TextView) this.viewExecuteResultNotify.findViewById(R.id.tv_name)).setText(R.string.scene2_execute_result_notify);
        ((TextView) this.viewExecuteResultNotify.findViewById(R.id.tv_content)).setText(this.b.o ? R.string.scene2_open : R.string.scene2_close);
        this.viewExecuteResultNotify.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MobclickAgent.a(SceneMoreActivity.this.getContext(), MiStatType.CLICK.getValue(), "scene_more_push");
                SceneMoreActivity.this.e();
            }
        });
    }

    private void d() {
        ((TextView) this.viewExecuteLocation.findViewById(R.id.tv_name)).setText(R.string.scene_execute_location);
        TextView textView = (TextView) this.viewExecuteLocation.findViewById(R.id.tv_content);
        textView.setCompoundDrawables((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        textView.setText(this.b.s == 0 ? R.string.scene_locale_cloud : R.string.scene_locale_local);
    }

    /* access modifiers changed from: private */
    public void e() {
        String[] strArr = {getString(R.string.scene2_open), getString(R.string.scene2_close)};
        final boolean z = this.b.o;
        MLAlertDialog b2 = new MLAlertDialog.Builder(this).a((CharSequence[]) strArr, this.b.o ^ true ? 1 : 0, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                SceneMoreActivity.this.b.o = i == 0;
                dialogInterface.dismiss();
                final XQProgressDialog xQProgressDialog = new XQProgressDialog(SceneMoreActivity.this);
                xQProgressDialog.setMessage(SceneMoreActivity.this.getString(R.string.retrieving_data));
                xQProgressDialog.setCancelable(false);
                xQProgressDialog.show();
                RemoteSceneApi.a().c((Context) SceneMoreActivity.this, SceneMoreActivity.this.b, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        xQProgressDialog.dismiss();
                        ((TextView) SceneMoreActivity.this.viewExecuteResultNotify.findViewById(R.id.tv_content)).setText(SceneMoreActivity.this.b.o ? R.string.scene2_open : R.string.scene2_close);
                    }

                    public void onFailure(Error error) {
                        if (error == null || error.a() != -1) {
                            Toast.makeText(SceneMoreActivity.this, R.string.upgrading_error, 0).show();
                        } else {
                            Toast.makeText(SceneMoreActivity.this, R.string.smarthome_scene_has_deleted_device, 0).show();
                        }
                        xQProgressDialog.dismiss();
                        SceneMoreActivity.this.b.o = z;
                    }
                });
            }
        }).b();
        b2.show();
        MultipleChoiceDialogHelper.a(b2.getContext(), b2);
    }

    private void f() {
        findViewById(R.id.view_add_to_lockscreen).setVisibility(8);
        ((TextView) this.viewAddToLockscreen.findViewById(R.id.tv_name)).setText(R.string.scene2_add_to_lockscreen);
        this.viewAddToLockscreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
    }

    private void g() {
        TextView textView = (TextView) this.viewAddToHome.findViewById(R.id.tv_name);
        if (SmartHomeSceneUtility.b(this.b)) {
            this.viewAddToHome.setVisibility(0);
        } else {
            this.viewAddToHome.setVisibility(8);
        }
        textView.setText(R.string.scene2_add_to_home);
        this.viewAddToHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MobclickAgent.a(SceneMoreActivity.this.getContext(), MiStatType.CLICK.getValue(), "scene_more_desk");
                SmartHomeSceneUtility.a((Activity) SceneMoreActivity.this, SceneMoreActivity.this.b, SmartHomeSceneUtility.b(SceneMoreActivity.this.b.i));
            }
        });
    }

    private void h() {
        ((TextView) this.viewDeleteScene.findViewById(R.id.tv_name)).setText(R.string.scene2_delete);
        this.viewDeleteScene.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SceneMoreActivity.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        new MLAlertDialog.Builder(this).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                SceneMoreActivity.this.b(dialogInterface, i);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) $$Lambda$SceneMoreActivity$bW_5u0c7mtjdOB06yHBJEf_X6m0.INSTANCE).a(true).b((int) R.string.scene_confirm_delete).d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(DialogInterface dialogInterface, int i) {
        MobclickAgent.a(getContext(), MiStatType.CLICK.getValue(), "scene_delet_click_tabmore");
        RemoteSceneApi.a().c((Context) this, this.b.f, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (SceneMoreActivity.this.b.t) {
                    LiteSceneManager.j().b(SceneMoreActivity.this.b.f);
                    LiteSceneManager.j().a((AsyncCallback) new AsyncCallback() {
                        public void onFailure(Error error) {
                        }

                        public void onSuccess(Object obj) {
                            LiteSceneManager.j().b();
                            SmarthomeCreateAutoSceneActivity.notifyPlugin(SceneMoreActivity.this, SceneMoreActivity.this.c, SceneMoreActivity.this.b, SceneMoreActivity.this.d);
                        }
                    });
                } else {
                    SceneManager.x().g(SceneMoreActivity.this.b.f);
                    SceneManager.x().c((String) null);
                }
                Intent intent = new Intent();
                intent.putExtra(SceneMoreActivity.TAG_SCENE_DELETED, true);
                SceneMoreActivity.this.setResult(-1, intent);
                SceneMoreActivity.this.finish();
            }

            public void onFailure(Error error) {
                if (SceneMoreActivity.this.isValid()) {
                    Toast.makeText(SceneMoreActivity.this, R.string.smarthome_scene_delete_fail, 0).show();
                }
            }
        });
    }
}
