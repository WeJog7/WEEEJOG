package hei.devweb.wejog.entities;

import java.time.LocalDate;

public class CommentEvent {
	
	private Long idComment;
	private Long idEvent;
	private Long creatorId;
	private LocalDate postDate;
	private String content;
	private String managerFirstName;
	private String managerPicturePath;
	
	/** public CommentEvent
	 * Constructor made for creating an event comment in database
	 */
	public CommentEvent(Long idComment, Long idEvent, Long creatorId, LocalDate postDate, String content){
		this.idComment=idComment;
		this.idEvent=idEvent;
		this.creatorId=creatorId;
		this.postDate=postDate;
		this.content=content;
	}
	
	
	/** public CommentEvent
	 * Constructor made for creating a list of event comment in database
	 */
	public CommentEvent(Long idComment, Long idEvent, Long creatorId, LocalDate postDate, String content, String managerFirstName,
			String managerPicturePath){
		this.idComment=idComment;
		this.idEvent=idEvent;
		this.creatorId=creatorId;
		this.postDate=postDate;
		this.content=content;
		this.managerFirstName=managerFirstName;
		this.managerPicturePath=managerPicturePath;
	}
	

	public String getManagerFirstName() {
		return managerFirstName;
	}


	public void setManagerFirstName(String managerFirstName) {
		this.managerFirstName = managerFirstName;
	}


	public String getManagerPicturePath() {
		return managerPicturePath;
	}


	public void setManagerPicturePath(String managerPicturePath) {
		this.managerPicturePath = managerPicturePath;
	}


	public Long getIdComment() {
		return idComment;
	}

	public void setIdComments(Long idComment) {
		this.idComment = idComment;
	}

	public Long getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(Long idEvent) {
		this.idEvent = idEvent;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public LocalDate getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDate postDate) {
		this.postDate = postDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
