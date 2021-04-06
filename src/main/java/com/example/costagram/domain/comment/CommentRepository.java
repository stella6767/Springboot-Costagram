package com.example.costagram.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
//	@Query(value = "INSERT INTO comment(content, imageId, userId, createDate) VALUES (:content, :imageId,:userId, now())", nativeQuery = true)
//	int mSave(String content, int imageId, int userId);  //이렇게 하면 문제인게 결과값 int 밖에 못 받으므로, comment id를 못 받음.. delete할려면 commentId가 필요한데.. 그래서 안씀

}
