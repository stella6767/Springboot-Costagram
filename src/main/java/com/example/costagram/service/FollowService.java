package com.example.costagram.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.costagram.domain.follow.FollowReposiotry;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FollowService {

	private final FollowReposiotry followReposiotry;

	@Transactional
	public int 팔로우(int fromUserId, int toUserId) {
		System.out.println("fromUserId : "+ fromUserId);
		System.out.println("toUserId : "+ toUserId);
		return followReposiotry.mfollow(fromUserId, toUserId);
	}

	@Transactional
	public int 언팔로우(int fromUserId, int toUserId) {
		return followReposiotry.mUnfollow(fromUserId, toUserId);
	}
}

