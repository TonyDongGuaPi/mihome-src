package com.xiaomi.smarthome.miio.camera.face.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.mijia.camera.MijiaCameraDevice;
import com.mijia.model.CameraImageLoader;
import com.mijia.model.CameraImageLoaderEx;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.nestscroll.UIUtils;
import com.xiaomi.smarthome.miio.camera.face.FaceManager;
import com.xiaomi.smarthome.miio.camera.face.adapter.FaceMarkDialogAdapter;
import com.xiaomi.smarthome.miio.camera.face.model.FigureInfo;
import com.xiaomi.smarthome.miio.camera.face.util.FaceUtils;
import java.util.ArrayList;
import java.util.List;

public class FaceMarkDialog extends Dialog {
    private Button btnCancel;
    private Button btnConfirm;
    private ImageView ivFace;
    private Context mContext;
    private List<FigureInfo> mDatas;
    /* access modifiers changed from: private */
    public EditText mEditText;
    private String mFaceId;
    private FaceManager mFaceManager;
    /* access modifiers changed from: private */
    public FaceMarkDialogAdapter mFaceMarkDialogAdapter;
    /* access modifiers changed from: private */
    public OnNameSelectListener mOnNameSelectListener;
    private RecyclerView mRecyclerView;
    private String mfaceName;

    public interface OnNameSelectListener {
        void onNameSelected(String str, boolean z);
    }

    protected FaceMarkDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public FaceMarkDialog(Context context, String str, List<FigureInfo> list, FaceManager faceManager) {
        this(context);
        this.mFaceId = str;
        this.mDatas = list;
        this.mFaceManager = faceManager;
        initConfig();
    }

    public FaceMarkDialog(Context context, String str, ArrayList<FigureInfo> arrayList, FaceManager faceManager, String str2) {
        this(context);
        this.mFaceId = str;
        this.mDatas = arrayList;
        this.mFaceManager = faceManager;
        this.mfaceName = str2;
        initConfig();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View inflate = LayoutInflater.from(this.mContext.getApplicationContext()).inflate(R.layout.face_input_dialog_custom_view, (ViewGroup) null);
        this.ivFace = (ImageView) inflate.findViewById(R.id.iv_face);
        this.mEditText = (EditText) inflate.findViewById(R.id.input_text);
        if (!TextUtils.isEmpty(this.mfaceName)) {
            this.mEditText.setText(this.mfaceName);
            this.mEditText.setSelection(this.mfaceName.length());
        }
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view);
        this.btnCancel = (Button) inflate.findViewById(R.id.btn_cancel);
        this.btnConfirm = (Button) inflate.findViewById(R.id.btn_confirm);
        ImageLoader.a().a(this.mFaceManager.getFaceImg(this.mFaceId), this.ivFace);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 3));
        if (this.mDatas == null || this.mDatas.size() <= 0) {
            this.mRecyclerView.setVisibility(8);
        } else {
            this.mFaceMarkDialogAdapter = new FaceMarkDialogAdapter(this.mContext.getApplicationContext(), this.mDatas, new FaceMarkDialogAdapter.ClickCallBack() {
                public void onRecyclerClick(int i) {
                    if (FaceMarkDialog.this.mOnNameSelectListener != null) {
                        FaceMarkDialog.this.mOnNameSelectListener.onNameSelected(FaceMarkDialog.this.mFaceMarkDialogAdapter.getItem(i).figureName, true);
                    }
                    FaceMarkDialog.this.dismiss();
                }
            }, this.mFaceManager);
            this.mRecyclerView.setAdapter(this.mFaceMarkDialogAdapter);
            this.mRecyclerView.setVisibility(0);
        }
        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FaceMarkDialog.this.dismiss();
            }
        });
        this.btnConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String obj = FaceMarkDialog.this.mEditText.getText().toString();
                if (TextUtils.isEmpty(obj)) {
                    ToastUtil.a((int) R.string.add_feature_empty_tips);
                } else if (StringUtil.a((CharSequence) obj) > 10) {
                    ToastUtil.a((int) R.string.add_feature_max_tips);
                } else if (FaceUtils.containsEmoji(obj)) {
                    ToastUtil.a((int) R.string.no_emoij_tips);
                } else {
                    if (FaceMarkDialog.this.mOnNameSelectListener != null) {
                        FaceMarkDialog.this.mOnNameSelectListener.onNameSelected(obj, false);
                    }
                    FaceMarkDialog.this.dismiss();
                }
            }
        });
        this.mContext.getApplicationContext().getResources();
        inflate.setLayoutParams(new FrameLayout.LayoutParams(-1, -2, 17));
        setContentView(inflate);
        Window window = getWindow();
        if (window != null) {
            window.setGravity(17);
            window.setBackgroundDrawable(new InsetDrawable(new ColorDrawable(0), UIUtils.a(8), UIUtils.a(8), UIUtils.a(8), UIUtils.a(8)));
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.height = -2;
            window.setAttributes(attributes);
        }
        initEditText();
    }

    private void initEditText() {
        this.mEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                int a2;
                if (StringUtil.a(charSequence) > 10 && (a2 = StringUtil.a(charSequence, 10)) < charSequence.length()) {
                    FaceMarkDialog.this.mEditText.setText(charSequence.subSequence(0, a2));
                    FaceMarkDialog.this.mEditText.setSelection(a2);
                }
            }
        });
    }

    private void initConfig() {
        if (!ImageLoader.a().b()) {
            Context applicationContext = this.mContext.getApplicationContext();
            ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(applicationContext);
            builder.b(3);
            builder.a();
            builder.b((FileNameGenerator) new Md5FileNameGenerator());
            builder.f(52428800);
            builder.a(QueueProcessingType.LIFO);
            if (MijiaCameraDevice.a(this.mFaceManager.getDevice().deviceStat()).n()) {
                builder.a((ImageDownloader) new CameraImageLoaderEx(applicationContext));
            } else {
                builder.a((ImageDownloader) new CameraImageLoader(applicationContext));
            }
            builder.b();
            builder.a(new DisplayImageOptions.Builder().b(true).d(true).a(Bitmap.Config.RGB_565).d());
            ImageLoader.a().a(builder.c());
        }
    }

    public void setOnNameSelectListener(OnNameSelectListener onNameSelectListener) {
        this.mOnNameSelectListener = onNameSelectListener;
    }
}
