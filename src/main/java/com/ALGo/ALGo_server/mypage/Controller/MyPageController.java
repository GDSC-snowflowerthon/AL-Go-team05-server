package com.ALGo.ALGo_server.mypage.Controller;

import com.ALGo.ALGo_server.entity.User;
import com.ALGo.ALGo_server.mypage.Dto.ChangeInfoRequestDto;
import com.ALGo.ALGo_server.mypage.Dto.MyInfoResponseDto;
import com.ALGo.ALGo_server.mypage.Service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/setting")
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping()
    public MyInfoResponseDto getMyInfo(@AuthenticationPrincipal User user){
        return myPageService.getMyInfo(user);
    }

    @PutMapping("/change")
    public ResponseEntity changeInfo(@AuthenticationPrincipal User user, @RequestBody ChangeInfoRequestDto requestDto){
        return myPageService.changeInfo(user, requestDto);
    }

}
