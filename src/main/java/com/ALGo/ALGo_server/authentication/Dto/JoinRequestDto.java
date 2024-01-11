package com.ALGo.ALGo_server.authentication.Dto;

import com.ALGo.ALGo_server.entity.Role;
import com.ALGo.ALGo_server.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinRequestDto {

    private String email;
    private String password;
    private String language;
    private String city;
    private String gu;
    private Role role;

    @Builder
    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .language(language)
                .city(city)
                .gu(gu)
                .role(Role.ROLE_USER)
                .build();
    }
}
