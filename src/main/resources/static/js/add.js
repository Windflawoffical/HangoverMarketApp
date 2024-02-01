function uploadForm() {
    let name = document.getElementById("alc_name").value;
    let description = document.getElementById("alc_description").value;
    let price = document.getElementById("alc_price").value;
    let manufacturer = document.getElementById("alc_manufacturer").value;
    let type = document.getElementById("alc_type").value;
    let image = document.getElementById("alc_img").files[0];
    
    if (name == "" || description == "" || price == ""
    || manufacturer == "" || type == "" || image==undefined) {
        alert("Проверьте правильность введённых данных!");
        return;
    }

    console.log(image.name);
    let alcohol = {
        name: name,
        description: description,
        price: price,
        manufacturer: manufacturer,
        type: type,
        img: name + '.' + image.name.split('.').pop()
    }

    fetch("/alcohols/add", {method: "POST",
                            body: JSON.stringify(alcohol),
                            headers: {"Content-type": "application/json; charset=UTF-8"}
                            }).then((response) => {
                                if(!response.ok) {
                                    alert("Что-то пошло не так!");
                                    throw new Error("Your response status code: " + response.status)
                                } else {
                                    alert("Товар добавлен в базу данных!");
                                    return response.json;
                                }
                            })

    console.log(alcohol);
    name = '';
    description = '';
    price = '';
    manufacturer = '';
    type = '';
    image = '';
}