function UnsubscribeEvent(idEvent){
	if (confirm("Do you want to unsubscribe to this event ?")) {
		window.location("desinscrireparticipant?idevent="+idEvent);
		return true;
	}
	else{
		return false;}
}