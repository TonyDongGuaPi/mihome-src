package com.xiaomi.jr.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.adobe.xmp.XMPConst;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.jr.common.lifecycle.LifecycledObjects;
import com.xiaomi.jr.common.utils.AppUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.MiuiClient;
import java.util.ArrayList;
import java.util.List;

public class PermissionActivity extends FragmentActivity {
    public static final String KEY_PERMISSIONS = "permissions";
    public static final String KEY_REQUEST_OBJECT_ID = "request_object_id";

    /* renamed from: a  reason: collision with root package name */
    private static final String f10994a = "PermissionActivity";
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 500;
    private Integer e;
    private long f;
    private String[] g;
    private List<String> h = null;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String[] stringArrayExtra = intent.getStringArrayExtra("permissions");
        if (stringArrayExtra == null) {
            finish();
            return;
        }
        this.g = stringArrayExtra;
        this.e = Integer.valueOf(intent.getIntExtra(KEY_REQUEST_OBJECT_ID, 0));
        if (this.e.intValue() == 0) {
            finish();
            return;
        }
        if (MiuiClient.a()) {
            ArrayList arrayList = new ArrayList();
            for (String str : stringArrayExtra) {
                if (PermissionUtil.a(str) && !PermissionUtil.b(this, str)) {
                    arrayList.add(str);
                }
            }
            if (!arrayList.isEmpty()) {
                c(arrayList);
                return;
            }
        }
        if (PermissionUtil.a((Context) this)) {
            List<String> b2 = b(stringArrayExtra);
            MifiLog.c(f10994a, "should show rationale permissions: " + d(b2));
            if (b2 == null || b2.isEmpty()) {
                a(stringArrayExtra);
            } else {
                b(b2);
            }
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (String str2 : stringArrayExtra) {
                if (!PermissionUtil.b(this, str2)) {
                    arrayList2.add(str2);
                }
            }
            if (arrayList2.isEmpty()) {
                a((List<String>) null);
            } else {
                c(arrayList2);
            }
        }
    }

    @TargetApi(23)
    private void a(String[] strArr) {
        if (MiuiClient.a()) {
            ArrayList arrayList = new ArrayList();
            for (String str : strArr) {
                int checkSelfPermission = checkSelfPermission(str);
                MifiLog.b(f10994a, "miui checkSelfPermission " + str + " result: " + checkSelfPermission);
                if (checkSelfPermission != 0) {
                    arrayList.add(str);
                }
            }
            if (arrayList.isEmpty()) {
                a((List<String>) null);
                return;
            }
            strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        this.f = System.currentTimeMillis();
        requestPermissions(strArr, 1);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        LifecycledObjects.a(this.e);
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.h != null) {
            List<String> list = this.h;
            this.h = null;
            c(list);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 1) {
            long currentTimeMillis = System.currentTimeMillis() - this.f;
            MifiLog.c(f10994a, "request permission takes: " + currentTimeMillis);
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (iArr[i2] != 0) {
                    arrayList.add(strArr[i2]);
                }
            }
            MifiLog.c(f10994a, "ungranted permissions in result: " + d(arrayList));
            if (currentTimeMillis >= 500) {
                a((List<String>) arrayList);
            } else if (arrayList.isEmpty()) {
                a((List<String>) null);
            } else {
                this.h = arrayList;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 2) {
            Request request = (Request) LifecycledObjects.b(this.e);
            if (!(request == null || request.a() == null)) {
                request.a().b();
            }
            finish();
        }
    }

    private void a(List<String> list) {
        Request request = (Request) LifecycledObjects.b(this.e);
        if (!(request == null || request.a() == null)) {
            if (list == null || list.isEmpty()) {
                request.a().a();
            } else {
                request.a().a(list.get(0));
            }
        }
        finish();
    }

    @TargetApi(23)
    private List<String> b(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (shouldShowRequestPermissionRationale(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private void b(List<String> list) {
        Request request = (Request) LifecycledObjects.b(this.e);
        if (request != null) {
            if (request.b() == null) {
                request.a((PermissionDialogDelegate) new DefaultPermissionDialogDelegate());
            }
            String string = getString(R.string.permission_rationale_message, new Object[]{AppUtils.d(this)});
            while (true) {
                String str = string;
                for (CharSequence[] next : PermissionUtil.a((Context) this, list).values()) {
                    if (!TextUtils.isEmpty(next[1])) {
                        string = str + "\n-" + Character.toUpperCase(next[1].charAt(0)) + next[1].subSequence(1, next[1].length());
                    }
                }
                request.b().a(this, getString(R.string.permission_rationale_title), str, getString(R.string.permission_rationale_confirm), new DialogInterface.OnClickListener() {
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        PermissionActivity.this.b(dialogInterface, i);
                    }
                }, getString(R.string.permission_cancel), new DialogInterface.OnClickListener(list) {
                    private final /* synthetic */ List f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(DialogInterface dialogInterface, int i) {
                        PermissionActivity.this.b(this.f$1, dialogInterface, i);
                    }
                });
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(DialogInterface dialogInterface, int i) {
        a(this.g);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(List list, DialogInterface dialogInterface, int i) {
        Request request = (Request) LifecycledObjects.b(this.e);
        if (!(request == null || request.a() == null)) {
            request.a().a((String) list.get(0));
        }
        finish();
    }

    private void c(List<String> list) {
        Request request = (Request) LifecycledObjects.b(this.e);
        if (request != null) {
            if (request.b() == null) {
                request.a((PermissionDialogDelegate) new DefaultPermissionDialogDelegate());
            }
            String string = getString(R.string.permission_setting_message, new Object[]{AppUtils.d(this)});
            String str = string;
            for (CharSequence[] next : PermissionUtil.a((Context) this, list).values()) {
                str = str + "\n-" + next[0] + " (" + next[1] + Operators.BRACKET_END_STR;
            }
            request.b().a(this, getString(R.string.permission_setting_title), str, getString(R.string.permission_setting_confirm), new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    PermissionActivity.this.a(dialogInterface, i);
                }
            }, getString(R.string.permission_cancel), new DialogInterface.OnClickListener(list) {
                private final /* synthetic */ List f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    PermissionActivity.this.a(this.f$1, dialogInterface, i);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface, int i) {
        PermissionUtil.a((Activity) this, 2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(List list, DialogInterface dialogInterface, int i) {
        Request request = (Request) LifecycledObjects.b(this.e);
        if (!(request == null || request.a() == null)) {
            request.a().a((String) list.get(0));
        }
        finish();
    }

    private String d(List<String> list) {
        if (list == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(Operators.ARRAY_START_STR);
        for (String append : list) {
            sb.append(append);
            sb.append(", ");
        }
        if (sb.length() <= 1) {
            return XMPConst.ai;
        }
        return sb.substring(0, sb.length() - 2) + Operators.ARRAY_END_STR;
    }
}
