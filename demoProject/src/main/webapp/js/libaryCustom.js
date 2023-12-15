// Tạo ảnh khi change input
const $ = document.querySelector.bind(document)

function  changeImage(idInput, imgTag){
    const input = $(idInput)
    const img = $(imgTag)
    input.addEventListener("change",(e)=>{
        const file = e.target.files[0];
        const url = URL.createObjectURL(file)
        img.src= url;
    })
}