function subscribeEvent(idEvent){
	if (confirm("Do you want to subscribe to this event ?")) {
		window.location("addparticipant?idevent="+idEvent);
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
	if (confirm("Would you like to delete this Event ?")) {
		window.location("deleteeventadmin?idevent="+idEvent);
		return true;
	}
	else{
		return false;}
}


function deleteArticle(idArticle) {
	if (confirm("Would you like to delete this article ?")) {
		window.location("deleteArticle?idarticle="+idArticle);
		return true;
	}
	else{
		return false;}
}