function deleteFriend(idFriend){
	if (confirm("Do you want to delete this friend ?")) {
		window.location("deleteami?idusers="+idFriend);
		return true;
	}
	else{
		return false;}
}


function UnsubscribeEvent(idEvent){
	if (confirm("Do you want to unsubscribe to this event ?")) {
		window.location("desinscrireparticipant?idevent="+idEvent);
		return true;
	}
	else{
		return false;}
}


function subscribeEvent(idEvent){
	if (confirm("Do you want to subscribe to this event ?")) {
		window.location("addparticipant?idevent="+idEvent);
		return true;
	}
	else{
		return false;}
}


function deletePerformance(idPerformance){
	if (confirm("Do you want to delete this performance ?")) {
		window.location("deletePerformance?idperformance="+idPerformance);
		return true;
	}
	else{
		return false;}
}


function deletePerformanceAdmin(idPerformance){
	if (confirm("Do you want to delete this performance ?")) {
		window.location("deleteperformanceadmin?idperformance="+idPerformance);
		return true;
	}
	else{
		return false;}
}


function deleteEvent(idEvent) {
	if (confirm("Would you like to delete this Event ?")) {
		window.location("deleteeventadmin?idevent="+idEvent);
		return true;
	}
	else{
		return false;}
}


function deleteArticle(idArticle) {
	if (confirm("Would you like to delete this article ?")) {
		window.location("deleteArticle?idarticle="+idArticle);
		return true;
	}
	else{
		return false;}
}


function deleteArticleAdmin(idArticle) {
	if (confirm("Would you like to delete this article ?")) {
		window.location("deletearticleadmin?idarticle="+idArticle);
		return true;
	}
	else{
		return false;}
}


function suppressionusers(id) {
	if (confirm("Would you like to delete this user ?")) {
		window.location("deleteuser?idusers="+id);
		return true;
	}
	else{
		return false;}

}


function validEvent(radio) {

	var datepicker = document.getElementById("datepicker").value;
	var hour = document.getElementById("hour");
	var hourChoosed = hour.options[hour.selectedIndex].value;
	var minutes = document.getElementById("minutes");
	var minutesChoosed = minutes.options[minutes.selectedIndex].value;
	var address = document.getElementById("adress").value; 
	var duration = document.getElementById("duration").value; 
	var distance = document.getElementById("distance").value;
	var flag=0;

	if((radio[0].checked || (radio[1].checked)) && datepicker.replace(/ /g,"")!="" && address.replace(/ /g,"")!="" 
		&& duration.replace(/ /g,"")!="" && distance.replace(/ /g,"")!=""){
		return true;
	}
	else{
		if(datepicker.replace(/ /g,"")=="" && flag==0){
			alert('Please enter a date.');
			flag=1;
		}

		if(flag==0){
			flag = CheckDate(datepicker);
		}

		if(hourChoosed=="" && flag==0){
			alert('Please enter an hour.');
			flag=1;
		}
		
		if(minutesChoosed=="" && flag==0){
			alert('Please enter minutes.');
			flag=1;
		}

		if (!radio[0].checked && !radio[1].checked && flag==0){
			alert("Please enter the moment of the day.");
			flag=1;
		}

		if(address.replace(/ /g,"")=="" && flag==0){
			alert('Please enter an address.');
			flag=1;
		}

		if(duration.replace(/ /g,"")=="" && flag==0){
			alert('Please enter a duration.');
			flag=1;
		}

		if(distance.replace(/ /g,"")=="" && flag==0){
			alert('Please enter the distance of the route.');
			flag=1;
		}

		return false;
	}
};


function validPerformance(){

	var datepicker = document.getElementById("datepicker").value;
	var duration = document.getElementById("duration").value;
	var distance = document.getElementById("distance").value;
	var average = document.getElementById("average").value;
	var calories = document.getElementById("calories").value;
	var flag=0;

	if(datepicker.replace(/ /g,"")!="" && duration.replace(/ /g,"")!="" && distance.replace(/ /g,"")!="" && average.replace(/ /g,"")!=""
		&& calories.replace(/ /g,"")!=""){
		return true;
	}

	else{
		if(datepicker.replace(/ /g,"")==""){
			alert('Please enter a date.');
			flag=1;
		}

		if(flag==0){
			flag = CheckDate(datepicker);
		}

		if(duration.replace(/ /g,"")=="" && flag==0){
			alert('Please enter a duration.');
			flag=1;
		}

		if(distance.replace(/ /g,"")=="" && flag==0){
			alert('Please enter a distance.');
			flag=1;
		}

		if(average.replace(/ /g,"")=="" && flag==0){
			alert('Please enter an average.');
			flag=1;
		}

		if(calories.replace(/ /g,"")=="" && flag==0){
			alert('Please enter the number of calories you have used.');
			flag=1;
		}

		return false;
	}
}


