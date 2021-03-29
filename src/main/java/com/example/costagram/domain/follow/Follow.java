package com.example.costagram.domain.follow;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.example.costagram.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Follow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name = "fromUserId")
	@ManyToOne
	private User fromUser; // ~~ 로부터  ,팔로우하는 사람
	
	@JoinColumn(name = "toUserId")
	@ManyToOne
	private User toUser; // ~~ 를, 팔로우 당하는 사람
	
	@CreationTimestamp
	private Timestamp createDate;
}
