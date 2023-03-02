package com.sparta.schedule.service;

import com.sparta.schedule.dto.response.MessageResponseDto;
import com.sparta.schedule.dto.request.UserRequestDto;
import com.sparta.schedule.dto.response.UserResponseDto;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.entity.UserRoleEnum;
import com.sparta.schedule.exception.ApiException;
import com.sparta.schedule.exception.ErrorCode;
import com.sparta.schedule.jwt.JwtUtil;
import com.sparta.schedule.repository.UserRepository;
import com.sparta.schedule.security.UserDetailsImpl;
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
    public ResponseEntity<MessageResponseDto> signup(UserRequestDto userRequestDto) {
        String email = userRequestDto.getEmail();
        String password = passwordEncoder.encode(userRequestDto.getPassword());
        String username = userRequestDto.getUsername();

//        회원 중복 확인
        Optional<User> found = userRepository.findByEmail(email);
        if(found.isPresent()){
            throw new ApiException(ErrorCode.DUPLICATED_USERNAME);
        }

        userRepository.save(User.user_service(username,password,email, UserRoleEnum.USER));
        return  ResponseEntity.ok(MessageResponseDto.User_ServiceCode(HttpStatus.OK, "회원가입 성공"));

    }

//          중복 회원 검사 (버튼)
    @Transactional
    public ResponseEntity<MessageResponseDto>idCheck(UserRequestDto userRequestDto){
        String email = userRequestDto.getEmail();
        Optional<User> users = userRepository.findByEmail(email);
        
//              중복 회원 일 경우
        if(users.isPresent()){
            throw new IllegalArgumentException("중복 회원입니다.");
        }
//              중복 회원이 아닐 경우 아이디 사용 가능
        return ResponseEntity.ok(MessageResponseDto.User_ServiceCode(HttpStatus.OK,"아이디 사용가능합니다."));
    }

    public ResponseEntity<UserResponseDto> getLogin(UserDetailsImpl userDetails) {

        Optional<User> user = userRepository.findByEmail(userDetails.getUser().getEmail());

        return ResponseEntity.ok()
                .body(UserResponseDto.User_ServiceCode(HttpStatus.OK, "", user.get().getUsername()));
    }

    @Transactional
    public ResponseEntity<UserResponseDto> login(UserRequestDto userRequestDto) {
        String email = userRequestDto.getEmail();
        String password = userRequestDto.getPassword();

//        사용자 확인 및 비밀번호 확인
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())){
            throw new ApiException(ErrorCode.NOT_MATCHING_INFO);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.get().getEmail(), user.get().getRole()));

        return ResponseEntity.ok()
                .headers(headers)
                .body(UserResponseDto.User_ServiceCode(HttpStatus.OK, "로그인 성공", user.get().getUsername()));

//        header에 들어갈 JWT 세팅 body에다가 넣는거
//       String jwtUtil2 = JwtUtil.AUTHORIZATION_HEADER+" "+ jwtUtil.createToken(user.get().getEmail(), user.get().getRole());
//
//        return MessageResponseDto.builder()
//                .code(HttpStatus.OK.value())
//                .msg("성공")
//                .jwtUtil(jwtUtil2)
//                .build();
    }


}
