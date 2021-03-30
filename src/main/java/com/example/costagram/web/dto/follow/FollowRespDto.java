package com.example.costagram.web.dto.follow;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FollowRespDto {
	private int userId;
	private String username;
	
	private String profileImageUrl;
	private BigInteger followState; //값은 BigInteget로 받아야함
	private BigInteger equalState;

}
