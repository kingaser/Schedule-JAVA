package com.sparta.schedule.service;

import com.sparta.schedule.dto.response.MessageResponseDto;
import com.sparta.schedule.dto.request.UserRequestDto;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.entity.UserRoleEnum;
import com.sparta.schedule.exception.ApiException;
import com.sparta.schedule.exception.ErrorCode;
import com.sparta.schedule.jwt.JwtUtil;
import com.sparta.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<MessageResponseDto> signup(UserRequestDto userRequestDto) {
        String email = userRequestDto.getEmail();
        String password = passwordEncoder.encode(userRequestDto.getPassword());
        String username = userRequestDto.getUsername();

//        회원 중복 확인
        Optional<User> found = userRepository.findByEmail(email);
        if(found.isPresent()){
            throw new IllegalArgumentException("중복 회원입니다.");
        }

        userRepository.save(User.user_service(username,password,email, UserRoleEnum.USER));
        return  ResponseEntity.ok(MessageResponseDto.User_ServiceCode(HttpStatus.OK,"회원가입 성공"));

    }


    @Transactional
    public MessageResponseDto login(UserRequestDto userRequestDto) {
        String email = userRequestDto.getEmail();
        String password = userRequestDto.getPassword();

//        사용자 확인 및 비밀번호 확인
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())){
            throw new ApiException(ErrorCode.NOT_MATCHING_INFO);
    }

//        header에 들어갈 JWT 세팅 body에다가 넣는거
       String jwtUtil2 = JwtUtil.AUTHORIZATION_HEADER+" "+ jwtUtil.createToken(user.get().getEmail(), user.get().getRole());

        return MessageResponseDto.builder()
                .code(HttpStatus.OK.value())
                .msg("성공")
                .jwtUtil(jwtUtil2)
                .build();
    }
}
