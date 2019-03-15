package dihtiar.sasha.security;

import dihtiar.sasha.model.Role;
import dihtiar.sasha.model.Users;
import dihtiar.sasha.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    @Autowired
    UsersService usersService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        Users user = usersService.findUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        String password = authentication.getCredentials().toString();
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("AHAHHA");
        }
        List<GrantedAuthority> list = new ArrayList<>();
        if (user.getRole().getId() == 1L) {
            list.add(new SimpleGrantedAuthority("ADMIN"));
        } else {
            list.add(new SimpleGrantedAuthority("USER"));
        }
        return new UsernamePasswordAuthenticationToken(user, null, list);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
