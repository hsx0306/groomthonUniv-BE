package com.sch.groomthon.hsx0306.service;


import com.sch.groomthon.hsx0306.repository.UserRepository;
import com.sch.groomthon.hsx0306.domains.User;
import lombok.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//스프링시큐리티임포트
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;



@RequiredArgsConstructor
@Service
// 스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스
public class UserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  // 사용자 이름(email)으로 사용자의 정보를 가져오는 메서드
  @Override
  public User loadUserByUsername(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(email));
  }
}