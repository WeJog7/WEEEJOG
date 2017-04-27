function deleteEvent(idEvent) {
	if (confirm("Would you like to delete this Event ?")) {
		window.location("deleteEvent?idEvent="+idEvent);
		return true;
	}
	else{
		return false;}
}


function deleteComment(idComment,eventId) {
	if (confirm("Do you want to delete this comment ?")) {
		window.location("deleteComment?idComment="+idComment+'&idEvent='+eventId);
		return true;
	}
	else{
		return false;}
}


function validComment(){

	var newCommentaryContent = document.getElementById("newCommentaryContent").value;

	if(newCommentaryContent.replace(/ /g,"")!=""){
		return true;
	}

	else{
		alert('Please enter a commentary.');
		return false;
	}
}


function showComments() {
	document.getElementById('oldComments').style.display = 'inline';
    document.getElementById('buttonHideComments').style.display = 'inline';
    document.getElementById('buttonShowComments').style.display = 'none';  
}


function hideComments() {
    document.getElementById('oldComments').style.display = 'none';
    document.getElementById('buttonHideComments').style.display = 'none';
    document.getElementById('buttonShowComments').style.display = 'inline';
}