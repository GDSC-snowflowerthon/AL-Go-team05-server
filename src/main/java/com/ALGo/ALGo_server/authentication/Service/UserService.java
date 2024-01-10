package com.ALGo.ALGo_server.authentication.Service;

import com.ALGo.ALGo_server.authentication.Dto.JoinRequestDto;
import com.ALGo.ALGo_server.authentication.Jwt.JwtTokenProvider;
import com.ALGo.ALGo_server.entity.User;
import com.ALGo.ALGo_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public Long join(JoinRequestDto requestDto) {
        //이메일 중복 검사
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복된 이메일입니다");
        }
        User user = userRepository.save(requestDto.toEntity());
        user.encodePassword(passwordEncoder); //비밀번호 암호화

        return user.getId();
    }
}
