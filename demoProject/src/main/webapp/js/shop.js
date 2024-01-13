const albumToggle = document.querySelector("#toggle-album")
const oddToggle = document.querySelector("#toggle-odd")
albumToggle.addEventListener("click",()=>{
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/demoProject_war/shop",
        data: { type: albumToggle.title},
        success: function (data) {
            $("#listProduct").empty();
            // Xử lý phản hồi từ server và hiển thị tại trang
            $("#listProduct").html(data);
        },
        error: function (error) {
            console.error("Error:", error);
        }
    });
})
oddToggle.addEventListener("click",()=>{
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/demoProject_war/shop",
        data: { type: oddToggle.title},
        success: function (data) {
            console.log(data)
            $("#listProduct").empty();
            // Xử lý phản hồi từ server và hiển thị tại trang
            $("#listProduct").html(data);
        },
        error: function (error) {
            console.error("Error:", error);
        }
    });
})




