function menuToggle() {
    var div = document.getElementById("main-nav");

    var className = div.getAttribute("class");

    if (className == "main-nav") {
        div.className += " active";
    } else {
        div.className = "main-nav";
    }
}


var showComments = function(element) {


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

function main() {

    var comments = document.querySelectorAll(".comments");

    for (var i = 0; i < comments.length; i++) {
        comments[i].addEventListener("click", showComments);;
    }
}


var showImages = function() {
    if (this.readyState == 4 && this.status == 200) {
        var jsonObject = JSON.parse(this.responseText);
        var output = '';

        for (i = 0; i < jsonObject.length; i++) {
            output += '<section class="container"><article id="' + jsonObject[i].ID + '"><figure><img src = "' + jsonObject[i].URL + '"alt = "' + jsonObject[i].UNAME + '"><figcaption> ' + jsonObject[i].Description + ' </figcaption> </figure> <section class = "comments"><p> <a class = "view-comment" onclick="showComments()"> View comments </a></p><div class = "old-comments hidden"><p> <span> {writer}: </span>Heheh</p><p> <span> {writer}: </span>Heheh</p><p> <span> {writer}: </span>Heheh</p></div> <form><div class = "new-comment"><textarea placeholder = "Write comment"> </textarea> <button type = "submit"class = "green-button"> Send </button> </div> </form> </section> </article> </section>';
        }

        document.querySelector(".main-content").innerHTML = output;
    }

    main();
}

var loadComments = function() {
    if (this.readyState == 4 && this.status == 200) {
        var jsonObject = JSON.parse(this.responseText);
        var output = '';

        var articles = document.querySelectorAll('article');

        for (k = 0; k < articles.length; k++) {

            for (i = 0; i < jsonObject.length; i++) {

                if (jsonObject[i].id == articles[k].getAttribute('ID')) {

                    articles[k].querySelector('.old-comments').innerHTML = '<p> <span> ' + jsonObject[i].owner +': </span>' + jsonObject[i].content + '</p>';
                }
            }
        }
    }
}


function getJson() {

    var xhr = new XMLHttpRequest();
    var xhrComments = new XMLHttpRequest();

    xhr.open("GET", "json/Img.json", true);
    xhrComments.open("GET", "json/Feedback.json", true);

    xhr.onreadystatechange = showImages;
    xhrComments.onreadystatechange = loadComments;

    xhr.send();
    xhrComments.send();

}

getJson();