function validar(){             
    var nick = document.forms["login"]["nick"].value;
    var pdw = document.forms["login"]["password"].value;
    if(nick == "" || pdw =="" ){
        alert("Por favor, rellene todos los campos");
        return false;
    }  
    return true;          
}