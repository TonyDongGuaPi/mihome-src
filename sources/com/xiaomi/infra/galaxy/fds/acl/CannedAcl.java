package com.xiaomi.infra.galaxy.fds.acl;

import com.xiaomi.infra.galaxy.fds.model.AccessControlList;
import java.util.ArrayList;
import java.util.List;

public enum CannedAcl {
    PUBLIC_READ(AccessControlList.UserGroups.ALL_USERS, AccessControlList.Permission.READ),
    PUBLIC_WRITE(AccessControlList.UserGroups.ALL_USERS, AccessControlList.Permission.WRITE),
    PUBLIC_READ_OBJECTS(AccessControlList.UserGroups.ALL_USERS, AccessControlList.Permission.READ_OBJECTS),
    PUBLIC_SSO_WRITE(AccessControlList.UserGroups.ALL_USERS, AccessControlList.Permission.SSO_WRITE),
    AUTHENTICATED_READ(AccessControlList.UserGroups.AUTHENTICATED_USERS, AccessControlList.Permission.READ),
    AUTHENTICATED_WRITE(AccessControlList.UserGroups.AUTHENTICATED_USERS, AccessControlList.Permission.WRITE),
    AUTHENTICATED_READ_OBJECTS(AccessControlList.UserGroups.AUTHENTICATED_USERS, AccessControlList.Permission.READ_OBJECTS),
    AUTHENTICATED_SSO_WRITE(AccessControlList.UserGroups.AUTHENTICATED_USERS, AccessControlList.Permission.SSO_WRITE);
    
    private final AccessControlList.UserGroups group;
    private final AccessControlList.Permission permission;

    private CannedAcl(AccessControlList.UserGroups userGroups, AccessControlList.Permission permission2) {
        this.group = userGroups;
        this.permission = permission2;
    }

    public AccessControlList.Grant getGrant() {
        return new AccessControlList.Grant(this.group.name(), this.permission, AccessControlList.GrantType.GROUP);
    }

    public static List<AccessControlList.Grant> parseFromString(String str) {
        String[] split = str.split(",");
        ArrayList arrayList = new ArrayList(split.length);
        for (String trim : split) {
            arrayList.add(valueOf(trim.trim()).getGrant());
        }
        return arrayList;
    }
}
