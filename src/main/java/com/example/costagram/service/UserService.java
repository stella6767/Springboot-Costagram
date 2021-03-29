package com.example.costagram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.costagram.domain.follow.FollowReposiotry;
import com.example.costagram.domain.user.User;
import com.example.costagram.domain.user.UserRepository;
import com.example.costagram.web.dto.user.UserProfileRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final FollowReposiotry followRepository;
	
	@Transactional(readOnly = true)
	public UserProfileRespDto 회원프로필(int userId, int principalId) {
		
		UserProfileRespDto userProfileRespDto = new UserProfileRespDto();
		
		User userEntity = userRepository.findById(userId).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		
		userProfileRespDto.setFollowState(true);
		userProfileRespDto.setFollowCount(100); // 내가 팔로우 하고 있는 카운트
		userProfileRespDto.setImageCount(10);
		userProfileRespDto.setUser(userEntity);
	
		
		return userProfileRespDto;
	}
	
	
}
