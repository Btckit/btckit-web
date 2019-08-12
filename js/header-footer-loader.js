let thisScript = document.currentScript;

$(function () {
	$("#header").load("header.html");
	$("#footer").load("footer.html");
});

window.addEventListener("load", function () {
	let tabId = thisScript.getAttribute("tab");
	let tab = document.getElementById(tabId);
	tab.className += " active";
});