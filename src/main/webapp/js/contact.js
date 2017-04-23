function validContact(){

	var email = document.getElementById("email_contact").value;
	var firstName = document.getElementById("firstName").value;
	var lastName = document.getElementById("lastName").value;
	var message = document.getElementById("message").value;
	var verification = grecaptcha.getResponse();
	var flag=0;

	if(verification.length != 0 && email.replace(/ /g,"")!="" && firstName.replace(/ /g,"")!="" && lastName.replace(/ /g,"")!=""
		&& message.replace(/ /g,"")!="" && validateEmail(email)==true){
		return true;
	}

	else{
		if(email.replace(/ /g,"")=="" || validateEmail(email)==false){
			if(email.replace(/ /g,"")==""){
				alert('Please enter your email.');
			}
			else{
				alert('Please enter a correct email.');
			}
			flag=1;
		}

		if(firstName.replace(/ /g,"")=="" && flag==0){
			alert('Please enter your first name.');
			flag=1;
		}

		if(lastName.replace(/ /g,"")=="" && flag==0){
			alert('Please enter your last name.');
			flag=1;
		}

		if(message.replace(/ /g,"")=="" && flag==0){
			alert('Please enter a message.');
			flag=1;
		}

		if(verification.length == 0 && flag==0){
			alert('The captcha must be done.');
			flag=1;
		}

		return false;
	}
}


function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}