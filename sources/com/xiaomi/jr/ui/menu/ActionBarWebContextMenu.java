package com.xiaomi.jr.ui.menu;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.GsonBuilder;
import com.miui.supportlite.R;
import com.miui.supportlite.app.ImmersionMenu;
import com.xiaomi.jr.common.app.ActivityChecker;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.ui.ActionBarCustomizer;
import com.xiaomi.jr.ui.UIUtils;
import com.xiaomi.jr.ui.menu.ActionBarWebContextMenu;
import java.util.List;

public class ActionBarWebContextMenu {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11033a = "ActionBarWebContextMenu";

    public interface MenuEventListener {
        void onMenuEvent(String str);
    }

    public static String a(Activity activity, String str, MenuEventListener menuEventListener) {
        if (!(activity instanceof com.miui.supportlite.app.Activity)) {
            MifiLog.e(f11033a, "setMenu: activity is not in miui support style");
            return "activity is not in miui support style";
        } else if (!ActivityChecker.a(activity)) {
            return "activity is not available";
        } else {
            com.miui.supportlite.app.Activity activity2 = (com.miui.supportlite.app.Activity) activity;
            MenuConfig menuConfig = (MenuConfig) new GsonBuilder().create().fromJson(str, MenuConfig.class);
            if (menuConfig == null) {
                return "config format error: " + str;
            }
            List<MenuItemConfig> a2 = menuConfig.a();
            if (a2 == null || a2.size() == 0) {
                return "no proper menu items: " + str;
            }
            activity.runOnUiThread(new Runnable(activity, a2, activity2, menuEventListener) {
                private final /* synthetic */ Activity f$0;
                private final /* synthetic */ List f$1;
                private final /* synthetic */ com.miui.supportlite.app.Activity f$2;
                private final /* synthetic */ ActionBarWebContextMenu.MenuEventListener f$3;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    ActionBarWebContextMenu.a(this.f$0, this.f$1, this.f$2, this.f$3);
                }
            });
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Activity activity, List list, com.miui.supportlite.app.Activity activity2, MenuEventListener menuEventListener) {
        int i;
        String str;
        if (ActivityChecker.a(activity)) {
            if (list.size() == 1) {
                MenuItemConfig menuItemConfig = (MenuItemConfig) list.get(0);
                if (TextUtils.isEmpty(menuItemConfig.b())) {
                    str = menuItemConfig.c();
                    i = 0;
                } else {
                    int a2 = UIUtils.a(activity, menuItemConfig.b());
                    if (a2 == 0) {
                        MifiLog.e(f11033a, "invalid icon name " + menuItemConfig.b());
                    }
                    str = null;
                    i = a2;
                }
                ActionBarCustomizer.a(activity2, str, new View.OnClickListener(menuItemConfig) {
                    private final /* synthetic */ MenuItemConfig f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        ActionBarWebContextMenu.MenuEventListener.this.onMenuEvent(this.f$1.a());
                    }
                }, i, new View.OnClickListener(menuItemConfig) {
                    private final /* synthetic */ MenuItemConfig f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        ActionBarWebContextMenu.MenuEventListener.this.onMenuEvent(this.f$1.a());
                    }
                }, 0, (View.OnClickListener) null);
            } else if (list.size() != 2 || TextUtils.isEmpty(((MenuItemConfig) list.get(0)).b()) || TextUtils.isEmpty(((MenuItemConfig) list.get(1)).b())) {
                ActionBarCustomizer.a(activity2, R.drawable.miuisupport_action_mode_immersion_more, (View.OnClickListener) new View.OnClickListener(activity, list, menuEventListener) {
                    private final /* synthetic */ Activity f$0;
                    private final /* synthetic */ List f$1;
                    private final /* synthetic */ ActionBarWebContextMenu.MenuEventListener f$2;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void onClick(View view) {
                        ActionBarWebContextMenu.a(this.f$0, this.f$1, this.f$2, view);
                    }
                });
            } else {
                ActionBarCustomizer.a(activity2, (String) null, (View.OnClickListener) null, UIUtils.a(activity, ((MenuItemConfig) list.get(0)).b()), new View.OnClickListener(list) {
                    private final /* synthetic */ List f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        ActionBarWebContextMenu.MenuEventListener.this.onMenuEvent(((MenuItemConfig) this.f$1.get(0)).a());
                    }
                }, UIUtils.a(activity, ((MenuItemConfig) list.get(1)).b()), new View.OnClickListener(list) {
                    private final /* synthetic */ List f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        ActionBarWebContextMenu.MenuEventListener.this.onMenuEvent(((MenuItemConfig) this.f$1.get(1)).a());
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Activity activity, final List list, final MenuEventListener menuEventListener, View view) {
        ImmersionMenu immersionMenu = new ImmersionMenu(activity);
        immersionMenu.a((ImmersionMenu.ImmersionMenuListener) new ImmersionMenu.ImmersionMenuListener() {
            public boolean b(Menu menu) {
                return true;
            }

            public void a(Menu menu) {
                for (int i = 0; i < list.size(); i++) {
                    menu.add(0, i, 0, ((MenuItemConfig) list.get(i)).c());
                }
            }

            public void a(Menu menu, MenuItem menuItem) {
                menuEventListener.onMenuEvent(((MenuItemConfig) list.get(menuItem.getItemId())).a());
            }
        });
        immersionMenu.a(view, (ViewGroup) null);
    }
}
