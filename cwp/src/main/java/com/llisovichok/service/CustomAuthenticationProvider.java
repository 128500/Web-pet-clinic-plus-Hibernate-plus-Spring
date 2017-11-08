package com.llisovichok.service;

import com.llisovichok.models.User;
import com.llisovichok.storages.Storages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KUDIN ALEKSANDR on 08.11.2017.
 */

@Service ("provider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private Storages storages;

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = storages.shHiberStorage.getByAuth(login, password);
        if(user != null){
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
            return new UsernamePasswordAuthenticationToken(login, password, grantedAuthorities);
        }
        else return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
