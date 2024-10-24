package com.bvm.mci.security.config;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class UserDetailsImpl implements UserDetails {



    private static final long serialVersionUID = -326075573976141582L;

    private String username;
    private String password;
    private String userkey;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl() {

    }

    public UserDetailsImpl(String userkey, String password,  Collection<? extends GrantedAuthority> authorities, String username) {
        this.userkey = userkey;
        this.password = password;
        this.authorities = authorities;
        this.username= username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
