function suppressionusers(id) {
	if (confirm("Would you like to delete this user ?")) {
		window.location("deleteuser?idusers="+id);
		return true;
	}
	else{
		return false;}

}


function validContact(){

	var email = document.getElementById("email_contact").value;
	var firstName = document.getElementById("firstName").value;
	var lastName = document.getElementById("lastName").value;
	var message = document.getElementById("message").value;
	var verification = grecaptcha.getResponse();

	if(verification.length != 0 && email.replace(/ /g,"")!="" && firstName.replace(/ /g,"")!="" && lastName.replace(/ /g,"")!=""
		&& message.replace(/ /g,"")!=""){
		return true;
	}

	else{
		if(email.replace(/ /g,"")==""){
			alert('Please enter your email.');
		}
		
		if(firstName.replace(/ /g,"")==""){
			alert('Please enter your first name.');
		}
		
		if(lastName.replace(/ /g,"")==""){
			alert('Please enter your last name.');
		}
		
		if(message.replace(/ /g,"")==""){
			alert('Please enter a message.');
		}

		if(verification.length == 0){
			alert('The captcha must be done.');
		}

		return false;
	}
}


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


function testerRadio(radio) {

	var firstName = document.getElementById("name").value;
	var lastName = document.getElementById("lastName").value;
	var mail = document.getElementById("mail").value;
	var ConfirmMail = document.getElementById("ConfirmMail").value; 
	var password = document.getElementById("password").value; 
	var confirmPassword = document.getElementById("confirmPassword").value;
	var verification = grecaptcha.getResponse();

	for (var i=0; i<radio.length;i++) {
		if (radio[i].checked) {
			if(firstName.replace(/ /g,"")!="" && lastName.replace(/ /g,"")!="" 
				&& mail == ConfirmMail && password == confirmPassword && verification.length != 0){
				return true;
			}
			else{
				if(firstName.replace(/ /g,"")==""){
					alert('Please enter your first name.');
				}
				
				if(lastName.replace(/ /g,"")==""){
					alert('Please enter your last name.');
				}
				
				if(mail != ConfirmMail){
					alert('Mails adress are not the same');
					//document.getElementById("mailCheck").innerHTML = "Mails adress are not the same";
				}
				if(password != confirmPassword){
					alert('You have entered two different passwords');
					//document.getElementById("passwordCheck").innerHTML = "You have entered two different passwords";
				}
				if(verification.length == 0)
				{
					alert('The captcha must be done.');
				}
				return false;
			}
		}
		else{
			alert("Please chose a sex");
			return false;
		}
	}
};