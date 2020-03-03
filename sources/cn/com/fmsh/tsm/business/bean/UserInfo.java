package cn.com.fmsh.tsm.business.bean;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1;
    String certNo = null;
    int certType = -1;
    String mail = null;
    String password = null;
    String phone = null;
    String realName = null;
    String userName = null;
    int userType = -1;
    String verificationCode = null;
    byte[] verificationCodeNo = null;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public int getUserType() {
        return this.userType;
    }

    public void setUserType(int i) {
        this.userType = i;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String str) {
        this.mail = str;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public String getRealName() {
        return this.realName;
    }

    public void setRealName(String str) {
        this.realName = str;
    }

    public int getCertType() {
        return this.certType;
    }

    public void setCertType(int i) {
        this.certType = i;
    }

    public String getCertNo() {
        return this.certNo;
    }

    public void setCertNo(String str) {
        this.certNo = str;
    }

    public byte[] getVerificationCodeNo() {
        return this.verificationCodeNo;
    }

    public void setVerificationCodeNo(byte[] bArr) {
        this.verificationCodeNo = bArr;
    }

    public String getVerificationCode() {
        return this.verificationCode;
    }

    public void setVerificationCode(String str) {
        this.verificationCode = str;
    }
}
