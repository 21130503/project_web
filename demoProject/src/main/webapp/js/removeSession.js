function removeSession(where){
    const  btnRemove = document.querySelectorAll(".btnRemove");
    const arrBtn = Array.from(btnRemove);
    arrBtn.forEach((item)=>{
        const type = item.title;
        const id = item.value;
        item.addEventListener("click",()=>{
            const xhr = new XMLHttpRequest();
            const url = `http://localhost:8080/demoProject_war/${where}?type=${type}&idProduct=${id}`;
            xhr.open("PUT", url, true);

            xhr.onload = function () {
                if (xhr.status === 200) {
                    const data = JSON.parse(xhr.responseText);
                    alert(data.message);
                    location.reload();
                } else if (xhr.status === 500) {
                    const data = JSON.parse(xhr.responseText);
                    alert(data.message);
                    // window.location.href="http://localhost:8080/demoProject_war/favourite"
                }
            };

            xhr.send();
        })
    })
}