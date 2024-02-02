const button = document.querySelector('button');

function isEverythingOk() {

    let phone_number = document.getElementById("phone").value
    let my_password = document.getElementById("pass").value;
    let my_repass = document.getElementById("repass").value;
    let first_name = document.getElementById("first_name").value;
    let second_name = document.getElementById("second_name").value;

    let user = {
        phoneNumber: phone_number,
        password: my_password,
        firstName: first_name,
        secondName: second_name
    };

    if(phone_number.length != 11 || (my_password != my_repass || my_password.length < 6)) {
        alert("Проверьте правильность введённых данных!")
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
                  throw new Error("Your response status code: " + response.status);
              } else {
                  alert("Регистрация прошла успешно!")
                  window.location.href = "/users/login";
                  return response.json();
              }
            });

    }
}
