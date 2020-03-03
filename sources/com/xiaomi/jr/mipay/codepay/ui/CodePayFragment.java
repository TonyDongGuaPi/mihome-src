package com.xiaomi.jr.mipay.codepay.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.miui.supportlite.app.AlertDialog;
import com.miui.supportlite.app.ImmersionMenu;
import com.squareup.picasso.Picasso;
import com.xiaomi.jr.common.app.ActivityChecker;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.UrlUtils;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.deeplink.DeeplinkConstants;
import com.xiaomi.jr.deeplink.DeeplinkUtils;
import com.xiaomi.jr.mipay.codepay.CodePayManager;
import com.xiaomi.jr.mipay.codepay.R;
import com.xiaomi.jr.mipay.codepay.component.CommonErrorView;
import com.xiaomi.jr.mipay.codepay.data.CodePayConfirmParams;
import com.xiaomi.jr.mipay.codepay.data.PayType;
import com.xiaomi.jr.mipay.codepay.presenter.CodePayContract;
import com.xiaomi.jr.mipay.codepay.presenter.CodePayPresenter;
import com.xiaomi.jr.mipay.codepay.presenter.Presenter;
import com.xiaomi.jr.mipay.codepay.util.CodeGenUtils;
import com.xiaomi.jr.mipay.codepay.util.CodePayConstants;
import com.xiaomi.jr.mipay.codepay.util.CodePayUtils;
import com.xiaomi.jr.mipay.codepay.util.MifiConstants;
import com.xiaomi.jr.mipay.common.http.HostManager;
import com.xiaomi.jr.mipay.pay.verifier.PayPassVerifier;
import com.xiaomi.jr.mipay.pay.verifier.model.VerifyResult;
import java.util.ArrayList;
import java.util.List;

public class CodePayFragment extends BaseFragment implements CodePayContract.View {

    /* renamed from: a  reason: collision with root package name */
    public static String f10924a = "mipay.codePay";
    private static final String b = "com.android.launcher.action.INSTALL_SHORTCUT";
    private static final String c = "close_code_pay";
    private static final String d = "create_desktop_shortcut";
    private View e;
    private ImageView f;
    private TextView g;
    private ImageView h;
    private View i;
    private ImageView j;
    private View k;
    private ImageView l;
    private TextView m;
    private CommonErrorView n;
    private View o;
    private View p;
    private ImageView q;
    private Animation r;
    private boolean s;
    /* access modifiers changed from: private */
    public CodePayPresenter t = new CodePayPresenter(this);
    private boolean u;
    private PayPassVerifier.VerifyPasswordListener v;

    private static class MenuItemData {

        /* renamed from: a  reason: collision with root package name */
        public String f10928a;
        public String b;

