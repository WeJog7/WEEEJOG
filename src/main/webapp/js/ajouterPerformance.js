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