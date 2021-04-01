package com.example.costagram.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.costagram.config.auth.PrincipalDetails;
import com.example.costagram.domain.image.Image;
import com.example.costagram.domain.image.ImageRepository;
import com.example.costagram.domain.tag.Tag;
import com.example.costagram.domain.tag.TagRepository;
import com.example.costagram.utils.TagUtils;
import com.example.costagram.web.dto.image.ImageReqDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class ImageService {

	private final ImageRepository imageRepository;
	private final TagRepository tagRepository;
	
	@Value("${file.path}")  //@Value 안의 ${} 안으로 application.yml 의 설정을 바로 땡겨올 수 있음.     
	private String uploadFolder;
	
	public List<Image> 피드이미지(int principalId){
		
		//1. principalId로 내가 팔로우하고 있는 사용자를 찾아야 됨. (한개이거나 컬렉션이거나)
		//select * from image where userId in (select toUserId from follow where fromUserId = "로그인아이디");
		
		List<Image> images = imageRepository.mfeed(principalId);
		
		//좋아요 하트 색깔 로직
		images.forEach((image)->{
			
			int likeCount = image.getLikes().size();
			image.setLikeCount(likeCount);
			
			image.getLikes().forEach((like)->{
				if(like.getUser().getId() == principalId) {
					image.setLikeState(true);
				}
			});
		});
		
		return images;
	}
	
	
	@Transactional
	public void 사진업로드(ImageReqDto imageReDto, PrincipalDetails principalDetails) {

		UUID uuid = UUID.randomUUID(); //같은 이름의 사진이 들어오면 충돌나므로 방지하기 위해
		String imageFileName = uuid+"_"+imageReDto.getFile().getOriginalFilename();
		System.out.println("파일명 : "+imageFileName);

		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		System.out.println("파일패스 : "+imageFilePath);
		try {
			Files.write(imageFilePath, imageReDto.getFile().getBytes());
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 참고 :  Image 엔티티에 Tag는 주인이 아니다. Image 엔티티로 통해서 Tag를 save할 수 없다.

		// 1. Image 저장
		Image image = imageReDto.toEntity(imageFileName, principalDetails.getUser());
		Image imageEntity = imageRepository.save(image);

		// 2. Tag 저장
		List<Tag> tags = TagUtils.parsingToTagObject(imageReDto.getTags(), imageEntity);
		tagRepository.saveAll(tags);

	}
	
}
