package com.example.costagram.service;

import org.springframework.stereotype.Service;

import com.example.costagram.domain.user.User;
import com.example.costagram.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	
	public void 회원프로필(int userId) {
		User userEntity = userRepository.findById(userId).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
	}
	
	
}
