package kadriozcan.marketplaceapp.security;

import kadriozcan.marketplaceapp.model.User;
import kadriozcan.marketplaceapp.utils.CollectionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@RequiredArgsConstructor
public class UserAuthDetails implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (Objects.nonNull(user) && CollectionUtils.isNotEmpty(user.getRoles())) {
            return user.getRoles().stream().map((t) -> new SimpleGrantedAuthority(t.getName())).toList();
        }
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }

    public User getUser(){
        return user;
    }
}
