function validEmail() {
    var form = document.getElementById("EmailForm");
    var formValue = form.value;
    var regex = /.+@.+\..+/
    if (formValue.match(regex)) {
        form.style.color = 'green';
    } else {
        form.style.color = 'red';
    }
}

function validUsername() {
    var form = document.getElementById("UsernameForm");
    var formValue = form.value;
    var regex = /.*[@#$%^&*()_+=?:№].*/
    if (formValue.match(regex)) {
        form.style.color = 'red';
    } else {
        form.style.color = 'green';
    }
}

function validPassword() {
    var passwordForm = document.getElementById("PasswordForm");
    var repeatPasswordForm = document.getElementById("RepeatPasswordForm");
    var password = passwordForm.value;
    var repeatPassword = repeatPasswordForm.value;
    if (password == repeatPassword) {
        passwordForm.style.color = 'green';
        repeatPasswordForm.style.color = 'green';
    } else {
        passwordForm.style.color = 'red';
        repeatPasswordForm.style.color = 'red';
    }
}