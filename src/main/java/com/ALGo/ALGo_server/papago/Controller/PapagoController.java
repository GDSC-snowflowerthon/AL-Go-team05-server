package com.ALGo.ALGo_server.papago.Controller;

import com.ALGo.ALGo_server.entity.User;
import com.ALGo.ALGo_server.papago.Service.NaverTransService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/papago")
public class PapagoController {
    private final NaverTransService naverTransService;

    //Only BE test용도
    @PostMapping("/translate")
    public String translate(@RequestBody String prompt, @AuthenticationPrincipal User user) throws ParseException {
        return naverTransService.getTransSentence(prompt, user);
    }
}
