
$('.card').on('click','input:checkbox',function () {
    var id = $(this).attr("id");
    if(id!="mainCheckbox") {
        var allCheck = $("#mainCheckbox").prop('checked');
        if (allCheck == true) {
            $("#mainCheckbox").attr("checked", false);
        }
    }else{
        var allCheck = $("#mainCheckbox").prop('checked');
        var obj = document.getElementsByClassName("cb");
        var k;
        for(k in obj){
          obj[k].checked=allCheck;
         }
        }
});
