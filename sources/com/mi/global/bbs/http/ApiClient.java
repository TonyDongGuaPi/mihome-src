package com.mi.global.bbs.http;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mi.account.sdk.util.Constants;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.manager.MiCommunitySdkManager;
import com.mi.global.bbs.manager.SdkListener;
import com.mi.global.bbs.model.AllThoughtModel;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.ClaimRewardResult;
import com.mi.global.bbs.model.ColumnArticleModel;
import com.mi.global.bbs.model.ColumnDetailModel;
import com.mi.global.bbs.model.ColumnFollowers;
import com.mi.global.bbs.model.ColumnHomeModel;
import com.mi.global.bbs.model.ColumnListModel;
import com.mi.global.bbs.model.ColumnSubModel;
import com.mi.global.bbs.model.DiscoveryUserModel;
import com.mi.global.bbs.model.FeedbackUploadResult;
import com.mi.global.bbs.model.FollowersResult;
import com.mi.global.bbs.model.ForYouCustomizeModel;
import com.mi.global.bbs.model.GetPushFlagModel;
import com.mi.global.bbs.model.InCityActivityResult;
import com.mi.global.bbs.model.MainLiteModel;
import com.mi.global.bbs.model.MedalDataModel;
import com.mi.global.bbs.model.MyFavorModel;
import com.mi.global.bbs.model.MyReplyModel;
import com.mi.global.bbs.model.MyThreadModel;
import com.mi.global.bbs.model.OnlineActivityResult;
import com.mi.global.bbs.model.PostDetailModel;
import com.mi.global.bbs.model.QuestionsModel;
import com.mi.global.bbs.model.SearchHotModel;
import com.mi.global.bbs.model.SignHomeData;
import com.mi.global.bbs.model.SubFollowItem;
import com.mi.global.bbs.model.SubForumItem;
import com.mi.global.bbs.model.SubForumModel;
import com.mi.global.bbs.model.TaskModel;
import com.mi.global.bbs.model.TaskNewBieModel;
import com.mi.global.bbs.model.UploadResultModel;
import com.mi.global.bbs.model.UserActivitiesModel;
import com.mi.global.bbs.model.UserCenterShareModel;
import com.mi.global.bbs.model.UserInfoModel;
import com.mi.global.bbs.model.UserInfoPermission;
import com.mi.global.bbs.ui.post.PostShortDetailBackItem;
import com.mi.global.bbs.ui.qa.QAHomeTrendItem;
import com.mi.global.bbs.ui.qa.QAInviteItem;
import com.mi.global.bbs.ui.qa.QANewItem;
import com.mi.global.bbs.ui.qa.QARequestItem;
import com.mi.global.bbs.utils.CheckUtil;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.xiaomi.youpin.network.annotation.ContentType;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final long CONNECT_TIME_OUT = 60000;
    private static final long READ_TIME_OUT = 60000;
    private static final long WRITE_TIME_OUT = 60000;
    private static volatile ApiService apiService;
    private static volatile FeedApiService feedService;

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (ApiClient.class) {
                if (apiService == null) {
                    Gson create = new GsonBuilder().setLenient().create();
                    apiService = (ApiService) new Retrofit.Builder().baseUrl(ConnectionHelper.getAppIndexUrl()).addConverterFactory(GsonConverterFactory.create(create)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(genericClient()).build().create(ApiService.class);
                }
            }
        }
        return apiService;
    }

    public static FeedApiService getFeedApiService() {
        if (feedService == null) {
            synchronized (ApiClient.class) {
                if (feedService == null) {
                    Gson create = new GsonBuilder().setLenient().create();
                    feedService = (FeedApiService) new Retrofit.Builder().baseUrl(ConnectionHelper.getFeedIndexUrl()).addConverterFactory(GsonConverterFactory.create(create)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(genericClient()).build().create(FeedApiService.class);
                }
            }
        }
        return feedService;
    }

    public static void setApiServiceNull() {
        feedService = null;
        apiService = null;
    }

    public static <T> Observable<T> scheduleObservable(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    private static OkHttpClient genericClient() {
        return new OkHttpClient.Builder().connectTimeout(60000, TimeUnit.MILLISECONDS).readTimeout(60000, TimeUnit.MILLISECONDS).writeTimeout(60000, TimeUnit.MILLISECONDS).addInterceptor(new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                String cookies = ReqHelper.getCookies();
                Request.Builder newBuilder = chain.request().newBuilder();
                if (!TextUtils.isEmpty(cookies)) {
                    newBuilder.addHeader("Cookie", cookies);
                }
                newBuilder.addHeader("Mi-Info", ReqHelper.getDeviceInfo());
                return chain.proceed(newBuilder.build());
            }
        }).addInterceptor(new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                SdkListener listener;
                Response proceed = chain.proceed(chain.request());
                if (proceed.code() == 401 && (listener = MiCommunitySdkManager.getInstance().getListener()) != null) {
                    MiCommunitySdkManager.getInstance().invalidateLoginData();
                    listener.onTokenExpired(Constants.Account.accountSid);
                }
                return proceed;
            }
        }).addInterceptor(new HttpLoggingInterceptor().setLevel(BBSApplication.isUserTest() ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE)).build();
    }

    public static Observable<JsonObject> signIn(int i, ObservableTransformer<? super JsonObject, ? extends JsonObject> observableTransformer) {
        return scheduleObservable(getApiService().signIn(i).compose(observableTransformer));
    }

    public static Observable<ClaimRewardResult> claimReward(int i, ObservableTransformer<? super ClaimRewardResult, ? extends ClaimRewardResult> observableTransformer) {
        return scheduleObservable(getApiService().claimReward(i).compose(observableTransformer));
    }

    public static Observable<BaseResult> reportThought(String str, String str2, ObservableTransformer<? super BaseResult, ? extends BaseResult> observableTransformer) {
        return scheduleObservable(getApiService().reportThought(str, str2).compose(observableTransformer));
    }

    public static Observable<ClaimRewardResult> newBieClaimReward(int i, ObservableTransformer<? super ClaimRewardResult, ? extends ClaimRewardResult> observableTransformer) {
        return scheduleObservable(getApiService().newBieClaimReward(i).compose(observableTransformer));
    }

    public static Observable<ClaimRewardResult> newBieFinish(int i, ObservableTransformer<? super ClaimRewardResult, ? extends ClaimRewardResult> observableTransformer) {
        return scheduleObservable(getApiService().newBieFinish(i).compose(observableTransformer));
    }

    public static Observable<TaskModel> getTask(ObservableTransformer<? super TaskModel, ? extends TaskModel> observableTransformer) {
        return scheduleObservable(getApiService().getTask().compose(observableTransformer));
    }

    public static Observable<TaskNewBieModel> getNewBieTask(ObservableTransformer<? super TaskNewBieModel, ? extends TaskNewBieModel> observableTransformer) {
        return scheduleObservable(getApiService().getNewBieTask().compose(observableTransformer));
    }

    public static Observable<AllThoughtModel> getAllThoughtList(int i, int i2, ObservableTransformer<? super AllThoughtModel, ? extends AllThoughtModel> observableTransformer) {
        return scheduleObservable(getApiService().getAllThoughtList(ParamsProvider.getColumnParams(i, i2)).compose(observableTransformer));
    }

    public static Observable<JsonObject> getMonthSignedData(String str, ObservableTransformer<? super JsonObject, ? extends JsonObject> observableTransformer) {
        return scheduleObservable(getApiService().getMonthSignedData(str).compose(observableTransformer));
    }

    public static Observable<JsonObject> isSigned(ObservableTransformer<? super JsonObject, ? extends JsonObject> observableTransformer) {
        return scheduleObservable(getApiService().isSigned().compose(observableTransformer));
    }

    public static Observable<MedalDataModel> getMedal(ObservableTransformer<? super MedalDataModel, ? extends MedalDataModel> observableTransformer) {
        return scheduleObservable(getApiService().getMedal().compose(observableTransformer));
    }

    public static void clearMedal() {
        scheduleObservable(getApiService().clearMedal()).subscribe(new Consumer<JsonObject>() {
            public void accept(@NonNull JsonObject jsonObject) throws Exception {
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
            }
        });
    }

    public static Observable<JsonObject> createNewThread(String str, String str2, String str3, String str4, ObservableTransformer<? super JsonObject, ? extends JsonObject> observableTransformer) {
        return scheduleObservable(getApiService().createNewThread(ParamsProvider.getNewThreadParams(str, str2, str3, str4)).compose(observableTransformer));
    }

    public static Observable<JsonObject> createQuestionThread(String str, String str2, String str3, String str4, int i, String str5, ObservableTransformer<? super JsonObject, ? extends JsonObject> observableTransformer) {
        return scheduleObservable(getApiService().createNewThread(ParamsProvider.getQuestionTreadParams(str, str2, str3, str4, i, str5)).compose(observableTransformer));
    }

    public static Observable<UploadResultModel> uploadImage(String str, ObservableTransformer<? super UploadResultModel, ? extends UploadResultModel> observableTransformer) {
        return scheduleObservable(getApiService().uploadImage(RequestBody.create(MediaType.parse("application/octet-stream"), new File(str))).compose(observableTransformer));
    }

    public static Observable<GetPushFlagModel> getPushMsgFlag(ObservableTransformer<? super GetPushFlagModel, ? extends GetPushFlagModel> observableTransformer) {
        return scheduleObservable(getApiService().getPushMsgFlag().compose(observableTransformer));
    }

    public static Observable<BaseResult> setPushMsgFlag(int i) {
        return scheduleObservable(getApiService().setPushMsgFlag(i));
    }

    public static Observable<FeedbackUploadResult> uploadFeedbackImage(String str) {
        return scheduleObservable(getApiService().uploadFeedbackImage(RequestBody.create(MediaType.parse("application/octet-stream"), new File(str))));
    }

    public static Observable<BaseResult> postFeedbackContent(String str, String str2, String str3, String str4, String str5, ObservableTransformer<? super BaseResult, ? extends BaseResult> observableTransformer) {
        return scheduleObservable(getApiService().postFeedbackContent(ParamsProvider.getFeedbackParams(str, str2, str3, str4, str5)).compose(observableTransformer));
    }

    public static Observable<SearchHotModel> getHotSearchKey(ObservableTransformer<? super SearchHotModel, ? extends SearchHotModel> observableTransformer) {
        return scheduleObservable(getApiService().getHotSearchKey().compose(observableTransformer));
    }

    public static Observable<JsonObject> search(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, ObservableTransformer<? super JsonObject, ? extends JsonObject> observableTransformer) {
        return scheduleObservable(getApiService().search(ParamsProvider.getSearchParams(str, str2, str3, str4, str5, str6, str7, str8)).compose(observableTransformer));
    }

    public static Observable<JsonObject> searchQA(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, ObservableTransformer<? super JsonObject, ? extends JsonObject> observableTransformer) {
        return scheduleObservable(getApiService().search(ParamsProvider.getSearchQAParams(str, str2, str3, str4, str5, str6, str7, str8, str9)).compose(observableTransformer));
    }

    public static Observable<SubForumModel> getSubForum(ObservableTransformer<? super SubForumModel, ? extends SubForumModel> observableTransformer) {
        return scheduleObservable(getApiService().getSubForum().compose(observableTransformer));
    }

    public static Observable<ForYouCustomizeModel> getForYouCustomize(ObservableTransformer<? super ForYouCustomizeModel, ? extends ForYouCustomizeModel> observableTransformer) {
        return scheduleObservable(getApiService().getForYouCustomize().compose(observableTransformer));
    }

    public static Observable<UserInfoModel> getUserInfo(ObservableTransformer<? super UserInfoModel, ? extends UserInfoModel> observableTransformer) {
        return scheduleObservable(getApiService().getUserInfo().compose(observableTransformer));
    }

    public static Observable<JsonObject> getPermission(ObservableTransformer<? super JsonObject, ? extends JsonObject> observableTransformer) {
        return scheduleObservable(getApiService().getPermission().compose(observableTransformer));
    }

    public static Observable<UserInfoModel> getUserInfo(String str, ObservableTransformer<? super UserInfoModel, ? extends UserInfoModel> observableTransformer) {
        return scheduleObservable(getApiService().getUserInfo(CheckUtil.getCheckAes(str)).compose(observableTransformer));
    }

    public static Observable<MyFavorModel> getFavor(int i, int i2, ObservableTransformer<? super MyFavorModel, ? extends MyFavorModel> observableTransformer) {
        return scheduleObservable(getApiService().getFavor(i, i2).compose(observableTransformer));
    }

    public static Observable<MyReplyModel> getMyReply(String str, int i, int i2, ObservableTransformer<? super MyReplyModel, ? extends MyReplyModel> observableTransformer) {
        Map<String, String> postParams = ParamsProvider.getPostParams(i, i2);
        if (!TextUtils.isEmpty(str)) {
            postParams.put("uid", CheckUtil.getCheckAes(str));
        }
        return scheduleObservable(getApiService().getMyReply(postParams).compose(observableTransformer));
    }

    public static Observable<MyThreadModel> getMyPost(String str, int i, int i2, ObservableTransformer<? super MyThreadModel, ? extends MyThreadModel> observableTransformer) {
        Map<String, String> postParams = ParamsProvider.getPostParams(i, i2);
        if (!TextUtils.isEmpty(str)) {
            postParams.put("uid", CheckUtil.getCheckAes(str));
        }
        return scheduleObservable(getApiService().getMyPost(postParams).compose(observableTransformer));
    }

    public static Observable<SubForumItem> getHotList(int i, int i2, ObservableTransformer<? super SubForumItem, ? extends SubForumItem> observableTransformer) {
        return scheduleObservable(getApiService().getHotList(ParamsProvider.getPostParams(i, i2)).compose(observableTransformer));
    }

    public static Observable<SubForumItem> getForumList(int i, int i2, ObservableTransformer<? super SubForumItem, ? extends SubForumItem> observableTransformer) {
        return scheduleObservable(getApiService().getForumList(ParamsProvider.getPostParams(i, i2)).compose(observableTransformer));
    }

    public static Observable<BaseResult> follow(String str, boolean z, ObservableTransformer<? super BaseResult, ? extends BaseResult> observableTransformer) {
        return scheduleObservable(getApiService().follow(z ? "add" : "del", CheckUtil.getCheckAes(str)).compose(observableTransformer));
    }

    public static Observable<BaseResult> editUserProfileBg(String str, ObservableTransformer<? super BaseResult, ? extends BaseResult> observableTransformer) {
        HashMap hashMap = new HashMap();
        hashMap.put(ParamKey.bkgimg, str);
        return scheduleObservable(getApiService().editUserProfile(hashMap).compose(observableTransformer));
    }

    public static Observable<BaseResult> editUserProfile(UserInfoModel.DataBean dataBean, UserInfoPermission userInfoPermission, ObservableTransformer<? super BaseResult, ? extends BaseResult> observableTransformer) {
        return scheduleObservable(getApiService().editUserProfile(ParamsProvider.getUserInfo(dataBean, userInfoPermission)).compose(observableTransformer));
    }

    public static Observable<UserActivitiesModel> getUserActivity(String str, int i, int i2, ObservableTransformer<? super UserActivitiesModel, ? extends UserActivitiesModel> observableTransformer) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("uid", CheckUtil.getCheckAes(str));
        }
        hashMap.put("start", String.valueOf(i));
        hashMap.put("page", String.valueOf(i2));
        return scheduleObservable(getApiService().getUserActivity(hashMap).compose(observableTransformer));
    }

    public static Observable<UserCenterShareModel> getUserShare(String str, ObservableTransformer<? super UserCenterShareModel, ? extends UserCenterShareModel> observableTransformer) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("uid", CheckUtil.getCheckAes(str));
        }
        return scheduleObservable(getApiService().getUserShare(hashMap).compose(observableTransformer));
    }

    public static Observable<SubForumItem> getSubforumItem(String str, int i, String str2, String str3, ObservableTransformer<? super SubForumItem, ? extends SubForumItem> observableTransformer) {
        return scheduleObservable(getApiService().getSubforumItem(ParamsProvider.getSubforumItem(str, i, str2, str3)).compose(observableTransformer));
    }

    public static Observable<QAHomeTrendItem> getQATrendingList(int i, int i2, ObservableTransformer<? super QAHomeTrendItem, ? extends QAHomeTrendItem> observableTransformer) {
        return scheduleObservable(getApiService().getQATrending(i, i2).compose(observableTransformer));
    }

    public static Observable<QANewItem> getQANewList(int i, int i2, ObservableTransformer<? super QANewItem, ? extends QANewItem> observableTransformer) {
        return scheduleObservable(getApiService().getQANewList(i, i2).compose(observableTransformer));
    }

    public static String getAppUrl(String str) {
        return ConnectionHelper.getAppIndexUrl() + str;
    }

    public static Observable<JsonObject> getForumComments(String str, int i, int i2, ObservableTransformer<? super JsonObject, ? extends JsonObject> observableTransformer) {
        return scheduleObservable(getApiService().getForumComments(ParamsProvider.getForumComments(str, i, i2)).compose(observableTransformer));
    }

    public static Observable<JsonObject> getForumComments(String str, ObservableTransformer<? super JsonObject, ? extends JsonObject> observableTransformer) {
        return scheduleObservable(getApiService().getForumComments(str).compose(observableTransformer));
    }

    public static Observable<BaseResult> postComments(String str, String str2, String str3, List<String> list, String str4, ObservableTransformer<? super BaseResult, ? extends BaseResult> observableTransformer) {
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (list != null && list.size() > 0) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                File file = new File(list.get(i));
                RequestBody create = RequestBody.create(MediaType.parse(ContentType.FORM_DATA), file);
                type.addFormDataPart("images_" + i, file.getName(), create);
            }
        }
        Map<String, String> postComments = ParamsProvider.getPostComments(str, str2, str3, str4);
        for (String next : postComments.keySet()) {
            type.addFormDataPart(next, postComments.get(next));
        }
        return scheduleObservable(getApiService().postComments(type.build().parts()).compose(observableTransformer));
    }

    public static void addParameter(Map<String, RequestBody> map, String str, Object obj) {
        if (obj instanceof String) {
            map.put(str, RequestBody.create(MediaType.parse("text/plain"), (String) obj));
        } else if (obj instanceof File) {
            File file = (File) obj;
            RequestBody create = RequestBody.create(MediaType.parse("bbs_image/*"), file);
            map.put(str + "\"; filename=\"" + file.getName() + "", create);
        }
    }

    public static void get(String str) {
        scheduleObservable(getApiService().get(str)).subscribe(new Consumer<JsonObject>() {
            public void accept(@NonNull JsonObject jsonObject) throws Exception {
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
            }
        });
    }

    public static Observable<BaseResult> post(String str, ObservableTransformer<? super BaseResult, ? extends BaseResult> observableTransformer) {
        return scheduleObservable(getApiService().post(str).compose(observableTransformer));
    }

    public static Observable<JsonObject> getSyncInfo(Map<String, String> map, ObservableTransformer<? super JsonObject, ? extends JsonObject> observableTransformer) {
        return scheduleObservable(getApiService().getSysncInfo(map).compose(observableTransformer));
    }

    public static Observable<JsonObject> reportDeviceInfo(Map<String, String> map) {
        return scheduleObservable(getApiService().registerPush(map));
    }

    public static Observable<JsonObject> postLoginCallBack(String str, String str2) {
        return scheduleObservable(getApiService().loginCallBack(ParamsProvider.getLoginCallBackParams(CheckUtil.getCheckAes(str), str2)));
    }

    public static Observable<JsonObject> getHomePostData(ObservableTransformer<? super JsonObject, ? extends JsonObject> observableTransformer) {
        return scheduleObservable(getApiService().getHomePost().compose(observableTransformer));
    }

    public static Observable<JsonObject> postHomeMoreData(String str, String str2, String str3, ObservableTransformer<? super JsonObject, ? extends JsonObject> observableTransformer) {
        return scheduleObservable(getApiService().postHomeMore(ParamsProvider.getHomeMoreParams(str, str2, str3)).compose(observableTransformer));
    }

    public static Observable<JsonObject> getForyouData(int i, int i2, int i3, ObservableTransformer<? super JsonObject, ? extends JsonObject> observableTransformer) {
        return scheduleObservable(getApiService().getForyouList(ParamsProvider.getForyouParams(i, i2, i3)).compose(observableTransformer));
    }

    public static Observable<JsonObject> postFavorite(String str, String str2, ObservableTransformer<? super JsonObject, ? extends JsonObject> observableTransformer) {
        return scheduleObservable(getApiService().postFavorite(ParamsProvider.getFavoriteParams(str, str2)).compose(observableTransformer));
    }

    public static Observable<JsonObject> getCarEventGift(String str) {
        return scheduleObservable(getApiService().getCardGift(str));
    }

    public static Observable<MainLiteModel> getLiteHomeData(int i, int i2, String str, ObservableTransformer<? super MainLiteModel, ? extends MainLiteModel> observableTransformer) {
        return scheduleObservable(getApiService().getLiteHomeData(ParamsProvider.getLiteHomeParams(i, i2, str)).compose(observableTransformer));
    }

    public static Observable<JsonObject> getOpenCountry(ObservableTransformer<? super JsonObject, ? extends JsonObject> observableTransformer) {
        return scheduleObservable(getApiService().getOpenCountry().compose(observableTransformer));
    }

    public static Observable<JsonObject> getLanguageList(ObservableTransformer<? super JsonObject, ? extends JsonObject> observableTransformer) {
        return scheduleObservable(getApiService().getLanguageList().compose(observableTransformer));
    }

    public static Observable<ColumnHomeModel> getColumnIndex(int i, int i2, ObservableTransformer<? super ColumnHomeModel, ? extends ColumnHomeModel> observableTransformer) {
        return scheduleObservable(getApiService().getColumnIndex(ParamsProvider.getColumnParams(i, i2)).compose(observableTransformer));
    }

    public static Observable<ColumnArticleModel> getColumnArticles(int i, int i2, String str, ObservableTransformer<? super ColumnArticleModel, ? extends ColumnArticleModel> observableTransformer) {
        return scheduleObservable(getApiService().getColumnArticles(ParamsProvider.getArticlesParams(i, i2, str)).compose(observableTransformer));
    }

    public static Observable<ColumnDetailModel> getColumnDetail(String str, ObservableTransformer<? super ColumnDetailModel, ? extends ColumnDetailModel> observableTransformer) {
        return scheduleObservable(getApiService().getColumnDetail(ParamsProvider.getColumnDetailParams(str)).compose(observableTransformer));
    }

    public static void thumbUp(String str, String str2) {
        scheduleObservable(getApiService().thumbUp(ParamsProvider.geThumbUpParams(str, CheckUtil.getCheckAes(str2)))).subscribe(new Consumer<BaseResult>() {
            public void accept(@NonNull BaseResult baseResult) throws Exception {
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
            }
        });
    }

    public static void thumbUpComment(String str, String str2) {
        scheduleObservable(getApiService().thumbUpComment(ParamsProvider.geThumbUpParams(str, CheckUtil.getCheckAes(str2)))).subscribe(new Consumer<BaseResult>() {
            public void accept(@NonNull BaseResult baseResult) throws Exception {
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
            }
        });
    }

    public static Observable<BaseResult> followColumn(String str, int i, ObservableTransformer<? super BaseResult, ? extends BaseResult> observableTransformer) {
        return scheduleObservable(getApiService().followColumn(ParamsProvider.getFollowColumnParams(str, i))).compose(observableTransformer);
    }

    public static Observable<FollowersResult> getFollowers(String str, int i) {
        return scheduleObservable(getApiService().getFollowers(CheckUtil.getCheckAes(str), i));
    }

    public static Observable<FollowersResult> getFolloweing(String str, int i) {
        return scheduleObservable(getApiService().getFollowing(CheckUtil.getCheckAes(str), i));
    }

    public static Observable<QARequestItem> getRequestList(int i, int i2, ObservableTransformer<? super QARequestItem, ? extends QARequestItem> observableTransformer) {
        return scheduleObservable(getApiService().getRequestList(i, i2).compose(observableTransformer));
    }

    public static Observable<QARequestItem> deleteQuestionThread(int i, int i2, ObservableTransformer<? super QARequestItem, ? extends QARequestItem> observableTransformer) {
        return scheduleObservable(getApiService().deleteQuestionThread(i, i2).compose(observableTransformer));
    }

    public static Observable<QAInviteItem> getInviteList(int i, int i2, ObservableTransformer<? super QAInviteItem, ? extends QAInviteItem> observableTransformer) {
        return scheduleObservable(getApiService().getInviteList(i, i2).compose(observableTransformer));
    }

    public static Observable<ColumnSubModel> getColumnSub(int i, int i2, ObservableTransformer<? super ColumnSubModel, ? extends ColumnSubModel> observableTransformer) {
        return scheduleObservable(getApiService().getColumnSub(ParamsProvider.getColumnSubParams(i, i2)).compose(observableTransformer));
    }

    public static Observable<ColumnListModel> getColumnList(int i, int i2, ObservableTransformer<? super ColumnListModel, ? extends ColumnListModel> observableTransformer) {
        return scheduleObservable(getApiService().getColumnList(ParamsProvider.getColumnListParams(i, i2)).compose(observableTransformer));
    }

    public static Observable<BaseResult> changeColumnPush(String str, int i, ObservableTransformer<? super BaseResult, ? extends BaseResult> observableTransformer) {
        return scheduleObservable(getApiService().changeColumnPush(ParamsProvider.getChangeColumnPushParams(str, i))).compose(observableTransformer);
    }

    public static Observable<BaseResult> changeColumnBg(String str, String str2, ObservableTransformer<? super BaseResult, ? extends BaseResult> observableTransformer) {
        return scheduleObservable(getApiService().changeCulumnBg(ParamsProvider.getChangeColumnBgParams(str, str2)).compose(observableTransformer));
    }

    public static Observable<ColumnFollowers> getColumnFollowers(String str, int i) {
        return scheduleObservable(getApiService().getColumnFollowers(str, i, 10));
    }

    public static Observable<MyThreadModel> getMyColumnPost(String str, int i, int i2, ObservableTransformer<? super MyThreadModel, ? extends MyThreadModel> observableTransformer) {
        Map<String, String> postParams = ParamsProvider.getPostParams(i, i2);
        if (!TextUtils.isEmpty(str)) {
            postParams.put("uid", CheckUtil.getCheckAes(str));
        }
        return scheduleObservable(getApiService().getMyColumnPost(postParams).compose(observableTransformer));
    }

    public static Observable<QuestionsModel> getQuestionStep() {
        return scheduleObservable(getApiService().getQuestionsStep());
    }

    public static Observable<BaseResult> followSubs(List<String> list, List<String> list2) {
        return scheduleObservable(getApiService().followSubs((String[]) list.toArray(new String[0]), (String[]) list2.toArray(new String[0])));
    }

    public static Observable<BaseResult> followForum(String str, String str2) {
        return scheduleObservable(getApiService().followForum(ParamsProvider.getFollowForumParams(str, str2)));
    }

    public static Observable<SignHomeData> getSignHomeData() {
        return scheduleObservable(getApiService().getSignHomeData());
    }

    public static Observable<BaseResult> miHomeNewUser() {
        return scheduleObservable(getApiService().miHomeNewUser());
    }

    public static Observable<SubFollowItem> getFollowingFeed(int i, int i2, String str, ObservableTransformer<? super SubFollowItem, ? extends SubFollowItem> observableTransformer) {
        Map<String, String> postParams = ParamsProvider.getPostParams(i, i2);
        postParams.put("id", str);
        return scheduleObservable(getFeedApiService().getFeed(postParams).compose(observableTransformer));
    }

    public static Observable<OnlineActivityResult> getOnlineActivityList(int i, int i2, ObservableTransformer<? super OnlineActivityResult, ? extends OnlineActivityResult> observableTransformer) {
        return scheduleObservable(getApiService().getOnlineActivityList(ParamsProvider.getPostParams(i, i2)).compose(observableTransformer));
    }

    public static Observable<InCityActivityResult> getInCityActivityList(int i, int i2, int i3, ObservableTransformer<? super InCityActivityResult, ? extends InCityActivityResult> observableTransformer) {
        return scheduleObservable(getApiService().getInCityActivityList(ParamsProvider.getIncityActivityParams(i, i2, i3)).compose(observableTransformer));
    }

    public static Observable<BaseResult> followingFollowDone(String str, ObservableTransformer<? super BaseResult, ? extends BaseResult> observableTransformer) {
        return scheduleObservable(getApiService().followingFollowDone(str).compose(observableTransformer));
    }

    public static Observable<BaseResult> postSignComments(String str, String str2, String str3, List<String> list, ObservableTransformer<? super BaseResult, ? extends BaseResult> observableTransformer) {
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (list != null && list.size() > 0) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                File file = new File(list.get(i));
                RequestBody create = RequestBody.create(MediaType.parse(ContentType.FORM_DATA), file);
                type.addFormDataPart("name_" + i, file.getName(), create);
            }
        }
        Map<String, String> postSignComments = ParamsProvider.getPostSignComments(str, str2, str3);
        for (String next : postSignComments.keySet()) {
            type.addFormDataPart(next, postSignComments.get(next));
        }
        return scheduleObservable(getApiService().signReply(type.build().parts()).compose(observableTransformer));
    }

    public static Observable<DiscoveryUserModel> getDiscoveryPeople(int i, int i2, int i3, ObservableTransformer<? super DiscoveryUserModel, ? extends DiscoveryUserModel> observableTransformer) {
        return scheduleObservable(getFeedApiService().getDiscoveryPeople(ParamsProvider.getFollowUserInfo(i, i2, i3)).compose(observableTransformer));
    }

    public static Observable<PostDetailModel> getPostDetail(String str, ObservableTransformer<? super PostDetailModel, ? extends PostDetailModel> observableTransformer) {
        return scheduleObservable(getApiService().getPostDetail(str).compose(observableTransformer));
    }

    public static Observable<BaseResult> postVotePoll(String str, String str2, List<String> list, ObservableTransformer<? super BaseResult, ? extends BaseResult> observableTransformer) {
        return scheduleObservable(getApiService().votePoll((String[]) list.toArray(new String[0]), ParamsProvider.getVoteParams(str, str2)).compose(observableTransformer));
    }

    public static Observable<PostShortDetailBackItem> postShortContent(String str, String str2, List<String> list) {
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (list != null && list.size() > 0) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                File file = new File(list.get(i));
                RequestBody create = RequestBody.create(MediaType.parse(ContentType.FORM_DATA), file);
                type.addFormDataPart("images_" + i, file.getName(), create);
            }
        }
        if (!TextUtils.isEmpty(str)) {
            type.addFormDataPart("message", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            type.addFormDataPart("url", str2);
        }
        return scheduleObservable(getApiService().postShortContent(type.build().parts()));
    }

    public static Observable<BaseResult> postShortContentComments(String str, String str2, String str3, List<String> list, ObservableTransformer<? super BaseResult, ? extends BaseResult> observableTransformer) {
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (list != null && list.size() > 0) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                File file = new File(list.get(i));
                RequestBody create = RequestBody.create(MediaType.parse(ContentType.FORM_DATA), file);
                type.addFormDataPart("name_" + i, file.getName(), create);
            }
        }
        Map<String, String> postSignComments = ParamsProvider.getPostSignComments(str, str2, str3);
        for (String next : postSignComments.keySet()) {
            type.addFormDataPart(next, postSignComments.get(next));
        }
        return scheduleObservable(getApiService().shortContentReply(type.build().parts()).compose(observableTransformer));
    }
}
