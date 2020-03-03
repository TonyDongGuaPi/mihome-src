package com.mi.global.shop.widget.share;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.newmodel.share.AddLoyaltyTokensResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.widget.share.ShareSystemDialog;
import com.mi.log.LogUtil;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import com.xiaomi.passport.snscorelib.internal.utils.SNSType;
import java.util.ArrayList;
import java.util.List;

public class CustomShareUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7283a = "CustomShareUtil";
    public static final String b = "com.facebook.katana";
    public static final String c = "app";
    public static final String d = "product";
    public static final int e = 100;
    /* access modifiers changed from: private */
    public Context f;
    /* access modifiers changed from: private */
    public Activity g;
    /* access modifiers changed from: private */
    public String h;
    private String i;
    private String j;
    private String k;
    /* access modifiers changed from: private */
    public String l;
    private String m;
    /* access modifiers changed from: private */
    public String n;
    private ShareDialog o;
    /* access modifiers changed from: private */
    public ArrayList<ResolveInfo> p;
    /* access modifiers changed from: private */
    public ShareSystemDialog q;

    public void a(Context context, Activity activity, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.f = context;
        this.g = activity;
        this.h = str;
        this.i = str2;
        this.j = str3;
        this.k = str4;
        this.l = str5;
        this.m = str6;
        this.n = str7;
        this.p = a(context);
    }

    public void a(final boolean z) {
        ShareSystemDialog.Builder builder = new ShareSystemDialog.Builder(this.f);
        builder.a((AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ResolveInfo resolveInfo = (ResolveInfo) CustomShareUtil.this.p.get(i);
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                if (resolveInfo.activityInfo.packageName.equals("com.facebook.katana")) {
                    CustomShareUtil.this.b(z);
                } else {
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.TEXT", CustomShareUtil.this.n + " " + CustomShareUtil.this.l);
                    CustomShareUtil.this.g.startActivityForResult(Intent.createChooser(intent, "share"), 100);
                    CustomShareUtil.this.a("Other");
                }
                if (CustomShareUtil.this.q.isShowing()) {
                    CustomShareUtil.this.q.dismiss();
                }
            }
        }).a((DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).a(this.p).a(z);
        this.q = builder.a();
        this.q.show();
    }

    public static ArrayList<ResolveInfo> a(Context context) {
        new ArrayList();
        Intent intent = new Intent("android.intent.action.SEND", (Uri) null);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("text/plain");
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        ArrayList<ResolveInfo> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < queryIntentActivities.size(); i2++) {
            arrayList.add(queryIntentActivities.get(i2));
        }
        return arrayList;
    }

    public void b(final boolean z) {
        if (this.f != null && this.g != null) {
            a(SNSType.FB);
            try {
                ShareLinkContent build = ((ShareLinkContent.Builder) new ShareLinkContent.Builder().setContentTitle(this.j).setContentDescription(this.k).setContentUrl(Uri.parse(this.l))).setImageUrl(Uri.parse(this.m)).build();
                this.o = new ShareDialog(this.g);
                this.o.registerCallback(ShopApp.m(), new FacebookCallback<Sharer.Result>() {
                    /* renamed from: a */
                    public void onSuccess(Sharer.Result result) {
                        LogUtil.b(CustomShareUtil.this.h, " shareDialog onSuccess");
                        if (z) {
                            CustomShareUtil.a(CustomShareUtil.this.f, "product");
                        }
                    }

                    public void onCancel() {
                        LogUtil.b(CustomShareUtil.this.h, "shareDialog onCancel");
                        MiToast.a(CustomShareUtil.this.f, (CharSequence) "You have cancel the share", 0);
                    }

                    public void onError(FacebookException facebookException) {
                        LogUtil.b(CustomShareUtil.this.h, "shareDialog onError");
                        MiToast.a(CustomShareUtil.this.f, (CharSequence) "Share Error", 0);
                        facebookException.printStackTrace();
                    }
                });
                try {
                    LogUtil.b(this.h, "goShareFB ShareDialog    ");
                    if (ShareDialog.canShow(ShareLinkContent.class)) {
                        this.o.show(build);
                        LogUtil.b(this.h, "goShareFB ShareDialog.canShow    ");
                        return;
                    }
                    LogUtil.b(this.h, "goShareFB ShareDialog. url ");
                    String al = ConnectionHelper.al();
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(al));
                    this.f.startActivity(intent);
                } catch (Exception e2) {
                    String str = this.h;
                    LogUtil.b(str, "FB share exception:" + e2.toString());
                    e2.printStackTrace();
                }
            } catch (Exception unused) {
                MiToast.a(this.f, (CharSequence) "Invalid url", 0);
            }
        }
    }

    private void a() {
        LogUtil.b(this.h, "TwittwerShare");
        if (this.f != null && this.g != null) {
            a("share_TW");
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(String.format("https://twitter.com/intent/tweet?text=%s&url=%s", new Object[]{ConnectionHelper.m(this.k), ConnectionHelper.m(this.l)})));
            for (ResolveInfo next : this.f.getPackageManager().queryIntentActivities(intent, 0)) {
                if (next.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
                    intent.setPackage(next.activityInfo.packageName);
                }
            }
            try {
                this.f.startActivity(intent);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if (this.g != null) {
            MiShopStatInterface.a("share", this.g.getClass().getSimpleName(), str, "");
        }
    }

    public static void a(final Context context, String str) {
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.bb()).buildUpon();
        buildUpon.appendQueryParameter("from", "app");
        buildUpon.appendQueryParameter("shareType", str);
        SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(buildUpon.toString(), AddLoyaltyTokensResult.class, new SimpleCallback<AddLoyaltyTokensResult>() {
            public void a(String str) {
            }

            public void a(AddLoyaltyTokensResult addLoyaltyTokensResult) {
                if (addLoyaltyTokensResult.data != null) {
                    CustomShareUtil.b(context, addLoyaltyTokensResult.data.msg);
                }
            }
        });
        simpleProtobufRequest.setTag(f7283a);
        RequestQueueUtil.a().add(simpleProtobufRequest);
    }

    public static void b(Context context, String str) {
        Toast makeText = Toast.makeText(context, str, 1);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }
}
