package com.mi.global.bbs.ui.post;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.ParamsProvider;
import com.mi.global.bbs.model.CategoryGroup;
import com.mi.global.bbs.model.CategoryItem;
import com.mi.global.bbs.model.UploadResultModel;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DefaultTextWatcher;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageUtil;
import com.mi.global.bbs.utils.ImeUtils;
import com.mi.global.bbs.utils.JsonParser;
import com.mi.global.bbs.utils.PermissionClaimer;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.utils.ViewUtils;
import com.mi.global.bbs.view.CategoryView;
import com.mi.global.bbs.view.Editor.FontEditText;
import com.mi.global.bbs.view.Editor.RichEditor;
import com.mi.global.bbs.view.EditorToolBarItemLayout;
import com.mi.global.bbs.view.EditorToolbar;
import com.mi.global.bbs.view.dialog.AddPollDialog;
import com.mi.multi_image_selector.MultiImageSelector;
import com.mi.multi_image_selector.MultiImageSelectorActivity;
import com.mi.multi_image_selector.utils.FileUtils;
import com.mi.util.ResourceUtil;
import com.mi.util.Utils;
import com.seek.biscuit.CompressException;
import com.seek.biscuit.CompressListener;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;
import org.json.JSONObject;

public class PostActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, RichEditor.OnTextChangeListener {
    public static final String DEFAULT_TYPE = "default_type";
    private static final String DRAFT_CONTENT = "draft_content";
    private static final String DRAFT_TITLE = "draft_title";
    private static final int REQUEST_CAMERA = 100;
    private static final int REQUEST_IMAGE = 101;
    private List<CategoryGroup> categoryGroups;
    /* access modifiers changed from: private */
    public int[] fontColor = {R.color.font_red, R.color.font_orange, R.color.font_green, R.color.font_blue, R.color.font_purple, R.color.font_gray};
    /* access modifiers changed from: private */
    public int[] fontIcons = {R.drawable.bbs_toolbar_font_red_icon, R.drawable.bbs_toolbar_font_orange_icon, R.drawable.bbs_toolbar_font_green_icon, R.drawable.bbs_toolbar_font_blue_icon, R.drawable.bbs_toolbar_font_purple_icon, R.drawable.bbs_toolbar_font_gray_icon};
    String fromType = "";
    @BindView(2131493607)
    LinearLayout mCategoryContainer;
    @BindView(2131493608)
    CategoryView mCategoryGroupView;
    @BindView(2131493610)
    CategoryView mCategoryView;
    @BindView(2131493611)
    EditorToolbar mEditorColorToolbar;
    @BindView(2131493612)
    EditorToolbar mEditorToolbar;
    /* access modifiers changed from: private */
    public List<String> mPollOptions;
    @BindView(2131493613)
    RichEditor mRichEditor;
    TextView mSubmitBt;
    @BindView(2131493616)
    FontEditText mTitleEditView;
    private File mTmpFile;
    @BindView(2131494254)
    FrameLayout mVoteContainer;
    @BindView(2131494255)
    LinearLayout mVoteItemContainer;

