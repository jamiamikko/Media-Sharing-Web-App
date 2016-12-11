//Fetch and handling function for imageData.

fetch('http://10.114.32.59:8080/SchoolProject1/webresources/generic/imageData').then(function(response) {
    var contentType = response.headers.get("content-type");

    if (contentType && contentType.indexOf("application/json") !== -1) {

        return response.json().then(function(json) {
            document.querySelector("#totalposts").innerHTML = json.length;
        });

    }

});

//Fetch and handling function for topComments.
fetch('http://10.114.32.59:8080/SchoolProject1/webresources/generic/topComments').then(function(response) {
    var contentType = response.headers.get("content-type");

    if (contentType && contentType.indexOf("application/json") !== -1) {

        return response.json().then(function(json) {
            document.querySelector('#pmostcomments').innerHTML = json[0].onContent.name;
        });

    }

});

//Fetch and handling function for topUser.
fetch('http://10.114.32.59:8080/SchoolProject1/webresources/generic/topUser').then(function(response) {
    var contentType = response.headers.get("content-type");

    if (contentType && contentType.indexOf("application/json") !== -1) {

        return response.json().then(function(json) {

            document.querySelector('#umostcomments').innerHTML = json[0].owner.userName;
        });

    }

});

//Fetch and handling function for userData.
fetch('http://10.114.32.59:8080/SchoolProject1/webresources/generic/userData').then(function(response) {
    var contentType = response.headers.get("content-type");

    if (contentType && contentType.indexOf("application/json") !== -1) {

        return response.json().then(function(json) {
            document.querySelector("#totalusers").innerHTML = json.length;
        });

    }

});