package com.xiaomi.smarthome.family;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.xiaomi.channel.gamesdk.GameServiceClient;
import com.xiaomi.channel.sdk.IShareReq;
import com.xiaomi.channel.sdk.MLImgObj;
import com.xiaomi.channel.sdk.MLShareApiFactory;
import com.xiaomi.channel.sdk.MLShareMessage;
import com.xiaomi.channel.sdk.MLShareReq;
import com.xiaomi.channel.sdk.ShareConstants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.framework.api.model.UserInfo;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.Miio;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Deprecated
public class FamilyAddMemberActivity extends BaseActivity {
    public static final int ADD_MEMBER_COMPLETE = 201;
    public static final int ADD_MEMBER_CONTINUE = 200;
    public static final int ADD_MEMBER_TYPE = 101;
    public static final String DOWNLOAD_URL = "https://home.mi.com/download";
    public static final int SEND_SMS_TYPE = 100;

    /* renamed from: a  reason: collision with root package name */
    private static final int f15627a = 11;
    private static final int b = -6;
    /* access modifiers changed from: private */
    public List<String> c = new ArrayList();
    /* access modifiers changed from: private */
    public FamilyItemData d;
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public XQProgressDialog f;
    private TextView g;
    /* access modifiers changed from: private */
    public Bitmap h;
    private View.OnClickListener i = new View.OnClickListener() {
        public void onClick(final View view) {
            if (view.isEnabled()) {
                view.setEnabled(false);
                FamilyAddMemberActivity.this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        view.setEnabled(true);
                    }
                }, 400);
                int id = view.getId();
                if (id == R.id.rl_account_container) {
                    Intent intent = new Intent();
                    FamilyAddMemberActivity.this.c.clear();
                    intent.setAction("android.intent.action.PICK");
                    intent.setData(ContactsContract.Contacts.CONTENT_URI);
                    FamilyAddMemberActivity.this.startActivityForResult(intent, 100);
                } else if (id == R.id.rl_miliao_container) {
                    if (FamilyAddMemberActivity.this.e == null) {
                        String unused = FamilyAddMemberActivity.this.e = "";
                    }
                    FamilyAddMemberActivity.this.shareMiTalk(FamilyAddMemberActivity.this, FamilyAddMemberActivity.this.getString(R.string.family_invite_msg_title), String.format(FamilyAddMemberActivity.this.getString(R.string.family_share_msg_detail), new Object[]{FamilyAddMemberActivity.this.e}));
                } else if (id == R.id.rl_wechat_container) {
                    if (FamilyAddMemberActivity.this.e == null) {
                        String unused2 = FamilyAddMemberActivity.this.e = "";
                    }
                    String string = FamilyAddMemberActivity.this.getString(R.string.family_invite_msg_title);
                    FamilyAddMemberActivity.this.shareWeChat(FamilyAddMemberActivity.this, string, String.format(FamilyAddMemberActivity.this.getString(R.string.family_share_msg_detail), new Object[]{FamilyAddMemberActivity.this.e}));
                }
            }
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.family_add_member);
        Intent intent = getIntent();
        this.d = (FamilyItemData) intent.getParcelableExtra(FamilyItemData.f15689a);
        if (this.d == null) {
            finish();
            return;
        }
        this.e = intent.getStringExtra(FamilyCreateEditActivity.FAMILY_CURRENT_USER_NAME);
        b();
        a();
        c();
    }

    private void a() {
        this.g = (TextView) findViewById(R.id.tv_account_id);
        findViewById(R.id.rl_account_container).setOnClickListener(this.i);
        findViewById(R.id.rl_miliao_container).setOnClickListener(this.i);
        findViewById(R.id.rl_wechat_container).setOnClickListener(this.i);
        this.f = new XQProgressDialog(this);
        this.f.setCancelable(false);
        this.f.setMessage(getString(R.string.family_account_search));
        this.g.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(final TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6 && (keyEvent == null || keyEvent.getKeyCode() != 66)) {
                    return false;
                }
                if (textView.getText().length() > 0) {
                    FamilyAddMemberActivity.this.mHandler.post(new Runnable() {
                        public void run() {
                            FamilyAddMemberActivity.this.b(textView.getText().toString());
                        }
                    });
                    return true;
                }
                ToastUtil.a((Context) FamilyAddMemberActivity.this, (int) R.string.family_nick_name_null, 0);
                return true;
            }
        });
    }

    private void b() {
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.family_add_member);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FamilyAddMemberActivity.this.finish();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        switch (i2) {
            case 100:
                if (i3 == -1) {
                    Cursor query = getContentResolver().query(intent.getData(), (String[]) null, (String) null, (String[]) null, (String) null);
                    if (query == null || !query.moveToFirst()) {
                        Toast.makeText(this, R.string.sh_user_not_exist, 0).show();
                    } else {
                        a(query);
                    }
                    if (query != null && !query.isClosed()) {
                        query.close();
                    }
                    if (this.c.size() > 0) {
                        this.g.setText(this.c.remove(0));
                        CharSequence text = this.g.getText();
                        if (text instanceof Spannable) {
                            Selection.setSelection((Spannable) text, text.length());
                        }
                        b(text.toString());
                        return;
                    }
                    return;
                }
                return;
            case 101:
                if (i3 == 200) {
                    this.g.setText("");
                    return;
                } else if (i3 == 201) {
                    setResult(-1);
                    finish();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.f != null) {
            this.f.dismiss();
            this.f = null;
        }
    }

    private void a(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("_id");
        if (columnIndex == -1) {
            Toast.makeText(this, R.string.sh_user_not_exist, 0).show();
            return;
        }
        String string = cursor.getString(columnIndex);
        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor query = contentResolver.query(uri, (String[]) null, "contact_id = " + string, (String[]) null, (String) null);
        if (query == null) {
            Toast.makeText(this, R.string.sh_user_not_exist, 0).show();
        } else if (query.moveToFirst()) {
            while (!query.isAfterLast()) {
                String a2 = a(query.getString(query.getColumnIndex("data1")));
                if (a2 != null) {
                    this.c.add(a2);
                }
                query.moveToNext();
            }
            if (!query.isClosed()) {
                query.close();
            }
        }
    }

    @Nullable
    private String a(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (Character.isDigit(str.charAt(i2))) {
                sb.append(str.charAt(i2));
            }
        }
        int indexOf = sb.indexOf("1");
        if (indexOf == -1) {
            return null;
        }
        String substring = sb.substring(indexOf);
        if (substring.length() != 11) {
            return null;
        }
        return substring;
    }

    /* access modifiers changed from: private */
    public void b(final String str) {
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this, R.string.please_inpt_account, 0).show();
            return;
        }
        this.f.show();
        RemoteFamilyApi.a().a((Context) this, str, (AsyncCallback<UserInfo, Error>) new AsyncCallback<UserInfo, Error>() {
            /* renamed from: a */
            public void onSuccess(UserInfo userInfo) {
                if (FamilyAddMemberActivity.this.f != null) {
                    if (userInfo != null && !TextUtils.isEmpty(userInfo.f16462a) && !userInfo.f16462a.equalsIgnoreCase("-1") && !userInfo.f16462a.equalsIgnoreCase("0")) {
                        FamilyAddMemberActivity.this.f.dismiss();
                        FamilyMemberData familyMemberData = new FamilyMemberData();
                        familyMemberData.g = userInfo.f16462a;
                        familyMemberData.h = userInfo.b;
                        familyMemberData.i = userInfo.e;
                        familyMemberData.k = "";
                        familyMemberData.j = userInfo.c;
                        Intent intent = new Intent(FamilyAddMemberActivity.this, FamilyAddMemberDetailActivity.class);
                        intent.putExtra(FamilyMemberData.f15708a, familyMemberData);
                        intent.putExtra(FamilyItemData.f15689a, FamilyAddMemberActivity.this.d);
                        FamilyAddMemberActivity.this.startActivityForResult(intent, 101);
                    } else if (FamilyAddMemberActivity.this.c(str)) {
                        Intent intent2 = new Intent(FamilyAddMemberActivity.this, FamilyAddMemberErrorActivity.class);
                        intent2.putExtra("userId", str);
                        intent2.putExtra(FamilyCreateEditActivity.FAMILY_CURRENT_USER_NAME, FamilyAddMemberActivity.this.e);
                        FamilyAddMemberActivity.this.startActivity(intent2);
                    }
                }
            }

            public void onFailure(Error error) {
                if (FamilyAddMemberActivity.this.f != null) {
                    FamilyAddMemberActivity.this.f.dismiss();
                    FamilyAddMemberActivity.this.a(error.a(), str);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(int i2, String str) {
        if (i2 == ErrorCode.ERROR_NETWORK_ERROR.getCode()) {
            Toast.makeText(this, R.string.sh_network_not_available, 0).show();
        } else if (i2 != -6) {
            Toast.makeText(this, R.string.sh_user_not_exist, 0).show();
        } else if (c(str)) {
            Intent intent = new Intent(this, FamilyAddMemberErrorActivity.class);
            intent.putExtra("userId", str);
            intent.putExtra(FamilyCreateEditActivity.FAMILY_CURRENT_USER_NAME, this.e);
            startActivity(intent);
        }
    }

    /* access modifiers changed from: private */
    public boolean c(String str) {
        return Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$").matcher(str).matches();
    }

    private boolean a(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public void shareMiTalk(Context context, String str, String str2) {
        try {
            if (!a(context, "com.xiaomi.channel")) {
                Toast.makeText(this, R.string.device_shop_share_no_miliao, 1).show();
                return;
            }
            MLShareApiFactory mLShareApiFactory = new MLShareApiFactory(this);
            mLShareApiFactory.a(context.getPackageName(), getString(R.string.app_name2));
            MLShareMessage mLShareMessage = new MLShareMessage();
            mLShareMessage.c = str;
            mLShareMessage.b = str2;
            mLShareMessage.f10070a = "https://home.mi.com/download";
            if (this.h != null) {
                mLShareMessage.d = new MLImgObj(this.h);
            }
            mLShareApiFactory.a((IShareReq) new MLShareReq(mLShareMessage, ShareConstants.M), false);
        } catch (RemoteException unused) {
            a(context, false);
        }
    }

    public void shareWeChat(Context context, String str, String str2) {
        if (!a(context, "com.tencent.mm")) {
            Toast.makeText(this, R.string.device_shop_share_no_weixin, 1).show();
            return;
        }
        IWXAPI iwxapi = SHApplication.getIWXAPI();
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.setThumbImage(this.h);
        wXMediaMessage.mediaObject = new WXWebpageObject("https://home.mi.com/download");
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = wXMediaMessage;
        req.scene = 0;
        Miio.h(FamilyCreateEditActivity.class.getSimpleName(), String.valueOf(iwxapi.sendReq(req)));
    }

    private void a(Context context, boolean z) {
        if (z) {
            Toast.makeText(context, R.string.device_shop_share_success, 0).show();
        } else {
            Toast.makeText(context, R.string.device_shop_share_failure, 0).show();
        }
    }

    static {
        GameServiceClient.c(SHApplication.getAppContext());
    }

    private void c() {
        Picasso.get().load((int) R.drawable.family_share_icon).resize(150, 150).into((Target) new Target() {
            public void onPrepareLoad(Drawable drawable) {
            }

            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                Bitmap unused = FamilyAddMemberActivity.this.h = bitmap;
            }

            public void onBitmapFailed(Exception exc, Drawable drawable) {
                Bitmap unused = FamilyAddMemberActivity.this.h = ((BitmapDrawable) drawable).getBitmap();
            }
        });
    }
}
