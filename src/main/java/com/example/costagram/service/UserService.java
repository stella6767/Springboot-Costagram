package com.example.costagram.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.costagram.domain.follow.FollowReposiotry;
import com.example.costagram.domain.user.User;
import com.example.costagram.domain.user.UserRepository;
import com.example.costagram.web.dto.user.UserProfileRespDto;
import com.example.costagram.web.dto.user.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final FollowReposiotry followRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
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
		
		userEntity.getImages().forEach((image) ->{
			image.setLikeCount(image.getLikes().size());
		});//굳이 likeCount 집어넣을 필요없이 userEntity의 image의 likes 사이즈 들고오면 되지만, 뷰에서 연산을 최소화하기 위해 set해주는 작업을 거치자.
		
		userProfileRespDto.setUser(userEntity);
		
		
		
		return userProfileRespDto;
	}
	
	
	
//	@Transactional
//	public User 회원수정(int id, UserUpdateReqDto userUpdateReqDto) {
//		User userEntity = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("id를 찾을 수 없습니다.");
//		}); //1차 캐시
//		
//		userEntity.setUsername(userUpdateReqDto.getUsername());
//		userEntity.setName(userUpdateReqDto.getName());
//		userEntity.setWebsite(userUpdateReqDto.getWebsite());
//		userEntity.setBio(userUpdateReqDto.getBio());
//		userEntity.setPhone(userUpdateReqDto.getPhone());
//		userEntity.setGender(userUpdateReqDto.getGender());
//		userEntity.setEmail(userUpdateReqDto.getEmail());
//		return userEntity;
//	}//더티체킹
	
	@Transactional
	public User 회원수정(int id, User user) {
		User userEntity = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		}); //1차 캐시
		
		//username, email 수정 불가
		
		String encPassword = bCryptPasswordEncoder.encode(user.getPassword());
		
		userEntity.setName(user.getName());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setBio(user.getBio());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		userEntity.setPassword(encPassword);
		
		return userEntity;
	}
	
}
