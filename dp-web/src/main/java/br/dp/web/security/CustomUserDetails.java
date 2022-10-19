package br.dp.web.security;

import br.dp.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {

    public CustomUserDetails(final String username, final String password, final Collection<? extends GrantedAuthority> authorities, final Usuario user) {

        super(username, password, true, true, true, true, authorities);

        this.user = user;

    }

    private final Usuario user;

    public Usuario getUser() {
        return user;
    }
}
