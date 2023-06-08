$(document).ready(function () {
    $("#addTagInputButton").click(function () {
        const siblings = $(this).siblings();
        let lastElement = $(this);
        if (siblings.length) {
            lastElement = siblings.last();
        }

        lastElement.after("<div class=\"d-flex d-row mt-1\">\n" +
            "                            <input class=\"form-control me-1\" type=\"text\" name=\"tagNames\"   id=\"flexCheckDefault\">\n" +
            "                            <button class=\"btn btn-danger\" type=\"button\" id='removeTagInputButton'>Видалити</button>\n" +
            "                        </div>");
        lastElement.next().children().last().click(removeTagInputButton);

    });
    $(".removeTagInputButton").click(removeTagInputButton);
    function removeTagInputButton(){
        $(this).parent().remove();
    }
});

