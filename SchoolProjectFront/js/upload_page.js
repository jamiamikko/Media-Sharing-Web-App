var idInput = document.querySelector('#userId');

idInput.value = getId();

var fileInput = document.querySelector('input[type="file"]');
var preview = document.querySelector('#imagePreview');

var previewImage = function() {
    var reader = new FileReader();
    reader.addEventListener('load', function(evt){
       preview.className = '';
       preview.src = this.result; 
    });
    
    var file = this.files[0];
    reader.readAsDataURL(file);
}

fileInput.addEventListener('change', previewImage);