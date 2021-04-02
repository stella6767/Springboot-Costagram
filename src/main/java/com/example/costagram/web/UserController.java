package com.example.costagram.web;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.costagram.config.auth.PrincipalDetails;
import com.example.costagram.domain.follow.Follow;
import com.example.costagram.domain.user.User;
import com.example.costagram.service.FollowService;
import com.example.costagram.service.UserService;
import com.example.costagram.web.dto.CMRespDto;
import com.example.costagram.web.dto.follow.FollowRespDto;
import com.example.costagram.web.dto.user.UserProfileRespDto;
import com.example.costagram.web.dto.user.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;
	private final FollowService followService;
	
	@GetMapping("/user/{pageUserId}/follow") //data 리턴하는 것
	public @ResponseBody CMRespDto<?> followList(@AuthenticationPrincipal PrincipalDetails principalDetails ,@PathVariable int pageUserId){
		
		System.out.println("들어옴?");
		
		List<FollowRespDto> follows = followService.팔로우리스트(principalDetails.getUser().getId(), pageUserId);
		return new CMRespDto<>(1, follows);
	}
	
	
	
	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {

		UserProfileRespDto userProfileRespDto = userService.회원프로필(id, principalDetails.getUser().getId());
		model.addAttribute("dto", userProfileRespDto);


		return "user/profile";
	}
	
	@GetMapping("/user/{id}/profileSetting")
	public String profileSetting(@PathVariable int id, Model model,  @AuthenticationPrincipal PrincipalDetails principalDetails) {

//		UserProfileRespDto userProfileRespDto = userService.회원프로필(id, principalDetails.getUser().getId());	
//		model.addAttribute("dto", userProfileRespDto);		
		return "user/profileSetting";
	}
	
	
	@PutMapping("/user/{id}")
	public @ResponseBody CMRespDto<?> profileUpdate(@PathVariable int id, User user, @AuthenticationPrincipal PrincipalDetails principalDetails) {
			
		System.out.println(user);
		
		User userEntity = userService.회원수정(id, user);
		principalDetails.setUser(userEntity);
	
		return new CMRespDto<>(1, null);
	}
	
}
