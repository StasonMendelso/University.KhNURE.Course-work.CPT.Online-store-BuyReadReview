$(document).ready(function () {
    const csrfToken = $("meta[name='_csrf']").attr("content");
    const csrfHeader = $("meta[name='_csrf_header']").attr("content");
    const contextPath = $("meta[name='context-path']").attr("content");
    let previousStatus;

    $("#orderStatus").focus(function () {
        previousStatus = $(this).val();
        console.log(previousStatus);

    })
    $("#orderStatus").change(function () {
        let orderId = $("input[name='orderId']").val();
        let status = $(this).val();
        console.log(orderId);
        console.log(status);
        let data = {"orderId": orderId, "status":status}
        $.ajax({
            type: "PATCH",
            contentType: "application/json",
            url: contextPath + "/orders/status",
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
                previousStatus = status;
                $(".description-status").removeClass("_hidden");
                setTimeout(function () {
                    $(".description-status").addClass("_hidden");
                },2000);
            },
            error: function (e) {
                console.log(previousStatus);
                $("#orderStatus").find('[value="'+previousStatus+'"]').attr('selected', 'selected');
                alert("Sorry, something went wrong");
            }
        });
    })

});
