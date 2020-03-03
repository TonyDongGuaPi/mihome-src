package com.mi.global.bbs.ui.column;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.ColumnDetailAdapter;
import com.mi.global.bbs.adapter.OnFollowListener;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.LoadMoreManager;
import com.mi.global.bbs.inter.OnTakePhotoListener;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.ColumnArticleModel;
import com.mi.global.bbs.model.ColumnDetailData;
import com.mi.global.bbs.model.ColumnDetailModel;
import com.mi.global.bbs.model.ColumnHomeModel;
import com.mi.global.bbs.model.UploadResultModel;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.FileUtils;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.utils.OnShareListener;
import com.mi.global.bbs.utils.PermissionClaimer;
import com.mi.global.bbs.utils.StatusBarClickListener;
import com.mi.global.bbs.view.SettingsItemContainerView;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.mi.global.bbs.view.dialog.ShareDialog;
import com.mi.multi_image_selector.MultiImageSelector;
import com.mi.util.Coder;
import com.mi.util.ResourceUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.xiaomi.smarthome.library.common.widget.crop.CropImageActivity;
import com.xiaomi.youpin.share.ShareObject;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;

public class ColumnDetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, OnFollowListener, OnTakePhotoListener, OnShareListener {
    private static final String COLUMN_BG_NAME = "column_bg";
    private static final String PLATE_ID = "columnid";
    private static final int REQUEST_CROP = 27;
    private static final int REQUEST_IMAGE = 17;
    /* access modifiers changed from: private */
    public ColumnDetailAdapter adapter;
    private String columnId = null;
    @BindView(2131493155)
    ObservableRecyclerView commonRecycleView;
    @BindView(2131493156)
    SwipeRefreshLayout commonRefreshView;
    List<ColumnDetailData> detailDatas;
    /* access modifiers changed from: private */
    public boolean hasMore = false;
    private Uri imageUri;
    /* access modifiers changed from: private */
    public WrapContentLinearLayoutManager layoutManager;
    private LoadMoreManager loadMoreManager;
    /* access modifiers changed from: private */
    public int page = 1;
    private String pageName;
    private int pageSize = 10;
    ImageView plateMore;
    /* access modifiers changed from: private */
    public boolean pushOpen = false;

    static /* synthetic */ int access$908(ColumnDetailActivity columnDetailActivity) {
        int i = columnDetailActivity.page;
        columnDetailActivity.page = i + 1;
        return i;
    }

    static /* synthetic */ int access$910(ColumnDetailActivity columnDetailActivity) {
        int i = columnDetailActivity.page;
        columnDetailActivity.page = i - 1;
        return i;
    }

    public static void jumpWithId(Context context, String str) {
        context.startActivity(new Intent(context, ColumnDetailActivity.class).putExtra(PLATE_ID, str));
    }

