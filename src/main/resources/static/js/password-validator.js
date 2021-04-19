let password = document.getElementById("password");
let repeatPassword = document.getElementById("repeatPassword");

function validatePassword() {

    if (password.value !== repeatPassword.value) {

        repeatPassword.setCustomValidity("Passwords Don't Match");
    }
    else {

        repeatPassword.setCustomValidity('');
    }
}

password.onchange = validatePassword;
repeatPassword.onkeyup = validatePassword;