function unblockUser(idUser){
	if (confirm("Do you want to unblock this user ?")) {
		window.location("unblockUser?idusers="+idUser);
		return true;
	}
	else{
		return false;}
}


function blockUser(idUser){
	if (confirm("Do you want to block this user ?")) {
		window.location("blockUser?idusers="+idUser);
		return true;
	}
	else{
		return false;}
}


function deleteUser(id) {
	if (confirm("Would you like to delete this user ?")) {
		window.location("deleteuser?idusers="+id);
		return true;
	}
	else{
		return false;}

}