    public static void jumpWithUrl(Context context, String str) {
        try {
            context.startActivity(new Intent(context, ColumnDetailActivity.class).putExtra(PLATE_ID, str.substring(str.indexOf("column_id=") + "column_id=".length(), str.length())));
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.contentNeedMargin = false;
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_common_refresh_layout);
        String imageCacheFileName = FileUtils.getImageCacheFileName(COLUMN_BG_NAME);
        if (!TextUtils.isEmpty(imageCacheFileName)) {
            this.imageUri = Uri.fromFile(new File(imageCacheFileName));
        }
        this.pageName = Constants.PageFragment.PAGE_COLUMN_DETAIL;
        ButterKnife.bind((Activity) this);
        this.detailDatas = new ArrayList();
        setTitleAndBack("", this.titleBackListener);
        this.loadMoreManager = new LoadMoreManager();
        this.commonRefreshView.setOnRefreshListener(this);
        this.commonRefreshView.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        this.layoutManager = new WrapContentLinearLayoutManager(this);
        this.layoutManager.setOrientation(1);
        this.commonRecycleView.setLayoutManager(this.layoutManager);
        this.adapter = new ColumnDetailAdapter(this, this.loadMoreManager, this.pageName);
        this.adapter.setOnFollowListener(this);
        this.commonRecycleView.setAdapter(this.adapter);
        this.mToolBarContainer.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.main_title_bar_bg)));
        this.mToolBarContainer.getBackground().setAlpha(0);
        this.mToolBarDivider.setVisibility(4);
        this.mTitleView.setVisibility(4);
        this.mUpImageView.setImageResource(R.drawable.bbs_arrow_up_white);
        addActionItem();
        this.plateMore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogFactory.showColumnPostSetting(ColumnDetailActivity.this, ColumnDetailActivity.this.pushOpen, new SettingsItemContainerView.OnItemCheckedChangedListener() {
                    public void onItemCheckedChangedListener(int i, boolean z) {
                        ColumnDetailActivity.this.changeColumnPush(z);
                    }
                });
            }
        });
        this.commonRecycleView.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            public void onDownMotionEvent() {
            }

            public void onUpOrCancelMotionEvent(ScrollState scrollState) {
            }

            public void onScrollChanged(int i, boolean z, boolean z2) {
                int i2 = 0;
                if (i <= 0) {
                    ColumnDetailActivity.this.mToolBarContainer.getBackground().setAlpha(0);
                    return;
                }
                int a2 = Coder.a(70.0f);
                int abs = Math.abs(i);
                if (abs <= a2) {
                    ColumnDetailActivity.this.mToolBarContainer.getBackground().setAlpha((int) ((((float) abs) / ((float) a2)) * 255.0f));
                } else {
                    ColumnDetailActivity.this.mToolBarContainer.getBackground().setAlpha(255);
                }
                boolean z3 = abs >= a2;
                ColumnDetailActivity.this.mTitleView.setVisibility(z3 ? 0 : 4);
                ColumnDetailActivity.this.mUpImageView.setImageResource(z3 ? R.drawable.bbs_arrow_up_black : R.drawable.bbs_arrow_up_white);
                ColumnDetailActivity.this.plateMore.setImageResource(z3 ? R.drawable.bbs_ic_action_more : R.drawable.bbs_ic_action_more_white);
                View access$700 = ColumnDetailActivity.this.mToolBarDivider;
                if (!z3) {
                    i2 = 4;
                }
                access$700.setVisibility(i2);
            }
        });
        this.commonRecycleView.addOnScrollListener(new InfiniteScrollListener(this.layoutManager, this.loadMoreManager) {
            public void onLoadMore() {
                if (ColumnDetailActivity.this.hasMore) {
                    ColumnDetailActivity.access$908(ColumnDetailActivity.this);
                    ColumnDetailActivity.this.getArticlesData();
                }
            }
        });
        this.adapter.setOnShareListener(this);
        this.adapter.setOnTakePhotoListener(this);
        setPreferData();
        this.columnId = getIntent().getStringExtra(PLATE_ID);
        getData();
    }

    private void addActionItem() {
        LayoutInflater.from(this).inflate(R.layout.bbs_column_detail_more, this.menuLayout, true);
        this.plateMore = (ImageView) this.menuLayout.findViewById(R.id.column_detail_more);
    }

    private void setPreferData() {
        this.detailDatas.clear();
        ColumnDetailData columnDetailData = new ColumnDetailData();
        columnDetailData.setColumnInfo(new ColumnDetailModel.DataBean.ColumnInfo());
        this.detailDatas.add(columnDetailData);
        this.adapter.clear();
        this.adapter.setData(this.detailDatas);
    }

    private void getData() {
        if (!TextUtils.isEmpty(this.columnId)) {
            ApiClient.getColumnDetail(this.columnId, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<ColumnDetailModel>() {
                public void accept(@NonNull ColumnDetailModel columnDetailModel) throws Exception {
                    ColumnDetailActivity.this.dismissProgress();
                    if (columnDetailModel != null && columnDetailModel.getErrno() == 0 && columnDetailModel.data != null && columnDetailModel.data.columnInfo != null) {
                        boolean z = true;
                        if (ColumnDetailActivity.this.page == 1) {
                            ColumnDetailActivity.this.detailDatas.clear();
                            ColumnDetailActivity.this.adapter.clear();
                            ColumnDetailActivity.this.adapter.notifyDataSetChanged();
                        }
                        ColumnDetailActivity columnDetailActivity = ColumnDetailActivity.this;
                        if (columnDetailModel.data.push != 1) {
                            z = false;
                        }
                        boolean unused = columnDetailActivity.pushOpen = z;
                        ColumnDetailActivity.this.mTitleView.setText(columnDetailModel.data.columnInfo.name);
                        ColumnDetailData columnDetailData = new ColumnDetailData();
                        columnDetailData.setColumnInfo(columnDetailModel.data.columnInfo);
                        columnDetailData.subcribStatus = columnDetailModel.data.subscribeStatus;
                        if (columnDetailData.subcribStatus) {
                            ColumnDetailActivity.this.plateMore.setVisibility(0);
                        } else {
                            ColumnDetailActivity.this.plateMore.setVisibility(8);
                        }
                        columnDetailData.setFollowers(columnDetailModel.data.subscribeUsers);
                        ColumnDetailActivity.this.detailDatas.add(columnDetailData);
                        ColumnDetailActivity.this.adapter.setData(ColumnDetailActivity.this.detailDatas);
                        ColumnDetailActivity.this.getArticlesData();
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    if (ColumnDetailActivity.this.page > 1) {
                        ColumnDetailActivity.access$910(ColumnDetailActivity.this);
                    }
                    ColumnDetailActivity.this.dismissProgress();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void getArticlesData() {
        if (this.page > 0) {
            this.loadMoreManager.loadStarted();
        }
        ApiClient.getColumnArticles(this.page, this.pageSize, this.columnId, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<ColumnArticleModel>() {
            public void accept(@NonNull ColumnArticleModel columnArticleModel) throws Exception {
                ColumnDetailActivity.this.dismissProgress();
                if (columnArticleModel == null || columnArticleModel.data == null || columnArticleModel.data.list == null || columnArticleModel.data.list.size() <= 0) {
                    boolean unused = ColumnDetailActivity.this.hasMore = false;
                    return;
                }
                if (ColumnDetailActivity.this.page == 1) {
                    ColumnDetailActivity.this.adapter.clear();
                } else {
                    ColumnDetailActivity.this.detailDatas.clear();
                }
                for (ColumnHomeModel.DataBean.ColumnArticle columnArticle : columnArticleModel.data.list) {
                    ColumnDetailData columnDetailData = new ColumnDetailData();
                    columnDetailData.setColumnArticle(columnArticle);
                    ColumnDetailActivity.this.detailDatas.add(columnDetailData);
                }
                ColumnDetailActivity.this.adapter.setData(ColumnDetailActivity.this.detailDatas);
                if (columnArticleModel.data.list.size() > 0) {
                    boolean unused2 = ColumnDetailActivity.this.hasMore = true;
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                ColumnDetailActivity.this.dismissProgress();
                boolean unused = ColumnDetailActivity.this.hasMore = false;
            }
        });
    }

    private void adjustStatusBar() {
        this.mTitleView.setOnClickListener(new StatusBarClickListener(new StatusBarClickListener.OnDoubleClickListener() {
            public void onDoubleClick() {
                if (ColumnDetailActivity.this.layoutManager.findLastVisibleItemPosition() > 50) {
                    ColumnDetailActivity.this.layoutManager.scrollToPosition(50);
                }
                ColumnDetailActivity.this.commonRecycleView.smoothScrollToPosition(0);
            }
        }));
        this.mToolBarContainer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
    }

    /* access modifiers changed from: private */
    public void dismissProgress() {
        this.loadMoreManager.loadFinished();
        if (this.commonRefreshView != null) {
            this.commonRefreshView.setRefreshing(false);
        }
    }

    public void onRefresh() {
        this.page = 1;
        this.hasMore = false;
        getData();
    }

    private void share(String str, String str2) {
        new ShareDialog(this).setShareTitle(str).setShareUrl(str2).setCallbackManager(this.callbackManager).show();
    }

    public void onShare(String str, String str2) {
        share(str, str2);
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }

    public void onTakePhoto(String str) {
        pickPicture();
    }

    private void pickPicture() {
        PermissionClaimer.requestPermissionsWithReasonDialog(this, true, PermissionClaimer.getRequestPermissionReasons(this, R.string.str_permission_access_file, R.string.str_permission_use_camera), new PermissionClaimer.DefaultPermissionReqListener() {
            public void onGranted() {
                super.onGranted();
                MultiImageSelector.a().b().a((Activity) ColumnDetailActivity.this, 17, true);
            }
        }, "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        ArrayList<String> stringArrayListExtra;
        super.onActivityResult(i, i2, intent);
        if (i == 17 && i2 == -1 && (stringArrayListExtra = intent.getStringArrayListExtra("select_result")) != null && stringArrayListExtra.size() > 0 && this.imageUri != null) {
            startCropIntent(stringArrayListExtra.get(0));
        }
        if (i == 27 && i2 == -1 && this.imageUri != null) {
            uploadImage(this.imageUri.getPath());
        }
    }

    private void startCropIntent(String str) {
        Uri uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(this, ResourceUtil.a("file_provider_authorities"), new File(str));
            intent.addFlags(1);
        } else {
            uri = Uri.fromFile(new File(str));
        }
        intent.setDataAndType(uri, ShareObject.d);
        intent.putExtra("crop", "true");
        intent.putExtra(CropImageActivity.ASPECT_X, 16);
        intent.putExtra(CropImageActivity.ASPECT_Y, 10);
        intent.putExtra(CropImageActivity.OUTPUT_X, 1080);
        intent.putExtra(CropImageActivity.OUTPUT_Y, 675);
        intent.putExtra("scale", true);
        intent.putExtra(AgentOptions.k, this.imageUri);
        intent.putExtra(CropImageActivity.RETURN_DATA, false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(CropImageActivity.SCALE_UP_IF_NEEDED, true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 27);
        } else {
            uploadImage(str);
        }
    }

    private void uploadImage(String str) {
        showProDialog("");
        ApiClient.uploadImage(str, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<UploadResultModel>() {
            public void accept(@NonNull UploadResultModel uploadResultModel) throws Exception {
                ColumnDetailActivity.this.handleUploadResult(uploadResultModel);
                ColumnDetailActivity.this.dismissProDialog();
                FileUtils.clearCacheImage();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                ColumnDetailActivity.this.dismissProDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleUploadResult(UploadResultModel uploadResultModel) {
        UploadResultModel.DataBean data;
        if (uploadResultModel != null && (data = uploadResultModel.getData()) != null && !TextUtils.isEmpty(data.getUrl())) {
            ApiClient.changeColumnBg(this.columnId, data.getUrl(), bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    if (baseResult.getErrno() == 0) {
                        ColumnDetailActivity.this.onRefresh();
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void changeColumnPush(final boolean z) {
        ApiClient.changeColumnPush(this.columnId, z ? 1 : 0, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
            public void accept(@NonNull BaseResult baseResult) throws Exception {
                if (baseResult.getErrno() == 0) {
                    boolean unused = ColumnDetailActivity.this.pushOpen = z;
                } else {
                    ColumnDetailActivity.this.toast(baseResult.getErrmsg());
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
            }
        });
    }

    public void onFollow(final int i, final String str, boolean z) {
        if (!z) {
            showProDialog(getString(R.string.following_ing));
            ApiClient.followColumn(str, 1, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    if (baseResult.getErrno() == 0) {
                        ColumnDetailActivity.this.toast(Integer.valueOf(R.string.follow_success));
                        if (ColumnDetailActivity.this.adapter.getThreadlist() != null && i < ColumnDetailActivity.this.adapter.getThreadlist().size()) {
                            ColumnDetailActivity.this.adapter.getThreadlist().get(i).subcribStatus = true;
                            ColumnDetailActivity.this.adapter.notifyDataSetChanged();
                        }
                    } else {
                        ColumnDetailActivity.this.toast(baseResult.getErrmsg());
                    }
                    ColumnDetailActivity.this.dismissProDialog();
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    ColumnDetailActivity.this.dismissProDialog();
                }
            });
            return;
        }
        DialogFactory.showAlert((Context) this, getString(R.string.unfollow_column_tip), getString(R.string.bbs_yes), getString(R.string.bbs_cancel), (DialogFactory.DefaultOnAlertClick) new DialogFactory.DefaultOnAlertClick() {
            public void onOkClick(View view) {
                ColumnDetailActivity.this.showProDialog(ColumnDetailActivity.this.getString(R.string.unfollowing_ing));
                ApiClient.followColumn(str, 0, ColumnDetailActivity.this.bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                    public void accept(@NonNull BaseResult baseResult) throws Exception {
                        if (baseResult.getErrno() != 0) {
                            ColumnDetailActivity.this.toast(baseResult.getErrmsg());
                        } else if (ColumnDetailActivity.this.adapter.getThreadlist() != null && i < ColumnDetailActivity.this.adapter.getThreadlist().size()) {
                            ColumnDetailActivity.this.adapter.getThreadlist().get(i).subcribStatus = false;
                            ColumnDetailActivity.this.adapter.notifyDataSetChanged();
                        }
                        ColumnDetailActivity.this.dismissProDialog();
                    }
                }, new Consumer<Throwable>() {
                    public void accept(@NonNull Throwable th) throws Exception {
                        ColumnDetailActivity.this.dismissProDialog();
                    }
                });
            }
        });
    }
}
