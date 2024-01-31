function getFile() {
    return document.getElementById("alc_picture").files[0];
};

function uploadForm() {
    let name = document.getElementById("alc_name");
    let description = document.getElementById("alc_description");
    let price = document.getElementById("alc_price");
    let manufacturer = document.getElementById("alc_manufacturer");
    let picture = getFile();
    console.log(picture);

    let alcohol = {
        
    }
}