package br.gov.sp.cptm.bykerack.data.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    USER("ROLE_USER"),
    EMPLOYEE("ROLE_EMPLOYEE"),
    QR_READER("QR_READER_ROLE");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return this.roleName;
    }
}
