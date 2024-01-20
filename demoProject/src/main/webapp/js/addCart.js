const btnCart = document.querySelectorAll(".addCart")
console.log(btnCart)
const arrBtn = Array.from(btnCart);
arrBtn.forEach((item)=>{
    console.log(item)
    const type = item.title;
    const id = item.value
    item.addEventListener("click",()=>{
        const xhr = new XMLHttpRequest();
        const url = `http://localhost:8080/demoProject_war/cart?type=${type}&idProduct=${id}`;

        xhr.open("POST", url, true);

        xhr.onload = function () {
            if (xhr.status === 200) {
                const data = JSON.parse(xhr.responseText);
                alert(data.message);
                location.reload();
            } else if (xhr.status === 500) {
                const data = JSON.parse(xhr.responseText);
                alert(data.message);
            }
        };

        xhr.send();
    })
})