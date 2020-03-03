package com.xiaomi.smarthome.miio.camera.face.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.camera.face.FaceManager;
import com.xiaomi.smarthome.miio.camera.face.adapter.FigureFaceAdapter;
import com.xiaomi.smarthome.miio.camera.face.model.FaceIdMetaResult;
import com.xiaomi.smarthome.miio.camera.face.model.FigureInfo;
import com.xiaomi.smarthome.miio.camera.face.util.FaceUtils;
import com.xiaomi.smarthome.miio.camera.face.widget.FaceRenameDialog;
import java.util.ArrayList;
import java.util.Arrays;

public class FigureFacesManagerActivity extends FaceSelectActivityNew implements View.OnClickListener, FigureFaceAdapter.ClickCallBack {
    public static final String TAG = "FigureFacesManagerActivity";
    /* access modifiers changed from: private */
    public boolean edited = false;
    private ImageView figureCoverFace;
    /* access modifiers changed from: private */
    public FigureInfo figureInfo;
    /* access modifiers changed from: private */
    public TextView figureNameTV;
    /* access modifiers changed from: private */
    public FigureFaceAdapter mAdapter;
    public boolean mIsMultiSelectMode = false;
    private XQProgressDialog mProgressDlg;
    private XQProgressDialog mPulingDlg;
    private RecyclerView recyclerView;
    private TextView tvTitle;
    /* access modifiers changed from: private */
    public TextView tv_statues;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_figure_face_manager);
        TitleBarUtil.a(findViewById(R.id.title_bar_container));
        this.figureInfo = (FigureInfo) getIntent().getExtras().get("figureInfo");
        initSelectView();
        iniView();
        loadData();
    }

    private void iniView() {
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        this.tvTitle = (TextView) findViewById(R.id.title_bar_title);
        this.tvTitle.setText(getResources().getString(R.string.lowpower_face_manager));
        this.tv_statues = (TextView) findViewById(R.id.tv_statues);
        findViewById(R.id.select_delete).setOnClickListener(this);
        findViewById(R.id.tv_edit_mark).setOnClickListener(this);
        this.figureCoverFace = (ImageView) findViewById(R.id.figure_cover_face);
        this.figureNameTV = (TextView) findViewById(R.id.figure_name_tv);
        this.figureNameTV.setText(this.figureInfo.figureName);
        ImageLoader.a().a(mFaceManager.getFaceImg(this.figureInfo.coverFaceId), this.figureCoverFace);
        this.recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        this.mAdapter = new FigureFaceAdapter(this, this, mFaceManager);
        this.mAdapter.setHasStableIds(true);
        this.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        ((SimpleItemAnimator) this.recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.recyclerView.setAnimation((Animation) null);
        this.recyclerView.setAdapter(this.mAdapter);
    }

    /* access modifiers changed from: private */
    public void loadData() {
        mFaceManager.getFigureFaces(new FaceManager.IFaceCallback() {
            public void onSuccess(Object obj, Object obj2) {
                FigureFacesManagerActivity.this.hideLoadDlg();
                FaceIdMetaResult faceIdMetaResult = (FaceIdMetaResult) obj2;
                FigureFacesManagerActivity.this.mAdapter.setData(new ArrayList(Arrays.asList(faceIdMetaResult.faceInfoMetas)));
                FigureFacesManagerActivity.this.tv_statues.setText(String.format(FigureFacesManagerActivity.this.getString(R.string.figure_face_count_tips), new Object[]{Integer.valueOf(faceIdMetaResult.faceInfoMetas.length)}));
                FigureFacesManagerActivity.this.mAdapter.notifyDataSetChanged();
                FigureFacesManagerActivity.this.refreshSelectTitle();
            }

            public void onFailure(int i, String str) {
                FigureFacesManagerActivity.this.hideLoadDlg();
                FigureFacesManagerActivity.this.mAdapter.setData(new ArrayList());
            }
        }, this.figureInfo.figureId);
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
        return this.mAdapter.getItemCount();
    }

    /* access modifiers changed from: protected */
    public boolean isAllSelected() {
        return this.mAdapter.getSelectedCount() == this.mAdapter.getItemCount() - 1;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.select_delete) {
            deleteAlertDialog();
        } else if (id == R.id.title_bar_return) {
            onBackPressed();
        } else if (id == R.id.tv_edit_mark) {
            showRenameDialog();
        }
    }

    private void showRenameDialog() {
        final FaceRenameDialog faceRenameDialog = new FaceRenameDialog(this, this.figureInfo.figureName);
        faceRenameDialog.setOnNameInputListener(new FaceRenameDialog.OnNameInputListener() {
            public void onNameInput(final String str) {
                FaceManagerBaseActivity.mFaceManager.modifyFigure(FigureFacesManagerActivity.this.figureInfo.figureId, str, new FaceManager.IFaceCallback() {
                    public void onSuccess(Object obj, Object obj2) {
                        ToastUtil.a((int) R.string.action_success);
                        FigureFacesManagerActivity.this.figureInfo.figureName = str;
                        FigureFacesManagerActivity.this.figureInfo.figureInfo = str;
                        FigureFacesManagerActivity.this.figureNameTV.setText(FigureFacesManagerActivity.this.figureInfo.figureName);
                        boolean unused = FigureFacesManagerActivity.this.edited = true;
                        faceRenameDialog.dismiss();
                    }

                    public void onFailure(int i, String str) {
                        if (i != 400303) {
                            ToastUtil.a((int) R.string.action_fail);
                        } else {
                            faceRenameDialog.showError();
                        }
                    }
                });
            }
        });
        faceRenameDialog.show();
    }

    private void deleteAlertDialog() {
        if (this.mAdapter.getSelectedCount() == 0) {
            ToastUtil.a((int) R.string.bottom_action_tip);
        } else if (isAllSelected()) {
            deleteAllDialog();
        } else {
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
            builder.b((CharSequence) getString(R.string.face_delete_face_dialog_title));
            builder.b((int) R.string.camera_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.a((int) R.string.camera_sure, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    FigureFacesManagerActivity.this.showDeleteDlg();
                    final boolean z = true;
                    if (FigureFacesManagerActivity.this.mAdapter.getSelectedFaceIds().size() != FigureFacesManagerActivity.this.mAdapter.mData.size() - 1) {
                        z = false;
                    }
                    FaceManagerBaseActivity.mFaceManager.deleteFacesFromFigure(FigureFacesManagerActivity.this.mAdapter.getSelectedFaceIds().toArray(), FigureFacesManagerActivity.this.figureInfo.figureId, new FaceManager.IFaceCallback() {
                        public void onSuccess(Object obj, Object obj2) {
                            if (z) {
                                ToastUtil.a((int) R.string.delete_sucess);
                                FigureFacesManagerActivity.this.setResult(-1);
                                FigureFacesManagerActivity.this.finish();
                                return;
                            }
                            FigureFacesManagerActivity.this.hideDeleteDlg();
                            FigureFacesManagerActivity.this.showLoadDlg();
                            FigureFacesManagerActivity.this.loadData();
                            boolean unused = FigureFacesManagerActivity.this.edited = true;
                            ToastUtil.a((int) R.string.delete_sucess);
                        }

                        public void onFailure(int i, String str) {
                            FigureFacesManagerActivity.this.hideDeleteDlg();
                            ToastUtil.a((int) R.string.delete_failed);
                        }
                    });
                    FigureFacesManagerActivity.this.exitSelectMode();
                }
            });
            builder.d();
        }
    }

    private void deleteAllDialog() {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        builder.b((CharSequence) getString(R.string.face_delete_all_faces_message));
        builder.b((int) R.string.camera_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.a((int) R.string.camera_sure, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                FigureFacesManagerActivity.this.showDeleteDlg();
                FaceManagerBaseActivity.mFaceManager.deleteFigure(FigureFacesManagerActivity.this.figureInfo.figureId, new FaceManager.IFaceCallback() {
                    public void onSuccess(Object obj, Object obj2) {
                        FigureFacesManagerActivity.this.hideDeleteDlg();
                        FigureFacesManagerActivity.this.setResult(-1);
                        FigureFacesManagerActivity.this.finish();
                    }

                    public void onFailure(int i, String str) {
                        FigureFacesManagerActivity.this.hideDeleteDlg();
                        ToastUtil.a((int) R.string.delete_failed);
                    }
                });
                FigureFacesManagerActivity.this.exitSelectMode();
            }
        });
        builder.d();
    }

    public void onBackPressed() {
        if (!handleBackPressed()) {
            if (this.edited) {
                setResult(-1);
            }
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onExitSelectMode() {
        this.mAdapter.setSelectedMod(false);
    }

    /* access modifiers changed from: protected */
    public void onEnterSelectMode() {
        if (this.mAdapter != null) {
            this.mAdapter.setSelectedMod(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onSelectAll() {
        this.mAdapter.selectAll();
    }

    /* access modifiers changed from: protected */
    public void onUnSelectAll() {
        this.mAdapter.unSelectAll();
    }

    /* access modifiers changed from: protected */
    public String getPageTitle() {
        return getString(R.string.lowpower_face_manager);
    }

    public void onRecyclerClick(int i) {
        if (i >= this.mAdapter.mData.size() - 1) {
            exitSelectMode();
            FaceUtils.showSelectDialog(this, mFaceManager.getDeviceId());
            return;
        }
        refreshSelectTitle();
    }

    public void onRecyclerLongClick(int i) {
        enterSelectMode();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 101:
            case 102:
                if (i2 == -1) {
                    mFaceManager.addFaceToFigure(intent.getStringExtra("faceId"), this.figureInfo.figureId, new FaceManager.IFaceCallback() {
                        public void onSuccess(Object obj, Object obj2) {
                            FigureFacesManagerActivity.this.loadData();
                        }

                        public void onFailure(int i, String str) {
                            if (i == 400305) {
                                ToastUtil.a((int) R.string.face_max_tips);
                            } else {
                                ToastUtil.a((int) R.string.action_fail);
                            }
                        }
                    });
                    return;
                }
                return;
            default:
                return;
        }
    }
}
