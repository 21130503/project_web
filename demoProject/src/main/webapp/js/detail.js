const imgInterface = document.querySelector('#image-interface')
const imgList = document.querySelectorAll('.img-item');
const array = Array.from(imgList)
console.log(imgInterface)
const arrySrc = array.map(item => {
    return item.src
})

array.forEach((item, index) => {
    item.addEventListener("mouseover", () => {
        imgInterface.src = arrySrc[index]
    })
})
// xử lí mua hàng
const minus = document.getElementById("minus");
const plus = document.getElementById("plus");
const totalInput = document.querySelector(".total-product");
minus.addEventListener("click", (e) => {
    e.preventDefault();
    let value = +totalInput.value;
    if (value == 1) {
        return;
    }
    totalInput.value = value - 1;
})
plus.addEventListener("click", (e) => {
    e.preventDefault()
    let value = +totalInput.value;
    totalInput.value = value + 1
})

//get address
async function getAddressWithAPI() {

    let res = await fetch("https://provinces.open-api.vn/api/?depth=3")
    let arrayAddress = await res.json()
//     get elemet
    const city = document.getElementById("nameCity")
    const district = document.getElementById("nameDistrict")
    const arrCity = arrayAddress.map((item, index) => {
        return `<option value="${item.name}">${item.name}</option>`
    })
    city.innerHTML = arrCity.join("")
    city.addEventListener("change", () => {
        getDistrict(arrayAddress[city.selectedIndex])
    })
}

function getDistrict(city) {
    const district = document.getElementById("nameDistrict");
    const arrayDistrict = city.districts.map((item, index) => {
        return `<option value="${item.name}">${item.name}</option>`
    })
    district.innerHTML = arrayDistrict.join('')
    district.addEventListener("change", ()=>{
        getCommune(city.districts[district.selectedIndex])
    })
}
function  getCommune(district){
    console.log(district)
    const commune = document.getElementById("nameCommune")
    const arrayCommuce = district.wards.map((item, index) => {
        return `<option value="${item.name}">${item.name}</option>`
    })
    commune.innerHTML = arrayCommuce.join('')

}

getAddressWithAPI()