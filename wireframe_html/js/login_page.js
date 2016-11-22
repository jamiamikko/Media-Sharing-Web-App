var login_button = document.getElementById("login_button");
var signup_button = document.getElementById("signup_button");
var login_form = document.getElementById("login");
var signup_form = document.getElementById("signup");

login_button.onclick = function(){
	if (signup_button.className != "clicked")
	{
		event.preventDefault();
	} 
	signup_button.className = "clicked";
	login_button.className = "";
	login_form.className = "show";
	signup_form.className = "";
}

signup_button.onclick = function(){
	if (login_button.className != "clicked")
	{
		event.preventDefault();
	}
	signup_button.className = "";
	login_button.className = "clicked";
	login_form.className = "";
	signup_form.className = "show";
}