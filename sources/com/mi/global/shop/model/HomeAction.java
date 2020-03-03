package com.mi.global.shop.model;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;

public class HomeAction implements Serializable {
    private static final long serialVersionUID = -1109556628901905801L;
    @SerializedName("login")
    public boolean mIsNeedLogin;
    @SerializedName("path")
    public String mPath;
    @SerializedName("stat")
    public String mStatDesc;
    @SerializedName("type")
    public String mType;

    public boolean isNeedLogin() {
        return this.mIsNeedLogin;
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(this.mType) || TextUtils.isEmpty(this.mPath);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HomeAction homeAction = (HomeAction) obj;
        if (this.mIsNeedLogin != homeAction.mIsNeedLogin) {
            return false;
        }
        if (this.mPath == null ? homeAction.mPath != null : !this.mPath.equals(homeAction.mPath)) {
            return false;
        }
        if (this.mStatDesc == null ? homeAction.mStatDesc == null : this.mStatDesc.equals(homeAction.mStatDesc)) {
            return this.mType.equals(homeAction.mType);
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.mType.hashCode() * 31) + (this.mPath != null ? this.mPath.hashCode() : 0)) * 31;
        if (this.mStatDesc != null) {
            i = this.mStatDesc.hashCode();
        }
        return ((hashCode + i) * 31) + (this.mIsNeedLogin ? 1 : 0);
    }

    public String toString() {
        return "HomeAction{mType='" + this.mType + Operators.SINGLE_QUOTE + ", mPath='" + this.mPath + Operators.SINGLE_QUOTE + ", mStatDesc='" + this.mStatDesc + Operators.SINGLE_QUOTE + ", mIsNeedLogin='" + this.mIsNeedLogin + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
