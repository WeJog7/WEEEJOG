function subscribeEvent(idEvent){
	if (confirm("Do you want to subscribe to this event ?")) {
		window.location("addparticipant?idEvent="+idEvent);
		return true;
	}
	else{
		return false;}
}


function deletePerformance(idPerformance){
	if (confirm("Do you want to delete this performance ?")) {
		window.location("deletePerformance?idperformance="+idPerformance);
		return true;
	}
	else{
		return false;}
}


function deleteEvent(idEvent) {
	if (confirm("Do you want to delete this Event ?")) {
		window.location("deleteEvent?idEvent="+idEvent);
		return true;
	}
	else{
		return false;}
}


function deleteArticle(idArticle) {
	if (confirm("Do you want to delete this article ?")) {
		window.location("deleteArticle?idarticle="+idArticle);
		return true;
	}
	else{
		return false;}
}