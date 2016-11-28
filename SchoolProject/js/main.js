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
            output += '<section class="container"><article><figure><img src = "' + jsonObject[i].URL + '"alt = "' + jsonObject[i].UNAME + '"><figcaption> ' + jsonObject[i].Description + ' </figcaption> </figure> <section class = "comments"><p> <a class = "view-comment" onclick="menuToggle()"> View comments </a></p><div class = "old-comments hidden"><p> <span> {writer}: </span>Heheh</p><p> <span> {writer}: </span>Heheh</p><p> <span> {writer}: </span>Heheh</p></div> <form><div class = "new-comment"><textarea placeholder = "Write comment"> </textarea> <button type = "submit"class = "green-button"> Send </button> </div> </form> </section> </article> </section>';
        }

        document.querySelector("main").innerHTML = output;
    }

    main();
}


function getJson() {

    var xhr = new XMLHttpRequest();

    xhr.open("GET", "http://10.114.32.46:8080/SchoolProject/webresources/generic/getData", true);

    xhr.onreadystatechange = showImages;

    xhr.send();

}

getJson();