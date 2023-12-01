
const logout = document.querySelector('#logout')
logout.addEventListener("click", async () => {
    const xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                location.reload();
            } else {
                console.error("Logout request failed");
            }
        }
    };

    xhr.open("GET", "http://localhost:8080/demoProject_war/logout", true);
    xhr.send();
});
