package com.xiaomi.smarthome.miio.ir;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.device.IRRemoteInfo;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import java.util.ArrayList;
import java.util.List;

public class IRHeaderTabs extends FrameLayout {
    static final String TAG = "IRHeaderTabs";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public boolean f13579a = false;
    private SharedPreferences b;
    /* access modifiers changed from: private */
    public ArrayList<View> c = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<String> d = new ArrayList<>();
    View mBtnIRSettings;
    View mBtnJumpRemote;
    Button mBtnRemote0;
    Button mBtnRemote1;
    Button mBtnRemote2;
    ImageView mCkDuoKan = null;
    ImageView mCkTianJia = null;
    ImageView mIrCollapseExpandIv;
    TextView mIrCollapseExpandTv;
    View mIrContainer;
    View mNormalRoot;
    View.OnClickListener mRemoteClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            int i = 0;
            while (i < IRHeaderTabs.this.c.size() && view != ((View) IRHeaderTabs.this.c.get(i))) {
                i++;
            }
            if (i < IRHeaderTabs.this.c.size()) {
                String str = (String) IRHeaderTabs.this.d.get(i);
            }
        }
    };
    public List<IRRemoteInfo> mRemoteInfoList;
    View mRemoteRoot;
    Button[] mRemotesNormal;
    Button[] mRemotesTab;
    LinearLayout mScDuoKan = null;
    LinearLayout mScTianJia = null;
    SwitchButton mSwCardMode = null;
    View mTabDivider0;
    View mTabDivider1;
    RadioButton mTabRemote0;
    RadioButton mTabRemote1;
    RadioButton mTabRemote2;
    TextView mTvDuoKan = null;
    TextView mTvTianJia = null;

    public IRHeaderTabs(Context context) {
        super(context);
        init();
    }

    public IRHeaderTabs(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public IRHeaderTabs(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: package-private */
    public void init() {
        inflate(getContext(), R.layout.ir_header_view_layout_locked_tabs, this);
        onFinishInflate();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f13579a = false;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mBtnIRSettings = findViewById(R.id.btn_ir_settings);
        this.mBtnIRSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CoreApi.a().q()) {
                    Intent intent = new Intent();
                    intent.setData(Uri.parse("https://home.mi.com/main/login"));
                    intent.setPackage(IRHeaderTabs.this.getContext().getPackageName());
                    IRHeaderTabs.this.getContext().startActivity(intent);
                    DisplayUtils.a(IRHeaderTabs.this.getContext(), 17432576, 17432577);
                    OpenApi.a(IRHeaderTabs.this.getContext());
                    return;
                }
                String a2 = IRDeviceUtil.a();
                if (!TextUtils.isEmpty(a2)) {
                    if (!CoreApi.a().c(a2)) {
                        CoreApi.a().a(true, (CoreApi.UpdateConfigCallback) new CoreApi.UpdateConfigCallback() {
                            public void a(PluginError pluginError) {
                            }

                            public void a(boolean z, boolean z2) {
                                if (z) {
                                    SmartHomeDeviceManager.a().r();
                                }
                            }
                        });
                        Toast.makeText(IRHeaderTabs.this.getContext(), R.string.toast_failed_retry, 0).show();
                        return;
                    }
                    IRDeviceUtil.a(IRHeaderTabs.this.getContext(), (Intent) null);
                    DisplayUtils.a(IRHeaderTabs.this.getContext(), 17432576, 17432577);
                    OpenApi.a(IRHeaderTabs.this.getContext());
                }
            }
        });
        this.mBtnIRSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CoreApi.a().q()) {
                    Intent intent = new Intent();
                    intent.setData(Uri.parse("https://home.mi.com/main/login"));
                    intent.setPackage(IRHeaderTabs.this.getContext().getPackageName());
                    IRHeaderTabs.this.getContext().startActivity(intent);
                    DisplayUtils.a(IRHeaderTabs.this.getContext(), 17432576, 17432577);
                    OpenApi.a(IRHeaderTabs.this.getContext());
                    return;
                }
                String a2 = IRDeviceUtil.a();
                if (!TextUtils.isEmpty(a2)) {
                    if (!CoreApi.a().c(a2)) {
                        CoreApi.a().a(true, (CoreApi.UpdateConfigCallback) new CoreApi.UpdateConfigCallback() {
                            public void a(PluginError pluginError) {
                            }

                            public void a(boolean z, boolean z2) {
                                if (z) {
                                    SmartHomeDeviceManager.a().r();
                                }
                            }
                        });
                        Toast.makeText(IRHeaderTabs.this.getContext(), R.string.toast_failed_retry, 0).show();
                        return;
                    }
                    IRDeviceUtil.a(IRHeaderTabs.this.getContext(), (Intent) null);
                    DisplayUtils.a(IRHeaderTabs.this.getContext(), 17432576, 17432577);
                    OpenApi.a(IRHeaderTabs.this.getContext());
                }
            }
        });
        this.mNormalRoot = findViewById(R.id.normal_root);
        this.mRemoteRoot = findViewById(R.id.remote_root);
        int i = 8;
        this.mNormalRoot.setVisibility(8);
        this.mRemoteRoot.setVisibility(8);
        this.mBtnRemote0 = (Button) this.mNormalRoot.findViewById(R.id.btn_remote0);
        this.mBtnRemote1 = (Button) this.mNormalRoot.findViewById(R.id.btn_remote1);
        this.mBtnRemote2 = (Button) this.mNormalRoot.findViewById(R.id.btn_remote2);
        this.mRemotesNormal = new Button[]{this.mBtnRemote0, this.mBtnRemote1, this.mBtnRemote2};
        this.mTabRemote0 = (RadioButton) this.mRemoteRoot.findViewById(R.id.tab_remote0);
        this.mTabRemote1 = (RadioButton) this.mRemoteRoot.findViewById(R.id.tab_remote1);
        this.mTabRemote2 = (RadioButton) this.mRemoteRoot.findViewById(R.id.tab_remote2);
        this.mTabDivider0 = this.mRemoteRoot.findViewById(R.id.tab_divider0);
        this.mTabDivider1 = this.mRemoteRoot.findViewById(R.id.tab_divider1);
        this.mRemotesTab = new Button[]{this.mTabRemote0, this.mTabRemote1, this.mTabRemote2};
        this.mIrContainer = findViewById(R.id.ir_content_container);
        this.mIrCollapseExpandTv = (TextView) findViewById(R.id.collapse_expand_tv);
        this.mIrCollapseExpandIv = (ImageView) findViewById(R.id.collapse_expand_iv);
        this.b = getContext().getSharedPreferences("lock_screen", 0);
        boolean z = this.b.getBoolean("ir_expand", false);
        View view = this.mIrContainer;
        if (z) {
            i = 0;
        }
        view.setVisibility(i);
        this.mIrCollapseExpandTv.setText(z ? R.string.collapse : R.string.expand);
        this.mIrCollapseExpandIv.setImageResource(z ? R.drawable.std_home_btn_collasping : R.drawable.std_home_btn_expanding);
        this.mIrCollapseExpandTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                IRHeaderTabs.this.a();
            }
        });
        findViewById(R.id.collapse_expand_iv_container).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                IRHeaderTabs.this.a();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a() {
        int i = 0;
        boolean z = this.mIrContainer.getVisibility() == 0;
        View view = this.mIrContainer;
        if (z) {
            i = 8;
        }
        view.setVisibility(i);
        this.mIrCollapseExpandTv.setText(!z ? R.string.collapse : R.string.expand);
        this.mIrCollapseExpandIv.setImageResource(!z ? R.drawable.std_home_btn_collasping : R.drawable.std_home_btn_expanding);
        this.b.edit().putBoolean("ir_expand", !z).commit();
    }

    /* access modifiers changed from: package-private */
    public void buildRemoteButtons(int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            View findViewById = findViewById(iArr[i]);
            if (findViewById != null) {
                findViewById.setOnClickListener(this.mRemoteClickListener);
                this.c.add(findViewById);
                this.d.add(IRV2ControllerMiActivity.IRV2_MI_CONTROLLER_KEYPAD[i]);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void buildRemotes(Button[] buttonArr, boolean z) {
        buttonArr[1].setVisibility(0);
        int i = 8;
        if (!CoreApi.a().q()) {
            buttonArr[0].setVisibility(0);
            a(buttonArr[0], (IRRemoteInfo) null, z);
            Button button = buttonArr[2];
            if (z) {
                i = 4;
            }
            button.setVisibility(i);
            a(buttonArr[1], z);
        } else if (this.mRemoteInfoList == null || this.mRemoteInfoList.size() <= 0) {
            buttonArr[0].setVisibility(z ? 4 : 8);
            Button button2 = buttonArr[2];
            if (z) {
                i = 4;
            }
            button2.setVisibility(i);
            a(buttonArr[1], z);
        } else {
            buttonArr[0].setVisibility(0);
            a(buttonArr[0], this.mRemoteInfoList.get(0), z);
            int size = this.mRemoteInfoList.size();
            if (size == 1) {
                Button button3 = buttonArr[2];
                if (z) {
                    i = 4;
                }
                button3.setVisibility(i);
                a(buttonArr[1], z);
            } else {
                buttonArr[2].setVisibility(0);
                if (size == 2) {
                    a(buttonArr[1], this.mRemoteInfoList.get(1), z);
                    a(buttonArr[2], z);
                } else if (size >= 3) {
                    a(buttonArr[1], this.mRemoteInfoList.get(1), z);
                    a(buttonArr[2], this.mRemoteInfoList.get(2), z);
                }
            }
        }
        if (z) {
            return;
        }
        if (buttonArr[2].getVisibility() != 0) {
            buttonArr[1].setBackgroundResource(R.drawable.lock_irv2_tab_right);
            this.mTabDivider1.setVisibility(4);
            return;
        }
        buttonArr[1].setBackgroundResource(R.drawable.lock_irv2_tab_middle);
        this.mTabDivider1.setVisibility(0);
    }

    public void updateBottomView() {
        AsyncTaskUtils.a(new AsyncTask<Object, Object, List<IRRemoteInfo>>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public List<IRRemoteInfo> doInBackground(Object... objArr) {
                try {
                    return IRDeviceUtil.b(SHApplication.getAppContext());
                } catch (Throwable th) {
                    th.printStackTrace();
                    return null;
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(List<IRRemoteInfo> list) {
                RadioButton radioButton;
                if (!IRHeaderTabs.this.f13579a) {
                    IRHeaderTabs.this.mRemoteInfoList = list;
                    int a2 = IRDeviceUtil.a(IRHeaderTabs.this.mRemoteInfoList, 3);
                    if (a2 >= 0 || !CoreApi.a().q()) {
                        IRHeaderTabs.this.mNormalRoot.setVisibility(8);
                        IRHeaderTabs.this.mRemoteRoot.setVisibility(0);
                        IRHeaderTabs.this.buildRemoteButtons(IRV2ControllerMiActivity.IRV2_MI_CONTROLLER_KEYPAD_VIEW);
                        IRHeaderTabs.this.buildRemotes(IRHeaderTabs.this.mRemotesTab, false);
                        if (a2 < 0) {
                            radioButton = (RadioButton) IRHeaderTabs.this.mRemotesTab[0];
                        } else {
                            radioButton = (RadioButton) IRHeaderTabs.this.mRemotesTab[a2];
                        }
                        radioButton.setChecked(true);
                        return;
                    }
                    IRHeaderTabs.this.mNormalRoot.setVisibility(0);
                    IRHeaderTabs.this.mRemoteRoot.setVisibility(8);
                    IRHeaderTabs.this.buildRemotes(IRHeaderTabs.this.mRemotesNormal, true);
                }
            }
        }, new Object[0]);
    }

    private void a(final Button button, final IRRemoteInfo iRRemoteInfo, boolean z) {
        if (!CoreApi.a().q()) {
            button.setText(R.string.buildin_ir_name);
            if (z) {
                button.setCompoundDrawables((Drawable) null, IRDeviceUtil.b(true), (Drawable) null, (Drawable) null);
            } else {
                button.setCompoundDrawables((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            if (z || !(button instanceof RadioButton)) {
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        button.getContext().startActivity(new Intent(button.getContext(), IRV2ControllerMiActivity.class));
                        DisplayUtils.a(IRHeaderTabs.this.getContext(), 17432576, 17432577);
                        OpenApi.a(IRHeaderTabs.this.getContext());
                    }
                });
            } else {
                ((RadioButton) button).setChecked(true);
            }
        } else {
            button.setText(iRRemoteInfo.b);
            if (z) {
                button.setCompoundDrawables((Drawable) null, IRDeviceUtil.c(iRRemoteInfo.c, true), (Drawable) null, (Drawable) null);
            } else {
                button.setCompoundDrawables((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            if (z || !IRDeviceUtil.a(iRRemoteInfo.c) || !(button instanceof RadioButton)) {
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!CoreApi.a().q()) {
                            Intent intent = new Intent();
                            intent.setData(Uri.parse("https://home.mi.com/main/login"));
                            intent.setPackage(IRHeaderTabs.this.getContext().getPackageName());
                            IRHeaderTabs.this.getContext().startActivity(intent);
                            DisplayUtils.a(IRHeaderTabs.this.getContext(), 17432576, 17432577);
                            OpenApi.a(IRHeaderTabs.this.getContext());
                            return;
                        }
                        String a2 = IRDeviceUtil.a();
                        if (!TextUtils.isEmpty(a2)) {
                            if (!CoreApi.a().c(a2)) {
                                CoreApi.a().a(true, (CoreApi.UpdateConfigCallback) new CoreApi.UpdateConfigCallback() {
                                    public void a(PluginError pluginError) {
                                    }

                                    public void a(boolean z, boolean z2) {
                                        if (z) {
                                            SmartHomeDeviceManager.a().r();
                                        }
                                    }
                                });
                                Toast.makeText(IRHeaderTabs.this.getContext(), R.string.toast_failed_retry, 0).show();
                                return;
                            }
                            Intent intent2 = new Intent();
                            intent2.putExtra("remote_id", iRRemoteInfo.f14856a);
                            IRDeviceUtil.a(IRHeaderTabs.this.getContext(), intent2);
                            DisplayUtils.a(IRHeaderTabs.this.getContext(), 17432576, 17432577);
                            OpenApi.a(IRHeaderTabs.this.getContext());
                        }
                    }
                });
            } else {
                ((RadioButton) button).setChecked(true);
            }
        }
    }

    private void a(Button button, boolean z) {
        button.setText(R.string.add_ir_remote);
        if (z) {
            Drawable drawable = getContext().getResources().getDrawable(R.drawable.std_lock_list_infrared_icon_plus);
            int a2 = DisplayUtils.a(56.0f);
            drawable.setBounds(0, 0, a2, a2);
            button.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, drawable, (Drawable) null, (Drawable) null);
        } else {
            button.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        }
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CoreApi.a().q()) {
                    Intent intent = new Intent();
                    intent.setData(Uri.parse("https://home.mi.com/main/login"));
                    intent.setPackage(IRHeaderTabs.this.getContext().getPackageName());
                    IRHeaderTabs.this.getContext().startActivity(intent);
                    DisplayUtils.a(IRHeaderTabs.this.getContext(), 17432576, 17432577);
                    OpenApi.a(IRHeaderTabs.this.getContext());
                    return;
                }
                String a2 = IRDeviceUtil.a();
                if (!TextUtils.isEmpty(a2)) {
                    if (!CoreApi.a().c(a2)) {
                        CoreApi.a().a(true, (CoreApi.UpdateConfigCallback) new CoreApi.UpdateConfigCallback() {
                            public void a(PluginError pluginError) {
                            }

                            public void a(boolean z, boolean z2) {
                                if (z) {
                                    SmartHomeDeviceManager.a().r();
                                }
                            }
                        });
                        Toast.makeText(IRHeaderTabs.this.getContext(), R.string.toast_failed_retry, 0).show();
                        return;
                    }
                    Intent intent2 = new Intent();
                    intent2.putExtra("add_device", true);
                    IRDeviceUtil.a(IRHeaderTabs.this.getContext(), intent2);
                    DisplayUtils.a(IRHeaderTabs.this.getContext(), 17432576, 17432577);
                    OpenApi.a(IRHeaderTabs.this.getContext());
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f13579a = true;
    }
}
