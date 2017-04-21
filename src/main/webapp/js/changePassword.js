function isValidForm(){

	var oldPassword = document.getElementById("oldPassword").value;
	var newPassword = document.getElementById("newPassword").value;
	var newPasswordConfirmation = document.getElementById("newPasswordConfirmation").value;

	if(oldPassword != newPassword){
		if(newPassword == newPasswordConfirmation){
			return true;
		}
		else{
			alert('New Password and New Password Confirmation are not the same.');
			return false;
		}
	}

	else{
		alert('The new password must be different from the older password.');
		return false;
	}
};