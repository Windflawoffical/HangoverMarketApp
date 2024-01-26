const modal = document.querySelector('dialog')
const modalBox = document.getElementById('modal-box')
const showModalBtn = document.getElementById('show-modal-btn')
const closeModalBtn = document.getElementById('close-modal-btn')
const noModalBtn = document.getElementById('no-btn')
// const window_opened = document.onload()
let isModalOpen = false

// showModalBtn.addEventListener('click', (e) => {
//   modal.showModal()
//   isModalOpen = true
//   e.stopPropagation()
// })

closeModalBtn.onmouseover = modalBox.onmouseout = handlerG;
noModalBtn.onmouseover = noModalBtn.onmouseout = handlerR;  

function handlerG(e){
    if (e.type == 'mouseover') {
        modalBox.style.backgroundColor = "#00ff48";
        }
        if (e.type == 'mouseout') {
        modalBox.style.backgroundColor ="#00eeff";
        }
}

function handlerR(e){
    if (e.type == 'mouseover') {
        modalBox.style.backgroundColor = "#ff004c";
        }
        if (e.type == 'mouseout') {
        modalBox.style.backgroundColor ="#00eeff";
        }
}


document.addEventListener("DOMContentLoaded", (e) => {
    modal.showModal();
    // e.stopPropagation();
    isModalOpen = true;
  });

closeModalBtn.addEventListener('click', () => {
  modal.close()
  isModalOpen = false
})

document.addEventListener('click', (e) => {
  if (isModalOpen && !modalBox.contains(e.target)) {
    modal.close()
  }
})