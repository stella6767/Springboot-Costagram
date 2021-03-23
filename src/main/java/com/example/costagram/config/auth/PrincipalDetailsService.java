package com.example.costagram.config.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.costagram.domain.user.User;
import com.example.costagram.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

//UserDetailsService는 내부적으로 IOC에 등록되어 있는데 내가 implements 해서 @Service로 직접 띄우면
//싱글톤이라 뒤에 띄운 객체가 덮어씌움

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("로그인 진행중 ....");

		// 로그인이 완료 되면 '/' 로 가게끔 작성

		User principal = userRepository.findByUsername(username); //시큐리티가 비밀번호는 알아서 막아주므로 name만 찾으면 됨
		System.out.println("찾았다면:  "+principal);

		if (principal == null) {
			return null;
//			throw new IllegalArgumentException(); //비번은 못 막음 할려면 인코딩 복호화해서 다시 찾아야됨.
			
		} else {
			return new PrincipalDetails(principal); //SecurityContextHolder => Authentication 객체 내부에 담김.
		}

	}

}
