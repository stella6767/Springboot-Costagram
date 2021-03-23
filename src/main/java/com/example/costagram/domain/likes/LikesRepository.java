package com.example.costagram.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Integer> { //Like가 mysql에서 예약어라 어쩔 수 없이 Likes

}
