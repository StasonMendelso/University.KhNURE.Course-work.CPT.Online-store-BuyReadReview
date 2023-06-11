let csrfToken
let csrfHeader
let contextPath

$(document).ready(function () {
    csrfToken = $("meta[name='_csrf']").attr("content");
    csrfHeader = $("meta[name='_csrf_header']").attr("content");
    contextPath = $("meta[name='context-path']").attr("content");

    bindEventHandlers();
});
function bindEventHandlers() {


    $("._removeItemFromCartButton").click(function () {
        $(".cart").addClass("_sending");
        let bookId = $(this).parent().find("input[name='bookId']").val();
        console.log(bookId);
        let data = {"book_id": bookId}
        $.ajax({
            type: "DELETE",
            contentType: "application/json",
            url: contextPath + "/cart",
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
                    updateCartNavbar(data);
                    updateCart(data);
                    $(".cart").removeClass("_sending");
                }, 1000);

            },
            error: function (e) {
                $(".main").removeClass("_sending");
                alert("Sorry, something went wrong");
            }
        });
    })



    $(".incrementItemQuantityButton").click(function () {
        let bookId = $(this).parent().find("input[name='bookId']").val();
        let inputQuantity = $(this).parent().find("input[name='item-quantity']");
        let quantity = parseInt(inputQuantity.val());
        inputQuantity.val(quantity + 1);

        updateItemQuantity(inputQuantity, quantity + 1);
    });
    $(".decrementItemQuantityButton").click(function () {
        let bookId = $(this).parent().find("input[name='bookId']").val();
        let inputQuantity = $(this).parent().find("input[name='item-quantity']");
        let quantity = parseInt(inputQuantity.val());
        if (invalidateItemQuantity(quantity-1)) {
            return;
        }
        inputQuantity.val(quantity - 1);
        updateItemQuantity(inputQuantity, quantity - 1);
    });
    $("input[name='item-quantity']").change(function () {
        console.log("value changed:"+$(this).val());
        console.log("value changed:"+parseInt($(this).val()));
        if (invalidateItemQuantity(parseInt($(this).val()))) {
           return;
        }
        updateItemQuantity($(this), $(this).val());
    });

    function invalidateItemQuantity(value) {
        return value < 1;
    }

    function updateItemQuantity(input, value) {
        console.log("send ajax");
        let bookId = input.parent().find("input[name='bookId']").val();
        let data = {"book_id": bookId, "quantity": value};
        $(".cart").addClass("_sending");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: contextPath + "/cart",
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
                    updateCartNavbar(data);
                    updateCart(data);
                    $(".cart").removeClass("_sending");
                }, 1000);

            },
            error: function (e) {
                $(".main").removeClass("_sending");
                alert("Sorry, something went wrong");
            }
        });
    }
}
function updateCartNavbar(cartData) {
    $("#cartItemsCount").text(cartData.quantity);
    $("#cartTotal").text(cartData.total + ' грн');
    var decimalString = cartData.total; // Assuming the server response contains the decimal value in a property called "decimalValue"
    var decimalNumber = parseFloat(decimalString);
}

function updateCart(cartData) {
    if (cartData.quantity == 0) {
        $(".cart").remove();
        $(".content").append("Кошик порожній, перейдіть до каталогу, щоб додати товари");
        return;
    }
    let cartItemList = cartData.cartItems;
    updateCartItems(cartItemList);
    updateCheckoutForm(cartData);
    bindEventHandlers();
}

function updateCartItems(cartItemList) {
    console.log(cartItemList);
    $(".cart-tbody").children().remove();
    for (const cartItem of cartItemList) {
        $(".cart-tbody").append(
            "<tr class=\"align-middle cart-item-row\">\n" +
            "                                    <td class=\"align-middle text-center\">\n" +
            "                                        <input hidden=\"hidden\" name=\"bookId\" value=" + cartItem.bookId + ">\n" +
            "                                        <button type=\"button\" class=\"btn-close _removeItemFromCartButton\" aria-label=\"Close\"></button>\n" +
            "                                    </td>\n" +
            "                                    <td class=\"cart-item-picture\">\n" +
            "                                        <img src=\"" + contextPath + "/file/book/" + cartItem.fileId + "\"\n" +
            "                                             class=\"card-img-top\" alt=\"item-photo\"/>\n" +
            "                                    </td>\n" +
            "                                    <td class=\"text-start\">\n" +
            "                                        <a href=\"" + contextPath + "/books/" + cartItem.bookId + "\"\n" +
            "                                           class=\"text-decoration-none\">" + cartItem.name + "</a>\n" +
            "                                    </td>\n" +
            "                                    <td class=\"text-start\">" + cartItem.article + "</td>\n" +
            "                                    <td class=\"text-start\">" + cartItem.price + " грн</td>\n" +
            "                                    <td class=\"text-center\">\n" +
            "                                        <div class=\"d-flex flex-row justify-content-center\">\n" +
            "                                            <input hidden=\"hidden\" name=\"bookId\" value=" + cartItem.bookId + ">\n" +
            "                                            <button class=\"btn btn-outline-secondary decrementItemQuantityButton\">-</button>\n" +
            "                                            <input class=\"form-control cart-item-quantity-input text-center\" type=\"number\" min=\"1\" name=\"item-quantity\" value=\"" + cartItem.quantity + "\">\n" +
            "                                            <button class=\"btn btn-outline-secondary incrementItemQuantityButton\">+</button>\n" +
            "                                        </div>\n" +
            "\n" +
            "                                    </td>\n" +
            "                                    <td class=\"text-end text-success fw-bolder\">" + cartItem.subtotal + " грн</td>\n" +
            "                                </tr>"
        );
    }
}

function updateCheckoutForm(cartData) {
    $(".cart-total-sum").text(cartData.total + " грн");
}
