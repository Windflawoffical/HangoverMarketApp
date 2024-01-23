const form = document.querySelector('form');
const formSubmit = document.querySelector('button');

form.addEventListener('change', changeFormHandler);

function changeFormHandler() {
    var phoneNumber = document.getElementById("phone").value
    var password = document.getElementById("password").value;
    if (phoneNumber.length == 11 && password.length > 6) {
        formSubmit.removeAttribute('disabled');
    }
}