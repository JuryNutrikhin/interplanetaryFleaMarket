package com.example.interplanetaryfleamarket.rdb.security;

import com.example.interplanetaryfleamarket.entity.User;
import com.example.interplanetaryfleamarket.rdb.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Getter
@Setter
public class RdbUserDetails implements UserDetails {

    private User user;//выгруженый из бд пользователь



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections
                .<GrantedAuthority>singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
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
