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
		
		User userEntity = userRepository.findById(userId).orElseThrow(()-> {
			return new IllegalArgumentException();
		});
		
		int followState = followRepository.mFollowState(principalId, userId);
		int followCount = followRepository.mFollowCount(userId);
		System.out.println(followState == 1);
		
		userProfileRespDto.setFollowState(followState == 1);
		userProfileRespDto.setFollowCount(followCount); // 내가 팔로우 하고 있는 카운트
		userProfileRespDto.setImageCount(userEntity.getImages().size());
		userProfileRespDto.setUser(userEntity);
		
		return userProfileRespDto;
	}
	
	
}
