function menuToggle() {
    var div = document.getElementById('main-nav');

    var className = div.getAttribute('class');

    if (className == 'main-nav') {
        div.className += ' active';
    } else {
        div.className = 'main-nav';
    }
}

function activateLogOut() {
    var loginButton = document.querySelector('.navi-item[href="login.html"]');

    loginButton.innerHTML = 'Logout';

    var href = loginButton.getAttribute('href');
    loginButton.href = 'index.html';

    loginButton.setAttribute('onclick', 'logOut()');
}

function logOut() {
    var cookieName = 'codeboxId=';
    document.cookie = cookieName + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

function getId() {
    var cookieName = 'codeboxId=';
    var ca = document.cookie.split(';');

    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(cookieName) == 0) {
            return c.substring(cookieName.length, c.length);
        }
    }

    return "";
}

var showComments = function(element) {
    if (element && element.target.getAttribute('class') == 'view-comment') {

        var hiddenComments = element.path[2].querySelector('.old-comments');

        var className = hiddenComments.getAttribute('class');

        if (className == 'old-comments hidden') {
            hiddenComments.className = 'old-comments';
            element.target.innerHTML = 'Hide comments';
        } else {
            hiddenComments.className += ' hidden';
            element.target.innerHTML = 'View comments';
        }
    }
}

function addClickEvents() {

    var comments = document.querySelectorAll('.comments');

    for (var i = 0; i < comments.length; i++) {
        comments[i].addEventListener('click', showComments);
    }
}

var loadComments = function(json) {

    var output = '';

    var articles = document.querySelectorAll('article');

    for (k = 0; k < articles.length; k++) {

        for (i = 0; i < json.length; i++) {

            if (json[i].id == articles[k].getAttribute('ID')) {
                articles[k].querySelector('.old-comments').innerHTML = '<p> <span> ' + json[i].owner.userName + ': </span>' + json[i].content + '</p>';
            }
        }
    }

}

var loadContent = function(json) {

    var output = '';
    var id;

    if (document.cookie.indexOf('codeboxId') > -1) {
        id = getId();
    } else if (window.location.href.indexOf('id') > -1) {
        id = window.location.href.split('?')[1].replace('id=', '');
        document.cookie = 'codeboxId=' + id + '; expires=Thu, 18 Dec 2020 12:00:00 UTC';
    }

    for (i = 0; i < json.length; i++) {
        output += '<section class="container"><article id="' + json[i].id + '"><figure><img src = "' + json[i].url + '"alt = "' + json[i].name + '"><figcaption> ' + json[i].description + ' </figcaption> </figure> <section class = "comments"><p> <a class = "view-comment" onclick="showComments()"> View comments </a></p><div class = "old-comments hidden"><p>There are no comments for this post..</p></div> <form> <div class = "new-comment"><input type="hidden" name="userId" value="' + id +'"><input type="hidden" name="postId" value="' + json[i].id +'"><textarea placeholder = "Write comment"> </textarea> <button type = "submit"class = "green-button"> Send </button> </div> </form> </section> </article> </section>';
    }

    document.querySelector('.main-content').innerHTML = output;


    addClickEvents();

    fetch('http://10.114.32.59:8080/SchoolProject1/webresources/generic/commentData').then(function(response) {
        var contentType = response.headers.get("content-type");

        if (contentType && contentType.indexOf("application/json") !== -1) {

            return response.json().then(function(newJson) {
                loadComments(newJson);
            });

        } else {
            console.log("Oops, we haven't got JSON!");
        }

    });
}

var loadUsers = function(json) {
    var output = '';
    var id;

    if (document.cookie.indexOf('codeboxId') > -1) {
        id = getId();
    } else if (window.location.href.indexOf('id') > -1) {
        id = window.location.href.split('?')[1].replace('id=', '');
        document.cookie = 'codeboxId=' + id + '; expires=Thu, 18 Dec 2020 12:00:00 UTC';
    }

    if (id) {

        var aside = document.querySelector('aside');
        var profileWrapper = document.querySelector('.profile-wrapper');

        for (j = 0; j < json.length; j++) {

            if (json[j].id == id) {
                if (aside) {
                    aside.innerHTML = '<object class="profile-img-size" data="img/profile-icon.svg" type="image/svg+xml"></object><h2>' + json[j].userName + '</h2>';
                } else if (profileWrapper) {
                    profileWrapper.innerHTML = '<div class="centering-wrapper"><h1>PROFILE</h1><object class="profile-img-size" data="img/profile-icon.svg" type="image/svg+xml"></object><h2>' + json[j].userName + '</h2><br><button class="green-button" name="button">Profile settings</button><br><br><button class="green-button" name="button">Change password</button></div>'
                }
            }
        }

        activateLogOut();

    }
}



function getJson() {

    var mainContent = document.querySelector('.main-content')

    if (mainContent) {

        fetch('http://10.114.32.59:8080/SchoolProject1/webresources/generic/imageData').then(function(response) {
            var contentType = response.headers.get("content-type");

            if (contentType && contentType.indexOf("application/json") !== -1) {

                return response.json().then(function(json) {
                    loadContent(json);
                });

            } else {
                console.log("Oops, we haven't got JSON!");
            }

        });

    }


    fetch('http://10.114.32.59:8080/SchoolProject1/webresources/generic/userData').then(function(response) {
        var contentType = response.headers.get("content-type");

        if (contentType && contentType.indexOf("application/json") !== -1) {

            return response.json().then(function(userJson) {
                loadUsers(userJson);
            });

        } else {
            console.log("Oops, we haven't got JSON!");
        }

    });

}
getJson();