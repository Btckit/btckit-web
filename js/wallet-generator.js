let startWith = document.getElementById("startWith");

function generate() {
	document.getElementById("generateSpinner").hidden = false;

	var settings = {
		"async": true,
		"crossDomain": true,
		"url": "https://api.btckit.io/createPair?startWith=" + startWith.value,
		"method": "GET",
		"headers": {
			"X-Requested-With": "XMLHttpRequest",
			"cache-control": "no-cache"
		}
	}
	$.ajax(settings).done(function (response) {
		changeValues(response.publicAddress, response.privateKey);
		document.getElementById("generateSpinner").hidden = true;
	});
}

function changeValues(publicAddress, privateKey, balance) {
	$("#publicAddress").html(publicAddress);
	$("#privateKey").html(privateKey);
}
