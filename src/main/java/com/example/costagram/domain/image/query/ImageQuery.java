package com.example.costagram.domain.image.query;

public class ImageQuery {

	public static String exploreQuery() {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("select * from image where id in ( ");
		sb.append("select imageId from ( ");
		sb.append("select imageId, count(ImageId) likecount from likes ");
		sb.append("where userId = 1 group by imageId order by likecount desc) t) ");
		
		
		return sb.toString();
		
	}
}
