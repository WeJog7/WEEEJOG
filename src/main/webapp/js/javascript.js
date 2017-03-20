JQuery(function($){
	$('#datepicker').datepicker();	
});


function date() {
    $( "#datepicker" ).datepicker({
    	changeMonth: true,
        changeYear: true,
        showButtonPanel: true,
        yearRange: "-100:+0",
        dateFormat: 'yy-mm-dd',
    });
  } ;

/*function isValidForm(){

	var mail = document.getElementById("mail").value;
	var ConfirmMail = document.getElementById("ConfirmMail").value; 
	var password = document.getElementById("password").value; 
	var confirmPassword = document.getElementById("confirmPassword").value;

	if(mail == ConfirmMail && password == confirmPassword){
		return true;
	} 
	else{
		alert('There is a problem in your form');
		return false;
	}
}*/

function testerRadio(radio) {

	var mail = document.getElementById("mail").value;
	var ConfirmMail = document.getElementById("ConfirmMail").value; 
	var password = document.getElementById("password").value; 
	var confirmPassword = document.getElementById("confirmPassword").value;

	for (var i=0; i<radio.length;i++) {
		if (radio[i].checked) {
			if(mail == ConfirmMail && password == confirmPassword){
				return true;
			}
			else{
				if(mail != ConfirmMail){
					alert('Mails adress are not the same');
					//document.getElementById("mailCheck").innerHTML = "Mails adress are not the same";
				}
				if(password != confirmPassword){
					alert('You have entered two different passwords');
					//document.getElementById("passwordCheck").innerHTML = "You have entered two different passwords";
				}
				return false;
			}
		}
		else{
			alert("Please chose a sex");
			return false;
		}
	}
}

/*function verifForm(f)
{
	var nomOk = verifNom(f.name);
	var prenomOk = veriflastName(f.lastName);
	var birthOk = verifbirth(f.birth);
	var mailOk = verifmail(f.mail);
	var ConfirmMailOk = verifConfirmMail(f.ConfirmMail);
	var passwordOk = verifpassword(f.password);
	var confirmPasswordOk = verifconfirmPassword(f.confirmPassword);


	if(nomOk && prenomOk &&  birthOk && mailOk && ConfirmMailOk && passwordOk && confirmPasswordOk)
		return true;
	else
	{
		alert("Veuillez remplir correctement tous les champs");
		return false;
	}
}*/




