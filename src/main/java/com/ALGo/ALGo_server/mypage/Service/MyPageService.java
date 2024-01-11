package com.ALGo.ALGo_server.mypage.Service;

import com.ALGo.ALGo_server.entity.User;
import com.ALGo.ALGo_server.mypage.Dto.ChangeInfoRequestDto;
import com.ALGo.ALGo_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity changeInfo(@AuthenticationPrincipal User user, ChangeInfoRequestDto requestDto){
        user.updateInfo(requestDto);
        userRepository.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }
}
