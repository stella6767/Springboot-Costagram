package com.example.costagram.web;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.costagram.config.auth.PrincipalDetails;
import com.example.costagram.domain.follow.Follow;
import com.example.costagram.service.FollowService;
import com.example.costagram.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FollowController {

	
	private final FollowService followService;
	
	
	@PostMapping("/follow/{toUserId}") //  /follow/3
	public CMRespDto<?> follow(@AuthenticationPrincipal PrincipalDetails details, @PathVariable int toUserId) {
		
		int result= followService.팔로우(details.getUser().getId(), toUserId);
		return new CMRespDto<>(1, result);
	}
	
	
	@DeleteMapping("/follow/{toUserId}") //  /follow/3
	public CMRespDto<?> unfollow(@AuthenticationPrincipal PrincipalDetails details, @PathVariable int toUserId) {	
		int result= followService.언팔로우(details.getUser().getId(), toUserId);
		return new CMRespDto<>(1, result);
	}
	
	
}
