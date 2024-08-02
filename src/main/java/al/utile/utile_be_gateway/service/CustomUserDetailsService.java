package al.utile.utile_be_gateway.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {



    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails = null; // @TODO call utile.getUserByUsername

        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return userDetails;
    }
}
