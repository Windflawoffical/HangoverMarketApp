fetch("/alcohols/get_all", {
    method: 'GET',
    headers: {
        // Добавляем необходимые заголовки
        'Content-type': 'application/json; charset=UTF-8',
    },
}).then((response) => {
    if(!response.ok) {
        throw new Error("Your response status code: " + response.status);
    } else {
        return response.json();
        }
    });