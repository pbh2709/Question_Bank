package com.example.question_bank.config;

import com.example.question_bank.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;


public class LoginUserDetails implements UserDetails {
    private final String username;
    private final String password;

    private final Collection <? extends GrantedAuthority> authorities;

    public LoginUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        //String[] roles = user.getRoles().split(",");

        this.authorities = Arrays.stream(user.getRoles().split(","))
                .map(role -> new SimpleGrantedAuthority(role))
                .toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ロールのコレクションを返す
        return authorities;
    }

    @Override
    public String getPassword() {
        // パスワードを返す
        return password;
    }

    @Override
    public String getUsername() {
        // ログイン名を返す
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        //  ユーザーが期限切れでなければtrueを返す
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //  ユーザーがロックされていなければtrueを返す
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //  パスワードが期限切れでなければtrueを返す
        return true;
    }

    @Override
    public boolean isEnabled() {
        //  ユーザーが有効ならtrueを返す
        return true;
    }


}