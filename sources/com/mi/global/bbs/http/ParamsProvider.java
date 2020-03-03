package com.mi.global.bbs.http;

import android.text.TextUtils;
import com.mi.global.bbs.http.ParamKey;
import com.mi.global.bbs.model.UserInfoModel;
import com.mi.global.bbs.model.UserInfoPermission;
import java.util.HashMap;
import java.util.Map;

public class ParamsProvider {
    public static Map<String, String> getNullParams() {
        return new HashMap();
    }

    public static Map<String, String> getParamsBy(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null) {
            return null;
        }
        Map<String, String> nullParams = getNullParams();
        for (int i = 0; i < strArr.length; i++) {
            if (!TextUtils.isEmpty(strArr2[i])) {
                nullParams.put(strArr[i], strArr2[i]);
            }
        }
        return nullParams;
    }

    public static Map<String, String> getNewThreadParams(String str, String str2, String str3, String str4) {
        return getParamsBy(new String[]{"subject", "message", ParamKey.typeid, "fid", "special"}, new String[]{str, str2, str3, str4, String.valueOf(0)});
    }

    public static Map<String, String> getQuestionTreadParams(String str, String str2, String str3, String str4, int i, String str5) {
        return getParamsBy(new String[]{"subject", "message", ParamKey.typeid, "fid", "special", ParamKey.isqa, ParamKey.ids}, new String[]{str, str2, str3, str4, String.valueOf(0), String.valueOf(i), str5});
    }

    public static Map<String, String> getFeedbackParams(String str, String str2, String str3, String str4, String str5) {
        return getParamsBy(new String[]{"contact", "content", ParamKey.appversion, ParamKey.mobileversion, ParamKey.attachids}, new String[]{str, str2, str3, str4, str5});
    }

    public static Map<String, String> getSearchParams(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        return getParamsBy(new String[]{ParamKey.wd, "type", "time", "forum", ParamKey.orderby, "start", "rows", ParamKey.fuzz}, new String[]{str, str2, str3, str4, str5, str6, str7, str8});
    }

    public static Map<String, String> getSearchQAParams(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        return getParamsBy(new String[]{ParamKey.isqa, ParamKey.wd, "type", "time", "forum", ParamKey.orderby, "start", "rows", ParamKey.fuzz}, new String[]{str, str2, str3, str4, str5, str6, str7, str8, str9});
    }

    public static Map<String, String> getPostParams(int i, int i2) {
        return getParamsBy(new String[]{"page", ParamKey.perpage}, new String[]{String.valueOf(i), String.valueOf(i2)});
    }

    public static Map<String, String> getIncityActivityParams(int i, int i2, int i3) {
        return getParamsBy(new String[]{"page", ParamKey.perpage, "type"}, new String[]{String.valueOf(i), String.valueOf(i2), String.valueOf(i3)});
    }

    public static Map<String, String> getFollowUserInfo(int i, int i2, int i3) {
        return getParamsBy(new String[]{"page", ParamKey.perpage, ParamKey.find}, new String[]{String.valueOf(i), String.valueOf(i2), String.valueOf(i3)});
    }

    public static Map<String, String> getUserInfo(UserInfoModel.DataBean dataBean, UserInfoPermission userInfoPermission) {
        UserInfoModel.DataBean dataBean2 = dataBean;
        UserInfoPermission userInfoPermission2 = userInfoPermission;
        return getParamsUserBy(new String[]{"icon", ParamKey.realname, "gender", ParamKey.livingcity, ParamKey.birthmonth, ParamKey.birthday, ParamKey.education, ParamKey.occupation, ParamKey.interest, "username", "email", ParamKey.realname_permission, ParamKey.gender_permission, ParamKey.livingcity_permission, ParamKey.birthday_permission, ParamKey.education_permission, ParamKey.occupation_permission, ParamKey.interest_permission}, new String[]{dataBean2.icon, dataBean2.realname, dataBean2.gender, dataBean2.livingcity, dataBean2.birthmonth, dataBean2.birthday, dataBean2.education, dataBean2.occupation, dataBean2.interest, dataBean2.username, dataBean2.email, String.valueOf(userInfoPermission2.realname), String.valueOf(userInfoPermission2.gender), String.valueOf(userInfoPermission2.livingcity), String.valueOf(userInfoPermission2.birthday), String.valueOf(userInfoPermission2.education), String.valueOf(userInfoPermission2.occupation), String.valueOf(userInfoPermission2.interest)});
    }

    public static Map<String, String> getParamsUserBy(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null) {
            return null;
        }
        Map<String, String> nullParams = getNullParams();
        for (int i = 0; i < strArr.length; i++) {
            if (strArr2[i] != null) {
                nullParams.put(strArr[i], strArr2[i]);
            }
        }
        return nullParams;
    }

    public static Map<String, String> getSubforumItem(String str, int i, String str2, String str3) {
        return getParamsBy(new String[]{"fid", "page", ParamKey.outputajax, ParamKey.typeid, "filter"}, new String[]{str, String.valueOf(i), String.valueOf(1), str2, str3});
    }

    public static Map<String, String> getForumComments(String str, int i, int i2) {
        return getParamsBy(new String[]{"tid", "page", ParamKey.outputajax, ParamKey.postorder}, new String[]{str, String.valueOf(i), String.valueOf(1), String.valueOf(i2)});
    }

    public static Map<String, String> getPostComments(String str, String str2, String str3, String str4) {
        return getParamsBy(new String[]{"fid", "tid", "message", ParamKey.reppid}, new String[]{str, str2, str3, str4});
    }

    public static Map<String, String> getSyncParams(String str, String str2, String str3) {
        return getParamsBy(new String[]{"version", "channel", "md5"}, new String[]{str, str2, str3});
    }

    public static Map<String, String> getPushParams(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        return getParamsBy(new String[]{ParamKey.PushMess.deviceMd5, ParamKey.PushMess.regId, ParamKey.PushMess.model, ParamKey.PushMess.system, ParamKey.PushMess.systemVersion, ParamKey.PushMess.sdkLevel, ParamKey.PushMess.manufacturer, ParamKey.PushMess.resolution, ParamKey.PushMess.server}, new String[]{str, str2, str3, str4, str5, str6, str7, str8, str9});
    }

    public static Map<String, String> getLoginCallBackParams(String str, String str2) {
        return getParamsBy(new String[]{"userId", "security"}, new String[]{str, str2});
    }

    public static Map<String, String> getHomeMoreParams(String str, String str2, String str3) {
        return getParamsBy(new String[]{"page", ParamKey.perpage, "max_id"}, new String[]{str, str2, str3});
    }

    public static Map<String, String> getFavoriteParams(String str, String str2) {
        return getParamsBy(new String[]{"type", "id"}, new String[]{str, str2});
    }

    public static Map<String, String> getLiteHomeParams(int i, int i2, String str) {
        return getParamsBy(new String[]{"page", ParamKey.perpage, ParamKey.orderby, ParamKey.ascdesc, "filter"}, new String[]{String.valueOf(i), String.valueOf(i2), str, "desc", str});
    }

    public static Map<String, String> geThumbUpParams(String str, String str2) {
        return getParamsBy(new String[]{"zid", "uid"}, new String[]{str, str2});
    }

    public static Map<String, String> getForyouParams(int i, int i2, int i3) {
        return getParamsBy(new String[]{"page", "num", ParamKey.mod}, new String[]{String.valueOf(i), String.valueOf(i2), String.valueOf(i3)});
    }

    public static Map<String, String> getColumnParams(int i, int i2) {
        return getParamsBy(new String[]{"page", "num"}, new String[]{String.valueOf(i), String.valueOf(i2)});
    }

    public static Map<String, String> getArticlesParams(int i, int i2, String str) {
        return getParamsBy(new String[]{"page", "num", ParamKey.columnID}, new String[]{String.valueOf(i), String.valueOf(i2), str});
    }

    public static Map<String, String> getColumnDetailParams(String str) {
        return getParamsBy(new String[]{ParamKey.columnID}, new String[]{str});
    }

    public static Map<String, String> getColumnSubParams(int i, int i2) {
        return getParamsBy(new String[]{"page", "num"}, new String[]{String.valueOf(i), String.valueOf(i2)});
    }

    public static Map<String, String> getColumnListParams(int i, int i2) {
        return getParamsBy(new String[]{"page", "num"}, new String[]{String.valueOf(i), String.valueOf(i2)});
    }

    public static Map<String, String> getFollowColumnParams(String str, int i) {
        return getParamsBy(new String[]{"id", "type"}, new String[]{str, String.valueOf(i)});
    }

    public static Map<String, String> getChangeColumnPushParams(String str, int i) {
        return getParamsBy(new String[]{"id", "type"}, new String[]{str, String.valueOf(i)});
    }

    public static Map<String, String> getChangeColumnBgParams(String str, String str2) {
        return getParamsBy(new String[]{"id", ParamKey.bgimg}, new String[]{str, str2});
    }

    public static Map<String, String> getFollowForumParams(String str, String str2) {
        return getParamsBy(new String[]{"id", "from"}, new String[]{str, str2});
    }

    public static Map<String, String> getPostSignComments(String str, String str2, String str3) {
        return getParamsBy(new String[]{"id", ParamKey.reply_id, "message", ParamKey.typeid}, new String[]{str, str2, str3, "1"});
    }

    public static Map<String, String> getNewThreadContainPollParams(String str, String str2, String str3, String str4) {
        return getParamsBy(new String[]{"subject", "message", ParamKey.typeid, "fid", "special", ParamKey.maxchoices}, new String[]{str, str2, str3, str4, String.valueOf(1), String.valueOf(1)});
    }

    public static Map<String, String> getVoteParams(String str, String str2) {
        return getParamsBy(new String[]{"fid", "tid"}, new String[]{str, str2});
    }
}
