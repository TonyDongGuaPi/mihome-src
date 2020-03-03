package com.xiaomi.smarthome.miio.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.DeviceMoreActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.library.common.widget.ColorRadioButton;
import com.xiaomi.smarthome.library.common.widget.SeekArc;
import com.xiaomi.smarthome.library.common.widget.colorpicker.ColorPicker;
import com.xiaomi.smarthome.library.common.widget.colorpicker.ColorPickerRoundRect;
import com.xiaomi.smarthome.miio.device.SmartBulbDevice;
import com.xiaomi.smarthome.miio.yeelight.YeelightColorAdapter;
import com.xiaomi.smarthome.miio.yeelight.YeelightColorItem;
import com.xiaomi.smarthome.miio.yeelight.YeelightPrefManager;
import com.xiaomi.smarthome.miio.yeelight.YeelightPresetDialog;
import com.xiaomi.smarthome.miio.yeelight.YeelightUserDefineDialog;
import java.util.ArrayList;
import java.util.List;

public class YeeLinkBulbActivityV2 extends BaseActivity implements ClientRemarkInputView.RenameInterface, SmartBulbDevice.UIListener {
    public static final int AUTO_CHANGE_TIME = 250;
    public static final int AUTO_DECREASE_LIGHT = 2;
    public static final int AUTO_INCREASE_LIGHT = 1;
    public static final String EXTRA_YEELINK_DEVICE = "yeelink_mac";
    static final int PRESET_COLOR_COUNT = 4;
    static final String TAG = "YeeLinkBulbActivityV2";
    YeelightColorAdapter mAdapter;
    SeekArc mBrightSeeker;
    Button mBtnSaveColor;
    RadioGroup mColorGroup;
    int mColorIdx = -1;
    List<YeelightColorItem> mColorList;
    ColorPickerRoundRect mColorPicker;
    SmartBulbDevice mDevice;
    EditText mEditColorName;
    XQProgressDialog mPD;
    ImageButton mPowerSwitch;
    ColorRadioButton mRbAddMore;
    long mSetColorTime = System.currentTimeMillis();
    TextView mTxtBrightValue;
    TextView mTxtColorValue;
    ViewSwitcher mViewSwitcher;

    public int getPresetBrightness(int i) {
        switch (i) {
            case 1:
                return 90;
            case 3:
                return 30;
            default:
                return 50;
        }
    }

    public void onLightChanged() {
    }

    public String getPresetName(int i) {
        switch (i) {
            case 0:
                return getString(R.string.yeelight_color_preset0);
            case 1:
                return getString(R.string.yeelight_color_preset1);
            case 2:
                return getString(R.string.yeelight_color_preset2);
            case 3:
                return getString(R.string.yeelight_color_preset3);
            default:
                return null;
        }
    }

    public String getPresetDisc(int i) {
        switch (i) {
            case 0:
                return getString(R.string.yeelight_color_desc0);
            case 1:
                return getString(R.string.yeelight_color_desc1);
            case 2:
                return getString(R.string.yeelight_color_desc2);
            case 3:
                return getString(R.string.yeelight_color_desc3);
            default:
                return null;
        }
    }

