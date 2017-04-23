function isValidForm(){

	var mail = document.getElementById("mail").value;
	var oldPassword = document.getElementById("oldPassword").value;
	var newPassword = document.getElementById("newPassword").value;
	var newPasswordConfirmation = document.getElementById("newPasswordConfirmation").value;
	var flag=0;
	
	if(mail.replace(/ /g,"")!="" && validateEmail(mail)==true && oldPassword.replace(/ /g,"")!="" && newPassword.replace(/ /g,"")!=""
		&& newPasswordConfirmation.replace(/ /g,"")!=""	&& oldPassword!=newPassword && newPassword==newPasswordConfirmation){
		return true;
	}

	else{
		if(mail.replace(/ /g,"")=="" || validateEmail(mail)==false){
			if(mail.replace(/ /g,"")==""){
				alert('Please enter your email.');
			}
			else{
				alert('Please enter a correct email.');
			}
			flag=1;
		}
		
		if(oldPassword.replace(/ /g,"")=="" && flag==0){
			alert('Please enter your actual password.');
			flag=1;
		}
		
		if(newPassword.replace(/ /g,"")=="" && flag==0){
			alert('Please enter your new password.');
			flag=1;
		}
		
		if(oldPassword==newPassword && flag==0){
			alert('The new password must be different from the actual password.');
			flag=1;
		}
		
		if(newPasswordConfirmation.replace(/ /g,"")=="" && flag==0){
			alert('Please confirm your new password.');
			flag=1;
		}
		
		if(newPassword!=newPasswordConfirmation && flag==0){
			alert('New Password and New Password Confirmation are not the same.');
			flag=1;
		}
		
		return false;
	}
};


function validateEmail(mail) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(mail);
}