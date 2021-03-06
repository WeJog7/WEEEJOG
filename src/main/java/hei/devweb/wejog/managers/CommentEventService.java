package hei.devweb.wejog.managers;

import java.util.List;

import hei.devweb.wejog.entities.CommentEvent;
import hei.devweb.wejog.impl.CommentEventDaoImpl;


public class CommentEventService {
	
	private static CommentEventDaoImpl commentDao = new CommentEventDaoImpl();
	
	
	private static class CommentEventServiceHolder {
		private static CommentEventService instance = new CommentEventService();
	}

	public static CommentEventService getInstance() {
		return CommentEventServiceHolder.instance;
	}
	
	
	public List<CommentEvent> ListCommentEventToDo(Long idEvent){
		return commentDao.ListCommentEventToDo(idEvent);
	}
	
	
	public CommentEvent addCommentEvent(CommentEvent newCommentEvent){
		return commentDao.addCommentEvent(newCommentEvent);
	}
	
	
	public void deleteCommentEvent(long idComment) {
		commentDao.deleteCommentEvent(idComment);
	}
	
	
	public CommentEvent getCommentEvent(Long idComment){
		return commentDao.getCommentEvent(idComment);
	}

}
