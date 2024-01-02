$(document).ready(function () {
    // Lắng nghe sự kiện thay đổi trên input file
    $(document).on('change', '.upload-img', function () {
        // Kiểm tra xem có file được chọn hay không
        if (this.files.length > 0) {
            // Sao chép input hiện tại và thêm vào container
            var newInput = $(this).clone();

            $('#dynamic-input-container').append(newInput);

            // Reset giá trị của input mới để không ảnh hưởng đến input trước đó
            newInput.val('');

            // Thêm sự kiện thay đổi cho input mới (nếu cần)
            newInput.on('change', function () {
                // Xử lý sự kiện khi có thay đổi trên input mới
            });
        }
    });
});