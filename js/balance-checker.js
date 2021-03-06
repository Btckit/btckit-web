let publicAddress = document.getElementById("publicAddress");

function check() {
	document.getElementById("checkerSpinner").hidden = false;

	let settings = {
		"async": true,
		"crossDomain": true,
		"url": "https://api.btckit.io/getBalance?publicAddress=" + publicAddress.value,
		"method": "GET",
		"headers": {
			"X-Requested-With": "XMLHttpRequest",
			"cache-control": "no-cache"
		}
	}
	$.ajax(settings).done(function (response) {
		changeValues(response.balance);
		document.getElementById("checkerSpinner").hidden = true;
	});
}

function changeValues(balance) {
	$("#balance").html(balance);
}

publicAddress.addEventListener("keyup", function(event) {
	if (event.keyCode === 13) {
		event.preventDefault();
		document.getElementById("checkButton").click();
	}
});
