package com.example.costagram.utils;

public class Script {
		
	public static String href(String msg, String url) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("location.href = '"+url+"';");
		sb.append("</script>");
		return sb.toString();
		
	}
	
	public static String back(String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("history.back();");
		sb.append("</script>");
		return sb.toString();
		
	}
	//중요한 정보, 즉 동시접근하면 안 되는 메서드는 앞에 syncrionized 붙이고 StringBuffer	
}
