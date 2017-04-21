function deleteFriend(idFriend){
	if (confirm("Do you want to delete this friend ?")) {
		window.location("deleteami?idusers="+idFriend);
		return true;
	}
	else{
		return false;}
}