    public static void jump(BaseActivity baseActivity, String str) {
        if (LoginManager.getInstance().hasLogin()) {
            baseActivity.startActivityForResult(new Intent(baseActivity, PostActivity.class).putExtra("default_type", str), Constants.RequestCode.REQUEST_GO_POST);
        } else {
            baseActivity.gotoAccount();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_activity_new_thread);
        ButterKnife.bind((Object) this, (View) this.mContentLayout);
        setTitleAndBack(getString(R.string.new_thread), this.titleBackListener);
        this.mPollOptions = new ArrayList();
        this.mRichEditor.setEditorFontSize(16);
        this.mRichEditor.setTextColor(getResources().getColor(R.color.black));
        this.mRichEditor.setPadding(16, 16, 16, 16);
        this.mRichEditor.setPlaceholder(getString(R.string.please_enter_the_content));
        this.mRichEditor.setOnTextChangeListener(this);
        this.mRichEditor.focusEditor();
        this.mEditorColorToolbar.addToolbarItem(new int[]{R.drawable.bbs_red, R.drawable.bbs_orange, R.drawable.bbs_green, R.drawable.bbs_blue, R.drawable.bbs_purple, R.drawable.bbs_gray});
        this.mEditorToolbar.addToolbarItem(new int[]{R.drawable.bbs_toolbar_undo_prev, R.drawable.bbs_toolbar_add_poll_icon, R.drawable.bbs_toolbar_select_img_icon, R.drawable.bbs_toolbar_video_icon, R.drawable.bbs_toolbar_font_gray_icon, R.drawable.bbs_toolbar_blod_normal});
        this.mEditorColorToolbar.setOnToolbarItemClickListener(new EditorToolbar.OnToolbarItemClickListener() {
            public void onItemClick(EditorToolBarItemLayout editorToolBarItemLayout, int i) {
                PostActivity.this.mRichEditor.setTextColor(PostActivity.this.getResources().getColor(PostActivity.this.fontColor[i]));
                PostActivity.this.doWonderfulAnim(editorToolBarItemLayout.getChildAt(0), i);
            }
        });
        this.mEditorToolbar.setOnToolbarItemClickListener(new EditorToolbar.OnToolbarItemClickListener() {
            public void onItemClick(EditorToolBarItemLayout editorToolBarItemLayout, int i) {
                PostActivity.this.handleToolbarItemClick(editorToolBarItemLayout, i);
            }
        });
        this.mCategoryGroupView.setOnItemClickListener(this);
        this.categoryGroups = JsonParser.parseList(Utils.Preference.getStringPref(this, Constants.Prefence.PREF_KEY_FORUMS, ""), new TypeToken<List<CategoryGroup>>() {
        }.getType());
        String stringExtra = getIntent().getStringExtra("default_type");
        this.fromType = stringExtra;
        this.mCategoryGroupView.setCategoryGroupList(this.categoryGroups, stringExtra);
        GoogleTrackerUtil.sendRecordEvent("post", "click_post", stringExtra);
        int currentPosition = this.mCategoryGroupView.getCurrentPosition();
        if (currentPosition == -1 || this.categoryGroups == null || this.categoryGroups.size() <= 0 || this.categoryGroups.get(currentPosition) == null || this.categoryGroups.get(currentPosition).getTypes() == null || this.categoryGroups.get(currentPosition).getTypes().size() <= 0) {
            this.mCategoryView.setText(R.string.select_category);
            this.mCategoryContainer.setVisibility(8);
        } else {
            this.mCategoryView.setCategoryItemList(this.categoryGroups.get(currentPosition).getTypes());
        }
        this.mTitleEditView.addTextChangedListener(new TitleTextWatcher());
        addSaveButton();
        handleDraftData();
    }

    private void addSaveButton() {
        View inflate = getLayoutInflater().inflate(R.layout.bbs_actionbar_submit, this.menuLayout, false);
        this.mSubmitBt = (TextView) inflate.findViewById(R.id.actionbar_submit);
        this.mSubmitBt.setOnClickListener(this);
        this.mSubmitBt.setEnabled(false);
        this.menuLayout.addView(inflate);
    }

    private void handleDraftData() {
        String stringPref = Utils.Preference.getStringPref(this, DRAFT_TITLE, "");
        String stringPref2 = Utils.Preference.getStringPref(this, DRAFT_CONTENT, "");
        boolean isEmpty = TextUtils.isEmpty(stringPref);
        boolean isEmpty2 = TextUtils.isEmpty(stringPref2);
        if (!isEmpty) {
            this.mTitleEditView.setText(stringPref);
        }
        if (!isEmpty2) {
            this.mRichEditor.setHtml(stringPref2);
        }
        if (!isEmpty && !isEmpty2) {
            this.mSubmitBt.setEnabled(true);
            this.mRichEditor.focusEditor();
        }
    }

    /* access modifiers changed from: private */
    public void handleToolbarItemClick(EditorToolBarItemLayout editorToolBarItemLayout, int i) {
        switch (i) {
            case 0:
                this.mRichEditor.undo();
                return;
            case 1:
                showAddPollDialog();
                return;
            case 2:
                ImeUtils.hideIme(editorToolBarItemLayout);
                pickPicture();
                return;
            case 3:
                ImeUtils.hideIme(editorToolBarItemLayout);
                showInsetUrlDialog();
                return;
            case 4:
                showOrHideColorStrip(editorToolBarItemLayout);
                return;
            case 5:
                textBold(editorToolBarItemLayout);
                return;
            default:
                return;
        }
    }

