package com.ximalaya.ting.android.opensdk.player.service;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ximalaya.ting.android.opensdk.httputil.Config;
import com.ximalaya.ting.android.opensdk.model.live.radio.Radio;
import com.ximalaya.ting.android.opensdk.model.statistic.RecordModel;
import com.ximalaya.ting.android.opensdk.model.token.AccessToken;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel;
import java.util.List;
import java.util.Map;

public interface IXmPlayer extends IInterface {
    void addPlayList(List<Track> list) throws RemoteException;

    void browseAlbums(long j, int i, int i2, long j2) throws RemoteException;

    void clearPlayCache() throws RemoteException;

    void exitSoundAd() throws RemoteException;

    void getAlbumByCategoryId(long j, int i, int i2, long j2) throws RemoteException;

    void getAlbumByCategoryIdAndTag(long j, int i, int i2, int i3, long j2) throws RemoteException;

    void getAlbumInfo(long j, int i, int i2, String str, long j2) throws RemoteException;

    void getAttentionAlbum(int i, String str, long j) throws RemoteException;

    void getCategoriesList(int i, int i2, long j) throws RemoteException;

    void getCategoryModelList(long j) throws RemoteException;

    String getCurPlayUrl() throws RemoteException;

    int getCurrIndex() throws RemoteException;

    long getCurrentTrackPlayedDuration() throws RemoteException;

    int getDefultPageSize() throws RemoteException;

    int getDuration() throws RemoteException;

    String getHistoryPos(String str) throws RemoteException;

    void getHotContent(boolean z, int i, int i2, long j) throws RemoteException;

    String getLastPlayTrackInAlbum(String str) throws RemoteException;

    void getMainHotContent(int i, int i2, int i3, long j) throws RemoteException;

    void getMyCollect(int i, int i2, long j) throws RemoteException;

    void getNewRank(int i, int i2, int i3, long j) throws RemoteException;

    void getNextPlayList() throws RemoteException;

    Map getParam() throws RemoteException;

    void getParseDeviceInfo(long j) throws RemoteException;

    int getPlayCurrPosition() throws RemoteException;

    List<Track> getPlayList(int i) throws RemoteException;

    boolean getPlayListOrder() throws RemoteException;

    int getPlayListSize() throws RemoteException;

    String getPlayMode() throws RemoteException;

    int getPlaySourceType() throws RemoteException;

    int getPlayerStatus() throws RemoteException;

    void getPrePlayList() throws RemoteException;

    void getProvinces(long j) throws RemoteException;

    Radio getRadio() throws RemoteException;

    void getRadioList(int i, long j, int i2, int i3, long j2) throws RemoteException;

    void getRadioSchedules(String str, long j) throws RemoteException;

    void getRank(String str, String str2, int i, long j) throws RemoteException;

    void getRecommendAlbumListByAlbumId(long j, long j2) throws RemoteException;

    void getRecommendAlbumListByTrackId(long j, long j2) throws RemoteException;

    void getSourseLists(String str, int i, int i2, int i3, int i4, long j) throws RemoteException;

    void getSpecialListenList(int i, int i2, int i3, long j) throws RemoteException;

    void getSubjectDetail(int i, int i2, long j, long j2) throws RemoteException;

    void getSuggestAlbums(int i, int i2, boolean z, long j) throws RemoteException;

    void getTags(long j, long j2) throws RemoteException;

    float getTempo() throws RemoteException;

    Track getTrack(int i) throws RemoteException;

    void getTrackDetailInfo(long j, long j2) throws RemoteException;

    Track getTrackInfoSync(long j) throws RemoteException;

    void getTrackListByLastTrack(long j, long j2, int i, long j3) throws RemoteException;

    void getTrackListByTrackIdAtAlbum(long j, long j2, boolean z, long j3) throws RemoteException;

    void getUserInfo(long j) throws RemoteException;

    boolean hasNextSound() throws RemoteException;

    boolean hasPreSound() throws RemoteException;

    boolean haveNextPlayList() throws RemoteException;

    boolean havePrePlayList() throws RemoteException;

    void init(String str, String str2, String str3) throws RemoteException;

    void insertPlayListHead(List<Track> list) throws RemoteException;

    boolean isAdPlaying() throws RemoteException;

    boolean isAdsActive() throws RemoteException;

    boolean isBuffering() throws RemoteException;

    boolean isDLNAState() throws RemoteException;

    boolean isLoading() throws RemoteException;

    boolean isOnlineSource() throws RemoteException;

    boolean isPlaying() throws RemoteException;

    void needContinuePlay(boolean z) throws RemoteException;

    boolean pausePlay() throws RemoteException;

    void pausePlayInMillis(long j) throws RemoteException;

    boolean permutePlayList() throws RemoteException;

    boolean play(int i) throws RemoteException;

    boolean playNext() throws RemoteException;

    boolean playPre() throws RemoteException;

    boolean playRadio(Radio radio) throws RemoteException;

    void registeAdsListener(IXmAdsEventDispatcher iXmAdsEventDispatcher) throws RemoteException;

    void registeCommonBusinessListener(IXmCommonBusinessDispatcher iXmCommonBusinessDispatcher) throws RemoteException;

    void registeCustomDataCallBack(IXmCustomDataCallBack iXmCustomDataCallBack) throws RemoteException;

    void registeMainDataSupportCallBack(IXmMainDataSupportDataCallback iXmMainDataSupportDataCallback) throws RemoteException;

    void registePlayerListener(IXmPlayerEventDispatcher iXmPlayerEventDispatcher) throws RemoteException;

    void removeListByIndex(int i) throws RemoteException;

    void requestSoundAd() throws RemoteException;

    void resetPlayList() throws RemoteException;

    void resetPlayer() throws RemoteException;

    boolean seekTo(int i) throws RemoteException;

    void setAdsDataHandlerClassName(String str) throws RemoteException;

    void setAppSecret(String str) throws RemoteException;

    void setAppkeyAndPackId(String str, String str2) throws RemoteException;

    void setBreakpointResume(boolean z) throws RemoteException;

    void setCategoryId(int i, long j) throws RemoteException;

    void setCheckAdContent(boolean z) throws RemoteException;

    void setDLNAState(boolean z) throws RemoteException;

    void setHistoryPosById(long j, int i) throws RemoteException;

    void setNotification(int i, Notification notification) throws RemoteException;

    void setPageSize(int i) throws RemoteException;

    void setPlayByAlbumTracks(String str, int i, long j) throws RemoteException;

    void setPlayByTrack(String str, long j) throws RemoteException;

    void setPlayCdnConfigureModel(CdnConfigModel cdnConfigModel) throws RemoteException;

    boolean setPlayIndex(int i) throws RemoteException;

    void setPlayList(Map map, List<Track> list) throws RemoteException;

    void setPlayListChangeListener(IXmDataCallback iXmDataCallback) throws RemoteException;

    void setPlayMode(String str) throws RemoteException;

    void setPlayModel(String str, int i, long j) throws RemoteException;

