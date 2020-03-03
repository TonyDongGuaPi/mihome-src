package com.xiaomi.smarthome.miio.camera.face.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mijia.app.AppConfig;
import com.mijia.app.Event;
import com.mijia.debug.SDKLog;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.camera.face.FaceManager;
import com.xiaomi.smarthome.miio.camera.face.adapter.FaceRecyclerAdapter;
import com.xiaomi.smarthome.miio.camera.face.model.FigureInfos;
import com.xiaomi.smarthome.miio.camera.face.model.UnmarkedFaceInfo;
import com.xiaomi.smarthome.miio.camera.face.util.FaceUtils;
import java.util.ArrayList;
import org.apache.commons.cli.HelpFormatter;
import org.json.JSONException;
import org.json.JSONObject;

public class FaceManagerActivity extends FaceSelectActivity implements View.OnClickListener, FaceRecyclerAdapter.ClickCallBack {
    private static final int REQUEST_FIGURE_FACE = 0;
    private static final int REQUEST_UNMARKED_LIST = 1;
    public static final String TAG = "FaceManagerActivity";
    /* access modifiers changed from: private */
    public ImageView ivUnmarkedFace1;
    /* access modifiers changed from: private */
    public ImageView ivUnmarkedFace2;
    /* access modifiers changed from: private */
    public ImageView ivUnmarkedFace3;
    private LinearLayout layout_select_bottom;
    /* access modifiers changed from: private */
    public LinearLayout llUnmarkedFaces;
    /* access modifiers changed from: private */
    public FaceRecyclerAdapter mAdapter;
    public boolean mIsMultiSelectMode = false;
    private XQProgressDialog mProgressDlg;
    private XQProgressDialog mPulingDlg;
    public boolean mSelectAllShowed = true;
    private String[] permitArray = {"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
    /* access modifiers changed from: private */
    public RecyclerView recyclerView;
    private TextView select_delete;
    private ImageView title_bar_return;
    /* access modifiers changed from: private */
    public TextView tvFaceMarked;
    private TextView tvFaceUnmarked;
    /* access modifiers changed from: private */
    public TextView tvNoMarkedFace;
    /* access modifiers changed from: private */
    public TextView tvToMore;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_face_manager);
        TitleBarUtil.a(findViewById(R.id.title_bar_container));
        initSelectView();
        iniView();
        loadData();
        Event.k();
    }

    private void iniView() {
        this.tvNoMarkedFace = (TextView) findViewById(R.id.tv_no_unmarked_face);
        this.ivUnmarkedFace1 = (ImageView) findViewById(R.id.iv_unmarked_face1);
        this.ivUnmarkedFace2 = (ImageView) findViewById(R.id.iv_unmarked_face2);
        this.ivUnmarkedFace3 = (ImageView) findViewById(R.id.iv_unmarked_face3);
        this.llUnmarkedFaces = (LinearLayout) findViewById(R.id.ll_unmarked_faces);
        this.layout_select_bottom = (LinearLayout) findViewById(R.id.layout_select_bottom);
        this.select_delete = (TextView) findViewById(R.id.select_delete);
        this.select_delete.setOnClickListener(this);
        this.tvToMore = (TextView) findViewById(R.id.tv_to_more);
        this.tvToMore.setOnClickListener(this);
        this.tvFaceMarked = (TextView) findViewById(R.id.tv_face_marked);
        this.tvFaceUnmarked = (TextView) findViewById(R.id.tv_face_unmarked);
        this.tvFaceMarked.setText(String.format(getString(R.string.face_marked), new Object[]{0}));
        ((TextView) findViewById(R.id.title_bar_title)).setText(getResources().getString(R.string.lowpower_face_manager));
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        this.mAdapter = new FaceRecyclerAdapter(this, this, mFaceManager);
        this.mAdapter.setHasStableIds(true);
        this.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        ((SimpleItemAnimator) this.recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.recyclerView.setAnimation((Animation) null);
        this.recyclerView.setAdapter(this.mAdapter);
    }

    /* access modifiers changed from: private */
    public void loadData() {
        if (mFaceManager != null) {
            mFaceManager.getFigures(new FaceManager.IFaceCallback() {
                public void onSuccess(Object obj, Object obj2) {
                    FaceManagerActivity.this.hideLoadDlg();
                    FigureInfos figureInfos = (FigureInfos) obj2;
                    String str = FaceManagerActivity.TAG;
                    SDKLog.b(str, figureInfos.figureInfos.size() + "");
                    FaceManagerActivity.this.mAdapter.setData(figureInfos.figureInfos);
                    if (figureInfos.figureInfos.size() >= 0) {
                        FaceManagerActivity.this.tvFaceMarked.setText(String.format(FaceManagerActivity.this.getString(R.string.face_marked), new Object[]{Integer.valueOf(figureInfos.figureInfos.size())}));
                    } else {
                        FaceManagerActivity.this.mEditBtn.setVisibility(8);
                    }
                    FaceManagerActivity.this.mAdapter.notifyDataSetChanged();
                    FaceManagerActivity.this.refreshSelectTitle();
                }

                public void onFailure(int i, String str) {
                    FaceManagerActivity.this.hideLoadDlg();
                    String str2 = FaceManagerActivity.TAG;
                    SDKLog.d(str2, i + HelpFormatter.f + str);
                }
            });
            mFaceManager.getUnmarkFaces(50, false, new FaceManager.IFaceCallback() {
                public void onFailure(int i, String str) {
                }

                public void onSuccess(Object obj, Object obj2) {
                    ArrayList arrayList = (ArrayList) obj2;
                    if (arrayList.size() > 0) {
                        UnmarkedFaceInfo unmarkedFaceInfo = (UnmarkedFaceInfo) arrayList.get(0);
                        ImageLoader.a().a(FaceManagerBaseActivity.mFaceManager.getFaceImg(unmarkedFaceInfo.faceId), FaceManagerActivity.this.ivUnmarkedFace1);
                        FaceManagerActivity.this.tvNoMarkedFace.setVisibility(8);
                        FaceManagerActivity.this.llUnmarkedFaces.setVisibility(0);
                        FaceManagerActivity.this.ivUnmarkedFace1.setTag(unmarkedFaceInfo.faceId);
                        FaceManagerActivity.this.ivUnmarkedFace1.setVisibility(0);
                        FaceManagerActivity.this.ivUnmarkedFace1.setOnClickListener(new View.OnClickListener() {
                            public final void onClick(View view) {
                                FaceManagerActivity.this.onClick(view);
                            }
                        });
                    } else {
                        FaceManagerActivity.this.tvNoMarkedFace.setVisibility(0);
                        FaceManagerActivity.this.llUnmarkedFaces.setVisibility(8);
                        FaceManagerActivity.this.ivUnmarkedFace1.setVisibility(8);
                    }
                    if (arrayList.size() > 1) {
                        UnmarkedFaceInfo unmarkedFaceInfo2 = (UnmarkedFaceInfo) arrayList.get(1);
                        ImageLoader.a().a(FaceManagerBaseActivity.mFaceManager.getFaceImg(unmarkedFaceInfo2.faceId), FaceManagerActivity.this.ivUnmarkedFace2);
                        FaceManagerActivity.this.ivUnmarkedFace2.setVisibility(0);
                        FaceManagerActivity.this.ivUnmarkedFace2.setTag(unmarkedFaceInfo2.faceId);
                        FaceManagerActivity.this.ivUnmarkedFace2.setOnClickListener(new View.OnClickListener() {
                            public final void onClick(View view) {
                                FaceManagerActivity.this.onClick(view);
                            }
                        });
                    } else {
                        FaceManagerActivity.this.ivUnmarkedFace2.setVisibility(8);
                    }
                    if (arrayList.size() > 2) {
                        UnmarkedFaceInfo unmarkedFaceInfo3 = (UnmarkedFaceInfo) arrayList.get(2);
                        ImageLoader.a().a(FaceManagerBaseActivity.mFaceManager.getFaceImg(unmarkedFaceInfo3.faceId), FaceManagerActivity.this.ivUnmarkedFace3);
                        FaceManagerActivity.this.ivUnmarkedFace3.setVisibility(0);
                        FaceManagerActivity.this.ivUnmarkedFace3.setTag(unmarkedFaceInfo3.faceId);
                        FaceManagerActivity.this.ivUnmarkedFace3.setOnClickListener(new View.OnClickListener() {
                            public final void onClick(View view) {
                                FaceManagerActivity.this.onClick(view);
                            }
                        });
                        FaceManagerActivity.this.tvToMore.setVisibility(0);
                        return;
                    }
                    FaceManagerActivity.this.tvToMore.setVisibility(8);
                    FaceManagerActivity.this.ivUnmarkedFace3.setVisibility(8);
                }
            });
        }
    }

    private void showAddFaceDialog(String str) {
        FaceUtils.processMarkFace(this, str, mFaceManager, new FaceManager.IFaceCallback() {
            public void onSuccess(Object obj, Object obj2) {
                ToastUtil.a((int) R.string.action_success);
                FaceManagerActivity.this.recyclerView.postDelayed(new Runnable() {
                    public void run() {
                        FaceManagerActivity.this.loadData();
                    }
                }, 1000);
            }

            public void onFailure(int i, String str) {
                ToastUtil.a((int) R.string.action_fail);
            }
        });
    }

    public void onBackPressed() {
        if (this.mIsMultiSelectMode) {
            setMultiSelectMode(false);
        } else {
            super.onBackPressed();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivSelectAll:
                if (this.mSelectAllShowed) {
                    this.mSelectAllShowed = false;
                    this.mSelectAllBtn.setImageResource(R.drawable.icon_selected);
                    this.mAdapter.selectAll();
                    refreshSelectTitle();
                    return;
                }
                this.mSelectAllShowed = true;
                this.mSelectAllBtn.setImageResource(R.drawable.icon_unselected);
                this.mAdapter.unSelectAll();
                refreshSelectTitle();
                return;
            case R.id.ivSelectAllCancel:
                if (this.mIsMultiSelectMode) {
                    setMultiSelectMode(false);
                    return;
                }
                return;
            case R.id.iv_unmarked_face1:
            case R.id.iv_unmarked_face2:
            case R.id.iv_unmarked_face3:
                showAddFaceDialog((String) view.getTag());
                return;
            case R.id.select_delete:
                deleteAlertDialog();
                return;
            case R.id.title_bar_more:
                if (mFaceManager.getDevice().isReadOnlyShared()) {
                    ToastUtil.a((int) R.string.auth_fail);
                    return;
                } else {
                    setMultiSelectMode(true);
                    return;
                }
            case R.id.title_bar_return:
                onBackPressed();
                return;
            case R.id.tv_to_more:
                startActivityForResult(new Intent(this, FaceUnmarkedListActivity.class), 1);
                return;
            default:
                return;
        }
    }

    private void deleteAlertDialog() {
        if (this.mAdapter.getSelectedCount() == 0) {
            ToastUtil.a((int) R.string.bottom_action_tip);
            return;
        }
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        builder.a((CharSequence) getString(R.string.face_delete_dialog_title));
        builder.b((CharSequence) getString(R.string.face_delete_dialog_message));
        builder.b((int) R.string.camera_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.a((int) R.string.camera_sure, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                FaceManagerActivity.this.showDeleteDlg();
                FaceManagerBaseActivity.mFaceManager.deleteFigures(new FaceManager.IFaceCallback() {
                    public void onSuccess(Object obj, Object obj2) {
                        FaceManagerActivity.this.hideDeleteDlg();
                        FaceManagerActivity.this.showLoadDlg();
                        FaceManagerActivity.this.loadData();
                        ToastUtil.a((int) R.string.delete_sucess);
                    }

                    public void onFailure(int i, String str) {
                        FaceManagerActivity.this.hideDeleteDlg();
                        ToastUtil.a((int) R.string.delete_failed);
                    }
                }, FaceManagerActivity.this.mAdapter.getSelectedFigureIds().toArray());
                FaceManagerActivity.this.setMultiSelectMode(false);
            }
        });
        builder.d();
    }

    public void showLoadDlg() {
        if (this.mPulingDlg == null) {
            this.mPulingDlg = new XQProgressDialog(this);
            this.mPulingDlg.setMessage(getResources().getString(R.string.camera_loading));
            this.mPulingDlg.setCancelable(false);
        }
        this.mPulingDlg.show();
    }

    public void hideLoadDlg() {
        if (this.mPulingDlg != null && this.mPulingDlg.isShowing()) {
            this.mPulingDlg.dismiss();
        }
    }

    public void showDeleteDlg() {
        if (this.mProgressDlg == null) {
            this.mProgressDlg = new XQProgressDialog(this);
            this.mProgressDlg.setCancelable(false);
            this.mProgressDlg.setMessage(getResources().getString(R.string.deleting));
        }
        this.mProgressDlg.show();
    }

    public void hideDeleteDlg() {
        if (this.mProgressDlg != null && this.mProgressDlg.isShowing()) {
            this.mProgressDlg.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public int getSelectCount() {
        return this.mAdapter.getSelectedCount();
    }

    /* access modifiers changed from: protected */
    public int getDataCount() {
        return this.mAdapter.mData.size();
    }

    public void onRecyclerClick(int i) {
        if (i >= this.mAdapter.mData.size() - 1) {
            setMultiSelectMode(false);
            FaceUtils.showSelectDialog(this, mFaceManager.getDeviceId());
            return;
        }
        if (!this.mIsMultiSelectMode) {
            Intent intent = new Intent(this, FigureFacesManagerActivity.class);
            intent.putExtra("figureInfo", this.mAdapter.mData.get(i));
            startActivityForResult(intent, 0);
        }
        refreshSelectTitle();
    }

    public void onRecyclerLongClick(int i) {
        if (!this.mIsMultiSelectMode) {
            setMultiSelectMode(true);
        }
        refreshSelectTitle();
    }

    public void setMultiSelectMode(boolean z) {
        this.mIsMultiSelectMode = z;
        if (z) {
            this.mEditBtn.setVisibility(8);
            this.layout_select_bottom.setVisibility(0);
        } else {
            this.layout_select_bottom.setVisibility(8);
        }
        this.mAdapter.setSelectedMod(z);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 0:
            case 1:
                if (i2 == -1) {
                    showLoadDlg();
                    loadData();
                    return;
                }
                return;
            case 101:
            case 102:
                if (i2 == -1) {
                    FaceUtils.processMarkFace(getContext(), intent.getStringExtra("faceId"), mFaceManager, new FaceManager.IFaceCallback() {
                        public void onSuccess(Object obj, Object obj2) {
                            ToastUtil.a((int) R.string.action_success);
                            FaceManagerActivity.this.recyclerView.postDelayed(new Runnable() {
                                public void run() {
                                    FaceManagerActivity.this.loadData();
                                }
                            }, 1000);
                        }

                        public void onFailure(int i, String str) {
                            ToastUtil.a((int) R.string.action_fail);
                        }
                    });
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void uploadFileAndMarkFace(String str) {
        String str2 = TAG;
        LogUtil.a(str2, "uploadFileAndMarkFace " + str);
        mFaceManager.uploadImageFile(this, str, new FaceManager.IFaceCallback() {
            public void onSuccess(Object obj, Object obj2) {
                try {
                    FaceUtils.processMarkFace(FaceManagerActivity.this.getContext(), ((JSONObject) ((JSONObject) obj).getJSONObject("data").getJSONArray("faceInfoMetas").get(0)).getString("faceId"), FaceManagerBaseActivity.mFaceManager, new FaceManager.IFaceCallback() {
                        public void onSuccess(Object obj, Object obj2) {
                            ToastUtil.a((int) R.string.action_success);
                            FaceManagerActivity.this.recyclerView.postDelayed(new Runnable() {
                                public void run() {
                                    FaceManagerActivity.this.loadData();
                                }
                            }, 1000);
                        }

                        public void onFailure(int i, String str) {
                            ToastUtil.a((int) R.string.action_fail);
                        }
                    });
                } catch (JSONException e) {
                    ToastUtil.a((int) R.string.action_fail);
                    e.printStackTrace();
                }
            }

            public void onFailure(int i, String str) {
                ToastUtil.a((int) R.string.action_fail);
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mAdapter != null) {
            this.mAdapter.destroyDisplayImageOptions();
        }
        Event.l();
    }

    class ItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public ItemDecoration() {
            this.space = 0;
            this.space = (int) (AppConfig.d * 2.0f);
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            if (recyclerView.getChildAdapterPosition(view) == FaceManagerActivity.this.mAdapter.getItemCount() - 1) {
                rect.bottom = 50;
            }
        }
    }
}
