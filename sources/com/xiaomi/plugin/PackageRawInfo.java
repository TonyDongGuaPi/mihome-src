package com.xiaomi.plugin;

import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.List;

public class PackageRawInfo {
    public long mDeveloperId;
    public String mMessageHandleName;
    public int mMinApiLevel;
    public int mPackageId;
    public String mPackageName;
    public String mPlatform;
    public List<String> mUrlList = new ArrayList();
    public int mVersion;
    public String mVersionName;

    public String toString() {
        return "PackageRawInfo[" + "version:" + this.mVersion + " " + "developerId:" + this.mDeveloperId + " " + "minApiLevel:" + this.mMinApiLevel + " " + "platform:" + this.mPlatform + " " + "packageName:" + this.mPackageName + " " + "mMessageHandleName:" + this.mMessageHandleName + " " + Operators.ARRAY_END_STR;
    }
}
