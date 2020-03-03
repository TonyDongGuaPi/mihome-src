package com.xiaomi.smarthome.operation.js_sdk.titlebar;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu;
import com.xiaomi.smarthome.operation.js_sdk.utils.JsSdkUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class TitleBarMenuAdapter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21115a = "TitleBarMenuAdapter";
    private static final String f = "inner:share_menu_id";
    private Context b;
    private TitleBarMenu c;
    private OnMenuClickListener d;
    /* access modifiers changed from: private */
    public View.OnClickListener e;
    private final CompatShareMenu g = new CompatShareMenu(f, "") {
        /* access modifiers changed from: package-private */
        public void onClick(View view, TitleBarMenu.Menu menu) {
            if (TitleBarMenuAdapter.this.e != null) {
                TitleBarMenuAdapter.this.e.onClick(view);
            }
        }
    };

    interface OnMenuClickListener {
        void a(View view, TitleBarMenu.Menu menu, String str);
    }

    private static class CompatShareMenu extends TitleBarMenu.Menu {
        public CompatShareMenu(String str, String str2) {
            super(str, str2);
            a((int) R.drawable.std_tittlebar_main_device_share);
        }
    }

    public TitleBarMenuAdapter(Context context, TitleBarMenu titleBarMenu) {
        this.b = context;
        this.c = titleBarMenu;
        this.g.a(this.b.getString(R.string.share_title));
    }

    public void a(OnMenuClickListener onMenuClickListener) {
        this.d = onMenuClickListener;
    }

    public void a(View.OnClickListener onClickListener) {
        this.e = onClickListener;
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
            r5 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu r1 = r5.c
            java.util.List r1 = r1.getDropDownMenus()
            r0.<init>(r1)
            java.util.Iterator r0 = r0.iterator()
        L_0x000f:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0020
            java.lang.Object r1 = r0.next()
            com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$Menu r1 = (com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu.Menu) r1
            boolean r1 = r1 instanceof com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenuAdapter.CompatShareMenu
            if (r1 == 0) goto L_0x000f
            return
        L_0x0020:
            java.util.ArrayList r0 = new java.util.ArrayList
            com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu r1 = r5.c
            java.util.List r1 = r1.getOptionMenus()
            r0.<init>(r1)
            java.util.Iterator r1 = r0.iterator()
        L_0x002f:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x004d
            java.lang.Object r2 = r1.next()
            com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$Menu r2 = (com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu.Menu) r2
            java.lang.String r3 = r2.a()
            java.lang.String r4 = "inner:share_menu_id"
            boolean r3 = android.text.TextUtils.equals(r3, r4)
            if (r3 == 0) goto L_0x0048
            return
        L_0x0048:
            boolean r2 = r2 instanceof com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenuAdapter.CompatShareMenu
            if (r2 == 0) goto L_0x002f
            return
        L_0x004d:
            int r1 = r0.size()
            r2 = 1
            r3 = 0
            if (r1 != r2) goto L_0x006f
            java.lang.Object r1 = r0.get(r3)
            com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$Menu r1 = (com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu.Menu) r1
            java.lang.String r1 = r1.a()
            com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu r4 = r5.c
            com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu$Menu r4 = r4.MORE_MENU
            java.lang.String r4 = r4.a()
            boolean r1 = android.text.TextUtils.equals(r1, r4)
            if (r1 == 0) goto L_0x006f
            r1 = 1
            goto L_0x0070
        L_0x006f:
            r1 = 0
        L_0x0070:
            if (r1 == 0) goto L_0x007a
            com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu r0 = r5.c
            com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenuAdapter$CompatShareMenu r1 = r5.g
            r0.insertOptionMenu(r1, r3)
            return
        L_0x007a:
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x0081
            goto L_0x0082
        L_0x0081:
            r2 = 0
        L_0x0082:
            if (r2 == 0) goto L_0x0085
            return
        L_0x0085:
            com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenu r0 = r5.c
            com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenuAdapter$CompatShareMenu r1 = r5.g
            r0.appendOptionMenu(r1)
            java.lang.String r0 = "TitleBarMenuAdapter"
            java.lang.String r1 = "addShareOptionMenu: "
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r0, (java.lang.String) r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.operation.js_sdk.titlebar.TitleBarMenuAdapter.a():void");
    }

    public void b() {
        this.c.removeOptionMenu(this.g);
    }

    public void a(String str) {
        TitleBarMenu.Menu menu;
        LogUtil.a(f21115a, "setOptionButton: " + str);
        final ButtonOption a2 = ButtonOption.b(str);
        if (a2 != null) {
            if (a2.b) {
                this.c.removeAllOptionMenus();
                a();
            } else if (a2.c == null || a2.c.isEmpty()) {
                this.c.removeAllOptionMenus();
                b();
            } else {
                ArrayList arrayList = new ArrayList();
                for (ButtonOption.ButtonMenu next : a2.c) {
                    if (TextUtils.equals("share", next.c)) {
                        menu = new CompatShareMenu(String.valueOf(next.f21122a), "") {
                            /* access modifiers changed from: package-private */
                            public void onClick(View view, TitleBarMenu.Menu menu) {
                                if (TitleBarMenuAdapter.this.e != null) {
                                    TitleBarMenuAdapter.this.e.onClick(view);
                                }
                                TitleBarMenuAdapter.this.a(view, menu, a2.f21121a);
                            }
                        };
                        b();
                    } else {
                        Bitmap a3 = JsSdkUtils.a(next.b);
                        AnonymousClass3 r4 = new TitleBarMenu.Menu(String.valueOf(next.f21122a), "") {
                            /* access modifiers changed from: package-private */
                            public void onClick(View view, TitleBarMenu.Menu menu) {
                                TitleBarMenuAdapter.this.a(view, menu, a2.f21121a);
                            }
                        };
                        r4.a(a3);
                        menu = r4;
                    }
                    menu.b(next.d);
                    arrayList.add(menu);
                }
                this.c.setOptionsMenu(arrayList);
            }
        }
    }

    public void b(String str) {
        TitleBarMenu.Menu menu;
        LogUtil.a(f21115a, "setDropDownMenu: " + str);
        final PopOption a2 = PopOption.b(str);
        if (a2 != null) {
            if (!a2.f21123a || a2.c == null || a2.c.isEmpty()) {
                this.c.removeDropDownMenu();
                a();
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (PopOption.PopMenu next : a2.c) {
                if (TextUtils.equals("share", next.c)) {
                    if (TextUtils.isEmpty(next.b)) {
                        next.b = this.b.getString(R.string.share_title);
                    }
                    menu = new CompatShareMenu(String.valueOf(next.f21124a), next.b) {
                        /* access modifiers changed from: package-private */
                        public void onClick(View view, TitleBarMenu.Menu menu) {
                            if (TitleBarMenuAdapter.this.e != null) {
                                TitleBarMenuAdapter.this.e.onClick(view);
                            }
                            TitleBarMenuAdapter.this.a(view, menu, a2.b);
                        }
                    };
                    b();
                } else {
                    menu = new TitleBarMenu.Menu(String.valueOf(next.f21124a), next.b) {
                        /* access modifiers changed from: package-private */
                        public void onClick(View view, TitleBarMenu.Menu menu) {
                            TitleBarMenuAdapter.this.a(view, menu, a2.b);
                        }
                    };
                    if (!TextUtils.isEmpty(next.d)) {
                        menu.a(JsSdkUtils.a(next.d));
                    }
                }
                menu.b(next.e);
                arrayList.add(menu);
            }
            this.c.setDropDownMenu(arrayList);
            a();
        }
    }

    /* access modifiers changed from: private */
    public void a(View view, TitleBarMenu.Menu menu, String str) {
        if (this.d != null) {
            this.d.a(view, menu, str);
        }
    }

    private static class ButtonOption {

        /* renamed from: a  reason: collision with root package name */
        String f21121a;
        boolean b;
        List<ButtonMenu> c;

        private ButtonOption() {
        }

        private static class ButtonMenu {

            /* renamed from: a  reason: collision with root package name */
            int f21122a;
            String b;
            String c;
            int d;

            ButtonMenu(int i) {
                this.f21122a = i;
            }
        }

        /* access modifiers changed from: private */
        public static ButtonOption b(String str) {
            try {
                ButtonOption buttonOption = new ButtonOption();
                JSONObject jSONObject = new JSONObject(str);
                buttonOption.f21121a = "optionButtonClick";
                int i = 0;
                buttonOption.b = jSONObject.optBoolean("reset", false);
                ArrayList arrayList = new ArrayList();
                buttonOption.c = arrayList;
                JSONArray optJSONArray = jSONObject.optJSONArray("items");
                while (optJSONArray != null && i < optJSONArray.length()) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    ButtonMenu buttonMenu = new ButtonMenu(i);
                    buttonMenu.b = jSONObject2.optString("icon");
                    buttonMenu.c = jSONObject2.optString("type");
                    buttonMenu.d = jSONObject2.optInt("badge", -1);
                    arrayList.add(buttonMenu);
                    i++;
                }
                return buttonOption;
            } catch (Exception e) {
                LogUtil.b(TitleBarMenuAdapter.f21115a, "parse: " + e.getLocalizedMessage());
                return null;
            }
        }
    }

    private static class PopOption {

        /* renamed from: a  reason: collision with root package name */
        boolean f21123a;
        String b;
        List<PopMenu> c;

        private PopOption() {
        }

        private static class PopMenu {

            /* renamed from: a  reason: collision with root package name */
            int f21124a;
            String b;
            String c;
            String d;
            int e;

            PopMenu(int i) {
                this.f21124a = i;
            }
        }

        /* access modifiers changed from: private */
        public static PopOption b(String str) {
            try {
                PopOption popOption = new PopOption();
                JSONObject jSONObject = new JSONObject(str);
                popOption.f21123a = jSONObject.optBoolean("enabled", true);
                popOption.b = "popMenuClick";
                ArrayList arrayList = new ArrayList();
                popOption.c = arrayList;
                JSONArray optJSONArray = jSONObject.optJSONArray("items");
                int i = 0;
                while (optJSONArray != null && i < optJSONArray.length()) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    PopMenu popMenu = new PopMenu(i);
                    popMenu.b = jSONObject2.optString("title");
                    popMenu.c = jSONObject2.optString("type");
                    popMenu.d = jSONObject2.optString("icon");
                    popMenu.e = jSONObject2.optInt("badge", -1);
                    arrayList.add(popMenu);
                    i++;
                }
                return popOption;
            } catch (Exception e) {
                LogUtil.b(TitleBarMenuAdapter.f21115a, "parse: " + e.getLocalizedMessage());
                return null;
            }
        }
    }
}
