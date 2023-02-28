package com.sparta.schedule.security;

import com.sparta.schedule.entity.User;
import com.sparta.schedule.exception.ErrorCode;
import com.sparta.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service    // custom 하고 Bean 으로 등록 후 사용 가능
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorCode.NOT_FOUND_USER.getMessage()));   // 사용자가 DB 에 없으면 예외처리

        return new UserDetailsImpl(user, user.getEmail());   // 사용자 정보를 UserDetails 로 반환
    }
}
