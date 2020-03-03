package com.xiaomi.smarthome.miio.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.nestscroll.UIUtils;

public class BleGatewayMeshDialog extends DialogFragment {

    /* renamed from: a  reason: collision with root package name */
    public static final int f13575a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final String e = "type";
    public static final String f = "isMesh";
    private int g;
    private boolean h;
    /* access modifiers changed from: private */
    public BLEDialogClickListener i;
    @BindView(2131427636)
    Button mBtnAgree;
    @BindView(2131428162)
    Button mBtnCancel;
    @BindView(2131428864)
    TextView mContent;
    @BindView(2131430914)
    View mMiddleLine;

    public interface BLEDialogClickListener {
        void onClick();
    }

    public BleGatewayMeshDialog a(BLEDialogClickListener bLEDialogClickListener) {
        this.i = bLEDialogClickListener;
        return this;
    }

    public static BleGatewayMeshDialog a(int i2, boolean z) {
        BleGatewayMeshDialog bleGatewayMeshDialog = new BleGatewayMeshDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("type", i2);
        bundle.putBoolean(f, z);
        bleGatewayMeshDialog.setArguments(bundle);
        return bleGatewayMeshDialog;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.g = getArguments().getInt("type");
            this.h = getArguments().getBoolean(f);
        }
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.dialog_blegateway_mesh, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        a();
        return inflate;
    }

    private void a() {
        switch (this.g) {
            case 1:
                this.mContent.setText(R.string.ble_mesh_dialog_close_tip);
                break;
            case 2:
                this.mBtnAgree.setText(R.string.ble_mesh_dialog_ok);
                this.mContent.setText(R.string.ble_mesh_dialog_download_xiaoai_app);
                break;
            case 3:
                this.mBtnAgree.setVisibility(8);
                this.mMiddleLine.setVisibility(8);
                this.mBtnCancel.setText(R.string.ble_mesh_dialog_know);
                this.mContent.setText(R.string.ble_mesh_dialog_update_hardware);
                this.mBtnCancel.setBackgroundResource(R.drawable.dialog_btn_selector);
                break;
            case 4:
                this.mBtnAgree.setText(R.string.ble_mesh_dialog_ok);
                if (!this.h) {
                    this.mContent.setText(R.string.ble_mesh_dialog_goto_app);
                    break;
                } else {
                    this.mContent.setText(R.string.ble_mesh_dialog_goto_app_mesh);
                    break;
                }
        }
        this.mBtnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BleGatewayMeshDialog.this.dismiss();
            }
        });
        this.mBtnAgree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (BleGatewayMeshDialog.this.i != null) {
                    BleGatewayMeshDialog.this.i.onClick();
                }
                BleGatewayMeshDialog.this.dismiss();
            }
        });
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.getWindow().requestFeature(1);
        onCreateDialog.setCancelable(false);
        onCreateDialog.setCanceledOnTouchOutside(false);
        onCreateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return onCreateDialog;
    }

    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            window.setGravity(80);
            window.setBackgroundDrawable(new InsetDrawable(new ColorDrawable(0), UIUtils.a(8), UIUtils.a(8), UIUtils.a(8), UIUtils.a(8)));
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.height = -2;
            window.setAttributes(attributes);
        }
    }

    public void show(FragmentManager fragmentManager, String str) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add((Fragment) this, str);
        beginTransaction.commitAllowingStateLoss();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
    }

    public void onDestroyView() {
        super.onDestroyView();
    }
}
