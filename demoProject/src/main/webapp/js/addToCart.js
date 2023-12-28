// Trong addToCart.js
function addToCart(productId, type) {
    console.log(productId)
    console.log(type)
    $.ajax({
        url: `/add-cart?idProduct=${productId}&type=${type}`,
        type: 'POST',
        data: {id: productId, type},
        success: function (response) {
            alert(response.message);

            // Cập nhật giỏ hàng nếu cần

        },
        error: function () {
            alert('Sản phẩm không tồn tại để thêm vào giỏ!');
        }
    });
}
