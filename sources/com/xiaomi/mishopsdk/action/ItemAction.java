package com.xiaomi.mishopsdk.action;

import android.text.TextUtils;
import com.alibaba.fastjson.annotation.JSONField;
import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;

@Deprecated
public class ItemAction implements Serializable {
    private static final long serialVersionUID = -1109556628901905801L;
    @JSONField(name = "extra")
    public String mExtra;
    @JSONField(name = "login")
    public boolean mIsNeedLogin;
    @JSONField(name = "log_code")
    public String mLogCode;
    @JSONField(name = "path")
    public String mPath;
    @JSONField(name = "type")
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
        ItemAction itemAction = (ItemAction) obj;
        if (this.mIsNeedLogin != itemAction.mIsNeedLogin) {
            return false;
        }
        if (this.mPath == null ? itemAction.mPath != null : !this.mPath.equals(itemAction.mPath)) {
            return false;
        }
        if (this.mLogCode == null ? itemAction.mLogCode == null : this.mLogCode.equals(itemAction.mLogCode)) {
            return this.mType.equals(itemAction.mType);
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.mType.hashCode() * 31) + (this.mPath != null ? this.mPath.hashCode() : 0)) * 31;
        if (this.mLogCode != null) {
            i = this.mLogCode.hashCode();
        }
        return ((hashCode + i) * 31) + (this.mIsNeedLogin ? 1 : 0);
    }

    public String toString() {
        return "HomeAction{mType='" + this.mType + Operators.SINGLE_QUOTE + ", mPath='" + this.mPath + Operators.SINGLE_QUOTE + ", mStatDesc='" + this.mLogCode + Operators.SINGLE_QUOTE + ", mIsNeedLogin='" + this.mIsNeedLogin + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
