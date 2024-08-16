package kadriozcan.marketplaceapp.security;

import kadriozcan.marketplaceapp.exception.UserNotFoundException;
import kadriozcan.marketplaceapp.model.User;
import kadriozcan.marketplaceapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {
    private final UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userService.getByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return new UserAuthDetails(user);
    }
}
