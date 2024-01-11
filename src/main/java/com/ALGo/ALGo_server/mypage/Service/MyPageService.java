package com.ALGo.ALGo_server.mypage.Service;

import com.ALGo.ALGo_server.entity.User;
import com.ALGo.ALGo_server.mypage.Dto.ChangeInfoRequestDto;
import com.ALGo.ALGo_server.mypage.Dto.MyInfoResponseDto;
import com.ALGo.ALGo_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public MyInfoResponseDto getMyInfo(User user){
        MyInfoResponseDto responseDto = MyInfoResponseDto.builder()
                .email(user.getEmail())
                .language(user.getLanguage())
                .city(user.getCity())
                .gu(user.getGu())
                .build();
        return responseDto;
    }

    @Transactional
    public ResponseEntity changeInfo(User user, ChangeInfoRequestDto requestDto){
        user.updateInfo(requestDto);
        userRepository.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }
}
