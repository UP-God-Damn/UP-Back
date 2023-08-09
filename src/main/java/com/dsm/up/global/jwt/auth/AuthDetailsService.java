package com.dsm.up.global.jwt.auth;
import com.dsm.up.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) {
        return userRepository.findByUserId(userId)
                .map(AuthDetails::new)
                .orElseThrow(() -> new RuntimeException("USERNOTFOUND"));
    }
}

