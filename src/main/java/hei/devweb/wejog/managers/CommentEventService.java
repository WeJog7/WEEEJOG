package hei.devweb.wejog.managers;

import java.util.List;

import hei.devweb.wejog.entities.CommentEvent;
import hei.devweb.wejog.impl.CommentEventDaoImpl;

public class CommentEventService {
	
	private static CommentEventDaoImpl commentDao = new CommentEventDaoImpl();
	
	
	public List<CommentEvent> ListCommentEventToDo(Long idEvent){
		return commentDao.ListCommentEventToDo(idEvent);
	}

}
