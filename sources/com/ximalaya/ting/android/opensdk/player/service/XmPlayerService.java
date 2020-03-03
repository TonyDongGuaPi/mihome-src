package com.ximalaya.ting.android.opensdk.player.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.alipay.mobile.security.bio.api.BioDetector;
import com.coloros.mcssdk.PushManager;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.shop.model.Tags;
import com.xiaomi.smarthome.framework.api.UserConfig;
import com.ximalaya.ting.android.opensdk.constants.ConstantsOpenSdk;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;
import com.ximalaya.ting.android.opensdk.datatrasfer.AccessTokenManager;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequestForMain;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.httputil.BaseCall;
import com.ximalaya.ting.android.opensdk.httputil.Config;
import com.ximalaya.ting.android.opensdk.httputil.HttpDNSUtilForOpenSDK;
import com.ximalaya.ting.android.opensdk.httputil.HttpUrlUtil;
import com.ximalaya.ting.android.opensdk.httputil.IDataSupportCallBack;
import com.ximalaya.ting.android.opensdk.httputil.XmSecretKeyUtil;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.advertis.Advertis;
import com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.AlbumList;
import com.ximalaya.ting.android.opensdk.model.album.SearchAlbumList;
import com.ximalaya.ting.android.opensdk.model.category.Category;
import com.ximalaya.ting.android.opensdk.model.category.CategoryList;
import com.ximalaya.ting.android.opensdk.model.category.CategoryModel;
import com.ximalaya.ting.android.opensdk.model.live.radio.Radio;
import com.ximalaya.ting.android.opensdk.model.live.schedule.Schedule;
import com.ximalaya.ting.android.opensdk.model.statistic.RecordModel;
import com.ximalaya.ting.android.opensdk.model.token.AccessToken;
import com.ximalaya.ting.android.opensdk.model.track.LastPlayTrackList;
import com.ximalaya.ting.android.opensdk.model.track.SearchTrackList;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.model.track.TrackBaseInfo;
import com.ximalaya.ting.android.opensdk.model.track.TrackHotList;
import com.ximalaya.ting.android.opensdk.model.track.TrackList;
import com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel;
import com.ximalaya.ting.android.opensdk.model.xdcs.StatToServerFactoryImplForOpen;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManagerForPlayer;
import com.ximalaya.ting.android.opensdk.player.advertis.IXmAdsStatusListener;
import com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager;
import com.ximalaya.ting.android.opensdk.player.appnotification.NotificationColorUtils;
import com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater;
import com.ximalaya.ting.android.opensdk.player.appwidget.WidgetProvider;
import com.ximalaya.ting.android.opensdk.player.mediacontrol.MediaControlManager;
import com.ximalaya.ting.android.opensdk.player.service.IXmPlayer;
import com.ximalaya.ting.android.opensdk.player.service.MyRemoteCallbackList;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerControl;
import com.ximalaya.ting.android.opensdk.player.statistic.XmStatisticsManager;
import com.ximalaya.ting.android.opensdk.util.BaseUtil;
import com.ximalaya.ting.android.opensdk.util.FileUtilBase;
import com.ximalaya.ting.android.opensdk.util.Logger;
import com.ximalaya.ting.android.opensdk.util.ModelUtil;
import com.ximalaya.ting.android.opensdk.util.NetworkType;
import com.ximalaya.ting.android.opensdk.util.PayUtil;
import com.ximalaya.ting.android.opensdk.util.SharedPreferencesUtil;
import com.ximalaya.ting.android.player.DownloadThread;
import com.ximalaya.ting.android.player.IDomainServerIpCallback;
import com.ximalaya.ting.android.player.MediadataCrytoUtil;
import com.ximalaya.ting.android.player.PlayCacheByLRU;
import com.ximalaya.ting.android.player.PlayerUtil;
import com.ximalaya.ting.android.player.StaticConfig;
import com.ximalaya.ting.android.player.XMediaPlayer;
import com.ximalaya.ting.android.player.XMediaPlayerConstants;
import com.ximalaya.ting.android.player.cdn.CdnConstants;
import com.ximalaya.ting.android.player.cdn.CdnUtil;
import com.ximalaya.ting.android.player.xdcs.IStatToServerFactory;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmPlayerService extends Service {
    public static final int CODE_DATA_CHANGE_LOGIN_INOROUT = 116;
    public static final int CODE_GET_ALBUMS_BY_CATEGORY_ID_AND_TAG = 129;
    public static final int CODE_GET_CATEGORIES_LIST = 128;
    public static final int CODE_GET_NEW_ALBUM_RANK = 132;
    public static final int CODE_GET_NEW_TRACK_RANK = 133;
    public static final int CODE_GET_PARSE_DEVICE_INFO = 117;
    public static final int CODE_GET_PROVINCES = 125;
    public static final int CODE_GET_RADIO_LIST = 126;
    public static final int CODE_GET_RADIO_SCHEDULES = 127;
    public static final int CODE_GET_RECOMMEND_ALBUMLIST_BY_ALBUMID = 123;
    public static final int CODE_GET_RECOMMEND_ALBUMLIST_BY_TRACKID = 124;
    public static final int CODE_GET_SPECIALLISTEN = 119;
    public static final int CODE_GET_SUBJECTDETAIL = 120;
    public static final int CODE_GET_SUGGEST_ALBUMS = 118;
    public static final int CODE_GET_TAGS_BY_CATEGORY_ID = 130;
    public static final int CODE_GET_TRACKLIST_BYTRACKIDATALBUM = 122;
    public static final int CODE_GET_TRACK_DETAIL_INFO = 131;
    public static final int CODE_HOT_ALBUM = 115;
    public static final int CODE_HOT_TRACK = 105;
    public static final int CODE_SUBSCRIBE_ALBUM = 121;
    private static final String K = "openSDK_getSubscribtAlbumList";
    private static final String L = "openSDK_getAttentionAlbumList";
    public static final int LOCAL_RADIO = 0;
    private static final String M = "openSDK_getAlbumData";
    private static final int N = 100;
    public static final int NATIONAL_RADIO = 1;
    public static final int NET_RADIO = 3;
    private static final int O = 101;
    public static final String OPENSDK_GETHOTTRACK = "openSDK_recommentTrack";
    public static final String OPENSDK_GETRANKALBUMLIST = "openSDK_getRankAlbumList";
    public static final String OPENSDK_GETRANKANCHORLIST = "openSDK_getRankAnchorList";
    public static final String OPENSDK_GETUSERINFO = "openSDK_getUserInfo";
    public static final String OPENSDK_GET_ALBUMS_BY_CATEGORY_ID_AND_TAG = "opensdk_get_albums_by_category_id_and_tag";
    public static final String OPENSDK_GET_CATEGORIES_LIST = "opensdk_get_categories_list";
    public static final String OPENSDK_GET_HOT_ALBUM = "openSDK_recommentAlbum";
    public static final String OPENSDK_GET_NEW_RANK_ALBUM = "openSDK_getNewRankAlbum";
    public static final String OPENSDK_GET_NEW_RANK_TRACK = "openSDK_getNewRankTrack";
    public static final String OPENSDK_GET_PARSE_DEVICE_INFO = "opensdk_get_parse_device_info";
    public static final String OPENSDK_GET_PROVINCES = "opensdk_get_provinces";
    public static final String OPENSDK_GET_RADIO_LIST = "opensdk_get_radio_list";
    public static final String OPENSDK_GET_RADIO_SCHEDULES = "opensdk_get_radio_schedules";
    public static final String OPENSDK_GET_RANK_TRACK = "openSDK_getRankList";
    public static final String OPENSDK_GET_RECOMMEND_ALBUMLIST_BY_ALBUMID = "opensdk_get_recommend_albumlist_by_albumid";
    public static final String OPENSDK_GET_RECOMMEND_ALBUMLIST_BY_TRACKID = "opensdk_get_recommend_albumlist_by_trackid";
    public static final String OPENSDK_GET_SPECIALLISTEN = "opensdk_get_speciallisten";
    public static final String OPENSDK_GET_SUBJECTDETAIL = "opensdk_get_subjectdetail";
    public static final String OPENSDK_GET_SUGGEST_ALBUMS = "opensdk_get_suggest_albums";
    public static final String OPENSDK_GET_TAGS_BY_CATEGORY_ID = "opensdk_get_tags_by_category_id";
    public static final String OPENSDK_GET_TRACKLIST_BYTRACKIDATALBUM = "opensdk_get_tracklist_bytrackidatalbum";
    public static final String OPENSDK_GET_TRACK_INFO = "openSDK_getTrackInfoDetail";
    public static final String OPENSDK_SUBSCRIBE_ALBUM = "opensdk_subscribe_album";
    private static final int P = 102;
    public static final int PLAN_NORMAL = 0;
    public static final int PLAN_PAUSE_ON_COMPLETE = -1;
    public static final int PROVINCE_RADIO = 2;
    private static final int Q = 103;
    private static final int R = 104;
    private static final int S = 106;
    private static final int T = 107;
    public static final int TYPE_HOT_ALBUM = 2;
    public static final int TYPE_HOT_TRACK = 1;
    public static final String TYPE_RANK_ALBUM = "album";
    public static final String TYPE_RANK_ANCHOR = "anchor";
    public static final String TYPE_RANK_TRACK = "track";
    private static final int U = 404;
    private static final String V = "getStringByUrlForOpenSDK";

    /* renamed from: a  reason: collision with root package name */
    private static final String f2220a = "XmPlayerService";
    private static final int c = 1000;
    /* access modifiers changed from: private */
    public static Service o;
    private int A = 0;
    private IStatToServerFactory B;
    private XMediaPlayer.OnPlayDataOutputListener C;
    private IDomainServerIpCallback D;
    private WidgetProvider E;
    private boolean F = false;
    /* access modifiers changed from: private */
    public IXmPlayerStatusListener G = new IXmPlayerStatusListener() {
        private long b;

        public void e() {
            Logger.a("onSoundPrepared XmPlayerService 192:" + System.currentTimeMillis());
            int unused = XmPlayerService.this.r = XmPlayerService.this.k.l();
            synchronized (XmPlayerService.class) {
                int beginBroadcast = XmPlayerService.this.d.beginBroadcast();
                for (int i = 0; i < beginBroadcast; i++) {
                    try {
                        ((IXmPlayerEventDispatcher) XmPlayerService.this.d.getBroadcastItem(i)).onSoundPrepared();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                XmPlayerService.this.d.finishBroadcast();
            }
        }

        public void a(PlayableModel playableModel, PlayableModel playableModel2) {
            Logger.a("onSoundSwitch XmPlayerService 221:" + System.currentTimeMillis());
            if (XmPlayerService.this.getPlayerImpl() != null) {
                long unused = XmPlayerService.this.getPlayerImpl().lastRequestTime = System.currentTimeMillis();
            }
            boolean unused2 = XmPlayerService.this.X = false;
            Logger.c(XmPlayerService.f2220a, DownloadThread.f2270a + "");
            SharedPreferencesUtil a2 = SharedPreferencesUtil.a(XmPlayerService.this.b);
            a2.a("downloadedSize", "" + DownloadThread.f2270a);
            DownloadThread.f2270a = 0;
            synchronized (XmPlayerService.class) {
                int beginBroadcast = XmPlayerService.this.d.beginBroadcast();
                for (int i = 0; i < beginBroadcast; i++) {
                    try {
                        ((IXmPlayerEventDispatcher) XmPlayerService.this.d.getBroadcastItem(i)).onSoundSwitch((Track) playableModel, (Track) playableModel2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                XmPlayerService.this.d.finishBroadcast();
            }
            if (playableModel != null && (playableModel instanceof Track)) {
                int c = XmPlayerService.this.k.c();
                XmPlayerService.this.p.a((Track) playableModel, c);
            }
            if (XmPlayerService.this.p != null) {
                XmPlayerService.this.p.c();
            }
            XmNotificationCreater.a(XmPlayerService.this.b).a(XmPlayerService.this.mListControl, XmPlayerService.this.u, XmPlayerService.this.v, XmPlayerService.this.w, NotificationColorUtils.a(XmPlayerService.this.b));
        }

        private void h() {
            SharedPreferences.Editor edit = XmPlayerService.this.getApplicationContext().getSharedPreferences("downloadedSize", SharedPreferencesUtil.f2250a).edit();
            edit.putString("downloadedSize", "" + DownloadThread.f2270a);
            edit.apply();
        }

        public void c() {
            Logger.a("onPlayStop XmPlayerService 271:" + System.currentTimeMillis());
            synchronized (XmPlayerService.class) {
                int beginBroadcast = XmPlayerService.this.d.beginBroadcast();
                for (int i = 0; i < beginBroadcast; i++) {
                    try {
                        ((IXmPlayerEventDispatcher) XmPlayerService.this.d.getBroadcastItem(i)).onPlayStop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                XmPlayerService.this.d.finishBroadcast();
            }
            int c = XmPlayerService.this.k.c();
            XmPlayerService.this.p.a((Track) XmPlayerService.this.mListControl.n(), c);
            XmPlayerService.this.p.d();
            XmPlayerService.this.z.e();
        }

        public void a() {
            Logger.a("onPlayStart XmPlayerService 294:" + System.currentTimeMillis());
            if (XmPlayerService.this.ab == null || !XmPlayerService.this.ab.a()) {
                if (XmPlayerService.this.X) {
                    boolean unused = XmPlayerService.this.X = false;
                    XmPlayerService.this.pausePlay();
                    return;
                }
                XmPlayerService.this.g();
                synchronized (XmPlayerService.class) {
                    int beginBroadcast = XmPlayerService.this.d.beginBroadcast();
                    for (int i = 0; i < beginBroadcast; i++) {
                        try {
                            ((IXmPlayerEventDispatcher) XmPlayerService.this.d.getBroadcastItem(i)).onPlayStart();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    XmPlayerService.this.d.finishBroadcast();
                }
                XmNotificationCreater.a(XmPlayerService.this.b).b(XmPlayerService.this.u, XmPlayerService.this.v, XmPlayerService.this.w, NotificationColorUtils.a(XmPlayerService.this.b));
                XmPlayerService.this.b();
                Track track = (Track) XmPlayerService.this.mListControl.n();
                int c = XmPlayerService.this.k.c();
                XmPlayerService.this.p.a(track, XmPlayerService.this.k.e(), c, XmPlayerService.this.getCurPlayUrl());
                XmPlayerService.this.p.a(c, XmPlayerService.this.k.p());
                XmPlayerService.this.z.c();
                XmPlayerService.this.a(track);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:38:0x00d3, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d5, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(int r7, int r8) {
            /*
                r6 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "onPlayProgress XmPlayerService 336:"
                r0.append(r1)
                long r1 = java.lang.System.currentTimeMillis()
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                com.ximalaya.ting.android.opensdk.util.Logger.a((java.lang.String) r0)
                java.lang.Class<com.ximalaya.ting.android.opensdk.player.service.XmPlayerService> r0 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.class
                monitor-enter(r0)
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r1 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x00d6 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl r1 = r1.mListControl     // Catch:{ all -> 0x00d6 }
                com.ximalaya.ting.android.opensdk.model.PlayableModel r1 = r1.n()     // Catch:{ all -> 0x00d6 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r2 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x00d6 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerControl r2 = r2.k     // Catch:{ all -> 0x00d6 }
                com.ximalaya.ting.android.opensdk.model.PlayableModel r2 = r2.f()     // Catch:{ all -> 0x00d6 }
                if (r1 == 0) goto L_0x00d4
                if (r2 != 0) goto L_0x0033
                goto L_0x00d4
            L_0x0033:
                boolean r2 = r1.equals(r2)     // Catch:{ all -> 0x00d6 }
                if (r2 == 0) goto L_0x0098
                r2 = r1
                com.ximalaya.ting.android.opensdk.model.track.Track r2 = (com.ximalaya.ting.android.opensdk.model.track.Track) r2     // Catch:{ all -> 0x00d6 }
                r3 = 0
                if (r7 <= 0) goto L_0x0056
                int r4 = r8 + -1000
                if (r7 >= r4) goto L_0x0056
                r2.a((int) r7)     // Catch:{ all -> 0x00d6 }
                boolean r2 = r2.be()     // Catch:{ all -> 0x00d6 }
                if (r2 == 0) goto L_0x006c
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r2 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x00d6 }
                long r4 = r1.a()     // Catch:{ all -> 0x00d6 }
                r2.saveSoundHistoryPos(r4, r7)     // Catch:{ all -> 0x00d6 }
                goto L_0x006c
            L_0x0056:
                int r4 = r8 + -1000
                if (r7 < r4) goto L_0x006c
                r2.a((int) r3)     // Catch:{ all -> 0x00d6 }
                boolean r2 = r2.be()     // Catch:{ all -> 0x00d6 }
                if (r2 == 0) goto L_0x006c
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r2 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x00d6 }
                long r4 = r1.a()     // Catch:{ all -> 0x00d6 }
                r2.saveSoundHistoryPos(r4, r3)     // Catch:{ all -> 0x00d6 }
            L_0x006c:
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r1 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x00d6 }
                android.os.RemoteCallbackList r1 = r1.d     // Catch:{ all -> 0x00d6 }
                int r1 = r1.beginBroadcast()     // Catch:{ all -> 0x00d6 }
            L_0x0076:
                if (r3 >= r1) goto L_0x008f
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r2 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x00d6 }
                android.os.RemoteCallbackList r2 = r2.d     // Catch:{ all -> 0x00d6 }
                android.os.IInterface r2 = r2.getBroadcastItem(r3)     // Catch:{ all -> 0x00d6 }
                com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher r2 = (com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher) r2     // Catch:{ all -> 0x00d6 }
                r2.onPlayProgress(r7, r8)     // Catch:{ Exception -> 0x0088 }
                goto L_0x008c
            L_0x0088:
                r2 = move-exception
                r2.printStackTrace()     // Catch:{ all -> 0x00d6 }
            L_0x008c:
                int r3 = r3 + 1
                goto L_0x0076
            L_0x008f:
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r1 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x00d6 }
                android.os.RemoteCallbackList r1 = r1.d     // Catch:{ all -> 0x00d6 }
                r1.finishBroadcast()     // Catch:{ all -> 0x00d6 }
            L_0x0098:
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r1 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x00d6 }
                com.ximalaya.ting.android.opensdk.player.statistic.XmStatisticsManager r1 = r1.p     // Catch:{ all -> 0x00d6 }
                r1.a((int) r7, (int) r8)     // Catch:{ all -> 0x00d6 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r1 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x00d6 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerControl r1 = r1.k     // Catch:{ all -> 0x00d6 }
                if (r1 == 0) goto L_0x00bc
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r1 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x00d6 }
                com.ximalaya.ting.android.opensdk.player.statistic.XmStatisticsManager r1 = r1.p     // Catch:{ all -> 0x00d6 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r2 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x00d6 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerControl r2 = r2.k     // Catch:{ all -> 0x00d6 }
                boolean r2 = r2.p()     // Catch:{ all -> 0x00d6 }
                r1.a((int) r7, (int) r8, (boolean) r2)     // Catch:{ all -> 0x00d6 }
            L_0x00bc:
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r1 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x00d6 }
                r1.e()     // Catch:{ all -> 0x00d6 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r1 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x00d6 }
                com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager r1 = r1.s     // Catch:{ all -> 0x00d6 }
                if (r1 == 0) goto L_0x00d2
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r1 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x00d6 }
                com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager r1 = r1.s     // Catch:{ all -> 0x00d6 }
                r1.a((int) r7, (int) r8)     // Catch:{ all -> 0x00d6 }
            L_0x00d2:
                monitor-exit(r0)     // Catch:{ all -> 0x00d6 }
                return
            L_0x00d4:
                monitor-exit(r0)     // Catch:{ all -> 0x00d6 }
                return
            L_0x00d6:
                r7 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x00d6 }
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.AnonymousClass1.a(int, int):void");
        }

        public void b() {
            Logger.a("onPlayPause XmPlayerService 393:" + System.currentTimeMillis());
            synchronized (XmPlayerService.class) {
                int beginBroadcast = XmPlayerService.this.d.beginBroadcast();
                for (int i = 0; i < beginBroadcast; i++) {
                    try {
                        ((IXmPlayerEventDispatcher) XmPlayerService.this.d.getBroadcastItem(i)).onPlayPause();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                XmPlayerService.this.d.finishBroadcast();
            }
            Logger.a("xmplayerservice onPlayPause" + new Date());
            XmNotificationCreater.a(XmPlayerService.this.b).a(XmPlayerService.this.u, XmPlayerService.this.v, XmPlayerService.this.w, NotificationColorUtils.a(XmPlayerService.this.b));
            XmPlayerService.this.c();
            XmPlayerService.this.z.d();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:24:0x00bf, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x0136, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void d() {
            /*
                r10 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "onSoundPlayComplete XmPlayerService 417:"
                r0.append(r1)
                long r1 = java.lang.System.currentTimeMillis()
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                com.ximalaya.ting.android.opensdk.util.Logger.a((java.lang.String) r0)
                java.lang.Class<com.ximalaya.ting.android.opensdk.player.service.XmPlayerService> r0 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.class
                monitor-enter(r0)
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r1 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                long r1 = r1.W     // Catch:{ all -> 0x0137 }
                r3 = -1
                int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                r1 = 1
                r2 = 0
                if (r5 != 0) goto L_0x0032
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r3 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                r4 = 0
                long unused = r3.W = r4     // Catch:{ all -> 0x0137 }
                r3 = 0
                goto L_0x0033
            L_0x0032:
                r3 = 1
            L_0x0033:
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r4 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl r4 = r4.mListControl     // Catch:{ all -> 0x0137 }
                int r4 = r4.a((boolean) r2)     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r5 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                android.os.RemoteCallbackList r5 = r5.d     // Catch:{ all -> 0x0137 }
                int r5 = r5.beginBroadcast()     // Catch:{ all -> 0x0137 }
                r6 = 0
            L_0x0046:
                if (r6 >= r5) goto L_0x005f
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r7 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                android.os.RemoteCallbackList r7 = r7.d     // Catch:{ all -> 0x0137 }
                android.os.IInterface r7 = r7.getBroadcastItem(r6)     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher r7 = (com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher) r7     // Catch:{ all -> 0x0137 }
                r7.onSoundPlayComplete()     // Catch:{ Exception -> 0x0058 }
                goto L_0x005c
            L_0x0058:
                r7 = move-exception
                r7.printStackTrace()     // Catch:{ all -> 0x0137 }
            L_0x005c:
                int r6 = r6 + 1
                goto L_0x0046
            L_0x005f:
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r5 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                android.os.RemoteCallbackList r5 = r5.d     // Catch:{ all -> 0x0137 }
                r5.finishBroadcast()     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r5 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                android.content.Context r5 = r5.b     // Catch:{ all -> 0x0137 }
                boolean r5 = com.ximalaya.ting.android.opensdk.player.appnotification.NotificationColorUtils.a(r5)     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r6 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                android.content.Context r6 = r6.b     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater r6 = com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater.a((android.content.Context) r6)     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r7 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                android.app.NotificationManager r7 = r7.u     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r8 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                android.app.Notification r8 = r8.v     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r9 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                int r9 = r9.w     // Catch:{ all -> 0x0137 }
                r6.a((android.app.NotificationManager) r7, (android.app.Notification) r8, (int) r9, (boolean) r5)     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r5 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                r5.d()     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r5 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.model.PlayableModel r5 = r5.q     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.model.track.Track r5 = (com.ximalaya.ting.android.opensdk.model.track.Track) r5     // Catch:{ all -> 0x0137 }
                if (r5 == 0) goto L_0x00c0
                boolean r6 = r5.aN()     // Catch:{ all -> 0x0137 }
                if (r6 == 0) goto L_0x00c0
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r1 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.service.IXmPlayerStatusListener r1 = r1.G     // Catch:{ all -> 0x0137 }
                if (r1 == 0) goto L_0x00be
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r1 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.service.IXmPlayerStatusListener r1 = r1.G     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r2 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.model.PlayableModel r2 = r2.q     // Catch:{ all -> 0x0137 }
                r3 = 0
                r1.a((com.ximalaya.ting.android.opensdk.model.PlayableModel) r2, (com.ximalaya.ting.android.opensdk.model.PlayableModel) r3)     // Catch:{ all -> 0x0137 }
            L_0x00be:
                monitor-exit(r0)     // Catch:{ all -> 0x0137 }
                return
            L_0x00c0:
                if (r4 < 0) goto L_0x00cc
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r5 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl r5 = r5.mListControl     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.model.PlayableModel r5 = r5.b((int) r4)     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.model.track.Track r5 = (com.ximalaya.ting.android.opensdk.model.track.Track) r5     // Catch:{ all -> 0x0137 }
            L_0x00cc:
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService$1$1 r6 = new com.ximalaya.ting.android.opensdk.player.service.XmPlayerService$1$1     // Catch:{ all -> 0x0137 }
                r6.<init>(r4, r3)     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r3 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                boolean r3 = r3.x     // Catch:{ all -> 0x0137 }
                if (r3 != 0) goto L_0x0111
                if (r5 == 0) goto L_0x0111
                boolean r3 = com.ximalaya.ting.android.opensdk.util.BaseUtil.d()     // Catch:{ all -> 0x0137 }
                if (r3 != 0) goto L_0x00ed
                java.lang.String r3 = "schedule"
                java.lang.String r4 = r5.b()     // Catch:{ all -> 0x0137 }
                boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x0137 }
                if (r3 != 0) goto L_0x0111
            L_0x00ed:
                java.lang.String r3 = "radio"
                java.lang.String r4 = r5.b()     // Catch:{ all -> 0x0137 }
                boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x0137 }
                if (r3 != 0) goto L_0x0111
                java.lang.String r3 = "live_flv"
                java.lang.String r4 = r5.b()     // Catch:{ all -> 0x0137 }
                boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x0137 }
                if (r3 == 0) goto L_0x0106
                goto L_0x0111
            L_0x0106:
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r1 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager r1 = r1.s     // Catch:{ all -> 0x0137 }
                r3 = 4
                r1.a((com.ximalaya.ting.android.opensdk.model.track.Track) r5, (int) r3, (com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager.PlayAdsCallback) r6, (boolean) r2)     // Catch:{ all -> 0x0137 }
                goto L_0x0135
            L_0x0111:
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r2 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager r2 = r2.s     // Catch:{ all -> 0x0137 }
                r2.i()     // Catch:{ all -> 0x0137 }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0137 }
                r2.<init>()     // Catch:{ all -> 0x0137 }
                java.lang.String r3 = "play 11:"
                r2.append(r3)     // Catch:{ all -> 0x0137 }
                long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0137 }
                r2.append(r3)     // Catch:{ all -> 0x0137 }
                java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0137 }
                com.ximalaya.ting.android.opensdk.util.Logger.a((java.lang.String) r2)     // Catch:{ all -> 0x0137 }
                r6.a(r1)     // Catch:{ all -> 0x0137 }
            L_0x0135:
                monitor-exit(r0)     // Catch:{ all -> 0x0137 }
                return
            L_0x0137:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0137 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.AnonymousClass1.d():void");
        }

        public boolean a(XmPlayerException xmPlayerException) {
            Logger.a("onError XmPlayerService 475:" + System.currentTimeMillis());
            synchronized (XmPlayerService.class) {
                XmNotificationCreater.a(XmPlayerService.this.b).a(XmPlayerService.this.u, XmPlayerService.this.v, XmPlayerService.this.w, NotificationColorUtils.a(XmPlayerService.this.b));
                int beginBroadcast = XmPlayerService.this.d.beginBroadcast();
                for (int i = 0; i < beginBroadcast; i++) {
                    try {
                        ((IXmPlayerEventDispatcher) XmPlayerService.this.d.getBroadcastItem(i)).onError(xmPlayerException);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                XmPlayerService.this.d.finishBroadcast();
            }
            return false;
        }

        public void a(int i) {
            synchronized (XmPlayerService.class) {
                int beginBroadcast = XmPlayerService.this.d.beginBroadcast();
                for (int i2 = 0; i2 < beginBroadcast; i2++) {
                    try {
                        ((IXmPlayerEventDispatcher) XmPlayerService.this.d.getBroadcastItem(i2)).onBufferProgress(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                XmPlayerService.this.d.finishBroadcast();
                XmPlayerService.this.p.a(i, XmPlayerService.this.k.l(), XmPlayerService.this.k.c());
            }
        }

        public void f() {
            XmPlayerService.this.f();
            synchronized (XmPlayerService.class) {
                int beginBroadcast = XmPlayerService.this.d.beginBroadcast();
                for (int i = 0; i < beginBroadcast; i++) {
                    try {
                        ((IXmPlayerEventDispatcher) XmPlayerService.this.d.getBroadcastItem(i)).onBufferingStart();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                XmPlayerService.this.d.finishBroadcast();
            }
        }

        public void g() {
            synchronized (XmPlayerService.class) {
                int beginBroadcast = XmPlayerService.this.d.beginBroadcast();
                for (int i = 0; i < beginBroadcast; i++) {
                    try {
                        ((IXmPlayerEventDispatcher) XmPlayerService.this.d.getBroadcastItem(i)).onBufferingStop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                XmPlayerService.this.d.finishBroadcast();
            }
        }
    };
    private IXmAdsStatusListener H = new IXmAdsStatusListener() {
        private byte[] b = new byte[0];

        public void a(Advertis advertis, int i) {
            Logger.a("mAdsListener onStartPlayAds XmPlayerService 556:" + System.currentTimeMillis());
            synchronized (this.b) {
                if (XmPlayerService.this.X) {
                    boolean unused = XmPlayerService.this.X = false;
                    XmPlayerService.this.pausePlay();
                    return;
                }
                int beginBroadcast = XmPlayerService.this.f.beginBroadcast();
                for (int i2 = 0; i2 < beginBroadcast; i2++) {
                    try {
                        ((IXmAdsEventDispatcher) XmPlayerService.this.f.getBroadcastItem(i2)).onStartPlayAds(advertis, i);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                XmPlayerService.this.f.finishBroadcast();
            }
        }

        public void a() {
            Logger.a("mAdsListener onStartGetAdsInfo XmPlayerService 580:" + System.currentTimeMillis());
            synchronized (this.b) {
                int beginBroadcast = XmPlayerService.this.f.beginBroadcast();
                for (int i = 0; i < beginBroadcast; i++) {
                    try {
                        ((IXmAdsEventDispatcher) XmPlayerService.this.f.getBroadcastItem(i)).onStartGetAdsInfo();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                XmPlayerService.this.f.finishBroadcast();
            }
        }

        public void a(AdvertisList advertisList) {
            Logger.a("mAdsListener onGetAdsInfo XmPlayerService 598:" + System.currentTimeMillis());
            synchronized (this.b) {
                XmPlayerService.this.f();
                int beginBroadcast = XmPlayerService.this.f.beginBroadcast();
                for (int i = 0; i < beginBroadcast; i++) {
                    try {
                        ((IXmAdsEventDispatcher) XmPlayerService.this.f.getBroadcastItem(i)).onGetAdsInfo(advertisList);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                XmPlayerService.this.f.finishBroadcast();
            }
        }

        public void a(int i, int i2) {
            Logger.a("mAdsListener onError XmPlayerService 618:" + System.currentTimeMillis());
            synchronized (this.b) {
                int beginBroadcast = XmPlayerService.this.f.beginBroadcast();
                for (int i3 = 0; i3 < beginBroadcast; i3++) {
                    try {
                        ((IXmAdsEventDispatcher) XmPlayerService.this.f.getBroadcastItem(i3)).onError(i, i2);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                XmPlayerService.this.f.finishBroadcast();
            }
        }

        public void d() {
            Logger.a("mAdsListener onCompletePlayAds XmPlayerService 636:" + System.currentTimeMillis());
            synchronized (this.b) {
                int beginBroadcast = XmPlayerService.this.f.beginBroadcast();
                for (int i = 0; i < beginBroadcast; i++) {
                    try {
                        ((IXmAdsEventDispatcher) XmPlayerService.this.f.getBroadcastItem(i)).onCompletePlayAds();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                XmPlayerService.this.f.finishBroadcast();
            }
        }

        public void c() {
            Logger.a("mAdsListener onAdsStopBuffering XmPlayerService 654:" + System.currentTimeMillis());
            synchronized (this.b) {
                int beginBroadcast = XmPlayerService.this.f.beginBroadcast();
                for (int i = 0; i < beginBroadcast; i++) {
                    try {
                        ((IXmAdsEventDispatcher) XmPlayerService.this.f.getBroadcastItem(i)).onAdsStopBuffering();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                XmPlayerService.this.f.finishBroadcast();
            }
        }

        public void b() {
            Logger.a("mAdsListener onAdsStartBuffering XmPlayerService 672:" + System.currentTimeMillis());
            synchronized (this.b) {
                int beginBroadcast = XmPlayerService.this.f.beginBroadcast();
                for (int i = 0; i < beginBroadcast; i++) {
                    try {
                        ((IXmAdsEventDispatcher) XmPlayerService.this.f.getBroadcastItem(i)).onAdsStartBuffering();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                XmPlayerService.this.f.finishBroadcast();
            }
        }
    };
    private long I = -813934592;
    private String J = "__xm__";
    /* access modifiers changed from: private */
    public long W = 0;
    /* access modifiers changed from: private */
    public boolean X = false;
    private Handler Y;
    /* access modifiers changed from: private */
    public Runnable Z;
    private XmPlayerControl.IPlaySeekListener aa = new XmPlayerControl.IPlaySeekListener() {
        public void a(int i) {
            if (XmPlayerService.this.p != null) {
                XmPlayerService.this.p.a(i);
            }
        }
    };
    /* access modifiers changed from: private */
    public XmAdsManager.IPlayStartCallBack ab;
    /* access modifiers changed from: private */
    public Context b;
    /* access modifiers changed from: private */
    public RemoteCallbackList<IXmPlayerEventDispatcher> d = new MyRemoteCallbackList();
    /* access modifiers changed from: private */
    public RemoteCallbackList<IXmCustomDataCallBack> e = new MyRemoteCallbackList();
    /* access modifiers changed from: private */
    public RemoteCallbackList<IXmAdsEventDispatcher> f = new MyRemoteCallbackList();
    /* access modifiers changed from: private */
    public RemoteCallbackList<IXmMainDataSupportDataCallback> g = new MyRemoteCallbackList();
    private SharedPreferences h;
    private SharedPreferences i;
    public boolean isLossAudioFocus = false;
    private XmPlayerImpl j;
    /* access modifiers changed from: private */
    public XmPlayerControl k;
    /* access modifiers changed from: private */
    public XmPlayerAudioFocusControl l;
    /* access modifiers changed from: private */
    public String m;
    public Config mConfig;
    protected XmPlayListControl mListControl;
    private XmPlayerConfig n;
    /* access modifiers changed from: private */
    public XmStatisticsManager p;
    /* access modifiers changed from: private */
    public PlayableModel q;
    /* access modifiers changed from: private */
    public int r;
    /* access modifiers changed from: private */
    public XmAdsManager s;
    /* access modifiers changed from: private */
    public IXmCommonBusinessDispatcher t;
    /* access modifiers changed from: private */
    public NotificationManager u;
    /* access modifiers changed from: private */
    public Notification v;
    /* access modifiers changed from: private */
    public int w;
    /* access modifiers changed from: private */
    public boolean x = false;
    /* access modifiers changed from: private */
    public boolean y = false;
    /* access modifiers changed from: private */
    public MediaControlManager z;

    public static final Intent getIntent(Context context) {
        return new Intent(context, XmPlayerService.class);
    }

    @SuppressLint({"NewApi"})
    public void onCreate() {
        super.onCreate();
        a();
        Logger.c(f2220a, "---onCreate");
    }

    private void a() {
        o = this;
        SharedPreferencesUtil.b(this);
        if (this.D == null) {
            if (BaseUtil.d()) {
                this.D = new IDomainServerIpCallback() {
                    public String[][] a(String str) {
                        return CommonRequestForMain.a(str);
                    }

                    public String b(String str) {
                        PlayableModel playableModel;
                        String str2;
                        if (TextUtils.isEmpty(str) || (playableModel = XmPlayerService.this.getPlayableModel()) == null) {
                            return null;
                        }
                        Track track = (Track) playableModel;
                        if (!track.w() || !str.contains(XMediaPlayerConstants.f)) {
                            return null;
                        }
                        if (BaseUtil.d()) {
                            str2 = CommonRequestForMain.a(track);
                        } else {
                            str2 = PayUtil.a(track.a());
                        }
                        if (!TextUtils.isEmpty(str2)) {
                            XmPlayerService.this.k.a(str2);
                        }
                        return str2;
                    }

                    public void a(String str, String str2, String str3) {
                        CommonRequestForMain.a(str, str2, str3);
                    }
                };
            } else {
                this.D = HttpDNSUtilForOpenSDK.a(this);
            }
            StaticConfig.a(this.D);
        }
        StaticConfig.a(CommonRequest.w());
        if (this.b == null) {
            this.b = getApplicationContext();
        }
        if (this.E == null) {
            this.E = new WidgetProvider();
        }
        if (this.n == null) {
            this.n = XmPlayerConfig.a(this.b);
        }
        if (this.k == null) {
            this.k = new XmPlayerControl(this.b);
            this.k.a(this.G);
            this.k.a(this.C);
            this.k.a(this.aa);
        }
        if (this.mListControl == null) {
            this.mListControl = new XmPlayListControl();
        }
        if (this.j == null) {
            this.j = new XmPlayerImpl();
        }
        if (this.h == null) {
            this.h = getSharedPreferences(PreferenceConstantsInOpenSdk.h, 0);
        }
        if (this.i == null) {
            this.i = getSharedPreferences(PreferenceConstantsInOpenSdk.i, 0);
        }
        if (this.l == null) {
            this.l = new XmPlayerAudioFocusControl(this.b);
        }
        this.p = XmStatisticsManager.a();
        this.p.a((Context) this);
        this.s = XmAdsManager.a(this.b);
        this.s.a(this.H);
        this.u = (NotificationManager) this.b.getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(XmNotificationCreater.U, "播放通知栏", 4);
            notificationChannel.enableLights(false);
            notificationChannel.setShowBadge(false);
            notificationChannel.setSound((Uri) null, (AudioAttributes) null);
            notificationChannel.enableVibration(false);
            this.u.createNotificationChannel(notificationChannel);
        }
        if (this.z == null) {
            try {
                this.z = new MediaControlManager(this);
                this.z.a();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (this.B == null) {
            this.B = new StatToServerFactoryImplForOpen();
            StaticConfig.a(this.B);
        }
        XmSecretKeyUtil.a().a(o.getApplicationContext());
    }

    public static XmPlayerService getPlayerSrvice() {
        return (XmPlayerService) o;
    }

    public XmPlayerImpl getPlayerImpl() {
        return this.j;
    }

    public void setVolume(float f2, float f3) {
        if (this.k != null) {
            this.k.a(f2, f3);
        }
    }

    public boolean isPlaying() {
        boolean z2 = this.k != null && this.k.m() == 3;
        boolean d2 = this.s != null ? this.s.d() : false;
        if (z2 || d2) {
            return true;
        }
        return false;
    }

    public int onStartCommand(Intent intent, int i2, int i3) {
        a();
        CommonRequestForMain.a();
        return 1;
    }

    public IBinder onBind(Intent intent) {
        a();
        CommonRequestForMain.a();
        Logger.c(f2220a, "onBind " + this.j.hashCode());
        return this.j;
    }

    public void closeNotification() {
        if (this.u != null) {
            stopForeground(true);
            this.u.cancel(this.w);
            Logger.a((Object) "process closeNotification mNotificationId:" + this.w);
        }
    }

    public void onDestroy() {
        SharedPreferences sharedPreferences;
        Logger.c(f2220a, "---onDestroy");
        super.onDestroy();
        closeNotification();
        c();
        stopPlay();
        stopForeground(true);
        this.z.b();
        this.k.k();
        this.p.b();
        this.s.b();
        XmNotificationCreater.a();
        o = null;
        this.d.kill();
        this.e.kill();
        this.f.kill();
        StaticConfig.a();
        this.D = null;
        FileUtilBase.a();
        PlayCacheByLRU.b();
        CommonRequest.v();
        MediadataCrytoUtil.b();
        XmPlayerManagerForPlayer.c();
        if (BaseUtil.d() && (sharedPreferences = this.b.getSharedPreferences("plugin_share_file", 4)) != null && sharedPreferences.getBoolean("need_exit_process_play", false)) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("need_exit_process_play", false);
            boolean commit = edit.commit();
            Logger.c("ApplicationManager", "kill process play : plugin_share_file " + commit);
            Process.killProcess(Process.myPid());
            System.exit(0);
        }
    }

    private boolean a(List<? extends PlayableModel> list) {
        return this.q == null || list == null || !list.contains(this.q);
    }

    /* access modifiers changed from: protected */
    public String getDownloadUrl(Track track) {
        if (!track.aO()) {
            return "";
        }
        String aq = track.aq();
        if (TextUtils.isEmpty(aq)) {
            try {
                if (this.t != null) {
                    aq = this.t.getDownloadPlayPath(track);
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
        if (TextUtils.isEmpty(aq)) {
            return null;
        }
        try {
            if (!new File(aq).exists()) {
                return null;
            }
            if (aq.contains(XMediaPlayerConstants.t)) {
                try {
                    this.t.isOldTrackDownload(track);
                    return "null";
                } catch (Throwable unused) {
                    aq = "";
                }
            }
            Logger.a("XmPlayerService:method=getTrackUrl:path=" + aq);
            return aq;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public String getTrackUrl(Track track) {
        String str;
        String downloadUrl = getDownloadUrl(track);
        if (!TextUtils.isEmpty(downloadUrl)) {
            return downloadUrl;
        }
        boolean d2 = NetworkType.d(this.b);
        if (d2) {
            d2 = !this.n.c();
        }
        if (this.mListControl.c() == 2) {
            if (d2) {
                if (TextUtils.isEmpty(track.aJ())) {
                    str = track.af();
                } else {
                    str = track.ah();
                }
                if (!TextUtils.isEmpty(str)) {
                    return str;
                }
                String af = track.af();
                if (!TextUtils.isEmpty(af)) {
                    return af;
                }
                String ah = track.ah();
                if (!TextUtils.isEmpty(ah)) {
                    return ah;
                }
                String aJ = track.aJ();
                if (!TextUtils.isEmpty(aJ)) {
                    return aJ;
                }
                String ab2 = track.ab();
                if (TextUtils.isEmpty(ab2)) {
                    return track.ad();
                }
                return ab2;
            }
            String aJ2 = track.aJ();
            if (!TextUtils.isEmpty(aJ2)) {
                return aJ2;
            }
            String ah2 = track.ah();
            if (!TextUtils.isEmpty(ah2)) {
                return ah2;
            }
            String af2 = track.af();
            if (!TextUtils.isEmpty(af2)) {
                return af2;
            }
            String ad = track.ad();
            if (TextUtils.isEmpty(ad)) {
                return track.ab();
            }
            return ad;
        } else if (this.mListControl.c() != 3) {
            return downloadUrl;
        } else {
            if (this.n.d()) {
                if (d2) {
                    String aB = track.aB();
                    if (TextUtils.isEmpty(aB)) {
                        return track.aD();
                    }
                    return aB;
                }
                String aD = track.aD();
                if (TextUtils.isEmpty(aD)) {
                    return track.aB();
                }
                return aD;
            } else if (d2) {
                String aA = track.aA();
                if (TextUtils.isEmpty(aA)) {
                    return track.aC();
                }
                return aA;
            } else {
                String aC = track.aC();
                return TextUtils.isEmpty(aC) ? track.aA() : aC;
            }
        }
    }

    private String a(Radio radio) {
        boolean d2 = NetworkType.d(this.b);
        if (d2) {
            d2 = !this.n.c();
        }
        if (this.n.d()) {
            if (d2) {
                String q2 = radio.q();
                if (TextUtils.isEmpty(q2)) {
                    return radio.s();
                }
                return q2;
            }
            String s2 = radio.s();
            if (TextUtils.isEmpty(s2)) {
                return radio.q();
            }
            return s2;
        } else if (d2) {
            String p2 = radio.p();
            if (TextUtils.isEmpty(p2)) {
                return radio.r();
            }
            return p2;
        } else {
            String p3 = radio.p();
            return TextUtils.isEmpty(p3) ? radio.r() : p3;
        }
    }

    private int a(long j2) {
        if (!this.n.a() || j2 <= 0) {
            return -1;
        }
        try {
            SharedPreferences sharedPreferences = this.h;
            return sharedPreferences.getInt("" + j2, -1);
        } catch (Exception unused) {
            SharedPreferences sharedPreferences2 = this.h;
            return (int) sharedPreferences2.getLong("" + j2, -1);
        }
    }

    @SuppressLint({"NewApi"})
    public void saveSoundHistoryPos(long j2, int i2) {
        if (j2 > 0) {
            SharedPreferences.Editor edit = this.h.edit();
            edit.putInt("" + j2, i2);
            if (Build.VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
        }
    }

    public String getSoundHistoryPos(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String[] split = str.split(",");
            for (int i2 = 0; i2 < split.length; i2++) {
                split[i2] = String.valueOf(a(Long.valueOf(split[i2]).longValue()));
            }
            return TextUtils.join(",", split);
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void a(Track track) {
        if (track != null && "track".equals(track.b()) && track.ao() != null) {
            SharedPreferences.Editor edit = this.i.edit();
            Map<String, ?> all = this.i.getAll();
            if (all != null && all.size() > 500) {
                for (Map.Entry next : all.entrySet()) {
                    Object value = next.getValue();
                    if (value != null) {
                        if (System.currentTimeMillis() - Long.parseLong(value.toString().split(this.J)[1]) > this.I) {
                            edit.remove((String) next.getKey());
                        }
                    }
                }
            }
            if (track.ao() != null) {
                edit.putString("" + track.ao().d(), new GsonBuilder().serializeSpecialFloatingPointValues().create().toJson((Object) track) + this.J + System.currentTimeMillis());
                if (Build.VERSION.SDK_INT >= 9) {
                    edit.apply();
                } else {
                    edit.commit();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public String a(String str) {
        try {
            String string = this.i.getString(str, (String) null);
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            return string.split(this.J)[0];
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void a(final Track track, final boolean z2) {
        Logger.a("playTrack 13:" + System.currentTimeMillis());
        String downloadUrl = getDownloadUrl(track);
        if (!(track.w() && !track.B()) || !TextUtils.isEmpty(downloadUrl)) {
            Logger.a("playTrack 16:" + System.currentTimeMillis());
            if (TextUtils.isEmpty(downloadUrl)) {
                downloadUrl = getTrackUrl(track);
            }
            if (!TextUtils.isEmpty(downloadUrl) || (track.aY() == 4 && track.u())) {
                a(downloadUrl, track, z2);
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("track_id", track.a() + "");
            CommonRequest.W(hashMap, new IDataCallBack<TrackBaseInfo>() {
                public void a(@Nullable TrackBaseInfo trackBaseInfo) {
                    XmPlayerService.this.a(XmPlayerService.this.a(trackBaseInfo), track, z2);
                }

                public void a(int i, String str) {
                    XmPlayerService.this.a((String) null, track, z2);
                }
            });
            return;
        }
        if (this.G != null) {
            this.G.f();
        }
        CommonRequest.a((Map<String, String>) new HashMap(), (IDataCallBack<String>) new IDataCallBack<String>() {
            public void a(String str) {
                Logger.a("playTrack 14:" + System.currentTimeMillis());
                if (XmPlayerService.this.G != null) {
                    XmPlayerService.this.G.g();
                }
                XmPlayerService.this.a(str, track, z2);
            }

            public void a(int i, String str) {
                Logger.a("playTrack 15:" + System.currentTimeMillis());
                if (XmPlayerService.this.G != null) {
                    XmPlayerService.this.G.g();
                }
                XmPlayerService.this.a((String) null, track, z2);
                if (i == 726) {
                    track.f(false);
                    XmPlayerService.this.G.a((PlayableModel) track, (PlayableModel) null);
                }
                Logger.a((Object) "playTrack updateTrackForPlay error code:" + i + " msg:" + str);
            }
        }, track);
    }

    /* access modifiers changed from: private */
    public String a(TrackBaseInfo trackBaseInfo) {
        boolean d2 = NetworkType.d(this.b);
        if (d2) {
            d2 = !this.n.c();
        }
        if (d2) {
            String g2 = trackBaseInfo.g();
            if (!TextUtils.isEmpty(g2)) {
                return g2;
            }
            String i2 = trackBaseInfo.i();
            if (!TextUtils.isEmpty(i2)) {
                return i2;
            }
            String k2 = trackBaseInfo.k();
            if (TextUtils.isEmpty(k2)) {
                return trackBaseInfo.n();
            }
            return k2;
        }
        String i3 = trackBaseInfo.i();
        if (!TextUtils.isEmpty(i3)) {
            return i3;
        }
        String g3 = trackBaseInfo.g();
        if (!TextUtils.isEmpty(g3)) {
            return g3;
        }
        String k3 = trackBaseInfo.k();
        return TextUtils.isEmpty(k3) ? trackBaseInfo.n() : k3;
    }

    /* access modifiers changed from: private */
    public void a(String str, Track track, boolean z2) {
        boolean z3;
        int a2 = a(track.a());
        if (a2 < 0 || track.aN() || a2 > track.W() * 1000) {
            a2 = 0;
        }
        if (TextUtils.isEmpty(str)) {
            str = getTrackUrl(track);
        }
        if (z2) {
            z3 = this.k.c(str, a2);
        } else {
            z3 = this.k.a(str, a2);
        }
        if (!z3) {
            this.q = null;
        }
    }

    public boolean playPre() {
        Logger.a("playPre:" + Arrays.toString(Thread.currentThread().getStackTrace()) + ":playPre");
        int k2 = this.mListControl.k();
        if (k2 >= 0) {
            return a(k2, true, 2);
        }
        return false;
    }

    public boolean playNext() {
        int a2 = this.mListControl.a(true);
        Logger.a("playNext index:" + a2);
        if (a2 >= 0) {
            return a(a2, true, 2);
        }
        return false;
    }

    public boolean playCurrent() {
        int l2 = this.mListControl.l();
        if (l2 >= 0) {
            return play(l2);
        }
        return false;
    }

    public boolean pausePlay() {
        Logger.a("pausePlay0:" + Arrays.toString(Thread.currentThread().getStackTrace()) + ":0pausePlay");
        if (this.s.g()) {
            this.s.f();
            if (this.G != null) {
                this.G.b();
            }
            return true;
        }
        if (this.l != null) {
            this.l.a(true);
        }
        return this.k.i();
    }

    public boolean stopPlay() {
        Logger.a("stopPlay:" + Arrays.toString(Thread.currentThread().getStackTrace()) + ":stopPlay");
        if (this.l != null) {
            this.l.c();
            this.l.a(true);
        }
        this.q = null;
        return this.k.j();
    }

    public void setSoundTouchAllParams(float f2, float f3, float f4) {
        if (this.k != null) {
            this.k.a(f2, f3, f4);
        }
    }

    public void requestAudioFocusControl() {
        this.l.b();
    }

    public boolean startPlay(boolean z2) {
        Logger.a("startPlay:" + Arrays.toString(Thread.currentThread().getStackTrace()) + ":startPlay");
        this.l.b();
        if (this.s != null && this.s.g()) {
            Logger.a("startPlay 0");
            int k2 = this.s.k();
            if (k2 != 1 && k2 != 3) {
                Logger.a("startPlay 2");
            } else if (this.s.a() != null) {
                this.s.e();
                Logger.a("startPlay 1");
                if (this.G != null) {
                    this.G.a();
                }
                return true;
            }
            return false;
        } else if (this.k == null) {
            Logger.a("startPlay 3");
            return false;
        } else if (!z2 || this.k.m() != 9) {
            boolean c2 = this.k.c(true);
            Logger.a("startPlay 5 ret:" + c2);
            if (c2) {
                return c2;
            }
            int l2 = this.mListControl.l();
            Logger.a("startPlay 6 index:" + l2);
            if (l2 < 0) {
                return c2;
            }
            Logger.a("startPlay 7");
            return play(l2);
        } else {
            Logger.a("startPlay 4");
            this.k.b(true);
            return false;
        }
    }

    public boolean startPlay() {
        return startPlay(false);
    }

    public boolean play(int i2) {
        return play(i2, true);
    }

    public boolean play(int i2, boolean z2) {
        return a(i2, z2, 0);
    }

    @Deprecated
    public boolean playRadio(Radio radio) {
        this.l.b();
        if (this.n.d() || radio == null) {
            return false;
        }
        try {
            if (radio.equals(this.q)) {
                return false;
            }
            this.k.j();
            this.mListControl.a(radio);
            this.G.a(this.q, (PlayableModel) radio);
            this.k.c(a(radio), 0);
            this.q = radio;
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00cc A[Catch:{ Exception -> 0x019d }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ce A[Catch:{ Exception -> 0x019d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(int r11, boolean r12, int r13) {
        /*
            r10 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "play 0:"
            r0.append(r1)
            long r1 = java.lang.System.currentTimeMillis()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.ximalaya.ting.android.opensdk.util.Logger.a((java.lang.String) r0)
            r0 = 0
            r1 = 0
            if (r11 < 0) goto L_0x01be
            com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl r2 = r10.mListControl
            int r2 = r2.i()
            if (r11 >= r2) goto L_0x01be
            com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl r2 = r10.mListControl     // Catch:{ Exception -> 0x019d }
            r2.a((int) r11)     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl r2 = r10.mListControl     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.model.PlayableModel r11 = r2.b((int) r11)     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.model.track.Track r11 = (com.ximalaya.ting.android.opensdk.model.track.Track) r11     // Catch:{ Exception -> 0x019d }
            if (r11 != 0) goto L_0x0055
            java.lang.String r11 = "XmPlayerService"
            java.lang.String r12 = "Get current model return null, play fail"
            com.ximalaya.ting.android.opensdk.util.Logger.e(r11, r12)     // Catch:{ Exception -> 0x019d }
            r10.q = r0     // Catch:{ Exception -> 0x019d }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x019d }
            r11.<init>()     // Catch:{ Exception -> 0x019d }
            java.lang.String r12 = "play 1:"
            r11.append(r12)     // Catch:{ Exception -> 0x019d }
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x019d }
            r11.append(r12)     // Catch:{ Exception -> 0x019d }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.util.Logger.a((java.lang.String) r11)     // Catch:{ Exception -> 0x019d }
            return r1
        L_0x0055:
            com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl r2 = r10.mListControl     // Catch:{ Exception -> 0x019d }
            java.util.List r2 = r2.d()     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.model.PlayableModel r3 = r10.q     // Catch:{ Exception -> 0x019d }
            int r2 = r2.indexOf(r3)     // Catch:{ Exception -> 0x019d }
            if (r2 < 0) goto L_0x0070
            com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl r3 = r10.mListControl     // Catch:{ Exception -> 0x019d }
            java.util.List r3 = r3.d()     // Catch:{ Exception -> 0x019d }
            java.lang.Object r2 = r3.get(r2)     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.model.PlayableModel r2 = (com.ximalaya.ting.android.opensdk.model.PlayableModel) r2     // Catch:{ Exception -> 0x019d }
            goto L_0x0071
        L_0x0070:
            r2 = r0
        L_0x0071:
            if (r2 != 0) goto L_0x0075
            com.ximalaya.ting.android.opensdk.model.PlayableModel r2 = r10.q     // Catch:{ Exception -> 0x019d }
        L_0x0075:
            boolean r3 = r2 instanceof com.ximalaya.ting.android.opensdk.model.track.Track     // Catch:{ Exception -> 0x019d }
            if (r3 == 0) goto L_0x00a1
            r3 = r2
            com.ximalaya.ting.android.opensdk.model.track.Track r3 = (com.ximalaya.ting.android.opensdk.model.track.Track) r3     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.model.album.SubordinatedAlbum r3 = r3.ao()     // Catch:{ Exception -> 0x019d }
            if (r3 == 0) goto L_0x00a1
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x019d }
            r3.<init>()     // Catch:{ Exception -> 0x019d }
            java.lang.String r4 = "Test statistic track"
            r3.append(r4)     // Catch:{ Exception -> 0x019d }
            r4 = r2
            com.ximalaya.ting.android.opensdk.model.track.Track r4 = (com.ximalaya.ting.android.opensdk.model.track.Track) r4     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.model.album.SubordinatedAlbum r4 = r4.ao()     // Catch:{ Exception -> 0x019d }
            java.lang.String r4 = r4.a()     // Catch:{ Exception -> 0x019d }
            r3.append(r4)     // Catch:{ Exception -> 0x019d }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.util.Logger.a((java.lang.Object) r3)     // Catch:{ Exception -> 0x019d }
        L_0x00a1:
            com.ximalaya.ting.android.opensdk.model.PlayableModel r3 = r10.q     // Catch:{ Exception -> 0x019d }
            r4 = 1
            if (r3 == 0) goto L_0x00b1
            com.ximalaya.ting.android.opensdk.model.PlayableModel r3 = r10.q     // Catch:{ Exception -> 0x019d }
            boolean r3 = r11.equals(r3)     // Catch:{ Exception -> 0x019d }
            if (r3 != 0) goto L_0x00af
            goto L_0x00b1
        L_0x00af:
            r3 = 0
            goto L_0x00b2
        L_0x00b1:
            r3 = 1
        L_0x00b2:
            com.ximalaya.ting.android.opensdk.model.PlayableModel r5 = r10.q     // Catch:{ Exception -> 0x019d }
            if (r5 == 0) goto L_0x0174
            com.ximalaya.ting.android.opensdk.model.PlayableModel r5 = r10.q     // Catch:{ Exception -> 0x019d }
            boolean r5 = r11.equals(r5)     // Catch:{ Exception -> 0x019d }
            if (r5 == 0) goto L_0x0174
            boolean r5 = r11.D()     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.model.PlayableModel r6 = r10.q     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.model.track.Track r6 = (com.ximalaya.ting.android.opensdk.model.track.Track) r6     // Catch:{ Exception -> 0x019d }
            boolean r6 = r6.D()     // Catch:{ Exception -> 0x019d }
            if (r5 == r6) goto L_0x00ce
            goto L_0x0174
        L_0x00ce:
            com.ximalaya.ting.android.opensdk.model.PlayableModel r5 = r10.q     // Catch:{ Exception -> 0x019d }
            if (r5 == 0) goto L_0x00ff
            com.ximalaya.ting.android.opensdk.model.album.SubordinatedAlbum r5 = r11.ao()     // Catch:{ Exception -> 0x019d }
            if (r5 == 0) goto L_0x00ff
            com.ximalaya.ting.android.opensdk.model.PlayableModel r5 = r10.q     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.model.track.Track r5 = (com.ximalaya.ting.android.opensdk.model.track.Track) r5     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.model.album.SubordinatedAlbum r5 = r5.ao()     // Catch:{ Exception -> 0x019d }
            if (r5 == 0) goto L_0x00ff
            com.ximalaya.ting.android.opensdk.model.album.SubordinatedAlbum r5 = r11.ao()     // Catch:{ Exception -> 0x019d }
            long r5 = r5.d()     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.model.PlayableModel r7 = r10.q     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.model.track.Track r7 = (com.ximalaya.ting.android.opensdk.model.track.Track) r7     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.model.album.SubordinatedAlbum r7 = r7.ao()     // Catch:{ Exception -> 0x019d }
            long r7 = r7.d()     // Catch:{ Exception -> 0x019d }
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x00ff
            com.ximalaya.ting.android.opensdk.player.service.IXmPlayerStatusListener r5 = r10.G     // Catch:{ Exception -> 0x019d }
            r5.a((com.ximalaya.ting.android.opensdk.model.PlayableModel) r2, (com.ximalaya.ting.android.opensdk.model.PlayableModel) r11)     // Catch:{ Exception -> 0x019d }
        L_0x00ff:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x019d }
            r2.<init>()     // Catch:{ Exception -> 0x019d }
            java.lang.String r5 = "play 3:"
            r2.append(r5)     // Catch:{ Exception -> 0x019d }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x019d }
            r2.append(r5)     // Catch:{ Exception -> 0x019d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.util.Logger.a((java.lang.String) r2)     // Catch:{ Exception -> 0x019d }
            r10.q = r11     // Catch:{ Exception -> 0x019d }
            if (r12 == 0) goto L_0x015b
            com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager r11 = r10.s     // Catch:{ Exception -> 0x019d }
            boolean r11 = r11.g()     // Catch:{ Exception -> 0x019d }
            if (r11 == 0) goto L_0x014c
            com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager r11 = r10.s     // Catch:{ Exception -> 0x019d }
            int r11 = r11.k()     // Catch:{ Exception -> 0x019d }
            if (r11 == r4) goto L_0x012e
            r12 = 3
            if (r11 != r12) goto L_0x0133
        L_0x012e:
            com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager r11 = r10.s     // Catch:{ Exception -> 0x019d }
            r11.e()     // Catch:{ Exception -> 0x019d }
        L_0x0133:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x019d }
            r11.<init>()     // Catch:{ Exception -> 0x019d }
            java.lang.String r12 = "play 4:"
            r11.append(r12)     // Catch:{ Exception -> 0x019d }
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x019d }
            r11.append(r12)     // Catch:{ Exception -> 0x019d }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.util.Logger.a((java.lang.String) r11)     // Catch:{ Exception -> 0x019d }
            return r1
        L_0x014c:
            com.ximalaya.ting.android.opensdk.player.service.XmPlayerControl r11 = r10.k     // Catch:{ Exception -> 0x019d }
            boolean r11 = r11.c((boolean) r4)     // Catch:{ Exception -> 0x019d }
            if (r11 != 0) goto L_0x015a
            com.ximalaya.ting.android.opensdk.model.PlayableModel r11 = r10.q     // Catch:{ Exception -> 0x019d }
            boolean r11 = r10.a((com.ximalaya.ting.android.opensdk.model.PlayableModel) r11, (boolean) r12, (int) r13, (boolean) r3)     // Catch:{ Exception -> 0x019d }
        L_0x015a:
            return r11
        L_0x015b:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x019d }
            r11.<init>()     // Catch:{ Exception -> 0x019d }
            java.lang.String r12 = "play 5:"
            r11.append(r12)     // Catch:{ Exception -> 0x019d }
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x019d }
            r11.append(r12)     // Catch:{ Exception -> 0x019d }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.util.Logger.a((java.lang.String) r11)     // Catch:{ Exception -> 0x019d }
            return r1
        L_0x0174:
            com.ximalaya.ting.android.opensdk.player.service.XmPlayerControl r4 = r10.k     // Catch:{ Exception -> 0x019d }
            r4.d((boolean) r1)     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.player.service.IXmPlayerStatusListener r4 = r10.G     // Catch:{ Exception -> 0x019d }
            r4.a((com.ximalaya.ting.android.opensdk.model.PlayableModel) r2, (com.ximalaya.ting.android.opensdk.model.PlayableModel) r11)     // Catch:{ Exception -> 0x019d }
            r10.q = r11     // Catch:{ Exception -> 0x019d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x019d }
            r2.<init>()     // Catch:{ Exception -> 0x019d }
            java.lang.String r4 = "play 2:"
            r2.append(r4)     // Catch:{ Exception -> 0x019d }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x019d }
            r2.append(r4)     // Catch:{ Exception -> 0x019d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x019d }
            com.ximalaya.ting.android.opensdk.util.Logger.a((java.lang.String) r2)     // Catch:{ Exception -> 0x019d }
            boolean r11 = r10.a((com.ximalaya.ting.android.opensdk.model.PlayableModel) r11, (boolean) r12, (int) r13, (boolean) r3)     // Catch:{ Exception -> 0x019d }
            return r11
        L_0x019d:
            r11 = move-exception
            r11.printStackTrace()
            r10.q = r0
            java.lang.String r12 = "play_info"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r0 = "play(0):"
            r13.append(r0)
            java.lang.String r11 = r11.toString()
            r13.append(r11)
            java.lang.String r11 = r13.toString()
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.String) r12, (java.lang.String) r11)
            goto L_0x01e4
        L_0x01be:
            java.lang.String r12 = "XmPlayerService"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r2 = "Index Out Of Bound, index:"
            r13.append(r2)
            r13.append(r11)
            java.lang.String r11 = ", total:"
            r13.append(r11)
            com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl r11 = r10.mListControl
            int r11 = r11.i()
            r13.append(r11)
            java.lang.String r11 = r13.toString()
            com.ximalaya.ting.android.opensdk.util.Logger.e(r12, r11)
            r10.q = r0
        L_0x01e4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.a(int, boolean, int):boolean");
    }

    public void setPlayList(Map<String, String> map, List<Track> list) {
        this.mListControl.a(map, list);
    }

    public int getPlayListSize() {
        List<Track> d2 = this.mListControl.d();
        if (d2 == null) {
            return 0;
        }
        return d2.size();
    }

    public synchronized boolean isLossAudioFocus() {
        return this.isLossAudioFocus;
    }

    public synchronized void setLossAudioFocus(boolean z2) {
        this.isLossAudioFocus = z2;
    }

    private boolean a(PlayableModel playableModel, boolean z2, int i2, boolean z3) throws Exception {
        setLossAudioFocus(false);
        if (z2) {
            this.l.b();
        }
        if (!z2) {
            boolean a2 = NotificationColorUtils.a(this.b);
            XmNotificationCreater.a(this.b).a(this.mListControl, this.u, this.v, this.w, a2);
            XmNotificationCreater.a(this.b).a(this.u, this.v, this.w, a2);
        }
        Logger.a("play 6_0 mPlayerControl.resetMediaPlayer:" + System.currentTimeMillis());
        this.k.g();
        if (playableModel instanceof Track) {
            final Track track = (Track) playableModel;
            if (z2) {
                Logger.a("play 6:" + System.currentTimeMillis());
                AnonymousClass6 r10 = new XmAdsManager.PlayAdsCallback() {
                    public void a(boolean z) {
                        Logger.a("play 7:" + System.currentTimeMillis());
                        try {
                            if (XmPlayerService.this.isLossAudioFocus()) {
                                XmPlayerService.this.setLossAudioFocus(false);
                                XmPlayerService.this.a(track, false);
                                if (XmPlayerService.this.G != null) {
                                    XmPlayerService.this.G.b();
                                    return;
                                }
                                return;
                            }
                            XmPlayerService.this.a(track, z);
                        } catch (Exception e) {
                            e.printStackTrace();
                            PlayableModel unused = XmPlayerService.this.q = null;
                            Logger.a("play 8:" + System.currentTimeMillis());
                            CdnUtil.a(CdnConstants.q, "playAdsCallback:" + e.toString());
                        }
                    }
                };
                if (this.x || ((!BaseUtil.d() && "schedule".equals(playableModel.b())) || "radio".equals(playableModel.b()) || PlayableModel.d.equals(playableModel.b()) || !z3)) {
                    this.s.i();
                    Logger.a("play 11:" + System.currentTimeMillis());
                    r10.a(true);
                } else {
                    Logger.a("play 12:" + System.currentTimeMillis());
                    this.s.a(track, i2, (XmAdsManager.PlayAdsCallback) r10, false);
                }
            } else {
                Logger.a("play 9:" + System.currentTimeMillis());
                try {
                    a(track, false);
                } catch (Exception e2) {
                    Logger.a("play 10:" + System.currentTimeMillis());
                    this.q = null;
                    e2.printStackTrace();
                    CdnUtil.a(CdnConstants.q, "playTrack:" + e2.toString());
                }
            }
            return true;
        } else if (!(playableModel instanceof Radio)) {
            return false;
        } else {
            this.k.b(a((Radio) playableModel), 0);
            return true;
        }
    }

    class XmPlayerImpl extends IXmPlayer.Stub {
        /* access modifiers changed from: private */
        public long lastRequestTime;

        public int getDefultPageSize() throws RemoteException {
            return 20;
        }

        XmPlayerImpl() {
        }

        public void setSoundTouchAllParams(float f, float f2, float f3) throws RemoteException {
            if (XmPlayerService.getPlayerSrvice() != null) {
                XmPlayerService.getPlayerSrvice().setSoundTouchAllParams(f, f2, f3);
            }
        }

        public boolean stopPlay() throws RemoteException {
            if (XmPlayerService.getPlayerSrvice() == null) {
                return false;
            }
            return XmPlayerService.getPlayerSrvice().stopPlay();
        }

        public boolean playPre() throws RemoteException {
            if (XmPlayerService.getPlayerSrvice() == null) {
                return false;
            }
            return XmPlayerService.getPlayerSrvice().playPre();
        }

        public boolean playNext() throws RemoteException {
            if (XmPlayerService.getPlayerSrvice() == null) {
                return false;
            }
            return XmPlayerService.getPlayerSrvice().playNext();
        }

        public boolean setPlayIndex(int i) throws RemoteException {
            if (XmPlayerService.getPlayerSrvice() == null) {
                return false;
            }
            return XmPlayerService.getPlayerSrvice().play(i, false);
        }

        public boolean play(int i) throws RemoteException {
            if (XmPlayerService.getPlayerSrvice() == null) {
                return false;
            }
            return XmPlayerService.getPlayerSrvice().play(i);
        }

        public boolean pausePlay() throws RemoteException {
            if (XmPlayerService.getPlayerSrvice() == null) {
                return false;
            }
            return XmPlayerService.getPlayerSrvice().pausePlay();
        }

        public boolean startPlay() throws RemoteException {
            if (XmPlayerService.getPlayerSrvice() == null) {
                return false;
            }
            return XmPlayerService.getPlayerSrvice().startPlay();
        }

        public void registePlayerListener(IXmPlayerEventDispatcher iXmPlayerEventDispatcher) throws RemoteException {
            Logger.c(XmPlayerService.f2220a, "Process " + Binder.getCallingPid() + "has register PlayerListener");
            if (iXmPlayerEventDispatcher != null && XmPlayerService.this.d != null) {
                XmPlayerService.this.d.register(iXmPlayerEventDispatcher, new MyRemoteCallbackList.ProcessCookie(Binder.getCallingPid(), Binder.getCallingUid()));
            }
        }

        public void unregistePlayerListener(IXmPlayerEventDispatcher iXmPlayerEventDispatcher) throws RemoteException {
            if (iXmPlayerEventDispatcher != null && XmPlayerService.this.d != null) {
                XmPlayerService.this.d.unregister(iXmPlayerEventDispatcher);
            }
        }

        public void setPlayMode(String str) throws RemoteException {
            if (XmPlayerService.this.mListControl != null) {
                XmPlayerService.this.mListControl.a(XmPlayListControl.PlayMode.valueOf(str));
            }
        }

        public String getPlayMode() throws RemoteException {
            return XmPlayerService.this.mListControl != null ? XmPlayerService.this.mListControl.b().toString() : "";
        }

        public void setPlayList(Map map, List<Track> list) throws RemoteException {
            if (XmPlayerService.getPlayerSrvice() != null) {
                XmPlayerService.getPlayerSrvice().setPlayList(map, list);
            }
        }

        public void addPlayList(List<Track> list) throws RemoteException {
            if (XmPlayerService.this.mListControl != null) {
                XmPlayerService.this.mListControl.a(list);
            }
        }

        public int getPlayerStatus() throws RemoteException {
            if (XmPlayerService.this.s == null || XmPlayerService.this.k == null) {
                return 7;
            }
            if (XmPlayerService.this.s.g()) {
                return 3;
            }
            return XmPlayerService.this.k.m();
        }

        public int getCurrIndex() throws RemoteException {
            if (XmPlayerService.this.mListControl != null) {
                return XmPlayerService.this.mListControl.l();
            }
            return -1;
        }

        public Track getTrack(int i) throws RemoteException {
            return (Track) XmPlayerService.this.mListControl.b(i);
        }

        public int getPlaySourceType() throws RemoteException {
            return XmPlayerService.this.mListControl.c();
        }

        public Radio getRadio() throws RemoteException {
            return XmPlayerService.this.mListControl.g();
        }

        public boolean seekTo(int i) throws RemoteException {
            if (!XmPlayerService.this.s.g() && XmPlayerService.this.mListControl.c() != 3) {
                return XmPlayerService.this.k.a(i);
            }
            return false;
        }

        public int getDuration() throws RemoteException {
            return XmPlayerService.this.k.l();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
            r0 = r3.this$0.mListControl.l();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean hasPreSound() throws android.os.RemoteException {
            /*
                r3 = this;
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r0 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this
                com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl r0 = r0.mListControl
                int r0 = r0.i()
                r1 = 1
                r2 = 0
                if (r0 > r1) goto L_0x000d
                return r2
            L_0x000d:
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r0 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this
                com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl r0 = r0.mListControl
                int r0 = r0.l()
                if (r0 > 0) goto L_0x0018
                return r2
            L_0x0018:
                int r0 = r0 + -1
                if (r0 < 0) goto L_0x001d
                return r1
            L_0x001d:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.XmPlayerImpl.hasPreSound():boolean");
        }

        public boolean hasNextSound() throws RemoteException {
            int i = XmPlayerService.this.mListControl.i();
            if (i <= 1) {
                return false;
            }
            return XmPlayerService.this.mListControl.l() + 1 < i || XmPlayerService.this.mListControl.a();
        }

        public void setAppSecret(String str) throws RemoteException {
            Logger.c(XmPlayerService.f2220a, "setAppSecret " + str);
            String unused = XmPlayerService.this.m = str;
            CommonRequest.a().a(XmPlayerService.this.b, XmPlayerService.this.m);
        }

        public List<Track> getPlayList(int i) throws RemoteException {
            List<Track> d = XmPlayerService.this.mListControl.d();
            if (d == null || d.size() < 30) {
                return d;
            }
            int size = d.size();
            int i2 = i * 30;
            int i3 = i2 + 30;
            if (size <= i2) {
                return null;
            }
            if (i3 > size) {
                i3 = i2 + (size % 30);
            }
            return d.subList(i2, i3);
        }

        public void setNotification(int i, Notification notification) throws RemoteException {
            if (notification != null) {
                try {
                    if (XmPlayerService.o != null) {
                        Logger.c(XmPlayerService.f2220a, "setNotification");
                        XmPlayerService.o.startForeground(i, notification);
                        Notification unused = XmPlayerService.this.v = notification;
                        int unused2 = XmPlayerService.this.w = i;
                        if (XmPlayerService.this.b != null && XmPlayerService.this.mListControl != null && XmPlayerService.this.u != null) {
                            boolean a2 = NotificationColorUtils.a(XmPlayerService.this.b);
                            XmNotificationCreater.a(XmPlayerService.this.b).a(XmPlayerService.this.mListControl, XmPlayerService.this.u, XmPlayerService.this.v, XmPlayerService.this.w, a2);
                            XmNotificationCreater.a(XmPlayerService.this.b).a(XmPlayerService.this.u, XmPlayerService.this.v, XmPlayerService.this.w, a2);
                        }
                    }
                } catch (Exception e) {
                    CdnUtil.a(CdnConstants.q, "setNotification:" + e.toString());
                }
            }
        }

        public boolean playRadio(Radio radio) throws RemoteException {
            if (XmPlayerService.getPlayerSrvice() == null) {
                return false;
            }
            return XmPlayerService.getPlayerSrvice().playRadio(radio);
        }

        public void registeAdsListener(IXmAdsEventDispatcher iXmAdsEventDispatcher) throws RemoteException {
            Logger.c(XmPlayerService.f2220a, "Process " + Binder.getCallingPid() + "has register AdsListener");
            if (iXmAdsEventDispatcher != null) {
                XmPlayerService.this.f.register(iXmAdsEventDispatcher, new MyRemoteCallbackList.ProcessCookie(Binder.getCallingPid(), Binder.getCallingUid()));
            }
        }

        public void unregisteAdsListener(IXmAdsEventDispatcher iXmAdsEventDispatcher) throws RemoteException {
            if (iXmAdsEventDispatcher != null) {
                XmPlayerService.this.f.unregister(iXmAdsEventDispatcher);
            }
        }

        public void setPageSize(int i) throws RemoteException {
            if (CommonRequest.a().q() != i) {
                CommonRequest.a().b(i);
            }
        }

        public boolean isOnlineSource() throws RemoteException {
            if (XmPlayerService.this.k != null) {
                return XmPlayerService.this.k.e();
            }
            return false;
        }

        public void clearPlayCache() throws RemoteException {
            if (XmPlayerService.this.k != null) {
                PlayerUtil.c(XmPlayerService.this.k.d());
            }
        }

        public void registeCommonBusinessListener(IXmCommonBusinessDispatcher iXmCommonBusinessDispatcher) throws RemoteException {
            IXmCommonBusinessDispatcher unused = XmPlayerService.this.t = iXmCommonBusinessDispatcher;
        }

        public int getPlayListSize() throws RemoteException {
            if (XmPlayerService.getPlayerSrvice() == null) {
                return 0;
            }
            return XmPlayerService.getPlayerSrvice().getPlayListSize();
        }

        public Map<String, String> getParam() throws RemoteException {
            return XmPlayerService.this.mListControl.e();
        }

        public boolean isPlaying() throws RemoteException {
            if (XmPlayerService.getPlayerSrvice() == null) {
                return false;
            }
            return XmPlayerService.getPlayerSrvice().isPlaying();
        }

        public boolean isAdsActive() throws RemoteException {
            if (XmPlayerService.this.s != null) {
                return XmPlayerService.this.s.g();
            }
            return false;
        }

        public void getNextPlayList() throws RemoteException {
            XmPlayerService.this.mListControl.b(false);
        }

        public void getPrePlayList() throws RemoteException {
            XmPlayerService.this.mListControl.c(false);
        }

        public void setPlayListChangeListener(IXmDataCallback iXmDataCallback) throws RemoteException {
            XmPlayerService.this.mListControl.a(iXmDataCallback);
        }

        public boolean permutePlayList() throws RemoteException {
            return XmPlayerService.this.mListControl.p();
        }

        public void setProxy(String str, int i, String str2, Map map) throws RemoteException {
            Config config = new Config();
            config.f = str;
            config.g = i;
            config.i = str2;
            config.n = map;
            setProxyNew(config);
        }

        public void requestSoundAd() throws RemoteException {
            Logger.a("requestSoundAd");
            if (!isAdsActive() && XmPlayerService.this.s != null && (XmPlayerService.this.q instanceof Track) && System.currentTimeMillis() - this.lastRequestTime > 3000) {
                this.lastRequestTime = System.currentTimeMillis();
                Logger.a("requestSoundAd playAds");
                XmPlayerService.this.s.a((Track) XmPlayerService.this.q, 0, (XmAdsManager.PlayAdsCallback) null, true);
            }
        }

        public void pausePlayInMillis(long j) throws RemoteException {
            long unused = XmPlayerService.this.W = j;
        }

        public boolean isAdPlaying() throws RemoteException {
            if (XmPlayerService.this.s != null) {
                return XmPlayerService.this.s.d();
            }
            return false;
        }

        public boolean haveNextPlayList() throws RemoteException {
            if (XmPlayerService.this.mListControl == null) {
                return false;
            }
            XmPlayerService.this.mListControl.r();
            return false;
        }

        public boolean havePrePlayList() throws RemoteException {
            if (XmPlayerService.this.mListControl == null) {
                return false;
            }
            XmPlayerService.this.mListControl.q();
            return false;
        }

        public boolean isBuffering() throws RemoteException {
            if (XmPlayerService.this.k == null) {
                return false;
            }
            if (XmPlayerService.this.k.o() || getPlayerStatus() == 9) {
                return true;
            }
            return false;
        }

        public void setProxyNew(Config config) throws RemoteException {
            Logger.e(XmPlayerService.f2220a, "代理 setProxyNew " + config);
            XmPlayerService.this.mConfig = config;
            XmPlayerService.this.k.a(config);
            CommonRequest.a().b(config);
            BaseCall.a().a(config);
            FileUtilBase.a((Context) XmPlayerService.this, config);
            HttpUrlUtil.f1992a = config;
        }

        public void exitSoundAd() throws RemoteException {
            String[] packagesForUid = XmPlayerService.this.getPackageManager().getPackagesForUid(getCallingUid());
            String str = (packagesForUid == null || packagesForUid.length <= 0) ? null : packagesForUid[0];
            if (!TextUtils.isEmpty("com.ximalaya.ting.android") && "com.ximalaya.ting.android".equals(str) && BaseUtil.d() && XmPlayerService.this.s != null) {
                XmPlayerService.this.s.a(isPlaying());
            }
        }

        public void setBreakpointResume(boolean z) throws RemoteException {
            XmPlayerConfig.a((Context) XmPlayerService.this).a(z);
        }

        public boolean isLoading() throws RemoteException {
            if (!(XmPlayerService.this.s == null || XmPlayerService.this.k == null)) {
                if (XmPlayerService.this.k.m() == 9) {
                    return true;
                }
                if (!XmPlayerService.this.s.g() || XmPlayerService.this.s.l() || XmPlayerService.this.s.k() == 2) {
                    return false;
                }
                return true;
            }
            return false;
        }

        public void init(String str, String str2, String str3) throws RemoteException {
            CommonRequest.a().b(str2);
            CommonRequest.a().c(str3);
            CommonRequest.a().a((Context) XmPlayerService.this, str);
        }

        public void setPlayModel(String str, int i, final long j) throws RemoteException {
            if (TextUtils.isEmpty(str)) {
                XmPlayerService.this.b("内容为null", j);
                return;
            }
            final HashMap hashMap = new HashMap();
            hashMap.put(DTransferConstants.F, str);
            if (i >= 0) {
                hashMap.put(DTransferConstants.w, i + "");
            }
            CommonRequest.e(hashMap, new IDataCallBack<SearchTrackList>() {
                public void a(SearchTrackList searchTrackList) {
                    if (searchTrackList == null || searchTrackList.c() == null || searchTrackList.c().size() <= 0) {
                        XmPlayerService xmPlayerService = XmPlayerService.this;
                        xmPlayerService.b(((String) hashMap.get(DTransferConstants.F)) + "没有搜索的数据", j);
                        return;
                    }
                    XmPlayerService.getPlayerSrvice().setPlayList(hashMap, searchTrackList.c());
                    XmPlayerService.getPlayerSrvice().play(0);
                }

                public void a(int i, String str) {
                    XmPlayerService xmPlayerService = XmPlayerService.this;
                    xmPlayerService.b(((String) hashMap.get(DTransferConstants.F)) + str, j);
                }
            });
        }

        public void setCategoryId(int i, final long j) throws RemoteException {
            final HashMap hashMap = new HashMap();
            hashMap.put(DTransferConstants.w, i + "");
            CommonRequest.n(hashMap, new IDataCallBack<TrackHotList>() {
                public void a(TrackHotList trackHotList) {
                    if (trackHotList == null || trackHotList.c() == null || trackHotList.c().size() <= 0) {
                        XmPlayerService.this.b((String) hashMap.get(DTransferConstants.F), j);
                        return;
                    }
                    XmPlayerService.getPlayerSrvice().setPlayList(hashMap, trackHotList.c());
                    XmPlayerService.getPlayerSrvice().play(0);
                }

                public void a(int i, String str) {
                    XmPlayerService xmPlayerService = XmPlayerService.this;
                    xmPlayerService.b(((String) hashMap.get(DTransferConstants.F)) + str, j);
                }
            });
        }

        public void getCategoryModelList(final long j) throws RemoteException {
            CommonRequest.a((Map<String, String>) new HashMap(), (IDataCallBack<CategoryList>) new IDataCallBack<CategoryList>() {
                public void a(CategoryList categoryList) {
                    ArrayList arrayList = new ArrayList();
                    for (Category next : categoryList.a()) {
                        CategoryModel categoryModel = new CategoryModel();
                        categoryModel.a((int) next.a());
                        categoryModel.a(next.c());
                        arrayList.add(categoryModel);
                    }
                    XmPlayerService.this.b(new Gson().toJson((Object) arrayList), 4, j);
                }

                public void a(int i, String str) {
                    XmPlayerService.this.b(str, j);
                }
            });
        }

        public void getSourseLists(String str, int i, int i2, int i3, final int i4, final long j) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put(DTransferConstants.F, str);
            hashMap.put(DTransferConstants.w, i + "");
            hashMap.put("page", i2 + "");
            hashMap.put("count", i3 + "");
            if (i4 == 1) {
                CommonRequest.d(hashMap, new IDataCallBack<SearchAlbumList>() {
                    public void a(SearchAlbumList searchAlbumList) {
                        XmPlayerService.this.b(new Gson().toJson((Object) searchAlbumList), i4, j);
                    }

                    public void a(int i, String str) {
                        XmPlayerService.this.b(str, j);
                    }
                });
            } else if (i4 == 2) {
                CommonRequest.e(hashMap, new IDataCallBack<SearchTrackList>() {
                    public void a(SearchTrackList searchTrackList) {
                        XmPlayerService.this.b(new Gson().toJson((Object) searchTrackList), i4, j);
                    }

                    public void a(int i, String str) {
                        XmPlayerService.this.b(str, j);
                    }
                });
            }
        }

        public void browseAlbums(long j, int i, int i2, final long j2) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put(DTransferConstants.C, j + "");
            hashMap.put("page", i + "");
            hashMap.put("count", i2 + "");
            CommonRequest.t(hashMap, new IDataCallBack<TrackList>() {
                public void a(TrackList trackList) {
                    XmPlayerService.this.b(new Gson().toJson((Object) trackList), 3, j2);
                }

                public void a(int i, String str) {
                    XmPlayerService.this.b(str, j2);
                }
            });
        }

        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0034, code lost:
            if (com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.getPlayerSrvice().play(0) == false) goto L_0x0055;
         */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0058  */
        /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setPlayByTrack(java.lang.String r8, long r9) throws android.os.RemoteException {
            /*
                r7 = this;
                boolean r0 = android.text.TextUtils.isEmpty(r8)
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r1 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.getPlayerSrvice()
                if (r1 != 0) goto L_0x000b
                return
            L_0x000b:
                r1 = 1
                if (r0 != 0) goto L_0x0056
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r2 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.getPlayerSrvice()     // Catch:{ Exception -> 0x0037 }
                r3 = 0
                com.ximalaya.ting.android.opensdk.model.track.Track[] r4 = new com.ximalaya.ting.android.opensdk.model.track.Track[r1]     // Catch:{ Exception -> 0x0037 }
                com.google.gson.Gson r5 = new com.google.gson.Gson     // Catch:{ Exception -> 0x0037 }
                r5.<init>()     // Catch:{ Exception -> 0x0037 }
                java.lang.Class<com.ximalaya.ting.android.opensdk.model.track.Track> r6 = com.ximalaya.ting.android.opensdk.model.track.Track.class
                java.lang.Object r8 = r5.fromJson((java.lang.String) r8, r6)     // Catch:{ Exception -> 0x0037 }
                com.ximalaya.ting.android.opensdk.model.track.Track r8 = (com.ximalaya.ting.android.opensdk.model.track.Track) r8     // Catch:{ Exception -> 0x0037 }
                r5 = 0
                r4[r5] = r8     // Catch:{ Exception -> 0x0037 }
                java.util.List r8 = java.util.Arrays.asList(r4)     // Catch:{ Exception -> 0x0037 }
                r2.setPlayList(r3, r8)     // Catch:{ Exception -> 0x0037 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r8 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.getPlayerSrvice()     // Catch:{ Exception -> 0x0037 }
                boolean r8 = r8.play(r5)     // Catch:{ Exception -> 0x0037 }
                if (r8 != 0) goto L_0x0056
                goto L_0x0055
            L_0x0037:
                r8 = move-exception
                r8.printStackTrace()
                java.lang.String r0 = "play_info"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "setPlayByTrack:"
                r2.append(r3)
                java.lang.String r8 = r8.toString()
                r2.append(r8)
                java.lang.String r8 = r2.toString()
                com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.String) r0, (java.lang.String) r8)
            L_0x0055:
                r0 = 1
            L_0x0056:
                if (r0 == 0) goto L_0x005f
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r8 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this
                java.lang.String r0 = "播放失败"
                r8.b(r0, r9)
            L_0x005f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.XmPlayerImpl.setPlayByTrack(java.lang.String, long):void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x004e, code lost:
            if (com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.getPlayerSrvice().play(r7) == false) goto L_0x0071;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setPlayByAlbumTracks(java.lang.String r6, int r7, long r8) throws android.os.RemoteException {
            /*
                r5 = this;
                boolean r0 = android.text.TextUtils.isEmpty(r6)
                r1 = 0
                r2 = 1
                if (r0 != 0) goto L_0x000d
                if (r7 >= 0) goto L_0x000b
                goto L_0x000d
            L_0x000b:
                r0 = 0
                goto L_0x000e
            L_0x000d:
                r0 = 1
            L_0x000e:
                if (r0 != 0) goto L_0x0070
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService$XmPlayerImpl$7 r3 = new com.ximalaya.ting.android.opensdk.player.service.XmPlayerService$XmPlayerImpl$7     // Catch:{ Exception -> 0x0051 }
                r3.<init>()     // Catch:{ Exception -> 0x0051 }
                java.lang.reflect.Type r3 = r3.getType()     // Catch:{ Exception -> 0x0051 }
                com.google.gson.Gson r4 = new com.google.gson.Gson     // Catch:{ Exception -> 0x0051 }
                r4.<init>()     // Catch:{ Exception -> 0x0051 }
                java.lang.Object r6 = r4.fromJson((java.lang.String) r6, (java.lang.reflect.Type) r3)     // Catch:{ Exception -> 0x0051 }
                com.ximalaya.ting.android.opensdk.model.track.CommonTrackList r6 = (com.ximalaya.ting.android.opensdk.model.track.CommonTrackList) r6     // Catch:{ Exception -> 0x0051 }
                java.util.List r3 = r6.c()     // Catch:{ Exception -> 0x0051 }
                if (r3 == 0) goto L_0x0034
                java.util.List r3 = r6.c()     // Catch:{ Exception -> 0x0051 }
                int r3 = r3.size()     // Catch:{ Exception -> 0x0051 }
                if (r3 != 0) goto L_0x0035
            L_0x0034:
                r0 = 1
            L_0x0035:
                if (r0 != 0) goto L_0x0070
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r3 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.getPlayerSrvice()     // Catch:{ Exception -> 0x0051 }
                java.util.Map r4 = r6.d()     // Catch:{ Exception -> 0x0051 }
                java.util.List r6 = r6.c()     // Catch:{ Exception -> 0x0051 }
                r3.setPlayList(r4, r6)     // Catch:{ Exception -> 0x0051 }
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r6 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.getPlayerSrvice()     // Catch:{ Exception -> 0x0051 }
                boolean r6 = r6.play(r7)     // Catch:{ Exception -> 0x0051 }
                if (r6 != 0) goto L_0x0070
                goto L_0x0071
            L_0x0051:
                r6 = move-exception
                r6.printStackTrace()
                java.lang.String r7 = "play_info"
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r3 = "setPlayByAlbumTracks:"
                r0.append(r3)
                java.lang.String r6 = r6.toString()
                r0.append(r6)
                java.lang.String r6 = r0.toString()
                com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.String) r7, (java.lang.String) r6)
                goto L_0x0071
            L_0x0070:
                r2 = r0
            L_0x0071:
                if (r2 == 0) goto L_0x007b
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r6 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this
                java.lang.String r7 = "播放失败"
                r6.b(r7, r8)
                goto L_0x0082
            L_0x007b:
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r6 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.this
                java.lang.String r7 = "播放成功"
                r6.b(r7, r1, r8)
            L_0x0082:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.XmPlayerImpl.setPlayByAlbumTracks(java.lang.String, int, long):void");
        }

        public void registeCustomDataCallBack(IXmCustomDataCallBack iXmCustomDataCallBack) throws RemoteException {
            Logger.c(XmPlayerService.f2220a, "Process " + Binder.getCallingPid() + "has register CustomDataCallBack");
            if (iXmCustomDataCallBack != null) {
                XmPlayerService.this.e.register(iXmCustomDataCallBack, new MyRemoteCallbackList.ProcessCookie(Binder.getCallingPid(), Binder.getCallingUid()));
            }
        }

        public void unregisteCustomDataCallBack(IXmCustomDataCallBack iXmCustomDataCallBack) throws RemoteException {
            if (iXmCustomDataCallBack != null) {
                XmPlayerService.this.e.unregister(iXmCustomDataCallBack);
            }
        }

        public boolean updateTrackInPlayList(Track track) throws RemoteException {
            int indexOf;
            if (track == null || (indexOf = XmPlayerService.this.mListControl.d().indexOf(track)) < 0) {
                return false;
            }
            XmPlayerService.this.mListControl.a(indexOf, track);
            if (!(XmPlayerService.this.q == null || track == null || XmPlayerService.this.q.a() != track.a())) {
                PlayableModel unused = XmPlayerService.this.q = track;
            }
            XmNotificationCreater.a(XmPlayerService.this.b).a(XmPlayerService.this.mListControl, XmPlayerService.this.u, XmPlayerService.this.v, XmPlayerService.this.w, NotificationColorUtils.a(XmPlayerService.this.b));
            return true;
        }

        public void getHotContent(boolean z, int i, int i2, final long j) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put(DTransferConstants.w, "0");
            hashMap.put("page", i + "");
            hashMap.put("count", i2 + "");
            if (!z) {
                CommonRequest.p(hashMap, new IDataCallBack<AlbumList>() {
                    public void a(AlbumList albumList) {
                        XmPlayerService.this.b(new Gson().toJson((Object) albumList), 6, j);
                    }

                    public void a(int i, String str) {
                        XmPlayerService.this.b(str, j);
                    }
                });
            } else {
                CommonRequest.n(hashMap, new IDataCallBack<TrackHotList>() {
                    public void a(TrackHotList trackHotList) {
                        XmPlayerService.this.b(new Gson().toJson((Object) trackHotList), 5, j);
                    }

                    public void a(int i, String str) {
                        XmPlayerService.this.b(str, j);
                    }
                });
            }
        }

        public void getTrackListByLastTrack(long j, long j2, int i, final long j3) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put(DTransferConstants.C, j + "");
            hashMap.put("track_id", j2 + "");
            hashMap.put("count", i + "");
            CommonRequest.r(hashMap, new IDataCallBack<LastPlayTrackList>() {
                public void a(LastPlayTrackList lastPlayTrackList) {
                    XmPlayerService.this.b(new Gson().toJson((Object) lastPlayTrackList), 8, j3);
                }

                public void a(int i, String str) {
                    XmPlayerService.this.b(str, j3);
                }
            });
        }

        public int getPlayCurrPosition() throws RemoteException {
            return XmPlayerService.this.k.c();
        }

        public boolean getPlayListOrder() throws RemoteException {
            return XmPlayerService.this.mListControl.o();
        }

        public void updateTrackDownloadUrlInPlayList(Track track) throws RemoteException {
            int indexOf = XmPlayerService.this.mListControl.d().indexOf(track);
            if (indexOf >= 0) {
                XmPlayerService.this.mListControl.d().get(indexOf).u(track.aq());
            }
        }

        public String getCurPlayUrl() throws RemoteException {
            if (XmPlayerService.this.k != null) {
                return XmPlayerService.this.k.d();
            }
            return null;
        }

        public void setVolume(float f, float f2) throws RemoteException {
            XmPlayerService.this.k.a(f, f2);
        }

        public void getAlbumByCategoryId(long j, int i, int i2, final long j2) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put(DTransferConstants.w, j + "");
            hashMap.put("page", i + "");
            hashMap.put("count", i2 + "");
            CommonRequest.p(hashMap, new IDataCallBack<AlbumList>() {
                public void a(AlbumList albumList) {
                    XmPlayerService.this.b(new Gson().toJson((Object) albumList), 9, j2);
                }

                public void a(int i, String str) {
                    XmPlayerService.this.b(str, j2);
                }
            });
        }

        public void registeMainDataSupportCallBack(IXmMainDataSupportDataCallback iXmMainDataSupportDataCallback) throws RemoteException {
            if (iXmMainDataSupportDataCallback != null) {
                XmPlayerService.this.g.register(iXmMainDataSupportDataCallback, new MyRemoteCallbackList.ProcessCookie(Binder.getCallingPid(), Binder.getCallingUid()));
            }
        }

        public void unregisteMainDataSupportCallBack(IXmMainDataSupportDataCallback iXmMainDataSupportDataCallback) throws RemoteException {
            if (iXmMainDataSupportDataCallback != null) {
                XmPlayerService.this.g.unregister(iXmMainDataSupportDataCallback);
            }
        }

        public void getMyCollect(int i, int i2, long j) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put("pageId", i + "");
            hashMap.put("pageSize", i2 + "");
            XmPlayerService.this.a((Map<String, String>) hashMap, 100, j, XmPlayerService.K);
        }

        public void getAttentionAlbum(int i, String str, long j) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put("device", "android");
            if (!TextUtils.isEmpty(str)) {
                hashMap.put(PreferenceConstantsInOpenSdk.T, str);
                hashMap.put("sign", "1");
            } else {
                hashMap.put("sign", "2");
            }
            hashMap.put("size", i + "");
            XmPlayerService.this.a((Map<String, String>) hashMap, 107, j, XmPlayerService.L);
        }

        public void getAlbumInfo(long j, int i, int i2, String str, long j2) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put("page", i + "");
            if (i2 > 0) {
                hashMap.put("pageSize", i2 + "");
            } else {
                hashMap.put("pageSize", UserConfig.g);
            }
            hashMap.put(DTransferConstants.ad, j + "");
            hashMap.put("isAsc", "true");
            hashMap.put("device", "android");
            hashMap.put("url_from", str);
            XmPlayerService.this.a((Map<String, String>) hashMap, 101, j2, XmPlayerService.M);
        }

        public void getRank(String str, String str2, int i, long j) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put("device", "android");
            hashMap.put("key", str2);
            hashMap.put("pageId", i + "");
            hashMap.put("pageSize", UserConfig.g);
            if ("track".equals(str)) {
                XmPlayerService.this.a((Map<String, String>) hashMap, 102, j, XmPlayerService.OPENSDK_GET_RANK_TRACK);
            } else if ("album".equals(str)) {
                XmPlayerService.this.a((Map<String, String>) hashMap, 103, j, XmPlayerService.OPENSDK_GETRANKALBUMLIST);
            } else if ("anchor".equals(str)) {
                XmPlayerService.this.a((Map<String, String>) hashMap, 104, j, XmPlayerService.OPENSDK_GETRANKANCHORLIST);
            }
        }

        public void getMainHotContent(int i, int i2, int i3, long j) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put("page", "" + i2);
            hashMap.put("per_page", "" + i3);
            hashMap.put("condition", Constants.PageFragment.PAGE_HOT);
            hashMap.put(DTransferConstants.x, Tags.BaiduLbs.ADDRTYPE);
            hashMap.put("tag_name", "");
            hashMap.put("device", "android");
            if (i == 1) {
                XmPlayerService.this.a((Map<String, String>) hashMap, 105, j, XmPlayerService.OPENSDK_GETHOTTRACK);
            } else if (i == 2) {
                hashMap.put("status", "0");
                XmPlayerService.this.a((Map<String, String>) hashMap, 115, j, XmPlayerService.OPENSDK_GET_HOT_ALBUM);
            }
        }

        public void getNewRank(int i, int i2, int i3, long j) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put("device", "android");
            hashMap.put("pageId", "" + i2);
            hashMap.put("pageSize", "" + i3);
            hashMap.put(TouchesHelper.TARGET_KEY, "main");
            if (i == 0) {
                hashMap.put("rankingListId", "21");
                XmPlayerService.this.a((Map<String, String>) hashMap, 132, j, XmPlayerService.OPENSDK_GET_NEW_RANK_ALBUM);
            } else if (i == 1) {
                hashMap.put("rankingListId", "57");
                XmPlayerService.this.a((Map<String, String>) hashMap, 133, j, XmPlayerService.OPENSDK_GET_NEW_RANK_TRACK);
            } else if (i == 2) {
                hashMap.put("rankingListId", "50");
                XmPlayerService.this.a((Map<String, String>) hashMap, 132, j, XmPlayerService.OPENSDK_GET_NEW_RANK_ALBUM);
            }
        }

        public void getUserInfo(long j) throws RemoteException {
            XmPlayerService.this.a((Map<String, String>) new HashMap(), 106, j, XmPlayerService.OPENSDK_GETUSERINFO);
        }

        public void setPlayStatisticClassName(String str) throws RemoteException {
            XmPlayerService.this.p.a(str);
        }

        public void setAdsDataHandlerClassName(String str) throws RemoteException {
            XmPlayerService.this.s.a(str);
        }

        public void getParseDeviceInfo(long j) throws RemoteException {
            XmPlayerService.this.a((Map<String, String>) new HashMap(), 117, j, XmPlayerService.OPENSDK_GET_PARSE_DEVICE_INFO);
        }

        public void getSuggestAlbums(int i, int i2, boolean z, long j) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put("pageId", "" + i);
            hashMap.put("pageSize", "" + i2);
            hashMap.put("isLogin", "" + z);
            XmPlayerService.this.a((Map<String, String>) hashMap, 118, j, XmPlayerService.OPENSDK_GET_SUGGEST_ALBUMS);
        }

        public void getSpecialListenList(int i, int i2, int i3, long j) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put("categoryId", i3 + "");
            hashMap.put("scale", "2");
            hashMap.put("pageId", i + "");
            hashMap.put("pageSize", i2 + "");
            XmPlayerService.this.a((Map<String, String>) hashMap, 119, j, XmPlayerService.OPENSDK_GET_SPECIALLISTEN);
        }

        public void getSubjectDetail(int i, int i2, long j, long j2) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put("id", j + "");
            hashMap.put("page", i + "");
            hashMap.put("count", i2 + "");
            XmPlayerService.this.a((Map<String, String>) hashMap, 120, j2, XmPlayerService.OPENSDK_GET_SUBJECTDETAIL);
        }

        public void subscribeAlbum(String str, boolean z, long j) throws RemoteException {
            long j2;
            HashMap hashMap = new HashMap();
            try {
                j2 = ((Album) new Gson().fromJson(str, Album.class)).b();
            } catch (Exception e) {
                e.printStackTrace();
                j2 = 0;
            }
            hashMap.put(DTransferConstants.ad, j2 + "");
            hashMap.put("album", str);
            hashMap.put("isSubscribed", z + "");
            XmPlayerService.this.a((Map<String, String>) hashMap, 121, j, XmPlayerService.OPENSDK_SUBSCRIBE_ALBUM);
        }

        public void getTrackListByTrackIdAtAlbum(long j, long j2, boolean z, long j3) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put("trackId", j + "");
            hashMap.put(DTransferConstants.ad, j2 + "");
            hashMap.put("asc", z + "");
            XmPlayerService.this.a((Map<String, String>) hashMap, 122, j3, XmPlayerService.OPENSDK_GET_TRACKLIST_BYTRACKIDATALBUM);
        }

        public void getRecommendAlbumListByTrackId(long j, long j2) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put("trackId", j + "");
            XmPlayerService.this.a((Map<String, String>) hashMap, 124, j2, XmPlayerService.OPENSDK_GET_RECOMMEND_ALBUMLIST_BY_TRACKID);
        }

        public void getRecommendAlbumListByAlbumId(long j, long j2) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put(DTransferConstants.ad, j + "");
            XmPlayerService.this.a((Map<String, String>) hashMap, 123, j2, XmPlayerService.OPENSDK_GET_RECOMMEND_ALBUMLIST_BY_ALBUMID);
        }

        public void getProvinces(long j) throws RemoteException {
            XmPlayerService.this.a((Map<String, String>) new HashMap(), 125, j, XmPlayerService.OPENSDK_GET_PROVINCES);
        }

        public void getRadioList(int i, long j, int i2, int i3, long j2) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put("radioType", i + "");
            if (i == 2) {
                hashMap.put("provinceCode", j + "");
            }
            hashMap.put("pageSize", i3 + "");
            hashMap.put(BioDetector.EXT_KEY_PAGENUM, i2 + "");
            XmPlayerService.this.a((Map<String, String>) hashMap, 126, j2, XmPlayerService.OPENSDK_GET_RADIO_LIST);
        }

        public void getRadioSchedules(String str, long j) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put("radio", str);
            XmPlayerService.this.a((Map<String, String>) hashMap, 127, j, XmPlayerService.OPENSDK_GET_RADIO_SCHEDULES);
        }

        public void getCategoriesList(int i, int i2, long j) throws RemoteException {
            XmPlayerService.this.a((Map<String, String>) new HashMap(), 128, j, XmPlayerService.OPENSDK_GET_CATEGORIES_LIST);
        }

        public void getAlbumByCategoryIdAndTag(long j, int i, int i2, int i3, long j2) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put("id", j + "");
            hashMap.put("page", i2 + "");
            hashMap.put("count", i3 + "");
            if (i != 0) {
                hashMap.put("keywordId", i + "");
            }
            XmPlayerService.this.a((Map<String, String>) hashMap, 129, j2, XmPlayerService.OPENSDK_GET_ALBUMS_BY_CATEGORY_ID_AND_TAG);
        }

        public void getTags(long j, long j2) {
            HashMap hashMap = new HashMap();
            hashMap.put("categoryId", j + "");
            XmPlayerService.this.a((Map<String, String>) hashMap, 130, j2, XmPlayerService.OPENSDK_GET_TAGS_BY_CATEGORY_ID);
        }

        public void getTrackDetailInfo(long j, long j2) {
            HashMap hashMap = new HashMap();
            hashMap.put("device", "android");
            hashMap.put("trackId", j + "");
            XmPlayerService.this.a((Map<String, String>) hashMap, 131, j2, XmPlayerService.OPENSDK_GET_TRACK_INFO);
        }

        public Track getTrackInfoSync(long j) {
            HashMap hashMap = new HashMap();
            hashMap.put("device", "android");
            hashMap.put("trackId", j + "");
            try {
                Class e = BaseCall.e();
                if (e != null) {
                    return (Track) e.getMethod("getTrackInfoDetailSync", new Class[]{Map.class}).invoke((Object) null, new Object[]{hashMap});
                }
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            } catch (IllegalAccessException e4) {
                e4.printStackTrace();
            }
            return null;
        }

        public void setTokenInvalidForSDK(final IXmTokenInvalidForSDKCallBack iXmTokenInvalidForSDKCallBack) throws RemoteException {
            if (iXmTokenInvalidForSDKCallBack != null) {
                CommonRequest.a().a((CommonRequest.ITokenStateChange) new CommonRequest.ITokenStateChange() {
                    public boolean a() {
                        return false;
                    }

                    public boolean b() {
                        return false;
                    }

                    public void c() {
                        if (iXmTokenInvalidForSDKCallBack != null) {
                            try {
                                iXmTokenInvalidForSDKCallBack.tokenInvalid();
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            } else {
                CommonRequest.a().a((CommonRequest.ITokenStateChange) null);
            }
        }

        public void setTokenToPlayForSDK(AccessToken accessToken) throws RemoteException {
            AccessTokenManager.a().a((Context) XmPlayerService.this);
            AccessTokenManager.a().a(accessToken);
        }

        public void setDLNAState(boolean z) throws RemoteException {
            boolean unused = XmPlayerService.this.x = z;
            if (XmPlayerService.this.k != null) {
                XmPlayerService.this.k.a(z);
            }
        }

        public void setPlayCdnConfigureModel(CdnConfigModel cdnConfigModel) throws RemoteException {
            CdnUtil.a(cdnConfigModel);
        }

        public void resetPlayList() throws RemoteException {
            if (XmPlayerService.this.mListControl != null) {
                XmPlayerService.this.mListControl.h();
            }
            XmNotificationCreater.a(XmPlayerService.this.b).a(XmPlayerService.this.mListControl, XmPlayerService.this.u, XmPlayerService.this.v, XmPlayerService.this.w, NotificationColorUtils.a(XmPlayerService.this.b));
            if (XmPlayerService.this.s != null) {
                XmPlayerService.this.s.i();
            }
        }

        public void removeListByIndex(int i) throws RemoteException {
            if (XmPlayerService.this.mListControl != null) {
                XmPlayerService.this.mListControl.c(i);
            }
        }

        public String getHistoryPos(String str) throws RemoteException {
            return XmPlayerService.this.getSoundHistoryPos(str);
        }

        public void setHistoryPosById(long j, int i) throws RemoteException {
            XmPlayerService.this.saveSoundHistoryPos(j, i);
        }

        public String getLastPlayTrackInAlbum(String str) throws RemoteException {
            return XmPlayerService.this.a(str);
        }

        public void needContinuePlay(boolean z) throws RemoteException {
            boolean unused = XmPlayerService.this.y = z;
            if (XmPlayerService.this.l != null) {
                XmPlayerService.this.l.b(z);
            }
        }

        public void setRecordModel(RecordModel recordModel) throws RemoteException {
            if (XmPlayerService.this.p != null) {
                XmPlayerService.this.p.a(recordModel);
            }
        }

        public boolean isDLNAState() throws RemoteException {
            if (XmPlayerService.this.k == null) {
                return XmPlayerService.this.x;
            }
            boolean unused = XmPlayerService.this.x = XmPlayerService.this.k.a();
            return XmPlayerService.this.x;
        }

        public void setPlayerProcessRequestEnvironment(int i) throws RemoteException {
            if (BaseUtil.d()) {
                try {
                    Field declaredField = Class.forName("com.ximalaya.ting.android.host.util.constant.AppConstants").getDeclaredField("environmentId");
                    declaredField.setAccessible(true);
                    declaredField.set((Object) null, Integer.valueOf(i));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                } catch (NoSuchFieldException e3) {
                    e3.printStackTrace();
                }
            }
        }

        public void resetPlayer() throws RemoteException {
            if (XmPlayerService.this.k != null) {
                XmPlayerService.this.k.g();
            }
        }

        public void setCheckAdContent(boolean z) throws RemoteException {
            if (BaseUtil.d()) {
                try {
                    Field declaredField = Class.forName("com.ximalaya.ting.android.host.manager.ad.AdManager").getDeclaredField("checkAdContent");
                    declaredField.setAccessible(true);
                    declaredField.set((Object) null, Boolean.valueOf(z));
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                } catch (ClassNotFoundException e3) {
                    e3.printStackTrace();
                    if (ConstantsOpenSdk.b) {
                        throw new RuntimeException("AdManager 类路径发生变化");
                    }
                }
            }
        }

        public void insertPlayListHead(List<Track> list) throws RemoteException {
            if (XmPlayerService.this.mListControl != null) {
                XmPlayerService.this.mListControl.b(list);
            }
        }

        public long getCurrentTrackPlayedDuration() {
            return XmPlayerControl.f2211a;
        }

        public float getTempo() throws RemoteException {
            if (XmPlayerService.this.k != null) {
                return XmPlayerService.this.k.n();
            }
            return 0.0f;
        }

        public void setAppkeyAndPackId(String str, String str2) throws RemoteException {
            CommonRequest.a().b(str);
            CommonRequest.a().c(str2);
        }
    }

    /* access modifiers changed from: private */
    public void a(Map<String, String> map, final int i2, final long j2, String str) {
        Logger.e(f2220a, "getRequestMData   type = " + i2 + "   ; urlKey = " + str);
        getDataWithXDCS(V, map, new IDataSupportCallBack<String>() {
            public void a(String str) {
                XmPlayerService.this.a(str, i2, j2);
            }

            public void a(int i, String str) {
                XmPlayerService.this.a(str, j2);
            }
        }, str);
    }

    public static <T> void getDataWithXDCS(String str, Map<String, String> map, IDataSupportCallBack<T> iDataSupportCallBack, Object... objArr) {
        Class e2 = BaseCall.e();
        if (e2 != null) {
            Class[] clsArr = new Class[(objArr.length + 2)];
            clsArr[0] = Map.class;
            clsArr[1] = IDataSupportCallBack.class;
            for (int i2 = 2; i2 < clsArr.length; i2++) {
                clsArr[i2] = objArr[i2 - 2].getClass();
            }
            try {
                Method declaredMethod = e2.getDeclaredMethod(str, clsArr);
                Object[] objArr2 = new Object[(objArr.length + 2)];
                objArr2[0] = map;
                objArr2[1] = iDataSupportCallBack;
                for (int i3 = 2; i3 < objArr2.length; i3++) {
                    objArr2[i3] = objArr[i3 - 2];
                }
                if (objArr2.length >= 2) {
                    declaredMethod.invoke((Object) null, objArr2);
                }
            } catch (Exception unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, int i2, long j2) {
        if (this.g != null) {
            int beginBroadcast = this.g.beginBroadcast();
            for (int i3 = 0; i3 < beginBroadcast; i3++) {
                try {
                    this.g.getBroadcastItem(i3).onSuccess(str, i2, j2);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
            this.g.finishBroadcast();
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, long j2) {
        if (this.g != null) {
            int beginBroadcast = this.g.beginBroadcast();
            for (int i2 = 0; i2 < beginBroadcast; i2++) {
                try {
                    this.g.getBroadcastItem(i2).onError(str, j2);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
            this.g.finishBroadcast();
        }
    }

    /* access modifiers changed from: private */
    public void b(String str, int i2, long j2) {
        a(str, i2, j2);
        if (this.e != null) {
            int beginBroadcast = this.e.beginBroadcast();
            for (int i3 = 0; i3 < beginBroadcast; i3++) {
                try {
                    this.e.getBroadcastItem(i3).onSuccess(str, i2);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
            this.e.finishBroadcast();
        }
    }

    /* access modifiers changed from: private */
    public void b(String str, long j2) {
        a(str, j2);
        if (this.e != null) {
            int beginBroadcast = this.e.beginBroadcast();
            for (int i2 = 0; i2 < beginBroadcast; i2++) {
                try {
                    this.e.getBroadcastItem(i2).onError(str);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
            this.e.finishBroadcast();
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.E != null && this.b != null) {
            this.E.onReceive(this.b, new Intent(ConstantsOpenSdk.e));
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.E != null && this.b != null) {
            this.E.onReceive(this.b, new Intent(ConstantsOpenSdk.f));
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        this.E.onReceive(this.b, new Intent(ConstantsOpenSdk.g));
    }

    public PlayableModel getPlayableModel() {
        return this.mListControl.n();
    }

    public XmPlayListControl.PlayMode getPlayMode() {
        if (this.mListControl != null) {
            return this.mListControl.b();
        }
        return XmPlayListControl.PlayMode.PLAY_MODEL_LIST;
    }

    public boolean isContinuePlay() {
        return this.y;
    }

    public boolean isOnlineResource() {
        return this.k.e();
    }

    public XmPlayListControl getPlayListControl() {
        return this.mListControl;
    }

    public XmPlayerControl getPlayControl() {
        return this.k;
    }

    public String getCurPlayUrl() {
        if (this.k != null) {
            return this.k.d();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void e() {
        long currentTimeMillis = this.W - System.currentTimeMillis();
        if (this.W > 0 && currentTimeMillis <= 0) {
            this.W = 0;
            try {
                if (getPlayerImpl().getPlayerStatus() == 3) {
                    pausePlay();
                } else {
                    this.X = true;
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public Handler getTimeHander() {
        if (this.Y == null) {
            this.Y = new Handler(Looper.getMainLooper());
        }
        return this.Y;
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.W > 0) {
            if (this.Z == null) {
                this.Z = new Runnable() {
                    public void run() {
                        XmPlayerService.this.e();
                        XmPlayerService.this.getTimeHander().postDelayed(XmPlayerService.this.Z, 500);
                    }
                };
            }
            getTimeHander().postDelayed(this.Z, 500);
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        if (getTimeHander() != null && this.Z != null) {
            getTimeHander().removeCallbacks(this.Z);
        }
    }

    public void closeApp() {
        try {
            if (this.t != null) {
                this.t.closeApp();
            }
        } catch (RemoteException e2) {
            Logger.c(f2220a, "close app " + e2.toString());
        }
        try {
            XmPlayerManager.c();
            XmPlayerManagerForPlayer.a();
            stopSelf();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (o != null && !BaseUtil.a(this, "com.ximalaya.ting.android")) {
            Logger.c(f2220a, "close app use stopself");
            try {
                stopSelf();
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
    }

    public void setNotification() {
        Notification a2;
        if (!this.F) {
            this.F = true;
            Class<?> cls = null;
            try {
                cls = Class.forName("com.ximalaya.ting.android.host.activity.MainActivity");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (cls != null && (a2 = XmNotificationCreater.a(o.getApplicationContext()).a(o.getApplicationContext(), cls)) != null) {
                try {
                    if (o != null) {
                        Logger.c(f2220a, "setNotification");
                        o.startForeground(16842960, a2);
                        this.v = a2;
                        this.w = 16842960;
                        if (this.b != null && this.mListControl != null && this.u != null) {
                            boolean a3 = NotificationColorUtils.a(this.b);
                            XmNotificationCreater.a(this.b).a(this.mListControl, this.u, this.v, this.w, a3);
                            XmNotificationCreater.a(this.b).a(this.u, this.v, this.w, a3);
                        }
                    }
                } catch (Exception e3) {
                    CdnUtil.a(CdnConstants.q, "setNotification:" + e3.toString());
                }
            }
        }
    }

    public PlayableModel getCurrPlayModel() {
        if (this.mListControl != null) {
            return this.mListControl.n();
        }
        return null;
    }

    public void setPlayDataOutPutListener(XMediaPlayer.OnPlayDataOutputListener onPlayDataOutputListener) {
        if (this.k != null) {
            this.k.a(onPlayDataOutputListener);
        }
        this.C = onPlayDataOutputListener;
    }

    private Radio a(PlayableModel playableModel) {
        Schedule b2 = ModelUtil.b((Track) playableModel);
        Radio radio = new Radio();
        radio.a(b2.p());
        radio.a("schedule");
        radio.c(b2.q());
        radio.e(b2.l().b());
        radio.e(b2.a());
        radio.j(b2.l().c());
        radio.k(b2.l().c());
        radio.h(System.currentTimeMillis());
        radio.f(b2.l().e());
        radio.g(b2.l().e());
        radio.h(b2.l().g());
        radio.i(b2.l().h());
        radio.b(b2.r());
        radio.d(b2.l().a());
        return radio;
    }

    public void seekTo(int i2) {
        if (getPlayerImpl() != null) {
            try {
                getPlayerImpl().seekTo(i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public int getDuration() {
        if (getPlayerImpl() == null) {
            return 0;
        }
        try {
            return getPlayerImpl().getDuration();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public int getPlayCurrPosition() {
        if (getPlayerImpl() == null) {
            return 0;
        }
        try {
            return getPlayerImpl().getPlayCurrPosition();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public XmPlayListControl.PlayMode getXmPlayMode() {
        if (getPlayListControl() != null) {
            try {
                return getPlayListControl().b();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return XmPlayListControl.PlayMode.PLAY_MODEL_LIST;
    }

    public void notifProgress(int i2, int i3) {
        if (this.G != null) {
            this.G.a(i2, i3);
        }
    }

    public void setPlayStartCallback(XmAdsManager.IPlayStartCallBack iPlayStartCallBack) {
        this.ab = iPlayStartCallBack;
    }

    public void playPauseNoNotif() {
        if (this.k != null) {
            Logger.a("playPauseNoNotif:" + Arrays.toString(Thread.currentThread().getStackTrace()) + ":playPauseNoNotif");
            this.k.d(false);
        }
    }
}
