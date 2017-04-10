package hei.devweb.wejog.entities;

import java.time.LocalDate;

public class Article {
	private Long idArticle;
	private String name;
	private LocalDate postDate;
	private String content;
	private String link;
	private Long creatorId;
	private String creatorFirstName;
	private String creatorPicturePath;
	private String response;
	

	public Article(Long idArticle, String name,LocalDate postDate,String content,String link,Long creatorId) {
		this.idArticle=idArticle;
		this.name=name;
		this.postDate=postDate;
		this.content=content;
		this.link=link;
		this.creatorId=creatorId;
	}
	
	
	public Article(Long idArticle, String name,LocalDate postDate,String content,String link,Long creatorId, 
			String creatorFirstName, String creatorPicturePath) {
		this.idArticle=idArticle;
		this.name=name;
		this.postDate=postDate;
		this.content=content;
		this.link=link;
		this.creatorId=creatorId;
		this.creatorFirstName=creatorFirstName;
		this.creatorPicturePath=creatorPicturePath;
	}
	
	
	public Article(Long idArticle, String name,LocalDate postDate,String content,String link,Long creatorId, 
			String creatorFirstName, String creatorPicturePath, String response) {
		this.idArticle=idArticle;
		this.name=name;
		this.postDate=postDate;
		this.content=content;
		this.link=link;
		this.creatorId=creatorId;
		this.creatorFirstName=creatorFirstName;
		this.creatorPicturePath=creatorPicturePath;
		this.response=response;
	}
	
	
	public String getResponse() {
		return response;
	}


	public void setResponse(String response) {
		this.response = response;
	}


	public LocalDate getPostDate() {
		return postDate;
	}


	public void setPostDate(LocalDate postDate) {
		this.postDate = postDate;
	}


	public Long getIdArticle() {
		return idArticle;
	}
	
	
	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getContent() {
		return content;
	}
	
	
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public String getLink() {
		return link;
	}
	
	
	public void setLink(String link) {
		this.link = link;
	}
	
	
	public Long getCreatorId() {
		return creatorId;
	}
	
	
	public void setCreatorId(Long userId) {
		this.creatorId = userId;
	}
	
	
	public String getCreatorPicturePath() {
		return creatorPicturePath;
	}
	
	
	public void setCreatorPicturePath(String creatorPicturePath) {
		this.creatorPicturePath = creatorPicturePath;
	}
	
	
	public String getCreatorFirstName() {
		return creatorFirstName;
	}


	public void setCreatorFirstName(String creatorFirstName) {
		this.creatorFirstName = creatorFirstName;
	}
}