package com.example.costagram.utils;

public class QueryToString {

	public static String popularImg() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("select i.id, i.caption, i.createDate, i.postimageurl, i.userId ");
		sb.append("from image i inner join ");
		sb.append("(select imageId, count(imageId) likeCount ");
		sb.append("from likes ");
		sb.append("group by imageId ");
		sb.append("order by likecount desc) a ");
		sb.append("on i.id = a.imageId order by a.likecount desc ");
		
		return sb.toString();
	}
	
}
