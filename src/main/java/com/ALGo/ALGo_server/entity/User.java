package com.ALGo.ALGo_server.entity;

import com.ALGo.ALGo_server.mypage.Dto.ChangeInfoRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String password;
    private String refreshToken;
    private String city;
    private String gu;
    private String language;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void updateInfo(ChangeInfoRequestDto requestDto){
        this.language = requestDto.getLanguage();
        this.city = requestDto.getCity();
        this.gu = requestDto.getGu();
    }


    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }


    //UserDetail implement한 내용
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(role.name()));
        return auth;
    }

    @Builder
    public User(String email, String password, String city, String gu, String language, Role role){
        this.email = email;
        this.password = password;
        this.city = city;
        this.gu = gu;
        this.language = language;
        this.role = role;
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
