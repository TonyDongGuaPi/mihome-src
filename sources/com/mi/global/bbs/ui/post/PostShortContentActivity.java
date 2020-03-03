package com.mi.global.bbs.ui.post;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bumptech.glide.request.RequestOptions;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.LinkModel;
import com.mi.global.bbs.ui.ShortContentDetailActivity;
import com.mi.global.bbs.utils.DefaultTextWatcher;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.ImageUtil;
import com.mi.global.bbs.utils.ImeUtils;
import com.mi.global.bbs.utils.LocaleHelper;
import com.mi.global.bbs.utils.PermissionClaimer;
import com.mi.global.bbs.utils.ViewUtils;
import com.mi.global.bbs.view.CheckedImageView;
import com.mi.global.bbs.view.Editor.FontEditText;
import com.mi.global.bbs.view.Editor.FontTextView;
import com.mi.global.bbs.view.dialog.AddLinkDialog;
import com.mi.multi_image_selector.MultiImageSelector;
import com.mi.multi_image_selector.MultiImageSelectorActivity;
import com.seek.biscuit.CompressResult;
import com.seek.biscuit.OnCompressCompletedListener;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class PostShortContentActivity extends BaseActivity implements View.OnClickListener {
    static final int CONTENT_SIZE = 400;
    static final int MIN_SIZE = 5;
    private static final int REQUEST_IMAGE = 101;
    public static final String UPDATE_FOLLOWING_ACTION = "update_following_action";
    int divide_margin = 0;
    int image_width = 0;
    boolean isNeedUploadFull;
    @BindView(2131492957)
    CheckedImageView mAddImage;
    @BindView(2131492960)
    CheckedImageView mAddUrl;
    @BindView(2131493163)
    FontEditText mContent;
    @BindView(2131493246)
    LinearLayout mExtraContainer;
    View mImageCover;
    RecyclerView mImageList;
    /* access modifiers changed from: private */
    public ArrayList<String> mImagePaths;
    ImageView mLinkCloseBt;
    ImageView mLinkImage;
    /* access modifiers changed from: private */
    public String mLinkPath;
    FontTextView mLinkTitle;
    @BindView(2131493757)
    FontTextView mNumIndicate;
    View sendBt;

    public class ImageAdapter extends RecyclerView.Adapter {
        /* access modifiers changed from: private */
        public Activity mContext;
        /* access modifiers changed from: private */
        public ArrayList<String> mPath;

        public class ImageHolder_ViewBinding implements Unbinder {
            private ImageHolder target;

            @UiThread
            public ImageHolder_ViewBinding(ImageHolder imageHolder, View view) {
                this.target = imageHolder;
                imageHolder.mDel = (ImageView) Utils.findRequiredViewAsType(view, R.id.del, "field 'mDel'", ImageView.class);
                imageHolder.mPic = (ImageView) Utils.findRequiredViewAsType(view, R.id.pic, "field 'mPic'", ImageView.class);
                imageHolder.mItemFrame = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.item_frame, "field 'mItemFrame'", FrameLayout.class);
            }

            @CallSuper
            public void unbind() {
                ImageHolder imageHolder = this.target;
                if (imageHolder != null) {
                    this.target = null;
                    imageHolder.mDel = null;
                    imageHolder.mPic = null;
                    imageHolder.mItemFrame = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        public ImageAdapter(ArrayList<String> arrayList, Activity activity) {
            this.mPath = arrayList;
            this.mContext = activity;
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ImageHolder(LayoutInflater.from(this.mContext).inflate(R.layout.bbs_short_content_add_image_item, viewGroup, false));
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            ImageHolder imageHolder = (ImageHolder) viewHolder;
            imageHolder.mItemFrame.getLayoutParams().height = PostShortContentActivity.this.image_width;
            imageHolder.mItemFrame.getLayoutParams().width = PostShortContentActivity.this.image_width;
            ((ViewGroup.MarginLayoutParams) imageHolder.mItemFrame.getLayoutParams()).setMarginEnd(i != 2 ? PostShortContentActivity.this.divide_margin : 0);
            imageHolder.mItemFrame.requestLayout();
            if (this.mPath.size() >= 3) {
                bindImageItem(imageHolder, i);
            } else if (i == this.mPath.size()) {
                imageHolder.mDel.setVisibility(8);
                imageHolder.mPic.setImageResource(R.drawable.add_image_icon);
                imageHolder.mPic.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        String requestPermissionReasons = PermissionClaimer.getRequestPermissionReasons(ImageAdapter.this.mContext, R.string.str_permission_access_file, R.string.str_permission_use_camera);
                        PermissionClaimer.requestPermissionsWithReasonDialog(ImageAdapter.this.mContext, true, requestPermissionReasons, new PermissionClaimer.DefaultPermissionReqListener() {
                            public void onGranted() {
                                super.onGranted();
                                MultiImageSelector.a().c().a(3).a((ArrayList<String>) ImageAdapter.this.mPath).a(ImageAdapter.this.mContext, 101, true);
                            }
                        }, "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE");
                    }
                });
            } else {
                bindImageItem(imageHolder, i);
            }
        }

        private void bindImageItem(ImageHolder imageHolder, final int i) {
            imageHolder.mDel.setVisibility(0);
            imageHolder.mDel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ImageAdapter.this.mPath.remove(i);
                    ImageAdapter.this.notifyDataSetChanged();
                    if (ImageAdapter.this.mPath.size() == 0) {
                        PostShortContentActivity.this.checkImagesStatus();
                    }
                }
            });
            imageHolder.mPic.setOnClickListener((View.OnClickListener) null);
            ImageLoader.showImage(imageHolder.mPic, this.mPath.get(i));
        }

        public class ImageHolder extends RecyclerView.ViewHolder {
            @BindView(2131493183)
            ImageView mDel;
            @BindView(2131493490)
            FrameLayout mItemFrame;
            @BindView(2131493788)
            ImageView mPic;

            public ImageHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }
        }

        public int getItemCount() {
            if (this.mPath.size() < 3) {
                return this.mPath.size() + 1;
            }
            return 3;
        }
    }

    public static void jump(BaseActivity baseActivity) {
        if (LoginManager.getInstance().hasLogin()) {
            baseActivity.startActivity(new Intent(baseActivity, PostShortContentActivity.class));
        } else {
            baseActivity.gotoAccount();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitleAndBack(R.string.post_to_following, this.titleBackListener);
        setCustomContentView(R.layout.bbs_activity_post_short_content);
        ButterKnife.bind((Activity) this);
        this.mImagePaths = new ArrayList<>();
        addSaveButton();
        initTextChange();
        bottomToolbarSettings();
        delayRequestFocus();
        measureWidth();
    }

    private void measureWidth() {
        int i = getResources().getDisplayMetrics().widthPixels;
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.padding_normal);
        this.divide_margin = getResources().getDimensionPixelOffset(R.dimen.common_padding);
        this.image_width = ((i - (dimensionPixelOffset * 2)) - (this.divide_margin * 2)) / 3;
    }

    private void delayRequestFocus() {
        this.mContent.postDelayed(new Runnable() {
            public void run() {
                ImeUtils.showIme(PostShortContentActivity.this.mContent);
                PostShortContentActivity.this.mContent.requestFocus();
            }
        }, 600);
    }

    private void bottomToolbarSettings() {
        this.mAddImage.setOnCheckedChangeListener(new CheckedImageView.OnCheckedChangeListener() {
            public void onChecked(boolean z) {
                if (z) {
                    PostShortContentActivity.this.mAddUrl.setEnabled(false);
                    ImeUtils.hideIme(PostShortContentActivity.this.mContent);
                    PostShortContentActivity.this.pickPicture();
                }
            }
        });
        this.mAddUrl.setOnCheckedChangeListener(new CheckedImageView.OnCheckedChangeListener() {
            public void onChecked(boolean z) {
                if (z) {
                    PostShortContentActivity.this.mAddImage.setEnabled(false);
                    PostShortContentActivity.this.showAddLinkDialog();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void showAddLinkDialog() {
        AddLinkDialog addLinkDialog = new AddLinkDialog(this);
        addLinkDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                PostShortContentActivity.this.checkLinkStatus();
            }
        });
        addLinkDialog.setAddLinkCompletedListener(new AddLinkDialog.AddLinkCompletedListener() {
            public void onAddLinkComplete(String str) {
                String unused = PostShortContentActivity.this.mLinkPath = str;
            }
        });
        addLinkDialog.show();
    }

    /* access modifiers changed from: private */
    public void checkLinkStatus() {
        boolean isEmpty = TextUtils.isEmpty(this.mLinkPath);
        this.mAddUrl.setChecked(!isEmpty);
        this.mAddImage.setEnabled(isEmpty);
        if (!isEmpty) {
            addLinkLayout();
        } else {
            checkContentLength();
        }
    }

    private void addLinkLayout() {
        this.sendBt.setEnabled(true);
        final View inflate = LayoutInflater.from(this).inflate(R.layout.bbs_short_content_add_link_layout, this.mExtraContainer, true);
        this.mLinkCloseBt = (ImageView) inflate.findViewById(R.id.remove_link_bt);
        this.mLinkCloseBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.3f, 1.0f, 0.3f, 1, 0.5f, 1, 0.5f);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(scaleAnimation);
                animationSet.addAnimation(alphaAnimation);
                animationSet.setDuration(400);
                animationSet.setAnimationListener(new ViewUtils.DefaultAnimationListener() {
                    public void onAnimationEnd(Animation animation) {
                        PostShortContentActivity.this.mExtraContainer.removeAllViews();
                        String unused = PostShortContentActivity.this.mLinkPath = null;
                        PostShortContentActivity.this.checkLinkStatus();
                    }
                });
                inflate.startAnimation(animationSet);
            }
        });
        this.mLinkImage = (ImageView) inflate.findViewById(R.id.link_img);
        this.mLinkTitle = (FontTextView) inflate.findViewById(R.id.link_title);
        this.mImageCover = inflate.findViewById(R.id.link_img_cover);
        loadLinkTitleAndImage();
    }

    private void loadLinkTitleAndImage() {
        this.mLinkTitle.setText(R.string.bbs_loading);
        LinkModel.loadByUrl(this, this.mLinkPath, new LinkModel.LinkDispatcher() {
            public void onDispatch(LinkModel linkModel) {
                PostShortContentActivity.this.mLinkTitle.setText(linkModel.title);
                RequestOptions requestOptions = (RequestOptions) ((RequestOptions) ImageLoader.getDefaultOptions().c(R.drawable.bbs_link_default)).b(R.drawable.bbs_link_default);
                PostShortContentActivity.this.mImageCover.setVisibility(!TextUtils.isEmpty(linkModel.firstImagePath) ? 0 : 8);
                ImageLoader.showImage(PostShortContentActivity.this.mLinkImage, linkModel.firstImagePath, requestOptions);
            }
        });
    }

    /* access modifiers changed from: private */
    public void pickPicture() {
        PermissionClaimer.requestPermissionsWithReasonDialog(this, true, PermissionClaimer.getRequestPermissionReasons(this, R.string.str_permission_access_file, R.string.str_permission_use_camera), new PermissionClaimer.DefaultPermissionReqListener() {
            public void onGranted() {
                super.onGranted();
                MultiImageSelector.a().c().a(3).a((ArrayList<String>) PostShortContentActivity.this.mImagePaths).a((Activity) PostShortContentActivity.this, 101, true);
            }
        }, "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE");
        checkImagesStatus();
    }

    private void initTextChange() {
        this.mContent.addTextChangedListener(new DefaultTextWatcher() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                PostShortContentActivity.this.checkContentLength();
            }
        });
    }

    /* access modifiers changed from: private */
    public void checkContentLength() {
        int length = 400 - this.mContent.getText().toString().length();
        this.mNumIndicate.setText(String.valueOf(length));
        boolean z = true;
        if (length < 0 || length > 400) {
            this.sendBt.setEnabled(false);
            this.mNumIndicate.setTextColor(ResourcesCompat.getColor(getResources(), R.color.text_length_alert, getTheme()));
        } else {
            this.sendBt.setEnabled(length <= 395);
            this.mNumIndicate.setTextColor(ResourcesCompat.getColor(getResources(), R.color.text_length_normal, getTheme()));
        }
        if (!TextUtils.isEmpty(this.mLinkPath) || this.mImagePaths.size() > 0) {
            View view = this.sendBt;
            if (length < 0) {
                z = false;
            }
            view.setEnabled(z);
        }
    }

    private void addSaveButton() {
        View inflate = getLayoutInflater().inflate(R.layout.bbs_actionbar_submit, this.menuLayout, false);
        inflate.setOnClickListener(this);
        this.sendBt = inflate;
        this.menuLayout.addView(inflate);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.sendBt.getLayoutParams();
        if (Build.VERSION.SDK_INT > 16) {
            layoutParams.setMarginEnd(getResources().getDimensionPixelOffset(R.dimen.common_padding));
        } else if (LocaleHelper.isAr()) {
            layoutParams.setMargins(getResources().getDimensionPixelOffset(R.dimen.common_padding), 0, 0, 0);
        } else {
            layoutParams.setMargins(0, 0, getResources().getDimensionPixelOffset(R.dimen.common_padding), 0);
        }
        this.sendBt.requestLayout();
        this.sendBt.setEnabled(false);
    }

    public void onClick(View view) {
        String obj = this.mContent.getText().toString();
        boolean z = this.mImagePaths.size() == 0;
        if (!z || !TextUtils.isEmpty(this.mLinkPath) || !TextUtils.isEmpty(obj)) {
            showProDialog("");
            if (z || this.isNeedUploadFull) {
                postContent(obj, this.mImagePaths);
            } else {
                compressAndUpload(this.mImagePaths);
            }
        }
    }

    /* access modifiers changed from: private */
    public void postContent(String str, ArrayList<String> arrayList) {
        ApiClient.postShortContent(str, this.mLinkPath, arrayList).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<PostShortDetailBackItem>() {
            public void accept(PostShortDetailBackItem postShortDetailBackItem) throws Exception {
                PostShortContentActivity.this.dismissProDialog();
                PostShortContentActivity.this.sendUpdateFollowingBroadcast();
                if (!(postShortDetailBackItem == null || postShortDetailBackItem.data == null || TextUtils.isEmpty(postShortDetailBackItem.data.messageId))) {
                    ShortContentDetailActivity.jump(PostShortContentActivity.this, postShortDetailBackItem.data.messageId);
                }
                PostShortContentActivity.this.finish();
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                PostShortContentActivity.this.dismissProDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void sendUpdateFollowingBroadcast() {
        Intent intent = new Intent();
        intent.setAction(UPDATE_FOLLOWING_ACTION);
        sendBroadcast(intent);
    }

    private void compressAndUpload(List<String> list) {
        ImageUtil.compress((Context) this, list, (OnCompressCompletedListener) new OnCompressCompletedListener() {
            public void onCompressCompleted(CompressResult compressResult) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(compressResult.f8807a);
                arrayList.addAll(compressResult.b);
                PostShortContentActivity.this.postContent(PostShortContentActivity.this.mContent.getText().toString(), arrayList);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101) {
            if (i2 == -1 && intent != null) {
                this.mImagePaths.clear();
                ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("select_result");
                this.isNeedUploadFull = intent.getBooleanExtra(MultiImageSelectorActivity.EXTRA_RESULT_BOOLEAN, false);
                if (stringArrayListExtra != null && stringArrayListExtra.size() > 0) {
                    this.mImagePaths.addAll(stringArrayListExtra);
                }
            }
            checkImagesStatus();
        }
    }

    /* access modifiers changed from: private */
    public void checkImagesStatus() {
        boolean z = this.mImagePaths.size() == 0;
        this.mAddImage.setChecked(!z);
        this.mAddUrl.setEnabled(z);
        if (!z) {
            this.sendBt.setEnabled(true);
            addImageLayout();
            return;
        }
        checkContentLength();
        this.mExtraContainer.removeAllViews();
    }

    private void addImageLayout() {
        this.mExtraContainer.removeAllViews();
        this.mImageList = (RecyclerView) LayoutInflater.from(this).inflate(R.layout.bbs_short_content_add_imgs_layout, this.mExtraContainer, true).findViewById(R.id.recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        this.mImageList.setLayoutManager(linearLayoutManager);
        this.mImageList.setAdapter(new ImageAdapter(this.mImagePaths, this));
    }
}