    private void showAddPollDialog() {
        AddPollDialog addPollDialog = new AddPollDialog(this, this.mPollOptions);
        addPollDialog.setOnAddCompleteListener(new AddPollDialog.OnAddCompleteListener() {
            public void onAddComplete(List<String> list) {
                PostActivity.this.mVoteItemContainer.removeAllViews();
                PostActivity.this.mPollOptions.clear();
                PostActivity.this.mPollOptions.addAll(list);
                PostActivity.this.showAndInflatePollLayout();
            }
        });
        addPollDialog.show();
    }

    private void showInsetUrlDialog() {
        DialogFactory.showInsertVideoUrlDialog(this, new DialogFactory.OnOkClickListener() {
            public void onOkClick(String str) {
                PostActivity.this.mRichEditor.insertLink(str, str);
            }
        });
    }

    private void textBold(EditorToolBarItemLayout editorToolBarItemLayout) {
        boolean isItemFocus = editorToolBarItemLayout.isItemFocus();
        ((ImageView) editorToolBarItemLayout.getChildAt(0)).setImageResource(isItemFocus ? R.drawable.bbs_toolbar_blod_normal : R.drawable.bbs_toolbar_blod_selected);
        editorToolBarItemLayout.setItemFocus(!isItemFocus);
        this.mRichEditor.setBold();
    }

    private void showOrHideColorStrip(EditorToolBarItemLayout editorToolBarItemLayout) {
        boolean isSelected = editorToolBarItemLayout.isSelected();
        editorToolBarItemLayout.setSelected(!isSelected);
        this.mEditorColorToolbar.setVisibility(isSelected ? 8 : 0);
    }

