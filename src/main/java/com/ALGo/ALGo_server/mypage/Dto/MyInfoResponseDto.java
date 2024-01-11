package com.ALGo.ALGo_server.mypage.Dto;

import com.ALGo.ALGo_server.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyInfoResponseDto {
    private String email;
    private String language;
    private String city;
    private String gu;

}
