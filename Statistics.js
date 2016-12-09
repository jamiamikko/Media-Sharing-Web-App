var totpost = function(){

	 for (i = 0; i < json.length; i++) {
        //output += '<section class="container"><article id="' + json[i].owner + '"><figure><img src = "' + json[i].url + '"alt = "' + json[i].name + '"><figcaption> ' + json[i].description + ' </figcaption> </figure>';
		console.log(json[i]);
    }

}
fetch('http://10.114.32.59:8080/SchoolProject1/webresources/generic/imageData').then(function(response) {
            var contentType = response.headers.get("content-type");

            if (contentType && contentType.indexOf("application/json") !== -1) {

                return response.json().then(function(json) {
                    totpost(json);
                });

            } else {
                console.log("Oops, we haven't got JSON!");
            }

        });

		
fetch('http://10.114.32.59:8080/SchoolProject1/webresources/generic/commentData').then(function(response) {
            var contentType = response.headers.get("content-type");

            if (contentType && contentType.indexOf("application/json") !== -1) {

                return response.json().then(function(json) {
                    console.log(json);
                });

            } else {
                console.log("Oops, we haven't got JSON!");
            }

        });
		
fetch('http://10.114.32.59:8080/SchoolProject1/webresources/generic/userMedia').then(function(response) {
            var contentType = response.headers.get("content-type");

            if (contentType && contentType.indexOf("application/json") !== -1) {

                return response.json().then(function(json) {
                    console.log(json);
                });

            } else {
                console.log("Oops, we haven't got JSON!");
            }

        });