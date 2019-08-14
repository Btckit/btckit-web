function generate() {
	document.getElementById("generateSpinner").hidden = false;
	var settings = {
		"async": true,
		"crossDomain": true,
		"url": "https://cors-anywhere.herokuapp.com/http://api.btckit.io/createPair",
		"method": "GET",
		"headers": {
			"X-Requested-With": "XMLHttpRequest",
			"cache-control": "no-cache"
		}
	}
	var j = $.ajax(settings).done(function (response) {
		changeValues(response.publicAddress, response.privateKey, response.balance);
		document.getElementById("generateSpinner").hidden = true;
	});
}

function changeValues(publicAddress, privateKey, balance) {
	$("#publicAddress").html(publicAddress);
	$("#privateKey").html(privateKey);
	$("#balance").html(balance);
}