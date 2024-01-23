const form = document.querySelector('form');
const formSubmit = document.querySelector('button');

form.addEventListener('change', changeFormHandler);

function changeFormHandler() {
    if (form.checkValidity()) {
        formSubmit.removeAttribute('disabled');
    }
}

function isCorrectForm() {
    var phoneNumber = document.getElementById("phone").value
    var password = document.getElementById("password").value;
    var repass = document.getElementById("repass").value;
    if(phoneNumber.length != 11) {
        alert("Проверьте правильность введённого номера телефона! (начиная с 8)")
        formSubmit.setAttribute('disabled', '');
    }
    if(password != repass || password.length < 6) {
        alert("Пароль не совпадают или же ваш пароль слишком мал..)")
        formSubmit.setAttribute('disabled', '');
    }
}
