package com.example.costagram.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.costagram.domain.user.User;
import com.example.costagram.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	public void 회원가입(User user){
		
		String rawPassword = user.getPassword(); //만약 암호화를 다른 곳에서도 계속 쓴다면 uils 패키지에 함수화하는게 좋다.
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		
		user.setPassword(encPassword);
		user.setRole("USER");
		
		userRepository.save(user);	
	}
	
	
}
