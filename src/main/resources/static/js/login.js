//function isEverythingOk() {
//
//    let login = document.getElementById("username").value
//    let my_password = document.getElementById("password").value;
//
//    let user = {
//        username: login,
//        password: my_password
//    };
//
//    fetch("/users/login", {
//        method: 'POST',
//        body: JSON.stringify(user),
//        headers: {
//            // Добавляем необходимые заголовки
//            'Content-type': 'application/json; charset=UTF-8',
//        },
//    }).then((response) => {
//      if(!response.ok) {
//          alert("Авторизация провалена, проверьте правильность введённых данных и повторите попытку позже!")
//          throw new Error("Your response status code: " + response.status);
//      } else {
//          alert("Авторизация прошла успешно!")
//          //window.location.href = "/";
//          return response.json();
//          }
//      });
//
//}

