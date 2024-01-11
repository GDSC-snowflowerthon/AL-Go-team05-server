package com.ALGo.ALGo_server.mypage.Controller;

import com.ALGo.ALGo_server.entity.User;
import com.ALGo.ALGo_server.mypage.Dto.ChangeInfoRequestDto;
import com.ALGo.ALGo_server.mypage.Service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {
    private final MyPageService myPageService;

    @PutMapping("/change")
    public ResponseEntity changeInfo(@AuthenticationPrincipal User user, @RequestBody ChangeInfoRequestDto requestDto){
        return myPageService.changeInfo(user, requestDto);
    }

}
