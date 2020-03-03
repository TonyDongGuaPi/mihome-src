package com.mi.global.bbs.http;

import com.google.gson.JsonObject;
import com.mi.global.bbs.model.AllThoughtModel;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.ClaimRewardResult;
import com.mi.global.bbs.model.ColumnArticleModel;
import com.mi.global.bbs.model.ColumnDetailModel;
import com.mi.global.bbs.model.ColumnFollowers;
import com.mi.global.bbs.model.ColumnHomeModel;
import com.mi.global.bbs.model.ColumnListModel;
import com.mi.global.bbs.model.ColumnSubModel;
import com.mi.global.bbs.model.FeedbackUploadResult;
import com.mi.global.bbs.model.FollowersResult;
import com.mi.global.bbs.model.ForYouCustomizeModel;
import com.mi.global.bbs.model.GetPushFlagModel;
import com.mi.global.bbs.model.InCityActivityResult;
import com.mi.global.bbs.model.LeaderBoardBean;
import com.mi.global.bbs.model.MIUIContentModel;
import com.mi.global.bbs.model.MIUIForumModel;
import com.mi.global.bbs.model.MainLiteModel;
import com.mi.global.bbs.model.MedalDataModel;
import com.mi.global.bbs.model.MyFavorModel;
import com.mi.global.bbs.model.MyReplyModel;
import com.mi.global.bbs.model.MyThreadModel;
import com.mi.global.bbs.model.OnlineActivityResult;
import com.mi.global.bbs.model.PostDetailModel;
import com.mi.global.bbs.model.QuestionsModel;
import com.mi.global.bbs.model.SearchHotModel;
import com.mi.global.bbs.model.ShortContentModel;
import com.mi.global.bbs.model.SignComments;
import com.mi.global.bbs.model.SignContentResult;
import com.mi.global.bbs.model.SignHomeData;
import com.mi.global.bbs.model.SignPostResult;
import com.mi.global.bbs.model.SubForumItem;
import com.mi.global.bbs.model.SubForumModel;
import com.mi.global.bbs.model.TaskModel;
import com.mi.global.bbs.model.TaskNewBieModel;
import com.mi.global.bbs.model.UploadResultModel;
import com.mi.global.bbs.model.UserActivitiesModel;
import com.mi.global.bbs.model.UserCenterShareModel;
import com.mi.global.bbs.model.UserInfoModel;
import com.mi.global.bbs.ui.post.PostShortDetailBackItem;
import com.mi.global.bbs.ui.qa.QAHomeTrendItem;
import com.mi.global.bbs.ui.qa.QAInviteItem;
import com.mi.global.bbs.ui.qa.QANewItem;
import com.mi.global.bbs.ui.qa.QARequestItem;
import io.reactivex.Observable;
import java.util.List;
import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {
    @FormUrlEncoded
    @POST("column/modpush")
    Observable<BaseResult> changeColumnPush(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("column/modimg")
    Observable<BaseResult> changeCulumnBg(@FieldMap Map<String, String> map);

    @GET("ajax.php?mod=ajaxtask")
    Observable<ClaimRewardResult> claimReward(@Query("rewardtask") int i);

    @GET("ajax.php?mod=medaltask")
    Observable<JsonObject> clearMedal();

    @FormUrlEncoded
    @POST("app.php?mod=newthreadtext")
    Observable<JsonObject> createNewThread(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app.php?mod=newthreadtext")
    Observable<JsonObject> createSpecialNewThread(@Field("polloption[]") String[] strArr, @FieldMap Map<String, String> map);

    @GET("ajax.php?mod=ajaxdynamicreplydelete")
    Observable<BaseResult> delShortContentReply(@QueryMap Map<String, String> map);

    @GET("ajax.php?mod=ajaxdynamicreplydelete")
    Observable<BaseResult> delSignReply(@QueryMap Map<String, String> map);

    @GET("ajax.php?mod=qarecommend")
    Observable<QARequestItem> deleteQuestionThread(@Query("ignore") int i, @Query("id") int i2);

    @FormUrlEncoded
    @POST("home.php?mod=spacecp&ac=profile&op=base&profilesubmit=1&profilesubmitbtn=1&isajax=1")
    Observable<BaseResult> editUserProfile(@FieldMap Map<String, String> map);

    @GET("home.php?mod=spacecp&ac=follow&handlekey=followmod&isajax=1")
    Observable<BaseResult> follow(@Query("op") String str, @Query("fuid") String str2);

    @FormUrlEncoded
    @POST("column/subscribe")
    Observable<BaseResult> followColumn(@FieldMap Map<String, String> map);

    @GET("home.php?mod=spacecp&ac=favorite&type=forum&handlekey=favoriteforum&outputajax=1")
    Observable<BaseResult> followForum(@QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST("home.php?mod=spacecp&ac=favorite&type=forum&handlekey=favoriteforum&outputajax=1&followmulti=1&from=questions_foryou")
    Observable<BaseResult> followSubs(@Field("id[]") String[] strArr, @Field("delid[]") String[] strArr2);

    @GET("ajax.php?mod=feedfollow")
    Observable<BaseResult> followingFollowDone(@Query("followuid") String str);

    @GET
    Observable<JsonObject> get(@Url String str);

    @GET("app.php?mod=shortmessage")
    Observable<AllThoughtModel> getAllThoughtList(@QueryMap Map<String, String> map);

    @GET
    Observable<JsonObject> getCardGift(@Url String str);

    @GET("column/articles")
    Observable<ColumnArticleModel> getColumnArticles(@QueryMap Map<String, String> map);

    @GET("column/detail")
    Observable<ColumnDetailModel> getColumnDetail(@QueryMap Map<String, String> map);

    @GET("column/subscribeusers")
    Observable<ColumnFollowers> getColumnFollowers(@Query("columnID") String str, @Query("page") int i, @Query("num") int i2);

    @GET("column/index")
    Observable<ColumnHomeModel> getColumnIndex(@QueryMap Map<String, String> map);

    @GET("column/list")
    Observable<ColumnListModel> getColumnList(@QueryMap Map<String, String> map);

    @GET("column/subscribelist")
    Observable<ColumnSubModel> getColumnSub(@QueryMap Map<String, String> map);

    @GET("app.php?mod=home&ac=favthread")
    Observable<MyFavorModel> getFavor(@Query("page") int i, @Query("perpage") int i2);

    @GET("home.php?mod=space&do=newfollow&type=follower&isajax=1")
    Observable<FollowersResult> getFollowers(@Query("uid") String str, @Query("page") int i);

    @GET("home.php?mod=space&do=newfollow&type=following&isajax=1")
    Observable<FollowersResult> getFollowing(@Query("uid") String str, @Query("page") int i);

    @GET("index.php?mod=fidlist&usage=foryou")
    Observable<ForYouCustomizeModel> getForYouCustomize();

    @GET
    Observable<JsonObject> getForumComments(@Url String str);

    @GET("forum.php?mod=viewthread")
    Observable<JsonObject> getForumComments(@QueryMap Map<String, String> map);

    @GET("forum.php?outputajax=1")
    Observable<SubForumItem> getForumList(@QueryMap Map<String, String> map);

    @GET("foryou/list")
    Observable<JsonObject> getForyouList(@QueryMap Map<String, String> map);

    @GET("app.php?mod=index&new=1")
    Observable<JsonObject> getHomePost();

    @GET("ajax.php?outputajax=1")
    Observable<SubForumItem> getHotList(@QueryMap Map<String, String> map);

    @GET("ajax.php?mod=hotwords")
    Observable<SearchHotModel> getHotSearchKey();

    @GET("thread/getActivityList")
    Observable<InCityActivityResult> getInCityActivityList(@QueryMap Map<String, String> map);

    @GET("ajax.php?mod=qarecommend")
    Observable<QAInviteItem> getInviteList(@Query("getuser") int i, @Query("fid") int i2);

    @GET("app.php?mod=language")
    Observable<JsonObject> getLanguageList();

    @GET("forum.php?outputajax=1")
    Observable<MainLiteModel> getLiteHomeData(@QueryMap Map<String, String> map);

    @GET("forum/getMiuiContent")
    Observable<MIUIContentModel> getMIUIContent();

    @GET("forum/threadList")
    Observable<MIUIForumModel> getMIUIForumList(@Query("name") String str, @Query("page") int i, @Query("pageSize") int i2);

    @GET("ajax.php?mod=medaltask&get=1")
    Observable<MedalDataModel> getMedal();

    @GET("ajax.php?mod=ajaxemotion")
    Observable<JsonObject> getMonthSignedData(@Query("yearmon") String str);

    @FormUrlEncoded
    @POST("column/mythreadlist")
    Observable<MyThreadModel> getMyColumnPost(@FieldMap Map<String, String> map);

    @GET("home.php?mod=space&do=thread&view=me&type=thread&outputajax=1")
    Observable<MyThreadModel> getMyPost(@QueryMap Map<String, String> map);

    @GET("home.php?mod=space&do=thread&view=me&from=space&type=reply&outputajax=1")
    Observable<MyReplyModel> getMyReply(@QueryMap Map<String, String> map);

    @GET("ajax.php?mod=newcomertask")
    Observable<TaskNewBieModel> getNewBieTask();

    @GET("thread/getOnlineActivityList")
    Observable<OnlineActivityResult> getOnlineActivityList(@QueryMap Map<String, String> map);

    @GET("app.php?mod=opencountry")
    Observable<JsonObject> getOpenCountry();

    @GET("home.php?mod=spacecp&isajax=1")
    Observable<JsonObject> getPermission();

    @GET
    Observable<PostDetailModel> getPostDetail(@Url String str);

    @GET("ajax.php?mod=pushmsgflag")
    Observable<GetPushFlagModel> getPushMsgFlag();

    @GET("app.php?mod=qanewest")
    Observable<QANewItem> getQANewList(@Query("start") int i, @Query("limit") int i2);

    @GET("app.php?mod=qatrend")
    Observable<QAHomeTrendItem> getQATrending(@Query("start") int i, @Query("limit") int i2);

    @GET("foryou/forums")
    Observable<QuestionsModel> getQuestionsStep();

    @GET("ajax.php?mod=qarecommend")
    Observable<QARequestItem> getRequestList(@Query("type") int i, @Query("start") int i2);

    @GET("ajax.php?mod=ajaxdynamicreplylist")
    Observable<SignComments> getShortContentComments(@Query("id") String str, @Query("page") int i, @Query("num") int i2, @Query("order") String str2, @Query("typeid") int i3);

    @GET("ajax.php?mod=ajaxdynamicdetail")
    Observable<ShortContentModel> getShortContentDetail(@Query("id") String str, @Query("typeid") int i);

    @GET("ajax.php?mod=ajaxsignleaderboard")
    Observable<LeaderBoardBean> getSignBoard(@Query("page") int i, @Query("num") int i2);

    @GET("ajax.php?mod=ajaxsigncalendar")
    Observable<JsonObject> getSignCalendar(@Query("yearmon") String str);

    @GET("ajax.php?mod=ajaxdynamicdetail")
    Observable<SignContentResult> getSignDetail(@Query("id") String str);

    @GET("ajax.php?mod=ajaxsignindex")
    Observable<SignHomeData> getSignHomeData();

    @GET("ajax.php?mod=ajaxdynamicreplylist")
    Observable<SignComments> getSignReply(@Query("id") String str, @Query("page") int i, @Query("num") int i2, @Query("order") String str2);

    @GET("index.php?mod=fidlist")
    Observable<SubForumModel> getSubForum();

    @GET("forum.php?mod=forumdisplay")
    Observable<SubForumItem> getSubforumItem(@QueryMap Map<String, String> map);

    @GET("app.php?mod=sync")
    Observable<JsonObject> getSysncInfo(@QueryMap Map<String, String> map);

    @GET("ajax.php?mod=ajaxtask&get=1")
    Observable<TaskModel> getTask();

    @GET("ajax.php?mod=useractivity")
    Observable<UserActivitiesModel> getUserActivity(@QueryMap Map<String, String> map);

    @GET("home.php?mod=space&isajax=1")
    Observable<UserInfoModel> getUserInfo();

    @GET("home.php?mod=space&isajax=1")
    Observable<UserInfoModel> getUserInfo(@Query("uid") String str);

    @GET("ajax.php?mod=usershare")
    Observable<UserCenterShareModel> getUserShare(@QueryMap Map<String, String> map);

    @GET("ajax.php?mod=ajaxemotion")
    Observable<JsonObject> isSigned();

    @GET("app.php?mod=loginback")
    Observable<JsonObject> loginCallBack(@QueryMap Map<String, String> map);

    @GET("app.php?mod=newuser")
    Observable<BaseResult> miHomeNewUser();

    @GET("ajax.php?mod=newcomertask")
    Observable<ClaimRewardResult> newBieClaimReward(@Query("taskid") int i);

    @GET("ajax.php?mod=newcomertask")
    Observable<ClaimRewardResult> newBieFinish(@Query("finish") int i);

    @POST
    Observable<BaseResult> post(@Url String str);

    @POST("app.php?mod=newreply")
    @Multipart
    Observable<BaseResult> postComments(@Part List<MultipartBody.Part> list);

    @FormUrlEncoded
    @POST("app.php?mod=favorite")
    Observable<JsonObject> postFavorite(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("app.php?mod=feedback")
    Observable<BaseResult> postFeedbackContent(@FieldMap Map<String, String> map);

    @GET("app.php?mod=adthreadlist&new=1")
    Observable<JsonObject> postHomeMore(@QueryMap Map<String, String> map);

    @POST("ajax.php?mod=ajaxmessage")
    @Multipart
    Observable<PostShortDetailBackItem> postShortContent(@Part List<MultipartBody.Part> list);

    @GET("app.php?mod=register")
    Observable<JsonObject> registerPush(@QueryMap Map<String, String> map);

    @POST("misc.php?mod=report&rtype=shortmessage&reportsubmit=1&inajax=1")
    Observable<BaseResult> reportThought(@Query("rid") String str, @Query("message") String str2);

    @FormUrlEncoded
    @POST("ajax.php?mod=search")
    Observable<JsonObject> search(@FieldMap Map<String, String> map);

    @GET("ajax.php?mod=pushmsgflag")
    Observable<BaseResult> setPushMsgFlag(@Query("pushmsgflag") int i);

    @GET("ajax.php?mod=ajaxsignopenalarm")
    Observable<JsonObject> setSignAlert(@Query("openalarm") int i);

    @POST("ajax.php?mod=ajaxdynamicreply")
    @Multipart
    Observable<BaseResult> shortContentReply(@Part List<MultipartBody.Part> list);

    @GET("ajax.php?mod=ajaxdynamicthumbup")
    Observable<BaseResult> shortContentThumbUp(@Query("id") String str, @Query("type") int i, @Query("typeid") int i2);

    @GET("ajax.php?mod=ajaxemotion")
    Observable<JsonObject> signIn(@Query("emotion") int i);

    @FormUrlEncoded
    @POST("ajax.php?mod=ajaxsign")
    Observable<SignPostResult> signPost(@Field("emotion") int i, @Field("sharedynamic") int i2, @Field("message") String str);

    @POST("ajax.php?mod=ajaxdynamicreply")
    @Multipart
    Observable<BaseResult> signReply(@Part List<MultipartBody.Part> list);

    @GET("ajax.php?mod=ajaxdynamicthumbup")
    Observable<BaseResult> signThumbUp(@Query("id") String str, @Query("type") int i);

    @GET("app.php?mod=misc&action=thumbup&type=1")
    Observable<BaseResult> thumbUp(@QueryMap Map<String, String> map);

    @GET("app.php?mod=misc&action=thumbup&type=2")
    Observable<BaseResult> thumbUpComment(@QueryMap Map<String, String> map);

    @POST("app.php?mod=upload")
    @Multipart
    Observable<FeedbackUploadResult> uploadFeedbackImage(@Part("bbs_image\"; filename=\"avatar.png") RequestBody requestBody);

    @POST("app.php?mod=uploadimg")
    @Multipart
    Observable<UploadResultModel> uploadImage(@Part("bbs_image\"; filename=\"avatar.png") RequestBody requestBody);

    @FormUrlEncoded
    @POST("forum.php?mod=misc&action=votepoll&pollsubmit=yes&quickforward=yes&isajax=1&mobile=2")
    Observable<BaseResult> votePoll(@Field("pollanswers[]") String[] strArr, @FieldMap Map<String, String> map);
}
