function sendInvitation(idFriend){
	if (confirm("Do you want to send an invitation to be friend ?")) {
		window.location("sendInvitation?idusers="+idFriend);
		return true;
	}
	else{
		return false;}
}