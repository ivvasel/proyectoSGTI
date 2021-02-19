function validar(){             
    var nick = document.forms["registro"]["nick"].value;
    var nombre = document.forms["registro"]["nombre"].value;
    var email = document.forms["registro"]["email"].value;
    var pdw1 = document.forms["registro"]["pdw1"].value;
    var pdw2 = document.forms["registro"]["pdw2"].value;
    var validacion = true;
    if(nick == "" || nombre =="" || email =="" || pdw1 =="" || pdw2 ==""){
        alert("Por favor, rellene todos los campos");
        validacion = false;
    }
    
    if (pdw1 !== pdw2 ) {             
        alert("Los campos de contraseñas deben coincidir");             
        validacion = false;             
    }   
    return validacion;          
}