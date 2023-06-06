$(document).ready(function () {
    const csrfToken = $("meta[name='_csrf']").attr("content");
    const csrfHeader = $("meta[name='_csrf_header']").attr("content");


    $("._addItemToCartButton").click(function () {
        let bookId = $(this).parent().find("input[name='bookId']").val();
        console.log(bookId);
        let data = {"book_id":bookId}
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/buy-read-review/cart",
            data: JSON.stringify(data),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            beforeSend: function(xhr) {
                if(csrfHeader==null){
                    return;
                }
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (data) {
                updateCartNavbar(data);
            },
            error: function (e) {
                alert("Sorry, something went wrong");
            }
        });
    })

    function updateCartNavbar(cartData){
        console.log(cartData.quantity);
        console.log(cartData.total);
        $("#cartItemsCount").text(cartData.quantity);
        $("#cartTotal").text(cartData.total  + ' грн');
    }
});