    public int getPresetColor(int i) {
        int i2 = R.color.yeelight_preset0;
        switch (i) {
            case 1:
                i2 = R.color.yeelight_preset1;
                break;
            case 2:
                i2 = R.color.yeelight_preset2;
                break;
            case 3:
                i2 = R.color.yeelight_preset3;
                break;
        }
        return getResources().getColor(i2);
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 1:
                this.mDevice.v();
                this.mHandler.sendEmptyMessageDelayed(1, 250);
                return;
            case 2:
                this.mDevice.w();
                this.mHandler.sendEmptyMessageDelayed(2, 250);
                return;
            default:
                return;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("yeelink_mac");
        if (stringExtra == null) {
            MyLog.e(" mac == null and return!");
            finish();
            return;
        }
        Device b = SmartHomeDeviceManager.a().b(stringExtra);
        if (b == null || !(b instanceof SmartBulbDevice)) {
            finish();
            return;
        }
        this.mDevice = (SmartBulbDevice) b;
        this.mDevice.a((SmartBulbDevice.UIListener) this);
        this.mDevice.x();
        setContentView(R.layout.yeelight_main);
        a();
        c();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    public void onBackPressed() {
        if (this.mViewSwitcher.getCurrentView().getId() == R.id.ll_add_new) {
            this.mViewSwitcher.showPrevious();
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mDevice != null) {
            this.mDevice.a();
        }
        e();
        if (YeelightPrefManager.b()) {
            YeelightPrefManager.c();
            new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.yeelight_tips_forusage)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).b().show();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.mDevice != null) {
            this.mDevice.b();
        }
    }

    private void a() {
        findViewById(R.id.module_a_4_return_more_black_more_btn).setVisibility(4);
        findViewById(R.id.module_a_4_return_more_black_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                YeeLinkBulbActivityV2.this.finish();
            }
        });
        findViewById(R.id.module_a_4_return_more_black_more_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                YeeLinkBulbActivityV2.this.gotoMorePage();
            }
        });
        if (this.mDevice.isShared()) {
            findViewById(R.id.module_a_4_return_more_black_more_btn).setVisibility(4);
        }
    }

    private void b() {
        this.mColorGroup = (RadioGroup) findViewById(R.id.rg_colors);
        ArrayList arrayList = new ArrayList();
        arrayList.add(findViewById(R.id.ib_preset0));
        arrayList.add(findViewById(R.id.ib_preset1));
        arrayList.add(findViewById(R.id.ib_preset2));
        arrayList.add(findViewById(R.id.ib_preset3));
        for (final int i = 0; i < arrayList.size(); i++) {
            final int presetColor = getPresetColor(i);
            final int presetBrightness = getPresetBrightness(i);
            final ArrayList arrayList2 = arrayList;
            final int i2 = i;
            final int i3 = presetColor;
            final int i4 = presetBrightness;
            ((View) arrayList.get(i)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    YeeLinkBulbActivityV2.this.a((ColorRadioButton) arrayList2.get(i2), i3, i4, i2);
                }
            });
            ((View) arrayList.get(i)).setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    YeelightPresetDialog yeelightPresetDialog = new YeelightPresetDialog(YeeLinkBulbActivityV2.this);
                    yeelightPresetDialog.a(presetColor, presetBrightness, YeeLinkBulbActivityV2.this.getPresetName(i), YeeLinkBulbActivityV2.this.getPresetDisc(i));
                    yeelightPresetDialog.show();
                    return true;
                }
            });
        }
        for (int i5 = 0; i5 < this.mAdapter.getCount(); i5++) {
            a(i5);
        }
    }

    /* access modifiers changed from: private */
    public void a(ColorRadioButton colorRadioButton, int i, int i2, int i3) {
        if (this.mDevice != null) {
            this.mColorIdx = i3;
            this.mDevice.a(Color.red(i), Color.green(i), Color.blue(i), i2);
            setColorBar(i, i2);
            a(Color.red(i), Color.green(i), Color.blue(i), i2);
            colorRadioButton.setChecked(true);
        }
    }

    /* access modifiers changed from: private */
    public void a(final int i) {
        int childCount = this.mColorGroup.getChildCount() - 1;
        View view = this.mAdapter.getView(i, (View) null, this.mColorGroup);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                YeeLinkBulbActivityV2.this.a((ColorRadioButton) view, YeelightPrefManager.b(i), YeelightPrefManager.c(i), i + 4);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                final YeelightUserDefineDialog yeelightUserDefineDialog = new YeelightUserDefineDialog(YeeLinkBulbActivityV2.this);
                yeelightUserDefineDialog.a(YeelightPrefManager.b(i), YeelightPrefManager.c(i), YeelightPrefManager.d(i), new View.OnClickListener() {
                    public void onClick(View view) {
                        YeeLinkBulbActivityV2.this.b(i);
                        yeelightUserDefineDialog.dismiss();
                    }
                });
                yeelightUserDefineDialog.show();
                return true;
            }
        });
        this.mColorGroup.addView(view, childCount);
        this.mColorGroup.addView(new View(this), childCount + 1, new ViewGroup.LayoutParams(getResources().getDimensionPixelSize(R.dimen.yeelight_radiobutton_margin), 0));
    }

    /* access modifiers changed from: private */
    public void b(int i) {
        int i2 = -1;
        int i3 = 0;
        int i4 = -1;
        while (true) {
            if (i3 >= this.mColorGroup.getChildCount()) {
                break;
            }
            View childAt = this.mColorGroup.getChildAt(i3);
            if (childAt.getId() != R.id.rb_add_more) {
                if ((childAt instanceof ColorRadioButton) && (i4 = i4 + 1) == 4) {
                    i2 = i3;
                    break;
                }
                i3++;
            } else {
                break;
            }
        }
        if (i2 >= 0) {
            while (this.mColorGroup.getChildAt(i2).getId() != R.id.rb_add_more) {
                this.mColorGroup.removeViewAt(i2);
            }
        }
        YeelightPrefManager.e(i);
        int d = YeelightPrefManager.d();
        for (int i5 = 0; i5 < d; i5++) {
            a(i5);
        }
    }

    private void c() {
        this.mAdapter = new YeelightColorAdapter(this);
        b();
        this.mViewSwitcher = (ViewSwitcher) findViewById(R.id.vs_bottom);
        this.mEditColorName = (EditText) findViewById(R.id.edit_color_name);
        this.mRbAddMore = (ColorRadioButton) findViewById(R.id.rb_add_more);
        this.mRbAddMore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                YeeLinkBulbActivityV2.this.mViewSwitcher.showNext();
            }
        });
        this.mBtnSaveColor = (Button) findViewById(R.id.btn_save_color);
        this.mBtnSaveColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String obj = YeeLinkBulbActivityV2.this.mEditColorName.getText().toString();
                if (!TextUtils.isEmpty(obj)) {
                    if (XMStringUtils.h(obj) > 8) {
                        Toast.makeText(YeeLinkBulbActivityV2.this, R.string.yeelight_toast_name_too_long, 1).show();
                        return;
                    } else {
                        YeelightPrefManager.a(YeeLinkBulbActivityV2.this.mColorPicker.getColor(), YeeLinkBulbActivityV2.this.mBrightSeeker.getProgress(), obj);
                        YeeLinkBulbActivityV2.this.a(YeelightPrefManager.d() - 1);
                    }
                }
                YeeLinkBulbActivityV2.this.mViewSwitcher.showPrevious();
            }
        });
        this.mTxtBrightValue = (TextView) findViewById(R.id.txt_bright_value);
        this.mTxtColorValue = (TextView) findViewById(R.id.txt_color_value);
        if (TextUtils.isEmpty(this.mDevice.getName())) {
            ((TextView) findViewById(R.id.module_a_4_return_more_black_title)).setText(R.string.smart_bulb);
        } else {
            ((TextView) findViewById(R.id.module_a_4_return_more_black_title)).setText(this.mDevice.getName());
        }
        this.mPowerSwitch = (ImageButton) findViewById(R.id.ib_power_switch);
        this.mPowerSwitch.setEnabled(true);
        this.mPowerSwitch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (YeeLinkBulbActivityV2.this.mDevice.r()) {
                    YeeLinkBulbActivityV2.this.mDevice.t();
                } else {
                    YeeLinkBulbActivityV2.this.mDevice.u();
                }
            }
        });
        this.mColorPicker = (ColorPickerRoundRect) findViewById(R.id.cp_yeelight);
        this.mBrightSeeker = (SeekArc) findViewById(R.id.sa_brightness);
        e();
    }

    /* access modifiers changed from: package-private */
    public void gotoMorePage() {
        Intent intent = new Intent(this, DeviceMoreActivity.class);
        intent.putExtra("did", this.mDevice.did);
        intent.putExtra(DeviceMoreActivity.ARGS_FIRMWARE_ENABLE, false);
        intent.putExtra(DeviceMoreActivity.ARGS_SHARE_EBABLE, false);
        intent.putExtra(DeviceMoreActivity.ARGS_UNBIND_ENABLE, false);
        intent.putExtra(DeviceMoreActivity.ARGS_DELETE_ENABLE, false);
        startActivityForResult(intent, 1);
        overridePendingTransition(0, 0);
    }

    private int a(float f) {
        return Color.HSVToColor(new float[]{f, 1.0f, 1.0f});
    }

    /* access modifiers changed from: private */
    public void a(int i, int i2, int i3) {
        this.mSetColorTime = System.currentTimeMillis();
        TextView textView = this.mTxtColorValue;
        textView.setText(getString(R.string.yeelight_color_value) + "R：" + i + "/ G:" + i2 + "/ B:" + i3);
        int i4 = (i << 16) & 16711680;
        this.mColorPicker.setOnColorSelectedListener((ColorPicker.OnColorSelectedListener) null);
        this.mColorPicker.setColor(i4 | ((i2 << 8) & 65280) | (i3 & 255) | -16777216);
        this.mColorPicker.setOnColorSelectedListener(new ColorPicker.OnColorSelectedListener() {
            public void a(int i) {
                YeeLinkBulbActivityV2.this.mDevice.a(Color.red(i), Color.green(i), Color.blue(i));
                YeeLinkBulbActivityV2.this.a(Color.red(i), Color.green(i), Color.blue(i));
                YeeLinkBulbActivityV2.this.setColorBar(i, YeeLinkBulbActivityV2.this.mDevice.s());
            }
        });
        this.mColorPicker.setOnColorChangingListener(new ColorPicker.OnColorChangingListener() {
            public void a(int i) {
                TextView textView = YeeLinkBulbActivityV2.this.mTxtColorValue;
                textView.setText(YeeLinkBulbActivityV2.this.getString(R.string.yeelight_color_value) + "R：" + Color.red(i) + "/ G:" + Color.green(i) + "/ B:" + Color.blue(i));
            }
        });
    }

    /* access modifiers changed from: private */
    public void c(int i) {
        this.mSetColorTime = System.currentTimeMillis();
        TextView textView = this.mTxtBrightValue;
        textView.setText(getString(R.string.yeelight_bright_value) + i + Operators.MOD);
        this.mBrightSeeker.setOnSeekArcChangeListener((SeekArc.OnSeekArcChangeListener) null);
        this.mBrightSeeker.setProgress(i);
        this.mBrightSeeker.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            public void a(SeekArc seekArc) {
            }

            public void a(SeekArc seekArc, int i, boolean z) {
                TextView textView = YeeLinkBulbActivityV2.this.mTxtBrightValue;
                textView.setText(YeeLinkBulbActivityV2.this.getString(R.string.yeelight_bright_value) + i + Operators.MOD);
            }

            public void b(SeekArc seekArc) {
                int progress = seekArc.getProgress();
                YeeLinkBulbActivityV2.this.mDevice.a(progress);
                YeeLinkBulbActivityV2.this.c(progress);
                YeeLinkBulbActivityV2.this.setColorBar(YeeLinkBulbActivityV2.this.mDevice.e(), progress);
            }
        });
    }

    private void a(int i, int i2, int i3, int i4) {
        a(i, i2, i3);
        c(i4);
    }

    private void d() {
        int e = this.mDevice.e() | -16777216;
        a(Color.red(e), Color.green(e), Color.blue(e), this.mDevice.s());
    }

    public void onGetBulbStatus() {
        Log.d(TAG, "onGetBulbStatus");
        if (System.currentTimeMillis() - this.mSetColorTime > 10000) {
            e();
        }
    }

    private void e() {
        if (this.mDevice != null) {
            d();
            updateColorBar();
        }
    }

    public void modifyBackName(String str) {
        this.mPD = new XQProgressDialog(this);
        this.mPD.setMessage(getString(R.string.changeing_back_name));
        this.mPD.setCancelable(false);
        this.mPD.show();
        this.mDevice.a(str);
    }

    /* access modifiers changed from: package-private */
    public void updateColorBar() {
        if (this.mDevice != null) {
            setColorBar(this.mDevice.e(), this.mDevice.s());
        }
    }

    /* access modifiers changed from: package-private */
    public int getColorByIdx(int i) {
        if (i < 4) {
            return getPresetColor(i);
        }
        return YeelightPrefManager.b(i - 4);
    }

    /* access modifiers changed from: package-private */
    public int getBrightByIdx(int i) {
        if (i < 4) {
            return getPresetBrightness(i);
        }
        return YeelightPrefManager.c(i - 4);
    }

    /* access modifiers changed from: package-private */
    public void setColorBar(int i, int i2) {
        int i3 = i | -16777216;
        if (this.mColorIdx >= 0 && i3 == getColorByIdx(this.mColorIdx) && i2 == getBrightByIdx(this.mColorIdx)) {
            setColorCheckState(this.mColorIdx);
            return;
        }
        int i4 = 0;
        while (true) {
            if (i4 < YeelightPrefManager.d() + 4) {
                if (i3 == getColorByIdx(i4) && i2 == getBrightByIdx(i4)) {
                    break;
                }
                i4++;
            } else {
                i4 = -1;
                break;
            }
        }
        setColorCheckState(i4);
    }

    /* access modifiers changed from: package-private */
    public void setColorCheckState(int i) {
        if (this.mColorGroup != null) {
            if (i < 0 || i >= this.mColorGroup.getChildCount()) {
                this.mColorGroup.clearCheck();
                return;
            }
            int i2 = -1;
            int i3 = 0;
            while (i3 < this.mColorGroup.getChildCount()) {
                View childAt = this.mColorGroup.getChildAt(i3);
                if (!(childAt instanceof ColorRadioButton) || (i2 = i2 + 1) != i) {
                    i3++;
                } else {
                    ((ColorRadioButton) childAt).setChecked(true);
                    return;
                }
            }
        }
    }

    private void f() {
        String o = this.mDevice.getName();
        final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) getLayoutInflater().inflate(R.layout.client_remark_input_view, (ViewGroup) null);
        clientRemarkInputView.initialize(this, new MLAlertDialog.Builder(this).a((int) R.string.change_back_name).b((View) clientRemarkInputView).d(false).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                clientRemarkInputView.onConfirm(dialogInterface);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
            }
        }).d(), o);
    }

    public void onNameChangeSuccess() {
        this.mPD.dismiss();
        if (TextUtils.isEmpty(this.mDevice.getName())) {
            ((TextView) findViewById(R.id.module_a_4_return_more_black_title)).setText(R.string.smart_bulb);
        } else {
            ((TextView) findViewById(R.id.module_a_4_return_more_black_title)).setText(this.mDevice.getName());
        }
    }

    public void onNameCHangeFailed() {
        this.mPD.dismiss();
        Toast.makeText(this, R.string.change_back_name_fail, 0).show();
    }
}