    void setPlayStatisticClassName(String str) throws RemoteException;

    void setPlayerProcessRequestEnvironment(int i) throws RemoteException;

    void setProxy(String str, int i, String str2, Map map) throws RemoteException;

    void setProxyNew(Config config) throws RemoteException;

    void setRecordModel(RecordModel recordModel) throws RemoteException;

    void setSoundTouchAllParams(float f, float f2, float f3) throws RemoteException;

    void setTokenInvalidForSDK(IXmTokenInvalidForSDKCallBack iXmTokenInvalidForSDKCallBack) throws RemoteException;

    void setTokenToPlayForSDK(AccessToken accessToken) throws RemoteException;

    void setVolume(float f, float f2) throws RemoteException;

    boolean startPlay() throws RemoteException;

    boolean stopPlay() throws RemoteException;

    void subscribeAlbum(String str, boolean z, long j) throws RemoteException;

    void unregisteAdsListener(IXmAdsEventDispatcher iXmAdsEventDispatcher) throws RemoteException;

    void unregisteCustomDataCallBack(IXmCustomDataCallBack iXmCustomDataCallBack) throws RemoteException;

    void unregisteMainDataSupportCallBack(IXmMainDataSupportDataCallback iXmMainDataSupportDataCallback) throws RemoteException;

    void unregistePlayerListener(IXmPlayerEventDispatcher iXmPlayerEventDispatcher) throws RemoteException;

    void updateTrackDownloadUrlInPlayList(Track track) throws RemoteException;

    boolean updateTrackInPlayList(Track track) throws RemoteException;

