package com.yungyu.oauthserver.service.impl;

import com.yungyu.oauthserver.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = new MyUser();
        myUser.setUserName(username);
        myUser.setPassword(passwordEncoder.encode("123456"));
        return new User(
                username,
                myUser.getPassword(),
                myUser.isEnabled(),
                myUser.isAccountNonExpired(),
                myUser.isCredentialsNonExpired(),
                myUser.isAccountNonLocked(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin")
        );
    }

}
