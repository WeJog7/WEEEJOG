function deleteEvent(idEvent) {
	if (confirm("Would you like to delete this Event ?")) {
		window.location("deleteEvent?idEvent="+idEvent);
		return true;
	}
	else{
		return false;}
}