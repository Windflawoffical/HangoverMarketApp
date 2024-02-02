
const fileInput = document.getElementById("alc_img");
const preview = document.querySelector("img.preview");
const reader = new FileReader();

function loadImg(event) {
    if (event.type = "load") {
        preview.src = reader.result;
    }
}

function URLImg(e) {
    const selectedFile = fileInput.files[0];
    if (selectedFile) {
        reader.addEventListener("load", loadImg);
        reader.readAsDataURL(selectedFile);
    }
}

fileInput.addEventListener("change", URLImg)

function uploadForm() {

    let alc_name = document.getElementById("alc_name").value;
    let description = document.getElementById("alc_description").value;
    let price = document.getElementById("alc_price").value;
    let manufacturer = document.getElementById("alc_manufacturer").value;
    let type = document.getElementById("alc_type").value;
    let imgData = reader.result;
    

    if (alc_name == "" || description == "" || price == ""
    || manufacturer == "" || type == "") {
        alert("Проверьте правильность введённых данных!");
        return;
    }

    let alcohol = {
        name: alc_name,
        description: description,
        price: price,
        manufacturer: manufacturer,
        type: type,
        img: imgData
    }

    Promise.all([
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
                            }),
    // fetch("/alcohols/imgHandler"), {}

    ])

    console.log(alcohol);
   
}

