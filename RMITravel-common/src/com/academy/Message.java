package com.academy;

import java.io.Serializable;

public class Message implements Serializable {
	/**
	 * Khi báº¡n muá»‘n truyá»�n cÃ¡c dá»¯ liá»‡u á»Ÿ dáº¡ng object thÃ¬ báº¡n cáº§n pháº£i implements Serializable
	 * vÃ¬ nÃ³ giÃºp dá»¯ liá»‡u cá»§a báº¡n truyá»�n Ä‘i sáº½ nguyÃªn ( cÃ³ nghÄ©a lÃ  k bá»‹ máº¥t mÃ¡c dá»¯ liá»‡u)
	 */
	private static final long serialVersionUID = 1330031345670397580L;
	private String title;
	private String body;
	
	public Message(String title, String body) {
		super();
		this.title = title;
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	
}
