function validRecaptcha(){
	var verification = grecaptcha.getResponse();
	
	if(verification.length == 0){
		alert('The captcha must be done.');
	}
}