function createCookie(cname, cvalue) {
    document.cookie = cname + "=" + cvalue + "; path=/memo";
}

function delCookie(cname) {
    let expireDate = new Date(Date.now() - 1);
    expireDate = expireDate.toUTCString();
    document.cookie = cname + "=; expires=" + expireDate + "; path=/memo";
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');

    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        console.log(c);
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }

    return "";
}