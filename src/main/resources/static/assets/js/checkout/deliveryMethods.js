$(document).ready(function () {
    $("#courier").change(function () {
        hideAll();
        $(this).parent().append("<div class=\"_courier\">\n" +
            "                                <label for=\"receiver-middle-name\" class=\"form-label\">Адрес доставки</label>\n" +
            "                                <input type=\"text\" class=\"form-control\"\n" +
            "                                       name=\"delivery.field1\">\n" +
            "                            </div>");
    })

    $("#self-pick").change(function () {
        hideAll();
        $(this).parent().append("<div class=\"_self-pick\">\n" +
            "                                <label for=\"receiver-middle-name\" class=\"form-label\">Адрес магазину</label>\n" +
            "                                <input type=\"text\" class=\"form-control\"\n" +
            "                                       name=\"delivery.field1\"\n" +
            "                                       value=\"м. Харків, Шевченківський район, просп. Науки, 14\">\n" +
            "                            </div>");
    })

    $("#nova-poshta").change(function () {
        hideAll();
        $(this).parent().append("<div class=\"_nova-poshta\">\n" +
            "                                <div>\n" +
            "                                    <label for=\"receiver-middle-name\" class=\"form-label\">Місто доставки</label>\n" +
            "                                    <input type=\"text\" class=\"form-control\"\n" +
            "                                           name=\"delivery.field1\">\n" +
            "                                </div>\n" +
            "                                <div>\n" +
            "                                    <label for=\"receiver-middle-name\" class=\"form-label\">Номер відділення</label>\n" +
            "                                    <input type=\"text\" class=\"form-control\"\n" +
            "                                           name=\"delivery.field2\">\n" +
            "                                </div>\n" +
            "                            </div>");
    })

    function hideAll() {
        $("._courier").remove();
        $("._self-pick").remove();
        $("._nova-poshta").remove();
    }
});