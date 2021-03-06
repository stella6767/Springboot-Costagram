package com.example.costagram.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.costagram.config.auth.PrincipalDetails;
import com.example.costagram.service.CommentService;
import com.example.costagram.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentController {
	
	private final CommentService commentService;
	
	
	@DeleteMapping("/comment/{id}")
	public CMRespDto<?> deleteById(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails){
		commentService.댓글삭제(id, principalDetails.getUser().getId());
		return new CMRespDto<>(1, null);
		
	}

}
