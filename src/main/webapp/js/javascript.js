JQuery(function($){
	$('#datepicker').datepicker();	
});

function fenvoi()
{
		// création des variables objets
		var mail = document.querySelector('input') ; 
		var ConfirmMail = document.querySelectorAll('input')[1] ; 
		var password = document.querySelectorAll('input')[2] ; 
		var confirmPassword = document.querySelectorAll('input')[3] ; 
		var formulaire  =  document.querySelector('form');  	
	// test
	if(mail.value == ConfirmMail.value && password.value == confirmPassword.value) 
	{formulaire.submit() ; 	} 
	else 	alert('Erreur de saisie d adresse mail ou de motdepasse ') ;
} // fin fonction 

function verifForm(f)
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
}




