$(function(){ $("#setting-wrapper").load("setting.html"); });

$(function() {
    $("#profile-img").on("change", function(){
        var files = !!this.files ? this.files : [];
        if (!files.length || !window.FileReader) return; 
        if (/^image/.test( files[0].type)){
            var reader = new FileReader();
            reader.readAsDataURL(files[0]); 
            reader.onloadend = function(){ 
            $('#myImage').css("background-image", "url("+this.result+")"); 
            };
        }
    });
});
