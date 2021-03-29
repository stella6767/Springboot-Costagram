package com.example.costagram.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.costagram.config.auth.PrincipalDetails;
import com.example.costagram.domain.user.User;
import com.example.costagram.service.UserService;
import com.example.costagram.web.dto.user.UserProfileRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;
	
	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {

		UserProfileRespDto userProfileRespDto = userService.회원프로필(id, principalDetails.getUser().getId());
		model.addAttribute("dto", userProfileRespDto);


		return "user/profile";
	}
	
	@GetMapping("/user/{id}/profileSetting")
	public String profileSetting(@PathVariable int id) {
		return "user/profileSetting";
	}
	
}
