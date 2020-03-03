package com.xiaomi.smarthome.messagecenter.mvi.view;

import com.xiaomi.smarthome.messagecenter.ui.MessageCenterViewState;
import com.xiaomi.smarthome.ui.base.BaseModel;
import java.util.List;

public interface PartialChanges {

    public static class FirstPageError implements PartialChanges {
        public MessageCenterViewState a(MessageCenterViewState messageCenterViewState) {
            return null;
        }
    }

    public static class NextPageError implements PartialChanges {
        public MessageCenterViewState a(MessageCenterViewState messageCenterViewState) {
            return null;
        }
    }

    public static class NextPageLoading implements PartialChanges {
        public MessageCenterViewState a(MessageCenterViewState messageCenterViewState) {
            return null;
        }
    }

    public static class PullToRefreshError implements PartialChanges {
        public MessageCenterViewState a(MessageCenterViewState messageCenterViewState) {
            return null;
        }
    }

    public static class PullToRefreshLoaded implements PartialChanges {
        public MessageCenterViewState a(MessageCenterViewState messageCenterViewState) {
            return null;
        }
    }

    public static class PullToRefreshLoading implements PartialChanges {
        public MessageCenterViewState a(MessageCenterViewState messageCenterViewState) {
            return null;
        }
    }

    MessageCenterViewState a(MessageCenterViewState messageCenterViewState);

    public static class FirstPageLoading implements PartialChanges {
        public MessageCenterViewState a(MessageCenterViewState messageCenterViewState) {
            MessageCenterViewState a2 = new MessageCenterViewState.Builder(messageCenterViewState).a();
            a2.b(true);
            return a2;
        }
    }

    public static class FirstPageLoaded implements PartialChanges {

        /* renamed from: a  reason: collision with root package name */
        List<BaseModel> f10487a;

        public MessageCenterViewState a(MessageCenterViewState messageCenterViewState) {
            return null;
        }

        public FirstPageLoaded(List<BaseModel> list) {
            this.f10487a = list;
        }
    }

    public static class NextPageLoaded implements PartialChanges {

        /* renamed from: a  reason: collision with root package name */
        List<BaseModel> f10488a;

        public MessageCenterViewState a(MessageCenterViewState messageCenterViewState) {
            return null;
        }

        public NextPageLoaded(List<BaseModel> list) {
            this.f10488a = list;
        }
    }
}
