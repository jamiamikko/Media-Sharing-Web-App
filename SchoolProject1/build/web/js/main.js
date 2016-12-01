function menuToggle() {
    var div = document.getElementById("main-nav");

    var className = div.getAttribute("class");

    if (className == "main-nav") {
        div.className += " active";
    } else {
        div.className = "main-nav";
    }
}

var showComments = function (element) {

    if (element.target.getAttribute("class") == "view-comment") {


        var hiddenComments = element.path[2].querySelector(".old-comments");

        var className = hiddenComments.getAttribute("class");

        if (className == "old-comments hidden") {
            hiddenComments.className = 'old-comments';
            element.target.innerHTML = "Hide comments";
        } else {
            hiddenComments.className += ' hidden';
            element.target.innerHTML = "View comments";
        }
    }
}

function addClickEvents() {

    var comments = document.querySelectorAll(".comments");

    for (var i = 0; i < comments.length; i++) {
        comments[i].addEventListener("click", showComments);
        ;
    }
}

var loadComments = function () {
    if (this.readyState == 4 && this.status == 200) {
        var jsonObject = JSON.parse(this.responseText);
        var output = '';

        var articles = document.querySelectorAll('article');

        for (k = 0; k < articles.length; k++) {

            for (i = 0; i < jsonObject.length; i++) {

                if (jsonObject[i].id == articles[k].getAttribute('ID')) {
                    articles[k].querySelector('.old-comments').innerHTML = '<p> <span> ' + jsonObject[i].owner.userName + ': </span>' + jsonObject[i].content + '</p>';
                }
            }
        }
    }
}

var loadContent = function () {
    if (this.readyState == 4 && this.status == 200) {
        var jsonObject = JSON.parse(this.responseText);
        var output = '';

        for (i = 0; i < jsonObject.length; i++) {
            output += '<section class="container"><article id="' + jsonObject[i].id + '"><figure><img src = "' + jsonObject[i].url + '"alt = "' + jsonObject[i].name + '"><figcaption> ' + jsonObject[i].description + ' </figcaption> </figure> <section class = "comments"><p> <a class = "view-comment" onclick="showComments()"> View comments </a></p><div class = "old-comments hidden"><p>There are no comments for this post..</p></div> <form><div class = "new-comment"><textarea placeholder = "Write comment"> </textarea> <button type = "submit"class = "green-button"> Send </button> </div> </form> </section> </article> </section>';
        }

        document.querySelector(".main-content").innerHTML = output;
    }

    addClickEvents();
    var xhrComments = new XMLHttpRequest();
    xhrComments.open("GET", "http://10.114.32.59:8080/SchoolProject1/webresources/generic/commentData", true);
    xhrComments.onreadystatechange = loadComments;
    xhrComments.send();
}

function getId() {
    var name = "codeboxId=";

    var ca = document.cookie.split(';');

    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }

    return "";
}

function logOut() {
    var name = "codeboxId=";
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

var loadUsers = function () {
    if (this.readyState == 4 && this.status == 200) {
        var jsonObject = JSON.parse(this.responseText);
        var output = '';
        var id;

        if (document.cookie.indexOf("codeboxId") > -1) {
            id = getId();
        } else if (window.location.href.indexOf("id") > -1) {
            id = window.location.href.split("?")[1].replace("id=", "");
            document.cookie = 'codeboxId=' + id + '; expires=Thu, 18 Dec 2020 12:00:00 UTC';
        }

        if (id) {

            var aside = document.querySelector('aside');
            var profileWrapper = document.querySelector('.profile-wrapper');

            for (j = 0; j < jsonObject.length; j++) {

                if (jsonObject[j].id == id) {
                    if (aside) {
                        aside.innerHTML = '<object class="profile-img-size" data="img/profile-icon.svg" type="image/svg+xml"></object><h2>' + jsonObject[j].userName + '</h2>';
                    } else if (profileWrapper) {
                        profileWrapper.innerHTML = '<div class="centering-wrapper"><h1>PROFILE</h1><object class="profile-img-size" data="img/profile-icon.svg" type="image/svg+xml"></object><h2>' + jsonObject[j].userName + '</h2><br><button class="green-button" name="button">Profile settings</button><br><br><button class="green-button" name="button">Change password</button></div>'
                    }
                }
            }

            var loginButton = document.querySelector('.navi-item[href="login.html"]');

            loginButton.innerHTML = 'Logout';

            var href = loginButton.getAttribute('href');
            loginButton.href = 'index.html';

            loginButton.setAttribute("onclick", "logOut()");

        }
    }
}


function getJson() {

    var mainContent = document.querySelector('.main-content');

    if (mainContent) {

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "http://10.114.32.59:8080/SchoolProject1/webresources/generic/imageData", true);
        xhr.onreadystatechange = loadContent;
        xhr.send();

    }

    var xhrUsers = new XMLHttpRequest();
    xhrUsers.open("GET", "http://10.114.32.59:8080/SchoolProject1/webresources/generic/userData", true);
    xhrUsers.onreadystatechange = loadUsers;
    xhrUsers.send();

}

getJson();