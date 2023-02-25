package com.sparta.schedule.service;

import com.sparta.schedule.dto.MegResponseDto;
import com.sparta.schedule.dto.UserRequestDto;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.entity.UserRoleEnum;
import com.sparta.schedule.exception.ApiException;
import com.sparta.schedule.exception.ErrorCode;
import com.sparta.schedule.jwt.JwtUtil;
import com.sparta.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

//          회원가입
    @Transactional
    public ResponseEntity<MegResponseDto> signup(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = passwordEncoder.encode(userRequestDto.getPassword());
        String email = userRequestDto.getEmail();

//        회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if(found.isPresent()){
            throw new IllegalArgumentException("중복 회원입니다.");
        }

        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            throw new IllegalArgumentException("중복된 이메일입니다.");
        }
        userRepository.save(User.user_service(username,password,email, UserRoleEnum.USER));
        
        return  ResponseEntity.ok(MegResponseDto.User_ServiceCode(HttpStatus.OK,"회원가입 성공"));

    }
    @Transactional
    public ResponseEntity<MegResponseDto>login(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();

//        사용자 확인 및 비밀번호 확인
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())){
            throw new ApiException(ErrorCode.NOT_MATCHING_INFO);
    }

//        header에 들어갈 JWT 세팅
        HttpHeaders headers = new HttpHeaders();
        headers.set(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.get().getUsername(), user.get().getRole()));

        return ResponseEntity.ok()
                .headers(headers)
                .body(MegResponseDto.User_ServiceCode(HttpStatus.OK, "로그인 되었습니다."));
    }
}
