
//Get reference to the buttons and forms
var login_button = document.getElementById("login_button"); 
var signup_button = document.getElementById("signup_button");
var login_form = document.getElementById("login");
var signup_form = document.getElementById("signup");

//when a buton is clicked, the coresponding form will show, if the form already show, the button do nothing
login_button.onclick = function() {
    if (signup_button.className != "red-button clicked") {
        event.preventDefault();
    }
    signup_button.className = "red-button clicked";
    login_button.className = "red-button";
    login_form.className = "show";
    signup_form.className = "";
}

signup_button.onclick = function() {
    if (login_button.className != "red-button clicked") {
        event.preventDefault();
    }
    signup_button.className = "red-button";
    login_button.className = "red-button clicked";
    login_form.className = "";
    signup_form.className = "show";
}
