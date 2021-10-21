package com.pierre.oceanblu.model;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class GoogleUserInfo {

    private final Map<String, Object> attributes;

    public String getId() {
        return (String) attributes.get("sub");
    }

    public String getGivenName() {
        return (String) attributes.get("given_name");
    }

    public String getFamilyName() {
        return (String) attributes.get("family_name");
    }

    public User.Role getAuthority() {
        return (User.Role) attributes.get("authority");
    }
}