    public static abstract class Stub extends Binder implements IXmPlayer {
        private static final String DESCRIPTOR = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer";
        static final int TRANSACTION_addPlayList = 28;
        static final int TRANSACTION_browseAlbums = 52;
        static final int TRANSACTION_clearPlayCache = 33;
        static final int TRANSACTION_exitSoundAd = 110;
        static final int TRANSACTION_getAlbumByCategoryId = 69;
        static final int TRANSACTION_getAlbumByCategoryIdAndTag = 85;
        static final int TRANSACTION_getAlbumInfo = 64;
        static final int TRANSACTION_getAttentionAlbum = 63;
        static final int TRANSACTION_getCategoriesList = 84;
        static final int TRANSACTION_getCategoryModelList = 56;
        static final int TRANSACTION_getCurPlayUrl = 46;
        static final int TRANSACTION_getCurrIndex = 14;
        static final int TRANSACTION_getCurrentTrackPlayedDuration = 116;
        static final int TRANSACTION_getDefultPageSize = 68;
        static final int TRANSACTION_getDuration = 15;
        static final int TRANSACTION_getHistoryPos = 98;
        static final int TRANSACTION_getHotContent = 57;
        static final int TRANSACTION_getLastPlayTrackInAlbum = 100;
        static final int TRANSACTION_getMainHotContent = 66;
        static final int TRANSACTION_getMyCollect = 62;
        static final int TRANSACTION_getNewRank = 67;
        static final int TRANSACTION_getNextPlayList = 41;
        static final int TRANSACTION_getParam = 31;
        static final int TRANSACTION_getParseDeviceInfo = 70;
        static final int TRANSACTION_getPlayCurrPosition = 16;
        static final int TRANSACTION_getPlayList = 30;
        static final int TRANSACTION_getPlayListOrder = 45;
        static final int TRANSACTION_getPlayListSize = 32;
        static final int TRANSACTION_getPlayMode = 25;
        static final int TRANSACTION_getPlaySourceType = 23;
        static final int TRANSACTION_getPlayerStatus = 13;
        static final int TRANSACTION_getPrePlayList = 42;
        static final int TRANSACTION_getProvinces = 81;
        static final int TRANSACTION_getRadio = 22;
        static final int TRANSACTION_getRadioList = 82;
        static final int TRANSACTION_getRadioSchedules = 83;
        static final int TRANSACTION_getRank = 65;
        static final int TRANSACTION_getRecommendAlbumListByAlbumId = 80;
        static final int TRANSACTION_getRecommendAlbumListByTrackId = 79;
        static final int TRANSACTION_getSourseLists = 51;
        static final int TRANSACTION_getSpecialListenList = 75;
        static final int TRANSACTION_getSubjectDetail = 76;
        static final int TRANSACTION_getSuggestAlbums = 74;
        static final int TRANSACTION_getTags = 86;
        static final int TRANSACTION_getTempo = 117;
        static final int TRANSACTION_getTrack = 21;
        static final int TRANSACTION_getTrackDetailInfo = 87;
        static final int TRANSACTION_getTrackInfoSync = 88;
        static final int TRANSACTION_getTrackListByLastTrack = 58;
        static final int TRANSACTION_getTrackListByTrackIdAtAlbum = 78;
        static final int TRANSACTION_getUserInfo = 61;
        static final int TRANSACTION_hasNextSound = 12;
        static final int TRANSACTION_hasPreSound = 11;
        static final int TRANSACTION_haveNextPlayList = 106;
        static final int TRANSACTION_havePrePlayList = 107;
        static final int TRANSACTION_init = 47;
        static final int TRANSACTION_insertPlayListHead = 115;
        static final int TRANSACTION_isAdPlaying = 105;
        static final int TRANSACTION_isAdsActive = 18;
        static final int TRANSACTION_isBuffering = 108;
        static final int TRANSACTION_isDLNAState = 72;
        static final int TRANSACTION_isLoading = 112;
        static final int TRANSACTION_isOnlineSource = 10;
        static final int TRANSACTION_isPlaying = 17;
        static final int TRANSACTION_needContinuePlay = 73;
        static final int TRANSACTION_pausePlay = 7;
        static final int TRANSACTION_pausePlayInMillis = 104;
        static final int TRANSACTION_permutePlayList = 44;
        static final int TRANSACTION_play = 4;
        static final int TRANSACTION_playNext = 3;
        static final int TRANSACTION_playPre = 2;
        static final int TRANSACTION_playRadio = 29;
        static final int TRANSACTION_registeAdsListener = 38;
        static final int TRANSACTION_registeCommonBusinessListener = 40;
        static final int TRANSACTION_registeCustomDataCallBack = 48;
        static final int TRANSACTION_registeMainDataSupportCallBack = 59;
        static final int TRANSACTION_registePlayerListener = 36;
        static final int TRANSACTION_removeListByIndex = 97;
        static final int TRANSACTION_requestSoundAd = 103;
        static final int TRANSACTION_resetPlayList = 96;
        static final int TRANSACTION_resetPlayer = 113;
        static final int TRANSACTION_seekTo = 9;
        static final int TRANSACTION_setAdsDataHandlerClassName = 94;
        static final int TRANSACTION_setAppSecret = 35;
        static final int TRANSACTION_setAppkeyAndPackId = 118;
        static final int TRANSACTION_setBreakpointResume = 111;
        static final int TRANSACTION_setCategoryId = 55;
        static final int TRANSACTION_setCheckAdContent = 114;
        static final int TRANSACTION_setDLNAState = 71;
        static final int TRANSACTION_setHistoryPosById = 99;
        static final int TRANSACTION_setNotification = 34;
        static final int TRANSACTION_setPageSize = 26;
        static final int TRANSACTION_setPlayByAlbumTracks = 54;
        static final int TRANSACTION_setPlayByTrack = 53;
        static final int TRANSACTION_setPlayCdnConfigureModel = 95;
        static final int TRANSACTION_setPlayIndex = 5;
        static final int TRANSACTION_setPlayList = 27;
        static final int TRANSACTION_setPlayListChangeListener = 43;
        static final int TRANSACTION_setPlayMode = 24;
        static final int TRANSACTION_setPlayModel = 50;
        static final int TRANSACTION_setPlayStatisticClassName = 93;
        static final int TRANSACTION_setPlayerProcessRequestEnvironment = 101;
        static final int TRANSACTION_setProxy = 1;
        static final int TRANSACTION_setProxyNew = 109;
        static final int TRANSACTION_setRecordModel = 91;
        static final int TRANSACTION_setSoundTouchAllParams = 102;
        static final int TRANSACTION_setTokenInvalidForSDK = 89;
        static final int TRANSACTION_setTokenToPlayForSDK = 90;
        static final int TRANSACTION_setVolume = 92;
        static final int TRANSACTION_startPlay = 6;
        static final int TRANSACTION_stopPlay = 8;
        static final int TRANSACTION_subscribeAlbum = 77;
        static final int TRANSACTION_unregisteAdsListener = 39;
        static final int TRANSACTION_unregisteCustomDataCallBack = 49;
        static final int TRANSACTION_unregisteMainDataSupportCallBack = 60;
        static final int TRANSACTION_unregistePlayerListener = 37;
        static final int TRANSACTION_updateTrackDownloadUrlInPlayList = 19;
        static final int TRANSACTION_updateTrackInPlayList = 20;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IXmPlayer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IXmPlayer)) {
                return new Proxy(iBinder);
            }
            return (IXmPlayer) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: com.ximalaya.ting.android.opensdk.model.track.Track} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: com.ximalaya.ting.android.opensdk.model.track.Track} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: com.ximalaya.ting.android.opensdk.model.live.radio.Radio} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: android.app.Notification} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: com.ximalaya.ting.android.opensdk.model.token.AccessToken} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: com.ximalaya.ting.android.opensdk.model.statistic.RecordModel} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v24, resolved type: com.ximalaya.ting.android.opensdk.httputil.Config} */
        /* JADX WARNING: type inference failed for: r0v1 */
        /* JADX WARNING: type inference failed for: r0v27 */
        /* JADX WARNING: type inference failed for: r0v28 */
        /* JADX WARNING: type inference failed for: r0v29 */
        /* JADX WARNING: type inference failed for: r0v30 */
        /* JADX WARNING: type inference failed for: r0v31 */
        /* JADX WARNING: type inference failed for: r0v32 */
        /* JADX WARNING: type inference failed for: r0v33 */
        /* JADX WARNING: type inference failed for: r0v34 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r12, android.os.Parcel r13, android.os.Parcel r14, int r15) throws android.os.RemoteException {
            /*
                r11 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r12 == r0) goto L_0x09de
                r0 = 0
                r2 = 0
                switch(r12) {
                    case 1: goto L_0x09ba;
                    case 2: goto L_0x09aa;
                    case 3: goto L_0x099a;
                    case 4: goto L_0x0986;
                    case 5: goto L_0x0972;
                    case 6: goto L_0x0962;
                    case 7: goto L_0x0952;
                    case 8: goto L_0x0942;
                    case 9: goto L_0x092e;
                    case 10: goto L_0x091e;
                    case 11: goto L_0x090e;
                    case 12: goto L_0x08fe;
                    case 13: goto L_0x08ee;
                    case 14: goto L_0x08de;
                    case 15: goto L_0x08ce;
                    case 16: goto L_0x08be;
                    case 17: goto L_0x08ae;
                    case 18: goto L_0x089e;
                    case 19: goto L_0x0877;
                    case 20: goto L_0x084c;
                    case 21: goto L_0x082f;
                    case 22: goto L_0x0816;
                    case 23: goto L_0x0806;
                    case 24: goto L_0x07f6;
                    case 25: goto L_0x07e6;
                    case 26: goto L_0x07d6;
                    case 27: goto L_0x07b8;
                    case 28: goto L_0x07a6;
                    case 29: goto L_0x0787;
                    case 30: goto L_0x0773;
                    case 31: goto L_0x0763;
                    case 32: goto L_0x0753;
                    case 33: goto L_0x0747;
                    case 34: goto L_0x0728;
                    case 35: goto L_0x0718;
                    case 36: goto L_0x0704;
                    case 37: goto L_0x06f0;
                    case 38: goto L_0x06dc;
                    case 39: goto L_0x06c8;
                    case 40: goto L_0x06b4;
                    case 41: goto L_0x06a8;
                    case 42: goto L_0x069c;
                    case 43: goto L_0x0688;
                    case 44: goto L_0x0678;
                    case 45: goto L_0x0668;
                    case 46: goto L_0x0658;
                    case 47: goto L_0x0640;
                    case 48: goto L_0x062c;
                    case 49: goto L_0x0618;
                    case 50: goto L_0x0600;
                    case 51: goto L_0x05db;
                    case 52: goto L_0x05be;
                    case 53: goto L_0x05aa;
                    case 54: goto L_0x0592;
                    case 55: goto L_0x057e;
                    case 56: goto L_0x056e;
                    case 57: goto L_0x054c;
                    case 58: goto L_0x052f;
                    case 59: goto L_0x051b;
                    case 60: goto L_0x0507;
                    case 61: goto L_0x04f7;
                    case 62: goto L_0x04df;
                    case 63: goto L_0x04c7;
                    case 64: goto L_0x04a6;
                    case 65: goto L_0x0489;
                    case 66: goto L_0x046c;
                    case 67: goto L_0x044f;
                    case 68: goto L_0x043f;
                    case 69: goto L_0x0422;
                    case 70: goto L_0x0412;
                    case 71: goto L_0x03ff;
                    case 72: goto L_0x03ef;
                    case 73: goto L_0x03dc;
                    case 74: goto L_0x03ba;
                    case 75: goto L_0x039d;
                    case 76: goto L_0x0380;
                    case 77: goto L_0x0365;
                    case 78: goto L_0x0343;
                    case 79: goto L_0x032f;
                    case 80: goto L_0x031b;
                    case 81: goto L_0x030b;
                    case 82: goto L_0x02ea;
                    case 83: goto L_0x02d6;
                    case 84: goto L_0x02be;
                    case 85: goto L_0x029d;
                    case 86: goto L_0x0289;
                    case 87: goto L_0x0275;
                    case 88: goto L_0x0258;
                    case 89: goto L_0x0244;
                    case 90: goto L_0x021d;
                    case 91: goto L_0x01f6;
                    case 92: goto L_0x01e2;
                    case 93: goto L_0x01d2;
                    case 94: goto L_0x01c2;
                    case 95: goto L_0x01a7;
                    case 96: goto L_0x019b;
                    case 97: goto L_0x018b;
                    case 98: goto L_0x0177;
                    case 99: goto L_0x0163;
                    case 100: goto L_0x014f;
                    case 101: goto L_0x013f;
                    case 102: goto L_0x0127;
                    case 103: goto L_0x011b;
                    case 104: goto L_0x010b;
                    case 105: goto L_0x00fb;
                    case 106: goto L_0x00eb;
                    case 107: goto L_0x00db;
                    case 108: goto L_0x00cb;
                    case 109: goto L_0x00a4;
                    case 110: goto L_0x0098;
                    case 111: goto L_0x0085;
                    case 112: goto L_0x0075;
                    case 113: goto L_0x0069;
                    case 114: goto L_0x0056;
                    case 115: goto L_0x0044;
                    case 116: goto L_0x0034;
                    case 117: goto L_0x0024;
                    case 118: goto L_0x0010;
                    default: goto L_0x000b;
                }
            L_0x000b:
                boolean r12 = super.onTransact(r12, r13, r14, r15)
                return r12
            L_0x0010:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r12 = r13.readString()
                java.lang.String r13 = r13.readString()
                r11.setAppkeyAndPackId(r12, r13)
                r14.writeNoException()
                return r1
            L_0x0024:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                float r12 = r11.getTempo()
                r14.writeNoException()
                r14.writeFloat(r12)
                return r1
            L_0x0034:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r12 = r11.getCurrentTrackPlayedDuration()
                r14.writeNoException()
                r14.writeLong(r12)
                return r1
            L_0x0044:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                android.os.Parcelable$Creator<com.ximalaya.ting.android.opensdk.model.track.Track> r12 = com.ximalaya.ting.android.opensdk.model.track.Track.CREATOR
                java.util.ArrayList r12 = r13.createTypedArrayList(r12)
                r11.insertPlayListHead(r12)
                r14.writeNoException()
                return r1
            L_0x0056:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x0062
                r2 = 1
            L_0x0062:
                r11.setCheckAdContent(r2)
                r14.writeNoException()
                return r1
            L_0x0069:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                r11.resetPlayer()
                r14.writeNoException()
                return r1
            L_0x0075:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.isLoading()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x0085:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x0091
                r2 = 1
            L_0x0091:
                r11.setBreakpointResume(r2)
                r14.writeNoException()
                return r1
            L_0x0098:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                r11.exitSoundAd()
                r14.writeNoException()
                return r1
            L_0x00a4:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x00b8
                android.os.Parcelable$Creator<com.ximalaya.ting.android.opensdk.httputil.Config> r12 = com.ximalaya.ting.android.opensdk.httputil.Config.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r0 = r12
                com.ximalaya.ting.android.opensdk.httputil.Config r0 = (com.ximalaya.ting.android.opensdk.httputil.Config) r0
            L_0x00b8:
                r11.setProxyNew(r0)
                r14.writeNoException()
                if (r0 == 0) goto L_0x00c7
                r14.writeInt(r1)
                r0.writeToParcel(r14, r1)
                goto L_0x00ca
            L_0x00c7:
                r14.writeInt(r2)
            L_0x00ca:
                return r1
            L_0x00cb:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.isBuffering()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x00db:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.havePrePlayList()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x00eb:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.haveNextPlayList()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x00fb:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.isAdPlaying()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x010b:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r12 = r13.readLong()
                r11.pausePlayInMillis(r12)
                r14.writeNoException()
                return r1
            L_0x011b:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                r11.requestSoundAd()
                r14.writeNoException()
                return r1
            L_0x0127:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                float r12 = r13.readFloat()
                float r15 = r13.readFloat()
                float r13 = r13.readFloat()
                r11.setSoundTouchAllParams(r12, r15, r13)
                r14.writeNoException()
                return r1
            L_0x013f:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                r11.setPlayerProcessRequestEnvironment(r12)
                r14.writeNoException()
                return r1
            L_0x014f:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r12 = r13.readString()
                java.lang.String r12 = r11.getLastPlayTrackInAlbum(r12)
                r14.writeNoException()
                r14.writeString(r12)
                return r1
            L_0x0163:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r2 = r13.readLong()
                int r12 = r13.readInt()
                r11.setHistoryPosById(r2, r12)
                r14.writeNoException()
                return r1
            L_0x0177:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r12 = r13.readString()
                java.lang.String r12 = r11.getHistoryPos(r12)
                r14.writeNoException()
                r14.writeString(r12)
                return r1
            L_0x018b:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                r11.removeListByIndex(r12)
                r14.writeNoException()
                return r1
            L_0x019b:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                r11.resetPlayList()
                r14.writeNoException()
                return r1
            L_0x01a7:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x01bb
                android.os.Parcelable$Creator<com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel> r12 = com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r0 = r12
                com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel r0 = (com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel) r0
            L_0x01bb:
                r11.setPlayCdnConfigureModel(r0)
                r14.writeNoException()
                return r1
            L_0x01c2:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r12 = r13.readString()
                r11.setAdsDataHandlerClassName(r12)
                r14.writeNoException()
                return r1
            L_0x01d2:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r12 = r13.readString()
                r11.setPlayStatisticClassName(r12)
                r14.writeNoException()
                return r1
            L_0x01e2:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                float r12 = r13.readFloat()
                float r13 = r13.readFloat()
                r11.setVolume(r12, r13)
                r14.writeNoException()
                return r1
            L_0x01f6:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x020a
                android.os.Parcelable$Creator<com.ximalaya.ting.android.opensdk.model.statistic.RecordModel> r12 = com.ximalaya.ting.android.opensdk.model.statistic.RecordModel.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r0 = r12
                com.ximalaya.ting.android.opensdk.model.statistic.RecordModel r0 = (com.ximalaya.ting.android.opensdk.model.statistic.RecordModel) r0
            L_0x020a:
                r11.setRecordModel(r0)
                r14.writeNoException()
                if (r0 == 0) goto L_0x0219
                r14.writeInt(r1)
                r0.writeToParcel(r14, r1)
                goto L_0x021c
            L_0x0219:
                r14.writeInt(r2)
            L_0x021c:
                return r1
            L_0x021d:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x0231
                android.os.Parcelable$Creator<com.ximalaya.ting.android.opensdk.model.token.AccessToken> r12 = com.ximalaya.ting.android.opensdk.model.token.AccessToken.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r0 = r12
                com.ximalaya.ting.android.opensdk.model.token.AccessToken r0 = (com.ximalaya.ting.android.opensdk.model.token.AccessToken) r0
            L_0x0231:
                r11.setTokenToPlayForSDK(r0)
                r14.writeNoException()
                if (r0 == 0) goto L_0x0240
                r14.writeInt(r1)
                r0.writeToParcel(r14, r1)
                goto L_0x0243
            L_0x0240:
                r14.writeInt(r2)
            L_0x0243:
                return r1
            L_0x0244:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.ximalaya.ting.android.opensdk.player.service.IXmTokenInvalidForSDKCallBack r12 = com.ximalaya.ting.android.opensdk.player.service.IXmTokenInvalidForSDKCallBack.Stub.asInterface(r12)
                r11.setTokenInvalidForSDK(r12)
                r14.writeNoException()
                return r1
            L_0x0258:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r12 = r13.readLong()
                com.ximalaya.ting.android.opensdk.model.track.Track r12 = r11.getTrackInfoSync(r12)
                r14.writeNoException()
                if (r12 == 0) goto L_0x0271
                r14.writeInt(r1)
                r12.writeToParcel(r14, r1)
                goto L_0x0274
            L_0x0271:
                r14.writeInt(r2)
            L_0x0274:
                return r1
            L_0x0275:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r2 = r13.readLong()
                long r12 = r13.readLong()
                r11.getTrackDetailInfo(r2, r12)
                r14.writeNoException()
                return r1
            L_0x0289:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r2 = r13.readLong()
                long r12 = r13.readLong()
                r11.getTags(r2, r12)
                r14.writeNoException()
                return r1
            L_0x029d:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r3 = r13.readLong()
                int r5 = r13.readInt()
                int r6 = r13.readInt()
                int r7 = r13.readInt()
                long r8 = r13.readLong()
                r2 = r11
                r2.getAlbumByCategoryIdAndTag(r3, r5, r6, r7, r8)
                r14.writeNoException()
                return r1
            L_0x02be:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                int r15 = r13.readInt()
                long r2 = r13.readLong()
                r11.getCategoriesList(r12, r15, r2)
                r14.writeNoException()
                return r1
            L_0x02d6:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r12 = r13.readString()
                long r2 = r13.readLong()
                r11.getRadioSchedules(r12, r2)
                r14.writeNoException()
                return r1
            L_0x02ea:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r3 = r13.readInt()
                long r4 = r13.readLong()
                int r6 = r13.readInt()
                int r7 = r13.readInt()
                long r8 = r13.readLong()
                r2 = r11
                r2.getRadioList(r3, r4, r6, r7, r8)
                r14.writeNoException()
                return r1
            L_0x030b:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r12 = r13.readLong()
                r11.getProvinces(r12)
                r14.writeNoException()
                return r1
            L_0x031b:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r2 = r13.readLong()
                long r12 = r13.readLong()
                r11.getRecommendAlbumListByAlbumId(r2, r12)
                r14.writeNoException()
                return r1
            L_0x032f:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r2 = r13.readLong()
                long r12 = r13.readLong()
                r11.getRecommendAlbumListByTrackId(r2, r12)
                r14.writeNoException()
                return r1
            L_0x0343:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r4 = r13.readLong()
                long r6 = r13.readLong()
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x0358
                r8 = 1
                goto L_0x0359
            L_0x0358:
                r8 = 0
            L_0x0359:
                long r9 = r13.readLong()
                r3 = r11
                r3.getTrackListByTrackIdAtAlbum(r4, r6, r8, r9)
                r14.writeNoException()
                return r1
            L_0x0365:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r12 = r13.readString()
                int r15 = r13.readInt()
                if (r15 == 0) goto L_0x0375
                r2 = 1
            L_0x0375:
                long r3 = r13.readLong()
                r11.subscribeAlbum(r12, r2, r3)
                r14.writeNoException()
                return r1
            L_0x0380:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r3 = r13.readInt()
                int r4 = r13.readInt()
                long r5 = r13.readLong()
                long r7 = r13.readLong()
                r2 = r11
                r2.getSubjectDetail(r3, r4, r5, r7)
                r14.writeNoException()
                return r1
            L_0x039d:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r3 = r13.readInt()
                int r4 = r13.readInt()
                int r5 = r13.readInt()
                long r6 = r13.readLong()
                r2 = r11
                r2.getSpecialListenList(r3, r4, r5, r6)
                r14.writeNoException()
                return r1
            L_0x03ba:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r4 = r13.readInt()
                int r5 = r13.readInt()
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x03cf
                r6 = 1
                goto L_0x03d0
            L_0x03cf:
                r6 = 0
            L_0x03d0:
                long r7 = r13.readLong()
                r3 = r11
                r3.getSuggestAlbums(r4, r5, r6, r7)
                r14.writeNoException()
                return r1
            L_0x03dc:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x03e8
                r2 = 1
            L_0x03e8:
                r11.needContinuePlay(r2)
                r14.writeNoException()
                return r1
            L_0x03ef:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.isDLNAState()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x03ff:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x040b
                r2 = 1
            L_0x040b:
                r11.setDLNAState(r2)
                r14.writeNoException()
                return r1
            L_0x0412:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r12 = r13.readLong()
                r11.getParseDeviceInfo(r12)
                r14.writeNoException()
                return r1
            L_0x0422:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r3 = r13.readLong()
                int r5 = r13.readInt()
                int r6 = r13.readInt()
                long r7 = r13.readLong()
                r2 = r11
                r2.getAlbumByCategoryId(r3, r5, r6, r7)
                r14.writeNoException()
                return r1
            L_0x043f:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r11.getDefultPageSize()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x044f:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r3 = r13.readInt()
                int r4 = r13.readInt()
                int r5 = r13.readInt()
                long r6 = r13.readLong()
                r2 = r11
                r2.getNewRank(r3, r4, r5, r6)
                r14.writeNoException()
                return r1
            L_0x046c:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r3 = r13.readInt()
                int r4 = r13.readInt()
                int r5 = r13.readInt()
                long r6 = r13.readLong()
                r2 = r11
                r2.getMainHotContent(r3, r4, r5, r6)
                r14.writeNoException()
                return r1
            L_0x0489:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r3 = r13.readString()
                java.lang.String r4 = r13.readString()
                int r5 = r13.readInt()
                long r6 = r13.readLong()
                r2 = r11
                r2.getRank(r3, r4, r5, r6)
                r14.writeNoException()
                return r1
            L_0x04a6:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r3 = r13.readLong()
                int r5 = r13.readInt()
                int r6 = r13.readInt()
                java.lang.String r7 = r13.readString()
                long r8 = r13.readLong()
                r2 = r11
                r2.getAlbumInfo(r3, r5, r6, r7, r8)
                r14.writeNoException()
                return r1
            L_0x04c7:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                java.lang.String r15 = r13.readString()
                long r2 = r13.readLong()
                r11.getAttentionAlbum(r12, r15, r2)
                r14.writeNoException()
                return r1
            L_0x04df:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                int r15 = r13.readInt()
                long r2 = r13.readLong()
                r11.getMyCollect(r12, r15, r2)
                r14.writeNoException()
                return r1
            L_0x04f7:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r12 = r13.readLong()
                r11.getUserInfo(r12)
                r14.writeNoException()
                return r1
            L_0x0507:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.ximalaya.ting.android.opensdk.player.service.IXmMainDataSupportDataCallback r12 = com.ximalaya.ting.android.opensdk.player.service.IXmMainDataSupportDataCallback.Stub.asInterface(r12)
                r11.unregisteMainDataSupportCallBack(r12)
                r14.writeNoException()
                return r1
            L_0x051b:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.ximalaya.ting.android.opensdk.player.service.IXmMainDataSupportDataCallback r12 = com.ximalaya.ting.android.opensdk.player.service.IXmMainDataSupportDataCallback.Stub.asInterface(r12)
                r11.registeMainDataSupportCallBack(r12)
                r14.writeNoException()
                return r1
            L_0x052f:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r3 = r13.readLong()
                long r5 = r13.readLong()
                int r7 = r13.readInt()
                long r8 = r13.readLong()
                r2 = r11
                r2.getTrackListByLastTrack(r3, r5, r7, r8)
                r14.writeNoException()
                return r1
            L_0x054c:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x0559
                r4 = 1
                goto L_0x055a
            L_0x0559:
                r4 = 0
            L_0x055a:
                int r5 = r13.readInt()
                int r6 = r13.readInt()
                long r7 = r13.readLong()
                r3 = r11
                r3.getHotContent(r4, r5, r6, r7)
                r14.writeNoException()
                return r1
            L_0x056e:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r12 = r13.readLong()
                r11.getCategoryModelList(r12)
                r14.writeNoException()
                return r1
            L_0x057e:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                long r2 = r13.readLong()
                r11.setCategoryId(r12, r2)
                r14.writeNoException()
                return r1
            L_0x0592:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r12 = r13.readString()
                int r15 = r13.readInt()
                long r2 = r13.readLong()
                r11.setPlayByAlbumTracks(r12, r15, r2)
                r14.writeNoException()
                return r1
            L_0x05aa:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r12 = r13.readString()
                long r2 = r13.readLong()
                r11.setPlayByTrack(r12, r2)
                r14.writeNoException()
                return r1
            L_0x05be:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                long r3 = r13.readLong()
                int r5 = r13.readInt()
                int r6 = r13.readInt()
                long r7 = r13.readLong()
                r2 = r11
                r2.browseAlbums(r3, r5, r6, r7)
                r14.writeNoException()
                return r1
            L_0x05db:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r3 = r13.readString()
                int r4 = r13.readInt()
                int r5 = r13.readInt()
                int r6 = r13.readInt()
                int r7 = r13.readInt()
                long r8 = r13.readLong()
                r2 = r11
                r2.getSourseLists(r3, r4, r5, r6, r7, r8)
                r14.writeNoException()
                return r1
            L_0x0600:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r12 = r13.readString()
                int r15 = r13.readInt()
                long r2 = r13.readLong()
                r11.setPlayModel(r12, r15, r2)
                r14.writeNoException()
                return r1
            L_0x0618:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.ximalaya.ting.android.opensdk.player.service.IXmCustomDataCallBack r12 = com.ximalaya.ting.android.opensdk.player.service.IXmCustomDataCallBack.Stub.asInterface(r12)
                r11.unregisteCustomDataCallBack(r12)
                r14.writeNoException()
                return r1
            L_0x062c:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.ximalaya.ting.android.opensdk.player.service.IXmCustomDataCallBack r12 = com.ximalaya.ting.android.opensdk.player.service.IXmCustomDataCallBack.Stub.asInterface(r12)
                r11.registeCustomDataCallBack(r12)
                r14.writeNoException()
                return r1
            L_0x0640:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r12 = r13.readString()
                java.lang.String r15 = r13.readString()
                java.lang.String r13 = r13.readString()
                r11.init(r12, r15, r13)
                r14.writeNoException()
                return r1
            L_0x0658:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r12 = r11.getCurPlayUrl()
                r14.writeNoException()
                r14.writeString(r12)
                return r1
            L_0x0668:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.getPlayListOrder()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x0678:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.permutePlayList()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x0688:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.ximalaya.ting.android.opensdk.player.service.IXmDataCallback r12 = com.ximalaya.ting.android.opensdk.player.service.IXmDataCallback.Stub.asInterface(r12)
                r11.setPlayListChangeListener(r12)
                r14.writeNoException()
                return r1
            L_0x069c:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                r11.getPrePlayList()
                r14.writeNoException()
                return r1
            L_0x06a8:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                r11.getNextPlayList()
                r14.writeNoException()
                return r1
            L_0x06b4:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.ximalaya.ting.android.opensdk.player.service.IXmCommonBusinessDispatcher r12 = com.ximalaya.ting.android.opensdk.player.service.IXmCommonBusinessDispatcher.Stub.asInterface(r12)
                r11.registeCommonBusinessListener(r12)
                r14.writeNoException()
                return r1
            L_0x06c8:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.ximalaya.ting.android.opensdk.player.service.IXmAdsEventDispatcher r12 = com.ximalaya.ting.android.opensdk.player.service.IXmAdsEventDispatcher.Stub.asInterface(r12)
                r11.unregisteAdsListener(r12)
                r14.writeNoException()
                return r1
            L_0x06dc:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.ximalaya.ting.android.opensdk.player.service.IXmAdsEventDispatcher r12 = com.ximalaya.ting.android.opensdk.player.service.IXmAdsEventDispatcher.Stub.asInterface(r12)
                r11.registeAdsListener(r12)
                r14.writeNoException()
                return r1
            L_0x06f0:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher r12 = com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher.Stub.asInterface(r12)
                r11.unregistePlayerListener(r12)
                r14.writeNoException()
                return r1
            L_0x0704:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher r12 = com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher.Stub.asInterface(r12)
                r11.registePlayerListener(r12)
                r14.writeNoException()
                return r1
            L_0x0718:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r12 = r13.readString()
                r11.setAppSecret(r12)
                r14.writeNoException()
                return r1
            L_0x0728:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                int r15 = r13.readInt()
                if (r15 == 0) goto L_0x0740
                android.os.Parcelable$Creator r15 = android.app.Notification.CREATOR
                java.lang.Object r13 = r15.createFromParcel(r13)
                r0 = r13
                android.app.Notification r0 = (android.app.Notification) r0
            L_0x0740:
                r11.setNotification(r12, r0)
                r14.writeNoException()
                return r1
            L_0x0747:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                r11.clearPlayCache()
                r14.writeNoException()
                return r1
            L_0x0753:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r11.getPlayListSize()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x0763:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.util.Map r12 = r11.getParam()
                r14.writeNoException()
                r14.writeMap(r12)
                return r1
            L_0x0773:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                java.util.List r12 = r11.getPlayList(r12)
                r14.writeNoException()
                r14.writeTypedList(r12)
                return r1
            L_0x0787:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x079b
                android.os.Parcelable$Creator<com.ximalaya.ting.android.opensdk.model.live.radio.Radio> r12 = com.ximalaya.ting.android.opensdk.model.live.radio.Radio.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r0 = r12
                com.ximalaya.ting.android.opensdk.model.live.radio.Radio r0 = (com.ximalaya.ting.android.opensdk.model.live.radio.Radio) r0
            L_0x079b:
                boolean r12 = r11.playRadio(r0)
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x07a6:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                android.os.Parcelable$Creator<com.ximalaya.ting.android.opensdk.model.track.Track> r12 = com.ximalaya.ting.android.opensdk.model.track.Track.CREATOR
                java.util.ArrayList r12 = r13.createTypedArrayList(r12)
                r11.addPlayList(r12)
                r14.writeNoException()
                return r1
            L_0x07b8:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.Class r12 = r11.getClass()
                java.lang.ClassLoader r12 = r12.getClassLoader()
                java.util.HashMap r12 = r13.readHashMap(r12)
                android.os.Parcelable$Creator<com.ximalaya.ting.android.opensdk.model.track.Track> r15 = com.ximalaya.ting.android.opensdk.model.track.Track.CREATOR
                java.util.ArrayList r13 = r13.createTypedArrayList(r15)
                r11.setPlayList(r12, r13)
                r14.writeNoException()
                return r1
            L_0x07d6:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                r11.setPageSize(r12)
                r14.writeNoException()
                return r1
            L_0x07e6:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r12 = r11.getPlayMode()
                r14.writeNoException()
                r14.writeString(r12)
                return r1
            L_0x07f6:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r12 = r13.readString()
                r11.setPlayMode(r12)
                r14.writeNoException()
                return r1
            L_0x0806:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r11.getPlaySourceType()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x0816:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                com.ximalaya.ting.android.opensdk.model.live.radio.Radio r12 = r11.getRadio()
                r14.writeNoException()
                if (r12 == 0) goto L_0x082b
                r14.writeInt(r1)
                r12.writeToParcel(r14, r1)
                goto L_0x082e
            L_0x082b:
                r14.writeInt(r2)
            L_0x082e:
                return r1
            L_0x082f:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                com.ximalaya.ting.android.opensdk.model.track.Track r12 = r11.getTrack(r12)
                r14.writeNoException()
                if (r12 == 0) goto L_0x0848
                r14.writeInt(r1)
                r12.writeToParcel(r14, r1)
                goto L_0x084b
            L_0x0848:
                r14.writeInt(r2)
            L_0x084b:
                return r1
            L_0x084c:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x0860
                android.os.Parcelable$Creator<com.ximalaya.ting.android.opensdk.model.track.Track> r12 = com.ximalaya.ting.android.opensdk.model.track.Track.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r0 = r12
                com.ximalaya.ting.android.opensdk.model.track.Track r0 = (com.ximalaya.ting.android.opensdk.model.track.Track) r0
            L_0x0860:
                boolean r12 = r11.updateTrackInPlayList(r0)
                r14.writeNoException()
                r14.writeInt(r12)
                if (r0 == 0) goto L_0x0873
                r14.writeInt(r1)
                r0.writeToParcel(r14, r1)
                goto L_0x0876
            L_0x0873:
                r14.writeInt(r2)
            L_0x0876:
                return r1
            L_0x0877:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x088b
                android.os.Parcelable$Creator<com.ximalaya.ting.android.opensdk.model.track.Track> r12 = com.ximalaya.ting.android.opensdk.model.track.Track.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r0 = r12
                com.ximalaya.ting.android.opensdk.model.track.Track r0 = (com.ximalaya.ting.android.opensdk.model.track.Track) r0
            L_0x088b:
                r11.updateTrackDownloadUrlInPlayList(r0)
                r14.writeNoException()
                if (r0 == 0) goto L_0x089a
                r14.writeInt(r1)
                r0.writeToParcel(r14, r1)
                goto L_0x089d
            L_0x089a:
                r14.writeInt(r2)
            L_0x089d:
                return r1
            L_0x089e:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.isAdsActive()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x08ae:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.isPlaying()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x08be:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r11.getPlayCurrPosition()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x08ce:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r11.getDuration()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x08de:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r11.getCurrIndex()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x08ee:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r11.getPlayerStatus()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x08fe:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.hasNextSound()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x090e:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.hasPreSound()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x091e:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.isOnlineSource()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x092e:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                boolean r12 = r11.seekTo(r12)
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x0942:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.stopPlay()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x0952:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.pausePlay()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x0962:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.startPlay()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x0972:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                boolean r12 = r11.setPlayIndex(r12)
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x0986:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                boolean r12 = r11.play(r12)
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x099a:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.playNext()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x09aa:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                boolean r12 = r11.playPre()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x09ba:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r13.enforceInterface(r12)
                java.lang.String r12 = r13.readString()
                int r15 = r13.readInt()
                java.lang.String r0 = r13.readString()
                java.lang.Class r2 = r11.getClass()
                java.lang.ClassLoader r2 = r2.getClassLoader()
                java.util.HashMap r13 = r13.readHashMap(r2)
                r11.setProxy(r12, r15, r0, r13)
                r14.writeNoException()
                return r1
            L_0x09de:
                java.lang.String r12 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayer"
                r14.writeString(r12)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.player.service.IXmPlayer.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IXmPlayer {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void setProxy(String str, int i, String str2, Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    obtain.writeMap(map);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean playPre() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean playNext() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean play(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setPlayIndex(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean startPlay() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean pausePlay() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean stopPlay() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean seekTo(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isOnlineSource() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean hasPreSound() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean hasNextSound() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getPlayerStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getCurrIndex() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getDuration() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getPlayCurrPosition() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isPlaying() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isAdsActive() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateTrackDownloadUrlInPlayList(Track track) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (track != null) {
                        obtain.writeInt(1);
                        track.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        track.a(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean updateTrackInPlayList(Track track) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (track != null) {
                        obtain.writeInt(1);
                        track.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    if (obtain2.readInt() != 0) {
                        track.a(obtain2);
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Track getTrack(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Track.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Radio getRadio() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Radio.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getPlaySourceType() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setPlayMode(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getPlayMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setPageSize(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setPlayList(Map map, List<Track> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
                    obtain.writeTypedList(list);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void addPlayList(List<Track> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean playRadio(Radio radio) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (radio != null) {
                        obtain.writeInt(1);
                        radio.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<Track> getPlayList(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Track.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Map getParam() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getPlayListSize() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void clearPlayCache() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setNotification(int i, Notification notification) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (notification != null) {
                        obtain.writeInt(1);
                        notification.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setAppSecret(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void registePlayerListener(IXmPlayerEventDispatcher iXmPlayerEventDispatcher) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iXmPlayerEventDispatcher != null ? iXmPlayerEventDispatcher.asBinder() : null);
                    this.mRemote.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unregistePlayerListener(IXmPlayerEventDispatcher iXmPlayerEventDispatcher) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iXmPlayerEventDispatcher != null ? iXmPlayerEventDispatcher.asBinder() : null);
                    this.mRemote.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void registeAdsListener(IXmAdsEventDispatcher iXmAdsEventDispatcher) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iXmAdsEventDispatcher != null ? iXmAdsEventDispatcher.asBinder() : null);
                    this.mRemote.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unregisteAdsListener(IXmAdsEventDispatcher iXmAdsEventDispatcher) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iXmAdsEventDispatcher != null ? iXmAdsEventDispatcher.asBinder() : null);
                    this.mRemote.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void registeCommonBusinessListener(IXmCommonBusinessDispatcher iXmCommonBusinessDispatcher) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iXmCommonBusinessDispatcher != null ? iXmCommonBusinessDispatcher.asBinder() : null);
                    this.mRemote.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getNextPlayList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getPrePlayList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setPlayListChangeListener(IXmDataCallback iXmDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iXmDataCallback != null ? iXmDataCallback.asBinder() : null);
                    this.mRemote.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean permutePlayList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getPlayListOrder() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getCurPlayUrl() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void init(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.mRemote.transact(47, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void registeCustomDataCallBack(IXmCustomDataCallBack iXmCustomDataCallBack) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iXmCustomDataCallBack != null ? iXmCustomDataCallBack.asBinder() : null);
                    this.mRemote.transact(48, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unregisteCustomDataCallBack(IXmCustomDataCallBack iXmCustomDataCallBack) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iXmCustomDataCallBack != null ? iXmCustomDataCallBack.asBinder() : null);
                    this.mRemote.transact(49, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setPlayModel(String str, int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    this.mRemote.transact(50, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getSourseLists(String str, int i, int i2, int i3, int i4, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    obtain.writeLong(j);
                    this.mRemote.transact(51, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void browseAlbums(long j, int i, int i2, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeLong(j2);
                    this.mRemote.transact(52, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setPlayByTrack(String str, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    this.mRemote.transact(53, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setPlayByAlbumTracks(String str, int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    this.mRemote.transact(54, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setCategoryId(int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    this.mRemote.transact(55, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getCategoryModelList(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    this.mRemote.transact(56, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getHotContent(boolean z, int i, int i2, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    this.mRemote.transact(57, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getTrackListByLastTrack(long j, long j2, int i, long j3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeInt(i);
                    obtain.writeLong(j3);
                    this.mRemote.transact(58, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void registeMainDataSupportCallBack(IXmMainDataSupportDataCallback iXmMainDataSupportDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iXmMainDataSupportDataCallback != null ? iXmMainDataSupportDataCallback.asBinder() : null);
                    this.mRemote.transact(59, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unregisteMainDataSupportCallBack(IXmMainDataSupportDataCallback iXmMainDataSupportDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iXmMainDataSupportDataCallback != null ? iXmMainDataSupportDataCallback.asBinder() : null);
                    this.mRemote.transact(60, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getUserInfo(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    this.mRemote.transact(61, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getMyCollect(int i, int i2, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    this.mRemote.transact(62, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getAttentionAlbum(int i, String str, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    this.mRemote.transact(63, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getAlbumInfo(long j, int i, int i2, String str, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    obtain.writeLong(j2);
                    this.mRemote.transact(64, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getRank(String str, String str2, int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    this.mRemote.transact(65, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getMainHotContent(int i, int i2, int i3, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeLong(j);
                    this.mRemote.transact(66, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getNewRank(int i, int i2, int i3, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeLong(j);
                    this.mRemote.transact(67, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getDefultPageSize() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(68, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getAlbumByCategoryId(long j, int i, int i2, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeLong(j2);
                    this.mRemote.transact(69, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getParseDeviceInfo(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    this.mRemote.transact(70, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setDLNAState(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(71, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isDLNAState() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(72, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void needContinuePlay(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(73, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getSuggestAlbums(int i, int i2, boolean z, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeLong(j);
                    this.mRemote.transact(74, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getSpecialListenList(int i, int i2, int i3, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeLong(j);
                    this.mRemote.transact(75, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getSubjectDetail(int i, int i2, long j, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    this.mRemote.transact(76, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void subscribeAlbum(String str, boolean z, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeLong(j);
                    this.mRemote.transact(77, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getTrackListByTrackIdAtAlbum(long j, long j2, boolean z, long j3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeLong(j3);
                    this.mRemote.transact(78, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getRecommendAlbumListByTrackId(long j, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    this.mRemote.transact(79, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getRecommendAlbumListByAlbumId(long j, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    this.mRemote.transact(80, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getProvinces(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    this.mRemote.transact(81, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getRadioList(int i, long j, int i2, int i3, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeLong(j2);
                    this.mRemote.transact(82, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getRadioSchedules(String str, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    this.mRemote.transact(83, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getCategoriesList(int i, int i2, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    this.mRemote.transact(84, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getAlbumByCategoryIdAndTag(long j, int i, int i2, int i3, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeLong(j2);
                    this.mRemote.transact(85, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getTags(long j, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    this.mRemote.transact(86, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getTrackDetailInfo(long j, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    this.mRemote.transact(87, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Track getTrackInfoSync(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    this.mRemote.transact(88, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Track.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setTokenInvalidForSDK(IXmTokenInvalidForSDKCallBack iXmTokenInvalidForSDKCallBack) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iXmTokenInvalidForSDKCallBack != null ? iXmTokenInvalidForSDKCallBack.asBinder() : null);
                    this.mRemote.transact(89, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setTokenToPlayForSDK(AccessToken accessToken) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (accessToken != null) {
                        obtain.writeInt(1);
                        accessToken.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(90, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        accessToken.a(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setRecordModel(RecordModel recordModel) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (recordModel != null) {
                        obtain.writeInt(1);
                        recordModel.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(91, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        recordModel.a(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setVolume(float f, float f2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeFloat(f);
                    obtain.writeFloat(f2);
                    this.mRemote.transact(92, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setPlayStatisticClassName(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(93, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setAdsDataHandlerClassName(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(94, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setPlayCdnConfigureModel(CdnConfigModel cdnConfigModel) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cdnConfigModel != null) {
                        obtain.writeInt(1);
                        cdnConfigModel.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(95, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void resetPlayList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(96, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void removeListByIndex(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(97, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getHistoryPos(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(98, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setHistoryPosById(long j, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeInt(i);
                    this.mRemote.transact(99, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getLastPlayTrackInAlbum(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(100, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setPlayerProcessRequestEnvironment(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(101, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setSoundTouchAllParams(float f, float f2, float f3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeFloat(f);
                    obtain.writeFloat(f2);
                    obtain.writeFloat(f3);
                    this.mRemote.transact(102, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void requestSoundAd() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(103, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void pausePlayInMillis(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    this.mRemote.transact(104, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isAdPlaying() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(105, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean haveNextPlayList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(106, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean havePrePlayList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(107, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isBuffering() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(108, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setProxyNew(Config config) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (config != null) {
                        obtain.writeInt(1);
                        config.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(109, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        config.a(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void exitSoundAd() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(110, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setBreakpointResume(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(111, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isLoading() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(112, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void resetPlayer() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(113, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setCheckAdContent(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(114, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void insertPlayListHead(List<Track> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    this.mRemote.transact(115, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long getCurrentTrackPlayedDuration() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(116, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float getTempo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(117, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setAppkeyAndPackId(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(118, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
