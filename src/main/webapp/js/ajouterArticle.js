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