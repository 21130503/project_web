const imgInterface= document.querySelector('#image-interface')
const imgList = document.querySelectorAll('.img-item');
const array = Array.from(imgList)
console.log(imgInterface)
const arrySrc = array.map(item=>{
    return item.src
})

array.forEach((item,index)=>{
    item.addEventListener("mouseover", ()=>{
        imgInterface.src = arrySrc[index]
    })
})