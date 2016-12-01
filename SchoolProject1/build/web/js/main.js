function menuToggle() {
    var div = document.getElementById("main-nav");

    var className = div.getAttribute("class");

    if (className === "main-nav") {
        div.className += " active";
    } else {
        div.className = "main-nav";
    }
}


var showComments = function (element) {


    if (element.target.getAttribute("class") === "view-comment") {


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

function main() {

    var comments = document.querySelectorAll(".comments");

    for (var i = 0; i < comments.length; i++) {
        comments[i].addEventListener("click", showComments);
        ;
    }
}


var showImages = function () {
    if (this.readyState === 4 && this.status === 200) {
        var jsonObject = JSON.parse(this.responseText);
        var output = '';

        for (i = 0; i < jsonObject.length; i++) {
            output += '<section class="container"><article id="' + jsonObject[i].id + '"><figure><img src = "' + jsonObject[i].url + '"alt = "' + jsonObject[i].name + '"><figcaption> ' + jsonObject[i].description + ' </figcaption> </figure> <section class = "comments"><p> <a class = "view-comment" onclick="showComments()"> View comments </a></p><div class = "old-comments hidden"><p>There are no comments for this post..</p></div> <form action="Commentor"><div class = "new-comment"><textarea placeholder = "Write comment"> </textarea> <button type = "submit"class = "green-button"> Send </button> </div> </form> </section> </article> </section>';
        }

        document.querySelector(".main-content").innerHTML = output;
    }

    main();
};

var loadComments = function () {
    if (this.readyState === 4 && this.status === 200) {
        var jsonObject = JSON.parse(this.responseText);
        var output = '';

        var articles = document.querySelectorAll('article');

        for (k = 0; k < articles.length; k++) {

            for (i = 0; i < jsonObject.length; i++) {

                if (jsonObject[i].id === articles[k].getAttribute('ID')) {

                    articles[k].querySelector('.old-comments').innerHTML = '<p> <span> ' + jsonObject[i].owner + ': </span>' + jsonObject[i].content + '</p>';
                }
            }
        }
    }
};

var loadUsers = function () {
    if (this.readyState === 4 && this.status === 200) {
        var jsonObject = JSON.parse(this.responseText);
        var output = '';
        var id = window.location.href.split("?")[1].replace("id=", "");
         
        for (j = 0; j < jsonObject.length; j++) {
            
            if (jsonObject[j].id === id) {
                console.log(jsonObject[j].userName);
                document.querySelector('aside').innerHTML = '<object class="profile-img-size" data="img/profile-icon.svg" type="image/svg+xml"></object><h2>' + jsonObject[j].userName +'</h2>';
                
            }
         }
        
    }
};


function getJson() {

    var xhr = new XMLHttpRequest();
    var xhrComments = new XMLHttpRequest();
    var xhrUsers = new XMLHttpRequest();


    xhr.open("GET", "http://10.114.32.59:8080/SchoolProject1/webresources/generic/imageData", true);
    xhrComments.open("GET", "http://10.114.32.59:8080/SchoolProject1/webresources/generic/commentData", true);
    xhrUsers.open("GET", "http://10.114.32.59:8080/SchoolProject1/webresources/generic/userData", true);

    xhr.onreadystatechange = showImages;
    xhrComments.onreadystatechange = loadComments;
    xhrUsers.onreadystatechange = loadUsers;

    xhr.send();
    xhrComments.send();
    xhrUsers.send();
}

getJson();