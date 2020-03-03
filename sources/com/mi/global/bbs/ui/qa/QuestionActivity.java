package com.mi.global.bbs.ui.qa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.CategoryGroup;
import com.mi.global.bbs.model.CategoryItem;
import com.mi.global.bbs.model.UploadResultModel;
import com.mi.global.bbs.ui.qa.QAInviteItem;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DefaultTextWatcher;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.FileUtils;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImeUtils;
import com.mi.global.bbs.utils.JsonParser;
import com.mi.global.bbs.utils.PermissionClaimer;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.utils.ViewUtils;
import com.mi.global.bbs.view.CategoryView;
import com.mi.global.bbs.view.Editor.FontEditText;
import com.mi.global.bbs.view.Editor.FontTextView;
import com.mi.global.bbs.view.Editor.RichEditor;
import com.mi.global.bbs.view.EditorToolBarItemLayout;
import com.mi.global.bbs.view.EditorToolbar;
import com.mi.multi_image_selector.MultiImageSelector;
import com.mi.multi_image_selector.MultiImageSelectorActivity;
import com.mi.util.Utils;
import com.seek.biscuit.Biscuit;
import com.seek.biscuit.CompressException;
import com.seek.biscuit.CompressListener;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class QuestionActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, RichEditor.OnTextChangeListener {
    public static final String DEFAULT_TYPE = "default_type";
    private static final String DRAFT_CONTENT = "draft_content";
    private static final String DRAFT_TITLE = "draft_title";
    private static final int ISQATREAD = 1;
    private static final int REQUEST_IMAGE = 101;
    private final int TO_INVITE_REQUEST_CODE = 1;
    private List<CategoryGroup> categoryGroups;
    String count = "";
    /* access modifiers changed from: private */
    public int[] fontColor = {R.color.font_red, R.color.font_orange, R.color.font_green, R.color.font_blue, R.color.font_purple, R.color.font_gray};
    /* access modifiers changed from: private */
    public int[] fontIcons = {R.drawable.bbs_toolbar_font_red_icon, R.drawable.bbs_toolbar_font_orange_icon, R.drawable.bbs_toolbar_font_green_icon, R.drawable.bbs_toolbar_font_blue_icon, R.drawable.bbs_toolbar_font_purple_icon, R.drawable.bbs_toolbar_font_gray_icon};
    String fromType = "";
    String ids = "";
    @BindView(2131493472)
    FontTextView inviteNotify;
    @BindView(2131493473)
    LinearLayout inviteNotifyLayout;
    List<QAInviteItem.DataBean> list = new ArrayList();
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
    @BindView(2131493613)
    RichEditor mRichEditor;
    TextView mSubmitBt;
    @BindView(2131493616)
    FontEditText mTitleEditView;
    @BindView(2131494254)
    FrameLayout mVoteContainer;
    @BindView(2131494255)
    LinearLayout mVoteItemContainer;
    int preFid = -1;

    public static void jump(BaseActivity baseActivity, String str) {
        if (LoginManager.getInstance().hasLogin()) {
            baseActivity.startActivityForResult(new Intent(baseActivity, QuestionActivity.class).putExtra("default_type", str), Constants.RequestCode.REQUEST_GO_POST);
        } else {
            baseActivity.gotoAccount();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_activity_qa_question);
        ButterKnife.bind((Object) this, (View) this.mContentLayout);
        setTitleAndBack(getString(R.string.qa_question_title), this.titleBackListener);
        this.mRichEditor.setEditorFontSize(16);
        this.mRichEditor.setTextColor(getResources().getColor(R.color.black));
        this.mRichEditor.setPadding(16, 16, 16, 16);
        this.mRichEditor.setPlaceholder(getString(R.string.qa_question_content_hint));
        this.mRichEditor.setOnTextChangeListener(this);
        this.mRichEditor.focusEditor();
        this.mEditorColorToolbar.addToolbarItem(new int[]{R.drawable.bbs_red, R.drawable.bbs_orange, R.drawable.bbs_green, R.drawable.bbs_blue, R.drawable.bbs_purple, R.drawable.bbs_gray});
        this.mEditorToolbar.addQuestionToolbarItem(new int[]{R.drawable.bbs_toolbar_select_img_icon, R.drawable.bbs_toolbar_video_icon, R.drawable.bbs_toolbar_invite_before});
        this.mEditorColorToolbar.setOnToolbarItemClickListener(new EditorToolbar.OnToolbarItemClickListener() {
            public void onItemClick(EditorToolBarItemLayout editorToolBarItemLayout, int i) {
                QuestionActivity.this.mRichEditor.setTextColor(QuestionActivity.this.getResources().getColor(QuestionActivity.this.fontColor[i]));
                QuestionActivity.this.doWonderfulAnim(editorToolBarItemLayout.getChildAt(0), i);
            }
        });
        this.mEditorToolbar.setOnToolbarItemClickListener(new EditorToolbar.OnToolbarItemClickListener() {
            public void onItemClick(EditorToolBarItemLayout editorToolBarItemLayout, int i) {
                QuestionActivity.this.handleToolbarItemClick(editorToolBarItemLayout, i);
            }
        });
        this.mCategoryGroupView.setOnItemClickListener(this);
        this.categoryGroups = JsonParser.parseList(Utils.Preference.getStringPref(this, Constants.Prefence.PREF_KEY_QA_FID_LIST, ""), new TypeToken<List<CategoryGroup>>() {
        }.getType());
        String stringExtra = getIntent().getStringExtra("default_type");
        this.fromType = stringExtra;
        this.mCategoryGroupView.setQuesionItemList(this.categoryGroups, stringExtra);
        GoogleTrackerUtil.sendRecordEvent("post", "click_post", stringExtra);
        this.mCategoryView.setText(R.string.qa_question_category_hint);
        this.mTitleEditView.addTextChangedListener(new TitleTextWatcher());
        this.inviteNotifyLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QuestionActivity.this.inviteNotifyLayout.setVisibility(8);
            }
        });
        this.inviteNotify.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bbs_question_bottom_notify_bg, getTheme()));
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
                ImeUtils.hideIme(editorToolBarItemLayout);
                pickPicture();
                return;
            case 1:
                ImeUtils.hideIme(editorToolBarItemLayout);
                showInsetUrlDialog();
                return;
            case 2:
                int currentPosition = this.mCategoryGroupView.getCurrentPosition();
                if (this.categoryGroups != null && this.categoryGroups.size() > 0) {
                    if (currentPosition >= 0) {
                        String fid = this.categoryGroups.get(currentPosition).getFid();
                        if (fid.equals(String.valueOf(this.preFid))) {
                            startActivityForResult(new Intent(this, QAInviteActivity.class).putExtra("ids", this.ids).putExtra("fid", fid).putParcelableArrayListExtra("list", (ArrayList) this.list), 1);
                            return;
                        } else {
                            startActivityForResult(new Intent(this, QAInviteActivity.class).putExtra("ids", "").putExtra("fid", fid), 1);
                            return;
                        }
                    } else {
                        toast(Integer.valueOf(R.string.qa_question_category_hint));
                        return;
                    }
                } else {
                    return;
                }
            default:
                return;
        }
    }

    private void showInsetUrlDialog() {
        DialogFactory.showInsertVideoUrlDialog(this, new DialogFactory.OnOkClickListener() {
            public void onOkClick(String str) {
                QuestionActivity.this.mRichEditor.insertLink(str, str);
            }
        });
    }

    private void pickPicture() {
        PermissionClaimer.requestPermissionsWithReasonDialog(this, true, PermissionClaimer.getRequestPermissionReasons(this, R.string.str_permission_access_file, R.string.str_permission_use_camera), new PermissionClaimer.DefaultPermissionReqListener() {
            public void onGranted() {
                super.onGranted();
                MultiImageSelector.a().b().a((Activity) QuestionActivity.this, 101, true);
            }
        }, "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    /* access modifiers changed from: private */
    public void doWonderfulAnim(View view, final int i) {
        final ImageView imageView = (ImageView) ((EditorToolBarItemLayout) this.mEditorToolbar.getChildAt(4)).getChildAt(0);
        ViewUtils.moveView(this, view, imageView, new ViewUtils.DefaultOnMoveListener() {
            public void onAnimationEnd() {
                imageView.setImageResource(QuestionActivity.this.fontIcons[i]);
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
                            QuestionActivity.this.saveDraftAndOut();
                            return;
                        case 1:
                            QuestionActivity.this.clearDraftAndOut();
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
        if (i == 101) {
            if (i2 == -1 && intent != null) {
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
        } else if (i == 1 && i2 == -1 && i2 == -1 && intent != null) {
            this.ids = intent.getStringExtra("ids");
            this.count = String.valueOf(intent.getIntExtra("count", 0));
            this.preFid = intent.getIntExtra(QAInviteActivity.QA_INVITE_PRE_FID_QARAM, -1);
            this.list = intent.getParcelableArrayListExtra("list");
            runOnUiThread(new Runnable() {
                public void run() {
                    TextView textView = (TextView) ((EditorToolBarItemLayout) QuestionActivity.this.mEditorToolbar.getChildAt(2)).getChildAt(1);
                    if (QuestionActivity.this.count.equals("0")) {
                        textView.setVisibility(8);
                        return;
                    }
                    textView.setText(QuestionActivity.this.count);
                    textView.setVisibility(0);
                }
            });
        }
    }

    private void compressAndUpload(List<String> list2) {
        Biscuit.a((Context) this).b(list2.get(0)).c(false).a((CompressListener) new CompressListener() {
            public void onSuccess(String str) {
                QuestionActivity.this.uploadImage(str);
            }

            public void onError(CompressException compressException) {
                QuestionActivity.this.uploadImage(compressException.originalPath);
            }
        }).a(FileUtils.getFolderName(FileUtils.DEFAULT_IMAGE_CACHE_PATH)).a(100).a().a();
    }

    /* access modifiers changed from: private */
    public void uploadImage(String str) {
        showProDialog("");
        ApiClient.uploadImage(str, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<UploadResultModel>() {
            public void accept(@NonNull UploadResultModel uploadResultModel) throws Exception {
                QuestionActivity.this.handleUploadResult(uploadResultModel);
                QuestionActivity.this.dismissProDialog();
                FileUtils.clearCacheImage();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                QuestionActivity.this.dismissProDialog();
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
            GoogleTrackerUtil.sendRecordEvent("question_activity", Constants.WebView.CLICK_QUESTION_SUBMIT, Constants.WebView.CLICK_QUESTION_SUBMIT);
            ImeUtils.hideIme(view);
            glideNewThread();
        }
    }

    private void glideNewThread() {
        String str;
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
            if (types == null || types.size() <= 0) {
                str = "";
            } else {
                int currentPosition2 = this.mCategoryView.getCurrentPosition();
                if (currentPosition2 == -1) {
                    toast(Integer.valueOf(R.string.please_select_category));
                    return;
                }
                str = types.get(currentPosition2).getId();
            }
            if (checkLengthValid(obj, html)) {
                showProDialog(getString(R.string.post_send));
                ApiClient.createQuestionThread(obj, html, str, fid, 1, this.ids, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<JsonObject>() {
                    public void accept(@NonNull JsonObject jsonObject) throws Exception {
                        QuestionActivity.this.onPostSuccess(jsonObject.toString());
                        QuestionActivity.this.dismissProDialog();
                    }
                }, new Consumer<Throwable>() {
                    public void accept(@NonNull Throwable th) throws Exception {
                        QuestionActivity.this.dismissProDialog();
                    }
                });
            }
        }
    }

    private boolean checkLengthValid(String str, String str2) {
        if (str.length() < 15) {
            toast(Integer.valueOf(R.string.title_too_short));
            return false;
        } else if (str2.length() >= 5) {
            return true;
        } else {
            toast(Integer.valueOf(R.string.qa_content_too_short));
            return false;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        TextView textView = (TextView) ((EditorToolBarItemLayout) this.mEditorToolbar.getChildAt(2)).getChildAt(1);
        if (this.categoryGroups.get(i).getFid().equals(String.valueOf(this.preFid))) {
            textView.setText(this.count);
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        this.ids = "";
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
            String html = QuestionActivity.this.mRichEditor.getHtml();
            QuestionActivity.this.handleSubmitButtonEnabled(QuestionActivity.this.mTitleEditView.getText().toString(), html);
        }
    }
}