    private void pickPicture() {
        PermissionClaimer.requestPermissionsWithReasonDialog(this, true, PermissionClaimer.getRequestPermissionReasons(this, R.string.str_permission_access_file, R.string.str_permission_use_camera), new PermissionClaimer.DefaultPermissionReqListener() {
            public void onGranted() {
                super.onGranted();
                MultiImageSelector.a().b().a((Activity) PostActivity.this, 101, true);
            }
        }, "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    private void openCamera() {
        Uri uri;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) != null) {
            try {
                this.mTmpFile = FileUtils.a(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (this.mTmpFile == null || !this.mTmpFile.exists()) {
                Toast.makeText(this, com.mi.multi_image_selector.R.string.bbs_mis_error_image_not_exist, 0).show();
                return;
            }
            if (Build.VERSION.SDK_INT >= 24) {
                uri = FileProvider.getUriForFile(this, ResourceUtil.a("file_provider_authorities"), this.mTmpFile);
                intent.addFlags(1);
            } else {
                uri = Uri.fromFile(this.mTmpFile);
            }
            intent.putExtra(AgentOptions.k, uri);
            startActivityForResult(intent, 100);
            return;
        }
        Toast.makeText(this, com.mi.multi_image_selector.R.string.mis_msg_no_camera, 0).show();
    }

    /* access modifiers changed from: private */
    public void doWonderfulAnim(View view, final int i) {
        final ImageView imageView = (ImageView) ((EditorToolBarItemLayout) this.mEditorToolbar.getChildAt(4)).getChildAt(0);
        ViewUtils.moveView(this, view, imageView, new ViewUtils.DefaultOnMoveListener() {
            public void onAnimationEnd() {
                imageView.setImageResource(PostActivity.this.fontIcons[i]);
            }
        });
    }

    public void onBackPressed() {
        ImeUtils.hideIme(this.mCategoryView);
        checkIfNeedHint();
    }

    private void checkIfNeedHint() {
        if (!this.mSubmitBt.isEnabled()) {
            finish();
        } else {
            DialogFactory.showEditQuitHintDialog(this, new DialogFactory.OnItemClickListener() {
                public void onItemClick(int i) {
                    switch (i) {
                        case 0:
                            PostActivity.this.saveDraftAndOut();
                            return;
                        case 1:
                            PostActivity.this.clearDraftAndOut();
                            return;
                        default:
                            return;
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void clearDraftAndOut() {
        this.mTitleEditView.setText("");
        this.mRichEditor.setHtml("");
        Utils.Preference.removePref(this, DRAFT_TITLE);
        Utils.Preference.removePref(this, DRAFT_CONTENT);
        finish();
    }

    /* access modifiers changed from: private */
    public void saveDraftAndOut() {
        Utils.Preference.setStringPref(this, DRAFT_TITLE, this.mTitleEditView.getText().toString());
        Utils.Preference.setStringPref(this, DRAFT_CONTENT, this.mRichEditor.getHtml());
        finish();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101 && i2 == -1 && intent != null) {
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("select_result");
            boolean booleanExtra = intent.getBooleanExtra(MultiImageSelectorActivity.EXTRA_RESULT_BOOLEAN, false);
            if (stringArrayListExtra != null && stringArrayListExtra.size() > 0) {
                if (booleanExtra) {
                    uploadImage(stringArrayListExtra.get(0));
                } else {
                    compressAndUpload(stringArrayListExtra);
                }
            }
        }
    }

    private void compressAndUpload(List<String> list) {
        ImageUtil.compress((Context) this, list, (CompressListener) new CompressListener() {
            public void onSuccess(String str) {
                PostActivity.this.uploadImage(str);
            }

            public void onError(CompressException compressException) {
                PostActivity.this.uploadImage(compressException.originalPath);
            }
        });
    }

    /* access modifiers changed from: private */
    public void uploadImage(String str) {
        showProDialog("");
        ApiClient.uploadImage(str, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<UploadResultModel>() {
            public void accept(@NonNull UploadResultModel uploadResultModel) throws Exception {
                PostActivity.this.handleUploadResult(uploadResultModel);
                PostActivity.this.dismissProDialog();
                com.mi.global.bbs.utils.FileUtils.clearCacheImage();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                PostActivity.this.dismissProDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleUploadResult(UploadResultModel uploadResultModel) {
        if (uploadResultModel != null) {
            UploadResultModel.DataBean data = uploadResultModel.getData();
            if (data != null) {
                this.mRichEditor.insertImage(data.getUrl(), String.valueOf(data.getAid()));
            } else {
                toast(uploadResultModel.getErrmsg());
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.actionbar_submit) {
            GoogleTrackerUtil.sendRecordEvent("post_activity", Constants.WebView.CLICK_POST_SUBMIT, Constants.WebView.CLICK_POST_SUBMIT);
            ImeUtils.hideIme(view);
            glideNewThread();
        }
    }

    private void glideNewThread() {
        String obj = this.mTitleEditView.getText().toString();
        String html = this.mRichEditor.getHtml();
        int currentPosition = this.mCategoryGroupView.getCurrentPosition();
        if (currentPosition == -1) {
            toast(Integer.valueOf(R.string.please_select_a_forum));
        } else if (this.categoryGroups == null || this.categoryGroups.size() == 0) {
            toast(Integer.valueOf(R.string.please_select_category));
        } else {
            CategoryGroup categoryGroup = this.categoryGroups.get(currentPosition);
            String fid = categoryGroup.getFid();
            List<CategoryItem> types = categoryGroup.getTypes();
            String str = "";
            if (types != null && types.size() > 0) {
                int currentPosition2 = this.mCategoryView.getCurrentPosition();
                if (currentPosition2 == -1) {
                    toast(Integer.valueOf(R.string.please_select_category));
                    return;
                }
                str = types.get(currentPosition2).getId();
            }
            if (checkLengthValid(obj, html)) {
                showProDialog(getString(R.string.post_send));
                if (this.mPollOptions.size() > 0) {
                    ApiClient.scheduleObservable(ApiClient.getApiService().createSpecialNewThread((String[]) this.mPollOptions.toArray(new String[0]), ParamsProvider.getNewThreadContainPollParams(obj, html, str, fid))).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<JsonObject>() {
                        public void accept(@NonNull JsonObject jsonObject) throws Exception {
                            PostActivity.this.onPostSuccess(jsonObject.toString());
                            PostActivity.this.dismissProDialog();
                        }
                    }, new Consumer<Throwable>() {
                        public void accept(@NonNull Throwable th) throws Exception {
                            PostActivity.this.dismissProDialog();
                        }
                    });
                } else {
                    ApiClient.createNewThread(obj, html, str, fid, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<JsonObject>() {
                        public void accept(@NonNull JsonObject jsonObject) throws Exception {
                            PostActivity.this.onPostSuccess(jsonObject.toString());
                            PostActivity.this.dismissProDialog();
                        }
                    }, new Consumer<Throwable>() {
                        public void accept(@NonNull Throwable th) throws Exception {
                            PostActivity.this.dismissProDialog();
                        }
                    });
                }
            }
        }
    }

    private boolean checkLengthValid(String str, String str2) {
        if (str.length() < 15) {
            toast(Integer.valueOf(R.string.title_too_short));
            return false;
        } else if (str2.length() >= 15) {
            return true;
        } else {
            toast(Integer.valueOf(R.string.content_too_short));
            return false;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        List<CategoryItem> types = this.categoryGroups.get(i).getTypes();
        this.mCategoryContainer.setVisibility((types == null || types.size() <= 0) ? 8 : 0);
        this.mCategoryView.setCategoryItemList(types);
    }

    public void onTextChange(String str) {
        handleSubmitButtonEnabled(this.mTitleEditView.getText().toString(), str);
    }

    /* access modifiers changed from: private */
    public void handleSubmitButtonEnabled(String str, String str2) {
        boolean z = false;
        if (str != null && str2 != null) {
            String trim = str2.replaceAll("&nbsp;", "").replaceAll("<br>", "").trim();
            String trim2 = str.trim();
            if (this.mSubmitBt != null) {
                TextView textView = this.mSubmitBt;
                if (!TextUtils.isEmpty(trim) && !TextUtils.isEmpty(trim2)) {
                    z = true;
                }
                textView.setEnabled(z);
            }
        } else if (this.mSubmitBt != null) {
            this.mSubmitBt.setEnabled(false);
        }
    }

    /* access modifiers changed from: private */
    public void onPostSuccess(String str) {
        try {
            GoogleTrackerUtil.sendRecordEvent("post", Constants.WebView.POST_SUCCEED, this.fromType);
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            String optString = jSONObject.optString("errmsg");
            if (optJSONObject != null) {
                String optString2 = optJSONObject.optString("link");
                if (!TextUtils.isEmpty(optString2)) {
                    jumpToDetailPage(optString2);
                }
            } else if (!TextUtils.isEmpty(optString)) {
                toast(optString);
            }
        } catch (Exception unused) {
            toast(Integer.valueOf(R.string.response_data_format_error));
        }
    }

    private void jumpToDetailPage(String str) {
        Intent intent = new Intent();
        intent.putExtra("url", str);
        setResult(-1, intent);
        toast(Integer.valueOf(R.string.publish_success));
        clearDraftAndOut();
    }

    private class TitleTextWatcher extends DefaultTextWatcher {
        private TitleTextWatcher() {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            String html = PostActivity.this.mRichEditor.getHtml();
            PostActivity.this.handleSubmitButtonEnabled(PostActivity.this.mTitleEditView.getText().toString(), html);
        }
    }

    public void removeVoteLayoutAndClear(View view) {
        DialogFactory.showAlert((Context) this, R.string.are_you_sure_remove_the_poll, R.string.remove, R.string.bbs_dialog_ask_cancel, (DialogFactory.DefaultOnAlertClick) new DialogFactory.DefaultOnAlertClick() {
            public void onOkClick(View view) {
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.3f, 1.0f, 0.3f, 1, 0.5f, 1, 0.5f);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(scaleAnimation);
                animationSet.addAnimation(alphaAnimation);
                animationSet.setDuration(400);
                animationSet.setAnimationListener(new ViewUtils.DefaultAnimationListener() {
                    public void onAnimationEnd(Animation animation) {
                        PostActivity.this.mVoteContainer.setVisibility(8);
                        PostActivity.this.mVoteItemContainer.removeAllViews();
                        PostActivity.this.mPollOptions.clear();
                    }
                });
                PostActivity.this.mVoteContainer.startAnimation(animationSet);
            }
        });
    }

    public void showAndInflatePollLayout() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, getResources().getDimensionPixelOffset(R.dimen.divide_height));
        int size = this.mPollOptions.size();
        for (int i = 0; i < size; i++) {
            TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.bbs_vote_item_text, this.mVoteItemContainer, false);
            textView.setText(this.mPollOptions.get(i));
            this.mVoteItemContainer.addView(textView);
            if (i != size - 1) {
                View view = new View(this);
                view.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.vote_container_border_color, getTheme()));
                this.mVoteItemContainer.addView(view, layoutParams);
            }
        }
        this.mVoteContainer.setVisibility(0);
    }
}
