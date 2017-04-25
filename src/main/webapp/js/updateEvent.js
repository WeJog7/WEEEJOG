function validEvent(radio) {

	var datepicker = document.getElementById("datepicker").value;
	var hour = document.getElementById("hour");
	var hourChoosed = hour.options[hour.selectedIndex].value;
	var minutes = document.getElementById("minutes");
	var minutesChoosed = minutes.options[minutes.selectedIndex].value;
	var duration = document.getElementById("duration").value; 
	var distance = document.getElementById("distance").value;
	var flag=0;

	if((radio[0].checked || (radio[1].checked)) && datepicker.replace(/ /g,"")!="" && hourChoosed!="" && minutesChoosed!="" 
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


function initAutocomplete() {
    var map = new google.maps.Map(document.getElementById('map'), {
      center: {lat: -33.8688, lng: 151.2195},
      zoom: 13,
      mapTypeId: 'roadmap'
      
    });

    // Create the search box and link it to the UI element.
    var input = document.getElementById('pac-input');
    var searchBox = new google.maps.places.SearchBox(input);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

    // Bias the SearchBox results towards current map's viewport.
    map.addListener('bounds_changed', function() {
      searchBox.setBounds(map.getBounds());
    });
    
    var markers = [];
    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener('places_changed', function() {
      var places = searchBox.getPlaces();

      if (places.length == 0) {
        return;
      }
      
      // Clear out the old markers.
      markers.forEach(function(marker) {
        marker.setMap(null);
      });
      markers = [];

      // For each place, get the icon, name and location.
      var bounds = new google.maps.LatLngBounds();
      places.forEach(function(place) {
        if (!place.geometry) {
          console.log("Returned place contains no geometry");
          return;
        }
        var icon = {
          url: place.icon,
          size: new google.maps.Size(71, 71),
          origin: new google.maps.Point(0, 0),
          anchor: new google.maps.Point(17, 34),
          scaledSize: new google.maps.Size(25, 25)
        };

        // Create a marker for each place.
        markers.push(new google.maps.Marker({
          map: map,
          icon: icon,
          title: place.name,
          position: place.geometry.location
        }));

        if (place.geometry.viewport) {
          // Only geocodes have viewport.
          bounds.union(place.geometry.viewport);
          document.getElementById("lat").value = place.geometry.location.lat();
          document.getElementById("long").value = place.geometry.location.lng();
        } else {
          bounds.extend(place.geometry.location);
        }
      });
      map.fitBounds(bounds);
      
    });
    
    
}