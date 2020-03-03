package com.xiaomi.shopviews.model;

import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;

public class HomeAction implements Serializable {
    private static final long serialVersionUID = -1109556628901905801L;
    public String mExtra;
    public boolean mIsNeedLogin;
    public String mLogCode;
    public String mPath;
    public String mType;

    public boolean equals(Object obj) {
        if (this != obj) {
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
            if (this.mLogCode == null ? homeAction.mLogCode != null : !this.mLogCode.equals(homeAction.mLogCode)) {
                return false;
            }
            if (!this.mType.equals(homeAction.mType)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.mType.hashCode() * 31) + (this.mPath != null ? this.mPath.hashCode() : 0)) * 31;
        if (this.mLogCode != null) {
            i = this.mLogCode.hashCode();
        }
        return ((hashCode + i) * 31) + (this.mIsNeedLogin ? 1 : 0);
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(this.mType) || TextUtils.isEmpty(this.mPath);
    }

    public boolean isNeedLogin() {
        return this.mIsNeedLogin;
    }

    public String toString() {
        return "HomeAction{mType='" + this.mType + Operators.SINGLE_QUOTE + ", mPath='" + this.mPath + Operators.SINGLE_QUOTE + ", mStatDesc='" + this.mLogCode + Operators.SINGLE_QUOTE + ", mIsNeedLogin='" + this.mIsNeedLogin + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
