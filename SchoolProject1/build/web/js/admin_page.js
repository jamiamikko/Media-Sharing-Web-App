var loadPostData = function (json) {

    var postContainer = document.querySelector('#posts');

    for (k = 0; k < json.length; k++) {
        postContainer.innerHTML = '<h3>All posts</h3><form action="http://10.114.32.59:8080/SchoolProject1/webresources/generic/deleteImage" method="post"><input type="hidden" name="id" value="' + json[k].id + '"><p>ID: ' + json[k].id + '. URL: ' + json[k].url + '. Name: ' + json[k].name + '. Description: ' + json[k].description + '.<br/><button class="green-button margin-top">Delete</button></p></form>'
    }

}

var loadUserData = function (json) {

    var userContainer = document.querySelector('#users');


    for (i = 0; i < json.length; i++) {

        if (json[i].id == getId() && json[i].privilege == true) {

            for (j = 0; j < json.length; j++) {
                if (!json[j].privilege || json[j].privilege == false) {
                    userContainer.innerHTML = '<h3>All users</h3><form action="http://10.114.32.59:8080/SchoolProject1/webresources/generic/deleteUser" method="post"><input type="hidden" name="id" value="' + json[j].id + '"><p>ID: ' + json[j].id + '. Name: ' + json[j].userName + '.<br/><button class="green-button margin-top">Delete</button></p></form>'
                }
            }

            fetch('http://10.114.32.59:8080/SchoolProject1/webresources/generic/imageData').then(function (response) {
                var contentType = response.headers.get("content-type");

                if (contentType && contentType.indexOf("application/json") !== -1) {

                    return response.json().then(function (imageJson) {
                        loadPostData(imageJson);
                    });

                } else {
                    console.log("Oops, we haven't got JSON!");
                }

            });
        }
    }



}

function getData() {
    fetch('http://10.114.32.59:8080/SchoolProject1/webresources/generic/userData').then(function (response) {
        var contentType = response.headers.get("content-type");

        if (contentType && contentType.indexOf("application/json") !== -1) {

            return response.json().then(function (userJson) {

                loadUserData(userJson);
            });

        } else {
            console.log("Oops, we haven't got JSON!");
        }

    });

}

getData();