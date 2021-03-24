package com.example.costagram.domain.follow;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FollowReposiotry extends JpaRepository<Follow, Integer>{
	
	//write (@Modifying 안 붙이면 발동안함, Service 함수에도 @Transitonal 붙여야 됨.)
	
	@Modifying
	@Query(value = "INSERT INTO follow(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
	int mfollow(int fromUserId, int toUserId); //요렇게 하면 Follow 객체 리턴이 안 되나....
	//prepareStatement updateQuery() => -1,1,0
	
	
	@Modifying
	@Query(value = "DELETE FROM follow WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
	int mUnfollow(int fromUserId, int toUserId); //prepareStatement updateQuery() => -1,1,0

}
