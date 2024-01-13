// Mã js cho trang DiscountAdmin  để xóa mã giảm giá
function confirmDelete(code) {
    if (confirm('Bạn có chắc chắn muốn xóa mã giảm giá này không?')) {
        deleteDiscount(code);
    }
}

function deleteDiscount(code) {
    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", "discountAdmin?action=deleteDiscount&code=" + code, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            window.location.reload();
        }
    };
    xhr.send();
}