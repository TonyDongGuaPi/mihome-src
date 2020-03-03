package com.mi.global.bbs.http;

public interface ParamKey {
    public static final String appversion = "appversion";
    public static final String ascdesc = "ascdesc";
    public static final String attachids = "attachids";
    public static final String bgimg = "bgimg";
    public static final String birthday = "birthday";
    public static final String birthday_permission = "privacy[birthday]";
    public static final String birthmonth = "birthmonth";
    public static final String birthyear = "birthyear";
    public static final String bkgimg = "bkgimg";
    public static final String columnID = "columnID";
    public static final String contact = "contact";
    public static final String content = "content";
    public static final String education = "education";
    public static final String education_permission = "privacy[education]";
    public static final String email = "email";
    public static final String fid = "fid";
    public static final String filter = "filter";
    public static final String find = "find";
    public static final String forum = "forum";
    public static final String from = "from";
    public static final String fuzz = "fuzz";
    public static final String gender = "gender";
    public static final String gender_permission = "privacy[gender]";
    public static final String icon = "icon";
    public static final String id = "id";
    public static final String ids = "notify_user";
    public static final String images = "images";
    public static final String interest = "interest";
    public static final String interest_permission = "privacy[interest]";
    public static final String isqa = "isqa";
    public static final String livingcity = "livingcity";
    public static final String livingcity_permission = "privacy[livingcity]";
    public static final String maxchoices = "maxchoices";
    public static final String message = "message";
    public static final String mobileversion = "mobileversion";
    public static final String mod = "mod";
    public static final String occupation = "occupation";
    public static final String occupation_permission = "privacy[occupation]";
    public static final String orderby = "orderby";
    public static final String outputajax = "outputajax";
    public static final String page = "page";
    public static final String pagenum = "num";
    public static final String perpage = "perpage";
    public static final String pollanswers = "pollanswers[]";
    public static final String postorder = "postorder";
    public static final String realname = "realname";
    public static final String realname_permission = "privacy[realname]";
    public static final String reply_id = "reply_id";
    public static final String reppid = "reppid";
    public static final String rows = "rows";
    public static final String special = "special";
    public static final String start = "start";
    public static final String subject = "subject";
    public static final String tid = "tid";
    public static final String time = "time";
    public static final String type = "type";
    public static final String typeid = "typeid";
    public static final String uid = "uid";
    public static final String username = "username";
    public static final String wd = "wd";

    public static class Favorite {
        static final String TID = "id";
        static final String TYPE = "type";
    }

    public static class HomeMoreData {
        static final String MAXID = "max_id";
        static final String PAGE = "page";
        static final String PERPAGE = "perpage";
    }

    public static class LoginCallBack {
        static final String POST_SECURITY = "security";
        static final String POST_XMUUID = "userId";
    }

    public static class PushMess {
        static String deviceMd5 = "deviceMd5";
        static String manufacturer = "manufacturer";
        static String model = "model";
        static String regId = "regId";
        static String resolution = "resolution";
        static String sdkLevel = "sdkLevel";
        static String server = "server";
        static String system = "system";
        static String systemVersion = "systemVersion";
    }

    public static class SyncInfo {
        static final String CHANNEL = "channel";
        static final String MD5 = "md5";
        static final String VERSION = "version";
    }

    public static class ThumbUp {
        static final String tid = "zid";
        static final String uid = "uid";
    }
}