        public MenuItemData(String str, String str2) {
            this.f10928a = str;
            this.b = str2;
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.jr_mipay_code_pay_fragment, (ViewGroup) null);
        this.e = inflate.findViewById(R.id.content);
        this.g = (TextView) inflate.findViewById(R.id.code);
        this.f = (ImageView) inflate.findViewById(R.id.barCode);
        this.h = (ImageView) inflate.findViewById(R.id.qrCode);
        this.i = inflate.findViewById(R.id.refresh);
        this.j = (ImageView) inflate.findViewById(R.id.refresh_icon);
        this.k = inflate.findViewById(R.id.type_container);
        this.m = (TextView) inflate.findViewById(R.id.pay_type_details);
        this.l = (ImageView) inflate.findViewById(R.id.type_icon);
        this.n = (CommonErrorView) inflate.findViewById(R.id.empty);
        this.o = inflate.findViewById(R.id.code_container);
        this.p = inflate.findViewById(R.id.open_term_pay_container);
        this.q = (ImageView) inflate.findViewById(R.id.menu);
        this.k.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CodePayFragment.this.d(view);
            }
        });
        this.i.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CodePayFragment.this.c(view);
            }
        });
        this.p.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CodePayFragment.this.b(view);
            }
        });
        inflate.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CodePayFragment.this.a(view);
            }
        });
        return inflate;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(View view) {
        k();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        this.t.a(true);
        if (this.r == null) {
            this.r = AnimationUtils.loadAnimation(getContext(), R.anim.jr_mipay_rotation_anim);
        }
        this.j.startAnimation(this.r);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        FragmentActivity activity = getActivity();
        String string = getString(R.string.jr_mipay_open_term_pay_title);
        DeeplinkUtils.openDeeplink(activity, string, MifiConstants.f10936a + "installment/?" + CodePayManager.d());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        getActivity().finish();
    }

    public Presenter c() {
        return this.t;
    }

    public void a(boolean z) {
        this.e.setVisibility(z ? 0 : 8);
    }

    public void a(PayType payType) {
        this.s = true;
        a(payType.mAuthCode);
        b(payType.mAuthCode);
        c(payType.mAuthCode);
        b(payType);
        this.j.setAnimation((Animation) null);
    }

    public void onDetach() {
        MifiLog.d("TestPay", "fragment detach");
        super.onDetach();
    }

    public void b(boolean z) {
        this.p.setVisibility(z ? 0 : 8);
    }

    public void a(int i2, String str, Throwable th) {
        if (this.s) {
            this.j.setAnimation((Animation) null);
            Toast.makeText(getActivity(), String.format("%s[%d]", new Object[]{str, Integer.valueOf(i2)}), 1).show();
            this.u = false;
            return;
        }
        this.o.setVisibility(8);
        this.n.setVisibility(0);
        this.n.showError(String.format("%s[%d]", new Object[]{str, Integer.valueOf(i2)}));
        this.u = true;
    }

    public void a(int i2, boolean z) {
        if (z) {
            this.o.setVisibility(8);
            this.n.setVisibility(0);
            this.n.startProgress();
        } else if (!this.u) {
            this.o.setVisibility(0);
            this.n.setVisibility(8);
            this.n.stopProgress();
        }
    }

    public void a() {
        FragmentActivity activity = getActivity();
        AnonymousClass1 r2 = new PayPassVerifier.VerifyPasswordListener() {
            public void a() {
                a(0);
            }

            public void a(VerifyResult verifyResult) {
                a(-1);
            }

            private void a(int i) {
                CodePayFragment.this.t.a(i);
            }
        };
        this.v = r2;
        PayPassVerifier.a(activity, PayPassVerifier.k, r2);
        activity.overridePendingTransition(0, 0);
    }

    public void a(String str, CodePayConfirmParams codePayConfirmParams) {
        Bundle bundle = new Bundle();
        bundle.putString("processId", str);
        bundle.putSerializable("params", codePayConfirmParams);
        CodePayUtils.c(this, CodePayConfirmFragment.f10922a, bundle);
    }

    public void c(boolean z) {
        if (z) {
            a(false);
            Utils.a((DialogFragment) new AlertDialog.Builder(getActivity()).a(R.string.jr_mipay_bind_card_title).b(R.string.jr_mipay_no_card_alert).a(R.string.jr_mipay_bind_card_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    CodePayFragment.this.c(dialogInterface, i);
                }
            }).b(17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    CodePayFragment.this.b(dialogInterface, i);
                }
            }).a(false).a(), getFragmentManager(), "bind_card");
            return;
        }
        h();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(DialogInterface dialogInterface, int i2) {
        h();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(DialogInterface dialogInterface, int i2) {
        b();
    }

    private void h() {
        Intent intent = new Intent(getActivity(), BindCardActivity.class);
        intent.putExtra("title", getString(R.string.jr_mipay_bind_card_title));
        intent.putExtra("url", HostManager.a("bindCard/bindCardPage"));
        intent.putExtra(CodePayWebActivity.KEY_START_LOADING, false);
        Utils.a((Context) getActivity(), intent);
        startActivityForResult(intent, 1004);
    }

    public void b() {
        FragmentActivity activity = getActivity();
        if (ActivityChecker.a((Activity) activity)) {
            activity.finish();
        }
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            StringBuilder sb = new StringBuilder(str);
            for (int i2 = 4; i2 < sb.length(); i2 += 6) {
                sb.insert(i2, "  ");
            }
            this.g.setText(sb.toString());
        }
    }

    private void b(String str) {
        this.f.setImageBitmap(CodeGenUtils.a(str, getResources().getDimensionPixelSize(R.dimen.jr_mipay_bar_code_widht), getResources().getDimensionPixelSize(R.dimen.jr_mipay_bar_code_height)));
    }

    private void c(String str) {
        this.h.setImageBitmap(CodeGenUtils.a(str, getResources().getDimensionPixelSize(R.dimen.jr_mipay_qr_code_width), getResources().getDimensionPixelSize(R.dimen.jr_mipay_qr_code_height), BitmapFactory.decodeResource(getResources(), R.drawable.jr_mipay_ic_mipay)));
    }

    private void b(PayType payType) {
        String str;
        if (!TextUtils.isEmpty(payType.mBriefSummary)) {
            str = payType.mBriefSummary;
        } else if (!TextUtils.isEmpty(payType.mSummary)) {
            str = payType.mSummary;
        } else if (TextUtils.equals(PayType.PAY_TYPE_BANLANCE, payType.mPayType)) {
            double d2 = (double) payType.mBalance;
            Double.isNaN(d2);
            String format = String.format("%.2f", new Object[]{Double.valueOf(d2 / 100.0d)});
            str = getString(R.string.jr_mipay_code_pay_type_balance, format);
        } else {
            if (TextUtils.equals("BANKCARD", payType.mPayType)) {
                if (2 == payType.mCardType) {
                    str = getString(R.string.jr_mipay_code_pay_type_bank_card, payType.mBankName, getString(R.string.jr_mipay_code_pay_card_type_credit), payType.mTailNum);
                } else if (1 == payType.mCardType) {
                    str = getString(R.string.jr_mipay_code_pay_type_bank_card, payType.mBankName, getString(R.string.jr_mipay_code_pay_card_type_debit), payType.mTailNum);
                }
            } else if (TextUtils.equals(PayType.PAY_TYPE_INSTALLMENT, payType.mPayType)) {
                str = payType.mSummary;
            }
            str = null;
        }
        this.m.setText(str);
        if (TextUtils.equals(payType.mPayType, PayType.PAY_TYPE_INSTALLMENT)) {
            this.l.setVisibility(0);
            this.l.setImageResource(R.drawable.jr_mipay_miloan_code);
        } else if (TextUtils.isEmpty(payType.mIconUrl)) {
            this.l.setVisibility(8);
        } else {
            this.l.setVisibility(0);
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.jr_mipay_pay_type_icon_width);
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.jr_mipay_pay_type_icon_height);
            Picasso.get().load(CodePayConstants.g + String.format("webp/w%dh%dq80/", new Object[]{Integer.valueOf(dimensionPixelSize), Integer.valueOf(dimensionPixelSize2)}) + payType.mIconUrl).into(this.l);
        }
    }

    private void i() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MenuItemData(getResources().getString(R.string.jr_mipay_close_code_pay), c));
        this.q.setOnClickListener(new View.OnClickListener(arrayList) {
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                CodePayFragment.this.a(this.f$1, view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(final List list, View view) {
        ImmersionMenu immersionMenu = new ImmersionMenu(getActivity());
        immersionMenu.a((ImmersionMenu.ImmersionMenuListener) new ImmersionMenu.ImmersionMenuListener() {
            public boolean b(Menu menu) {
                return true;
            }

            public void a(Menu menu) {
                for (int i = 0; i < list.size(); i++) {
                    menu.add(0, i, 0, ((MenuItemData) list.get(i)).f10928a);
                }
            }

            /* JADX WARNING: Code restructure failed: missing block: B:5:0x0026, code lost:
                r2 = r1.b.getContext();
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void a(android.view.Menu r2, android.view.MenuItem r3) {
                /*
                    r1 = this;
                    java.util.List r2 = r3
                    int r3 = r3.getItemId()
                    java.lang.Object r2 = r2.get(r3)
                    com.xiaomi.jr.mipay.codepay.ui.CodePayFragment$MenuItemData r2 = (com.xiaomi.jr.mipay.codepay.ui.CodePayFragment.MenuItemData) r2
                    java.lang.String r3 = "close_code_pay"
                    java.lang.String r0 = r2.b
                    boolean r3 = android.text.TextUtils.equals(r3, r0)
                    if (r3 == 0) goto L_0x001c
                    com.xiaomi.jr.mipay.codepay.ui.CodePayFragment r2 = com.xiaomi.jr.mipay.codepay.ui.CodePayFragment.this
                    r2.j()
                    goto L_0x0043
                L_0x001c:
                    java.lang.String r3 = "create_desktop_shortcut"
                    java.lang.String r2 = r2.b
                    boolean r2 = android.text.TextUtils.equals(r3, r2)
                    if (r2 == 0) goto L_0x0043
                    com.xiaomi.jr.mipay.codepay.ui.CodePayFragment r2 = com.xiaomi.jr.mipay.codepay.ui.CodePayFragment.this
                    android.content.Context r2 = r2.getContext()
                    if (r2 != 0) goto L_0x002f
                    return
                L_0x002f:
                    int r3 = android.os.Build.VERSION.SDK_INT
                    r0 = 19
                    if (r3 >= r0) goto L_0x0039
                    com.xiaomi.jr.mipay.codepay.ui.CodePayFragment.b((android.content.Context) r2)
                    return
                L_0x0039:
                    java.lang.String r3 = "com.android.launcher.permission.INSTALL_SHORTCUT"
                    com.xiaomi.jr.mipay.codepay.ui.CodePayFragment$2$1 r0 = new com.xiaomi.jr.mipay.codepay.ui.CodePayFragment$2$1
                    r0.<init>(r2)
                    com.xiaomi.jr.permission.PermissionManager.a((android.content.Context) r2, (java.lang.String) r3, (com.xiaomi.jr.permission.Request.Callback) r0)
                L_0x0043:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.mipay.codepay.ui.CodePayFragment.AnonymousClass2.a(android.view.Menu, android.view.MenuItem):void");
            }
        });
        immersionMenu.a(view, (ViewGroup) null);
    }

    /* access modifiers changed from: private */
    public void j() {
        new AlertDialog.Builder(getActivity()).a(R.string.jr_mipay_close_code_pay_tips).a(R.string.jr_mipay_button_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                CodePayFragment.this.a(dialogInterface, i);
            }
        }).b(17039360, (DialogInterface.OnClickListener) null).a(true).a().show(getFragmentManager(), "closeCodePay");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface, int i2) {
        this.t.b();
        getActivity().finish();
    }

    /* access modifiers changed from: private */
    public static void b(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setPackage(context.getPackageName());
        intent.setData(Uri.parse(UrlUtils.a(CodePayManager.f1446a, "from", CodePayManager.a())));
        if (Build.VERSION.SDK_INT >= 26) {
            ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService("shortcut");
            if (shortcutManager != null) {
                shortcutManager.requestPinShortcut(new ShortcutInfo.Builder(context, CodePayManager.a()).setIcon(Icon.createWithResource(context, CodePayManager.b())).setShortLabel(CodePayManager.a()).setIntent(intent).build(), (IntentSender) null);
                Utils.a(context, R.string.jr_mipay_code_pay_shortcut_create_success);
                return;
            }
            return;
        }
        Intent intent2 = new Intent(b);
        intent2.putExtra("duplicate", false);
        intent2.putExtra("android.intent.extra.shortcut.NAME", CodePayManager.a());
        intent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(context, CodePayManager.b()));
        intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
        context.sendBroadcast(intent2);
        Utils.a(context, R.string.jr_mipay_code_pay_shortcut_create_success);
    }

    private void k() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(CodePayConstants.k, this.t.c());
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.t.e());
        bundle.putSerializable(CodePayConstants.j, arrayList);
        bundle.putInt(DeeplinkConstants.KEY_REQUEST_CODE, 1001);
        CodePayUtils.c(this, ChoosePayMethodFragment.f10916a, bundle);
    }
}