function validArticle(){

	var lien_source = document.getElementById("lien_source").value;
	var titre_article = document.getElementById("titre_article").value;
	var synopsis = document.getElementById("synopsis").value;
	var flag=0;

	if(lien_source.replace(/ /g,"")!="" && titre_article.replace(/ /g,"")!="" && synopsis.replace(/ /g,"")!=""){
		return true;
	}

	else{
		if(lien_source.replace(/ /g,"")==""){
			alert('Please enter the link of your source.');
			flag=1;
		}

		if(titre_article.replace(/ /g,"")=="" && flag==0){
			alert('Please enter a title for your article.');
			flag=1;
		}

		if(synopsis.replace(/ /g,"")=="" && flag==0){
			alert('Please enter the synopsis of your article.');
			flag=1;
		}

		return false;
	}
}


function validContact(){

	var email = document.getElementById("email_contact").value;
	var firstName = document.getElementById("firstName").value;
	var lastName = document.getElementById("lastName").value;
	var message = document.getElementById("message").value;
	var verification = grecaptcha.getResponse();
	var flag=0;

	if(verification.length != 0 && email.replace(/ /g,"")!="" && firstName.replace(/ /g,"")!="" && lastName.replace(/ /g,"")!=""
		&& message.replace(/ /g,"")!=""){
		return true;
	}

	else{
		if(email.replace(/ /g,"")==""){
			alert('Please enter your email.');
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
	var datepicker = document.getElementById("datepicker").value;
	var mail = document.getElementById("mail").value;
	var ConfirmMail = document.getElementById("ConfirmMail").value; 
	var password = document.getElementById("password").value; 
	var confirmPassword = document.getElementById("confirmPassword").value;
	var verification = grecaptcha.getResponse();
	var flag=0;


	if((radio[0].checked || (radio[1].checked)) && firstName.replace(/ /g,"")!="" && lastName.replace(/ /g,"")!="" && datepicker.replace(/ /g,"")!="" && password!=""
		&& mail!="" && mail == ConfirmMail && password == confirmPassword && verification.length != 0){
		return true;
	}
	else{
		if (!radio[0].checked && !radio[1].checked){
			alert("Please chose a sex");
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

		if(datepicker.replace(/ /g,"")=="" && flag==0){
			alert('Please enter a date.');
			flag=1;
		}

		if(flag==0){
			flag = CheckDate(datepicker);
		}

		if(mail=="" && flag==0){
			alert('Please enter an email.');
			flag=1;
		}

		if(password=="" && flag==0){
			alert('Please enter a password.');
			flag=1;
		}

		if(mail != ConfirmMail && flag==0){
			alert('Mails adress are not the same');
			//document.getElementById("mailCheck").innerHTML = "Mails adress are not the same";
			flag=1;
		}
		if(password != confirmPassword && flag==0){
			alert('You have entered two different passwords');
			//document.getElementById("passwordCheck").innerHTML = "You have entered two different passwords";
			flag=1;
		}
		if(verification.length == 0 && flag==0){
			alert('The captcha must be done.');
			flag=1;
		}
		return false;
	}

};


function CheckDate(datepicker) {
	var dateNow = new Date();
	var yearMin= dateNow.getFullYear()-100;
	var yearMax= dateNow.getFullYear();
	var separateur="-";
	var a=(datepicker.substring(0,4));
	var m=(datepicker.substring(5,7));
	var j=(datepicker.substring(8));
	var flag=0;

	if((!isNaN(datepicker.substring(4,5))||!isNaN(datepicker.substring(7,8))&& flag==0)){
		alert("Wrong format, please enter a correct date"); flag=1;
	}

	if((datepicker.substring(4,5)!=separateur||datepicker.substring(7,8)!=separateur) && flag==0){
		alert("Delimiters must be like "+separateur); flag=1;
	}

	if((isNaN(a)||(a<yearMin)||(a>yearMax)) && flag==0){
		alert("In the date, the year is not correct."); flag=1;
	}

	if((isNaN(m)||(m<1)||(m>12)) && flag==0){
		alert("In the date, the month is not correct."); flag=1;
	}

	if((isNaN(j)||(j<1)||(j>31)) && flag==0){
		alert("In the date, the day is not correct."); flag=1;
	}

	return flag;
};