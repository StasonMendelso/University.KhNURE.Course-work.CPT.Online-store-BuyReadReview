$(document).ready(function() {
    $("#addForm").submit(function(e){
        const input = $("input[name='publisherName']");
        const inputVal = input.val();
        const select = $("select[name='publisherName']");
        const selectVal = select.val();
        console.log(inputVal);
        console.log(selectVal);
        if(!inputVal){
            input.attr("disabled", "disabled");
        }else{
            select.attr("disabled", "disabled");
        }
    });
});



