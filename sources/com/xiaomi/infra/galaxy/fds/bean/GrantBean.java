package com.xiaomi.infra.galaxy.fds.bean;

import com.xiaomi.infra.galaxy.fds.model.AccessControlList;

public class GrantBean {

    /* renamed from: a  reason: collision with root package name */
    private GranteeBean f10157a;
    private AccessControlList.Permission b;
    private AccessControlList.GrantType c;

    public GrantBean() {
    }

    public GrantBean(GranteeBean granteeBean, AccessControlList.Permission permission) {
        this(granteeBean, permission, AccessControlList.GrantType.USER);
    }

    public GrantBean(GranteeBean granteeBean, AccessControlList.Permission permission, AccessControlList.GrantType grantType) {
        this.f10157a = granteeBean;
        this.b = permission;
        this.c = grantType;
    }

    public GranteeBean a() {
        return this.f10157a;
    }

    public void a(GranteeBean granteeBean) {
        this.f10157a = granteeBean;
    }

    public AccessControlList.Permission b() {
        return this.b;
    }

    public void a(AccessControlList.Permission permission) {
        this.b = permission;
    }

    public AccessControlList.GrantType c() {
        return this.c;
    }

    public void a(AccessControlList.GrantType grantType) {
        this.c = grantType;
    }
}
