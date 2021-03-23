package com.example.costagram.domain.image;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.example.costagram.domain.likes.Likes;
import com.example.costagram.domain.tag.Tag;
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
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	private String caption; //오늘 나 너무 피곤했어!!
    private String ppostImageUrl;
    
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
    
    @OneToMany(mappedBy = "image")
    private List<Tag> tags;
    
    @OneToMany(mappedBy = "image")
    private List<Likes> likes; //A 이미지에 홍길동, 장보고, 임꺽정 좋아요. (고소영)
    
    //follow 정보
    //comment(댓글)
    
    
    @CreationTimestamp
    private Timestamp createDate;
    
}
