$(document).ready(function () {
    const csrfToken = $("meta[name='_csrf']").attr("content");
    const csrfHeader = $("meta[name='_csrf_header']").attr("content");
    const contextPath = $("meta[name='context-path']").attr("content");

    $("#update-order-description").click(function () {
        $(this).addClass("disabled");
        let orderId = $("input[name='orderId']").val();
        let description = $("#order-description").val();
        console.log(orderId);
        console.log(description);
        let data = {"orderId": orderId, "description": description}
        $.ajax({
            type: "PATCH",
            contentType: "application/json",
            url: contextPath + "/orders/description",
            data: JSON.stringify(data),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            beforeSend: function (xhr) {
                if (csrfHeader == null) {
                    return;
                }
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (data) {
                setTimeout(function () {
                    $("#update-order-description").removeClass("disabled");
                }, 2000);
                $("#order-description").text(data.description);
                alert("Статус опису замовлення оновлено!");
            },
            error: function (e) {
                alert("Sorry, something went wrong");
            }
        });
    })

});
