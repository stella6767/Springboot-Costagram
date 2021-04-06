package com.example.costagram.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.costagram.config.auth.PrincipalDetails;
import com.example.costagram.domain.follow.FollowReposiotry;
import com.example.costagram.domain.image.Image;
import com.example.costagram.domain.tag.Tag;
import com.example.costagram.domain.user.User;
import com.example.costagram.domain.user.UserRepository;
import com.example.costagram.utils.TagUtils;
import com.example.costagram.web.dto.image.ImageReqDto;
import com.example.costagram.web.dto.user.UserProfileRespDto;
import com.example.costagram.web.dto.user.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final FollowReposiotry followRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Value("${file.path}")  //@Value 안의 ${} 안으로 application.yml 의 value값 바로 땡겨올 수 있음.     
	private String uploadFolder;

	@Transactional
	public User 회원사진변경(MultipartFile profileImageFile, PrincipalDetails principalDetails) {

		UUID uuid = UUID.randomUUID(); //같은 이름의 사진이 들어오면 충돌나므로 방지하기 위해
		String imageFileName = uuid+"_"+profileImageFile.getOriginalFilename();
		System.out.println("파일명 : "+imageFileName);

		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		System.out.println("파일패스 : "+imageFilePath);
		try {
			Files.write(imageFilePath, profileImageFile.getBytes());
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}

		User userEntity = userRepository.findById(principalDetails.getUser().getId()).get();
		userEntity.setProfileImageUrl(imageFileName); //풀경로는 안넣어도 되는게, 
		
		return userEntity;
	}//더티체킹 
	
	
	
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
