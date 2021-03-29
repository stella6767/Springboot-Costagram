package com.example.costagram.web.dto.user;

import com.example.costagram.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileRespDto { //DTO 완성
	private boolean followState;
	private int followCount;
	private int imageCount;
	private User user;

}
