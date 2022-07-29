package com.example.cardgame3.security;

import com.google.common.collect.Sets;

import java.util.Set;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(ApplicationUserPermission.PLAYER_GETPLAYERS, ApplicationUserPermission.PLAYER_SAVECARD)),
    PLAYER(Sets.newHashSet());


    private final Set<ApplicationUserPermission> permissions;


    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
