package com.xiaomi.smarthome.newui.mainpage.devicepage.model;

import android.text.TextUtils;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.newui.DropMenuStateHelper;
import com.xiaomi.smarthome.newui.mainpage.devicepage.model.MainPageViewState;
import com.xiaomi.smarthome.newui.widget.topnavi.PageBean;
import com.xiaomi.smarthome.ui.base.EditModeView;
import java.util.List;

public interface DeviceMainPartialChanges extends PartialChanges<MainPageViewState> {

    public static class PageLoading implements DeviceMainPartialChanges {
        public MainPageViewState a(MainPageViewState mainPageViewState) {
            return new MainPageViewState.Builder(mainPageViewState).a(mainPageViewState == null).a((EditModeView.EditModeModel) null).a();
        }
    }

    public static class PageLoaded implements DeviceMainPartialChanges {

        /* renamed from: a  reason: collision with root package name */
        final List<MainPageDeviceModel> f20688a;

        public PageLoaded(List<MainPageDeviceModel> list) {
            this.f20688a = list;
        }

        public MainPageViewState a(MainPageViewState mainPageViewState) {
            PageBean b = DropMenuStateHelper.a().b();
            int i = 3;
            if (!TextUtils.isEmpty(b.f) && !b.h) {
                String str = b.f;
                char c = 65535;
                int hashCode = str.hashCode();
                if (hashCode != -2077299665) {
                    if (hashCode != -252753263) {
                        if (hashCode != 491886639) {
                            if (hashCode == 1189320177 && str.equals(HomeManager.h)) {
                                c = 0;
                            }
                        } else if (str.equals(HomeManager.e)) {
                            c = 2;
                        }
                    } else if (str.equals(HomeManager.d)) {
                        c = 1;
                    }
                } else if (str.equals(HomeManager.f)) {
                    c = 3;
                }
                switch (c) {
                    case 0:
                        i = 0;
                        break;
                    case 1:
                        i = 5;
                        break;
                    case 2:
                        i = 1;
                        break;
                    case 3:
                        i = 2;
                        break;
                    default:
                        i = 4;
                        break;
                }
            }
            return new MainPageViewState.Builder(mainPageViewState).a(this.f20688a).a((EditModeView.EditModeModel) null).a(i).a();
        }
    }

    public static class PageEditMode implements DeviceMainPartialChanges {

        /* renamed from: a  reason: collision with root package name */
        public final EditModeView.EditModeModel f20687a;

        public PageEditMode(EditModeView.EditModeModel editModeModel) {
            this.f20687a = editModeModel;
        }

        public MainPageViewState a(MainPageViewState mainPageViewState) {
            return new MainPageViewState.Builder(mainPageViewState).a(this.f20687a).a();
        }
    }
}
