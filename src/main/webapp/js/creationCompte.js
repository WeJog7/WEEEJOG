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