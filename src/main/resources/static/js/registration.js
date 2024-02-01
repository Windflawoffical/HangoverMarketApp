const form = document.getElementById("main-form");
const formSubmit = document.querySelector('button');

form.addEventListener('change', changeFormHandler);

function changeFormHandler() {
    if (form.checkValidity()) {
        formSubmit.removeAttribute('disabled');
    }
}

function isEverythingOk() {

    let phone_number = document.getElementById("phone").value
    let my_password = document.getElementById("password").value;
    let my_repass = document.getElementById("repass").value;
    let first_name = document.getElementById("first name").value;
    let second_name = document.getElementById("second name").value;

    let user = {
        phoneNumber: phone_number,
        password: my_password,
        firstName: first_name,
        secondName: second_name
    };

    if(phone_number.length != 11 || (my_password != my_repass || my_password.length < 6)) {
        alert("Проверьте правильность введённых данных!")
        formSubmit.setAttribute('disabled', '');
    } else {
        fetch("/users/registration", {
            method: 'POST',
            body: JSON.stringify(user),
            headers: {
                // Добавляем необходимые заголовки
                'Content-type': 'application/json; charset=UTF-8',
            },
        }).then((response) => {
              if(!response.ok) {
                  alert("Пользователь с таким телефонным номером уже существует!")
                  formSubmit.setAttribute('disabled', '');
                  throw new Error("Your response status code: " + response.status);
              } else {
                  alert("Регистрация прошла успешно!")
                  window.location.href = "/users/login";
                  return response.json();
              }
            });

    }
}
