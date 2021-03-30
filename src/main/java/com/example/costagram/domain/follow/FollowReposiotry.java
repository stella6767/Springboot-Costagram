package com.example.costagram.domain.follow;

import java.util.List;

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
	
	@Query(value = "select count(*) from follow where fromUserId = :principalId AND toUserId = :userId", nativeQuery = true)
	int mFollowState(int principalId, int userId); //내가 팔로우하고 있는지의 여부

	@Query(value = "select count(*) from follow where fromUserId = :userId", nativeQuery = true)
	int mFollowCount(int userId); //페이지의 주인을 팔로우하고 있는 사람숫자
	
//	//네이밍 쿼리(JPA가 제공해주는 것) = 간단한 쿼리
//	List<Follow> findByFromUserId(int userId); 안 쓸거임
	
	
	
	

}
