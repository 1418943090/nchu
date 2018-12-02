
$('.card').on('click','input:checkbox,review',function () {
    var id = $(this).attr("id");
    if(id!="reviewCheckbox") {
        var allCheck = $("#reviewCheckbox").prop('checked');
        if (allCheck == true) {
            $("#reviewCheckbox").attr("checked", false);
        }
    }else{
        var allCheck = $("#reviewCheckbox").prop('checked');
        var obj = document.getElementsByClassName("re");
        var k;
        for(k in obj){
            obj[k].checked=allCheck;
        }
    }
});

function del(list){


    var review_id=[];
    var obj = document.getElementsByClassName("re");
    var k,n;
    for (k in obj) {
        if (obj[k].checked && obj[k].id != undefined) {
            for(n in list){
                if(list[n].id == obj[k].id){
                    review_id.push(list[n]);
                }
            }
        }
    }
    if(review_id.length==0){
        swal({
            title: "你还没有选择要删除的请求哦!",
            text: "",
            icon: "warning",
            button: "确定",
        });
    }else{
        //toastr.warning("温馨提示:删除操作只是删除记录,并不会对之前的处理结果产生影响.");
        swal({
            title: "温馨提示",
            text: "删除操作只是删除记录,并不会对之前的处理结果产生影响!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    submitDeal(review_id,"/review/del");
                }
            });
    }
}

function fa(list,type){

    var obj = document.getElementsByClassName("re");
    var num = 0;
    var k;
    var id;
    for (k in obj) {
        if (obj[k].checked && obj[k].id != undefined) {
            num++;
            id = obj[k].id;
        }
    }
    if (num == 0) {
        swal({
            title: "你还没有选择要处理的请求哦!",
            text: "",
            icon: "warning",
            button: "确定",
        });
    }
    else if (num > 1) {
        swal({
            title: "一次只能处理一个请求哦!",
            text: "",
            icon: "warning",
            button: "确定",
        });
    }else{
    var n = 0;
    var date;
     for(n in list){
        if(list[n].id==id){
          date = list[n];
           break;
        }
     }
   if(date.status=="已处理"){
      // toastr.warning("该请求已被处理，无法重复处理");
       swal({
           title: "该请求已被处理，无法重复处理!",
           text: "",
           icon: "warning",
           button: "确定",
       });
   }
   else{
       var url;
       if(type=="pass"){
           url = "/review/pass";
       }
       if(type=="refuse")
           url = "/review/refuse";

       submitDeal(date,url);
   }
    }
}
function submitDeal(data,url){

    $.ajax({
        url:url,
        contentType: 'application/json;charset=utf-8',
        type: 'post',
        async:true,
        data: JSON.stringify(data),
        success: function(data){
            $("#rightContainer").html(data);
        },
        error: function() {
            swal({
                title: "操作失败",
                text: "",
                icon: "error",
                button: "确定",
            });
        }
    });

}

