package com.davtyan.sequrity.sequrityapi.sequrity.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;


@Getter
public class JwtUser implements UserDetails {

    private final Long id;
    private final String userName;
    private final String firsName;
    private final String lastName;
    private final String password;
    private final String email;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Date lastPasswordResetData;

    public JwtUser(Long id, String userName, String firsName, String lastName, String password, String email, boolean enabled, Collection<? extends GrantedAuthority> authorities, Date lastPasswordResetData) {
        this.id = id;
        this.userName = userName;
        this.firsName = firsName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.authorities = authorities;
        this.lastPasswordResetData = lastPasswordResetData;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
