package team404.project.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, USER, SUPPORT;

    @Override
    public String getAuthority() {
        return name();
    }
}
