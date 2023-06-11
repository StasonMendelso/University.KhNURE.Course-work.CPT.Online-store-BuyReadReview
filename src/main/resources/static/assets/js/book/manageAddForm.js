$(document).ready(function () {
    $("#addAuthorInputButton").click(function () {
        const siblings = $(this).siblings();
        let lastElement = $(this);
        if (siblings.length) {
            lastElement = siblings.last();
        }

        lastElement.after("<div class=\"d-flex d-row mt-1\">\n" +
            "                            <input class=\"form-control me-1\" type=\"text\" name=\"authorNames\"   id=\"flexCheckDefault\">\n" +
            "                            <button class=\"btn btn-danger\" type=\"button\" id='removeAuthorInputButton'>Видалити</button>\n" +
            "                        </div>");
        lastElement.next().children().last().click(removeAuthorInputButton);

    });
    $(".removeAuthorInputButton").click(removeAuthorInputButton);
    function removeAuthorInputButton(){
        $(this).parent().remove();
    }

    $("#addGenreInputButton").click(function () {
        const siblings = $(this).siblings();
        let lastElement = $(this);
        if (siblings.length) {
            lastElement = siblings.last();
        }

        lastElement.after("<div class=\"d-flex d-row mt-1\">\n" +
            "                            <input class=\"form-control me-1\" type=\"text\" name=\"genreNames\"   id=\"flexCheckDefault\">\n" +
            "                            <button class=\"btn btn-danger\" type=\"button\" id='removeGenreInputButton'>Видалити</button>\n" +
            "                        </div>");
        lastElement.next().children().last().click(removeGenreInputButton);

    });
    $(".removeGenreInputButton").click(removeGenreInputButton);
    function removeGenreInputButton(){
        $(this).parent().remove();
    }
    $("#addFileInputButton").click(function () {
        const siblings = $(this).siblings();
        let lastElement = $(this);
        if (siblings.length) {
            lastElement = siblings.last();
        }

        lastElement.after("  <div class=\"d-flex d-row mt-1\">\n" +
            "                            <input class=\"form-control me-1\" type=\"file\" name=\"multipartFiles\">\n" +
            "                            <button class=\"btn btn-danger\" type=\"button\" id=\"removeFileInputButton\">Видалити</button>\n" +
            "                        </div>");
        lastElement.next().children().last().click(removeFileInputButton);

    });
    $(".removeFileInputButton").click(removeFileInputButton);
    function removeFileInputButton(){
        $(this).parent().remove();
    }
});

