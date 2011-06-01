//Useful links:
// http://code.google.com/apis/maps/documentation/javascript/reference.html#Marker
// http://code.google.com/apis/maps/documentation/javascript/services.html#Geocoding
// http://jqueryui.com/demos/autocomplete/#remote-with-cache

var geocoder;
var map;
var marker;

// Fonction d'initialisation de la map
function initialize() {
    // MAP
    var latlng = new google.maps.LatLng(41.659, -4.714);
    var options = {
	zoom : 10,
	center : latlng,
	mapTypeId : google.maps.MapTypeId.ROADMAP
    };

    map = new google.maps.Map(document.getElementById("map_canvas"), options);

    // GEOCODER
    geocoder = new google.maps.Geocoder();

    marker = new google.maps.Marker({
	    map : map,
	    draggable : true
	});

}

// computePolygon
// Calcule l'ensemble des points nécessaires, avec un rayon donné, et le nombre d'arretes (n),
// le polygon autour de l'endroit précisé par l'utilisateur
// Fonction qui doit être appelé après l'initialisation de la map.
// rayon en degree
// 40076 / 360 = 111.32 km/degree (the accepted figure is 111.325 km).
// => 1 degree = 111.325km
// => 1km = 0,008982708
function computePolygon(lat, lon, rayon, n){

    var angle = 0;
    var deg_km = 0.008982708;
    var deg_rayon_km = rayon*deg_km;
    polygon = new Array();
    var points = new Array();
    lats = [];
    lons = [];
    pi_s2 = Math.pi/2;
    tr_pi_s2 = 3 * pi_s2;
    de_pi = Math.pi*2;
    for(i = 0; i < n; i++){

	lats[i] = lat + deg_rayon_km*Math.sin(angle);
	lons[i] = lon + deg_rayon_km*Math.cos(angle);

	points.push(new google.maps.LatLng(lats[i],lons[i]));

	angle += 2*Math.PI/n;
    }

    var poly = new google.maps.Polygon({
	    paths: points,
	    strokeColor: "#FF0000",
	    strokeOpacity: 0.8,
	    strokeWeight: 3,
	    fillColor: "#FF0000",
	    fillOpacity: 0.35
	});

    return poly;
}

$(document).ready(function() {
	
	initialize();
	
	$(function() {
		$("#address").autocomplete({
			// This bit uses the geocoder to fetch address
			// values
			source : function(request, response) {
			    geocoder.geocode({'address' : request.term},
					     function(results, status) {
						 response($.map(results, function(item) {
							     return {
								 label : item.formatted_address,
								     value : item.formatted_address,
								     latitude : item.geometry.location
								     .lat(),
								     longitude : item.geometry.location
								     .lng()
								     }
							 }));
					     })
				},
			    // This bit is executed upon selection of an address
			    select : function(event, ui) {
			    $("#latitude").val(ui.item.latitude);
			    $("#longitude").val(ui.item.longitude);
			    var location = new google.maps.LatLng(ui.item.latitude, ui.item.longitude);
			    marker.setPosition(location);
			    map.setCenter(location);
			    circleOptions.setMap();
			    polygon.setMap();
			    var circleOptions = {
				strokeColor: "#FF0000",
				strokeOpacity: 0.8,
				strokeWeight: 2,
				fillColor: "#F0C300",
				fillOpacity: 0.25,
				map: map,
				center: location,
				radius: 10000 //meters
			    };
			    cityCircle = new google.maps.Circle(circleOptions);

			    
			    var polygon = computePolygon(ui.item.latitude,ui.item.longitude,10,12);			    

			    polygon.setMap(map);
			    
			}
		    });
	    });

	// Add listener to marker for reverse geocoding
	google.maps.event.addListener(marker, 'drag', function() {
		geocoder.geocode({
			'latLng' : marker.getPosition()
			    }, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
			    if (results[0]) {
				$('#address').val(results[0].formatted_address);
				$('#latitude').val(marker.getPosition().lat());
				$('#longitude').val(marker.getPosition().lng());
			    }
			}
		    });
	    });

    });